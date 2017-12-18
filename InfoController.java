/*
control info window
*/

package harkkatyo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;


public class InfoController implements Initializable{
	
	@FXML
	private javafx.scene.control.Button close;
	
	public void closeButton(ActionEvent event) {
		Stage scene = (Stage) close.getScene().getWindow();
        scene.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
}
