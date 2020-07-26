package pack01_javafx.sec07_JavaFX_UIThread.EX03_Async_Service.UsingFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class MyController implements Initializable{
	@FXML private Label label;
	@FXML private Button laptime;
	@FXML private Button reset;
	@FXML private Button start;
	@FXML private Button stop;
	
	@FXML private Button succeed;
	@FXML private Button fail;
	@FXML private Button cancel;	
	@FXML private HBox hbox2;
	
	@FXML private TextArea laptime_ta;
	
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
	public void initialize(URL location, ResourceBundle resources) {
		
		succeed.prefWidthProperty().bind(hbox2.widthProperty().divide(3));		
		fail.prefWidthProperty().bind(hbox2.widthProperty().divide(3));
		cancel.prefWidthProperty().bind(hbox2.widthProperty().divide(3));
		
		Service<String> myService = new Service<String>() {

			@Override
			protected Task<String> createTask() {
				Task<String> task = new Task<String>() {
					@Override
					protected String call() throws Exception {
						while(true) {					
							while(!stopFlag) {
								try {Thread.sleep(10);} catch (InterruptedException e) { return null; }						

								sec+=(++msec)/100;
								msec=(msec)%100;
								
								min+=sec/60;
								sec=sec%60;
								String str = String.format("%02d:%02d:%02d",min,sec,msec);
								updateValue(str);
								
								if(succeedFlag) return null; //state: succeed
								if(failFlag) throw new Exception(); //state: fail
																
								if(laptimeFlag) {								
									updateMessage("LapCount "+lapCount++ +" :\t"+str+System.lineSeparator());								
									laptimeFlag=false;
								}					
							}
							try {Thread.sleep(1);} catch (InterruptedException e) {	return null; } //이게 있어야 stopFlag에 반응 (무한 루프에서 interrupt() 수행을 위해서인듯함)								
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
				task.valueProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {						
						label.setText(newValue);
					}
				});
				task.messageProperty().addListener(new ChangeListener<String>() {
					@Override
					public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {						
						laptime_ta.appendText(newValue);
					}
				});

				return task;
			}
			
			
		};
		
		myService.start();
		
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
			myService.cancel();
			
			laptime.setDisable(true);
			reset.setDisable(false);
			start.setDisable(false);			
			stop.setDisable(true);
			
			succeed.setDisable(true);
			fail.setDisable(true);
			cancel.setDisable(true);
		});
		
		
	}
	
}
