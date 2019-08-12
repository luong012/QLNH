package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class TableData {
	

	public static int getNextTableID() throws SQLException {
		
		int a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {
				
			String sql = "select last_number from user_sequences  where sequence_name = 'MABAN_SEQ'";
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
	
	public static ArrayList<Table> addTableData(Table a) throws SQLException{
		String sql = "{call SP_THEM_BAN(?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setInt(1, a.getTableTypeID());
			cStmt.setString(2, a.getTableDesc());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		ArrayList<Table> arr = TableData.searchTableData(-1,null,null,-1);
		
		return arr;
	}
	
	public static ArrayList<Table> updateTableData(Table a) throws SQLException{
		String sql = "{call SP_SUA_BAN(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setInt(1, a.getTableID());
			cStmt.setNString(2, a.getTableTypeName());
			cStmt.setNString(3, a.getTableDesc());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		ArrayList<Table> arr = TableData.searchTableData(-1,null,null,-1);
		
		return arr;
	}
	
	public static ArrayList<Table> delTableData(Table a) throws SQLException{
		String sql = "{call SP_XOA_BAN(?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setInt(1, a.getTableID());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		ArrayList<Table> arr = TableData.searchTableData(-1,null,null,-1);
		
		return arr;
	}
	
	
	public static ArrayList<Table> searchTableData(int x, String y, String z, int t) throws SQLException{
		String sql = "begin SP_TRACUU_BAN(?,?,?,?,?); end;";
		ArrayList<Table> arr = new ArrayList<Table>();

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			if (x<0)
				cStmt.setString(1, null);
			else
				cStmt.setInt(1, x);
			cStmt.setNString(2, z);
			cStmt.setNString(3, y);
			if (t<0)
				cStmt.setString(4, null);
			else
				cStmt.setInt(4, t);
			cStmt.registerOutParameter(5, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(5);
			try {
			
				while (rs.next()) {
					Table tb = new Table();
				   	  
					  int a = rs.getInt(1);
					  String b = rs.getNString(2);
					  int c = rs.getInt(3);
					  String d = rs.getString(4);
					  String e = rs.getNString(5);
					  int f = rs.getInt(6);
					  
					 
					  
					  tb.setTableID(a);
					  tb.setTableTypeName(b);
					  tb.setTableMaxCus(c);
					  tb.setTableStatus(d);
					  tb.setTableDesc(e);
					  tb.setTableTypeID(f);
			          arr.add(tb);
					
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
