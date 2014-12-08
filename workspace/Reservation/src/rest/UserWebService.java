package rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

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
	@Resource
	private WebServiceContext context;
	
	public WebServiceContext getContext() {
		return context;
	}

	public void setContext(WebServiceContext context) {
		this.context = context;
	}

	//rest/user/login
	@POST
	@Consumes("application/json")
	@Path("/login")
	
	public String loginFunc (Login login, @Context HttpServletRequest req) {
		String userName = login.userName;
		String password = login.password;
		Person prsn = psnDao.findByUserName(userName);
		if (prsn == null)  {
			return "fail";
		}
		else if (prsn.getPassword().equals(password)){
			/*MessageContext ctx = context.getMessageContext();
			HttpSession session =((javax.servlet.http.HttpServletRequest)ctx.get(MessageContext.SERVLET_REQUEST)).getSession();
			session.setAttribute("user", prsn);*/
			req.getSession().setAttribute("user", prsn);
			return "pass";
		}
		else {
			return "fail";
		}
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
	public String createUser (User newUser, @Context HttpServletRequest req) {
		System.out.print(newUser.getAddressId());
		if (usrDao.findByUserName(newUser.getUserName())== null) {
			User usr = usrDao.createUser(newUser);
			req.getSession().setAttribute("user", usr);
			return "user created";
		}
			return "exists";
	}
	
	@POST
	@Path("/createAdmin")
	@Consumes("application/json")
	public String createPerson(Person newAdmin, @Context HttpServletRequest req) {
		if (psnDao.findByUserName(newAdmin.getUserName())== null) {
			Person person = psnDao.create(newAdmin);
			req.getSession().setAttribute("user", person);
			return "admin created";
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
