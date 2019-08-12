package application;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MenuInfoController {

	@FXML
    private ComboBox<String> menudetailtypeCBox;

    @FXML
    private Button findButton;

    @FXML
    private TableColumn<MenuDetail, Number> menudetailresourcecostColumn;

    @FXML
    private TextField menuavailablenumberTField;

    @FXML
    private TableColumn<Menu, Number> menucostColumn;

    @FXML
    private TextField menudetailidTField;

    @FXML
    private TableColumn<Menu, String> menuidColumn;

    @FXML
    private TextField estimatedcostTField;

    @FXML
    private TextField menudetailnameTField;

    @FXML
    private Button viewdetailButton;

    @FXML
    private TableColumn<MenuDetail, String> menudetailresourcenameColumn;

    @FXML
    private TableColumn<Menu, String> menutypenameColumn;

    @FXML
    private Button closeButton;

    @FXML
    private TextField menuinfoidTField;

    @FXML
    private TableColumn<MenuDetail, Number> menudetailresourcequantityColumn;

    @FXML
    private TextField menucostTField;

    @FXML
    private TableColumn<MenuDetail, String> menudetailresourceunitColumn;

    @FXML
    private TableView<MenuDetail> menudetailTView;

    @FXML
    private TableView<Menu> menuinfoTView;

    @FXML
    private ComboBox<String> menutypeCBox;

    @FXML
    private TableColumn<Menu, Number> menuavailablenumberColumn;
    
    @FXML
    private Button addmenuButton;
    
    @FXML
    private TextField costTField;
    
    @FXML
    private Button manmenuButton;

    @FXML
    private TextField offercostTField;
    
    static ObservableList<Menu> menuList;
    
    static ObservableList<MenuDetail> menuDetailList;
    
    public void updateMenuInfoTView(String a, String b, float c, int d) throws SQLException {


    	
    	ArrayList<Menu> arr = MenuData.searchMenuData(a,b,c,d);

    	menuinfoTView.getItems().clear();

    	menuList = FXCollections.observableArrayList(arr);
    	menuinfoTView.setItems(menuList);
    }
    
  	public void updateMenuDetailTView(Menu menu) throws SQLException {
  		
  		menudetailTView.getItems().clear();
  		
  		menudetailidTField.setText(String.valueOf(menu.getMenuID()));
  		menudetailnameTField.setText(menu.getMenuName());
  		menudetailtypeCBox.getSelectionModel().select(menu.getMenuType().getMenuTypeID()-1);
  		

  		
  		ArrayList<MenuDetail> arr = MenuDetailData.getMenuDetailData(menu.getMenuID());
  		menuDetailList = FXCollections.observableArrayList(arr);
  		menudetailTView.setItems(menuDetailList);
  				
  		estimatedcostTField.setText(String.valueOf(menu.getMenuEstimatedCost()));
  		offercostTField.setText(String.valueOf(menu.getMenuOfferCost()));
  		costTField.setText(String.valueOf(menu.getMenuCost()));
  	}
  	
  	 void updateMenuTypeCBox() throws SQLException {
  		 menutypeCBox.getItems().clear();
  		 ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
  		 menutypeCBox.getItems().add("All");
  		 menutypeCBox.getSelectionModel().select(0);
  		 for (int i=0;i<arr.size();i++) {
  			 String tmp=arr.get(i).getMenuTypeName();
  			 menutypeCBox.getItems().add(tmp);    	
  			 menudetailtypeCBox.getItems().add(tmp);  
  		 }
  	 }

    
    @FXML
    void initialize() throws SQLException {

    	ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
    	menutypeCBox.getItems().add("All");
    	menutypeCBox.getSelectionModel().select(0);
    	for (int i=0;i<arr.size();i++) {
    		String tmp=arr.get(i).getMenuTypeName();
    		menutypeCBox.getItems().add(tmp);    	
    		menudetailtypeCBox.getItems().add(tmp);  
    	}
    	
    	menuidColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuName"));
    	menutypenameColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("menuTypeName"));
    	menucostColumn.setCellValueFactory(new PropertyValueFactory<Menu, Number>("menuCost"));
    	menuavailablenumberColumn.setCellValueFactory(new PropertyValueFactory<Menu, Number>("menuAvailableNumber"));
    	
    	menudetailresourcenameColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, String>("menuDetailResourceName"));
    	menudetailresourcequantityColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, Number>("menuDetailResourceQuantity"));
    	menudetailresourceunitColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, String>("menuDetailResourceUnit"));
    	menudetailresourcecostColumn.setCellValueFactory(new PropertyValueFactory<MenuDetail, Number>("menuDetailResourcePrice"));

    	
    	updateMenuInfoTView(null, null, -1, -1);
    	
    	if(Global.activeRole>1) {
    		addmenuButton.setVisible(false);
    		manmenuButton.setVisible(false);
    	}
    	
    }
    
    @FXML
    void viewAddMenu(ActionEvent event) {
    	
    	AddMenuWindow addMenuWindow = new AddMenuWindow();
		Stage stage = (Stage) closeButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addMenuWindow.start(newStage);   
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			menuinfoTView.getItems().clear();
    			try {
    				updateMenuInfoTView(null, null, -1, -1);
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
		
    	});

    }
    
    @FXML
    void viewModMenu(ActionEvent event) {

    }
    
    @FXML
    void viewManWindow(ActionEvent event) {
    	
    	MenuTypeWindow menuTypeWindow = new MenuTypeWindow();
		Stage stage = (Stage) closeButton.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	menuTypeWindow.start(newStage);   	
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

    			try {
					updateMenuTypeCBox();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
		
    	});

    	
    }


    @FXML
    void viewMenuInfo(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= menuinfoTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("View Menu Detail Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one menu to view");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	Menu menu = menuList.get(i);
    	
    	updateMenuDetailTView(menu);
    	

    }

    @FXML
    void findMenuInfo(ActionEvent event) throws SQLException {
    	
    	float tmp1=-1;
    	int tmp2=-1;
    	String string = menutypeCBox.getSelectionModel().getSelectedItem();
    	if (string==null) string = null;
    	else if(string.equals("All")) string=null;
    	if (!menucostTField.getText().isEmpty()) tmp1=Float.parseFloat(menucostTField.getText());
    	if (!menuavailablenumberTField.getText().isEmpty()) tmp2=Integer.parseInt(menuavailablenumberTField.getText());
    	updateMenuInfoTView(menuinfoidTField.getText(),string,tmp1,tmp2);

    }

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) closeButton.getScene().getWindow();
    	stage.close();

    }

}
