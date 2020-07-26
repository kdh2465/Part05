package pack01_javafx.sec05_JavaFX_EventHandling.EX03_PropertyChangeListening.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. fxml �ε�
		//HBox root = FXMLLoader.load(getClass().getResource("root.fxml"));
		FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
		HBox root = loader.load();
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
}
