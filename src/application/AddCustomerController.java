package application;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddCustomerController {

    @FXML
    private DatePicker cusbirthDPicker;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cusaddressTField;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField cusphoneTField;

    @FXML
    private TextField cusidTField;

    @FXML
    private TextField cusnameTField;

    @FXML
    void confirmAdd(ActionEvent event) throws SQLException {
    	
    	if (cusidTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	if (cusnameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	Customer customer = new Customer();
    	customer.setCustomerID(cusidTField.getText());
    	customer.setCustomerName(cusnameTField.getText());
    	customer.setCustomerBirth(cusbirthDPicker.getValue());
    	customer.setCustomerAddress(cusaddressTField.getText());
    	customer.setCustomerPhone(cusphoneTField.getText());
    	
    	try {
			CustomerData.addCustomerData(customer);
		} catch (SQLIntegrityConstraintViolationException  e) {
			
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Customer Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Customer ID already exists!");
    	    alert.showAndWait();
    	    return ;
			
		}
    	
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    }
    
    @FXML
    void initialize() throws SQLException {
    	
    	
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    }

}
