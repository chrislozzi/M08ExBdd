/**
 * 
 */
package fr.fms.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import fr.fms.dao.DAOFactory;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;

/**
 * @author Stagiaires09
 *
 */
public class IShopBusinessImpl implements IShopBusiness{
	private HashMap<Integer, Article> cart = new HashMap<Integer, Article>();

	@Override
	public void addToCart(Article article) {
		if(cart.containsKey(article.getIdArticle())) {
		
		cart.get(article.getIdArticle()).setQuantity(article.getQuantity()+1);
		
		}else{
			cart.put(article.getIdArticle(), article);
		}

	}

	@Override
	public void removeFromCart(Article article) {
		int quantity = article.getQuantity()-1;
		if(0 < quantity) {
			cart.get(article.getIdArticle()).setQuantity(article.getQuantity()-1);
		}
		else cart.remove(article.getIdArticle());

	}

	@Override
	public ArrayList<Article> consultCart() {
		ArrayList<Article> cartToList= null;
		cartToList = cart.values().stream().collect(
				Collectors.toCollection(ArrayList::new));
		return  cartToList; 
	}

	@Override
	public void order() {
		// TODO Auto-generated method stub
		cart.clear();
	}

	@Override
	public ArrayList<Article> getAllArticle() {
		return DAOFactory.getArticleDao().readAll();
	}

	@Override
	public ArrayList<Category> getAllCategory() {
		return DAOFactory.getCategoryDao().readAll();
	}

	@Override
	public ArrayList<User> getAllUser() {
		return DAOFactory.getUserDao().readAll();
	
	}

	@Override
	public ArrayList<Article> getArticlesByCategory(int idCategory) {
		return DAOFactory.getArticleDao().findByCategory(idCategory);
	}





}
