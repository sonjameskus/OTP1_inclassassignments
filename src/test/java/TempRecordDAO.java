
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class TempRecordDAOTest {

    @Test
    void savesTemperatureRecordUsingPreparedStatement() throws Exception {
        Connection connection = mock(Connection.class);
        PreparedStatement statement = mock(PreparedStatement.class);

        when(connection.prepareStatement(anyString()))
                .thenReturn(statement);

        TempRecord record = new TempRecord(100.0, 37.78, 2, 1);
        TempRecordDAO dao = new TempRecordDAO();

        try (MockedStatic<MariaDBConnection> db =
                     mockStatic(MariaDBConnection.class)) {

            db.when(MariaDBConnection::connect)
                    .thenReturn(connection);

            dao.save(record);

            db.verify(MariaDBConnection::connect);
            verify(connection).prepareStatement(
                    contains("INSERT INTO temp_records"));
            verify(statement).setDouble(1, 100.0);
            verify(statement).setDouble(2, 37.78);
            verify(statement).setInt(3, 2);
            verify(statement).setInt(4, 1);
            verify(statement).setTimestamp(eq(5), any());
            verify(statement).executeUpdate();
            verify(statement).close();
            verify(connection).close();
        }
    }

    @Test
    void handlesDatabaseErrorWithoutThrowing() throws Exception {
        Connection connection = mock(Connection.class);

        when(connection.prepareStatement(anyString()))
                .thenThrow(new SQLException("Test database error"));

        TempRecordDAO dao = new TempRecordDAO();
        TempRecord record = new TempRecord(100.0, 37.78, 2, 1);

        try (MockedStatic<MariaDBConnection> db =
                     mockStatic(MariaDBConnection.class)) {

            db.when(MariaDBConnection::connect)
                    .thenReturn(connection);

            assertDoesNotThrow(() -> dao.save(record));

            verify(connection).prepareStatement(anyString());
            verify(connection).close();
        }
    }
}
