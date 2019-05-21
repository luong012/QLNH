package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class ResourceData {
	
	
	
	public static int getNextResourceID() throws SQLException {
		
		Statement statement = InitForm.connection.createStatement();

		String sql = "select last_number from user_sequences  where sequence_name = 'MANL_SEQ'";
		ResultSet rs = statement.executeQuery(sql);
		
		int a=-1;
		while(rs.next()) {
			a=rs.getInt(1);
		}
		return a;
	}
	
	public static ArrayList<Resource> getResourceData() throws SQLException{
	
	Statement statement = InitForm.connection.createStatement();
	ArrayList<Resource> arr = new ArrayList<Resource>();
	

	String sql = "select manl, tennl, sltonkho, dongianl, donvinl from nguyenlieu order by tennl asc";
	
	ResultSet rs = statement.executeQuery(sql);
	
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
	return arr;
}
	
	
	
	public static ArrayList<Resource> addResourceData(Resource a) throws SQLException{
		String sql = "{call SP_THEM_NL(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
			
		cStmt.setNString(1, a.getResourceName());
		cStmt.setNString(2, a.getResourceUnit());
		cStmt.setFloat(3, a.getResourceUnitPrice());
		cStmt.execute();
		
		ArrayList<Resource> arr = ResourceData.getResourceData();
		
		return arr;
	}
	
	public static ArrayList<Resource> searchResourceData(String x) throws SQLException{
		String sql = "begin SP_TRACUU_NL(?,?); end;";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		  System.out.println(x);

		if (x==null) cStmt.setNString(1, null);
		else
			cStmt.setNString(1, x.toUpperCase());
		cStmt.registerOutParameter(2, OracleTypes.CURSOR);
		cStmt.executeUpdate();
		
		ArrayList<Resource> arr = new ArrayList<Resource>();

		ResultSet rs = (ResultSet) cStmt.getObject(2);
		
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

		
		return arr;	}
	

}
