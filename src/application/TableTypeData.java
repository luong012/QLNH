package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableTypeData {
	public static ArrayList<TableType> getTableTypeData() throws SQLException{
		
		Statement statement = InitForm.connection.createStatement();
		ArrayList<TableType> arr = new ArrayList<TableType>();
		
		String sql = "Select * from loaiban";
		
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
	

	

}
