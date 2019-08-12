package application;

public class BillDetail {
	
	private int billDetailMenuID;
	private String billDetailMenuName;
	private float billDetailMenuUnitPrice;
	private int billDetailMenuQuantity;
	private float billDetailMenuCost;
	public String getBillDetailMenuName() {
		return billDetailMenuName;
	}
	public void setBillDetailMenuName(String billDetailMenuName) {
		this.billDetailMenuName = billDetailMenuName;
	}
	public int getBillDetailMenuQuantity() {
		return billDetailMenuQuantity;
	}
	public void setBillDetailMenuQuantity(int billDetailMenuQuantity) {
		this.billDetailMenuQuantity = billDetailMenuQuantity;
	}
	public float getBillDetailMenuCost() {
		return billDetailMenuCost;
	}
	public void setBillDetailMenuCost(float billDetailMenuCost) {
		this.billDetailMenuCost = billDetailMenuCost;
	}
	public int getBillDetailMenuID() {
		return billDetailMenuID;
	}
	public void setBillDetailMenuID(int billDetailMenuID) {
		this.billDetailMenuID = billDetailMenuID;
	}
	public float getBillDetailMenuUnitPrice() {
		return billDetailMenuUnitPrice;
	}
	public void setBillDetailMenuUnitPrice(float billDetailMenuUnitPrice) {
		this.billDetailMenuUnitPrice = billDetailMenuUnitPrice;
	}
	

}
