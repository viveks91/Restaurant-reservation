package vivek.S03.jdbc.Managers;

import vivek.S03.jdbc.DAO.CastDAO;
import vivek.S03.jdbc.Models.Cast;

import java.util.List;

public class CastManager {

	private CastDAO castDao = new CastDAO();

	public void createCast(Cast cast) {

		castDao.insert(cast);
	}

	public List<Cast> readAllCast() {

		return castDao.selectAll();
	}

	public List<Cast> readAllCastForActor(Integer actorId) {

		return castDao.selectForActor(actorId);
	}

	public List<Cast> readAllCastForMovie(Integer movieId) {

		return castDao.selectForMovie(movieId);
	}

	public void updateCast(String charecter, Integer actorId, Integer movieId) {

		castDao.update (charecter, actorId, movieId);
	}

	public void deleteCast(Integer actorId, Integer movieId) {

		castDao.delete (actorId, movieId);
	}


	public CastDAO getCastDao() {
		return castDao;
	}

	public void setCastDao (CastDAO castDao) {
		this.castDao = castDao;
	}

}
