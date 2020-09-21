package pack01_javafx.sec01_JavaFX_GUI_Structure.EX05_SceneAndStage;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MyStage extends Application {
		
	@Override
	//#1. 매개변수로 Stage 전달
	public void start(Stage primaryStage) throws Exception {
		
		VBox parent = new VBox();
		parent.setPrefWidth(200);
		parent.setPrefHeight(100);
		
		//#1. Scene 생성자		
		//Scene scene = new Scene(parent,Color.GREEN);
		Scene scene = new Scene(parent,300,200,Color.BLUE);
		//Scene scene = new Scene(parent,300,200,Color.RED);
				
		//#2. Stage 메서드 1
		primaryStage.setScene(scene); //Scene지정
		primaryStage.setTitle("Scene & Stage"); //타이틀지정
		primaryStage.setX(400); //Stage의 x좌표
		primaryStage.setY(400); //Stage의 Y좌표
		primaryStage.setMaxWidth(800); //Stage 최대폭 지정
		primaryStage.setResizable(true); //크기변경여부지정(디폴트 true)
		primaryStage.show(); //화면에 띄우기		
		//primaryStage.setWidth(400); //Stage의 폭설정
		//primaryStage.setHeight(300); //Stage의 높이설정
		System.out.println("Parent : "+parent.getWidth()+" * "+parent.getHeight());
		System.out.println("Scene  : "+scene.getWidth()+" * "+scene.getHeight());
		System.out.println("Stage  : "+primaryStage.getWidth()+" * "+primaryStage.getHeight());
		
		
		
		
		
				
		/* 컨테이너 배경색 지정방법
		//배경색지정방법 (#1. Background객체/#2. Style)
		//parent.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(10))));
		//parent.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		//parent.setStyle("-fx-background-color: #ff000088");
		//parent.setStyle("-fx-background-color: rgba(255,0,0,0.0)");
		parent.setBackground(new Background(new BackgroundFill(Color.BLUE,null,new Insets(10))));
		*/
		
		/* 컨트롤 배경색 지정방법
		//배경색지정방법 (#1. Background객체/#2. Style)
		//control.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(10))));
		//control.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		//control.setStyle("-fx-background-color: #ff000088");
		//control.setStyle("-fx-background-color: rgba(255,0,0,0.0)");
		control.setBackground(new Background(new BackgroundFill(Color.BLUE,null,new Insets(10))));
		
		또는
		control.setBackground(Color.RED);
		*/
		
//
//		//Scene scene = new Scene(parent);
//		//#1. Scene 메서드
//		System.out.println(parent.getWidth());
//		System.out.println(scene.getHeight());
//		
//		Color c;
//		
//		
//		
//		primaryStage.setScene(scene);
//		System.out.println(scene.getWidth());
//		System.out.println(scene.getHeight());
//		primaryStage.setTitle("Stage내의 UI 구성 및 배치하기");
//		System.out.println(primaryStage.getWidth());
//		System.out.println(primaryStage.getHeight());
//		primaryStage.show();
//		
//		System.out.println(primaryStage.getWidth());
//		System.out.println(primaryStage.getHeight());
//		
//		//#1. Scene 메서드
//				System.out.println(parent.getWidth());
//				System.out.println(scene.getHeight());
//		
//		Thread t = new Thread(()->{
//			for(int i=0; i<100; i++) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//
//				e.printStackTrace();
//			}
//			if(i%2==0) {
//				scene.setRoot(parent);	
//				//primaryStage.show();
//			}
//			else {
//				scene.setRoot(parent2);
//				//primaryStage.show();
//				
//			}		
//			}
//		});
//		t.start();
//		
//		//#6. Stage에 Scene 추가 
//		for(int i=0; i<10; i++) {
//			Thread.sleep(1000);
//			if(i%2==0) {
//				scene.setRoot(parent);			
//			}
//			else {
//				scene.setRoot(parent2);
//			}			
//		}
//		
//		
//		System.out.println(primaryStage.getX());
//		System.out.println(primaryStage.getY());
//		
//		primaryStage.setX(100);
//		primaryStage.setY(100);
//		
//		System.out.println(primaryStage.getX());
//		System.out.println(primaryStage.getY());
//		
//		
//		System.out.println(primaryStage.getWidth());
//		System.out.println(primaryStage.getHeight());
//		
//		primaryStage.setWidth(200);
//		primaryStage.setHeight(100);
//		
//		System.out.println(primaryStage.getWidth());
//		System.out.println(primaryStage.getHeight());
//		
//		primaryStage.setMaxWidth(500);
//		primaryStage.setMinWidth(150);
//		
//		//primaryStage.setFullScreen(true);
//		//primaryStage.setIconified(true);
//		primaryStage.setMaximized(true);
//		//primaryStage.setFullScreen(true);
//		
//		primaryStage.setResizable(false);
//		
	}	
}
