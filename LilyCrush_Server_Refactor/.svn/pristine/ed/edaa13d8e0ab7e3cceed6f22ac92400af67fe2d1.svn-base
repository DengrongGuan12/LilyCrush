package server;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import model.Channel;
import model.Player;
import model.managers.PlayersManager;

public class MyServer implements Runnable {
	/*
	 * 开启两个端口，一个读一个写
	 */
	private static MyServer myServer;
	final int RECIEVE_IN_PORT = 9090;
	final int RECIEVE_OUT_PORT = 9091;
	ServerSocket rInServer = null, rOutServer = null;

	private MyServer() {
		try {
			rInServer = new ServerSocket(RECIEVE_IN_PORT);
			rOutServer = new ServerSocket(RECIEVE_OUT_PORT);
			System.out.println("Welcome to the server!");
			System.out.println(new Date());
			System.out.println("The server is ready!");
			System.out.println("PORT:" + RECIEVE_IN_PORT);
			System.out.println("Local Machine's name:"
					+ InetAddress.getLocalHost());

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	public static MyServer getInstance() {
		if (myServer == null) {
			myServer = new MyServer();
		}
		return myServer;
	}
	public void close() {
		try {
			rInServer.close();
			rOutServer.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int ID=1;
		try {
			while (true) {
				Socket socketIn = rInServer.accept();
				Socket socketOut = rOutServer.accept();
				Channel channel=new Channel(socketIn, socketOut);
				
				Player player=new Player(ID, channel);
				PlayersManager playersManager=PlayersManager.getInstance();
				playersManager.addPlayer(player);
				channel.setPlayer(player);
				ID++;
				System.out.println(ID);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}
	public static void main(String[] args){
		MyServer myServer =new MyServer();
		Thread thread=new Thread(myServer);
		thread.start();
	}

}
