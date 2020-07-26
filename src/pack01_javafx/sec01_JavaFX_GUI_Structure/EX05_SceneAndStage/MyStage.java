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
	//#1. �Ű������� Stage ����
	public void start(Stage primaryStage) throws Exception {
		
		VBox parent = new VBox();
		parent.setPrefWidth(200);
		parent.setPrefHeight(100);
		
		//#1. Scene ������		
		//Scene scene = new Scene(parent,Color.GREEN);
		Scene scene = new Scene(parent,300,200,Color.BLUE);
		//Scene scene = new Scene(parent,300,200,Color.RED);
				
		//#2. Stage �޼��� 1
		primaryStage.setScene(scene); //Scene����
		primaryStage.setTitle("Scene & Stage"); //Ÿ��Ʋ����
		primaryStage.setX(400); //Stage�� x��ǥ
		primaryStage.setY(400); //Stage�� Y��ǥ
		primaryStage.setMaxWidth(800); //Stage �ִ��� ����
		primaryStage.setResizable(true); //ũ�⺯�濩������(����Ʈ true)
		primaryStage.show(); //ȭ�鿡 ����		
		//primaryStage.setWidth(400); //Stage�� ������
		//primaryStage.setHeight(300); //Stage�� ���̼���
		System.out.println("Parent : "+parent.getWidth()+" * "+parent.getHeight());
		System.out.println("Scene  : "+scene.getWidth()+" * "+scene.getHeight());
		System.out.println("Stage  : "+primaryStage.getWidth()+" * "+primaryStage.getHeight());
		
		
		
		
		
				
		/* �����̳� ���� �������
		//����������� (#1. Background��ü/#2. Style)
		//parent.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(10))));
		//parent.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		//parent.setStyle("-fx-background-color: #ff000088");
		//parent.setStyle("-fx-background-color: rgba(255,0,0,0.0)");
		parent.setBackground(new Background(new BackgroundFill(Color.BLUE,null,new Insets(10))));
		*/
		
		/* ��Ʈ�� ���� �������
		//����������� (#1. Background��ü/#2. Style)
		//control.setBackground(new Background(new BackgroundFill(Color.RED,new CornerRadii(5),new Insets(10))));
		//control.setBackground(new Background(new BackgroundFill(Color.RED,null,null)));
		//control.setStyle("-fx-background-color: #ff000088");
		//control.setStyle("-fx-background-color: rgba(255,0,0,0.0)");
		control.setBackground(new Background(new BackgroundFill(Color.BLUE,null,new Insets(10))));
		
		�Ǵ�
		control.setBackground(Color.RED);
		*/
		
//
//		//Scene scene = new Scene(parent);
//		//#1. Scene �޼���
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
//		primaryStage.setTitle("Stage���� UI ���� �� ��ġ�ϱ�");
//		System.out.println(primaryStage.getWidth());
//		System.out.println(primaryStage.getHeight());
//		primaryStage.show();
//		
//		System.out.println(primaryStage.getWidth());
//		System.out.println(primaryStage.getHeight());
//		
//		//#1. Scene �޼���
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
//		//#6. Stage�� Scene �߰� 
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
