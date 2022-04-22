/**
 * 
 */
package fr.fms.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

/**
 * @author Stagiaires09
 *
 */
public interface  IShopBusiness{	
	public void addToCart(Article article); //ajoute un article au panier
	public void removeFromCart(Article article); //retire un article du panier
	ArrayList<Article> consultCart(); //retourne le panier par l'id de l'utilisateur
	public ArrayList<Category> getAllCategory();
	public ArrayList<Article> getAllArticle();
	public ArrayList<User>getAllUser();
	public ArrayList<Article> getArticlesByCategory(int idCategory);
	public void order(); //passe la commande
}
