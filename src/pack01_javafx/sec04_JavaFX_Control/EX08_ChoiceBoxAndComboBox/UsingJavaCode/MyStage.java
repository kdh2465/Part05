package pack01_javafx.sec04_JavaFX_Control.EX08_ChoiceBoxAndComboBox.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#.
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(200);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		root.setPadding(new Insets(10));
		root.setSpacing(30);
		
		//@ChoiceBox
		ChoiceBox<String> choiceBox = new ChoiceBox<>();
		
		ObservableList<String> oblist1 = FXCollections.observableArrayList();
		for(int i=0; i<20; i++)
			oblist1.add("choice "+i);
		choiceBox.setItems(oblist1);
		choiceBox.setValue("choice 0"); //���ð� ����
		
		//getItem�� �̿��� ��� (�ڵ忡���� �̰� �������� getItems�� setItems�� ��� �־� fxml������ ���� ���·θ� ����
		//choiceBox.getItems().addAll("choice 1", "choice 2", "choice 3");

		//@ComboBox
		ComboBox<String> comboBox = new ComboBox<>();
				
		ObservableList<String> oblist2 = FXCollections.observableArrayList();
		for(int i=0; i<20; i++)
			oblist2.add("combo " + i);
		comboBox.setItems(oblist2);
		comboBox.setValue("combo 0");
		comboBox.setPlaceholder(new Label("������ ����"));
		comboBox.setVisibleRowCount(3);
		
		//getItem�� �̿��� ��� (�ڵ忡���� �̰� �������� getItems�� setItems�� ��� �־� fxml������ ���� ���·θ� ����
		//comboBox.getItems().addAll("combo 1", "combo 2", "combo 3");
				
		ObservableList<Node> children = root.getChildren();
		children.add(choiceBox);
		children.add(comboBox);
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
