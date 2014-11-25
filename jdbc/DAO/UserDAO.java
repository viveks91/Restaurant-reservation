package vivek.S03.jdbc.DAO;

import vivek.S03.jdbc.Models.User;
import vivek.S03.jdbc.Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

	public void insert(User user){
		String insert = "insert into user (username, password, firstName, lastName, email, dob)"
				+ "values(?,?,?,?,?,?)";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstName());
			statement.setString(4, user.getLastName());
			statement.setString(5, user.getEmail());
			statement.setString(6, user.getDob());
			statement.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

	}

	public List<User> selectAll() {

		String selectAll = "select * from User";
		Connection connection = Connect.getInstance().getConnection();
		List<User> users = new ArrayList<User>();


		try {
			ResultSet rs = connection.createStatement().executeQuery(selectAll);
			while(rs.next()) {
				User user = structureUser(rs);
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return users;
	}


	public User select (String username) {

		String select = "select * from User where username=? limit 1";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			return structureUser(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return null;
	}


	private User structureUser (ResultSet rs) throws SQLException {

		if(rs.next()) {
			String userName = rs.getString("username");
			String password = rs.getString("password");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String email = rs.getString("email");
			String dob = rs.getString("dob");
			User user = new User(userName, password, firstName, lastName, email, dob);
			return user;
		} else {
			return null;
		}
	}


	public void update (String username, User user) {

		String update = "update User set password=?,firstName=?,lastName=?," +
				"email=?,dateOfBirth=? where username=?";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user.getPassword());
			statement.setString(2, user.getFirstName());
			statement.setString(3, user.getLastName());
			statement.setString(4, user.getEmail());
			statement.setString(5, user.getDob());
			statement.setString(6, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}


	public void delete (String username) {

		String delete = "delete from User where username=?";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, username);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

}

