package application;

public class Global {
	static String activeUsername = "NULL";
	static int activeRole = 0;
	static String activeRoleName = "NULL";
	public static void setRoleName(int role) {
		if (role==1) activeRoleName="Manager";
		if (role==2) activeRoleName="Cashier";
		if (role==3) activeRoleName="Waiter";
		if (role==4) activeRoleName="Chef";
	}

}
