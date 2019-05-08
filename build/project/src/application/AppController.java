package application;

import java.net.URL;
import java.util.ResourceBundle;
 
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AppController {
	
	@FXML
	private Button buttonLogin, buttonExit;
	
	@FXML
	private TextField tfieldUsername;
	
	@FXML
	private PasswordField tfieldPassword;
	
	@FXML
	private Label  labelUsername, labelPassword;

	
	public void initialize(URL location, ResourceBundle resources) {	   }
	
	public void wrongPassword() {
		buttonLogin.setDisable(true);
		buttonExit.setDisable(true);
		Alert alert = new Alert(AlertType.WARNING);
	    alert.setTitle("Login Failed");
		alert.setHeaderText(null);
	    alert.setContentText("Incorrect username/password.");
	    alert.showAndWait();	
	    buttonLogin.setDisable(false);
	    buttonExit.setDisable(false);	   
	}
	
	public void correctPassword() {
		buttonLogin.setDisable(true);
		buttonExit.setDisable(true);
		Alert alert = new Alert(AlertType.INFORMATION);
	    alert.setTitle("Login Success");
		alert.setHeaderText(null);
	    alert.setContentText("Connect successfully!");
	    alert.showAndWait();	
	    buttonLogin.setDisable(false);
	    buttonExit.setDisable(false);	   
	}

	public void closeWindow(ActionEvent event) {
	    Stage stage = (Stage) buttonExit.getScene().getWindow();
	    stage.close();
	}
	
	public void loginSystem(ActionEvent event) {
		if (tfieldUsername.getText().equals("root") && tfieldPassword.getText().equals("admin")) {
			//correctPassword();
			Stage stage = (Stage) buttonExit.getScene().getWindow();
		    stage.close();	
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/application/MainWindow.fxml"));
				primaryStage.setTitle("Main");
			    primaryStage.setScene(new Scene(root));
			    primaryStage.show();
			    primaryStage.setResizable(false);		        
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		else {
		//	wrongPassword();
		}
	}
}
