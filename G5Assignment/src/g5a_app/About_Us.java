package g5a_app;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class About_Us extends JPanel {

	/**
	 * Create the panel.
	 */
	public About_Us() {
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(About_Us.class.getResource("/imageIcon/group1.jpg")));
		add(lblNewLabel);

	}

}
