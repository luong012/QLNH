package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddMenuController {

    @FXML
    private TextField menuidTField;

    @FXML
    private TextField menunameTField;

    @FXML
    private Button autocostnumberButton;

    @FXML
    private TableColumn<MenuDetail, String> menuresourceunitColumn;

    @FXML
    private Button findButton;

    @FXML
    private TextField resourcequantityTField;

    @FXML
    private TableColumn<MenuDetail, Number> menuresourcequantityColumn;

    @FXML
    private TableView<MenuDetail> menuTView;

    @FXML
    private TableColumn<Resource, Number> resourceunitpriceColumn;

    @FXML
    private Button confirmButton;

    @FXML
    private TableColumn<Resource, Number> resourcequantityleftColumn;

    @FXML
    private TableView<Resource> resourceTView;

    @FXML
    private TableColumn<Resource, String> resourcenameColumn;

    @FXML
    private TextField estimatedcostTField;

    @FXML
    private Button addresourceButton;

    @FXML
    private Button delorderButton;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Resource, String> resourceunitColumn;

    @FXML
    private ComboBox<String> menutypeCBox;

    @FXML
    private TableColumn<MenuDetail, Number> menuresourcecostColumn;

    @FXML
    private TextField resourceidTField;

    @FXML
    private TextField costTField;

    @FXML
    private TextField offercostTField;

    @FXML
    private TableColumn<MenuDetail, String> menuresourcenameColumn;

    static ObservableList<Resource> resourceList;
    
    static ArrayList<MenuDetail> menuDetailResourceArrList = new ArrayList<MenuDetail>();
        
    static ObservableList<MenuDetail> menuDetailResourceList;
    
    public void updateResourceTView(String string) throws SQLException {


    	
    	ArrayList<Resource> arr = ResourceData.searchResourceData(string);

    	resourceTView.getItems().clear();

    	resourceList = FXCollections.observableArrayList(arr);
    	resourceTView.setItems(resourceList);
    	
    }
    
  	public void updateMenuTView() throws SQLException {
  		if (menutypeCBox.getItems().isEmpty()) {
  	  		menutypeCBox.setDisable(false); 			
  			ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
  	    	for (int i=0;i<arr.size();i++) {
  	    		String tmp=arr.get(i).getMenuTypeName();
  	    		menutypeCBox.getItems().add(tmp);    	
  	    	}
  		}
  		
  		
  		menuTView.getItems().clear();
  		menuDetailResourceList = FXCollections.observableArrayList(menuDetailResourceArrList);
  		menuTView.setItems(menuDetailResourceList);
  		
  		float total = 0;
  		for (int i=0; i <menuDetailResourceList.size();i++) {
  			total+=menuDetailResourceList.get(i).getMenuDetailResourcePrice();
  		}
  		
  		estimatedcostTField.setText(String.valueOf(total));
  		offercostTField.setText(String.valueOf(total*1.3f));
  	}
  	
  	@FXML
    void initialize() throws SQLException {
    	
  		menuDetailResourceArrList.removeAll(menuDetailResourceArrList);
  		menutypeCBox.setDisable(true);
  		
    	
    	resourcenameColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("resourceName"));
    	resourceunitColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("resourceUnit"));
    	resourceunitpriceColumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceUnitPrice"));
    	resourcequantityleftColumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceQuantityLeft"));
    	
    	menuresourcenameColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, String>("menuDetailResourceName"));
    	menuresourcequantityColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, Number>("menuDetailResourceQuantity"));
    	menuresourceunitColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, String>("menuDetailResourceUnit"));
    	menuresourcecostColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, Number>("menuDetailResourcePrice"));
    	
    	menuidTField.setText(String.valueOf(MenuData.getNextMenuID()));
    	

    	updateResourceTView(null);
    	
    }
    
    @FXML
    void addResource(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= resourceTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one resource to add");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	if(resourcequantityTField.getText().equals("") || resourcequantityTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	if(Integer.parseInt(resourcequantityTField.getText())<=0) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Number value must be greater than 0");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	Resource resource = resourceList.get(i);
    	
    	
    	for (int i1=0;i1<menuDetailResourceArrList.size();i1++) 
    		if (resource.getResourceID()==menuDetailResourceArrList.get(i1).getMenuDetailResourceID()) {
    			menuDetailResourceArrList.get(i1).setMenuDetailResourceQuantity(menuDetailResourceArrList.get(i1).getMenuDetailResourceQuantity()+Integer.parseInt(resourcequantityTField.getText()));
    			resourcequantityTField.setText("");
    	    	menuDetailResourceArrList.get(i1).setMenuDetailResourcePrice(menuDetailResourceArrList.get(i1).getMenuDetailResourceQuantity()*menuDetailResourceArrList.get(i1).getMenuDetailResourceUnitPrice());
    	    	updateMenuTView();
    	    	return;
    		}
    	MenuDetail menuDetail = new MenuDetail();
    	menuDetail.setMenuDetailResourceID(resource.getResourceID());
    	menuDetail.setMenuDetailResourceName(resource.getResourceName());
    	menuDetail.setMenuDetailResourceQuantity(Integer.parseInt(resourcequantityTField.getText()));
	    resourcequantityTField.setText("");
	    menuDetail.setMenuDetailResourceUnit(resource.getResourceUnit());
	    menuDetail.setMenuDetailResourceUnitPrice(resource.getResourceUnitPrice());
	    menuDetail.setMenuDetailResourcePrice(menuDetail.getMenuDetailResourceQuantity()*menuDetail.getMenuDetailResourceUnitPrice());
	   	menuDetailResourceArrList.add(menuDetail);
    	
	    updateMenuTView();

    }

    @FXML
    void findResource(ActionEvent event) throws SQLException {

    	updateResourceTView(resourceidTField.getText());
    	
    }


    @FXML
    void setCostTFValue(ActionEvent event) {
    	
    	if (!offercostTField.getText().isEmpty()) {
    		costTField.setText(offercostTField.getText());
    	}

    }

    @FXML
    void delResource(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= menuTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Delete Menu Detail Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one menu resource to delete");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	menuDetailResourceArrList.remove(i);
    	updateMenuTView();

    }

    @FXML
    void confirmMenu(ActionEvent event) throws SQLException {
    	
    	if(costTField.getText().equals("") || costTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}  
    	
    	if(menutypeCBox.getSelectionModel().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}   
    	
    	if(menunameTField.getText().equals("") || menunameTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}   
    	
    	
    	Menu menu = new Menu();
    	
    	menu.setMenuName(menunameTField.getText());
    	MenuType menuType = new MenuType();
    	menuType.setMenuTypeName(menutypeCBox.getSelectionModel().getSelectedItem());
    	menu.setMenuType(menuType);
    	menu.setMenuCost(Float.parseFloat(costTField.getText()));
    	menu.setMenuEstimatedCost(Float.parseFloat(estimatedcostTField.getText()));
    	menu.setMenuDetailList(menuDetailResourceArrList);
    	
    	MenuData.addMenuData(menu);
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

}
