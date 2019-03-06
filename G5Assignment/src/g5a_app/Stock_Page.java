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


public class Stock_Page extends JPanel {

	private static final long serialVersionUID = 1L;

	
	private JPanel pnlTransaction ;
	
	CatagoryPage catagoryPage = new CatagoryPage();
	Products_Page products_Page = new Products_Page();
	
	public Stock_Page(){
		initcompenent();
	}
	private void lblCtg_Clicked(MouseEvent eve) {
		pnlTransaction.add(catagoryPage, BorderLayout.CENTER);
		catagoryPage.setVisible(true);
		products_Page.setVisible(false);
	}
	
	private void lblProduct_Clicked(MouseEvent eve)
	{
		pnlTransaction.add(products_Page, BorderLayout.CENTER);
		products_Page.setVisible(true);
		catagoryPage.setVisible(false);
	}
	private void initcompenent()
	{
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMenuBar = new JPanel();
		pnlMenuBar.setBackground(new Color(105, 105, 105));
		add(pnlMenuBar, BorderLayout.WEST);
		pnlMenuBar.setLayout(new GridLayout(10, 1, 5, 5));
		
		JLabel lblProduct = new JLabel("    Product");
		lblProduct.setBackground(new Color(105, 105, 105));
		lblProduct.setOpaque(true);
		lblProduct.setForeground(new Color(255, 255, 255));
		lblProduct.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblProduct);
		addActionEffectToButton(lblProduct);
		lblProduct.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent eve)
			{
				lblProduct_Clicked(eve); 
			}
		});
		
		JLabel lblCatagory = new JLabel("    Catagory    ");
		lblCatagory.setOpaque(true);
		lblCatagory.setHorizontalTextPosition(SwingConstants.LEADING);
		lblCatagory.setBackground(new Color(105, 105, 105));
		lblCatagory.setForeground(new Color(255, 255, 255));
		lblCatagory.setFont(new Font("Arial", Font.BOLD, 15));
		pnlMenuBar.add(lblCatagory);
		addActionEffectToButton(lblCatagory);
		lblCatagory.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent eve)
			{
				lblCtg_Clicked(eve);
			}
		});
		
		pnlTransaction = new JPanel();
		pnlTransaction.setBackground(new Color(245, 245, 245));
		add(pnlTransaction, BorderLayout.CENTER);
		pnlTransaction.setLayout(new BorderLayout(0, 0));
		
		pnlTransaction.add(catagoryPage, BorderLayout.CENTER);
		products_Page.setBackground(new Color(47, 79, 79));
		pnlTransaction.add(products_Page, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(105, 105, 105));
		add(panel, BorderLayout.NORTH);
		
		JLabel lblStock = new JLabel("Stock");
		panel.add(lblStock);
		lblStock.setFont(new Font("Arial", Font.BOLD, 15));
		lblStock.setForeground(new Color(255, 255, 255));
		lblStock.setOpaque(true);
		lblStock.setBackground(new Color(105, 105, 105));
		lblStock.setHorizontalAlignment(SwingConstants.CENTER);
		catagoryPage.setVisible(false);
		products_Page.setVisible(true);

	}
	
	private void addActionEffectToButton(final JLabel lbl)
	{
		final Color colorA = new Color(47, 79, 79);
		final Color colorB = new Color(105, 105, 105);
		
		lbl.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent eve)
			{
				lbl.setBackground(colorA);
			}
			
			public void mouseExited(MouseEvent eve)
			{
				lbl.setBackground(colorB);
			}
		});
	}

}
