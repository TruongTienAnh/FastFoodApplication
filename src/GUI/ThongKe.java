package GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.jar.Attributes.Name;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.event.ChangeEvent;
import javax.management.loading.PrivateClassLoader;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import DAO.DAODonHang;
import DAO.DAOTimKiem;
import DTO.ExcelExporter;
import DTO.THONGKE;
import Design.GradientButton;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
public class ThongKe extends JPanel {

	private JPanel contentPane;
	private JTabbedPane thongKePane;
	private JTable table;
	private JTable table2;
	private JTable table3;
	private String title = "";
	private String path = "";
	DAODonHang bangDonHang = new DAODonHang();
	DAOTimKiem timKiem = new DAOTimKiem();
	ArrayList<THONGKE> thongke = new ArrayList<THONGKE>();
	int month;
	int yearChoosed;
	ArrayList<Integer> yearArrayList = new ArrayList<Integer>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe frame = new ThongKe();
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
	public ThongKe() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		
		thongKePane = new JTabbedPane(JTabbedPane.TOP);
		thongKePane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				changeTabColor(thongKePane);
			}
		});
		thongKePane.setFont(new Font("SansSerif", Font.PLAIN, 15));
		
		DoanhThu();
		
		DonHang();
		
		DonNhap();
	}
	// ****************************************ĐƠN HÀNG**************************************
	public void DonHang() {
		JPanel donHangMainPane = new JPanel();
		thongKePane.addTab("Đơn hàng", null, donHangMainPane, null);
		String t[] = new String[12];
		for(int i = 0; i < 12; i++) {
			 t[i] = String.valueOf(i+1);
		}
		
		JTabbedPane donHangTabedPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel doanhThuOptionPane = new JPanel();
		doanhThuOptionPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_2_1 = new JLabel("Doanh thu theo:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Theo năm");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongke = renderAllDONHANGbyYear();
				title = "Thống kê đơn hàng từng năm";
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoThng = new JRadioButton("Theo tháng trong năm");
		
		rdbtnTheoThng.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoNgyTrong = new JRadioButton("Theo ngày trong tháng");
		
		rdbtnTheoNgyTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoQu = new JRadioButton("Theo quý");
		
		rdbtnTheoQu.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnTheoQu);
		buttonGroup.add(rdbtnTheoNgyTrong);
		buttonGroup.add(rdbtnTheoThng);
		buttonGroup.add(rdbtnNewRadioButton);
		JLabel lblNewLabel_2 = new JLabel("Chọn tháng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JComboBox chonThangDoanhThucomboBox = new JComboBox(t);
		
		JLabel lblChnNm_2 = new JLabel("Chọn năm");
		lblChnNm_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JComboBox chonNamDoanhThucomboBox = new JComboBox();
		readYearFromDH();
		for (int i = 0; i < yearArrayList.size(); i++) {
			chonNamDoanhThucomboBox.addItem(yearArrayList.get(i));
		}
		
		rdbtnTheoThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongke = renderAllDONHANGbyMonth();
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				title = "Thống kê đơn hàng năm " + chonNamDoanhThucomboBox.getSelectedItem();
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnTheoNgyTrong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				month = Integer.parseInt( String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
				thongke = renderAllDONHANGbyDay(month, yearChoosed);
				title = "Thống kê Đơn Hàng tháng " + month +"/" + yearChoosed;
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnTheoQu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				thongke = renderAllDONHANGbySeason(yearChoosed);
				title = "Thống kê đơn hàng các mùa trong năm " + yearChoosed;
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		chonNamDoanhThucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt(String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				if(rdbtnTheoThng.isSelected()) {
					thongke = renderAllDONHANGbyMonth();
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					title = "Thống kê đơn hàng năm " + chonNamDoanhThucomboBox.getSelectedItem();	
				}
				else if(rdbtnTheoNgyTrong.isSelected()) {
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					month = Integer.parseInt( String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
					thongke = renderAllDONHANGbyDay(month, yearChoosed);
					title = "Thống kê Đơn Hàng tháng " + month +"/" + yearChoosed;
				}
				else if(rdbtnTheoQu.isSelected()) {
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					thongke = renderAllDONHANGbySeason(yearChoosed);
					title = "Thống kê đơn hàng các mùa trong năm " + yearChoosed;
					if(thongke.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane,
							    "Không có dữ liệu nào được tìm thấy!");
					}
				}
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		chonThangDoanhThucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt(String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				month = Integer.parseInt(String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
				if(rdbtnTheoNgyTrong.isSelected()) {
					thongke = renderDONHANGbyDay(month, yearChoosed);
					title = "Thống kê đơn hàng tháng " + month +"/" + yearChoosed;
				}
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		JButton btnNewButton_2 = new JButton("Xem biểu đồ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBarChart(title, thongke);
			}
		});
		
		JButton btnNewButton_2_1 = new JButton("Xuất excel");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatExcel(thongke);
			}
		});
		GroupLayout gl_doanhThuOptionPane = new GroupLayout(doanhThuOptionPane);
		gl_doanhThuOptionPane.setHorizontalGroup(
			gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
									.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rdbtnTheoThng, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(rdbtnTheoNgyTrong, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnTheoQu, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(chonThangDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblChnNm_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(chonNamDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_2)
							.addGap(34)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(263, Short.MAX_VALUE))
		);
		gl_doanhThuOptionPane.setVerticalGroup(
			gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnTheoThng, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTheoQu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnTheoNgyTrong, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addGap(32)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(4)
							.addComponent(chonThangDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblChnNm_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(4)
							.addComponent(chonNamDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		doanhThuOptionPane.setLayout(gl_doanhThuOptionPane);
		GroupLayout gl_donHangMainPane = new GroupLayout(donHangMainPane);
		gl_donHangMainPane.setHorizontalGroup(
			gl_donHangMainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_donHangMainPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_donHangMainPane.createParallelGroup(Alignment.LEADING)
						.addComponent(donHangTabedPane, GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
						.addComponent(doanhThuOptionPane, GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_donHangMainPane.setVerticalGroup(
			gl_donHangMainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_donHangMainPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(doanhThuOptionPane, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(donHangTabedPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(0))
		);
		
		JPanel donHangChiTietTabedPane = new JPanel();
		donHangTabedPane.addTab("Chi tiết", null, donHangChiTietTabedPane, null);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JScrollPane doanhThuChiTietscrollPane = new JScrollPane();
		doanhThuChiTietscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_donHangChiTietTabedPane = new GroupLayout(donHangChiTietTabedPane);
		doanhThuChiTietscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		table = new JTable();
		table.setModel(new DefaultTableModel(
			    new Object[][] {
			        // Thêm các hàng dữ liệu ở đây
			    },
			    new String[] {
			        "STT", "Thời gian", "Doanh thu"
			    }
		));
		table.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table.getTableHeader().setBackground(Color.decode("#CB356B"));
		table.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		doanhThuChiTietscrollPane.setViewportView(table);
		gl_donHangChiTietTabedPane.setHorizontalGroup(
			gl_donHangChiTietTabedPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_donHangChiTietTabedPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1193))
				.addComponent(doanhThuChiTietscrollPane, GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
		);
		gl_donHangChiTietTabedPane.setVerticalGroup(
			gl_donHangChiTietTabedPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_donHangChiTietTabedPane.createSequentialGroup()
					.addComponent(doanhThuChiTietscrollPane, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(379))
		);
		donHangChiTietTabedPane.setLayout(gl_donHangChiTietTabedPane);
		donHangMainPane.setLayout(gl_donHangMainPane);
	}
	//********************************************DOANH THU**********************************************
	public void DoanhThu() {
		JPanel doanhThuMainPane_1 = new JPanel();
		thongKePane.addTab("Doanh thu", null, doanhThuMainPane_1, null);
		
		JTabbedPane doanhThuMainPane = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel doanhThuOptionPane = new JPanel();
		doanhThuOptionPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		String t[] = new String[12];
		for(int i = 0; i < 12; i++) {
			 t[i] = String.valueOf(i+1);
		}
		JComboBox chonThangDoanhThucomboBox = new JComboBox(t);
		
		
		JComboBox chonNamDoanhThucomboBox = new JComboBox();
		
		readYearFromDH();
		for (int i = 0; i < yearArrayList.size(); i++) {
			chonNamDoanhThucomboBox.addItem(yearArrayList.get(i));
		}
		JLabel lblNewLabel_2 = new JLabel("Chọn tháng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		JButton btnNewButton_1 = new GradientButton("Thêm","#CB356B","#BD3F32");
		
		
		JLabel lblChnNm_2 = new JLabel("Chọn năm");
		lblChnNm_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JLabel lblNewLabel_2_1 = new JLabel("Doanh thu theo:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Theo năm");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongke = renderDONHANGbyYear();
				title = "Thống kê doanh thu từng năm";
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoThng = new JRadioButton("Theo tháng trong năm");
		rdbtnTheoThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongke = renderDONHANGbyMonth();
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				title = "Thống kê doanh thu năm " + chonNamDoanhThucomboBox.getSelectedItem();
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnTheoThng.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoNgyTrong = new JRadioButton("Theo ngày trong tháng");
		rdbtnTheoNgyTrong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				month = Integer.parseInt( String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
				thongke = renderDONHANGbyDay(month, yearChoosed);
				title = "Thống kê doanh thu tháng " + month +"/" + yearChoosed;
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnTheoNgyTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		JRadioButton rdbtnTheoQu = new JRadioButton("Theo quý");
		rdbtnTheoQu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				thongke = renderDONHANGbySeason(yearChoosed);
				title = "Thống kê doanh thu quý trong năm " + yearChoosed;
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		buttonGroup.add(rdbtnTheoQu);
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnTheoThng);
		buttonGroup.add(rdbtnTheoNgyTrong);
		
		
		// action to combox year
		chonNamDoanhThucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt(String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				if(rdbtnTheoThng.isSelected()) {
					thongke = renderDONHANGbyMonth();
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					title = "Thống kê doanh thu năm " + chonNamDoanhThucomboBox.getSelectedItem();	
				}
				else if(rdbtnTheoNgyTrong.isSelected()) {
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					month = Integer.parseInt( String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
					thongke = renderDONHANGbyDay(month, yearChoosed);
					title = "Thống kê doanh thu tháng " + month +"/" + yearChoosed;
				}
				else if(rdbtnTheoQu.isSelected()) {
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					thongke = renderDONHANGbySeason(yearChoosed);
					title = "Thống kê doanh thu quý trong năm " + yearChoosed;
					if(thongke.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane,
							    "Không có dữ liệu nào được tìm thấy!");
					}
				}
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		chonThangDoanhThucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt(String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				month = Integer.parseInt(String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
				if(rdbtnTheoNgyTrong.isSelected()) {
					thongke = renderDONHANGbyDay(month, yearChoosed);
					title = "Thống kê doanh thu tháng " + month +"/" + yearChoosed;
				}
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		
		GroupLayout gl_doanhThuMainPane_1 = new GroupLayout(doanhThuMainPane_1);
		gl_doanhThuMainPane_1.setHorizontalGroup(
			gl_doanhThuMainPane_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_doanhThuMainPane_1.createSequentialGroup()
					.addGroup(gl_doanhThuMainPane_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_doanhThuMainPane_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(doanhThuMainPane, GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE))
						.addGroup(gl_doanhThuMainPane_1.createSequentialGroup()
							.addGap(10)
							.addComponent(doanhThuOptionPane, GroupLayout.PREFERRED_SIZE, 1210, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_doanhThuMainPane_1.setVerticalGroup(
			gl_doanhThuMainPane_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuMainPane_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(doanhThuOptionPane, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(doanhThuMainPane, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JButton btnNewButton_2 = new JButton("Xem biểu đồ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonGroup.isSelected(null))
					JOptionPane.showMessageDialog(contentPane,
						    "Xin chọn định dạng doanh thu!",
						    "Inane warning",
						    JOptionPane.WARNING_MESSAGE);
				else {
					showBarChart(title, thongke);
				}
			}
		});
		
		
		rdbtnTheoQu.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JButton xuatExcelDoanhThu = new JButton("Xuất excel");
		xuatExcelDoanhThu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Bạn chưa chọn dữ liệu!",
						    "Inane warning",
						    JOptionPane.WARNING_MESSAGE);
				}
				else {
					xuatExcel(thongke);
				}
			}
		});
		GroupLayout gl_doanhThuOptionPane = new GroupLayout(doanhThuOptionPane);
		gl_doanhThuOptionPane.setHorizontalGroup(
			gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
									.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rdbtnTheoThng, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(rdbtnTheoNgyTrong, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnTheoQu, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(chonThangDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblChnNm_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(chonNamDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_2)
							.addGap(34)
							.addComponent(xuatExcelDoanhThu, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(263, Short.MAX_VALUE))
		);
		gl_doanhThuOptionPane.setVerticalGroup(
			gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnTheoThng, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTheoQu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnTheoNgyTrong, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addGap(32)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(4)
							.addComponent(chonThangDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblChnNm_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(4)
							.addComponent(chonNamDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(xuatExcelDoanhThu, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		doanhThuOptionPane.setLayout(gl_doanhThuOptionPane);
		
		JPanel doanhThuChiTietTabbedPane = new JPanel();
		doanhThuMainPane.addTab("Chi tiết", null, doanhThuChiTietTabbedPane, null);
		JScrollPane doanhThuChiTietscrollPane = new JScrollPane();
		doanhThuChiTietscrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		table2 = new JTable();
		table2.setModel(new DefaultTableModel(
			    new Object[][] {
			        // Thêm các hàng dữ liệu ở đây
			    },
			    new String[] {
			        "STT", "Thời gian", "Doanh thu"
			    }
		));
		table2.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table2.getTableHeader().setBackground(Color.decode("#CB356B"));
		table2.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		doanhThuChiTietscrollPane.setViewportView(table2);
		
		GroupLayout gl_doanhThuChiTietTabbedPane = new GroupLayout(doanhThuChiTietTabbedPane);
		gl_doanhThuChiTietTabbedPane.setHorizontalGroup(
			gl_doanhThuChiTietTabbedPane.createParallelGroup(Alignment.LEADING)
				.addComponent(doanhThuChiTietscrollPane, GroupLayout.DEFAULT_SIZE, 1205, Short.MAX_VALUE)
		);
		gl_doanhThuChiTietTabbedPane.setVerticalGroup(
			gl_doanhThuChiTietTabbedPane.createParallelGroup(Alignment.LEADING)
				.addComponent(doanhThuChiTietscrollPane, GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
		);
		doanhThuChiTietTabbedPane.setLayout(gl_doanhThuChiTietTabbedPane);
		doanhThuMainPane_1.setLayout(gl_doanhThuMainPane_1);
	}
	// ------------------------------------------DON NHAP---------------------------------
	public void DonNhap() {
		JPanel donNhapMainPane = new JPanel();
		thongKePane.addTab("Đơn nhập", null, donNhapMainPane, null);
		String t[] = new String[12];
		for(int i = 0; i < 12; i++) {
			 t[i] = String.valueOf(i+1);
		}
		
		JTabbedPane doanhThuMainPane_2_1 = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel doanhThuOptionPane = new JPanel();
		doanhThuOptionPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel lblNewLabel_2_1 = new JLabel("Doanh thu theo:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Theo năm");
		rdbtnNewRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongke = renderDONNHAPbyYear();
				title = "Thống kê đơn nhập từng năm";
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoThng = new JRadioButton("Theo tháng trong năm");
		
		rdbtnTheoThng.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoNgyTrong = new JRadioButton("Theo ngày trong tháng");
		
		rdbtnTheoNgyTrong.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JRadioButton rdbtnTheoQu = new JRadioButton("Theo quý");
		
		rdbtnTheoQu.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnNewRadioButton);
		buttonGroup.add(rdbtnTheoThng);
		buttonGroup.add(rdbtnTheoQu);
		buttonGroup.add(rdbtnTheoNgyTrong);
		
		JLabel lblNewLabel_2 = new JLabel("Chọn tháng");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		JComboBox chonThangDoanhThucomboBox = new JComboBox(t);
		
		
		JLabel lblChnNm_2 = new JLabel("Chọn năm");
		lblChnNm_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		JComboBox chonNamDoanhThucomboBox = new JComboBox();
		readYearFromDH();
		for (int i = 0; i < yearArrayList.size(); i++) {
			chonNamDoanhThucomboBox.addItem(yearArrayList.get(i));
		}
		chonNamDoanhThucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt(String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				if(rdbtnTheoThng.isSelected()) {
					thongke = renderAllDONNHAPbyMonth();
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					title = "Thống kê đơn hàng năm " + chonNamDoanhThucomboBox.getSelectedItem();	
				}
				else if(rdbtnTheoNgyTrong.isSelected()) {
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					month = Integer.parseInt( String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
					thongke = renderAllDONNHAPbyDay(month, yearChoosed);
					title = "Thống kê Đơn Hàng tháng " + month +"/" + yearChoosed;
				}
				else if(rdbtnTheoQu.isSelected()) {
					yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
					thongke = renderAllDONNHAPbySeason(yearChoosed);
					title = "Thống kê đơn hàng các mùa trong năm " + yearChoosed;
					if(thongke.isEmpty()) {
						JOptionPane.showMessageDialog(contentPane,
							    "Không có dữ liệu nào được tìm thấy!");
					}
				}
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		chonThangDoanhThucomboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt(String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				month = Integer.parseInt(String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
				if(rdbtnTheoNgyTrong.isSelected()) {
					thongke = renderAllDONNHAPbyDay(month, yearChoosed);
					title = "Thống kê đơn hàng tháng " + month +"/" + yearChoosed;
				}
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		
		
		rdbtnTheoThng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thongke = renderAllDONNHAPbyMonth();
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				title = "Thống kê đơn nhập năm " + chonNamDoanhThucomboBox.getSelectedItem();
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnTheoNgyTrong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				month = Integer.parseInt( String.valueOf(chonThangDoanhThucomboBox.getSelectedItem()));
				thongke = renderAllDONNHAPbyDay(month, yearChoosed);
				title = "Thống kê Đơn Nhập tháng " + month +"/" + yearChoosed;
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		rdbtnTheoQu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yearChoosed = Integer.parseInt( String.valueOf(chonNamDoanhThucomboBox.getSelectedItem()));
				thongke = renderAllDONNHAPbySeason(yearChoosed);
				title = "Thống kê Đơn Nhập các mùa trong năm " + yearChoosed;
				if(thongke.isEmpty()) {
					JOptionPane.showMessageDialog(contentPane,
						    "Không có dữ liệu nào được tìm thấy!");
				}
			}
		});
		
		JButton btnNewButton_2 = new JButton("Xem biểu đồ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showBarChart(title, thongke);
			}
		});
		
		JButton btnNewButton_2_1 = new JButton("Xuất excel");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				xuatExcel(thongke);
			}
		});
		GroupLayout gl_doanhThuOptionPane = new GroupLayout(doanhThuOptionPane);
		gl_doanhThuOptionPane.setHorizontalGroup(
			gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
									.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
									.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(rdbtnTheoThng, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
									.addGap(23)
									.addComponent(rdbtnTheoNgyTrong, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnTheoQu, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
									.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(chonThangDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblChnNm_2, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
									.addGap(4)
									.addComponent(chonNamDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewButton_2)
							.addGap(34)
							.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(263, Short.MAX_VALUE))
		);
		gl_doanhThuOptionPane.setVerticalGroup(
			gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnNewRadioButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addComponent(rdbtnTheoThng, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnTheoQu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnTheoNgyTrong, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))))
					.addGap(32)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(4)
							.addComponent(chonThangDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblChnNm_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_doanhThuOptionPane.createSequentialGroup()
							.addGap(4)
							.addComponent(chonNamDoanhThucomboBox, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_doanhThuOptionPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
						.addComponent(btnNewButton_2_1, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		doanhThuOptionPane.setLayout(gl_doanhThuOptionPane);
		GroupLayout gl_donNhapMainPane = new GroupLayout(donNhapMainPane);
		gl_donNhapMainPane.setHorizontalGroup(
			gl_donNhapMainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_donNhapMainPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_donNhapMainPane.createParallelGroup(Alignment.LEADING)
						.addComponent(doanhThuMainPane_2_1, GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE)
						.addComponent(doanhThuOptionPane, GroupLayout.DEFAULT_SIZE, 1210, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_donNhapMainPane.setVerticalGroup(
			gl_donNhapMainPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_donNhapMainPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(doanhThuOptionPane, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(doanhThuMainPane_2_1, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		
		JScrollPane scrollPane = new JScrollPane();
		doanhThuMainPane_2_1.addTab("Chi tiết", null, scrollPane, null);
		donNhapMainPane.setLayout(gl_donNhapMainPane);
		table3 = new JTable();
		table3.setModel(new DefaultTableModel(
			    new Object[][] {
			        // Thêm các hàng dữ liệu ở đây
			    },
			    new String[] {
			        "STT", "Thời gian", "Doanh thu"
			    }
		));
		table3.getTableHeader().setForeground(Color.decode("#FFEE58"));
		table3.getTableHeader().setBackground(Color.decode("#CB356B"));
		table3.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,15));
		scrollPane.setViewportView(table3);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(thongKePane)
					.addGap(15))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(thongKePane, GroupLayout.PREFERRED_SIZE, 660, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(18, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void changeTabColor(JTabbedPane self) {
		int s = self.getSelectedIndex();
		for (int i = 0;i<thongKePane.getTabCount();i++) {
			if(i!=s) {
				self.setForegroundAt(i, Color.decode("#000000"));
				self.setBackgroundAt(i, Color.decode("#FFFFFF"));
			}
		}
		self.setForegroundAt(s, Color.decode("#CB356B"));
	}
	
	
	public ArrayList<THONGKE> renderDONHANGbyYear() {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = bangDonHang.selectByYear();
		table2.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {	        
				private static final long serialVersionUID = 1L;
					@Override
			        public boolean isCellEditable(int row, int column) {
			            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
			            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table2.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONHANGbyYear() {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDHByYear();
		table.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {	        
				private static final long serialVersionUID = 1L;
					@Override
			        public boolean isCellEditable(int row, int column) {
			            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
			            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderDONNHAPbyYear() {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDNByYear();
		table3.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {	        
				private static final long serialVersionUID = 1L;
					@Override
			        public boolean isCellEditable(int row, int column) {
			            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
			            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table3.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderDONHANGbyMonth() {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = bangDonHang.selectByMonth(yearChoosed);
		table2.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table2.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONHANGbyMonth() {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDHByMonth(yearChoosed);
		table.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONNHAPbyMonth() {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDNByMonth(yearChoosed);
		table3.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table3.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderDONHANGbyDay(int month, int year) {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = bangDonHang.selectByDay(month, year);
		table2.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table2.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONHANGbyDay(int month, int year) {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDHByDay(month, year);
		table.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONNHAPbyDay(int month, int year) {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDNByDay(month, year);
		table3.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
		        /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table3.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public void showBarChart(String title, ArrayList<THONGKE> t) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(THONGKE temp: t) {
			dataset.setValue(temp.getDoanhThu(), "Doanh thu", temp.getDate());
		}
		JFreeChart chart = ChartFactory.createBarChart(title, "Thời gian", "Tổng tiền(VND)", dataset, PlotOrientation.VERTICAL, false, true, false);
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setRangeGridlinePaint(Color.BLACK);
		ChartFrame frame = new ChartFrame("DOANH THU", chart);
		frame.setVisible(true);
		frame.setSize(600, 400);
	}
	public ArrayList<THONGKE> renderDONHANGbySeason(int year) {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = bangDonHang.selectBySeason(year);
		table2.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table2.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONHANGbySeason(int year) {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDHBySeason(year);
		table.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public ArrayList<THONGKE> renderAllDONNHAPbySeason(int year) {
		ArrayList<DTO.THONGKE> listDH = new ArrayList<DTO.THONGKE>();
		listDH = timKiem.selectAllDNBySeason(year);
		table3.setModel(new DefaultTableModel(
				 new String[] {"STT", "Thời gian", "Doanh thu"},
		    0) {
				private static final long serialVersionUID = 1L;
				@Override
		        public boolean isCellEditable(int row, int column) {
		            // Trả về false để ngăn người dùng chỉnh sửa các ô trong bảng
		            return false;
		        }
		});
		for(DTO.THONGKE t: listDH) {
			Object[] data = {t.getSTT(), t.getDate(), t.getDoanhThu()};
			DefaultTableModel model = (DefaultTableModel) table3.getModel();
			model.addRow(data);
		}
		return listDH;
	}
	public void xuatExcel(ArrayList<THONGKE> t) {
		if(t.isEmpty()) {
			JOptionPane.showMessageDialog(contentPane, 
					"Bạn chưa chọn dữ liệu",
	                "Lưu file excel",
	                JOptionPane.ERROR_MESSAGE);
		}
		else {
			path = ExcelExporter.export(t, title);
			
			JOptionPane.showMessageDialog(contentPane, 
					path,
	                "Lưu file excel",
	                JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void readYearFromDH() {
		yearArrayList = bangDonHang.getListYear();
	}
}
