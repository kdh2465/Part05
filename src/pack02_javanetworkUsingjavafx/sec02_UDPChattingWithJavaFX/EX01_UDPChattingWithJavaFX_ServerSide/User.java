package pack02_javanetworkUsingjavafx.sec02_UDPChattingWithJavaFX.EX01_UDPChattingWithJavaFX_ServerSide;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;

public class User {
	boolean stopListening;
	DatagramSocket datagramSocket;
	SocketAddress socketAddress;
	
	
	User(DatagramSocket datagramSocket, SocketAddress socketAddress){
		this.datagramSocket=datagramSocket;
		this.socketAddress=socketAddress;
	}
	
	void sendData(int code, String data) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_code = String.valueOf(code);
		sendData = str_code.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);

		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
				
		sendData = data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
		
	}
	
	void sendData(int code, String data, String fromUser) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_code = String.valueOf(code);
		sendData = str_code.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
				
		sendData = data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
		sendData = fromUser.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
	}
	
	void sendData(int code, String data, String fromUser, String toUser) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_code = String.valueOf(code);
		sendData = str_code.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
				
		sendData = data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
		sendData = fromUser.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
		sendData = toUser.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
	}
	
	void sendData(int length) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_length = String.valueOf(length);
		sendData = str_length.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
	}
	
	void sendData(int length, byte[] data) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_length = String.valueOf(length);
		sendData = str_length.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
		
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
		sendPacket = new DatagramPacket(data, length, socketAddress);
		try {
			datagramSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}	
		
	}
	
	void sendFile(File file) throws IOException {		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		DatagramPacket sendPacket;
		byte[] sendData;
		
		byte[] readbuf = new byte[2048];
		
		while(true) {
			int count = bis.read(readbuf);
			if(count==-1) {
				
				String str_length = String.valueOf(-1);
				sendData = str_length.getBytes();
				sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
				try {
					datagramSocket.send(sendPacket);
				} catch (IOException e) {System.out.println("데이터 전송실패");}
				break;
			}	
			String str_length = String.valueOf(count);
			sendData = str_length.getBytes();
			sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);
			try {
				datagramSocket.send(sendPacket);
			} catch (IOException e) {System.out.println("데이터 전송실패");}
			
			sendPacket = new DatagramPacket(readbuf, count, socketAddress);
			try {
				datagramSocket.send(sendPacket);
			} catch (IOException e) {System.out.println("데이터 전송실패");}
						
		}
	}
	
	void close() {
		if(datagramSocket!=null) datagramSocket.close();
	}
	
	public void listening(Runnable runnable) {
		new Thread(runnable).start();
	}
}
