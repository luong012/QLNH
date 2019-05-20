package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableTypeData {
	public static ArrayList<TableType> getTableTypeData() throws SQLException{
		
		Statement statement = InitForm.connection.createStatement();
		ArrayList<TableType> arr = new ArrayList<TableType>();
		
		String sql = "Select * from loaiban order by SLKhachToiDa asc";
		
		ResultSet rs = statement.executeQuery(sql);
		
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
		return arr;
	}
	
	public static int getNextTableTypeID() throws SQLException {
		
		Statement statement = InitForm.connection.createStatement();

		String sql = "select last_number from user_sequences  where sequence_name = 'MALB_SEQ'";
		ResultSet rs = statement.executeQuery(sql);
		
		int a=-1;
		while(rs.next()) {
			a=rs.getInt(1);
		}
		return a;
	}
	
	public static ArrayList<TableType> addTableData(TableType a) throws SQLException{
		String sql = "{call SP_THEM_LOAIBAN(?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
			
		cStmt.setString(1, a.getTableName());
		cStmt.setInt(2, a.getMaxCus());
		cStmt.execute();
		
		ArrayList<TableType> arr = TableTypeData.getTableTypeData();
		
		return arr;
	}
	

}
