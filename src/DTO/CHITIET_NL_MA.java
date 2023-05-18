package DTO;

public class CHITIET_NL_MA {
	private String id_nguyen_lieu;
	private String id_mon_an;
	private int soluong;
	
	public CHITIET_NL_MA(String id_nguyen_lieu, String id_mon_an, int soluong) {
		super();
		this.id_nguyen_lieu = id_nguyen_lieu;
		this.id_mon_an = id_mon_an;
		this.soluong = soluong;
	}

	public String getId_nguyen_lieu() {
		return id_nguyen_lieu;
	}

	public void setId_nguyen_lieu(String id_nguyen_lieu) {
		this.id_nguyen_lieu = id_nguyen_lieu;
	}

	public String getId_mon_an() {
		return id_mon_an;
	}

	public void setId_mon_an(String id_mon_an) {
		this.id_mon_an = id_mon_an;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	
}
