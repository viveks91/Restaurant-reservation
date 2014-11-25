package vivek.S03.jdbc.DAO;

import vivek.S03.jdbc.Models.Movie;
import vivek.S03.jdbc.Util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO {

	public void insert (Movie movie) {

		String insert = "insert into Movie(id, title, posterImage, releaseDate) " +
				"values (?, ?, ?, ?)";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setInt(1, movie.getId());
			statement.setString(2, movie.getTitle());
			statement.setString(3, movie.getPosterImage());
			statement.setString(4, movie.getReleaseDate());

			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public List<Movie> selectAll() {

		String selectAll = "select * from Movie";
		Connection connection = Connect.getInstance().getConnection();
		List<Movie> movies = new ArrayList<Movie>();


		try {
			ResultSet rs = connection.createStatement().executeQuery(selectAll);
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String title = rs.getString("title");
				String posterImage = rs.getString("posterImage");
				String releaseDate = rs.getString("releaseDate");
				Movie movie = new Movie(id, title, posterImage, releaseDate);
				movies.add(movie);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return movies;
	}

	public Movie select(Integer id) {

		String select = "select * from Movie where id=? limit 1";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				String title = rs.getString("title");
				String posterImage = rs.getString("posterImage");
				String releaseDate = rs.getString("releaseDate");
				Movie movie = new Movie(id, title, posterImage, releaseDate);
				return movie;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}

		return null;
	}


	public void update (Integer id, Movie movie) {

		String update = "update Movie set title=?,posterImage=?,releaseDate=? where id=?";
		Connection connection = Connect.getInstance().getConnection();


		try {
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, movie.getTitle());
			statement.setString(2, movie.getPosterImage());
			statement.setString(3, movie.getReleaseDate());
			statement.setInt(4, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connect.getInstance().closeConnection(connection);
		}
	}

	public void delete (Integer id) {

		String delete = "delete from Movie where id=?";
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
