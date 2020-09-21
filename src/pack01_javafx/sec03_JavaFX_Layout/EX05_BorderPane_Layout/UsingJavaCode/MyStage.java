package pack01_javafx.sec03_JavaFX_Layout.EX05_BorderPane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		BorderPane root = new BorderPane();
		root.setPrefSize(300, 200);
		
		//@center
		Button btn_center = new Button("Center");
		btn_center.setMaxHeight(Double.MAX_VALUE);
		btn_center.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setAlignment(btn_center, Pos.CENTER);
		root.setCenter(btn_center);
		
		//@top
		Button btn_top = new Button("Top");
		btn_top.setMaxHeight(Double.MAX_VALUE);
		btn_top.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setAlignment(btn_top, Pos.CENTER);
		root.setTop(btn_top);
		
		//@right
		Button btn_right = new Button("Right");
		btn_right.setMaxHeight(Double.MAX_VALUE);
		btn_right.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setAlignment(btn_right, Pos.CENTER);
		root.setRight(btn_right);
				
		//@bottom
		Button btn_bottom = new Button("Bottom");
		btn_bottom.setMaxHeight(Double.MAX_VALUE);
		btn_bottom.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setAlignment(btn_bottom, Pos.CENTER);
		root.setBottom(btn_bottom);
		
		//@left
		Button btn_left = new Button("Left");
		btn_left.setMaxHeight(Double.MAX_VALUE);
		btn_left.setMaxWidth(Double.MAX_VALUE);
		BorderPane.setAlignment(btn_left, Pos.CENTER);
		root.setLeft(btn_left);
				
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
