package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.CHITIET_DONHANG;
import DTO.DON_NHAPHANG;
import DTO.NGUYENLIEU;
import DTO.NHA_CU_CA;

public class DAONguyenLieu implements DAOObject<NGUYENLIEU> {

	@Override
	public boolean insert(NGUYENLIEU t) {
		Connection con = JDBCUtil.getConnection();
		try {
			 String query = "INSERT INTO NGUYENLIEU values(?,?,?,?,'0')";
			 PreparedStatement prepare_statement = con.prepareStatement(query);
			 prepare_statement.setString(1,t.getId_nguyen_lieu());
			 prepare_statement.setString(2,t.getId_mon_an());
			 prepare_statement.setString(3,t.getDon_vi());
			 prepare_statement.setDouble(4,t.getSo_luong());
			 prepare_statement.execute();
			 con.close();
			 return true;
		} catch (Exception e) {
			System.err.println("Loi insert mon an ở DAO");
		}
		return false;
	}

	@Override
	public boolean update(NGUYENLIEU t) {
	    Connection con = JDBCUtil.getConnection();
		try {
//			String query = "UPDATE NGUYENLIEU \n"+
//					    	"set TENNGUYENLIEU = N'"+t.getId_mon_an() +"',"+
//					    	"DONVI = '"+t.getDon_vi()+"',"+
//					    	"SOLUONG = "+t.getSo_luong()+","+
//					    	"\nwhere IDNGUYENLIEU= '"+t.getId_nguyen_lieu()+"'";
			PreparedStatement psmtThem = con.prepareStatement("UPDATE NGUYENLIEU SET TENNGUYENLIEU = ?, DONVI = ?,SOLUONG = ? WHERE IDNGUYENLIEU = ?");
			psmtThem.setNString(1, t.getId_mon_an());
			psmtThem.setNString(2, t.getDon_vi());
			psmtThem.setInt	   (3, t.getSo_luong());
			psmtThem.setNString(4, t.getId_nguyen_lieu());
			psmtThem.executeUpdate();
			con.close();

			
			return true;
		} catch (Exception e) {
			System.err.println("loi update NL");
		}
		return false;
	}

	@Override
	public boolean delete(NGUYENLIEU t) {
		Connection con = JDBCUtil.getConnection();
		try {
//			String query = "UPDATE NGUYENLIEU \n"+
//			    	"set TT_XOA = N'"+"1"+"',"+
//			    	"\nwhere IDNGUYENLIEU= '"+t.getId_nguyen_lieu()+"'";
//			PreparedStatement pre_sta = con.prepareStatement(query);
//	
//			pre_sta.executeUpdate();
//	
//			con.close();
			PreparedStatement pstm = con.prepareStatement("UPDATE NGUYENLIEU SET TT_XOA = 1 WHERE IDNGUYENLIEU = ?");
			pstm.setNString(1, t.getId_nguyen_lieu());
			pstm.executeUpdate();
			con.close();

	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public ArrayList<NGUYENLIEU> selectAll() {
		ArrayList<DTO.NGUYENLIEU> ds_nl = new ArrayList<DTO.NGUYENLIEU>();
		Connection con = JDBCUtil.getConnection();
		try {
			Statement sta = con.createStatement();
			String query = "Select * from NguyenLieu where TT_XOA ='0'";
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				String ma_nguyen_lieu = rss.getString("IDNGUYENLIEU");
				String ten_nguyen_lieu = rss.getString("TENNGUYENLIEU");
				String don_vi = rss.getString("DONVI");
				int soluong = rss.getInt("SOLUONG");
				NGUYENLIEU nguyen_lieu = new NGUYENLIEU(ma_nguyen_lieu, ten_nguyen_lieu, don_vi, soluong);
				ds_nl.add(nguyen_lieu);
			}
			con.close();
			
			return ds_nl;
		
		} catch (Exception e) {
			System.err.println("loi");
		}
		
		return ds_nl;
	}

	@Override
	public ArrayList<NGUYENLIEU> selectByCondition(String condition) {
		   ArrayList<NGUYENLIEU> ds_nl = new ArrayList<DTO.NGUYENLIEU>();
		   Connection con = JDBCUtil.getConnection();
		try {
			Statement sta = con.createStatement();
			String query = "Select * from NguyenLieu"+
					        "Where IDNGUYENLIEU like '%"+condition+"%'"+
					         "or  TENNGUYENLIEU like '%"+condition+"%' and TT_XOA='0' ";
			ResultSet rss = sta.executeQuery(query);
			while(rss.next()) {
				String ma_nguyen_lieu = rss.getString("IDNGUYENLIEU");
				String ten_nguyen_lieu = rss.getString("TENNGUYENLIEU");
				String don_vi = rss.getString("DONVI");
				int soluong = rss.getInt("SOLUONG");
				NGUYENLIEU nguyen_lieu = new NGUYENLIEU(ma_nguyen_lieu, ten_nguyen_lieu, don_vi, soluong);
				ds_nl.add(nguyen_lieu);
			}
			con.close();
			return ds_nl;
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		
		return null;
	}

	public void TruNguyenLieu(String id_nguyen_lieu, int so_luong_tru) {
		Connection con = JDBCUtil.getConnection();
		try {
			String query = "UPDATE NGUYENLIEU \n"
					//+ "set SOLUONG = SOLUONG - ? \n"
					+ "set SOLUONG = SOLUONG - "+ so_luong_tru +"? \n"
					+ "where IDNGUYENLIEU = '"+id_nguyen_lieu+ "'";
			PreparedStatement sta = con.prepareStatement(query);
			sta.setInt(1,so_luong_tru);
			sta.setString(2,id_nguyen_lieu);
			
			sta.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ;
	}
	//=========================================( NHA CUNG CAP THUC PHAM SIEU BAN)===============================
	public ArrayList<NHA_CU_CA> Select_All_NCC ()
	{      ArrayList<DTO.NHA_CU_CA> ds_ncc = new ArrayList<DTO.NHA_CU_CA>();
	Connection con = JDBCUtil.getConnection();
	try {
		Statement sta = con.createStatement();
		String query = "Select * from NHACUNGCAP where TT_XOA ='0'";
		ResultSet rss = sta.executeQuery(query);
		while(rss.next()) {
			String ID_Nha_Cung_cap = rss.getString("IDNHACUNGCAP");
			String Ten_Nha_cung_cap = rss.getString("TENNHACUNGCAP");
			String DIA_CHI = rss.getString("DIACHI");
			String So_Dien_Thoai=rss.getString("SODIENTHOAI");
			String TT_XOA=rss.getString("TT_XOA");
			NHA_CU_CA ncc = new NHA_CU_CA(ID_Nha_Cung_cap,Ten_Nha_cung_cap,DIA_CHI,So_Dien_Thoai,TT_XOA);
			ds_ncc.add(ncc);
		}
		con.close();
		
		return ds_ncc;
	
	} catch (Exception e) {
		System.err.println("loi");
	}
	return null;
		
	}
	
	//@Override
	public boolean insertNCC(NHA_CU_CA t) {
		Connection con = JDBCUtil.getConnection();
		try {
			 String query = "INSERT INTO NHACUNGCAP values(?,?,?,?,'0')";
			 PreparedStatement prepare_statement = con.prepareStatement(query);
			 prepare_statement.setString(1,t.GetIDNcc());
			 prepare_statement.setString(2,t.GetTenNcc());
			 prepare_statement.setString(3,t.GetDiaChicc());
			 prepare_statement.setString(4,t.GetSDTNcc());
			 prepare_statement.execute();
			 con.close();
			 return true;
		} catch (Exception e) {
			System.err.println("Loi insert mon an ở DAO");
		}
		return false;
	}
	
	public boolean updateNCC(NHA_CU_CA t) {
	    Connection con = JDBCUtil.getConnection();
		try {
//			String query = "UPDATE NGUYENLIEU \n"+
//					    	"set TENNGUYENLIEU = N'"+t.getId_mon_an() +"',"+
//					    	"DONVI = '"+t.getDon_vi()+"',"+
//					    	"SOLUONG = "+t.getSo_luong()+","+
//					    	"\nwhere IDNGUYENLIEU= '"+t.getId_nguyen_lieu()+"'";
			PreparedStatement psmtThem = con.prepareStatement("UPDATE NHACUNGCAP SET TENNHACUNGCAP = ?, DIACHI = ?,SODIENTHOAI = ? WHERE IDNHACUNGCAP = ?");
			psmtThem.setNString(1, t.GetTenNcc());
			psmtThem.setNString(2, t.GetDiaChicc());
			psmtThem.setNString(3, t.GetSDTNcc());
			psmtThem.setNString(4, t.GetIDNcc());
			psmtThem.executeUpdate();
			con.close();

			
			return true;
		} catch (Exception e) {
			System.err.println("loi update NL");
		}
		return false;
	}
	public boolean deleteNCC(NHA_CU_CA t) {
		Connection con = JDBCUtil.getConnection();
		try {
//			String query = "UPDATE NGUYENLIEU \n"+
//			    	"set TT_XOA = N'"+"1"+"',"+
//			    	"\nwhere IDNGUYENLIEU= '"+t.getId_nguyen_lieu()+"'";
//			PreparedStatement pre_sta = con.prepareStatement(query);
//	
//			pre_sta.executeUpdate();
//	
//			con.close();
			PreparedStatement pstm = con.prepareStatement("UPDATE NHACUNGCAP SET TT_XOA = 1 WHERE IDNHACUNGCAP = ?");
			pstm.setNString(1, t.GetIDNcc());
			pstm.executeUpdate();
			con.close();

	
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	//====================================================(DON NHAP HANG)========================================
    public ArrayList<DON_NHAPHANG> selecall_DON_NHAP_HANG()
    {
    	ArrayList<DTO.DON_NHAPHANG> ds_don_nhap_hang = new ArrayList<DTO.DON_NHAPHANG>();
    	Connection con = JDBCUtil.getConnection();
    	try {
    		Statement sta = con.createStatement();
    		String query = "Select * from DONNHAP where TT_XOA ='0'";
    		ResultSet rss = sta.executeQuery(query);
    		while(rss.next()) {
    			String ID_DON_NHAP 		= 	  	   rss.getString("IDDONNHAP");
    			String ID_Nhan_vien	    = 		  rss.getString("IDNHANVIEN");
    			String ID_NHA_CC 		= 		rss.getString("IDNHACUNGCAP");
    			int THANH_TIEN 			=       	  rss.getInt("THANHTIEN");
    			String NGAYNHAP			=		    rss.getString("NGAYNHAP");
    			String TT_xoa 			=			  rss.getString("TT_XOA");
    			DON_NHAPHANG DNH = new DON_NHAPHANG(ID_DON_NHAP,ID_Nhan_vien,ID_NHA_CC ,THANH_TIEN ,NGAYNHAP,"0");
    			ds_don_nhap_hang.add(DNH);
    		}
    		con.close();
    		
    		return ds_don_nhap_hang;
    	
    	} catch (Exception e) {
    		System.err.println("loi");
    	}
    	return null;
    		
    }
    public boolean updateDNH(DON_NHAPHANG t) {
	    Connection con = JDBCUtil.getConnection();
		try {
//			String query = "UPDATE NGUYENLIEU \n"+
//					    	"set TENNGUYENLIEU = N'"+t.getId_mon_an() +"',"+
//					    	"DONVI = '"+t.getDon_vi()+"',"+
//					    	"SOLUONG = "+t.getSo_luong()+","+
//					    	"\nwhere IDNGUYENLIEU= '"+t.getId_nguyen_lieu()+"'";
			PreparedStatement psmtThem = con.prepareStatement("UPDATE DONNHAP SET IDNHANVIEN = ?,IDNHACUNGCAP = ?,THANHTIEN= ?,NGAYNHAP WHERE IDNHACUNGCAP = ?");
			psmtThem.setNString(1, t.get_IDNhanvien());
			psmtThem.setNString(2, t.get_IDNHA_CUNG_CAP());
			psmtThem.setInt	   (3, t.get_Thanhtien());
			psmtThem.setNString(4, t.get_NGAYNHAP());
			psmtThem.executeUpdate();
			con.close();

			
			return true;
		} catch (Exception e) {
			System.err.println("loi update NL");
		}
		return false;
	}
    public boolean insertDNH(DON_NHAPHANG t) {
		Connection con = JDBCUtil.getConnection();
		try {
			 String query = "INSERT INTO DONNHAP values(?,?,?,?,?,'0')";
			 PreparedStatement prepare_statement = con.prepareStatement(query);
			 System.out.println(t.get_ID_DON_NHAP_HANG());
			 System.out.println(t.get_IDNhanvien());
			 System.out.println(t.get_IDNHA_CUNG_CAP());
			 System.out.println(t.get_Thanhtien());
			 System.out.println(t.get_NGAYNHAP());
			 prepare_statement.setString(1,t.get_ID_DON_NHAP_HANG());
			 prepare_statement.setString(2,t.get_IDNhanvien());
			 prepare_statement.setString(3,t.get_IDNHA_CUNG_CAP());
			 prepare_statement.setDouble(4,(double) t.get_Thanhtien());
			 prepare_statement.setString(5,"");
			 prepare_statement.execute();
			 con.close();
			 return true;
		} catch (Exception e) {
			System.err.println("Loi insertDNH");
		}
		return false;
	}
    public boolean insertDNH_chitiet(CHITIET_DONHANG t) {
		Connection con = JDBCUtil.getConnection();
		try {
		System.out.println(t.getId_don_hang());
		System.out.println(t.getId_mon_an());
		System.out.println(t.getSo_luong());
		System.out.println(t.getDon_gia());
			 String query = "INSERT INTO CHITIETDONNHAP values(?,?,?,?)";
			 PreparedStatement prepare_statement = con.prepareStatement(query);
			 prepare_statement.setString(1,t.getId_don_hang());
			 prepare_statement.setString(2,t.getId_mon_an());
			 prepare_statement.setInt(3,t.getSo_luong());
			 prepare_statement.setDouble(4,t.getDon_gia());
			 prepare_statement.execute();
			 con.close();
			 return true;
		} catch (Exception e) {
			System.err.println(e);
		}
		return false;
	}
//◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘◘
}

