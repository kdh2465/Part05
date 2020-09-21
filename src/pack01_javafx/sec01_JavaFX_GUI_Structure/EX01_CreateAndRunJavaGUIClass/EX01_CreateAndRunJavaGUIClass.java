package pack01_javafx.sec01_JavaFX_GUI_Structure.EX01_CreateAndRunJavaGUIClass;

public class EX01_CreateAndRunJavaGUIClass {
	public static void main(String[] args) {

		//이렇게 별도로 구성할 수 있으나 반드시 Application 클래스는 public 이어야 함
        //main 함수가 포함된 클래스가 직접 Application 클래스를 호출하는 경우 launch(args)로만 가능
		MyStage.launch(MyStage.class, args);
		
	}
}
