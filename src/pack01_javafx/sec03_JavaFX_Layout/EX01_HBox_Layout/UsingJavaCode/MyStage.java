package pack01_javafx.sec03_JavaFX_Layout.EX01_HBox_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefSize(200, 100);
		root.setSpacing(10);
		root.setFillHeight(true); //default(true)
		root.setAlignment(Pos.CENTER);
		
		ObservableList<Node> children = root.getChildren();
		
		Button btn1 = new Button("btn1");				
		HBox.setMargin(btn1, new Insets(10));
		btn1.setMaxHeight(Double.MAX_VALUE); //fillHeight: true
		
		Button btn2 = new Button("btn2");
		HBox.setHgrow(btn2, Priority.ALWAYS);
		btn2.setMaxWidth(Double.MAX_VALUE);
		
		children.add(btn1);
		children.add(btn2);
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
