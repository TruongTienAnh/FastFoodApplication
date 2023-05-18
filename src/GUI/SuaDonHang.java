package GUI;

import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JTextField;

import Design.GradientButton;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import com.toedter.calendar.JDateChooser;

import DAO.DAODonHang;
import DAO.DAONhanVien;
import DTO.DONHANG;
import DTO.NHANVIEN;


public class SuaDonHang extends JFrame {
	private JTextField soNhanMonTxt;
	private JTextField tenTxt;
	private JTextField soDienThoaiTxt;
	private JTextField diaChiTxt;
	private JComboBox id_nhan_vien_combo;
	private JComboBox loai_don_combo;
	private JComboBox trang_thai_combo;
	private DONHANG t1;
	private JLabel dia_chi_lbl;
	private Component soDienThoaiLbl;
	private Component ten_lbl;
	private JLabel Id_don_hang_txt;
	private Component so_nhan_mon_lbl;
	private OderView ord;
	
	public SuaDonHang(OderView orderView,DONHANG t) {
		t1 = t;
		ord = orderView;
		setTitle("Sửa thông tin đơn hàng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(984,324);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				suaDonHang();
			}
		});
		btnNewButton.setText("Sửa");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		id_nhan_vien_combo = new JComboBox();
		id_nhan_vien_combo.setModel(new DefaultComboBoxModel(layMaNhanVienBanHang()));
		id_nhan_vien_combo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		Id_don_hang_txt = new JLabel("New label");
		Id_don_hang_txt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		loai_don_combo = new JComboBox();
		loai_don_combo.setModel(new DefaultComboBoxModel(new String[] {"Tại chỗ", "Mang đi"}));
		loai_don_combo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		so_nhan_mon_lbl = new JLabel("Số nhận món :");
		so_nhan_mon_lbl.setForeground(new Color(191, 0, 0));
		so_nhan_mon_lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		ten_lbl = new JLabel("Tên :");
		ten_lbl.setForeground(new Color(191, 0, 0));
		ten_lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		soDienThoaiLbl = new JLabel("Số điện thoại :");
		soDienThoaiLbl.setForeground(new Color(191, 0, 0));
		soDienThoaiLbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		dia_chi_lbl = new JLabel("Địa chỉ :");
		dia_chi_lbl.setForeground(new Color(191, 0, 0));
		dia_chi_lbl.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		soNhanMonTxt = new JTextField();
		soNhanMonTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		soNhanMonTxt.setColumns(10);
		
		tenTxt = new JTextField();
		tenTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tenTxt.setColumns(10);
		
		soDienThoaiTxt = new JTextField();
		soDienThoaiTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		soDienThoaiTxt.setColumns(10);
		
		diaChiTxt = new JTextField();
		diaChiTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		diaChiTxt.setColumns(10);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Trạng thái :");
		lblNewLabel_1_1_2.setForeground(new Color(191, 0, 0));
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		trang_thai_combo = new JComboBox();
		trang_thai_combo.setModel(new DefaultComboBoxModel(new String[] {"Đang xử lý", "Hủy", "Đang giao", "Hoàn thành"}));
		trang_thai_combo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
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
							.addComponent(dia_chi_lbl, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(id_nhan_vien_combo, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(loai_don_combo, 0, 183, Short.MAX_VALUE))
								.addComponent(Id_don_hang_txt, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))
							.addGap(175)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(so_nhan_mon_lbl, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(ten_lbl, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
								.addComponent(soDienThoaiLbl, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(soDienThoaiTxt, 210, 210, 210)
						.addComponent(tenTxt, 210, 210, 210)
						.addComponent(soNhanMonTxt, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
						.addComponent(diaChiTxt, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
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
								.addComponent(ten_lbl, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(soDienThoaiTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(soDienThoaiLbl, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(diaChiTxt, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(dia_chi_lbl, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
								.addComponent(lblNewLabel_2)
								.addComponent(Id_don_hang_txt, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
								.addComponent(so_nhan_mon_lbl, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
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
		hienThiSuaDonHang();
	}
	public String[] layMaNhanVienBanHang() {
		ArrayList<NHANVIEN> ds_nv = new DAONhanVien().layNhanVienBanHang();
		String[] ma_nv_bh = new String[ds_nv.size()];
		for(int i = 0; i< ds_nv.size();i++) {
			ma_nv_bh[i] = ds_nv.get(i).getId();
		}
		return ma_nv_bh;
	}
	public void hienThiSuaDonHang() {
		Id_don_hang_txt.setText(t1.getId_don_hang());
		id_nhan_vien_combo.setSelectedItem(t1.getId_nhan_vien());
		loai_don_combo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				hienThiTaiCho_MangDi();
			}
		});
		if(t1.getTen()!=null)
			loai_don_combo.setSelectedItem("Mang đi");
		else
			loai_don_combo.setSelectedItem("Tại chỗ");
		trang_thai_combo.setSelectedItem(t1.getTrang_thai());
		
		soNhanMonTxt.setText(t1.getSo_dat_mon()+"");
		tenTxt.setText(t1.getTen());
		soDienThoaiTxt.setText(t1.getSo_dien_thoai());
		diaChiTxt.setText(t1.getDia_chi());
	}
	public void hienThiTaiCho_MangDi() {
		if(loai_don_combo.getSelectedItem().toString().equalsIgnoreCase("Tại chỗ")) {
			ten_lbl.setVisible(false);
			tenTxt.setVisible(false);
			soDienThoaiLbl.setVisible(false);
			soDienThoaiTxt.setVisible(false);
			dia_chi_lbl.setVisible(false);
			diaChiTxt.setVisible(false);
			
			so_nhan_mon_lbl.setVisible(true);
			soNhanMonTxt.setVisible(true);
			
		}else {
			so_nhan_mon_lbl.setVisible(false);
			soNhanMonTxt.setVisible(false);
			
			ten_lbl.setVisible(true);
			tenTxt.setVisible(true);
			soDienThoaiLbl.setVisible(true);
			soDienThoaiTxt.setVisible(true);
			dia_chi_lbl.setVisible(true);
			diaChiTxt.setVisible(true);
		}
	}
	public void suaDonHang() {
		int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn sửa","Cảnh báo",JOptionPane.CANCEL_OPTION);
		if(confirm != 0) {
			return ;
		}
		String ma_nv = id_nhan_vien_combo.getSelectedItem().toString();
		Date ngay_dat = t1.getNgay_dat_hang();
		double tong_tien = t1.getTong_tien();
		int so_dat_mon = 0;
		String ten = null;
		String so_dien_thoai = null;
		String dia_chi = null;
		if(loai_don_combo.getSelectedItem().toString().equalsIgnoreCase("Tại chỗ")){
			try {
				so_dat_mon = Integer.parseInt(soNhanMonTxt.getText());
			}catch (Exception e) {
				JOptionPane.showConfirmDialog(null, "Vui lòng nhập số vào ô số đặt món","Cảnh báo",JOptionPane.CANCEL_OPTION);
				return ;
			}
			ten = "";
			so_dien_thoai = "";
			dia_chi = "";
			
		}else {
			ten = tenTxt.getText();
			try {
				int SDT_test = Integer.parseInt(soDienThoaiTxt.getText());
			}catch (Exception e) {
				JOptionPane.showConfirmDialog(null, "Vui lòng nhập số số điện thoại hợp lệ","Cảnh báo",JOptionPane.CANCEL_OPTION);
				return;
			}
			so_dien_thoai = soDienThoaiTxt.getText();
			dia_chi = diaChiTxt.getText();
			so_dat_mon = 0;
		}
		String trang_thai = trang_thai_combo.getSelectedItem().toString();
		
		
		DONHANG don_hang = new DONHANG(t1.getId_don_hang(), ma_nv, ngay_dat, tong_tien, ten, so_dien_thoai, dia_chi, so_dat_mon, trang_thai);
		try {
			new DAODonHang().update(don_hang);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Sửa đơn hàng thất bại");
			return ;
		}
		JOptionPane.showMessageDialog(null, "Chỉnh sửa thành công");
		this.setVisible(false);
		ord.btnNewButton_2.doClick();
	}
}
