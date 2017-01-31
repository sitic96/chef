package db;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by sitora on 26.01.17.
 */
public class Connector {
    private static final MysqlDataSource DATA_SOURCE;

    static {
        DATA_SOURCE = new MysqlDataSource();
        DATA_SOURCE.setUser("root");
        DATA_SOURCE.setPassword("root");
        DATA_SOURCE.setPort(8889);
        DATA_SOURCE.setDatabaseName("chief");
        DATA_SOURCE.setServerName("localhost");
    }

    public static Connection connect() throws SQLException {
        return DATA_SOURCE.getConnection();
    }

    public static MysqlDataSource getDataSource() {
        return DATA_SOURCE;
    }

    public static void close(Connection connection) throws SQLException {
        connection.close();
    }
}
