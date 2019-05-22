package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResourceInfoController {

    @FXML
    private TableView<Resource> resourceTView;

    @FXML
    private Button addtableButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<Resource, Number> resourceidColumn;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<Resource, String> resourcenameColumn;

    @FXML
    private TableColumn<Resource, String> resourceunitColumn;

    @FXML
    private TableColumn<Resource, Number> resourceunitpriceColumn;

    @FXML
    private TableColumn<Resource, Number> resourcequantityleftcolumn;

    @FXML
    private TextField resourceidTField;

    @FXML
    private Button modButton;
    
    static ObservableList<Resource> resourceList;


    @FXML
    void viewAddResourceWindow(ActionEvent event) {
    	
    	AddResourceWindow addResourceWindow = new AddResourceWindow();
		Stage stage = (Stage) findButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addResourceWindow.start(newStage);  

    }
    
    public void updateResourceTView() throws SQLException {


    	
    	ArrayList<Resource> arr = ResourceData.searchResourceData(null);

    	
    	resourceList = FXCollections.observableArrayList(arr);
    	resourceTView.setItems(resourceList);
    	
    }

    @FXML
    void initialize() throws SQLException {
    	
    	resourceidColumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceID"));
    	resourcenameColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("resourceName"));
    	resourceunitColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("resourceUnit"));
    	resourceunitpriceColumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceUnitPrice"));
    	resourcequantityleftcolumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceQuantityLeft"));

    	updateResourceTView();
    	
    }
    
    @FXML
    void viewModResourceWindow(ActionEvent event) {

    }

    @FXML
    void refreshResource(ActionEvent event) throws SQLException {
    	
    	resourceTView.getItems().clear();
    	updateResourceTView();
    	

    }

    @FXML
    void findResult(ActionEvent event) throws SQLException {

    	ArrayList<Resource> arr = ResourceData.searchResourceData(resourceidTField.getText());

    	
    	resourceList = FXCollections.observableArrayList(arr);
    	resourceTView.setItems(resourceList);
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) findButton.getScene().getWindow();
    	stage.close();

    }

}
