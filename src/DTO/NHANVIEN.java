package DTO;

public class NHANVIEN {
	private String id;
	private String chuc_vu;
	private String ho_ten;
	private String so_dien_thoai;
	private String vi_tri_lam_viec;
	private String tt_xoa;
	
	
	public NHANVIEN(String id, String chuc_vu, String ho_ten, String so_dien_thoai, String vi_tri_lam_viec,
			String tt_xoa) {
		this.id = id;
		this.chuc_vu = chuc_vu;
		this.ho_ten = ho_ten;
		this.so_dien_thoai = so_dien_thoai;
		this.vi_tri_lam_viec = vi_tri_lam_viec;
		this.tt_xoa = tt_xoa;
	}

	public NHANVIEN(String id, String chuc_vu, String ho_ten, String so_dien_thoai, String vi_tri_lam_viec) {
		this.id = id;
		this.chuc_vu = chuc_vu;
		this.ho_ten = ho_ten;
		this.so_dien_thoai = so_dien_thoai;
		this.vi_tri_lam_viec = vi_tri_lam_viec;
		this.tt_xoa = "0";
	}
	
	public NHANVIEN() {}
	@Override
	public String toString() {
		return "NHANVIEN [id=" + id + ", chuc_vu=" + chuc_vu + ", ho_ten=" + ho_ten + ", so_dien_thoai=" + so_dien_thoai
				+ ", vi_tri_lam_viec=" + vi_tri_lam_viec + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getChuc_vu() {
		return chuc_vu;
	}

	public void setChuc_vu(String chuc_vu) {
		this.chuc_vu = chuc_vu;
	}

	public String getHo_ten() {
		return ho_ten;
	}

	public void setHo_ten(String ho_ten) {
		this.ho_ten = ho_ten;
	}

	public String getSo_dien_thoai() {
		return so_dien_thoai;
	}

	public void setSo_dien_thoai(String so_dien_thoai) {
		this.so_dien_thoai = so_dien_thoai;
	}

	public String getVi_tri_lam_viec() {
		return vi_tri_lam_viec;
	}

	public void setVi_tri_lam_viec(String vi_tri_lam_viec) {
		this.vi_tri_lam_viec = vi_tri_lam_viec;
	}
	public String getTt_xoa() {
		return tt_xoa;
	}
	public void setTt_xoa(String tt_xoa) {
		this.tt_xoa = tt_xoa;
	}

	
	
	
	
}
