package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private Button refreshButton;

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
    
    
    public void updateTableView() throws SQLException {


    	
    	ArrayList<Table> arr = TableData.getTableData();

    	
    	ObservableList<Table> list = FXCollections.observableArrayList(arr);
    	tableTView.setItems(list);
    	
    }

    @FXML
    void initialize() throws SQLException {
    	
    	tableidColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableID"));
    	tabletypeColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableTypeName"));
    	tablemaxcusColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableMaxCus"));
    	tablestatusColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableStatus"));
    	tabledescColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableDesc"));

    	updateTableView();
    }
    
    @FXML
    void refreshTable(ActionEvent event) {
    	tableTView.getItems().clear();
		try {
			updateTableView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    

    @FXML
    void viewAddTableWindow(ActionEvent event) {
    	AddTableWindow addTableWindow = new AddTableWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addTableWindow.start(newStage);   	
    	newStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {
    	    }
    	});

    }

    @FXML
    void viewModTableWindow(ActionEvent event) {

    }

    @FXML
    void viewDelTableWindow(ActionEvent event) {

    }

    @FXML
    void viewTableTypeWindow(ActionEvent event) {
    	TableTypeWindow tableTypeWindow = new TableTypeWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	tableTypeWindow.start(newStage);   	

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
