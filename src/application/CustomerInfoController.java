package application;

import java.sql.SQLException;
import java.time.LocalDate;
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

public class CustomerInfoController {

    @FXML
    private ComboBox<String> custypeCBox;

    @FXML
    private TableColumn<Customer, String> cusnameColumn;
    
    @FXML
    private TableColumn<Customer, String> custypeColumn;

    @FXML
    private TableColumn<Customer, String> cusaddressColumn;

    @FXML
    private Button delButton;

    @FXML
    private Button findButton;

    @FXML
    private TableView<Customer> cusTView;

    @FXML
    private TextField cusphoneTField;

    @FXML
    private Button modButton;

    @FXML
    private TextField cusidTField;

    @FXML
    private Button exitButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<Customer, LocalDate> cusbirthColumn;

    @FXML
    private Button addcusButton;

    @FXML
    private TableColumn<Customer, String> cusphoneColumn;

    @FXML
    private TableColumn<Customer, String> cusidColumn;
    
    @FXML
    private TableColumn<Customer, Number> cuspointColumn;

    @FXML
    private TextField cusnameTField;
    
    static ObservableList<Customer> customerList;
    
    
    public void updateCusTView() throws SQLException {


    	
    	ArrayList<Customer> arr = CustomerData.searchCustomerData(null,null,null,null);

    	
    	customerList = FXCollections.observableArrayList(arr);
    	cusTView.setItems(customerList);
    	
    }

    @FXML
    void initialize() throws SQLException {
    	
    	cusidColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
    	cusnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
    	cusbirthColumn.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("customerBirth"));
    	cusaddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
    	cusphoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
    	custypeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerTypeName"));
    	cuspointColumn.setCellValueFactory(new PropertyValueFactory<Customer, Number>("customerPoint"));

    	updateCusTView();
    	
    	ArrayList<CustomerType> arr = CustomerTypeData.getCustomerTypeData();
    	custypeCBox.getItems().add("All");
    	custypeCBox.getSelectionModel().select(0);
    	for (int i=0;i<arr.size();i++) {
    		custypeCBox.getItems().add(arr.get(i).getCustomerTypeName());    	
    	}
    }
    

    @FXML
    void viewAddCusWindow(ActionEvent event) {
    	
    	AddCustomerWindow addCustomerWindow = new AddCustomerWindow();
		Stage stage = (Stage) findButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addCustomerWindow.start(newStage);  
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			cusTView.getItems().clear();
    			try {
    				updateCusTView();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});

    }

    @FXML
    void viewModCusWindow(ActionEvent event) {
    	
    	int i=-1;
    	i= cusTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Modify Customer Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one item to modify");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	Global.selectedCus=cusTView.getSelectionModel().getSelectedItem();
    	
    	ModCustomerWindow modCustomerWindow = new ModCustomerWindow();
		Stage stage = (Stage) findButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	modCustomerWindow.start(newStage);  
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			cusTView.getItems().clear();
    			try {
    				updateCusTView();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});

    }

    @FXML
    void DelCusAlert(ActionEvent event) {

    }
    
    @FXML
    void show(ActionEvent event) throws SQLException {
    	
    	CustomerStatWindow customerStatWindow = new CustomerStatWindow();
		Stage stage = (Stage) findButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	customerStatWindow.start(newStage);  
    	
    }

    @FXML
    void refreshCus(ActionEvent event) throws SQLException {
    	
    	cusTView.getItems().clear();
    	updateCusTView();

    }

    @FXML
    void findResult(ActionEvent event) throws SQLException {
    	
    	String a = custypeCBox.getSelectionModel().getSelectedItem();
    	if (a.equals("All")) a=null;
    	
    	ArrayList<Customer> arr = CustomerData.searchCustomerData(cusidTField.getText(), cusnameTField.getText(), cusphoneTField.getText(), a);
    	

    	customerList = FXCollections.observableArrayList(arr);
    	cusTView.setItems(customerList);
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) findButton.getScene().getWindow();
    	stage.close();

    }

}
