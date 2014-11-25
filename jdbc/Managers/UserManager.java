package vivek.S03.jdbc.Managers;

import vivek.S03.jdbc.DAO.CommentDAO;
import vivek.S03.jdbc.DAO.UserDAO;
import vivek.S03.jdbc.Models.User;

import java.util.List;

public class UserManager {

	private UserDAO userDao = new UserDAO();
	private CommentDAO commentDao = new CommentDAO();

	public void createUser(User user) {

		userDao.insert(user);
	}

	public List<User> readAllUsers() {

		return userDao.selectAll();
	}

	public User readUser(String username) {

		return userDao.select(username);
	}

	public void updateUser(String username, User user) {

		userDao.update (username, user);
		commentDao.updateUser(username, user.getUsername());
	}

	public void deleteUser(String username) {

		/// This function cascades delete action in comments table also
		commentDao.deleteUser(username);
		userDao.delete (username);

	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

}
