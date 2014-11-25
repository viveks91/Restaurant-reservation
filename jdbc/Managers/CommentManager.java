package vivek.S03.jdbc.Managers;

import vivek.S03.jdbc.DAO.CommentDAO;
import vivek.S03.jdbc.Models.Comment;

import java.util.List;

public class CommentManager {

	private CommentDAO commentDao = new CommentDAO();

	public void createComment(Comment Comment) {

		commentDao.insert (Comment);
	}

	public List<Comment> readAllComments() {

		return commentDao.selectAll();
	}

	public List<Comment> readAllCommentsForUsername(String username) {

		return commentDao.selectForUsername(username);
	}

	public List<Comment> readAllCommentsForMovie(Integer movieId) {

		return commentDao.selectForMovie(movieId);
	}

	public void updateComment(Integer movieId, String username, Comment comment) {

		commentDao.update(movieId, username, comment);
	}

	public void deleteComment(Integer movieId, String username) {

		commentDao.delete(movieId, username);
	}


	public CommentDAO getCommentDao() {
		return commentDao;
	}

	public void setCommentDAO(CommentDAO commentDao) {
		this.commentDao = commentDao;
	}

}
