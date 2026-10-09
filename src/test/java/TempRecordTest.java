
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TempRecordTest {

    @Test
    void constructorStoresConversionValues() {
        TempRecord record =
                new TempRecord(25.0, 77.0, 1, 2);

        assertEquals(25.0, record.getInputValue(), 0.0001);
        assertEquals(77.0, record.getResultValue(), 0.0001);
        assertEquals(1, record.getFromUnitId());
        assertEquals(2, record.getToUnitId());
    }

    @Test
    void storesNegativeTemperature() {
        TempRecord record =
                new TempRecord(-40.0, -40.0, 1, 2);

        assertEquals(-40.0, record.getInputValue(), 0.0001);
        assertEquals(-40.0, record.getResultValue(), 0.0001);
    }

    @Test
    void storesSameSourceAndTargetUnit() {
        TempRecord record =
                new TempRecord(20.0, 20.0, 1, 1);

        assertEquals(record.getFromUnitId(),
                record.getToUnitId());
    }
}
