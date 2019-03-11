package g5a_app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import resource.Customer_Accessable;
import resource.Member_Accessable;
import resource.Person_Accessable_Static;
import resource.Product_Accessable_Static;
import resource.ReferenceMethod;
import resource.UserPerforment;

public class Main_Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Home_Page home_Page = new Home_Page();
	About_Us about_Us = new About_Us();
	Setting_Page setting_Page = new Setting_Page();
	Cash_Register_Page cash_Register_Page = new Cash_Register_Page(this);
	Customer_Page customer_Page = new Customer_Page();
	Reporting_Page reporting_Page = new Reporting_Page();
	Stock_Page stock_Page = new Stock_Page();

	public static JPanel contentpane;
	private JPanel pnlHome;
	private JLabel lblHome;
	private JPanel pnlAbout_us, pnlMenuBar;
	private JLabel lblAbout_us;

	private JPanel pnlCash;
	private JPanel pnlCustomer;
	private JPanel pnlReport;
	private JPanel pnlStock;
	private JPanel pnlSetting;
	private JPanel pnlLogout;
	private JLabel lblCash;
	private JLabel lblCustomer;
	private JLabel lblReport;
	private JLabel lblStock;
	private JLabel lblSetting;
	private JLabel lblLogout;
	private JPanel pnlTransaction;
	private JPanel pnlTitlebar;
	private JLabel lblTitle;
	private JLabel lblMenu;
	private JLabel lblTime;

	public Main_Frame() {
		
		ReferenceMethod.findMaxIDSaleDetail();
		ReferenceMethod.findMaxIDSales();
		initialize();
	}

	private void dynamicTimeShow() {

		Date date = new Date();

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent eve) {
				Date time = new Date();
				SimpleDateFormat siFormat = new SimpleDateFormat("hh:mm:ss a  ");
				lblTime.setText("" + simpleDateFormat.format(date) + "  " + siFormat.format(time));
			}
		}).start();

	}
	// Home Page

	private void lblHome_Clicked(MouseEvent eve) {
		if (Person_Accessable_Static.isCashperm() == true) {
			pnlTransaction.add(home_Page, BorderLayout.CENTER);
			cash_Register_Page.setVisible(false);
			home_Page.setVisible(true);
			about_Us.setVisible(false);
			setting_Page.setVisible(false);
			customer_Page.setVisible(false);
			stock_Page.setVisible(false);
			reporting_Page.setVisible(false);
		} else
			JOptionPane.showMessageDialog(null, "You doesn't get permission to access Cash ", "Permissions",
					JOptionPane.ERROR_MESSAGE);
	}

	// end homepage
	private void lblCash_Clicked(MouseEvent eve) {
		if (Person_Accessable_Static.isCashperm() == true) {
			pnlTransaction.add(cash_Register_Page, BorderLayout.CENTER);
			cash_Register_Page.setVisible(true);
			home_Page.setVisible(false);
			about_Us.setVisible(false);
			setting_Page.setVisible(false);
			customer_Page.setVisible(false);
			stock_Page.setVisible(false);
			reporting_Page.setVisible(false);
		} else
			JOptionPane.showMessageDialog(null, "You doesn't get permission to access Cash ", "Permissions",
					JOptionPane.ERROR_MESSAGE);
	}

	private void lblCustomer_Clicked(MouseEvent eve) {
		if (Person_Accessable_Static.isCustomerperm() == true) {
			pnlTransaction.add(customer_Page, BorderLayout.CENTER);
			about_Us.setVisible(false);
			setting_Page.setVisible(false);
			cash_Register_Page.setVisible(false);
			customer_Page.setVisible(true);
			stock_Page.setVisible(false);
			reporting_Page.setVisible(false);
		} else
			JOptionPane.showMessageDialog(null, "You doesn't get permission to access Customer ", "Permissions",
					JOptionPane.ERROR_MESSAGE);
	}

	private void lblReport_Clicked(MouseEvent eve) {
		if (Person_Accessable_Static.isReportperm() == true) {
			pnlTransaction.add(reporting_Page, BorderLayout.CENTER);
			about_Us.setVisible(false);
			setting_Page.setVisible(false);
			cash_Register_Page.setVisible(false);
			customer_Page.setVisible(false);
			stock_Page.setVisible(false);
			reporting_Page.setVisible(true);
		} else
			JOptionPane.showMessageDialog(null, "You doesn't get permission to access Report ", "Permissions",
					JOptionPane.ERROR_MESSAGE);
	}

	private void lblStock_Clicked(MouseEvent eve) {
		if (Person_Accessable_Static.isStockperm() == true) {
			pnlTransaction.add(stock_Page, BorderLayout.CENTER);
			about_Us.setVisible(false);
			setting_Page.setVisible(false);
			cash_Register_Page.setVisible(false);
			customer_Page.setVisible(false);
			stock_Page.setVisible(true);
			reporting_Page.setVisible(false);
		} else
			JOptionPane.showMessageDialog(null, "You doesn't get permission to access Stock ", "Permissions",
					JOptionPane.ERROR_MESSAGE);
	}

	private void logout_Clicked(MouseEvent eve) {

		Login_Frame.accessables.clear();
		Product_Accessable_Static.cateArr.clear();
		Product_Accessable_Static.proArr.clear();
		Person_Accessable_Static.accTypeArr.clear();
		Product_Accessable_Static.proByCate.clear();
		UserPerforment.userPerformentsArr.clear();
		Customer_Accessable.customer_Accessables_Arr.clear();
		Member_Accessable.member_Accessables_Arr.clear();
		Login_Frame login_Frame = new Login_Frame();
		login_Frame.login_frame.setVisible(true);
		login_Frame.login_frame.setLocationRelativeTo(null);
		login_Frame.login_frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.dispose();
	}

	@Override
	public void dispose() {
		UserPerforment.setLogoutTimeS(ReferenceMethod.getTimeShow());
		UserPerforment.setLogoutDateS(ReferenceMethod.getDateShow());
		// ReferenceMethod.storeUserReportToDatabase();
		super.dispose();
	}

	private void lblAbout_us_Clicked(MouseEvent eve) {

		pnlTransaction.add(about_Us);
		about_Us.setVisible(true);
		setting_Page.setVisible(false);
		cash_Register_Page.setVisible(false);
		customer_Page.setVisible(false);
		stock_Page.setVisible(false);
		reporting_Page.setVisible(false);

	}

	private void lblSetting_Clicked(MouseEvent eve) {

		if (Person_Accessable_Static.isSettingperm() == true) {
			pnlTransaction.add(setting_Page, BorderLayout.CENTER);
			about_Us.setVisible(false);
			setting_Page.setVisible(true);
			cash_Register_Page.setVisible(false);
			customer_Page.setVisible(false);
			stock_Page.setVisible(false);
			reporting_Page.setVisible(false);
		} else
			JOptionPane.showMessageDialog(null, "You doesn't get permission to access Setting ", "Permissions",
					JOptionPane.ERROR_MESSAGE);
	}

	private void initialize() {

		dynamicTimeShow();
		setSize(1280, 720);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				arg0.getWindow().dispose();
			}
		});

		// pnlMenuBar.setVisible(false);

		contentpane = new JPanel();
		contentpane.setBackground(new Color(112, 128, 144));
		setContentPane(contentpane);
		contentpane.setLayout(new BorderLayout(0, 0));

		pnlMenuBar = new JPanel();
		pnlMenuBar.setBackground(new Color(0, 128, 128));
		contentpane.add(pnlMenuBar, BorderLayout.WEST);
		pnlMenuBar.setLayout(new GridLayout(10, 1, 10, 5));
		// homepage
		pnlHome = new JPanel();
		pnlHome.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlHome);
		pnlHome.setLayout(new BorderLayout(0, 0));

		lblHome = new JLabel("  Home  ");
		lblHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblHome_Clicked(eve);
			}
		});
		lblHome.setHorizontalAlignment(SwingConstants.LEFT);
		lblHome.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Home_32.png")));
		lblHome.setBorder(null);
		lblHome.setBackground(new Color(0, 128, 128));
		lblHome.setOpaque(true);
		lblHome.setForeground(new Color(255, 255, 255));
		lblHome.setFont(new Font("Arial", Font.BOLD, 15));
		pnlHome.add(lblHome, BorderLayout.CENTER);
		addActionEffectToButton(lblHome);
		//
		pnlCash = new JPanel();
		pnlCash.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlCash);
		pnlCash.setLayout(new BorderLayout(0, 0));

		lblCash = new JLabel("  Cash Register  ");
		lblCash.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblCash_Clicked(eve);
			}
		});
		lblCash.setHorizontalAlignment(SwingConstants.LEFT);
		lblCash.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Cash_Register_32.png")));
		lblCash.setBorder(null);
		lblCash.setBackground(new Color(0, 128, 128));
		lblCash.setOpaque(true);
		lblCash.setForeground(new Color(255, 255, 255));
		lblCash.setFont(new Font("Arial", Font.BOLD, 15));
		pnlCash.add(lblCash, BorderLayout.CENTER);
		addActionEffectToButton(lblCash);
		//
		pnlCustomer = new JPanel();
		pnlCustomer.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlCustomer);
		pnlCustomer.setLayout(new BorderLayout(0, 0));

		lblCustomer = new JLabel("  Customer    ");
		lblCustomer.setHorizontalAlignment(SwingConstants.LEFT);
		lblCustomer.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Customer_32.png")));
		lblCustomer.setBorder(null);
		lblCustomer.setBackground(new Color(0, 128, 128));
		lblCustomer.setOpaque(true);
		lblCustomer.setForeground(new Color(255, 255, 255));
		lblCustomer.setFont(new Font("Arial", Font.BOLD, 15));
		pnlCustomer.add(lblCustomer, BorderLayout.CENTER);
		lblCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblCustomer_Clicked(eve);
			}
		});
		addActionEffectToButton(lblCustomer);

		pnlStock = new JPanel();
		pnlStock.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlStock);
		pnlStock.setLayout(new BorderLayout(0, 0));

		lblStock = new JLabel("  Stock    ");
		lblStock.setHorizontalAlignment(SwingConstants.LEFT);
		lblStock.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Sell_Stock_32.png")));
		lblStock.setBorder(null);
		lblStock.setBackground(new Color(0, 128, 128));
		lblStock.setOpaque(true);
		lblStock.setForeground(new Color(255, 255, 255));
		lblStock.setFont(new Font("Arial", Font.BOLD, 15));
		pnlStock.add(lblStock, BorderLayout.CENTER);
		lblStock.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblStock_Clicked(eve);
			}
		});
		addActionEffectToButton(lblStock);

		pnlReport = new JPanel();
		pnlReport.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlReport);
		pnlReport.setLayout(new BorderLayout(0, 0));

		lblReport = new JLabel("  Report    ");
		lblReport.setHorizontalAlignment(SwingConstants.LEFT);
		lblReport.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Accounting_32.png")));
		lblReport.setBorder(null);
		lblReport.setBackground(new Color(0, 128, 128));
		lblReport.setOpaque(true);
		lblReport.setForeground(new Color(255, 255, 255));
		lblReport.setFont(new Font("Arial", Font.BOLD, 15));
		pnlReport.add(lblReport, BorderLayout.CENTER);
		lblReport.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent eve) {
				lblReport_Clicked(eve);
			}
		});
		addActionEffectToButton(lblReport);

		pnlSetting = new JPanel();
		pnlSetting.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlSetting);
		pnlSetting.setLayout(new BorderLayout(0, 0));

		lblSetting = new JLabel("  Setting    ");
		lblSetting.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetting.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblSetting_Clicked(eve);
			}
		});
		lblSetting.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Settings_32.png")));
		lblSetting.setBorder(null);
		lblSetting.setBackground(new Color(0, 128, 128));
		lblSetting.setOpaque(true);
		lblSetting.setForeground(new Color(255, 255, 255));
		lblSetting.setFont(new Font("Arial", Font.BOLD, 15));
		pnlSetting.add(lblSetting, BorderLayout.CENTER);
		addActionEffectToButton(lblSetting);

		pnlAbout_us = new JPanel();
		pnlAbout_us.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlAbout_us);
		pnlAbout_us.setLayout(new BorderLayout(0, 0));

		lblAbout_us = new JLabel("  About Us    ");
		lblAbout_us.setHorizontalAlignment(SwingConstants.LEFT);
		lblAbout_us.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Customer_32.png")));
		lblAbout_us.setBorder(null);
		lblAbout_us.setBackground(new Color(0, 128, 128));
		lblAbout_us.setOpaque(true);
		lblAbout_us.setFont(new Font("Arial", Font.BOLD, 15));
		lblAbout_us.setForeground(new Color(255, 255, 255));
		pnlAbout_us.add(lblAbout_us, BorderLayout.CENTER);
		lblAbout_us.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				lblAbout_us_Clicked(eve);
			}
		});
		addActionEffectToButton(lblAbout_us);

		pnlLogout = new JPanel();
		pnlLogout.setBackground(new Color(47, 79, 79));
		pnlMenuBar.add(pnlLogout);
		pnlLogout.setLayout(new BorderLayout(0, 0));

		lblLogout = new JLabel("  Logout   ");
		lblLogout.setHorizontalAlignment(SwingConstants.LEFT);
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {
				logout_Clicked(eve);
			}
		});
		lblLogout.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Logout_Rounded_Left_32.png")));
		lblLogout.setBorder(null);
		lblLogout.setBackground(new Color(0, 128, 128));
		lblLogout.setOpaque(true);
		lblLogout.setForeground(new Color(255, 255, 255));
		lblLogout.setFont(new Font("Arial", Font.BOLD, 15));
		pnlLogout.add(lblLogout, BorderLayout.CENTER);
		addActionEffectToButton(lblLogout);

		pnlTransaction = new JPanel();
		pnlTransaction.setBackground(new Color(211, 211, 211));
		contentpane.add(pnlTransaction, BorderLayout.CENTER);
		pnlTransaction.setLayout(new BorderLayout(0, 0));

		pnlTransaction.add(customer_Page, BorderLayout.CENTER);
		pnlTransaction.add(stock_Page, BorderLayout.CENTER);
		pnlTransaction.add(reporting_Page, BorderLayout.CENTER);
		pnlTransaction.add(setting_Page, BorderLayout.CENTER);
		pnlTransaction.add(cash_Register_Page, BorderLayout.CENTER);
		about_Us.setBackground(new Color(255, 255, 255));
		pnlTransaction.add(about_Us);
		about_Us.setVisible(true);
		setting_Page.setVisible(false);
		cash_Register_Page.setVisible(false);
		customer_Page.setVisible(false);
		stock_Page.setVisible(false);
		reporting_Page.setVisible(false);

		pnlTitlebar = new JPanel();
		pnlTitlebar.setBackground(new Color(0, 128, 128));
		contentpane.add(pnlTitlebar, BorderLayout.NORTH);
		pnlTitlebar.setLayout(new BorderLayout(0, 0));

		lblTitle = new JLabel();
		lblTitle.setText("Logged as : ");
		lblTitle.setForeground(new Color(176, 196, 222));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTitlebar.add(lblTitle, BorderLayout.CENTER);

		lblMenu = new JLabel("  Menu  ");
		lblMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent eve) {

				if (pnlMenuBar.isVisible() == true) {
					pnlMenuBar.setVisible(false);
				} else if (pnlMenuBar.isVisible() == false) {
					pnlMenuBar.setVisible(true);
				}
			}

		});
		lblMenu.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Circled_Menu_32.png")));
		lblMenu.setForeground(Color.WHITE);
		lblMenu.setFont(new Font("Arial", Font.BOLD, 15));
		pnlTitlebar.add(lblMenu, BorderLayout.WEST);

		String name = Person_Accessable_Static.getUsername();

		lblTime = new JLabel("");
		lblTitle.setText("Logged as : " + name);
		lblTime.setIcon(new ImageIcon(Main_Frame.class.getResource("/imageIcon/icons8_Schedule_16.png")));
		lblTime.setForeground(new Color(255, 255, 255));
		pnlTitlebar.add(lblTime, BorderLayout.EAST);

	}

	private void addActionEffectToButton(final JLabel lbl) {
		final Color colorA = new Color(115, 198, 182);
		final Color colorB = new Color(0, 128, 128);

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
