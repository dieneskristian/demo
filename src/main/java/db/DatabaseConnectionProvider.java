package db;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;

public class DatabaseConnectionProvider {
	
	private static BasicDataSource ds = new BasicDataSource();
	
	static {
        ds.setUrl("jdbc:h2:~/test");
        ds.setUsername("sa");
        ds.setPassword("");
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(5);
    }
	
	public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
	
}
