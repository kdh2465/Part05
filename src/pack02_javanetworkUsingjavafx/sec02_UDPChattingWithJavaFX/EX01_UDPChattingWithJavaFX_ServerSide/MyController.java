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
				
		//@서비스 시작 (포트번호 엔터 또는 시작버튼)
		EventHandler<ActionEvent> eventHandler = (event)->{
			String str = tf_portNum.getText().trim();
			int port=0;
			if(str.length()!= 0&& str!=null) {
				port = Integer.parseInt(str);
				serverThread = new ServerThread(port);
				tf_portNum.setDisable(true);
				btn_serverStart.setDisable(true);
				btn_serverStop.setDisable(false);				
				tf_serverState.setText("서비스 시작 상태");				
			} else {
				System.out.println("포트번호를 입력하세요!");
				return;
			}
		};				
		tf_portNum.setOnAction(eventHandler);
		btn_serverStart.setOnAction(eventHandler);
		
		//@서비스 종료
		btn_serverStop.setOnAction(event->{			
			serverThread.datagramSocket.close();
			tf_portNum.setDisable(false);
			btn_serverStart.setDisable(false);
			btn_serverStop.setDisable(true);
			tf_serverState.setText("서비스 중지 상태");			
		});
	}
	
	class ServerThread extends Thread {
		DatagramSocket datagramSocket;
		HashMap<String, User> users = new HashMap<>();
		
		public ServerThread(int port) {
			try {				
				datagramSocket=new DatagramSocket(port);//입력된 port에 바인딩					
			} catch (IOException e) {
				printLog("해당 포트를 열 수 없습니다.");
				return;
			}
			//@서비스 시작 로그 작성
			Platform.runLater(()->{
				try {
					printLog("서비스 시작 ("+InetAddress.getLocalHost().getHostAddress()+")");
				} catch (UnknownHostException e) {
					printLog("서비스 시작 실패");
				}
			});			
			start(); //객체 생성후 바로 시작
		}
		@Override
		public void run() {
			byte[] receivedData = new byte[65508];
			DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
						
			while(true) { //클라이언트 접속 대기 + 접속시 사용자 추가
				
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
					
					case ProtocolCode.SEND_REQUEST_ENTER: //UDP는 참가하기도 다른  case와 동일 레벨로 작성 (TCP와 다른점)
												
						User user = new User(datagramSocket, receivedPacket.getSocketAddress());
						users.put(data, user);		
						
						if(data!=null) {																
							Platform.runLater(()->{
								listView.getItems().add(data);
								label.setText("참석자 ("+users.size()+")");
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
							label.setText("참석자 ("+users.size()+")");
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

						users.get(fromUser).sendData(ProtocolCode.ADD_TEXT, fileName+ "파일 전송", fromUser);
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
					Platform.runLater(()->printLog("서비스 종료")); //UI 쓰레드가 아니기 때문				
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
