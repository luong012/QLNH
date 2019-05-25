package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class MenuDetailData {
	
	public static void addMenuDetailData(MenuDetail menuDetail) throws SQLException{
		String sql = "{call SP_THEM_CTMA(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			cStmt.setInt(1, MenuData.getNextMenuID()-1);
			cStmt.setInt(2, menuDetail.getMenuDetailResourceID());
			cStmt.setInt(3, menuDetail.getMenuDetailResourceQuantity());
			cStmt.execute();
		} finally {
			 try {
					cStmt.close();} catch (Exception ignore) {}
			 }		
		return ;
	}
	
	public static ArrayList<MenuDetail> getMenuDetailData(int x) throws SQLException {
		
		ArrayList<MenuDetail> arr = new ArrayList<MenuDetail>();
		String sql = "begin SP_TRACUU_CTMA(?,?); end;";
		

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {

			cStmt.setInt(1, x);
	
			cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(2);
			try {
			
				while (rs.next()) {
					MenuDetail r = new MenuDetail();
					
					int a = rs.getInt(1);
					String b = rs.getNString(2);
					int c = rs.getInt(3);
					float d = rs.getFloat(4);
					String e = rs.getNString(5);
					float f = c*d;
					  
					  
					  
					  
					  r.setMenuDetailResourceID(a);
					  r.setMenuDetailResourceName(b);
					  r.setMenuDetailResourceQuantity(c);
					  r.setMenuDetailResourceUnitPrice(d);
					  r.setMenuDetailResourceUnit(e);
					  r.setMenuDetailResourcePrice(f);
			          arr.add(r);
					
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

}
