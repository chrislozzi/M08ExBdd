package fr.fms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import fr.fms.business.IShopBusiness;
import fr.fms.business.IShopBusinessImpl;
import fr.fms.dao.ArticleDao;
import fr.fms.dao.BddConnection;
import fr.fms.dao.CategoryDao;
import fr.fms.dao.DAOFactory;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.User;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import fr.fms.entities.User;


public class MyShopApp {	
	private static IShopBusinessImpl myShop;
	private static ArrayList<Article> listArticle;
	private static ArrayList<Category> listCategory;
	
	private static User customer;
	private static Scanner scan = new Scanner(System.in);	
	/**
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {		
		boolean authentified =false;
		myShop = new IShopBusinessImpl();
		listArticle = myShop.getAllArticle();
		welcome();
		System.out.println("Merci de bien vouloir vous identifier");
		System.out.println("--------------------------------------------");
		CategoryDao catD = new CategoryDao();
		System.out.println(catD.read(1).toString());
		while(true) 
		{
			try {
//				while(!authentified)
//				{
//					System.out.println("Saisissez votre login:");
//					String login = scan.next();
//					System.out.println("Saisissez votre mot de passe:");
//					String password = scan.next();
//					customer = new User(login, password);
//
//					authentified = authentication(customer);
//					if(authentified) System.out.println("Bienvenue : " + customer.getLogin());
//					else System.out.println("Erreur de Login ou de mot de passe");	
//				}

				displayArticles(0);
				int action = 0;
				while(action != 7) 
				{	

					System.out.println("\n1:Afficher les articles - 2:Afficher les catégories - 3:Mettre un article dans le panier"
									 + " - 4:Afficher le panier - 5:Retirer un article du panier - 6:Passer la commande - 7:sortir");							
					action = scan.nextInt();
					int idArticle;
					
					switch(action) {							
					case 1 : displayArticles(0);													
					break;
					
					case 2 :
						System.out.println(myShop.getAllCategory().toString());
						System.out.println("saisissez l'identifiant de la catégorie");
						int idCategory = scan.nextInt();
						displayArticles(idCategory-1);											
					break;
					
					case 3 : System.out.println("saisissez l'identifiant de l'article");
					idArticle = scan.nextInt();
					Article article = listArticle.get(idArticle-2);
					myShop.addToCart(article);
					displayCart();
					break;
					
					case 4 : displayCart();
					break;
					
					case 5 : System.out.println("saisissez l'identifiant de l'article à retirer du panier");
					idArticle = scan.nextInt();
					Article article1 = listArticle.get(idArticle-2);
					myShop.removeFromCart(article1);
					displayCart();
					break;
					
					case 6 :
						
						
						
					break;
					
					case 7 : System.out.println("sortie" + "\n");
					break;
					
					default : System.out.println("mauvaise saisie");							
					}
				}
			
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		}
	}
	
	private static void welcome() {
		System.out.println();
		System.out.println("********************************************");
		System.out.println("       BIENVENU DANS MA BOUTIQUE V1         ");
		System.out.println("********************************************");		
		System.out.println();
	}
	
	private static void initBank() {			
	}
	//vérifie l'existence de l'utilisateur en base
	private static boolean authentication(User customer) throws Exception {
		Boolean Authorised = false;
		ArrayList<User> users =  myShop.getAllUser();
		for (User user : users) {
			if(user.isAuthorised(customer)) Authorised=true;
		}
		return Authorised;
	}
	//Affiche tous les articles
	private static void displayArticles(int idCategory) {
		ArrayList<Article>  listToDisplay = listArticle;
		if(idCategory != 0) listToDisplay =myShop.getArticlesByCategory(idCategory);
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
		System.out.format("|%-4s|%-30s|%-30s|%-10s|\n","Id", "Description", "Marque", "Prix");
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
		
		listToDisplay.forEach(article -> {			
			System.out.format("|%-4d|%-30s|%-30s|%-10.2f|\n",article.getIdArticle(),
															 article.getDescription(),
															 article.getBrand(),
															 article.getUnitaryPrice())
			;});
		
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
	}
	private static void displayCart() {
		ArrayList<Article> listCart = myShop.consultCart();
		System.out.format("%-4s%-30s%-30s%-10s%-8s \n","+----", "+------------------------------", "+------------------------------+", "----------+","--------+");
		System.out.format("|%-4s|%-30s|%-30s|%-10s|%-8s|\n","Id", "Description", "Marque", "Prix", "Quantité");
		System.out.format("%-4s%-30s%-30s%-10s%-8s \n","+----", "+------------------------------", "+------------------------------+", "----------+","--------+");
		
		listCart.forEach(article -> {			
			System.out.format("|%-4d|%-30s|%-30s|%-10.2f|%-8d|\n",article.getIdArticle(),
																  article.getDescription(),
																  article.getBrand(),
																  article.getUnitaryPrice(), 
																  article.getQuantity())
			;});
		
		System.out.format("%-4s%-30s%-30s%-10s%-8s \n","+----", "+------------------------------", "+------------------------------+", "----------+","--------+");
		
	}
}
