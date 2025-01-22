package com.spring.app.index.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spring.app.index.service.IndexService;

import jakarta.servlet.http.HttpServletRequest;

/*
	사용자 웹브라우저 요청(View) <==> Application 클래스(MyspringApplication) ==> @Controller 클래스 <==>> Service단(핵심업무로직단, business logic단) <==>> Model단[Repository](DAO, DTO) <==>> myBatis <==>> DB(오라클)           
	(http://localhost:9090/myspring/...  )   ↑                                    |                                                                                                                              
	                                         |                              View Resolver
	                                         |                                    ↓
	                                         |                              View단(.jsp 또는 Bean명)
	                                         -------------------------------------|  
	
	사용자(클라이언트)가 웹브라우저에서 http://localhost:9090/myspring/index 을 실행하면
	Application 클래스인 com.spring.app.MyspringApplication 이 작동된다.
	MyspringApplication 은 bean 으로 등록된 객체중 controller 빈을 찾아서 URL값이 "/myspring/index" 로
	매핑된 메소드를 실행시키게 된다.                                               
	Service(서비스)단 객체를 업무 로직단(비지니스 로직단)이라고 부른다.
	Service(서비스)단 객체가 하는 일은 Model단에서 작성된 데이터베이스 관련 여러 메소드들 중 관련있는것들만을 모아 모아서
	하나의 트랜잭션 처리 작업이 이루어지도록 만들어주는 객체이다.
	여기서 업무라는 것은 데이터베이스와 관련된 처리 업무를 말하는 것으로 Model 단에서 작성된 메소드를 말하는 것이다.
	이 서비스 객체는 @Controller 단에서 넘겨받은 어떤 값을 가지고 Model 단에서 작성된 여러 메소드를 호출하여 실행되어지도록 해주는 것이다.
	실행되어진 결과값을 @Controller 단으로 넘겨준다.
*/

// ==== #1. 컨트롤러 선언 ====
// @Component
/* 클래스명 앞에 @Component 어노테이션을 적어주면 해당 클래스는 bean으로 자동 등록된다. 
   그리고 bean의 이름은 해당 클래스명에서 첫글자만 소문자로 바뀐 이름으로 되어진다. 
   즉, 여기서 bean의 이름은 beginController 인 것이다. 
   여기서 @Controller 를 사용하므로 @Controller 에는 @Component 기능이 이미 포함되어 있으므로 @Component를 명기하지 않아도 BeginController 는 bean 으로 등록되어 스프링컨테이너가 자동적으로 관리해준다. 
*/
@Controller
@RequestMapping(value="/*")
public class IndexController {

	// === 의존객체 주입하기(DI: Dependency Injection) ===
    // ※ 의존객체주입(DI : Dependency Injection) 
	//  ==> 스프링은 객체를 관리해주는 컨테이너를 제공해주고 있다.
	//      스프링 컨테이너는 bean으로 등록되어진 IndexController 클래스 객체가 사용되어질때, 
	//      IndexController 클래스의 인스턴스 객체변수(의존객체)인 IndexService service 에 
	//      자동적으로 bean 으로 등록되어 생성되어진 IndexService service 객체를  
	//      IndexController 클래스의 인스턴스 변수 객체로 사용되어지게끔 넣어주는 것을 의존객체주입(DI : Dependency Injection)이라고 부른다. 
	//      이것이 바로 IoC(Inversion of Control == 제어의 역전) 인 것이다.
	//      즉, 개발자가 인스턴스 변수 객체를 필요에 의해 생성해주던 것에서 탈피하여 스프링은 컨테이너에 객체를 담아 두고, 
	//      필요할 때에 컨테이너로부터 객체를 가져와 사용할 수 있도록 하고 있다. 
	//      스프링은 객체의 생성 및 생명주기를 관리할 수 있는 기능을 제공하고 있으므로, 더이상 개발자에 의해 객체를 생성 및 소멸하도록 하지 않고
	//      객체 생성 및 관리를 스프링 프레임워크가 가지고 있는 객체 관리기능을 사용하므로 Inversion of Control == 제어의 역전 이라고 부른다.  
	//      그래서 스프링 컨테이너를 IoC 컨테이너라고도 부른다.
	
	//  IOC(Inversion of Control) 란 ?
	//  ==> 스프링은 사용하고자 하는 객체를 빈형태로 이미 만들어 두고서 컨테이너(Container)에 넣어둔후
	//      필요한 객체사용시 컨테이너(Container)에서 꺼내어 사용하도록 되어있다.
	//      이와 같이 객체 생성 및 소멸에 대한 제어권을 개발자가 하는것이 아니라 스프링 Container 가 하게됨으로써 
	//      객체에 대한 제어역할이 개발자에게서 스프링 Container로 넘어가게 됨을 뜻하는 의미가 제어의 역전 
	//      즉, IOC(Inversion of Control) 이라고 부른다.
	
	
	//  === 느슨한 결합 ===
	//      스프링 컨테이너가 IndexController 객체에서 IndexService 객체를 사용할 수 있도록 
	//      만들어주는 것을 "느슨한 결합" 이라고 부른다.
	//      느스한 결합은 IndexController 객체가 메모리에서 삭제되더라도 IndexService service 객체는 메모리에서 동시에 삭제되는 것이 아니라 남아 있다.
	
	// ===> 단단한 결합(개발자가 인스턴스 변수 객체를 필요에 의해서 생성해주던 것)
	// private IndexService service = new IndexService_imple(); 
	// ===> IndexController 객체가 메모리에서 삭제 되어지면  IndexService service 객체는 멤버변수(필드)이므로 메모리에서 자동적으로 삭제되어진다.
	
	
	// === #6. 의존객체 주입하기(DI: Dependency Injection) === 
	@Autowired  // Type에 따라 알아서 Bean 을 주입해준다.
	private IndexService service;
	
	
	// === #7. 메인 페이지 요청 === //
	@GetMapping("/")   // http://localhost:9090/myspring/
	public String main() {
		return "redirect:/index";  //  http://localhost:9090/myspring/index
	}
	
	@GetMapping("index")  //  http://localhost:9090/myspring/index
	public String index(HttpServletRequest request) {
		
		List<Map<String, String>> mapList = service.getImgfilenameList();
		
		request.setAttribute("mapList", mapList);
		
		return "mycontent1/main/index";
		//   /WEB-INF/views/mycontent1/main/index.jsp 페이지를 만들어야 한다.
	}
	
	
	
	
}
