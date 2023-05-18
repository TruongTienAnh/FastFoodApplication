package DTO;

public class THONGKE {
	private int STT;
	private String date;
	private double doanhThu;
	public int getSTT() {
		return STT;
	}
	public void setSTT(int sTT) {
		STT = sTT;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getDoanhThu() {
		return doanhThu;
	}
	public void setDoanhThu(double doanhThu) {
		this.doanhThu = doanhThu;
	}
	public THONGKE(int sTT, String date, double doanhThu) {
		super();
		STT = sTT;
		this.date = date;
		this.doanhThu = doanhThu;
	}
	
}
