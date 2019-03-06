package g5a_app;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import resource.Catagory_Accessable;
import resource.Customer_Accessable;
import resource.Person_Accessable_Static;
import resource.Product_Accessable_Static;
import resource.Products_Accessable;
import resource.ReferenceMethod;
import resource.Sale_Detail;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cash_Register_Page extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Sale_Detail> details = new ArrayList<>();
	
	private DefaultTableModel dTableModel;
	private static DefaultListModel<String> dListModel;
	
	private JScrollPane scrollPane;
	
	protected static JComboBox<String> comboBox;
	private JTextField txtQty;
	private JTextField txtAmount;
	private JTable tblInvioce;
	private JList<String> list;
	private static int lstSel,rowTbSel;
	
	private JLabel lblSubtotal,lblShSubTotal,lblShCash,lblShTotal,lblShChange,lblShDate,lblShBy;
	public static JLabel lblShCustomer,lblShDiscount;
	
	private static double subtotal,total,change,discount = 0,cash;
	
	private JButton btnAdd,btnPay,btnUpdateItem,btnRemoveItem,btnCheckOut,btnVoidInvoice,btnCashIn;
	
	public static void storeDataToCombobox() 
	{
		comboBox.removeAllItems();
		for(Catagory_Accessable cboShow : Product_Accessable_Static.cateArr)
		{
			comboBox.addItem(cboShow.getCataNameD().toString());
		}
		ReferenceMethod.storeToArrByCategory("Default");
	}
	
	private void storeDataToList()
	{
		dListModel.removeAllElements();
		for(Products_Accessable showOnList : Product_Accessable_Static.proByCate)
		{
			dListModel.addElement(showOnList.getPnamebyCate().toString());
		}
	}
	
	private void lblShCustomer_Clicked(MouseEvent eve,JFrame jf)
	{
		Customer_Selected customer_Selected = new Customer_Selected(jf);
		customer_Selected.setVisible(true);
		customer_Selected.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setEnabled(false);
		

	}
	
	private void lstItem_Clicked(MouseEvent eve)
	{
		lstSel = list.getSelectedIndex();
		if(lstSel>-1)
		{
			tblInvioce.clearSelection();
			txtQty.setEditable(true);
			btnAdd.setEnabled(true);
			btnCashIn.setEnabled(false);
			btnCheckOut.setEnabled(false);
			btnRemoveItem.setEnabled(false);
			btnUpdateItem.setEnabled(false);
			txtQty.requestFocus();
			
		}
	}
	
	private void btnAdd_Clicked(ActionEvent eve)
	{
		if(!txtQty.getText().equals(""))
		{
			ReferenceMethod.getUnitPriceProductFromDB(dListModel, lstSel);
			Object[] datarow = {txtQty.getText(),dListModel.getElementAt(lstSel),(Double.parseDouble(txtQty.getText())*Double.parseDouble(Product_Accessable_Static.getPpriceS()))};
			dTableModel.addRow(datarow);
			Sale_Detail sale_Detail = new Sale_Detail();
			sale_Detail.setId("1");
			sale_Detail.setSaleID("1");
			sale_Detail.setProID(Product_Accessable_Static.getPidS());
			sale_Detail.setQty(Integer.parseInt(txtQty.getText()));
			
			details.add(sale_Detail);
			btnPay.setEnabled(true);
			btnVoidInvoice.setEnabled(true);
			txtQty.setText("");
			

			
			
			subtotal += Double.parseDouble(dTableModel.getValueAt(tblInvioce.getRowCount()-1,2).toString());
			
			lblShSubTotal.setText("$ "+subtotal);
			
			dateTimeShow();
			
		}else JOptionPane.showMessageDialog(null, "Please Enter Quality of item ...!", "Warning", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void dateTimeShow()
	{
		Date date = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yyyy");
			
				Date time = new Date();
				SimpleDateFormat siFormat = new SimpleDateFormat("hh:mm:ss a  ");
				
				lblShDate.setText(""+simpleDateFormat.format(date)+"  "+siFormat.format(time));
			
	}
	
	private void btnPay_Clicked(ActionEvent eve)
	{
		ReferenceMethod.getUnitPriceProductFromDB(dListModel, lstSel);
		btnAdd.setEnabled(false);
		btnPay.setText("Cancel Pay");
		txtAmount.setEditable(true);
		btnPay.setBackground(Color.red);
		btnCashIn.setEnabled(true);
		txtQty.setEditable(false);
		txtAmount.requestFocus();
		list.setEnabled(false);
		list.clearSelection();
		
		///discount = Double.parseDouble(lblShDiscount.getText());
		
		total = subtotal ;//*discount;
		
		lblShTotal.setText(" $ "+total);
		
		
		
	}
	
	private void btnCancelPay_Clicked(ActionEvent eve)
	{
		btnAdd.setEnabled(true);
		btnPay.setText("Pay");
		txtAmount.setEditable(false);
		btnPay.setBackground(new Color(60, 179, 113));
		btnCashIn.setEnabled(false);
		btnCheckOut.setEnabled(false);
		list.setEnabled(true);
		total = 0;
		cash = 0;
		lblShCash.setText("0.00");
	}
	
	private void btnUpdateItem_Clicked(ActionEvent eve)
	{
		dTableModel.setValueAt(txtQty.getText(),rowTbSel,0);
		//details.se    //set(rowTbSel, Integer.parseInt(txtQty.getText()));
	}
	
	private void btnRemoveItem_Clicked(ActionEvent eve)
	{
		dTableModel.removeRow(rowTbSel);
		details.remove(rowTbSel);
	}
	
	private void btnCashIn_Clicked(ActionEvent eve)
	{
		
		DecimalFormat decimalFormat = new DecimalFormat("$  0.00 ");
		
		if(!txtAmount.getText().equals(""))
		{
			btnCashIn.setEnabled(false);
			btnCheckOut.setEnabled(true);
			tblInvioce.clearSelection();
			btnUpdateItem.setEnabled(false);
			btnRemoveItem.setEnabled(false);
			
			cash = Double.parseDouble(txtAmount.getText());
			lblShCash.setText(" $ "+cash);
			
			change = cash - total;
			
			
			lblShChange.setText(decimalFormat.format(change));//" $ "+change);
			
			txtAmount.setText("");
			txtAmount.setEditable(false);
			txtAmount.getCaret().setVisible(false);
			txtQty.setText("");
		}else JOptionPane.showMessageDialog(null, "Please Enter Cash...!", "Warning", JOptionPane.PLAIN_MESSAGE);
	}
	
	private void btnCheckOut_Clicked(ActionEvent eve)
	{
			btnCheckOut.setEnabled(false);
			btnVoidInvoice.setEnabled(false);
			dTableModel.setRowCount(0);
			btnPay.setEnabled(false);
			btnPay.setText("Pay");
			btnPay.setBackground(new Color(60, 179, 113));
			txtAmount.setEditable(false);
			txtQty.setEditable(false);
			txtQty.setText("");
			list.setEnabled(true);
			lblShSubTotal.setText("0.00");
			lblShCash.setText("0.00");
			lblShTotal.setText("0.00");
			lblShChange.setText("0.00");
			lblShDate.setText("");
			
//			for(Sale_Detail sh : details)
//			{
//				System.out.println(""+sh.getId()+"\t"+sh.getSaleID()+"\t"+sh.getProID()+"\n"+sh.getQty()+"\n");
//			}
			
			details.clear();
	}
	
	private void btnVoidInvoice_Clicked(ActionEvent eve)
	{
		btnCheckOut.setEnabled(false);
		btnVoidInvoice.setEnabled(false);
		dTableModel.setRowCount(0);
		btnPay.setEnabled(false);
		btnPay.setText("Pay");
		btnPay.setBackground(new Color(60, 179, 113));
		txtAmount.setEditable(false);
		txtQty.setEditable(false);
		list.setEnabled(true);
		txtQty.getCaret().setVisible(false);
		list.clearSelection();
		txtAmount.getCaret().setVisible(false);
		btnAdd.setEnabled(false);
		btnCashIn.setEnabled(false);
		txtQty.setText("");
		btnRemoveItem.setEnabled(false);
		btnUpdateItem.setEnabled(false);
		lblShSubTotal.setText("0.00");
		lblShCash.setText("0.00");
		lblShTotal.setText("0.00");
		lblShChange.setText("0.00");
		lblShDate.setText("");
	}
	
	private void rowData_Selected(MouseEvent eve)
	{
		rowTbSel = tblInvioce.getSelectedRow();
		if(rowTbSel>-1)
		{
			list.clearSelection();
			btnAdd.setEnabled(false);
			btnRemoveItem.setEnabled(true); btnUpdateItem.setEnabled(true);
			txtQty.setEditable(true);
			txtQty.setText(dTableModel.getValueAt(rowTbSel, 0).toString());
			
		}
	}
	
	


	public Cash_Register_Page(JFrame jFrame) {
		

		
		dListModel = new DefaultListModel<String>();
		storeDataToList();
		
		setBackground(UIManager.getColor("Button.darkShadow"));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlTransaction = new JPanel();
		pnlTransaction.setBackground(new Color(47, 79, 79));
		add(pnlTransaction, BorderLayout.CENTER);
		pnlTransaction.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel pnlListProduct = new JPanel();
		pnlTransaction.add(pnlListProduct);
		pnlListProduct.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(47, 79, 79));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(20);
		pnlListProduct.add(panel, BorderLayout.NORTH);
		
		JLabel lblCategory = new JLabel("CATEGORY");
		lblCategory.setForeground(new Color(255, 255, 255));
		panel.add(lblCategory);
		
		comboBox = new JComboBox<String>();
		
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(comboBox.getItemCount()>0)
				{
					Product_Accessable_Static.proByCate.clear();
					dListModel.removeAllElements();
					ReferenceMethod.storeToArrByCategory(comboBox.getSelectedItem().toString());
					storeDataToList();
				}
				
			}
		});
		
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		pnlListProduct.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblListOfProducts = new JLabel("LIST OF PRODUCTS");
		lblListOfProducts.setForeground(new Color(255, 255, 255));
		lblListOfProducts.setBackground(new Color(34, 139, 34));
		lblListOfProducts.setOpaque(true);
		lblListOfProducts.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lblListOfProducts, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);
		
		list = new JList<String>(dListModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lstItem_Clicked(arg0);
			}
		});
		scrollPane.setViewportView(list);
		
		JPanel panel_2 = new JPanel();
		pnlListProduct.add(panel_2, BorderLayout.SOUTH);
		storeDataToCombobox();
		
		
		JPanel pnlSale = new JPanel();
		pnlTransaction.add(pnlSale);
		pnlSale.setLayout(new GridLayout(2, 0, 0, 0));
		
		JPanel pnlSaleUp = new JPanel();
		pnlSale.add(pnlSaleUp);
		pnlSaleUp.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(47, 79, 79));
		pnlSaleUp.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(47, 79, 79));
		panel_3.add(panel_4, BorderLayout.NORTH);
		panel_4.setLayout(new GridLayout(2, 0, 0, 50));
		
		JLabel lblQty = new JLabel("Quality");
		lblQty.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblQty.setForeground(new Color(255, 255, 255));
		lblQty.setBackground(new Color(47, 79, 79));
		lblQty.setOpaque(true);
		panel_4.add(lblQty);
		
		txtQty = new JTextField();
		txtQty.setEditable(false);
		txtQty.setFont(new Font("Arial", Font.PLAIN, 15));
		panel_4.add(txtQty);
		txtQty.setColumns(10);
		
		JLabel lblCashIn = new JLabel("Cash");
		lblCashIn.setOpaque(true);
		lblCashIn.setForeground(Color.WHITE);
		lblCashIn.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCashIn.setBackground(new Color(47, 79, 79));
		panel_4.add(lblCashIn);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		panel_4.add(txtAmount);
		txtAmount.setColumns(10);
		
		JLabel label = new JLabel(" ");
		label.setOpaque(true);
		label.setBackground(new Color(47, 79, 79));
		label.setFont(new Font("Arial", Font.PLAIN, 22));
		pnlSaleUp.add(label, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("          ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(47, 79, 79));
		lblNewLabel_1.setOpaque(true);
		pnlSaleUp.add(lblNewLabel_1, BorderLayout.WEST);
		
		JLabel lblNewLabel_2 = new JLabel("          ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(47, 79, 79));
		lblNewLabel_2.setOpaque(true);
		pnlSaleUp.add(lblNewLabel_2, BorderLayout.EAST);
		
		JPanel pnlSaleDown = new JPanel();
		pnlSaleDown.setBackground(new Color(47, 79, 79));
		pnlSale.add(pnlSaleDown);
		pnlSaleDown.setLayout(new GridLayout(5, 1, 0, 0));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(47, 79, 79));
		pnlSaleDown.add(panel_5);
		
		btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Arial", Font.PLAIN, 15));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.setBackground(new Color(70, 130, 180));
		btnAdd.setRequestFocusEnabled(false);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnAdd_Clicked(arg0);
			}
		});
		panel_5.add(btnAdd);
		
		
		btnPay = new JButton("Pay");
		btnPay.setFont(new Font("Arial", Font.PLAIN, 15));
		btnPay.setForeground(new Color(255, 255, 255));
		btnPay.setBackground(new Color(60, 179, 113));
		btnPay.setRequestFocusEnabled(false);
		
		btnPay.setEnabled(false);
		panel_5.add(btnPay);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(47, 79, 79));
		pnlSaleDown.add(panel_6);
		
		btnUpdateItem = new JButton("Update Item");
		btnUpdateItem.setFont(new Font("Arial", Font.PLAIN, 15));
		btnUpdateItem.setForeground(new Color(255, 255, 255));
		btnUpdateItem.setBackground(new Color(0, 128, 128));
		btnUpdateItem.setRequestFocusEnabled(false);
		btnUpdateItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUpdateItem_Clicked(arg0);
			}
		});
		btnUpdateItem.setEnabled(false);
		panel_6.add(btnUpdateItem);
		
		btnRemoveItem = new JButton("Remove Item");
		btnRemoveItem.setFont(new Font("Arial", Font.PLAIN, 15));
		btnRemoveItem.setForeground(new Color(255, 255, 255));
		btnRemoveItem.setBackground(new Color(199, 21, 133));
		btnRemoveItem.setRequestFocusEnabled(false);
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnRemoveItem_Clicked(arg0);
			}
		});
		btnRemoveItem.setEnabled(false);
		panel_6.add(btnRemoveItem);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(47, 79, 79));
		pnlSaleDown.add(panel_7);
		
		btnCashIn = new JButton("Cash In");
		btnCashIn.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCashIn.setForeground(new Color(255, 255, 255));
		btnCashIn.setBackground(new Color(50, 205, 50));
		btnCashIn.setRequestFocusEnabled(false);
		btnCashIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCashIn_Clicked(arg0);
			}
		});
		btnCashIn.setEnabled(false);
		panel_7.add(btnCashIn);
		
		btnCheckOut = new JButton("Check Out");
		btnCheckOut.setFont(new Font("Arial", Font.PLAIN, 15));
		btnCheckOut.setForeground(new Color(255, 255, 255));
		btnCheckOut.setBackground(new Color(0, 191, 255));
		btnCheckOut.setRequestFocusEnabled(false);
		btnCheckOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnCheckOut_Clicked(arg0);
			}
		});
		btnCheckOut.setEnabled(false);
		panel_7.add(btnCheckOut);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBackground(new Color(47, 79, 79));
		pnlSaleDown.add(panel_8);
		
		btnVoidInvoice = new JButton("Void Invoice");
		btnVoidInvoice.setFont(new Font("Arial", Font.PLAIN, 15));
		btnVoidInvoice.setForeground(new Color(255, 255, 255));
		btnVoidInvoice.setBackground(new Color(255, 20, 147));
		btnVoidInvoice.setRequestFocusEnabled(false);
		btnVoidInvoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnVoidInvoice_Clicked(arg0);
			}
		});
		btnVoidInvoice.setEnabled(false);
		panel_8.add(btnVoidInvoice);
		
		JPanel pnlInvioce = new JPanel();
		pnlInvioce.setBackground(new Color(255, 255, 255));
		pnlTransaction.add(pnlInvioce);
		pnlInvioce.setLayout(new BorderLayout(0, 0));
		
		Object[] nameHTbl = {"Qty"," Description ","Amount ($)"};
		
		dTableModel = new DefaultTableModel();
		dTableModel.setColumnIdentifiers(nameHTbl);
		
		JPanel panel_12 = new JPanel();
		panel_12.setBackground(new Color(255, 255, 255));
		pnlInvioce.add(panel_12, BorderLayout.CENTER);
		panel_12.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPaneInvoice = new JScrollPane();
		scrollPaneInvoice.setBorder(null);
		scrollPaneInvoice.setOpaque(false);
		scrollPaneInvoice.getViewport().setBackground(Color.white);
		panel_12.add(scrollPaneInvoice, BorderLayout.CENTER);
		
		tblInvioce = new JTable(dTableModel);
		tblInvioce.setBorder(null);
		tblInvioce.setBackground(new Color(255, 255, 255));
		tblInvioce.setShowVerticalLines(false);
		tblInvioce.setShowHorizontalLines(false);
		tblInvioce.setShowGrid(false);
		tblInvioce.setFont(new Font("Arial", Font.PLAIN, 15));
		scrollPaneInvoice.setViewportView(tblInvioce);
		scrollPaneInvoice.setBackground(new Color(255, 255, 255));
		
		tblInvioce.getTableHeader().setBackground(Color.white);
		
		tblInvioce.getColumnModel().getColumn(0).setPreferredWidth(75);
		tblInvioce.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		
		JPanel panel_10 = new JPanel();
		panel_10.setBackground(new Color(255, 255, 255));
		panel_12.add(panel_10, BorderLayout.SOUTH);
		panel_10.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel_11 = new JPanel();
		panel_11.setBackground(new Color(255, 255, 255));
		panel_10.add(panel_11);
		panel_11.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblSubtotal = new JLabel("Subtotal");
		lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(lblSubtotal);
		
		lblShSubTotal = new JLabel("0.00");
		lblShSubTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblShSubTotal);
		
		JLabel lblDis = new JLabel("Discount");
		lblDis.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(lblDis);
		
		lblShDiscount = new JLabel("0.00");
		lblShDiscount.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblShDiscount);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(lblTotal);
		
		lblShTotal = new JLabel("0.00");
		lblShTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblShTotal);
		
		JLabel lblCash = new JLabel("Cash");
		lblCash.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_11.add(lblCash);
		
		lblShCash = new JLabel("0.00");
		lblShCash.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_11.add(lblShCash);
		
		JPanel panel_15 = new JPanel();
		panel_15.setBackground(new Color(255, 255, 255));
		panel_10.add(panel_15);
		panel_15.setLayout(new BorderLayout(0, 0));
		
		JLabel lblC = new JLabel(" CHANGE");
		lblC.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_15.add(lblC, BorderLayout.WEST);
		
		lblShChange = new JLabel(" 0.00     ");
		lblShChange.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panel_15.add(lblShChange, BorderLayout.EAST);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 255, 255));
		panel_12.add(panel_9, BorderLayout.NORTH);
		panel_9.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblNewLabel_3 = new JLabel(" ");
		panel_9.add(lblNewLabel_3);
		
		JLabel label_1 = new JLabel(" ");
		panel_9.add(label_1);
		
		JLabel lblReceiptNo = new JLabel("Receipt No :");
		lblReceiptNo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lblReceiptNo);
		
		JLabel lblShReceiptNo = new JLabel(" ");
		lblShReceiptNo.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_9.add(lblShReceiptNo);
		
		JLabel lblCustomerName = new JLabel("Customer :");
		lblCustomerName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lblCustomerName);
		
		lblShCustomer = new JLabel(" ");
		lblShCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblShCustomer_Clicked(arg0,jFrame);
			}
		});
		lblShCustomer.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_9.add(lblShCustomer);
		
		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lblDate);
		
		lblShDate = new JLabel(" ");
		lblShDate.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_9.add(lblShDate);
		
		JLabel lblBy = new JLabel("By :");
		lblBy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panel_9.add(lblBy);
		
		lblShBy = new JLabel(" ");
		lblShBy.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_9.add(lblShBy);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(new Color(255, 255, 255));
		pnlInvioce.add(panel_13, BorderLayout.WEST);
		
		JPanel panel_14 = new JPanel();
		panel_14.setBackground(new Color(255, 255, 255));
		pnlInvioce.add(panel_14, BorderLayout.EAST);
		
		JLabel label_9 = new JLabel(" ");
		pnlInvioce.add(label_9, BorderLayout.SOUTH);
		
		JPanel panel_16 = new JPanel();
		panel_16.setBackground(new Color(255, 255, 255));
		pnlInvioce.add(panel_16, BorderLayout.NORTH);
		panel_16.setLayout(new BorderLayout(0, 0));
		
		JLabel lblInvoice = new JLabel("INVOICE");
		lblInvoice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblInvoice.setHorizontalAlignment(SwingConstants.CENTER);
		panel_16.add(lblInvoice, BorderLayout.NORTH);
		
		JLabel lblCompanyName = new JLabel(" ");
		lblCompanyName.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblCompanyName.setHorizontalAlignment(SwingConstants.CENTER);
		panel_16.add(lblCompanyName, BorderLayout.CENTER);
		tblInvioce.getColumnModel().getColumn(1).setPreferredWidth(500);
		tblInvioce.getColumnModel().getColumn(2).setPreferredWidth(200);
		
		lblShCustomer.setText("To Go");
	
		tblInvioce.getTableHeader().setReorderingAllowed(false);
		
		tblInvioce.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent eve)
			{
				rowData_Selected(eve);
			}
		});
		
		tblInvioce.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		btnPay.addActionListener(this);
		
		tblInvioce.setDefaultEditor(Object.class, null);
		
		lblShBy.setText(Person_Accessable_Static.getUsername());
	}
	
	public void actionPerformed(ActionEvent eve)
	{
		if(eve.getActionCommand().equals("Pay"))
		{
			btnPay_Clicked(eve);
		}
		if(eve.getActionCommand().equals("Cancel Pay"))
		{
			btnCancelPay_Clicked(eve);
		}
	}

}
