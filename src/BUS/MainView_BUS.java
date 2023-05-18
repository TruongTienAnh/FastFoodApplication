package BUS;

import java.util.ArrayList;

import DAO.DAONhanVien;
import DTO.NHANVIEN;
import GUI.MainView;

public class MainView_BUS {
	public String defind_user(MainView main_view) {
		NHANVIEN nv = new DAONhanVien().getNhanVienByID(main_view.MaNV);
		main_view.setTitle("MainView - "+nv.getHo_ten());
		return nv.getChuc_vu();
		
	}
}
