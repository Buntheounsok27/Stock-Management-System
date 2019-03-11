package g5a_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import resource.Customer_Accessable;
import resource.DataMysqlConnection;
import resource.Member_Accessable;

public class Customer_Info_Page extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtCustomerID;
	private JTextField txtCustomerName;
	private JTextField txtCustPhone;
	private JTextField txtAddress;
	public static DefaultTableModel dTableModel;
	public static JTable tblProduct;
	private JButton btnCreateNew, btnAdd, btnUpdate, btnRemove;

	private static JComboBox<String> comboBox;
	private static int rowSel, pid;
	private static String proNameUnchange;
	private JTextField txtCountry;

	/**
	 * Create the panel.
	 */
	public Customer_Info_Page() {
		setBackground(new Color(47, 79, 79));
		initcomponent();
		storeDataToTable();
	}

	public static void storeDataToCbo() {
		comboBox.removeAllItems();
		for (Member_Accessable onCbo : Member_Accessable.member_Accessables_Arr) {
			comboBox.addItem(onCbo.getMemberType().toString());
		}
	}

	private void clearText() {
		txtCustomerID.setText("CID-00" + Customer_Accessable.getIdN());
		txtCustomerName.setText("");
		txtCustPhone.setText("");
		txtAddress.setText("");
		txtCountry.setText("");
	}

	public static void storeDataToTable() {
		dTableModel.setRowCount(0);
		for (Customer_Accessable onTable : Customer_Accessable.customer_Accessables_Arr) {
			Object[] dataCust = { onTable.getName(), onTable.getPhone(), onTable.getMemberType() };
			dTableModel.addRow(dataCust);
		}
	}

	private void btnCreateNew_Clicked(ActionEvent eve) {
		btnAdd.setEnabled(true);
		tblProduct.clearSelection();
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnCreateNew.setEnabled(false);
		clearText();
	}

	private void btnAdd_Clicked(ActionEvent eve) {
		boolean same = true;

		for (int i = 0; i < dTableModel.getRowCount(); i++) {
			if (dTableModel.getValueAt(i, 1).toString().equalsIgnoreCase(txtCustPhone.getText())) {
				same = true;
				JOptionPane.showMessageDialog(null, "This Phone Number have Already...!", "Adding New Data ",
						JOptionPane.PLAIN_MESSAGE);
				break;
			} else {
				same = false;
				continue;
			}
		}
		if (same == false) {
			addDataProductToDatabase();
			clearText();
		}

	}

	private void btnUpdate_Clicked(ActionEvent eve) {

		if (txtCustPhone.getText().equals(proNameUnchange)) {
			updateDataProductToDatabase();
		} else {
			boolean same = true;
			for (int i = 0; i < dTableModel.getRowCount(); i++) {
				if (dTableModel.getValueAt(i, 1).toString().equalsIgnoreCase(txtCustPhone.getText())) {
					same = true;
					JOptionPane.showMessageDialog(null, "This Phone Number have Already...!", "Updating Data ",
							JOptionPane.PLAIN_MESSAGE);
					break;
				} else {
					same = false;
					continue;
				}
			}
			if (same == false) {
				updateDataProductToDatabase();
			}
		}

	}

	private void btnRemove_Clicked(ActionEvent eve) {
		removeDataProductFromDatabase();
	}

	private void removeDataProductFromDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "Delete From tblcustomers where CusID = " + pid + "";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			JOptionPane.showMessageDialog(null, "Data Deleted Successful", "Deleted Data ", JOptionPane.PLAIN_MESSAGE);
			dTableModel.removeRow(rowSel);
			clearText();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateDataProductToDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "UPDATE tblcustomers SET CusName = '" + txtCustomerName.getText() + "', Phone = '"
					+ (txtCustPhone.getText()) + "', Address = '" + txtAddress.getText() + "', Country_Region = '"
					+ txtCountry.getText() + "', MemberType= '" + comboBox.getSelectedItem().toString()
					+ "' WHERE CusID = " + pid + "";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

			dTableModel.setValueAt(txtCustomerName.getText(), rowSel, 0);
			dTableModel.setValueAt(txtCustPhone.getText(), rowSel, 1);
			dTableModel.setValueAt(comboBox.getSelectedItem().toString(), rowSel, 2);

			JOptionPane.showMessageDialog(null, "Data Updated Successful", "Updating Data ", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void addDataProductToDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "INSERT INTO tblcustomers values(null,'" + txtCustomerName.getText() + "','"
					+ txtCustPhone.getText() + "','" + txtAddress.getText() + "','" + txtCountry.getText() + "','"
					+ comboBox.getSelectedItem().toString() + "',0)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			Object[] newData = { txtCustomerName.getText(), txtCustPhone.getText(),
					comboBox.getSelectedItem().toString() };

			dTableModel.addRow(newData);

			clearText();
			JOptionPane.showMessageDialog(null, "Data Added Successful", "Add New Data ", JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void rowOnTblProduct_Selected(MouseEvent eve) {
		rowSel = tblProduct.getSelectedRow();
		if (rowSel > -1) {

			com.mysql.jdbc.Connection con = null;
			com.mysql.jdbc.PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
						DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
				String sqlacc = "SELECT tblcustomers.CusID,tblcustomers.CusName,tblcustomers.Phone,tblcustomers.Address,tblcustomers.Country_Region,tblmember.MemberType FROM tblcustomers INNER JOIN tblmember ON tblcustomers.MemberType = tblmember.MemberType WHERE Phone = '"
						+ dTableModel.getValueAt(rowSel, 1) + "'";

				preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					comboBox.setSelectedItem(resultSet.getObject(6));
					txtCustomerID.setText("CID-00" + resultSet.getString(1));
					txtCustomerName.setText(resultSet.getString(2));
					txtCustPhone.setText(resultSet.getString(3));
					txtAddress.setText(resultSet.getString(4));
					txtCountry.setText(resultSet.getString(5));
				}

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
			}

			proNameUnchange = dTableModel.getValueAt(rowSel, 1).toString();

			String id = txtCustomerID.getText();
			int lid = id.length();
			pid = Integer.parseInt(id.substring(4, lid));

			btnCreateNew.setEnabled(true);
			btnAdd.setEnabled(false);
			btnUpdate.setEnabled(true);
			btnRemove.setEnabled(true);
		}
	}

	private void initcomponent() {

		setLayout(new BorderLayout(30, 0));

		JPanel pnlCtg = new JPanel();
		pnlCtg.setBackground(new Color(47, 79, 79));
		add(pnlCtg, BorderLayout.CENTER);
		pnlCtg.setLayout(new BorderLayout(30, 0));

		JPanel panel = new JPanel();
		pnlCtg.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblListCustomer = new JLabel("List Customer");
		lblListCustomer.setOpaque(true);
		lblListCustomer.setBackground(new Color(46, 139, 87));
		lblListCustomer.setForeground(new Color(255, 255, 255));
		lblListCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblListCustomer, BorderLayout.NORTH);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(47, 79, 79));
		panel.add(panel_1, BorderLayout.SOUTH);

		btnCreateNew = new JButton("Create New");
		btnCreateNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnCreateNew_Clicked(arg0);
			}
		});
		btnCreateNew.setForeground(new Color(255, 250, 250));
		btnCreateNew.setBackground(new Color(30, 144, 255));
		panel_1.add(btnCreateNew);

		String[] hCol = { "Customer Name", "Phone Number", "Member Type" };

		dTableModel = new DefaultTableModel();
		dTableModel.setColumnIdentifiers(hCol);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		tblProduct = new JTable(dTableModel);
		scrollPane.setViewportView(tblProduct);
		tblProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				rowOnTblProduct_Selected(eve);
			}
		});

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(47, 79, 79));
		pnlCtg.add(panel_5, BorderLayout.WEST);

		JPanel pnlAddCustomer = new JPanel();
		pnlAddCustomer.setBackground(new Color(47, 79, 79));
		add(pnlAddCustomer, BorderLayout.EAST);
		pnlAddCustomer.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		pnlAddCustomer.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(47, 79, 79));
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 10, 10));

		JLabel lblCatagoryName_1 = new JLabel("Member Type");
		lblCatagoryName_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblCatagoryName_1);

		comboBox = new JComboBox<String>();
		storeDataToCbo();
		panel_4.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("     ");
		panel_4.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Customer ID");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNewLabel);

		txtCustomerID = new JTextField();
		txtCustomerID.setEditable(false);
		panel_4.add(txtCustomerID);
		txtCustomerID.setColumns(10);

		JLabel label = new JLabel("     ");
		panel_4.add(label);

		JLabel lblProductName = new JLabel("Customer Name");
		lblProductName.setForeground(new Color(255, 255, 255));
		panel_4.add(lblProductName);

		txtCustomerName = new JTextField();
		panel_4.add(txtCustomerName);
		txtCustomerName.setColumns(10);

		JLabel label_1 = new JLabel("     ");
		panel_4.add(label_1);

		JLabel lblPriceUnit = new JLabel("Phone");
		lblPriceUnit.setForeground(new Color(255, 255, 255));
		panel_4.add(lblPriceUnit);

		txtCustPhone = new JTextField();
		panel_4.add(txtCustPhone);
		txtCustPhone.setColumns(10);

		JLabel label_2 = new JLabel("     ");
		panel_4.add(label_2);

		JLabel lblRetailGoods = new JLabel("Address");
		lblRetailGoods.setForeground(new Color(255, 255, 255));
		panel_4.add(lblRetailGoods);

		txtAddress = new JTextField();
		panel_4.add(txtAddress);
		txtAddress.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(47, 79, 79));
		panel_2.add(lblNewLabel_1, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(15);
		panel_3.setBackground(new Color(47, 79, 79));
		pnlAddCustomer.add(panel_3, BorderLayout.SOUTH);

		btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 250, 250));
		btnAdd.setBackground(new Color(30, 144, 255));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent eve) {

				btnAdd_Clicked(eve);

			}
		});
		panel_3.add(btnAdd);

		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnUpdate_Clicked(arg0);
			}
		});
		btnUpdate.setForeground(new Color(255, 250, 250));
		btnUpdate.setBackground(new Color(0, 0, 255));
		panel_3.add(btnUpdate);

		btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(255, 250, 250));
		btnRemove.setBackground(new Color(255, 20, 147));
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRemove_Clicked(arg0);
			}
		});
		panel_3.add(btnRemove);

		btnCreateNew.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnRemove.setEnabled(false);
		txtCustomerID.setText("CID-00" + Customer_Accessable.getIdN());

		JLabel label_4 = new JLabel("     ");
		panel_4.add(label_4);

		JLabel lblCountryregion = new JLabel("Country/Region");
		lblCountryregion.setForeground(Color.WHITE);
		panel_4.add(lblCountryregion);

		txtCountry = new JTextField();
		txtCountry.setColumns(10);
		panel_4.add(txtCountry);

		tblProduct.setDefaultEditor(Object.class, null);
	}

}
