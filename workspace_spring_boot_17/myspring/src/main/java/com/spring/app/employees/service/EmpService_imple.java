package com.spring.app.employees.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import org.apache.poi.ss.usermodel.Row;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.spring.app.employees.model.EmpDAO;
import com.spring.app.employees.model2.EmpDAO2;
import com.spring.app.member.model.MemberDAO;

@Service
public class EmpService_imple implements EmpService {

	@Autowired
	private EmpDAO mapper_dao;
	// EmpDAO 는 Mapper 인터페이스로 사용되어지는 것이다.
	
	@Autowired
	private EmpDAO2 dao;
	// EmpDAO2 는 매퍼파일인 hr.xml 에 기술된 sql문을 반복해서 불러와야 하는 메소드가 있으므로 
	// Mapper 인터페이스로 사용하지 못하고 일반적인 인터페이스로 사용해야만 한다.
	
	@Autowired
	private MemberDAO member_dao;
	// MemberDAO member_dao 이 필요한 이유는 
	// 인사관리 페이지에 접속한 이후에, 인사관리 페이지에 접속한 페이지URL, 사용자ID, 접속IP주소, 접속시간을 기록으로 DB에 tbl_empManger_accessTime 테이블에 insert 하도록 한다.
	// tbl_empManger_accessTime 테이블은 hr 이 아닌 mymvc_user 에서 생성할 것이므로, 
	// sql문은 매퍼 xml 파일중 member.xml 에 기록할 것이다. 
	// 그래서 MemberDAO 를 사용한 것이다.
	

	// employees 테이블에서 근무중인 사원들의 부서번호 가져오기
	@Override
	public List<String> deptIdList() {
		List<String> deptIdList = mapper_dao.deptIdList();
		return deptIdList;
	}
	
	
	// employees 테이블에서 모든 사원들을 가져오기
	@Override
	public List<Map<String, String>> employeeList() {
		List<Map<String, String>> employeeList = mapper_dao.employeeList(); 
		return employeeList;
	}


	// employees 테이블에서 조건에 만족하는 사원들을 가져오기
	@Override
	public List<Map<String, String>> employeeList(Map<String, Object> paraMap) {
		List<Map<String, String>> employeeList = mapper_dao.employeeListSearch(paraMap); 
		return employeeList;
	}


	// === #193. employees 테이블에서 조건에 만족하는 사원들을 가져와서 Excel 파일로 만들기 === // 
	@Override
	public void employeeList_to_Excel(Map<String, Object> paraMap, Model model) {
		
		// === 조회결과물인 empList 를 가지고 엑셀 시트 생성하기 ===
		// 시트를 생성하고, 행을 생성하고, 셀을 생성하고, 셀안에 내용을 넣어주면 된다.
		
		SXSSFWorkbook workbook = new SXSSFWorkbook();
		
		// 시트생성
		SXSSFSheet sheet = workbook.createSheet("HR사원정보");
		
		// 시트 열 너비 설정
		sheet.setColumnWidth(0, 2000);
		sheet.setColumnWidth(1, 4000);
		sheet.setColumnWidth(2, 2000);
		sheet.setColumnWidth(3, 4000);
		sheet.setColumnWidth(4, 3000);
		sheet.setColumnWidth(5, 2000);
		sheet.setColumnWidth(6, 1500);
		sheet.setColumnWidth(7, 1500);
		
		// 행의 위치를 나타내는 변수
		int rowLocation = 0;
		
		////////////////////////////////////////////////////////////////////////////////////////
		// CellStyle 정렬하기(Alignment)
		// CellStyle 객체를 생성하여 Alignment 세팅하는 메소드를 호출해서 인자값을 넣어준다.
		// 아래는 HorizontalAlignment(가로)와 VerticalAlignment(세로)를 모두 가운데 정렬 시켰다.
		CellStyle mergeRowStyle = workbook.createCellStyle();
		mergeRowStyle.setAlignment(HorizontalAlignment.CENTER);
		mergeRowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		                                // import org.apache.poi.ss.usermodel.VerticalAlignment 으로 해야함. 
		
		CellStyle headerStyle = workbook.createCellStyle();
		headerStyle.setAlignment(HorizontalAlignment.CENTER);
		headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		                                // import org.apache.poi.ss.usermodel.VerticalAlignment 으로 해야함.
		
		
		// CellStyle 배경색(ForegroundColor)만들기
        // setFillForegroundColor 메소드에 IndexedColors Enum인자를 사용한다.
        // setFillPattern은 해당 색을 어떤 패턴으로 입힐지를 정한다.
		mergeRowStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex()); // IndexedColors.DARK_BLUE.getIndex() 는 색상(남색)의 인덱스값을 리턴시켜준다. 
		mergeRowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // IndexedColors.LIGHT_YELLOW.getIndex() 는 연한노랑의 인덱스값을 리턴시켜준다. 
		headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
				
		// CellStyle 천단위 쉼표, 금액
        CellStyle moneyStyle = workbook.createCellStyle();
        moneyStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0"));
        
        
        // Cell 폰트(Font) 설정하기
        // 폰트 적용을 위해 POI 라이브러리의 Font 객체를 생성해준다.
        // 해당 객체의 세터를 사용해 폰트를 설정해준다. 대표적으로 글씨체, 크기, 색상, 굵기만 설정한다.
        // 이후 CellStyle의 setFont 메소드를 사용해 인자로 폰트를 넣어준다.
        Font mergeRowFont = workbook.createFont(); // import org.apache.poi.ss.usermodel.Font; 으로 한다.
		mergeRowFont.setFontName("나눔고딕");
		mergeRowFont.setFontHeight((short)500);
		mergeRowFont.setColor(IndexedColors.WHITE.getIndex());
		mergeRowFont.setBold(true);
		
		mergeRowStyle.setFont(mergeRowFont);
		
		
		// CellStyle 테두리 Border
        // 테두리는 각 셀마다 상하좌우 모두 설정해준다.
        // setBorderTop, Bottom, Left, Right 메소드와 인자로 POI라이브러리의 BorderStyle 인자를 넣어서 적용한다.
		headerStyle.setBorderTop(BorderStyle.THICK);
		headerStyle.setBorderBottom(BorderStyle.THICK);
		headerStyle.setBorderLeft(BorderStyle.THIN);
		headerStyle.setBorderRight(BorderStyle.THIN);
		
		
		// Cell Merge 셀 병합시키기
        /* 셀병합은 시트의 addMergeRegion 메소드에 CellRangeAddress 객체를 인자로 하여 병합시킨다.
           CellRangeAddress 생성자의 인자로(시작 행, 끝 행, 시작 열, 끝 열) 순서대로 넣어서 병합시킬 범위를 정한다. 배열처럼 시작은 0부터이다.  
        */
		// 병합할 행 만들기
		Row mergeRow = sheet.createRow(rowLocation); // 엑셀에서 행의 시작은 0 부터 시작한다.
		
		// 병합할 행에 "우리회사 사원정보" 로 셀을 만들어 셀에 스타일을 주기 
		for(int i=0; i<8; i++) {
			Cell cell = mergeRow.createCell(i);
			cell.setCellStyle(mergeRowStyle);
			cell.setCellValue("우리회사 사원정보");
		}// end of for-----------------------
		
		// 셀 병합하기
		sheet.addMergedRegion(new CellRangeAddress(rowLocation, rowLocation, 0, 7)); // 시작 행, 끝 행, 시작 열, 끝 열 
		////////////////////////////////////////////////////////////////////////////////////////////////
		
		// 헤더 행 생성
		Row headerRow = sheet.createRow(++rowLocation); // 엑셀에서 행의 시작은 0 부터 시작한다.
		                                                // ++rowLocation 은 전위연산자임. 
		
		// 해당 행의 첫번째 열 셀 생성
		Cell headerCell = headerRow.createCell(0);  // 엑셀에서 열의 시작은 0 부터 시작한다.
		headerCell.setCellValue("부서번호");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 두번째 열 셀 생성
		headerCell = headerRow.createCell(1);
		headerCell.setCellValue("부서명");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 세번째 열 셀 생성
		headerCell = headerRow.createCell(2);
		headerCell.setCellValue("사원번호");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 네번째 열 셀 생성
		headerCell = headerRow.createCell(3);
		headerCell.setCellValue("사원명");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 다섯번째 열 셀 생성
		headerCell = headerRow.createCell(4);
		headerCell.setCellValue("입사일자");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 여섯번째 열 셀 생성
		headerCell = headerRow.createCell(5);
		headerCell.setCellValue("월급");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 일곱번째 열 셀 생성
		headerCell = headerRow.createCell(6);
		headerCell.setCellValue("성별");
		headerCell.setCellStyle(headerStyle);
		
		// 해당 행의 여덟번째 열 셀 생성
		headerCell = headerRow.createCell(7);
		headerCell.setCellValue("나이");
		headerCell.setCellStyle(headerStyle);
		
		
		// ==== HR사원정보 내용에 해당하는 행 및 셀 생성하기 ==== //
		Row bodyRow = null;
		Cell bodyCell = null;
		
		List<Map<String, String>> employeeList = employeeList(paraMap);
		
		for(int i=0; i<employeeList.size(); i++) {
			
			Map<String, String> empMap = employeeList.get(i);
			
			// 행생성
			bodyRow = sheet.createRow(i + (rowLocation+1)); 
			
			// 데이터 부서번호 표시
			bodyCell = bodyRow.createCell(0);
			bodyCell.setCellValue(empMap.get("department_id"));
			
			// 데이터 부서명 표시
			bodyCell = bodyRow.createCell(1);
			bodyCell.setCellValue(empMap.get("department_name"));
			
			// 데이터 사원번호 표시
			bodyCell = bodyRow.createCell(2);
			bodyCell.setCellValue(empMap.get("employee_id"));
			
			// 데이터 사원명 표시
			bodyCell = bodyRow.createCell(3);
			bodyCell.setCellValue(empMap.get("fullname"));
			
			// 데이터 입사일자 표시
			bodyCell = bodyRow.createCell(4);
			bodyCell.setCellValue(empMap.get("hire_date"));	
			
			// 데이터 월급 표시
			bodyCell = bodyRow.createCell(5);
			bodyCell.setCellValue(Integer.parseInt(empMap.get("monthsal")));
			bodyCell.setCellStyle(moneyStyle); // 천단위 쉼표, 금액
			
			// 데이터 성별 표시
			bodyCell = bodyRow.createCell(6);
			bodyCell.setCellValue(empMap.get("gender"));
			
			// 데이터 나이 표시
			bodyCell = bodyRow.createCell(7);
			bodyCell.setCellValue(Integer.parseInt(empMap.get("age")));
			
		}// end of for----------------------------
		
		model.addAttribute("locale", Locale.KOREA);
        model.addAttribute("workbook", workbook);
        model.addAttribute("workbookName", "HR사원정보");
		
	}


	// === #198. Excel 파일을 업로드 하면 엑셀데이터를 데이터베이스 테이블에 insert 해주는 예제 === //
	@Override
	public int add_employeeList(List<Map<String, String>> paraMapList) {
		int insert_count = dao.add_employeeList(paraMapList);
		return insert_count;
	}


	// employees 테이블에서 부서명별 인원수 및 퍼센티지 가져오기
	@Override
	public List<Map<String, String>> employeeCntByDeptname() {
		List<Map<String, String>> deptnamePercentageList = mapper_dao.employeeCntByDeptname();
		return deptnamePercentageList;
	}


	// employees 테이블에서 성별 인원수 및 퍼센티지 가져오기
	@Override
	public List<Map<String, String>> employeeCntByGender() {
		List<Map<String, String>> genderPercentageList = mapper_dao.employeeCntByGender();
		return genderPercentageList;
	}


	// employees 테이블에서 성별 입사년도별 인원수 가져오기
	@Override
	public String employeeCntByGenderHireYear() {
		
		List<Map<String, String>> genderHireYearList = mapper_dao.employeeCntByGenderHireYear(); 
		
		JsonArray jsonArr = new JsonArray();  // []
		// com.google.gson.JsonArray 
		
		for(Map<String, String> map : genderHireYearList) {
			JsonObject jsonObj = new JsonObject(); // {}
			jsonObj.addProperty("gender", map.get("gender"));
			jsonObj.addProperty("Y2001", map.get("Y2001"));
			jsonObj.addProperty("Y2002", map.get("Y2002"));
			jsonObj.addProperty("Y2003", map.get("Y2003"));
			jsonObj.addProperty("Y2004", map.get("Y2004"));
			jsonObj.addProperty("Y2005", map.get("Y2005"));
			jsonObj.addProperty("Y2006", map.get("Y2006"));
			jsonObj.addProperty("Y2007", map.get("Y2007"));
			jsonObj.addProperty("Y2008", map.get("Y2008"));
			
			jsonArr.add(jsonObj);
		}// end of for------------------------
		
	/*	
		Gson gson = new Gson();
		String json = gson.toJson(jsonArr);  
		return json;
	*/
		// 또는
		return new Gson().toJson(jsonArr);
	}


	// 특정 부서명에 근무하는 직원들의 성별 인원수 퍼센티지 가져오기 
	@Override
	public List<Map<String, String>> genderCntSpecialDeptname(String deptname) {
		
		List<Map<String, String>> genderPercentageList = mapper_dao.genderCntSpecialDeptname(deptname);
		
		return genderPercentageList;
	}


	// 인사관리 페이지에 접속한 이후에, 인사관리 페이지에 접속한 페이지URL, 사용자ID, 접속IP주소, 접속시간을 기록으로 DB에 tbl_empManger_accessTime 테이블에 insert 하도록 한다. 
	@Override
	public void insert_accessTime(Map<String, String> paraMap) {
		member_dao.insert_accessTime(paraMap);
	}


	// 인사관리 페이지별 사용자별 접속통계 가져오기 
	@Override
	public String pageurlUsername() {
		
		List<Map<String, String>> pageurlUsernameList = member_dao.pageurlUsername();
		/*
		    "pagename":직원목록  "name":관리자   "cnt":3
		    "pagename":직원목록  "name":서영학   "cnt":2
		    "pagename":통계차트  "name":관리자   "cnt":2
		    "pagename":통계차트  "name":서영학   "cnt":1
		*/
		
        JsonObject jsonObj = new JsonObject(); // {}
		
		JsonArray categories = new JsonArray(); // [] 
		JsonArray series     = new JsonArray(); // []
		
		Gson gson = new Gson();
		
		for(int i=0; i<pageurlUsernameList.size(); i++) {
			if(i==0) {
				categories.add(pageurlUsernameList.get(i).get("pagename"));	
			}
			
			if( i>0 && !pageurlUsernameList.get(i-1).get("pagename").equals(pageurlUsernameList.get(i).get("pagename")) ) {
				categories.add(pageurlUsernameList.get(i).get("pagename"));	
			}
			
			////////////////////////////////////////////////////////////////////////
			
			JsonObject sub_jsonObj = new JsonObject(); // {}
			sub_jsonObj.addProperty("name", pageurlUsernameList.get(i).get("name"));
			
			JsonArray data_jsonArr = new JsonArray(); // []
			data_jsonArr.add(pageurlUsernameList.get(i).get("cnt"));
			
			sub_jsonObj.addProperty("data", gson.toJson(data_jsonArr));
			
			series.add(sub_jsonObj); 
			
		}// end of for------------------------------------------------------------		
		
	//	System.out.println("~~~ 확인용 gson.toJson(categories) => " + gson.toJson(categories));   
		// ~~~ 확인용 gson.toJson(categories) => ["직원목록","통계차트"]
		
	//	System.out.println("~~~ 확인용 gson.toJson(series) => " + gson.toJson(series));  
		// ~~~ 확인용 gson.toJson(series) => [{"name":"관리자","data":"[\"3\"]"}
		//                                  ,{"name":"서영학","data":"[\"2\"]"}
		//                                  ,{"name":"관리자","data":"[\"2\"]"}
		//                                  ,{"name":"서영학","data":"[\"1\"]"}] 
		
		jsonObj.addProperty("categories", gson.toJson(categories));
		jsonObj.addProperty("series", gson.toJson(series));
		
  // 	System.out.println("### 확인용 gson.toJson(jsonObj) => " + gson.toJson(jsonObj)); 
		// ### 확인용 gson.toJson(jsonObj) => {"categories":"[\"직원목록\",\"통계차트\"]"
		//                                  ,"series":"[{\"name\":\"관리자\",\"data\":\"[\\\"3\\\"]\"}
		//                                             ,{\"name\":\"서영학\",\"data\":\"[\\\"2\\\"]\"}
		//                                             ,{\"name\":\"관리자\",\"data\":\"[\\\"2\\\"]\"}
		//                                             ,{\"name\":\"서영학\",\"data\":\"[\\\"1\\\"]\"}]"}   
		
		return gson.toJson(jsonObj);
	}
	
	
	
	
	
}
