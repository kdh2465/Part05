package pack02_javanetworkUsingjavafx.sec01_TCPChattingWithJavaFX.EX01_TCPChattingWithJavaFX_ServerSide;


import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
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
		btn_serverStop.setOnAction(event->{try {
			serverThread.serverSocket.close();
			tf_portNum.setDisable(false);
			btn_serverStart.setDisable(false);
			btn_serverStop.setDisable(true);
			tf_serverState.setText("서비스 중지 상태");
		} catch (IOException e) {}
		});			
	}
	
	class ServerThread extends Thread {
		ServerSocket serverSocket;
		HashMap<String, User> users = new HashMap<>();
		
		public ServerThread(int port) {
			try {
				serverSocket=new ServerSocket(port);						
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
						
			while(true) { //클라이언트 접속 대기 + 접속시 사용자 추가
				try {
					System.out.println("사용자 접속 대기...");
					Socket socket=serverSocket.accept();
					User user = new User(socket);
										
					int codeNum = user.dis.readInt();//코드 읽기
					String userName= user.dis.readUTF();//데이터 읽기
					
					//@사용자 추가
					if(codeNum!=ProtocolCode.SEND_REQUEST_ENTER) {
						System.out.println("SEND_REQUEST_ENTER 오류");
						break;
					}					
					users.put(userName, user);
					
					//@서버와 사용자별 통신 쓰레드
					user.listening(()->{
						while(true) {
							DataInputStream dis = user.dis;
							try {
								int code = dis.readInt();
								String data = dis.readUTF();		
								String fromUser;
								String toUser;
								String fileName;
							
								switch(code) {

								case ProtocolCode.SEND_REQUEST_EXIT:
									users.remove(data);
									Platform.runLater(()->{
										listView.getItems().remove(data);
										listView.refresh();
									});
									
									Set<String> set = users.keySet();
									for(String key: set) {
										users.get(key).sendData(ProtocolCode.DELETE_USER, data);
									}
									Platform.runLater(()->{
										label.setText("참석자 ("+users.size()+")");
										printLog(data+"("+socket.getRemoteSocketAddress()+") Disconnected ");								
									});
									break;
									
								case ProtocolCode.SEND_TEXT_TO_ALL:				
									fromUser = dis.readUTF();			
									for(String key: users.keySet()) {
										users.get(key).sendData(ProtocolCode.ADD_TEXT, data, fromUser);
									}							
									break;
									
								case ProtocolCode.SEND_TEXT_TO_USER:				
									fromUser = dis.readUTF();
									toUser = dis.readUTF();							
									users.get(fromUser).sendData(ProtocolCode.ADD_TEXT, data, fromUser);
									//if(!toUser.equals(fromUser))
										users.get(toUser).sendData(ProtocolCode.ADD_TEXT, data, fromUser);														
									break;
									
								case ProtocolCode.SEND_FILE_TO_ALL:
									fromUser = dis.readUTF();								
									fileName = data.substring(data.lastIndexOf("\\")+1);
									System.out.println(fileName);
									for(String key: users.keySet()) {
										if(!key.equals(fromUser))
											users.get(key).sendData(ProtocolCode.ADD_FILE, fileName, fromUser);
									}									
									while(true) {								
										int num = dis.readInt();
										if(num==-1) {
											for(String key: users.keySet()) {
												if(!key.equals(fromUser))
													users.get(key).sendData(-1);
											}
											System.out.println("servertoclient file transmission complete!!");
											break;
										}											
										byte[] filebuf = new byte[2048];
										dis.read(filebuf, 0, num);
										
										for(String key: users.keySet()) {
											if(!key.equals(fromUser))
												users.get(key).sendData(num, filebuf);
										}
									}
									break;
									
								case ProtocolCode.SEND_FILE_TO_USER:
									fromUser = dis.readUTF();
									toUser = dis.readUTF();	
									fileName = data.substring(data.lastIndexOf("\\")+1);
									System.out.println(fileName);
									users.get(fromUser).sendData(ProtocolCode.ADD_TEXT, fileName+ "파일 전송", fromUser);
									users.get(toUser).sendData(ProtocolCode.ADD_FILE, fileName, fromUser);
																	
									while(true) {								
										int num = dis.readInt();
										if(num==-1) {
											users.get(toUser).sendData(-1);
											break;
										}											
										byte[] filebuf = new byte[2048];
										dis.read(filebuf, 0, num);
										
										users.get(toUser).sendData(num, filebuf);
									}
								}
							} catch (IOException e) {}
						}
					});
					
					System.out.println(userName+"추가");
					
					if(userName!=null) {																
						Platform.runLater(()->{
							listView.getItems().add(userName);
							label.setText("참석자 ("+users.size()+")");
							printLog(userName+"("+socket.getRemoteSocketAddress()+") Connected");
						});
					} 
					
					Set<String> set = users.keySet();
					for(String key: set) {
						if(!key.equals(userName))
							users.get(key).sendData(ProtocolCode.ADD_USER, userName);
					}
					for(String key: set) {
						users.get(userName).sendData(ProtocolCode.ADD_USER, key);
					}
					System.out.println("한바퀴!!!");
					Thread.sleep(1);
					
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
