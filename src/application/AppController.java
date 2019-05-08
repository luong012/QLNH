package application;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AppController {
	
	@FXML
    private Button exitButton;

    @FXML
    private TextField usernameTField;

    @FXML
    private Button loginButton;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView loginImage;

    @FXML
    private Label passwordLabel;

    @FXML
    private PasswordField passwordTField;
    	
	public void initialize(URL location, ResourceBundle resources) {	   }
	
	public void wrongPassword() {
		loginButton.setDisable(true);
		exitButton.setDisable(true);
		Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle("Login Failed");
		alert.setHeaderText(null);
	    alert.setContentText("Incorrect username/password.");
	    alert.showAndWait();	
	    loginButton.setDisable(false);
	    exitButton.setDisable(false);	   
	}
	
	public void correctPassword() {
		loginButton.setDisable(true);
		exitButton.setDisable(true);
		Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Login Success");
		alert.setHeaderText(null);
	    alert.setContentText("Connect successfully!");
	    alert.showAndWait();	
	    loginButton.setDisable(false);
	    exitButton.setDisable(false);	    
	}

	public void closeWindow(ActionEvent event) {
	    Stage stage = (Stage) exitButton.getScene().getWindow();
	    stage.close();
	}
	
	public void loginSystem(ActionEvent event) {
		if (usernameTField.getText().equals("root") && passwordTField.getText().equals("admin")) {
			correctPassword();
			Stage stage = (Stage) exitButton.getScene().getWindow();
		    stage.close();	
			MainWindow mainWindow = new MainWindow();
			mainWindow.start(stage);
		}
		else {
		wrongPassword();
		}
	}
	
}
