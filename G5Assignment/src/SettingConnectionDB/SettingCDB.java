package SettingConnectionDB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import resource.DataMysqlConnection;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class SettingCDB {

	private JFrame frame;
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JTextField txtDatabase;

	private JButton btnSave, btnTest;

	File file = new File("posg5_doc/configDatabase.bin");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SettingCDB window = new SettingCDB();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SettingCDB() {
		initialize();
	}

	@SuppressWarnings("deprecation")
	private void btnSave_Clicked(ActionEvent eve) {
		String url = "jdbc:mysql://" + txtHost.getText() + ":" + txtPort.getText() + "/" + txtDatabase.getText();

		writeBinaryFile(url,txtHost.getText(),txtPort.getText(), txtUser.getText(), txtPassword.getText(), txtDatabase.getText());

		readBinaryFile();
	}

	private void btnTest_Clicked(ActionEvent eve) {
		@SuppressWarnings("unused")
		com.mysql.jdbc.Connection connection;

		String urlConnection = DataMysqlConnection.getUrl();
		String user = "" + DataMysqlConnection.getUser();
		String password = "" + DataMysqlConnection.getPassword();

		try {
			connection = (com.mysql.jdbc.Connection) DriverManager.getConnection(urlConnection, user, password);
			JOptionPane.showMessageDialog(null, "Connected to Database Successful...");

		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "" + e.getMessage());
		}
	}

	private void writeBinaryFile(String url,String host,String port, String user, String password, String dataBase) {

		if (file.exists()) {
			try {

				FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeUTF(url);
				
				objectOutputStream.writeUTF(host);
				
				objectOutputStream.writeUTF(port);

				objectOutputStream.writeUTF(user);

				objectOutputStream.writeUTF(password);

				objectOutputStream.writeUTF(dataBase);
				objectOutputStream.close();

			} catch (FileNotFoundException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "" + e.getMessage());
			} catch (IOException e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, "" + e.getMessage());
			}
		} else {
			try {

				file.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				objectOutputStream.writeUTF(url);
				objectOutputStream.flush();
				objectOutputStream.writeUTF(host);
				objectOutputStream.writeUTF(port);
				objectOutputStream.writeUTF(user);
				objectOutputStream.flush();
				objectOutputStream.writeUTF(password);
				objectOutputStream.flush();
				objectOutputStream.writeUTF(dataBase);
				objectOutputStream.close();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "" + e.getMessage());
			}
		}

		JOptionPane.showMessageDialog(null, "Saved");
	}

	private void readBinaryFile() {
		try {

			FileInputStream fileInputStream = new FileInputStream(file);
			ObjectInputStream inputStream = new ObjectInputStream(fileInputStream);
			String s = inputStream.readUTF();
			DataMysqlConnection.setUrl(s);
			
			DataMysqlConnection.setHost(inputStream.readUTF());
			DataMysqlConnection.setPort(inputStream.readUTF());
			
			String s1 = inputStream.readUTF();
			DataMysqlConnection.setUser(s1);

			String s2 = inputStream.readUTF();
			DataMysqlConnection.setPassword(s2);

			String s3 = inputStream.readUTF();
			DataMysqlConnection.setDataBase(s3);

			inputStream.close();

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "" + e.getMessage());
		} catch (IOException e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "" + e.getMessage());
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		readBinaryFile();

		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(47, 79, 79));
		frame.setResizable(false);
		frame.setBounds(100, 100, 295, 287);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JLabel label = new JLabel("HOST");
		label.setFont(new Font("Arial", Font.BOLD, 15));
		label.setForeground(new Color(255, 255, 255));
		label.setBounds(10, 14, 83, 14);
		frame.getContentPane().add(label);

		txtHost = new JTextField();
		txtHost.setText(DataMysqlConnection.getHost());
		txtHost.setColumns(10);
		txtHost.setBounds(121, 11, 155, 20);
		frame.getContentPane().add(txtHost);

		JLabel label_1 = new JLabel("PORT");
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setForeground(new Color(255, 255, 255));
		label_1.setBounds(10, 44, 83, 14);
		frame.getContentPane().add(label_1);

		txtPort = new JTextField();
		txtPort.setText(DataMysqlConnection.getPort());
		txtPort.setColumns(10);
		txtPort.setBounds(121, 41, 155, 20);
		frame.getContentPane().add(txtPort);

		JLabel label_2 = new JLabel("USER");
		label_2.setFont(new Font("Arial", Font.BOLD, 15));
		label_2.setForeground(new Color(255, 255, 255));
		label_2.setBounds(10, 76, 83, 14);
		frame.getContentPane().add(label_2);

		txtUser = new JTextField();
		txtUser.setText("root");
		txtUser.setColumns(10);
		txtUser.setBounds(121, 73, 155, 20);
		frame.getContentPane().add(txtUser);

		JLabel label_3 = new JLabel("PASSWORD");
		label_3.setFont(new Font("Arial", Font.BOLD, 15));
		label_3.setForeground(new Color(255, 255, 255));
		label_3.setBounds(10, 112, 101, 14);
		frame.getContentPane().add(label_3);

		txtPassword = new JPasswordField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(121, 109, 155, 20);
		frame.getContentPane().add(txtPassword);

		JLabel label_4 = new JLabel("DATABASE");
		label_4.setFont(new Font("Arial", Font.BOLD, 15));
		label_4.setForeground(new Color(255, 255, 255));
		label_4.setBounds(10, 148, 83, 14);
		frame.getContentPane().add(label_4);

		txtDatabase = new JTextField();
		txtDatabase.setColumns(10);
		txtDatabase.setBounds(121, 145, 155, 20);
		frame.getContentPane().add(txtDatabase);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Arial", Font.BOLD, 15));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setBackground(new Color(0, 128, 128));
		btnSave.setRequestFocusEnabled(false);
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnSave_Clicked(arg0);
			}
		});
		btnSave.setBounds(187, 176, 89, 23);
		frame.getContentPane().add(btnSave);

		btnTest = new JButton("Test Service");
		btnTest.setFont(new Font("Arial", Font.BOLD, 15));
		btnTest.setForeground(new Color(255, 255, 255));
		btnTest.setBackground(new Color(46, 139, 87));
		btnTest.setRequestFocusEnabled(false);
		btnTest.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				btnTest_Clicked(arg0);
			}
		});
		btnTest.setBounds(0, 225, 131, 23);
		frame.getContentPane().add(btnTest);

		txtUser.setText(DataMysqlConnection.getUser());
		txtDatabase.setText(DataMysqlConnection.getDataBase());
	}
}
