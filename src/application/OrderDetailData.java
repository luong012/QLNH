package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class OrderDetailData {
	
	public static ArrayList<OrderDetail> searchOrderDetailData(int x, LocalDate date) throws SQLException{
		

		ArrayList<OrderDetail> arr = new ArrayList<OrderDetail>();
		String sql = "begin SP_TRACUU_PN(?,?,?); end;";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			if (x==-1)
				cStmt.setString(1, null);
			else
				cStmt.setInt(1, x);
			if (date==null) cStmt.setDate(2, null);
			else
				cStmt.setDate(2, Date.valueOf(date));
			cStmt.registerOutParameter(3, OracleTypes.CURSOR);
			
			cStmt.executeUpdate();
					
			ResultSet rs = (ResultSet) cStmt.getObject(3);
			try {				
				while (rs.next()) {
					OrderDetail t = new OrderDetail();
				   	  
					int a = rs.getInt(1);
					LocalDate b = rs.getDate(2).toLocalDate();
					float d = rs.getFloat(3);
					ArrayList<OrderResource> e = OrderResourceData.getOrderResource(a);
					int f = e.size();
					
					float c = 0;
			  		for (int i=0; i <e.size();i++) {
			  			c+=e.get(i).getOrderResourcePrice();
			  		}
			          
					  
					t.setOrderDetailID(a);
					t.setOrderDetailDate(b);
					t.setOrderDetailEstimatedCost(c);
					t.setOrderDetailCost(d);
					t.setOrderResourceList(e);
					t.setOrderNumberOfResource(f);
			        arr.add(t);
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
	
	public static int getNextOrderDetailID() throws SQLException {
		
		int a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {
			
	
			String sql = "select last_number from user_sequences  where sequence_name = 'MAPN_SEQ'";
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
	
	
	public static ArrayList<OrderDetail> addOrderDetailData(OrderDetail orderDetail) throws SQLException{
		String sql = "{call SP_THEM_PN(?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
							
			Date date = Date.valueOf(orderDetail.getOrderDetailDate());
			cStmt.setDate(1, date);
			cStmt.setFloat(2, orderDetail.getOrderDetailCost());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		for (int i=0; i<orderDetail.getOrderResourceList().size(); i++) {
			OrderResourceData.addOrderResourceData(orderDetail.getOrderResourceList().get(i));
		}
		
		ArrayList<OrderDetail> arr = OrderDetailData.searchOrderDetailData(-1, null);
		
		return arr;
	}
	
}
