package application;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class MainController {

	@FXML
    private Menu restaurantMenu;

    @FXML
    private TextField billcusnameTField;

    @FXML
    private Button findButton;

    @FXML
    private MenuItem menuinfoItem;

    @FXML
    private TextField creationtimeTField;

    @FXML
    private TableColumn<Bill, Number> billtableColumn;

    @FXML
    private MenuItem checkinItem;

    @FXML
    private MenuItem menutypemanItem;

    @FXML
    private MenuItem resourceinfoItem;

    @FXML
    private MenuItem deletetableItem;

    @FXML
    private MenuItem resourceoptionItem;

    @FXML
    private MenuItem regcusItem;

    @FXML
    private TextField billtablenameTField;

    @FXML
    private MenuItem empmanItem;

    @FXML
    private Button shortcut5Button;

    @FXML
    private SeparatorMenuItem file1Separator;

    @FXML
    private MenuItem exitItem;

    @FXML
    private MenuItem revenuereportItem;

    @FXML
    private Menu helpMenu;

    @FXML
    private MenuItem searchcusItem;

    @FXML
    private SeparatorMenuItem file1Separator1;

    @FXML
    private Button shortcut3Button;

    @FXML
    private TableColumn<application.Menu, String> menunameColumn;

    @FXML
    private Menu accountMenu;

    @FXML
    private Button addmenuButton;

    @FXML
    private MenuItem monthlyworkingreportItem;

    @FXML
    private TextField billidTField;

    @FXML
    private MenuItem refreshItem;

    @FXML
    private TableColumn<application.Menu, Number> menuunitpriceColumn;

    @FXML
    private MenuItem addtableItem;

    @FXML
    private MenuItem editbillItem;

    @FXML
    private TableView<application.Menu> menuTView;

    @FXML
    private Button confirmButton;

    @FXML
    private MenuItem updatecusItem;

    @FXML
    private Menu financeMenu;

    @FXML
    private MenuItem tableoptionItem;

    @FXML
    private TableView<BillDetail> billdetailTView;

    @FXML
    private Button shortcut2Button;

    @FXML
    private MenuItem newbillItem;

    @FXML
    private TableColumn<application.Menu, String> menutypenameColumn;

    @FXML
    private TextField billcustypenameTField;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuBar mainMenuBar;

    @FXML
    private TextField menunameTField;

    @FXML
    private TextField billtableTField;

    @FXML
    private MenuItem aboutItem;

    @FXML
    private MenuItem tableinfoItem;

    @FXML
    private MenuItem addresourceItem;

    @FXML
    private MenuItem orderinfoItem;

    @FXML
    private MenuItem invenreportItem;

    @FXML
    private MenuItem chargeItem;

    @FXML
    private MenuItem modifyresourceItem;

    @FXML
    private MenuItem accdetailsItem;

    @FXML
    private MenuItem modifytableItem;

    @FXML
    private Menu resourcemanMenu;

    @FXML
    private ComboBox<String> menutypenameCBox;

    @FXML
    private TableView<Bill> billtableTView;

    @FXML
    private TableColumn<application.Menu, Number> menuavailablequantityColumn;
    
    @FXML
    private TableColumn<BillDetail, Number> billdetailmenucostColumn;

    @FXML
    private TableColumn<BillDetail, Number> billdetailmenuquantityColumn;
    
    @FXML
    private TableColumn<BillDetail, String> billdetailmenunameColumn;


    @FXML
    private Menu tablemanMenu;

    @FXML
    private MenuItem outcomereportItem;

    @FXML
    private MenuItem incomereportItem;

    @FXML
    private Button shortcut1Button;

    @FXML
    private Button shortcut4Button;

    @FXML
    private TextField menuquantityTField;

    @FXML
    private MenuItem addorderItem;
    
    @FXML
    private TextField billtotalcostTField;
    
    @FXML
    private TextField discountTField;
    
    @FXML
    private Button shortcut6Button;
    
    @FXML
    private Button shortcut7Button;
    
    @FXML
    private Button shortcut8Button;
    
    @FXML
    private Button shortcut9Button;
    
    @FXML
    private Button shortcut10Button;
    
    @FXML
    private GridPane mainGPane;
    
    @FXML
    private GridPane secondGPane;
    
    @FXML
    private Label empnameLabel;
    
    @FXML
    private ImageView roleImg;
    
    @FXML
    private Label timeLabel;
    
    @FXML
    private Menu customerMenu;
    
    @FXML
    private Button changeButton;
    
    static ObservableList<Bill> billList;
    
    static ObservableList<BillDetail> billDetailList;
    
    static ObservableList<application.Menu> menuList;
    
    static ArrayList<BillDetail> billDetailMenuArrList = new ArrayList<BillDetail>();
    
    private Bill selectedBill;

    
    
    public void updateBillTableTView() throws SQLException {
    	
    	ArrayList<Bill> arr = BillData.searchBillData(-1, "0", null, null);
    	
    	billtableTView.getItems().clear();
    	billList = FXCollections.observableArrayList(arr);
    	billtableTView.setItems(billList);
    	
    }
    
    public void updateMenuTView(String a, String b, float c, int d) throws SQLException {    	
    	ArrayList<application.Menu> arr = MenuData.searchMenuData(a,b,c,d);

    	menuTView.getItems().clear();

    	menuList = FXCollections.observableArrayList(arr);
    	menuTView.setItems(menuList);
    }
    
    public void updateBillDetailTView(Bill bill) throws SQLException {    	
    	
    	billdetailTView.getItems().clear();
    	
    	billDetailList = FXCollections.observableArrayList(billDetailMenuArrList);
    	billdetailTView.setItems(billDetailList);
    	
    	float total = 0;
  		for (int i=0; i <billDetailList.size();i++) {
  			total+=billDetailList.get(i).getBillDetailMenuCost();
  		}
  		
  		billtotalcostTField.setText(String.valueOf(total));

    }
    

    @FXML
    void initialize() throws SQLException {
    	
    	initClock();
    	
    	selectedBill = new Bill();
    	
    	billDetailMenuArrList.removeAll(billDetailMenuArrList);
    	ArrayList<MenuType> arr = MenuTypeData.getMenuTypeData();
		menutypenameCBox.getItems().add("All");    	
    	for (int i=0;i<arr.size();i++) {
    		String tmp=arr.get(i).getMenuTypeName();
    		menutypenameCBox.getItems().add(tmp);    	
    	}
    	menutypenameCBox.getSelectionModel().select(0);
    	
    	billtableColumn.setCellValueFactory(new PropertyValueFactory<Bill, Number>("billTableID"));
    	
    	menunameColumn.setCellValueFactory(new PropertyValueFactory<application.Menu, String>("menuName"));
    	menutypenameColumn.setCellValueFactory(new PropertyValueFactory<application.Menu, String>("menuTypeName"));
    	menuunitpriceColumn.setCellValueFactory(new PropertyValueFactory<application.Menu, Number>("menuCost"));
    	menuavailablequantityColumn.setCellValueFactory(new PropertyValueFactory<application.Menu, Number>("menuAvailableNumber"));
    	

    	billdetailmenunameColumn.setCellValueFactory(new PropertyValueFactory<BillDetail, String>("billDetailMenuName"));
    	billdetailmenuquantityColumn.setCellValueFactory(new PropertyValueFactory<BillDetail, Number>("billDetailMenuQuantity"));
    	billdetailmenucostColumn.setCellValueFactory(new PropertyValueFactory<BillDetail, Number>("billDetailMenuCost"));
    	
    	updateBillTableTView();
    	discountTField.setText("-0%");
    	
    	
    	billtableTView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	    if (newSelection != null) {
    	    	menuTView.getItems().clear();
    	    	billdetailTView.getItems().clear();
    	    	}

        	try {
				updateMenuTView(null, null, -1, -1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
        	try {
        		selectedBill=newSelection;
        		LocalDateTime creationTime = newSelection.getBillCreationTime();
            	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            	String string = creationTime.format(formatter);
            	
            	billDetailMenuArrList = BillDetailData.getBillDetailData(newSelection.getBillID());
            	billidTField.setText(String.valueOf(newSelection.getBillID()));
            	creationtimeTField.setText(string);
            	billtableTField.setText(String.valueOf(newSelection.getBillTableID()));
            	billtablenameTField.setText(newSelection.getBillTable().getTableTypeName());
            	billcusnameTField.setText(newSelection.getBillCustomer().getCustomerName());
            	billcustypenameTField.setText(newSelection.getBillCustomer().getCustomerTypeName());
            	billtotalcostTField.setText(String.valueOf(newSelection.getBillCost()));
            	String custype = newSelection.getBillCustomer().getCustomerTypeName();
            	if (custype.equals("Khách hàng thân thiết")) discountTField.setText("-5%");
            	else if (custype.equals("VIP")) discountTField.setText("-10%");
            	else discountTField.setText("-0%");
				updateBillDetailTView(newSelection);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
    	    
    	});
    	
    	grantAccess();
    	
    }
    
    private void initClock() {

        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            timeLabel.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    
    private void grantAccess() {
    	
    	System.out.println(Global.activeEmp.getEmpID());
    	
    	empnameLabel.setText(Global.activeEmp.getEmpName());
    	String role;
    	if (Global.activeRole==1) role="manager";
    	else if (Global.activeRole==2) role="cashier";
    	else if (Global.activeRole==3) role="waiter";
    	else role="chef";
    	
		Image newImg = new Image(getClass().getResourceAsStream("/res/"+role+".png"));
		roleImg.setImage(newImg);
    	
		
		
		if (Global.activeRole>1) {
			mainMenuBar.setVisible(false);		
			changeButton.setDisable(true);
		}
		
		if (Global.activeRole>2) {
			shortcut1Button.setVisible(false);
			shortcut2Button.setVisible(false);
			shortcut3Button.setVisible(false);
			shortcut4Button.setVisible(false);
			shortcut5Button.setVisible(false);
			addmenuButton.setVisible(false);
			menuquantityTField.setDisable(false);
			billtotalcostTField.setDisable(false);
			confirmButton.setVisible(false);
		}
		
	}
    
    @FXML
    void changeGPane(ActionEvent event) {    	

		if (mainGPane.isVisible()) {
			mainGPane.setVisible(false);
			secondGPane.setVisible(true);
		}		
		else
		{
			secondGPane.setVisible(false);
			mainGPane.setVisible(true);
		}
    	
    }
    
    @FXML
    void viewLoginWindow(ActionEvent event) throws SQLException {
    	

		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
    	stage.close();
    	LoginWindow loginWindow = new LoginWindow();
		Stage newStage = new Stage();
        newStage.initStyle(StageStyle.TRANSPARENT);
    	loginWindow.start(newStage);  
    	
    }

	@FXML
    void addMenu(ActionEvent event) throws SQLException {
    	
    	int i=-1;
    	i= menuTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one resource to add");
    	    alert.showAndWait();
    	    return ;
    	}
    	
    	if(menuquantityTField.getText().equals("") || menuquantityTField.getText().isEmpty() ) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	if(Integer.parseInt(menuquantityTField.getText())<=0) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Menu Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Number value must be greater than 0");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	application.Menu menu = menuList.get(i);
    	
    	for (int i1=0;i1<billDetailMenuArrList.size();i1++) 
    		if (menu.getMenuID()==billDetailMenuArrList.get(i1).getBillDetailMenuID()) {
    			billDetailMenuArrList.get(i1).setBillDetailMenuQuantity(billDetailMenuArrList.get(i1).getBillDetailMenuQuantity()+Integer.parseInt(menuquantityTField.getText()));
    			menuquantityTField.setText("");
    			billDetailMenuArrList.get(i1).setBillDetailMenuCost(billDetailMenuArrList.get(i1).getBillDetailMenuQuantity()*billDetailMenuArrList.get(i1).getBillDetailMenuUnitPrice());
    			updateBillDetailTView(selectedBill);
    	    	return;
    		}
    	BillDetail billDetail = new BillDetail();
    	billDetail.setBillDetailMenuID(menu.getMenuID());
    	billDetail.setBillDetailMenuName(menu.getMenuName());
    	billDetail.setBillDetailMenuQuantity(Integer.parseInt(menuquantityTField.getText()));
    	menuquantityTField.setText("");
	    billDetail.setBillDetailMenuUnitPrice(menu.getMenuCost());
	    billDetail.setBillDetailMenuCost(billDetail.getBillDetailMenuQuantity()*billDetail.getBillDetailMenuUnitPrice());
	    billDetailMenuArrList.add(billDetail);
    	
	    updateBillDetailTView(selectedBill);
    }

    @FXML
    void findMenu(ActionEvent event) throws SQLException {
    	String a = menutypenameCBox.getSelectionModel().getSelectedItem();
    	if (a.equals("All")) a=null;
    	updateMenuTView(menunameTField.getText(),a,-1,-1);

    }
    
    @FXML
    void confirmBill(ActionEvent event) {
    	
    	Bill bill = new Bill();
		bill.setBillEmployee(Global.activeEmp);
    	bill.setBillID(Long.parseLong(billidTField.getText()));
    	bill.setBillCost(Float.parseFloat(billtotalcostTField.getText()));
    	bill.setBillDetailMenuList(billDetailMenuArrList);
    	try {
			BillData.updateBillData(bill);
		} catch (SQLException e) {
			if (e.getErrorCode()==20001) {
				Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Add Bill Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Not enough resource");
	    	    alert.showAndWait();
	    	    return;
			}
			
			if (e.getErrorCode()==20002) {
				Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Add Bill Error");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Table is currently unavailable");
	    	    alert.showAndWait();
	    	    return;
			}
			e.printStackTrace();
		}
    	
    	if (!BillData.errorMenu.equals("")) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Add Bill Error");
    		alert.setHeaderText(null);
    		alert.setContentText(BillData.errorMenu);
    	    alert.showAndWait();
    	    return;
    	}
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Bill Success");
		alert.setHeaderText(null);
		alert.setContentText("Update Bill Success");
	    alert.showAndWait();
	    
	    try {
			updateBillDetailTView(selectedBill);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			updateMenuTView(null, null, -1, -1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    @FXML
    void show(ActionEvent event) throws SQLException {
    	
    	DailyIncomeWindow dailyIncomeWindow = new DailyIncomeWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	dailyIncomeWindow.start(newStage);
    	
    }
    
    @FXML
    void charge(ActionEvent event) throws JRException {
    	
    	int i=-1;
    	i=billtableTView.getSelectionModel().getSelectedIndex();
    	if(i==-1) {
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Charge Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please select one bill to charge");
    	    alert.showAndWait();
    	    return ;
    	}
    	Bill bill = billtableTView.getSelectionModel().getSelectedItem();
    	
    	
    	try {
			BillData.chargeBill(bill);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Charge Bill Success");
			alert.setHeaderText(null);
			alert.setContentText("Charge Bill Success");
		    alert.showAndWait();
			updateBillTableTView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String reportSrcFile = "C:\\Users\\DELL\\eclipse-workspace\\QLNH\\src\\application\\Bill.jasper";
    	JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportSrcFile);
    	Connection conn = InitForm.connection;
    	  Map<String, Object> parameters = new HashMap<String, Object>();
    	  parameters.put("mahd", bill.getBillID());
    	 
         JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
         
//         JasperViewer jasperViewer = new JasperViewer(print,false); 
//         jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
//         jasperViewer.setTitle("Thông tin hoá đơn");
//         jasperViewer.setZoomRatio((float) 1.25);
//         jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
//         jasperViewer.requestFocus();
//         jasperViewer.setVisible(true);
         
         File outDir = new File("C:/jasperoutput");
         outDir.mkdirs();
   
         JRPdfExporter exporter = new JRPdfExporter();
   
         ExporterInput exporterInput = new SimpleExporterInput(print);
         // ExporterInput
         exporter.setExporterInput(exporterInput);
   
         // ExporterOutput
         OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                 "C:/jasperoutput/"+bill.getBillID()+"BillReport.pdf");
         // Output
         exporter.setExporterOutput(exporterOutput);
   
         //
         SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
         exporter.setConfiguration(configuration);
         exporter.exportReport();
   
         System.out.print("Done!");
    	
    }
    
    @FXML
    void viewDateWindow(ActionEvent event) {
    	
    	DateWindow dateWindow = new DateWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	dateWindow.start(newStage);
    	
    }


    public void closeMainWindow(ActionEvent event) {
    	Platform.exit();
    }
    
    public void checkinConfirm(ActionEvent event) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Check In"); 
	    alert.setHeaderText(null);
	    alert.setContentText("Ready to check in");
	
	    Optional<ButtonType> option = alert.showAndWait();
	    if (option.get()==ButtonType.OK) {
	    	Alert alert2 = new Alert(AlertType.INFORMATION);
		    alert2.setTitle("Check In Success");
			alert2.setHeaderText(null);
		    alert2.setContentText("Check in successfully!");
		    alert2.showAndWait();	
		    checkinItem.setDisable(true);
	    }
    }
    
    public void viewAccDetails(ActionEvent event) {
    	EmployeeAccountDetailsWindow employeeAccountDetailsWindow = new EmployeeAccountDetailsWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	employeeAccountDetailsWindow.start(newStage);
        
    }
    
    public void viewTableTypeManagement(ActionEvent event) {
    	TableTypeWindow tableTypeWindow = new TableTypeWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	tableTypeWindow.start(newStage);
    }
    
    public void viewTableInfo(ActionEvent event) {
    	ViewTableInfoWindow viewTableInfoWindow = new ViewTableInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	viewTableInfoWindow.start(newStage);
    }
    
    public void addTable(ActionEvent event) {
    	AddTableWindow addTableWindow = new AddTableWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addTableWindow.start(newStage);   
    }
    
    public void resourceInfo(ActionEvent event) {
    	ResourceInfoWindow resourceInfoWindow = new ResourceInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	resourceInfoWindow.start(newStage);   
    }
    
    @FXML
    void viewAddResourceWindow(ActionEvent event) {
    	
    	AddResourceWindow addResourceWindow = new AddResourceWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addResourceWindow.start(newStage);  

    }
    
    @FXML
    void viewOrderInfoWindow(ActionEvent event) {
    	
    	OrderDetailInfoWindow orderDetailInfoWindow = new OrderDetailInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	orderDetailInfoWindow.start(newStage);  

    }

    @FXML
    void addOrder(ActionEvent event) {
    	
    	AddOrderWindow addOrderWindow = new AddOrderWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addOrderWindow.start(newStage);   	    	

    }
    
    public void viewMenuTypeManagement(ActionEvent event) {
    	MenuTypeWindow menuTypeWindow = new MenuTypeWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	menuTypeWindow.start(newStage);
    }
    
    @FXML
    void viewMenuInfoWindow(ActionEvent event) {
    	
    	MenuInfoWindow menuInfoWindow = new MenuInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	menuInfoWindow.start(newStage);   	    	

    }
    
    @FXML
    void viewCusInfoWindow(ActionEvent event) {
    	
    	CustomerInfoWindow customerInfoWindow = new CustomerInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	customerInfoWindow.start(newStage);   	

    }
    
    @FXML
    void registerCustomer(ActionEvent event) {
    	
    	AddCustomerWindow addCustomerWindow = new AddCustomerWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addCustomerWindow.start(newStage);   	

    }
    
    @FXML
    void viewEmpInfoWindow(ActionEvent event) {
    	
    	EmployeeInfoWindow employeeInfoWindow = new EmployeeInfoWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	employeeInfoWindow.start(newStage);   

    }
    
    @FXML
    void addBill(ActionEvent event) {
    	
    	AddBillWindow addBillWindow = new AddBillWindow();
		Stage stage = (Stage) mainMenuBar.getScene().getWindow();
		Stage newStage = new Stage();
    	newStage.initModality(Modality.WINDOW_MODAL);
    	newStage.initOwner(stage);
    	addBillWindow.start(newStage);   
    	
    	newStage.setOnHiding (new EventHandler<WindowEvent>() {
    		public void handle(WindowEvent we) {

				try {
					updateMenuTView(null, null, -1, -1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					updateBillTableTView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	    }
    	});

    }

    
}
