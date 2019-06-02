package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class CustomerData {
	
	public static ArrayList<Customer> addCustomerData(Customer a) throws SQLException{
		String sql = "{call SP_THEM_KH(?,?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, a.getCustomerID());
			cStmt.setString(2, a.getCustomerName());
			if (a.getCustomerBirth()==null) cStmt.setDate(3, null);
			else
				cStmt.setDate(3, Date.valueOf(a.getCustomerBirth()));
			cStmt.setString(4, a.getCustomerAddress());
			cStmt.setString(5, a.getCustomerPhone());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		ArrayList<Customer> arr = CustomerData.searchCustomerData(null,null,null,null);
		
		return arr;
	}
	
	
	public static ArrayList<Customer> searchCustomerData(String x, String y, String z, String t) throws SQLException{
		String sql = "begin SP_TRACUU_KH(?,?,?,?,?); end;";
		ArrayList<Customer> arr = new ArrayList<Customer>();

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, x);
			cStmt.setNString(2, z);
			cStmt.setNString(3, y);
			cStmt.setNString(4, t);
			cStmt.registerOutParameter(5, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			System.out.println(x);
			System.out.println(y);
			System.out.println(z);
			System.out.println(t);
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(5);
			try {
			
				while (rs.next()) {
					Customer cus = new Customer();
				   	  
					  String a = rs.getString(1);
					  String b = rs.getNString(2);
					  LocalDate c = rs.getDate(3).toLocalDate();
					  String d = rs.getNString(4);
					  String e = rs.getNString(5);
					  int f = rs.getInt(6);
					  String g = rs.getNString(7);
					  int h = rs.getInt(8);
					  CustomerType fgh = new CustomerType();
					  fgh.setCustomerTypeID(f);
					  fgh.setCustomerTypeName(g);
					  fgh.setCustomerTypeDiscount(h);
					  int i = rs.getInt(9);
					  
					 
					  cus.setCustomerID(a);
					  cus.setCustomerName(b);
					  cus.setCustomerBirth(c);
					  cus.setCustomerAddress(d);
					  cus.setCustomerPhone(e);
					  cus.setCustomerType(fgh);
					  cus.setCustomerTypeName(g);
					  cus.setCustomerPoint(i);
					  
					  arr.add(cus);
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
