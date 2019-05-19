package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddTableController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField maxcusnumberTField;

    @FXML
    private Label idnumberLabel;

    @FXML
    private Label birthLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private TextArea tabledescTArea;

    @FXML
    private TextField tableidTField;

    @FXML
    private Button confirmButton;

    @FXML
    private ComboBox<?> tabletypeCBox;

    @FXML
    private Label nameLabel;

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    }

}
