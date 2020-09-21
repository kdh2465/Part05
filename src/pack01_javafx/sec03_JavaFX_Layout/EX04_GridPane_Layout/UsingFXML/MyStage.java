package pack01_javafx.sec03_JavaFX_Layout.EX04_GridPane_Layout.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. GridPane_Using FXML
		GridPane root = FXMLLoader.load(getClass().getResource("root.fxml"));
			
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
