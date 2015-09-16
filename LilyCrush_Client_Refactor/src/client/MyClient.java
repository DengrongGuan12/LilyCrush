package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.Info;

public class MyClient {
	final int RECEIVE_OUT_PORT = 9090;
	final int RECEIVE_IN_PORT = 9091;
	Socket socketIn = null, socketOut = null;
	Thread readThread = null;
	NetRead m_Owner;
	ObjectOutputStream writer ;
	public MyClient(String host,NetRead netRead) {
		this.m_Owner=netRead;
		String str = host;
		try {
			socketOut = new Socket(str, RECEIVE_OUT_PORT);
			socketIn = new Socket(str, RECEIVE_IN_PORT);
			readThread = new ReadThread(socketIn, this);
			readThread.start();
			writer=new ObjectOutputStream(socketOut.getOutputStream());

		} catch (Exception e) {
			// TODO: handle exception
			netRead.wrongIP();
		}

	}

	public boolean write(Info info) {
		if (writer == null) {
			return false;
		}
		try {
			writer.reset();
			//TODO
			System.out.println("向服务器发送+"+info.getMessage());
			writer.writeObject(info);
			writer.flush();
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	public void close(){
		try {
			System.out.println("Closed");
			socketIn.close();
			socketOut.close();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void readInfo(Info info){
		this.m_Owner.readInfo(info);
		//System.out.println(str);
		
	}
	public void serverDead(){
		this.m_Owner.serverDead();
	}
}
