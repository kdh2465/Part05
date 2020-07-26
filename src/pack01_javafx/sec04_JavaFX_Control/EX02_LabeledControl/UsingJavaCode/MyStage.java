package pack01_javafx.sec04_JavaFX_Control.EX02_LabeledControl.UsingJavaCode;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		BorderPane root = new BorderPane();
		root.setPrefSize(250, 150);
		root.setPadding(new Insets(10));
		
		//#1-1. Top: Label
		Label title = new Label("Label/Button/CheckBox/RadioButton");
		BorderPane.setAlignment(title, Pos.CENTER);
		root.setTop(title);
					
		//#1-2. Center
		//@CheckBox						
		CheckBox checkBox1 = new CheckBox("check #1");
		CheckBox checkBox2 = new CheckBox("check #2");
		CheckBox checkBox3 = new CheckBox("check #3");
		checkBox1.setUserData("첫번째 CheckBox");
		checkBox2.setUserData("두번째 CheckBox");
		checkBox3.setUserData("세번째 CheckBox");
		checkBox1.setSelected(true);
		VBox vbox1 = new VBox(checkBox1, checkBox2, checkBox3);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setSpacing(10);
		
		//@RadioButton
		RadioButton radioBtn1 = new RadioButton("radio #1");
		RadioButton radioBtn2 = new RadioButton("radio #2");
		RadioButton radioBtn3 = new RadioButton("radio #3");		
		radioBtn1.setUserData("첫번째 RadioButton");
		radioBtn2.setUserData("두번째 RadioButton");
		radioBtn3.setUserData("첫번째 RadioButton");
		radioBtn1.setSelected(true);
		
		ToggleGroup tg = new ToggleGroup();
		
		radioBtn1.setToggleGroup(tg);
		radioBtn2.setToggleGroup(tg);
		radioBtn3.setToggleGroup(tg);
						
		VBox vbox2 = new VBox(radioBtn1, radioBtn2, radioBtn3);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setSpacing(10);
		
		//@ Center - HBox
		HBox hbox_center = new HBox(vbox1, vbox2);
		hbox_center.setAlignment(Pos.CENTER);
		hbox_center.setSpacing(20);
				
		root.setCenter(hbox_center);
		
		//#1-3. Bottom: Button
		Button btn1 = new Button("취소");
		Button btn2 = new Button("확인");
		HBox hBox_bottom = new HBox(btn1, btn2);
		hBox_bottom.setSpacing(50);
		hBox_bottom.setAlignment(Pos.CENTER);
		root.setBottom(hBox_bottom);
											
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
