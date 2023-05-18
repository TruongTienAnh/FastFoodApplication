package BUS;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import DAO.DAOChiTiet_DH;
import DAO.DAOChiTiet_MA_KM;
import DAO.DAOChiTiet_NL_MA;
import DAO.DAODonHang;
import DAO.DAOKhuyenMai;
import DAO.DAONguyenLieu;
import DTO.CHITIET_DONHANG;
import DTO.CHITIET_NL_MA;
import DTO.DONHANG;
import DTO.KHUYENMAI;
import DTO.MONAN;
import DTO.MONAN_KHUYENMAI;
import GUI.CartView;
import GUI.QuantityPane;
public class CartView_BUS {
	private static CartView cartView;
	
	public CartView_BUS(CartView cartview) {
		cartView = cartview;
	}
	
	
	public static void displayCartItems() {
		DefaultTableModel model = (DefaultTableModel) cartView.table.getModel();
		model.setRowCount(0);
		ArrayList<MONAN> od_list = cartView.sale_view.odvf.Order_list;
		ArrayList<String> si_list = cartView.sale_view.odvf.Size;
		ArrayList<String> up_list = cartView.sale_view.odvf.Unitprice;
		ArrayList<String> q_list = cartView.sale_view.odvf.Quantity;
		ArrayList<String> t_list = cartView.sale_view.odvf.Total;
		for(int i = 0;i<od_list.size();i++) {
			model.addRow(new Object[] {i+1,
					od_list.get(i).getImage(),
					od_list.get(i).getName(),
					si_list.get(i),
					up_list.get(i),
					q_list.get(i),
					t_list.get(i)});
		}
	}
	
	public void displaySubTotal() {
		ArrayList<String> t_list = cartView.sale_view.odvf.Total;
		double subTotal = 0.0;
		for(int i = 0; i< t_list.size();i++) {
			subTotal += Double.parseDouble(t_list.get(i));
		}
		cartView.subTotalLabel.setText(subTotal+" VND");
		cartView.subTotal = subTotal;
	}
	
	public QuantityEvent qe = new QuantityEvent() {
		
		@Override
		public void onIncrease(int row, QuantityPane qp) {
			int newQuantity = Integer.parseInt(cartView.sale_view.odvf.Quantity.get(row))+1;
			System.out.println(newQuantity);
			boolean kt_nguyen_lieu = cartView.sale_view.odvf.kiemTraNguyenLieu(cartView.sale_view.odvf.Order_list.get(row).getId(),1);
			
			if(kt_nguyen_lieu) {
				cartView.sale_view.odvf.Quantity.set(row, newQuantity +"");
				System.out.println(cartView.sale_view.odvf.Quantity.get(row));
				double currentUnitPrice = Double.parseDouble(cartView.sale_view.odvf.Unitprice.get(row));
				cartView.sale_view.odvf.Total.set(row, String.valueOf(currentUnitPrice*newQuantity));
				displayCartItems();
				cartView.sale_view.odvf.truNguyenLieuTrongGioHang();
				qp.textField_1.setText(cartView.sale_view.odvf.Quantity.get(row));
				new CartView_BUS(cartView).displaySubTotal();
			}
			else {
				JOptionPane.showMessageDialog(null, "Không đủ nguyên liệu");
			}
		}
		
		@Override
		public void onDecrease(int row, QuantityPane qp) {
			int newQuantity = Integer.parseInt(cartView.sale_view.odvf.Quantity.get(row));
			
			if(Integer.parseInt(cartView.sale_view.odvf.Quantity.get(row))>0)
				newQuantity = Integer.parseInt(cartView.sale_view.odvf.Quantity.get(row))-1;
			cartView.sale_view.odvf.Quantity.set(row,newQuantity  +"");
			
			double currentUnitPrice = Double.parseDouble(cartView.sale_view.odvf.Unitprice.get(row));
			cartView.sale_view.odvf.Total.set(row, String.valueOf(currentUnitPrice*newQuantity));
			new CartView_BUS(cartView).displayCartItems();
			cartView.sale_view.odvf.truNguyenLieuTrongGioHang();
			qp.textField_1.setText(cartView.sale_view.odvf.Quantity.get(row));
			new CartView_BUS(cartView).displaySubTotal();
		}
	};
	
	public String[] LayKhuyenMai() {
		ArrayList<KHUYENMAI> ds_km = new DAOKhuyenMai().selectAll();
		String[] ds_ten_km = new String[ds_km.size()+1];
		ds_ten_km[0] = "--------";
		for (int i =0; i<ds_km.size();i++) {
			ds_ten_km[i+1] = ds_km.get(i).getId_khuyen_mai();
		}
		return ds_ten_km;
	}
	
	public void apDungKhuyenMai(String IDKhuyenMai) {
		if(IDKhuyenMai.equalsIgnoreCase("--------")) {
			for(int i = 0; i<cartView.sale_view.odvf.Order_list.size();i++) {
				double currentUnitPrice = cartView.sale_view.odvf.Order_list.get(i).getUnitPrice();
				cartView.sale_view.odvf.Unitprice.set(i,String.valueOf(currentUnitPrice));
				cartView.sale_view.odvf.Total.set(i,String.valueOf(currentUnitPrice * Integer.parseInt(cartView.sale_view.odvf.Quantity.get(i))));
			}
		}else {
			ArrayList <MONAN_KHUYENMAI> ds_ma_km = new DAOChiTiet_MA_KM().selectByIdKhuyenMai(IDKhuyenMai);
			
			for(int i = 0; i<cartView.sale_view.odvf.Order_list.size();i++) {
				MONAN mon = cartView.sale_view.odvf.Order_list.get(i);
				for (int j =0; j<ds_ma_km.size(); j++) {
					if(mon.getId().equalsIgnoreCase(ds_ma_km.get(j).getId_mon_an())) {
						double currentUnitPrice = Double.parseDouble(cartView.sale_view.odvf.Unitprice.get(i));
						double new_unit_price = currentUnitPrice * (100 - ds_ma_km.get(j).getPhan_tram_giam())*1.0/100;
						cartView.sale_view.odvf.Unitprice.set(i,String.valueOf(new_unit_price));
						cartView.sale_view.odvf.Total.set(i,String.valueOf(new_unit_price * Integer.parseInt(cartView.sale_view.odvf.Quantity.get(i))));
					}
				}
			} 
		}	
		displayCartItems();
		displaySubTotal();
	}
	
	public void ThanhToan() {
		if(cartView.sale_view.odvf.Order_list.size() == 0) {
			JOptionPane.showMessageDialog(null, "Giỏ hàng đang rỗng");
			return ;
		}
		
		String id_don_hang = "DH" + (new DAODonHang().selectAll().size()+1);
		String id_nhan_vien = cartView.sale_view.pw.MaNV;
		Date ngay_dat_hang = new Date(System.currentTimeMillis());
		double tong_tien = cartView.subTotal;
		
		String ten = null;
		String so_dien_thoai = null;
		String dia_chi = null;
		
		int so_dat_mon = 0;
		
		if(cartView.comboBox.getSelectedItem().toString().equalsIgnoreCase("Tại chỗ")) {
			try {
				so_dat_mon = Integer.parseInt(cartView.soNhanMonTxt.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập vào số đặt món");
				return ;
			}
			
		}else if(cartView.comboBox.getSelectedItem().toString().equalsIgnoreCase("Mang đi")){
			ten = cartView.TenTxt.getText();
			try {
				int sdt_kt = Integer.parseInt(cartView.SDTTxt.getText());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Vui lòng số điện thoại hợp lệ");
				return ;
			}
			so_dien_thoai = cartView.SDTTxt.getText();
			dia_chi = cartView.DiaChiTxt.getText();
			
			if(ten.equalsIgnoreCase("") || so_dien_thoai.equalsIgnoreCase("") || dia_chi.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin nhận hàng");
				return ;
			}
		}
		
		// nhập đơn hàng
		DONHANG don_hang = new DONHANG(id_don_hang, id_nhan_vien, ngay_dat_hang, tong_tien, ten, so_dien_thoai, dia_chi, so_dat_mon, "Đang xử lý");
		new DAODonHang().insert(don_hang);
		
		for (int i = 0 ;i < cartView.sale_view.odvf.Order_list.size(); i++) {
			String id_mon_an = cartView.sale_view.odvf.Order_list.get(i).getId();
			double gia_ban = Double.parseDouble(cartView.sale_view.odvf.Unitprice.get(i));
			int so_luong = Integer.parseInt(cartView.sale_view.odvf.Quantity.get(i));
			
			// nhập chi tiết hóa đơn
			CHITIET_DONHANG ct_dh = new CHITIET_DONHANG(id_don_hang, id_mon_an, gia_ban, so_luong);
			new DAOChiTiet_DH().insert(ct_dh);
			
			// trừ nguyên liệu
			ArrayList<CHITIET_NL_MA> ds_ct_nl = new DAOChiTiet_NL_MA().selectByIdMonAn(id_mon_an);
			for (int j = 0; j<ds_ct_nl.size();j++) {
				new DAONguyenLieu().TruNguyenLieu(ds_ct_nl.get(j).getId_nguyen_lieu(), ds_ct_nl.get(j).getSoluong()*so_luong);
			}
			
		}
		
		JOptionPane.showMessageDialog(null, "Đã thanh toán");
		// refresh giỏ hàng
		cartView.sale_view.odvf.Order_list.clear();
		cartView.sale_view.odvf.Size.clear();
		cartView.sale_view.odvf.Unitprice.clear();
		cartView.sale_view.odvf.Quantity.clear();
		cartView.sale_view.odvf.Total.clear();
		
		cartView.soNhanMonTxt.setText("");
		cartView.TenTxt.setText("");
		cartView.DiaChiTxt.setText("");
		cartView.SDTTxt.setText("");
		cartView.comboBox.setSelectedIndex(0);
		cartView.comboBox_1.setSelectedIndex(0);
		
		displayCartItems();
		displaySubTotal();
	}
}
