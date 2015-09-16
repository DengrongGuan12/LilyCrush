package businesslogicService;

import enums.Avatar;
import view.MainFrame;
import view.SettingsPanel;

public interface SettingBLService {
	public void setMainFrame(MainFrame mainFrame);
	public void setSettingPanel(SettingsPanel settingsPanel);
	public void changePasswdSuc();
	/*
	 * 点击修改密码之后需要调用的方法，这里需要两个界面的接口，
	 * 一个是当原密码不正确时调用的接口，一个是网络连接不正常时调用
	 * 之前需要做一些基本的格式判断比如两次密码是否一致，是否为空
	 */
	public void changePasswd(String oldPasswd,String newPasswd);
	/*
	 * 点击修改头像需要调用的方法
	 */
	public void changeAvatar(Avatar avatar);
	public void changeAvatarSuc();
	public void showPersonalInfo();

}
