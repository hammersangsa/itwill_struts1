package com.util.sqlMap;

import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapConfig {
	
	private static final SqlMapClient sqlMap;
	
	static {
		
		try {
			
			String source = "com/util/sqlMap/sqlMapConfig.xml";
			
			Reader reader = Resources.getResourceAsReader(source);
			//source의 위치를 읽어서 reader에 담는다
			sqlMap = SqlMapClientBuilder.buildSqlMapClient(reader);
			//client가 사용할 수 있는 sqlMap을 생성
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error");
		}
		
	}

	public static SqlMapClient getSqlMapInstance() {
		return sqlMap;
	}
	
}
