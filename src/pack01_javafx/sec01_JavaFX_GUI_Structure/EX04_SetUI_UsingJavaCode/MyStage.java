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
	//#1. 매개변수로 Stage 전달
	public void start(Stage primaryStage) throws Exception {
		
		//#2. root 컨테이너 생성
		VBox parent = new VBox();
		parent.setPrefWidth(200);
		parent.setPrefHeight(100);
		parent.setSpacing(20);
		parent.setAlignment(Pos.CENTER);
		
		//#3. GUI 구성요소 생성		
		Label lb = new Label("레이블");
		lb.setFont(new Font(20));				
		Button btn = new Button("버튼");
		
		//#4. GUI 구성요소 --> Root Parent에 추가		
		ObservableList<Node> ol = parent.getChildren();
		ol.add(lb);
		ol.add(btn);
		
		//#5. Scene 객체 생성시 생성자 매개변수로 root parent 전달
		Scene scene = new Scene(parent);
		
		//#6. Stage에 Scene 추가 
		primaryStage.setScene(scene);
		primaryStage.setTitle("Stage내의 UI 구성 및 배치하기");
		primaryStage.show();
		
	}	
}
