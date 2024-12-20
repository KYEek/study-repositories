package myshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.controller.AbstractController;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myshop.domain.ProductVO;
import myshop.model.ProductDAO;
import myshop.model.ProductDAO_imple;

public class MallDisplayJSON extends AbstractController {

	private ProductDAO pdao = new ProductDAO_imple();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse respone) throws Exception {
		
		String sname = request.getParameter("sname"); // "HIT" "NEW" "BEST"
		String start = request.getParameter("start");
		String len = request.getParameter("len");
		
		/*
	       맨 처음에는 sname("HIT")상품을  start("1") 부터 len("8")개를 보여준다.
	       더보기... 버튼을 클릭하면  sname("HIT")상품을  start("9") 부터 len("8")개를 보여준다.
	       또  더보기... 버튼을 클릭하면 sname("HIT")상품을  start("17") 부터 len("8")개를 보여준다.      
	    */
		
		Map<String, String> paraMap = new HashMap<>();
		paraMap.put("sname", sname); // "HIT" "NEW" "BEST"
		paraMap.put("start", start); // start "1" "9" "17" "25" "33"

		String end = String.valueOf(Integer.parseInt(start) + Integer.parseInt(len) - 1);
		paraMap.put("end", end);

		List<ProductVO> productList = pdao.selectBySpecName(paraMap);
	}

}
