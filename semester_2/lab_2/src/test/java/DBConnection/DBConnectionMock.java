package DBConnection;

import java.sql.*;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class DBConnectionMock {
    static public void initMock() throws SQLException {
        Connection connection = mock(Connection.class);
        when(DriverManager.getConnection(anyString(), anyString(), anyString())).thenReturn(connection);
        ResultSet resultSetTable = mock(ResultSet.class);
        ResultSet checkResultSet = mock(ResultSet.class);
        Statement statement = mock(Statement.class);

        doNothing().when(connection).setAutoCommit(true);
        when(connection.createStatement()).thenReturn(statement);
        when(statement.executeQuery(anyString())).thenReturn(resultSetTable);
        when(statement.executeQuery(anyString())).thenReturn(checkResultSet);
        doNothing().when(statement).executeUpdate(anyString());
        when(resultSetTable.next()).thenReturn(true);
        when(checkResultSet.next()).thenReturn(false);
        when(checkResultSet.getString("role")).thenReturn("tour_agent");
    }
}
