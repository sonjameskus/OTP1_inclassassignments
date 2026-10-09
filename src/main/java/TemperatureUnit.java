public class TemperatureUnit {

    private int id;
    private String name;
    private String symbol;

    public TemperatureUnit(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }
}