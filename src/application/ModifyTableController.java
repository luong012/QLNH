package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ModifyTableController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField maxcusnumberTField;
    
    @FXML
    private TextField tablestatusTField;

    @FXML
    private Label idnumberLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private TextArea tabledescTArea;

    @FXML
    private TextField tableidTField;

    @FXML
    private Button confirmButton;

    @FXML
    private ComboBox<String> tabletypeCBox;

    @FXML
    private Label nameLabel;

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    }
    
    @FXML
    void initialize() throws SQLException {
    	ArrayList<TableType> arr = TableTypeData.getTableTypeData();
    	for (int i=0;i<arr.size();i++) {
    		tabletypeCBox.getItems().add(arr.get(i).getTableName());    	
    	}
    	
    	ArrayList<Table> arr2 = TableData.getTableData();

    	tabletypeCBox.getSelectionModel().select(arr2.get(Global.prevIndex).getTableTypeName());
    	maxcusnumberTField.setText(String.valueOf(arr2.get(Global.prevIndex).getTableMaxCus()));
    	tabletypeCBox.getSelectionModel().selectedItemProperty().addListener( (options, oldValue, newValue) -> {
    		String tmp = "";
    		int i=-1;
    		while (!tmp.equals(newValue)&&i<arr.size()) {
    			i++;
    			tmp=String.valueOf(arr.get(i).getTableName());
    		}
    		if (i<arr.size() && i>-1) {
    			maxcusnumberTField.setText(String.valueOf(arr.get(i).getMaxCus()));
    		}
    	});
    	
    	

    	tableidTField.setText(String.valueOf(arr2.get(Global.prevIndex).getTableID()));
    	tabledescTArea.setText(arr2.get(Global.prevIndex).getTableDesc());
    	tablestatusTField.setText(arr2.get(Global.prevIndex).getTableStatus());
    }
    
    @FXML
    void confirmAdd(ActionEvent event) throws SQLException {
    	
     	if (maxcusnumberTField.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	Table table = new Table();
    	ArrayList<TableType> arr = TableTypeData.getTableTypeData();
    	String string = tabletypeCBox.getSelectionModel().getSelectedItem();
    	String tmp = "";
		int i=-1;
		while (!tmp.equals(string)&&i<arr.size()) {
			i++;
			tmp=String.valueOf(arr.get(i).getTableName());
		}
		if (i<arr.size() && i>-1) {
			table.setTableTypeID(arr.get(i).getTableID());
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Confirm Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return ;
		}
    	table.setTableDesc(tabledescTArea.getText());
    	TableData.addTableData(table);
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    	
    }

}
