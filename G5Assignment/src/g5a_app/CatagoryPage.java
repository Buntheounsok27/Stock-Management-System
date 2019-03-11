package g5a_app;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import resource.Catagory_Accessable;
import resource.DataMysqlConnection;
import resource.Product_Accessable_Static;
import resource.ReferenceMethod;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class CatagoryPage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtCtgName;
	private JList<String> list;
	private DefaultListModel<String> dListModel;

	private JButton btnCreateNew, btnAdd, btnUpdate, btnRemove;

	private static int lstCatSel;

	/**
	 * Create the panel.
	 */

	private void storeDataToList() {
		for (Catagory_Accessable dolist : Product_Accessable_Static.cateArr) {
			dListModel.addElement(dolist.getCataNameD().toString());
		}
	}

	private void listShowData_Clicked(MouseEvent eve) {
		lstCatSel = list.getSelectedIndex();
		if (lstCatSel > -1) {
			txtCtgName.setText(dListModel.getElementAt(lstCatSel).toString());
			btnCreateNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnRemove.setEnabled(true);
			btnAdd.setEnabled(false);
			if (dListModel.getElementAt(lstCatSel).equals("Default")) {
				txtCtgName.setEditable(false);
				btnUpdate.setEnabled(false);
				btnRemove.setEnabled(false);
			} else {
				txtCtgName.setEditable(true);
				btnUpdate.setEnabled(true);
				btnRemove.setEnabled(true);
			}
		}
	}

	private void clearText() {
		txtCtgName.setText("");
	}

	private void btnCreateNew_Clicked(ActionEvent eve) {
		btnAdd.setEnabled(true);
		list.clearSelection();
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnCreateNew.setEnabled(false);
		txtCtgName.setEditable(true);
		clearText();
	}

	private void btnAdd_Clicked(ActionEvent eve) {
		boolean same = true;
		for (int i = 0; i < dListModel.size(); i++) {
			if (dListModel.getElementAt(i).toString().equalsIgnoreCase(txtCtgName.getText())) {
				same = true;
				JOptionPane.showMessageDialog(null, "This Catagory Name have Already...!", "Adding New Data ",
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
		boolean same = true;
		for (int i = 0; i < dListModel.size(); i++) {
			if (dListModel.getElementAt(i).toString().equalsIgnoreCase(txtCtgName.getText())) {
				same = true;
				JOptionPane.showMessageDialog(null, "This Catagory Name have Already...!", "Adding New Data ",
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

	private void btnRemove_Clicked(ActionEvent eve) {
		removeDataFromDatabase();
	}

	private void insertDataToDatabase() {

		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;

		try {
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
					DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
			String sqlacc = "INSERT INTO tblcategory values(null,'" + txtCtgName.getText() + "',0)";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();
			JOptionPane.showMessageDialog(null, "Data Stored Successful", "Adding New Data ",
					JOptionPane.PLAIN_MESSAGE);
			dListModel.addElement(txtCtgName.getText());
			btnAdd.setEnabled(true);
			btnRemove.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCreateNew.setEnabled(false);
			clearText();
			Product_Accessable_Static.proArr.clear();
			Product_Accessable_Static.cateArr.clear();
			ReferenceMethod.storeCatagoryToArraylist();
			Products_Page.storeDataToCbo();
			Cash_Register_Page.storeDataToCombobox();
			ReferenceMethod.storeToArrByCategory("Default");

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
			String oldCa = dListModel.getElementAt(lstCatSel).toString();

			String sqlacc = "Update tblcategory SET Category = '" + txtCtgName.getText() + "' where Category= '"
					+ dListModel.getElementAt(lstCatSel) + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Data Update Successful", "Updating Data ", JOptionPane.PLAIN_MESSAGE);

			ReferenceMethod.updaateCateToProductWhenCateUpdate(txtCtgName.getText(), oldCa);
			Product_Accessable_Static.proArr.clear();
			Product_Accessable_Static.cateArr.clear();
			ReferenceMethod.storeCatagoryToArraylist();
			ReferenceMethod.storeProductToArraylist();
			Products_Page.storeDataToCbo();
			Products_Page.storeDataToTable();
			Cash_Register_Page.storeDataToCombobox();
			ReferenceMethod.storeToArrByCategory("Default");

			dListModel.setElementAt(txtCtgName.getText(), lstCatSel);

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
			String name = txtCtgName.getText();

			String sqlacc = "Delete From tblcategory where Category = '" + txtCtgName.getText() + "'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			JOptionPane.showMessageDialog(null, "Data Deleted Successful", "Deleted Data ", JOptionPane.PLAIN_MESSAGE);
			dListModel.removeElementAt(lstCatSel);
			btnAdd.setEnabled(true);
			list.clearSelection();
			btnRemove.setEnabled(false);
			btnUpdate.setEnabled(false);
			btnCreateNew.setEnabled(false);
			clearText();

			Products_Page.tblProduct.removeAll();
			Product_Accessable_Static.proArr.clear();
			Product_Accessable_Static.cateArr.clear();
			ReferenceMethod.updateCategoryOfProduct(name);
			ReferenceMethod.storeCatagoryToArraylist();
			ReferenceMethod.storeProductToArraylist();
			Cash_Register_Page.storeDataToCombobox();
			ReferenceMethod.storeToArrByCategory("Default");
			Products_Page.storeDataToCbo();
			Products_Page.storeDataToTable();

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
		}

	}

	public CatagoryPage() {
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

		dListModel = new DefaultListModel<String>();
		storeDataToList();

		list = new JList<String>(dListModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				listShowData_Clicked(eve);
			}
		});
		panel.add(list, BorderLayout.CENTER);

		JLabel lblCatagoryName = new JLabel("Catagory Name");
		lblCatagoryName.setOpaque(true);
		lblCatagoryName.setBackground(new Color(46, 139, 87));
		lblCatagoryName.setForeground(new Color(255, 255, 255));
		lblCatagoryName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblCatagoryName, BorderLayout.NORTH);

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

		JLabel lblCatagoryName_1 = new JLabel("Catagory Name:");
		lblCatagoryName_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblCatagoryName_1);

		txtCtgName = new JTextField();
		panel_4.add(txtCtgName);
		txtCtgName.setColumns(10);

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
		btnCreateNew.setEnabled(false);

		txtCtgName.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (txtCtgName.getText().equals("")) {
					btnAdd.setEnabled(false);
				} else {
					btnAdd.setEnabled(true);
				}

			}

		});

	}

}
