package resource;

import java.util.ArrayList;

public class UserPerforment {
	
	private String userID,userName,loginDate,loginTime,logoutTime,LogoutDate,Saled;
	
	private static String userIDS,userNameS,loginDateS,loginTimeS,logoutTimeS,LogoutDateS,SaledS;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public static String getUserIDS() {
		return userIDS;
	}

	public static void setUserIDS(String userIDS) {
		UserPerforment.userIDS = userIDS;
	}

	public static ArrayList<UserPerforment> userPerformentsArr = new ArrayList<UserPerforment>();
	
	

	public static String getUserNameS() {
		return userNameS;
	}

	public static void setUserNameS(String userNameS) {
		UserPerforment.userNameS = userNameS;
	}

	public static String getLoginDateS() {
		return loginDateS;
	}

	public static void setLoginDateS(String loginDateS) {
		UserPerforment.loginDateS = loginDateS;
	}

	public static String getLoginTimeS() {
		return loginTimeS;
	}

	public static void setLoginTimeS(String loginTimeS) {
		UserPerforment.loginTimeS = loginTimeS;
	}

	public static String getLogoutTimeS() {
		return logoutTimeS;
	}

	public static void setLogoutTimeS(String logoutTimeS) {
		UserPerforment.logoutTimeS = logoutTimeS;
	}

	public static String getLogoutDateS() {
		return LogoutDateS;
	}

	public static void setLogoutDateS(String logoutDateS) {
		LogoutDateS = logoutDateS;
	}

	public static String getSaledS() {
		return SaledS;
	}

	public static void setSaledS(String saledS) {
		SaledS = saledS;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getLogoutDate() {
		return LogoutDate;
	}

	public void setLogoutDate(String logoutDate) {
		LogoutDate = logoutDate;
	}

	public String getSaled() {
		return Saled;
	}

	public void setSaled(String saled) {
		Saled = saled;
	}
	
	

}
