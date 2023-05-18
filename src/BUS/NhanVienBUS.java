package BUS;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import DAO.DAONhanVien;
import DAO.DAOTaiKhoan;
import DAO.JDBCUtil;
import DTO.NHANVIEN;
import DTO.TAIKHOAN;
import GUI.EnployeeView;
import test.Employee;

public class NhanVienBUS {
	private EnployeeView enployeeView;
	private EnployeeView.AddEmployee addEmployee;
	private EnployeeView.ConfigEmployee configEmployee;
	public NhanVienBUS(EnployeeView.AddEmployee addEmployee) {
		this.addEmployee = addEmployee;
	}
	public NhanVienBUS(EnployeeView.ConfigEmployee configEmployee) {
		this.configEmployee = configEmployee;
	}
	public NhanVienBUS(EnployeeView enployeeView) {
		this.enployeeView = enployeeView;
	}
	public void them(String so_dien_thoai, String ho_ten, String chuc_vu, String vi_tri_lam_viec) {
	    if (checkValidate()) {
	        Connection con = JDBCUtil.getConnection();
	        String id = "";
	        String id_tai_khoan = "";
	        try {
	            int countNV = new DAONhanVien().countNhanVien();
	            int countAD = new DAONhanVien().countAdmin();
	            int countTK = new DAOTaiKhoan().countTaiKhoan();
	            int countTKAD = new DAOTaiKhoan().countTaiKhoanAD();
	            id = "NV" + String.format("%03d", countNV + 1);
	            id_tai_khoan = "TKNV" + String.format("%02d", countTK + 1);
	            if(chuc_vu.equals("ADMIN")) {
	            	id = "AD" + String.format("%03d", countAD + 1);
	            	id_tai_khoan = "TKAD" + String.format("%02d", countTKAD + 1);
	            }
	            NHANVIEN nv = new NHANVIEN(id, chuc_vu,  ho_ten,  so_dien_thoai,  vi_tri_lam_viec, "0");
	            DAONhanVien insert = new DAONhanVien();
	            insert.insert(nv);
	            
	            if(!nv.getChuc_vu().equals("Bao ve"))
	            {   
		            TAIKHOAN tk = new TAIKHOAN(id_tai_khoan, id, "", "", "not active", "0");
		            DAOTaiKhoan insert2 = new DAOTaiKhoan();
		            insert2.insert(tk);
	            }
	            con.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        this.addEmployee.setVisible(false);
	    }
	}


	public void renderNhanVienTable() {
		DAONhanVien nhanVienDAO = new DAONhanVien();
		ArrayList<NHANVIEN> dsNhanVien = nhanVienDAO.getNhanVien();
		System.err.println(dsNhanVien.size());
		this.enployeeView.employeeModel.setRowCount(0);
		for (NHANVIEN nhanvien : dsNhanVien) {
			String id = nhanvien.getId();
			String chuc_vu = nhanvien.getChuc_vu();
			String ho_ten = nhanvien.getHo_ten();
			String so_dien_thoai = nhanvien.getSo_dien_thoai();
			String vi_tri_lam_viec = nhanvien.getVi_tri_lam_viec(); 
			String tt_xoa = nhanvien.getTt_xoa();
			Object[] row = new Object[] {id, ho_ten, chuc_vu, so_dien_thoai, vi_tri_lam_viec};
			if(tt_xoa.trim().equals("0") && id.matches("^NV\\d{3}$*")) {
				this.enployeeView.employeeModel.addRow(row);
			}
		}
	}
	public boolean checkValidate() {
		if (this.addEmployee.hoTenField.getText().equals("") || this.addEmployee.soDienThoaiField.getText().equals("") || this.addEmployee.viTriLamViecField.getText().equals("")) {
		    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin.");
		    return false;
		}else if(!this.addEmployee.soDienThoaiField.getText().matches("\\d{10}")) {
			JOptionPane.showMessageDialog(null, "Vui lòng nhập lại 'Số điện thoại không hợp lệ'");
			return false;
		}
		return true;
	}
	public void xoa(String id) {
		DAONhanVien xoa = new DAONhanVien();
		xoa.delete(id);
	}
	public String[] getIdList() {
	    DAONhanVien dao = new DAONhanVien();
	    int count = 0;
	    ArrayList<NHANVIEN> dsNhanVien = dao.getNhanVien();
	    for (NHANVIEN nv : dsNhanVien) {
	        if (nv.getId().matches("NV\\d{3}\\s*")) {
	            count++;
	        }
	    }
	    String[] idList = new String[count];
	    int index = 0;
	    for (NHANVIEN nv : dsNhanVien) {
	        if (nv.getId().matches("NV\\d{3}\\s*")) {
	            idList[index] = nv.getId();
	            index++;
	        }
	    }
	    return idList;
	}
	public void upLoadByID(String item) {
		DAONhanVien get = new DAONhanVien();
		ArrayList<NHANVIEN> dsNhanVien = get.getNhanVien();
        for (NHANVIEN nhanvien : dsNhanVien) {
			if(item.equals(nhanvien.getId())) {
				this.configEmployee.hoTenField.setText(nhanvien.getHo_ten());
				this.configEmployee.chucVuField.setSelectedItem(nhanvien.getChuc_vu());
				this.configEmployee.viTriLamViecField.setText(nhanvien.getVi_tri_lam_viec());
			}
		}
	}
	public void capNhat(String id) {
		DAONhanVien capnhat = new DAONhanVien();
		DAONhanVien getNv = new DAONhanVien();
		NHANVIEN nv = getNv.getNhanVienByID(id);
		nv.setHo_ten(this.configEmployee.hoTenField.getText());
		nv.setChuc_vu((String)this.configEmployee.chucVuField.getSelectedItem());
		nv.setVi_tri_lam_viec(this.configEmployee.viTriLamViecField.getText());
		capnhat.update(nv);
		this.configEmployee.setVisible(false);
	}
	public void timKiem() {
		String id = this.enployeeView.timKiemField.getText();
		String name = this.enployeeView.timKiemField.getText();
		NHANVIEN nv = new DAONhanVien().getNhanVienByID(id);
		ArrayList<NHANVIEN> dsNv = new DAONhanVien().getNhanVienByName(name);
		
		if(this.enployeeView.timKiemField.getText().equals("")) {
			renderNhanVienTable();
		}else {
			this.enployeeView.employeeModel.setRowCount(0);
			if(nv != null && nv.getId().matches("NV\\d{3}\\s*")) {
				this.enployeeView.employeeModel.addRow(new Object[] {nv.getId(), nv.getHo_ten(), nv.getChuc_vu(), nv.getVi_tri_lam_viec()});
			}else if(!dsNv.isEmpty()) {			
				for (NHANVIEN nhanvien : dsNv) {
					if(!nhanvien.getId().matches("AD\\d{3}\\s*$"))
					this.enployeeView.employeeModel.addRow(new Object[] {nhanvien.getId(), nhanvien.getHo_ten(), nhanvien.getChuc_vu(), nhanvien.getVi_tri_lam_viec()});
				}
			}
		}
		this.enployeeView.timKiemField.setText("");
	}
	public void sortBy(String selectedItem) {
		ArrayList<NHANVIEN>	data = new DAONhanVien().getNhanVien();
		ArrayList<NHANVIEN> dsNhanVien = data;
		this.enployeeView.employeeModel.setRowCount(0);
		if(selectedItem.equals("tên(A-Z)")) {
				Collections.sort(data, new Comparator<NHANVIEN>() {
			    public int compare(NHANVIEN nv1, NHANVIEN nv2) {
			    	String ten1 = nv1.getHo_ten().split(" ")[nv1.getHo_ten().split(" ").length - 1];
			    	String ten2 = nv2.getHo_ten().split(" ")[nv1.getHo_ten().split(" ").length - 1];
					if (ten1.compareTo(ten2) == 0) {
					    String hoDem1 = nv1.getHo_ten().split(" ")[nv1.getHo_ten().split(" ").length - 2];
					    String hoDem2 = nv2.getHo_ten().split(" ")[nv2.getHo_ten().split(" ").length - 2];
					    return hoDem1.compareTo(hoDem2);
					}
					return ten1.compareTo(ten2);
			    }
			});
			for (NHANVIEN nhanvien : dsNhanVien) {
				String id = nhanvien.getId();
				String chuc_vu = nhanvien.getChuc_vu();
				String ho_ten = nhanvien.getHo_ten();
				String so_dien_thoai = nhanvien.getSo_dien_thoai();
				String vi_tri_lam_viec = nhanvien.getVi_tri_lam_viec(); 
				String tt_xoa = nhanvien.getTt_xoa();
				Object[] row = new Object[] {id, ho_ten, chuc_vu, so_dien_thoai, vi_tri_lam_viec};
				if(tt_xoa.equals("0") && id.matches("NV\\d{3}\\s*")) {
					this.enployeeView.employeeModel.addRow(row);
				}
			}
		}else if(selectedItem.equals("tên(Z-A)")) {
			Collections.sort(dsNhanVien, new Comparator<NHANVIEN>() {
			    public int compare(NHANVIEN nv1, NHANVIEN nv2) {
			    	String ten1 = nv1.getHo_ten().split(" ")[nv1.getHo_ten().split(" ").length - 1];
			    	String ten2 = nv2.getHo_ten().split(" ")[nv1.getHo_ten().split(" ").length - 1];
					return ten2.compareTo(ten1);
			    }
			});
			for (NHANVIEN nhanvien : dsNhanVien) {
				String id = nhanvien.getId();
				String chuc_vu = nhanvien.getChuc_vu();
				String ho_ten = nhanvien.getHo_ten();
				String so_dien_thoai = nhanvien.getSo_dien_thoai();
				String vi_tri_lam_viec = nhanvien.getVi_tri_lam_viec(); 
				String tt_xoa = nhanvien.getTt_xoa();
				Object[] row = new Object[] {id, ho_ten, chuc_vu, so_dien_thoai, vi_tri_lam_viec};
				if(tt_xoa.trim().equals("0") && id.matches("NV\\d{3}\\s*")) {
					this.enployeeView.employeeModel.addRow(row);
				}
			}
		}else if(selectedItem.equals("ID")) {
			Collections.sort(dsNhanVien, new Comparator<NHANVIEN>() {
			    public int compare(NHANVIEN nv1, NHANVIEN nv2) {
			    	String id1 = nv1.getId();
			    	String id2 = nv2.getId();
					return id1.compareTo(id2);
			    }
			});
			for (NHANVIEN nhanvien : dsNhanVien) {
				String id = nhanvien.getId();
				String chuc_vu = nhanvien.getChuc_vu();
				String ho_ten = nhanvien.getHo_ten();
				String so_dien_thoai = nhanvien.getSo_dien_thoai();
				String vi_tri_lam_viec = nhanvien.getVi_tri_lam_viec(); 
				String tt_xoa = nhanvien.getTt_xoa();
				Object[] row = new Object[] {id, ho_ten, chuc_vu, so_dien_thoai, vi_tri_lam_viec};
				if(tt_xoa.trim().equals("0") && id.matches("NV\\d{3}\\s*")) {
					this.enployeeView.employeeModel.addRow(row);
				}
			}
		}
	}
}
