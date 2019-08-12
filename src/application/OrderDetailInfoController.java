package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class OrderDetailInfoController {

    @FXML
    private TableColumn<OrderDetail, Number> ordercostColumn;

    @FXML
    private DatePicker orderinfodateDPicker;

    @FXML
    private Button findButton;

    @FXML
    private TableView<OrderDetail> orderinfoTView;

    @FXML
    private TableColumn<OrderResource, Number> orderresourcequantityColumn;

    @FXML
    private TableColumn<OrderDetail, Number> ordernumberresourceColumn;

    @FXML
    private TextField orderidTField;

    @FXML
    private TableColumn<OrderDetail, LocalDate> orderdateColumn;

    @FXML
    private TableView<OrderResource> orderdetailTView;

    @FXML
    private TextField estimatedcostTField;

    @FXML
    private Button viewdetailButton;

    @FXML
    private TextField orderinfoidTField;

    @FXML
    private TableColumn<OrderResource, String> orderresourcenameColumn;

    @FXML
    private Button closeButton;

    @FXML
    private TextField costTField;
    
    @FXML
    private Button addorderButton;

    @FXML
    private TableColumn<OrderDetail, Number> orderidColumn;

    @FXML
    private DatePicker orderdateDPicker;

    @FXML
    private TableColumn<OrderResource, Number> orderresourcecostColumn;
        
    static ObservableList<OrderDetail> orderDetailList;
            
    static ObservableList<OrderResource> orderResourceList;

    public void updateOrderinfoTView(int x , LocalDate date) throws SQLException {


    	
    	ArrayList<OrderDetail> arr = OrderDetailData.searchOrderDetailData(x, date);

    	orderinfoTView.getItems().clear();

    	orderDetailList = FXCollections.observableArrayList(arr);
    	orderinfoTView.setItems(orderDetailList);
    }
    
  	public void updateOrderdetailTView(OrderDetail orderDetail) throws SQLException {
  		
  		orderdetailTView.getItems().clear();
  		
  		orderidTField.setText(String.valueOf(orderDetail.getOrderDetailID()));
    	
    	orderdateDPicker.setValue(orderDetail.getOrderDetailDate());

  		
  		ArrayList<OrderResource> arr = OrderResourceData.getOrderResource(orderDetail.getOrderDetailID());
  		orderResourceList = FXCollections.observableArrayList(arr);
  		orderdetailTView.setItems(orderResourceList);
  				
  		estimatedcostTField.setText(String.valueOf(orderDetail.getOrderDetailEstimatedCost()));
  		costTField.setText(String.valueOf(orderDetail.getOrderDetailCost()));
  	}
    
    @FXML
    void initialize() throws SQLException {

    	
    	orderidColumn.setCellValueFactory(new PropertyValueFactory<OrderDetail, Number>("orderDetailID"));
    	orderdateColumn.setCellValueFactory(new PropertyValueFactory<OrderDetail, LocalDate>("orderDetailDate"));
    	ordercostColumn.setCellValueFactory(new PropertyValueFactory<OrderDetail, Number>("orderDetailCost"));
    	ordernumberresourceColumn.setCellValueFactory(new PropertyValueFactory<OrderDetail, Number>("orderNumberOfResource"));
    	
    	orderresourcenameColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, String>("orderResourceName"));
    	orderresourcequantityColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, Number>("orderResourceQuantity"));
    	orderresourcecostColumn.setCellValueFactory(new PropertyValueFactory<OrderResource, Number>("orderResourcePrice"));
    	
    	
    	updateOrderinfoTView(-1, null);
    	
    }
    
    @FXML
    void addOrder(ActionEvent event) throws SQLException {
    	
    	AddOrderWindow addOrderWindow = new AddOrderWindow();
		Stage stage = (Stage) closeButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addOrderWindow.start(newStage);   	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    	    	orderinfoTView.getItems().clear();
    			try {
    				updateOrderinfoTView(-1, null);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});    	

    }


    @FXML
    void viewOrderDetail(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= orderinfoTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("View Order Detail Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one order to view");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	OrderDetail orderDetail = orderDetailList.get(i);
    	
    	updateOrderdetailTView(orderDetail);
    	
    	

    }

    @FXML
    void findOrderInfo(ActionEvent event) throws SQLException {
    	if (orderinfoidTField.getText().isEmpty())	updateOrderinfoTView(-1 , orderinfodateDPicker.getValue());
    	else
    		updateOrderinfoTView(Integer.parseInt(orderinfoidTField.getText()) , orderinfodateDPicker.getValue());

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

    @FXML
    void confirmOrder(ActionEvent event) {

    }

}
