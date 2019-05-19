package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewTableInfoController {

    @FXML
    private TableView<Table> tableTView;

    @FXML
    private Button addtableButton;

    @FXML
    private ComboBox<?> tablestatusCBox;

    @FXML
    private Button exitButton;

    @FXML
    private Button tabletypemanButton;

    @FXML
    private Button delButton;

    @FXML
    private Button findButton;

    @FXML
    private TextField cusnumTField;

    @FXML
    private TextField tableidTField;

    @FXML
    private ComboBox<?> tabletypeCBox;

    @FXML
    private Button modButton;
    
    @FXML
    private TableColumn<Table, Number> tableidColumn;
    
    @FXML
    private TableColumn<Table, String> tabletypeColumn;
    
    @FXML
    private TableColumn<Table, Number> tablestatusColumn;

    @FXML
    private TableColumn<Table, String> tablemaxcusColumn;
    
    @FXML
    private TableColumn<Table, String> tabledescColumn;
    

    @FXML
    void initialize() throws SQLException {
    	
    	tableidColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableID"));
    	tabletypeColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableTypeName"));
    	tablemaxcusColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableMaxCus"));
    	tablestatusColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableStatus"));
    	tabledescColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableDesc"));

    	
    	ArrayList<Table> arr = TableData.getTableData();
//    	for (int i=0;i<arr.size();i++) {
//    		Table tmp = arr.get(i);
//    		tableTView.getItems().addAll("a","b","c","d","e");
//    	}
    	
    	ObservableList<Table> list = FXCollections.observableArrayList(arr);
    	tableTView.setItems(list);
    }

    @FXML
    void viewAddTableWindow(ActionEvent event) {
    	AddTableWindow addTableWindow = new AddTableWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addTableWindow.start(newStage);   	

    }

    @FXML
    void viewModTableWindow(ActionEvent event) {

    }

    @FXML
    void viewDelTableWindow(ActionEvent event) {

    }

    @FXML
    void viewTableTypeWindow(ActionEvent event) {

    }

    @FXML
    void findResult(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) exitButton.getScene().getWindow();
    	stage.close();

    }

}
