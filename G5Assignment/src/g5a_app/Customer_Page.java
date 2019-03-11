package g5a_app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Customer_Page extends JPanel {

	Customer_Info_Page customer_Info_Page = new Customer_Info_Page();
	Member_Page member_Page = new Member_Page();

	private static final long serialVersionUID = 1L;
	private JPanel pnlTransaction;

	public Customer_Page() {
		initcompenent();
	}

	private void lblCust_Clicked(MouseEvent eve) {
		pnlTransaction.add(customer_Info_Page, BorderLayout.CENTER);
		customer_Info_Page.setVisible(true);
		member_Page.setVisible(false);
	}

	private void lblMember_Clicked(MouseEvent eve) {
		pnlTransaction.add(member_Page, BorderLayout.CENTER);
		member_Page.setVisible(true);
		customer_Info_Page.setVisible(false);
	}

	private void initcompenent() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMenuBar = new JPanel();
		pnlMenuBar.setBackground(new Color(105, 105, 105));
		add(pnlMenuBar, BorderLayout.WEST);
		pnlMenuBar.setLayout(new GridLayout(10, 1, 5, 5));

		JLabel lblCust = new JLabel("    Customer");
		lblCust.setBackground(new Color(105, 105, 105));
		lblCust.setOpaque(true);
		lblCust.setForeground(new Color(255, 255, 255));
		lblCust.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblCust);
		addActionEffectToButton(lblCust);
		lblCust.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblCust_Clicked(eve);
			}
		});

		JLabel lblMember = new JLabel("    Member    ");
		lblMember.setOpaque(true);
		lblMember.setHorizontalTextPosition(SwingConstants.LEADING);
		lblMember.setBackground(new Color(105, 105, 105));
		lblMember.setForeground(new Color(255, 255, 255));
		lblMember.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblMember);
		addActionEffectToButton(lblMember);
		lblMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblMember_Clicked(eve);
			}
		});

		pnlTransaction = new JPanel();
		pnlTransaction.setBackground(new Color(245, 245, 245));
		add(pnlTransaction, BorderLayout.CENTER);
		pnlTransaction.setLayout(new BorderLayout(0, 0));

		pnlTransaction.add(member_Page, BorderLayout.CENTER);
		pnlTransaction.add(customer_Info_Page, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		add(panel, BorderLayout.NORTH);

		JLabel lblBar = new JLabel("Customer");
		panel.add(lblBar);
		lblBar.setFont(new Font("Arial", Font.BOLD, 15));
		lblBar.setForeground(new Color(255, 255, 255));
		lblBar.setOpaque(true);
		lblBar.setBackground(new Color(105, 105, 105));
		lblBar.setHorizontalAlignment(SwingConstants.CENTER);
		member_Page.setVisible(false);
		customer_Info_Page.setVisible(true);

	}

	private void addActionEffectToButton(final JLabel lbl) {
		final Color colorA = new Color(47, 79, 79);
		final Color colorB = new Color(105, 105, 105);

		lbl.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent eve) {
				lbl.setBackground(colorA);
			}

			@Override
			public void mouseExited(MouseEvent eve) {
				lbl.setBackground(colorB);
			}
		});
	}

}