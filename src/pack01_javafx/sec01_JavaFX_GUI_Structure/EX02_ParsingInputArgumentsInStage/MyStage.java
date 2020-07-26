package pack01_javafx.sec01_JavaFX_GUI_Structure.EX02_ParsingInputArgumentsInStage;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.stage.Stage;

public class MyStage extends Application {
	
	@Override
	public void init() throws Exception {
		Parameters params = getParameters(); //Input Arguments 가져오기
		
		//#1. key-value 쌍을 하나의 String으로 가져오기
		List<String> args1 = params.getRaw();
		if(args1.size()==0) { System.out.println("arguments 없음"); System.exit(0); }
		System.out.println(args1);
		
		//#2. key-value 쌍으로 구성된 데이터를 Map Entity로 가져오기
		Map<String, String> args2 = params.getNamed();
		if(args2.size()==0) { System.out.println("arguments 없음"); System.exit(0); }
		System.out.println(args2);
		
		//#3. key-value 쌍으로 구성되지 않은 데이터를 list로 가져오기
		List<String> args3 = params.getUnnamed();
		if(args3.size()==0) { System.out.println("arguments 없음"); System.exit(0); }
		System.out.println(args3);
				
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		//UI 처리부분
	}
	
}
