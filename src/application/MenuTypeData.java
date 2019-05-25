package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MenuTypeData {
	public static ArrayList<MenuType> getMenuTypeData() throws SQLException{
		
		ArrayList<MenuType> arr = new ArrayList<MenuType>();
		Statement statement = InitForm.connection.createStatement();
		try {
			
			String sql = "Select * from loaimonan order by malma asc";
			
			ResultSet rs = statement.executeQuery(sql);
			try {
				while (rs.next()) {
					MenuType mt = new MenuType();	    	  
			          int a = rs.getInt(1);
			          String b = rs.getNString(2);
			          mt.setMenuTypeID(a);
			          mt.setMenuTypeName(b);
			          arr.add(mt); 
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
	
	public static int getNextMenuTypeID() throws SQLException {
		
		int a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {

			String sql = "select last_number from user_sequences  where sequence_name = 'MALMA_SEQ'";
			ResultSet rs = statement.executeQuery(sql);
			try {
				while(rs.next()) {
					a=rs.getInt(1);
				}
			} finally {
				try {
					rs.close();} catch (Exception ignore) {}
				}
		} finally {
			try {
				statement.close();} catch (Exception ignore) {}
			}
		return a;
	}
	
	public static ArrayList<MenuType> addMenuTypeData(MenuType a) throws SQLException{
		String sql = "{call SP_THEM_LOAIMA(?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, a.getMenuTypeName());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
		
		return arr;
	}
	

}