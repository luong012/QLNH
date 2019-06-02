package application;

import java.time.LocalDate;

public class Customer {
	
	private String customerID;
	private String customerName;
	private LocalDate customerBirth;
	private String customerAddress;
	private String customerPhone;
	private CustomerType customerType;
	private String customerTypeName;
	private int customerPoint;
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public LocalDate getCustomerBirth() {
		return customerBirth;
	}
	public void setCustomerBirth(LocalDate customerBirth) {
		this.customerBirth = customerBirth;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public CustomerType getCustomerType() {
		return customerType;
	}
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	public String getCustomerTypeName() {
		return customerTypeName;
	}
	public void setCustomerTypeName(String customerTypeName) {
		this.customerTypeName = customerTypeName;
	}
	public int getCustomerPoint() {
		return customerPoint;
	}
	public void setCustomerPoint(int customerPoint) {
		this.customerPoint = customerPoint;
	}

}
