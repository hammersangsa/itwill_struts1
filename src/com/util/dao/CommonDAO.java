package com.util.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface CommonDAO {

	//데이터 추가
	public void insertData(String id, Object value) throws SQLException;
		
	//데이터 수정
	public int updateData(String id, Object value) throws SQLException;//하나만
	public int updateData(String id, Map<String, Object> map) throws SQLException;//검색
	
	//데이터 삭제
	public int deleteData(String id, Object value) throws SQLException;
	public int deleteData(String id, Map<String, Object> map) throws SQLException;
	public int deleteAllData(String id) throws SQLException;
	
	//데이터 한개 가져오기
	public Object getReadData(String id);
	public Object getReadData(String id, Object value);
	public Object getReadData(String id, Map<String, Object> map);
	
	//데이터 한개(숫자) 가져오기
	public int getIntValue(String id);
	public int getIntValue(String id, Object value);
	public int getIntValue(String id, Map<String, Object> map);
	
	//데이터 전체 가져오기
	public List<Object> getListData(String id);
	public List<Object> getListData(String id, Object value);
	public List<Object> getListData(String id, Map<String, Object> map);
	
		
	
	
	
	
}
