package Utils;


/**
 * Getting a working connection
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {

	public static Connect connection = null;
	String connectionUrl = "jdbc:mysql://localhost:3306/restaurant_reservation";

	private Connect() {}

	public static Connect getInstance() {

		if(connection == null) connection = new Connect();
		return connection;

	}

	public Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionUrl, "root", null);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	public void closeConnection (Connection connection){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


}
