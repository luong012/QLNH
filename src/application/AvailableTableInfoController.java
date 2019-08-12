package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AvailableTableInfoController {

    @FXML
    private TableView<Table> tableTView;

    @FXML
    private ComboBox<String> tablestatusCBox;

    @FXML
    private TableColumn<Table, Number> tablemaxcusColumn;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<Table, String> tablestatusColumn;

    @FXML
    private TextField tableidTField;

    @FXML
    private Button confirmButton;

    @FXML
    private ComboBox<String> tabletypeCBox;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Table, String> tabletypeColumn;

    @FXML
    private TextField cusnumTField;

    @FXML
    private TableColumn<Table, Number> tableidColumn;

    @FXML
    private TableColumn<Table, String> tabledescColumn;
    
    static ObservableList<Table> tableList;
    
    
    public void updateTableView() throws SQLException {


    	
    	ArrayList<Table> arr = TableData.searchTableData(-1,null,"0",-1);

    	
    	tableList = FXCollections.observableArrayList(arr);
    	tableTView.setItems(tableList);
    	
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	

    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }
    
    @FXML
    void initialize() throws SQLException {
    	
    	tableidColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableID"));
    	tabletypeColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableTypeName"));
    	tablemaxcusColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableMaxCus"));
    	tablestatusColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableStatus"));
    	tabledescColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableDesc"));

    	updateTableView();
    	
    	ArrayList<TableType> arr = TableTypeData.getTableTypeData();
    	tabletypeCBox.getItems().add("All");
    	tabletypeCBox.getSelectionModel().select(0);
    	for (int i=0;i<arr.size();i++) {
    		tabletypeCBox.getItems().add(arr.get(i).getTableName());    	
    	}
    	tablestatusCBox.getItems().addAll("All","0","1");
    	tablestatusCBox.getSelectionModel().select(1);
    	
    }
    
    @FXML
    void confirmSelected(ActionEvent event) {
    	int i=-1;
    	i= tableTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Select Table Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one table");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	if(tableList.get(i).getTableStatus().equals("1")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Select Table Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Table is currently unavailable");
    	    alert.showAndWait();
    	    return ;
    	}
    	Global.selectedTable=tableList.get(i);
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();
    }

    @FXML
    void findResult(ActionEvent event) throws SQLException {
    	
    	int a = -1;
    	if (!tableidTField.getText().equals("")) a = Integer.parseInt(tableidTField.getText());
    	String b = tabletypeCBox.getSelectionModel().getSelectedItem();
    	if (b.equals("All")) b=null;
    	String c = tablestatusCBox.getSelectionModel().getSelectedItem();
    	if (c.equals("All")) c=null;

		int d = -1;
    	if (!cusnumTField.getText().equals("")) d= Integer.parseInt(cusnumTField.getText());
    	ArrayList<Table> arr = TableData.searchTableData(a, b, c, d);

    	tableList = FXCollections.observableArrayList(arr);
    	tableTView.setItems(tableList);

    }

}
