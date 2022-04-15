/**
 * 
 */
package fr.fms;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import fr.fms.dao.CreateConfigFile;
import fr.fms.entities.Article;

/**
 * @author Stagiaires09
 *
 */
public class TestJdbc {

	//Exercie 2
	public static void main(String[] args) throws Exception{
		ArrayList<Article> articles = new ArrayList<Article>();
		CreateConfigFile confProperties = new CreateConfigFile();
		confProperties.Create();
		Properties prop = CreateConfigFile.readPropertiesFile("conf.properties");
		
		try {
			Class.forName(prop.getProperty("db.driver.class")); //enregistre la class auprès du driver manager
													  //autrement dit : charge le pilote
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		//récupère une connection à partir d'une url + id ° pwd
	
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.login"), prop.getProperty("db.password"))){ //Connection de java.sql
			String strSql = "SELECT * FROM T_Articles"; 
			try(Statement statement = connection.createStatement()){ //objet transportant la requete sql
				try(ResultSet resultSet =statement.executeQuery(strSql)) { //ResultSet de java.sql
					while(resultSet.next()) {
						int rsIdUser = resultSet.getInt(1);
						String rsDescription  = resultSet.getString(2);
						String rsBrand = resultSet.getString(3);
						double rsPrice = resultSet.getDouble(4);
						articles.add((new Article(rsIdUser, rsDescription, rsBrand, rsPrice)));
					}

				}

			}
			for(Article a :articles)
				System.out.println(a.getIdArticle() + " - " + a.getDescription() + " - " + a.getBrand()  + " - " + a.getUnitaryPrice()); 			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		//Exercice 3
		//ajout d'un nouvel article
		try(Connection connection = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.login"), prop.getProperty("db.password"))){ //Connection de java.sql
			articles.add(new Article(16, "S10", "Samsung", 2000));
			Article obj = articles.get(15);
			try(Statement statement = connection.createStatement()){
				String str = "INSERT INTO T_Articles(Description, Brand, UnitaryPrice)"
						+ "VALUE ('" + obj.getDescription() + "' ,'" + obj.getBrand() + "'," + obj.getUnitaryPrice() +" );";
				int row = statement.executeUpdate(str);
				if(row ==1) System.out.println("insertion ok");

			}
			//mis à jour du champs idCatégorie du nouvel article
			//			articles.add(new Article(16, "S10", "Samsung", 2000));
			//			int articleIdCategory =2;
			//			try(Statement statement = connection.createStatement()){				
			//				String str = "UPDATE T_Articles SET IdCategory =" + articleIdCategory + " where IdArticle =" + obj.getIdArticle() + ";";
			//				int row = statement.executeUpdate(str);
			//				if(row ==1) System.out.println("mise à jour effectuée");
			//			}
			//
			//			//suppression d'un article
			//			int idArticle = 1;
			//			try(Statement statement = connection.createStatement()){				
			//				String str = "DELETE FROM T_Articles where IdArticle =" + idArticle + ";";
			//				int row = statement.executeUpdate(str);
			//				if(row ==1) System.out.println("suppression effectuée");
			//			}

			//affiche toutes les informations sur un article (inner join)
			int idArticle2 = 5;
			try(Statement statement = connection.createStatement()){				
				String str = "SELECT IdArticle,T_Articles.Description,Brand,UnitaryPrice,T_Articles.IdCategory,CatName,T_Categories.Desciption "
						+ "from t_articles inner join t_categories where t_articles.IdCategory = t_categories.IdCategory and IdArticle =" + idArticle2 + ";";
				try(ResultSet resultSet = statement.executeQuery(str)) { //ResultSet de java.sql
					while(resultSet.next()) {
						System.out.println(resultSet.getInt("IdArticle") + " - " + resultSet.getString("Description") + " - " + resultSet.getString("Brand")  + " - " + resultSet.getDouble("UnitaryPrice")
						+ " - " + resultSet.getString("CatName") + " - " + resultSet.getString("Desciption"));

					}

				}

			}

			catch(SQLException e) {
				e.printStackTrace();
			}
 
		}

	}

}
