package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import models.User;
import dao.UserDAO;
import dao.AddressDAO;

// /rest/user
@Path("/user")
public class UserWebService {
	
	UserDAO usrDao = new UserDAO();
	AddressDAO addrDao = new AddressDAO();
	
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
	@Path("/")
	@Consumes("application/json")
	public void createUser(User newUser) {
		System.out.print(newUser.getFirstName());
	//	usrDao.createUser(newUser);
	}
/*	@PUT
	@Path("/{id}")
	@Consumes("application/json")
	public void updateUser(@PathParam("id") int id, User newUser) {
		dao.updateUser(id, newUser);
	}*/
}