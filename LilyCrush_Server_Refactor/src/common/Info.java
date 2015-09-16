package common;

import java.io.Serializable;

import enums.Message;

/*
 * ������Ϣ����ĸ���
 */
public class Info implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Message message;
	public Info(Message message){
		this.setMessage(message);
		
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	

}
