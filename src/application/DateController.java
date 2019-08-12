package application;

import java.io.File;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.LocalDate;

import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class DateController {

    @FXML
    private DatePicker startDPicker;

    @FXML
    private Button closeButton;

    @FXML
    private Button confirmButton;

    @FXML
    private DatePicker endDPicker;
    
    @FXML
    private RadioButton detailincomereportRadio;

    @FXML
    private RadioButton revenuereportRadio;

    @FXML
    private RadioButton inventoryreportRadio;

    @FXML
    private RadioButton incomereportRadio;

    @FXML
    private ToggleGroup type;
    
    @FXML
    void confirm(ActionEvent event) throws JRException {
    	
    	RadioButton selectedRadioButton = (RadioButton) type.getSelectedToggle();
    	String toggleGroupValue = selectedRadioButton.getText();
    	
    	if (toggleGroupValue.equals(incomereportRadio.getText())) incomeReport();
    	else if (toggleGroupValue.equals(detailincomereportRadio.getText())) detailIncomeReport();
    	else if (toggleGroupValue.equals(inventoryreportRadio.getText())) inventoryReport();
    	else if (toggleGroupValue.equals(revenuereportRadio.getText())) revenueReport();
    	
    }


    void incomeReport() throws JRException {
    	
    	if(startDPicker.getValue()==null) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Print Income Report Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}
    	
    	if(endDPicker.getValue()==null) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Print Income Report Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Required field cannot be left blank.");
    	    alert.showAndWait();
    	    return;
    	}
    	    String reportSrcFile = "C:\\Users\\DELL\\eclipse-workspace\\QLNH\\src\\application\\IncomeReport.jrxml";
        	JasperReport jasperReport = null;
			try {
				jasperReport = JasperCompileManager.compileReport(reportSrcFile);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	Connection conn = InitForm.connection;
        	  Map<String, Object> parameters = new HashMap<String, Object>();
        	  parameters.put("startdate",Timestamp.valueOf(startDPicker.getValue().atStartOfDay()));
        	  parameters.put("enddate",Timestamp.valueOf(endDPicker.getValue().atStartOfDay()));
        	 
             JasperPrint print = null;
			try {
				print = JasperFillManager.fillReport(jasperReport, parameters, conn);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             JasperViewer jasperViewer = new JasperViewer(print,false); 
             jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
             jasperViewer.setTitle("Thông tin hoá đơn");
             jasperViewer.setZoomRatio((float) 1.25);
             jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
             jasperViewer.requestFocus();
             jasperViewer.setVisible(true);
             
             File outDir = new File("C:/jasperoutput");
             outDir.mkdirs();
       
             JRPdfExporter exporter = new JRPdfExporter();
       
             ExporterInput exporterInput = new SimpleExporterInput(print);
             // ExporterInput
             exporter.setExporterInput(exporterInput);
       
             // ExporterOutput
             OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                     "C:/jasperoutput/"+startDPicker.getValue()+endDPicker.getValue()+"IncomeReport.pdf");
             // Output
             exporter.setExporterOutput(exporterOutput);
       
             //
             SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
             exporter.setConfiguration(configuration);
             try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
             System.out.print("Done!");
        	 
             Stage stage = (Stage) confirmButton.getScene().getWindow();
         	stage.close(); 		
    	
    }
    
    void detailIncomeReport() throws JRException {
    	
    	    String reportSrcFile = "C:\\Users\\DELL\\eclipse-workspace\\QLNH\\src\\application\\DetailIncomeReport.jasper";
        	JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportSrcFile);
        	Connection conn = InitForm.connection;
        	JasperPrint print = null;
        	Map<String, Object> parameters = new HashMap<String, Object>();
			try {
				print = JasperFillManager.fillReport(jasperReport, parameters, conn);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
             JasperViewer jasperViewer = new JasperViewer(print,false); 
             jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
             jasperViewer.setTitle("Thông tin chi tiết hoá đơn");
             jasperViewer.setZoomRatio((float) 1.25);
             jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
             jasperViewer.requestFocus();
             jasperViewer.setVisible(true);
             
             File outDir = new File("C:/jasperoutput");
             outDir.mkdirs();
       
             JRPdfExporter exporter = new JRPdfExporter();
       
             ExporterInput exporterInput = new SimpleExporterInput(print);
             // ExporterInput
             exporter.setExporterInput(exporterInput);
       
             // ExporterOutput
             OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                     "C:/jasperoutput/DetailIncomeReport.pdf");
             // Output
             exporter.setExporterOutput(exporterOutput);
       
             //
             SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
             exporter.setConfiguration(configuration);
             try {
				exporter.exportReport();
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
       
             System.out.print("Done!");
        	 
             Stage stage = (Stage) confirmButton.getScene().getWindow();
         	stage.close(); 		
    	
    }
    
    void inventoryReport() throws JRException {
    	
	    String reportSrcFile = "C:\\Users\\DELL\\eclipse-workspace\\QLNH\\src\\application\\InventoryReport.jasper";
    	JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportSrcFile);
    	Connection conn = InitForm.connection;
    	JasperPrint print = null;
    	Map<String, Object> parameters = new HashMap<String, Object>();
		try {
			print = JasperFillManager.fillReport(jasperReport, parameters, conn);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         JasperViewer jasperViewer = new JasperViewer(print,false); 
         jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
         jasperViewer.setTitle("Thông tin chi tiết hoá đơn");
         jasperViewer.setZoomRatio((float) 1.25);
         jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
         jasperViewer.requestFocus();
         jasperViewer.setVisible(true);
         
         File outDir = new File("C:/jasperoutput");
         outDir.mkdirs();
   
         JRPdfExporter exporter = new JRPdfExporter();
   
         ExporterInput exporterInput = new SimpleExporterInput(print);
         // ExporterInput
         exporter.setExporterInput(exporterInput);
   
         // ExporterOutput
         OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                 "C:/jasperoutput/"+LocalDate.now()+"InventoryReport.pdf");
         // Output
         exporter.setExporterOutput(exporterOutput);
   
         //
         SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
         exporter.setConfiguration(configuration);
         try {
			exporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
         System.out.print("Done!");
    	 
         Stage stage = (Stage) confirmButton.getScene().getWindow();
     	stage.close(); 		
	
    }
    
    void revenueReport() throws JRException {
    	
	    String reportSrcFile = "C:\\Users\\DELL\\eclipse-workspace\\QLNH\\src\\application\\RevenueReport.jasper";
    	JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(reportSrcFile);
    	Connection conn = InitForm.connection;
    	JasperPrint print = null;
    	Map<String, Object> parameters = new HashMap<String, Object>();
		try {
			print = JasperFillManager.fillReport(jasperReport, parameters, conn);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         JasperViewer jasperViewer = new JasperViewer(print,false); 
         jasperViewer.setDefaultCloseOperation(JasperViewer.DISPOSE_ON_CLOSE);
         jasperViewer.setTitle("Thông tin chi tiết hoá đơn");
         jasperViewer.setZoomRatio((float) 1.25);
         jasperViewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
         jasperViewer.requestFocus();
         jasperViewer.setVisible(true);
         
         File outDir = new File("C:/jasperoutput");
         outDir.mkdirs();
   
         JRPdfExporter exporter = new JRPdfExporter();
   
         ExporterInput exporterInput = new SimpleExporterInput(print);
         // ExporterInput
         exporter.setExporterInput(exporterInput);
   
         // ExporterOutput
         OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                 "C:/jasperoutput/RevenueReport.pdf");
         // Output
         exporter.setExporterOutput(exporterOutput);
   
         //
         SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
         exporter.setConfiguration(configuration);
         try {
			exporter.exportReport();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
         System.out.print("Done!");
    	 
         Stage stage = (Stage) confirmButton.getScene().getWindow();
     	stage.close(); 		
	
	}

    @FXML
    void closeWindow(ActionEvent event) {
    	
    	Stage stage = (Stage) confirmButton.getScene().getWindow();
    	stage.close();

    }

}
