﻿package pack01_javafx.sec04_JavaFX_Control.EX06_ListViewTreeView.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. fxml 로딩
		HBox root = FXMLLoader.load(getClass().getResource("root.fxml"));
			
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
