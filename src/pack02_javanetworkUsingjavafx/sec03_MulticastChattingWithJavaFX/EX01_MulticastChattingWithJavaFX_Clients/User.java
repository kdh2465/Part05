package pack02_javanetworkUsingjavafx.sec03_MulticastChattingWithJavaFX.EX01_MulticastChattingWithJavaFX_Clients;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketAddress;
import java.util.Arrays;

public class User {
	boolean stopListening;
	String name;
	MulticastSocket multicastSocket;
	SocketAddress socketAddress;
	String sperator = "###";
	
	
	User(String name, MulticastSocket multicastSocket, SocketAddress socketAddress){
		this.name=name;
		this.multicastSocket=multicastSocket;
		this.socketAddress=socketAddress;
	}
	
	void sendData(int code, String data) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_data = name+sperator+code+sperator+data;		
		sendData = str_data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);

		try {
			multicastSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
	}
	
	void sendData(int code, String data, String fromUser) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_data = name+sperator+code+sperator+data+sperator+fromUser;		
		sendData = str_data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);

		try {
			multicastSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
	}
	
	void sendData(int code, String data, String fromUser, String toUser) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_data = name+sperator+code+sperator+data+sperator+fromUser+sperator+toUser;		
		sendData = str_data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);

		try {
			multicastSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}		
		
	}
	
	void sendData(int code, int length) {
		DatagramPacket sendPacket;
		byte[] sendData;
		
		String str_data = name+sperator+code+sperator+length;		
		sendData = str_data.getBytes();
		sendPacket = new DatagramPacket(sendData, sendData.length, socketAddress);

		try {
			multicastSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}
	}
	byte[] concatArray(byte[] a, byte[] b) {
		byte[] result = new byte[a.length+b.length];
		
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		
		return result;
	}

	void sendData(int code, int length2, byte[] data2) { //한글이 포함된 경우 String->byte->String시 비트수 변형발생으로 각각 byte로 변경 수행
		DatagramPacket sendPacket;
		
		byte[] a = concatArray(name.getBytes(), sperator.getBytes());
		byte[] b = concatArray(String.valueOf(code).getBytes(), sperator.getBytes());
		byte[] c = concatArray(String.valueOf(length2).getBytes(), sperator.getBytes());
		byte[] data1=concatArray(a,concatArray(b,c));
		
		int length1 = data1.length;
		
		byte[] totalData = new byte[length1+length2];
		System.arraycopy(data1, 0, totalData, 0, length1);
		System.arraycopy(data2, 0, totalData, length1, length2);
					
		sendPacket = new DatagramPacket(totalData, totalData.length, socketAddress);
		
		try {
			multicastSocket.send(sendPacket);
		} catch (IOException e) {System.out.println("데이터 전송실패");}
	}
	

	void sendFile(File file) throws IOException {		
		FileInputStream fis = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
		byte[] readbuf = new byte[2048];
		
		while(true) {
			int count = bis.read(readbuf);
			if(count==-1) {				
				sendData(ProtocolCode.ONLY_LENGTH, -1);
				System.out.println("file 전송 완료");
				break;
			}			
			sendData(ProtocolCode.LENGTH_AND_BYYE, count, readbuf);		
			try {Thread.sleep(1);} catch (InterruptedException e) {}
		}
		bis.close();
	}
	
	void close() {
		if(multicastSocket!=null) multicastSocket.close();
	}
	
	public void listening(Runnable runnable) {
		new Thread(runnable).start();
	}
}
