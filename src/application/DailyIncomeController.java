package application;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class DailyIncomeController {

	@FXML
    private Label totalLabel;

    @FXML
    private TableColumn<Bill, LocalDateTime> endColumn;

    @FXML
    private TableColumn<Bill, String> empColumn;

    @FXML
    private TableColumn<Bill, Number> costColumn;

    @FXML
    private Button closeButton;

    @FXML
    private TableColumn<Bill, LocalDateTime> startColumn;

    @FXML
    private TableColumn<Bill, String> cusColumn;

    @FXML
    private TableColumn<Bill, Number> tableColumn;

    @FXML
    private TableView<Bill> incomeTView;

    @FXML
    private TableColumn<Bill, Number> idColumn;

    
    @FXML
    void initialize() throws SQLException {
    	Income income;
    	ArrayList<Bill> billArr;
    	income = BillData.getDailyIncome();
    	billArr = income.getIncomeBillArr();
    	float totalCost = income.getIncomeTotal();
    	
    	idColumn.setCellValueFactory(new PropertyValueFactory<Bill, Number>("billID"));
    	tableColumn.setCellValueFactory(new PropertyValueFactory<Bill, Number>("billTableID"));
    	cusColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("billCustomerID"));
    	empColumn.setCellValueFactory(new PropertyValueFactory<Bill, String>("billEmployeeID"));
    	startColumn.setCellValueFactory(new PropertyValueFactory<Bill, LocalDateTime>("billCreationTime"));
    	endColumn.setCellValueFactory(new PropertyValueFactory<Bill, LocalDateTime>("billClosingTime"));
    	costColumn.setCellValueFactory(new PropertyValueFactory<Bill, Number>("billCost"));
    	
    	ObservableList<Bill> billList = FXCollections.observableArrayList(billArr);
    	incomeTView.setItems(billList);
    	
    	totalLabel.setText(String.valueOf(totalCost));
    	
    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

}
