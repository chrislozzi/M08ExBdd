package fr.fms.entities;

/**
 * @author Stagiaires09
 *
 */
public class Article {
	private int idArticle = 0;
	private String description = null;
	private String brand = null;
	private double unitaryPrice = 0.0;
	/**
	 * @param idArticle
	 * @param desription
	 * @param brand
	 * @param unitaryPrice
	 */
	public Article(int idArticle, String description, String brand, double unitaryPrice) {
		this.idArticle = idArticle;
		this.description = description;
		this.brand = brand;
		this.unitaryPrice = unitaryPrice;
	}
	/**
	 * @param idArticle
	 * @param desription
	 * @param brand
	 */
	public Article(String description, String brand, double unitaryPrice) {
		this.description = description;
		this.brand = brand;
		this.unitaryPrice = unitaryPrice;
	}
	/**
	 * @return the idArticle
	 */
	public Integer getIdArticle() {
		return idArticle;
	}
	/**
	 * @param idArticle the idArticle to set
	 */
	public void setIdArticle(Integer idArticle) {
		this.idArticle = idArticle;
	}
	/**
	 * @return the desription
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param desription the desription to set
	 */
	public void setDecsription(String desription) {
		this.description = description;
	}
	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}
	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}
	/**
	 * @return the unitaryPrice
	 */
	public double getUnitaryPrice() {
		return unitaryPrice;
	}
	/**
	 * @param unitaryPrice the unitaryPrice to set
	 */
	public void setUnitaryPrice(Float unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
	}
	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", description=" + description + ", brand=" + brand + ", unitaryPrice="
				+ unitaryPrice + "]";
	}
	
	

	
}
