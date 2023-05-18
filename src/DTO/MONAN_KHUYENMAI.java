package DTO;

public class MONAN_KHUYENMAI {
	private String id_mon_an;
	private String id_khuyen_mai;
	private int phan_tram_giam;
	
	public MONAN_KHUYENMAI(String id_mon_an, String id_khuyen_mai, int phan_tram_giam) {
		this.id_mon_an = id_mon_an;
		this.id_khuyen_mai = id_khuyen_mai;
		this.phan_tram_giam = phan_tram_giam;
	}

	public String getId_mon_an() {
		return id_mon_an;
	}

	public void setId_mon_an(String id_mon_an) {
		this.id_mon_an = id_mon_an;
	}

	public String getId_khuyen_mai() {
		return id_khuyen_mai;
	}

	public void setId_khuyen_mai(String id_khuyen_mai) {
		this.id_khuyen_mai = id_khuyen_mai;
	}

	public int getPhan_tram_giam() {
		return phan_tram_giam;
	}

	public void setPhan_tram_giam(int phan_tram_giam) {
		this.phan_tram_giam = phan_tram_giam;
	}
	
	
	
	
	
}
