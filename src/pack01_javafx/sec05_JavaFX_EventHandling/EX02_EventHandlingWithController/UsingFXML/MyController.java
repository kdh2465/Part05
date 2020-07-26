package pack01_javafx.sec05_JavaFX_EventHandling.EX02_EventHandlingWithController.UsingFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyController implements Initializable{
	@FXML private VBox vbox1;
	@FXML private HBox root;
	@FXML private TextArea ta_log;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//@ MouseEvent ----------------------------
		vbox1.setOnMouseClicked((event)->{
			ta_log.appendText("\nMouse Clicked: "+event.getX()+", "+event.getY());			
		});
		
		//@ KeyEvent ----------------------------
		EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {				
				ta_log.appendText("\nKeyPress");
				ta_log.appendText("\nKeyCode : "+event.getCode().name()+"  PressedText : "+event.getText());
				
			}
		};	
		root.addEventFilter(KeyEvent.KEY_PRESSED, keyPressed);
		root.addEventFilter(KeyEvent.KEY_RELEASED, (event)-> {ta_log.appendText("\nKey Released");});				
	}
	public void addStageEvent(Stage stage) {		
		//@WindowEvent
		stage.addEventFilter(WindowEvent.WINDOW_SHOWING, (event)->{System.out.println("\nWindow Showing");});
		stage.setOnShown((event)->{ta_log.setText("\nWindow Event: Window Shown");});
		stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (event)->{System.out.println("\nWindow Event: Window Close");});
	}
	
	//@ ActionEvent
	public void btn_ActionHandler(ActionEvent event) {
		ta_log.setText("");
	}
	
}
