
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

public class Main extends Application {

    private final TemperatureConverter converter =
            new TemperatureConverter();

    private final TempRecordDAO tempRecordDAO =
            new TempRecordDAO();

    @Override
    public void start(Stage stage) {

        Label title = new Label("Temperature Converter");
        title.getStyleClass().add("title");

        Label subtitle = new Label(
                "Convert temperatures between units"
        );
        subtitle.getStyleClass().add("subtitle");

        Label fromLabel = new Label("FROM");
        fromLabel.getStyleClass().add("field-label");

        ComboBox<String> fromUnit = new ComboBox<>();
        fromUnit.getItems().addAll(
                "Celsius (°C)",
                "Fahrenheit (°F)",
                "Kelvin (K)"
        );
        fromUnit.setValue("Celsius (°C)");
        fromUnit.setMaxWidth(Double.MAX_VALUE);

        Label valueLabel = new Label("TEMPERATURE");
        valueLabel.getStyleClass().add("field-label");

        TextField input = new TextField();
        input.setPromptText("Enter temperature, e.g. 25");

        Label toLabel = new Label("TO");
        toLabel.getStyleClass().add("field-label");

        ComboBox<String> toUnit = new ComboBox<>();
        toUnit.getItems().addAll(
                "Celsius (°C)",
                "Fahrenheit (°F)",
                "Kelvin (K)"
        );
        toUnit.setValue("Fahrenheit (°F)");
        toUnit.setMaxWidth(Double.MAX_VALUE);

        Button convertButton = new Button("Convert temperature");
        convertButton.getStyleClass().add("convert-button");
        convertButton.setMaxWidth(Double.MAX_VALUE);

        Label result = new Label("Your result will appear here");
        result.getStyleClass().add("result");
        result.setWrapText(true);

        Label status = new Label("");
        status.getStyleClass().add("status");
        status.setWrapText(true);

        convertButton.setOnAction(event -> {
            try {
                double value = Double.parseDouble(
                        input.getText().trim()
                );

                if (!Double.isFinite(value)) {
                    result.setText("Enter a finite temperature.");
                    status.setText("");
                    return;
                }

                String from = fromUnit.getValue();
                String to = toUnit.getValue();

                double converted = convert(value, from, to);

                if (!Double.isFinite(converted)) {
                    result.setText("Could not convert this value.");
                    status.setText("");
                    return;
                }

                String fromSymbol = symbol(from);
                String toSymbol = symbol(to);

                result.setText(String.format(
                        Locale.US,
                        "%.2f %s = %.2f %s",
                        value, fromSymbol, converted, toSymbol
                ));

                int fromId = unitId(from);
                int toId = unitId(to);

                TempRecord record = new TempRecord(
                        value,
                        converted,
                        fromId,
                        toId
                );

                tempRecordDAO.save(record);

                status.setText(
                        "Conversion sent to the database."
                );

            } catch (NumberFormatException e) {
                result.setText("Please enter a valid number.");
                status.setText("");
            } catch (Exception e) {
                result.setText("Conversion failed.");
                status.setText(e.getMessage());
            }
        });

        VBox card = new VBox(
                12,
                title,
                subtitle,
                fromLabel,
                fromUnit,
                valueLabel,
                input,
                toLabel,
                toUnit,
                convertButton,
                result,
                status
        );

        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(28));
        card.getStyleClass().add("converter-card");

        VBox root = new VBox(card);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(24));
        root.getStyleClass().add("app-background");

        Scene scene = new Scene(root, 480, 650);

        var css = getClass().getResource("/style.css");
        if (css != null) {
            scene.getStylesheets().add(css.toExternalForm());
        }

        stage.setTitle("Temperature Converter");
        stage.setMinWidth(400);
        stage.setMinHeight(580);
        stage.setScene(scene);
        stage.show();
    }

    private double convert(double value, String from, String to) {
        if (from.equals(to)) {
            return value;
        }

        double celsius;

        if (from.startsWith("Celsius")) {
            celsius = value;
        } else if (from.startsWith("Fahrenheit")) {
            celsius = converter.fahrenheitToCelsius(value);
        } else {
            celsius = converter.kelvinToCelsius(value);
        }

        if (to.startsWith("Celsius")) {
            return celsius;
        } else if (to.startsWith("Fahrenheit")) {
            return converter.celsiusToFahrenheit(celsius);
        } else {
            return celsius + 273.15;
        }
    }

    private String symbol(String unit) {
        if (unit.startsWith("Celsius")) {
            return "°C";
        } else if (unit.startsWith("Fahrenheit")) {
            return "°F";
        }
        return "K";
    }

    private int unitId(String unit) {
        if (unit.startsWith("Celsius")) {
            return 1;
        } else if (unit.startsWith("Fahrenheit")) {
            return 2;
        }
        return 3;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
