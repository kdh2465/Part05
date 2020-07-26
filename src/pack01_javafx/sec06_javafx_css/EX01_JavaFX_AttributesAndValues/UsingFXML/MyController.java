package pack01_javafx.sec06_javafx_css.EX01_JavaFX_AttributesAndValues.UsingFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class MyController implements Initializable{
	@FXML private VBox vbox15;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String imageURL = getClass().getResource("image/coffee_clip.png").toString();		
		vbox15.setStyle("-fx-background-image:url("+imageURL+")");
	}	
}
