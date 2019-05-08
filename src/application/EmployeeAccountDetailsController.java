package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EmployeeAccountDetailsController {

    @FXML
    private Label resposLabel;

    @FXML
    private Separator secondSeparator;

    @FXML
    private Separator firstSeparator;

    @FXML
    private RadioButton manRadio;

    @FXML
    private Label personalLabel;

    @FXML
    private TextField nameTField;

    @FXML
    private DatePicker birthDatePicker;

    @FXML
    private Separator thirdSeparator;

    @FXML
    private Label birthLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Button confirmButton;

    @FXML
    private RadioButton chefRadio;

    @FXML
    private Label phoneLabel;

    @FXML
    private TextField idnumberTField;

    @FXML
    private RadioButton waiterRadio;

    @FXML
    private Label positionLabel;

    @FXML
    private RadioButton cashierRadio;

    @FXML
    private Label idnumberLabel;

    @FXML
    private ToggleGroup position;

    @FXML
    private TextField locationTField;

    @FXML
    private TextField phoneTField;

    @FXML
    private Label nameLabel;

    @FXML
    void closeWindow(ActionEvent event) {
		Stage stage = (Stage) confirmButton.getScene().getWindow();
		stage.close();
    }

}
