package com.spring.app.begin.service;

import java.util.List;

import com.spring.app.begin.domain.BeginVO;

public interface BeginService {
	
	//spring_test 테이블에 insert 하기
	int insert();

	// spring_test 테이블에 select 하기
	List<BeginVO> select();

}
