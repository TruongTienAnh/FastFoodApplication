package DAO;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.CHITIET_NL_MA;
import DTO.NGUYENLIEU;

public class DAOChiTiet_NL_MA implements DAOObject{

	@Override
	public boolean insert(Object t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Object t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList selectByIdMonAn(String id_mon_an) {
		ArrayList<CHITIET_NL_MA> ds_ct_nl_ma = new ArrayList<CHITIET_NL_MA>();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement sta =  con.createStatement();
			String squery = "Select * from ChiTiet_NL_MA \n"
							+"where IDMONAN = '"+id_mon_an+"'";
			
			ResultSet rss = sta.executeQuery(squery);
			
			while(rss.next()) {
				String id_nguyen_lieu = rss.getString("IDNGUYENLIEU");
				String id_Mon_an = rss.getString("IDMONAN");
				int soluong = rss.getInt("SOLUONG");
				
				CHITIET_NL_MA ct_nl_ma = new CHITIET_NL_MA(id_nguyen_lieu, id_Mon_an, soluong);
				ds_ct_nl_ma.add(ct_nl_ma);
			}
			sta.executeQuery(squery);
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ds_ct_nl_ma;
	}
	public ArrayList<CHITIET_NL_MA> selectAll2(){
		ArrayList<CHITIET_NL_MA> ds_ct_nl_ma = new ArrayList<CHITIET_NL_MA>();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement sta =  con.createStatement();
			String squery = "Select * from ChiTiet_NL_MA \n";		
			
			ResultSet rss = sta.executeQuery(squery);
			
			while(rss.next()) {
				String id_nguyen_lieu = rss.getString("IDNGUYENLIEU");
				String id_Mon_an = rss.getString("IDMONAN");
				int soluong = rss.getInt("SOLUONG");
				
				CHITIET_NL_MA ct_nl_ma = new CHITIET_NL_MA(id_nguyen_lieu, id_Mon_an, soluong);
				ds_ct_nl_ma.add(ct_nl_ma);
			}
			sta.executeQuery(squery);
			con.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ds_ct_nl_ma;
	}
}
