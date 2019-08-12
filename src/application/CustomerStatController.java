package application;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CustomerStatController {

    @FXML
    private TableColumn<Customer, String> cusaddressColumn;

    @FXML
    private TableColumn<Customer, String> custypeColumn;

    @FXML
    private TableColumn<Customer, Number> cuspointColumn;

    @FXML
    private TableColumn<Customer, LocalDate> cusbirthColumn;

    @FXML
    private TableView<Customer> cusTView;

    @FXML
    private TableColumn<Customer, String> cusnameColumn;

    @FXML
    private TableColumn<Customer, String> cusphoneColumn;

    @FXML
    private PieChart pieChart;

    @FXML
    private TableColumn<Customer, String> cusidColumn;
    
    @FXML
    private Button closeButton;
    
    @FXML
    void initialize() throws SQLException {
    	
    	cusidColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerID"));
    	cusnameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerName"));
    	cusbirthColumn.setCellValueFactory(new PropertyValueFactory<Customer, LocalDate>("customerBirth"));
    	cusaddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerAddress"));
    	cusphoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerPhone"));
    	custypeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("customerTypeName"));
    	cuspointColumn.setCellValueFactory(new PropertyValueFactory<Customer, Number>("customerPoint"));
    	
    	CusStat cusStat = CustomerData.searchGroupCustomerData();
       	ArrayList<Customer> arr = cusStat.getCsArr();
       	ArrayList<Integer> arrI = cusStat.getCsNumber();
    	
    	ObservableList<Customer> customerList = FXCollections.observableArrayList(arr);
    	cusTView.setItems(customerList);
    	
    	ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
    			new PieChart.Data("VIP", arrI.get(0)),
    			new PieChart.Data("Khách hàng thân thiết", arrI.get(1)),
    			new PieChart.Data("Khách hàng mới", arrI.get(2)));
    	pieChart.setData(pieChartData);
    	pieChart.setStartAngle(90);
    }
    
    @FXML
    void closeWindow() {
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();
    }

}
