package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DAO.DAONhanVien;
import DAO.DAOTaiKhoan;
import DTO.NHANVIEN;
import Design.GradientButton;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;

	public class EnployeeView extends JPanel {

	public JPanel contentPane;
	public JTable employeeTable;
	public JTextField timKiemField;
	public JTabbedPane tabbedPane;
	public JTextField timKiemTaiKhoanField;
	public JTable taiKhoanTable;
	public DefaultTableModel taiKhoanModel;
	public DefaultTableModel employeeModel;
	private JComboBox comboBox_1;
	public EnployeeView() {
		setBounds(0, 0, 1280, 720);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeTabColor(tabbedPane);
			}
		});
		tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Nhân viên", null, panel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		employeeModel = new DefaultTableModel();
		employeeModel.addColumn("Mã nhân viên");
		employeeModel.addColumn("Tên");
		employeeModel.addColumn("Chức vụ");
		employeeModel.addColumn("Số điện thoại");
		employeeModel.addColumn("Vị trí làm việc");
		employeeTable = new JTable(employeeModel);
		scrollPane.setViewportView(employeeTable);
		new NhanVienBUS(EnployeeView.this).renderNhanVienTable();
		
		employeeTable.getTableHeader().setForeground(Color.decode("#FFEE58"));
		employeeTable.getTableHeader().setBackground(Color.decode("#CB356B"));
		employeeTable.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		
		timKiemField = new JTextField();
		timKiemField.setColumns(10);
		
		JButton timKiemButton = new GradientButton("Tìm kiếm","#CB356B","#BD3F32");
		timKiemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NhanVienBUS(EnployeeView.this).timKiem();
			}
		});
		timKiemButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timKiemButton.setPreferredSize(new Dimension(100,50));
		
		
		JComboBox xapXepComboBox = new JComboBox();
		xapXepComboBox.setFont(new Font("Tahoma", Font.PLAIN, 13));
		xapXepComboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "tên(A-Z)", "tên(Z-A)"}));
		xapXepComboBox.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // Code xử lý sự kiện khi người dùng chọn một phần tử khác trong danh sách
		        // Ví dụ:
		        String selectedItem = (String) xapXepComboBox.getSelectedItem();
		        System.out.println(selectedItem);
		        new NhanVienBUS(EnployeeView.this).sortBy(selectedItem);
		    }
		});
		JLabel lblNewLabel = new JLabel("Sắp xếp :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JButton btnNewButton_1 = new GradientButton("Thêm","#CB356B","#BD3F32");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddEmployee();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		JButton btnNewButton_1_1 = new GradientButton("Sửa","#CB356B","#BD3F32");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ConfigEmployee();
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		
		JButton xoaButton = new GradientButton("Xóa","#CB356B","#BD3F32");
		xoaButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		xoaButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = employeeTable.getSelectedRow();
				String id = (String) employeeModel.getValueAt(index, 0);
				new NhanVienBUS(EnployeeView.this).xoa(id);
				new NhanVienBUS(EnployeeView.this).renderNhanVienTable();	
				new TaiKhoanBUS(EnployeeView.this).renderTaiKhoanTable();
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		JLabel lblNewLabel_2_2 = new JLabel("");
		
		JLabel lblNewLabel_3 = new JLabel("");
		
		JLabel lblNewLabel_4 = new JLabel("");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(xoaButton, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(timKiemField, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
							.addGap(27)
							.addComponent(timKiemButton, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(xapXepComboBox, 0, 205, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1122, Short.MAX_VALUE))
					.addGap(27))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(xapXepComboBox, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_panel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(timKiemField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(timKiemButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNewLabel_4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2_2, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(xoaButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addGap(34))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 1172, Short.MAX_VALUE)
					.addGap(36)
					.addComponent(lblNewLabel_1)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(tabbedPane)
							.addGap(23))))
		);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Tài khoản", null, panel_1, null);
		
		GradientButton btnNewButton_1_3 = new GradientButton("Thêm", "#CB356B", "#BD3F32");
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ThemTaiKhoan();
			}
		});
		btnNewButton_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		GradientButton btnNewButton_1_1_1 = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnNewButton_1_1_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new SuaTaiKhoan();
			}
		});
		GradientButton xoaBtn = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		xoaBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		xoaBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int index = taiKhoanTable.getSelectedRow();
				String id = (String) taiKhoanModel.getValueAt(index, 1);
				new TaiKhoanBUS(EnployeeView.this).xoa(id);
				new TaiKhoanBUS(EnployeeView.this).renderTaiKhoanTable();
			}
		});
		JLabel lblNewLabel_2_2_1 = new JLabel("");
		
		JLabel lblNewLabel_4_1 = new JLabel("");
		
		timKiemTaiKhoanField = new JTextField();
		timKiemTaiKhoanField.setColumns(10);
		
		GradientButton timkiemBtn = new GradientButton("Tìm kiếm", "#CB356B", "#BD3F32");
		timkiemBtn.setPreferredSize(new Dimension(100, 50));
		timkiemBtn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timkiemBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String ev = e.getActionCommand();
				System.out.println(ev);
				new TaiKhoanBUS(EnployeeView.this).timKiem();
			}
		});
		JLabel lblNewLabel_3_1 = new JLabel("");
		
		JLabel lblNewLabel_5 = new JLabel("Sắp xếp :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		comboBox_1 = new JComboBox();
		comboBox_1.addItem("Trạng thái");
		comboBox_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) comboBox_1.getSelectedItem();
		        System.out.println(selectedItem);
		        new TaiKhoanBUS(EnployeeView.this).sapxep(selectedItem);}
		});
		JLabel lblNewLabel_2_1 = new JLabel("");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(50)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(xoaBtn, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel_2_2_1, GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_4_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(timKiemTaiKhoanField, GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
							.addGap(27)
							.addComponent(timkiemBtn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
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
								.addComponent(timKiemTaiKhoanField, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(timkiemBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel_1.createSequentialGroup()
								.addGap(1)
								.addComponent(lblNewLabel_4_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addGap(18)
					.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_2_2_1, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
							.addComponent(btnNewButton_1_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1_3, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(xoaBtn, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addGap(34))
		);
		taiKhoanModel = new DefaultTableModel();
		taiKhoanModel.addColumn("Mã tài khoản");
		taiKhoanModel.addColumn("Mã nhân viên");
		taiKhoanModel.addColumn("Tên tài khoản");
		taiKhoanModel.addColumn("Mật khẩu");
		taiKhoanModel.addColumn("Tình trạng");
		taiKhoanTable = new JTable(taiKhoanModel);
		scrollPane_1.setViewportView(taiKhoanTable);
		new TaiKhoanBUS(EnployeeView.this).renderTaiKhoanTable();
		
		JTableHeader hd = taiKhoanTable.getTableHeader();
		hd.setForeground(Color.decode("#FFEE58"));
		hd.setBackground(Color.decode("#CB356B"));
		hd.setFont(new Font("SansSerif",Font.PLAIN,15));
		
		panel_1.setLayout(gl_panel_1);
		setLayout(groupLayout);
		
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
	public class AddEmployee extends JFrame {
		public JTextField hoTenField;
		public JComboBox<String> chucVuField;
		public JTextField viTriLamViecField;
		public JTextField soDienThoaiField;
		
		public AddEmployee() {
			setTitle("Thêm nhân viên");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,380);
			setLocationRelativeTo(null);
			
			JLabel lblNewLabel_2 = new JLabel("Số điện thoại :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			soDienThoaiField = new JTextField("0797816348");
			soDienThoaiField.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Họ tên :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			hoTenField = new JTextField("Trần Đức An");
			hoTenField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel(" Chức vụ :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			chucVuField = new JComboBox<String>();
			chucVuField.addItem("ADMIN");
			chucVuField.addItem("Ban Hang");
			chucVuField.addItem("Quan ly kho");
			chucVuField.addItem("Bao ve");
			
			JLabel lblVTrLm = new JLabel("Vị trí làm việc :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			viTriLamViecField = new JTextField("Cửa hàng");
			viTriLamViecField.setColumns(10);
			
			GradientButton themButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			themButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			themButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					new	NhanVienBUS(AddEmployee.this).them(soDienThoaiField.getText(), hoTenField.getText(), (String)chucVuField.getSelectedItem(), viTriLamViecField.getText());
					new NhanVienBUS(EnployeeView.this).renderNhanVienTable();
					new TaiKhoanBUS(EnployeeView.this).renderTaiKhoanTable();
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
								.addComponent(lblNewLabel_2)
								.addGap(6)
								.addComponent(soDienThoaiField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(hoTenField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addGap(41)
								.addComponent(chucVuField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblVTrLm)
								.addGap(5)
								.addComponent(viTriLamViecField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
						.addGap(118))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(154)
						.addComponent(themButton, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(185, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_2)
							.addComponent(soDienThoaiField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(hoTenField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(chucVuField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblVTrLm)
							.addComponent(viTriLamViecField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_3)
							.addComponent(themButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(38, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	public class ConfigEmployee extends JFrame {
		public JTextField hoTenField;
		public JComboBox<String> chucVuField;
		public JTextField viTriLamViecField;
		
		public ConfigEmployee() {
			setTitle("Sửa thông tin nhân viên");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,380);
			setLocationRelativeTo(null);
			JLabel lblNewLabel_2 = new JLabel("Mã nhân viên :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			
			JLabel lblNewLabel = new JLabel("Họ tên :");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			hoTenField = new JTextField("A");
			hoTenField.setColumns(10);
			
			JLabel lblNewLabel_1 = new JLabel(" Chức vụ :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			String[] chucVuList = {"ADMIN", "Ban Hang", "Quan Ly Kho", "Bao ve"};
			chucVuField = new JComboBox<>(chucVuList);
			
			JLabel lblVTrLm = new JLabel("Vị trí làm việc :");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			viTriLamViecField = new JTextField("C");
			viTriLamViecField.setColumns(10);
			JComboBox comboboxID = new JComboBox(new NhanVienBUS(this).getIdList());
			comboboxID.setSelectedIndex(-1);
			comboboxID.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			        // Xử lý sự kiện tại đây
			        // Lấy giá trị item được chọn
			    	String item = comboboxID.getSelectedItem().toString();
			    	new NhanVienBUS(ConfigEmployee.this).upLoadByID(item);
			    }
			});
			GradientButton capNhatButton = new GradientButton("Cập nhật","#CB356B","#BD3F32");
			capNhatButton.setText("Cập nhật");
			capNhatButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			capNhatButton.addActionListener( new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String id = comboboxID.getSelectedItem().toString();
					new NhanVienBUS(ConfigEmployee.this).capNhat(id);
					new NhanVienBUS(EnployeeView.this).renderNhanVienTable();
				}
			});
			JLabel lblNewLabel_3 = new JLabel();
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_2)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(comboboxID, 0, 158, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(hoTenField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblNewLabel_1)
								.addGap(41)
								.addComponent(chucVuField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE))
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblVTrLm)
								.addGap(5)
								.addComponent(viTriLamViecField, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
						.addGap(118))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(154)
						.addComponent(capNhatButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(134, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(comboboxID, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel)
							.addComponent(hoTenField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(chucVuField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblVTrLm)
							.addComponent(viTriLamViecField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_3)
							.addComponent(capNhatButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(40, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	public class ThemTaiKhoan extends JFrame {
		public JTextField maTaiKhoanField;
		public JTextField tenTaiKhoanField;
		public JTextField matKhauField;
		public JComboBox<String> comboBox;
		
		public ThemTaiKhoan() {
			setTitle("Thêm tài khoản");
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setSize(450,380);
			setLocationRelativeTo(null);
			
			JLabel lblNewLabel_2 = new JLabel("Mã tài khoản :");
			lblNewLabel_2.setForeground(new Color(191, 0, 0));
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			
			JLabel lblNewLabel = new JLabel("Mã nhân viên");
			lblNewLabel.setForeground(new Color(191, 0, 0));
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			maTaiKhoanField = new JTextField();
			maTaiKhoanField.setColumns(10);
			maTaiKhoanField.setEnabled(false);
			
			JLabel lblNewLabel_1 = new JLabel("Tên tài khoản :");
			lblNewLabel_1.setForeground(new Color(191, 0, 0));
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			tenTaiKhoanField = new JTextField();
			tenTaiKhoanField.setColumns(10);
			tenTaiKhoanField.setEnabled(false);
			JLabel lblVTrLm = new JLabel("mật khẩu");
			lblVTrLm.setForeground(new Color(191, 0, 0));
			lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
			
			matKhauField = new JTextField();
			matKhauField.setColumns(10);
			matKhauField.setEnabled(false);
			GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
			btnNewButton.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					new TaiKhoanBUS(ThemTaiKhoan.this).them();
					new TaiKhoanBUS(EnployeeView.this).renderTaiKhoanTable();
				}
			});
			
			JLabel lblNewLabel_3 = new JLabel("");
			
			comboBox = new JComboBox<>(new TaiKhoanBUS(ThemTaiKhoan.this).getIdNhanVien());
			comboBox.setSelectedIndex(-1);
			comboBox.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					tenTaiKhoanField.setEnabled(true);
					matKhauField.setEnabled(true);
					String item = comboBox.getSelectedItem().toString();
					System.out.println(item);
					new TaiKhoanBUS(ThemTaiKhoan.this).upLoadById(item);
					
				}
			});
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblVTrLm)
									.addComponent(lblNewLabel_1))
								.addGap(3)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(tenTaiKhoanField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
									.addComponent(matKhauField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblNewLabel_2))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(maTaiKhoanField, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
									.addComponent(comboBox, 0, 174, Short.MAX_VALUE))))
						.addGap(107))
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(154)
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(134, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(34)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_2)
							.addComponent(maTaiKhoanField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_1)
							.addComponent(tenTaiKhoanField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblVTrLm)
							.addComponent(matKhauField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addGap(35)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblNewLabel_3)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(34, Short.MAX_VALUE))
			);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
		}
	}
	public class SuaTaiKhoan extends JFrame {
			public JTextField maTaiKhoanField;
			public JTextField tenTaiKhoanField;
			public JTextField matKhauField;
			public JComboBox<String> tinhTrangBox;
			public JComboBox<String> comboBox;
			
			public SuaTaiKhoan() {
				setTitle("Sửa tài khoản");
				setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				setSize(450,445);
				setLocationRelativeTo(null);
				
				JLabel lblNewLabel_2 = new JLabel("Mã tài khoản :");
				lblNewLabel_2.setForeground(new Color(191, 0, 0));
				lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				JLabel lblNewLabel = new JLabel("Mã nhân viên");
				lblNewLabel.setForeground(new Color(191, 0, 0));
				lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				maTaiKhoanField = new JTextField("Mã nhân tài khoản");
				maTaiKhoanField.setColumns(10);
				maTaiKhoanField.setEnabled(false);
				
				JLabel lblNewLabel_1 = new JLabel("Tên tài khoản :");
				lblNewLabel_1.setForeground(new Color(191, 0, 0));
				lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				tenTaiKhoanField = new JTextField("tên tài khoản");
				tenTaiKhoanField.setColumns(10);
				
				JLabel lblVTrLm = new JLabel("mật khẩu");
				lblVTrLm.setForeground(new Color(191, 0, 0));
				lblVTrLm.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				matKhauField = new JTextField("mật khẩu");
				matKhauField.setColumns(10);
				
				GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
				btnNewButton.setText("sửa");
				btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
				btnNewButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						new TaiKhoanBUS(SuaTaiKhoan.this).capNhat((String)comboBox.getSelectedItem());
						new TaiKhoanBUS(EnployeeView.this).renderTaiKhoanTable();
					}
				});
				JLabel lblNewLabel_3 = new JLabel("");
				
				comboBox = new JComboBox<>(new TaiKhoanBUS(SuaTaiKhoan.this).getIdNhanVienToUpdate());
				comboBox.setSelectedIndex(-1);
				comboBox.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						tenTaiKhoanField.setEnabled(true);
						matKhauField.setEnabled(true);
						String item = comboBox.getSelectedItem().toString();
						System.out.println(item);
						new TaiKhoanBUS(SuaTaiKhoan.this).upLoadByID(item);
						
					}
				});
				
				JLabel lblTnhTrng = new JLabel("Tình trạng :");
				lblTnhTrng.setForeground(new Color(191, 0, 0));
				lblTnhTrng.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				tinhTrangBox = new JComboBox();
				tinhTrangBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
				tinhTrangBox.setModel(new DefaultComboBoxModel(new String[] {"active", "not active"}));
				
				GroupLayout groupLayout = new GroupLayout(getContentPane());
				groupLayout.setHorizontalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblTnhTrng, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(tinhTrangBox, 0, 173, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblVTrLm)
										.addComponent(lblNewLabel_1))
									.addGap(3)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(tenTaiKhoanField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
										.addComponent(matKhauField, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(maTaiKhoanField, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
										.addComponent(comboBox, 0, 174, Short.MAX_VALUE))))
							.addGap(107))
				);
				groupLayout.setVerticalGroup(
					groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(34)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(maTaiKhoanField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(tenTaiKhoanField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(35)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblVTrLm)
								.addComponent(matKhauField, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTnhTrng, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(tinhTrangBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3))
							.addGap(29))
				);
				getContentPane().setLayout(groupLayout);
				setVisible(true);
			}
			
		}
}
