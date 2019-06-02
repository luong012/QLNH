package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CustomerTypeData {
	
		
	public static ArrayList<CustomerType> getCustomerTypeData() throws SQLException{
			
			ArrayList<CustomerType> arr = new ArrayList<CustomerType>();
			Statement statement = InitForm.connection.createStatement();
			try {
				
				String sql = "Select * from loaikhachhang order by malkh asc";
				
				ResultSet rs = statement.executeQuery(sql);
				try {
					while (rs.next()) {
						  CustomerType ct = new CustomerType();	    	  
				          int a = rs.getInt(1);
				          String b = rs.getNString(2);
				          int c = rs.getInt(3);
				          ct.setCustomerTypeID(a);
				          ct.setCustomerTypeName(b);
				          ct.setCustomerTypeDiscount(c);
				          arr.add(ct); 
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
