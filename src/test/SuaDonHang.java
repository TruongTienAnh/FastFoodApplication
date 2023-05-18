package test;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JTextField;

import Design.GradientButton;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import DAO.DAONhanVien;
import DTO.DONHANG;
import DTO.NHANVIEN;


public class SuaDonHang extends JFrame {
	private JTextField soNhanMonTxt;
	private JTextField tenTxt;
	private JTextField soDienThoaiTxt;
	private JTextField diaChiTxt;
	
	public SuaDonHang(DONHANG t) {
		setTitle("Sửa thông tin đơn hàng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(969,324);
		setLocationRelativeTo(null);
		JLabel lblNewLabel_2 = new JLabel("Mã đơn hàng :");
		lblNewLabel_2.setForeground(new Color(191, 0, 0));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel = new JLabel("Mã nhân viên :");
		lblNewLabel.setForeground(new Color(191, 0, 0));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1 = new JLabel("Loại đơn :");
		lblNewLabel_1.setForeground(new Color(191, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		GradientButton btnNewButton = new GradientButton("Thêm","#CB356B","#BD3F32");
		btnNewButton.setText("Sửa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JComboBox id_nhan_vien_combo = new JComboBox();
		id_nhan_vien_combo.setModel(new DefaultComboBoxModel(hienThiNhanVien_combobox()));
		id_nhan_vien_combo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel Id_don_hang_txt = new JLabel("New label");
		Id_don_hang_txt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JComboBox loai_don_combo = new JComboBox();
		loai_don_combo.setModel(new DefaultComboBoxModel(new String[] {"Tại chỗ", "Mang đi"}));
		loai_don_combo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_1 = new JLabel("Số nhận món :");
		lblNewLabel_1_1.setForeground(new Color(191, 0, 0));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Tên :");
		lblNewLabel_1_1_1.setForeground(new Color(191, 0, 0));
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Số điện thoại :");
		lblNewLabel_1_1_1_1.setForeground(new Color(191, 0, 0));
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Địa chỉ :");
		lblNewLabel_1_1_1_2.setForeground(new Color(191, 0, 0));
		lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		soNhanMonTxt = new JTextField();
		soNhanMonTxt.setColumns(10);
		
		tenTxt = new JTextField();
		tenTxt.setColumns(10);
		
		soDienThoaiTxt = new JTextField();
		soDienThoaiTxt.setColumns(10);
		
		diaChiTxt = new JTextField();
		diaChiTxt.setColumns(10);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Trạng thái :");
		lblNewLabel_1_1_2.setForeground(new Color(191, 0, 0));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JComboBox trang_thai_combo = new JComboBox();
		trang_thai_combo.setModel(new DefaultComboBoxModel(new String[] {"Đang xử lý", "Đang giao", "Hoàn thành"}));
		trang_thai_combo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(trang_thai_combo, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1_1_1_2, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(id_nhan_vien_combo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(loai_don_combo, 0, 183, Short.MAX_VALUE))
								.addComponent(Id_don_hang_txt, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
							.addGap(175)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(soDienThoaiTxt, 210, 210, 210)
						.addComponent(tenTxt, 210, 210, 210)
						.addComponent(soNhanMonTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(diaChiTxt, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(420, Short.MAX_VALUE)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
					.addGap(412))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(34)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(soNhanMonTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tenTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(soDienThoaiTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(diaChiTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(lblNewLabel_2)
								.addComponent(Id_don_hang_txt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(id_nhan_vien_combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_1)
								.addComponent(loai_don_combo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(lblNewLabel_1_1_2, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(trang_thai_combo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))))
					.addGap(30)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(30))
		);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
	}
	public String[] hienThiNhanVien_combobox() {
		ArrayList<NHANVIEN> ds_nv = new DAONhanVien().layNhanVienBanHang();
		String [] ma_nv_bh = new String[ds_nv.size()];
		for(int i = 0; i<ds_nv.size();i++) {
			ma_nv_bh[i] = ds_nv.get(i).getId();
		}
		return ma_nv_bh;
	}
}
