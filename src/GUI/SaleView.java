package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BUS.CartView_BUS;
import BUS.SaleView_BUS;
import DAO.DAOMonAn;
import DAO.DAONguyenLieu;
import DTO.*;
import Design.Combobox;
import Design.CustomLabel;
import Design.CustomPanel;
import Design.GradientButton;
import Design.RoundTextfield;

import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.GradientPaint;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.FlowLayout;

public class SaleView extends JPanel {

	private JPanel contentPane;
	public JPanel panel;
	private JTextField textField;
	
	private JLabel lblNewLabel_1;
	public JScrollPane scrollPane;
	public JLabel CartLength;
	public CustomPanel panel_1;
	public MainView pw;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	
	public CartView cartView;
	public SaleView_BUS odvf;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public SaleView(MainView parentwindow) {
		this.pw  = parentwindow;
		SaleView ov = this;
		odvf = new SaleView_BUS(this);
		cartView = new CartView(ov);
		
//		contentPane = new JPanel();
//		contentPane.setBounds(0, 0, 1920, 1080);
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
//		setUndecorated(true);
//		contentPane = new JPanel();
//		contentPane.setBackground(new Color(255, 255, 255));
		setBackground(new Color(255, 255, 255));
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUnitIncrement(20);
		
		
		panel = new JPanel();
		panel.setBackground(new Color(82, 76, 103));
		scrollPane.setViewportView(panel);
		
		odvf.displayItem();
		
		
		panel_1 = new CustomPanel();
		
		CartLength = new JLabel("0");
		CartLength.setHorizontalAlignment(SwingConstants.CENTER);
		CartLength.setForeground(new Color(255, 255, 255));
		CartLength.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		textField = new RoundTextfield("Nhập tên món hoặc mã món");
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField.getText().equalsIgnoreCase("Nhập tên món hoặc mã món")) {
					textField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().equalsIgnoreCase("")) {
					textField.setText("Nhập tên món hoặc mã món");
				}
			}
		});
		textField.setBorder(new EmptyBorder(0,0,0,0));
		textField.setBackground(new Color(255, 255, 255));
		textField.setForeground(Color.decode("#BD3F32"));
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				odvf.searchItem(textField.getText());
				odvf.displayItem();
			}
		});
		textField.setFont(new Font("Tahoma", Font.PLAIN, 21));
		textField.setColumns(10);
		
		GradientButton btnNewButton = new GradientButton("Order","#CB356B","#BD3F32");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				CartView cartView = new CartView(ov);
				new CartView_BUS(cartView).displayCartItems();
				pw.splitPane.setRightComponent(cartView);
				cartView.setVisible(true);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setColor1("#6B356B");
				btnNewButton.setColor2("#5D3F32");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setColor1("#CB356B");
				btnNewButton.setColor2("#BD3F32");
			}
		});
		btnNewButton.setText("");
		btnNewButton.setIcon(new ImageIcon(SaleView.class.getResource("/IMG/cart.png")));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		Combobox comboBox = new Combobox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"ID(A -> Z)", "ID(Z -> A)", "Tên(A -> Z)", "Tên(Z -> A)", "Giá(Thấp -> Cao)", "Giá(Cao -> Thấp)"}));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				System.out.println(comboBox.getSelectedItem().toString());
				if(comboBox.getSelectedItem().toString().equalsIgnoreCase("ID(A -> Z)")) {
					odvf.sortByID( 0);
				}else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("ID(Z -> A)")) {
					odvf.sortByID(1);
				}			
				else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Tên(A -> Z)")) {
					odvf.sortByName(0);
				}else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Tên(Z -> A)")) {
					odvf.sortByName(1);
				}else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Giá(Thấp -> Cao)")) {
					odvf.sortByPrice(0);
				}else if(comboBox.getSelectedItem().toString().equalsIgnoreCase("Giá(Cao -> Thấp)")) {
					odvf.sortByPrice(1);
				}
			}
		});
		
		lblNewLabel_1 = new JLabel("Sắp xếp :");
		lblNewLabel_1.setForeground(new Color(200, 200, 83));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 1280, Short.MAX_VALUE)
					.addGap(10))
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1280, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(0)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 652, Short.MAX_VALUE)
					.addGap(0))
		);
		
		lblNewLabel = new JLabel("");
		
		lblNewLabel_2 = new JLabel("");
		
		lblNewLabel_3 = new JLabel("");
		
		lblNewLabel_4 = new JLabel("");
		
		lblNewLabel_5 = new JLabel("");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 11, Short.MAX_VALUE)
					.addGap(238)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(24)
							.addComponent(CartLength, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(102)
					.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_5, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(lblNewLabel_4, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(CartLength)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(1)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE))
									.addGap(5))
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(lblNewLabel_3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))))
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addGap(11)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		setLayout(groupLayout);
		
	}
	
}
