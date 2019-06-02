package application;

public class CustomerType {
	
	private int customerTypeID;
	public int getCustomerTypeID() {
		return customerTypeID;
	}
	public void setCustomerTypeID(int customerTypeID) {
		this.customerTypeID = customerTypeID;
	}
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	public int getCustomerTypeDiscount() {
		return customerTypeDiscount;
	}
	public void setCustomerTypeDiscount(int customerTypeDiscount) {
		this.customerTypeDiscount = customerTypeDiscount;
	}
	private String customerTypeName;
	private int customerTypeDiscount;
}
