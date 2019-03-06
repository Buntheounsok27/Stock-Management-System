package resource;

import java.util.ArrayList;

public class Customer_Accessable {
	
	private String id,name,phone,address,country,memberType,discount;
	private static String idS,nameS,phoneS,addressS,countryS,memberTypeS,maxID,idN,discountS;
	
	
	
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public static String getDiscountS() {
		return discountS;
	}
	public static void setDiscountS(String discountS) {
		Customer_Accessable.discountS = discountS;
	}
	public static String getMaxID() {
		return maxID;
	}
	public static void setMaxID(String maxID) {
		Customer_Accessable.maxID = maxID;
	}
	public static String getIdN() {
		return idN;
	}
	public static void setIdN(String idN) {
		Customer_Accessable.idN = idN;
	}
	public static ArrayList<Customer_Accessable> customer_Accessables_Arr = new ArrayList<Customer_Accessable>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getMemberType() {
		return memberType;
	}
	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}
	public static String getIdS() {
		return idS;
	}
	public static void setIdS(String idS) {
		Customer_Accessable.idS = idS;
	}
	public static String getNameS() {
		return nameS;
	}
	public static void setNameS(String nameS) {
		Customer_Accessable.nameS = nameS;
	}
	public static String getPhoneS() {
		return phoneS;
	}
	public static void setPhoneS(String phoneS) {
		Customer_Accessable.phoneS = phoneS;
	}
	public static String getAddressS() {
		return addressS;
	}
	public static void setAddressS(String addressS) {
		Customer_Accessable.addressS = addressS;
	}
	public static String getCountryS() {
		return countryS;
	}
	public static void setCountryS(String countryS) {
		Customer_Accessable.countryS = countryS;
	}
	public static String getMemberTypeS() {
		return memberTypeS;
	}
	public static void setMemberTypeS(String memberTypeS) {
		Customer_Accessable.memberTypeS = memberTypeS;
	}
	
	

}
