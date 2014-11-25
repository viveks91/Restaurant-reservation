package vivek.S03.jdbc.Managers;

import vivek.S03.jdbc.DAO.CastDAO;
import vivek.S03.jdbc.DAO.CommentDAO;
import vivek.S03.jdbc.DAO.MovieDAO;
import vivek.S03.jdbc.Models.Movie;

import java.util.List;

public class MovieManager {
	private MovieDAO movieDao = new MovieDAO();
	private CommentDAO commentDao = new CommentDAO();
	private CastDAO castDao = new CastDAO();

	public void createMovie(Movie movie) {

		movieDao.insert (movie);
	}

	public List<Movie> readAllMovies() {

		return movieDao.selectAll();
	}

	public Movie readMovie(Integer movieId) {

		return movieDao.select(movieId);
	}

	public void updateMovie(Integer movieId, Movie movie) {

		movieDao.update (movieId, movie);
	}

	public void deleteMovie(Integer movieId) {

		// This function cascades delete action in comments and cast tables as well
		commentDao.deleteMovie(movieId);
		castDao.deleteMovie(movieId);
		movieDao.delete (movieId);
	}

	public MovieDAO getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDAO movieDao) {
		this.movieDao = movieDao;
	}
}
