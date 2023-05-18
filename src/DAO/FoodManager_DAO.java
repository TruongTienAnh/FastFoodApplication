package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import DTO.CHITIET_NL_MA;
import DTO.KHUYENMAI;
import DTO.MONAN;
import DTO.MONAN_KHUYENMAI;

public class FoodManager_DAO {
	public List<MONAN> getAllfood(){
		List<MONAN> monans = new ArrayList<MONAN>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
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
				monans.add(food);
			}
			
			connection.close();
			return monans;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return monans;
	}
	public void addfood(MONAN food) {
		Connection connection = JDBCUtil.getConnection();
		try {
			
			String query = "INSERT INTO MONAN values(?,?,?,?,?,?,'0')";
			PreparedStatement prepare_statement = connection.prepareStatement(query);
			prepare_statement.setString(1,food.getId());
			prepare_statement.setString(2,food.getName());
			prepare_statement.setString(3,food.getType());
			prepare_statement.setString(4,food.getImage());
			prepare_statement.setDouble(5,food.getUnitPrice());
			prepare_statement.setString(6,food.getDecription());
			prepare_statement.executeUpdate();
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	public void updatefood(MONAN food) {
		Connection connection = JDBCUtil.getConnection();
		try {
			
			String query = "UPDATE MONAN \n"+
					"set TENMONAN = N'"+food.getName() +"',"+
					"LOAIMONAN = '"+food.getType()+"',"+
					"HINHANH = '"+food.getImage()+"',"+
					"GIABAN = "+food.getUnitPrice()+" ,"+
					"MOTA = N'"+food.getDecription()+"' "+
					"where IDMONAN = '"+food.getId()+"'";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.executeUpdate();
			System.out.print(query);
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}   
	public void deletefood(String id) {
		Connection connection = JDBCUtil.getConnection();
		try {
					
			String query = "DELETE FROM MONAN"+" WHERE IDMONAN = ?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setString(1, id);
			prepare_statement.executeUpdate();
			System.out.print(query);
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}	
	}
	public ArrayList<String> getIdList() {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<String> list = new ArrayList<>();
	    try {
	         // thực hiện truy vấn để lấy danh sách các giá trị của thuộc tính 'id'
	         Statement statement = connection.createStatement();
	         String sql = "SELECT IDMONAN FROM MONAN";
	         ResultSet resultSet = statement.executeQuery(sql);
	         while (resultSet.next()) {
	            list.add(resultSet.getString("IDMONAN"));
	         }
	         connection.close();
	    } catch (Exception e) {
	         System.out.println(e);
	    }
	    return list;
	    }
	
	
	public List<MONAN> getFoodbysearch(String condition){
		List<MONAN> monans = new ArrayList<MONAN>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
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
				monans.add(food);
			}
			
			connection.close();
			return monans;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return monans;
	}
	public List<MONAN> getFoodintoPrice(String sortOrder){
		List<MONAN> monans = new ArrayList<MONAN>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query =  "SELECT * FROM MONAN ORDER BY GIABAN " + sortOrder;
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
				monans.add(food);
			}
			
			connection.close();
			return monans;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return monans;
	}
	
	////////////////////////////////////////
	public List<KHUYENMAI> getAllkhuyenmai(){
		List<KHUYENMAI> khuyenmais = new ArrayList<KHUYENMAI>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query = "select * from khuyenmai where TT_XOA ='0'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String id = rss.getString("IDKHUYENMAI");
				String name = rss.getString("TENKHUYENMAI");
				Date ngaybd = rss.getDate("NGAYBATDAU");
				Date ngaykt = rss.getDate("NGAYKETTHUC");
				String decripton = rss.getString("MOTA");
				
				KHUYENMAI khuỵenmai = new KHUYENMAI(id, name,ngaybd,ngaykt ,decripton);
				khuyenmais.add(khuỵenmai);
			}
			
			connection.close();
			return khuyenmais;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return khuyenmais;
	}

	public void addkhuyenmai(KHUYENMAI khuyenmai) {
		Connection connection = JDBCUtil.getConnection();
		try {
			
			String query = "INSERT INTO KHUYENMAI values(?,?,?,?,?,'0')";
			PreparedStatement prepare_statement = connection.prepareStatement(query);
			prepare_statement.setString(1,khuyenmai.getId_khuyen_mai());
			prepare_statement.setString(2,khuyenmai.getTen_khuyen_mai());
			java.sql.Date ngaybd_sql = new java.sql.Date(khuyenmai.getNgay_bat_dau().getTime());
			prepare_statement.setDate(3,ngaybd_sql);
			java.sql.Date ngaykt_sql = new java.sql.Date(khuyenmai.getNgay_ket_thuc().getTime());
	        prepare_statement.setDate(4, ngaykt_sql);
			prepare_statement.setString(5,khuyenmai.getMo_ta());
			prepare_statement.executeUpdate();
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updatekhuyenmai(KHUYENMAI khuyenmai) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String query = "UPDATE KHUYENMAI "+
			               "SET TENKHUYENMAI=?, NGAYBATDAU=?, NGAYKETTHUC=?, MOTA=? "+
			               "WHERE IDKHUYENMAI=?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setString(5,khuyenmai.getId_khuyen_mai());
			prepare_statement.setString(1,khuyenmai.getTen_khuyen_mai());
			java.sql.Date ngaybd_sql = new java.sql.Date(khuyenmai.getNgay_bat_dau().getTime());
			prepare_statement.setDate(2,ngaybd_sql);
			java.sql.Date ngaykt_sql = new java.sql.Date(khuyenmai.getNgay_ket_thuc().getTime());
	        prepare_statement.setDate(3, ngaykt_sql);
			prepare_statement.setString(4,khuyenmai.getMo_ta());
			prepare_statement.executeUpdate();
			System.out.println(query);
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void deletekhuyenmai(String id) {
		Connection connection = JDBCUtil.getConnection();
		try {
					
			String query = "DELETE FROM KHUYENMAI"+" WHERE IDKHUYENMAI = ?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setString(1, id);
			prepare_statement.executeUpdate();
			System.out.print(query);
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}	
	}
	
	public ArrayList<String> getIdListkhuyenmai() {
	    Connection connection = JDBCUtil.getConnection();
	    ArrayList<String> list = new ArrayList<>();
	    try {
	        Statement statement = connection.createStatement();
	        String sql = "SELECT IDKHUYENMAI FROM KHUYENMAI";
	        ResultSet resultSet = statement.executeQuery(sql);
	        while (resultSet.next()) {
	            String idkhuyenmai = resultSet.getString("IDKHUYENMAI");
	            list.add(idkhuyenmai);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return list;
	}
	
	
	
	public List<KHUYENMAI> getKhuyenmaibysearch(String condition){
		List<KHUYENMAI> khuyenmais = new ArrayList<KHUYENMAI>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query = "select * from KHUYENMAI \n"+
					"Where IDKHUYENMAI like '%"+condition+"%'"+
					" or TENKHUYENMAI like '%"+condition+"%' and TT_XOA = '0'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String id = rss.getString("IDKHUYENMAI");
				String name = rss.getString("TENKHUYENMAI");
				Date ngaybd = rss.getDate("NGAYBATDAU");
				Date ngaykt = rss.getDate("NGAYKETTHUC");
				String decripton = rss.getString("MOTA");
				KHUYENMAI sale = new KHUYENMAI(id, name, ngaybd, ngaykt, decripton);
				khuyenmais.add(sale);
			}
			
			connection.close();
			return khuyenmais;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return khuyenmais;
	}

	
	
	
	public List<MONAN_KHUYENMAI> getAllMA_KhuyenMai(){
		List<MONAN_KHUYENMAI> monan_KHUYENMAIs = new ArrayList<MONAN_KHUYENMAI>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query = "select * from MONAN_KHUYENMAI";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String idMA = rss.getString("IDMONAN");
				String idKM = rss.getString("IDKHUYENMAI");
				int phantram = rss.getInt("PHANTRAMGIAM");				
				MONAN_KHUYENMAI foodkm = new MONAN_KHUYENMAI(idMA, idKM, phantram);
				monan_KHUYENMAIs.add(foodkm);
			}
			
			connection.close();
			return monan_KHUYENMAIs;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return monan_KHUYENMAIs;
	}
	
	public void addMA_KhuyenMai(MONAN_KHUYENMAI monan_KHUYENMAI) {
		Connection connection = JDBCUtil.getConnection();
		try {
			
			String query = "INSERT INTO MONAN_KHUYENMAI values(?,?,?)";
			PreparedStatement prepare_statement = connection.prepareStatement(query);
			prepare_statement.setString(1,monan_KHUYENMAI.getId_khuyen_mai());
			prepare_statement.setString(2,monan_KHUYENMAI.getId_mon_an());
			prepare_statement.setInt(3,monan_KHUYENMAI.getPhan_tram_giam());
			prepare_statement.executeUpdate();
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void updateMA_KhuyenMai(MONAN_KHUYENMAI monan_KHUYENMAI) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String query = "UPDATE MONAN_KHUYENMAI "+
			               "SET PHANTRAMGIAM=? "+
			               "WHERE IDKHUYENMAI=? AND IDMONAN=?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setInt(1,monan_KHUYENMAI.getPhan_tram_giam());
			prepare_statement.setString(2,monan_KHUYENMAI.getId_khuyen_mai());
			prepare_statement.setString(3,monan_KHUYENMAI.getId_mon_an());
			prepare_statement.executeUpdate();
			System.out.println(query);
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void deleteMA_KhuyenMai(String id, String id1) {
		Connection connection = JDBCUtil.getConnection();
		try {	
			String query = "DELETE FROM MONAN_KHUYENMAI "+" WHERE IDKHUYENMAI = ? AND IDMONAN = ?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setString(1, id);
			prepare_statement.setString(2, id1);
			prepare_statement.executeUpdate();
			System.out.print(query);
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}	
	}
	public ArrayList<String> getIdfood(String idKMAI) {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<String> list = new ArrayList<>();
	    try {
	         // thực hiện truy vấn để lấy danh sách các giá trị của thuộc tính 'id'
	         Statement statement = connection.createStatement();
	         String sql = "SELECT IDMONAN FROM MONAN_KHUYENMAI WHERE IDKHUYENMAI= '" + idKMAI + "'";
	         ResultSet resultSet = statement.executeQuery(sql);
	         while (resultSet.next()) {
	            list.add(resultSet.getString("IDMONAN"));
	         }
	         connection.close();
	    } catch (Exception e) {
	         System.out.println(e);
	    }
	    return list;
	    }
	public ArrayList<String> getIdKm() {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<String> list = new ArrayList<>();
	    try {
	         // thực hiện truy vấn để lấy danh sách các giá trị của thuộc tính 'id'
	         Statement statement = connection.createStatement();
	         String sql = "SELECT DISTINCT IDKHUYENMAI FROM MONAN_KHUYENMAI";
	         ResultSet resultSet = statement.executeQuery(sql);
	         while (resultSet.next()) {
	            list.add(resultSet.getString("IDKHUYENMAI"));
	         }
	         connection.close();
	    } catch (Exception e) {
	         System.out.println(e);
	    }
	    return list;
	    }
	
	public List<MONAN_KHUYENMAI> getMAKhuyenmaibysearch(String condition){
		List<MONAN_KHUYENMAI> monan_KHUYENMAIs = new ArrayList<MONAN_KHUYENMAI>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query = "select * from MONAN_KHUYENMAI \n"+
					"Where IDKHUYENMAI like '%"+condition+"%'"+
					" or IDMONAN like '%"+condition+"%'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String idkm = rss.getString("IDKHUYENMAI");
				String idMA = rss.getString("IDMONAN");
				int phantram = rss.getInt("PHANTRAMGIAM");	
				MONAN_KHUYENMAI sale = new MONAN_KHUYENMAI(idMA, idkm, phantram);
				monan_KHUYENMAIs.add(sale);
			}
			
			connection.close();
			return monan_KHUYENMAIs;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return monan_KHUYENMAIs;
	}
	
	public List<MONAN_KHUYENMAI> getKhuyeMaiMAinto(String sortOrder){
		List<MONAN_KHUYENMAI> monan_KHUYENMAIs = new ArrayList<MONAN_KHUYENMAI>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query =  "SELECT * FROM MONAN_KHUYENMAI ORDER BY '" + sortOrder + "'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String idkm = rss.getString("IDKHUYENMAI");
				String idMA = rss.getString("IDMONAN");
				int phantram = rss.getInt("PHANTRAMGIAM");	
				MONAN_KHUYENMAI sale = new MONAN_KHUYENMAI(idMA, idkm, phantram);
				
				monan_KHUYENMAIs.add(sale);
			}
			
			connection.close();
			return monan_KHUYENMAIs;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return monan_KHUYENMAIs;
	}
	////////////////////////////////
	public List<CHITIET_NL_MA> getAllCT_NL_MA(){
		List<CHITIET_NL_MA> chitiet_NL_MAs = new ArrayList<CHITIET_NL_MA>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query = "select * from CHITIET_NL_MA";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String idMA = rss.getString("IDMONAN");
				String idNL = rss.getString("IDNGUYENLIEU");
				int soluong = rss.getInt("SOLUONG");				
				CHITIET_NL_MA foodkm = new CHITIET_NL_MA( idNL,idMA, soluong);
				chitiet_NL_MAs.add(foodkm);
			}
			
			connection.close();
			return chitiet_NL_MAs;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return chitiet_NL_MAs;
	}
	public ArrayList<String> getIDNL() {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<String> list = new ArrayList<>();
	    try {
	         Statement statement = connection.createStatement();
	         String sql = "SELECT IDNGUYENLIEU FROM NGUYENLIEU";
	         ResultSet resultSet = statement.executeQuery(sql);
	         while (resultSet.next()) {
	            list.add(resultSet.getString("IDNGUYENLIEU"));
	         }
	         connection.close();
	    } catch (Exception e) {
	         System.out.println(e);
	    }
	    return list;
	    }
	
	public void addCT_NL_MA(CHITIET_NL_MA chitiet_NL_MA) {
		Connection connection = JDBCUtil.getConnection();
		try {
			
			String query = "INSERT INTO CHITIET_NL_MA values(?,?,?)";
			PreparedStatement prepare_statement = connection.prepareStatement(query);
			prepare_statement.setString(1,chitiet_NL_MA.getId_mon_an());
			prepare_statement.setString(2,chitiet_NL_MA.getId_nguyen_lieu());
			prepare_statement.setInt(3,chitiet_NL_MA.getSoluong());
			prepare_statement.executeUpdate();
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	public void updateCT_NL_MA(CHITIET_NL_MA chitiet_NL_MA) {
		Connection connection = JDBCUtil.getConnection();
		try {
			String query = "UPDATE CHITIET_NL_MA "+
			               "SET SOLUONG=? "+
			               "WHERE IDNGUYENLIEU=? AND IDMONAN=?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setInt(1,chitiet_NL_MA.getSoluong());
			prepare_statement.setString(2,chitiet_NL_MA.getId_nguyen_lieu());
			prepare_statement.setString(3,chitiet_NL_MA.getId_mon_an());
			prepare_statement.executeUpdate();
			System.out.println(query);
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public void deleteNLMA(String id, String id1) {
		Connection connection = JDBCUtil.getConnection();
		try {	
			String query = "DELETE FROM CHITIET_NL_MA "+" WHERE IDNGUYENLIEU = ? AND IDMONAN = ?";
			PreparedStatement prepare_statement = connection.prepareStatement(query);	
			prepare_statement.setString(2, id);
			prepare_statement.setString(1, id1);
			prepare_statement.executeUpdate();
			System.out.print(query);
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}	
	}
	public ArrayList<Integer> getIdfoodnl() {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<Integer> list = new ArrayList<>();
	    try {
	         Statement statement = connection.createStatement();
	         String sql = "SELECT DISTINCT IDMONAN FROM CHITIET_NL_MA";
	         ResultSet resultSet = statement.executeQuery(sql);
	         while (resultSet.next()) {
	            list.add(resultSet.getInt("IDMONAN"));
	         }
	         connection.close();
	    } catch (Exception e) {
	         System.out.println(e);
	    }
	    return list;
	    }
	public ArrayList<String> getIdnl(String idMAN) {
		Connection connection = JDBCUtil.getConnection();
		ArrayList<String> list = new ArrayList<>();
	    try {
	         // thực hiện truy vấn để lấy danh sách các giá trị của thuộc tính 'id'
	         Statement statement = connection.createStatement();
	         String sql = "SELECT IDNGUYENLIEU FROM CHITIET_NL_MA WHERE IDMONAN='" + idMAN + "'";
	         ResultSet resultSet = statement.executeQuery(sql);
	         while (resultSet.next()) {
	            list.add(resultSet.getString("IDNGUYENLIEU"));
	         }
	         connection.close();
	    } catch (Exception e) {
	         System.out.println(e);
	    }
	    return list;
	    }
	public List<CHITIET_NL_MA> getCTNLbysearch(String condition){
		List<CHITIET_NL_MA> chitiet_NL_MAs = new ArrayList<CHITIET_NL_MA>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query = "select * from CHITIET_NL_MA \n"+
					"Where IDNGUYENLIEU like '%"+condition+"%'"+
					" or IDMONAN like '%"+condition+"%'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String idNL = rss.getString("IDNGUYENLIEU");
				String idMA = rss.getString("IDMONAN");
				int soluong = rss.getInt("SOLUONG");	
				CHITIET_NL_MA nl = new CHITIET_NL_MA(idNL, idMA, soluong);
				chitiet_NL_MAs.add(nl);
			}
			
			connection.close();
			return chitiet_NL_MAs;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return chitiet_NL_MAs;
	}
	public List<CHITIET_NL_MA> getNGUYENLIEUMAinto(String sortOrder){
		List<CHITIET_NL_MA> chitiet_NL_MAs = new ArrayList<CHITIET_NL_MA>();
		Connection connection = JDBCUtil.getConnection();
		try {
			Statement statement = connection.createStatement();
			
			String query =  "SELECT * FROM CHITIET_NL_MA ORDER BY '" + sortOrder + "'";
			System.out.println(query);
			
			ResultSet rss =  statement.executeQuery(query);
			
			while(rss.next()) {
				String idnl = rss.getString("IDNGUYENLIEU");
				String idMA = rss.getString("IDMONAN");
				int soluong = rss.getInt("SOLUONG");	
				CHITIET_NL_MA nguyenlieu = new CHITIET_NL_MA(idnl, idMA, soluong);
				
				chitiet_NL_MAs.add(nguyenlieu);
			}
			
			connection.close();
			return chitiet_NL_MAs;
			
		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		return chitiet_NL_MAs;
	}
}
