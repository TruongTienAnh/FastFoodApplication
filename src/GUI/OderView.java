package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.geom.Dimension2D;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;

import BUS.OrderView_BUS;
import DAO.DAODonHang;
import DTO.DONHANG;
import Design.GradientButton;
import Design.RoundTextfield;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class OderView extends JPanel {

//	private JPanel contentPane;
	public JTextField textField;
	private JLabel lblNewLabel;
	public JTable table;
	public JComboBox comboBox;
	public JDateChooser dateChooser_1;
	public JDateChooser dateChooser;
	public GradientButton btnNewButton_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OderView frame = new OderView();
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
	public OderView() {
		OderView self = this;
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

//		setContentPane(contentPane);
		
		textField = new RoundTextfield("");
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(textField.getText().equalsIgnoreCase("Nhập vào mã đơn, mã nhân viên...")) {
					textField.setText("");
				}
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().equalsIgnoreCase("")) {
					textField.setText("Nhập vào mã đơn, mã nhân viên...");
				}
				
			}
		});
		textField.setText("Nhập vào mã đơn, mã nhân viên...");
		textField.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textField.setColumns(10);
		textField.setMaximumSize(new Dimension(100,40));
		
		lblNewLabel = new JLabel("Từ :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblNewLabel.setForeground(Color.decode("#CB356B"));
		
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setMinimumSize(new Dimension(50,40));
		dateChooser.setFont(new Font("SansSerif",Font.PLAIN,18));
		
		JLabel lbln = new JLabel("Đến :");
		lbln.setHorizontalAlignment(SwingConstants.RIGHT);
		lbln.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lbln.setForeground(Color.decode("#CB356B"));
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setDateFormatString("dd-MM-yyyy");
		dateChooser_1.setFont(new Font("SansSerif",Font.PLAIN,18));
		dateChooser_1.setMinimumSize(new Dimension(50,40));
		
		JButton btnNewButton = new GradientButton("Sửa","#CB356B","#BD3F32");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderView_BUS(self).chinhSuaDonHang();
			}
		});
		btnNewButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton.setMinimumSize(new Dimension(80,40));
		
		GradientButton btnNewButton_1 = new GradientButton("Xóa","#CB356B","#BD3F32");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderView_BUS(self).xoaDonHang();
			}
		});
		btnNewButton_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_1.setMinimumSize(new Dimension(80,40));
		
		btnNewButton_2 = new GradientButton("Reset","#a8ff78","#78ffd6");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setColor1("#b0ee78");
				btnNewButton_2.setColor2("#60eed6");
				btnNewButton_2.setForeground(Color.decode("#FFEE58"));
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setColor1("#a8ff78");
				btnNewButton_2.setColor2("#78ffd6");
				btnNewButton_2.setForeground(Color.white);
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderView_BUS(self).hienThiNgayLucDau();
				comboBox.setSelectedIndex(0);
				textField.setText("Nhập vào mã đơn, mã nhân viên...");
				new OrderView_BUS(self).hienThiDonHangLanDau();
				
			}
		});
		
		btnNewButton_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnNewButton_2.setText("Tải lại");
		btnNewButton_2.setMinimumSize(new Dimension(80,40));
		
		
		GradientButton grdntbtnTmKim = new GradientButton("Sửa", "#CB356B", "#BD3F32");
		grdntbtnTmKim.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText().equalsIgnoreCase("Nhập vào mã đơn, mã nhân viên.."))
					JOptionPane.showMessageDialog(null, "Vui lòng nhập vào mã đơn hàng hoặc mã nhân viên");
				else  
					new OrderView_BUS(self).timKiemDonHang(textField.getText());
			}
		});
		grdntbtnTmKim.setText("Tìm");
		grdntbtnTmKim.setCursor(new Cursor(Cursor.HAND_CURSOR));
		grdntbtnTmKim.setMinimumSize(new Dimension(80, 40));
		grdntbtnTmKim.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		JLabel lblNewLabel_2 = new JLabel("");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		GradientButton btnNewButton_1_1 = new GradientButton("", "#CB356B","#BD3F32");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row == -1) {
					JOptionPane.showConfirmDialog(null, "Làm ơn chọn 1 đơn hàng đã hoàn thành","Cảnh báo",JOptionPane.CANCEL_OPTION);
				}else {
					String hoan_thanh = table.getValueAt(row, 8).toString();
					if(hoan_thanh.equalsIgnoreCase("Hoàn thành")) {
						JOptionPane.showMessageDialog(null, "Đơn hàng đã hoàn thành");
					}else {
						int confirm = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn đã hoàn thành đơn","Cảnh báo",JOptionPane.CANCEL_OPTION);
						if(confirm == 0) {
							new DAODonHang().HoanThanh(table.getValueAt(row, 0).toString());
							new OrderView_BUS(self).hienThiDonHangLanDau();
						}
					}
				}
			}
		});
		btnNewButton_1_1.setText("Hoàn thành");
		btnNewButton_1_1.setMinimumSize(new Dimension(80, 40));
		btnNewButton_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		
		GradientButton btnNewButton_2_1_1 = new GradientButton("Reset", "#a8ff78", "#78ffd6");
		btnNewButton_2_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderView_BUS(self).inDonHangRaExcel();
			}
		});
		btnNewButton_2_1_1.setText("Export to Excel");
		btnNewButton_2_1_1.setMinimumSize(new Dimension(80, 40));
		btnNewButton_2_1_1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new OrderView_BUS(self).sapXep_LocTheoNgay();
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sắp xếp", "Tổng tiền(Thấp -> Cao)", "Tổng tiền(Cao -> Thấp)"}));
		
		GradientButton btnNewButton_1_2 = new GradientButton("Xóa", "#CB356B", "#BD3F32");
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selected_row = table.getSelectedRow();
				if(selected_row == -1) {
					JOptionPane.showConfirmDialog(null, "Hãy chọn một hóa đơn để in");
				}else {
					String ma_hoa_don = table.getValueAt(selected_row, 0).toString();
					new OrderView_BUS(self).inDonHangPDF(ma_hoa_don);
				}
			}
		});
		btnNewButton_1_2.setText("in");
		btnNewButton_1_2.setMinimumSize(new Dimension(80, 40));
		btnNewButton_1_2.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		
		GroupLayout gl_contentPane = new GroupLayout(this);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(27)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(grdntbtnTmKim, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(32)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbln, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dateChooser_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
							.addComponent(btnNewButton_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(36)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 402, Short.MAX_VALUE)
							.addComponent(btnNewButton_2_1_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 1196, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(15)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(btnNewButton_2, 0, 0, Short.MAX_VALUE)
								.addComponent(dateChooser_1, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)
								.addComponent(lbln, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(dateChooser, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
								.addComponent(grdntbtnTmKim, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
						.addComponent(lblNewLabel_2, GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnNewButton_1_2, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addComponent(btnNewButton_2_1_1, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		table = new JTable();
		table.setRowHeight(50);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"M\u00E3 \u0111\u01A1n h\u00E0ng", "M\u00E3 nh\u00E2n vi\u00EAn", "Ng\u00E0y \u0111\u1EB7t", "Th\u00E0nh ti\u1EC1n", "T\u00EAn", "\u0110\u1ECBa ch\u1EC9", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i", "S\u1ED1 \u0111\u1EB7t m\u00F3n", "Tr\u1EA1ng th\u00E1i"
			}
		));
		table.getTableHeader().setFont(new Font("SansSerif",Font.PLAIN,18));
		table.getTableHeader().setBackground(Color.decode("#CB356B"));
		table.getTableHeader().setForeground(Color.yellow);
		
		table.setFont(new Font("SansSerif",Font.PLAIN,18));
		
		
		scrollPane.setViewportView(table);
		setLayout(gl_contentPane);
		
		new OrderView_BUS(this).hienThiDonHangLanDau();
		
		new OrderView_BUS(self).hienThiNgayLucDau();
		
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					new OrderView_BUS(self).sapXep_LocTheoNgay();
					System.out.println(1);
				}
			}
		});
		
		dateChooser_1.addPropertyChangeListener(new PropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					new OrderView_BUS(self).sapXep_LocTheoNgay();
					System.out.println(2);
				}
			}
		});
	}
}
