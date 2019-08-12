package application;

import java.util.ArrayList;

public class Income {
	
	private ArrayList<Bill> incomeBillArr;
	private float incomeTotal;
	public ArrayList<Bill> getIncomeBillArr() {
		return incomeBillArr;
	}
	public void setIncomeBillArr(ArrayList<Bill> incomeBillArr) {
		this.incomeBillArr = incomeBillArr;
	}
	public float getIncomeTotal() {
		return incomeTotal;
	}
	public void setIncomeTotal(float incomeTotal) {
		this.incomeTotal = incomeTotal;
	}

}
