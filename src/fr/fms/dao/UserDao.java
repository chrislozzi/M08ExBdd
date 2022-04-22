/**
 * 
 */
package fr.fms.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import fr.fms.entities.User;

/**
 * @author Stagiaires09
 *
 */
public class UserDao implements Dao<User>{
	private static final String CREATE = "INSERT INTO T_Users(login, password) VALUES (?,?);";
	private static final String SELECT = "SELECT * FROM T_Users WHERE IdUser = ?;";
	private static final String UPDATE = "UPDATE T_Users SET login = ?, password = ? WHERE IdUser=?;";
	private static final String DELETE = "DELETE FROM T_Users WHERE IdUser=?";
	private static final String SELECT_ALL = "SELECT * FROM T_Users;";
	private static  ArrayList<User> users;
	//private BddConnection bddConection;
	
	public UserDao(){
		}


	@Override
	public void create(User obj)  {		
		try(PreparedStatement ps = connection.prepareStatement(CREATE)){	
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());				
				ps.executeUpdate();

		}
		catch(SQLException e) {
			
			System.out.println("Echec de la création de l'article");
		}
	}

	@Override
	public User read(int id)  {
		User user = null;				
		try(PreparedStatement ps = connection.prepareStatement(SELECT)){	
			ps.setInt(1,id);
			try(ResultSet resultSet =ps.executeQuery()) {
				while(resultSet.next()) {					
					int rsIdUser = resultSet.getInt("IdUser");
					String rsLogin  = resultSet.getString("Login");
					String rsPassword = resultSet.getString("Password");
					user = new User(rsIdUser, rsLogin, rsPassword);
				} 		
			}
		}
		catch(SQLException e) {
			System.out.println("Erreur de lecture");
		}
		return user;
	}	

	@Override
	public boolean update(User obj) {
		boolean updated = false;		
		try(PreparedStatement ps = connection.prepareStatement(UPDATE)){	
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());				
			ps.setInt(3, obj.getIdUser());					
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
			System.out.println("Echec de la suppression du User");
		}
		return deleted;
	}

	@Override
	public ArrayList<User> readAll() {
		users = new ArrayList<User>();
		try(Statement statement = connection.createStatement()){ //objet transportant la requete sql
			try(ResultSet resultSet =statement.executeQuery(SELECT_ALL)) { //ResultSet de java.sql
				while(resultSet.next()) {
					int rsIdUser = resultSet.getInt("IdUser");
					String rsLogin  = resultSet.getString("Login");
					String rsPassword = resultSet.getString("Password");				
					users.add((new User(rsIdUser, rsLogin, rsPassword)));
				}

			}

		}
		catch(SQLException e) {
			System.out.println("Erreur de lecture");
		}
		return users;
	}

}
