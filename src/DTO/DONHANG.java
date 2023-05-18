package DTO;

import java.util.Date;

public class DONHANG {
	private String id_don_hang;
	private String id_nhan_vien;
	private Date ngay_dat_hang;
	private double tong_tien;
	private int so_dat_mon;
	private String ten;
	private String so_dien_thoai;
	private String dia_chi;
	private String trang_thai;
	
	public DONHANG(String id_don_hang, String id_nhan_vien, Date ngay_dat_hang, double tong_tien, 
			String ten, String so_dien_thoai, String dia_chi,int so_dat_mon, String trang_thai) {
		this.id_don_hang = id_don_hang;
		this.id_nhan_vien = id_nhan_vien;
		this.ngay_dat_hang = ngay_dat_hang;
		this.tong_tien = tong_tien;
		this.so_dat_mon = so_dat_mon;
		this.ten = ten;
		this.so_dien_thoai = so_dien_thoai;
		this.dia_chi = dia_chi;
		this.trang_thai = trang_thai;
	}

	public String getId_don_hang() {
		return id_don_hang;
	}

	public void setId_don_hang(String id_don_hang) {
		this.id_don_hang = id_don_hang;
	}

	public String getId_nhan_vien() {
		return id_nhan_vien;
	}

	public void setId_nhan_vien(String id_nhan_vien) {
		this.id_nhan_vien = id_nhan_vien;
	}

	public Date getNgay_dat_hang() {
		return ngay_dat_hang;
	}

	public void setNgay_dat_hang(Date ngay_dat_hang) {
		this.ngay_dat_hang = ngay_dat_hang;
	}

	public double getTong_tien() {
		return tong_tien;
	}

	public void setTong_tien(double tong_tien) {
		this.tong_tien = tong_tien;
	}

	public int getSo_dat_mon() {
		return so_dat_mon;
	}

	public void setSo_dat_mon(int so_dat_mon) {
		this.so_dat_mon = so_dat_mon;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public String getSo_dien_thoai() {
		return so_dien_thoai;
	}

	public void setSo_dien_thoai(String so_dien_thoai) {
		this.so_dien_thoai = so_dien_thoai;
	}

	public String getDia_chi() {
		return dia_chi;
	}

	public void setDia_chi(String dia_chi) {
		this.dia_chi = dia_chi;
	}

	public String getTrang_thai() {
		return trang_thai;
	}

	public void setTrang_thai(String trang_thai) {
		this.trang_thai = trang_thai;
	}
	
	
	
	
}
