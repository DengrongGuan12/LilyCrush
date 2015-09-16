package server;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import common.Info;
import model.Channel;
import model.Player;

public class ReadThread extends Thread{
	Socket clientRequest;
	ObjectInputStream reader;
	String m_sReadIn="";
	Channel channel;
	Info info;
	public ReadThread(Socket s,Channel channel){
		this.clientRequest=s;
		this.channel=channel;
		
		try {
			this.reader=new ObjectInputStream(this.clientRequest.getInputStream());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void run(){
		while(true){
			if(this.channel==null){
				break;
			} 
			if(this.clientRequest.isClosed()){
				try {
					this.reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("ObjectReader closed failed!");
				}
				break;
			}
			try {
				Object obj=this.reader.readObject();
				if(obj!=null){
					Info info=(Info)obj;
					this.channel.read(info);
				}
			} catch (Exception e) {
				// TODO: handle exception
				//e.printStackTrace();
				System.out.println("client disconnect!");
				try {
					this.clientRequest.close();
					this.channel.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return;
			}
		}
		
	}

}
