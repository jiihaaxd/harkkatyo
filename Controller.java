package harkkatyo;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import harkkatyo.SmartPost;
import javafx.event.ActionEvent;
import harkkatyo.Storage;
import harkkatyo.Package;


public class Controller implements Initializable {
	SmartPost sp = new SmartPost();
	Storage s = new Storage();	
	ArrayList<String> cl = new ArrayList<>();
	ArrayList<Package> pl = new ArrayList<Package>();

	@FXML
	private ComboBox<String> postBox;
    @FXML
    private WebView web;
    @FXML
    private Button addtomap;
    @FXML
    private Button addtoroad;
    @FXML
    private Button deleteroads;
    @FXML
    private Button packagee;
    @FXML
    private ComboBox<String> choosePackage;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		cl = sp.getList();
		ObservableList<String> postList = FXCollections.observableArrayList(cl);
		postBox.setItems(postList);
		loadLocalFile();	
	}
	
	@FXML
	private void initialize(MouseEvent event) {
		Singleton sn = null;
		sn = Singleton.getInstance();
		
		ArrayList<String> t = new ArrayList<>();
		t = sn.getfromlist();
		
		ObservableList<String> packageList = FXCollections.observableArrayList(t);
		choosePackage.getItems().setAll(packageList);
	}

	@FXML
    private void loadLocalFile() {
		web.getEngine().load(getClass().getResource("index.html").toExternalForm());
    }

    @FXML
    private void drawMarker(ActionEvent event) {

    	String ad = sp.getAddress(postBox.getValue());
    	String info = sp.getInfo(postBox.getValue());
    	
    	web.getEngine().executeScript("document.goToLocation('" + ad + "','" + info + "','orange')");
   }
    
   @FXML
	private void drawRoad(ActionEvent event) {
	   ArrayList<Float> al = new ArrayList<>();
	   
	   al = sp.getArray(choosePackage.getValue());
	   
	   web.getEngine().executeScript("document.createPath("+ al + ", 'orange', 0.1)");
   }
   @FXML
   private void delRoads(ActionEvent event) {
	   web.getEngine().executeScript("document.deletePaths()");
   }
   
   @FXML
   private void openPackage(ActionEvent event) {
	   try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("harkkatyoFXML.fxml"));

			Scene scene = new Scene(root,672,683);
			primaryStage.setTitle("TIMO Järjestelmä");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			System.err.println("Error");
			e.printStackTrace();
		}
   }
}
