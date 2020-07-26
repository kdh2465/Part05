package pack01_javafx.sec03_JavaFX_Layout.EX09_AnchorPane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		AnchorPane root = new AnchorPane();
		root.setPrefSize(200, 100);
		
		Button btn1 = new Button("btn1");
		AnchorPane.setTopAnchor(btn1, 10.0);
		AnchorPane.setBottomAnchor(btn1, 60.0);
		AnchorPane.setLeftAnchor(btn1, 20.0);
		AnchorPane.setRightAnchor(btn1, 20.0);		
		
		Button btn2 = new Button("btn2");
		btn2.setLayoutX(20);
		btn2.setLayoutY(60);
		
		Button btn3 = new Button("btn3");
		AnchorPane.setTopAnchor(btn3, 60.0);
		AnchorPane.setBottomAnchor(btn3, 10.0);
		AnchorPane.setLeftAnchor(btn3, 80.0);
		AnchorPane.setRightAnchor(btn3, 20.0);
		
				
		ObservableList<Node> children = root.getChildren();
		children.add(btn1);
		children.add(btn2);
		children.add(btn3);
											
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
