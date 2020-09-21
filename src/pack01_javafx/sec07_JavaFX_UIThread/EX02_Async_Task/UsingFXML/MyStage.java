package pack01_javafx.sec07_JavaFX_UIThread.EX02_Async_Task.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    //#1. FXML 파일 로딩
	    VBox root = FXMLLoader.load(getClass().getResource("root.fxml"));    
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
}
