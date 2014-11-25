package vivek.S03.jdbc.Util;


/**
 * Getting a working connection
 * 
 */

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Connect {

	public static Connect connection = null;

	private Connect() {}

	public static Connect getInstance() {

		if(connection == null) connection = new Connect();
		return connection;

	}

	public Connection getConnection() {
		Connection connection = null;
		Context initContext;

		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/s03/assignment3");
			connection = ds.getConnection();

		} catch (SQLException | NamingException e) {
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
