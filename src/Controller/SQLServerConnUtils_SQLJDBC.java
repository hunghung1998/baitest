package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLServerConnUtils_SQLJDBC {
	 public static Connection getSQLServerConnection_SQLJDBC() 
	            throws ClassNotFoundException, SQLException {
		 
		 	String hostName = "localhost";	        
	        String database = "Sales_Manager";
	        String userName = "sa";
	        String password = "123456";
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	        String connectionURL = "jdbc:sqlserver://" + hostName + ":1433" 
	                + ";databaseName=" + database;
	 
	        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
	        return conn;
	 }
}
