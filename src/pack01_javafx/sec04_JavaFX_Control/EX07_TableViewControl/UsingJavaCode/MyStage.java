package pack01_javafx.sec04_JavaFX_Control.EX07_TableViewControl.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(200);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		root.setPadding(new Insets(10));
		
		//@TableView
		//TableView<Student> tableView = new TableView<>(FXCollections.observableArrayList(new Student("11111111","학생1")));
		TableView<Student> tableView = new TableView<>();
		TableColumn<Student, String> column1 = new TableColumn<>("S-Number");
		column1.setCellValueFactory(new PropertyValueFactory<>("s_num"));
						
		TableColumn<Student, String> column2 = new TableColumn<>("Name");
		column2.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		tableView.getColumns().add(column1);
		tableView.getColumns().add(column2);
		tableView.setTableMenuButtonVisible(false);
		
		tableView.getItems().add(new Student("11111111","학생1"));
		tableView.getItems().add(new Student("22222222","학생2"));
	
		//마지막 데이터까지의 column만 표기 (default = UNCON~ : 데이터가 있는 Column + 1)
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		//데이터가 없을때의 표시 내용
		tableView.setPlaceholder(new Label("데이터가 없습니다."));
		
		ObservableList<Node> children = root.getChildren();
		children.add(tableView);
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
