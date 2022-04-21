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
	private int quantity = 1;
	/**
	 * @param idArticle
	 * @param desription
	 * @param brand
	 * @param unitaryPrice
	 */
	public Article(int idArticle, String description, String brand, double unitaryPrice) {
		setIdArticle(idArticle);
		setDecsription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);

	}
	/**
	 * @param idArticle
	 * @param desription
	 * @param brand
	 */
	public Article(String description, String brand, double unitaryPrice) {
		setDecsription(description);
		setBrand(brand);
		setUnitaryPrice(unitaryPrice);
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param desription the description to set
	 */
	public void setDecsription(String description) {
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
	public void setUnitaryPrice(double unitaryPrice) {
		this.unitaryPrice = unitaryPrice;
		
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "Article [idArticle=" + idArticle + ", description=" + description + ", brand=" + brand + ", unitaryPrice="
				+ unitaryPrice + "]";
	}




}
