package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.THONGKE;

public class DAOTimKiem {
	public ArrayList<THONGKE> selectAllDHByYear() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT YEAR(NGAYDATHANG) AS YEAR, SUM(TONGTIEN) AS TONGTIEN\r\n"
					+ "FROM DONHANG\r\n"
					+ "GROUP BY YEAR(NGAYDATHANG)\r\n"
					+ "ORDER BY YEAR";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "Năm " + String.valueOf(rss.getInt("YEAR"));
				double doanhThu = rss.getDouble("TONGTIEN");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				System.out.println(STT);
				System.out.println(ngay);
				System.out.println(doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<THONGKE> selectAllDHByMonth(int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT \r\n"
					+ "    YEAR(NGAYDATHANG) AS year, \r\n"
					+ "    MONTH(NGAYDATHANG) AS month, \r\n"
					+ "    SUM(TONGTIEN) AS revenue \r\n"
					+ "FROM \r\n"
					+ "    DONHANG\r\n"
					+ "WHERE \r\n"
					+ "    YEAR(NGAYDATHANG) = " + String.valueOf(year)
					+ " GROUP BY \r\n"
					+ "	YEAR(NGAYDATHANG),\r\n"
					+ "    MONTH(NGAYDATHANG)";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "Tháng " + String.valueOf(rss.getInt("month")) + "/" + String.valueOf(rss.getInt("year"));
				double doanhThu = rss.getDouble("revenue");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// theo mùa
	public ArrayList<THONGKE> selectAllDHBySeason(int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT DATEPART(QUARTER,NGAYDATHANG) AS QUARTER, "
					+ "SUM(TONGTIEN) AS TONGTIEN\r\n"
					+ "FROM DONHANG\r\n"
					+ "WHERE YEAR(NGAYDATHANG) = "+ year
					+ "GROUP BY YEAR(NGAYDATHANG), DATEPART(QUARTER,NGAYDATHANG)\r\n"
					+ "ORDER BY YEAR(NGAYDATHANG), DATEPART(QUARTER,NGAYDATHANG);";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "";
				if(rss.getInt("QUARTER") == 1) {
					ngay = "Mùa Xuân(Tháng 1 - 3)/" + year;
				}
				else if(rss.getInt("QUARTER") == 2) {
					ngay = "Mùa Hạ(Tháng 4 - 6)/" + year;
				}
				else if(rss.getInt("QUARTER") == 3) {
					ngay = "Mùa Thu(Tháng 7 - 9)/" + year;
				}
				else if(rss.getInt("QUARTER") == 4) {
					ngay = "Mùa Đông(Tháng 10 - 12)/" + year;
				}
				double doanhThu = rss.getDouble("TONGTIEN");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<THONGKE> selectAllDHByDay(int month, int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT DAY(NGAYDATHANG) AS DAY, SUM(TONGTIEN) AS TONGTIEN\r\n"
					+ "FROM DONHANG\r\n"
					+ "WHERE YEAR(NGAYDATHANG) = " + year +" AND MONTH(NGAYDATHANG) = " + month
					+ " GROUP BY DAY(NGAYDATHANG)\r\n"
					+ "ORDER BY DAY";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "Ngày " + String.valueOf(rss.getInt("DAY"));
				double doanhThu = rss.getDouble("TONGTIEN");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				System.out.println(STT);
				System.out.println(ngay);
				System.out.println(doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<THONGKE> selectAllDNByYear() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT YEAR(NGAYNHAP) AS YEAR, SUM(THANHTIEN) AS TONGTIEN\r\n"
					+ "FROM DONNHAP\r\n"
					+ "GROUP BY YEAR(NGAYNHAP)\r\n"
					+ "ORDER BY YEAR";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "Năm " + String.valueOf(rss.getInt("YEAR"));
				double doanhThu = rss.getDouble("TONGTIEN");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				System.out.println(STT);
				System.out.println(ngay);
				System.out.println(doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<THONGKE> selectAllDNByMonth(int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT \r\n"
					+ "    YEAR(NGAYNHAP) AS year, \r\n"
					+ "    MONTH(NGAYNHAP) AS month, \r\n"
					+ "    SUM(THANHTIEN) AS revenue \r\n"
					+ "FROM \r\n"
					+ "    DONNHAP\r\n"
					+ "WHERE \r\n"
					+ "    YEAR(NGAYNHAP) = " + String.valueOf(year)
					+ " GROUP BY \r\n"
					+ "	YEAR(NGAYNHAP),\r\n"
					+ "    MONTH(NGAYNHAP)";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "Tháng " + String.valueOf(rss.getInt("month")) + "/" + String.valueOf(rss.getInt("year"));
				double doanhThu = rss.getDouble("revenue");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	// theo mùa
	public ArrayList<THONGKE> selectAllDNBySeason(int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT DATEPART(QUARTER, NGAYNHAP) AS QUARTER, "
					+ "SUM(THANHTIEN) AS TONGTIEN\r\n"
					+ "FROM DONNHAP\r\n"
					+ "WHERE YEAR(NGAYNHAP) = "+ year
					+ "GROUP BY YEAR(NGAYNHAP), DATEPART(QUARTER,NGAYNHAP)\r\n"
					+ "ORDER BY YEAR(NGAYNHAP), DATEPART(QUARTER,NGAYNHAP);";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "";
				if(rss.getInt("QUARTER") == 1) {
					ngay = "Mùa Xuân(Tháng 1 - 3)/" + year;
				}
				else if(rss.getInt("QUARTER") == 2) {
					ngay = "Mùa Hạ(Tháng 4 - 6)/" + year;
				}
				else if(rss.getInt("QUARTER") == 3) {
					ngay = "Mùa Thu(Tháng 7 - 9)/" + year;
				}
				else if(rss.getInt("QUARTER") == 4) {
					ngay = "Mùa Đông(Tháng 10 - 12)/" + year;
				}
				double doanhThu = rss.getDouble("TONGTIEN");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<THONGKE> selectAllDNByDay(int month, int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT DAY(NGAYNHAP) AS DAY, SUM(THANHTIEN) AS TONGTIEN\r\n"
					+ "FROM DONNHAP\r\n"
					+ "WHERE YEAR(NGAYNHAP) = " + year +" AND MONTH(NGAYNHAP) = " + month
					+ "GROUP BY DAY(NGAYNHAP)\r\n"
					+ "ORDER BY DAY";
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				int STT = j++;
				String ngay = "Ngày " + String.valueOf(rss.getInt("DAY"));
				double doanhThu = rss.getDouble("TONGTIEN");
				THONGKE thongke = new THONGKE(STT, ngay, doanhThu);
				System.out.println(STT);
				System.out.println(ngay);
				System.out.println(doanhThu);
				ds_don_hang.add(thongke);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
