package pack01_javafx.sec03_JavaFX_Layout.EX08_TilePane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		TilePane root = new TilePane();
		root.setPrefSize(300, 100);
		root.setPrefTileWidth(60);
		root.setPrefTileHeight(60);
		root.setHgap(10);
		root.setVgap(10);
		root.setAlignment(Pos.CENTER);
				
		Button btn1 = new Button("btn1");
		btn1.setMaxHeight(Double.MAX_VALUE);
		btn1.setMaxWidth(Double.MAX_VALUE);		
		
		Button btn2 = new Button("btn2");
		TilePane.setAlignment(btn2, Pos.BOTTOM_RIGHT);
		
		Button btn3 = new Button("btn3");
		TilePane.setAlignment(btn3, Pos.CENTER);
		
		Button btn4 = new Button("btn4");
		btn4.setMaxHeight(Double.MAX_VALUE);
		btn4.setMaxWidth(Double.MAX_VALUE);
		
		ObservableList<Node> children = root.getChildren();
		children.add(btn1);
		children.add(btn2);
		children.add(btn3);
		children.add(btn4);
									
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
