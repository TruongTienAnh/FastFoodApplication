/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import Design.GradientButton;

import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import BUS.Login_BUS;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.Toolkit;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginView extends javax.swing.JFrame {
	private char echo;
	private boolean reveal;
	private GroupLayout groupLayout;
	private LoginView self;
   
    public LoginView() {
    	self = this;
    	
    	setTitle("Đăng nhập");
    	setSize(new Dimension(630,300));
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	setLocationRelativeTo(null);
    	setIconImage(Toolkit.getDefaultToolkit().getImage(LoginView.class.getResource("/IMG/FastMeal.png")));
    	getContentPane().setBackground(new Color(82, 76, 103));
    	
    	initComponents();
    	getContentPane().setLayout(groupLayout);
    	setVisible(true);
    	requestFocusInWindow();
    }

    
    private void initComponents() {
    	JLabel lblNewLabel = new JLabel("");
    	lblNewLabel.addComponentListener(new ComponentAdapter() {
    		@Override
    		public void componentResized(ComponentEvent e) {
    			ImageIcon icon = new ImageIcon(new ImageIcon(LoginView.class.getResource("/IMG/FastMeal.png")).getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),Image.SCALE_SMOOTH));
    			lblNewLabel.setIcon(icon);
    		}
    	});
    	lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/IMG/FastMeal.png")));
    	
    	username_txt = new JTextField();
    	username_txt.addFocusListener(new FocusAdapter() {
    		@Override
    		public void focusGained(FocusEvent e) {
    			if(username_txt.getText().equalsIgnoreCase("Tên đăng nhập")) {
    				username_txt.setText("");
    			};
    		}
    		
    		@Override
    		public void focusLost(FocusEvent e) {
    			if(username_txt.getText().trim().equalsIgnoreCase("")) {
    				username_txt.setText("Tên đăng nhập");
    			}
    		}
    	});
    	username_txt.setText("Tên đăng nhập");
    	username_txt.setForeground(new Color(255, 255, 128));
    	username_txt.setBackground(new Color(82, 76, 102));
    	username_txt.setFont(new Font("SansSerif", Font.PLAIN, 20));
    	username_txt.setColumns(10);
    	username_txt.setBorder(new EmptyBorder(0,0,0,0));
    	
    	
    	password_txt = new JPasswordField();
    	password_txt.addKeyListener(new KeyAdapter() {
    		@Override
    		public void keyTyped(KeyEvent e) {
    			if(reveal==true) {
    				reveal=false;
    				password_txt.setEchoChar(echo);
    			}
    		}
    	});
    	password_txt.setText("Mật khẩu");
    	password_txt.setBackground(new Color(82, 76, 102));
    	password_txt.setFont(new Font("SansSerif", Font.PLAIN, 18));
    	password_txt.setForeground(new Color(255, 255, 128));
    	password_txt.setColumns(10);
    	password_txt.setBorder(new EmptyBorder(0,0,0,0));
    	password_txt.addFocusListener(new FocusAdapter() {
    		@Override
    		public void focusGained(FocusEvent e) {
    			if(password_txt.getText().equalsIgnoreCase("Mật khẩu")) {
    				password_txt.setText("");
    			};
    		}
    		
    		@Override
    		public void focusLost(FocusEvent e) {
    			if(password_txt.getText().trim().equalsIgnoreCase("")) {
    				password_txt.setText("Mật khẩu");
    			}
    		}
    	});
    	
    	echo = password_txt.getEchoChar();
    	password_txt.setEchoChar((char)'\0');
    	reveal = true;
    	
    	JButton reveal_pass_btn = new JButton("");
    	reveal_pass_btn.setToolTipText("Hiển thị mật khẩu");
    	reveal_pass_btn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			
    			if(reveal == false) {
    				reveal_pass_btn.setIcon(new ImageIcon(LoginView.class.getResource("/IMG/unreveal.png")));
    				password_txt.setEchoChar((char) '\0');
    				reveal = true;
    			}else {
    				if(!password_txt.getText().equalsIgnoreCase("Mật khẩu")) {
    					reveal_pass_btn.setIcon(new ImageIcon(LoginView.class.getResource("/IMG/reveal-icon.png")));
        				password_txt.setEchoChar(echo);
        				reveal = false;
    				}
    				
    			}
    		}
    	});
    	reveal_pass_btn.setIcon(new ImageIcon(LoginView.class.getResource("/IMG/reveal-icon.png")));
    	reveal_pass_btn.setContentAreaFilled(false);
    	reveal_pass_btn.setBorderPainted(false);
    	reveal_pass_btn.setFocusPainted(false);
    	reveal_pass_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	JLabel lblNewLabel_1 = new JLabel("");
    	lblNewLabel_1.setOpaque(true);
    	lblNewLabel_1.setBackground(new Color(255, 0, 0));
    	
    	
    	JLabel lblNewLabel_1_1 = new JLabel("");
    	lblNewLabel_1_1.setOpaque(true);
    	lblNewLabel_1_1.setBackground(new Color(255, 0, 0));
    	
    	
    	
    	GradientButton login_btn = new GradientButton("New button","#CB356B","#BD3F32");
    	login_btn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			int lg_rs = new Login_BUS().login(self);
    			if(lg_rs == 1) {
    				self.setVisible(false);
    			}else if(lg_rs==-1) {
    				JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại");
    			}
    		}
    	});
    	login_btn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			login_btn.setForeground(new Color(255,255,0));
    		}
    		
    		@Override
    		public void mouseExited(MouseEvent e) {
    			login_btn.setForeground(new Color(255,255,255));
    		}
    	});
    	login_btn.setText("Đăng nhập");
    	login_btn.setFont(new Font("SansSerif", Font.PLAIN, 15));
    	
    	JLabel lblNewLabel_2 = new JLabel("Quên mật khẩu ? Click vào");
    	lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
    	lblNewLabel_2.setForeground(new Color(255, 255, 255));
    	lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 15));
    	
    	JButton quen_mat_khau_btn = new JButton("đây");
    	quen_mat_khau_btn.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseEntered(MouseEvent e) {
    			quen_mat_khau_btn.setForeground(Color.decode("#CB356B"));
    		}
    		@Override
    		public void mouseExited(MouseEvent e) {
    			quen_mat_khau_btn.setForeground(new Color(255, 255, 255));
    		}
    	});
    	quen_mat_khau_btn.setForeground(new Color(255, 255, 128));
    	quen_mat_khau_btn.setBackground(new Color(82, 76, 102));
    	quen_mat_khau_btn.setHorizontalAlignment(SwingConstants.LEFT);
    	quen_mat_khau_btn.setFont(new Font("SansSerif", Font.PLAIN, 15));
    	quen_mat_khau_btn.setContentAreaFilled(false);
    	quen_mat_khau_btn.setBorderPainted(false);
    	quen_mat_khau_btn.setBorder(new EmptyBorder(0,0,0,0));
    	quen_mat_khau_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    	
    	groupLayout = new GroupLayout(getContentPane());
    	groupLayout.setHorizontalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 249, Short.MAX_VALUE)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(134)
    						.addComponent(login_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    						.addGap(110))
    					.addGroup(groupLayout.createSequentialGroup()
    						.addGap(62)
    						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
    							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
    							.addComponent(lblNewLabel_1_1, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
    							.addComponent(username_txt, GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
    							.addGroup(groupLayout.createSequentialGroup()
    								.addComponent(password_txt, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
    								.addPreferredGap(ComponentPlacement.RELATED)
    								.addComponent(reveal_pass_btn, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
    							.addGroup(groupLayout.createSequentialGroup()
    								.addGap(0)
    								.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
    								.addPreferredGap(ComponentPlacement.RELATED)
    								.addComponent(quen_mat_khau_btn)
    								.addGap(59)))))
    				.addContainerGap())
    	);
    	groupLayout.setVerticalGroup(
    		groupLayout.createParallelGroup(Alignment.LEADING)
    			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 261, Short.MAX_VALUE)
    			.addGroup(groupLayout.createSequentialGroup()
    				.addGap(49)
    				.addComponent(username_txt, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
    				.addGap(4)
    				.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
    					.addComponent(reveal_pass_btn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    					.addComponent(password_txt, GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
    				.addPreferredGap(ComponentPlacement.UNRELATED)
    				.addComponent(login_btn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    				.addPreferredGap(ComponentPlacement.RELATED)
    				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
    					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
    					.addComponent(quen_mat_khau_btn, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE))
    				.addContainerGap())
    	);

 
    }

    
    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginView();
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
//    private frm.frmTrangChu frmTrangChu1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField txtMaSanPham_SanPham1;
    public JTextField username_txt;
    public JPasswordField password_txt;
}
