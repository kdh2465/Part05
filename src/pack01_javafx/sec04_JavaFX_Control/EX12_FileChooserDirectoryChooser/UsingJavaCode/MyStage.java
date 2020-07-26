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
		
		//#1. ��Ʈ �����̳� �� �ڽ� ��� ����
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
		fileChooser.setInitialFileName("initialSaveFileName"); //SaveDialog���� ���
		fileChooser.setInitialDirectory(new File("d://filechoosertest//"));
		
		ExtensionFilter extFilter1 = new ExtensionFilter("pdf ����", "*.pdf"); 
		ExtensionFilter extFilter2 = new ExtensionFilter("�ؽ�Ʈ ����", "*.txt");
		ExtensionFilter extFilter3 = new ExtensionFilter("��� ����", "*.*");				
		fileChooser.getExtensionFilters().addAll(extFilter1, extFilter2, extFilter3);
				
		fileOpen.setOnAction((event)->{
			//@OpenDialog
			fileChooser.setTitle("FileChooser�� ���� ����");
			File openedFile = fileChooser.showOpenDialog(primaryStage);
			if(openedFile!=null) { 
				System.out.println("OpenedFile = "+openedFile.getName()); 
			}
			else { System.out.println("���� ���Ⱑ ��ҵǾ����ϴ�."); 
			}
		});
		
		fileSave.setOnAction((event)->{
			//@SaveDialog : ���� ������ ������ ���� FileOutputStream�� �̿��Ͽ� �ۼ��Ͽ��� ��
			fileChooser.setTitle("FileChooser�� ���� ����");
			File savedFile = fileChooser.showSaveDialog(primaryStage);
			if(savedFile!=null) { 
				System.out.println("SavedFile = "+savedFile.getName()); 
			}
			else { System.out.println("���� ������ ��ҵǾ����ϴ�."); 
			}
		});
		
		dirOpen.setOnAction((event)->{
			//#1-2 DirectoryChooser
			DirectoryChooser directoryChooser = new DirectoryChooser();
			directoryChooser.setTitle("DirectoryChooser�� ���� �����ϱ�");				
			directoryChooser.setInitialDirectory(new File("D://directorychoosertest"));
			
			File selectedDir = directoryChooser.showDialog(primaryStage);
			if(selectedDir!=null) { 
				System.out.println("SelectedDir = "+selectedDir.getName()); 
			}
			else { System.out.println("���������� ��ҵǾ����ϴ�."); 
			}		
		});
		
		//#2. Scene ����
		Scene scene = new Scene(root);
		
		//#3. Stage ����
		primaryStage.setScene(scene);
		primaryStage.show();
	}
} 
