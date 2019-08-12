package application;


public class Global {
	static Employee activeEmp;
	static String activeUsername = "NULL";
	static int activeRole = 1;
	static String activeRoleName = "NULL";
	static int prevIndex;
	static Table selectedTable;
	static Customer selectedCus;
	static Employee selectedEmp;
	public static void setRoleName(int role) {
		if (role==1) activeRoleName="Manager";
		if (role==2) activeRoleName="Cashier";
		if (role==3) activeRoleName="Waiter";
		if (role==4) activeRoleName="Chef";
	}
}
