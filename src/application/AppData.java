package application;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types; 

public class AppData {
	public static int getRole(String username2, String password2) throws ClassNotFoundException,
    SQLException { 
		Connection connection = ConnectionUtils.getMyConnection();
	
		String sql = "{call SP_KIEMTRATK(?,?,?)}";

		CallableStatement cStmt = connection.prepareCall(sql);
		
		cStmt.registerOutParameter(3, Types.INTEGER);
		
		cStmt.setString(1, username2);
		cStmt.setString(2, password2);
		
		cStmt.execute();
		
		int outputRole = cStmt.getInt(3);
		
		connection.close();
		return outputRole;
	}

}
