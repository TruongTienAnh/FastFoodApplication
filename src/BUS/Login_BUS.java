package BUS;

import java.net.PasswordAuthentication;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import DAO.DAOTaiKhoan;
import DTO.TAIKHOAN;
import GUI.LoginView;
import GUI.MainView;

public class Login_BUS {
	private ArrayList<TAIKHOAN> ds_tk;
	public int login(LoginView login_view) {
		if(login_view.username_txt.getText().trim().equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tên tài khoản");
				return -1;
		}else if(login_view.password_txt.getText().trim().equalsIgnoreCase("")){
			JOptionPane.showMessageDialog(null, "Vui lòng nhập vào mật khẩu");
			return -1;
		}else {
			String urs_nm = login_view.username_txt.getText().trim();
			String psswrd = login_view.password_txt.getText().trim();
			ArrayList<TAIKHOAN> ds_tk = new DAOTaiKhoan().selectAll();
			
			for (int i = 0;i < ds_tk.size();i++) {
				if(urs_nm.equalsIgnoreCase(ds_tk.get(i).getTen_dang_nhap())) {
					if(ds_tk.get(i).getTinh_trang().equalsIgnoreCase("active")) {
						if(psswrd.equalsIgnoreCase(ds_tk.get(i).getMat_khau())) {
							login_view.setVisible(false);
							MainView main_view = new MainView(ds_tk.get(i).getId_nhan_vien());
							main_view.setVisible(true);
							return 1;
						}else {
							JOptionPane.showMessageDialog(null, "Sai mật khẩu");
							return 0;
						}
					}else {
						JOptionPane.showMessageDialog(null, "Tài khoản của bạn đã bị khóa");
						return 0;
					}
				}
			}
		
		}
		return -1;
	}
	
}
