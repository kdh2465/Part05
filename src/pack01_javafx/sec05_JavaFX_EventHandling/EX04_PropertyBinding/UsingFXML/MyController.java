package pack01_javafx.sec05_JavaFX_EventHandling.EX04_PropertyBinding.UsingFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class MyController implements Initializable{
	@FXML private TextField textField1;
	@FXML private TextField textField2;
	@FXML private TextField textField3;
	@FXML private TextField textField4;
	@FXML private TextField textField5;
	@FXML private TextField textField6;
	@FXML private Slider slider;
	@FXML private Label label;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//@Forword Direction
		textField2.textProperty().bind(textField1.textProperty());
		//@Backword Direction
		textField3.textProperty().bind(textField4.textProperty());
		//@BiDirection
		textField5.textProperty().bindBidirectional(textField6.textProperty());
		
		label.rotateProperty().bind(slider.valueProperty());
		
		
	}
	
}
