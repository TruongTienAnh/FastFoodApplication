 package DAO;

import java.awt.Dimension;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import DTO.DONHANG;
import DTO.THONGKE;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
public class DAODonHang implements DAOObject<DONHANG>{

	@Override
	public boolean insert(DONHANG t) {
		Connection con = JDBCUtil.getConnection();
		try {
			String query = "INSERT INTO DONHANG values(?,?,?,?,?,?,?,?,?,0)";
			PreparedStatement sta = con.prepareStatement(query);
			sta.setString(1,t.getId_don_hang());
			sta.setString(2,t.getId_nhan_vien());
			DateFormat df = new SimpleDateFormat("Y-MM-dd HH:mm:ss.0");
			sta.setString(3,df.format(t.getNgay_dat_hang()));
			sta.setDouble(4,t.getTong_tien());
			sta.setString(5,t.getTen());
			sta.setString(6,t.getSo_dien_thoai());
			sta.setString(7,t.getDia_chi());
			sta.setInt(8,t.getSo_dat_mon());
			sta.setString(9,t.getTrang_thai());
			
			sta.execute();
			
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(DONHANG t) {
		Connection con = JDBCUtil.getConnection();
		try {
			String query = "UPDATE DONHANG \n"
							+"set IDNHANVIEN =  ?,"
							+"TONGTIEN = ?,"
							+"TEN = ?,"
							+"SODIENTHOAI = ?,"
							+"DIACHI = ?,"
							+"SODATMON = ?,"
							+"TRANGTHAI = ? \n"
							+"WHERE IDDONHANG = ?";
			PreparedStatement sta = con.prepareStatement(query);
			sta.setString(1,t.getId_nhan_vien());
			sta.setDouble(2, t.getTong_tien());
			sta.setString(3,t.getTen());
			sta.setString(4,t.getSo_dien_thoai());
			sta.setString(5,t.getDia_chi());
			sta.setInt(6,t.getSo_dat_mon());
			sta.setString(7,t.getTrang_thai());
			sta.setString(8,t.getId_don_hang());
			
			sta.execute();
			
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(DONHANG t) {
		Connection con = JDBCUtil.getConnection();
		try {
			Statement sta = con.createStatement();
			String query = "INSERT INTO DONHANG \n"
							+ "set TT_XOA = '1' \n"
							+ "where IDDONHANG = '"+t.getId_don_hang()+"'";
			sta.executeUpdate(query);
			con.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ArrayList<DONHANG> selectAll() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<DONHANG> ds_don_hang = new ArrayList<DONHANG>();
		try {
			Statement sta = con.createStatement();
			String query = "Select * from DONHANG where TT_XOA ='0' \n"
					+ "Order by IDDONHANG desc";
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				String id_don_hang = rss.getString("IDDONHANG");
				String id_nhan_vien = rss.getString("IDNHANVIEN");
				java.sql.Timestamp tsTimestamp = rss.getTimestamp("NGAYDATHANG");
				Date ngay_dat_hang = new java.sql.Date(tsTimestamp.getTime());
				double tong_tien = rss.getDouble("TONGTIEN");
				String ten = rss.getString("TEN");
				String so_dien_thoai = rss.getString("SODIENTHOAI");
				String dia_chi = rss.getString("DIACHI");
				int so_dat_mon = rss.getInt("SODATMON");
				String trang_thai = rss.getString("TRANGTHAI");
				DONHANG don_hang = new DONHANG(id_don_hang, id_nhan_vien, ngay_dat_hang, tong_tien, ten, so_dien_thoai, dia_chi, so_dat_mon, trang_thai);
				ds_don_hang.add(don_hang);
			}
			con.close();
			return ds_don_hang;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<DONHANG> selectByCondition(String condition) {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<THONGKE> selectByYear() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT YEAR(NGAYDATHANG) AS YEAR, SUM(TONGTIEN) AS TONGTIEN\r\n"
					+ "FROM DONHANG\r\n"
					+" WHERE TT_XOA = 0 AND TRANGTHAI = 'Hoàn thành'"
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
	
	
	public ArrayList<DONHANG> timKiemDonHang(String timKiem) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<DONHANG> ds_don_hang_tk = new ArrayList<DONHANG>();
		try {
			String query = "Select * from DONHANG where IDDONHANG like '%"+timKiem+"%' or IDNHANVIEN like '%"+timKiem+"%'";
			Statement sta = con.createStatement();
			ResultSet rss = sta.executeQuery(query);
			int j = 1;
			while(rss.next()) {
				String id_don_hang = rss.getString("IDDONHANG");
				String id_nhan_vien = rss.getString("IDNHANVIEN");
				java.sql.Timestamp tsTimestamp = rss.getTimestamp("NGAYDATHANG");
				Date ngay_dat_hang = new java.sql.Date(tsTimestamp.getTime());
				double tong_tien = rss.getDouble("TONGTIEN");
				String ten = rss.getString("TEN");
				String so_dien_thoai = rss.getString("SODIENTHOAI");
				String dia_chi = rss.getString("DIACHI");
				int so_dat_mon = rss.getInt("SODATMON");
				String trang_thai = rss.getString("TRANGTHAI");
				DONHANG don_hang = new DONHANG(id_don_hang, id_nhan_vien, ngay_dat_hang, tong_tien, ten, so_dien_thoai, dia_chi, so_dat_mon, trang_thai);
				ds_don_hang_tk.add(don_hang);
			}
			con.close();
			return ds_don_hang_tk;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<THONGKE> selectByMonth(int year) {
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
					+"AND TT_XOA = 0 AND TRANGTHAI = 'Hoàn thành'"
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
	public ArrayList<THONGKE> selectBySeason(int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT DATEPART(QUARTER,NGAYDATHANG) AS QUARTER, "
					+ "SUM(TONGTIEN) AS TONGTIEN\r\n"
					+ "FROM DONHANG\r\n"
					+ "WHERE YEAR(NGAYDATHANG) = "+ year +" AND TRANGTHAI = 'Hoàn thành' and TT_XOA = 0\r\n"
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
	public ArrayList<THONGKE> selectByDay(int month, int year) {
		Connection con = JDBCUtil.getConnection();
		ArrayList<THONGKE> ds_don_hang = new ArrayList<THONGKE>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT DAY(NGAYDATHANG) AS DAY, SUM(TONGTIEN) AS TONGTIEN\r\n"
					+ "FROM DONHANG\r\n"
					+ "WHERE YEAR(NGAYDATHANG) = " + year +" AND MONTH(NGAYDATHANG) = " + month
					+"AND TT_XOA = 0 AND TRANGTHAI = 'Hoàn thành'"
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
	public ArrayList<Integer> getListYear() {
		Connection con = JDBCUtil.getConnection();
		ArrayList<Integer> listYear = new ArrayList<Integer>();
		try {
			Statement sta = con.createStatement();
			String query = "SELECT YEAR(NGAYDATHANG) AS YEAR \r\n"
					+ "  FROM DONHANG\r\n"
					+ "  GROUP BY YEAR(NGAYDATHANG)\r\n"
					+ "  ORDER BY YEAR desc";
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				listYear.add(rss.getInt("YEAR"));
			}
			con.close();
			return listYear;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void xuatExcel(ArrayList<THONGKE> t) {
		 ArrayList<Object[]> data = new ArrayList<Object[]>();
		 for(THONGKE temp: t) {
	        data.add(new Object[]{temp.getSTT(), temp.getDate(), temp.getDoanhThu()});
		 }
	        try (// Tạo một workbook mới của Excel
	        	Workbook workbook = new XSSFWorkbook()) {
				// Tạo một sheet mới trong workbook
				Sheet sheet = workbook.createSheet("Sheet1");
				// Tạo một row mới trong sheet
				Row row = sheet.createRow(0);
				// Tạo các cell mới trong row và gán giá trị cho chúng
				Cell cell1 = row.createCell(0);
				cell1.setCellValue("STT");

				Cell cell2 = row.createCell(1);
				cell2.setCellValue("Thời gian");

				Cell cell3 = row.createCell(2);
				cell3.setCellValue("Tổng tiền");

				// Lặp qua ArrayList và thêm dữ liệu vào sheet
				for(int i = 0; i < data.size(); i++) {
				    row = sheet.createRow(i+1);
				    Object[] rowData = data.get(i);

				    for(int j = 0; j < rowData.length; j++) {
				        Cell cell = row.createCell(j);
				        cell.setCellValue(rowData[j].toString());
				    }
				}

				// Lưu workbook vào file Excel
				try {
					File f = new File("DoAnJava/excel/output.xlsx");
				    FileOutputStream outputStream = new FileOutputStream(f);
				    workbook.write(outputStream);
				    outputStream.close();
				    System.out.println("Data exported successfully to Excel!");
				} catch (Exception e) {
				    e.printStackTrace();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	public void HoanThanh(String id_don_hang) {
		Connection con = JDBCUtil.getConnection();
		try {
			Statement sta = con.createStatement();
			String executeQuery = "Update DONHANG \n"
								+ "set TRANGTHAI = N'Hoàn Thành'"
								+ "where IDDONHANG = '"+id_don_hang+"'";
			sta.executeUpdate(executeQuery);
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
