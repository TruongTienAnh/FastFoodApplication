package BUS;

import java.util.ArrayList;
import java.util.List;

import DAO.FoodManager_DAO;
import DTO.CHITIET_NL_MA;
import DTO.KHUYENMAI;
import DTO.MONAN;
import DTO.MONAN_KHUYENMAI;

public class FoodManager_BUS {
	FoodManager_DAO foodDAO = new FoodManager_DAO();
	public List<MONAN> getAllfood() {
		return foodDAO.getAllfood();
	}
	public void addfoods(MONAN monan) {
		foodDAO.addfood(monan);
	}
	public void updatefoods(MONAN monan) {
		foodDAO.updatefood(monan);
	}
	public void deletefoods(String id) {
		foodDAO.deletefood(id);
	}
	public String[] getIdArray() {
	      ArrayList<String> list = foodDAO.getIdList();
	      return list.toArray(new String[0]);
	}
	
	public List<MONAN> getFoodbysearch(String condition) {
		return foodDAO.getFoodbysearch(condition);
	}
	public List<MONAN> getFoodintoPrice(String sortOrder) {
		return foodDAO.getFoodintoPrice(sortOrder);
	}
	
	
	
	
	
	public List<KHUYENMAI> getAllkhuyenmai() {
		return foodDAO.getAllkhuyenmai();
	}
	public void addkhuyenmai(KHUYENMAI khuyenmai) {
		foodDAO.addkhuyenmai(khuyenmai);
	}
	public void updatekhuyenmai(KHUYENMAI khuyenmai) {
		foodDAO.updatekhuyenmai(khuyenmai);
	}
	public void deletekhuyenmai(String id) {
		foodDAO.deletekhuyenmai(id);
	}
	public String[] getIdkhuyenmaiArray() {
	      ArrayList<String> list = foodDAO.getIdListkhuyenmai();
	      return list.toArray(new String[0]);
	}
	
	public List<KHUYENMAI> getKhuyenmaibysearch(String condition) {
		return foodDAO.getKhuyenmaibysearch(condition);
	}
	
	
	
	
	
	public List<MONAN_KHUYENMAI> getAllMA_KhuyenMai() {
		return foodDAO.getAllMA_KhuyenMai();
	}
	public void addMA_KhuyenMai(MONAN_KHUYENMAI monan_KHUYENMAI) {
		foodDAO.addMA_KhuyenMai(monan_KHUYENMAI);
	}
	public void updateMA_KhuyenMai(MONAN_KHUYENMAI monan_KHUYENMAI) {
		foodDAO.updateMA_KhuyenMai(monan_KHUYENMAI);
	}
	public void deleteMA_KhuyenMai(String id, String id1) {
		foodDAO.deleteMA_KhuyenMai(id, id1);
	}
	public String[] getIdfood(String idKMAI) {
	      ArrayList<String> list = foodDAO.getIdfood(idKMAI);
	      return list.toArray(new String[0]);
	}
	public String[] getIdKm() {
	      ArrayList<String> list = foodDAO.getIdKm();
	      return list.toArray(new String[0]);
	}
	public List<MONAN_KHUYENMAI> getMAKhuyenmaibysearch(String condition) {
		return foodDAO.getMAKhuyenmaibysearch(condition);
	}
	public List<MONAN_KHUYENMAI> getKhuyeMaiMAinto(String sortOrder) {
		return foodDAO.getKhuyeMaiMAinto(sortOrder);
	}
	///////////////////////////////////////////////////////
	public List<CHITIET_NL_MA> getAllCT_NL_MA() {
		return foodDAO.getAllCT_NL_MA();
	}
	
	public String[] getIDNL() {
	      ArrayList<String> list = foodDAO.getIDNL();
	      return list.toArray(new String[0]);
	}
	public void addCT_NL_MA(CHITIET_NL_MA chitiet_NL_MA) {
		foodDAO.addCT_NL_MA(chitiet_NL_MA);
	}
	public void updateCT_NL_MA(CHITIET_NL_MA chitiet_NL_MA) {
		foodDAO.updateCT_NL_MA(chitiet_NL_MA);
	}
	public void deleteNLMA(String id, String id1) {
		foodDAO.deleteNLMA(id, id1);
	}
	public Integer[] getIdfoodnl() {
	      ArrayList<Integer> list = foodDAO.getIdfoodnl();
	      return list.toArray(new Integer[0]);
	}
	public String[] getIdnl(String idMAN) {
	      ArrayList<String> list = foodDAO.getIdnl(idMAN);
	      return list.toArray(new String[0]);
	}
	public List<CHITIET_NL_MA> getCTNLbysearch(String condition) {
		return foodDAO.getCTNLbysearch(condition);
	}
	public List<CHITIET_NL_MA> getNGUYENLIEUMAinto(String sortOrder) {
		return foodDAO.getNGUYENLIEUMAinto(sortOrder);
	}
}


