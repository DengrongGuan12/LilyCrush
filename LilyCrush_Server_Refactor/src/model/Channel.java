package model;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import common.Info;
import common.friendInfo.AddFriendInfo;
import common.friendInfo.DeleteFriendInfo;
import common.gameInfo.OneInitGameInfo;
import common.gameInfo.SwapInfo;
import common.gameInfo.UsePropInfo;
import common.loginInfo.LoginInfo;
import common.registerInfo.RegInfo;
import common.roomInfo.InviteFriendInfo;
import common.roomInfo.JoinCooprtGame;
import common.settingInfo.ChangeAvatarInfo;
import common.settingInfo.ChangePasswdInfo;
import server.ReadThread;

public class Channel {
	private Player player;
	private Socket socketIn = null, socketOut = null;
	private ReadThread readThread = null;
	private ObjectOutputStream writer;
	public Channel(Socket socketIn,Socket socketOut){
		this.socketIn=socketIn;
		this.socketOut=socketOut;
		this.initReadThread();
		this.initPrintWriter();
	}
	private void initReadThread() {
		this.readThread = new ReadThread(socketIn, this);
		this.readThread.start();
	}
	private void initPrintWriter() {

		try {
			this.writer = new ObjectOutputStream(socketOut.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public void setPlayer(Player player){
		this.player=player;
	}
	public void read(Info info) {
		switch (info.getMessage()) {
		case login: {
			LoginInfo loginInfo = (LoginInfo) info;
			this.player.login(loginInfo);
			break;
		}

		case register: {
			RegInfo regInfo = (RegInfo) info;
			this.player.register(regInfo);
			break;
		}

		case changePasswd: {
			ChangePasswdInfo changePasswdInfo = (ChangePasswdInfo) info;
			this.player.changePasswd(changePasswdInfo);
			break;
		}

		case initOneGame: {
			OneInitGameInfo oneInitGameInfo = (OneInitGameInfo) info;
			this.player.initOneGame(oneInitGameInfo);
			break;
		}
		case stOneGame: {
			this.player.startOneGame();
			break;
		}
		case pauseSingleGame:{
			this.player.pauseOneGame();
			break;
		}
		case continueSingleGame:{
			this.player.continueSingleGame();
			break;
		}
		case exitGame:{
			this.player.exitRoom();
			break;
		}
		case swapPositions: {
			SwapInfo swapInfo = (SwapInfo) info;
			this.player.swapPosition(swapInfo);
			break;
		}
		case CooperationGame: {
			this.player.findRoom();
			break;
		}
		case createCooprtGame: {
			this.player.createRoom();
			break;
		}
		case readyForCooprtGame: {
			this.player.ready();
			break;
		}
		case noReadyForCooprtGame: {
			this.player.noready();
			break;
		}
		case startCooprtGame: {
			this.player.startCooperateGame();
			break;
		}
		case joinCooprtGame: {
			JoinCooprtGame joinCooprtGame = (JoinCooprtGame) info;
			this.player.joinRoom(joinCooprtGame);
			break;
		}
		case getFriendsList: {
			this.player.getFriendsList();
			break;
		}
		case deleteFriend: {
			DeleteFriendInfo deleteFriendInfo = (DeleteFriendInfo) info;
			this.player.deleteFriend(deleteFriendInfo);
			break;
		}
		case addFriend: {
			AddFriendInfo addFriendInfo = (AddFriendInfo) info;
			this.player.addFriend(addFriendInfo);
			break;
		}
		case agreeToBecomeFriend: {
			AddFriendInfo agreeToBecomeFriendInfo = (AddFriendInfo) info;
			this.player.agreeToBecomeFriend(agreeToBecomeFriendInfo);
			break;
		}
		case refuseToBecomeFriend: {
			AddFriendInfo addFriendInfo = (AddFriendInfo) info;
			this.player.refuseToBecomeFriend(addFriendInfo);
			break;
		}
		case inviteFriendCooperate: {
			InviteFriendInfo inviteFriendInfo = (InviteFriendInfo) info;
			this.player.inviteFriend(inviteFriendInfo);
			break;
		}
		case agreeInvitedToCooperateGame: {

			InviteFriendInfo inviteFriendInfo = (InviteFriendInfo) info;
			this.player.agreeInvitedToCooperateGame(inviteFriendInfo);
			break;
		}
		case refuseInvitedToCooperateGame: {
			InviteFriendInfo inviteFriendInfo = (InviteFriendInfo) info;
			this.player.refuseInvitedToCooperateGame(inviteFriendInfo);
			break;
		}
		case getSingleHistoryData: {
			this.player.getSingleHistoryData();
			break;
		}
		case getSingleGameRank: {
			this.player.getSingleGameRank();
			break;
		}
		case getMultiGameRank: {
			this.player.getMultiGameRank();
			break;
		}
		case changeAvatar: {
			ChangeAvatarInfo changeAvatarInfo = (ChangeAvatarInfo) info;
			this.player.changeAvatar(changeAvatarInfo);
			break;
		}
		case canAutoCrash: {
			this.player.autoCrush();
			break;

		}
		case useProp: {
			UsePropInfo usePropInfo = (UsePropInfo) info;
			this.player.useProp(usePropInfo);
			break;

		}
		case exitRoom: {
			this.player.exitRoom();
			break;
		}
		default:
			break;
		}

	}
	public void close() {
		this.player.offLine();
		try {
			this.writer.close();
			this.socketOut.close();
			this.socketIn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public boolean write(Info info) {
		if (this.writer == null) {
			return false;
		}
		try {
			this.writer.reset();
			this.writer.writeObject(info);
			this.writer.flush();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
