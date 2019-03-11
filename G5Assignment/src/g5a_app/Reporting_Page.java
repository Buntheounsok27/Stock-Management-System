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
import javax.swing.UIManager;
import java.awt.SystemColor;

public class Reporting_Page extends JPanel {

	UserReport_Page userReport_Page = new UserReport_Page();
	XReadingDaily_Page xReadingDaily_Page = new XReadingDaily_Page();
	XReadingMonthly_Page xReadingMonthly_Page = new XReadingMonthly_Page();
	CategoryDaily_Page categoryDaily_Page = new CategoryDaily_Page();
	CategoryMonthly_Page categoryMonthly_Page = new CategoryMonthly_Page();

	private static final long serialVersionUID = 1L;
	private JPanel pnlTransaction;

	public Reporting_Page() {
		setBackground(UIManager.getColor("Button.darkShadow"));
		setLayout(new BorderLayout(0, 0));

		initcompenent();
	}

	private void lbluserlog_Clicked(MouseEvent eve) {
		pnlTransaction.add(userReport_Page, BorderLayout.CENTER);
		userReport_Page.setVisible(true);
		xReadingDaily_Page.setVisible(false);
		xReadingMonthly_Page.setVisible(false);
		categoryDaily_Page.setVisible(false);
		categoryMonthly_Page.setVisible(false);
	}

	private void lblXreadingD_Clicked(MouseEvent eve) {
		pnlTransaction.add(xReadingDaily_Page, BorderLayout.CENTER);
		userReport_Page.setVisible(false);
		xReadingDaily_Page.setVisible(true);
		xReadingMonthly_Page.setVisible(false);
		categoryDaily_Page.setVisible(false);
		categoryMonthly_Page.setVisible(false);
	}

	private void lblXreadingM_Clicked(MouseEvent eve) {
		pnlTransaction.add(xReadingMonthly_Page, BorderLayout.CENTER);
		userReport_Page.setVisible(false);
		xReadingDaily_Page.setVisible(false);
		xReadingMonthly_Page.setVisible(true);
		categoryDaily_Page.setVisible(false);
		categoryMonthly_Page.setVisible(false);
	}

	private void lblCAD_Clicked(MouseEvent eve) {
		pnlTransaction.add(categoryDaily_Page, BorderLayout.CENTER);
		userReport_Page.setVisible(false);
		xReadingDaily_Page.setVisible(false);
		xReadingMonthly_Page.setVisible(false);
		categoryDaily_Page.setVisible(true);
		categoryMonthly_Page.setVisible(false);
	}

	private void lblCAM_Clicked(MouseEvent eve) {
		pnlTransaction.add(categoryMonthly_Page, BorderLayout.CENTER);
		userReport_Page.setVisible(false);
		xReadingDaily_Page.setVisible(false);
		xReadingMonthly_Page.setVisible(false);
		categoryDaily_Page.setVisible(false);
		categoryMonthly_Page.setVisible(true);
	}

	private void initcompenent() {
		setLayout(new BorderLayout(0, 0));

		JPanel pnlMenuBar = new JPanel();
		pnlMenuBar.setBackground(new Color(105, 105, 105));
		add(pnlMenuBar, BorderLayout.WEST);
		pnlMenuBar.setLayout(new GridLayout(10, 1, 5, 5));

		JLabel lblXreading = new JLabel("     X-READING");
		lblXreading.setVerticalAlignment(SwingConstants.BOTTOM);
		lblXreading.setOpaque(true);
		lblXreading.setForeground(Color.WHITE);
		lblXreading.setFont(new Font("Arial", Font.BOLD, 12));
		lblXreading.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblXreading);

		JLabel lblXRDaily = new JLabel("    Daily");
		lblXRDaily.setBackground(new Color(105, 105, 105));
		lblXRDaily.setOpaque(true);
		lblXRDaily.setForeground(new Color(255, 255, 255));
		lblXRDaily.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblXRDaily);
		addActionEffectToButton(lblXRDaily);
		lblXRDaily.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblXreadingD_Clicked(eve);
			}
		});

		JLabel lblXRMontly = new JLabel("    Montly ");
		lblXRMontly.setOpaque(true);
		lblXRMontly.setHorizontalTextPosition(SwingConstants.LEADING);
		lblXRMontly.setBackground(new Color(105, 105, 105));
		lblXRMontly.setForeground(new Color(255, 255, 255));
		lblXRMontly.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblXRMontly);
		lblXRMontly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblXreadingM_Clicked(eve);
			}
		});
		addActionEffectToButton(lblXRMontly);

		JLabel lblCategorySales = new JLabel("     CATEGORY SALES");
		lblCategorySales.setVerticalAlignment(SwingConstants.BOTTOM);
		lblCategorySales.setOpaque(true);
		lblCategorySales.setForeground(Color.WHITE);
		lblCategorySales.setFont(new Font("Arial", Font.BOLD, 10));
		lblCategorySales.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblCategorySales);

		JLabel lblCADaily = new JLabel("    Daily");
		lblCADaily.setOpaque(true);
		lblCADaily.setForeground(Color.WHITE);
		lblCADaily.setFont(new Font("Arial", Font.BOLD, 15));
		lblCADaily.setBackground(SystemColor.controlDkShadow);
		lblCADaily.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblCAD_Clicked(eve);
			}
		});
		pnlMenuBar.add(lblCADaily);

		JLabel lblCAMontly = new JLabel("    Montly ");
		lblCAMontly.setOpaque(true);
		lblCAMontly.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCAMontly.setForeground(Color.WHITE);
		lblCAMontly.setFont(new Font("Arial", Font.BOLD, 15));
		lblCAMontly.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblCAMontly);
		lblXRMontly.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblCAM_Clicked(eve);
			}
		});

		pnlTransaction = new JPanel();
		pnlTransaction.setBackground(new Color(245, 245, 245));
		add(pnlTransaction, BorderLayout.CENTER);
		pnlTransaction.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		add(panel, BorderLayout.NORTH);

		JLabel lblReport = new JLabel("Report");
		panel.add(lblReport);
		lblReport.setFont(new Font("Arial", Font.BOLD, 15));
		lblReport.setForeground(new Color(255, 255, 255));
		lblReport.setOpaque(true);
		lblReport.setBackground(new Color(105, 105, 105));
		lblReport.setHorizontalAlignment(SwingConstants.CENTER);

		addActionEffectToButton(lblCADaily);
		addActionEffectToButton(lblCAMontly);

		JLabel lblUserLog = new JLabel("     User Peforment");
		lblUserLog.setVerticalAlignment(SwingConstants.BOTTOM);
		lblUserLog.setOpaque(true);
		lblUserLog.setForeground(Color.WHITE);
		lblUserLog.setFont(new Font("Arial", Font.BOLD, 12));
		lblUserLog.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblUserLog);

		JLabel lblUserLog_1 = new JLabel("    User Log");
		lblUserLog_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lbluserlog_Clicked(arg0);
			}
		});
		lblUserLog_1.setOpaque(true);
		lblUserLog_1.setForeground(Color.WHITE);
		lblUserLog_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblUserLog_1.setBackground(SystemColor.controlDkShadow);
		pnlMenuBar.add(lblUserLog_1);

		pnlTransaction.add(userReport_Page, BorderLayout.CENTER);
		pnlTransaction.add(xReadingDaily_Page, BorderLayout.CENTER);
		pnlTransaction.add(xReadingMonthly_Page, BorderLayout.CENTER);
		pnlTransaction.add(categoryDaily_Page, BorderLayout.CENTER);
		pnlTransaction.add(categoryMonthly_Page, BorderLayout.CENTER);

		addActionEffectToButton(lblUserLog_1);

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
