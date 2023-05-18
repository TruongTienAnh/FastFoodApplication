package DTO;

import java.util.Date;

public class KHUYENMAI {
	private String id_khuyen_mai;
	private String ten_khuyen_mai;
	private Date ngay_bat_dau;
	private Date ngay_ket_thuc;
	private String mo_ta;
	
	public KHUYENMAI(String id_khuyen_mai, String ten_khuyen_mai, Date ngay_bat_dau, Date ngay_ket_thuc, String mo_ta) {
		this.id_khuyen_mai = id_khuyen_mai;
		this.ten_khuyen_mai = ten_khuyen_mai;
		this.ngay_bat_dau = ngay_bat_dau;
		this.ngay_ket_thuc = ngay_ket_thuc;
		this.mo_ta = mo_ta;
	}

	public String getId_khuyen_mai() {
		return id_khuyen_mai;
	}

	public void setId_khuyen_mai(String id_khuyen_mai) {
		this.id_khuyen_mai = id_khuyen_mai;
	}

	public String getTen_khuyen_mai() {
		return ten_khuyen_mai;
	}

	public void setTen_khuyen_mai(String ten_khuyen_mai) {
		this.ten_khuyen_mai = ten_khuyen_mai;
	}

	public Date getNgay_bat_dau() {
		return ngay_bat_dau;
	}

	public void setNgay_bat_dau(Date ngay_bat_dau) {
		this.ngay_bat_dau = ngay_bat_dau;
	}

	public Date getNgay_ket_thuc() {
		return ngay_ket_thuc;
	}

	public void setNgay_ket_thuc(Date ngay_ket_thuc) {
		this.ngay_ket_thuc = ngay_ket_thuc;
	}

	public String getMo_ta() {
		return mo_ta;
	}

	public void setMo_ta(String mo_ta) {
		this.mo_ta = mo_ta;
	}
	
	
	
}
