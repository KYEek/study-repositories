package member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import member.domain.MemberVO;
import member.model.*;


public class MemberList extends AbstractController {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		MemberDAO mdao = new MemberDAO_imple();
		
		//관리자로 로그인 했을 때만 회원조회가 가능하게
		HttpSession session = request.getSession();
		
		MemberVO loginuser = (MemberVO) session.getAttribute("loginuser");
		
		if(loginuser != null&& "admin".equals(loginuser.getUserid())){
			//관리자로 로그인 했을 경우
			String sizePerPage = request.getParameter("sizePerPage");
			String currentShowPageNo = request.getParameter("currentShowPageNo");
			
			
//			System.out.println("~~~확인용 sizePage");
			if(sizePerPage == null) {
				sizePerPage = "10";
			}
			if(currentShowPageNo==null) {
				currentShowPageNo = "1";
			}
			
			Map<String, String> paraMap = new HashMap<>();
			paraMap.put("sizePerPage", sizePerPage);
			paraMap.put("currentShowPageNo", currentShowPageNo);// 한페이지당 보여줄 행의 개수
			
			//페이징 처리를 안한 모든 회원 또는 검색결과 보여주기
//			List<MemberVO> memberList = mdao.select_Member_nopaging();
			
//			페이징 처리한 모든 회원 목록 또는 검색되어진 회원 목록 조회
			List<MemberVO> memberList = mdao.select_Member_paging(paraMap);
			request.setAttribute("memberList", memberList);
			request.setAttribute("sizePerPage", sizePerPage);
			
			super.setRedirect(false);
			super.setViewPage("/WEB-INF/member/admin/memberList.jsp");
		}
		else {
			//로그인 안한 경우 or 일반사용자
			String message = "관리자만 접근이 가능합니다.";
	         String loc = "javascript:history.back()";
	         
	        request.setAttribute("message", message);
	        request.setAttribute("loc", loc);
	        
	        super.setRedirect(true);
	        super.setViewPage(request.getContextPath()+"/index.up");
		}

	}

}
