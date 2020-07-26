package pack01_javafx.sec05_JavaFX_EventHandling.EX04_PropertyBinding.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MyStage extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		VBox root = new VBox();
		root.setPrefWidth(300);
		root.setPrefHeight(300);
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		
		TextField textField1 = new TextField();
		TextField textField2 = new TextField();
		textField2.textProperty().bind(textField1.textProperty());
		HBox hbox1 = new HBox(textField1, textField2);
		hbox1.setSpacing(10);
		
		TextField textField3 = new TextField();
		TextField textField4 = new TextField();
		textField3.textProperty().bind(textField4.textProperty());
		HBox hbox2 = new HBox(textField3, textField4);
		hbox2.setSpacing(10);
		
		TextField textField5 = new TextField();
		TextField textField6 = new TextField();
		textField5.textProperty().bindBidirectional(textField6.textProperty());
		HBox hbox3 = new HBox(textField5, textField6);
		hbox3.setSpacing(10);
				
		Slider slider = new Slider(-180,180,0);	
		Label label = new Label("회전 텍스트");
		label.rotateProperty().bind(slider.valueProperty());		
		HBox hbox4 = new HBox(slider, label);
		hbox4.setSpacing(30);
		hbox4.setPrefHeight(200);
		hbox4.setAlignment(Pos.CENTER);
		
		ObservableList<Node> children = root.getChildren();
		children.add(new Label("Forward Direction"));
		children.add(hbox1);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(new Label("Backward Direction"));
		children.add(hbox2);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(new Label("BiDirection"));
		children.add(hbox3);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(hbox4);
				 
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
