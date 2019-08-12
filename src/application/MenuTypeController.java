package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MenuTypeController {

    @FXML
    private Button modconfirmButton;

    @FXML
    private ComboBox<String> modmenutypeidCBox;

    @FXML
    private TextField modmenutypenameTField;
    
    @FXML
    private TextField delmenutypenameTField;

    @FXML
    private Button modcancelButton;

    @FXML
    private Button delconfirmButton;

    @FXML
    private TextField addmenutypeidTField;

    @FXML
    private Button addconfirmButton;

    @FXML
    private Button delcancelButton;

    @FXML
    private TextField addmenutypenameTField;

    @FXML
    private Button addcancelButton;

    @FXML
    private ComboBox<String> delmenutypeidCBox;
    
    @FXML
    void initialize() throws SQLException {
    	
    	addmenutypeidTField.setText(String.valueOf(MenuTypeData.getNextMenuTypeID()));
    	
    	ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
//    	for (int i=0;i<arr.size();i++) {
//    		int tmp=arr.get(i).getMenuTypeID();
////    		modmenutypeidCBox.getItems().add(String.valueOf(tmp));    	
//    		delmenutypeidCBox.getItems().add(String.valueOf(tmp));    	
//
////    	}
////    	modmenutypeidCBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
////    		String tmp = "";
////    		int i=-1;
////    		while (!tmp.equals(newValue)&&i<arr.size()) {
////    			i++;
////    			tmp=String.valueOf(arr.get(i).getMenuTypeID());
////    		}
////    		if (i<arr.size() && i>-1) {
////    			modmenutypenameTField.setText(arr.get(i).getMenuTypeName());
////    		}
////    	});
////    	
//    	delmenutypeidCBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
//    		String tmp = "";
//    		int i=-1;
//    		while (!tmp.equals(newValue)&&i<arr.size()) {
//    			i++;
//    			tmp=String.valueOf(arr.get(i).getMenuTypeID());
//    		}
//    		if (i<arr.size() && i>-1) {
//    			delmenutypenameTField.setText(arr.get(i).getMenuTypeName());
//    		}
//    	});
    	
 
    	
    }
    
    public void closeWindow() {
    	Stage stage = (Stage) addconfirmButton.getScene().getWindow();
		stage.close();
    }

    @FXML
    void confirmAdd(ActionEvent event) throws SQLException {
    	
    	if (addmenutypenameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	
    	MenuType menuType = new MenuType();
    	menuType.setMenuTypeName(addmenutypenameTField.getText());
    	MenuTypeData.addMenuTypeData(menuType);
    	closeWindow();
    }


    @FXML
    void cancelAdd(ActionEvent event) {
    	closeWindow();
    }

    @FXML
    void confirmMod(ActionEvent event) {
    	if (modmenutypenameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    }

    @FXML
    void cancelMod(ActionEvent event) {
    	closeWindow();

    }

    @FXML
    void confirmDel(ActionEvent event) {
    	if (delmenutypenameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    }

    @FXML
    void cancelDel(ActionEvent event) {
    	closeWindow();

    }

}
