/**
 * 
 */
package fr.fms.entities;

/**
 * @author Stagiaires09
 *
 */
public class Category {
	private int IdCategory =0;
	private String CatName ="";
	private String Description="";
	
	
	/**
	 * @param idCategory
	 * @param catName
	 * @param description
	 */
	public Category(int idCategory, String catName, String description) {
		super();
		setIdCategory(idCategory);
		setCatName(catName);
		setDescription(description);
		
	}
	
	
	/**
	 * @param catName
	 * @param description
	 */
	public Category(String catName, String description) {
		super();
		setCatName(catName);
		setDescription(description);
	}



	/**
	 * @return the idCategory
	 */
	public int getIdCategory() {
		return IdCategory;
	}
	/**
	 * @param idCategory the idCategory to set
	 */
	public void setIdCategory(int idCategory) {
		IdCategory = idCategory;
	}
	/**
	 * @return the catName
	 */
	public String getCatName() {
		return CatName;
	}
	/**
	 * @param catName the catName to set
	 */
	public void setCatName(String catName) {
		CatName = catName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}



	@Override
	public String toString() {
		return "Category [IdCategory=" + IdCategory + ", CatName=" + CatName + ", Description=" + Description + "]";
	}
	
	
	
}
