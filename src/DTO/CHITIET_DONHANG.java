package DTO;

public class CHITIET_DONHANG {
	private String id_mon_an;
	private String id_don_hang;
	private double don_gia;
	private int so_luong;
	
	public CHITIET_DONHANG(String id_don_hang, String id_mon_an,  double don_gia, int so_luong) {
		this.id_mon_an = id_mon_an;
		this.id_don_hang = id_don_hang;
		this.don_gia = don_gia;
		this.so_luong = so_luong;
	}

	public String getId_mon_an() {
		return id_mon_an;
	}

	public void setId_mon_an(String id_mon_an) {
		this.id_mon_an = id_mon_an;
	}

	public String getId_don_hang() {
		return id_don_hang;
	}

	public void setId_don_hang(String id_don_hang) {
		this.id_don_hang = id_don_hang;
	}

	public double getDon_gia() {
		return don_gia;
	}

	public void setDon_gia(double don_gia) {
		this.don_gia = don_gia;
	}

	public int getSo_luong() {
		return so_luong;
	}

	public void setSo_luong(int so_luong) {
		this.so_luong = so_luong;
	}
	
	
	
}
