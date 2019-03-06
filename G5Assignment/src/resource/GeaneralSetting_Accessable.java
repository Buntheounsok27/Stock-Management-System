package resource;

public class GeaneralSetting_Accessable {
	
	private static String companyName,dateTime,reciept;

	public static String getCompanyName() {
		return companyName;
	}

	public static void setCompanyName(String companyName) {
		GeaneralSetting_Accessable.companyName = companyName;
	}

	public static String getDateTime() {
		return dateTime;
	}

	public static void setDateTime(String dateTime) {
		GeaneralSetting_Accessable.dateTime = dateTime;
	}

	public static String getReciept() {
		return reciept;
	}

	public static void setReciept(String reciept) {
		GeaneralSetting_Accessable.reciept = reciept;
	}
	
	

}
