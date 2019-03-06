package resource;

public class Sale {
	
	private String id,sale_date,emID,cusID;
	
	private static String idS,sale_dateS,emIDS,cusIDS;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSale_date() {
		return sale_date;
	}

	public void setSale_date(String sale_date) {
		this.sale_date = sale_date;
	}

	public String getEmID() {
		return emID;
	}

	public void setEmID(String emID) {
		this.emID = emID;
	}

	public String getCusID() {
		return cusID;
	}

	public void setCusID(String cusID) {
		this.cusID = cusID;
	}

	public static String getIdS() {
		return idS;
	}

	public static void setIdS(String idS) {
		Sale.idS = idS;
	}

	public static String getSale_dateS() {
		return sale_dateS;
	}

	public static void setSale_dateS(String sale_dateS) {
		Sale.sale_dateS = sale_dateS;
	}

	public static String getEmIDS() {
		return emIDS;
	}

	public static void setEmIDS(String emIDS) {
		Sale.emIDS = emIDS;
	}

	public static String getCusIDS() {
		return cusIDS;
	}

	public static void setCusIDS(String cusIDS) {
		Sale.cusIDS = cusIDS;
	}
	
	

}
