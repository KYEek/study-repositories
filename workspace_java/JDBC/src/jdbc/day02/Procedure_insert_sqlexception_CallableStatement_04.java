package jdbc.day02;



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

	}

}
