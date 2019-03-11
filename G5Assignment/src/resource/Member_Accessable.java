package resource;

import java.util.ArrayList;

public class Member_Accessable {

	private String id, memberType;
	private double discount;

	public static ArrayList<Member_Accessable> member_Accessables_Arr = new ArrayList<Member_Accessable>();

	private static String idS, memberTypeS;
	private static double discountS;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public static String getIdS() {
		return idS;
	}

	public static void setIdS(String idS) {
		Member_Accessable.idS = idS;
	}

	public static String getMemberTypeS() {
		return memberTypeS;
	}

	public static void setMemberTypeS(String memberTypeS) {
		Member_Accessable.memberTypeS = memberTypeS;
	}

	public static double getDiscountS() {
		return discountS;
	}

	public static void setDiscountS(double discountS) {
		Member_Accessable.discountS = discountS;
	}

}
