/**
 * 
 */
package fr.fms;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.BddConnection;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;
import fr.fms.forms.*;

/**
 * @author Stagiaires09
 *
 */
public class Shop {
	//	public static ArticleDao artDao = new ArticleDao();
	//	public static UserDao usrDao = new UserDao();
	//	public static Article art = null;
	//	public static User usr = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		ArticleDao artDao = new ArticleDao();
		//		UserDao usrDao = new UserDao();
		//		Article art = null;
		//		User usr = null;
		//exercice 6
		wellcome();


		Scanner scan = new Scanner(System.in);	
		System.out.println("Merci de bien vouloir vous identifier");
		System.out.println("--------------------------------------------");

		System.out.println("Saisissez votre login:");
		String login = scan.next();
		System.out.println("Saisissez votre mot de passe:");
		String password = scan.next();
		User visitor = new User(login, password);
		try {	
			if(authentication(visitor)) System.out.println("Bienvenue : " + visitor.getLogin());
			else System.out.println("Erreur de Login ou de mot de passe");


			if(new UserDao().readAll().contains(visitor)) 

				System.out.println("Bienvenue" + visitor.getLogin());
			new ArticleDao().readAll().toString();
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		//***********************************************************
		//exercice 5.2
		//Création d'un nouvel article
		//artDao.create(new Article("Casque OCR","Facebook", 3000.0));

		//Lecture d'un article
		//		System.out.println(artDao.read(3));

		//Update d'un article
		//		art =  artDao.read(3);
		//		art.setBrand("Windows");
		//		System.out.println(artDao.update(art));
		//update d'un article inexistant 


		//Supprime un article
		//		System.out.println(artDao.delete(59));
		//tentative de suppression d'un article inexistant
		//		System.out.println(artDao.delete(987));

		//Affiche tous les articles
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
		System.out.format("|%-4s|%-30s|%-30s|%-10s|\n","Id", "Description", "Marque", "Prix");
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");
		artDao.readAll().forEach(article -> {			
			System.out.format("|%-4d|%-30s|%-30s|%-10.2f|\n",article.getIdArticle(),article.getDescription(),article.getBrand(),article.getUnitaryPrice())
			;});
		System.out.format("%-4s%-30s%-30s%-10s \n","+----", "+------------------------------", "+------------------------------+", "----------+");




		//***********************************************************

		//Exercice 6
		//Création d'un utilisateur
		//		usr = new User("Toto", "mot2pass");
		//		usrDao.create(usr);

		//lecture d'un utilisateur
		//		System.out.println(usrDao.read(2));

		//Update d'un utilisateur
		//		usr = usrDao.read(5);
		//		usr.setPassword("pas2mot2pass");
		//		System.out.println(usrDao.update(usr));



		//gestion des doublons et exo 7 fait dans le script shop.sql
		//***********************************************************



		//exercice 9
		//Vérification de l'unicité du Singleton BddConnection		

		//		Connection  bddC3 = BddConnection.getConnection();
		//		Connection  bddC4 = BddConnection.getConnection();
		//	
		//		System.out.println(bddC3.equals(bddC4));
		//		System.out.println(bddC3.hashCode() == bddC4.hashCode());

		//		try {
		//			System.out.println(new ArticleDao().read(7));
		//		} catch (SQLException e) {
		//			// TODO Auto-generated catch block
		//			System.out.println(e.getMessage());
		//		}
		//		wellcome();

	}

	private static void wellcome() {
		System.out.println();
		System.out.println("********************************************");
		System.out.println("        BIENVENU DANS MA BOUTIQUE V1        ");
		System.out.println("********************************************");		
		System.out.println();
	}
	private static boolean authentication(User visitor) throws SQLException {
		Boolean Authorised = false;
		ArrayList<User> users =  new UserDao().readAll();
		for (User user : users) {
			if(user.isAuthorised(visitor)) Authorised=true;
		}
		return Authorised;
	}

}
