package client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import common.Info;
public class ReadThread extends Thread{

	Socket clientRequest;
	MyClient m_myClient;
	ObjectInputStream reader;
	public ReadThread(Socket s,MyClient ms){
		this.clientRequest=s;
		this.m_myClient=ms;
		
	}
	public void run(){
		try {
			this.reader=new ObjectInputStream(this.clientRequest.getInputStream());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("ReadThread start!");
		while(true){
			System.out.println("�����ڴӷ�������ȡ��Ϣ������");
			if(this.m_myClient==null){
				break;
			}
			if(this.clientRequest.isClosed()){
				try {
					System.out.println("read closed");
					this.reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			try {
				Object obj=reader.readObject();
				System.out.println("�Ҷ�����һ����Ϣ��");
				if(obj!=null){
					Info info=(Info)obj;
					System.out.println("�ӷ������˻�ȡ����Ϣ"+info.getMessage());
					m_myClient.readInfo(info);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				this.m_myClient.serverDead();
				break;
			}
		}
		
	}



}
