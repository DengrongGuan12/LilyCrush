package businesslogicService;

import view.LoginPanel;

public interface LoginBLService {
	public void setLoginPanel(LoginPanel loginPanel);
	public void loginSuc();
	public void loginFail();
	public void hasLogin();
	/*
	 * �����¼����Ҫ���õķ���������ֵΪ���������Ƿ�����
	 * ����֮ǰ��Ҫ�ж�һ���Ƿ�Ϊ��
	 */
	public void login(String name,String passwd);
}
