package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import businesslogicService.GameBLService;
import businesslogicService.LoginBLService;

import view.component.BackGroundPanel;
import view.component.ClickButton;
import view.component.TPswdField;
import view.component.TTextField;

public class LoginPanel extends BackGroundPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LoginBLService loginBLService;

	private MainFrame mainFrame;
	private LoginPanel loginPanel;

	private BackGroundPanel login;
	private TTextField idTextField;
	private TPswdField passwdTextField;
	private JLabel idLabel;
	private JLabel passwdLabel;
	private ClickButton loginButton;
	private ClickButton registerButton;
	private ClickButton setIpButton;

	public LoginPanel() {
		initialUI();
		loginPanel = this;
	}

	public void initialUI() {
		login = this;
		ImageIcon loginImageIcon = new ImageIcon("images/登陆.png");
		login.setBounds(280, 100, loginImageIcon.getIconWidth(),
				loginImageIcon.getIconHeight());
		login.setImage(loginImageIcon.getImage());

		idTextField = new TTextField();
		idTextField.setBounds(170, 160, 170, 20);
		idTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		login.add(idTextField);

		passwdTextField = new TPswdField();
		passwdTextField.setBounds(170, 227, 170, 20);
		passwdTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		login.add(passwdTextField);

		idLabel = new JLabel(new ImageIcon("images/用户名.png"));
		idLabel.setBounds(70, 158, 100, 20);
		login.add(idLabel);

		passwdLabel = new JLabel(new ImageIcon("images/密码.png"));
		passwdLabel.setBounds(70, 225, 100, 20);
		login.add(passwdLabel);

		ImageIcon setIpIcon = new ImageIcon("images/settings/修改头像.png");
		setIpButton = new ClickButton("images/settings/修改头像.png");
		setIpButton.setBounds(40, 80, setIpIcon.getIconWidth(),
				setIpIcon.getIconHeight());
		setIpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginPanel.fadeOut();
				mainFrame.changePanel("SetIpPanel");
			}
		});
		login.add(setIpButton);

		ImageIcon loginIcon = new ImageIcon("images/登录按钮.png");
		loginButton = new ClickButton("images/登录按钮.png");
		loginButton.setBounds(70, 290, loginIcon.getIconWidth(),
				loginIcon.getIconHeight());
		LoginListener loginListener = new LoginListener();
		loginButton.addActionListener(loginListener);
		login.add(loginButton);

		ImageIcon registerIcon = new ImageIcon("images/注册按钮.png");
		registerButton = new ClickButton("images/注册按钮.png");
		registerButton.setBounds(250, 290, registerIcon.getIconWidth(),
				registerIcon.getIconHeight());
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loginPanel.fadeOut();
				loginPanel.setNull();
				mainFrame.changePanel("RegisterPanel");
			}

		});
		login.add(registerButton);

		this.setVisible(false);
	}

	private void setNull() {
		idTextField.setText("");
		passwdTextField.setText("");
	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame, LoginBLService loginBLService) {
		this.mainFrame = mainFrame;
		this.loginBLService = loginBLService;
	}

	// 登录的动作，调用登录逻辑接口
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (idTextField.getText().equals("")
					|| passwdTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "用户名与密码不能为空！", "系统消息",
						JOptionPane.WARNING_MESSAGE);
			} else {
				loginBLService.login(idTextField.getText(),
						passwdTextField.getText());
			}
		}

	}
	
	public void alreadyLogin(){
		JOptionPane.showMessageDialog(null, "用户已经登录!", "登录",
				JOptionPane.ERROR_MESSAGE);
	}

	public void loginSuccess() {
		JOptionPane.showMessageDialog(null, "登陆成功!", "登录",
				JOptionPane.INFORMATION_MESSAGE);
		this.fadeOut();
		mainFrame.changePanel("OptionPanel");
	}

	public void loginFail() {
		JOptionPane.showMessageDialog(null, "用户名或密码不正确!", "登录",
				JOptionPane.ERROR_MESSAGE);
	}

}
