package application;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TableData {
	
public static ArrayList<Table> getTableData() throws SQLException{
		
		Statement statement = InitForm.connection.createStatement();
		ArrayList<Table> arr = new ArrayList<Table>();
		
//		String sql = "Select maban, ban.malb, trangthaiban, motaban, tenlb, slkhachtoida from ban, loaiban where ban.malb=loaiban.malb";
//		
//		ResultSet rs = statement.executeQuery(sql);
//		
//		while (rs.next()) {
//			  Table t = new Table();
//		   	  TableType tt = new TableType();	   
//	          int a = rs.getInt(1);
//	          int b = rs.getInt(2);
//	          String c = rs.getString(3);
//	          String d = rs.getNString(4);
//	          String e = rs.getNString(5);
//	          int f = rs.getInt(6);
//	          
//	          tt.setTableID(b);
//	          tt.setTableName(e);
//	          tt.setMaxCus(f);
//	          
//	          t.setTableID(a);
//	          t.setTableType(tt);
//	          t.setTableDesc(d);
//	          t.setTableStatus(c);
//	          arr.add(t);
//	          
//	      }
		String sql = "Select maban, tenlb, slkhachtoida, trangthaiban, motaban from ban, loaiban where ban.malb=loaiban.malb";
		
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			  Table t = new Table();
		   	  
			  int a = rs.getInt(1);
			  String b = rs.getNString(2);
			  int c = rs.getInt(3);
			  String d = rs.getString(4);
			  String e = rs.getNString(5);
	          
			  
			  t.setTableID(a);
			  t.setTableTypeName(b);
			  t.setTableMaxCus(c);
			  t.setTableStatus(d);
			  t.setTableDesc(e);
	          arr.add(t);
		}
		return arr;
	}

}
