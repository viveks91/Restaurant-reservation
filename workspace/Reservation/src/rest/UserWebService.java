package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import models.Person;
import models.User;
import dao.UserDAO;
import dao.PersonDAO;

class Login {
	public String userName;
	public String password;
}
// /rest/user
@Path("/user")
public class UserWebService {
	
	UserDAO usrDao = new UserDAO();
	PersonDAO psnDao = new PersonDAO();
	
	//rest/user/login
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	@Path("/login")
	public Login loginFunc (Login login) {
		String userName = login.userName;
		String password = login.password;
		return login;
		//Person person = psnDao.findByUserName(login.userName);
	}
	
	/*@GET
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
		System.out.print(newUser.getAddressId());
		if (usrDao.findByUserName(newUser.getUserName())== null) {
			usrDao.createUser(newUser);
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
