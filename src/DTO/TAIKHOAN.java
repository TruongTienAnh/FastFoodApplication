package DTO;

public class TAIKHOAN {
	private String id_tai_khoan;
	private String id_nhan_vien;
	private String ten_dang_nhap;
	private String mat_khau;
	private String tinh_trang;
	private String tt_xoa;
	public void setTt_xoa(String tt_xoa) {
		this.tt_xoa = tt_xoa;
	}
	public TAIKHOAN(String id_tai_khoan, String id_nhan_vien, String ten_dang_nhap, String mat_khau, String tinh_trang) {
		this.id_tai_khoan = id_tai_khoan;
		this.id_nhan_vien = id_nhan_vien;
		this.ten_dang_nhap = ten_dang_nhap;
		this.mat_khau = mat_khau;
		this.tinh_trang = tinh_trang;
		
	}
	public TAIKHOAN(String id_tai_khoan, String id_nhan_vien, String ten_dang_nhap, String mat_khau, String tinh_trang,
			String tt_xoa) {
		super();
		this.id_tai_khoan = id_tai_khoan;
		this.id_nhan_vien = id_nhan_vien;
		this.ten_dang_nhap = ten_dang_nhap;
		this.mat_khau = mat_khau;
		this.tinh_trang = tinh_trang;
		this.tt_xoa = tt_xoa;
	}
	public String getId_tai_khoan() {
		return id_tai_khoan;
	}

	public void setId_tai_khoan(String id_tai_khoan) {
		this.id_tai_khoan = id_tai_khoan;
	}

	public String getId_nhan_vien() {
		return id_nhan_vien;
	}

	public void setId_nhan_vien(String id_nhan_vien) {
		this.id_nhan_vien = id_nhan_vien;
	}

	public String getTen_dang_nhap() {
		return ten_dang_nhap;
	}

	public void setTen_dang_nhap(String ten_dang_nhap) {
		this.ten_dang_nhap = ten_dang_nhap;
	}

	public String getMat_khau() {
		return mat_khau;
	}

	public void setMat_khau(String mat_khau) {
		this.mat_khau = mat_khau;
	}

	public String getTinh_trang() {
		return tinh_trang;
	}

	public void setTinh_trang(String tinh_trang) {
		this.tinh_trang = tinh_trang;
	}
	public String getTt_xoa() {
		return tt_xoa;
	}

	
	
	
	
}
