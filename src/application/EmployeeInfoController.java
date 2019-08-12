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

public class EmployeeInfoController {

    @FXML
    private TableColumn<Employee, String> empstatusColumn;

    @FXML
    private TextField empnameTField;

    @FXML
    private ComboBox<String> empstatusCBox;

    @FXML
    private TableColumn<Employee, LocalDate> empwdateColumn;

    @FXML
    private ComboBox<String> emproleCBox;

    @FXML
    private TableColumn<Employee, String> empidColumn;

    @FXML
    private Button findButton;

    @FXML
    private Button addButton;

    @FXML
    private TableColumn<Employee, String> emprolenameColumn;

    @FXML
    private TableColumn<Employee, String> empaddressColumn;

    @FXML
    private Button modButton;

    @FXML
    private TableColumn<Employee, String> empnameColumn;

    @FXML
    private TableColumn<Employee, LocalDate> empbirthColumn;

    @FXML
    private TableColumn<Employee, String> empphoneColumn;

    @FXML
    private Button exitButton;

    @FXML
    private TableView<Employee> empTView;

    @FXML
    private TextField empidTField;

    @FXML
    private Button refreshButton;
    
    static ObservableList<Employee> employeeList;
    
    public void updateEmpTView() throws SQLException {


    	
    	ArrayList<Employee> arr = EmployeeData.searchEmployeeData(null,null,null,-1);

    	
    	employeeList = FXCollections.observableArrayList(arr);
    	empTView.setItems(employeeList);
    	
    }

    @FXML
    void initialize() throws SQLException {
    	
    	empidColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empID"));
    	empnameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empName"));
    	empbirthColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("empBirth"));
    	empaddressColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empAddress"));
    	empphoneColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empPhone"));
    	empwdateColumn.setCellValueFactory(new PropertyValueFactory<Employee, LocalDate>("empWDate"));
    	empstatusColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empStatusText"));
    	emprolenameColumn.setCellValueFactory(new PropertyValueFactory<Employee, String>("empRoleName"));

    	updateEmpTView();
    	
    	ArrayList<Role> arr = RoleData.getRoleData();
    	emproleCBox.getItems().add("All");
    	emproleCBox.getSelectionModel().select(0);
    	for (int i=0;i<arr.size();i++) {
    		emproleCBox.getItems().add(arr.get(i).getRoleName());    	
    	}
    	
    	empstatusCBox.getItems().addAll("All","Đang làm việc","Đã nghỉ việc");
    	empstatusCBox.getSelectionModel().select(0);
    	
    	
    }

    @FXML
    void viewAddEmpWindow(ActionEvent event) {
    	
    	AddEmployeeWindow addEmployeeWindow = new AddEmployeeWindow();
		Stage stage = (Stage) findButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addEmployeeWindow.start(newStage);  
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			empTView.getItems().clear();
    			try {
    				updateEmpTView();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});

    }

    @FXML
    void viewModEmpWindow(ActionEvent event) {
    	
    	int i=-1;
    	i= empTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Modify Table Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one item to modify");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	Global.selectedEmp=empTView.getSelectionModel().getSelectedItem();
    	
    	ModEmployeeWindow modEmployeeWindow = new ModEmployeeWindow();
		Stage stage = (Stage) findButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	modEmployeeWindow.start(newStage);  
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			empTView.getItems().clear();
    			try {
    				updateEmpTView();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});

    }

    @FXML
    void refreshCus(ActionEvent event) {

    }

    @FXML
    void findResult(ActionEvent event) throws SQLException {
    	String a = emproleCBox.getSelectionModel().getSelectedItem();
    	if (a.equals("All")) a=null;
    	
    	ArrayList<Employee> arr = EmployeeData.searchEmployeeData(empidTField.getText(), empnameTField.getText(), a, empstatusCBox.getSelectionModel().getSelectedIndex()-1);
    	
    	employeeList = FXCollections.observableArrayList(arr);
    	empTView.setItems(employeeList);

    	

    }
    
    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) findButton.getScene().getWindow();
    	stage.close();

    }

}
