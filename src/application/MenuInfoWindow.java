package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class MenuInfoWindow extends Application {

	
	 public void start(Stage primaryStage) {
	       try {
	           Parent root = FXMLLoader.load(getClass()
	                   .getResource("/application/MenuInfoWindow.fxml"));
	           primaryStage.setTitle("Menu Information");
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
