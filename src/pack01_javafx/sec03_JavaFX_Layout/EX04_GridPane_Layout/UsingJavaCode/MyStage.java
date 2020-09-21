package pack01_javafx.sec03_JavaFX_Layout.EX04_GridPane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		GridPane root = new GridPane();
		root.setPrefSize(200, 100);
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10));
		
		Button btn1 = new Button("btn1");
		Button btn2 = new Button("btn2");
		Button btn3 = new Button("btn3");		
		
		btn1.setMaxWidth(Double.MAX_VALUE);
		btn1.setMaxHeight(Double.MAX_VALUE);
		GridPane.setHgrow(btn1, Priority.ALWAYS);
		GridPane.setVgrow(btn1, Priority.ALWAYS);		
		GridPane.setRowIndex(btn1, 0);
		GridPane.setColumnIndex(btn1, 0);
		
		btn2.setMaxWidth(Double.MAX_VALUE);
		btn2.setMaxHeight(Double.MAX_VALUE);
		GridPane.setHgrow(btn2, Priority.ALWAYS);
		GridPane.setVgrow(btn2, Priority.ALWAYS);		
		GridPane.setRowIndex(btn2, 0);
		GridPane.setColumnIndex(btn2, 1);
		
		btn3.setMaxWidth(Double.MAX_VALUE);
		btn3.setMaxHeight(Double.MAX_VALUE);
		GridPane.setHgrow(btn3, Priority.ALWAYS);
		GridPane.setVgrow(btn3, Priority.ALWAYS);		
		GridPane.setRowIndex(btn3, 1);
		GridPane.setColumnIndex(btn3, 0);
		GridPane.setColumnSpan(btn3, 2);
		
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
