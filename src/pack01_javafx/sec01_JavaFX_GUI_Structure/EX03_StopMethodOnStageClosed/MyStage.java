package pack01_javafx.sec01_JavaFX_GUI_Structure.EX03_StopMethodOnStageClosed;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyStage extends Application {
		
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
	}
	
	@Override
	public void stop() throws Exception {
		System.out.println("리소스 자원을 해제합니다.");
		System.out.println("프로그램을 종료합니다.");		
	}
	
}
