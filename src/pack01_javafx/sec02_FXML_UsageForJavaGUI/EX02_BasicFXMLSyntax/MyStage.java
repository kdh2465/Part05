package pack01_javafx.sec02_FXML_UsageForJavaGUI.EX02_BasicFXMLSyntax;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. FXML ���� �ε�
		HBox root = FXMLLoader.load(getClass().getResource("root.fxml"));		
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
