/**
 * 
 */
package fr.fms.dao;

/**
 * @author Stagiaires09
 *
 */
public class DAOFactory {
	public static UserDao getUserDAO() {
		return new UserDao();
	}
	public static ArticleDao getArticleDAO() {
		return new ArticleDao();
	}
}
