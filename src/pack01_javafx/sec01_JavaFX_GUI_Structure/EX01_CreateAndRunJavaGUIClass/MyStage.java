package pack01_javafx.sec01_JavaFX_GUI_Structure.EX01_CreateAndRunJavaGUIClass;
import javafx.application.Application;
import javafx.stage.Stage;

public class MyStage extends Application {
	
	public MyStage() {
		System.out.println("MyStage() : "+Thread.currentThread().getName());
		//MyStage() : JavaFX Application Thread-->UI 생성 변경은 이 Thread에서만 사용 
	}
	@Override
	public void init() throws Exception {
		super.init();
		System.out.println("init() : "+Thread.currentThread().getName());
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.show();
		System.out.println("start() : "+Thread.currentThread().getName());
	}
	@Override
	public void stop() throws Exception {
		//super.stop();
		System.out.println("stop() : "+Thread.currentThread().getName());
	}

}
