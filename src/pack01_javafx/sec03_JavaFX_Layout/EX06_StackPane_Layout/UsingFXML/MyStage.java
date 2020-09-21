﻿package pack01_javafx.sec03_JavaFX_Layout.EX06_StackPane_Layout.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. StackPane_Using FXML
		StackPane root = FXMLLoader.load(getClass().getResource("root.fxml"));
			
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
