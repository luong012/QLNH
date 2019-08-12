package application;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddEmployeeController {

    @FXML
    private TextField empphoneTField;

    @FXML
    private RadioButton chefRadio;

    @FXML
    private TextField empnameTField;

    @FXML
    private RadioButton waiterRadio;

    @FXML
    private RadioButton manRadio;

    @FXML
    private TextField empidTField;

    @FXML
    private RadioButton cashierRadio;

    @FXML
    private DatePicker empbirthDPicker;

    @FXML
    private TextField empaddressTField;

    @FXML
    private ToggleGroup position;

    @FXML
    private Button confirmButton;

    @FXML
    private Button closeButton;
    
    @FXML
    void confirmAdd(ActionEvent event) {
    	
    	if (empidTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	if (empnameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
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
    	
    	try {
			EmployeeData.addEmployeeData(emp);
		} catch (SQLIntegrityConstraintViolationException e) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Customer Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Customer ID already exists!");
    	    alert.showAndWait();
    	    return ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
