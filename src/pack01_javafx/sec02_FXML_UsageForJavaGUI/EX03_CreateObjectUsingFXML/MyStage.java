package pack01_javafx.sec02_FXML_UsageForJavaGUI.EX03_CreateObjectUsingFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#. FXML 파일 로딩
		MyFXML myFXML1=FXMLLoader.load(getClass().getResource("root1.fxml"));
		MyFXML myFXML2=FXMLLoader.load(getClass().getResource("root2.fxml"));
		MyFXML myFXML3=FXMLLoader.load(getClass().getResource("root3.fxml"));
		MyFXML myFXML4=FXMLLoader.load(getClass().getResource("root4.fxml"));
		
		System.out.println(myFXML1.str);
		System.out.println(myFXML2.str);
		System.out.println(myFXML3.str);
		System.out.println(myFXML4.str);		
				
	}
}
