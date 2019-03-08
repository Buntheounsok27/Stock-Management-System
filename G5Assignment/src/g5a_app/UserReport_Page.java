package g5a_app;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import com.toedter.calendar.JDateChooser;

import resource.UserPerforment;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;

public class UserReport_Page extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel defaultTableModel = new DefaultTableModel();
	private JTable table;

	private void storeDataFromArraylistToTable()
	{
		defaultTableModel.setRowCount(0);
		int  i= 1;
		for(UserPerforment shOnTable : UserPerforment.userPerformentsArr)
		{
			Object[] rowData = {i,shOnTable.getUserName(),shOnTable.getLoginDate(),shOnTable.getLoginTime(),shOnTable.getLogoutTime(),shOnTable.getLogoutDate(),0.00};
			defaultTableModel.addRow(rowData);
			i++;
		}
	}
	
	
	public UserReport_Page() {
		Object[] header = {" No ","User Name","Date Login"," Time Login "," Time Logout","Date Logout"," Total Sale "};
		
		defaultTableModel.setColumnIdentifiers(header);
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setViewportView(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("              ");
		label.setBackground(Color.WHITE);
		label.setOpaque(true);
		panel_1.add(label, BorderLayout.WEST);
		
		JLabel label_1 = new JLabel("                              ");
		label_1.setBackground(Color.WHITE);
		label_1.setOpaque(true);
		panel_1.add(label_1, BorderLayout.SOUTH);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(Color.WHITE);
		panel_1.add(panel_8, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel(" ");
		panel_8.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		table = new JTable(defaultTableModel);
		storeDataFromArraylistToTable();
		table.setEnabled(false);
		table.setColumnSelectionAllowed(false);
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.getTableHeader().setBackground(Color.WHITE);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getTableHeader().setReorderingAllowed(false);
		scrollPane_1.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(47, 79, 79));
		panel.add(panel_2, BorderLayout.EAST);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(47, 79, 79));
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(47, 79, 79));
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(47, 79, 79));
		panel_4.add(panel_6, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("End Date  ");
		lblNewLabel_1.setForeground(Color.WHITE);
		panel_6.add(lblNewLabel_1);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		panel_6.add(dateChooser_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(47, 79, 79));
		panel_4.add(panel_5, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setForeground(Color.WHITE);
		panel_5.add(lblNewLabel);
		
		JDateChooser dateChooser = new JDateChooser();
		panel_5.add(dateChooser);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(47, 79, 79));
		panel_3.add(panel_7, BorderLayout.SOUTH);
		
		JButton btnGenerateReport = new JButton("Generate Report");
		btnGenerateReport.setBackground(new Color(119, 136, 153));
		btnGenerateReport.setRequestFocusEnabled(false);
		btnGenerateReport.setForeground(Color.WHITE);
		panel_7.add(btnGenerateReport);
		
		JLabel lblUserLogReport = new JLabel("User Log Report");
		lblUserLogReport.setBackground(new Color(47, 79, 79));
		lblUserLogReport.setOpaque(true);
		lblUserLogReport.setForeground(Color.WHITE);
		lblUserLogReport.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblUserLogReport.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblUserLogReport, BorderLayout.NORTH);

	}

}
