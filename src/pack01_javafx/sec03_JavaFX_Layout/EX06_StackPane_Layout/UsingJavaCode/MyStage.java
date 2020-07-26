package pack01_javafx.sec03_JavaFX_Layout.EX06_StackPane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. ��Ʈ �����̳� �� �ڽ� ��� ����
		StackPane root = new StackPane();
		root.setPrefSize(200, 100);
		root.setAlignment(Pos.CENTER); //�׸� Alignment ������ �������� ����(default : CENTER)
		
		Button btn1 = new Button("Stack 1");
		Button btn2 = new Button("Stack 2");
		Button btn3 = new Button("Stack 3");
		
		StackPane.setAlignment(btn1, Pos.CENTER); //�׸� Alignment ����
		StackPane.setAlignment(btn2, Pos.CENTER); //�׸� Alignment ����
		StackPane.setAlignment(btn3, Pos.CENTER); //�׸� Alignment ����
		
		StackPane.setMargin(btn1, new Insets(0,80,30,0));
		StackPane.setMargin(btn3, new Insets(30,0,0,80));
		
		ObservableList<Node> children = root.getChildren();
		children.add(btn1);
		children.add(btn2);
		children.add(btn3);
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
