package pack01_javafx.sec05_JavaFX_EventHandling.EX03_PropertyChangeListening.UsingFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MyController implements Initializable{
	@FXML private Label label1;
	@FXML private Label label2;
	@FXML private Label label3;
	@FXML private TextField textField;
	@FXML private CheckBox checkBox;
	@FXML private ComboBox<String> comboBox;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//@textField property
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("OldValue = "+oldValue +"  newValue = "+newValue);
				label1.setText("글자수 : "+newValue.length());
			}
		});
		//@checkBox property
		checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				// TODO Auto-generated method stub
				if(newValue) {
					label2.setText("체크 선택");
				} else {
					label2.setText("체크 해제");
				}
			}
		});
		//@comboBox property		
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				label3.setText(newValue+" 선택");				
			}			
		});
		
	}
	
}
