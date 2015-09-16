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
		ImageIcon loginImageIcon = new ImageIcon("images/��½.png");
		login.setBounds(280, 100, loginImageIcon.getIconWidth(),
				loginImageIcon.getIconHeight());
		login.setImage(loginImageIcon.getImage());

		idTextField = new TTextField();
		idTextField.setBounds(170, 160, 170, 20);
		idTextField.setFont(new Font("����", Font.PLAIN, 18));
		login.add(idTextField);

		passwdTextField = new TPswdField();
		passwdTextField.setBounds(170, 227, 170, 20);
		passwdTextField.setFont(new Font("����", Font.PLAIN, 18));
		login.add(passwdTextField);

		idLabel = new JLabel(new ImageIcon("images/�û���.png"));
		idLabel.setBounds(70, 158, 100, 20);
		login.add(idLabel);

		passwdLabel = new JLabel(new ImageIcon("images/����.png"));
		passwdLabel.setBounds(70, 225, 100, 20);
		login.add(passwdLabel);

		ImageIcon setIpIcon = new ImageIcon("images/settings/�޸�ͷ��.png");
		setIpButton = new ClickButton("images/settings/�޸�ͷ��.png");
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

		ImageIcon loginIcon = new ImageIcon("images/��¼��ť.png");
		loginButton = new ClickButton("images/��¼��ť.png");
		loginButton.setBounds(70, 290, loginIcon.getIconWidth(),
				loginIcon.getIconHeight());
		LoginListener loginListener = new LoginListener();
		loginButton.addActionListener(loginListener);
		login.add(loginButton);

		ImageIcon registerIcon = new ImageIcon("images/ע�ᰴť.png");
		registerButton = new ClickButton("images/ע�ᰴť.png");
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

	// ����MainFrame�����ã����ڷ�������л�����Ϣ
	public void setMainFrame(MainFrame mainFrame, LoginBLService loginBLService) {
		this.mainFrame = mainFrame;
		this.loginBLService = loginBLService;
	}

	// ��¼�Ķ��������õ�¼�߼��ӿ�
	class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (idTextField.getText().equals("")
					|| passwdTextField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "�û��������벻��Ϊ�գ�", "ϵͳ��Ϣ",
						JOptionPane.WARNING_MESSAGE);
			} else {
				loginBLService.login(idTextField.getText(),
						passwdTextField.getText());
			}
		}

	}
	
	public void alreadyLogin(){
		JOptionPane.showMessageDialog(null, "�û��Ѿ���¼!", "��¼",
				JOptionPane.ERROR_MESSAGE);
	}

	public void loginSuccess() {
		JOptionPane.showMessageDialog(null, "��½�ɹ�!", "��¼",
				JOptionPane.INFORMATION_MESSAGE);
		this.fadeOut();
		mainFrame.changePanel("OptionPanel");
	}

	public void loginFail() {
		JOptionPane.showMessageDialog(null, "�û��������벻��ȷ!", "��¼",
				JOptionPane.ERROR_MESSAGE);
	}

}