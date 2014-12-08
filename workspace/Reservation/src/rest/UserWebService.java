package rest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

import models.Person;
import models.User;
import dao.PersonDAO;
import dao.UserDAO;

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
	
	@POST
	@Path("/logout")
	public void logoutSession(@Context HttpServletRequest req) {
		req.getSession().invalidate();
	}
	
	@GET
	@Path("/view/{username}")
	public String getUser(@PathParam("username") String userName, @Context HttpServletRequest req) {
		User user1 = usrDao.findByUserName(userName);
		req.getSession().setAttribute("viewuser", user1);
		return "done";
	}
	
	
	@DELETE
	@Path("/delete")
	public void deleteUserForId(@Context HttpServletRequest req) {
		Person person = (Person)req.getSession().getAttribute("user");
		req.getSession().invalidate();
		psnDao.deleteByUserName(person.getUserName());
	}
	
	@POST
	@Path("/create")
	@Consumes("application/json")
	public String createUser (User newUser, @Context HttpServletRequest req) {
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
	
	@PUT
	@Path("/update")
	@Consumes("application/json")
	public void updateUser(User newUser, @Context HttpServletRequest req) {
		Person person = (Person)req.getSession().getAttribute("user");
		psnDao.updateFirstNameByUserName(person.getUserName(),newUser.getFirstName());
		psnDao.updateLastNameByUserName(person.getUserName(), newUser.getLastName());
		usrDao.updateByUserName(newUser.getUserName(), newUser.getPhoneNo(), newUser.getEmailId(), newUser.getAddressId());
	}
}
