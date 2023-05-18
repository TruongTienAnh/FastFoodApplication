package GUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSplitPane;

import Design.CustomLabel;
import Design.GradientButton;

import javax.swing.JPanel;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import BUS.MainView_BUS;

public class MainView extends JFrame {
	public JSplitPane splitPane;
	private GradientButton btnNewButton;
	private ArrayList<JButton> btn_list;
	private JButton OrderTabButton;
	private JButton SaleTabButton;
	private JButton EmployeeTabButton;
	private JButton StorageTabButton;
	private JButton FoodManageButton;
	private JButton StatisticTabButton;
	private JButton LogoutButton;
	private OderView orderView;
	private SaleView sale;
	private EnployeeView empl;
	private FoodManagement foodm;
	private ThongKe tgke;
	private StorageView stoview;
	private JPanel TabPanelLeft;
	private CustomLabel lblNewLabel;
	public String MaNV; // xác định nhân viên nào đang đăng nhập
	private MainView self;

	public MainView(String MaNV) {
		self = this;
		self.setMinimumSize(new Dimension(1280,720));
		this.MaNV = MaNV;
		String chuc_vu = new MainView_BUS().defind_user(self);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainView.class.getResource("/IMG/FastMeal.png")));
		setSize(1920,1040);
//		setUndecorated(true);
		setLocationRelativeTo(null);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		
		btn_list = new ArrayList<JButton>();
		
		btnNewButton = new GradientButton("New button","#CB356B","#CB356B");
		btnNewButton.setIcon(new ImageIcon(MainView.class.getResource("/IMG/order_icon.png")));
		btnNewButton.setText("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(splitPane.getDividerLocation());
				if(splitPane.getDividerLocation()!=1 && splitPane.getDividerLocation()>60)
					splitPane.setDividerLocation(1);
				else if(splitPane.getDividerLocation()!=250)
					splitPane.setDividerLocation(250);
			}
		});
		btnNewButton.setBounds(11, 11, 44, 44);
		
		TabPanelLeft = new JPanel();
		TabPanelLeft.setBackground(new Color(82, 72, 103));
		splitPane.setLeftComponent(TabPanelLeft);
		TabPanelLeft.setLayout(null);
		
		lblNewLabel = new CustomLabel("Danh mục :");
		lblNewLabel.setBounds(0, 0, 249, 67);
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
		
		
		LogoutButton = new JButton("Đăng xuất");
		LogoutButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView login_view = new LoginView();
				login_view.setVisible(true);
				setVisible(false);
			}		
		});
		LogoutButton.setBounds(0, 964, 249, 44);
		LogoutButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				LogoutButton.setForeground(Color.yellow);
				LogoutButton.setBackground(Color.decode("#BD3F32"));
			}
			public void mouseExited(MouseEvent e) {
				LogoutButton.setForeground(Color.white);
				LogoutButton.setBackground(Color.decode("#CB356B"));
			}
			
		});
		LogoutButton.setForeground(Color.white);
		LogoutButton.setBackground(Color.decode("#CB356B"));
		LogoutButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		LogoutButton.setFocusPainted(false);
		LogoutButton.setBorderPainted(false);
//		odv.panel_1.add(btnNewButton); 
		splitPane.setDividerLocation(250);
//		splitPane.setDividerLocation(1);
		splitPane.setEnabled(false); 
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(splitPane, GroupLayout.DEFAULT_SIZE, 1021, Short.MAX_VALUE)
		);
		getContentPane().setLayout(groupLayout);
		
		if(chuc_vu.equalsIgnoreCase("ADMIN")) {
			innitAdmin(); // admin
		}else if(chuc_vu.equalsIgnoreCase("Ban Hang")) {
			innitBanHang(); // nếu là nhân viên bán hàng
		}else if(chuc_vu.equalsIgnoreCase("Quan Ly Kho")) {
			innitKhoHang();	// nếu là nhân viên quản kho
		}
			
		TabPanelLeft.setLayout(null);
		TabPanelLeft.add(lblNewLabel);
		TabPanelLeft.add(LogoutButton);
		
		relocateTheTabButtons();
	}
	
	public void innitBanHang() {

		orderView = new OderView();
		sale = new SaleView(this);
		
		SaleTabButton = new JButton("Bán hàng");
		SaleTabButton.setBounds(0, 67, 249, 76);
		btn_list.add(SaleTabButton);
		SaleTabButton.setFocusPainted(false);
		SaleTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		SaleTabButton.setForeground(Color.decode("#CB356B"));
		SaleTabButton.setBackground(Color.white);
		SaleTabButton.setHorizontalAlignment(SwingConstants.LEFT);
		SaleTabButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		SaleTabButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sale.panel_1.add(btnNewButton);
				splitPane.setRightComponent(sale);
				sale.setVisible(true);
				changeColorButton(SaleTabButton);
			}
		});
		
		
		OrderTabButton = new JButton("Đơn hàng");
		OrderTabButton.setBounds(0, 135, 249, 76);
		btn_list.add(OrderTabButton);
		OrderTabButton.setForeground(Color.decode("#CB356B"));
		OrderTabButton.setBackground(Color.white);
		OrderTabButton.setHorizontalAlignment(SwingConstants.LEADING);
		OrderTabButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		OrderTabButton.setFocusPainted(false);
		OrderTabButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		OrderTabButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(orderView);
				orderView.setVisible(true);
				orderView.add(btnNewButton);
				changeColorButton(OrderTabButton);
			}
		});
		
		TabPanelLeft.add(SaleTabButton);
		TabPanelLeft.add(OrderTabButton);
		
		
	}
	public void innitKhoHang() {
		stoview = new StorageView();
		StorageTabButton = new JButton("Kho hàng");
		StorageTabButton.setIcon(null);
		StorageTabButton.setBounds(0, 359, 249, 76);
		btn_list.add(StorageTabButton);
		StorageTabButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(stoview);
				stoview.add(btnNewButton);
				stoview.setVisible(true);
				changeColorButton(StorageTabButton);
			}
		});
		StorageTabButton.setHorizontalAlignment(SwingConstants.LEADING);
		StorageTabButton.setForeground(new Color(203, 53, 107));
		StorageTabButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		StorageTabButton.setFocusPainted(false);
		StorageTabButton.setBackground(Color.WHITE);
		TabPanelLeft.add(StorageTabButton);
		
	}
	public void innitAdmin() {
		innitBanHang();
		innitKhoHang();
		innitThongKe();
		empl = new EnployeeView();
		
		EmployeeTabButton = new JButton("Nhân Viên");
		EmployeeTabButton.setBounds(0, 209, 249, 76);
		btn_list.add(EmployeeTabButton);
		EmployeeTabButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(empl);
				changeColorButton(EmployeeTabButton);
				empl.setVisible(true);
				empl.add(btnNewButton);
			}
		});
		EmployeeTabButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		EmployeeTabButton.setHorizontalAlignment(SwingConstants.LEADING);
		EmployeeTabButton.setForeground(new Color(203, 53, 107));
		EmployeeTabButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		EmployeeTabButton.setFocusPainted(false);
		EmployeeTabButton.setBackground(Color.WHITE);
		
		
		foodm = new FoodManagement();
		FoodManageButton = new JButton("Món ăn");
		FoodManageButton.setIcon(null);
		FoodManageButton.setBounds(0, 284, 249, 76);
		btn_list.add(FoodManageButton);
		FoodManageButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(foodm);
				foodm.add(btnNewButton);
				foodm.setVisible(true);
				changeColorButton(FoodManageButton);
			}
		});
		FoodManageButton.setHorizontalAlignment(SwingConstants.LEADING);
		FoodManageButton.setForeground(new Color(203, 53, 107));
		FoodManageButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		FoodManageButton.setFocusPainted(false);
		FoodManageButton.setBackground(Color.WHITE);
		
		TabPanelLeft.add(StatisticTabButton);
		TabPanelLeft.add(EmployeeTabButton);
		TabPanelLeft.add(FoodManageButton);
		
	}
	public void innitThongKe() {
		tgke = new ThongKe();
		StatisticTabButton = new JButton("Thống kê");
		StatisticTabButton.setBounds(0, 434, 249, 76);
		StatisticTabButton.setHorizontalAlignment(SwingConstants.LEADING);
		StatisticTabButton.setForeground(new Color(203, 53, 107));
		StatisticTabButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		StatisticTabButton.setFocusPainted(false);
		StatisticTabButton.setBackground(Color.WHITE);
		btn_list.add(StatisticTabButton);
		StatisticTabButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				splitPane.setRightComponent(tgke);
				tgke.add(btnNewButton);
				tgke.setVisible(true);
				changeColorButton(StatisticTabButton);
			}
		});
		
	}
	
	public void changeColorButton(JButton btn) {
		for (int i = 0;i < btn_list.size(); i++) {
			if(!btn_list.get(i).equals(btn)) {
				btn_list.get(i).setForeground(Color.decode("#CB356B"));
				btn_list.get(i).setBackground(Color.white);
			}
			btn.setForeground(Color.decode("#FFEE58"));
			btn.setBackground(Color.decode("#AAAAAA"));
		}
	}
	public void relocateTheTabButtons() {
		int currentY = 67;
		for (int i = 0;i<btn_list.size();i++) {
			btn_list.get(i).setLocation(0, currentY);
			this.TabPanelLeft.add(btn_list.get(i));
			currentY+=76;	
		}
	}
	public static void main(String[] args) {
		MainView mv = new MainView("AD001");
		mv.setVisible(true);
		mv.SaleTabButton.doClick();
	}
}
