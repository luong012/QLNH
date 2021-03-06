package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.application.Platform;
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
	
	public void illegalText() {
		loginButton.setDisable(true);
		exitButton.setDisable(true);
		Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle("Login Failed");
		alert.setHeaderText(null);
	    alert.setContentText("Username/password username must be from 1 to 15 characters.");
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
	    Platform.exit();
	}
	
	public void loginSystem(ActionEvent event) throws ClassNotFoundException, SQLException{
		String username = usernameTField.getText();
		String password = passwordTField.getText();
		int roleResult = AppData.getRole(username, password);
		if (roleResult>0) {
			Global.activeEmp=EmployeeData.searchEmployeeData(AppData.getEmpID(username, password), null, null, -1).get(0);
			Global.activeUsername = username;
			Global.activeRole = roleResult;
			Global.setRoleName(roleResult);
			correctPassword();
			
			Stage stage = (Stage) exitButton.getScene().getWindow();
		    stage.close();	
			MainWindow mainWindow = new MainWindow();
			mainWindow.start(stage);
		}
		else 
			if (username.length()>15 || username.length()<1 || password.length()>15 || password.length()<1 ) illegalText();
		else {
		wrongPassword();
		}
	}
	
}
