package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Parent;


public class ViewTableInfoWindow extends Application {

	 public void start(Stage primaryStage) {
	       try {
	           Parent root = FXMLLoader.load(getClass()
	                   .getResource("/application/ViewTableInfoWindow.fxml"));
	 
	           primaryStage.setTitle("Table Infomation");
	           primaryStage.setScene(new Scene(root));
	           primaryStage.show();
	           primaryStage.setResizable(false);
	        
	       } catch(Exception e) {
	           e.printStackTrace();
	       }
	   }
	  
	   public static void main(String[] args) {
	       launch(args);
	   }
}
