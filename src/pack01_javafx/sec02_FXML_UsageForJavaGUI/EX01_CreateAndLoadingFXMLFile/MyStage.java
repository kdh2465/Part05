package pack01_javafx.sec02_FXML_UsageForJavaGUI.EX01_CreateAndLoadingFXMLFile;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//FXML 파일 로딩 방법 #1.
		VBox root1 = FXMLLoader.load(getClass().getResource("root.fxml"));		
		//FXML 파일 로딩 방법 #2.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
		VBox root2 = loader.load();
		
		//자바코드에서 직적 UI 구성
		VBox root = new VBox();
		root.setPrefWidth(150);
		root.setPrefHeight(50);
		root.setSpacing(10);
		root.getChildren().add(new Label("FXML 로딩"));
		root.getChildren().add(new Button("클릭"));
		
		//Scene 설정
		Scene scene = new Scene(root1);
		
		//Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
