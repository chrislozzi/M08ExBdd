/**
 * 
 */
package fr.fms;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import fr.fms.dao.ArticleDao;
import fr.fms.dao.BddConnection;
import fr.fms.dao.UserDao;
import fr.fms.entities.Article;
import fr.fms.entities.User;

/**
 * @author Stagiaires09
 *
 */
public class Shop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArticleDao artDao = new ArticleDao();
		UserDao usrDao = new UserDao();
		Article art = null;
		User usr = null;
		
		
		//exercice 5.2
		//Création d'un nouvel article
		//artDao.create(new Article("Casque OCR","Facebook", 3000.0));
		
//		//Lecture d'un article
//		System.out.println(artDao.read(3));
		
		//Update d'un article
//		art =  artDao.read(3);
//		art.setBrand("Windows");
//		System.out.println(artDao.update(art));
		//update d'un article inexistant 
		
		
		//Supprime un article
//		System.out.println(artDao.delete(59));
		System.out.println(artDao.delete(987)); //tentative de suppression d'un article inexistant

		//Affiche tous les articles
//		artDao.readAll().toString();
		
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
		//exercie 6 et 7 fait dans le script shop.sql
		//exercice 9
		//Vérification de l'unicité du Singleton BddConnection
		BddConnection  bddC1 = BddConnection.getInstance();
		BddConnection  bddC2 = BddConnection.getInstance();
		System.out.println(bddC1.equals(bddC2));
		
		
	
	}
	

}
