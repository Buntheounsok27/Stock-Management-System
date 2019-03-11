package g5a_app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import resource.Customer_Accessable;
import resource.DataMysqlConnection;
import resource.ReferenceMethod;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Customer_Selected extends JFrame {

	
	private static double discountS;
	/**
	 * 
	 * 
	 */
	
	private DecimalFormat decimalFormat = new DecimalFormat("0.00");
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JTable table;
	private JButton btnYes, btnCancel;

	private void storeToList() {
		for (Customer_Accessable shOnList : Customer_Accessable.customer_Accessables_Arr) {
			Object[] rowN = { shOnList.getName(), shOnList.getPhone(), shOnList.getMemberType() };
			defaultTableModel.addRow(rowN);
		}
	}

	private void btnCancel_Clicked(ActionEvent eve, JFrame frame) {
		frame.setEnabled(true);
		this.dispose();
	}

	private void btnYes_Clicked(ActionEvent eve, JFrame frame) {
		Cash_Register_Page.lblShCustomer.setText(defaultTableModel.getValueAt(table.getSelectedRow(), 0).toString());
		
		Cash_Register_Page.discount = discountS = Double.parseDouble(Customer_Accessable.getDiscountS());
		
		Cash_Register_Page.lblShDiscount.setText("%  "+decimalFormat.format(discountS*100));
		frame.setEnabled(true);
		this.dispose();
	}

	private void rowSel_Clicked(MouseEvent eve) {
		int sel = table.getSelectedRow();

		if (sel > -1) {
			btnYes.setEnabled(true);
			com.mysql.jdbc.Connection con = null;
			com.mysql.jdbc.PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;

			try {
				con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
						DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
				String sqlacc = "Select Discount from tblmember where MemberType = '"
						+ defaultTableModel.getValueAt(sel, 2) + "'";

				preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					Customer_Accessable.setDiscountS(resultSet.getString(1));
				}

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
			}
			
			try {
				con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),
						DataMysqlConnection.getUser(), DataMysqlConnection.getPassword());
				String sqlacc = "Select CusID from tblcustomers where Phone = '"+defaultTableModel.getValueAt(sel, 1).toString()+"'";

				preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
				resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					Cash_Register_Page.cusID =  Integer.parseInt(resultSet.getString(1));
				}

			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Connection Database", JOptionPane.ERROR_MESSAGE);
			}
		} else
			btnYes.setEnabled(false);
	}

	public Customer_Selected(JFrame frame) {
		Object[] hm = { "Customer Name", "Phone Number", "Member Type" };
		defaultTableModel.setColumnIdentifiers(hm);

		Customer_Accessable.customer_Accessables_Arr.clear();
		ReferenceMethod.storeCustomerFromDBToArraylist();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent eve) {
				frame.setEnabled(true);
			}
		});

		setLocationRelativeTo(null);
		setResizable(false);
		setBounds(100, 100, 321, 509);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblListCustomer = new JLabel("List Customer");
		lblListCustomer.setOpaque(true);
		lblListCustomer.setForeground(new Color(255, 255, 255));
		lblListCustomer.setBackground(new Color(0, 128, 128));
		lblListCustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblListCustomer.setHorizontalAlignment(SwingConstants.CENTER);
		lblListCustomer.setBounds(10, 11, 296, 27);
		contentPane.add(lblListCustomer);

		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnCancel_Clicked(e, frame);
			}
		});
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setBackground(new Color(199, 21, 133));
		btnCancel.setBounds(44, 433, 89, 23);
		contentPane.add(btnCancel);

		btnYes = new JButton("Yes");
		btnYes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnYes_Clicked(arg0, frame);
			}
		});
		btnYes.setForeground(new Color(255, 255, 255));
		btnYes.setBackground(new Color(0, 139, 139));
		btnYes.setBounds(188, 433, 89, 23);
		contentPane.add(btnYes);
		btnYes.setEnabled(false);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 295, 386);
		contentPane.add(scrollPane);

		table = new JTable(defaultTableModel);
		storeToList();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				rowSel_Clicked(eve);
			}
		});
	}
}
