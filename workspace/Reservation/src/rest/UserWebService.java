package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import models.User;
import dao.UserDAO;

// /rest/user
@Path("/user")
public class UserWebService {
	
	UserDAO usrDao = new UserDAO();
	
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
	public String createUser (User newUser) {
//		System.out.print(newUser.getAddressId());
		if (usrDao.findByUserName(newUser.getUserName())== null) {
			usrDao.createUser(newUser);
//			System.out.println(newUser.getUserName());
			return newUser.getUserName();
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
