package pack01_javafx.sec07_JavaFX_UIThread.EX01_PlatformRunLater.UsingFXML;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class MyController implements Initializable{
	@FXML private Label label;
	@FXML private Button laptime;
	@FXML private Button reset;
	@FXML private Button start;
	@FXML private Button stop;
	@FXML private TextArea laptime_ta;
	
	boolean stopFlag=true; 
	boolean laptimeFlag;
	int lapCount;
	long min;
	long sec;
	long msec;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		Thread thread = new Thread(()->{
			
			while(true) {				
				while(!stopFlag) {
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
	}
	
}
