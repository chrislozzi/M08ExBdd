/**
 * 
 */
package fr.fms.entities;

/**
 * @author Stagiaires09
 *
 */
public class User {
	private int idUser =0;
	private String login ="";
	private String password ="";
	/**
	 * @param idUser
	 * @param login
	 * @param password
	 */
	public User(int idUser, String login, String password) {
		super();
		this.idUser = idUser;
		this.login = login;
		this.password = password;
	}
	/**
	 * @param login
	 * @param password
	 */
	public User(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	/**
	 * @return the idUser
	 */
	public int getIdUser() {
		return idUser;
	}
	/**
	 * @param idUser the idUser to set
	 */
	public void setIdUser(int idUser) {
		this.idUser = idUser;
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
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", login=" + login + ", password=" + password + "]";
	}
	
}
