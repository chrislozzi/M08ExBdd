/**
 * 
 */
package fr.fms.dao;

/**
 * @author Stagiaires09
 *
 */
public class DAOFactory {
		
	public static UserDao getUserDao() {
		return new UserDao();
	}
	public static ArticleDao getArticleDao() {
		return  new ArticleDao();
	}
	public static CategoryDao getCategoryDao() {
		return new CategoryDao();
	}
}
