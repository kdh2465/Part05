package pack01_javafx.sec04_JavaFX_Control.EX03_CommonMethodOfTextInputControl.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. ��Ʈ �����̳� �� �ڽ� ��� ����
		VBox root = new VBox();
		root.setPrefWidth(300);
		root.setPrefHeight(100);
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		
		TextField textField1 = new TextField();
		textField1.setFont(new Font(15));
		textField1.setText("ù��° TextField");
	
		textField1.selectAll(); //Java Code������ ��� ���� (���� Controller���� ��ɺο�)
		textField1.copy(); //Java Code������ ��� ���� (���� Controller���� ��ɺο�)
						
		TextField textField2 = new TextField();
		textField2.paste(); //Java Code������ ��� ���� (���� Controller���� ��ɺο�)
		textField2.appendText(" (���纻)");
		textField2.setEditable(false);
				
		TextField textField3 = new TextField(); 
		textField3.setPromptText("����° TextField"); 
		
		ObservableList<Node> children = root.getChildren();
		children.addAll(textField1, textField2, textField3);
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
