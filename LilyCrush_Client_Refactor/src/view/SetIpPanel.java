package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import businesslogic.managers.GameManager;
import businesslogicService.GameBLService;
import view.LoginPanel.LoginListener;
import view.component.BackGroundPanel;
import view.component.ClickButton;
import view.component.GameFrame;
import view.component.TPswdField;
import view.component.TTextField;

public class SetIpPanel extends BackGroundPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GameBLService gameBLService;
	private GameManager gameManager;

	private MainFrame mainFrame;
	private SetIpPanel setIpPanel;

	private TTextField ipTextField;
	private TTextField confirmIpTextField;

	private ClickButton confirmButton;
	private ClickButton backButton;

	private JLabel ipFailLabel;
	private JLabel ipSuccessLabel;
	private JLabel conIpFailLabel;
	private JLabel conIpSuccessLabel;

	private boolean isCorrectInput;

	public SetIpPanel() {
		initialUI();
		setIpPanel = this;
	}

	private void initialUI() {
		ConfirmIpFocusListener confirmIpFocusListener = new ConfirmIpFocusListener();
		SetIpListener setIpListener = new SetIpListener();

		ImageIcon setIpImageIcon = new ImageIcon("images/ip/ip地址.png");
		this.setBounds(0, -20, setIpImageIcon.getIconWidth(),
				setIpImageIcon.getIconHeight());
		this.setImage(setIpImageIcon.getImage());

		ipFailLabel = new JLabel(new ImageIcon("images/fail.png"));
		ipFailLabel.setBounds(650, 280, 20, 20);
		ipFailLabel.setOpaque(false);
		this.add(ipFailLabel);
		ipFailLabel.setVisible(false);

		conIpFailLabel = new JLabel(new ImageIcon("images/fail.png"));
		conIpFailLabel.setBounds(650, 347, 20, 20);
		conIpFailLabel.setOpaque(false);
		this.add(conIpFailLabel);
		conIpFailLabel.setVisible(false);

		ipSuccessLabel = new JLabel(new ImageIcon("images/success.png"));
		ipSuccessLabel.setBounds(650, 280, 20, 20);
		ipSuccessLabel.setOpaque(false);
		this.add(ipSuccessLabel);
		ipSuccessLabel.setVisible(false);

		conIpSuccessLabel = new JLabel(new ImageIcon("images/success.png"));
		conIpSuccessLabel.setBounds(650, 347, 20, 20);
		conIpSuccessLabel.setOpaque(false);
		this.add(conIpSuccessLabel);
		conIpSuccessLabel.setVisible(false);

		ipTextField = new TTextField();
		ipTextField.setBounds(450, 280, 170, 20);
		ipTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		ipTextField.addFocusListener(confirmIpFocusListener);
		this.add(ipTextField);

		confirmIpTextField = new TTextField();
		confirmIpTextField.setBounds(450, 347, 170, 20);
		confirmIpTextField.setFont(new Font("宋体", Font.PLAIN, 18));
		confirmIpTextField.addFocusListener(confirmIpFocusListener);
		this.add(confirmIpTextField);

		ImageIcon confirmIcon = new ImageIcon("images/ip/确认ip.png");
		confirmButton = new ClickButton("images/ip/确认ip.png");
		confirmButton.setBounds(380, 410, confirmIcon.getIconWidth(),
				confirmIcon.getIconHeight());
		confirmButton.addActionListener(setIpListener);
		this.add(confirmButton);

		ImageIcon backIcon = new ImageIcon("images/ip/取消.png");
		backButton = new ClickButton("images/ip/取消.png");
		backButton.setBounds(550, 410, backIcon.getIconWidth(),
				backIcon.getIconHeight());
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}

		});
		this.add(backButton);

		this.setVisible(false);
	}

	class SetIpListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == confirmButton) {
				if (isCorrectInput == false) {
					JOptionPane.showMessageDialog(null, "两次输入ip地址不一致，请重新填写！",
							"系统消息", JOptionPane.ERROR_MESSAGE);
				}else{
					// 修改ip地址方法
					gameManager.setIP(ipTextField.getText());
					gameManager.connectToServer();
					setIpPanel.fadeOut();
					mainFrame.changePanel("LoginPanel");
				}
			}
		}

	}

	private void setNull() {
		ipTextField.setText("");
		confirmIpTextField.setText("");
		ipFailLabel.setVisible(false);
		ipSuccessLabel.setVisible(false);
		conIpFailLabel.setVisible(false);
		conIpSuccessLabel.setVisible(false);
	}

	class ConfirmIpFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			isCorrectInput = false;
			if (e.getSource() == ipTextField) {
				ipFailLabel.setVisible(false);
				ipSuccessLabel.setVisible(false);
			} else if (e.getSource() == confirmIpTextField) {
				conIpFailLabel.setVisible(false);
				conIpSuccessLabel.setVisible(false);
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

			if (ipTextField.getText().equals(confirmIpTextField.getText())
					&& ipTextField.getText().length() > 0) {
				ipFailLabel.setVisible(false);
				ipSuccessLabel.setVisible(true);
				conIpFailLabel.setVisible(false);
				conIpSuccessLabel.setVisible(true);
				isCorrectInput = true;
			} else if (!ipTextField.getText().equals(
					confirmIpTextField.getText())
					&& ipTextField.getText().equals("")) {
				ipFailLabel.setVisible(true);
				ipSuccessLabel.setVisible(false);
				conIpFailLabel.setVisible(true);
				conIpSuccessLabel.setVisible(false);
			} else if (ipTextField.getText().equals("")
					&& ipTextField.getText().equals(
							confirmIpTextField.getText())) {
				ipFailLabel.setVisible(false);
				ipSuccessLabel.setVisible(false);
				conIpFailLabel.setVisible(false);
				conIpSuccessLabel.setVisible(false);
			} else if (!ipTextField.getText().equals(
					confirmIpTextField.getText())
					&& ipTextField.getText().length() > 0) {
				ipFailLabel.setVisible(false);
				ipSuccessLabel.setVisible(true);
				conIpFailLabel.setVisible(true);
				conIpSuccessLabel.setVisible(false);
			}
		}

	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame, GameBLService gameBLService,GameManager gameManager) {
		this.mainFrame = mainFrame;
		this.gameBLService = gameBLService;
		this.gameManager = gameManager;
	}

}
