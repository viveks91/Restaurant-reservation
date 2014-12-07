package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import models.Person;
import dao.PersonDAO;

// /rest/user
@Path("/admin")
public class AdminWebService {
	
	PersonDAO psnDao = new PersonDAO();

	// /rest/user
/*	@GET
	@Produces("application/json")
	@Path("/")
	public List<User> getAllUsers() {
		return dao.findAllUsers();
	}
	@GET
	@Produces("application/json")
	@Path("/{id}")
	public User getUserForId(@PathParam("id") int id) {
		return dao.findUserById(id);
	}
	@DELETE
	@Path("/{id}")
	public void deleteUserForId(@PathParam("id") int id) {
		dao.deleteUser(id);
	}*/
	@POST
	@Path("/create")
	@Consumes("application/json")
	public String createPerson(Person newAdmin) {
		if (psnDao.findByUserName(newAdmin.getUserName())== null) {
			psnDao.create(newAdmin);
			return "created";
		}
		return "exists";
	}
	
/*	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public void updateUser(@PathParam("id") int id, User newUser) {
		dao.updateUser(id, newUser);
	}*/
}

