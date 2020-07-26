package pack01_javafx.sec01_JavaFX_GUI_Structure.EX04_SetUI_UsingJavaCode;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyStage extends Application {
		
	@Override
	//#1. �Ű������� Stage ����
	public void start(Stage primaryStage) throws Exception {
		
		//#2. root �����̳� ����
		VBox parent = new VBox();
		parent.setPrefWidth(200);
		parent.setPrefHeight(100);
		parent.setSpacing(20);
		parent.setAlignment(Pos.CENTER);
		
		//#3. GUI ������� ����		
		Label lb = new Label("���̺�");
		lb.setFont(new Font(20));				
		Button btn = new Button("��ư");
		
		//#4. GUI ������� --> Root Parent�� �߰�		
		ObservableList<Node> ol = parent.getChildren();
		ol.add(lb);
		ol.add(btn);
		
		//#5. Scene ��ü ������ ������ �Ű������� root parent ����
		Scene scene = new Scene(parent);
		
		//#6. Stage�� Scene �߰� 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Stage���� UI ���� �� ��ġ�ϱ�");
		primaryStage.show();
		
	}	
}
