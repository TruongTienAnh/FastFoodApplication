package DTO;
public class DON_NHAPHANG
{
	private String ID_NHANVIEN;
	private String ID_DON_NHAP_HANG;
	private String ID_NHA_CUNG_CAP;
	private int    THANHTIEN;
	private String NGAYNHAP;
	private String TRANG_THAI;
	
	public DON_NHAPHANG (String ID_DON_NHAP_HANG,String ID_NHANVIEN,String ID_NHA_CUNG_CAP,int    THANHTIEN, String NGAYNHAP,String TRANG_THAI)
	{  this.ID_NHANVIEN			=ID_NHANVIEN;
	   this.ID_DON_NHAP_HANG 	=ID_DON_NHAP_HANG;
	   this.ID_NHA_CUNG_CAP 	=ID_NHA_CUNG_CAP;
	   this.NGAYNHAP			=NGAYNHAP;
	   this.THANHTIEN			=THANHTIEN;
	   this.TRANG_THAI			=TRANG_THAI;
		
	}
	public String get_IDNhanvien() {
		
		return ID_NHANVIEN;
	}
	public void set_IDNhanvien(String ID_NHANVIEN)
	{
		this.ID_NHANVIEN=ID_NHANVIEN;
	}
	public String get_ID_DON_NHAP_HANG() {
		
		return ID_DON_NHAP_HANG;
	}
	public void set_ID_DON_NHAP_HANG(String ID_DON_NHAP_HANG)
	{
		this.ID_DON_NHAP_HANG=ID_DON_NHAP_HANG;
	}
	public String get_IDNHA_CUNG_CAP() {
		
		return ID_NHA_CUNG_CAP;
	}
	public void set_IDNHA_CUNG_CAP(String ID_NHA_CUNG_CAP)
	{
		this.ID_NHA_CUNG_CAP=ID_NHA_CUNG_CAP;
	}
	public int get_Thanhtien() {
		
		return THANHTIEN;
	}
	public void set_Thanhtien(int thanhtien)
	{
		this.THANHTIEN=THANHTIEN;
	}
	public String get_TRANG_THAI() {
		
		return TRANG_THAI;
	}
	public void set_TRANG_THAI(String TRANG_THAI)
	{
		this.TRANG_THAI=TRANG_THAI;
	}
	public String get_NGAYNHAP() {
		
		return NGAYNHAP;
	}
	public void set_NGAYNHAP(String NGAYNHAP)
	{
		this.NGAYNHAP=NGAYNHAP;
	}

	
}