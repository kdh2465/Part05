package pack01_javafx.sec07_JavaFX_UIThread.EX03_Async_Service.UsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
	    //#1. FXML ���� �ε�
	    VBox root = FXMLLoader.load(getClass().getResource("root.fxml"));
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();		
	}
}