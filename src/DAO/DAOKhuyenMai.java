package DAO;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.KHUYENMAI;

public class DAOKhuyenMai implements DAOObject<KHUYENMAI> {

	@Override
	public boolean insert(KHUYENMAI t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(KHUYENMAI t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(KHUYENMAI t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<KHUYENMAI> selectAll() {
		ArrayList<KHUYENMAI> ds_KhuyenMai = new ArrayList<KHUYENMAI>();
		try {
			Connection con = JDBCUtil.getConnection();
			Statement sta = con.createStatement();
			String query = "Select * from KHUYENMAI where TT_XOA = '0' "
						+ "and NgayBatDau<= getDate() "
						+ "and NgayKetThuc>=getDate()";
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				String id_khuyen_mai = rss.getString("IDKHUYENMAI");
				String ten_khuyen_mai = rss.getString("TENKHUYENMAI");
				
				Date ngay_bat_dau_sql = rss.getDate("NGAYBATDAU");
				java.util.Date ngay_bat_dau = new java.util.Date();
				ngay_bat_dau.setYear(ngay_bat_dau_sql.getYear());
				ngay_bat_dau.setMonth(ngay_bat_dau_sql.getMonth());
				ngay_bat_dau.setDate(ngay_bat_dau_sql.getDate());
				
				
				Date ngay_ket_thuc_sql = rss.getDate("NGAYKETTHUC");
				java.util.Date ngay_ket_thuc= new java.util.Date();
				ngay_ket_thuc.setYear(ngay_ket_thuc_sql.getYear());
				ngay_ket_thuc.setMonth(ngay_bat_dau_sql.getMonth());
				ngay_ket_thuc.setDate(ngay_bat_dau_sql.getDate());
				
				
				String mota = rss.getString("MOTA");
				KHUYENMAI khuyen_mai = new KHUYENMAI(id_khuyen_mai, ten_khuyen_mai, ngay_bat_dau, ngay_ket_thuc, mota);
				ds_KhuyenMai.add(khuyen_mai);
			}
			
			con.close();
			return ds_KhuyenMai;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<KHUYENMAI> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
