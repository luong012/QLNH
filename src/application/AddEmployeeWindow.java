package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class AddEmployeeWindow extends Application {

	
	 public void start(Stage primaryStage) {
	       try {
	           Parent root = FXMLLoader.load(getClass()
	                   .getResource("/application/AddEmployeeWindow.fxml"));
	           primaryStage.setTitle("Add Employee");
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
