package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import businesslogicService.GameBLService;
import businesslogicService.RegisterBLService;

import view.component.BackGroundPanel;
import view.component.ClickButton;
import view.component.TPswdField;
import view.component.TTextField;

public class RegisterPanel extends BackGroundPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RegisterBLService registerBLService;

	private MainFrame mainFrame;
	private RegisterPanel registerPanel;

	private BackGroundPanel register;
	private TTextField idTextField;
	private TTextField nameTextField;
	private TPswdField passwdTextField;
	private TPswdField passwdConfirmTextField;
	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel passwdLabel;
	private JLabel passwdConfirmLabel;
	private ClickButton loginButton;
	private ClickButton registerButton;

	private JLabel pswdFailLabel;
	private JLabel pswdSuccessLabel;
	private JLabel conPswdFailLabel;
	private JLabel conPswdSuccessLabel;

	private boolean isCorrectInput = false;

	public RegisterPanel() {
		initialUI();
		registerPanel = this;
	}

	public void initialUI() {
		register = this;
		ImageIcon loginImageIcon = new ImageIcon("images/ע��.png");
		register.setBounds(280, 100, loginImageIcon.getIconWidth(),
				loginImageIcon.getIconHeight());
		register.setImage(loginImageIcon.getImage());

		pswdFailLabel = new JLabel(new ImageIcon("images/fail.png"));
		pswdFailLabel.setBounds(380, 190, 20, 20);
		pswdFailLabel.setOpaque(false);
		register.add(pswdFailLabel);
		pswdFailLabel.setVisible(false);

		conPswdFailLabel = new JLabel(new ImageIcon("images/fail.png"));
		conPswdFailLabel.setBounds(380, 240, 20, 20);
		conPswdFailLabel.setOpaque(false);
		register.add(conPswdFailLabel);
		conPswdFailLabel.setVisible(false);

		pswdSuccessLabel = new JLabel(new ImageIcon("images/success.png"));
		pswdSuccessLabel.setBounds(380, 190, 20, 20);
		pswdSuccessLabel.setOpaque(false);
		register.add(pswdSuccessLabel);
		pswdSuccessLabel.setVisible(false);

		conPswdSuccessLabel = new JLabel(new ImageIcon("images/success.png"));
		conPswdSuccessLabel.setBounds(380, 240, 20, 20);
		conPswdSuccessLabel.setOpaque(false);
		register.add(conPswdSuccessLabel);
		conPswdSuccessLabel.setVisible(false);

		idTextField = new TTextField();
		idTextField.setBounds(170, 90, 170, 20);
		idTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		register.add(idTextField);

		nameTextField = new TTextField();
		nameTextField.setBounds(170, 140, 170, 20);
		nameTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		register.add(nameTextField);

		ConfirmPswdFocusListener cpfl = new ConfirmPswdFocusListener();

		passwdTextField = new TPswdField();
		passwdTextField.setBounds(170, 190, 170, 20);
		passwdTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		passwdTextField.addFocusListener(cpfl);
		register.add(passwdTextField);

		passwdConfirmTextField = new TPswdField();
		passwdConfirmTextField.setBounds(170, 240, 170, 20);
		passwdConfirmTextField.setFont(new Font("΢���ź�", Font.PLAIN, 18));
		passwdConfirmTextField.addFocusListener(cpfl);
		register.add(passwdConfirmTextField);

		idLabel = new JLabel(new ImageIcon("images/�û���.png"));
		idLabel.setBounds(20, 90, 170, 20);
		register.add(idLabel);

		nameLabel = new JLabel(new ImageIcon("images/�ǳ�.png"));
		nameLabel.setBounds(20, 140, 170, 20);
		register.add(nameLabel);

		passwdLabel = new JLabel(new ImageIcon("images/����.png"));
		passwdLabel.setBounds(20, 190, 170, 20);
		register.add(passwdLabel);

		passwdConfirmLabel = new JLabel(new ImageIcon("images/����ȷ��.png"));
		passwdConfirmLabel.setBounds(20, 242, 170, 20);
		register.add(passwdConfirmLabel);

		ImageIcon reLoginIcon = new ImageIcon("images/���ص�¼��ť.png");
		loginButton = new ClickButton("images/���ص�¼��ť.png");
		loginButton.setBounds(70, 300, reLoginIcon.getIconWidth(),
				reLoginIcon.getIconHeight());
		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				registerPanel.fadeOut();
				registerPanel.setNull();
				mainFrame.changePanel("LoginPanel");
			}

		});
		register.add(loginButton);

		RegisterListener registerListener = new RegisterListener();
		ImageIcon registerIcon = new ImageIcon("images/ע�ᰴť.png");
		registerButton = new ClickButton("images/ע�ᰴť.png");
		registerButton.setBounds(250, 300, registerIcon.getIconWidth(),
				registerIcon.getIconHeight());
		registerButton.addActionListener(registerListener);
		register.add(registerButton);

		this.setVisible(false);
	}

	// ����û����ǳ��Ƿ�Ϊ��
	public boolean checkNull() {
		if (idTextField.getText().equals("")
				|| nameTextField.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public void registerSuccess() {
		JOptionPane.showMessageDialog(null, "ע��ɹ�!", "ע��",
				JOptionPane.INFORMATION_MESSAGE);
		this.fadeOut();
		mainFrame.changePanel("LoginPanel");
	}

	public void registerFail() {
		JOptionPane.showMessageDialog(null, "�û����Ѵ��ڣ�ע��ʧ��!", "ע��",
				JOptionPane.ERROR_MESSAGE);
	}

	class RegisterListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (isCorrectInput == false) {
				JOptionPane.showMessageDialog(null, "�û���Ϣ����������ȷ����������д!", "ע��",
						JOptionPane.ERROR_MESSAGE);
			} else {
				registerBLService.register(idTextField.getText(),
						passwdTextField.getText(), nameTextField.getText());
			}
		}

	}

	private void setNull() {
		idTextField.setText("");
		nameTextField.setText("");
		passwdTextField.setText("");
		passwdConfirmTextField.setText("");
		pswdFailLabel.setVisible(false);
		pswdSuccessLabel.setVisible(false);
		conPswdFailLabel.setVisible(false);
		conPswdSuccessLabel.setVisible(false);
	}

	class ConfirmPswdFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			isCorrectInput = false;
			if (e.getSource() == passwdTextField) {
				pswdFailLabel.setVisible(false);
				pswdSuccessLabel.setVisible(false);
			} else if (e.getSource() == passwdConfirmTextField) {
				conPswdFailLabel.setVisible(false);
				conPswdSuccessLabel.setVisible(false);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

			if (passwdTextField.getText().equals(
					passwdConfirmTextField.getText())
					&& passwdTextField.getText().length() > 0) {
				pswdFailLabel.setVisible(false);
				pswdSuccessLabel.setVisible(true);
				conPswdFailLabel.setVisible(false);
				conPswdSuccessLabel.setVisible(true);
				if (checkNull() == true) {
					isCorrectInput = true;
				}
			} else if (!passwdTextField.getText().equals(
					passwdConfirmTextField.getText())
					&& passwdTextField.getText().equals("")) {
				pswdFailLabel.setVisible(true);
				pswdSuccessLabel.setVisible(false);
				conPswdFailLabel.setVisible(true);
				conPswdSuccessLabel.setVisible(false);
			} else if (passwdTextField.getText().equals("")
					&& passwdTextField.getText().equals(
							passwdConfirmTextField.getText())) {
				pswdFailLabel.setVisible(false);
				pswdSuccessLabel.setVisible(false);
				conPswdFailLabel.setVisible(false);
				conPswdSuccessLabel.setVisible(false);
			} else if (!passwdTextField.getText().equals(
					passwdConfirmTextField.getText())
					&& passwdTextField.getText().length() > 0) {
				pswdFailLabel.setVisible(false);
				pswdSuccessLabel.setVisible(true);
				conPswdFailLabel.setVisible(true);
				conPswdSuccessLabel.setVisible(false);
			}
		}

	}

	// ����MainFrame�����ã����ڷ�������л�����Ϣ
	public void setMainFrame(MainFrame mainFrame,
			RegisterBLService registerBLService) {
		this.mainFrame = mainFrame;
		this.registerBLService = registerBLService;
	}

}
