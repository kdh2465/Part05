package pack01_javafx.sec05_JavaFX_EventHandling.EX02_EventHandlingWithController.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. fxml 로딩
		//HBox root = FXMLLoader.load(getClass().getResource("root.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
		HBox root = loader.load();
		MyController myController = loader.getController();
		myController.addStageEvent(primaryStage); //controller에서 stage를 사용하기 위해서 만든 메서드

		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
}
