package application;

import java.util.ArrayList;

public class Menu {
	
	private int menuID;
	private String menuName;
	private MenuType menuType;
	private float menuEstimatedCost;
	private float menuOfferCost;
	private float menuCost;
	private int menuAvailableNumber;	
	private String menuTypeName;
	private ArrayList<MenuDetail> menuDetailList;
	public int getMenuID() {
		return menuID;
	}
	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public MenuType getMenuType() {
		return menuType;
	}
	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}
	public float getMenuEstimatedCost() {
		return menuEstimatedCost;
	}
	public void setMenuEstimatedCost(float menuEstimatedCost) {
		this.menuEstimatedCost = menuEstimatedCost;
	}
	public float getMenuOfferCost() {
		return menuOfferCost;
	}
	public void setMenuOfferCost(float menuOfferCost) {
		this.menuOfferCost = menuOfferCost;
	}
	public float getMenuCost() {
		return menuCost;
	}
	public void setMenuCost(float menuCost) {
		this.menuCost = menuCost;
	}
	public int getMenuAvailableNumber() {
		return menuAvailableNumber;
	}
	public void setMenuAvailableNumber(int menuAvailableNumber) {
		this.menuAvailableNumber = menuAvailableNumber;
	}
	public ArrayList<MenuDetail> getMenuDetailList() {
		return menuDetailList;
	}
	public void setMenuDetailList(ArrayList<MenuDetail> menuDetailList) {
		this.menuDetailList = menuDetailList;
	}
	public String getMenuTypeName() {
		return menuTypeName;
	}
	public void setMenuTypeName(String menuTypeName) {
		this.menuTypeName = menuTypeName;
	}

}
