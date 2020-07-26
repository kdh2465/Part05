package pack02_javanetworkUsingjavafx.sec02_UDPChattingWithJavaFX.EX01_UDPChattingWithJavaFX_ServerSide;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.Set;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MyController implements Initializable{
	@FXML private Label label;
	@FXML private ListView<String> listView;
	@FXML private TextArea ta_serverLog;
	@FXML private TextField tf_serverState;
	@FXML private TextField tf_portNum;
	@FXML private Button btn_serverStart;
	@FXML private Button btn_serverStop;
	
	ServerThread serverThread;
				
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
		btn_serverStop.setDisable(true);
				
		//@���� ���� (��Ʈ��ȣ ���� �Ǵ� ���۹�ư)
		EventHandler<ActionEvent> eventHandler = (event)->{
			String str = tf_portNum.getText().trim();
			int port=0;
			if(str.length()!= 0&& str!=null) {
				port = Integer.parseInt(str);
				serverThread = new ServerThread(port);
				tf_portNum.setDisable(true);
				btn_serverStart.setDisable(true);
				btn_serverStop.setDisable(false);				
				tf_serverState.setText("���� ���� ����");				
			} else {
				System.out.println("��Ʈ��ȣ�� �Է��ϼ���!");
				return;
			}
		};				
		tf_portNum.setOnAction(eventHandler);
		btn_serverStart.setOnAction(eventHandler);
		
		//@���� ����
		btn_serverStop.setOnAction(event->{			
			serverThread.datagramSocket.close();
			tf_portNum.setDisable(false);
			btn_serverStart.setDisable(false);
			btn_serverStop.setDisable(true);
			tf_serverState.setText("���� ���� ����");			
		});
	}
	
	class ServerThread extends Thread {
		DatagramSocket datagramSocket;
		HashMap<String, User> users = new HashMap<>();
		
		public ServerThread(int port) {
			try {				
				datagramSocket=new DatagramSocket(port);//�Էµ� port�� ���ε�					
			} catch (IOException e) {
				printLog("�ش� ��Ʈ�� �� �� �����ϴ�.");
				return;
			}
			//@���� ���� �α� �ۼ�
			Platform.runLater(()->{
				try {
					printLog("���� ���� ("+InetAddress.getLocalHost().getHostAddress()+")");
				} catch (UnknownHostException e) {
					printLog("���� ���� ����");
				}
			});			
			start(); //��ü ������ �ٷ� ����
		}
		@Override
		public void run() {
			byte[] receivedData = new byte[65508];
			DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
						
			while(true) { //Ŭ���̾�Ʈ ���� ��� + ���ӽ� ����� �߰�
				
				try {
					datagramSocket.receive(receivedPacket);
					int code = Integer.parseInt(new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim());
					datagramSocket.receive(receivedPacket);
					String data = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
					
					String fromUser;
					String toUser;
					String fileName;
					
					Set<String> set;
				
					switch(code) {
					
					case ProtocolCode.SEND_REQUEST_ENTER: //UDP�� �����ϱ⵵ �ٸ�  case�� ���� ������ �ۼ� (TCP�� �ٸ���)
												
						User user = new User(datagramSocket, receivedPacket.getSocketAddress());
						users.put(data, user);		
						
						if(data!=null) {																
							Platform.runLater(()->{
								listView.getItems().add(data);
								label.setText("������ ("+users.size()+")");
								printLog(data+"("+user.socketAddress+") Connected");
							});
						} 
						
						set = users.keySet();
						for(String key: set) {
							if(!key.equals(data))
								users.get(key).sendData(ProtocolCode.ADD_USER, data);
						}
						for(String key: set) {
							users.get(data).sendData(ProtocolCode.ADD_USER, key);
						}						
						Thread.sleep(1);						
					break;
					

					case ProtocolCode.SEND_REQUEST_EXIT:

						SocketAddress removeSocketAddress = users.get(data).socketAddress; 
						
						users.remove(data);
						Platform.runLater(()->{
							listView.getItems().remove(data);
							listView.refresh();
						});

						set = users.keySet();
						for(String key: set) {
							users.get(key).sendData(ProtocolCode.DELETE_USER, data);
						}
						
						Platform.runLater(()->{
							label.setText("������ ("+users.size()+")");
							printLog(data+"("+removeSocketAddress+") Disconnected ");								
							//printLog(data+"("+") Disconnected ");
						});
						
						break;
						
					case ProtocolCode.SEND_TEXT_TO_ALL:			

						datagramSocket.receive(receivedPacket);
						fromUser = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();

						for(String key: users.keySet()) {
							users.get(key).sendData(ProtocolCode.ADD_TEXT, data, fromUser);
						}							
						break;
						
					case ProtocolCode.SEND_TEXT_TO_USER:	
						
						datagramSocket.receive(receivedPacket);
						fromUser = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
						
						datagramSocket.receive(receivedPacket);
						toUser = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
						
						users.get(fromUser).sendData(ProtocolCode.ADD_TEXT, data, fromUser);									
						users.get(toUser).sendData(ProtocolCode.ADD_TEXT, data, fromUser);														
						break;
						
					case ProtocolCode.SEND_FILE_TO_ALL:
						datagramSocket.receive(receivedPacket);
						fromUser = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
						
						fileName = data.substring(data.lastIndexOf("\\")+1);

						for(String key: users.keySet()) {
							if(!key.equals(fromUser))
								users.get(key).sendData(ProtocolCode.ADD_FILE, fileName, fromUser);
						}									
						while(true) {			
							datagramSocket.receive(receivedPacket);
							int num = Integer.parseInt(new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim());

							if(num==-1) {
								for(String key: users.keySet()) {
									if(!key.equals(fromUser))
										users.get(key).sendData(-1);
								}
								System.out.println("servertoclient file transmission complete!!");
								break;
							}											
							
							datagramSocket.receive(receivedPacket);
							byte[] filebuf =Arrays.copyOf(receivedPacket.getData(), num);

							for(String key: users.keySet()) {

								if(!key.equals(fromUser))
									users.get(key).sendData(num, filebuf);
							}
						}
						break;
						
					case ProtocolCode.SEND_FILE_TO_USER:									

						datagramSocket.receive(receivedPacket);
						fromUser = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
						
						datagramSocket.receive(receivedPacket);
						toUser = new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim();
						
						fileName = data.substring(data.lastIndexOf("\\")+1);

						users.get(fromUser).sendData(ProtocolCode.ADD_TEXT, fileName+ "���� ����", fromUser);
						users.get(toUser).sendData(ProtocolCode.ADD_FILE, fileName, fromUser);
														
						while(true) {	
							datagramSocket.receive(receivedPacket);
							int num = Integer.parseInt(new String(receivedPacket.getData(),0,receivedPacket.getLength()).trim());
							if(num==-1) {
								users.get(toUser).sendData(-1);
								break;
							}		
							
							datagramSocket.receive(receivedPacket);
							byte[] filebuf =Arrays.copyOf(receivedPacket.getData(), num);
							
							users.get(toUser).sendData(num, filebuf);
						}
					}
				} catch (Exception e) {
					Platform.runLater(()->printLog("���� ����")); //UI �����尡 �ƴϱ� ����				
					break;
				}
			}
		}
		void printLog(String str) {
			Date date = new Date();				
			SimpleDateFormat sdf = new SimpleDateFormat("[MM-dd/HH:mm:ss] : ");
			ta_serverLog.appendText(sdf.format(date)+str+System.lineSeparator());		
		}
	}
}
