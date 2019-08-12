package application;

import java.time.LocalDate;

public class Employee {
	
	private String empID;
	private String empName;
	private LocalDate empBirth;
	private String empAddress;
	private String empPhone;
	private LocalDate empWDate;
	private Role empRole;
	private String empRoleName;
	private int empStatus;
	private String empStatusText;
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public LocalDate getEmpBirth() {
		return empBirth;
	}
	public void setEmpBirth(LocalDate empBirth) {
		this.empBirth = empBirth;
	}
	public String getEmpAddress() {
		return empAddress;
	}
	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}
	public String getEmpPhone() {
		return empPhone;
	}
	public void setEmpPhone(String empPhone) {
		this.empPhone = empPhone;
	}
	public LocalDate getEmpWDate() {
		return empWDate;
	}
	public void setEmpWDate(LocalDate empWDate) {
		this.empWDate = empWDate;
	}
	public Role getEmpRole() {
		return empRole;
	}
	public void setEmpRole(Role empRole) {
		this.empRole = empRole;
	}
	public String getEmpRoleName() {
		return empRoleName;
	}
	public void setEmpRoleName(String empRoleName) {
		this.empRoleName = empRoleName;
	}
	public int getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(int empStatus) {
		this.empStatus = empStatus;
	}
	public String getEmpStatusText() {
		return empStatusText;
	}
	public void setEmpStatusText(String empStatusText) {
		this.empStatusText = empStatusText;
	}

}
