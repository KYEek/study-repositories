package com.spring.app.springscheduler.service;

public interface SpringschedulerService {

	// === #209. Spring Scheduler(스프링스케줄러) 연습(매 1분마다 DB에 insert 하기) ===
	// !!<주의>!! 스프링스케줄러로 사용되는 메소드는 반드시 리턴타입은 void 이어야 하고, 파라미터가 없어야 한다.!!!!!!!!!!
	void test_Scheduler_insert();
	
}
