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
	private static final BddConnection INSTANCE = new BddConnection();

	private  BddConnection() {				
	};

	public static BddConnection getInstance() {
		return INSTANCE;
	}

	public  Connection getConnection() {		
		Connection connection = null;
		CreateConfigFile confProperties = new CreateConfigFile();

		try {
			confProperties.Create();		
			Properties p = null;		
			p = CreateConfigFile.readPropertiesFile("conf.properties");		
			Class.forName(p.getProperty("db.driver.class")); //enregistre la class auprès du driver manager
			connection = DriverManager.getConnection(p.getProperty("db.url"),p.getProperty("db.login"), p.getProperty("db.password"));

		} catch(ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		} catch(SQLException e2) {
			e2.printStackTrace();
		}

		return connection;

	}

}
