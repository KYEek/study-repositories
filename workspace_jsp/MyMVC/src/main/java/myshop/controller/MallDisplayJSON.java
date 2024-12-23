package myshop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

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
		
		JSONArray jsonArr = new JSONArray();
		
		if(productList.size()> 0) {
			for(ProductVO pvo : productList) {
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("pnum", pvo.getPnum());
				jsonObj.put("pname", pvo.getPname());
				jsonObj.put("cname", pvo.getCategvo().getCname());
				jsonObj.put("pcompany", pvo.getPcompany());
	            jsonObj.put("pimage1", pvo.getPimage1());
	            jsonObj.put("pimage2", pvo.getPimage2());
	            jsonObj.put("pqty", pvo.getPqty());
	            jsonObj.put("price", pvo.getPrice());
	            jsonObj.put("saleprice", pvo.getSaleprice());
	            jsonObj.put("sname", pvo.getPinputdate());
	            jsonObj.put("pcontent", pvo.getPcontent());
	            jsonObj.put("point", pvo.getPoint());
	            jsonObj.put("pinputdate", pvo.getPinputdate());
	            
	            jsonObj.put("discountPercent", pvo.getDiscountPercent());
	            
	            jsonArr.put(jsonObj);
			}
		}//end of if----------------------------
		
		String json = jsonArr.toString(); //문자열로 변환
		
		System.out.println("~~확인용 josn : " + json);
		request.setAttribute("json", json);
		
		super.setRedirect(false);
		super.setViewPage("/WEB-INF/jsonview.jsp");
	}

}
