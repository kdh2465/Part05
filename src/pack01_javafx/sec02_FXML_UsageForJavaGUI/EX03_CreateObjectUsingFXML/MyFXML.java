package pack01_javafx.sec02_FXML_UsageForJavaGUI.EX03_CreateObjectUsingFXML;

import javafx.beans.NamedArg;

public class MyFXML {
	String str;
	public MyFXML() {
		str = "ù��° ������� ��ü ����";
	}
	public MyFXML(@NamedArg("str") String str){
		this.str = str;
	}
	public static MyFXML valueOf(String str) {
		return new MyFXML(str);
	}
	public static MyFXML getInstance() {
		return new MyFXML("�׹�° ������� ��ü ����");
	}
}
