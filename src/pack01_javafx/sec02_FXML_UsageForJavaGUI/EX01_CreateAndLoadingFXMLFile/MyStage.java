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
		
		//FXML ���� �ε� ��� #1.
		VBox root1 = FXMLLoader.load(getClass().getResource("root.fxml"));		
		//FXML ���� �ε� ��� #2.
		FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
		VBox root2 = loader.load();
		
		//�ڹ��ڵ忡�� ���� UI ����
		VBox root = new VBox();
		root.setPrefWidth(150);
		root.setPrefHeight(50);
		root.setSpacing(10);
		root.getChildren().add(new Label("FXML �ε�"));
		root.getChildren().add(new Button("Ŭ��"));
		
		//Scene ����
		Scene scene = new Scene(root1);
		
		//Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
