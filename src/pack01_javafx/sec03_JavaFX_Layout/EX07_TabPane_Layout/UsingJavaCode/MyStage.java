package pack01_javafx.sec03_JavaFX_Layout.EX07_TabPane_Layout.UsingJavaCode;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		TabPane root = new TabPane();
		root.setPrefSize(200, 100);
		
		Label label1 = new Label("첫번째 Tab Content");
		Label label2 = new Label("두번째 Tab Content");
		Label label3 = new Label("세번째 Tab Content");
		
		label1.setAlignment(Pos.CENTER);		
		label1.setMaxWidth(Double.MAX_VALUE);
		label1.setMaxHeight(Double.MAX_VALUE);
		
		label2.setAlignment(Pos.CENTER);		
		label2.setMaxWidth(Double.MAX_VALUE);
		label2.setMaxHeight(Double.MAX_VALUE);
		
		label3.setAlignment(Pos.CENTER);		
		label3.setMaxWidth(Double.MAX_VALUE);
		label3.setMaxHeight(Double.MAX_VALUE);
						
		Tab tab1 = new Tab("Tab1", label1);
		Tab tab2 = new Tab("Tab2", label2);
		Tab tab3 = new Tab("Tab3", label3);
				
		root.getTabs().add(tab1);
		root.getTabs().add(tab2);
		root.getTabs().add(tab3);
																
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
