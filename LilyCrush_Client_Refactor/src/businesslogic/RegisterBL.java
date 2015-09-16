package businesslogic;

import client.MyClient;
import common.Info;
import common.registerInfo.RegInfo;
import view.MainFrame;
import view.RegisterPanel;
import businesslogicService.RegisterBLService;

public class RegisterBL implements RegisterBLService{
	private MainFrame mainFrame;
	private MyClient myClient;
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setMyClient(MyClient myClient) {
		this.myClient = myClient;
	}
	private RegisterPanel registerPanel;

	@Override
	public void setRegisterPanel(RegisterPanel registerPanel) {
		this.registerPanel=registerPanel;
		
	}

	@Override
	public void regSuc() {
		this.registerPanel.registerSuccess();
		
	}

	@Override
	public void regFail() {
		this.registerPanel.registerFail();
		
	}
	public void register(String name, String passwd, String nickname) {
		Info regInfo = new RegInfo(name, passwd, nickname);
		if (!this.myClient.write(regInfo)) {
			/*
			 * 需要网络连接不正常的界面接口 public void disconnected(){}
			 */
			this.mainFrame.disconnected();
		}
	}
	

}
