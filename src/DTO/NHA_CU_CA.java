package DTO;
public class NHA_CU_CA {
	private String	idNhaCungCap;
	private String  Tennhacungcap;
	private String  Diachinhacungcap;
	private String  Sodienthoai;
	private String TRANG_THAI;
	public NHA_CU_CA (String idNhaCungCap,String  Tennhacungcap,String  Diachinhacungcap,String  Sodienthoai,String TRANG_THAI)
	{   this.idNhaCungCap	 =    idNhaCungCap;
		this.Diachinhacungcap=Diachinhacungcap;
		this.Sodienthoai     =     Sodienthoai;
		this.Tennhacungcap   =   Tennhacungcap;
		this.TRANG_THAI 	 =		TRANG_THAI;
	} 
	public String GetIDNcc() {
		return idNhaCungCap;
		
	}
	public void set_id_Ncc(String idNhaCungCap) {
		this.idNhaCungCap=idNhaCungCap;
	}
	public String GetTenNcc() {
		return Tennhacungcap;
		
	}
	public void set_Ten_Ncc(String  Tennhacungcap) {
		this.Tennhacungcap=Tennhacungcap;
		
	}
	public String GetDiaChicc() {
		return Diachinhacungcap;
		
	}
	public void set_DiaChi_Ncc(String  Diachinhacungcap) {
		this.Diachinhacungcap=Diachinhacungcap;
		
	}
	public String GetSDTNcc() {
		return Sodienthoai;
		
	}
	public void set_SDT_Ncc(String Sodienthoai) {
		this.Sodienthoai=Sodienthoai;
	}
	public String GetTRANG_THAI() {
		return TRANG_THAI;
		
	}
	public void set_TRANG_THAI(String TRANG_THAI) {
		this.TRANG_THAI=TRANG_THAI;
	}
}
