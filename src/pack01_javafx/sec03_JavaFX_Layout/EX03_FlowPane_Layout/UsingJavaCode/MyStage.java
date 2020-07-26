package pack01_javafx.sec03_JavaFX_Layout.EX03_FlowPane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. ��Ʈ �����̳� �� �ڽ� ��� ����
		FlowPane root = new FlowPane();
		root.setPrefSize(200, 100);
		root.setHgap(10);
		root.setVgap(10);	
		root.setAlignment(Pos.CENTER);			
		
		ObservableList<Node> children = root.getChildren();
		
		children.add(new Button("btn1"));
		children.add(new Button("btn2"));	
		children.add(new Button("btn3"));	
		children.add(new Button("btn4"));	
		children.add(new Button("btn5"));	
		
		Button btn6 = new Button("btn6");
		FlowPane.setMargin(btn6, new Insets(10));
		children.add(btn6);
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
