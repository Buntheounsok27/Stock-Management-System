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

import resource.AccountType_Accessable;
import resource.DataMysqlConnection;
import resource.Person_Accessable_Static;
import resource.ReferenceMethod;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.FlowLayout;

public class AccountType_Page extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPosition;
	private JList<String> lstPosition;
	private DefaultListModel<String> dListModel;
	
	private JButton btnCreateNew,btnAdd,btnUpdate,btnRemove;
	
	
	private static int lstCatSel;
	/**
	 * Create the panel.
	 */
	
	private void clearText()
	{
		txtPosition.setText("");
	}
	
	private void storeDataToList()
	{
		for(AccountType_Accessable dolist : Person_Accessable_Static.accTypeArr)
		{
			dListModel.addElement(dolist.getAccountType().toString());
		}
	}
	
	
	private void listShowData_Clicked(MouseEvent eve)
	{
		lstCatSel = lstPosition.getSelectedIndex();
		if(lstCatSel>-1){
			txtPosition.setText(dListModel.getElementAt(lstCatSel).toString());
			btnCreateNew.setEnabled(true);
			btnUpdate.setEnabled(true);
			btnRemove.setEnabled(true);
			btnAdd.setEnabled(false);
			if(dListModel.getElementAt(lstCatSel).equals("General"))
			{
				txtPosition.setEditable(false);
				btnUpdate.setEnabled(false);
				btnRemove.setEnabled(false);
			}else{
				txtPosition.setEditable(true);
				btnUpdate.setEnabled(true);
				btnRemove.setEnabled(true);
			}
		}
	}
	
	private void btnCreateNew_Clicked(ActionEvent eve)
	{
		btnAdd.setEnabled(true);
		lstPosition.clearSelection();
		btnRemove.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnCreateNew.setEnabled(false);
		clearText();
	}
	
	private void btnAdd_Clicked(ActionEvent eve)
	{
		boolean same = true;
		for(int i=0; i<dListModel.size();i++)
		{
			if(dListModel.getElementAt(i).toString().equalsIgnoreCase(txtPosition.getText()))
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
			insertDataToDatabase();
			clearText();
		}
	}
	
	private void btnUpdate_Clicked(ActionEvent eve)
	{
		boolean same = true;
		for(int i=0; i<dListModel.size();i++)
		{
			if(dListModel.getElementAt(i).toString().equalsIgnoreCase(txtPosition.getText()))
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
			updateDataToDatabase();
			clearText();
		}
		
	}
	
	private void btnDelete_Clicked(ActionEvent eve)
	{
		removeDataFromDatabase();
	}
	
	
	private void insertDataToDatabase()
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());			
			String sqlacc = "INSERT INTO tblaccounttype  values(null,'"+txtPosition.getText()+"',0)";
			
			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();
			JOptionPane.showMessageDialog(null, "Data Stored Successful", "Adding New Data ",
					JOptionPane.PLAIN_MESSAGE);
			dListModel.addElement(txtPosition.getText());
			
			//Person_Accessable_Static.accessables.clear();
			Person_Accessable_Static.accTypeArr.clear();
			ReferenceMethod.storeAccountTypeToArraylist();
			Account_Page.storeDataToCbo();
			
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Adding New Data", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateDataToDatabase()
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			String sqlacc = "Update tblaccounttype SET AccountType = '" +txtPosition.getText() +"' where AccountType = '"+dListModel.getElementAt(lstCatSel)+"'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(null, "Data Update Successful", "Updating Data ", JOptionPane.PLAIN_MESSAGE);
			
			Person_Accessable_Static.accessables.clear();
			Person_Accessable_Static.accTypeArr.clear();
			ReferenceMethod.updateAccTypeToDBWhenAccTypeUpdate(txtPosition.getText(), dListModel.getElementAt(lstCatSel));
			ReferenceMethod.storeAccountTypeToArraylist();
			Login_Frame.storeAccount();
			Account_Page.storeDataToCbo();
			
			dListModel.setElementAt(txtPosition.getText(), lstCatSel);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ""+ex.getMessage(), "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void removeDataFromDatabase()
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());			
			String pos = txtPosition.getText();

			String sqlacc = "Delete From tblaccounttype where AccountType = '"+txtPosition.getText()+"'";

			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			preparedStatement.execute();

			JOptionPane.showMessageDialog(null, "Data Deleted Successful", "Deleted Data ", JOptionPane.PLAIN_MESSAGE);
			dListModel.removeElementAt(lstCatSel);
			clearText();
			
			Person_Accessable_Static.accTypeArr.clear();
			ReferenceMethod.updateAccTypeToWhenAccTypeRemove(pos);
			ReferenceMethod.storeAccountTypeToArraylist();
			Account_Page.storeDataToCbo();
			
			

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ""+ex.getMessage(), "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public AccountType_Page() {
		
		
		
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
		
		lstPosition = new JList<String>(dListModel);
		lstPosition.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				listShowData_Clicked(eve);
			}
		});
		panel.add(lstPosition, BorderLayout.CENTER);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setOpaque(true);
		lblPosition.setBackground(new Color(46, 139, 87));
		lblPosition.setForeground(new Color(255, 255, 255));
		lblPosition.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPosition, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(47, 79, 79));
		panel.add(panel_1, BorderLayout.SOUTH);
		
		btnCreateNew = new JButton("Create New");
		btnCreateNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCreateNew_Clicked(arg0);
			}
		});
		btnCreateNew.setForeground(new Color(255, 250, 250));
		btnCreateNew.setBackground(new Color(30, 144, 255));
		panel_1.add(btnCreateNew);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(47, 79, 79));
		pnlCtg.add(panel_5, BorderLayout.WEST);
		
		JPanel pnlAddCtg = new JPanel();
		pnlAddCtg.setBackground(new Color(47, 79, 79));
		add(pnlAddCtg, BorderLayout.EAST);
		pnlAddCtg.setLayout(new BorderLayout(30, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(47, 79, 79));
		pnlAddCtg.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(47, 79, 79));
		panel_2.add(panel_4);
		
		JLabel lblCatagoryName_1 = new JLabel("Position");
		lblCatagoryName_1.setForeground(new Color(255, 255, 255));
		panel_4.add(lblCatagoryName_1);
		lblCatagoryName_1.setHorizontalAlignment(SwingConstants.CENTER);
		
		txtPosition = new JTextField();
		panel_4.add(txtPosition);
		txtPosition.setColumns(10);
		txtPosition.addKeyListener(new KeyAdapter() {
	
			public void keyReleased(KeyEvent arg0) {
				if(txtPosition.getText().equals(""))
				{
					btnAdd.setEnabled(false);
				}else
				{
					btnAdd.setEnabled(true);
				}
				
			}
			
		});
	
		
		JLabel lblNewLabel = new JLabel(" ");
		panel_2.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setHgap(15);
		panel_3.setBackground(new Color(47, 79, 79));
		pnlAddCtg.add(panel_3, BorderLayout.SOUTH);
		
		btnAdd = new JButton("Add");
		btnAdd.setForeground(new Color(255, 250, 250));
		btnAdd.setBackground(new Color(30, 144, 255));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAdd_Clicked(arg0);
			}
		});
		panel_3.add(btnAdd);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
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
			public void actionPerformed(ActionEvent arg0) {
				btnDelete_Clicked(arg0);
			}
		});
		panel_3.add(btnRemove);
		
		btnCreateNew.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnRemove.setEnabled(false);
		btnAdd.setEnabled(false);

	}

}
