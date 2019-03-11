package resource;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class ReferenceMethod {

	public static String passwordAdminCompare;
	public static int idSpeacial;
	public static int maxIDPro;

	public static String getTimeShow() {
		Date time = new Date();
		SimpleDateFormat siFormat = new SimpleDateFormat("hh:mm:ss a  ");

		return siFormat.format(time);
	}

	public static String getDateShow() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		return simpleDateFormat.format(date);
	}

	public static void StoreDataLastRow() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select * from tblaccount where ID =(Select MAX(ID) From tblaccount)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				int incr = Integer.parseInt(resultSet.getString("ID")) + 1;
				String IDinc = String.valueOf(incr);
				Person_Accessable_Static.setAccIDN(IDinc);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void findIDSpecail() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select ID from tblaccount where Special_Type = 1";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

				idSpeacial = resultSet.getInt("ID");
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void permissionDailog(String title, SwingConstants o) {
		JTextField txtpassword = new JTextField();

		Object[] obj = { "Enter Administrator's Password ", txtpassword };

		JOptionPane.showInputDialog(null, obj, o);

		passwordAdminCompare = txtpassword.getText();

	}

	public static void storeCatagoryToArraylist() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select *from tblcategory";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Catagory_Accessable catagory_Accessable = new Catagory_Accessable();
				catagory_Accessable.setCataNameD(resultSet.getString("Category"));
				Product_Accessable_Static.cateArr.add(catagory_Accessable);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeProductToArraylist() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select *from tblproducts";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Products_Accessable products_Accessable = new Products_Accessable();
				products_Accessable.setPid("PID-00" + resultSet.getString(1));
				products_Accessable.setPname(resultSet.getString(2));
				products_Accessable.setPprice(resultSet.getString(3));
				products_Accessable.setPcate(resultSet.getString(4));
				products_Accessable.setPretailgood(resultSet.getString(5));

				Product_Accessable_Static.proArr.add(products_Accessable);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeAccountTypeToArraylist() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select *from tblaccounttype";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				AccountType_Accessable aType_Accessable = new AccountType_Accessable();
				aType_Accessable.setAccountType(resultSet.getString("AccountType"));

				Person_Accessable_Static.accTypeArr.add(aType_Accessable);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void findMaxIDPro() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select * from tblproducts where ID =(Select MAX(ID) From tblproducts)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {

					int incr = Integer.parseInt(resultSet.getString("ID")) + 1;
					Product_Accessable_Static.setPidN(incr);

			}else Product_Accessable_Static.setPidN(1);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeToArrByCategory(String cateSel) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select ProName from tblproducts Where Category = '" + cateSel + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Products_Accessable pAccessable = new Products_Accessable();
				pAccessable.setPnamebyCate(resultSet.getString("ProName"));
				Product_Accessable_Static.proByCate.add(pAccessable);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updateCategoryOfProduct(String cate) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Update tblproducts SET Category = 'Default' where Category = '" + cate + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updaateCateToProductWhenCateUpdate(String upCa, String oldCa) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Update tblproducts SET Category = '" + upCa + "' where Category = '" + oldCa + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void updateAccTypeToWhenAccTypeRemove(String accType) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Update tblaccount SET Position = 'General' where Position = '" + accType + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updateAccTypeToDBWhenAccTypeUpdate(String upAcc, String oldAcc) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Update tblaccount SET Position = '" + upAcc + "' where Position = '" + oldAcc + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void getUnitPriceProductFromDB(DefaultListModel<String> model, int sel) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select ID,UnitPrice from tblproducts Where ProName = '" + model.getElementAt(sel) + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product_Accessable_Static.setPpriceS(String.valueOf(resultSet.getDouble("UnitPrice")));
				Product_Accessable_Static.setPidS(resultSet.getString(1));
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void getUnitPriceProductFromDB(DefaultTableModel model, int sel) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select ID,UnitPrice from tblproducts Where ProName = '" + model.getValueAt(sel, 1)+ "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product_Accessable_Static.setPpriceS(String.valueOf(resultSet.getDouble("UnitPrice")));
				Product_Accessable_Static.setPidS(resultSet.getString(1));
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeUserReportToDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "INSERT INTO tblreportuser values(null,'" + UserPerforment.getUserIDS() + "','"
					+ UserPerforment.getLoginDateS() + "','" + UserPerforment.getLoginTimeS() + "','"
					+ UserPerforment.getLogoutTimeS() + "','" + UserPerforment.getLogoutDateS() + "')";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeUserReportFromDBToArraylist() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "SELECT tblaccount.Name,tblreportuser.Datelogin,tblreportuser.Timelogin,tblreportuser.Timelogout,tblreportuser.Datelogout FROM tblreportuser INNER JOIN tblaccount ON tblreportuser.Emp_ID = tblaccount.ID";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserPerforment userPerforment = new UserPerforment();
				userPerforment.setUserName(resultSet.getString(1));
				userPerforment.setLoginDate(resultSet.getString(2));
				userPerforment.setLoginTime(resultSet.getString(3));
				userPerforment.setLogoutTime(resultSet.getString(4));
				userPerforment.setLogoutDate(resultSet.getString(5));

				UserPerforment.userPerformentsArr.add(userPerforment);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void storeUserReportFromDBToArraylist(String name) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "SELECT tblaccount.Name,tblreportuser.Datelogin,tblreportuser.Timelogin,tblreportuser.Timelogout,tblreportuser.Datelogout FROM tblreportuser INNER JOIN tblaccount ON tblreportuser.Emp_ID = tblaccount.ID WHERE tblaccount.Name = '"+name+"'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserPerforment userPerforment = new UserPerforment();
				userPerforment.setUserName(resultSet.getString(1));
				userPerforment.setLoginDate(resultSet.getString(2));
				userPerforment.setLoginTime(resultSet.getString(3));
				userPerforment.setLogoutTime(resultSet.getString(4));
				userPerforment.setLogoutDate(resultSet.getString(5));

				UserPerforment.userPerformentsArr.add(userPerforment);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeMemberFromDBToArraylist() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select *from tblmember";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Member_Accessable member_Accessable = new Member_Accessable();
				member_Accessable.setId(resultSet.getString(1));
				member_Accessable.setMemberType(resultSet.getString(2));
				member_Accessable.setDiscount(resultSet.getDouble(3));

				Member_Accessable.member_Accessables_Arr.add(member_Accessable);

			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void storeCustomerFromDBToArraylist() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "SELECT tblcustomers.CusID,tblcustomers.CusName,tblcustomers.Phone,tblcustomers.Address,tblcustomers.Country_Region,tblmember.MemberType,tblmember.Discount FROM tblcustomers INNER JOIN tblmember ON tblcustomers.MemberType = tblmember.MemberType";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Customer_Accessable accessable = new Customer_Accessable();
				accessable.setId(resultSet.getString(1));
				accessable.setName(resultSet.getString(2));
				accessable.setPhone(resultSet.getString(3));
				accessable.setAddress(resultSet.getString(4));
				accessable.setCountry(resultSet.getString(5));
				accessable.setMemberType(resultSet.getString(6));
				accessable.setDiscount(resultSet.getString(7));

				Customer_Accessable.customer_Accessables_Arr.add(accessable);
			}

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void findMaxIDCus() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select CusID from tblcustomers where CusID =(Select MAX(CusID) From tblcustomers)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
					int incr = Integer.parseInt(resultSet.getString(1)) + 1;
					Customer_Accessable.setIdN(String.valueOf(incr));
			}else Customer_Accessable.setIdN("1");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updateMemberTypeToWhenMemberTypeRemove(String accType) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Update tblcustomers SET MemberType = 'General' where MemberType = '" + accType + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void updateMemberTypeToDBWhenMemberTypeUpdate(String upAcc, String oldAcc) {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Update tblcustomers SET MemberType = '" + upAcc + "' where MemberType = '" + oldAcc + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void findMaxIDSaleDetail()
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select ID from tblsalesdetails where ID =(Select MAX(ID) From tblsalesdetails)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
					int incr = Integer.parseInt(resultSet.getString(1)) + 1;
					Sale_Detail.setIdN(String.valueOf(incr));
			}else Sale_Detail.setIdN("1");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void findMaxIDSales()
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Select ID from tblsales where ID =(Select MAX(ID) From tblsales)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
					int incr = Integer.parseInt(resultSet.getString(1)) + 1;
					Sale.setIDN(String.valueOf(incr));
			}else Sale.setIDN("1");

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void phone_char(KeyEvent eve) {
		if (!Character.isDigit(eve.getKeyChar())) {
			Toolkit.getDefaultToolkit().beep();
			eve.consume();
		}
	}

	public static void InputInteger(KeyEvent evt) {
		if (!Character.isDigit(evt.getKeyChar())) {
			Toolkit.getDefaultToolkit().beep();
			evt.consume();
		}
	}

	public static void InputInteger(KeyEvent evt, JTextField txt, int length) {
		if (!Character.isDigit(evt.getKeyChar())) {
			Toolkit.getDefaultToolkit().beep();
			evt.consume();
		} else {
			String st = txt.getText();
			if (st.length() >= length) {
				Toolkit.getDefaultToolkit().beep();
				evt.consume();
			}
		}
	}

	public static void InputFloating(KeyEvent evt, JTextField txt) {
		String st;
		if (evt.getKeyChar() == 46) {
			st = txt.getText();
			if (st.indexOf(46) != -1) {
				Toolkit.getDefaultToolkit().beep();
				evt.consume();
			} else {
				if (st.equals("") || st.equals(".")) {
					txt.setText("0.");
					evt.consume();
				}
			}
		} else if (!Character.isDigit(evt.getKeyChar())) {
			Toolkit.getDefaultToolkit().beep();
			evt.consume();
		}
	}

	public static void InputFloating(KeyEvent evt, JTextField txt, int length) {
		String st;
		double num;
		if (evt.getKeyChar() == 46) {
			st = txt.getText();
			if (st.indexOf(46) != -1) {
				Toolkit.getDefaultToolkit().beep();
				evt.consume();
			} else {
				if (st.equals("") || st.equals("." + st)) {
					txt.setText("0.");
					evt.consume();
				}
			}
		} else if (!Character.isDigit(evt.getKeyChar())) {
			Toolkit.getDefaultToolkit().beep();
			evt.consume();
		} else {
			st = txt.getText();
			if (!st.equals("")) {
				num = Double.valueOf(st);
				st = "" + (long) num;
				if (st.length() > length) {
					Toolkit.getDefaultToolkit().beep();
					evt.consume();
				}
			}
		}
	}

	public static void StringOnly(KeyEvent eve, JTextField txt, int lenght) {
		if (Character.isDigit(eve.getKeyChar())) {
			Toolkit.getDefaultToolkit().beep();
			eve.consume();
		} else {
			String st = txt.getText();
			if (st.length() >= lenght) {
				Toolkit.getDefaultToolkit().beep();
				eve.consume();
			}
		}
	}

	public static void ClearText(JTextField... txt) {
		for (JTextField temp : txt) {
			temp.setText("");
		}
		txt[0].grabFocus();
	}

	public static boolean CheckText(JTextField... txt) {
		String st;
		for (JTextField temp : txt) {
			st = temp.getText().trim();
			if (st.equals("")) {
				temp.grabFocus();
				return false;
			}
		}
		return true;
	}

}