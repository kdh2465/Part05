package pack01_javafx.sec06_javafx_css.EX01_JavaFX_AttributesAndValues.UsingJavaCode;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class MyStage extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		VBox root = new VBox();
		root.setPrefSize(400,350);		
		root.setSpacing(10);
		//root.setPadding(new Insets(10));
		
		//배경색 (background)
		VBox vbox11 = new VBox();
		vbox11.setPrefSize(100, 100);		
		VBox vbox12 = new VBox();
		vbox12.setPrefSize(100, 100);
		VBox vbox13 = new VBox();
		vbox13.setPrefSize(100, 100);	
		VBox vbox14 = new VBox();
		vbox14.setPrefSize(100, 100);	
		VBox vbox15 = new VBox();
		vbox15.setPrefSize(400, 150);
		
		HBox hbox1 = new HBox(vbox11, new Separator(Orientation.VERTICAL), vbox12,  new Separator(Orientation.VERTICAL), vbox13, new Separator(Orientation.VERTICAL), vbox14);
		
		//테두리 (background)		
		VBox vbox21 = new VBox();
		vbox21.setPrefSize(100, 100);		
		VBox vbox22 = new VBox();
		vbox22.setPrefSize(100, 100);
		VBox vbox23 = new VBox();
		vbox23.setPrefSize(100, 100);	
		VBox vbox24 = new VBox();
		vbox24.setPrefSize(100, 100);
		
		
		HBox hbox2 = new HBox(vbox21, new Separator(Orientation.VERTICAL), vbox22,  new Separator(Orientation.VERTICAL), vbox23, new Separator(Orientation.VERTICAL), vbox24);
				
		root.getChildren().addAll(hbox1, vbox15, hbox2);
		
		//Style 지정
		//@background()
		vbox11.setStyle("-fx-background-color:green, rgba(0,255,255,0)");
		vbox12.setStyle("-fx-background-color:red;-fx-background-radius:10; -fx-background-insets:10");
		vbox13.setStyle("-fx-background-color:blue;-fx-background-radius:20; -fx-background-insets:20,10,20,10");		
		vbox14.setStyle("-fx-background-color:red, green; -fx-background-radius:10,20; -fx-background-insets:10,20");		
		
		String imageURL = getClass().getResource("image/coffee_clip.png").toString();		
		vbox15.setStyle("-fx-background-image:url("+imageURL+")");
		
		//@border
		vbox21.setStyle("-fx-border-color:red, green; -fx-border-width:3");
		vbox22.setStyle("-fx-border-color:red;-fx-border-radius:10; -fx-border-insets:10");
		vbox23.setStyle("-fx-border-color:blue;-fx-border-radius:20; -fx-border-insets:20,10,20,10");		
		vbox24.setStyle("-fx-border-color:red, green; -fx-border-radius:10,20; -fx-border-insets:10,20");
				
		//#2. Scene 설정
		Scene scene = new Scene(root);

		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
