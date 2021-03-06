package application;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types; 

public class AppData {
	public static int getRole(String username2, String password2) throws ClassNotFoundException,
    SQLException { 

		int outputRole = 0;
		String sql = "{call SP_KIEMTRATK(?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
		
			cStmt.registerOutParameter(3, Types.INTEGER);
			cStmt.registerOutParameter(4, Types.NVARCHAR);
			
			cStmt.setString(1, username2);
			cStmt.setString(2, password2);
			
			cStmt.execute();
			
			outputRole = cStmt.getInt(3);
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}

		}
		return outputRole;
	}
	
	public static String getEmpID(String username2, String password2) throws ClassNotFoundException,
    SQLException { 

		String empID ="";
		String sql = "{call SP_KIEMTRATK(?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
		
			cStmt.registerOutParameter(3, Types.INTEGER);
			cStmt.registerOutParameter(4, Types.NVARCHAR);
			
			cStmt.setString(1, username2);
			cStmt.setString(2, password2);
			
			cStmt.execute();
			
			empID = cStmt.getString(4);
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}

		}
		return empID;
	}


}
