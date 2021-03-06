package common.registerInfo;

import common.Info;

import enums.Message;

public class RegInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String passwd;
	private String nickname;
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public RegInfo(String name,String passwd,String nickname){
		super(Message.register);
		this.setName(name);
		this.setPasswd(passwd);
		this.setNickname(nickname);
		
		
	}

}
