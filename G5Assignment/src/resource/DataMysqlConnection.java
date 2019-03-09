package resource;

public class DataMysqlConnection {
	
	private static String url,user,password,dataBase;

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		DataMysqlConnection.url = url;
	}

	public static String getUser() {
		return user;
	}

	public static void setUser(String user) {
		DataMysqlConnection.user = user;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		DataMysqlConnection.password = password;
	}

	public static String getDataBase() {
		return dataBase;
	}

	public static void setDataBase(String dataBase) {
		DataMysqlConnection.dataBase = dataBase;
	}
	
	

}
