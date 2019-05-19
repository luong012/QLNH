package application;

public class Table {
	
	private int tableID;
	private String tableTypeName;
	private int tableMaxCus;
	private String tableStatus;
	private String tableDesc;
	public int getTableID() {
		return tableID;
	}
	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String getTableStatus() {
		return tableStatus;
	}
	public void setTableStatus(String tableStatus) {
		this.tableStatus = tableStatus;
	}
	public String getTableDesc() {
		return tableDesc;
	}
	public void setTableDesc(String tableDesc) {
		this.tableDesc = tableDesc;
	}
//	
//	private int tableID;
//	private TableType tableType;
//	private String tableStatus;
//	private String tableDesc;
//	public int getTableID() {
//		return tableID;
//	}
//	public void setTableID(int tableID) {
//		this.tableID = tableID;
//	}
//	public TableType getTableType() {
//		return tableType;
//	}
//	public void setTableType(TableType tableType) {
//		this.tableType = tableType;
//	}
//	public String getTableStatus() {
//		return tableStatus;
//	}
//	public void setTableStatus(String tableStatus) {
//		this.tableStatus = tableStatus;
//	}
//	public String getTableDesc() {
//		return tableDesc;
//	}
//	public void setTableDesc(String tableDesc) {
//		this.tableDesc = tableDesc;
//	}
	public String getTableTypeName() {
		return tableTypeName;
	}
	public void setTableTypeName(String tableTypeName) {
		this.tableTypeName = tableTypeName;
	}
	public int getTableMaxCus() {
		return tableMaxCus;
	}
	public void setTableMaxCus(int tableMaxCus) {
		this.tableMaxCus = tableMaxCus;
	}


}
