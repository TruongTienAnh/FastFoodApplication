package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.CHITIET_DONHANG;

public class DAOChiTiet_DH implements DAOObject<CHITIET_DONHANG> {

	@Override
	public boolean insert(CHITIET_DONHANG t) {
		Connection con = JDBCUtil.getConnection();
		try {
			String query = "INSERT INTO CHITIET_DONHANG values(?,?,?,?)";
			PreparedStatement sta = con.prepareStatement(query);
			sta.setString(1,t.getId_don_hang());
			sta.setString(2,t.getId_mon_an());
			sta.setDouble(3,t.getDon_gia());
			sta.setInt(4,t.getSo_luong());
			sta.execute();
			
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(CHITIET_DONHANG t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CHITIET_DONHANG t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<CHITIET_DONHANG> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<CHITIET_DONHANG> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public ArrayList<CHITIET_DONHANG> seclectByIdDonHang(String id_don_hang){
		Connection con = JDBCUtil.getConnection();
		ArrayList<CHITIET_DONHANG> ds_ct_dh = new ArrayList<>();
		try {
			Statement sta  = con.createStatement();
			String query = "Select from CHITIET_DONHANG where IDDONHANG = '"+id_don_hang+"'";
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				String id_mon_an = rss.getString("IDMONAN");
				double don_gia = rss.getDouble("GIA");
				int so_luong = rss.getInt("SOLUONG");
				
			}
	
			con.close();
			return ds_ct_dh;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
