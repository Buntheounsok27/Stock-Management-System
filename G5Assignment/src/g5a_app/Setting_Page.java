package g5a_app;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class Setting_Page extends JPanel {

	private static final long serialVersionUID = 1L;

	Account_Page account_Page = new Account_Page();
	AccountType_Page accountType_Page = new AccountType_Page();

	private JPanel pnlTransaction;

	public Setting_Page() {
		initcompenent();
	}

	private void lblAccount_Clicked(MouseEvent eve) {
		account_Page.setVisible(true);
		accountType_Page.setVisible(false);
	}

	private void lblAccountType_Clicked(MouseEvent eve) {
		pnlTransaction.add(accountType_Page, BorderLayout.CENTER);
		accountType_Page.setVisible(true);
		account_Page.setVisible(false);
	}

	private void initcompenent() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMenuBar = new JPanel();
		pnlMenuBar.setBackground(new Color(105, 105, 105));
		add(pnlMenuBar, BorderLayout.WEST);
		pnlMenuBar.setLayout(new GridLayout(10, 1, 5, 5));

		JLabel lblAccount = new JLabel("   Account");
		lblAccount.setBackground(new Color(105, 105, 105));
		lblAccount.setOpaque(true);
		lblAccount.setForeground(new Color(255, 255, 255));
		lblAccount.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblAccount);
		addActionEffectToButton(lblAccount);
		lblAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblAccount_Clicked(eve);
			}
		});

		JLabel lblAccountType = new JLabel("   Account Type   ");
		lblAccountType.setBackground(new Color(105, 105, 105));
		lblAccountType.setOpaque(true);
		lblAccountType.setForeground(new Color(255, 255, 255));
		lblAccountType.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblAccountType);
		addActionEffectToButton(lblAccountType);

		JLabel lblSetting = new JLabel("   Setting   ");
		lblSetting.setVerticalAlignment(SwingConstants.BOTTOM);
		lblSetting.setOpaque(true);
		lblSetting.setForeground(SystemColor.menu);
		lblSetting.setFont(new Font("Arial", Font.BOLD, 15));
		lblSetting.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblSetting);

		JLabel lblGeneralSetting = new JLabel("   General Setting  ");
		lblGeneralSetting.setOpaque(true);
		lblGeneralSetting.setForeground(Color.WHITE);
		lblGeneralSetting.setFont(new Font("Arial", Font.BOLD, 15));
		lblGeneralSetting.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblGeneralSetting);
		addActionEffectToButton(lblGeneralSetting);

		lblAccountType.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblAccountType_Clicked(eve);
			}
		});

		pnlMenuBar.add(lblGeneralSetting);
		lblGeneralSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {

			}
		});

		pnlTransaction = new JPanel();
		pnlTransaction.setBackground(new Color(245, 245, 245));
		add(pnlTransaction, BorderLayout.CENTER);
		pnlTransaction.setLayout(new BorderLayout(0, 0));

		pnlTransaction.add(accountType_Page, BorderLayout.CENTER);
		pnlTransaction.add(account_Page, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.controlDkShadow);
		add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("Setting");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Arial", Font.BOLD, 15));
		panel.add(label);

		account_Page.setVisible(true);
		accountType_Page.setVisible(false);

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
