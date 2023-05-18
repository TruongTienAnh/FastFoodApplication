package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.CHITIET_NL_MA;
import DTO.MONAN_KHUYENMAI;

public class DAOChiTiet_MA_KM implements DAOObject<MONAN_KHUYENMAI> {

	@Override
	public boolean insert(MONAN_KHUYENMAI t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MONAN_KHUYENMAI t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(MONAN_KHUYENMAI t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MONAN_KHUYENMAI> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MONAN_KHUYENMAI> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<MONAN_KHUYENMAI> selectByIdKhuyenMai(String id_khuyen_mai){
		ArrayList<MONAN_KHUYENMAI> ds_ma_km = new ArrayList<MONAN_KHUYENMAI>();
		Connection con = JDBCUtil.getConnection();
		String query = "Select * from MONAN_KHUYENMAI where IDKHUYENMAI = '"+id_khuyen_mai+"'";
		try {
			Statement sta = con.createStatement();
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				String id_mon_an = rss.getString("IDMONAN");
				int phan_tram_giam = rss.getInt("PHANTRAMGIAM");
				
				MONAN_KHUYENMAI ma_km = new MONAN_KHUYENMAI(id_mon_an, id_khuyen_mai, phan_tram_giam);
				ds_ma_km.add(ma_km);
			}
			con.close();
			return ds_ma_km;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
