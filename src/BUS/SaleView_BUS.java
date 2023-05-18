package BUS;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.JPanel;

import DAO.DAOChiTiet_NL_MA;
import DAO.DAOMonAn;
import DAO.DAONguyenLieu;
import DTO.CHITIET_NL_MA;
import DTO.MONAN;
import DTO.NGUYENLIEU;
import GUI.ItemPanel;
import GUI.SaleView;

public class SaleView_BUS {
	public ArrayList<MONAN> food_list;

	private ArrayList<NGUYENLIEU> ds_nl_1;

	private ArrayList<CHITIET_NL_MA> ds_ct_nl_ma;
	
	public static ArrayList<MONAN> Order_list;
	public static ArrayList<String> Unitprice;
	public static ArrayList<String> Quantity;
	public static ArrayList<String> Total;
	public static ArrayList<String> Size;
	
	public static SaleView odv;
	
	
	public SaleView_BUS(SaleView odv) {
		this.odv = odv;
		food_list = new DAOMonAn().selectAll();
		ds_nl_1 =  new DAONguyenLieu().selectAll(); // lấy kho nguyên liệu
		ds_ct_nl_ma = new DAOChiTiet_NL_MA().selectAll2();
		Order_list = new ArrayList<MONAN>();
		Unitprice = new ArrayList<>();
		Quantity = new ArrayList<>();
		Total = new ArrayList<>();
		Size = new ArrayList<>();
		
		
		
	}
	
	public boolean kiemTraNguyenLieu(String IdMonAn, int soluong) {	
		// lấy chi tiết nguyên liệu của món đang kiểm tra
		for(int i = 0;i<ds_ct_nl_ma.size();i++) {
			CHITIET_NL_MA ct = ds_ct_nl_ma.get(i);
			if(ct.getId_mon_an().equalsIgnoreCase(IdMonAn)) {
				for(int j = 0;j<ds_nl_1.size();j++) {
					NGUYENLIEU nl = ds_nl_1.get(j);
					if(ct.getId_nguyen_lieu().equalsIgnoreCase(nl.getId_nguyen_lieu())) {
						// trừ nguyên liệu của món ăn đang kiểm tra
						if(ds_nl_1.get(j).getSo_luong() - ct.getSoluong()*soluong<0) {
							return false;
						}
					}
				}
			}
		}	
		return true;	
	}
	
	public void displayItem() {
		odv.panel = new JPanel();
		odv.panel.setBackground(new Color(82, 76, 103));
		odv.scrollPane.setViewportView(odv.panel);
		
		int size = food_list.size();
		int grid_row = (size%4==0)?size/4:(size/4+1);
		odv.panel.setLayout(new GridLayout(grid_row, 4, 5, 5));
		for(int i = 0; i<size;i++) {			
			MONAN thisf = food_list.get(i);
			ItemPanel ip = new ItemPanel(odv,thisf);
			if(!kiemTraNguyenLieu(thisf.getId(),1)) {
				ip.addToCartButton.setText("Đã bán hết");
				ip.addToCartButton.setEnabled(false);
				
			}
			odv.panel.add(ip);
		}
	
	}
	
	public void searchItem(String text) {
//		odv.panel = new JPanel();
//		odv.panel.setBackground(new Color(82, 76, 103));
//		odv.scrollPane.setViewportView(odv.panel);
		food_list = new DAOMonAn().selectByCondition(text);
	}
	
	
	public void sortByName(int opt) {
//		odv.food_list = new DAOMonAn().selectAll();
		
		odv.panel = new JPanel();
		odv.panel.setBackground(new Color(82, 76, 103));
		odv.scrollPane.setViewportView(odv.panel);
		
		if(opt == 0) {
			Collections.sort(food_list, (o1,o2)-> o1.getName().compareToIgnoreCase(o2.getName()));
		}else {
			Collections.sort(food_list, (o1,o2)-> o2.getName().compareToIgnoreCase(o1.getName()));
		}
		
		displayItem();
		
	}
	public void sortByID(int opt) {
//		odv.food_list = new DAOMonAn().selectAll();
		
		odv.panel = new JPanel();
		odv.panel.setBackground(new Color(82, 76, 103));
		odv.scrollPane.setViewportView(odv.panel);
		
		if(opt == 0) {
			Collections.sort(food_list, (o1,o2)-> o1.getId().compareToIgnoreCase(o2.getId()));
		}else {
			Collections.sort(food_list, (o1,o2)-> o2.getId().compareToIgnoreCase(o1.getId()));
		}
		
		displayItem();
		
	}
	public void sortByPrice(int opt) {
//		odv.food_list = new DAOMonAn().selectAll();
		
		odv.panel = new JPanel();
		odv.panel.setBackground(new Color(82, 76, 103));
		odv.scrollPane.setViewportView(odv.panel);
		
		if(opt == 0) {
			Collections.sort(food_list, (o1,o2)-> (int)(o1.getUnitPrice() - o2.getUnitPrice()));
		}else {
			Collections.sort(food_list, (o1,o2)-> (int)(o2.getUnitPrice() - o1.getUnitPrice()));
		}
		
		displayItem();
	}
	public void displayCartLength() {
		this.odv.CartLength.setText(this.Order_list.size()+"");
	}
	public void truNguyenLieuTrongGioHang() {
		ds_nl_1 =  new DAONguyenLieu().selectAll(); // lấy kho nguyên liệu
		for(int k = 0;k<Order_list.size();k++) {
			ArrayList<CHITIET_NL_MA> ds_ct_nl_ma = new DAOChiTiet_NL_MA().selectByIdMonAn(Order_list.get(k).getId());
			for(int i = 0;i<ds_ct_nl_ma.size();i++) {
				CHITIET_NL_MA ct = ds_ct_nl_ma.get(i);
				for(int j = 0;j<ds_nl_1.size();j++) {
					NGUYENLIEU nl = ds_nl_1.get(j);
					if(ct.getId_nguyen_lieu().equalsIgnoreCase(nl.getId_nguyen_lieu())) {
						// trừ bớt nguyên liệu của những món ăn gì đang có sẵn trong giỏ hàng
						ds_nl_1.get(j).setSo_luong(ds_nl_1.get(j).getSo_luong() - ct.getSoluong()*Integer.parseInt(Quantity.get(k)));	
					}
				}
			}
		}
	}
	public void themVaoGioHang(MONAN mon_an, double price,String size, int quantity, double total) {
		boolean available = false;
		for(int i = 0;i<Order_list.size();i++) {
			if(Order_list.get(i).getId().equalsIgnoreCase(mon_an.getId()) && 
				(Size.get(i).equals(size) || Size.get(i).equals("Regular"))) {
				available = true;
				quantity = Integer.parseInt(Quantity.get(i)) + quantity;
				total = quantity * price;
				Quantity.set(i,(""+quantity));
				Total.set(i,(""+total));
			}
		}
		if(available == false) {
			Order_list.add(mon_an);
			Unitprice.add(price+"");
			Quantity.add(quantity+"");
			Total.add(total+"");
			if(mon_an.getType().equalsIgnoreCase("Fried Chicken") || mon_an.getType().equalsIgnoreCase("Hamburger"))
				Size.add("Regular");
			else
				Size.add(size);
		}
		truNguyenLieuTrongGioHang();
		displayItem();
		displayCartLength();
	}
}
