package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddOrderController {

    @FXML
    private TextField quantityTField;

    @FXML
    private Button addorderButton;

    @FXML
    private TableView<OrderResource> orderTView;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<OrderResource, Number> orderresourcequantityColumn;

    @FXML
    private TableColumn<Resource, Number> resourceunitpriceColumn;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField orderidTField;

    @FXML
    private TableColumn<Resource, Number> resourcequantityleftColumn;

    @FXML
    private TableView<Resource> resourceTView;

    @FXML
    private TableColumn<Resource, String> resourcenameColumn;

    @FXML
    private TextField estimatedcostTField;

    @FXML
    private TextField resourceTField;

    @FXML
    private Button delorderButton;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Resource, String> resourceunitColumn;

    @FXML
    private TableColumn<OrderResource, String> orderresourcenameColumn;
    
    @FXML
    private Button autocostnumberButton;

    @FXML
    private TextField costTField;

    @FXML
    private DatePicker orderdateDPicker;

    @FXML
    private TableColumn<OrderResource, Number> orderresourcecostColumn;
    
    @FXML
    private TableColumn<OrderResource, String> orderresourceunitColumn;
    
    static ObservableList<Resource> resourceList;
    
    static ArrayList<OrderResource> orderResourceArrList = new ArrayList<OrderResource>();
        
    static ObservableList<OrderResource> orderResourceList;

    public void updateResourceTView(String string) throws SQLException {


    	
    	ArrayList<Resource> arr = ResourceData.searchResourceData(string);

    	resourceTView.getItems().clear();

    	resourceList = FXCollections.observableArrayList(arr);
    	resourceTView.setItems(resourceList);
    	
    }
    
  	public void updateOrderTView() {
  		
  		orderTView.getItems().clear();
  		orderResourceList = FXCollections.observableArrayList(orderResourceArrList);
  		orderTView.setItems(orderResourceList);
  		
  		float total = 0;
  		for (int i=0; i <orderResourceList.size();i++) {
  			total+=orderResourceList.get(i).getOrderResourcePrice();
  		}
  		
  		estimatedcostTField.setText(String.valueOf(total));
  	}
    
    @FXML
    void initialize() throws SQLException {
    	
    	orderResourceArrList.removeAll(orderResourceArrList);
    	
    	resourcenameColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("resourceName"));
    	resourceunitColumn.setCellValueFactory(new PropertyValueFactory<Resource, String>("resourceUnit"));
    	resourceunitpriceColumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceUnitPrice"));
    	resourcequantityleftColumn.setCellValueFactory(new PropertyValueFactory<Resource, Number>("resourceQuantityLeft"));
    	
    	orderresourcenameColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, String>("orderResourceName"));
    	orderresourcequantityColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, Number>("orderResourceQuantity"));
    	orderresourceunitColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, String>("orderResourceUnit"));
    	orderresourcecostColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, Number>("orderResourcePrice"));
    	
    	orderidTField.setText(String.valueOf(OrderDetailData.getNextOrderDetailID()));
    	
    	orderdateDPicker.setValue(LocalDate.now());

    	updateResourceTView(null);
    	
    }

    @FXML
    void addResource(ActionEvent event) {
    	
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
    	
    	if(quantityTField.getText().equals("") || quantityTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	Resource resource = resourceList.get(i);
    	
    	
    	for (int i1=0;i1<orderResourceArrList.size();i1++) 
    		if (resource.getResourceID()==orderResourceArrList.get(i1).getOrderResourceID()) {
    			orderResourceArrList.get(i1).setOrderResourceQuantity(orderResourceArrList.get(i1).getOrderResourceQuantity()+Integer.parseInt(quantityTField.getText()));
    	    	quantityTField.setText("");
    	    	orderResourceArrList.get(i1).setOrderResourcePrice(orderResourceArrList.get(i1).getOrderResourceQuantity()*orderResourceArrList.get(i1).getOrderResourceUnitPrice());
    	    	updateOrderTView();
    	    	return;
    		}
	    OrderResource orderResource = new OrderResource();
	    orderResource.setOrderResourceID(resource.getResourceID());
	    orderResource.setOrderResourceName(resource.getResourceName());
	    orderResource.setOrderResourceQuantity(Integer.parseInt(quantityTField.getText()));
	    quantityTField.setText("");
	    orderResource.setOrderResourceUnit(resource.getResourceUnit());
	    orderResource.setOrderResourceUnitPrice(resource.getResourceUnitPrice());
	   	orderResource.setOrderResourcePrice(orderResource.getOrderResourceQuantity()*orderResource.getOrderResourceUnitPrice());
	    orderResourceArrList.add(orderResource);
    	
    	updateOrderTView();
    	
    }

    	
    @FXML
    void findResource(ActionEvent event) throws SQLException {
    	
    	updateResourceTView(resourceTField.getText());

    }

    @FXML
    void delOrder(ActionEvent event) {
    	
    	int i=-1;
    	i= orderTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Delete Order Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one order resource to delete");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	orderResourceArrList.remove(i);
    	updateOrderTView();

    }
    
    @FXML
    void setCostTFValue(ActionEvent event) {
    	
    	if (!estimatedcostTField.getText().isEmpty()) {
    		costTField.setText(estimatedcostTField.getText());
    	}

    }


    @FXML
    void closeWindow(ActionEvent event) {
    	
    	
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void confirmOrder(ActionEvent event) throws SQLException {
    	
    	if(costTField.getText().equals("") || costTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Resource Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}    	
    	
    	OrderDetail orderDetail = new OrderDetail();
    	
    	orderDetail.setOrderDetailDate(orderdateDPicker.getValue());
    	orderDetail.setOrderDetailCost(Float.parseFloat(costTField.getText()));
    	orderDetail.setOrderResourceList(orderResourceArrList);
    	
    	OrderDetailData.addOrderDetailData(orderDetail);
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();


    }

}
