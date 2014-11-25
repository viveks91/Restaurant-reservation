package vivek.S03.jdbc.Managers;

import vivek.S03.jdbc.DAO.ActorDAO;
import vivek.S03.jdbc.DAO.CastDAO;
import vivek.S03.jdbc.Models.Actor;

import java.util.List;

public class ActorManager {
	private ActorDAO actorDao = new ActorDAO();
	private CastDAO castDao = new CastDAO();

	public void createActor(Actor newActor) {

		actorDao.insert (newActor);
	}

	public List<Actor> readAllActors() {

		return actorDao.selectAll();
	}

	public Actor readActor(Integer actorId) {

		return actorDao.select (actorId);
	}

	public void updateActor(Integer actorId, Actor actor) {

		actorDao.update (actorId, actor);
	}

	public void deleteActor(Integer actorId) {

		//This function cascades delete action in cast table also
		castDao.deleteActor(actorId);
		actorDao.delete (actorId);

	}

	public ActorDAO getActorDao() {
		return actorDao;
	}

	public void setActorDao(ActorDAO actorDao) {
		this.actorDao = actorDao;
	}


}
