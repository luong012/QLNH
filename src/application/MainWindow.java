package application;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class MainWindow extends Application {

	 public void start(Stage primaryStage) {
	       try {
	           Parent root = FXMLLoader.load(getClass()
	                   .getResource("/application/MainWindow.fxml"));
	           String title = "Main Window "+Global.activeUsername+"/"+Global.activeRoleName;
	           primaryStage.setTitle(title);
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
