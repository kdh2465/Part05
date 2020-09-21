package pack01_javafx.sec04_JavaFX_Control.EX09_DatePickerColorPicker.UsingJavaCode;

import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefWidth(300);
		root.setPrefHeight(100);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		root.setPadding(new Insets(10));
		root.setSpacing(30);
		
		//@DatePicker
		DatePicker datePicker = new DatePicker();
		//datePicker.setValue(LocalDate.of(2030,1,1)); //특정날짜
		datePicker.setValue(LocalDate.now()); //현재날짜
		
		//@ColorPicker
		ColorPicker colorPicker = new ColorPicker();
		//colorPicker.setValue(Color.RED);
		colorPicker.setValue(new Color(1.0,0,0,1.0));
		
		ObservableList<Node> children = root.getChildren();
		children.add(datePicker);
		children.add(colorPicker);
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
