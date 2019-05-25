package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class MenuData {

	public static ArrayList<Menu> searchMenuData(String x, String y, float z , int t) throws SQLException{
		

		ArrayList<Menu> arr = new ArrayList<Menu>();
		String sql = "begin SP_TRACUU_MA(?,?,?,?,?); end;";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
	
			if (x==null)
				cStmt.setNString(1, null);
			else
				cStmt.setNString(1, x.toUpperCase());
			if (y==null)
				cStmt.setNString(2, null);
			else
				cStmt.setNString(2, y.toUpperCase());
			if (z<0) cStmt.setString(3, null);
			else
				cStmt.setFloat(3, z);
			if (t<0) cStmt.setString(4, null);
			else
				cStmt.setInt(4, t);
			cStmt.registerOutParameter(5, OracleTypes.CURSOR);
			
			cStmt.executeUpdate();
					
			ResultSet rs = (ResultSet) cStmt.getObject(5);
			try {				
				while (rs.next()) {
					Menu m = new Menu();
				   	  
					int a = rs.getInt(1);
					String b = rs.getNString(2);
					MenuType mt = new MenuType();
					int c = rs.getInt(3);
					String d = rs.getNString(4);
					mt.setMenuTypeID(c);
					mt.setMenuTypeName(d);
					float e = rs.getFloat(5);
					float g = rs.getFloat(6);
					int h = rs.getInt(7);
					ArrayList<MenuDetail> i = MenuDetailData.getMenuDetailData(a);
					
					

										  
					m.setMenuID(a);
					m.setMenuName(b);
					m.setMenuType(mt);
					m.setMenuEstimatedCost(e);
					m.setMenuOfferCost(e*1.3f);
					m.setMenuCost(g);
					m.setMenuAvailableNumber(h);
					m.setMenuTypeName(d);
					m.setMenuDetailList(i);
			        arr.add(m);
				}
			} finally {
				try {
					rs.close();} catch (Exception ignore) {}
				}
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		return arr;

}
	
	public static int getNextMenuID() throws SQLException {
		
		int a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {
			
	
			String sql = "select last_number from user_sequences  where sequence_name = 'MAMA_SEQ'";
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
	
	
	public static ArrayList<Menu> addMenuData(Menu menu) throws SQLException{
		String sql = "{call SP_THEM_MA(?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
							
			cStmt.setNString(1, menu.getMenuName());
			cStmt.setNString(2, menu.getMenuType().getMenuTypeName());
			cStmt.setFloat(3, menu.getMenuEstimatedCost());
			cStmt.setFloat(4, menu.getMenuCost());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		for (int i=0; i<menu.getMenuDetailList().size(); i++) {
			MenuDetailData.addMenuDetailData(menu.getMenuDetailList().get(i));
		}
		
		ArrayList<Menu> arr = MenuData.searchMenuData(null, null, -1, -1);		
		return arr;
	}
	
}
