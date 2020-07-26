package pack01_javafx.sec04_JavaFX_Control.EX10_MenuBarContextMenu.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.stage.Stage;


public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		VBox root = new VBox();
		root.setPrefWidth(400);
		root.setPrefHeight(150);

		//@MenuBar===
		MenuBar menuBar = new MenuBar();
		Menu menu1 = new Menu("Menu");
		Menu menu2 = new Menu("CheckMenuItem");
		Menu menu3 = new Menu("RadioMenuItem");
		Menu menu4 = new Menu("SubMenu");
				
		menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);
		
		//@1. separated menu
		MenuItem menuItem11 = new MenuItem("menuItem11");
		MenuItem menuItem12 = new MenuItem("menuItem12");
		SeparatorMenuItem separator = new SeparatorMenuItem();
		MenuItem menuItem13 = new MenuItem("menuItem13");
		menu1.getItems().addAll(menuItem11, menuItem12, separator, menuItem13);
		
		//@2. checkmenuitem
		CheckMenuItem menuItem21 = new CheckMenuItem("menuItem21");
		CheckMenuItem menuItem22 = new CheckMenuItem("menuItem22");
		CheckMenuItem menuItem23 = new CheckMenuItem("menuItem23");
		menu2.getItems().addAll(menuItem21, menuItem22, menuItem23);


		//@3. radiomenuitem				
		RadioMenuItem menuItem31 = new RadioMenuItem("menuItem31");
		RadioMenuItem menuItem32 = new RadioMenuItem("menuItem32");
		RadioMenuItem menuItem33 = new RadioMenuItem("menuItem33");
		
		ToggleGroup toggleGroup = new ToggleGroup();
		toggleGroup.getToggles().add(menuItem31);
		toggleGroup.getToggles().add(menuItem32);
		toggleGroup.getToggles().add(menuItem33);

		menu3.getItems().addAll(menuItem31, menuItem32, menuItem33);
		
		//@4. submenu
		MenuItem menuItem41 = new MenuItem("menuItem41");
		MenuItem menuItem42 = new MenuItem("menuItem42");
		Menu subMenu = new Menu("submenu");
		MenuItem subMenuItem1 = new MenuItem("subMenuItem1");
		MenuItem subMenuItem2 = new MenuItem("subMenuItem2");
		subMenu.getItems().addAll(subMenuItem1, subMenuItem2);

		menu4.getItems().addAll(menuItem41, menuItem42, subMenu);
					
		//@ContextMenu===
		ContextMenu contextMenu = new ContextMenu();
        MenuItem menuItem1 = new MenuItem("context menu 1");
        MenuItem menuItem2 = new MenuItem("context menu 2");
        MenuItem menuItem3 = new MenuItem("context menu 3");
        contextMenu.getItems().addAll(menuItem1,menuItem2,menuItem3);
        
        TextArea textArea = new TextArea();
        textArea.setContextMenu(contextMenu);
        textArea.setText("컨텍스트메뉴가 포함된 TextArea");
    
        VBox.setVgrow(textArea, Priority.ALWAYS);				        
		
		ObservableList<Node> children = root.getChildren();
		children.add(menuBar);
		children.add(textArea);
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
