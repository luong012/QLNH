package application;

import java.sql.CallableStatement;
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
		String sql = "Select maban, tenlb, slkhachtoida, trangthaiban, motaban, ban.malb from ban, loaiban where ban.malb=loaiban.malb";
		
		ResultSet rs = statement.executeQuery(sql);
		
		while (rs.next()) {
			  Table t = new Table();
		   	  
			  int a = rs.getInt(1);
			  String b = rs.getNString(2);
			  int c = rs.getInt(3);
			  String d = rs.getString(4);
			  String e = rs.getNString(5);
			  int f = rs.getInt(6);
	          
			  
			  t.setTableID(a);
			  t.setTableTypeName(b);
			  t.setTableMaxCus(c);
			  t.setTableStatus(d);
			  t.setTableDesc(e);
			  t.setTableTypeID(f);
	          arr.add(t);
		}
		return arr;
	}

	public static int getNextTableID() throws SQLException {
		
		Statement statement = InitForm.connection.createStatement();

		String sql = "select last_number from user_sequences  where sequence_name = 'MABAN_SEQ'";
		ResultSet rs = statement.executeQuery(sql);
		
		int a=-1;
		while(rs.next()) {
			a=rs.getInt(1);
		}
		return a;
	}
	
	public static ArrayList<Table> addTableData(Table a) throws SQLException{
		String sql = "{call SP_THEM_BAN(?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
			
		cStmt.setInt(1, a.getTableTypeID());
		cStmt.setString(2, a.getTableDesc());
		cStmt.execute();
		
		ArrayList<Table> arr = TableData.getTableData();
		
		return arr;
	}

}
