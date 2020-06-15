package Client;


import DBConnection.DBConnectionMock;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class ClientPage {
    @Test
    void checkDoGet() throws SQLException {
        DBConnectionMock.initMock();

    }

}
