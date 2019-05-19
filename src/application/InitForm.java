package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class InitForm extends Application {
	public static Connection connection;
	
	public void initDatabase() throws ClassNotFoundException, SQLException {
		connection = Init.getOracleConnection();
	}
	
	public void start(Stage primaryStage) throws IOException {
		try {
	
  
			Parent root = FXMLLoader.load(getClass().getResource("/application/InitForm.fxml"));
	
			primaryStage.setTitle("Login");
//			primaryStage.initStyle(StageStyle.TRANSPARENT);
			
			Scene s = new Scene(root);
		
	        s.setFill(null);
		
			primaryStage.setScene(s);
			
			primaryStage.show();
			primaryStage.setResizable(false);
			
			initDatabase();
			
			PauseTransition delay = new PauseTransition(Duration.seconds(3));
			delay.setOnFinished( event -> {
				primaryStage.close();
				LoginWindow loginWindow = new LoginWindow();
				loginWindow.start(primaryStage);
				}
			);
			delay.play();			
			
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
