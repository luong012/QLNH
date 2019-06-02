package application;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Menu restaurantMenu;

    @FXML
    private MenuItem refreshItem;

    @FXML
    private MenuItem modifymenuItem;

    @FXML
    private MenuItem menuinfoItem;

    @FXML
    private MenuItem addtableItem;

    @FXML
    private MenuItem editbillItem;

    @FXML
    private MenuItem updatecusItem;

    @FXML
    private Menu financeMenu;

    @FXML
    private MenuItem checkinItem;

    @FXML
    private MenuItem resourceinfoItem;

    @FXML
    private MenuItem deletetableItem;

    @FXML
    private MenuItem regcusItem;

    @FXML
    private Button shortcut2Button;

    @FXML
    private MenuItem newbillItem;

    @FXML
    private MenuItem empmanItem;

    @FXML
    private Menu fileMenu;

    @FXML
    private Button shortcut5Button;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private SeparatorMenuItem file1Separator;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private MenuItem addmenuItem;

    @FXML
    private MenuItem tableinfoItem;

    @FXML
    private MenuItem addresourceItem;

    @FXML
    private MenuItem exitItem;

    @FXML
    private MenuItem invenreportItem;

    @FXML
    private MenuItem revenuereportItem;

    @FXML
    private MenuItem chargeItem;

    @FXML
    private MenuItem modifyresourceItem;

    @FXML
    private MenuItem accdetailsItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem searchcusItem;

    @FXML
    private MenuItem modifytableItem;

    @FXML
    private Menu resourcemanMenu;

    @FXML
    private SeparatorMenuItem file1Separator1;

    @FXML
    private Button shortcut3Button;

    @FXML
    private Menu menumanMenu;

    @FXML
    private Menu accountMenu;

    @FXML
    private Menu tablemanMenu;

    @FXML
    private MenuItem outcomereportItem;

    @FXML
    private MenuItem incomereportItem;

    @FXML
    private Button shortcut1Button;

    @FXML
    private Button shortcut4Button;
    
    @FXML
    private MenuItem menuoptionItem;
    
    @FXML
    private MenuItem resourceoptionItem;

    @FXML
    private MenuItem tableoptionItem;

    @FXML
    private MenuItem monthlyworkingreportItem;

    @FXML
    private MenuItem deletemenuItem;

    @FXML
    private Menu customerMenu;
    
    @FXML
    private MenuItem addorderItem;
    
    @FXML
    private MenuItem orderinfoItem;
    
    @FXML
    private MenuItem menutypemanItem;


    public void closeMainWindow(ActionEvent event) {
    	Platform.exit();
    }
    
    public void checkinConfirm(ActionEvent event) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Check In");
	    alert.setHeaderText(null);
	    alert.setContentText("Ready to check in");
	
	    Optional<ButtonType> option = alert.showAndWait();
	    if (option.get()==ButtonType.OK) {
	    	Alert alert2 = new Alert(AlertType.INFORMATION);
		    alert2.setTitle("Check In Success");
			alert2.setHeaderText(null);
		    alert2.setContentText("Check in successfully!");
		    alert2.showAndWait();	
		    checkinItem.setDisable(true);
	    }
    }
    
    public void viewAccDetails(ActionEvent event) {
    	EmployeeAccountDetailsWindow employeeAccountDetailsWindow = new EmployeeAccountDetailsWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	employeeAccountDetailsWindow.start(newStage);
        
    }
    
    public void viewTableTypeManagement(ActionEvent event) {
    	TableTypeWindow tableTypeWindow = new TableTypeWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	tableTypeWindow.start(newStage);
    }
    
    public void viewTableInfo(ActionEvent event) {
    	ViewTableInfoWindow viewTableInfoWindow = new ViewTableInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	viewTableInfoWindow.start(newStage);
    }
    
    public void addTable(ActionEvent event) {
    	AddTableWindow addTableWindow = new AddTableWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addTableWindow.start(newStage);   
    }
    
    public void resourceInfo(ActionEvent event) {
    	ResourceInfoWindow resourceInfoWindow = new ResourceInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	resourceInfoWindow.start(newStage);   
    }
    
    @FXML
    void viewAddResourceWindow(ActionEvent event) {
    	
    	AddResourceWindow addResourceWindow = new AddResourceWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addResourceWindow.start(newStage);  

    }
    
    @FXML
    void viewOrderInfoWindow(ActionEvent event) {
    	
    	OrderDetailInfoWindow orderDetailInfoWindow = new OrderDetailInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	orderDetailInfoWindow.start(newStage);  

    }

    @FXML
    void addOrder(ActionEvent event) {
    	
    	AddOrderWindow addOrderWindow = new AddOrderWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addOrderWindow.start(newStage);   	    	

    }
    
    public void viewMenuTypeManagement(ActionEvent event) {
    	MenuTypeWindow menuTypeWindow = new MenuTypeWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	menuTypeWindow.start(newStage);
    }
    
    @FXML
    void viewMenuInfoWindow(ActionEvent event) {
    	
    	MenuInfoWindow menuInfoWindow = new MenuInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	menuInfoWindow.start(newStage);   	    	

    }
    
    @FXML
    void viewCusInfoWindow(ActionEvent event) {
    	
    	CustomerInfoWindow customerInfoWindow = new CustomerInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	customerInfoWindow.start(newStage);   	

    }
    
    @FXML
    void registerCustomer(ActionEvent event) {
    	
    	AddCustomerWindow addCustomerWindow = new AddCustomerWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addCustomerWindow.start(newStage);   	

    }

    
}
