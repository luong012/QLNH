package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class AddEmployeeController {

    @FXML
    private TextField empphoneTField;

    @FXML
    private RadioButton chefRadio;

    @FXML
    private TextField empnameTField;

    @FXML
    private RadioButton waiterRadio;

    @FXML
    private RadioButton manRadio;

    @FXML
    private TextField empidTField;

    @FXML
    private RadioButton cashierRadio;

    @FXML
    private DatePicker empbirthDPicker;

    @FXML
    private TextField empaddressTField;

    @FXML
    private ToggleGroup position;

    @FXML
    private Button confirmButton;

    @FXML
    void closeWindow(ActionEvent event) {

    }

}
