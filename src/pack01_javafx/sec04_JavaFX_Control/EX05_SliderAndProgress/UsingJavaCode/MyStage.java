package pack01_javafx.sec04_JavaFX_Control.EX05_SliderAndProgress.UsingJavaCode;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. ��Ʈ �����̳� �� �ڽ� ��� ����
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(150);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		root.setPadding(new Insets(10));
		
		//@setProgress���� �������� ������ �ִϸ��̼����� ����		
		ProgressIndicator pIndicator = new ProgressIndicator(0.1);
		ProgressBar pBar1 = new ProgressBar(0.9);
		
		Slider slider1 = new Slider(100, 200, 100);		
		slider1.setValue(150);
		slider1.setShowTickLabels(true);
		slider1.setShowTickMarks(true);
		slider1.setMajorTickUnit(25.0);//tick ����
		
		Label label = new Label("test");
		
		VBox vbox = new VBox(pIndicator, label, pBar1, slider1);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		
		ProgressBar pBar2 = new ProgressBar(0.3);
		pBar2.setStyle("-fx-accent: red");
		pBar2.setRotate(-90);
		
				
		Slider slider2 = new Slider();		
		slider2.setValue(50);
		slider2.setShowTickLabels(true);
		slider2.setShowTickMarks(true);
		slider2.setMajorTickUnit(25.0);//tick ����
		slider2.setOrientation(Orientation.VERTICAL);
		//slider2.setRotate(-90);//orientation�� ���̰� ����
		
		
		Thread t = new Thread(()->{	
			System.out.println(Thread.currentThread().getName().toString());
			for(int i=1; i<=100; i++) {		
				final double k=i;
				Platform.runLater(()->{	//�� JavaFX Application Thread�� sleep�� ���� �ʰ� ������ �����带 �����Ͽ� sleep ����
					pIndicator.setProgress(k/100.0);
					pBar1.setProgress(k/100.0);
					pBar2.setProgress(k/100.0);	//progress�� �Ϲ� �����忡���� ���������� ���о��� UI ������JavaFX Application Threadó���� �ٶ���
					slider1.setValue(k+100);
					slider2.setValue(k);					
					label.setText(k+"");		//label�� �ݵ�� JavaFX Application Thread������ ���� ����			
				});
				try {Thread.sleep(200);} catch (InterruptedException e) {} // �߿�. sleep�� �ܺ� �����忡�� ������ UI���游 Java Application Thread���� ó�� (sleep�� runlater�� �ѱ�� �� �ð����� UI lock)  
			}
		});
		t.setDaemon(true);
		//t.start(); 0.2�ʴ� 1%�� ����
		
								
		ObservableList<Node> children = root.getChildren();
		children.add(vbox);
		children.add(pBar2);
		children.add(slider2);		
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
