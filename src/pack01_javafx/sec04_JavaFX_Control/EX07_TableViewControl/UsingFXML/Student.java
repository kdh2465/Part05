package pack01_javafx.sec04_JavaFX_Control.EX07_TableViewControl.UsingFXML;

import javafx.beans.NamedArg;

public class Student {
	private String s_num;
	private String name;
	
	public Student(@NamedArg("s_num") String s_num, @NamedArg("name") String name) {
		this.s_num=s_num;
		this.name=name;
	}
	
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
