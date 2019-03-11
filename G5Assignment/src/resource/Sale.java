package resource;

public class Sale {

	private String id, sale_date, emID, cusID,totalAmount,cashIN;

	private static String idS, sale_dateS, emIDS, cusIDS,IDN,totalAmountS,cashINS;

	
	
	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCashIN() {
		return cashIN;
	}

	public void setCashIN(String cashIN) {
		this.cashIN = cashIN;
	}

	public static String getIDN() {
		return IDN;
	}

	public static void setIDN(String iDN) {
		IDN = iDN;
	}

	public static String getTotalAmountS() {
		return totalAmountS;
	}

	public static void setTotalAmountS(String totalAmountS) {
		Sale.totalAmountS = totalAmountS;
	}

	public static String getCashINS() {
		return cashINS;
	}

	public static void setCashINS(String cashINS) {
		Sale.cashINS = cashINS;
	}

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
