package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableTypeData {
	public static ArrayList<TableType> getTableTypeData() throws SQLException{
		
		ArrayList<TableType> arr = new ArrayList<TableType>();
		Statement statement = InitForm.connection.createStatement();
		try {
			
			String sql = "Select * from loaiban order by SLKhachToiDa asc";
			
			ResultSet rs = statement.executeQuery(sql);
			try {
				while (rs.next()) {
			    	  TableType tt = new TableType();	    	  
			          int a = rs.getInt(1);
			          String b = rs.getNString(2);
			          int c = rs.getInt(3);
			          tt.setTableID(a);
			          tt.setTableName(b);
			          tt.setMaxCus(c);
			          arr.add(tt); 
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
	
	public static int getNextTableTypeID() throws SQLException {
		
		int a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {

			String sql = "select last_number from user_sequences  where sequence_name = 'MALB_SEQ'";
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
	
	public static ArrayList<TableType> addTableData(TableType a) throws SQLException{
		String sql = "{call SP_THEM_LOAIBAN(?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, a.getTableName());
			cStmt.setInt(2, a.getMaxCus());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		ArrayList<TableType> arr = TableTypeData.getTableTypeData();
		
		return arr;
	}
	

}
