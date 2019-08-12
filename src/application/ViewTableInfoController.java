package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
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
    private ComboBox<String> tablestatusCBox;

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
    private ComboBox<String> tabletypeCBox;

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
    
    static ObservableList<Table> tableList;
    
    
    public void updateTableView() throws SQLException {


    	
    	ArrayList<Table> arr = TableData.searchTableData(-1,null,null,-1);

    	
    	tableList = FXCollections.observableArrayList(arr);
    	tableTView.setItems(tableList);
    	
    }
    
    public void updateTableTypeCBox() throws SQLException {
    	
    	tabletypeCBox.getItems().clear();
		ArrayList<TableType> arr = TableTypeData.getTableTypeData();
	   	tabletypeCBox.getItems().add("All");
    	tabletypeCBox.getSelectionModel().select(0);
    	for (int i=0;i<arr.size();i++) {
    		tabletypeCBox.getItems().add(arr.get(i).getTableName());    	
    	}
    	
    }

    @FXML
    void initialize() throws SQLException {
    	
    	tableidColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableID"));
    	tabletypeColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableTypeName"));
    	tablemaxcusColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableMaxCus"));
    	tablestatusColumn.setCellValueFactory(new PropertyValueFactory<Table, Number>("tableStatus"));
    	tabledescColumn.setCellValueFactory(new PropertyValueFactory<Table, String>("tableDesc"));

    	updateTableView();
    	
    	ArrayList<TableType> arr = TableTypeData.getTableTypeData();
    	tabletypeCBox.getItems().add("All");
    	tabletypeCBox.getSelectionModel().select(0);
    	for (int i=0;i<arr.size();i++) {
    		tabletypeCBox.getItems().add(arr.get(i).getTableName());    	
    	}
    	tablestatusCBox.getItems().addAll("All","0","1");
    	tablestatusCBox.getSelectionModel().select(0);
    	
    	if (Global.activeRole>1) {
    		modButton.setVisible(false);
    		tabletypemanButton.setVisible(false);
    		delButton.setVisible(false);
    		addtableButton.setVisible(false);
    	}
    	
    }
    
    @FXML
    void refreshTable(ActionEvent event) {

    	
    }
    

    @FXML
    void viewAddTableWindow(ActionEvent event) {
    	AddTableWindow addTableWindow = new AddTableWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addTableWindow.start(newStage);   	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    	    	tableTView.getItems().clear();
    			try {
    				updateTableView();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});
    }

    @FXML
    void viewModTableWindow(ActionEvent event) throws SQLException {

    	int i=-1;
    	i= tableTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Modify Table Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one item to modify");
    	    alert.showAndWait();
    	    return ;
    	}
    	Global.prevIndex=i;
    	ModifyTableWindow modifyTableWindow = new ModifyTableWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	modifyTableWindow.start(newStage);   
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    	    	tableTView.getItems().clear();
    			try {
    				updateTableView();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});
    }

    @FXML
    void viewDelTableWindow(ActionEvent event) {
    	
    	int i=-1;
    	i= tableTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Delete Table Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one item to delete");
    	    alert.showAndWait();
    	    return ;
    	}
    	Table table = tableList.get(i);
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Table");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure?");
	    Optional<ButtonType> option = alert.showAndWait();
	    if (option.get()==ButtonType.OK) {
	    	try {
				TableData.delTableData(table);
				Alert alert2 = new Alert(AlertType.INFORMATION);
	    		alert2.setTitle("Success");
	    		alert2.setHeaderText(null);
	    		alert2.setContentText("Delete table success");
	    	    alert2.showAndWait();
			} catch (SQLException e) {
				if (e.getErrorCode()==8177) {
					Alert alert2 = new Alert(AlertType.ERROR);
		    		alert2.setTitle("Delete Table Error");
		    		alert2.setHeaderText(null);
		    		alert2.setContentText("Deadlock detected!");
		    	    alert2.showAndWait();
		    	    return ;
				}
	    	    e.printStackTrace();
				Alert alert2 = new Alert(AlertType.ERROR);
	    		alert2.setTitle("Delete Table Error");
	    		alert2.setHeaderText(null);
	    		alert2.setContentText("Something went wrong...");
	    	    alert2.showAndWait();
	    	    return ;
			}
	    }
	    tableTView.getItems().clear();
		try {
			updateTableView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void viewTableTypeWindow(ActionEvent event) {
    	TableTypeWindow tableTypeWindow = new TableTypeWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	tableTypeWindow.start(newStage);   	
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			try {
					updateTableTypeCBox();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
		
    	});


    }

    @FXML
    void findResult(ActionEvent event) throws SQLException {
		int a = -1;
    	if (!tableidTField.getText().equals("")) a = Integer.parseInt(tableidTField.getText());
    	String b = tabletypeCBox.getSelectionModel().getSelectedItem();
    	if (!(b==null)) if (b.equals("All")) b=null;
    	String c = tablestatusCBox.getSelectionModel().getSelectedItem();
    	if (c.equals("All")) c=null;

		int d = -1;
    	if (!cusnumTField.getText().equals("")) d= Integer.parseInt(cusnumTField.getText());
    	ArrayList<Table> arr = TableData.searchTableData(a, b, c, d);

    	tableList = FXCollections.observableArrayList(arr);
    	tableTView.setItems(tableList);
    	

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) exitButton.getScene().getWindow();
    	stage.close();

    }

}
