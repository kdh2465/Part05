package pack01_javafx.sec07_JavaFX_UIThread.EX02_Async_Task.UsingJavaCode;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
	boolean succeedFlag;
	boolean cancelFlag;
	boolean failFlag;
	int lapCount;
	long min;
	long sec;
	long msec;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		VBox root = new VBox();
		root.setPrefSize(300, 250);
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);
		root.setPadding(new Insets(10));
						
		Label label = new Label("00:00:00");
		label.setStyle("-fx-font-size:30");
		
		
		Button laptime = new Button("LapTime");
		Button reset = new Button("Reset");
		Button start = new Button("Start");		
		Button stop = new Button("Stop");
		laptime.setDisable(true);
		reset.setDisable(true);
		stop.setDisable(true);
		HBox hbox1 = new HBox(laptime, reset, start, stop);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(10);
		start.setMaxWidth(Double.MAX_VALUE);
		HBox.setHgrow(start, Priority.ALWAYS);
		
		Button succeed = new Button("Succeed");		
		Button fail = new Button("Fail");
		Button cancel = new Button("Canceled");
		succeed.setDisable(true);
		fail.setDisable(true);
		cancel.setDisable(true);
		
		HBox hbox2 = new HBox(succeed, fail, cancel);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.setSpacing(10);

		//3개의 버튼 간격 동일하게 맞추기
		succeed.prefWidthProperty().bind(hbox2.widthProperty().divide(3));
		cancel.prefWidthProperty().bind(hbox2.widthProperty().divide(3));
		fail.prefWidthProperty().bind(hbox2.widthProperty().divide(3));
				
		TextArea laptime_ta = new TextArea();

		Task<String> task = new Task<String>() {
			@Override
			protected String call() throws Exception {
				while(true) {					
					while(!stopFlag) {
						try {Thread.sleep(10);} catch (InterruptedException e) {
							return null;
						}						
						//Platform.runLater(()->{
							sec+=(++msec)/100;
							msec=(msec)%100;
							
							min+=sec/60;
							sec=sec%60;
							String str = String.format("%02d:%02d:%02d",min,sec,msec);
							updateValue(str);
							//label.setText(str);
							
							if(succeedFlag) return null; //state: succeed
							if(failFlag) throw new Exception(); //state: fail
							//if(isCancelled()) return null; //state: cancel
							
							if(laptimeFlag) {
								//laptime_ta.appendText("LapCount "+lapCount++ +" :\t"+str+System.lineSeparator());
								updateMessage("LapCount "+lapCount++ +" :\t"+str+System.lineSeparator());								
								laptimeFlag=false;
							}
						//});					
					}
					try {Thread.sleep(1);} catch (InterruptedException e) {						
						return null;
					} //이게 있어야 stopFlag에 반응 (무한 루프에서 interrupt() 수행을 위해서인듯함)								
				}				
			}	
			
			@Override
			protected void succeeded() {				
				laptime_ta.appendText("성공\n");
			}
		
			@Override
			protected void failed() {				
				laptime_ta.appendText("실패\n");
			}
			
			@Override
			protected void cancelled() {				
				laptime_ta.appendText("취소\n");
			}
		};
		
		Thread thread = new Thread(task);
		
		thread.setDaemon(true);
		thread.start();
		
		//label.textProperty().bind(task.valueProperty());
		
		task.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				label.setText(newValue);
			}
		});
		task.messageProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				laptime_ta.appendText(newValue);
			}
		});
				
		start.setOnAction((event)->{			
			stopFlag=false;
			start.setText("reStart");
			laptime.setDisable(false);
			reset.setDisable(true);			
			start.setDisable(true);
			stop.setDisable(false);
			
			succeed.setDisable(false);
			fail.setDisable(false);
			cancel.setDisable(false);

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
			
			succeed.setDisable(true);
			fail.setDisable(true);
			cancel.setDisable(true);
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
		
		succeed.setOnAction((event)->{			
			succeedFlag=true;
			laptime.setDisable(true);
			reset.setDisable(true);
			start.setDisable(true);			
			stop.setDisable(true);
			
			succeed.setDisable(true);
			fail.setDisable(true);
			cancel.setDisable(true);

		});
		fail.setOnAction((event)->{			
			failFlag=true;
			laptime.setDisable(true);
			reset.setDisable(true);
			start.setDisable(true);			
			stop.setDisable(true);
			
			succeed.setDisable(true);
			fail.setDisable(true);
			cancel.setDisable(true);
		});
		cancel.setOnAction((event)->{			
			task.cancel();
			
			laptime.setDisable(true);
			reset.setDisable(false);
			start.setDisable(false);			
			stop.setDisable(true);
			
			succeed.setDisable(true);
			fail.setDisable(true);
			cancel.setDisable(true);
		});
								
		ObservableList<Node> children = root.getChildren();
		children.add(label);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(hbox1);		
		children.add(hbox2);
		children.add(new Separator(Orientation.HORIZONTAL));
		children.add(laptime_ta);		
		 
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
