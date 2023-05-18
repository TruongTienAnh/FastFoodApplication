package test;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import DTO.MONAN;
import Design.RoundTextfield;
import GUI.ItemPanel;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class OrderTest extends JFrame {

	private JPanel contentPane;
	private RoundTextfield textField;
	private JRadioButton rdbtnNewRadioButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderTest frame = new OrderTest();
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
	public OrderTest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 736);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new LineBorder(new Color(192, 192, 192)));

		ItemPanel ip = new ItemPanel(null,new MONAN("1","Standard Beef Hamburger","Hamburger","/IMG/hamburger1.png", 10.0,"Null"));
		
		contentPane.add(ip);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new RoundTextfield("");
		textField.setBounds(48, 437, 192, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(48, 501, 192, 40);
		contentPane.add(comboBox);
		
		rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(283, 437, 109, 23);
		rdbtnNewRadioButton.setSelected(true);
		contentPane.add(rdbtnNewRadioButton);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton);
		
		
	}
}
