package application;

public class Resource {
	
	private int resourceID;
	private String resourceName;
	private int resourceQuantityLeft;
	private float resourceUnitPrice;
	private String resourceUnit;
	
	
	
	public int getResourceID() {
		return resourceID;
	}
	public void setResourceID(int resourceID) {
		this.resourceID = resourceID;
	}
	public String getResourceName() {
		return resourceName;
	}
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}
	public int getResourceQuantityLeft() {
		return resourceQuantityLeft;
	}
	public void setResourceQuantityLeft(int resourceQuantityLeft) {
		this.resourceQuantityLeft = resourceQuantityLeft;
	}
	public float getResourceUnitPrice() {
		return resourceUnitPrice;
	}
	public void setResourceUnitPrice(float resourceUnitPrice) {
		this.resourceUnitPrice = resourceUnitPrice;
	}
	public String getResourceUnit() {
		return resourceUnit;
	}
	public void setResourceUnit(String resourceUnit) {
		this.resourceUnit = resourceUnit;
	}

}
