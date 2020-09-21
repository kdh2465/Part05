package pack01_javafx.sec04_JavaFX_Control.EX12_FileChooserDirectoryChooser.UsingJavaCode;

import java.io.File;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MyStage extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//#1. 루트 컨테이너 및 자식 노드 구성
		HBox root = new HBox();
		root.setPrefWidth(250);
		root.setPrefHeight(100);
		root.setAlignment(Pos.CENTER);
		root.setSpacing(10);
		
		Button fileOpen = new Button("FileOpen");
		Button fileSave = new Button("FileSave");
		Button dirOpen = new Button("DirOpen");
		
		root.getChildren().addAll(fileOpen, fileSave, dirOpen);
				
		//#1-1 FileChooser
		FileChooser fileChooser = new FileChooser();		
		fileChooser.setInitialFileName("initialSaveFileName"); //SaveDialog에서 사용
		fileChooser.setInitialDirectory(new File("d://filechoosertest//"));
		
		ExtensionFilter extFilter1 = new ExtensionFilter("pdf 파일", "*.pdf"); 
		ExtensionFilter extFilter2 = new ExtensionFilter("텍스트 파일", "*.txt");
		ExtensionFilter extFilter3 = new ExtensionFilter("모든 파일", "*.*");				
		fileChooser.getExtensionFilters().addAll(extFilter1, extFilter2, extFilter3);
				
		fileOpen.setOnAction((event)->{
			//@OpenDialog
			fileChooser.setTitle("FileChooser로 파일 열기");
			File openedFile = fileChooser.showOpenDialog(primaryStage);
			if(openedFile!=null) { 
				System.out.println("OpenedFile = "+openedFile.getName()); 
			}
			else { System.out.println("파일 열기가 취소되었습니다."); 
			}
		});
		
		fileSave.setOnAction((event)->{
			//@SaveDialog : 실제 파일의 저장은 직접 FileOutputStream을 이용하여 작성하여야 함
			fileChooser.setTitle("FileChooser로 파일 저장");
			File savedFile = fileChooser.showSaveDialog(primaryStage);
			if(savedFile!=null) { 
				System.out.println("SavedFile = "+savedFile.getName()); 
			}
			else { System.out.println("파일 저장이 취소되었습니다."); 
			}
		});
		
		dirOpen.setOnAction((event)->{
			//#1-2 DirectoryChooser
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("DirectoryChooser로 폴더 선택하기");				
			directoryChooser.setInitialDirectory(new File("D://directorychoosertest"));
			
			File selectedDir = directoryChooser.showDialog(primaryStage);
			if(selectedDir!=null) { 
				System.out.println("SelectedDir = "+selectedDir.getName()); 
			}
			else { System.out.println("폴더선택이 취소되었습니다."); 
			}		
		});
		
		//#2. Scene 설정
		Scene scene = new Scene(root);
		
		//#3. Stage 설정
		primaryStage.setScene(scene);
		primaryStage.show();
	}
} 
