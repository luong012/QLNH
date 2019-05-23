package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class OrderResourceData {

	public static void addOrderResourceData(OrderResource orderResource) throws SQLException{
		String sql = "{call SP_THEM_CTPN(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			cStmt.setInt(1, OrderDetailData.getNextOrderDetailID()-1);
			cStmt.setInt(2, orderResource.getOrderResourceID());
			cStmt.setInt(3, orderResource.getOrderResourceQuantity());
			cStmt.execute();
		} finally {
			 try {
					cStmt.close();} catch (Exception ignore) {}
			 }		
		return ;
	}
	
	public static ArrayList<OrderResource> getOrderResource(int x) throws SQLException {
		
		ArrayList<OrderResource> arr = new ArrayList<OrderResource>();
		String sql = "begin SP_TRACUU_CTPN(?,?); end;";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {

			cStmt.setInt(1, x);
	
			cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(2);
			try {
			
				while (rs.next()) {
					OrderResource r = new OrderResource();
					
					int a = rs.getInt(1);
					String b = rs.getNString(2);
					int c = rs.getInt(3);
					float d = rs.getFloat(4);
					String e = rs.getNString(5);
					float f = c*d;
					  
					  
					  
					  
					  r.setOrderResourceID(a);
					  r.setOrderResourceName(b);
					  r.setOrderResourceQuantity(c);
					  r.setOrderResourceUnitPrice(d);
					  r.setOrderResourceUnit(e);
					  r.setOrderResourcePrice(f);
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
