package BUS;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

import DAO.DAONhanVien;
import DAO.DAOTaiKhoan;
import DAO.JDBCUtil;
import DTO.NHANVIEN;
import DTO.TAIKHOAN;
import DTO.TAIKHOAN;
import GUI.EnployeeView;
import test.Employee;

public class TaiKhoanBUS {
	private EnployeeView enployeeView;
	private EnployeeView.ThemTaiKhoan themTaiKhoan;
	private EnployeeView.SuaTaiKhoan suaTaiKhoan;
	public TaiKhoanBUS(EnployeeView enployeeView) {
		this.enployeeView = enployeeView;
	}
	public TaiKhoanBUS(EnployeeView.ThemTaiKhoan themTaiKhoan) {
		this.themTaiKhoan = themTaiKhoan;
	}
	public TaiKhoanBUS(EnployeeView.SuaTaiKhoan suaTaiKhoan) {
		this.suaTaiKhoan = suaTaiKhoan;
	}
	public void renderTaiKhoanTable() {
		DAOTaiKhoan taikhoanDAO = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> dsTaiKhoan = taikhoanDAO.getTaiKhoan();
		this.enployeeView.taiKhoanModel.setRowCount(0);
		for (TAIKHOAN taikhoan : dsTaiKhoan) {
			String id_tai_khoan = taikhoan.getId_tai_khoan();
		    String id_nhan_vien = taikhoan.getId_nhan_vien();
		    String ten_dang_nhap = taikhoan.getTen_dang_nhap();
		    String mat_khau = taikhoan.getMat_khau();
		    String tinh_trang = taikhoan.getTinh_trang();
		    String tt_xoa = taikhoan.getTt_xoa();
			Object[] row = new Object[] {id_tai_khoan, id_nhan_vien, ten_dang_nhap, mat_khau, tinh_trang, tt_xoa};
			if(tt_xoa.trim().equals("0") && id_nhan_vien.matches("NV\\d{3}\\s*")) { 
				this.enployeeView.taiKhoanModel.addRow(row);
			}
		}
	}
	public void them() {
		if (checkValidate(this.themTaiKhoan.tenTaiKhoanField.getText())) {
			TAIKHOAN tk = new TAIKHOAN(this.themTaiKhoan.maTaiKhoanField.getText(), this.themTaiKhoan.comboBox.getSelectedItem().toString(), this.themTaiKhoan.tenTaiKhoanField.getText(), this.themTaiKhoan.matKhauField.getText(), "active");
			DAOTaiKhoan in = new DAOTaiKhoan();
			in.update(tk);
			this.themTaiKhoan.setVisible(false);;
		}else
		System.out.println("tài khoản chưa được thêm vào");
	}
	public boolean checkValidate(String tentk) {
		DAOTaiKhoan tk = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> dstk = tk.getTaiKhoan();
		for (TAIKHOAN taikhoan : dstk) {
			 if(this.themTaiKhoan.comboBox.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "vui lòng chọn ID");
				return false;
			}else if (taikhoan.getTen_dang_nhap().equals(tentk)) {
					JOptionPane.showMessageDialog(null, "tài khoản đã tồn tại");
					return false;
			}else if(this.themTaiKhoan.tenTaiKhoanField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "vui lòng nhập tên tài khoản");
				return false;
			}else if(this.themTaiKhoan.matKhauField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "vui lòng nhập mật khẩu");
				return false;
			}
		}
		return true;
	}
	public String[] getIdNhanVien() {
		DAOTaiKhoan tkList = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> idnhanvien =  tkList.getTaiKhoan();
		int count = 0;
		for (TAIKHOAN tk : idnhanvien) {
	        if (tk.getTen_dang_nhap().equals("") && tk.getMat_khau().equals("") && tk.getTt_xoa().equals("0")) {
	            count++;
	        }
	    }
		String[] idNhanVienList = new String[count];;
	    int index = 0;
	    for (TAIKHOAN TK : idnhanvien) {
	        if (TK.getTen_dang_nhap().equals("") && TK.getMat_khau().equals("") && TK.getTt_xoa().equals("0")) {
	        	idNhanVienList[index] = TK.getId_nhan_vien();
	            index++;
	        }
	    }
	    return idNhanVienList;
	}
	public void upLoadById(String item) {
		DAOTaiKhoan get = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> dsTk = get.getTaiKhoan();
        for (TAIKHOAN taikhoan : dsTk) {
			if(item.equals(taikhoan.getId_nhan_vien()) && taikhoan.getTen_dang_nhap().equals("") && taikhoan.getMat_khau().equals("") ) {
				this.themTaiKhoan.maTaiKhoanField.setText(taikhoan.getId_tai_khoan());
				this.themTaiKhoan.tenTaiKhoanField.setText(taikhoan.getTen_dang_nhap());
				this.themTaiKhoan.matKhauField.setText(taikhoan.getMat_khau());
			}
		}
	}
	public void upLoadByID(String item) {
		DAOTaiKhoan get = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> dsTk = get.getTaiKhoan();
        for (TAIKHOAN taikhoan : dsTk) {
			if(item.equals(taikhoan.getId_nhan_vien())) {
				this.suaTaiKhoan.maTaiKhoanField.setText(taikhoan.getId_tai_khoan());
				this.suaTaiKhoan.tenTaiKhoanField.setText(taikhoan.getTen_dang_nhap());
				this.suaTaiKhoan.matKhauField.setText(taikhoan.getMat_khau());
				this.suaTaiKhoan.tinhTrangBox.setSelectedItem(taikhoan.getTinh_trang());
			}
		}
	}
	public String[] getIdNhanVienToUpdate() {
		DAOTaiKhoan tkList = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> idnhanvien =  tkList.getTaiKhoan();
		int count = 0;
		for (TAIKHOAN tk : idnhanvien) {
	        if (tk.getTt_xoa().trim().equals("0")) {
	            count++;
	        }
	    }
		String[] idNhanVienList = new String[count];;
	    int index = 0;
	    for (TAIKHOAN TK : idnhanvien) {
	        if (TK.getTt_xoa().trim().equals("0")) {
	        	idNhanVienList[index] = TK.getId_nhan_vien();
	            index++;
	        }
	    }
	    return idNhanVienList;
	}
	public void capNhat(String id) {
		DAOTaiKhoan capnhat = new DAOTaiKhoan();
		DAOTaiKhoan getTK = new DAOTaiKhoan();
		TAIKHOAN TK = getTK.getTaiKhoanByIDNhanVien(id);
		if(checkValidateForUpdate(this.suaTaiKhoan.tenTaiKhoanField.getText()))
			{
				TK.setTen_dang_nhap(this.suaTaiKhoan.tenTaiKhoanField.getText());
				TK.setMat_khau(this.suaTaiKhoan.matKhauField.getText());
				TK.setTinh_trang((String) this.suaTaiKhoan.tinhTrangBox.getSelectedItem());
				capnhat.update(TK);
				this.suaTaiKhoan.setVisible(false);
			}
	}
	private boolean checkValidateForUpdate(String tentk) {
		DAOTaiKhoan tk = new DAOTaiKhoan();
		ArrayList<TAIKHOAN> dstk = tk.getTaiKhoan();
		for (TAIKHOAN taikhoan : dstk) {
			 if(this.suaTaiKhoan.comboBox.getSelectedItem() == null) {
				JOptionPane.showMessageDialog(null, "vui lòng chọn ID");
				return false;
			}else if (taikhoan.getTen_dang_nhap().equals(tentk) && !taikhoan.getId_nhan_vien().equals((String)this.suaTaiKhoan.comboBox.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "tài khoản đã tồn tại");
					return false;
			}else if(this.suaTaiKhoan.tenTaiKhoanField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "vui lòng nhập tên tài khoản");
				return false;
			}else if(this.suaTaiKhoan.matKhauField.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "vui lòng nhập mật khẩu");
				return false;
			}
		}
		return true;
	}
	public void xoa(String id) {
		DAOTaiKhoan xoa = new DAOTaiKhoan();
		xoa.delete(id);
	}
	public void timKiem() {
		String idnhanvien = this.enployeeView.timKiemTaiKhoanField.getText();
		String idtaikhoan = this.enployeeView.timKiemTaiKhoanField.getText();
		TAIKHOAN idnv = new DAOTaiKhoan().getTaiKhoanByIDNhanVien(idnhanvien);
		TAIKHOAN idtk = new DAOTaiKhoan().getTaiKhoanByIDTaiKhoan(idtaikhoan);
		System.out.println(idnv);
		System.out.println(idtk);
		if(this.enployeeView.timKiemTaiKhoanField.getText().equals("")) {
			renderTaiKhoanTable();
			System.out.println("done1");
		}else {
			System.out.println("done2");
			this.enployeeView.taiKhoanModel.setRowCount(0);
			if(idtk != null && idtk.getId_tai_khoan().matches("TKNV\\d{2}\\s*")) {
				System.out.println("done3");
				this.enployeeView.taiKhoanModel.addRow(new Object[] {idtk.getId_tai_khoan(), idtk.getId_nhan_vien(), idtk.getTen_dang_nhap(), idtk.getMat_khau(), idtk.getTinh_trang()});
			}else if(idnv != null && idnv.getId_nhan_vien().matches("NV\\d{3}\\s*")) {
				System.out.println("done4");
				this.enployeeView.taiKhoanModel.addRow(new Object[] {idnv.getId_tai_khoan(), idnv.getId_nhan_vien(), idnv.getTen_dang_nhap(), idnv.getMat_khau(), idnv.getTinh_trang()});
			}
		}
		this.enployeeView.timKiemTaiKhoanField.setText("");
	}
	public void sapxep(String selectedItem) {
		ArrayList<TAIKHOAN>	data = new DAOTaiKhoan().getTaiKhoan();
		ArrayList<TAIKHOAN> dstaikhoan = data;
		this.enployeeView.taiKhoanModel.setRowCount(0);
		if(selectedItem.equals("Trạng thái")) {
			Collections.sort(dstaikhoan, new Comparator<TAIKHOAN>() {
			    public int compare(TAIKHOAN tk1, TAIKHOAN tk2) {
			    	String tt1 = tk1.getTinh_trang();
			    	String tt2 = tk2.getTinh_trang();
					return tt1.compareTo(tt2);
			    }
			});
			for (TAIKHOAN tk : dstaikhoan) {
				String idtaikhoan = tk.getId_tai_khoan();
				String idnhanvien = tk.getId_nhan_vien();
				String tendangnhap = tk.getTen_dang_nhap();
				String matkhau = tk.getMat_khau();
				String tinhtrang = tk.getTinh_trang(); 
				String tt_xoa = tk.getTt_xoa();
				Object[] row = new Object[] {idtaikhoan, idnhanvien, tendangnhap, matkhau, tinhtrang};
				if(tt_xoa.equals("0") && idnhanvien.matches("NV\\d{3}\\s*")) {
					this.enployeeView.taiKhoanModel.addRow(row);
				}
			}
	}
}
}
