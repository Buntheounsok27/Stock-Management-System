package g5a_app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import resource.DataMysqlConnection;
import resource.Person_Accessable;
import resource.Person_Accessable_Static;
import resource.ReferenceMethod;
import resource.UserPerforment;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Login_Frame {
	
	
	public static ArrayList<Person_Accessable> accessables = new ArrayList<>();

	protected JFrame login_frame;
	private JPanel contentPane;
	private JPasswordField txtPassword;
	private JComboBox<String> cboUserName;

	public Login_Frame() {
		readBinaryFile();
		ReferenceMethod.storeCustomerFromDBToArraylist();
		ReferenceMethod.storeMemberFromDBToArraylist();
		ReferenceMethod.storeCatagoryToArraylist();
		ReferenceMethod.storeAccountTypeToArraylist();
		ReferenceMethod.findIDSpecail();
		ReferenceMethod.findMaxIDPro();
		ReferenceMethod.findMaxIDCus();
		storeAccount();
		ReferenceMethod.storeUserReportFromDBToArraylist();
		initialize();
	}
	
	public static void StoreUpdateDatabase()
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			String sqlacc = "Select *from tblaccount where Name ='"+Person_Accessable_Static.getUsername()+"'";
			
			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Person_Accessable_Static.setAccID(resultSet.getString("ID"));
				Person_Accessable_Static.setUsername(resultSet.getString("Name"));
				Person_Accessable_Static.setPassword(resultSet.getString("Password"));
				Person_Accessable_Static.setReportperm(resultSet.getBoolean("Report_perm"));
				Person_Accessable_Static.setSettingperm(resultSet.getBoolean("Setting_perm"));
				Person_Accessable_Static.setCashperm(resultSet.getBoolean("Cash_perm"));
				Person_Accessable_Static.setCustomerperm(resultSet.getBoolean("Customer_perm"));
				Person_Accessable_Static.setStockperm(resultSet.getBoolean("Stock_perm"));
								
			}
		
		}catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@SuppressWarnings("deprecation")
	private void Login_Clicked(ActionEvent eve)
	{
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			String sqlacc = "Select *from tblaccount where Name ='"+cboUserName.getSelectedItem().toString()+"'and password = '"+txtPassword.getText()+"'";
			
			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			if(!txtPassword.getText().equals(""))
			{
			if(resultSet.next())
			{
				Person_Accessable_Static.setAccID(resultSet.getString("ID"));
				Person_Accessable_Static.setUsername(cboUserName.getSelectedItem().toString());
				Person_Accessable_Static.setPassword(txtPassword.getText());
				Person_Accessable_Static.setReportperm(resultSet.getBoolean("Report_perm"));
				Person_Accessable_Static.setSettingperm(resultSet.getBoolean("Setting_perm"));
				Person_Accessable_Static.setCashperm(resultSet.getBoolean("Cash_perm"));
				Person_Accessable_Static.setCustomerperm(resultSet.getBoolean("Customer_perm"));
				Person_Accessable_Static.setStockperm(resultSet.getBoolean("Stock_perm"));
				
				UserPerforment.setUserIDS(Person_Accessable_Static.getAccID());
				UserPerforment.setLoginDateS(ReferenceMethod.getDateShow());
				UserPerforment.setLoginTimeS(ReferenceMethod.getTimeShow());
				
				Main_Frame main_page = new Main_Frame();
				main_page.setVisible(true);
				main_page.setLocationRelativeTo(null);
				main_page.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				this.login_frame.dispose();
				
			}
			else JOptionPane.showMessageDialog(null, "Password is incorrect...!", "Login Account",JOptionPane.WARNING_MESSAGE);
			}else JOptionPane.showMessageDialog(null, "Please Enter Password...!", "Login Account",JOptionPane.WARNING_MESSAGE);
		}catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void storeAccount()
	{
		readBinaryFile();
		com.mysql.jdbc.Connection con = null;
		com.mysql.jdbc.PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try
		{
			con = (com.mysql.jdbc.Connection) DriverManager.getConnection(DataMysqlConnection.getUrl(),DataMysqlConnection.getUser(),DataMysqlConnection.getPassword());
			String sqlacc = "Select Name from tblaccount";
			
			preparedStatement = (com.mysql.jdbc.PreparedStatement) con.prepareStatement(sqlacc);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Person_Accessable person_Accessable = new Person_Accessable();
				person_Accessable.setUsernameD(resultSet.getString("Name"));
				accessables.add(person_Accessable);
			}
			
			Person_Accessable_Static.accessables = accessables;
			
		}catch(SQLException ex)
		{
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void readBinaryFile()
	{
		String file = "posg5_doc/configDatabase.bin";
		
		try {
			
			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			String s = inputStream.readUTF();
			DataMysqlConnection.setUrl(s);
			
			String s1 = inputStream.readUTF();
			DataMysqlConnection.setUser(s1);
			
			String s2 = inputStream.readUTF();
			DataMysqlConnection.setPassword(s2);
			
			inputStream.close();
			
			
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,""+e.getMessage());
		}catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null,""+e.getMessage());
		}
	}

	private void initialize() {

		login_frame = new JFrame();
		login_frame.setSize(500, 400);
		login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 79, 79));
		login_frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		login_frame.setResizable(false);
		
		cboUserName = new JComboBox<String>();
		for(int i=0 ; i<accessables.size();i++)
		{
			cboUserName.addItem(accessables.get(i).getUsernameD());
		}
		cboUserName.setFont(new Font("Arial", Font.PLAIN, 20));
		cboUserName.setBounds(170, 140, 240, 30);
		contentPane.add(cboUserName);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(170, 230, 240, 30);
		contentPane.add(txtPassword);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setIcon(new ImageIcon(Login_Frame.class.getResource("/imageIcon/icons8_Male_User_16.png")));
		lblUserName.setForeground(new Color(255, 255, 255));
		lblUserName.setFont(new Font("Arial", Font.PLAIN, 15));
		lblUserName.setBounds(30, 140, 130, 30);
		contentPane.add(lblUserName);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setIcon(new ImageIcon(Login_Frame.class.getResource("/imageIcon/icons8_Password_16.png")));
		lblPassword.setForeground(new Color(255, 255, 255));
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 15));
		lblPassword.setBounds(30, 230, 130, 30);
		contentPane.add(lblPassword);
		
		JLabel lblLoginSystem = new JLabel("Login System");
		lblLoginSystem.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblLoginSystem.setHorizontalTextPosition(SwingConstants.CENTER);
		lblLoginSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSystem.setForeground(new Color(255, 255, 255));
		lblLoginSystem.setFont(new Font("Arial", Font.BOLD, 20));
		lblLoginSystem.setIcon(new ImageIcon(Login_Frame.class.getResource("/imageIcon/icons8_Workspace_48.png")));
		lblLoginSystem.setBounds(142, 11, 179, 99);
		contentPane.add(lblLoginSystem);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent eve) {
				Login_Clicked(eve);
			}
		});
		btnLogin.setRequestFocusEnabled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setIcon(new ImageIcon(Login_Frame.class.getResource("/imageIcon/icons8_Login_16.png")));
		btnLogin.setBackground(new Color(255, 20, 147));
		btnLogin.setBounds(320, 290, 89, 23);
		contentPane.add(btnLogin);
	}
}
