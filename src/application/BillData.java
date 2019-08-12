package application;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class BillData {
	
	static String errorMenu="";
	private static Income income;
	
	public static ArrayList<Bill> searchBillData(int x, String y, LocalDateTime z, LocalDateTime t) throws SQLException{
		
		ArrayList<Bill> arr = new ArrayList<Bill>();
		String sql = "begin SP_TRACUU_HD(?,?,?,?,?); end;";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
	
			
			if (x<0) cStmt.setString(1, null);
			else
				cStmt.setInt(1, x);
			cStmt.setString(2, y);
			if (z==null) cStmt.setDate(3, null);
			else
				cStmt.setTimestamp(3, Timestamp.valueOf(y));
			if (t==null) cStmt.setDate(4, null);
			else
				cStmt.setTimestamp(4, Timestamp.valueOf(y));
			cStmt.registerOutParameter(5, OracleTypes.CURSOR);
			
			cStmt.executeUpdate();
					
			ResultSet rs = (ResultSet) cStmt.getObject(5);
			try {				
				while (rs.next()) {
					Bill m = new Bill();
				   	  
					long a = rs.getLong(1);
					int b = rs.getInt(2);
					Table table = TableData.searchTableData(b, null, null, -1).get(0);
					String c = rs.getString(3);
					Customer customer = CustomerData.searchCustomerData(c, null, null, null).get(0);
					String d = rs.getString(4);
					Employee employee = EmployeeData.searchEmployeeData(d, null, null, -1).get(0);
					String e = rs.getString(5);
					LocalDateTime f = rs.getTimestamp(6).toLocalDateTime();
					LocalDateTime g = null;
					if (e.equals("1"))  g = rs.getTimestamp(7).toLocalDateTime();
					float h = rs.getFloat(8);
					ArrayList<BillDetail> i = BillDetailData.getBillDetailData(a); 
					
					m.setBillID(a);
					m.setBillTable(table);
					m.setBillCustomer(customer);
					m.setBillEmployee(employee);
					m.setBillStatus(e);
					m.setBillCreationTime(f);	
					if (e.equals("1")) m.setBillClosingTime(g);
					m.setBillCost(h);
					m.setBillDetailMenuList(i);
					m.setBillTableID(b);
					
					
			        arr.add(m);
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
	
	public static long getNextBillID() throws SQLException {
		
		long a=-1;
		Statement statement = InitForm.connection.createStatement();
		try {
			
	
			String sql = "select last_number from user_sequences  where sequence_name = 'MAHD_SEQ'";
			ResultSet rs = statement.executeQuery(sql);
			try {
				
				while(rs.next()) {
					a=rs.getLong(1);
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
	
	
	public static ArrayList<Bill> addBillData(Bill bill) throws SQLException{
		errorMenu="";
		String sql = "{call SP_THEM_HD(?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
							
			cStmt.setInt(1, bill.getBillTable().getTableID());
			cStmt.setString(2, bill.getBillCustomer().getCustomerID());
			cStmt.setString(3, bill.getBillEmployee().getEmpID());
			cStmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			cStmt.execute();
			
			for (int i=0; i<bill.getBillDetailMenuList().size(); i++) {
				try {
					BillDetailData.addBillDetailData(bill.getBillDetailMenuList().get(i));}
				catch (Exception e) {
					errorMenu+=bill.getBillDetailMenuList().get(i).getBillDetailMenuName()+"\n";
					e.printStackTrace();
				}
			}
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		
		
		ArrayList<Bill> arr = BillData.searchBillData(-1, null, null, null);		
		return arr;
	}
	
	public static ArrayList<Bill> updateBillData(Bill bill) throws SQLException{
		errorMenu="";
			for (int i=0; i<bill.getBillDetailMenuList().size(); i++) {
				try {
					BillDetailData.addBillDetailData(bill.getBillDetailMenuList().get(i));}
				catch (Exception e) {
					errorMenu+=bill.getBillDetailMenuList().get(i).getBillDetailMenuName()+"\n";
					e.printStackTrace();
				}
			}
		
		
		
		ArrayList<Bill> arr = BillData.searchBillData(-1, null, null, null);		
		return arr;
	}
	
	public static void chargeBill(Bill bill) throws SQLException{
		String sql = "{call SP_TINHTIEN_HD(?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {		
			System.out.println(bill.getBillID());
			System.out.println(bill.getBillCustomer().getCustomerID());
			System.out.println(Timestamp.valueOf(LocalDateTime.now()));
			cStmt.setLong(1, bill.getBillID());
			cStmt.setString(2, bill.getBillCustomer().getCustomerID());
			cStmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			cStmt.execute();			
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
	}
	
	public static Income getDailyIncome() throws SQLException {

		ArrayList<Bill> arr = new ArrayList<Bill>();
		float fl;
		String sql = "{call SP_TRACUU_DOANHTHU(?,?)}";
		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {

			
			cStmt.registerOutParameter(1, OracleTypes.CURSOR);
			cStmt.registerOutParameter(2, OracleTypes.FLOAT);
			cStmt.executeUpdate();

			ResultSet rs = (ResultSet) cStmt.getObject(1);
			try {
				
				
				while (rs.next()) {
				   	  
					long a = rs.getLong(1);
					int b = rs.getInt(2);
					String c = rs.getString(3);
					String d = rs.getString(4);
					String e = rs.getString(5);
					Timestamp ts1 = rs.getTimestamp(6);
					LocalDateTime f = ts1.toLocalDateTime(); 
					Timestamp ts2 = rs.getTimestamp(7);
					LocalDateTime g = ts2.toLocalDateTime();
					float h = rs.getFloat(8);
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					
					System.out.println(a+" "+b+" "+c+" "+d+" "+e+" "+f.format(formatter)+" "+g.format(formatter)+" "+h);
					
					Bill bill = new Bill();
					bill.setBillID(a);
					bill.setBillTableID(b);
					bill.setBillCustomerID(c);
					bill.setBillEmployeeID(d);
					bill.setBillStatus(e);
					bill.setBillCreationTime(f);
					bill.setBillClosingTime(g);
					bill.setBillCost(h);
					
					arr.add(bill);
					
				}			
			} finally {
				try {
					rs.close();} catch (Exception ignore) {}

				}
			fl=cStmt.getFloat(2);
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}

		Income income = new Income();
		income.setIncomeBillArr(arr);
		income.setIncomeTotal(fl);
		return income;
	}
}
