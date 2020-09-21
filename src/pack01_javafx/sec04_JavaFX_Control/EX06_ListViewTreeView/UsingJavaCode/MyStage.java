package pack01_javafx.sec04_JavaFX_Control.EX06_ListViewTreeView.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#.ListView / TreeView
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(200);
		root.setAlignment(Pos.CENTER);
		root.setFillHeight(false);
		root.setPadding(new Insets(10));
		
		//@ListView
		ListView<String> listView = new ListView<>();
		//listView.setOrientation(Orientation.HORIZONTAL);
		
		/*코드에서만 가능한 방법 getchildren과는 다르게 setItems 메서드가 있어서 fxml에서 <items>는 setItems를 의미함
		list.getItems().addAll("ListItem1", "ListItem2", "ListItem3");
		*/
		ObservableList<String> obervableList = FXCollections.observableArrayList();
		obervableList.add("ListItem1");
		obervableList.add("ListItem2");
		obervableList.add("ListItem3");
				
		listView.setItems(obervableList);

		
		//@TreeView
		TreeView<String> treeView = new TreeView<>();
		
		TreeItem<String> rootItem = new TreeItem<>("rootItem");
		TreeItem<String> subItem1 = new TreeItem<>("subItem 1");
		subItem1.getChildren().add(new TreeItem<>("Item 11"));
		subItem1.getChildren().add(new TreeItem<>("Item 12"));
		TreeItem<String> subItem2 = new TreeItem<>("subItem 2");
		subItem2.getChildren().add(new TreeItem<>("Item 21"));
		subItem2.getChildren().add(new TreeItem<>("Item 22"));		
		
		rootItem.getChildren().addAll(subItem1, subItem2);
		
		rootItem.setExpanded(true);
		subItem1.setExpanded(true);
		subItem2.setExpanded(true);
				
		treeView.setRoot(rootItem);		
				
		ObservableList<Node> children = root.getChildren();
		children.add(listView);
		children.add(treeView);
				
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
