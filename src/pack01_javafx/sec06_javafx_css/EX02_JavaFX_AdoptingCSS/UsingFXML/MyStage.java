package pack01_javafx.sec06_javafx_css.EX02_JavaFX_AdoptingCSS.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. fxml 로딩
		//HBox root = FXMLLoader.load(getClass().getResource("root.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
		VBox root = loader.load();
		
		//@JavaCode에서 Stylesheet 가져오기
		//root.getStylesheets().add(getClass().getResource("myStyle.css").toString());
	
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
}
