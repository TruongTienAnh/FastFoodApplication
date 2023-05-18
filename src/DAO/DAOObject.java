package DAO;

import java.sql.Connection;
import java.util.ArrayList;

public interface DAOObject <T>{
	public boolean insert(T t);
	public boolean update(T t);
	public boolean delete(T t);
	public ArrayList<T> selectAll();
	public ArrayList<T> selectByCondition(String condition);
}
