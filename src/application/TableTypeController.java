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

public class TableTypeController {

    @FXML
    private ComboBox<String> modtableidCBox;
    
    

    @FXML
    private TextField deltabletypenameTField;

    @FXML
    private TextField addtabletypemaxcusTField;

    @FXML
    private TextField addtabletypeidTField;

    @FXML
    private Button modcancelButton;

    @FXML
    private TextField modtabletypemaxcusTField;

    @FXML
    private Button addconfirmButton;

    @FXML
    private Button delcancelButton;

    @FXML
    private ComboBox<String> deltableidCBox;

    @FXML
    private Button modconfirmButton;

    @FXML
    private TextField deltabletypemaxcusTField;

    @FXML
    private TextField addtabletypenameTField;

    @FXML
    private TextField modtabletypenameTField;

    @FXML
    private Button delconfirmButton;

    @FXML
    private Button addcancelButton;
    
    @FXML
    void initialize() throws SQLException {
    	
    	addtabletypeidTField.setText(String.valueOf(TableTypeData.getNextTableTypeID()));
    	
    	ArrayList<TableType> arr = TableTypeData.getTableTypeData();
    	for (int i=0;i<arr.size();i++) {
    		int tmp=arr.get(i).getTableID();
    		modtableidCBox.getItems().add(String.valueOf(tmp));    	
    		deltableidCBox.getItems().add(String.valueOf(tmp));    	

    	}
    	modtableidCBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    		String tmp = "";
    		int i=-1;
    		while (!tmp.equals(newValue)&&i<arr.size()) {
    			i++;
    			tmp=String.valueOf(arr.get(i).getTableID());
    		}
    		if (i<arr.size() && i>-1) {
    			modtabletypemaxcusTField.setText(String.valueOf(arr.get(i).getMaxCus()));
    			modtabletypenameTField.setText(arr.get(i).getTableName());
    		}
    	});
    	
    	deltableidCBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    		String tmp = "";
    		int i=-1;
    		while (!tmp.equals(newValue)&&i<arr.size()) {
    			i++;
    			tmp=String.valueOf(arr.get(i).getTableID());
    		}
    		if (i<arr.size() && i>-1) {
    			deltabletypemaxcusTField.setText(String.valueOf(arr.get(i).getMaxCus()));
    			deltabletypenameTField.setText(arr.get(i).getTableName());
    		}
    	});
    	
 
    	}
    
    public void closeWindow() {
    	Stage stage = (Stage) addconfirmButton.getScene().getWindow();
		stage.close();
    }
    
    public void confirmAdd(ActionEvent event) throws SQLException {
    	
    	if (addtabletypemaxcusTField.getText().equals("") || addtabletypenameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	
    	TableType tableType = new TableType();
    	tableType.setMaxCus(Integer.parseInt(addtabletypemaxcusTField.getText()));
    	tableType.setTableName(addtabletypenameTField.getText());
    	TableTypeData.addTableData(tableType);
    	closeWindow();
    }
    
    public void cancelAdd(ActionEvent event) {
    	closeWindow();
    }
    public void confirmMod(ActionEvent event) {
    	if (modtabletypemaxcusTField.getText().equals("") || modtabletypenameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	closeWindow();
    }
    
    public void cancelMod(ActionEvent event) {
    	closeWindow();
    }
    public void confirmDel(ActionEvent event) {
    	if (deltabletypemaxcusTField.getText().equals("") || deltabletypenameTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	closeWindow();
    }
    
    public void cancelDel(ActionEvent event) {
    	closeWindow();
    }
    


}
