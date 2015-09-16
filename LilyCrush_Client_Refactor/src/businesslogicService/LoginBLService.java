package businesslogicService;

import view.LoginPanel;

public interface LoginBLService {
	public void setLoginPanel(LoginPanel loginPanel);
	public void loginSuc();
	public void loginFail();
	public void hasLogin();
	/*
	 * 点击登录后需要调用的方法，返回值为网络连接是否正常
	 * 可能之前需要判断一下是否为空
	 */
	public void login(String name,String passwd);
}
