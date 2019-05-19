package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TableTypeController {

    @FXML
    private ComboBox<?> modtableidCBox;

    @FXML
    private TextField deltabletypenameTField;

    @FXML
    private TextField addtabletypemaxcusTField;

    @FXML
    private TextField addtabletypeidTField;

    @FXML
    private Button modcancelButton;

    @FXML
    private TextField modtabletypemaxcusTField;

    @FXML
    private Button addconfirmButton;

    @FXML
    private Button delcancelButton;

    @FXML
    private ComboBox<?> deltabletypenameCBox;

    @FXML
    private Button modconfirmButton;

    @FXML
    private TextField deltabletypemaxcusTField;

    @FXML
    private TextField addtabletypenameTFileld;

    @FXML
    private TextField modtabletypenameTField;

    @FXML
    private Button delconfirmButton;

    @FXML
    private Button addcancelButton;
    
    public void closeWindow() {
    	Stage stage = (Stage) addconfirmButton.getScene().getWindow();
		stage.close();
    }
    
    public void confirmAdd(ActionEvent event) {
    	closeWindow();
    }
    
    public void cancelAdd(ActionEvent event) {
    	closeWindow();
    }
    public void confirmMod(ActionEvent event) {
    	closeWindow();
    }
    
    public void cancelMod(ActionEvent event) {
    	closeWindow();
    }
    public void confirmDel(ActionEvent event) {
    	closeWindow();
    }
    
    public void cancelDel(ActionEvent event) {
    	closeWindow();
    }


}
