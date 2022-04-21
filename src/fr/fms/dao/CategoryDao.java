/**
 * 
 */
package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.fms.entities.Category;
import fr.fms.entities.Category;

/**
 * @author Stagiaires09
 *
 */
public class CategoryDao implements Dao<Category>{
	private static final String CREATE = "INSERT INTO T_Categories(CatName, Description) VALUES (?,?);";
	private static final String SELECT = "SELECT * FROM T_Categories WHERE IdCategory = ?;";
	private static final String UPDATE = "UPDATE T_Categories SET CatName = ?, Description = ? WHERE IdCategory = ?;";
	private static final String DELETE = "DELETE FROM T_Categories WHERE IdCategory=?";
	private static final String SELECT_ALL = "SELECT * FROM T_Categories;";
	private static  ArrayList<Category> categories;

	public CategoryDao(){
	}

	@Override
	public void create(Category obj){		
		try(PreparedStatement ps = connection.prepareStatement(CREATE)){	
			ps.setString(1, obj.getCatName());	
			ps.setString(2, obj.getDescription());
			if(ps.executeUpdate() !=1) throw new RuntimeException("Echec: auncune création de catégorie effectuée");

		}
		catch(SQLException e) {
			System.out.println("Echec de création de la catégorie");
		}
	}

	@Override
	public Category read(int id) {
		Category category = null;				
		try(PreparedStatement ps = connection.prepareStatement(SELECT)){	
			ps.setInt(1,id);
			try(ResultSet resultSet =ps.executeQuery()) {
				resultSet.next();				
					int rsIdCategory = resultSet.getInt("IdCategory");
					String rsCatName = resultSet.getString("CatName");
					String rsDescription  = resultSet.getString("Description");
					category = new Category(rsIdCategory, rsCatName, rsDescription);
			}
		}
		catch(SQLException e) {
			System.out.println("Erreur de lecture");
		}
		return category;
	}	

	@Override
	public boolean update(Category obj) {
		boolean updated = false;		
		try(PreparedStatement ps = connection.prepareStatement(UPDATE)){	
			ps.setString(1, obj.getCatName());
			ps.setString(2, obj.getDescription());				
			if(ps.executeUpdate() ==1) updated = true;
		}

		catch(SQLException e) {
			System.out.println("Echec de la mise à jour");
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
	public ArrayList<Category> readAll() {
		categories = new ArrayList<Category>();
		try(Statement statement = connection.createStatement()){ //objet transportant la requete sql
			try(ResultSet resultSet =statement.executeQuery(SELECT_ALL)) { //ResultSet de java.sql
				while(resultSet.next()) {
					int rsIdCategory = resultSet.getInt("IdCategory");
					String rsCatName = resultSet.getString("CatName");
					String rsDescription  = resultSet.getString("Description");
					
					categories.add((new Category(rsIdCategory, rsCatName ,rsDescription)));
				}

			}

		}
		catch(SQLException e) {
			System.out.println("Erreur de lecture");
		}
		return categories;
	}
}
