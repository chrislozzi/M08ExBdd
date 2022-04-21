/**
 * 
 */
package fr.fms.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Stagiaires09
 *
 */
public class BddConnection {
	private static final CreateConfigFile confProperties = new CreateConfigFile();
	private static final BddConnection INSTANCE = new BddConnection(confProperties);	
	private   Connection connection = null;
	
	private  BddConnection(CreateConfigFile confProperties) {	
		Properties readPropFile = null;
		try {
							
			readPropFile = CreateConfigFile.readPropertiesFile("conf.properties");		
			Class.forName(readPropFile.getProperty("db.driver.class")); 
			connection = DriverManager.getConnection(readPropFile.getProperty("db.url"),
													readPropFile.getProperty("db.login"), 
													readPropFile.getProperty("db.password"));

		} catch(ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch(SQLException e2) {
			e2.printStackTrace();
		}
	}

	public static Connection getConnection() {
		
		return INSTANCE.connection;
	}

	

}
