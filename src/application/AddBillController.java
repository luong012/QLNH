package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class AddBillController {

    @FXML
    private TextField menunameTField;

    @FXML
    private TextField tablenameTField;

    @FXML
    private TableColumn<BillDetail, Number> billmenucostColumn;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<Menu, Number> menuunitpriceColumn;

    @FXML
    private Button tablebrowseButton;

    @FXML
    private Button cusbrowseButton;

    @FXML
    private TableView<Menu> menuTView;

    @FXML
    private Button confirmButton;

    @FXML
    private TableColumn<Menu, Number> menuavailablequantityColumn;

    @FXML
    private TableView<BillDetail> billTView;

    @FXML
    private TableColumn<Menu, String> menunameColumn;

    @FXML
    private TextField cusnameTField;

    @FXML
    private Button addmenuButton;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<BillDetail, String> billmenunameColumn;

    @FXML
    private TableColumn<Menu, String> menutypeColumn;

    @FXML
    private TableColumn<BillDetail, Number> billmenuquantityColumn;

    @FXML
    private TextField menuquantityTField;

    @FXML
    private Button delmenuButton;

    @FXML
    private TextField billidTField;
    
    @FXML
    private TextField totalcostTField;
    
    @FXML
    private ComboBox<String> menutypeCBox;
    
    private Table selectedTable = new Table();
    
    private Customer selectedCus = new Customer();
    
    private Bill bill= new Bill();
    
    static ObservableList<Menu> menuList;
    
    static ArrayList<BillDetail> billDetailMenuArrList = new ArrayList<BillDetail>();
    
    static ObservableList<BillDetail> billDetailMenuList;
    
    
    public void updateMenuTView(String a, String b, float c, int d) throws SQLException {


    	
    	ArrayList<Menu> arr = MenuData.searchMenuData(a,b,c,d);

    	menuTView.getItems().clear();

    	menuList = FXCollections.observableArrayList(arr);
    	menuTView.setItems(menuList);
    }
    
  	public void updateBillTView() throws SQLException { 		
  		
  		billTView.getItems().clear();
  		billDetailMenuList = FXCollections.observableArrayList(billDetailMenuArrList);
  		billTView.setItems(billDetailMenuList);
  		
  		float total = 0;
  		for (int i=0; i <billDetailMenuList.size();i++) {
  			total+=billDetailMenuList.get(i).getBillDetailMenuCost();
  		}
  		
  		totalcostTField.setText(String.valueOf(total));
  	}
    
    @FXML
    void initialize() throws SQLException {
    	billDetailMenuArrList.removeAll(billDetailMenuArrList);
    	ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
		menutypeCBox.getItems().add("All");    	
    	for (int i=0;i<arr.size();i++) {
    		String tmp=arr.get(i).getMenuTypeName();
    		menutypeCBox.getItems().add(tmp);    	
    	}
    	
    	Global.selectedTable=new Table();
    	Global.selectedCus=new Customer();
    	
    	menunameColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuName"));
    	menutypeColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuTypeName"));
    	menuunitpriceColumn.setCellValueFactory(new PropertyValueFactory<Menu, Number>("menuCost"));
    	menuavailablequantityColumn.setCellValueFactory(new PropertyValueFactory<Menu, Number>("menuAvailableNumber"));
    	

    	billmenunameColumn.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("billDetailMenuName"));
    	billmenuquantityColumn.setCellValueFactory(new PropertyValueFactory<BillDetail, Number>("billDetailMenuQuantity"));
    	billmenucostColumn.setCellValueFactory(new PropertyValueFactory<BillDetail, Number>("billDetailMenuCost"));
    	
    	
    	menutypeCBox.getSelectionModel().select(0);
    	updateMenuTView(null, null, -1, -1);

    	billidTField.setText(String.valueOf(BillData.getNextBillID()));
    }

    @FXML
    void addMenu(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= menuTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one resource to add");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	if(menuquantityTField.getText().equals("") || menuquantityTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	if(Integer.parseInt(menuquantityTField.getText())<=0) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Number value must be greater than 0");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	Menu menu = menuList.get(i);
    	
    	for (int i1=0;i1<billDetailMenuArrList.size();i1++) 
    		if (menu.getMenuID()==billDetailMenuArrList.get(i1).getBillDetailMenuID()) {
    			billDetailMenuArrList.get(i1).setBillDetailMenuQuantity(billDetailMenuArrList.get(i1).getBillDetailMenuQuantity()+Integer.parseInt(menuquantityTField.getText()));
    			menuquantityTField.setText("");
    			billDetailMenuArrList.get(i1).setBillDetailMenuCost(billDetailMenuArrList.get(i1).getBillDetailMenuQuantity()*billDetailMenuArrList.get(i1).getBillDetailMenuUnitPrice());
    			updateBillTView();
    	    	return;
    		}
    	BillDetail billDetail = new BillDetail();
    	billDetail.setBillDetailMenuID(menu.getMenuID());
    	billDetail.setBillDetailMenuName(menu.getMenuName());
    	billDetail.setBillDetailMenuQuantity(Integer.parseInt(menuquantityTField.getText()));
    	menuquantityTField.setText("");
	    billDetail.setBillDetailMenuUnitPrice(menu.getMenuCost());
	    billDetail.setBillDetailMenuCost(billDetail.getBillDetailMenuQuantity()*billDetail.getBillDetailMenuUnitPrice());
	    billDetailMenuArrList.add(billDetail);
    	
	    updateBillTView();
    }

    @FXML
    void findMenu(ActionEvent event) throws SQLException {
    	String a = menutypeCBox.getSelectionModel().getSelectedItem();
    	if (a.equals("All")) a=null;
    	updateMenuTView(menunameTField.getText(),a,-1,-1);

    }


    @FXML
    void delMenu(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= billTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Delete Bill Detail Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one bill menu to delete");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	billDetailMenuArrList.remove(i);
    	updateBillTView();

    }

    @FXML
    void confirmBill(ActionEvent event) {
    	
    	if(tablenameTField.getText().equals("") || tablenameTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Bill Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}  
    	
    	if(totalcostTField.getText().equals("") || totalcostTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Bill Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}  
    	
    	Bill bill = new Bill();
    	bill.setBillCustomer(selectedCus);
    	bill.setBillTable(selectedTable);
    	bill.setBillEmployee(Global.activeEmp);
    	bill.setBillCost(Float.parseFloat(totalcostTField.getText()));
    	bill.setBillDetailMenuList(billDetailMenuArrList);
    	try {
			BillData.addBillData(bill);
		} catch (SQLException e) {
			if (e.getErrorCode()==20001) {
				Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Add Bill Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Not enough resource");
	    	    alert.showAndWait();
	    	    return;
			}
			
			if (e.getErrorCode()==20002) {
				Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Add Bill Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Table is currently unavailable");
	    	    alert.showAndWait();
	    	    return;
			}
			e.printStackTrace();
		}
    	if (!BillData.errorMenu.equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Bill Error");
    		alert.setHeaderText(null);
    		alert.setContentText(BillData.errorMenu);
    	    alert.showAndWait();
    	    return;
    	}
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();
    	
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void browseAvailableTable(ActionEvent event) {
    	
    	AvailableTableInfoWindow availableTableInfoWindow = new AvailableTableInfoWindow();
		Stage stage = (Stage) closeButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	availableTableInfoWindow.start(newStage);
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {
    			if (Global.selectedTable.getTableID()!=0) {
    			System.out.println("Ok");
    			selectedTable = Global.selectedTable;
    	    	
    	    	tablenameTField.setText(String.valueOf(selectedTable.getTableID()));
    	    	
    	    	bill.setBillTable(selectedTable);
    			}
    	    }
    	});
    	
    	
    }

    @FXML
    void browseAvailableCus(ActionEvent event) {
    	
    	AvailableCustomerInfoWindow availableCustomerInfoWindow = new AvailableCustomerInfoWindow();
		Stage stage = (Stage) closeButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	availableCustomerInfoWindow.start(newStage);
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {
    			if (Global.selectedCus.getCustomerID()!=null) {
    			System.out.println("Ok");
    			selectedCus = Global.selectedCus;
    	    	
    	    	cusnameTField.setText(String.valueOf(selectedCus.getCustomerName()));
    	    	
    	    	bill.setBillCustomer(selectedCus);
    			}
    	    }
    	});

    }

}
