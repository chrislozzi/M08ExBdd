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
import fr.fms.dao.DAOFactory;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import fr.fms.entities.User;


public class MyShopApp {	
	private static ArticleDao articleDao;
	private static IShopBusinessImpl myShop;
	private static ArrayList<Article> listArticle;
	private static User customer;
	private static Scanner scan = new Scanner(System.in);		 
	public static void main(String[] args) throws Exception {		
		boolean authentified =false;
		myShop = new IShopBusinessImpl();
		listArticle = myShop.readAll();
		welcome();
		System.out.println("Merci de bien vouloir vous identifier");
		System.out.println("--------------------------------------------");

		while(true) 
		{
			try {
				while(!authentified)
				{
					System.out.println("Saisissez votre login:");
					String login = scan.next();
					System.out.println("Saisissez votre mot de passe:");
					String password = scan.next();
					customer = new User(login, password);

					authentified = authentication(customer);
					if(authentified) System.out.println("Bienvenue : " + customer.getLogin());
					else System.out.println("Erreur de Login ou de mot de passe");	
				}

				displayArticles();
				int action = 0;
				while(action != 6) 
				{	

					System.out.println("\n1:Afficher les articles - 2:Mettre un article dans le panier - 3:Afficher le panier"
									 + " - 4:Retirer un article du panier - 5:Passer la commande - 6:sortir");							
					action = scan.nextInt();
					int idArticle;
					
					switch(action) {							
					case 1 : displayArticles();													
					break;

					case 2 : System.out.println("saisissez l'identifiant de l'article");
					idArticle = scan.nextInt();
					Article article = listArticle.get(idArticle-1);
					myShop.addToCart(article);
					displayCart();
					break;

					case 3 : displayCart();
					break;

					case 4 : System.out.println("saisissez l'identifiant de l'article à retirer du panier");
					idArticle = scan.nextInt();
					Article article1 = listArticle.get(idArticle-1);
					myShop.removeFromCart(article1);
					displayCart();
					break;

					case 5 :;
					break;

					case 6 : System.out.println("sortie" + "\n");
					break;

					default : System.out.println("mauvaise saisie");							
					}
				}
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
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
		ArrayList<User> users =  DAOFactory.getUserDAO().readAll();
		for (User user : users) {
			if(user.isAuthorised(customer)) Authorised=true;
		}
		return Authorised;
	}
	//Affiche tous les articles
	private static void displayArticles() {
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
		System.out.format("|%-4s|%-30s|%-30s|%-10s|\n","Id", "Description", "Marque", "Prix");
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");

		listArticle.forEach(article -> {			
			System.out.format("|%-4d|%-30s|%-30s|%-10.2f|\n",article.getIdArticle(),article.getDescription(),article.getBrand(),article.getUnitaryPrice())
			;});

		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
	}
	private static void displayCart() {
		ArrayList<Article> listCart = myShop.consultCart();
		System.out.format("%-4s%-30s%-30s%-10s%-8s \n","+----", "+------------------------------", "+------------------------------+", "----------+","--------+");
		System.out.format("|%-4s|%-30s|%-30s|%-10s|%-8s|\n","Id", "Description", "Marque", "Prix", "Quantité");
		System.out.format("%-4s%-30s%-30s%-10s%-8s \n","+----", "+------------------------------", "+------------------------------+", "----------+","--------+");

		listCart.forEach(article -> {			
			System.out.format("|%-4d|%-30s|%-30s|%-10.2f|%-8d|\n",article.getIdArticle(),article.getDescription(),article.getBrand(),article.getUnitaryPrice(), article.getQuantity())
			;});

		System.out.format("%-4s%-30s%-30s%-10s%-8s \n","+----", "+------------------------------", "+------------------------------+", "----------+","--------+");
		
	}
}
