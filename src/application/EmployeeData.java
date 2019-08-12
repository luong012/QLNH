package application;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import oracle.jdbc.OracleTypes;

public class EmployeeData {
	
	public static ArrayList<Employee> addEmployeeData(Employee a) throws SQLException{
		String sql = "{call SP_THEM_NV(?,?,?,?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, a.getEmpID());
			cStmt.setString(2, a.getEmpName());
			if (a.getEmpBirth()==null) cStmt.setDate(3, null);
			else
				cStmt.setDate(3, Date.valueOf(a.getEmpBirth()));
			cStmt.setString(4, a.getEmpAddress());
			cStmt.setString(5, a.getEmpPhone());
			cStmt.setNString(6, a.getEmpRole().getRoleName());
			cStmt.setDate(7, Date.valueOf(LocalDate.now()));
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		ArrayList<Employee> arr = EmployeeData.searchEmployeeData(null,null,null,-1);
		
		return arr;
	}
	
	public static ArrayList<Employee> updateEmployeeData(Employee a) throws SQLException{
		String sql = "{call SP_SUA_NV(?,?,?,?,?,?,?,?)}";

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, a.getEmpID());
			cStmt.setString(2, a.getEmpName());
			if (a.getEmpBirth()==null) cStmt.setDate(3, null);
			else
				cStmt.setDate(3, Date.valueOf(a.getEmpBirth()));
			cStmt.setString(4, a.getEmpAddress());
			cStmt.setString(5, a.getEmpPhone());
			cStmt.setDate(6, Date.valueOf(LocalDate.now()));
			cStmt.setNString(7, a.getEmpRole().getRoleName());
			cStmt.setInt(8,a.getEmpStatus());
			cStmt.execute();
		} finally {
			try {
				cStmt.close();} catch (Exception ignore) {}
			}
		
		ArrayList<Employee> arr = EmployeeData.searchEmployeeData(null,null,null,-1);
		
		return arr;
	}
	
	
	public static ArrayList<Employee> searchEmployeeData(String x, String y, String z, int t) throws SQLException{
		String sql = "begin SP_TRACUU_NV(?,?,?,?,?); end;";
		ArrayList<Employee> arr = new ArrayList<Employee>();

		CallableStatement cStmt = InitForm.connection.prepareCall(sql);
		try {
			
			cStmt.setString(1, x);
			if (y==null) 
				cStmt.setNString(2, null);
			else
				cStmt.setNString(2, y.toUpperCase());
			cStmt.setNString(3, z);
			if (t==-1) 
				cStmt.setString(4, null);
			else 
				cStmt.setInt(4, t);
			cStmt.registerOutParameter(5, OracleTypes.CURSOR);
			cStmt.executeUpdate();
			
			
	
			ResultSet rs = (ResultSet) cStmt.getObject(5);
			try {
			
				while (rs.next()) {
					Employee emp = new Employee();
					String a = rs.getString(1);
					String b = rs.getNString(2);
					LocalDate c = rs.getDate(3).toLocalDate();
					String d = rs.getNString(4);
					String e = rs.getString(5);
					LocalDate f = rs.getDate(6).toLocalDate();
					int g = rs.getInt(7);
					String h = rs.getNString(8);
					Role gh = new Role();
					gh.setRoleID(g);
					gh.setRoleName(h);
					int i = rs.getInt(9);
					
				   	 
					emp.setEmpID(a);
					emp.setEmpName(b);
					emp.setEmpBirth(c);
					emp.setEmpAddress(d);
					emp.setEmpPhone(e);
					emp.setEmpWDate(f);
					emp.setEmpRole(gh);
					emp.setEmpRoleName(h);
					emp.setEmpStatus(i);
					
					if (i==0) emp.setEmpStatusText("Đang làm việc"); else emp.setEmpStatusText("Đã nghỉ việc");
					  
					  arr.add(emp);
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
