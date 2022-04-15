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

	public UserDao(){
	}


	@Override
	public void create(User obj) {		
		try(PreparedStatement ps = connection.prepareStatement(CREATE)){	
			ps.setString(1, obj.getLogin());
			ps.setString(2, obj.getPassword());				
			if(ps.executeUpdate() !=1) throw new RuntimeException("Echec: auncune création de User effectuée");

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User read(int id) {
		User User = null;				
		try(PreparedStatement ps = connection.prepareStatement(SELECT)){	
			ps.setInt(1,id);
			try(ResultSet resultSet =ps.executeQuery()) {
				while(resultSet.next()) {					
					int rsIdUser = resultSet.getInt(1);
					String rsLogin  = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);
					User = new User(rsIdUser, rsLogin, rsPassword);
				} 		
			}
		}
		catch(SQLException e) {
			throw new RuntimeException("Erreur de lecture");
		}
		return User;
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
			//throw new RuntimeException("Echec de la suppression de l'User");
			e.printStackTrace();
		}
		return deleted;
	}

	@Override
	public ArrayList<User> readAll() {
		users = new ArrayList<User>();
		try(Statement statement = connection.createStatement()){ //objet transportant la requete sql
			try(ResultSet resultSet =statement.executeQuery(SELECT_ALL)) { //ResultSet de java.sql
				while(resultSet.next()) {
					int rsIdUser = resultSet.getInt(1);
					String rsLogin  = resultSet.getString(2);
					String rsPassword = resultSet.getString(3);				
					users.add((new User(rsIdUser, rsLogin, rsPassword)));
				}

			}

		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

}
