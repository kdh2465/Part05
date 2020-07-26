package pack01_javafx.sec05_JavaFX_EventHandling.EX01_EventFilterVsHandler;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class MyStage extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(150);
		
		Image image = new Image(getClass().getResource("image/coffee.jpg").toString());		
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(250);
		imageView.setPreserveRatio(true); 
				
		ObservableList<Node> children = root.getChildren();
		children.add(imageView);
		 
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
		//EventFilter vs. EventHandler		
		imageView.addEventFilter(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("imageview eventFilter");});		
		root.addEventFilter(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("pane eventFilter"); });
		scene.addEventFilter(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("scene eventFilter");});		
		primaryStage.addEventFilter(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("stage eventFilter");});
		imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("imageview eventHandler");});
		root.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("pane eventHandler"); event.consume();});
		scene.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("scene eventHandler");});
		primaryStage.addEventHandler(MouseEvent.MOUSE_CLICKED, (event)->{System.out.println("stage eventHandler");});

	}
}
