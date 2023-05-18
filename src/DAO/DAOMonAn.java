package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import DTO.MONAN;

public class DAOMonAn implements DAOObject<MONAN>{

	@Override
	public boolean insert(MONAN t) {
		Connection con = JDBCUtil.getConnection();
		try {
			
			String query = "INSERT INTO MONAN values(?,?,?,?,?,?,'0')";
			PreparedStatement prepare_statement = con.prepareStatement(query);
			prepare_statement.setString(1,t.getId());
			prepare_statement.setString(2,t.getName());
			prepare_statement.setString(3,t.getType());
			prepare_statement.setString(4,t.getImage());
			prepare_statement.setDouble(5,t.getUnitPrice());
			prepare_statement.setString(6,t.getDecription());
			prepare_statement.executeUpdate();
			con.close();
			return true;
		}catch (Exception e) {
			System.out.println(e);
		}
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(MONAN t) {
		Connection con = JDBCUtil.getConnection();
		try {
			
			String query = "UPDATE MONAN \n"+
							"set TENMONAN = N'"+t.getName() +"',"+
							"LOAIMONAN = '"+t.getType()+"',"+
							"HINHANH = '"+t.getImage()+"',"+
							"GIABAN = "+t.getUnitPrice()+" ,"+
							"MOTA = N'"+t.getDecription()+"' "+
							"\nwhere IDMONAN = '"+t.getId()+"'";
			PreparedStatement pre_sta = con.prepareStatement(query);
			
			
			pre_sta.executeUpdate();
			
			con.close();
			return true;
		}catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}

	@Override
	public boolean delete(MONAN t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<MONAN> selectAll() {
		ArrayList<MONAN> rs = new ArrayList<MONAN>();
			
		Connection con = JDBCUtil.getConnection();
			
			 try {
				Statement statement = con.createStatement();
				
				String query = "select * from MONAN where TT_XOA ='0'";
				System.out.println(query);
				
				ResultSet rss =  statement.executeQuery(query);
				
				while(rss.next()) {
					String id = rss.getString("IDMONAN");
					String name = rss.getString("TENMONAN");
					String type = rss.getString("LOAIMONAN");
					String image = rss.getString("HINHANH");
					double unitPrice = rss.getDouble("GIABAN");
					String decripton = rss.getString("MOTA");
					
					MONAN food = new MONAN(id, name, type, image, unitPrice, decripton);
					rs.add(food);
				}
				
				con.close();
				return rs;
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return rs;
	}

	@Override
	public ArrayList<MONAN> selectByCondition(String condition) {
		ArrayList<MONAN> rs = new ArrayList<MONAN>();
		
		Connection con = JDBCUtil.getConnection();
			
			 try {
				Statement statement = con.createStatement();
				
				String query = "select * from MONAN \n"+
								"Where IDMONAN like '%"+condition+"%'"+
								" or TENMONAN like '%"+condition+"%' and TT_XOA = '0'";
				System.out.println(query);
				
				ResultSet rss =  statement.executeQuery(query);
				
				while(rss.next()) {
					String id = rss.getString("IDMONAN");
					String name = rss.getString("TENMONAN");
					String type = rss.getString("LOAIMONAN");
					String image = rss.getString("HINHANH");
					double unitPrice = rss.getDouble("GIABAN");
					String decripton = rss.getString("MOTA");
					
					MONAN food = new MONAN(id, name, type, image, unitPrice, decripton);
					rs.add(food);
				}
				
				con.close();
				return rs;
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return rs;
	}

}
