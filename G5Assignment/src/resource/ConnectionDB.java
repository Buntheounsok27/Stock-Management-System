package resource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectionDB {

	private static Connection con;

	public static Connection testConnectionDB() {
		String url = "jdbc:ucanaccess://Database\\g5smartshop.accdb";

		try {
			con = DriverManager.getConnection(url);
			JOptionPane.showMessageDialog(null, "Database Connention Succeed", "Connection Database",
					JOptionPane.PLAIN_MESSAGE);

		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Database doesn't connect", "Connection Database",
					JOptionPane.ERROR_MESSAGE);
		}

		return con;
	}

}
