package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import BUS.CartView_BUS;
import BUS.SaleView_BUS;
import DTO.MONAN;
import Design.GradientButton;
import Design.RoundTextfield;
import test.CustomButton;
import test.testFrame;

import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;

public class AddCartFrame extends JFrame {

	private JPanel contentPane;
	private RoundTextfield textField;
	
	private GradientButton btnNewButton_2;
	
	private MONAN food;
	private ButtonGroup bg;
	private CustomRadioButton S_radio;
	private CustomRadioButton M_radio;
	private CustomRadioButton L_radio;
	private JLabel total_price;
	private static SaleView sale_view;
	private JLabel nameLabel;
	
	private double totalPrice;
	private double currentUnitPrice;
	private int currentQuantity;
	private String currentSize;
	private GradientButton btnNewButton_3;

	/**
	 * Create the frame.
	 */
	public AddCartFrame(SaleView orderView1,MONAN food) {
		sale_view = orderView1;
		this.food = food;
		this.currentQuantity = 1;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				sale_view.pw.setEnabled(false);
			}
			@Override
			public void windowClosed(WindowEvent e) {
				sale_view.pw.setEnabled(true);
				sale_view.pw.requestFocus();
				sale_view.odvf.truNguyenLieuTrongGioHang();
				sale_view.odvf.displayItem();
			}
		});
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 515);
		setLocationRelativeTo(null);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(167, 167, 167));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		GradientButton btnNewButton = new GradientButton("-", "#CB356B", "#CB356B");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decrease();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(254, 391, 50, 32);
		contentPane.add(btnNewButton);

		
		textField = new RoundTextfield("");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setText("1");
		textField.setEditable(false);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!Character.isDigit(c)) {
					e.consume();
				}
			}
		});
		textField.setBounds(151, 391, 93, 32);
		contentPane.add(textField);
		
		textField.setColumns(10);

		GradientButton btnNewButton_1 = new GradientButton("+", "#CB356B", "#CB356B");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				increase();
			}
		});
		btnNewButton_1.setText("+");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton_1.setBounds(91, 391, 51, 32);
		contentPane.add(btnNewButton_1);
		

		btnNewButton_2 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sale_view.odvf.themVaoGioHang(food, currentUnitPrice, currentSize, currentQuantity, totalPrice);
				new CartView_BUS(sale_view.cartView).displaySubTotal();
				JOptionPane.showMessageDialog(null, "Thêm vào giỏ thành công");
				
//				System.out.println("add");
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_2.setBounds(151, 472, 93, 32);
		contentPane.add(btnNewButton_2);

		JLabel imageLabel = new JLabel("");
		imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imageLabel.setIcon(new javax.swing.ImageIcon(testFrame.class.getResource(food.getImage())));
		imageLabel.setBounds(47, 6, 309, 200);
		contentPane.add(imageLabel);

		if (!food.getType().equalsIgnoreCase("Fried Chicken") 
			&& !food.getType().equalsIgnoreCase("Hamburger")
			&& !food.getType().equalsIgnoreCase("Combo"))
			displaySize();

		JLabel lblNewLabel_1 = new JLabel("Price : ");
		lblNewLabel_1.setForeground(new Color(255, 255, 128));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(91, 434, 69, 27);
		contentPane.add(lblNewLabel_1);

		total_price = new JLabel("1 $");
		total_price.setHorizontalAlignment(SwingConstants.CENTER);
		total_price.setFont(new Font("Tahoma", Font.PLAIN, 18));
		total_price.setBounds(151, 434, 164, 27);
		contentPane.add(total_price);

		
		nameLabel = new JLabel("");
		nameLabel.setForeground(Color.decode("#BD3F32"));
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setText(food.getName());
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nameLabel.setBounds(0, 217, 400, 27);
		contentPane.add(nameLabel);

		
		btnNewButton_3 = new GradientButton("","#999999","#999999");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_3.setColor1("#eeeeee");
				btnNewButton_3.setColor2("#eeeeee");
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_3.setColor1("#999999");
				btnNewButton_3.setColor2("#999999");
			}
		});
		btnNewButton_3.setIcon(new ImageIcon(AddCartFrame.class.getResource("/IMG/x-cross-icon_Copy.png")));
		btnNewButton_3.setBounds(358, 0, 40, 40);
		contentPane.add(btnNewButton_3);
		
		JTextPane MoTaTxt = new JTextPane();
		MoTaTxt.setEditable(false);
		MoTaTxt.setBackground(new Color(167, 167, 167));
		MoTaTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		MoTaTxt.setText(food.getDecription());
		MoTaTxt.setBounds(86, 300, 226, 80);
		
		contentPane.add(MoTaTxt);
		setUndecorated(true);
		calculatePrice();
	}

	private void displaySize() {
		S_radio = new CustomRadioButton(Color.decode("#CB356B"), Color.white);
		S_radio.setBorder(new LineBorder(Color.black, 2));
		S_radio.setText("S");
		S_radio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		S_radio.setBounds(120, 252, 40, 23);
		S_radio.setSelected(true);
		S_radio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatePrice();
				
			}
		});
		contentPane.add(S_radio);

		

		M_radio = new CustomRadioButton(Color.decode("#CB356B"), Color.WHITE);
		M_radio.setText("M");
		M_radio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		M_radio.setBorder(new LineBorder(Color.black, 2));
		M_radio.setBounds(170, 252, 40, 23);
		M_radio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatePrice();
				
			}
		});
		contentPane.add(M_radio);
	
		

		L_radio = new CustomRadioButton(Color.decode("#CB356B"), Color.WHITE);
		L_radio.setText("L");
		L_radio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		L_radio.setBorder(new LineBorder(Color.black, 2));
		L_radio.setBounds(220, 252, 40, 23);
		L_radio.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculatePrice();
				
			}
		});
		contentPane.add(L_radio);

		bg = new ButtonGroup();
		bg.add(S_radio);
		bg.add(M_radio);
		bg.add(L_radio);
	}

	public void increase() {
		int cnum = Integer.parseInt(textField.getText());
		cnum++;
		//kiểm tra nguyên liệu
		if(sale_view.odvf.kiemTraNguyenLieu(food.getId(),cnum)) {
			currentQuantity = cnum;
			textField.setEditable(true);
			textField.setText("" + cnum);
			textField.setEditable(false);
			calculatePrice();
		}else {
			JOptionPane.showMessageDialog(null,"Hết nguyên liệu!");
		}
		
		
		
	}

	public void decrease() {
		int cnum = Integer.parseInt(textField.getText());
		if (cnum > 1)
			cnum--;
		currentQuantity = cnum;
		textField.setEditable(true);
		textField.setText("" + cnum);
		textField.setEditable(false);
		calculatePrice();
	}

	public void calculatePrice() {
		if(S_radio!=null) {
			if (S_radio.isSelected()) {
				currentUnitPrice = food.getUnitPrice();
				currentSize = "S";
			} else if (M_radio.isSelected()) {
				currentUnitPrice = food.getUnitPrice() * 1.5;
				currentSize = "M";
			} else if (L_radio.isSelected()) {
				currentUnitPrice = food.getUnitPrice() * 2;
				currentSize = "L";
			}
		}else {
			currentUnitPrice = food.getUnitPrice();
		}
		
		totalPrice = currentUnitPrice * currentQuantity;
		totalPrice = Math.round(totalPrice*100)*1.0/100;
		
		total_price.setText(""+String.valueOf(totalPrice)+"VND");
	}
}
