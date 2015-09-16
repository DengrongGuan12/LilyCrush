package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import businesslogicService.GameBLService;
import businesslogicService.LoginBLService;
import businesslogicService.SettingBLService;
import common.settingInfo.PersonalInfo;
import enums.Avatar;
import view.component.BackGroundPanel;
import view.component.ClickButton;
import view.component.TPswdField;

public class SettingsPanel extends BackGroundPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SettingBLService settingBLService;

	private MainFrame mainFrame;

	private SettingsPanel settingsPanel;

	private Font passwdFont;
	private Font labelFont;

	private ClickButton backButton;

	private BackGroundPanel avatarPanel;
	private BackGroundPanel settings;
	private JLabel nickNameLabel;
	private JLabel coinsLabel;
	private TPswdField passwdTextField;
	private TPswdField passwdConfirmTextField;
	private TPswdField oldPasswdTextField;
	private ClickButton changePswdButtonUp;
	private ClickButton changePswdButtonDown;
	private ClickButton changeAvatarButton;
	private ClickButton confirmButton;

	private JLabel currentAvatarLabel;
	private JLabel avatarFrameLabel;

	private ImageHelper imageHelper;
	private JLabel avatar0;
	private JLabel avatar1;
	private JLabel avatar2;
	private JLabel avatar3;
	private JLabel avatar4;
	private JLabel avatar5;
	private JLabel avatar6;
	private JLabel avatar7;
	private JLabel select0;
	private JLabel select1;
	private JLabel select2;
	private JLabel select3;
	private JLabel select4;
	private JLabel select5;
	private JLabel select6;
	private JLabel select7;
	private ClickButton confirmAvatarButton;
	private ClickButton cancelAvatarButton;

	private Avatar selectedAvatar;// 当前头像

	private BackGroundPanel changePswdPanel;

	private JLabel pswdFailLabel;
	private JLabel pswdSuccessLabel;
	private JLabel conPswdFailLabel;
	private JLabel conPswdSuccessLabel;

	private JLabel myNickNameJLabel;
	private JLabel myCoinsJLabel;

	private PersonalInfo personalInfo;

	private boolean isCorrectInput = false;

	public SettingsPanel() {
		initialUI();
		settingsPanel = this;
	}

	public void initialUI() {
		Font labelFont = new Font("宋体", Font.PLAIN, 25);

		ChangePanelListener changePanelListener = new ChangePanelListener();
		ButtonListener buttonListener = new ButtonListener();
		SelectListener selectListener = new SelectListener();
		passwdFont = new Font("微软雅黑", Font.PLAIN, 18);

		settings = this;
		ImageIcon settingsIcon = new ImageIcon("images/settings/个人信息.png");
		settings.setBounds(0, 0, settingsIcon.getIconWidth(),
				settingsIcon.getIconHeight());
		settings.setImage(settingsIcon.getImage());

		ImageIcon avatarPanelIcon = new ImageIcon("images/settings/头像面板.png");
		avatarPanel = new BackGroundPanel();
		avatarPanel.setBounds(0, 0, avatarPanelIcon.getIconWidth(),
				avatarPanelIcon.getIconHeight());
		avatarPanel.setImage(avatarPanelIcon.getImage());
		// avatarPanel.setAlpha(1.0f);
		avatarPanel.setVisible(false);
		settings.add(avatarPanel);

		ImageIcon selectIcon = new ImageIcon("images/settings/边框（红）.png");
		select0 = new JLabel(selectIcon);
		select0.setBounds(180, 115, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select0.setVisible(false);
		avatarPanel.add(select0);

		select1 = new JLabel(selectIcon);
		select1.setBounds(350, 115, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select1.setVisible(false);
		avatarPanel.add(select1);

		select2 = new JLabel(selectIcon);
		select2.setBounds(518, 115, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select2.setVisible(false);
		avatarPanel.add(select2);

		select3 = new JLabel(selectIcon);
		select3.setBounds(684, 115, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select3.setVisible(false);
		avatarPanel.add(select3);

		select4 = new JLabel(selectIcon);
		select4.setBounds(180, 290, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select4.setVisible(false);
		avatarPanel.add(select4);

		select5 = new JLabel(selectIcon);
		select5.setBounds(350, 290, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select5.setVisible(false);
		avatarPanel.add(select5);

		select6 = new JLabel(selectIcon);
		select6.setBounds(518, 290, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select6.setVisible(false);
		avatarPanel.add(select6);

		select7 = new JLabel(selectIcon);
		select7.setBounds(684, 290, selectIcon.getIconWidth(),
				selectIcon.getIconHeight());
		select7.setVisible(false);
		avatarPanel.add(select7);

		ImageIcon avatar0Icon = imageHelper.getAvatar(Avatar.defaultImg, 100,
				100);
		avatar0 = new JLabel(avatar0Icon);
		avatar0.setBounds(199, 135, 100, 100);
		avatar0.addMouseListener(selectListener);
		avatarPanel.add(avatar0);

		ImageIcon avatar1Icon = imageHelper.getAvatar(Avatar.hokain1, 100, 100);
		avatar1 = new JLabel(avatar1Icon);
		avatar1.setBounds(369, 135, 100, 100);
		avatar1.addMouseListener(selectListener);
		avatarPanel.add(avatar1);

		ImageIcon avatar2Icon = imageHelper.getAvatar(Avatar.hokain2, 100, 100);
		avatar2 = new JLabel(avatar2Icon);
		avatar2.setBounds(536, 135, 100, 100);
		avatar2.addMouseListener(selectListener);
		avatarPanel.add(avatar2);

		ImageIcon avatar3Icon = imageHelper.getAvatar(Avatar.hokain3, 100, 100);
		avatar3 = new JLabel(avatar3Icon);
		avatar3.setBounds(702, 135, 100, 100);
		avatar3.addMouseListener(selectListener);
		avatarPanel.add(avatar3);

		ImageIcon avatar4Icon = imageHelper.getAvatar(Avatar.hokain4, 100, 100);
		avatar4 = new JLabel(avatar4Icon);
		avatar4.setBounds(199, 312, 100, 100);
		avatar4.addMouseListener(selectListener);
		avatarPanel.add(avatar4);

		ImageIcon avatar5Icon = imageHelper.getAvatar(Avatar.hokain5, 100, 100);
		avatar5 = new JLabel(avatar5Icon);
		avatar5.setBounds(369, 312, 100, 100);
		avatar5.addMouseListener(selectListener);
		avatarPanel.add(avatar5);

		ImageIcon avatar6Icon = imageHelper.getAvatar(Avatar.hokain5, 100, 100);
		avatar6 = new JLabel(avatar6Icon);
		avatar6.setBounds(536, 312, 100, 100);
		avatar6.addMouseListener(selectListener);
		avatarPanel.add(avatar6);

		ImageIcon avatar7Icon = imageHelper.getAvatar(Avatar.hokain5, 100, 100);
		avatar7 = new JLabel(avatar7Icon);
		avatar7.setBounds(702, 312, 100, 100);
		avatar7.addMouseListener(selectListener);
		avatarPanel.add(avatar7);

		ImageIcon confirmAvatarButtonIcon = new ImageIcon(
				"images/settings/确定头像.png");
		confirmAvatarButton = new ClickButton("images/settings/确定头像.png");
		confirmAvatarButton.setBounds(520, 500,
				confirmAvatarButtonIcon.getIconWidth(),
				confirmAvatarButtonIcon.getIconHeight());
		confirmAvatarButton.addActionListener(buttonListener);
		avatarPanel.add(confirmAvatarButton);

		ImageIcon cancelAvatarButtonIcon = new ImageIcon(
				"images/settings/取消.png");
		cancelAvatarButton = new ClickButton("images/settings/取消.png");
		cancelAvatarButton.setBounds(680, 500,
				cancelAvatarButtonIcon.getIconWidth(),
				cancelAvatarButtonIcon.getIconHeight());
		cancelAvatarButton.addActionListener(buttonListener);
		avatarPanel.add(cancelAvatarButton);

		ImageIcon backButtonIcon = new ImageIcon("images/返回.png");
		backButton = new ClickButton("images/返回.png");
		backButton.setBounds(30, 30, backButtonIcon.getIconWidth(),
				backButtonIcon.getIconHeight());
		backButton.addActionListener(changePanelListener);
		settings.add(backButton);

		ImageIcon cabIcon = new ImageIcon("images/settings/修改头像.png");
		changeAvatarButton = new ClickButton("images/settings/修改头像.png");
		changeAvatarButton.setBounds(275, 215, cabIcon.getIconWidth(),
				cabIcon.getIconHeight());
		changeAvatarButton.addActionListener(buttonListener);
		settings.add(changeAvatarButton);

		ImageIcon avatarFrameIcon = new ImageIcon("images/settings/头像框.png");
		avatarFrameLabel = new JLabel(avatarFrameIcon);
		avatarFrameLabel.setBounds(180, 120, avatarFrameIcon.getIconWidth(),
				avatarFrameIcon.getIconHeight());
		settings.add(avatarFrameLabel);

		ImageIcon currentIcon = imageHelper.getAvatar(Avatar.defaultImg, 100,
				100);
		currentAvatarLabel = new JLabel();
		currentAvatarLabel.setIcon(currentIcon);
		currentAvatarLabel.setBounds(200, 140, 100, 100);
		settings.add(currentAvatarLabel);

		ImageIcon changePswdPanelIcon = new ImageIcon(
				"images/settings/密码修改.png");
		changePswdPanel = new BackGroundPanel();
		changePswdPanel.setBounds(110, 340, changePswdPanelIcon.getIconWidth(),
				changePswdPanelIcon.getIconHeight());
		changePswdPanel.setImage(changePswdPanelIcon.getImage());
		changePswdPanel.setVisible(false);
		settings.add(changePswdPanel);

		ConfirmPswdFocusListener cpfl = new ConfirmPswdFocusListener();

		oldPasswdTextField = new TPswdField();
		oldPasswdTextField.setBounds(230, 30, 170, 20);
		oldPasswdTextField.setForeground(Color.white);
		oldPasswdTextField.setFont(passwdFont);
		oldPasswdTextField.addFocusListener(cpfl);
		changePswdPanel.add(oldPasswdTextField);

		passwdTextField = new TPswdField();
		passwdTextField.setBounds(230, 75, 170, 20);
		passwdTextField.setForeground(Color.white);
		passwdTextField.setFont(passwdFont);
		passwdTextField.addFocusListener(cpfl);
		changePswdPanel.add(passwdTextField);

		passwdConfirmTextField = new TPswdField();
		passwdConfirmTextField.setBounds(230, 122, 170, 20);
		passwdConfirmTextField.setFont(passwdFont);
		passwdConfirmTextField.setForeground(Color.white);
		passwdConfirmTextField.addFocusListener(cpfl);
		changePswdPanel.add(passwdConfirmTextField);

		pswdFailLabel = new JLabel(new ImageIcon("images/fail.png"));
		pswdFailLabel.setBounds(460, 75, 20, 20);
		pswdFailLabel.setOpaque(false);
		changePswdPanel.add(pswdFailLabel);
		pswdFailLabel.setVisible(false);

		conPswdFailLabel = new JLabel(new ImageIcon("images/fail.png"));
		conPswdFailLabel.setBounds(460, 122, 20, 20);
		conPswdFailLabel.setOpaque(false);
		changePswdPanel.add(conPswdFailLabel);
		conPswdFailLabel.setVisible(false);

		pswdSuccessLabel = new JLabel(new ImageIcon("images/success.png"));
		pswdSuccessLabel.setBounds(460, 75, 20, 20);
		pswdSuccessLabel.setOpaque(false);
		changePswdPanel.add(pswdSuccessLabel);
		pswdSuccessLabel.setVisible(false);

		conPswdSuccessLabel = new JLabel(new ImageIcon("images/success.png"));
		conPswdSuccessLabel.setBounds(460, 122, 20, 20);
		conPswdSuccessLabel.setOpaque(false);
		changePswdPanel.add(conPswdSuccessLabel);
		conPswdSuccessLabel.setVisible(false);

		ImageIcon confirmButtonIcon = new ImageIcon("images/settings/确认修改.png");
		confirmButton = new ClickButton("images/settings/确认修改.png");
		confirmButton.setBounds(450, 180, confirmButtonIcon.getIconWidth(),
				confirmButtonIcon.getIconHeight());
		confirmButton.addActionListener(buttonListener);
		changePswdPanel.add(confirmButton);

		ImageIcon cpbdIcon = new ImageIcon("images/settings/修改密码（朝下）.png");
		changePswdButtonDown = new ClickButton("images/settings/修改密码（朝下）.png");
		changePswdButtonDown.setBounds(190, 300, cpbdIcon.getIconWidth(),
				cpbdIcon.getIconHeight());
		changePswdButtonDown.setVisible(false);
		changePswdButtonDown.addActionListener(buttonListener);
		settings.add(changePswdButtonDown);

		ImageIcon cpbuIcon = new ImageIcon("images/settings/修改密码（朝上）.png");
		changePswdButtonUp = new ClickButton("images/settings/修改密码（朝上）.png");
		changePswdButtonUp.setBounds(190, 300, cpbuIcon.getIconWidth(),
				cpbuIcon.getIconHeight());
		changePswdButtonUp.addActionListener(buttonListener);
		settings.add(changePswdButtonUp);

		ImageIcon nickNameIcon = new ImageIcon("images/settings/昵称.png");
		nickNameLabel = new JLabel(nickNameIcon);
		nickNameLabel.setBounds(330, 130, nickNameIcon.getIconWidth(),
				nickNameIcon.getIconHeight());
		nickNameLabel.setOpaque(false);
		settings.add(nickNameLabel);

		myNickNameJLabel = new JLabel();
		myNickNameJLabel.setForeground(Color.white);
		myNickNameJLabel.setFont(labelFont);
		myNickNameJLabel.setBounds(480, 150, 100, 20);
		myNickNameJLabel.setOpaque(false);
		settings.add(myNickNameJLabel);

		ImageIcon coinsIcon = new ImageIcon("images/settings/金币.png");
		coinsLabel = new JLabel();
		coinsLabel.setIcon(coinsIcon);
		coinsLabel.setBounds(350, 200, coinsIcon.getIconWidth(),
				coinsIcon.getIconHeight());
		coinsLabel.setOpaque(false);
		settings.add(coinsLabel);

		myCoinsJLabel = new JLabel();
		myCoinsJLabel.setForeground(Color.white);
		myCoinsJLabel.setFont(labelFont);
		myCoinsJLabel.setBounds(400, 210, 100, 20);
		myCoinsJLabel.setOpaque(false);
		settings.add(myCoinsJLabel);

		this.setVisible(false);
	}

	public void setPersonalInfo(PersonalInfo personalInfo) {
		ImageIcon avatarIcon = ImageHelper.getAvatar(personalInfo.getAvatar(),
				100, 100);
		currentAvatarLabel.setIcon(avatarIcon);

		myNickNameJLabel.setText(personalInfo.getNickname());

		myCoinsJLabel.setText(String.valueOf(personalInfo.getCoins()));
	}

	private void setNull() {
		oldPasswdTextField.setText("");
		passwdTextField.setText("");
		passwdConfirmTextField.setText("");
		pswdFailLabel.setVisible(false);
		pswdSuccessLabel.setVisible(false);
		conPswdFailLabel.setVisible(false);
		conPswdSuccessLabel.setVisible(false);
	}

	private boolean checkNull() {
		if (oldPasswdTextField.getText().equals("")
				|| passwdTextField.getText().equals("")
				|| passwdConfirmTextField.getText().equals("")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkSelected() {
		if (select0.isVisible() || select1.isVisible() || select2.isVisible()
				|| select3.isVisible() || select4.isVisible()
				|| select5.isVisible() || select6.isVisible()
				|| select7.isVisible()) {
			return true;
		} else {
			return false;
		}
	}

	private void setUnSelected() {
		select0.setVisible(false);
		select1.setVisible(false);
		select2.setVisible(false);
		select3.setVisible(false);
		select4.setVisible(false);
		select5.setVisible(false);
		select6.setVisible(false);
		select7.setVisible(false);
	}

	class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == changePswdButtonUp) {
				changePswdButtonUp.setVisible(false);
				changePswdButtonDown.setVisible(true);
				changePswdPanel.fadeIn();
			} else if (e.getSource() == changePswdButtonDown) {
				changePswdButtonDown.setVisible(false);
				changePswdButtonUp.setVisible(true);
				changePswdPanel.fadeOut();
				setNull();
			} else if (e.getSource() == confirmButton) {
				if (isCorrectInput == true) {
					settingBLService.changePasswd(oldPasswdTextField.getText(),
							passwdTextField.getText());
				} else {
					JOptionPane.showMessageDialog(null, "密码信息不完整或不正确，请重新填写!",
							"系统消息", JOptionPane.ERROR_MESSAGE);
				}
			} else if (e.getSource() == changeAvatarButton) {
				avatarPanel.fadeIn();
			} else if (e.getSource() == confirmAvatarButton) {
				if (checkSelected() == false) {
					JOptionPane.showMessageDialog(null, "未选择头像，请选择一个头像!",
							"系统消息", JOptionPane.ERROR_MESSAGE);
				} else {
					// currentAvatarLabel.setIcon(imageHelper.getAvatar(selectedAvatar,
					// 100, 100));
					settingBLService.changeAvatar(selectedAvatar);
					avatarPanel.fadeOut();
					setUnSelected();
				}
			} else if (e.getSource() == cancelAvatarButton) {
				avatarPanel.fadeOut();
				setUnSelected();
			}
		}

	}

	private void setSelectedAvatar(Avatar avatar) {
		this.selectedAvatar = avatar;
	}

	class SelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			setUnSelected();
			// TODO Auto-generated method stub
			if (e.getSource() == avatar0) {
				select0.setVisible(true);
				setSelectedAvatar(Avatar.defaultImg);
			} else if (e.getSource() == avatar1) {
				select1.setVisible(true);
				setSelectedAvatar(Avatar.hokain1);
			} else if (e.getSource() == avatar2) {
				select2.setVisible(true);
				setSelectedAvatar(Avatar.hokain2);
			} else if (e.getSource() == avatar3) {
				select3.setVisible(true);
				setSelectedAvatar(Avatar.hokain3);
			} else if (e.getSource() == avatar4) {
				select4.setVisible(true);
				setSelectedAvatar(Avatar.hokain4);
			} else if (e.getSource() == avatar5) {
				select5.setVisible(true);
				setSelectedAvatar(Avatar.hokain5);
			} else if (e.getSource() == avatar6) {
				select6.setVisible(true);
				setSelectedAvatar(Avatar.hokain5);
			} else if (e.getSource() == avatar7) {
				select7.setVisible(true);
				setSelectedAvatar(Avatar.hokain5);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

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

	public void wrongPasswd() {
		JOptionPane.showMessageDialog(null, "原密码不正确!", "系统消息 ",
				JOptionPane.ERROR_MESSAGE);
	}

	public void changePasswdSuc() {
		JOptionPane.showMessageDialog(null, "密码修改成功!", "系统消息",
				JOptionPane.INFORMATION_MESSAGE);
		setNull();
	}

	public void changeAvatarSuc() {
		JOptionPane.showMessageDialog(null, "头像修改成功!", "系统消息",
				JOptionPane.INFORMATION_MESSAGE);
		ImageIcon newAvatar = ImageHelper.getAvatar(selectedAvatar, 100, 100);
		currentAvatarLabel.setIcon(newAvatar);
	}

	class ChangePanelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			settingsPanel.fadeOut();
			if (e.getSource() == backButton) {
				mainFrame.changePanel("OptionPanel");
			}
		}

	}

	// 设置MainFrame的引用，用于发送面板切换的信息
	public void setMainFrame(MainFrame mainFrame,
			SettingBLService settingBLService) {
		this.mainFrame = mainFrame;
		this.settingBLService = settingBLService;
	}

}
