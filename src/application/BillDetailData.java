package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class BillDetailData {
	
	public static void addBillDetailData(BillDetail billDetail) throws SQLException{
		String sql = "{call SP_THEM_CTHD(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			cStmt.setLong(1, BillData.getNextBillID()-1);
			cStmt.setInt(2, billDetail.getBillDetailMenuID());
			cStmt.setInt(3, billDetail.getBillDetailMenuQuantity());
			cStmt.execute();
		} finally {
			 try {
					cStmt.close();} catch (Exception ignore) {}
			 }		
		return ;
	}
	
	public static ArrayList<BillDetail> getBillDetailData(long x) throws SQLException {
		
		ArrayList<BillDetail> arr = new ArrayList<BillDetail>();
		String sql = "begin SP_TRACUU_CTHD(?,?); end;";
		

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {

			cStmt.setLong(1, x);
	
			cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(2);
			try {
			
				while (rs.next()) {
					BillDetail r = new BillDetail();
					
					int a = rs.getInt(1);
					String b = rs.getNString(2);
					float c = rs.getFloat(3);
					int d = rs.getInt(4);
					float e = c*d;
					
					r.setBillDetailMenuID(a);
					r.setBillDetailMenuName(b);
					r.setBillDetailMenuUnitPrice(c);
					r.setBillDetailMenuQuantity(d);
					r.setBillDetailMenuCost(e);
					

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
