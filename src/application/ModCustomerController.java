package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ModCustomerController {

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
    void initialize() {
    	
    	Customer cus = Global.selectedCus;
    	
    	cusidTField.setText(cus.getCustomerID());
    	cusnameTField.setText(cus.getCustomerName());
    	cusbirthDPicker.setValue(cus.getCustomerBirth());
    	cusphoneTField.setText(cus.getCustomerPhone());
    	cusaddressTField.setText(cus.getCustomerAddress());
    	
    }

    @FXML
    void confirmMod(ActionEvent event) {
    	
    	Customer cus = new Customer();
    	
    	cus.setCustomerID(cusidTField.getText());
    	cus.setCustomerName(cusnameTField.getText());
    	cus.setCustomerBirth(cusbirthDPicker.getValue());
    	cus.setCustomerPhone(cusphoneTField.getText());
    	cus.setCustomerAddress(cusaddressTField.getText());
    	
    	try {
			CustomerData.updateCustomerData(cus);
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
