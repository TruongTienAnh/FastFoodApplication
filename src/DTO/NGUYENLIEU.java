package DTO;

public class NGUYENLIEU {
	private String id_nguyen_lieu;
	private String ten_nguyen_lieu;
	private String don_vi;
	private int so_luong;
	
	public NGUYENLIEU(String id_nguyen_lieu, String id_mon_an, String don_vi, int so_luong) {
		this.id_nguyen_lieu = id_nguyen_lieu;
		this.ten_nguyen_lieu = id_mon_an;
		this.don_vi = don_vi;
		this.so_luong = so_luong;
	}

	public String getId_nguyen_lieu() {
		return id_nguyen_lieu;
	}

	public void setId_nguyen_lieu(String id_nguyen_lieu) {
		this.id_nguyen_lieu = id_nguyen_lieu;
	}

	public String getId_mon_an() {
		return ten_nguyen_lieu;
	}

	public void setId_mon_an(String id_mon_an) {
		this.ten_nguyen_lieu = id_mon_an;
	}

	public String getDon_vi() {
		return don_vi;
	}

	public void setDon_vi(String don_vi) {
		this.don_vi = don_vi;
	}

	public int getSo_luong() {
		return so_luong;
	}

	public void setSo_luong(int so_luong) {
		this.so_luong = so_luong;
	}
	
	
	
	
}
