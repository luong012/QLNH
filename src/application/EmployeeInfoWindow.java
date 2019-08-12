package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeInfoWindow extends Application {
	
	 public void start(Stage primaryStage) {
	       try {
	           Parent root = FXMLLoader.load(getClass()
	                   .getResource("/application/EmployeeInfoWindow.fxml"));
	           primaryStage.setTitle("Employee Information");
	           primaryStage.initStyle(StageStyle.TRANSPARENT);
	           primaryStage.setScene(new Scene(root));
	           primaryStage.show();
	        
	       } catch(Exception e) {
	           e.printStackTrace();
	       }
	   }
	  
	   public static void main(String[] args) {
	       launch(args);
	   }

}
