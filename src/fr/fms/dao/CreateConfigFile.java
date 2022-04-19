package fr.fms.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
//Exercice 4
public class CreateConfigFile{ //Valeur par défaut du fichier config.properties
	private static final  String defaultDriverClass = "org.mariadb.jdbc.Driver";
	private	static final String defaultUrl = "jdbc:mariadb://localhost:3306/Shop";
	private	static final String defaultLogin = "root";
	private	static final String defaultPassword = "fms2022";
	private String driverClass;
	private String url;
	private String login;
	private String password;
	public static Properties propFile = new Properties();

	/**
	 * 
	 */
	public CreateConfigFile() {
		setDriverClass(defaultDriverClass);
		setUrl(url);
		setLogin(login);
		setPassword(defaultPassword);
	}

	/**
	 * @return the driverClass
	 */
	public String getDriverClass() {
		return propFile.getProperty(driverClass);
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
		return propFile.getProperty(url);
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
		return propFile.getProperty(password);
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	public void create () throws IOException{
		//Renseingement des champs du fichier properties
		try (OutputStream os = (new FileOutputStream("conf.properties"))) {
			propFile.setProperty("db.driver.class", defaultDriverClass);
			propFile.setProperty("db.url", defaultUrl);
			propFile.setProperty("db.login", defaultLogin);
			propFile.setProperty("db.password", defaultPassword);
			propFile.store(os, null); 
		} catch (IOException e) {
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
public static void main(String[] args) {
	CreateConfigFile confProperties = new CreateConfigFile();
	try {
		confProperties.create();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}
