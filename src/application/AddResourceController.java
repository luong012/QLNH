package application;

import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class AddResourceController {

    @FXML
    private ComboBox<String> resourceunitCBox;

    @FXML
    private TextField resourcenameTField;

    @FXML
    private TextField resourceunitpriceTField;

    @FXML
    private Button closeButton;

    @FXML
    private TextField resourceidTField;

    @FXML
    private Button confirmButton;
    
    
    
    @FXML
    void initialize() throws SQLException {
    	
    	resourceunitCBox.getItems().addAll("gam", "kg", "con", "bó" , "gói", "củ" , "lon", "chai");
    	resourceunitCBox.getSelectionModel().select(0);
    	resourceidTField.setText(String.valueOf(ResourceData.getNextResourceID()));
    	
    }

    boolean checkTFNull(TextField textField) {
    	if (textField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	}
    	return textField.getText().equals("");
    }
    
    void exitWindow() {
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();
    }
    
    @FXML
    void confirmAdd(ActionEvent event) throws SQLException {
    	
    	if (checkTFNull(resourcenameTField)) return ;
    	if (checkTFNull(resourceunitpriceTField)) return ;
    	
    	Resource resource = new Resource();
    	resource.setResourceName(resourcenameTField.getText());
    	resource.setResourceUnit(resourceunitCBox.getSelectionModel().getSelectedItem());
    	resource.setResourceUnitPrice(Float.parseFloat(resourceunitpriceTField.getText()));
    	
    	ResourceData.addResourceData(resource);
    	exitWindow();

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	exitWindow();
    }

}
