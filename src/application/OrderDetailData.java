package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDetailData {
	
	public static ArrayList<Table> getOrderDetailData() throws SQLException{
		
		Statement statement = InitForm.connection.createStatement();
		ArrayList<OrderDetail> arr = new ArrayList<OrderDetail>();
		
		String sql = "Select maban, tenlb, slkhachtoida, trangthaiban, motaban, ban.malb from ban, loaiban where ban.malb=loaiban.malb order by maban asc";
		
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
	
	public static int getNextOrderDetailID() throws SQLException {
		
		Statement statement = InitForm.connection.createStatement();

		String sql = "select last_number from user_sequences  where sequence_name = 'MAPN_SEQ'";
		ResultSet rs = statement.executeQuery(sql);
		
		int a=-1;
		while(rs.next()) {
			a=rs.getInt(1);
		}
		return a;
	}
	
	public static ArrayList<Resource> addResourceData(Resource a) throws SQLException{
		String sql = "{call SP_THEM_PN(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
			
		cStmt.setNString(1, a.getResourceName());
		cStmt.setNString(2, a.getResourceUnit());
		cStmt.setFloat(3, a.getResourceUnitPrice());
		cStmt.execute();
		
		ArrayList<Resource> arr = ResourceData.getResourceData();
		
		return arr;
	}
	
}
