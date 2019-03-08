package g5a_app;

import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;

import resource.AccountType_Accessable;
import resource.DataMysqlConnection;
import resource.Person_Accessable;
import resource.Person_Accessable_Static;
import resource.ReferenceMethod;

import java.awt.Font;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class Account_Page extends JPanel {
	/**
	 * 
	 */
	ArrayList<Person_Accessable> arrayList = new ArrayList<>();

	private static DefaultListModel<String> listModel = new DefaultListModel<String>();

	private static final long serialVersionUID = 1L;
	private JTextField tfName;
	private JPasswordField tfPassword, tfRePassword;
	private JButton btnAdd, btnCreateNew, btnUpdate, btnRemove;
	private JCheckBox chckbxFullAccess, chckbxReport, cbxSetting, chckbxCustomer, chckbxStock, chckbxCashRegister;
	private JList<String> lstUser;
	private JCheckBox chcboShowPassword;
	private JTextField txtAccID;
	private JLabel lblAccID;

	private static int AccId, AccSel;
	private static String nameUnchange;
	
	private int settingInt,reportInt,cashInt,customerInt,stockInt;
	
	private JPanel panel_1;
	private JLabel lblAccountType;
	private static JComboBox<String> cboPosition;

	/**
	 * Create the panel.
	 */
	protected static void storeDataToList() {
		listModel.removeAllElements();
		for (Person_Accessable showOnlist : Person_Accessable_Static.accessables) {
			listModel.addElement(showOnlist.getUsernameD().toString());

		}
	}
	
	protected static void storeDataToCbo()
	{
		cboPosition.removeAllItems();
		for(AccountType_Accessable showOnCbo : Person_Accessable_Static.accTypeArr)
		{
			cboPosition.addItem(showOnCbo.getAccountType().toString());
		}
	}

	@SuppressWarnings("deprecation")
	private void btnAdd_Clicked(ActionEvent eve) {
		
		boolean same = true;
		for(int i=0; i<listModel.size();i++)
		{
			if(listModel.getElementAt(i).toString().equalsIgnoreCase(tfName.getText()))
			{
				same = true;
				JOptionPane.showMessageDialog(null, "This Catagory Name have Already...!", "Adding New Data ", JOptionPane.PLAIN_MESSAGE);
				break;
			}
			else
			{
			    same = false;	continue;
			}
		}
		if(same == false)
		{
			if (tfRePassword.getText().equals(tfPassword.getText())) {

				tfRePassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				btnCreateNew.setEnabled(true);
				insertDataToDatabase();
				ReferenceMethod.StoreDataLastRow();
				clearTextion();
			}
		}
	}
	
	private void addKeyEventToTextField(final JTextField txt)
	{
		txt.addKeyListener(new KeyAdapter() {
				public void keyReleased(KeyEvent arg0) {
					if(txt.getText().equals(""))
					{
						btnAdd.setEnabled(false);
					}else
					{
						btnAdd.setEnabled(true);
					}
					
				}
				
			});
	}
	
	private void addKeyEventToTextPassword(final JPasswordField txt)
	{
		txt.addKeyListener(new KeyAdapter() {
				@SuppressWarnings("deprecation")
				public void keyReleased(KeyEvent arg0) {
					if(txt.getText().equals(""))
					{
						btnAdd.setEnabled(false);
					}else
					{
						btnAdd.setEnabled(true);
					}
					
				}
				
			});
	}

	private void btnCreateNew_Clicked(ActionEvent eve) {
		
		txtAccID.setText("AID-00" + Person_Accessable_Static.getAccID().toString());
		btnAdd.setEnabled(true);
		btnUpdate.setEnabled(false);
		btnRemove.setEnabled(false);
		lstUser.clearSelection();
		clearTextion();
		btnCreateNew.setEnabled(false);

		chckbxFullAccess.setEnabled(true);
		chckbxReport.setEnabled(true);
		cbxSetting.setEnabled(true);
		chckbxCustomer.setEnabled(true);
		chckbxStock.setEnabled(true);
		chckbxCashRegister.setEnabled(true);

		tfName.setEditable(true);
		tfPassword.setEditable(true);
		tfRePassword.setEditable(true);
		
		accountFeature();
	}

	private void btnUpdate_Clicked(ActionEvent eve) {
		
		 

		if(tfName.hasFocus()==false || tfName.getText().equals(nameUnchange))
		{
			String ID = txtAccID.getText();
			int lId = ID.length();
			String CatchID = txtAccID.getText().substring(5, lId);
			AccId = Integer.parseInt(CatchID);

			listModel.setElementAt(tfName.getText(), AccSel);
			updateDataToDatabase();
			Login_Frame.StoreUpdateDatabase();
		}else
		{ boolean same = true;
			for(int i=0; i<listModel.size();i++)
			{
				if(listModel.getElementAt(i).toString().equalsIgnoreCase(tfName.getText()))
				{
					same = true;
					JOptionPane.showMessageDialog(null, "This User Name have Already...!", "Adding New Data ", JOptionPane.PLAIN_MESSAGE);
					break;
				}
				else
				{
				    same = false;	continue;
				}
			}
			if(same == false)
			{
				String ID = txtAccID.getText();
				int lId = ID.length();
				String CatchID = txtAccID.getText().substring(5, lId);
				AccId = Integer.parseInt(CatchID);

				listModel.setElementAt(tfName.getText(), AccSel);
				updateDataToDatabase();
				Login_Frame.StoreUpdateDatabase();
			}
		}
		
	}

	private void btnRemove_Clicked(ActionEvent eve) {
		String ID = txtAccID.getText();
		int lId = ID.length();
		String CatchID = txtAccID.getText().substring(5, lId);
		AccId = Integer.parseInt(CatchID);

		if (AccId != ReferenceMethod.idSpeacial) {
			removeDataFromDatabase();
			listModel.removeElementAt(AccSel);
			clearTextion();
		} else
			JOptionPane.showMessageDialog(null, "You can't remove this account", "Deleting", JOptionPane.ERROR_MESSAGE);

	}

	private void removeDataFromDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			String sqlacc = "Delete From tblaccount where ID =" + AccId;

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			JOptionPane.showMessageDialog(null, "Data Deleted Successful", "Deleted Data ", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void clearTextion() {
		txtAccID.setText("AID-00" + Person_Accessable_Static.getAccIDN().toString());
		tfName.setText("");
		tfPassword.setText("");
		tfRePassword.setText("");
		chckbxFullAccess.setSelected(false);
		chckbxReport.setSelected(false);
		cbxSetting.setSelected(false);
		chckbxCustomer.setSelected(false);
		chckbxStock.setSelected(false);
		chckbxCashRegister.setSelected(false);
	}

	@SuppressWarnings("deprecation")
	private void insertDataToDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		
		convertBoolean();
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			
			String sqlacc = "Insert into tblaccount values (null,0,'" + tfName.getText() + "','"+cboPosition.getSelectedItem().toString()+"','"
					+ tfRePassword.getText() + "'," + settingInt+ "," + reportInt
					+ "," +cashInt + "," +stockInt+ ","+customerInt+ ")";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();
			JOptionPane.showMessageDialog(null, "Data Stored Successful", "Adding New Data ",
					JOptionPane.PLAIN_MESSAGE);
			listModel.addElement(tfName.getText());
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Adding New Data", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void convertBoolean()
	{
		if(cbxSetting.isSelected()==true)
		{
			settingInt = 1;
		}else settingInt = 0;
		
		if(chckbxCustomer.isSelected()==true)
		{
			customerInt = 1;
		}else customerInt = 0;
		
		if(chckbxCashRegister.isSelected()==true)
		{
			cashInt = 1;
		}else cashInt = 0;
		
		if(chckbxReport.isSelected()==true)
		{
			reportInt = 1;
		}else reportInt = 0;
		
		if(chckbxStock.isSelected()==true)
		{
			stockInt = 1;
		}else stockInt = 0;
	}

	@SuppressWarnings("deprecation")
	private void updateDataToDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		
		convertBoolean();
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			String sqlacc = "Update tblaccount SET Name = '" + tfName.getText() + "',Position = '"+cboPosition.getSelectedItem().toString()+"', Password = '"
					+ tfRePassword.getText() + "', Setting_perm =" +settingInt+ ""
					+ ", Report_perm = " + reportInt+ ", Cash_perm = "
					+ cashInt+ ", Stock_perm = " + stockInt
					+ ", Customer_perm = " + customerInt+ " where ID = " + AccId;

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Data Update Successful", "Add New Data ", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void lstUserRow_Selected(MouseEvent eve) {
		AccSel = lstUser.getSelectedIndex();

		if (AccSel > -1) {
			
			nameUnchange = lstUser.getSelectedValue().toString();

			btnCreateNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnRemove.setEnabled(true);
			btnAdd.setEnabled(false);
			tfRePassword.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			com.mysql.jdbc.Connection con = null;
			com.mysql.jdbc.PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			
			try
			{
				con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
				String sqlacc = "Select *from tblaccount where Name ='" + lstUser.getSelectedValue().toString() + "'";

				preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
				resultSet = preparedStatement.executeQuery();

				if (resultSet.next()) {
					String aid = resultSet.getString("ID");
					txtAccID.setText("AID-00" + aid);
					tfName.setText(lstUser.getSelectedValue().toString());
					tfPassword.setText(resultSet.getString("Password"));
					tfRePassword.setText(resultSet.getString("Password"));
					cboPosition.setSelectedItem(resultSet.getString(4));
					cbxSetting.setSelected(resultSet.getBoolean("Setting_perm"));
					chckbxReport.setSelected(resultSet.getBoolean("Report_perm"));
					chckbxCashRegister.setSelected(resultSet.getBoolean("cash_perm"));
					chckbxStock.setSelected(resultSet.getBoolean("stock_perm"));
					chckbxCustomer.setSelected(resultSet.getBoolean("customer_perm"));
					if (resultSet.getBoolean("Setting_perm") == true && resultSet.getBoolean("Report_perm")
							&& resultSet.getBoolean("customer_perm") && resultSet.getBoolean("stock_perm")
							&& resultSet.getBoolean("cash_perm")) {
						chckbxFullAccess.setSelected(true);
					} else
						chckbxFullAccess.setSelected(false);
					if (Integer.parseInt(aid) == ReferenceMethod.idSpeacial) 
					{
						if (ReferenceMethod.idSpeacial == Integer.parseInt(Person_Accessable_Static.getAccID().toString())) {
							tfName.setEditable(true);
							tfPassword.setEditable(true);
							tfRePassword.setEditable(true);
							btnRemove.setEnabled(false);
							cboPosition.setEnabled(false);

						}
						else {

							tfName.setEditable(false);
							tfPassword.setEditable(false);
							tfRePassword.setEditable(false);
							btnUpdate.setEnabled(false);
							btnRemove.setEnabled(false);
							cboPosition.setEnabled(false);
						}

						chckbxFullAccess.setEnabled(false);
						chckbxReport.setEnabled(false);
						cbxSetting.setEnabled(false);
						chckbxCustomer.setEnabled(false);
						chckbxStock.setEnabled(false);
						chckbxCashRegister.setEnabled(false);
					}
					else {
						chckbxFullAccess.setEnabled(true);
						chckbxReport.setEnabled(true);
						cbxSetting.setEnabled(true);
						chckbxCustomer.setEnabled(true);
						chckbxStock.setEnabled(true);
						chckbxCashRegister.setEnabled(true);

						tfName.setEditable(true);
						tfPassword.setEditable(true);
						tfRePassword.setEditable(true);
						btnUpdate.setEnabled(true);
						btnRemove.setEnabled(true);
						cboPosition.setEnabled(true);
					}
					
					if(Integer.parseInt(Person_Accessable_Static.getAccID()) != ReferenceMethod.idSpeacial)
					{
						if(Integer.parseInt(aid)==ReferenceMethod.idSpeacial)
						{
							tfName.setEditable(false);
							tfPassword.setEditable(false);
							tfRePassword.setEditable(false);
							chcboShowPassword.setEnabled(false);
							cboPosition.setEditable(false);
						}else {
						
						tfName.setEditable(true);
						tfPassword.setEditable(true);
						tfRePassword.setEditable(true);
						chckbxFullAccess.setEnabled(false);
						chckbxReport.setEnabled(false);
						cbxSetting.setEnabled(false);
						chckbxCustomer.setEnabled(false);
						chckbxStock.setEnabled(false);
						chckbxCashRegister.setEnabled(false);
						chcboShowPassword.setEnabled(true);
						
						btnAdd.setEnabled(false);
						btnCreateNew.setEnabled(false);
						btnRemove.setEnabled(false);
						cboPosition.setEnabled(false);
						
						
					
						}
					}
				}
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void accountFeature()
	{
		if(Integer.parseInt(Person_Accessable_Static.getAccID())!=ReferenceMethod.idSpeacial)
		{
			btnCreateNew.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnRemove.setEnabled(false);
			btnAdd.setEnabled(false);
		}
	}

	public Account_Page() {

		initcompenent();
		accountFeature();

	}

	private void initcompenent() {
		arrayList = Person_Accessable_Static.accessables;

		ReferenceMethod.StoreDataLastRow();
		storeDataToList();
		setBackground(new Color(47, 79, 79));
		setLayout(new BorderLayout(50, 10));

		JPanel pnlWest = new JPanel();
		pnlWest.setBackground(new Color(173, 216, 230));
		add(pnlWest, BorderLayout.CENTER);
		pnlWest.setLayout(new BorderLayout(0, 0));

		lstUser = new JList<String>(listModel);
		pnlWest.add(lstUser, BorderLayout.CENTER);
		lstUser.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent eve) {
				lstUserRow_Selected(eve);
			}
		});

		JLabel lblUserAccount = new JLabel("User Account");
		lblUserAccount.setBackground(new Color(85, 107, 47));
		lblUserAccount.setForeground(Color.WHITE);
		lblUserAccount.setFont(new Font("Arial", Font.BOLD, 20));
		lblUserAccount.setOpaque(true);
		lblUserAccount.setHorizontalAlignment(SwingConstants.CENTER);
		pnlWest.add(lblUserAccount, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		pnlWest.add(panel, BorderLayout.SOUTH);

		btnCreateNew = new JButton("Create New");
		btnCreateNew.setRequestFocusEnabled(false);
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				btnCreateNew_Clicked(eve);
			}
		});
		btnCreateNew.setForeground(new Color(245, 245, 220));
		btnCreateNew.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCreateNew.setBackground(new Color(0, 128, 128));
		btnCreateNew.setEnabled(false);
		panel.add(btnCreateNew);

		JPanel pnlCeneter = new JPanel();
		pnlCeneter.setBackground(new Color(47, 79, 79));
		add(pnlCeneter, BorderLayout.EAST);
		pnlCeneter.setLayout(new BorderLayout(10, 10));

		JPanel pnlInfo = new JPanel();
		pnlInfo.setForeground(new Color(255, 255, 255));
		pnlInfo.setBackground(new Color(47, 79, 79));
		pnlInfo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Account Infomation",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlCeneter.add(pnlInfo, BorderLayout.NORTH);
		pnlInfo.setLayout(new GridLayout(0, 2, 10, 10));

		lblAccID = new JLabel("Account ID:");
		lblAccID.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccID.setForeground(new Color(245, 245, 220));
		lblAccID.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlInfo.add(lblAccID);

		txtAccID = new JTextField();
		txtAccID.setEditable(false);
		txtAccID.setText("AID-00" + Person_Accessable_Static.getAccIDN().toString());
		pnlInfo.add(txtAccID);
		txtAccID.setColumns(10);

		JLabel lblName = new JLabel("Name:");
		lblName.setForeground(new Color(245, 245, 220));
		lblName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInfo.add(lblName);

		tfName = new JTextField("");
		pnlInfo.add(tfName);
		tfName.setColumns(10);
		
		lblAccountType = new JLabel("Account Type");
		lblAccountType.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountType.setForeground(new Color(245, 245, 220));
		lblAccountType.setFont(new Font("Arial", Font.PLAIN, 15));
		pnlInfo.add(lblAccountType);
		
		cboPosition = new JComboBox<String>();
		storeDataToCbo();
		pnlInfo.add(cboPosition);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(new Color(245, 245, 220));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInfo.add(lblPassword);

		tfPassword = new JPasswordField();
		pnlInfo.add(tfPassword);
		tfPassword.setColumns(10);

		JLabel lblReenterPassword = new JLabel("Re-Enter Password:");
		lblReenterPassword.setForeground(new Color(245, 245, 220));
		lblReenterPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblReenterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		pnlInfo.add(lblReenterPassword);

		tfRePassword = new JPasswordField();
		pnlInfo.add(tfRePassword);
		tfRePassword.setColumns(10);

		chcboShowPassword = new JCheckBox("Show Password");
		chcboShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {

				if (chcboShowPassword.isSelected()) {
					tfPassword.setEchoChar((char) 0);
					tfRePassword.setEchoChar((char) 0);
				} else {
					tfPassword.setEchoChar('●');
					tfRePassword.setEchoChar('●');
				}
			}
		});
		chcboShowPassword.setForeground(new Color(240, 248, 255));
		chcboShowPassword.setBackground(new Color(47, 79, 79));
		pnlInfo.add(chcboShowPassword);

		JPanel pnlPermission = new JPanel();
		pnlPermission.setForeground(new Color(255, 255, 255));
		pnlPermission.setBackground(new Color(47, 79, 79));
		pnlPermission.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Permissions",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlCeneter.add(pnlPermission, BorderLayout.CENTER);
		pnlPermission.setLayout(new GridLayout(0, 2, 0, 0));

		chckbxCashRegister = new JCheckBox("Cash Register");
		chckbxCashRegister.setForeground(new Color(245, 245, 220));
		chckbxCashRegister.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxCashRegister.setBackground(new Color(47, 79, 79));
		pnlPermission.add(chckbxCashRegister);

		chckbxStock = new JCheckBox("Stock");
		chckbxStock.setForeground(new Color(245, 245, 220));
		chckbxStock.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxStock.setBackground(new Color(47, 79, 79));
		pnlPermission.add(chckbxStock);

		chckbxCustomer = new JCheckBox("Customer");
		chckbxCustomer.setForeground(new Color(245, 245, 220));
		chckbxCustomer.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxCustomer.setBackground(new Color(47, 79, 79));
		pnlPermission.add(chckbxCustomer);

		cbxSetting = new JCheckBox("Setting");
		cbxSetting.setForeground(new Color(245, 245, 220));
		cbxSetting.setFont(new Font("Arial", Font.PLAIN, 15));
		cbxSetting.setBackground(new Color(47, 79, 79));
		pnlPermission.add(cbxSetting);

		chckbxReport = new JCheckBox("Report");
		chckbxReport.setForeground(new Color(245, 245, 220));
		chckbxReport.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxReport.setBackground(new Color(47, 79, 79));
		pnlPermission.add(chckbxReport);

		chckbxFullAccess = new JCheckBox("Full Access");
		chckbxFullAccess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {

				if (chckbxFullAccess.isSelected() == true) {
					cbxSetting.setSelected(true);
					chckbxCashRegister.setSelected(true);
					chckbxCustomer.setSelected(true);
					chckbxReport.setSelected(true);
					chckbxStock.setSelected(true);
				} else {
					cbxSetting.setSelected(false);
					chckbxCashRegister.setSelected(false);
					chckbxCustomer.setSelected(false);
					chckbxReport.setSelected(false);
					chckbxStock.setSelected(false);
				}
			}
		});
		chckbxFullAccess.setForeground(new Color(245, 245, 220));
		chckbxFullAccess.setFont(new Font("Arial", Font.PLAIN, 15));
		chckbxFullAccess.setBackground(new Color(47, 79, 79));
		pnlPermission.add(chckbxFullAccess);

		JPanel pnlBtns = new JPanel();
		pnlBtns.setBackground(new Color(47, 79, 79));
		pnlCeneter.add(pnlBtns, BorderLayout.SOUTH);
		pnlBtns.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnAdd = new JButton("Add");
		btnAdd.setRequestFocusEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				btnAdd_Clicked(eve);
			}
		});
		btnAdd.setForeground(new Color(245, 245, 220));
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAdd.setBackground(new Color(30, 144, 255));
		pnlBtns.add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {

				btnUpdate_Clicked(eve);
			}
		});
		btnUpdate.setRequestFocusEnabled(false);
		btnUpdate.setForeground(new Color(245, 245, 220));
		btnUpdate.setFont(new Font("Arial", Font.PLAIN, 15));
		btnUpdate.setBackground(new Color(0, 0, 128));
		pnlBtns.add(btnUpdate);

		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				btnRemove_Clicked(eve);
			}
		});
		btnRemove.setRequestFocusEnabled(false);
		btnRemove.setForeground(new Color(245, 245, 220));
		btnRemove.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRemove.setBackground(new Color(255, 0, 0));
		pnlBtns.add(btnRemove);
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(false);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(47, 79, 79));
		add(panel_1, BorderLayout.WEST);

		chckbxCashRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				if (chckbxCashRegister.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == false && chckbxStock.isSelected() == false
						&& cbxSetting.isSelected() == false && chckbxCustomer.isSelected() == false
						&& chckbxCashRegister.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == true && chckbxStock.isSelected() == true
						&& cbxSetting.isSelected() == true && chckbxCustomer.isSelected() == true
						&& chckbxCashRegister.isSelected() == true) {
					chckbxFullAccess.setSelected(true);
				}
			}
		});

		chckbxCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				if (chckbxCustomer.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == false && chckbxStock.isSelected() == false
						&& cbxSetting.isSelected() == false && chckbxCustomer.isSelected() == false
						&& chckbxCashRegister.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == true && chckbxStock.isSelected() == true
						&& cbxSetting.isSelected() == true && chckbxCustomer.isSelected() == true
						&& chckbxCashRegister.isSelected() == true) {
					chckbxFullAccess.setSelected(true);
				}
			}
		});

		cbxSetting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				if (cbxSetting.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == false && chckbxStock.isSelected() == false
						&& cbxSetting.isSelected() == false && chckbxCustomer.isSelected() == false
						&& chckbxCashRegister.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == true && chckbxStock.isSelected() == true
						&& cbxSetting.isSelected() == true && chckbxCustomer.isSelected() == true
						&& chckbxCashRegister.isSelected() == true) {
					chckbxFullAccess.setSelected(true);
				}
			}
		});

		chckbxStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				if (chckbxStock.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == false && chckbxStock.isSelected() == false
						&& cbxSetting.isSelected() == false && chckbxCustomer.isSelected() == false
						&& chckbxCashRegister.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == true && chckbxStock.isSelected() == true
						&& cbxSetting.isSelected() == true && chckbxCustomer.isSelected() == true
						&& chckbxCashRegister.isSelected() == true) {
					chckbxFullAccess.setSelected(true);
				}
			}
		});

		chckbxReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				if (chckbxReport.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == false && chckbxStock.isSelected() == false
						&& cbxSetting.isSelected() == false && chckbxCustomer.isSelected() == false
						&& chckbxCashRegister.isSelected() == false) {
					chckbxFullAccess.setSelected(false);
				} else if (chckbxReport.isSelected() == true && chckbxStock.isSelected() == true
						&& cbxSetting.isSelected() == true && chckbxCustomer.isSelected() == true
						&& chckbxCashRegister.isSelected() == true) {
					chckbxFullAccess.setSelected(true);
				}
			}
		});
		
		addKeyEventToTextField(tfName);
		addKeyEventToTextPassword(tfPassword);
		
		btnAdd.setEnabled(false);
	}

}
