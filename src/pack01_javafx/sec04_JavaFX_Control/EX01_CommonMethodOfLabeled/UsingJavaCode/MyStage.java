package pack01_javafx.sec04_JavaFX_Control.EX01_CommonMethodOfLabeled.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. ��Ʈ �����̳� �� �ڽ� ��� ����
		HBox root = new HBox();
		root.setPrefWidth(400);
		root.setPrefHeight(100);
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		
		Label label1 = new Label("ù��° Label�̸� ���پȿ� �ٿ��� ǥ��");
		label1.setUnderline(true);
		label1.setTextFill(Color.RED);		
		label1.setFont(new Font(15));		
		
		Label label2 = new Label("�ι�° Label�̸� ���� �پȿ� �ٿ��� ǥ�� (�߾�����)");
		label2.setTextFill(Color.BLUE);		
		label2.setWrapText(true);
		label2.setTextAlignment(TextAlignment.CENTER);
		
		Label label3 = new Label("����° Label�̸� ���� �پȿ� �ٿ��� ǥ�� (��������)");
		label3.setTextFill(Color.GREEN);		
		label3.setWrapText(true);
		label3.setTextAlignment(TextAlignment.RIGHT);
		
		ObservableList<Node> children = root.getChildren();
		children.addAll(label1, label2, label3);
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
