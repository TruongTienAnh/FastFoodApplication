package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import BUS.NhanVienBUS;
import DAO.DAONguyenLieu;
import DAO.DAONhanVien;
import DTO.CHITIET_DONHANG;
import DTO.DON_NHAPHANG;
import DTO.NGUYENLIEU;
import DTO.NHANVIEN;
import DTO.NHA_CU_CA;
import Design.GradientButton;
import GUI.EnployeeView.ConfigEmployee;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class StorageView extends JPanel {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField textField_1;
	private JTable table_1;
	private JTabbedPane tabbedPane;
	private JTextField textField_4;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StorageView frame = new StorageView();
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
	public StorageView() {
               System.out.println("du lieu out");
		setBounds(0, 0, 1280, 720);
		System.out.println("ksljd");
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeTabColor(tabbedPane);
			}
		});
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Kho Hàng", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
//bảng kho nguyên liệu-----------------------------------------------------------------------------------
		table = new JTable();
		table.setModel(new DefaultTableModel(
			 new Object[][] {
				
				
			},
			new String[] {
				"M\u00E3 nguy\u00EAn li\u1EC7u", "T\u00EAn nguy\u00EAn li\u1EC7u","\u0110\u01A1n v\u1ECB" , "S\u1ED1 l\u01B0\u1EE3ng"
			}
		));
		renderNL();
		
		scrollPane.setViewportView(table);
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnNewButton = new GradientButton("Tìm kiếm","#CB356B","#BD3F32");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox comboBox = new JComboBox();
		
		JLabel lblNewLabel = new JLabel("Sắp xếp :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1 = new GradientButton("Thêm","#CB356B","#BD3F32");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemNguyenLieu();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1_1 = new GradientButton("Sửa","#CB356B","#BD3F32");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaNguyenLieu();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1_2 = new GradientButton("Xóa","#CB356B","#BD3F32");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index= table.getSelectedRow();
				      xoaNL(index);
				      renderNL();
				}
		});
		btnNewButton_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(textField, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
							.addGap(132)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(10))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1075, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addGap(77))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))))
					.addGap(24)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
					.addGap(11)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
					.addGap(9))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Đơn nhập kho", null, panel_1, null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//+__)_+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ BANG DON NHAP HANG
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
			
			},
			new String[] {
				"M\u00E3 nh\u00E2n vi\u00EAn","M\u00E3 \u0111\u01A1n nh\u1EADp" , "M\u00E3 nh\u00E0 cung c\u1EA5p", "Ng\u00E0y", "Th\u00E0nh ti\u1EC1n"
			}
		));
		table_1.getColumnModel().getColumn(2).setPreferredWidth(116);
		scrollPane_1.setViewportView(table_1);
		render_DON_nhaphang();
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton_2 = new GradientButton("Tìm kiếm","#CB356B","#BD3F32");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox comboBox_1 = new JComboBox();
		
		JLabel lblNewLabel_1 = new JLabel("Sắp xếp :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1_3 = new GradientButton("Thêm","#CB356B","#BD3F32");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemDonNhap();
			}
		});
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1_1_1 = new GradientButton("Sửa","#CB356B","#BD3F32");
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaDonNhap();
			}
		});
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1_2_1 = new GradientButton("Xóa","#CB356B","#BD3F32");
		btnNewButton_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		GradientButton btnNewButton_1_2_1_2 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2_1_2.setText("Xem chi tiết");
		btnNewButton_1_2_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1_2_1_2, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1.createSequentialGroup()
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addGap(136)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)))
					.addGap(55))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_1, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))))
					.addGap(24)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_2_1_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 1209, Short.MAX_VALUE)
					.addGap(41))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 684, Short.MAX_VALUE)
					.addGap(34))
		);
		
		JPanel panel_1_1 = new JPanel();
		tabbedPane.addTab("Nhà cung cấp", null, panel_1_1, null);
		
		GradientButton btnNewButton_1_3_1 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_1_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemNhaCungCap();
			}
		});
		btnNewButton_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_1_1_1 = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SuaNhaCungCap();
			}
		});
		btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_2_1_1 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index= table_2.getSelectedRow();
				System.out.println(index);
				      xoaNCC(index);
				      renderncc();
				}
		});
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		
		GradientButton btnNewButton_2_1 = new GradientButton("Tìm kiếm", "#CB356B", "#BD3F32");
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_1_1 = new JLabel("Sắp xếp :");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox comboBox_1_1 = new JComboBox();
		
		JLabel lblNewLabel_3_1 = new JLabel("");
		
		JScrollPane scrollPane_1_1 = new JScrollPane();
		scrollPane_1_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1_1 = new GroupLayout(panel_1_1);
		gl_panel_1_1.setHorizontalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(52)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton_1_1_1_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
							.addGap(13)
							.addComponent(btnNewButton_1_2_1_1, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1_1.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_panel_1_1.createSequentialGroup()
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
								.addGap(10)
								.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addGap(136)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(comboBox_1_1, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
								.addGap(30)
								.addComponent(lblNewLabel_3_1, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
							.addComponent(scrollPane_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1097, Short.MAX_VALUE)))
					.addGap(55))
		);
		gl_panel_1_1.setVerticalGroup(
			gl_panel_1_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1_1.createSequentialGroup()
					.addGap(24)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(2)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(2)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1_1.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblNewLabel_3_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox_1_1, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))))
					.addGap(24)
					.addComponent(scrollPane_1_1, GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
					.addGap(5)
					.addGroup(gl_panel_1_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1_1.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_1_1_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_3_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1_2_1_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE))
					.addGap(11))
		);
		//BANG NHA CUNG CAP **************************************************************
		table_2 = new JTable();
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 NCC", "T\u00EAn nh\u00E0 cung c\u1EA5p", "\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i"
			}
			
		));
        renderncc();
		table_2.getTableHeader().setForeground(Color.yellow);
		table_2.getTableHeader().setBackground(Color.decode("#CB356B"));
		table_2.setFont(new Font("SansSerif",Font.PLAIN,15));
		
		scrollPane_1_1.setViewportView(table_2);
		panel_1_1.setLayout(gl_panel_1_1);
		setLayout(groupLayout);
		
		table.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table.getTableHeader().setBackground(Color.decode("#CB356B"));
		table.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		
		table_1.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table_1.getTableHeader().setBackground(Color.decode("#CB356B"));
		table_1.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		
		
	}
	private DAONguyenLieu DAONguyenLieu() {
		// TODO Auto-generated method stub
		return null;
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
//bang thêm_nguyên_liệu 
	class ThemNguyenLieu extends JFrame {
		
		private JTextField textField;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		
		public ThemNguyenLieu() {
			setTitle("thêm nguyên liệu");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,382);
			setLocationRelativeTo(null);
			
			JLabel lblNewLabel_2 = new JLabel("Mã nguyên liệu :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField = new JTextField();
			textField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Tên nguyên liệu :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("Đơn vị :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			
			JLabel lblVTrLm = new JLabel("Số lượng :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String maNL		=textField.getText();
				String TenNL	=textField_3.getText();
				String Donvi	=textField_1.getText();
				String soluong	=textField_2.getText();
        try {
			
			action_themnguyenlieu(maNL,TenNL,Donvi,Integer.parseInt(soluong));
			setVisible(false);
			renderNL();
        } catch (Exception e2) {
			System.err.println("loi");
		}
							}
			});
				
			JLabel lblNewLabel_3 = new JLabel("");
			
			
			
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
								.addGap(56))
							.addGroup(groupLayout.createSequentialGroup()
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
											.addPreferredGap(ComponentPlacement.RELATED))))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGap(76))))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
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
							.addComponent(lblVTrLm)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(32)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(41)
								.addComponent(lblNewLabel_3)))
						.addContainerGap(42, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
		
	}
	
	class SuaNguyenLieu extends JFrame {
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		
		public SuaNguyenLieu() {
			setTitle("Sửa nguyên liệu");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,382);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã nguyên liệu :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel("Tên nguyên liệu :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("Đơn vị :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JLabel lblVTrLm = new JLabel("Số lượng :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setText("Sửa");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			
			JLabel lblNewLabel_3 = new JLabel("");
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			JComboBox comboBox = new JComboBox(getidLst());
			comboBox.setSelectedIndex(-1);
			comboBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	String item = comboBox.getSelectedItem().toString();
			    
			    	ArrayList<NGUYENLIEU> listNL =new ArrayList<NGUYENLIEU>();
					DAONguyenLieu Ngliu=new DAONguyenLieu();
					listNL=Ngliu.selectAll();
					for(NGUYENLIEU lst: listNL)
					{
						if(item.equals(lst.getId_nguyen_lieu() ) )
						{textField_3.setText(lst.getId_mon_an());
						 textField_1.setText(lst.getDon_vi());
						 textField_2.setText(""+lst.getSo_luong());
						}
					}
			    }
			});
			btnNewButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	
			    	Action_suaNL(comboBox.getSelectedItem().toString(),textField_3.getText(),textField_1.getText(), Integer.parseInt(textField_2.getText()));
			    	setVisible(false);
			    	renderNL();
			    }
			    });
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
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
										.addPreferredGap(ComponentPlacement.RELATED)))))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(comboBox, Alignment.LEADING, 0, 196, Short.MAX_VALUE)
									.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
								.addGap(56))))
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
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(41)
								.addComponent(lblNewLabel_3))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(32)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(42, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
		
	}
	class ThemNhaCungCap extends JFrame {
		private JTextField textFieldx;
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		
		public ThemNhaCungCap() {
			setTitle("Thêm nhà cung cấp");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,382);
			setLocationRelativeTo(null);
			
			JLabel lblNewLabel_2 = new JLabel("Mã nhà cung cấp:");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			
			
			JLabel lblNewLabel = new JLabel(" Tên NCC :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textFieldx = new JTextField();
			textFieldx.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel("số điện thoại :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JLabel lblVTrLm = new JLabel("địa chỉ :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			
			
			JLabel lblNewLabel_3 = new JLabel("");
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed (ActionEvent e) {
					String IDncc		=textFieldx.getText();
					String TenNCC	   	=textField_3.getText();
					String diachi_ncc	=textField_2.getText();
					String sdt_Ncc		=textField_1.getText();
	        try {
				
	        	action_themncc(IDncc,TenNCC, diachi_ncc,sdt_Ncc);
				
				setVisible(false);
				renderncc();
	        } catch (Exception e2) {
				System.err.println("loi");
			}
								}
				});
			//JTextField comboBox = new JTextField();
			JLabel lblNewLabex_x = new JLabel("");
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
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
										.addPreferredGap(ComponentPlacement.RELATED)))))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(textFieldx, Alignment.LEADING, 0, 196, Short.MAX_VALUE)
									.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
								.addGap(56))))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(textFieldx, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
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
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(41)
								.addComponent(lblNewLabel_3))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(32)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(42, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
		
	}
	class SuaNhaCungCap extends JFrame {
		private JTextField textField_1;
		private JTextField textField_2;
		private JTextField textField_3;
		
		public SuaNhaCungCap() {
			setTitle("Sửa nhà cung cấp");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,382);
			setLocationRelativeTo(null);
			
			JLabel lblNewLabel_2 = new JLabel("Mã nhà cung cấp:");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel = new JLabel(" Tên NCC :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("số điện thoại :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			
			JLabel lblVTrLm = new JLabel("địa chỉ :");
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
			
			JComboBox comboBox = new JComboBox(getidncclst());
			comboBox.setSelectedIndex(-1);
			comboBox.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	String item = comboBox.getSelectedItem().toString();
			    
			    	ArrayList<NHA_CU_CA> listNCC =new ArrayList<NHA_CU_CA>();
					DAONguyenLieu newNCC=new DAONguyenLieu();
					listNCC=newNCC.Select_All_NCC();
					for(NHA_CU_CA lst: listNCC)
					{
						if(item.equals(lst.GetIDNcc() ) )
						{textField_3.setText(lst.GetTenNcc());
						 textField_2.setText(lst.GetDiaChicc());
						 textField_1.setText(lst.GetSDTNcc());
						}
					}
			    }
			});
			btnNewButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	
			    	Action_suaNCC(comboBox.getSelectedItem().toString(),textField_3.getText(),textField_2.getText(),textField_1.getText());
			    	setVisible(false);
			    	renderncc();
			    }
			    });
			
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
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
										.addPreferredGap(ComponentPlacement.RELATED)))))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(textField_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
									.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE))
								.addGap(56))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(comboBox, Alignment.LEADING, 0, 196, Short.MAX_VALUE)
									.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
								.addGap(56))))
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
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(41)
								.addComponent(lblNewLabel_3))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(32)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addContainerGap(42, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
		
	}
	class ThemDonNhap extends JFrame{
		private JTable table;
		ArrayList<CHITIET_DONHANG> Chi_TIET_NHAP_HANG =new ArrayList<CHITIET_DONHANG>();
		public ThemDonNhap() {
			setTitle("Thêm đơn hàng");
			setSize(935,788);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			JLabel lblNewLabel = new JLabel("Mã nhân viên :");
			lblNewLabel.setForeground(Color.decode("#BD3F32"));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblMnNp = new JLabel("Mã đơn nhập :");
			lblMnNp.setForeground(new Color(189, 63, 50));
			lblMnNp.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			//JLabel lblNewLabel_1 = new JLabel("New label");
			//lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			JComboBox lblNewLabel_1 = new JComboBox(getNVIDlst());
			lblNewLabel_1.setSelectedIndex(-1);
			
			//lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNgyNhp = new JLabel("Ngày nhập :");
			lblNgyNhp.setForeground(new Color(189, 63, 50));
			lblNgyNhp.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JDateChooser dateChooser = new JDateChooser();
			
			JLabel lblNhCungCp = new JLabel("Nhà cung cấp :");
			lblNhCungCp.setForeground(new Color(189, 63, 50));
			lblNhCungCp.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JComboBox comboBox = new JComboBox(getidncclst());
			comboBox.setSelectedIndex(-1);
			
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JTextField panel = new JTextField();
			
			JLabel lblMNguynLiu = new JLabel("Mã nguyên liệu :");
			lblMNguynLiu.setForeground(new Color(189, 63, 50));
			lblMNguynLiu.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblnGi = new JLabel("Giá bán :");
			lblnGi.setForeground(new Color(189, 63, 50));
			lblnGi.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JComboBox comboBox_1 = new JComboBox(getidLst());
			comboBox_1.setSelectedIndex(-1);
			
			JTextField panel_1 = new JTextField();
			
			JLabel lblSLng = new JLabel("Số lượng :");
			lblSLng.setForeground(new Color(189, 63, 50));
			lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JTextField panel_1_1 = new JTextField();
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			JLabel lblTngCng = new JLabel("Tổng cộng :");
			lblTngCng.setForeground(new Color(189, 63, 50));
			lblTngCng.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1_1 = new JLabel("");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Nhập hàng","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton grdntbtnThm = new GradientButton("Nhập hàng", "#CB356B", "#BD3F32");
			grdntbtnThm.setText("Thêm");
			grdntbtnThm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton grdntbtnXa = new GradientButton("Nhập hàng", "#CB356B", "#BD3F32");
			grdntbtnXa.setText("Xóa");
			grdntbtnXa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(48)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblSLng, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(grdntbtnThm, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblnGi, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
										.addGap(397))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblMNguynLiu, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox_1, 0, 174, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblMnNp, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(panel, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
												.addGap(76)))
										.addGap(62)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNgyNhp, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(comboBox, 0, 0, Short.MAX_VALUE)))
										.addGap(64)))
								.addGap(146))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(grdntbtnXa, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
										.addGap(346)
										.addComponent(lblTngCng, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
								.addGap(35))))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(412, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(381))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addComponent(panel, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblMnNp, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNgyNhp, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))))
						.addGap(42)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMNguynLiu, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblnGi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblSLng, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(grdntbtnThm, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(grdntbtnXa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTngCng, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(35))
			);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"M\u00E3 nguy\u00EAn li\u1EC7u", "\u0111\u01A1n gi\u00E1", "s\u1ED1 l\u01B0\u1EE3ng", "th\u00E0nh ti\u1EC1n"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(101);
			table.getTableHeader().setForeground(Color.yellow);
			table.getTableHeader().setBackground(Color.decode("#CB356B"));
			table.setFont(new Font("SansSerif",Font.PLAIN,15));
			
			
			scrollPane.setViewportView(table);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
			grdntbtnThm.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	String ITEMIDNL=comboBox_1.getSelectedItem().toString();
			    	String ITEMMA_donNHAP=panel.getText();
			    	int ITEMGIA= Integer.parseInt(panel_1.getText());
			    	int ITEMSOLUONG=Integer.parseInt(panel_1_1.getText());
			    	CHITIET_DONHANG CHITIETDONNHAPHANG =new CHITIET_DONHANG(ITEMIDNL, ITEMMA_donNHAP, ITEMGIA, ITEMSOLUONG);
			        Chi_TIET_NHAP_HANG.add(CHITIETDONNHAPHANG);
			    	RENDER_CHI_TIET_NHAP_HANG();
			    	lblNewLabel_1_1.setText(toncongdonnhap());
			    }
			});
			grdntbtnXa.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	int index= table.getSelectedRow();
			    	 xoa_NL_CHITIETDONHANG(index);
			    	 RENDER_CHI_TIET_NHAP_HANG();
			    	 lblNewLabel_1_1.setText(toncongdonnhap());
			    }
			});
			btnNewButton.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	String itemIDdonnhap	=panel.getText();
			    	String itemIDnhanvien	=lblNewLabel_1.getSelectedItem().toString();
			    	String itemIDnhacungcap	=comboBox.getSelectedItem().toString();
			    	int    itemTIEN   		=Integer.parseInt(toncongdonnhap());
			    	String ngaynhap			=dateChooser.getDate().toString();
			    	DON_NHAPHANG DON = new DON_NHAPHANG(itemIDdonnhap, itemIDnhanvien, itemIDnhacungcap,itemTIEN   , ngaynhap, "0");
			    	DAONguyenLieu DHNa = new DAONguyenLieu();
			    	System.out.println(itemIDdonnhap+itemIDnhanvien+itemIDnhacungcap+itemTIEN+ngaynhap);
			    	    DHNa.insertDNH(DON);
			    	    render_DON_nhaphang();
			    	    for (int i=0;i<500;i++);
			    	for(CHITIET_DONHANG lst: Chi_TIET_NHAP_HANG)
			    		DHNa.insertDNH_chitiet(lst);
			    		//System.out.println(lst.getDon_gia()+lst.getId_don_hang()+lst.getId_mon_an()+lst.getSo_luong());
			           setVisible(false);
			    }	
			    });
		}
		public String toncongdonnhap() {
			int TONG=0;
			try {
				for (CHITIET_DONHANG lst :Chi_TIET_NHAP_HANG)
				{
					TONG=TONG+(int) (lst.getSo_luong()*lst.getDon_gia());
				}
				
				return String.valueOf(TONG);
			} catch (Exception e) {
				// TODO: handle exception
			}
			return"0";
		}
		public void RENDER_CHI_TIET_NHAP_HANG() {
			
			try {DefaultTableModel	model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			for(CHITIET_DONHANG lst: Chi_TIET_NHAP_HANG)
				{Object[] data = {lst.getId_don_hang(),""+lst.getSo_luong(),""+lst.getDon_gia(),""+lst.getSo_luong()*lst.getDon_gia()};

				model.addRow(data);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}	
		public void xoa_NL_CHITIETDONHANG(int index) {
			try { 
				
				Chi_TIET_NHAP_HANG.remove(index);
			} catch (Exception e) {
				
			}
			
		}
		
	}
	class SuaDonNhap extends JFrame{
		private JTable table;
		public SuaDonNhap() {
			setTitle("Sửa đơn");
			setSize(935,788);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			
			JLabel lblNewLabel = new JLabel("Mã nhân viên :");
			lblNewLabel.setForeground(Color.decode("#BD3F32"));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblMnNp = new JLabel("Mã đơn nhập :");
			lblMnNp.setForeground(new Color(189, 63, 50));
			lblMnNp.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1 = new JLabel("New label");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNgyNhp = new JLabel("Ngày nhập :");
			lblNgyNhp.setForeground(new Color(189, 63, 50));
			lblNgyNhp.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JDateChooser dateChooser = new JDateChooser();
			
			JLabel lblNhCungCp = new JLabel("Nhà cung cấp :");
			lblNhCungCp.setForeground(new Color(189, 63, 50));
			lblNhCungCp.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JComboBox comboBox = new JComboBox();
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblMNguynLiu = new JLabel("Mã nguyên liệu :");
			lblMNguynLiu.setForeground(new Color(189, 63, 50));
			lblMNguynLiu.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblnGi = new JLabel("Giá bán :");
			lblnGi.setForeground(new Color(189, 63, 50));
			lblnGi.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JComboBox comboBox_1 = new JComboBox();
			
			JPanel panel_1 = new JPanel();
			
			JLabel lblSLng = new JLabel("Số lượng :");
			lblSLng.setForeground(new Color(189, 63, 50));
			lblSLng.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JPanel panel_1_1 = new JPanel();
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
			
			JLabel lblTngCng = new JLabel("Tổng cộng :");
			lblTngCng.setForeground(new Color(189, 63, 50));
			lblTngCng.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_1_1 = new JLabel("New label");
			lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton btnNewButton = new GradientButton("Nhập hàng","#CB356B","#BD3F32");
			btnNewButton.setText("Sửa đơn");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton grdntbtnThm = new GradientButton("Nhập hàng", "#CB356B", "#BD3F32");
			grdntbtnThm.setText("Thêm");
			grdntbtnThm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			GradientButton grdntbtnXa = new GradientButton("Nhập hàng", "#CB356B", "#BD3F32");
			grdntbtnXa.setText("Xóa");
			grdntbtnXa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			JLabel lblNewLabel_2 = new JLabel("New label");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(48)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblSLng, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(panel_1_1, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
								.addGap(45)
								.addComponent(grdntbtnThm, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblnGi, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
										.addGap(397))
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblMNguynLiu, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(comboBox_1, 0, 174, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblMnNp, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addGap(18)
												.addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
												.addGap(76)))
										.addGap(62)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNgyNhp, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(comboBox, 0, 0, Short.MAX_VALUE)))
										.addGap(64)))
								.addGap(146))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(grdntbtnXa, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
										.addGap(346)
										.addComponent(lblTngCng, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)))
								.addGap(35))))
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(412, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(381))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(26)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblMnNp, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNgyNhp, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
									.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNhCungCp, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))))
						.addGap(42)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblMNguynLiu, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblnGi, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblSLng, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(grdntbtnThm, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
								.addComponent(panel_1_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblNewLabel_1_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(grdntbtnXa, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTngCng, GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(35))
			);
			
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"M\u00E3 nguy\u00EAn li\u1EC7u", "\u0111\u01A1n gi\u00E1", "s\u1ED1 l\u01B0\u1EE3ng", "th\u00E0nh ti\u1EC1n"
				}
			));
			table.getColumnModel().getColumn(0).setPreferredWidth(101);
			table.getTableHeader().setForeground(Color.yellow);
			table.getTableHeader().setBackground(Color.decode("#CB356B"));
			table.setFont(new Font("SansSerif",Font.PLAIN,15));
			
			
			scrollPane.setViewportView(table);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	//##################################################################################################################
	public void renderncc()
	{
		ArrayList<NHA_CU_CA> listNCC =new ArrayList<NHA_CU_CA>();
		DAONguyenLieu NCC=new DAONguyenLieu();
		listNCC=NCC.Select_All_NCC();
		
		
		DefaultTableModel	model = (DefaultTableModel) table_2.getModel();
		model.setRowCount(0);
		for(NHA_CU_CA lst: listNCC)
			{Object[] data = {lst.GetIDNcc(),lst.GetTenNcc(),lst.GetDiaChicc(),lst.GetSDTNcc()};
			
			model.addRow(data);
			}
	}
	public void action_themncc(String IDncc,String tenNCC,String DiachiNCC,String SDTNCC)
	{   NHA_CU_CA newNCC = new NHA_CU_CA(IDncc, tenNCC, DiachiNCC, SDTNCC,"0");
		ArrayList<NHA_CU_CA> listNCC =new ArrayList<NHA_CU_CA>();
		DAONguyenLieu NCC=new DAONguyenLieu();
		listNCC=NCC.Select_All_NCC();
		boolean check=true;
		for(NHA_CU_CA lst: listNCC)
		{ if (lst.GetIDNcc().equalsIgnoreCase(newNCC.GetIDNcc()+"    "))
			{
			System.out.println("da co");
			check= false;
			}
		}
		if (check)
		{
			NCC.insertNCC(newNCC);
		}
	}
	 public String [] getidncclst()
	 {
		 try {
			 ArrayList<NHA_CU_CA> listNCC =new ArrayList<NHA_CU_CA>();
				DAONguyenLieu NCC=new DAONguyenLieu();
				listNCC=NCC.Select_All_NCC();
				String idlst[]= new String [listNCC.size()];
				for (int i = 0; i < listNCC.size(); i++) {
			        idlst[i] = listNCC.get(i).GetIDNcc();
			    }
			    return idlst;
		
		} catch (Exception e) {
			
		}
		 return null;
	 }
	 public void Action_suaNCC(String item,String tenNCC,String Diachi,String sdt)
		{         System.out.println(item+tenNCC+Diachi+sdt);
			try {
				NHA_CU_CA newNCC = new NHA_CU_CA(item, tenNCC, Diachi, sdt,"0");
				 
					DAONguyenLieu NCC=new DAONguyenLieu();
					NCC.updateNCC(newNCC);
				
					
			} catch (Exception e) {
		
			}
		}
	 public void xoaNCC(int index) {
			try { 
				ArrayList<NHA_CU_CA> listNCC =new ArrayList<NHA_CU_CA>();
				DAONguyenLieu NCCxoa=new DAONguyenLieu();
				listNCC=NCCxoa.Select_All_NCC();
				String idlst[]= new String [listNCC.size()];
				for (int i = 0; i <listNCC.size(); i++) {
			        idlst[i] = listNCC.get(i).GetIDNcc();
			    }
				NHA_CU_CA NCCx = new NHA_CU_CA(idlst[index], "","" ,"", "0");
				System.out.println(idlst[index]);
			     	NCCxoa.deleteNCC(NCCx);
			} catch (Exception e) {
				
			}
			
		}
	//==========================================================Nguyen_LIEU==================================================
	public void renderNL()
	{
		ArrayList<NGUYENLIEU> listNL =new ArrayList<NGUYENLIEU>();
		DAONguyenLieu Ngliu=new DAONguyenLieu();
		listNL=Ngliu.selectAll();
		DefaultTableModel	model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for(NGUYENLIEU lst: listNL)
			{Object[] data = {lst.getId_nguyen_lieu(),lst.getId_mon_an(),lst.getDon_vi(),lst.getSo_luong()};
			
			model.addRow(data);
			}

	}
	public void action_themnguyenlieu(String MaNL,String TenNL,String DV,int SL)
	{   
	try {
		 NGUYENLIEU nguyenlieu = new NGUYENLIEU(MaNL, TenNL, DV, SL);
		 ArrayList<NGUYENLIEU> listNL =new ArrayList<NGUYENLIEU>();
			DAONguyenLieu Ngliu=new DAONguyenLieu();
			listNL=Ngliu.selectAll();
			boolean check =true;
			for(NGUYENLIEU lst: listNL)
			{
				if (lst.getId_nguyen_lieu().equalsIgnoreCase(MaNL+"     "))
				{  System.out.println(lst.getSo_luong());
					nguyenlieu.setSo_luong(nguyenlieu.getSo_luong()+lst.getSo_luong());
				    Ngliu.update(nguyenlieu);
				    
				    check=false;
				}
					
			}
			
			if (check)
			{
				Ngliu.insert(nguyenlieu);
				
			}

	} catch (Exception e) {
		
		System.err.println("loi insert nguyen lieu");
	}
		
		
	}
	public String [] getidLst ()
	{
		try {
			 ArrayList<NGUYENLIEU> listNL =new ArrayList<NGUYENLIEU>();
				DAONguyenLieu Ngliu=new DAONguyenLieu();
				listNL=Ngliu.selectAll();
				String idlst[]= new String [listNL.size()];
				for (int i = 0; i < listNL.size(); i++) {
			        idlst[i] = listNL.get(i).getId_nguyen_lieu();
			    }
			    return idlst;
		} catch (Exception e2) {
			System.err.println("loi fix..NL");
		}
		return null;
	}
	public void Action_suaNL(String item,String tenNL,String DV,int soluong)
	{
		try {
			NGUYENLIEU nguyenlieu = new NGUYENLIEU(item, tenNL, DV, soluong);
			 ArrayList<NGUYENLIEU> listNL =new ArrayList<NGUYENLIEU>();
				DAONguyenLieu Ngliu=new DAONguyenLieu();
				listNL=Ngliu.selectAll();
			
				for(NGUYENLIEU lst: listNL)
				{
					if (lst.getId_nguyen_lieu().equalsIgnoreCase(item))
					{  		
					    Ngliu.update(nguyenlieu);
					
					}
						
				}
		} catch (Exception e) {
	
		}
	}
	public void xoaNL(int index) {
		try { 
			ArrayList<NGUYENLIEU> listNL =new ArrayList<NGUYENLIEU>();
			DAONguyenLieu Ngliu=new DAONguyenLieu();
			listNL=Ngliu.selectAll();
			String idlst[]= new String [listNL.size()];
			for (int i = 0; i <listNL.size(); i++) {
		        idlst[i] = listNL.get(i).getId_nguyen_lieu();
		    }
			NGUYENLIEU nguyenlieu = new NGUYENLIEU(idlst[index], "", "", 0);
			System.out.println(idlst[index]);
		     	Ngliu.delete(nguyenlieu);
		} catch (Exception e) {
			
		}
		
	}
	//==========================================================DON_Nhaphang==================================================
	public void render_DON_nhaphang()
	{
		ArrayList<DON_NHAPHANG> listDNH =new ArrayList<DON_NHAPHANG>();
		DAONguyenLieu DNH=new DAONguyenLieu();
		listDNH=DNH.selecall_DON_NHAP_HANG();
		DefaultTableModel	model = (DefaultTableModel) table_1.getModel();
		model.setRowCount(0);
		for(DON_NHAPHANG lst: listDNH)
			{Object[] data = {lst.get_ID_DON_NHAP_HANG(),lst.get_IDNhanvien(),lst.get_IDNHA_CUNG_CAP(),lst.get_NGAYNHAP(),lst.get_Thanhtien()};
			
			model.addRow(data);
			}
	}
	public String[] getNVIDlst() {
	    DAONhanVien dao = new DAONhanVien();
	    ArrayList<NHANVIEN> dsNhanVien = dao.getNhanVien();
	    String[] idList = new String[dsNhanVien.size()];
	    for (int i = 0; i < dsNhanVien.size(); i++) {
	        idList[i] = dsNhanVien.get(i).getId();
	    }
	    return idList;
	}
	

//◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘

}
