package pack02_javanetworkUsingjavafx.sec01_TCPChattingWithJavaFX.EX01_TCPChattingWithJavaFX_ServerSide;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

public class User {
	boolean stopListening;
	Socket socket;
	DataInputStream dis;
	DataOutputStream dos;
	
	User(Socket socket){
		this.socket=socket;
		try {
			dis=new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			dos=new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
		} catch (IOException e) {System.out.println("스트림 생성 실패");}		
	}
	
	void sendData(int code, String data) {
		try {
			dos.writeInt(code);
			dos.writeUTF(data);
			dos.flush();
		} catch (IOException e) { System.out.println("데이터 전송실패");}			
	}
	
	void sendData(int code, String data, String fromUser) {
		try {
			dos.writeInt(code);
			dos.writeUTF(data);
			dos.writeUTF(fromUser);
			dos.flush();
		} catch (IOException e) { System.out.println("데이터 전송실패");}		
	}
	
	void sendData(int code, String data, String fromUser, String toUser) {
		try {
			dos.writeInt(code);
			dos.writeUTF(data);
			dos.writeUTF(fromUser);
			dos.writeUTF(toUser);
			dos.flush();
		} catch (IOException e) { System.out.println("데이터 전송실패");}			
	}
	
	void sendData(int length) {
		try {					
			dos.writeInt(length);
			dos.flush();
		} catch (IOException e) { System.out.println("데이터 전송실패");}	
	}
	
	void sendData(int length, byte[] data) {
		try {					
			dos.writeInt(length);					
			dos.write(data, 0, length);
			dos.flush();
		} catch (IOException e) { System.out.println("데이터 전송실패");}	
	}
	
	void sendFile(File file) throws IOException {		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		byte[] readbuf = new byte[2048];
		
		while(true) {
			int count = bis.read(readbuf);
			if(count==-1) {
				dos.writeInt(-1);
				dos.flush();
				break;
			}				
			dos.writeInt(count);								
			dos.write(readbuf, 0, count);
			dos.flush();				
		}
	}
	
	void close() {
		try {
			if(socket!=null) socket.close();
			if(dis!=null) dis.close();
			if(dos!=null) dos.close();
			} catch (IOException e) {}
	}
	
	public void listening(Runnable runnable) {
		new Thread(runnable).start();
	}
}
