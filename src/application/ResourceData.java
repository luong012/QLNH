package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class ResourceData {
	
	
	
	public static int getNextResourceID() throws SQLException {
	
		int a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {
			
			String sql = "select last_number from user_sequences  where sequence_name = 'MANL_SEQ'";
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
	
	public static ArrayList<Resource> getResourceData() throws SQLException{
	
	Statement statement = InitForm.connection.createStatement();
	ArrayList<Resource> arr = new ArrayList<Resource>();
	try {

		String sql = "select manl, tennl, sltonkho, dongianl, donvinl from nguyenlieu order by tennl asc";
		
		ResultSet rs = statement.executeQuery(sql);
		try {
		
			while (rs.next()) {
				  Resource r = new Resource();
			   	  
				  int a = rs.getInt(1);
				  String b = rs.getNString(2);
				  int c = rs.getInt(3);
				  float d = rs.getFloat(4);
				  String e = rs.getNString(5);
		          
				  
				  r.setResourceID(a);
				  r.setResourceName(b);
				  r.setResourceQuantityLeft(c);
				  r.setResourceUnitPrice(d);
				  r.setResourceUnit(e);
		          arr.add(r);
			}
		} finally {
			try {
		rs.close();} catch (Exception ignore) {}
	}

	} finally {
		try {statement.close();} catch (Exception ignore) {}
			
		}
		
	return arr;
}
	
	
	
	public static ArrayList<Resource> addResourceData(Resource a) throws SQLException{
		String sql = "{call SP_THEM_NL(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setNString(1, a.getResourceName());
			cStmt.setNString(2, a.getResourceUnit());
			cStmt.setFloat(3, a.getResourceUnitPrice());
			cStmt.execute();
		} finally { 
			try {
				cStmt.close();} catch (Exception ignore) {}
		}
		
		
		
		ArrayList<Resource> arr = ResourceData.getResourceData();
		
		return arr;
	}
	
	public static ArrayList<Resource> searchResourceData(String x) throws SQLException{
		String sql = "begin SP_TRACUU_NL(?,?); end;";
		ArrayList<Resource> arr = new ArrayList<Resource>();
		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			if (x==null) cStmt.setNString(1, null);
			else
				cStmt.setNString(1, x.toUpperCase());
			cStmt.registerOutParameter(2, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(2);
			try {
				while (rs.next()) {
					Resource r = new Resource();
				   	  
					  int a = rs.getInt(1);
					  String b = rs.getNString(2);
					  int c = rs.getInt(3);
					  float d = rs.getFloat(4);
					  String e = rs.getNString(5);
					  
					  
					  r.setResourceID(a);
					  r.setResourceName(b);
					  r.setResourceQuantityLeft(c);
					  r.setResourceUnitPrice(d);
					  r.setResourceUnit(e);
			          arr.add(r);
					
				}		


			} finally { 
				try {rs.close();} catch (Exception ignore) {}
			}
		} finally {
			try { cStmt.close();} catch (Exception ignore) {}
			}
	

		
	return arr;
	}

}
