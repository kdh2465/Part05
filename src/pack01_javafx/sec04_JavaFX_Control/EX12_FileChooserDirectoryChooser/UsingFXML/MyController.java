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
	}
	
	public void setStage(Stage stage) {
		this.primaryStage = stage;
	}	
}
