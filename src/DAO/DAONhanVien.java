package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DTO.NHANVIEN;
import DTO.TAIKHOAN;

public class DAONhanVien implements DAOObject<NHANVIEN>{
	public int countNhanVien() {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmCount;
		try {
			pstmCount = con.prepareStatement("SELECT COUNT(*) FROM NHANVIEN WHERE IDNHANVIEN LIKE 'NV%';");
            ResultSet rsCount = pstmCount.executeQuery();
            rsCount.next();
            int count = rsCount.getInt(1);
            return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public boolean insert(NHANVIEN t) {
		Connection con = JDBCUtil.getConnection();
		try {
		    String id = t.getId();
			String chuc_vu = t.getChuc_vu();
			String ho_ten = t.getHo_ten();
			String so_dien_thoai = t.getSo_dien_thoai();
			String vi_tri_lam_viec = t.getVi_tri_lam_viec();
		    String tt_xoa = t.getTt_xoa();
			PreparedStatement psmtThem = con.prepareStatement("INSERT INTO NHANVIEN VALUES(?, ?, ?, ?, ?, ?)");
			psmtThem.setNString(1, id);
			psmtThem.setNString(2, chuc_vu);
			psmtThem.setNString(3, ho_ten);
			psmtThem.setNString(4, so_dien_thoai);
			psmtThem.setNString(5, vi_tri_lam_viec);
			psmtThem.setNString(6, tt_xoa);
			psmtThem.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean update(NHANVIEN t) {
		Connection con = JDBCUtil.getConnection();
		try {
			
		    String ho_ten = t.getHo_ten();
			String chuc_vu = t.getChuc_vu();
			String vi_tri_lam_viec = t.getVi_tri_lam_viec();
			String id = t.getId();
			PreparedStatement psmtThem = con.prepareStatement("UPDATE NHANVIEN SET HOTEN = ?, CHUCVU = ?, VITRILAMVIEC = ? WHERE IDNHANVIEN = ?");
			psmtThem.setNString(1, ho_ten);
			psmtThem.setNString(2, chuc_vu);
			psmtThem.setNString(3, vi_tri_lam_viec);
			psmtThem.setNString(4, id);
			psmtThem.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(NHANVIEN t) {
		Connection con = JDBCUtil.getConnection();
		String id = t.getId();
		try {
			PreparedStatement pstm = con.prepareStatement("UPDATE NHANVIEN SET TT_XOA = 1 WHERE IDNHANVIEN = ?");
			pstm.setNString(1, id);
			pstm.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public boolean delete(String t) {
		Connection con = JDBCUtil.getConnection();
		String id = t;
		try {
			PreparedStatement pstm = con.prepareStatement("UPDATE NHANVIEN SET TT_XOA = 1 WHERE IDNHANVIEN = ?");
			pstm.setNString(1, id);
			pstm.executeUpdate();
			PreparedStatement psmt = con.prepareStatement("UPDATE TAIKHOAN SET TT_XOA = 1 WHERE IDNHANVIEN = ?");
			psmt.setString(1, id);
			psmt.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public ArrayList<NHANVIEN> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<NHANVIEN> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<NHANVIEN> getNhanVien(){
		ArrayList<NHANVIEN> nhanVien = new ArrayList<NHANVIEN>();
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM NHANVIEN");
			ResultSet rs =  pstm.executeQuery();
			
			while(rs.next()) {
				
				String Id_nhan_vien = rs.getString("IDNHANVIEN");
				String chuc_vu = rs.getString("CHUCVU");
				String ho_ten = rs.getString("HOTEN");
				String so_dien_thoai = rs.getString("SODIENTHOAI");
				String vi_tri_lam_viec = rs.getString("VITRILAMVIEC");
				String tt_xoa = rs.getNString("TT_XOA");
				NHANVIEN nv = new NHANVIEN(Id_nhan_vien, chuc_vu, ho_ten, so_dien_thoai, vi_tri_lam_viec, tt_xoa);
				nhanVien.add(nv);
			}
			
				con.close();
				return nhanVien;
			}catch(Exception e) {
			 e.printStackTrace();
			 return null;
			}
		
	}
	public NHANVIEN getNhanVienByID(String id_nhan_vien) {
		NHANVIEN nhan_vien = null;
		Connection con = JDBCUtil.getConnection();
		
		 try {
			
			PreparedStatement pstm = con.prepareStatement("SELECT *FROM NHANVIEN WHERE IDNHANVIEN = ?");
			pstm.setNString(1, id_nhan_vien);
			ResultSet rss =  pstm.executeQuery();
			while(rss.next()) {
				String Id_nhan_vien = rss.getString("IDNHANVIEN");
				String chuc_vu = rss.getString("CHUCVU");
				String ho_ten = rss.getString("HOTEN");
				String so_dien_thoai = rss.getString("SODIENTHOAI");
				String vi_tri_lam_viec = rss.getString("VITRILAMVIEC");
				String tt_xoa = rss.getNString("TT_XOA");
				nhan_vien = new NHANVIEN(Id_nhan_vien, chuc_vu, ho_ten, so_dien_thoai, vi_tri_lam_viec, tt_xoa);
			}
			
			con.close();
			return nhan_vien;
		 } catch (Exception e) {
			 e.printStackTrace();// TODO: handle exception
			 return null;
		 }
	}
	public ArrayList<NHANVIEN> getNhanVienByName(String name) {
		ArrayList<NHANVIEN> dsNhanVien = new ArrayList<>();
		Connection con = JDBCUtil.getConnection();
		 try {
			
			PreparedStatement pstm = con.prepareStatement("SELECT *FROM NHANVIEN WHERE HOTEN = ?");
			pstm.setNString(1, name);
			ResultSet rss =  pstm.executeQuery();
			while(rss.next()) {
				String Id_nhan_vien = rss.getString("IDNHANVIEN");
				String chuc_vu = rss.getString("CHUCVU");
				String ho_ten = rss.getString("HOTEN");
				String so_dien_thoai = rss.getString("SODIENTHOAI");
				String vi_tri_lam_viec = rss.getString("VITRILAMVIEC");
				String tt_xoa = rss.getNString("TT_XOA");
				NHANVIEN nv = new NHANVIEN(Id_nhan_vien, chuc_vu, ho_ten, so_dien_thoai, vi_tri_lam_viec, tt_xoa);
				dsNhanVien.add(nv);
			}
			con.close();
			return dsNhanVien;
		 } catch (Exception e) {
			 e.printStackTrace();// TODO: handle exception
			 return null;
		 }
	}
	public ArrayList<NHANVIEN> layNhanVienBanHang() {
		ArrayList<NHANVIEN> ds_nv = new ArrayList<NHANVIEN>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement statement = con.createStatement();
			String query = "Select * from NHANVIEN where CHUCVU like '%Ban Hang%' or CHUCVU like '%ADMIN%'";
			ResultSet rss= statement.executeQuery(query);
			while(rss.next()) {
				String Id_nhan_vien = rss.getString("IDNHANVIEN");
				String chuc_vu = rss.getString("CHUCVU");
				String ho_ten = rss.getString("HOTEN");
				String so_dien_thoai = rss.getString("SODIENTHOAI");
				String vi_tri_lam_viec = rss.getString("VITRILAMVIEC");
				
				NHANVIEN nhan_vien = new NHANVIEN(Id_nhan_vien, chuc_vu, ho_ten, so_dien_thoai, vi_tri_lam_viec);
				ds_nv.add(nhan_vien);
			}
			con.close();
			return ds_nv;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public int countAdmin() {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmCount;
		try {
			pstmCount = con.prepareStatement("SELECT COUNT(*) FROM NHANVIEN WHERE IDNHANVIEN LIKE 'AD%';");
            ResultSet rsCount = pstmCount.executeQuery();
            rsCount.next();
            int count = rsCount.getInt(1);
            return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
