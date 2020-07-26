package pack02_javanetworkUsingjavafx.sec03_MulticastChattingWithJavaFX.EX01_MulticastChattingWithJavaFX_Clients;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. BorderPane_Using FXML
		FXMLLoader loader= new FXMLLoader(getClass().getResource("root.fxml"));
		BorderPane root = loader.load();
		
		((MyController)loader.getController()).setStage(primaryStage);
		
		//BorderPane root = FXMLLoader.load(getClass().getResource("root.fxml"));
			
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	} 
}
