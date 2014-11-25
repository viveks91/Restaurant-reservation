package vivek.S03.jdbc.DAO;

import vivek.S03.jdbc.Models.Comment;
import vivek.S03.jdbc.Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAO {

	public void insert (Comment comment) {

		String insert = "insert into Comment(comment, date, user, movie) " +
				"values (?, ?, ?, ?)";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, comment.getComment());
			statement.setString(2, comment.getDate());
			statement.setString(3, comment.getUser());
			statement.setInt(4, comment.getMovie());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public List<Comment> selectAll() {

		String selectAll = "select * from Comment";
		Connection connection = Connect.getInstance().getConnection();
		List<Comment> comments = new ArrayList<Comment>();

		try {
			ResultSet rs = connection.createStatement().executeQuery(selectAll);
			while(rs.next()) {
				Comment aComment = structureComment(rs);
				comments.add(aComment); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return comments;
	}

	public List<Comment> selectForUsername(String username) {

		String sql = "select * from Comment where username=?";
		Connection connection = Connect.getInstance().getConnection();
		List<Comment> comments = new ArrayList<Comment>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, username);
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while(rs.next()) {
				Comment aComment = structureComment(rs);
				comments.add(aComment); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return comments;
	}

	public List<Comment> selectForMovie(Integer movieId) {

		String sql = "select * from Comment where movie=?";
		Connection connection = Connect.getInstance().getConnection();
		List<Comment> comments = new ArrayList<Comment>();

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, movieId);
			ResultSet rs = connection.createStatement().executeQuery(sql);
			while(rs.next()) {
				Comment aComment = structureComment(rs);
				comments.add(aComment); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return comments;
	}


	public void update (Integer movie, String user, Comment comment) {

		String update = "update Comment set comment=?, date=? where movie=? and user=?";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, comment.getComment());
			statement.setString(2, comment.getDate());
			statement.setInt(3, movie);
			statement.setString(4, user);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void updateUser (String username, String updatedUsername) {

		String update = "update Comment, set username=?, where username=?";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, updatedUsername);
			statement.setString(2, username);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void deleteUser (String user) {

		String update = "update Comment set user=NULL where user=?";
		Connection connection = Connect.getInstance().getConnection();

		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, user);

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void deleteMovie (Integer movieId) {

		String delete = "delete Comment where movie=?";
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

	public void delete (Integer movie, String user) {

		String delete = "delete from Comment where movie=? and user=?";
		Connection connection = Connect.getInstance().getConnection();
		try {
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setInt(1, movie);
			statement.setString(2, user);
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	private Comment structureComment(ResultSet rs) throws SQLException {

		if(rs.next()) {
			String comment = rs.getString("comment");
			String date = rs.getString("date");
			String user = rs.getString("username");
			Integer movie = rs.getInt("movie");
			return new Comment(comment, date, user, movie);
		} 

		return null;
	}

}
