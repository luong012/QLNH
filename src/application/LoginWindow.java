package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginWindow extends Application {	
	public void start(Stage primaryStage) {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/application/LoginWindow.fxml"));

			primaryStage.setTitle("Login");
			primaryStage.setScene(new Scene(root));
			primaryStage.show();
			primaryStage.setResizable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
