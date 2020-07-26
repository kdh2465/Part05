package pack01_javafx.sec05_JavaFX_EventHandling.EX03_PropertyChangeListening.UsingJavaCode;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MyStage extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefWidth(450);
		root.setPrefHeight(150);
		root.setSpacing(10);
		root.setPadding(new Insets(10));
		
		Label label1 = new Label("글자수 : 0");
		TextField textField = new TextField();
		//@textField property
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				System.out.println("OldValue = "+oldValue +"  newValue = "+newValue);
				label1.setText("글자수 : "+newValue.length());
			}
		});	
		
		VBox vbox1 = new VBox(textField,label1);
		vbox1.setAlignment(Pos.CENTER);
		vbox1.setPrefWidth(150);
		vbox1.setSpacing(10);

		Label label2 = new Label("체크 해제");
		CheckBox checkBox = new CheckBox("체크박스");
		//@checkBox property
		checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				
				// TODO Auto-generated method stub
				if(newValue) {
					label2.setText("체크 선택");
				} else {
					label2.setText("체크 해제");
				}
			}
		});
		VBox vbox2 = new VBox(checkBox,label2);
		vbox2.setAlignment(Pos.CENTER);
		vbox2.setPrefWidth(150);
		vbox2.setSpacing(10);

		Label label3 = new Label();
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.getItems().addAll("item1","item2","item3");
		//@comboBox property
		comboBox.valueProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				label3.setText(newValue+" 선택");				
			}			
		});
		comboBox.setValue("item1");
		VBox vbox3 = new VBox(comboBox,label3);
		vbox3.setAlignment(Pos.CENTER);
		vbox3.setPrefWidth(150);
		vbox3.setSpacing(10);
				
		ObservableList<Node> children = root.getChildren();
		children.add(vbox1);
		children.add(new Separator(Orientation.VERTICAL));
		children.add(vbox2);
		children.add(new Separator(Orientation.VERTICAL));
		children.add(vbox3);		
		 
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
