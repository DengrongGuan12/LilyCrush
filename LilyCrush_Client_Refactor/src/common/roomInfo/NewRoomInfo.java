package common.roomInfo;

import java.util.ArrayList;

import common.Info;
import common.settingInfo.PersonalInfo;
import enums.Message;

public class NewRoomInfo extends Info{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private PersonalInfo hostInfo;
	public PersonalInfo getHostInfo() {
		return hostInfo;
	}
	public void setHostInfo(PersonalInfo hostInfo) {
		this.hostInfo = hostInfo;
	}
	private ArrayList<PersonalInfo> personalInfos=new ArrayList<PersonalInfo>();
	public ArrayList<PersonalInfo> getPersonalInfos() {
		return personalInfos;
	}
	public void setPersonalInfos(ArrayList<PersonalInfo> personalInfos) {
		this.personalInfos = personalInfos;
	}
	public NewRoomInfo(ArrayList<PersonalInfo> personalInfos,PersonalInfo hostInfo){
		super(Message.refreshRoomInfo);
		this.setPersonalInfos(personalInfos);
		this.setHostInfo(hostInfo);
		
	}

}
