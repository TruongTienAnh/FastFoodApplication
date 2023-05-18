package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.MONAN;
import DTO.NHANVIEN;
import DTO.TAIKHOAN;

public class DAOTaiKhoan implements DAOObject<TAIKHOAN> {

	@Override
	public boolean insert(TAIKHOAN t) {
		Connection con = JDBCUtil.getConnection();
		try {
		    String id_tai_khoan = t.getId_tai_khoan();
		    String id_nhan_vien = t.getId_nhan_vien();
		    String ten_dang_nhap = t.getTen_dang_nhap();
		    String mat_khau = t.getMat_khau();
		    String tinh_trang = t.getTinh_trang();
		    String tt_xoa = t.getTt_xoa();
		    PreparedStatement psmtThem = con.prepareStatement("INSERT INTO TAIKHOAN (IDTAIKHOAN, IDNHANVIEN, TENDANGNHAP, MATKHAU, TINHTRANG, TT_XOA) VALUES (?, ?, ?, ?, ?, ?)");
		    psmtThem.setNString(1, id_tai_khoan);
		    psmtThem.setNString(2, id_nhan_vien);
		    psmtThem.setNString(3, ten_dang_nhap);
		    psmtThem.setNString(4, mat_khau);
		    psmtThem.setNString(5, tinh_trang);
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
	public boolean update(TAIKHOAN t) {
		Connection con = JDBCUtil.getConnection();
		try {
			
			String id_tai_khoan = t.getId_tai_khoan();
			String id_nhan_vien = t.getId_nhan_vien();
			String ten_dang_nhap = t.getTen_dang_nhap();
			String mat_khau = t.getMat_khau();
			String tinh_trang = t.getTinh_trang();
			PreparedStatement psmtThem = con.prepareStatement("UPDATE TAIKHOAN SET IDTAIKHOAN = ?, TENDANGNHAP = ?, MATKHAU = ?, TINHTRANG = ? WHERE IDNHANVIEN = ?");
			psmtThem.setNString(1, id_tai_khoan);
			psmtThem.setNString(2, ten_dang_nhap);
		    psmtThem.setNString(3, mat_khau);
		    psmtThem.setNString(4, tinh_trang);;
		    psmtThem.setNString(5, id_nhan_vien);;
			psmtThem.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean delete(TAIKHOAN t) {
		Connection con = JDBCUtil.getConnection();
		String id = t.getId_nhan_vien();
		try {
			PreparedStatement pstm = con.prepareStatement("UPDATE TAIKHOAN SET TT_XOA = 1 WHERE IDNHANVIEN = ?");
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
			PreparedStatement pstm = con.prepareStatement("UPDATE TAIKHOAN SET TT_XOA = 1 WHERE IDNHANVIEN = ?");
			pstm.setNString(1, id);
			pstm.executeUpdate();
			con.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public ArrayList<TAIKHOAN> selectAll() {
		ArrayList<TAIKHOAN> ds_tk = new ArrayList<TAIKHOAN>();
		Connection con = JDBCUtil.getConnection();
		
		 try {
			Statement statement = con.createStatement();
			
			String query = "select * from TAIKHOAN where TT_XOA = '0'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String id_tai_khoan = rss.getString("IDTAIKHOAN");
				String id_nhan_vien = rss.getString("IDNHANVIEN");
				String ten_dang_nhap = rss.getString("TENDANGNHAP");
				String mat_khau = rss.getString("MATKHAU");
				String tinh_trang = rss.getString("TINHTRANG");
				
				TAIKHOAN tai_khoan = new TAIKHOAN(id_tai_khoan, id_nhan_vien, ten_dang_nhap, mat_khau, tinh_trang);
				ds_tk.add(tai_khoan);
			}
			
			con.close();
			return ds_tk;
		 } catch (Exception e) {
			 e.printStackTrace();
			 return null;
		 }
	}
	public ArrayList<TAIKHOAN> getTaiKhoan(){
		ArrayList<TAIKHOAN> taikhoan = new ArrayList<TAIKHOAN>();
		Connection con = JDBCUtil.getConnection();
		try {
			PreparedStatement pstm = con.prepareStatement("SELECT * FROM TAIKHOAN");
			ResultSet rs =  pstm.executeQuery();
			while(rs.next()) {
				
				String id_tai_khoan = rs.getString("IDTAIKHOAN");
				String id_nhan_vien = rs.getString("IDNHANVIEN");
				String ten_dang_nhap = rs.getString("TENDANGNHAP");
				String mat_khau = rs.getString("MATKHAU");
				String tinh_trang = rs.getString("TINHTRANG");
				String tt_xoa = rs.getNString("TT_XOA");
				TAIKHOAN tk = new TAIKHOAN(id_tai_khoan, id_nhan_vien, ten_dang_nhap, mat_khau, tinh_trang, tt_xoa);
				taikhoan.add(tk);
			}
				con.close();
				return taikhoan;
			}catch(Exception e) {
			 e.printStackTrace();
			 return null;
			}
	}
	public TAIKHOAN getTaiKhoanByIDNhanVien(String id_nv) {
		TAIKHOAN taikhoan = null;
		Connection con = JDBCUtil.getConnection();
		
		 try {
			
			PreparedStatement pstm = con.prepareStatement("SELECT *FROM TAIKHOAN WHERE IDNHANVIEN = ?");
			pstm.setNString(1, id_nv);
			ResultSet rss =  pstm.executeQuery();
			while(rss.next()) {
				String idtaikhoan = rss.getString("IDTAIKHOAN");
				String idnhanvien = rss.getString("IDNHANVIEN");
				String tendangnhap = rss.getString("TENDANGNHAP");
				String matkhau = rss.getString("MATKHAU");
				String tinhtrang = rss.getString("TINHTRANG");
				String tt_xoa = rss.getNString("TT_XOA");
				taikhoan = new TAIKHOAN(idtaikhoan, idnhanvien, tendangnhap, matkhau, tinhtrang, tt_xoa);
			}
			
			con.close();
			return taikhoan;
		 } catch (Exception e) {
			 e.printStackTrace();// TODO: handle exception
			 return null;
		 }
	}
	public TAIKHOAN getTaiKhoanByIDTaiKhoan(String id_tk) {
		TAIKHOAN taikhoan = null;
		Connection con = JDBCUtil.getConnection();
		
		 try {
			
			PreparedStatement pstm = con.prepareStatement("SELECT *FROM TAIKHOAN WHERE IDTAIKHOAN = ?");
			pstm.setNString(1, id_tk);
			ResultSet rss =  pstm.executeQuery();
			while(rss.next()) {
				String idtaikhoan = rss.getString("IDTAIKHOAN");
				String idnhanvien = rss.getString("IDNHANVIEN");
				String tendangnhap = rss.getString("TENDANGNHAP");
				String matkhau = rss.getString("MATKHAU");
				String tinhtrang = rss.getString("TINHTRANG");
				String tt_xoa = rss.getNString("TT_XOA");
				taikhoan = new TAIKHOAN(idtaikhoan, idnhanvien, tendangnhap, matkhau, tinhtrang, tt_xoa);
			}
			
			con.close();
			return taikhoan;
		 } catch (Exception e) {
			 e.printStackTrace();// TODO: handle exception
			 return null;
		 }
	}
	@Override
	public ArrayList<TAIKHOAN> selectByCondition(String condition) {

		return null;
	}
	public int countTaiKhoan() {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmCount;
		try {
			pstmCount = con.prepareStatement("SELECT COUNT(*) FROM TAIKHOAN WHERE IDTAIKHOAN LIKE 'TKNV%';");
            ResultSet rsCount = pstmCount.executeQuery();
            rsCount.next();
            int count = rsCount.getInt(1);
            return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public int countTaiKhoanAD() {
		Connection con = JDBCUtil.getConnection();
		PreparedStatement pstmCount;
		try {
			pstmCount = con.prepareStatement("SELECT COUNT(*) FROM TAIKHOAN WHERE IDTAIKHOAN LIKE 'TKAD%';");
            ResultSet rsCount = pstmCount.executeQuery();
            rsCount.next();
            int count = rsCount.getInt(1);
            return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
	public ArrayList<TAIKHOAN> getTaiKhoanByTenDangNhap(String tentaikhoan) {
		ArrayList<TAIKHOAN> danhsachtaikhoan = new ArrayList<>();
		Connection con = JDBCUtil.getConnection();
		 try {
			
			PreparedStatement pstm = con.prepareStatement("SELECT *FROM TAIKHOAN WHERE TENDANGNHAP = ?");
			pstm.setNString(1, tentaikhoan);
			ResultSet rss =  pstm.executeQuery();
			while(rss.next()) {
				String idtaikhoan = rss.getString("IDTAIKHOAN");
				String idnhanvien = rss.getString("IDNHANVIEN");
				String tendangnhap = rss.getString("TENDANGNHAP");
				String matkhau = rss.getString("MATKHAU");
				String tinhtrang = rss.getString("TINHTRANG");
				String tt_xoa = rss.getNString("TT_XOA");
				TAIKHOAN tk = new TAIKHOAN(idtaikhoan, idnhanvien, tendangnhap, matkhau, tinhtrang, tt_xoa);
				danhsachtaikhoan.add(tk);
			}
			con.close();
			return danhsachtaikhoan;
		 } catch (Exception e) {
			 e.printStackTrace();// TODO: handle exception
			 return null;
		 }
	}
}
