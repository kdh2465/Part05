package pack01_javafx.sec05_JavaFX_EventHandling.EX02_EventHandlingWithController.UsingJavacode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class MyStage extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox(); 
		root.setPrefWidth(500);
		root.setPrefHeight(150);
		
		Label mouseEventLabel = new Label("Event Area");		
		Button btn = new Button("Clear TextArea");		
		VBox vbox1 = new VBox(mouseEventLabel,btn);
		vbox1.setPrefWidth(200);
		vbox1.setSpacing(30);
		vbox1.setAlignment(Pos.CENTER);
		
		TextArea ta_log = new TextArea(); 
		ta_log.setEditable(false);
		VBox vbox2 = new VBox(ta_log);
		vbox2.setPrefWidth(300);
		
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
	
		//@ ActionEvent
		btn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				ta_log.setText("");
			}
		});
				
		//@WindowEvent
		primaryStage.addEventFilter(WindowEvent.WINDOW_SHOWING, (event)->{System.out.println("\nWindow Showing");});		
		primaryStage.setOnShown((event)->{ta_log.setText("\nWindow Event: Window Shown");});
		primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (event)->{System.out.println("\nWindow Event: Window Close");});
				
		ObservableList<Node> children = root.getChildren();
		children.add(vbox1);
		children.add(vbox2);
		 
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
