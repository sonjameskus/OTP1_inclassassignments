import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TemperatureConverterTest {

    @Test
    void testFahrenheitToCelsius() {
        TemperatureConverter converter = new TemperatureConverter();

        assertEquals(0, converter.fahrenheitToCelsius(32), 0.001);
        assertEquals(100, converter.fahrenheitToCelsius(212), 0.001);
        assertEquals(-40, converter.fahrenheitToCelsius(-40), 0.001);
    }

    @Test
    void testCelsiusToFahrenheit() {
        TemperatureConverter converter = new TemperatureConverter();

        assertEquals(32, converter.celsiusToFahrenheit(0), 0.001);
        assertEquals(212, converter.celsiusToFahrenheit(100), 0.001);
        assertEquals(-40, converter.celsiusToFahrenheit(-40), 0.001);
    }

    @Test
    void testIsExtremeTemperature() {
        TemperatureConverter converter = new TemperatureConverter();

        assertFalse(converter.isExtremeTemperature(-40));
        assertTrue(converter.isExtremeTemperature(-40.1));

        assertFalse(converter.isExtremeTemperature(50));
        assertTrue(converter.isExtremeTemperature(50.1));
    }

    @Test
    void testKelvinToCelsius() {
        TemperatureConverter converter = new TemperatureConverter();

        assertEquals(26.85, converter.kelvinToCelsius(300), 0.001);
        assertEquals(0, converter.kelvinToCelsius(273.15), 0.001);
        assertEquals(10, converter.kelvinToCelsius(283.15), 0.001);

    }
}