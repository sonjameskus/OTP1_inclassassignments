import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class TempRecordDAO {

    public void save(TempRecord record) {

        String sql = """
                INSERT INTO temp_records
                (input_value, result_value, from_unit_id, to_unit_id, created_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection = MariaDBConnection.connect();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, record.getInputValue());
            statement.setDouble(2, record.getResultValue());
            statement.setInt(3, record.getFromUnitId());
            statement.setInt(4, record.getToUnitId());
            statement.setTimestamp(5, new Timestamp(System.currentTimeMillis()));

            statement.executeUpdate();

            System.out.println("Temperature record saved!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}