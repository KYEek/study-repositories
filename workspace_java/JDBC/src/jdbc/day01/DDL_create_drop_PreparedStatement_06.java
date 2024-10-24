package jdbc.day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DDL_create_drop_PreparedStatement_06 {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			
			
			System.out.println(" 🫵연결할 오라클 서버의 IP 주소 : ");

			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");
			
			String sql_1 = " select * "
						 +" from user_tables "
						 +" where table_name = 'TBL_MEMO' ";
			
			String sql_2 = "drop table tbl_memo purge";
							// tbl_memo 테이블에 딸린 자식 테이블이 존재하지 않을 경우 tbl_memo 테이블을 삭제하는 것이다.
			
			//String sql_2 = "drop table tbl_memo cascade constraints purge";
							// tbl_memo 테이블에 딸린 자식 테이블이 존재하는 경우 먼저 자식테이블에 존재하는 foreign key 를 먼저 삭제한 후, tbl_memo 테이블을 삭제하는 것이다.
			
			String sql_3 = "create table tbl_memo\r\n"
					+ "    (no          number(4)\r\n"
					+ "    ,name        Nvarchar2(20) not null\r\n"
					+ "    ,msg         Nvarchar2(100) not null\r\n"
					+ "    ,writeday    date default sysdate\r\n"
					+ "    ,constraint  PK_tbl_memo_no primary key(no)\r\n"
					+ "    )";
			
			String sql_4 = " select * "
						 + " from user_sequences "
						 + " where sequence_name = 'SEQ_MEMO' ";
			
			String sql_5 = " drop sequence seq_memo ";
			
			
			String sql_6 = " create sequence seq_memo "
						 + " start with 1 "
						 + " increment by 1 "
						 + " nomaxvalue "
						 + " nominvalue "
						 + " nocycle "
						 + " nocache ";
			
			String sql_7 = " insert into tbl_memo( no, name, msg )"
						 + " values(seq_memo.nextval, '이순신', '안녕하세요? 이순신입니다') ";
			
			String sql_8 = " select no, name, msg, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') as writeday "
						 + " from tbl_memo "
						 + " order by no desc ";
			// == 생성해야 할 TBL_MEMO 테이블이 이미 기존에 존재하는지 여부를 알아온다. == //
			
			
			
			pstmt = conn.prepareStatement(sql_1);
			
			rs = pstmt.executeQuery(); //sql 실행
			
			if(rs.next()) {	//TBL_MEMO 테이블이 이미 존재한다면
				
				pstmt = conn.prepareStatement(sql_2);//TBL_MEMO 테이블을 drop 하겠다.
				int n = pstmt.executeUpdate();
				/*
	            .executeUpdate(); 은 SQL문이 DML문(insert, update, delete, merge) 이거나 
	                              SQL문이 DDL문(create, drop, alter, truncate) 일 경우에 사용된다. 
	                  
	            SQL문이 DML문이라면 return 되어지는 값은 적용되어진 행의 개수를 리턴시켜준다.
	            예를 들어, insert into ... 하면 1 개행이 입력되므로 리턴값은 1 이 나온다. 
	                   update ... 할 경우에 update 할 대상의 행의 개수가 5 이라면 리턴값은 5 가 나온다. 
	                   delete ... 할 경우에 delete 되어질 대상의 행의 개수가 3 이라면 리턴값은 3 이 나온다.
	                  
	            SQL문이 DDL문이라면 return 되어지는 값은 무조건 0 이 리턴된다.
	             
	             
	            .executeQuery(); 은 SQL문이 DQL(select)일 경우에 사용된다. 
	          */
				
				System.out.println("~~~~ 확인용 drop table : " + n);
				
			}

			// TBL_MEMO 테이블을 생성하겠다.
			pstmt = conn.prepareStatement(sql_3);
			int n = pstmt.executeUpdate();
			System.out.println("~~~~ 확인용 Create table : " + n);

			
			
			// == 생성해야할 시퀀스 SEQ_MEMO 가 이미 기존에 존재하는지 여부를 알아온다. ==
			pstmt = conn.prepareStatement(sql_4);

			rs = pstmt.executeQuery(); // sql 실행
			
			
			if (rs.next()) { // SEQ_MEMO 시퀀스가 이미 존재한다면

				pstmt = conn.prepareStatement(sql_5);// SEQ_MEMO 시퀀스를 drop 하겠다.
				n = pstmt.executeUpdate();
				
				System.out.println("~~~~ 확인용 drop sequence : " + n);

			}
			
			
			// SEQ_MEMO 시퀀스를 생성하겠다.
			pstmt = conn.prepareStatement(sql_6);

			n = pstmt.executeUpdate(); // sql 실행
			System.out.println("~~~~ 확인용 Create Sequence : " + n);
			
			
			//TBL_MEMO 테이블에 insert 하기
			pstmt = conn.prepareStatement(sql_7);// SEQ_MEMO 시퀀스를 drop 하겠다.
			n = pstmt.executeUpdate();
			System.out.println("~~~~ 확인용 insert into tbl_memo : " + n);
			
			//TBL_MEMO 테이블을 select 하기
			pstmt = conn.prepareStatement(sql_8);
			rs = pstmt.executeQuery(); //sql문 실행
			
			StringBuilder sb = new StringBuilder();
			sb.append("-".repeat(100)+"\n");
            sb.append("일련번호\t성명\t글내용\t작성일자\n");
            sb.append("-".repeat(100)+"\n");
			
            rs.next();
            ///여기 확인부탁
            sb.append(rs.getInt("no") + "\t" +
            		rs.getString("name") + "\t" +
            		rs.getString("msg") + "\t" +
            		rs.getString("writeday") + "\n");
			
            System.out.println(sb.toString());
			
			
		
		}
		
		catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
					rs = null;
				}
				
				if(pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				
				if(conn != null) {
					conn.close();
					conn = null;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
		System.out.println("프로그램 종료😘");
	}// end of main()-------------------------------------------------------------------------

}
