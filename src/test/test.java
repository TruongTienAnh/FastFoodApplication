package test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAO.DAOChiTiet_DH;
import DAO.DAOChiTiet_MA_KM;
import DAO.DAOChiTiet_NL_MA;
import DAO.DAODonHang;
import DAO.DAOKhuyenMai;
import DAO.DAOMonAn;
import DAO.DAONhanVien;
import DAO.DAOTaiKhoan;
import DTO.CHITIET_DONHANG;
import DTO.CHITIET_NL_MA;
import DTO.DONHANG;
import DTO.KHUYENMAI;
import DTO.MONAN;
import DTO.MONAN_KHUYENMAI;
import DTO.TAIKHOAN;


public class test {
	public static void main(String[] args) {
//		ArrayList<CHITIET_NL_MA> ds_ct_nl_ma = new DAOChiTiet_NL_MA().selectByIdMonAn("01");
//		for(int i = 0;i<ds_ct_nl_ma.size();i++) {
//			System.out.println(ds_ct_nl_ma.get(i).getId_nguyen_lieu());
//		}
		
//		ArrayList<MONAN> ds_ma = new DAOMonAn().selectAll();
//		for(int i = 0; i<ds_ma.size() ; i++) {
//			System.out.println(ds_ma.get(i).getName());
//		}
		
//		ArrayList<KHUYENMAI> ds_km = new DAOKhuyenMai().selectAll();
//		for (int i =0; i<ds_km.size(); i++) {
//			DateFormat df= new SimpleDateFormat("dd/MM/Y");
//			System.out.println(df.format(ds_km.get(i).getNgay_bat_dau()));
//		}
		
//		ArrayList<MONAN_KHUYENMAI> ds_ma_km = new DAOChiTiet_MA_KM().selectByIdKhuyenMai("KM223");
//		for(int i = 0;i<ds_ma_km.size();i++) {
//			System.out.println(ds_ma_km.get(i).getId_mon_an() + " " + ds_ma_km.get(i).getPhan_tram_giam());
//		}
		
		Date ngay1 = new Date(System.currentTimeMillis());
		DONHANG dh = new DONHANG("2", "NV001", ngay1, 30000.0, "Khách", "0909090909", "123 Lý Thường Kiệt", 32, "Đang xử lý");
		new DAODonHang().insert(dh);
		
		
	}
}
