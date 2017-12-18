/*
create package window
*/

package harkkatyo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import harkkatyo.Storage;
import harkkatyo.Package;


public class Controller2 implements Initializable{
	
	@FXML
	private RadioButton c1;
    @FXML
    private RadioButton c2;
    @FXML
    private RadioButton c3;
    @FXML
    private Button infobutton;
    @FXML
    private TextField name;
    @FXML
    private TextField size1;
    @FXML
    private TextField size2;
    @FXML
    private TextField size3;
    @FXML
    private TextField weight;
    @FXML
    private CheckBox breakvalue;
    @FXML
    private ToggleGroup cg;
    @FXML
    private Button cancelbutton;
    @FXML
    private Button createpackagebutton;    
    @FXML
    private ComboBox<String> chooseItem;
    @FXML
    private ComboBox<String> chooseFrom;
    @FXML
    private ComboBox<String> chooseTo;
    
    Storage s = new Storage();
    ArrayList<Package> pa = new ArrayList<>();
    ArrayList<Package> pl = new ArrayList<>();
    SmartPost sp = new SmartPost();
	
	ArrayList<String> cl = new ArrayList<>();

	public ArrayList<Package> getPlist() {
		return pl;
	}
	
		public String selectClassType() {
			String returnValue = "";
	        if(c1.isSelected()) {
	        	returnValue = "1";
	        } else if(c2.isSelected()) {
	        	returnValue = "2";
	        } else if(c3.isSelected()) {
	        	returnValue = "3"; 
	        } else {
	        	returnValue = "0";
	        }
	        
	        return returnValue;
	    }
		
		@FXML
		public void infoButton(ActionEvent event) {
		
			try {
				Stage primaryStage = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("InfoFXML.fxml"));
	
				Scene scene = new Scene(root,300,300);
				primaryStage.setTitle("Infoa luokista");
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch(Exception e) {
				System.err.println("Error");
				e.printStackTrace();
			}
	    }

		public void cancelButton(ActionEvent event) {
			Stage scene = (Stage) cancelbutton.getScene().getWindow();
		    scene.close();
		}
		
		@FXML
		public void createPackageButton(ActionEvent event) {
			Singleton sn = null;
			sn = Singleton.getInstance();
			ToggleGroup tg = new ToggleGroup();
			c1.setUserData(1);
			c1.setToggleGroup(tg);
			c2.setUserData(2);
			c2.setToggleGroup(tg);
			c3.setUserData(3);
			c3.setToggleGroup(tg);
			Stage scene1 = (Stage) createpackagebutton.getScene().getWindow();

			boolean test;
			if(chooseItem.getValue() == "" || chooseItem.getValue() == null) {

				String size11 = size1.getText();
				int size111 = Integer.parseInt(size11);
				String size22 = size2.getText();
				int size222 = Integer.parseInt(size22);
				String size33 = size3.getText();
				int size333 = Integer.parseInt(size33);
				int sizetot = size111*size222*size333;
				test = s.CreatePackage2(name.getText(), sizetot, tg.getSelectedToggle().getUserData().toString(), 100, breakvalue.isSelected());

				if (test == true) {
					pl.add(new ReadyPackage(name.getText(), breakvalue.isSelected(), 100, sizetot, chooseFrom.getValue(), chooseTo.getValue()));
					sn.addtolist(name.getText()+": "+chooseFrom.getValue()+"; " + chooseTo.getValue());
					scene1.close();
				} 
				
				else {
					try {
						Stage primaryStage = new Stage();
						Parent root = FXMLLoader.load(getClass().getResource("NoclassFXML.fxml"));
						Scene scene = new Scene(root,300,300);
						primaryStage.setTitle("HUOM");
						primaryStage.setScene(scene);
						primaryStage.show();
					} 
					catch(Exception e) {
						System.err.println("Error");
						e.printStackTrace();
					}
				}	
			} 
			
			else {
				test = s.CreatePackage(chooseItem.getValue(), tg.getSelectedToggle().getUserData().toString());

				if (test == true) {
					s.addReady(chooseItem.getValue(), s.getBreakvalue(chooseItem.getValue()), 100, s.getSizevalue(chooseItem.getValue()), chooseFrom.getValue(), chooseTo.getValue());
					pl.add(new ReadyPackage(chooseItem.getValue(), s.getBreakvalue(chooseItem.getValue()), 100, s.getSizevalue(chooseItem.getValue()), chooseFrom.getValue(), chooseTo.getValue()));
					sn.addtolist(chooseItem.getValue()+": "+chooseFrom.getValue()+"; " + chooseTo.getValue());
					scene1.close();
			    } 
				
				else {
					try {
						Stage primaryStage = new Stage();
						Parent root = FXMLLoader.load(getClass().getResource("NoclassFXML.fxml"));
			
						Scene scene = new Scene(root,300,300);
						primaryStage.setTitle("HUOM");
						primaryStage.setScene(scene);
						primaryStage.show();
					} catch(Exception e) {
						System.err.println("Error");
						e.printStackTrace();
					}
				}
			}	
		}
		
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			pa = s.getList();
			chooseItem.getItems().add("");
			for (Package a : pa) {
				chooseItem.getItems().add(a.getName());
			}
			
			cl = sp.getList();
			ObservableList<String> postList = FXCollections.observableArrayList(cl);

			chooseFrom.setItems(postList);
			chooseTo.setItems(postList);
		}	
	}

