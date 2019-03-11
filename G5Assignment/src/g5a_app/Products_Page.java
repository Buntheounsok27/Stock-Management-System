package g5a_app;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import resource.Catagory_Accessable;
import resource.DataMysqlConnection;
import resource.Product_Accessable_Static;
import resource.Products_Accessable;
import resource.ReferenceMethod;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;

public class Products_Page extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtProductID;
	private JTextField txtProductName;
	private JTextField txtPriceUnit;
	private JTextField txtRetailGoods;
	public static DefaultTableModel dTableModel;
	public static JTable tblProduct;
	private JButton btnCreateNew, btnAdd, btnUpdate, btnRemove;

	private static JComboBox<String> comboBox;
	private static int rowSel, pid;
	private static String proNameUnchange;

	public Products_Page() {
		setBackground(new Color(47, 79, 79));
		initcomponent();
		ReferenceMethod.storeProductToArraylist();
		storeDataToTable();
	}

	public static void storeDataToCbo() {
		comboBox.removeAllItems();
		for (Catagory_Accessable onCbo : Product_Accessable_Static.cateArr) {
			comboBox.addItem(onCbo.getCataNameD().toString());
		}
	}

	private void clearText() {
		txtProductID.setText("PID-00" + Product_Accessable_Static.getPidN());
		txtProductName.setText("");
		txtPriceUnit.setText("");
		txtRetailGoods.setText("");
	}

	public static void storeDataToTable() {
		dTableModel.setRowCount(0);
		for (Products_Accessable onTable : Product_Accessable_Static.proArr) {
			Object[] dataProduct = { onTable.getPid(), onTable.getPname(), onTable.getPprice(), onTable.getPcate(),
					onTable.getPretailgood() };
			dTableModel.addRow(dataProduct);
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
			if (dTableModel.getValueAt(i, 1).toString().equalsIgnoreCase(txtProductName.getText())) {
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
			addDataProductToDatabase();
			clearText();
		}

	}

	private void btnUpdate_Clicked(ActionEvent eve) {

		if (txtProductName.getText().equals(proNameUnchange)) {
			updateDataProductToDatabase();
		} else {
			boolean same = true;
			for (int i = 0; i < dTableModel.getRowCount(); i++) {
				if (dTableModel.getValueAt(i, 1).toString().equalsIgnoreCase(txtProductName.getText())) {
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
			String sqlacc = "Delete From tblproducts where ID = " + pid + "";

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
			String sqlacc = "UPDATE tblproducts SET proName = '" + txtProductName.getText() + "', UnitPrice = "
					+ Double.parseDouble(txtPriceUnit.getText()) + ",Category = '"
					+ comboBox.getSelectedItem().toString() + "', Quantity = "
					+ Integer.parseInt(txtRetailGoods.getText()) + " WHERE ID = " + pid + "";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

			dTableModel.setValueAt(txtProductName.getText(), rowSel, 1);
			dTableModel.setValueAt(txtPriceUnit.getText(), rowSel, 2);
			dTableModel.setValueAt(comboBox.getSelectedItem().toString(), rowSel, 3);
			dTableModel.setValueAt(txtRetailGoods.getText(), rowSel, 4);

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
			String sqlacc = "INSERT INTO tblproducts values(null,'" + txtProductName.getText() + "',"
					+ Double.parseDouble(txtPriceUnit.getText()) + ",'" + comboBox.getSelectedItem().toString() + "',"
					+ Integer.parseInt(txtRetailGoods.getText()) + ")";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			Object[] newData = { txtProductID.getText(), txtProductName.getText(), txtPriceUnit.getText(),
					comboBox.getSelectedItem().toString(), txtRetailGoods.getText() };

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

			proNameUnchange = dTableModel.getValueAt(rowSel, 1).toString();

			txtProductID.setText(dTableModel.getValueAt(rowSel, 0).toString());
			txtProductName.setText(dTableModel.getValueAt(rowSel, 1).toString());
			txtPriceUnit.setText(dTableModel.getValueAt(rowSel, 2).toString());
			comboBox.setSelectedItem(dTableModel.getValueAt(rowSel, 3).toString());
			txtRetailGoods.setText(dTableModel.getValueAt(rowSel, 4).toString());

			String id = txtProductID.getText();
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

		JLabel lblListProduct = new JLabel("List Products");
		lblListProduct.setOpaque(true);
		lblListProduct.setBackground(new Color(46, 139, 87));
		lblListProduct.setForeground(new Color(255, 255, 255));
		lblListProduct.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblListProduct, BorderLayout.NORTH);

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

		String[] hCol = { "PID", "PName", "Unit Price", "Category", "Quantity" };

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

		JPanel pnlAddProduct = new JPanel();
		pnlAddProduct.setBackground(new Color(47, 79, 79));
		add(pnlAddProduct, BorderLayout.EAST);
		pnlAddProduct.setLayout(new BorderLayout(0, 0));

		JPanel panel_2 = new JPanel();
		pnlAddProduct.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(47, 79, 79));
		panel_2.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 10, 10));

		JLabel lblCatagoryName_1 = new JLabel("Catagory Name:");
		lblCatagoryName_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblCatagoryName_1);

		comboBox = new JComboBox<String>();
		storeDataToCbo();
		panel_4.add(comboBox);

		JLabel lblNewLabel_2 = new JLabel("     ");
		panel_4.add(lblNewLabel_2);

		JLabel lblNewLabel = new JLabel("Product ID");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		panel_4.add(lblNewLabel);

		txtProductID = new JTextField();
		txtProductID.setEditable(false);
		panel_4.add(txtProductID);
		txtProductID.setColumns(10);

		JLabel label = new JLabel("     ");
		panel_4.add(label);

		JLabel lblProductName = new JLabel("Product Name");
		lblProductName.setForeground(new Color(255, 255, 255));
		panel_4.add(lblProductName);

		txtProductName = new JTextField();
		panel_4.add(txtProductName);
		txtProductName.setColumns(10);

		JLabel label_1 = new JLabel("     ");
		panel_4.add(label_1);

		JLabel lblPriceUnit = new JLabel("Price Unit");
		lblPriceUnit.setForeground(new Color(255, 255, 255));
		panel_4.add(lblPriceUnit);

		txtPriceUnit = new JTextField();
		panel_4.add(txtPriceUnit);
		txtPriceUnit.setColumns(10);

		JLabel label_2 = new JLabel("     ");
		panel_4.add(label_2);

		JLabel lblRetailGoods = new JLabel("Retail Goods");
		lblRetailGoods.setForeground(new Color(255, 255, 255));
		panel_4.add(lblRetailGoods);

		txtRetailGoods = new JTextField();
		panel_4.add(txtRetailGoods);
		txtRetailGoods.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel(" ");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setBackground(new Color(47, 79, 79));
		panel_2.add(lblNewLabel_1, BorderLayout.NORTH);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(15);
		panel_3.setBackground(new Color(47, 79, 79));
		pnlAddProduct.add(panel_3, BorderLayout.SOUTH);

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
		txtProductID.setText("PID-00" + Product_Accessable_Static.getPidN());
	}

}
