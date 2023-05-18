package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;


import com.toedter.calendar.JDateChooser;

import BUS.FoodManager_BUS;
import DTO.CHITIET_NL_MA;
import DTO.KHUYENMAI;
import DTO.MONAN;
import DTO.MONAN_KHUYENMAI;
import Design.GradientButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Date;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FoodManagement extends JPanel {
	
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_4;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTable table_4;
	FoodManager_BUS foodBUS = new FoodManager_BUS();
	DefaultTableModel defaultTableModel;
	DefaultTableModel defaultTableModel1;
	DefaultTableModel defaultTableModel2;
	DefaultTableModel defaultTableModel3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoodManagement frame = new FoodManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//loaddata
	private void loaddata(List<MONAN> monans) {
		for(MONAN monan : monans) {
			defaultTableModel.addRow(new Object[] {monan.getId(), monan.getName(), monan.getType(), monan.getImage(),
					monan.getUnitPrice(), monan.getDecription()});
		}
	}
	private void loaddataKhuyenMai(List<KHUYENMAI> khuyenmais) {
		for(KHUYENMAI khuyenmai : khuyenmais) {
			defaultTableModel1.addRow(new Object[] {khuyenmai.getId_khuyen_mai(), khuyenmai.getTen_khuyen_mai(), khuyenmai.getNgay_bat_dau(), khuyenmai.getNgay_ket_thuc(),
					khuyenmai.getMo_ta()});
		}
	}
	private void loaddataCT_NL_MA(List<CHITIET_NL_MA> chitiet_NL_MAs) {
		for(CHITIET_NL_MA chitiet_NL_MA : chitiet_NL_MAs) {
			defaultTableModel3.addRow(new Object[] {chitiet_NL_MA.getId_mon_an(), chitiet_NL_MA.getId_nguyen_lieu(), chitiet_NL_MA.getSoluong()});
		}
	}
	private void loaddataMA_KhuyenMai(List<MONAN_KHUYENMAI> monan_KHUYENMAIs) {
		for(MONAN_KHUYENMAI monan_KHUYENMAI : monan_KHUYENMAIs) {
			defaultTableModel2.addRow(new Object[] { monan_KHUYENMAI.getId_khuyen_mai(),monan_KHUYENMAI.getId_mon_an(), monan_KHUYENMAI.getPhan_tram_giam()});
		}
	}
	/**
	 * Create the frame.
	 */
	public FoodManagement() {
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1040);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeTabColor(tabbedPane);
			}
		});
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Món ăn", null, panel_1, null);
		
		GradientButton btnNewButton_1_3 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemMonAn();
			}
		});
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_1_1 = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaMonAn();
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_2_1 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
		        if (row >= 0) {
		        String id = table.getValueAt(row, 0).toString();
		        foodBUS.deletefoods(id);
		        defaultTableModel.setRowCount(0);
				loaddata(foodBUS.getAllfood());
			}
			}
		});
		JLabel lblNewLabel_2_2_1 = new JLabel("");
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		GradientButton btnNewButton_2 = new GradientButton("Tìm kiếm", "#CB356B", "#BD3F32");
		btnNewButton_2.setPreferredSize(new Dimension(100, 50));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = textField.getText();
				List<MONAN> monans = foodBUS.getFoodbysearch(condition);
				defaultTableModel.setRowCount(0);
				for(MONAN monan : monans) {
					defaultTableModel.addRow(new Object[] {monan.getId(), monan.getName(), monan.getType(), monan.getImage(),
							monan.getUnitPrice(), monan.getDecription()});
				}
			}
		});
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		
		JLabel lblNewLabel_5 = new JLabel("Sắp xếp :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		String[] items = {"Giá tăng dần", "Giá giảm dần"};
		JComboBox<String> comboBox_1 = new JComboBox<String>(items);
		comboBox_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String sortOrder = "";
		        if (comboBox_1.getSelectedItem().toString().equals("Giá tăng dần")) {
		            sortOrder = "ASC";		            
		        } else if (comboBox_1.getSelectedItem().toString().equals("Giá giảm dần")) {
		            sortOrder = "DESC";
		        }
		        List<MONAN> monans = foodBUS.getFoodintoPrice(sortOrder);
	            defaultTableModel.setRowCount(0);
				for(MONAN monan : monans) {
					defaultTableModel.addRow(new Object[] {monan.getId(), monan.getName(), monan.getType(), monan.getImage(),
							monan.getUnitPrice(), monan.getDecription()});
				}
		        
            }
        });
		JLabel lblNewLabel_2_1 = new JLabel("");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1199, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2_1, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
							.addGap(27)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1, 0, 205, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 603, Short.MAX_VALUE)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNewLabel_4_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_2_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(34))
		);
		
		table = new JTable();
		defaultTableModel = new DefaultTableModel();
		table.setModel(defaultTableModel);
		defaultTableModel.addColumn("ID");
		defaultTableModel.addColumn("Tên Món ĂN");
		defaultTableModel.addColumn("Loại Món Ăn");
		defaultTableModel.addColumn("Hình Ảnh");
		defaultTableModel.addColumn("Giá");
		defaultTableModel.addColumn("Mô tả");
		loaddata(foodBUS.getAllfood());
		
		table.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table.getTableHeader().setBackground(Color.decode("#CB356B"));
		table.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		scrollPane_1.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		//khuyến mãi
		
		JPanel panel_1_1 = new JPanel();
		tabbedPane.addTab("Khuyến mãi", null, panel_1_1, null);
		
		GradientButton btnNewButton_1_3_1 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemKhuyenMai();
			}
		});
		btnNewButton_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_1_1_1 = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaKhuyenMai();
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_2_1_1 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
		        if (row >= 0) {
		        String id = table_1.getValueAt(row, 0).toString();
		        foodBUS.deletekhuyenmai(id);
		        defaultTableModel1.setRowCount(0);
				loaddataKhuyenMai(foodBUS.getAllkhuyenmai());
			}
			}
		});
		JLabel lblNewLabel_2_2_1_1 = new JLabel("");
		
		JLabel lblNewLabel_4_1_1 = new JLabel("");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		GradientButton btnNewButton_2_1 = new GradientButton("Tìm kiếm", "#CB356B", "#BD3F32");
		btnNewButton_2_1.setPreferredSize(new Dimension(100, 50));
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = textField_1.getText();
				List<KHUYENMAI> khuyenmais = foodBUS.getKhuyenmaibysearch(condition);
				defaultTableModel1.setRowCount(0);
				for(KHUYENMAI khuyenmai : khuyenmais) {
					defaultTableModel1.addRow(new Object[] {khuyenmai.getId_khuyen_mai(), khuyenmai.getTen_khuyen_mai(), khuyenmai.getNgay_bat_dau(), khuyenmai.getNgay_ket_thuc(),
							khuyenmai.getMo_ta()});
				}
			}
		});
		JLabel lblNewLabel_3_1_1 = new JLabel("");
		
		JLabel lblNewLabel_5_1 = new JLabel("Sắp xếp :");
		lblNewLabel_5_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox comboBox_1_1 = new JComboBox();
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1199, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1_1_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2_1_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2_1_1, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4_1_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
							.addGap(27)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3_1_1, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1_1, 0, 205, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1_1, GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGap(0, 603, Short.MAX_VALUE)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1_1.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_1_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1_1.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNewLabel_4_1_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(scrollPane_1_1, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_1_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_2_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2_2_1_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(34))
		);
		
		table_1 = new JTable();
		defaultTableModel1 = new DefaultTableModel();
		table_1.setModel(defaultTableModel1);
		defaultTableModel1.addColumn("ID");
		defaultTableModel1.addColumn("Tên Khuyến Mãi");
		defaultTableModel1.addColumn("Ngày bắt đầu");
		defaultTableModel1.addColumn("Ngày kết thúc");
		defaultTableModel1.addColumn("Mô tả");
		loaddataKhuyenMai(foodBUS.getAllkhuyenmai());
		
		table_1.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table_1.getTableHeader().setBackground(Color.decode("#CB356B"));
		table_1.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		scrollPane_1_1.setViewportView(table_1);
		panel_1_1.setLayout(gl_panel_1_1);
		
		//chi tiets khuyến mãi
		
		JPanel panel_1_2 = new JPanel();
		tabbedPane.addTab("Chi tiết khuyến mãi", null, panel_1_2, null);
		
		GradientButton btnNewButton_1_3_2 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_1_3_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemChiTietKhuyenMai();
			}
		});
		btnNewButton_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_1_1_2 = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		btnNewButton_1_1_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaChiTietKhuyenMai();
			}
		});
		btnNewButton_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_2_1_2 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_2_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_2.getSelectedRow();
		        if (row >= 0) {
		        String id = table_2.getValueAt(row, 0).toString();
		        String id1 = table_2.getValueAt(row, 1).toString();
		        foodBUS.deleteMA_KhuyenMai(id, id1);
		        defaultTableModel2.setRowCount(0);
				loaddataMA_KhuyenMai(foodBUS.getAllMA_KhuyenMai());
			}
			}
		});
		JLabel lblNewLabel_2_2_1_2 = new JLabel("");
		
		JLabel lblNewLabel_4_1_2 = new JLabel("");
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		
		GradientButton btnNewButton_2_2 = new GradientButton("Tìm kiếm", "#CB356B", "#BD3F32");
		btnNewButton_2_2.setPreferredSize(new Dimension(100, 50));
		btnNewButton_2_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = textField_2.getText();
				List<MONAN_KHUYENMAI> monan_KHUYENMAIs = foodBUS.getMAKhuyenmaibysearch(condition);
				defaultTableModel2.setRowCount(0);
				for(MONAN_KHUYENMAI monan_KHUYENMAI : monan_KHUYENMAIs) {
					defaultTableModel2.addRow(new Object[] {monan_KHUYENMAI.getId_khuyen_mai(), monan_KHUYENMAI.getId_mon_an(), monan_KHUYENMAI.getPhan_tram_giam()});
				}
			}
		});
		JLabel lblNewLabel_3_1_2 = new JLabel("");
		
		JLabel lblNewLabel_5_2 = new JLabel("Sắp xếp :");
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 15)); 
		
		String[] itemss = {"Mã Khuyến Mãi", "Mã món ăn"};
		JComboBox<String> comboBox_1_2 = new JComboBox<String>(itemss);	
		comboBox_1_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String sortOrder = "";
		        if (comboBox_1_2.getSelectedItem().toString().equals("Mã món ăn")) {
		            sortOrder = "IDMONAN";		            
		        } else if (comboBox_1_2.getSelectedItem().toString().equals("Mã Khuyến Mãi")) {
		            sortOrder = "IDKHUYENMAI";
		        }
		        List<MONAN_KHUYENMAI> monan_KHUYENMAIs = foodBUS.getKhuyeMaiMAinto(sortOrder);
	            defaultTableModel2.setRowCount(0);
				for(MONAN_KHUYENMAI monan_KHUYENMAI : monan_KHUYENMAIs) {
					defaultTableModel2.addRow(new Object[] {monan_KHUYENMAI.getId_khuyen_mai(), monan_KHUYENMAI.getId_mon_an(), monan_KHUYENMAI.getPhan_tram_giam()});
				}
		        
            }
        });
		
		JLabel lblNewLabel_2_1_2 = new JLabel("");
		
		JScrollPane scrollPane_1_2 = new JScrollPane();
		scrollPane_1_2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1_2 = new GroupLayout(panel_1_2);
		gl_panel_1_2.setHorizontalGroup(
			gl_panel_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1199, Short.MAX_VALUE)
				.addGroup(gl_panel_1_2.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_2.createSequentialGroup()
							.addComponent(btnNewButton_1_3_2, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1_1_2, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2_1_2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2_1_2, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addGroup(gl_panel_1_2.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4_1_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
							.addGap(27)
							.addComponent(btnNewButton_2_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3_1_2, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_5_2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1_2, 0, 205, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_2_1_2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1_2, GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_panel_1_2.setVerticalGroup(
			gl_panel_1_2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 603, Short.MAX_VALUE)
				.addGroup(gl_panel_1_2.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1_2.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1_2.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1_2.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_1_2.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1_2.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNewLabel_4_1_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(scrollPane_1_2, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1_2.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_2.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_1_3_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_1_1_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_2_1_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2_2_1_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(34))
		);
		
		table_2 = new JTable();
		defaultTableModel2 = new DefaultTableModel();
		table_2.setModel(defaultTableModel2);
		defaultTableModel2.addColumn("Mã Khuyến Mãi");
		defaultTableModel2.addColumn("Mã Món Ăn");
		defaultTableModel2.addColumn("Phần trăm giảm");
		loaddataMA_KhuyenMai(foodBUS.getAllMA_KhuyenMai());
		
		table_2.getColumnModel().getColumn(2).setPreferredWidth(147);
		scrollPane_1_2.setViewportView(table_2);
		panel_1_2.setLayout(gl_panel_1_2);
		
		//chi tiết Nguyên liệu món ăn
		JPanel panel_1_4 = new JPanel();
		tabbedPane.addTab("Chi tiết nguyên liệu", null, panel_1_4, null);
		
		GradientButton btnNewButton_1_3_4 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_1_3_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemChiTietNguyenLieu();
			}
		});
		btnNewButton_1_3_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_1_1_4 = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		btnNewButton_1_1_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaChiTietNguyenLieu();
			}
		});
		btnNewButton_1_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_2_1_4 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2_1_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_2_1_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table_4.getSelectedRow();
		        if (row >= 0) {
		        String id = table_4.getValueAt(row, 0).toString();
		        String id1 = table_4.getValueAt(row, 1).toString();
		        foodBUS.deleteNLMA(id, id1);
		        defaultTableModel3.setRowCount(0);
				loaddataCT_NL_MA(foodBUS.getAllCT_NL_MA());
			}
			}
		});
		
		JLabel lblNewLabel_2_2_1_4 = new JLabel("");
		
		JLabel lblNewLabel_4_1_4 = new JLabel("");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		GradientButton btnNewButton_2_4 = new GradientButton("Tìm kiếm", "#CB356B", "#BD3F32");
		btnNewButton_2_4.setPreferredSize(new Dimension(100, 50));
		btnNewButton_2_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_2_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String condition = textField_4.getText();
				List<CHITIET_NL_MA> chitiet_NL_MAs = foodBUS.getCTNLbysearch(condition);
				defaultTableModel3.setRowCount(0);
				for(CHITIET_NL_MA chitiet_NL_MA : chitiet_NL_MAs) {
					defaultTableModel3.addRow(new Object[] {chitiet_NL_MA.getId_mon_an(), chitiet_NL_MA.getId_nguyen_lieu(), chitiet_NL_MA.getSoluong()});
				}
			}
		});
		
		JLabel lblNewLabel_3_1_4 = new JLabel("");
		
		JLabel lblNewLabel_5_4 = new JLabel("Sắp xếp :");
		lblNewLabel_5_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		String[] itemsss = {"Mã Nguyên Liệu", "Mã món ăn"};
		JComboBox<String> comboBox_1_4 = new JComboBox<String>(itemsss);	
		comboBox_1_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				String sortOrder = "";
		        if (comboBox_1_4.getSelectedItem().toString().equals("Mã món ăn")) {
		            sortOrder = "IDMONAN";		            
		        } else if (comboBox_1_4.getSelectedItem().toString().equals("Mã Nguyên Liệu")) {
		            sortOrder = "IDNGUYENLIEU";
		        }
		        List<CHITIET_NL_MA> chitiet_NL_MAhs = foodBUS.getNGUYENLIEUMAinto(sortOrder);
	            defaultTableModel3.setRowCount(0);
				for(CHITIET_NL_MA chitiet_NL_MA : chitiet_NL_MAhs) {
					defaultTableModel3.addRow(new Object[] {chitiet_NL_MA.getId_mon_an(), chitiet_NL_MA.getId_nguyen_lieu(), chitiet_NL_MA.getSoluong()});
				}
		        
            }
        });
		
		JLabel lblNewLabel_2_1_4 = new JLabel("");
		
		JScrollPane scrollPane_1_4 = new JScrollPane();
		scrollPane_1_4.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1_4 = new GroupLayout(panel_1_4);
		gl_panel_1_4.setHorizontalGroup(
			gl_panel_1_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1199, Short.MAX_VALUE)
				.addGroup(gl_panel_1_4.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1_4.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_4.createSequentialGroup()
							.addComponent(btnNewButton_1_3_4, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1_1_4, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2_1_4, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2_1_4, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addGroup(gl_panel_1_4.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4_1_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
							.addGap(27)
							.addComponent(btnNewButton_2_4, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3_1_4, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_5_4, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox_1_4, 0, 205, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_2_1_4, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1_4, GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_panel_1_4.setVerticalGroup(
			gl_panel_1_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 603, Short.MAX_VALUE)
				.addGroup(gl_panel_1_4.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel_1_4.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox_1_4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2_1_4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1_4.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel_1_4.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3_1_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel_1_4.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2_4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5_4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1_4.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNewLabel_4_1_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(scrollPane_1_4, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1_4.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1_4.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_1_3_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_1_1_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_2_1_4, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_2_2_1_4, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(34))
		);
		
		table_4 = new JTable();
		defaultTableModel3 = new DefaultTableModel();
		table_4.setModel(defaultTableModel3);
		defaultTableModel3.addColumn("Mã Món Ăn");
		defaultTableModel3.addColumn("Mã Nguyên Liệu");
		defaultTableModel3.addColumn("Số Lượng");
		loaddataCT_NL_MA(foodBUS.getAllCT_NL_MA());
		
		scrollPane_1_4.setViewportView(table_4);
		panel_1_4.setLayout(gl_panel_1_4);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1864, Short.MAX_VALUE)
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 934, Short.MAX_VALUE)
					.addGap(46))
		);
		setLayout(groupLayout);;
		
		table_4.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table_4.getTableHeader().setBackground(Color.decode("#CB356B"));
		table_4.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		
		table_4.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table_4.getTableHeader().setBackground(Color.decode("#CB356B"));
		table_4.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
	}
	public void changeTabColor(JTabbedPane self) {
		int s = self.getSelectedIndex();
		for (int i = 0;i<tabbedPane.getTabCount();i++) {
			if(i!=s) {
				self.setForegroundAt(i, Color.decode("#000000"));
				self.setBackgroundAt(i, Color.decode("#FFFFFF"));
			}
		}
		self.setForegroundAt(s, Color.decode("#CB356B"));
	}
	class ThemMonAn extends JFrame {
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField;
		private JTextField textField_4;
		MONAN food;
		public ThemMonAn() {
			setTitle("Thêm món ăn");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,526);
			setLocationRelativeTo(null);
			
			JLabel lblNewLabel_2 = new JLabel("Mã món");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Tên món :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Loại :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JLabel lblVTrLm = new JLabel("hình ảnh :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setText("Thêm");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			
			JLabel lblNewLabel_3 = new JLabel("");
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			textField = new JTextField();
			textField.setColumns(10);
			
			JLabel lblMT = new JLabel("Mô tả :");
			lblMT.setForeground(new Color(191, 0, 0));
			lblMT.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JTextArea textArea = new JTextArea();
			
			JLabel lblGiBn = new JLabel("Giá bán :");
			lblGiBn.setForeground(new Color(191, 0, 0));
			lblGiBn.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = textField.getText();
					String name = textField_3.getText();
					String type = textField_1.getText();
					String image = textField_2.getText();
					double price = Double.parseDouble(textField_4.getText());
					String decription = textArea.getText();
					food = new MONAN(id, name, type, image, price, decription);
					foodBUS.addfoods(food);
					defaultTableModel.setRowCount(0);
					loaddata(foodBUS.getAllfood());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(lblVTrLm)
													.addComponent(lblNewLabel_1))
												.addGap(3))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED))))))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblGiBn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(70)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
											.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
											.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
											.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
										.addGap(56))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())))))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVTrLm))
						.addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGiBn, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(2)
										.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE))
									.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addGap(29)))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(26))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	class SuaMonAn extends JFrame {
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		private JTextField textField_4;
		MONAN food;
		private JComboBox<String> comboBox;
		
		public SuaMonAn() {
			setTitle("Sửa món ăn");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,526);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã món");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Tên món :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Loại :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JLabel lblVTrLm = new JLabel("hình ảnh :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setText("sửa");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JLabel lblNewLabel_3 = new JLabel("");
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			JLabel lblMT = new JLabel("Mô tả :");
			lblMT.setForeground(new Color(191, 0, 0));
			lblMT.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JTextArea textArea = new JTextArea();
			
			JLabel lblGiBn = new JLabel("Giá bán :");
			lblGiBn.setForeground(new Color(191, 0, 0));
			lblGiBn.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			
			comboBox = new JComboBox<>(foodBUS.getIdArray());
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
					String id = comboBox.getSelectedItem().toString();
					String name = textField_3.getText();
					String type = textField_1.getText();
					String image = textField_2.getText();
					double price = Double.parseDouble(textField_4.getText());
					String decription = textArea.getText();
					food = new MONAN(id, name, type, image, price, decription);
					foodBUS.updatefoods(food);
					
					
					defaultTableModel.setRowCount(0);
					loaddata(foodBUS.getAllfood());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2)
											.addPreferredGap(ComponentPlacement.UNRELATED))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
													.addComponent(lblVTrLm)
													.addComponent(lblNewLabel_1))
												.addGap(3))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
												.addPreferredGap(ComponentPlacement.RELATED))))))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblGiBn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
								.addGap(70)))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
									.addGap(56))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(textField_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(comboBox, Alignment.LEADING, 0, 197, Short.MAX_VALUE))
									.addGap(56)))))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblVTrLm))
						.addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblGiBn, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_3)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(30)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(2)
										.addComponent(textArea, GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE))
									.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGap(37))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	class ThemKhuyenMai extends JFrame {
		private JTextField textField_3;
		private JTextField textField;
		KHUYENMAI khuyenmai;
		public ThemKhuyenMai() {
			setTitle("Thêm khuyến mãi");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,489);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã khuyến mãi :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Tên khuyến mãi");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Ngày bắt đầu :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblVTrLm = new JLabel("Ngày kết thúc :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			JLabel lblMT = new JLabel("Mô tả :");
			lblMT.setForeground(new Color(191, 0, 0));
			lblMT.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JTextArea textArea = new JTextArea();
			
			textField = new JTextField();
			textField.setColumns(10);
			
			JDateChooser dateChooser = new JDateChooser();
			
			JDateChooser dateChooser_1 = new JDateChooser();
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String id = textField.getText();
					String name = textField_3.getText();
					Date ngaybd = dateChooser.getDate();
					Date ngaykt = dateChooser_1.getDate();
					String decription = textArea.getText();
					khuyenmai = new KHUYENMAI(id, name,  ngaybd, ngaykt, decription);
					foodBUS.addkhuyenmai(khuyenmai);
					defaultTableModel1.setRowCount(0);
					loaddataKhuyenMai(foodBUS.getAllkhuyenmai());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblVTrLm)
							.addComponent(lblNewLabel_1)
							.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
									.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
											.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
											.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
										.addGap(56))))))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblVTrLm)
							.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(63)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGap(37)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(58))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	class SuaKhuyenMai extends JFrame {
		private JTextField textField_3;
		private JComboBox<String> comboBox;
		KHUYENMAI khuyenmai;
		public SuaKhuyenMai() {
			setTitle("Sửa khuyến mãi");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,489);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã khuyến mãi :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Tên khuyến mãi");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Ngày bắt đầu :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblVTrLm = new JLabel("Ngày kết thúc :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setText("Sửa");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			JLabel lblMT = new JLabel("Mô tả :");
			lblMT.setForeground(new Color(191, 0, 0));
			lblMT.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JTextArea textArea = new JTextArea();
			
			JDateChooser dateChooser = new JDateChooser();
			
			JDateChooser dateChooser_1 = new JDateChooser();
			
			comboBox = new JComboBox<>(foodBUS.getIdkhuyenmaiArray());
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
					String id = comboBox.getSelectedItem().toString();
					String name = textField_3.getText();
					Date ngaybd = dateChooser.getDate();
					Date ngaykt = dateChooser_1.getDate();
					String decription = textArea.getText();
					khuyenmai = new KHUYENMAI(id, name, ngaybd, ngaykt, decription);
					foodBUS.updatekhuyenmai(khuyenmai);
					
					
					defaultTableModel1.setRowCount(0);
					loaddataKhuyenMai(foodBUS.getAllkhuyenmai());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblVTrLm)
							.addComponent(lblNewLabel_1)
							.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
										.addComponent(comboBox, Alignment.LEADING, 0, 196, Short.MAX_VALUE))
									.addGap(56)))))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_2)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblVTrLm)
							.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(63)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMT, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
							.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
						.addGap(37)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(58))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	class ThemChiTietKhuyenMai extends JFrame {
		private JComboBox<String> textField;
		private JComboBox<String> comboBox;
		MONAN_KHUYENMAI monan_KHUYENMAI;
		public ThemChiTietKhuyenMai() {
			setTitle("Thêm chi tiết khuyến mãi");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,330);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã khuyến mãi :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Mã món :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Phần trăm giảm");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			JTextField phan_tram_giam = new JTextField();
			phan_tram_giam.setColumns(10);
			
			comboBox = new JComboBox<>(foodBUS.getIdArray());
			textField = new JComboBox<>(foodBUS.getIdkhuyenmaiArray());
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String idKM = comboBox.getSelectedItem().toString();
					String idMA = textField.getSelectedItem().toString();
					int phantram = Integer.parseInt(phan_tram_giam.getText());
					monan_KHUYENMAI = new MONAN_KHUYENMAI(idMA, idKM,  phantram);
					foodBUS.addMA_KhuyenMai(monan_KHUYENMAI);
					defaultTableModel2.setRowCount(0);
					loaddataMA_KhuyenMai(foodBUS.getAllMA_KhuyenMai());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(comboBox, Alignment.LEADING, 0, 196, Short.MAX_VALUE)
									.addComponent(phan_tram_giam, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
								.addGap(56))))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(168, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(145))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(phan_tram_giam, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addGap(45)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(196))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	class SuaChiTietKhuyenMai extends JFrame {
		private JTextField textField;
		private JComboBox<String> comboBox;
		private JComboBox<String> comboBox_1;
		MONAN_KHUYENMAI monan_KHUYENMAI;
		public SuaChiTietKhuyenMai() {
			setTitle("Sửa chi tiết khuyến mãi");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,330);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã khuyến mãi :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Mã món :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Phần trăm giảm");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setText("Sửa");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			comboBox = new JComboBox<>();
			
			comboBox_1 = new JComboBox<>(foodBUS.getIdKm());
			comboBox_1.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String idKMAI = comboBox_1.getSelectedItem().toString();
			        String[] items = foodBUS.getIdfood(idKMAI);
			        comboBox.setModel(new DefaultComboBoxModel<>(items));
			    }
			});
			
			
			textField = new JTextField();
			textField.setColumns(10);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
					String idKM = comboBox.getSelectedItem().toString();
					String idMA = comboBox_1.getSelectedItem().toString();
					int phantram = Integer.parseInt(textField.getText());
					monan_KHUYENMAI = new MONAN_KHUYENMAI(idKM, idMA, phantram);
					foodBUS.updateMA_KhuyenMai(monan_KHUYENMAI);
					
					
					defaultTableModel2.setRowCount(0);
					loaddataMA_KhuyenMai(foodBUS.getAllMA_KhuyenMai());
				}
			});
			
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(comboBox_1, 0, 150, Short.MAX_VALUE)
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
									.addComponent(comboBox, 0, 196, Short.MAX_VALUE))
								.addGap(56))))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(168, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(145))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(45)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(196))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	/////////////////////////////////////////////////////////////////////////////
	class ThemChiTietNguyenLieu extends JFrame {
		private JTextField textField;
		private JComboBox<String> comboBox;
		private JComboBox<String> comboBox_1;
		CHITIET_NL_MA chitiet_NL_MA;
		public ThemChiTietNguyenLieu() {
			setTitle("Thêm chi tiết nguyên liệu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,330);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã món ăn :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Mã nguyên liệu :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Số lượng :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			comboBox_1 = new JComboBox<>(foodBUS.getIdArray());
			
			comboBox = new JComboBox(foodBUS.getIDNL());
			
			textField = new JTextField();
			textField.setColumns(10);
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String idMA = comboBox_1.getSelectedItem().toString();
					String idNL = comboBox.getSelectedItem().toString();
					int soluong = Integer.parseInt(textField.getText());
					chitiet_NL_MA = new CHITIET_NL_MA(idNL, idMA,  soluong);
					foodBUS.addCT_NL_MA(chitiet_NL_MA);
					
					defaultTableModel3.setRowCount(0);
					loaddataCT_NL_MA(foodBUS.getAllCT_NL_MA());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(comboBox_1, 0, 150, Short.MAX_VALUE)
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
									.addComponent(comboBox, 0, 196, Short.MAX_VALUE))
								.addGap(56))))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(168, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(145))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(45)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(196))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	class SuaChiTietNguyenLieu extends JFrame {
		private JTextField textField;
		private JComboBox<String> comboBox;
		private JComboBox<Integer> comboBox_1;
		CHITIET_NL_MA chitiet_NL_MA;
		public SuaChiTietNguyenLieu() {
			setTitle("Sửa chi tiết nguyên liệu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(450,330);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã món ăn :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Mã nguyên liệu :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Số lượng :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setText("Sửa");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			
			
			comboBox_1 = new JComboBox<>(foodBUS.getIdfoodnl());
			comboBox = new JComboBox<String>();
			comboBox_1.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        String idMAN = comboBox_1.getSelectedItem().toString();
			        String[] items = foodBUS.getIdnl(idMAN);
			        comboBox.setModel(new DefaultComboBoxModel<>(items));
			    }
			});
			
			textField = new JTextField();
			textField.setColumns(10);
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
					String idMA = comboBox.getSelectedItem().toString();
					String idNL = comboBox_1.getSelectedItem().toString();
					int soluong = Integer.parseInt(textField.getText());
					chitiet_NL_MA = new CHITIET_NL_MA(idMA, idNL, soluong);
					foodBUS.updateCT_NL_MA(chitiet_NL_MA);
					
					
					defaultTableModel3.setRowCount(0);
					loaddataCT_NL_MA(foodBUS.getAllCT_NL_MA());
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel_1))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(comboBox_1, 0, 150, Short.MAX_VALUE)
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(textField, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
									.addComponent(comboBox, 0, 196, Short.MAX_VALUE))
								.addGap(56))))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(168, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
						.addGap(145))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_1)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(45)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(196))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
}
