package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ModEmployeeController {

    @FXML
    private TextField empphoneTField;

    @FXML
    private TextField empnameTField;

    @FXML
    private ComboBox<String> empstatusCBox;

    @FXML
    private RadioButton manRadio;

    @FXML
    private DatePicker empbirthDPicker;

    @FXML
    private Button confirmButton;

    @FXML
    private RadioButton chefRadio;

    @FXML
    private RadioButton waiterRadio;

    @FXML
    private TextField empidTField;

    @FXML
    private Button closeButton;

    @FXML
    private RadioButton cashierRadio;

    @FXML
    private TextField empaddressTField;

    @FXML
    private ToggleGroup position;

    @FXML
    private DatePicker empwdateDPicker;

    
    @FXML
    void initialize() {
    	Employee emp = Global.selectedEmp;
    	empidTField.setText(emp.getEmpID());
    	empnameTField.setText(emp.getEmpName());
    	empbirthDPicker.setValue(emp.getEmpBirth());
    	empaddressTField.setText(emp.getEmpAddress());
    	empphoneTField.setText(emp.getEmpPhone());
    	empwdateDPicker.setValue(emp.getEmpWDate());
    	empstatusCBox.getItems().addAll("Đang làm việc","Đã nghỉ việc");
    	empstatusCBox.getSelectionModel().select(emp.getEmpStatus());
    	int a = emp.getEmpRole().getRoleID();
    	if (a==1) position.selectToggle(manRadio);
    	if (a==2) position.selectToggle(cashierRadio);
    	if (a==3) position.selectToggle(waiterRadio);
    	if (a==4) position.selectToggle(chefRadio);
    	 
    }
    @FXML
    void confirmMod(ActionEvent event) {
    	
    	Employee emp = new Employee();
    	emp.setEmpID(empidTField.getText());
    	emp.setEmpName(empnameTField.getText());
    	emp.setEmpBirth(empbirthDPicker.getValue());
    	emp.setEmpAddress(empaddressTField.getText());
    	emp.setEmpPhone(empphoneTField.getText());
    	RadioButton selectedValue = (RadioButton) position.getSelectedToggle();
    	Role role = new Role();
    	role.setRoleName(selectedValue.getText());
    	emp.setEmpRole(role);
    	emp.setEmpWDate(empwdateDPicker.getValue());
    	if (empstatusCBox.getSelectionModel().getSelectedItem().equals("Đang làm việc"))
    		emp.setEmpStatus(0);
    	else 
    		emp.setEmpStatus(1);
    	try {
			EmployeeData.updateEmployeeData(emp);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Modify Customer Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Something went wrong. Please try again.");
    	    alert.showAndWait();
    	    return ;
		}
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();


    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    }

}
