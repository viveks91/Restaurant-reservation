package vivek.S03.jdbc.DAO;

import vivek.S03.jdbc.Models.Actor;
import vivek.S03.jdbc.Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActorDAO {

	public void insert (Actor actor) {

		String insert = "insert into Actor(id, firstName, lastName, dob) " +
				"values (?, ?, ?, ?)";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, actor.getId());
			statement.setString(2, actor.getFirstName());
			statement.setString(3, actor.getLastName());
			statement.setString(4, actor.getDob());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public List<Actor> selectAll() {

		String selectAll = "select * from Actor";
		Connection connection = Connect.getInstance().getConnection();
		List<Actor> actors = new ArrayList<Actor>();

		try {
			ResultSet rs = connection.createStatement().executeQuery(selectAll);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String dob = rs.getString("dob");
				Actor actor = new Actor(id, firstName, lastName, dob);
				actors.add(actor);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return actors;
	}


	public Actor select (Integer id) {

		String select = "select * from Actor where id=? limit 1";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String dob = rs.getString("dob");
				Actor actor = new Actor(id, firstName, lastName, dob);
				return actor;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return null;
	}

	public void update(Integer id, Actor actor) {

		String update = "update Actor set firstName=?, lastName=?," +
				" dob=? where id=?";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, actor.getFirstName());
			statement.setString(2, actor.getLastName());
			statement.setString(3, actor.getDob());
			statement.setInt(4, actor.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void delete(Integer id) {

		String delete = "delete from Actor where id=?";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}
}
