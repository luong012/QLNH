package application;

import java.util.ArrayList;
import java.util.Date;

public class OrderDetail {
	
	private int orderDetailID;
	private Date orderDetailDate;
	private float orderDetailEstimatedCost;
	private float orderDetailCost;
	private ArrayList<OrderResource> orderResourceList;
	public int getOrderDetailID() {
		return orderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
	}
	public Date getOrderDetailDate() {
		return orderDetailDate;
	}
	public void setOrderDetailDate(Date orderDetailDate) {
		this.orderDetailDate = orderDetailDate;
	}
	public float getOrderDetailEstimatedCost() {
		return orderDetailEstimatedCost;
	}
	public void setOrderDetailEstimatedCost(float orderDetailEstimatedCost) {
		this.orderDetailEstimatedCost = orderDetailEstimatedCost;
	}
	public float getOrderDetailCost() {
		return orderDetailCost;
	}
	public void setOrderDetailCost(float orderDetailCost) {
		this.orderDetailCost = orderDetailCost;
	}
	public ArrayList<OrderResource> getOrderResourceList() {
		return orderResourceList;
	}
	public void setOrderResourceList(ArrayList<OrderResource> orderResourceList) {
		this.orderResourceList = orderResourceList;
	}
	

}
