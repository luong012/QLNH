package application;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Bill {
	
	private long billID;
	private Table billTable;
	private Customer billCustomer;
	private Employee billEmployee;
	private String billStatus;
	private LocalDateTime billCreationTime;
	private LocalDateTime billClosingTime;
	private float billCost;
	private ArrayList<BillDetail> billDetailMenuList;
	private int billTableID;
	private String billCustomerID;
	private String billEmployeeID;
	public long getBillID() {
		return billID;
	}
	public void setBillID(long billID) {
		this.billID = billID;
	}
	public Table getBillTable() {
		return billTable;
	}
	public void setBillTable(Table billTable) {
		this.billTable = billTable;
	}
	public Customer getBillCustomer() {
		return billCustomer;
	}
	public void setBillCustomer(Customer billCustomer) {
		this.billCustomer = billCustomer;
	}
	public Employee getBillEmployee() {
		return billEmployee;
	}
	public void setBillEmployee(Employee billEmployee) {
		this.billEmployee = billEmployee;
	}
	public String getBillStatus() {
		return billStatus;
	}
	public void setBillStatus(String billStatus) {
		this.billStatus = billStatus;
	}
	public LocalDateTime getBillCreationTime() {
		return billCreationTime;
	}
	public void setBillCreationTime(LocalDateTime billCreationTime) {
		this.billCreationTime = billCreationTime;
	}
	public LocalDateTime getBillClosingTime() {
		return billClosingTime;
	}
	public void setBillClosingTime(LocalDateTime billClosingTime) {
		this.billClosingTime = billClosingTime;
	}
	public float getBillCost() {
		return billCost;
	}
	public void setBillCost(float billCost) {
		this.billCost = billCost;
	}
	public ArrayList<BillDetail> getBillDetailMenuList() {
		return billDetailMenuList;
	}
	public void setBillDetailMenuList(ArrayList<BillDetail> billDetailMenuList) {
		this.billDetailMenuList = billDetailMenuList;
	}
	public int getBillTableID() {
		return billTableID;
	}
	public void setBillTableID(int billTableID) {
		this.billTableID = billTableID;
	}
	public String getBillCustomerID() {
		return billCustomerID;
	}
	public void setBillCustomerID(String billCustomerID) {
		this.billCustomerID = billCustomerID;
	}
	public String getBillEmployeeID() {
		return billEmployeeID;
	}
	public void setBillEmployeeID(String billEmployeeID) {
		this.billEmployeeID = billEmployeeID;
	}

}
