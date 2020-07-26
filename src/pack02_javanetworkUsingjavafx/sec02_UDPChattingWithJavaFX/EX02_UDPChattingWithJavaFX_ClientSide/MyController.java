package pack02_javanetworkUsingjavafx.sec02_UDPChattingWithJavaFX.EX02_UDPChattingWithJavaFX_ClientSide;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class MyController implements Initializable{
	@FXML private ListView<String> listView;
	@FXML private TextField tf_ip;
	@FXML private TextField tf_port;
	@FXML private TextField tf_name;		
	@FXML private Button btn_enter;
	@FXML private Button btn_exit;	
	@FXML private TextArea ta_clientText;
	@FXML private ComboBox<String> comboBox;
	@FXML private TextField tf_text;
	@FXML private Button btn_send;	
	@FXML private Button btn_fileOpen;
	
	DatagramSocket datagramSocket;
	User user;
	String name;	
	Stage primaryStage;
	File sendFile, receivedFile;
	
	public void setStage(Stage stage) {
		primaryStage=stage;
		
		primaryStage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, (event)->{
			user.sendData(ProtocolCode.SEND_REQUEST_EXIT, name);
			user.stopListening=true;
			System.out.println("\nWindow Closed");});
	}
	
	void printData(String str) {
		Date date = new Date();				
		SimpleDateFormat sdf = new SimpleDateFormat("[MM-dd/HH:mm:ss] : ");
		Platform.runLater(()->ta_clientText.appendText(sdf.format(date)+str+System.lineSeparator()));		
	}
				
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		tf_text.setDisable(true);
		btn_send.setDisable(true);		
		
		btn_fileOpen.setOnAction((event)->{			
			FileChooser fileChooser = new FileChooser();		
			fileChooser.setInitialDirectory(new File("d://"));			
			ExtensionFilter extFilter1 = new ExtensionFilter("��� ����", "*.*");		
			ExtensionFilter extFilter2 = new ExtensionFilter("�ؽ�Ʈ ����", "*.txt");
			ExtensionFilter extFilter3 = new ExtensionFilter("pdf ����", "*.pdf"); 
			fileChooser.getExtensionFilters().addAll(extFilter1, extFilter2, extFilter3);
								
			//@OpenDialog
			fileChooser.setTitle("���� ���� �����ϱ�");
			sendFile = fileChooser.showOpenDialog(primaryStage);
			if(sendFile!=null) { 
				System.out.println("OpenedFile = "+sendFile.getName());
				tf_text.setText(sendFile.getAbsolutePath());
				tf_text.setEditable(false); //���������� ��� �ؽ�Ʈ ���� �Ұ��ϵ��� (���߿� ���۽� editable�� �������� Ȯ��)
			}
			else { System.out.println("���� ���Ⱑ ��ҵǾ����ϴ�."); 
			}			
		});
		
		btn_enter.setOnAction((event)->{
						
			//@IP�� Port�� �о�� ���� ����
			String str_ip = tf_ip.getText().trim();
			String str_port=tf_port.getText().trim();			
			if(str_ip!=null && str_port!=null && str_ip.length()!=0 && str_port.length()!=0) {
				try {
					datagramSocket = new DatagramSocket(); //���� ��Ʈ�� ���ε�
					
					user = new User(datagramSocket, new InetSocketAddress(str_ip,Integer.parseInt(str_port)));
					
					//@���� ���� ���� ���� ��ȭ�� ���� 
					name = tf_name.getText().trim();
					if(name.length()==0 || name==null) {
						System.out.println("��ȭ���� �Է��ϼ���");
						return;
					}
					System.out.println(ProtocolCode.SEND_REQUEST_ENTER+", "+name);
					user.sendData(ProtocolCode.SEND_REQUEST_ENTER, name);

					user.listening(()->{
						byte[] receivedData = new byte[65508];
						DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
						
						while(true) {
							if(user.stopListening) {
								user.close();
								break;
							}
							
							try {
								datagramSocket.receive(receivedPacket);
								int code = Integer.parseInt(new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim());

								String fromName=null;
								String data;
								switch(code) {
								case ProtocolCode.ADD_USER:
									datagramSocket.receive(receivedPacket);
									data = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
									Platform.runLater(()->{
										listView.getItems().add(data);
										if(!name.equals(data))
											comboBox.getItems().add(data);						
									});
									break;
								case ProtocolCode.DELETE_USER:
									datagramSocket.receive(receivedPacket);
									data = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
									Platform.runLater(()->{
										listView.getItems().remove(data);
										comboBox.getItems().remove(data);							
									});
									break;
								case ProtocolCode.ADD_TEXT:
									datagramSocket.receive(receivedPacket);
									data = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
									datagramSocket.receive(receivedPacket);
									fromName = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
									
									printData("["+fromName+"] "+data);
									break;					
								
								case ProtocolCode.ADD_FILE:		
									datagramSocket.receive(receivedPacket);
									data = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
									datagramSocket.receive(receivedPacket);
									fromName = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim(); //data:filename
		
									printData("["+fromName+"] "+data + "���� �۽�"); //data=fileName
		
									File receivedFile = new File("src/pack04_javanetwork/download_files/"+data);
									if(!receivedFile.exists()) receivedFile.createNewFile();
									else {
										int i=1;
										while(true) {
											receivedFile = new File("src/pack04_javanetwork/download_files/"+data+"_"+i);
											if(!receivedFile.exists()) {
												receivedFile.createNewFile();
												break;
											}
											i++;
										}
									}
									
									FileOutputStream fos = new FileOutputStream(receivedFile);
									BufferedOutputStream bos = new BufferedOutputStream(fos);						
									
									
									while(true) {
										datagramSocket.receive(receivedPacket);
										int num=Integer.parseInt(new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim());
										if(num==-1) {
											bos.close();
											printData("File: "+data +" ���� ���� �Ϸ�");
											break;
										}
										datagramSocket.receive(receivedPacket);
										byte[] readbuf = Arrays.copyOf(receivedPacket.getData(), num);
										
										bos.write(readbuf, 0, num);
										bos.flush();
									}						
									break;
								}						
							} catch (IOException e) {			
								e.printStackTrace();
								System.out.println("���ҽ� ����");
								break;										
							}
						}
					});
					
				} catch (IOException e) {System.out.println("Ŭ���̾�Ʈ ���Ͽ��� ����"); return;}
			} else {
				System.out.println("IP�� Port�� �Է��ϼ���!");
				return;
			}
			
			tf_text.setDisable(false);
			btn_send.setDisable(false);			
			
			tf_ip.setDisable(true);
			tf_port.setDisable(true);
			tf_name.setDisable(true);
			
			btn_enter.setDisable(true);
			btn_exit.setDisable(false);
			
		});
		
		btn_exit.setOnAction((event)->{
			user.sendData(ProtocolCode.SEND_REQUEST_EXIT, name);
			user.stopListening=true;
			Platform.exit();
		});
		
		EventHandler<ActionEvent> eventHandler = (event)->{
			String text = tf_text.getText().trim();
			String toUser = comboBox.getValue();

			if(tf_text.isEditable()) { //text ����			
				if(text.length()!=0 && text!=null) {
					if(toUser.equals("��ü�����"))
						user.sendData(ProtocolCode.SEND_TEXT_TO_ALL, text, name);
					else
						user.sendData(ProtocolCode.SEND_TEXT_TO_USER, text, name, toUser);
					tf_text.setText("");
				}
			} else { //file ����
				if(text.length()!=0 && text!=null) {
					if(toUser.equals("��ü�����"))	{					

						user.sendData(ProtocolCode.SEND_FILE_TO_ALL, text, name);
						try {
							user.sendFile(sendFile);
							printData(text+" ���� ����");
						} catch (IOException e) {}						
					}
					else {

						user.sendData(ProtocolCode.SEND_FILE_TO_USER, text, name, toUser);
						try {
							user.sendFile(sendFile);
							printData(text+" ���� ����");
						} catch (IOException e) {}	
					}
					tf_text.setText("");
					tf_text.setEditable(true);
				}				
			}			
		};		
		tf_text.setOnAction(eventHandler);
		btn_send.setOnAction(eventHandler);				
	}		
}

