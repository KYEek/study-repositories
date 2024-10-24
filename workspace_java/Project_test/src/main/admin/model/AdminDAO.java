package main.admin.model;

import java.util.Map;

public interface AdminDAO {
	
	//관리자 로그인 
	boolean login(Map<String, String> login);

}
