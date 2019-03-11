package resource;

import java.util.ArrayList;

public class Product_Accessable_Static {

	public static ArrayList<Catagory_Accessable> cateArr = new ArrayList<Catagory_Accessable>();

	public static ArrayList<Products_Accessable> proArr = new ArrayList<Products_Accessable>();

	public static ArrayList<Products_Accessable> proByCate = new ArrayList<Products_Accessable>();

	private static String cateName, proByCateS;

	private static String pidS, pnameS, ppriceS, pcateS, pretailgoodS, crateS;

	private static int pidN;

	public static String getProByCateS() {
		return proByCateS;
	}

	public static void setProByCateS(String proByCateS) {
		Product_Accessable_Static.proByCateS = proByCateS;
	}

	public static int getPidN() {
		return pidN;
	}

	public static void setPidN(int pidN) {
		Product_Accessable_Static.pidN = pidN;
	}

	public static String getCateName() {
		return cateName;
	}

	public static void setCateName(String cateName) {
		Product_Accessable_Static.cateName = cateName;
	}

	public static String getPidS() {
		return pidS;
	}

	public static void setPidS(String pidS) {
		Product_Accessable_Static.pidS = pidS;
	}

	public static String getPnameS() {
		return pnameS;
	}

	public static void setPnameS(String pnameS) {
		Product_Accessable_Static.pnameS = pnameS;
	}

	public static String getPpriceS() {
		return ppriceS;
	}

	public static void setPpriceS(String ppriceS) {
		Product_Accessable_Static.ppriceS = ppriceS;
	}

	public static String getPcateS() {
		return pcateS;
	}

	public static void setPcateS(String pcateS) {
		Product_Accessable_Static.pcateS = pcateS;
	}

	public static String getPretailgoodS() {
		return pretailgoodS;
	}

	public static void setPretailgoodS(String pretailgoodS) {
		Product_Accessable_Static.pretailgoodS = pretailgoodS;
	}

	public static String getCrateS() {
		return crateS;
	}

	public static void setCrateS(String crateS) {
		Product_Accessable_Static.crateS = crateS;
	}

}
