package g5a_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import resource.Customer_Accessable;
import resource.DataMysqlConnection;
import resource.Member_Accessable;
import resource.ReferenceMethod;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Member_Page extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField txtMemberType;
	private DefaultTableModel dListModel;

	private JButton btnCreateNew, btnAdd, btnUpdate, btnRemove;
	private JTextField txtDiscount;
	private static int lstCatSel;
	private JTable list;
	
	private static String member;

	public Member_Page() {
		initcompenent();
	}

	private void storeDataToList() {
		dListModel.setRowCount(0);
		for (Member_Accessable dolist : Member_Accessable.member_Accessables_Arr) {
			Object[] rowdata = { dolist.getMemberType(), (dolist.getDiscount()*100) };
			dListModel.addRow(rowdata);
		}
	}

	private void clearText() {
		txtMemberType.setText("");
		txtDiscount.setText("");
	}

	private void btnCreateNew_Clicked(ActionEvent eve) {
		btnAdd.setEnabled(true);
		list.clearSelection();
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnCreateNew.setEnabled(false);
		txtMemberType.setEditable(true);
		clearText();
	}

	private void btnAdd_Clicked(ActionEvent eve) {
		boolean same = true;
		for (int i = 0; i < dListModel.getRowCount(); i++) {
			if (dListModel.getValueAt(i, 0).toString().equalsIgnoreCase(txtMemberType.getText())) {
				same = true;
				JOptionPane.showMessageDialog(null, "This Member Type have Already...!", "Adding New Data ",
						JOptionPane.PLAIN_MESSAGE);
				break;
			} else {
				same = false;
				continue;
			}
		}
		if (same == false) {
			insertDataToDatabase();
			clearText();
		}
	}

	private void btnUpdate_Clicked(ActionEvent eve) {
		
		if (txtMemberType.getText().equals(member)) {
			updateDataToDatabase();
		} else {
		
		boolean same = true;
		for (int i = 0; i < dListModel.getRowCount(); i++) {
			if (dListModel.getValueAt(i, 0).toString().equalsIgnoreCase(txtMemberType.getText())) {
				same = true;
				JOptionPane.showMessageDialog(null, "This Member have Already...!", "Adding New Data ",
						JOptionPane.PLAIN_MESSAGE);
				break;
			} else {
				same = false;
				continue;
			}
		}
		if (same == false) {
			updateDataToDatabase();
			clearText();
		}
		}

	}

	private void btnRemove_Clicked(ActionEvent eve) {
		removeDataFromDatabase();
	}

	private void insertDataToDatabase() {

		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			String discount = String.valueOf(Double.valueOf(txtDiscount.getText())/100);
			
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "INSERT INTO tblmember values(null,'" + txtMemberType.getText() + "',"
					+ Float.parseFloat(discount) + ",0)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();
			JOptionPane.showMessageDialog(null, "Data Stored Successful", "Adding New Data ",
					JOptionPane.PLAIN_MESSAGE);
			Object[] rowdata = { txtMemberType.getText(), txtDiscount.getText() };

			dListModel.addRow(rowdata);
			btnAdd.setEnabled(true);
			btnRemove.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCreateNew.setEnabled(false);
			clearText();
			Member_Accessable.member_Accessables_Arr.clear();
			ReferenceMethod.storeMemberFromDBToArraylist();
			Customer_Info_Page.storeDataToCbo();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Adding New Data", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateDataToDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());

			String discount = String.valueOf(Double.valueOf(txtDiscount.getText())/100);
			
			String sqlacc = "Update tblmember SET MemberType = '" + txtMemberType.getText() + "', Discount = "
					+ Double.parseDouble(discount) + " where MemberType= '"
					+ dListModel.getValueAt(lstCatSel, 0) + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Data Update Successful", "Updating Data ", JOptionPane.PLAIN_MESSAGE);

			dListModel.setValueAt(txtMemberType.getText(), lstCatSel, 0);
			dListModel.setValueAt(txtDiscount.getText(), lstCatSel, 1);

			Customer_Accessable.customer_Accessables_Arr.clear();
			Member_Accessable.member_Accessables_Arr.clear();
			ReferenceMethod.updateMemberTypeToDBWhenMemberTypeUpdate(txtMemberType.getText(),
					dListModel.getValueAt(lstCatSel, 0).toString());
			ReferenceMethod.storeMemberFromDBToArraylist();
			ReferenceMethod.storeCustomerFromDBToArraylist();
			Customer_Info_Page.storeDataToCbo();
			Customer_Info_Page.storeDataToTable();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void removeDataFromDatabase() {
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String name = txtMemberType.getText();
			
			ReferenceMethod.updateMemberTypeToWhenMemberTypeRemove(name);

			String sqlacc = "Delete From tblmember where MemberType = '" + txtMemberType.getText() + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			JOptionPane.showMessageDialog(null, "Data Deleted Successful", "Deleted Data ", JOptionPane.PLAIN_MESSAGE);
			dListModel.removeRow(lstCatSel);
			btnAdd.setEnabled(true);
			list.clearSelection();
			btnRemove.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCreateNew.setEnabled(false);
			clearText();

			Member_Accessable.member_Accessables_Arr.clear();
			Customer_Accessable.customer_Accessables_Arr.clear();
			ReferenceMethod.storeMemberFromDBToArraylist();
			ReferenceMethod.storeCustomerFromDBToArraylist();
			Customer_Info_Page.storeDataToCbo();
			Customer_Info_Page.storeDataToTable();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void listShowData_Clicked(MouseEvent eve) {
		lstCatSel = list.getSelectedRow();
		if (lstCatSel > -1) {
			txtMemberType.setText(dListModel.getValueAt(lstCatSel, 0).toString());
			txtDiscount.setText(dListModel.getValueAt(lstCatSel, 1).toString());
			btnCreateNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnRemove.setEnabled(true);
			btnAdd.setEnabled(false);
			
			if (dListModel.getValueAt(lstCatSel, 0).equals("General")) {
				txtMemberType.setEditable(false);
				btnUpdate.setEnabled(false);
				btnRemove.setEnabled(false);
			} else {
				txtMemberType.setEditable(true);
				btnUpdate.setEnabled(true);
				btnRemove.setEnabled(true);
			}
			
			member = dListModel.getValueAt(lstCatSel, 0).toString();
		}
	}

	private void initcompenent() {
		setBackground(new Color(47, 79, 79));
		setLayout(new BorderLayout(30, 0));

		JPanel pnlCtg = new JPanel();
		pnlCtg.setBackground(new Color(47, 79, 79));
		add(pnlCtg, BorderLayout.CENTER);
		pnlCtg.setLayout(new BorderLayout(30, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		pnlCtg.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		Object[] hname = { "Member Type", "Discount %" };

		dListModel = new DefaultTableModel();
		dListModel.setColumnIdentifiers(hname);
		storeDataToList();

		JLabel lblMember = new JLabel("List Member Type");
		lblMember.setOpaque(true);
		lblMember.setBackground(new Color(46, 139, 87));
		lblMember.setForeground(new Color(255, 255, 255));
		lblMember.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblMember, BorderLayout.NORTH);

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
		btnCreateNew.setForeground(new Color(255, 255, 255));
		btnCreateNew.setBackground(new Color(30, 144, 255));
		panel_1.add(btnCreateNew);

		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(47, 79, 79));
		pnlCtg.add(panel_6, BorderLayout.WEST);

		JPanel pnlAddCtg = new JPanel();
		pnlAddCtg.setBackground(new Color(47, 79, 79));
		add(pnlAddCtg, BorderLayout.EAST);
		pnlAddCtg.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(47, 79, 79));
		panel_2.setBorder(null);
		pnlAddCtg.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(47, 79, 79));
		panel_2.add(panel_4, BorderLayout.CENTER);

		JLabel lblCatagoryName_1 = new JLabel("Member Type");
		lblCatagoryName_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblCatagoryName_1);

		txtMemberType = new JTextField();
		panel_4.add(txtMemberType);
		txtMemberType.setColumns(10);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(47, 79, 79));
		panel_2.add(panel_5, BorderLayout.NORTH);
		panel_5.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel(" ");
		panel_5.add(lblNewLabel, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(15);
		panel_3.setBackground(new Color(47, 79, 79));
		pnlAddCtg.add(panel_3, BorderLayout.SOUTH);

		btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(30, 144, 255));
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnAdd_Clicked(arg0);
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
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setBackground(new Color(0, 0, 255));
		panel_3.add(btnUpdate);

		btnRemove = new JButton("Remove");
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBackground(new Color(255, 0, 0));
		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnRemove_Clicked(arg0);
			}
		});
		panel_3.add(btnRemove);

		btnAdd.setEnabled(false);
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(false);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(47, 79, 79));
		pnlAddCtg.add(panel_7, BorderLayout.CENTER);
		panel_7.setLayout(new BorderLayout(0, 0));

		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(47, 79, 79));
		panel_7.add(panel_8, BorderLayout.NORTH);

		JLabel lblNewLabel_1 = new JLabel("Discount  %     ");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		panel_8.add(lblNewLabel_1);

		txtDiscount = new JTextField();
		panel_8.add(txtDiscount);
		txtDiscount.setColumns(10);
		btnCreateNew.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		list = new JTable(dListModel);
		scrollPane.setViewportView(list);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				listShowData_Clicked(eve);
			}
		});

		txtMemberType.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (txtMemberType.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}

			}

		});

		list.setDefaultEditor(Object.class, null);

	}

}
