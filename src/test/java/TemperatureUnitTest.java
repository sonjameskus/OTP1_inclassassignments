
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureUnitTest {

    @Test
    void constructorStoresUnitInformation() {
        TemperatureUnit unit =
                new TemperatureUnit(1, "Celsius", "°C");

        assertEquals(1, unit.getId());
        assertEquals("Celsius", unit.getName());
        assertEquals("°C", unit.getSymbol());
    }

    @Test
    void storesFahrenheitUnit() {
        TemperatureUnit unit =
                new TemperatureUnit(2, "Fahrenheit", "°F");

        assertEquals(2, unit.getId());
        assertEquals("Fahrenheit", unit.getName());
        assertEquals("°F", unit.getSymbol());
    }

    @Test
    void storesKelvinUnit() {
        TemperatureUnit unit =
                new TemperatureUnit(3, "Kelvin", "K");

        assertEquals(3, unit.getId());
        assertEquals("Kelvin", unit.getName());
        assertEquals("K", unit.getSymbol());
    }
}
