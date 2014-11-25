package vivek.S03.jdbc.DAO;

import vivek.S03.jdbc.Models.Cast;
import vivek.S03.jdbc.Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CastDAO {

	public void insert (Cast cast) {

		String insert = "insert into Cast(characterName, movie, actor) " +
				"values (?, ?, ?)";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, cast.getCharecterName());
			statement.setInt(2, cast.getMovie());
			statement.setInt(3, cast.getActor());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}


	public List<Cast> selectAll() {

		String selectAll = "select * from Cast";
		Connection connection = Connect.getInstance().getConnection();
		List<Cast> casts = new ArrayList<Cast>();


		try {
			ResultSet rs = connection.createStatement().executeQuery(selectAll);
			while(rs.next()) {
				Cast cast = structureCast(rs);
				casts.add(cast); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return casts;
	}

	public List<Cast> selectForActor (Integer actorId) {

		String selectActor = "select * from Cast where actor=?";
		Connection connection = Connect.getInstance().getConnection();
		List<Cast> casts = new ArrayList<Cast>();

		try {
			PreparedStatement statement = connection.prepareStatement(selectActor);
			statement.setInt(1, actorId);
			ResultSet rs = connection.createStatement().executeQuery(selectActor);
			while(rs.next()) {
				Cast cast = structureCast(rs);
				casts.add(cast); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return casts;
	}

	public List<Cast> selectForMovie(Integer movieId) {

		String selectMovie = "select * from Cast where movie=?";
		Connection connection = Connect.getInstance().getConnection();
		List<Cast> casts = new ArrayList<Cast>();

		try {
			PreparedStatement statement = connection.prepareStatement(selectMovie);
			statement.setInt(1, movieId);
			ResultSet rs = connection.createStatement().executeQuery(selectMovie);
			while(rs.next()) {
				Cast cast = structureCast(rs);
				casts.add(cast); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return casts;
	}


	public void update (String charecter, Integer actorId, Integer movieId) {

		String update = "update Comment set characterName=?" +
				" where actor=? and movie=?";
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, charecter);
			statement.setInt(2, actorId);
			statement.setInt(3, movieId);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void delete (Integer actorId, Integer movieId) {

		String delete = "delete from Cast where actor=? and movie=?";
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, actorId);
			statement.setInt(2, movieId);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void deleteMovie (Integer movieId) {

		String delete = "delete from Cast where movie=?";
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, movieId);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void deleteActor (Integer ActorId) {

		String update = "update Cast, set actor = NULL, where actor=?";
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setInt(1, ActorId);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	private Cast structureCast(ResultSet rs) throws SQLException {

		if(rs.next()) {
			String characterName = rs.getString("characterName");
			Integer movieId = rs.getInt("movieId");
			Integer actorId = rs.getInt("actorId");
			return new Cast(characterName, movieId, actorId);
		} 

		return null;
	}

}

