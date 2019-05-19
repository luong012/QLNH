package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ViewTableInfoController {

    @FXML
    private TableView<?> tableTView;

    @FXML
    private Button addtableButton;

    @FXML
    private ComboBox<?> tablestatusCBox;

    @FXML
    private Button exitButton;

    @FXML
    private Button tabletypemanButton;

    @FXML
    private Button delButton;

    @FXML
    private Button findButton;

    @FXML
    private TextField cusnumTField;

    @FXML
    private TextField tableidTField;

    @FXML
    private ComboBox<?> tabletypeCBox;

    @FXML
    private Button modButton;

    @FXML
    void viewAddTableWindow(ActionEvent event) {
    	AddTableWindow addTableWindow = new AddTableWindow();
		Stage stage = (Stage) exitButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addTableWindow.start(newStage);   	

    }

    @FXML
    void viewModTableWindow(ActionEvent event) {

    }

    @FXML
    void viewDelTableWindow(ActionEvent event) {

    }

    @FXML
    void viewTableTypeWindow(ActionEvent event) {

    }

    @FXML
    void findResult(ActionEvent event) {

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	Stage stage = (Stage) exitButton.getScene().getWindow();
    	stage.close();

    }

}
