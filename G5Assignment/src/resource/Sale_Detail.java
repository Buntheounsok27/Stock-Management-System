package resource;

import java.util.ArrayList;

public class Sale_Detail {
	
	public static ArrayList<Sale_Detail> sale_Details_Arr = new ArrayList<>();
	
	private String id,saleID,proID;
	private int qty;
	
	private static String idS,saleIDS,proIDS;
	private static int qtyS;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSaleID() {
		return saleID;
	}
	public void setSaleID(String saleID) {
		this.saleID = saleID;
	}
	public String getProID() {
		return proID;
	}
	public void setProID(String proID) {
		this.proID = proID;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public static String getIdS() {
		return idS;
	}
	public static void setIdS(String idS) {
		Sale_Detail.idS = idS;
	}
	public static String getSaleIDS() {
		return saleIDS;
	}
	public static void setSaleIDS(String saleIDS) {
		Sale_Detail.saleIDS = saleIDS;
	}
	public static String getProIDS() {
		return proIDS;
	}
	public static void setProIDS(String proIDS) {
		Sale_Detail.proIDS = proIDS;
	}
	public static int getQtyS() {
		return qtyS;
	}
	public static void setQtyS(int qtyS) {
		Sale_Detail.qtyS = qtyS;
	}
	
	

}
