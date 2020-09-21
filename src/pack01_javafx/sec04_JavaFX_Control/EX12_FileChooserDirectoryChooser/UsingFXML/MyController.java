package pack01_javafx.sec04_JavaFX_Control.EX12_FileChooserDirectoryChooser.UsingFXML;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class MyController implements Initializable{
	@FXML private Button fileOpen;
	@FXML private Button fileSave;
	@FXML private Button dirOpen;
	
	Stage primaryStage;	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
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
	}
	
	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}	
}
