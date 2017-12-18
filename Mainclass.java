package harkkatyo;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.stage.Stage; 
import javafx.fxml.FXMLLoader;


public class Mainclass extends Application{
	public static void main(String[] args) {
		launch(args);
	 }

	@Override
	public void start(Stage arg0) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("harkkatyo.fxml"));
		Scene scene = new Scene(root);
		arg0.setTitle("TIMO järjestelmä");
		arg0.setScene(scene);
		arg0.show();
	}
}