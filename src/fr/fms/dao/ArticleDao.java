/**
 * 
 */
package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Article;

/**
 * @author Stagiaires09
 *
 */
public class ArticleDao implements Dao<Article>{
	private static final String CREATE = "INSERT INTO T_Articles(Description, Brand, UnitaryPrice) VALUES (?,?,?);";
	private static final String SELECT = "SELECT * FROM T_Articles WHERE IdArticle = ?;";
	private static final String UPDATE = "UPDATE T_Articles SET description = ?, brand = ?, unitaryPrice =? WHERE IdArticle = ?;";
	private static final String DELETE = "DELETE FROM T_Articles WHERE IdArticle=?";
	private static final String SELECT_ALL = "SELECT * FROM T_Articles;";
	private static  ArrayList<Article> articles;

	public ArticleDao(){
	}

	@Override
	public void create(Article obj) {		
		try(PreparedStatement ps = connection.prepareStatement(CREATE)){	
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitaryPrice());					
			if(ps.executeUpdate() !=1) throw new RuntimeException("Echec: auncune création d'article effectuée");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Article read(int id) {
		Article article = null;				
		try(PreparedStatement ps = connection.prepareStatement(SELECT)){	
			ps.setInt(1,id);
			try(ResultSet resultSet =ps.executeQuery()) {
				while(resultSet.next()) {					
					int rsIdArticle = resultSet.getInt(1);
					String rsDescription  = resultSet.getString(2);
					String rsBrand = resultSet.getString(3);
					double rsPrice = resultSet.getDouble(4);
					article = new Article(rsIdArticle, rsDescription, rsBrand, rsPrice);
				} 		
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Erreur de lecture");
		}
		return article;
	}	

	@Override
	public boolean update(Article obj) {
		boolean updated = false;		
		try(PreparedStatement ps = connection.prepareStatement(UPDATE)){	
			ps.setString(1, obj.getDescription());
			ps.setString(2, obj.getBrand());
			ps.setDouble(3, obj.getUnitaryPrice());					
			ps.setInt(4, obj.getIdArticle());					
			if(ps.executeUpdate() ==1) updated = true;
		}

		catch(SQLException e) {
			throw new RuntimeException("Echec de la mise à jour");
		}
		return updated;
	}
	
	 
	@Override
	public boolean delete(int id) {
		boolean deleted =false;
		try(PreparedStatement ps = connection.prepareStatement(DELETE)){	
			ps.setInt(1,id);
			ps.executeUpdate();
			if(ps.executeUpdate() ==1) deleted = true;
		}

		catch(SQLException e) {
			e.printStackTrace();
		}
		return deleted;
	}

	@Override
	public ArrayList<Article> readAll() {
		articles = new ArrayList<Article>();
		try(Statement statement = connection.createStatement()){ //objet transportant la requete sql
			try(ResultSet resultSet =statement.executeQuery(SELECT_ALL)) { //ResultSet de java.sql
				while(resultSet.next()) {
					int rsIdArticle = resultSet.getInt(1);
					String rsDescription  = resultSet.getString(2);
					String rsBrand = resultSet.getString(3);
					double rsPrice = resultSet.getDouble(4);
					articles.add((new Article(rsIdArticle, rsDescription, rsBrand, rsPrice)));
				}

			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return articles;
	}

}
