package jdbc.day02;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*		== HR에서 예전에 생성해두었던 pcd_tbl_member_test1_insert 프로시저 을 사용해본다.
 create or replace procedure  pcd_tbl_member_test1_insert(p_id varchar2, p_pwd varchar2, p_name varchar2)
    is
        error_input     exception;
        error_dayTime   exception;
        number_cnt      number(2) := 0;
        char_cnt        number(2) := 0;
        special_cnt     number(2) := 0;
    begin
    
        --입력(insert)이 불가한 요일명과 시간대를 알아봅시다. --
        if(to_char(sysdate, 'd') in ('1', '7') or
            to_number(to_char(sysdate, 'hh24')) <14 or
            to_number(to_char(sysdate, 'hh24')) >16 )then
            raise error_dayTime;
        
        -- 입력(insert)이 가능한 요일명과 시간대 이라면 암호를 검사하겠다.
        else
            if
                length(p_pwd) > 20 or length(p_pwd)< 5 then
                raise error_input;
            end if;
            
            for i in 1..length(p_pwd) loop
                if ascii(substr(p_pwd, i, 1)) > 127 then raise error_input;
                elsif substr(p_pwd, i, 1) between 'A' and 'Z' then char_cnt := char_cnt + 1;
                elsif substr(p_pwd, i, 1) between 'a' and 'z' then char_cnt := char_cnt + 1;
                elsif substr(p_pwd, i, 1) between '0' and '9' then number_cnt := number_cnt + 1;
                else special_cnt := special_cnt + 1;
                end if;
            end loop;
            
            if
                (number_cnt = 0) or (char_cnt = 0) or (special_cnt = 0) then
                raise error_input;
            else 
                insert into tbl_member_test1(userid, passwd, name) values (p_id, p_pwd, p_name);
            end if;
        end if;
         
    exception
        when error_dayTime then
        raise_application_error(-20003, '>> 영업시간(월~금 14:00 ~ 16:59:59 까지)이 아니므로 입력불가함!! <<');
        when error_input then
        raise_application_error(-20002, '암호는 최소 5글자 이상 20글자 이하의 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다');
    end pcd_tbl_member_test1_insert; 
 
 */


public class Procedure_insert_sqlexception_CallableStatement_04 {

	public static void main(String[] args) {
		
		Connection conn = null;

		CallableStatement cstmt = null;
		// CallableStatement cstmt 은 Connection conn(연결한 DB 서버)에 존재하는 Procedure 를 호출해주는 객체(우편배달부)이다.
		String userid = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			

			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "HR", "gclass");
			
			
			// >>> 3. Connection conn 객체를 사용하여 prepareCall() 메소드를 호출함으로써
	        //        CallableStatement cstmt 객체를 생성한다.
	        //        즉, 우편배달부(택배기사) 객체 만들기
			cstmt = conn.prepareCall("{call pcd_tbl_member_test1_insert(?, ?, ?)}");
			/*
				 오라클 서버에 생성한 프로시저 pcd_student_select_one 의 
            	 매개변수 갯수가 2개 이므로 ? 를 2개 준다.
            	 
            	 다음으로 오라클의 프로시저를 수행( executeUpdate() 또는 execute() ) 하기에 앞서서  
				반드시 해야할 일은 IN mode 로 되어진 파라미터에 값을 넣어주고,
				OUT mode 로 설정된 곳에 그 결과값을 받아오도록 아래와 같이 설정해야 한다.
				
				프로시저의 IN mode 로 되어진 파라미터에 값을 넣어줄때는 
				cstmt.setXXX() 메소드를 사용한다. 
					 
			 */
			
			Scanner sc = new Scanner(System.in);
			System.out.print("😐 아이디 : ");
			userid = sc.nextLine();	//서울 강남구
			
			System.out.print("😐 비밀번호 : ");
			String passwd = sc.nextLine();	//서울 강남구
			
			System.out.print("😐 성명 : ");
			String name = sc.nextLine();	//서울 강남구
			
			cstmt.setString(1, userid); // 숫자 1 은 프로시저 파라미터중 첫번째 파라미터인 IN 모드의 ? 를 말한다.
			cstmt.setString(2, passwd); // 숫자 1 은 프로시저 파라미터중 두번째 파라미터인 IN 모드의 ? 를 말한다.
			cstmt.setString(3, name); 	// 숫자 1 은 프로시저 파라미터중 세번째 파라미터인 IN 모드의 ? 를 말한다.
			
			// >>> 4. CallableStatement cstmt 객체를 사용하여 오라클의 프로시저 실행하기  <<<
			//cstmt.execute();		//오라클 서버에게 해당 프로시저를 실행해라는 것이다.
			//또는
			int n = cstmt.executeUpdate();	//오라클 서버에게 해당 프로시저를 실행해라는 것이다.
			
			if(n==1) {
				System.out.println(">>> 회원가입 성공!! <<<");
			}
			else {
				
			}
			
			
			
			sc.close();

		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			if(e.getErrorCode()==20002 || e.getErrorCode() == 20003) {
				System.out.println(e.getMessage());
			}
			else if(e.getErrorCode()== 1) {
				System.out.println("아이디 "+userid+" 은(는) 이미 사용중이므로 다른 아이디로 입력하세요!!");
			}
			else {
				e.printStackTrace();
			}
		} finally {
			try {

				if (cstmt != null) {
					cstmt.close();
					cstmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("(((o(*ﾟ▽ﾟ*)o)))프로그램 종료");


	}

}
