package GUI;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import Design.Combobox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import Design.GradientButton;

public class AccountingView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountingView frame = new AccountingView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccountingView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1040);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên :");
		lblNewLabel.setForeground(Color.decode("#CB356B"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(32, 15, 198, 30);
		contentPane.add(lblNewLabel);
		
		JLabel MaNVtxt = new JLabel("New label");
		MaNVtxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		MaNVtxt.setBounds(240, 15, 257, 30);
		contentPane.add(MaNVtxt);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 215, 1831, 694);
		
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblThiGiant = new JLabel("Thời gian đặt hàng :");
		lblThiGiant.setForeground(new Color(203, 53, 107));
		lblThiGiant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblThiGiant.setBounds(32, 78, 198, 30);
		contentPane.add(lblThiGiant);
		
		JLabel DateTxt = new JLabel("New label");
		DateTxt.setFont(new Font("Tahoma", Font.PLAIN, 20));
		DateTxt.setBounds(240, 78, 257, 30);
		contentPane.add(DateTxt);
		
		JLabel lblTngTin = new JLabel("Tổng tiền :");
		lblTngTin.setForeground(new Color(203, 53, 107));
		lblTngTin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTngTin.setBounds(1444, 920, 116, 30);
		contentPane.add(lblTngTin);
		
		JLabel MaNVtxt_1 = new JLabel("New label");
		MaNVtxt_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		MaNVtxt_1.setBounds(1454, 960, 293, 30);
		contentPane.add(MaNVtxt_1);
		
		JLabel lblLoin = new JLabel("Loại đơn :");
		lblLoin.setForeground(new Color(203, 53, 107));
		lblLoin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblLoin.setBounds(995, 15, 103, 30);
		contentPane.add(lblLoin);
		
		Combobox comboBox = new Combobox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tại chỗ", "Mang đi"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setBounds(1133, 3, 172, 44);
		contentPane.add(comboBox);
		
		JLabel lblTn = new JLabel("Tên :");
		lblTn.setForeground(new Color(203, 53, 107));
		lblTn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTn.setBounds(995, 78, 103, 30);
		contentPane.add(lblTn);
		
		JLabel lblaCh = new JLabel("Địa chỉ :");
		lblaCh.setForeground(new Color(203, 53, 107));
		lblaCh.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblaCh.setBounds(995, 129, 103, 30);
		contentPane.add(lblaCh);
		
		JLabel lblSinThoi = new JLabel("Số điện thoại :");
		lblSinThoi.setForeground(new Color(203, 53, 107));
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSinThoi.setBounds(995, 174, 150, 30);
		contentPane.add(lblSinThoi);
		
		textField = new JTextField();
		textField.setBounds(1133, 82, 172, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(1133, 129, 172, 30);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(1133, 174, 172, 30);
		contentPane.add(textField_2);
		
		JLabel lblSNhnMn = new JLabel("Số nhận món :");
		lblSNhnMn.setForeground(new Color(203, 53, 107));
		lblSNhnMn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSNhnMn.setBounds(1389, 78, 137, 30);
		contentPane.add(lblSNhnMn);
		
		textField_3 = new JTextField();
		textField_3.setBounds(1526, 78, 86, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		GradientButton btnNewButton_1 = new GradientButton("Thanh Toán", "#a8ff78", "#78ffd6");
		btnNewButton_1.setText("Xác nhận");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setFocusPainted(false);
		btnNewButton_1.setBounds(1666, 915, 197, 40);
		contentPane.add(btnNewButton_1);
	}
}
