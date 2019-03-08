package g5a_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

public class XReadingDaily_Page extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public XReadingDaily_Page() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
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
		
		JLabel lblXreadDaily = new JLabel("X-Reading Daily Report");
		lblXreadDaily.setBackground(new Color(47, 79, 79));
		lblXreadDaily.setOpaque(true);
		lblXreadDaily.setForeground(Color.WHITE);
		lblXreadDaily.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblXreadDaily.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblXreadDaily, BorderLayout.NORTH);

	}

}
