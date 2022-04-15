package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
//Exercice 4
public class CreateConfigFile{ //Valeur par défaut du fichier config.properties
public static final  String defaultDriverClass = "org.mariadb.jdbc.Driver";
public	static final String defaultUrl = "jdbc:mariadb://localhost:3306/Shop";
public	static final String defaultLogin = "root";
public	static final String defaultPassword = "fms2022";
private String driverClass;
private String url;
private String login;
private String password;
public static Properties p = new Properties();


	/**
	 * 
	 */
	public CreateConfigFile() {
		this.driverClass = defaultDriverClass;
		this.url = defaultUrl;
		this.login = defaultLogin;
		this.password = defaultPassword;
	}

	/**
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return p.getProperty(driverClass);
	}

	/**
	 * @param driverClass the driverClass to set
	 */
	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return p.getProperty(url);
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return p.getProperty(password);
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public void Create () throws IOException{
		//Renseingement des champs 
		try (OutputStream os = (new FileOutputStream("conf.properties"))) {
			p.setProperty("db.driver.class", defaultDriverClass);
			p.setProperty("db.url", defaultUrl);
			p.setProperty("db.login", defaultLogin);
			p.setProperty("db.password", defaultPassword);
			p.store(os, null); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new IOException("Erreur d'écriture dans le fichier properties");
		}
	
	}
	public static Properties readPropertiesFile(String fileName) throws IOException{
		 FileInputStream fis = null;
	      Properties prop = null;
	      try {
	         fis = new FileInputStream(fileName);
	         prop = new Properties();
	         prop.load(fis);
	      } catch(FileNotFoundException fnfe) {
	         throw new FileNotFoundException("Fichier introuvable");
	      } catch(IOException ioe) {
	         ioe.printStackTrace();
	      } finally {
	         fis.close();
	      }
	      return prop;
	}

}
