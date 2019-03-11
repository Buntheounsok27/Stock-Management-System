package resource;

import java.util.ArrayList;

public class Person_Accessable_Static {

	public static ArrayList<Person_Accessable> accessables = new ArrayList<>();
	public static ArrayList<AccountType_Accessable> accTypeArr = new ArrayList<>();

	private static String accID, username, password, position, accIDN, accTypeS;
	private static boolean settingperm, reportperm, stockperm, customerperm, cashperm;

	public static ArrayList<Person_Accessable> getAccessables() {
		return accessables;
	}

	public static void setAccessables(ArrayList<Person_Accessable> accessables) {
		Person_Accessable_Static.accessables = accessables;
	}

	public static String getAccID() {
		return accID;
	}

	public static void setAccID(String accID) {
		Person_Accessable_Static.accID = accID;
	}

	public static String getAccIDN() {
		return accIDN;
	}

	public static void setAccIDN(String accID) {
		Person_Accessable_Static.accIDN = accID;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		Person_Accessable_Static.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Person_Accessable_Static.password = password;
	}

	public static String getPosition() {
		return position;
	}

	public static void setPosition(String position) {
		Person_Accessable_Static.position = position;
	}

	public static boolean isSettingperm() {
		return settingperm;
	}

	public static void setSettingperm(boolean settingperm) {
		Person_Accessable_Static.settingperm = settingperm;
	}

	public static boolean isReportperm() {
		return reportperm;
	}

	public static void setReportperm(boolean reportperm) {
		Person_Accessable_Static.reportperm = reportperm;
	}

	public static boolean isStockperm() {
		return stockperm;
	}

	public static void setStockperm(boolean stockperm) {
		Person_Accessable_Static.stockperm = stockperm;
	}

	public static boolean isCustomerperm() {
		return customerperm;
	}

	public static void setCustomerperm(boolean customerperm) {
		Person_Accessable_Static.customerperm = customerperm;
	}

	public static boolean isCashperm() {
		return cashperm;
	}

	public static void setCashperm(boolean cashperm) {
		Person_Accessable_Static.cashperm = cashperm;
	}

	public static String getAccTypeS() {
		return accTypeS;
	}

	public static void setAccTypeS(String accTypeS) {
		Person_Accessable_Static.accTypeS = accTypeS;
	}

}
