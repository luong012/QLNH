package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RoleData {
	
	public static ArrayList<Role> getRoleData() throws SQLException{
		
		ArrayList<Role> arr = new ArrayList<Role>();
		Statement statement = InitForm.connection.createStatement();
		try {
			
			String sql = "Select * from chucvu order by macv asc";
			
			ResultSet rs = statement.executeQuery(sql);
			try {
				while (rs.next()) {
					  Role role = new Role();	    	  
			          int a = rs.getInt(1);
			          String b = rs.getNString(2);
			          
			          role.setRoleID(a);
			          role.setRoleName(b);
			          arr.add(role); 
			      }
			} finally {
				try {
					rs.close();} catch (Exception ignore) {}

				}
		} finally {
			try {
				statement.close();} catch (Exception ignore) {}
			}
		return arr;
	}

}
