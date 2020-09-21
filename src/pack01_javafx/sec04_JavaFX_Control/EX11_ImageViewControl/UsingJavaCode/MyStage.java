package pack01_javafx.sec04_JavaFX_Control.EX11_ImageViewControl.UsingJavaCode;

import java.io.File;
import java.io.FileInputStream;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		VBox root = new VBox();
		root.setPrefWidth(250);
		root.setPrefHeight(150);

		//@ImageView=== control은 아니고 장식용
		String url1 = getClass().getResource("..//resources//images//coffee.jpg").toString();
		ImageView imageView1 = new ImageView();
		imageView1.setImage(new Image(url1));
		imageView1.setFitWidth(250);
		imageView1.setPreserveRatio(true); 
			
		//@ 버튼과 레이블의 이미지 뷰 활용
		String url2 = getClass().getResource("..//resources//icons//icon_a.png").toString();
		Menu menu = new Menu("메뉴", new ImageView(url2));
		MenuBar menuBar = new  MenuBar(menu);
		
		String url3 = getClass().getResource("..//resources//icons//icon_b.png").toString();
		Button btn = new Button("버튼", new ImageView(url3));
		
		String url4 = getClass().getResource("..//resources//icons//icon_c.png").toString();
		Label label = new Label("레이블", new ImageView(url4));
			
		ObservableList<Node> children = root.getChildren();
		children.add(menuBar);
		children.add(imageView1);
		children.add(new HBox(btn,label));
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
