package com.spring.app.begin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.spring.app.begin.domain.BeginVO;
import com.spring.app.begin.model.BeginDAO;

//==== #2. Service 선언 ====
//트랜잭션 처리를 담당하는 곳, 업무를 처리하는 곳, 비지니스(Business)단
//@Component
@Service
public class BeginService_imple implements BeginService {

	// === #5. 의존객체 주입하기(DI: Dependency Injection) ===
	@Autowired
	private BeginDAO dao; // Type에 따라 알아서 Bean 을 주입해준다.
	// Type 에 따라 Spring 컨테이너가 알아서 bean 으로 등록된 com.spring.app.begin.model.BeginDAO_imple 의 bean 을  dao 에 주입시켜준다.  
    // 그러므로 dao 는 null 이 아니다.
	
	@Override
	public int insert() {
		int n = dao.insert();
		return n;
	}

	@Override
	public List<BeginVO> select() {
		List<BeginVO> beginvoList = dao.select();
		
		return beginvoList;
	}

}
