package myshop.model;

import java.sql.Connection;
import myshop.domain.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import myshop.domain.ImageVO;
import myshop.domain.ProductVO;

public class ProductDAO_imple implements ProductDAO {

	private DataSource ds;  // DataSource ds 는 아파치톰캣이 제공하는 DBCP(DB Connection Pool)이다. 
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 생성자
	public ProductDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
		    Context envContext  = (Context)initContext.lookup("java:/comp/env");
		    ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch(NamingException e) {
			e.printStackTrace();
		} 
	}
	
	
	// 사용한 자원을 반납하는 close() 메소드 생성하기
	private void close() {
		try {
			if(rs    != null) {rs.close();	  rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn  != null) {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}// end of private void close()---------------
	
	
	@Override
	public List<ImageVO> imageSelectAll() throws SQLException {
		
		List<ImageVO> imgList = new ArrayList<>();
		
		try {
			  conn = ds.getConnection();
			  
			  String sql = " select imgno, imgname, imgfilename "
			  		     + " from tbl_main_page "
			  		     + " order by imgno asc ";
			  
			  pstmt = conn.prepareStatement(sql);
			  
			  rs = pstmt.executeQuery();
			  
			  while(rs.next()) {
				  
				  ImageVO imgvo = new ImageVO();
				  imgvo.setImgno(rs.getInt("imgno"));
				  imgvo.setImgname(rs.getString("imgname"));
				  imgvo.setImgfilename(rs.getString("imgfilename"));
				  
				  imgList.add(imgvo);
			  }// end of while------------------
			  
		} finally {
			close();
		}
		
		return imgList;
		
	}// end of public List<ImageVO> imageSelectAll() throws SQLException-------

	
	// 제품의 스펙별(HIT, NEW, BEST) 상품의 전체개수를 알아오기
	   @Override
	   public int totalPspecCount(String fk_snum) throws SQLException {
	      
	      int totalCount = 0;
	      
	      try {
	         conn = ds.getConnection();
	         
	         String sql = " select count(*) "
	                  + " from tbl_product "
	                  + " where fk_snum = ? ";
	         
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, fk_snum);
	         
	         rs = pstmt.executeQuery();
	         
	         rs.next();
	         
	         totalCount = rs.getInt(1);
	         
	      } finally {
	         close();
	      }
	      
	      return totalCount;
	      
	   }// end of public int totalPspecCount(String string) throws SQLException------


	@Override
	public List<ProductVO> selectBySpecName(Map<String, String> paraMap) throws SQLException {
		
		List<ProductVO> productList = new ArrayList<>();
		conn =ds.getConnection();
		try {
		String sql = "SELECT pnum, pname, cname, pcompany, pimage1, pimage2, pqty, price, saleprice, sname, pcontent, point, pinputdate "
				+ "FROM \r\n"
				+ "(\r\n"
				+ "    SELECT row_number() over(order by pnum desc) AS RNO\r\n"
				+ "         , pnum, pname, C.cname, pcompany, pimage1, pimage2, pqty, price, saleprice, S.sname, pcontent, point \r\n"
				+ "         , to_char(pinputdate, 'yyyy-mm-dd') AS pinputdate\r\n"
				+ "    FROM tbl_product P "
				+ "    JOIN tbl_category C "
				+ "    ON P.fk_cnum = C.cnum "
				+ "    JOIN tbl_spec S "
				+ "    ON P.fk_snum = S.snum "
				+ "    WHERE S.sname = ? "
				+ " ) "
				+ " WHERE rno between ? and ? ";
		pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, "sname");
		pstmt.setString(2, "start");
		pstmt.setString(3, "end");
		
		rs = pstmt.executeQuery();
		
		while(rs.next()) {
			ProductVO pvo = new ProductVO();
			// pcontent, point, pinputdate
			
			pvo.setPnum(rs.getInt("pnum"));
			pvo.setPname(rs.getString("pname"));
			
			CategoryVO catevo = new CategoryVO();
			catevo.setCname(rs.getString("cname"));
			pvo.setCategvo(catevo);
			
			pvo.setPcompany(rs.getString("pcompany"));
			pvo.setPimage1(rs.getNString("pimage1"));
			pvo.setPimage2(rs.getNString("pimage2"));
			pvo.setPqty(rs.getInt("pqty"));
			pvo.setPrice(rs.getInt("price"));
			pvo.setSaleprice(rs.getInt("saleprice"));
			
			SpecVO spvo = new SpecVO();
			spvo.setSname(rs.getString("sname"));
			pvo.setSpvo(spvo);
			pvo.setPcontent(rs.getString("pcontent"));
			pvo.setPoint(rs.getInt("point"));
			pvo.setPinputdate(rs.getString("pinputdate"));
			
			productList.add(pvo);
			
		}
		} finally {
			close();
		}
		return productList;
	}
	
	
}
