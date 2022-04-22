/**
 * 
 */
package fr.fms.entities;

/**
 * @author Stagiaires09
 *
 */
public class Category {
	private int idCategory;
	private String catName;
	private String description;
	
	
	/**
	 * @param idCategory
	 * @param catName
	 * @param description
	 */
	public Category(int idCategory, String catName, String description) {
		setIdCategory(idCategory);
		setCatName(catName);
		setDescription(description);
		
	}
	
	
	/**
	 * @param catName
	 * @param description
	 */
	public Category(String catName, String description) {
		setCatName(catName);
		setDescription(description);
	}


	/**
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return idCategory;
	}


	/**
	 * @param idCategory the idCategory to set
	 */
	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}


	/**
	 * @return the catName
	 */
	public String getCatName() {
		return catName;
	}


	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		this.catName = catName;
	}


	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}


	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}


	@Override
	public String toString() {
		return "Category [idCategory=" + idCategory + ", catName=" + catName + ", description=" + description + "]";
	}




	
	
	
}
