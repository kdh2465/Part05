package pack01_javafx.sec04_JavaFX_Control.EX11_ImageViewControl.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. fxml �ε�
		VBox root = FXMLLoader.load(getClass().getResource("root.fxml"));
			
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
