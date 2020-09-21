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
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(150);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		root.setPadding(new Insets(10));
		
		//@setProgress값을 설정하지 않으면 애니메이션으로 동작		
		ProgressIndicator pIndicator = new ProgressIndicator(0.1);
		ProgressBar pBar1 = new ProgressBar(0.9);
		
		Slider slider1 = new Slider(100, 200, 100);		
		slider1.setValue(150);
		slider1.setShowTickLabels(true);
		slider1.setShowTickMarks(true);
		slider1.setMajorTickUnit(25.0);//tick 간격
		
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
		slider2.setMajorTickUnit(25.0);//tick 간격
		slider2.setOrientation(Orientation.VERTICAL);
		//slider2.setRotate(-90);//orientation과 차이가 있음
		
		
		Thread t = new Thread(()->{	
			System.out.println(Thread.currentThread().getName().toString());
			for(int i=1; i<=100; i++) {		
				final double k=i;
				Platform.runLater(()->{	//즉 JavaFX Application Thread가 sleep이 되지 않게 별도의 쓰레드를 생성하여 sleep 진행
					pIndicator.setProgress(k/100.0);
					pBar1.setProgress(k/100.0);
					pBar2.setProgress(k/100.0);	//progress는 일반 쓰레드에서도 가능하지만 구분없이 UI 변경은JavaFX Application Thread처리가 바람직
					slider1.setValue(k+100);
					slider2.setValue(k);					
					label.setText(k+"");		//label은 반드시 JavaFX Application Thread에서만 수정 가능			
				});
				try {Thread.sleep(200);} catch (InterruptedException e) {} // 중요. sleep은 외부 쓰레드에서 돌리고 UI변경만 Java Application Thread에서 처리 (sleep을 runlater로 넘기면 그 시간동안 UI lock)  
			}
		});
		t.setDaemon(true);
		//t.start(); 0.2초당 1%씩 증가
		
								
		ObservableList<Node> children = root.getChildren();
		children.add(vbox);
		children.add(pBar2);
		children.add(slider2);		
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
