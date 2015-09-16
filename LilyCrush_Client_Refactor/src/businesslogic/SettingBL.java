package businesslogic;

import client.MyClient;
import view.MainFrame;
import view.SettingsPanel;
import common.Info;
import common.settingInfo.ChangeAvatarInfo;
import common.settingInfo.ChangePasswdInfo;
import common.settingInfo.PersonalInfo;
import enums.Avatar;
import PublicInfo.MyInfo;
import businesslogic.managers.PropManager;
import businesslogicService.SettingBLService;

public class SettingBL implements SettingBLService{
	private MainFrame mainFrame;
	private SettingsPanel settingsPanel;
	private String tempPasswd;
	private Avatar tempAvatar;
	private MyClient myClient;
	@Override
	public void changePasswdSuc() {
		MyInfo.setPasswd(this.tempPasswd);
		this.settingsPanel.changePasswdSuc();
		
	}
	public void changePasswd(String oldPasswd, String newPasswd) {
		if (oldPasswd.equals(MyInfo.getPasswd())) {
			Info changePasswdInfo = new ChangePasswdInfo(newPasswd);
			if (!this.myClient.write(changePasswdInfo)) {
				/*
				 * ��Ҫ�������Ӳ������Ľ���ӿ� public void disconnected(){}
				 */
				this.mainFrame.disconnected();
			}else {
				this.tempPasswd=newPasswd;
			}
		} else {
			/*
			 * ��Ҫԭ���벻��ȷʱ�Ľ���ӿ� public void wrongPasswd(){}
			 */
			this.settingsPanel.wrongPasswd();
		}
	}
	@Override
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame=mainFrame;
		
	}
	@Override
	public void setSettingPanel(SettingsPanel settingsPanel) {
		this.settingsPanel=settingsPanel;
		
	}
	@Override
	public void changeAvatar(Avatar avatar) {
		if (!this.myClient.write(new ChangeAvatarInfo(avatar))) {
			this.mainFrame.disconnected();
		}else {
			this.tempAvatar=avatar;
		}

	}
	@Override
	public void changeAvatarSuc() {
		MyInfo.setAvatar(this.tempAvatar);
		this.settingsPanel.changeAvatarSuc();
		
	}
	@Override
	public void showPersonalInfo() {
		PropManager propManager=PropManager.getInstance();
		this.mainFrame.getSettingsPanel().setPersonalInfo(
				new PersonalInfo(MyInfo.getID(), MyInfo.getBriefIntro(), MyInfo.getName(),
						MyInfo.getAvatar(), propManager.getCoins()));

	}

}