package pack01_javafx.sec04_JavaFX_Control.EX04_TextInputControl.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		VBox root = new VBox();
		root.setPrefSize(250, 250);		
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		
		Label label1 = new Label("TextField");
		TextField textField = new TextField();
		textField.setPromptText("TextField");
		VBox vbox1 = new VBox(label1, textField);
		vbox1.setSpacing(5);
		
		Label label2 = new Label("PasswordField");
		PasswordField passwordField = new PasswordField();
		VBox vbox2 = new VBox(label2, passwordField);
		vbox2.setSpacing(5);
				
		Label label3 = new Label("TextArea");
		TextArea textArea = new TextArea();
		textArea.setPrefRowCount(4);
		textArea.setPrefColumnCount(10);
		VBox vbox3 = new VBox(label3, textArea);
		vbox3.setSpacing(5);
		
		ObservableList<Node> children = root.getChildren();
		children.addAll(vbox1,vbox2,vbox3);
		
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
