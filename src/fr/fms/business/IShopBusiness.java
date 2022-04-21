/**
 * 
 */
package fr.fms.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.dao.ArticleDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

/**
 * @author Stagiaires09
 *
 */
public interface  IShopBusiness{	
	public void addToCart(Article article); //ajoute un article au panier
	public void removeFromCart(Article article); //retire un article du panier
	ArrayList<Article> consultCart(); //retourne le panier par l'id de l'utilisateur
	public void order(); //passe la commande
	public ArrayList<Article> readAll();
}
