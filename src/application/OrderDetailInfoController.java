package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class OrderDetailInfoController {

    @FXML
    private TableColumn<?, ?> ordercostColumn;

    @FXML
    private DatePicker orderinfodateDPicker;

    @FXML
    private Button findButton;

    @FXML
    private TableView<?> orderinfoTView;

    @FXML
    private TableColumn<?, ?> orderresourcequantityColumn;

    @FXML
    private TableColumn<?, ?> ordernumberresourceColumn;

    @FXML
    private TextField orderidTField;

    @FXML
    private TableColumn<?, ?> orderdateColumn;

    @FXML
    private TableView<?> orderdetailTView;

    @FXML
    private TextField estimatedcostTField;

    @FXML
    private Button viewdetailButton;

    @FXML
    private TextField orderinfoidTField;

    @FXML
    private TableColumn<?, ?> orderresourceidColumn;

    @FXML
    private Button closeButton;

    @FXML
    private TextField costTField;

    @FXML
    private TableColumn<?, ?> orderidColumn;

    @FXML
    private DatePicker orderdateDPicker;

    @FXML
    private TableColumn<?, ?> orderresourcecostColumn;

    @FXML
    void viewOrderDetail(ActionEvent event) {

    }

    @FXML
    void findOrderInfo(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {

    }

    @FXML
    void confirmOrder(ActionEvent event) {

    }

}
