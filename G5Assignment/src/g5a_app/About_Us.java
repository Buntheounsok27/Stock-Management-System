package g5a_app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

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
