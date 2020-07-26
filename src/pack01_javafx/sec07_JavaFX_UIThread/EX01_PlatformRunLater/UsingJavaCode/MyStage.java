package pack01_javafx.sec07_JavaFX_UIThread.EX01_PlatformRunLater.UsingJavaCode;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	
	boolean stopFlag=true; 
	boolean laptimeFlag;
	int lapCount;
	long min;
	long sec;
	long msec;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		VBox root = new VBox();
		root.setPrefSize(300,250);
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
						
		Label label = new Label("00:00:00");
		label.setStyle("-fx-font-size:30");
		
		
		Button laptime = new Button("LapTime");
		Button reset = new Button("Reset");
		Button start = new Button("Start");		
		Button stop = new Button("Stop");
		HBox hbox = new HBox(laptime, reset, start, stop);
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(10);
		start.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(start, Priority.ALWAYS);

		laptime.setDisable(true);
		reset.setDisable(true);
		stop.setDisable(true);
		
		TextArea laptime_ta = new TextArea();
		
		Thread thread = new Thread(()->{
			
			while(true) {
				
				if(!stopFlag) {
					try {Thread.sleep(10);} catch (InterruptedException e) {}
					
					Platform.runLater(()->{
						sec+=(++msec)/100;
						msec=(msec)%100;
						
						min+=sec/60;
						sec=sec%60;
						String str = String.format("%02d:%02d:%02d",min,sec,msec);
						label.setText(str);
						
						if(laptimeFlag) {
							laptime_ta.appendText("LapCount "+lapCount++ +" :\t"+str+System.lineSeparator());
							laptimeFlag=false;
						}
					});					
				}
				try {Thread.sleep(1);} catch (InterruptedException e) {} //이게 있어야 stopFlag에 반응
			}
		});
		thread.setDaemon(true);
		thread.start();
		
		
		start.setOnAction((event)->{			
			stopFlag=false;
			start.setText("reStart");
			laptime.setDisable(false);
			reset.setDisable(true);			
			start.setDisable(true);
			stop.setDisable(false);
		});
		
		stop.setOnAction((event)->{
			stopFlag=true;
			if(min!=0 || sec!=0 || msec!=0) {
				start.setText("reStart");
			}
			laptime.setDisable(true);
			reset.setDisable(false);
			start.setDisable(false);			
			stop.setDisable(true);
		});
		
		reset.setOnAction((event)->{
			lapCount=0;
			stopFlag=true;
			min=sec=msec=0;
			String str = String.format("%02d:%02d:%02d",min,sec,msec);
			label.setText(str);
			start.setText("Start");
			reset.setDisable(true);
			
			laptime_ta.setText("");
			
		});
		
		laptime.setOnAction((event)->{			
			laptimeFlag=true;			
		});
				
								
		ObservableList<Node> children = root.getChildren();
		children.add(label);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(hbox);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(laptime_ta);		
		 
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
