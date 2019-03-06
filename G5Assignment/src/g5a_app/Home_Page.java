package g5a_app;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Home_Page extends JPanel {

	private static final long serialVersionUID = 1L;

	public Home_Page() {
		setBackground(UIManager.getColor("Button.darkShadow"));
		setLayout(new BorderLayout(0, 0));
		
		JLabel lblWelcomeToPoint = new JLabel("Welcome to Point of Sale System");
		lblWelcomeToPoint.setVerticalTextPosition(SwingConstants.BOTTOM);
		lblWelcomeToPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToPoint.setHorizontalTextPosition(SwingConstants.CENTER);
		lblWelcomeToPoint.setIcon(new ImageIcon(Home_Page.class.getResource("/imageIcon/icons8_Cash_Register1_64.png")));
		lblWelcomeToPoint.setFont(new Font("Arial", Font.BOLD, 30));
		lblWelcomeToPoint.setForeground(Color.WHITE);
		add(lblWelcomeToPoint);
		
		JLabel lblProvideByG = new JLabel("Provide by G5 Smart Group Solution");
		lblProvideByG.setForeground(UIManager.getColor("CheckBox.highlight"));
		lblProvideByG.setFont(new Font("Arial", Font.BOLD, 20));
		lblProvideByG.setHorizontalAlignment(SwingConstants.CENTER);
		lblProvideByG.setHorizontalTextPosition(SwingConstants.CENTER);
		add(lblProvideByG, BorderLayout.SOUTH);
		

	}

}
