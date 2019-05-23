package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class OrderDetail {
	
	private int orderDetailID;
	private LocalDate orderDetailDate;
	private float orderDetailEstimatedCost;
	private float orderDetailCost;
	private ArrayList<OrderResource> orderResourceList;
	private int orderNumberOfResource;
	public int getOrderDetailID() {
		return orderDetailID;
	}
	public void setOrderDetailID(int orderDetailID) {
		this.orderDetailID = orderDetailID;
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
	public int getOrderNumberOfResource() {
		return orderNumberOfResource;
	}
	public void setOrderNumberOfResource(int orderNumberOfResource) {
		this.orderNumberOfResource = orderNumberOfResource;
	}
	public LocalDate getOrderDetailDate() {
		return orderDetailDate;
	}
	public void setOrderDetailDate(LocalDate orderDetailDate) {
		this.orderDetailDate = orderDetailDate;
	}
	

}
