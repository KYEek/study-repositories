show user;
--USER이(가) "SYSTEM"입니다.

-- 이게 1줄 주석문
/*
    여러줄 
    주석문
*/

select * from dba_users;
/*
    ORA-00942: 테이블 또는 뷰가 존재하지 않습니다
    00942. 00000 -  "table or view does not exist"
    *Cause:    
    *Action:
    4행, 15열에서 오류 발생
    
*/

-- RDBMS(Relation DataBase Management System) 관계형 데이터베이스 관리 시스템 ***--
--데이터(의미있는 정보)들을 모아놓은 것을 데이터베이스 이라고 한다.
--이러한 데이터(의미있는 정보)들을 "테이블 형태"로 저장해놓은  것을 관계형 테이터베이스(RDB)라고 보면 된다.

select *
from departments;

select *
from employees;

--관계형 데이터베이스(RDB)를 다투는 시스템을 RDBMS(Relation DataBase Management System) 관계형 데이터베이스 관리 시스템 이라고 부른다.
--RDBMS 의 종류 : ORACLE, MySQL, MS-SQL Server, DB2
--그중에서 점유율이 1등인 ORACLE 을 우리는 배우겠다

-- *** ORACLE 은 관계형 데이터베이스(Relation DataBase) 관리 시스템(Management System) 이다. ***
--     관계형 데이터베이스 관리 시스템을 RDBMS(Relation DataBase Management System) 라고 부른다.
--     관계형 데이터베이스는 데이터를 열(Column, Field) 과 행(Row, Record, tuple) 으로 이루어진 테이블(Table, entity, 개체) 형태로 저장한다.

show user;
--USER이(가) "SYSTEM"입니다.

-- ** 현재 오라클 서버에 접속되어진 사용자(지금은 hr)가 만든(소유의) 테이블(Table) 목록을 조회해 본다.
select *
from tab;

select * from user_cons_columns where table_name = 'JOB_HISTORY';
/*
    ——————————————————
    TNAME               TABTYPE
    ——————————————————
    COUNTRIES	        TABLE
    DEPARTMENTS	        TABLE
    EMPLOYEES	        TABLE
    EMP_DETAILS_VIEW	VIEW (VIEW 는 테이블은 아니지만 SELECT 되어진 결과물을 마치 테이블인것처럼 보는것)
    JOBS	            TABLE
    JOB_HISTORY	        TABLE
    LOCATIONS	        TABLE
    REGIONS	            TABLE
*/
Select *
From Departments;   -- SQL 명령어는 대,소문자를 구분하지 않습니다.


SELECT *
FROM DEPARTMENTS;   -- SQL 명령어는 대,소문자를 구분하지 않습니다.

seLECt *
from departments;   -- SQL 명령어는 대,소문자를 구분하지 않습니다.

SeLeCt *
FROm DepARTMENTS;   -- SQL 명령어는 대,소문자를 구분하지 않습니다.

select DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID, LOCATION_ID  -- 컬럼명도 대,소문자를 구분하지 않습니다.
from departments;

select DEPARTMENT_ID, DEpartMENT_NAME, MANAger_id, lOCATION_ID  -- 컬럼명도 대,소문자를 구분하지 않습니다.
from departments;

-- !!! 저장되어진 데이터 값 만큼은 반드시 대,소문자를 구분합니다. !!! --
select *
from departments
where department_name = 'Sales';     -- 데이터베이스에서 = 는 같다는 의미고 자바는 대입한다는 의미

-- !!! 저장되어진 데이터 값 만큼은 반드시 대,소문자를 구분합니다. !!! --
select *
from departments
where department_name = 'SALES';     -- 결과물이 안나온다

select *
from departments
where department_name = 'sales';     -- 결과물이 안나온다

select *
from departments
where department_name = 'SaleS';     -- 결과물이 안나온다

--————————————————————————————————————————————————————————————————————————
/*
⊂_ヽ
  ＼＼  Λ＿Λ
　　 ＼(‘ㅅ’) 두둠칫      ==== !!! 아주~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~중요!!===
      > ⌒ヽ            ==== 필수 암기 !!!
　　　/ 　 へ＼           === 어떠한 테이블 (또는 뷰)에서 데이터 정보를 꺼내와 보는 명령어인 select 의 처리(해석, parsing) 순서
　　 /　　/　 ＼＼             select 컬럼명, 컬럼명       ---5
　　 ﾚ　ノ　　  ヽ_つ          from 테이블명(또는 뷰명)    ---1
　　/　/두둠칫                 where 조건절              ---2 where 조건절이 뜻하는 것은 해당 테이블명(또는 뷰명)에서 조건에 만족하는 행(row)을 메모리(RAM)에 로딩(올리는 것)해주는 것이다.
　 /　/|                     group by 절              ---3 
　(　(ヽ                     having 그룹함수 조건절      ---4
　|　|、＼                   order by 절               ---6
　| 丿 ＼ ⌒)               위의 순서대로 해야지 아무리 복잡한 sql 문이라도 문제없이 풀 수 이따
　| |　　) /                만약 select 부터 한다면 나중에 골치아파진다고 한다
`ノ )　　Lﾉ
--————————————————————————————————————————————————————————————————————————    

*/


select *
from departments;   --"부서" 테이블

select department_id, department_name
from departments;

describe departments;   -- departments 테이블의 컬럼(column)의 정보를 알려주는 것이다.
-- 또는
desc departments;

/*
이름                                널?                                                                                                 유형           
---------------                    --------                                                                                           ------------ 
DEPARTMENT_ID   (부서번호)          NOT NULL  ==> NOT NULL 은 반드시 데이터를 입력해야 한다는 뜻이다.                                          NUMBER(4)    ==> 숫자타입  -9999 ~ 9999  
DEPARTMENT_NAME (부서명)            NOT NULL                                                                                           VARCHAR2(30)  ==> 문자열타입  최대 30byte 까지만 입력가능함.
MANAGER_ID      (부서장의 사원번호)            ==> 없는 것이나 NULL 은 같은것인데 데이터를 입력하든 입력하지 않든 모두 허용한다는 뜻이다.                NUMBER(6)    ==> 숫자타입  -999999 ~ 999999   
LOCATION_ID     (부서위치ID)                                                                                                            NUMBER(4)    ==> 숫자타입  -9999 ~ 9999 
*/


--- NUMBER (6,2) ==>   -9999.99 ~ 9999.99
--- NUMBER (4,1) ==>   -999.9 ~ 999.9
select *
from locations;
select *
from TBL_EMPLOYEES_BACKUP_20240924_0912;

select *
from employees; --"사원" 테이블

desc employees;

select table_name
from user_tables;

/*
    이름                         널?             유형           
    --------------              --------        ------------ 
    EMPLOYEE_ID   (사원번호)     NOT NULL        NUMBER(6)    
    FIRST_NAME    (이름)                        VARCHAR2(20) 
    LAST_NAME     (성)          NOT NULL        VARCHAR2(25) 
    EMAIL         (이메일)      NOT NULL         VARCHAR2(25) 
    PHONE_NUMBER  (연락처)                       VARCHAR2(20) 
    HIRE_DATE     (입사일자)    NOT NULL         DATE -- 날짜타입        
    JOB_ID        (직종ID)      NOT NULL         VARCHAR2(10) 
    SALARY        (기본급여)                      NUMBER(8,2)     -999999.99 ~ 999999.99
    COMMISSION_PCT (커미션[수당]퍼센티지)          NUMBER(2,2)     -0.99 ~ 0.99
    MANAGER_ID     (직속상관[사수]의 사원번호)      NUMBER(6)    
    DEPARTMENT_ID  (해당사원이 근무하는 부서번호)   NUMBER(4)    
*/

select *
from LOCATIONS;  -- 부서의 위치정보를 알려주는 테이블

select * 
from COUNTRIES;  -- 국가정보를 알려주는 테이블

select * 
from REGIONS;    -- 대륙정보를 알려주는 테이블


--————————————————————————————————————————————————————————————————————————    
---- NULL 은 존재하지 않는 것이므로 4칙연산(+ - * /)에 NULL 이 포함되어지면 그 결과는 무조건 NULL 이 된다.!!!!!


1.NVL

select '안녕하세요', 500, '500', '0075', null, 'null'
from dual;  -- dual 은 select 다음에 나오는 값들을 화면에 보여주기 위한 용도로 쓰이는 가상테이블이다.


select 1+2, 1+null, 3*0, null * 0, 2-1, 2-null, 5/2, 5/null
from dual;

select nvl(7,3), nvl(null,3), nvl('이순신', '거북선'), nvl(null,'거북선')   
from dual; --첫번째 값이 null이 아니면 그대로 출력 null 이면 콤마 뒤의 값을 출력


2. NVL2

    select nvl2(7,3,2), nvl2(null,3,2), --첫번째 값이 null이 아니면 두번째 값 출력 null 이면 세번째 값을 출력
           nvl2('이순신','거북선', '구국영웅'), nvl2(null, '거북선', '구국영웅')
    from dual;
    
    
    -- employees 테이블에서 부서번호가 60번에 근무하는 사원들만 모든 정보를 출력하시오.
    
    select *                               --- 3. *이므로 employees 테이블에 존재하는 모든 컬럼들을 화면에 보여라
    from employees                         --- 1. employees 테이블에서 
    where department_id = 60;              --- 2. department_id 컬럼의 값이 60 과 일치하는 행들만 메모리(RAM)에 퍼올린다.
    
    select *                               --- 2. * 이므로 employees 테이블에 존재하는 모든 컬럼들을 화면에 보여라
    from employees;                        --- 1. employees 테이블에서 where 절이 없으므로 employees 테이블에 저장되어진 모든 행들(107개행)을 메모리에 퍼올린다.
    
    select employee_id, first_name, last_name, hire_date    --- 2. 메모리에 퍼올려진 행들에서 화면에 보여주는 컬럼은 employee_id, first_name, last_name, hire_date 컬럼만 보여준다
    from employees;                                         --- 1. employees 테이블에서 where 절이 없으므로 employees 테이블에 저장되어진 모든 행들(107개행)을 메모리에 퍼올린다.
    
    select 2+3*4, 2+(3*4), (2+3)*4
    from dual;
    
    -- 월급은 기본급여(salary) + 수당(salary * commission_pct)
    -- commission_pct 컬럼의 값이 null 이라 함은 수당이 없다는 말이고, commission_pct 컬럼의 값이 0.3 이라 함은 자신의 기본급여(salary) * 0.3 이 실제 수당금액이다.
    
    select employee_id, first_name, last_name, salary, commission_pct, salary + (salary * commission_pct),
            nvl(salary + (salary * commission_pct), salary),
            nvl2(commission_pct, salary + (salary * commission_pct), salary)
    from employees;
    
    --- *** select 되어져 나온 결과물에서 컬럼에 대한 별칭(별명, alias)을 부여하겠다.
    
    select employee_id as "사원번호" --여기는 꼭 홑따옴표가 아닌 쌍따옴표 , 이게 별칭(alias)
            ,first_name "이름"    --별칭(alias)에서 as 는 생략가능함
            , last_name 성       --별칭(alias)에서 쌍따옴표는 생략 가능
            , salary "기본 급여"    --공백이 있으면 꼭 쌍따옴표 추가
            , commission_pct 커미션퍼센테이지
            , salary + (salary * commission_pct) "1월급"  --별칭(alias)에 숫자가 처음에 나오면 쌍따옴표를 넣어줘야 함
            , nvl(salary + (salary * commission_pct), salary) 월급2
            , nvl2(commission_pct, salary + (salary * commission_pct), salary) 월급3
    from employees;
    
    
    
    -------------- **** 비교연산자 **** ----------------
   1. 같다                 = 
   2. 같지않다              !=  <>  ^=
   3. 크다. 작다            >   <
   4. 같거나크다. 같거나작다   >=  <=
   5. NULL 은 존재하지 않는 것이므로 비교대상이 될 수가 없다.!!!!!
      그러므로 비교연산( =  !=  <>  ^=  >  <  >=  <= )을 할 수가 없다.
      그래서 비교연산을 하려면 nvl()함수, nvl2()함수를 사용하여 처리한다.!!!!!
      
      
   -- 오라클에서 컬럼들을 붙일때(연결할때)는 문자타입이든 숫자타입이든 날짜타입이든 관계없이 || 를 쓰면된다.    

    select '대한민국' || '서울시' || 1234 || sysdate
    from dual;
    
    -- employees 테이블에서 부서번호가 30번에 근무하는 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
      
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where department_id = 30;
    
    -- employees 테이블에서 부서번호가 null인 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    desc employees;
    
     select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where department_id = null;
    -- 데이터가 출력되지 않는다.
    -- 왜냐하면 null은 존재하지 않는 것이므로 비교자체가 되지 않기 때문이다.
    
    
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where nvl(department_id, -9999) = -9999;
    
    
    select employee_id, first_name, last_name, department_id, nvl(department_id, -9999)
    from employees;
    
    
    -- 또는
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where department_id is null;
    
    -- null 은 is 연산자를 사용하여 구한다.
    -- department_id 컬럼의 값이 null 인 행들만 RAM(메모리)에 퍼올리는 것이다.
    
    -------------------------------------------------------------------------9월11일 수업-------------------------------------------------------------
    -- !!! se;ect 구문을 작성하기 전 반드시 해당 테이블의 구조를 먼저 확인하자.!! ---
    
    -- employees 테이블에서 부서번호가 30번이 아닌 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where nvl(department_id,-9999) != 30;
    --where department_id <> 30;
    --where department_id ^= 30;
    
    
    -- 또는
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where not nvl(department_id, -9999) = 30;
    
    
    -- employees 테이블에서 부서번호가 null이 아닌 사원들만
    -- 사원번호, 사원명, 월급, 부서번호를 나타내세요.
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where department_id is not null;
    
    --또는
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where not department_id is null;
    
    --또는
    select employee_id 사원번호, first_name || ' ' || last_name 이름, nvl(salary + (salary * commission_pct), salary) 월급, department_id 부서번호  
    from employees
    where nvl(department_id, -9999) = -9999;
    
    -- *** select 되어져 나온 결과 데이터를 정렬(오름차순 정렬, 내림차순 정렬) 하여 보여주기
    
    select employee_id, first_name, last_name, salary, department_id
    from employees
    order by salary asc;    -- salary 컬럼의 값을 기준으로 오름차순 정렬하여 보이세요
    
    --또는
    select employee_id, first_name, last_name, salary, department_id
    from employees
    order by salary;    -- salary 컬럼의 값을 기준으로 오름차순 정렬하여 보이세요
                        -- asc 은 생략가능하다.
    
    select employee_id, first_name, last_name, salary, department_id
    from employees
    order by salary desc;    -- salary 컬럼의 값을 기준으로 내림차순 정렬하여 보이세요
    
    select employee_id, first_name, last_name, department_id
    from employees
    order by salary;  -- order by 다음에 나오는 컬럼은 반드시 select 절에 나오는 컬럼만 되는 것이 아니라 해당 테이블(또는 뷰)에 존재하는 컬럼이라면 order by 절 다음에 사용가능하다
    
    -- 월급의 오름차순으로 나타내세요
    select employee_id as "사원번호"
        , first_name || ' ' || last_name as "이름"
        , nvl(salary + (salary * commission_pct), salary) as "월급"
        , department_id as "부서번호"
    from employees
    order by nvl(salary + (salary * commission_pct), salary) asc;
    
    --또는 
    select employee_id as "사원번호"
        , first_name || ' ' || last_name as "이름"
        , nvl(salary + (salary * commission_pct), salary) as "월급"
        , department_id as "부서번호"
    from employees
    order by "월급" asc;
    
    --또는 
    select employee_id as "사원번호"
        , first_name || ' ' || last_name as "이름"
        , nvl(salary + (salary * commission_pct), salary) as "월급"
        , department_id as "부서번호"
    from employees
    order by 3 asc;
    
     --또는 
    select employee_id as "사원번호"
        , first_name || ' ' || last_name as "이름"
        , nvl(salary + (salary * commission_pct), salary) as "월급"
        , department_id as "부서번호"
    from employees
    order by 3;
    
    -- 월급의 내림차순으로 나타내세요
   select employee_id as "사원번호"
        , first_name || ' ' || last_name as "이름"
        , nvl(salary + (salary * commission_pct), salary) as "월급"
        , department_id as "부서번호"
    from employees
    order by 3 desc;
    
    
    -- 정렬(오름차순정렬, 내림차순정렬)을 할때 null 은 존재하지 않는 것이므로
    -- 오라클에서는 정렬시 null 을 가장 큰것으로 간주를 해주고,
    -- 마이크로소프트사의 MS-SQL 에서는 정렬시 null 을 가장 작은것으로 간주를 한다.
    
    -- department_id 컬럼의 값을 기준으로 오름차순 정렬하여 나타내세요.
    select employee_id, first_name, last_name, department_id
    from employees
    order by department_id asc;
    
    -- department_id 컬럼의 값을 기준으로 내림차순 정렬하여 나타내세요.
    select employee_id, first_name, last_name, department_id
    from employees
    order by department_id asc;
    
    
    --- *** 1차정렬, 2차정렬에 대해서  알아봅니다. ***---
    
    --employees 테이블에서 부서번호별 오름차순 정렬을 한 후에 동일한 부서번호내에서는
    --월급의 내림차순으로 정렬하여 사우너번호, 사원명, 월급, 부서번호를 나타내세요
    
    select employee_id as 사원번호
        , first_name || ' ' || last_name as 사원명
        , nvl(salary + (salary * commission_pct), salary) as 월급
        , department_id as 부서번호
    from employees
    order by 4 asc, 3 desc;
    --      1차 정렬, 2차 정렬
    
    select employee_id as 사원번호
        , first_name || ' ' || last_name as 사원명
        , nvl(salary + (salary * commission_pct), salary) as 월급
        , department_id as 부서번호
    from employees
    order by 4 , 3 desc; --asc 는 생략 가능함
    --   1차 정렬, 2차 정렬
    
   -- employees 테이블에서 수당퍼센티지가 null 인 사원들만 
   -- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
   -- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요. 
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , salary 월급
        , department_id 부서번호
   from employees
   where commission_pct is null
   order by 4, 3 desc;
   
   -- employees 테이블에서 수당퍼센티지가 null이 아닌 사원들만 
   -- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
   -- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요. 
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , salary  + (salary  * commission_pct) 월급
        , department_id 부서번호
   from employees
   where commission_pct is not null
   order by 4, 3 desc;
   
   -- employees 테이블에서 월급 (기본급여 + 수당금액)이 10000 보다 큰 사원들만
   -- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
   -- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요. 
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , nvl(salary + (salary * commission_pct), salary) 월급
        , department_id 부서번호
   from employees
   where nvl(salary + (salary * commission_pct), salary) > 10000
   order by 4, 3 desc;
   
   -- employees 테이블에서 부서번호가 50번 부서가 아닌 사원들만
   -- 사원번호, 사원명, 월급(기본급여+수당금액), 부서번호를 나타내되 
   -- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 월급의 내림차순으로 나타내세요.
   
   desc employees;
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , nvl(salary + (salary * commission_pct), salary) 월급
        , department_id 부서번호
   from employees
   where nvl(department_id, -9999) != 50
   order by 4, 3 desc;
   
   
   
   
   
   
   
   
   
   
   --- *** AND  OR  IN()   NOT  연산자
   
   -- employees 테이블에서 80번 부서에 근무하는 사원들중에 기본급여가 10000 이상인 사원들만 표시
   -- 사원번호, 사원명, 기본급여, 부서번호를 나타내세요
   
   select employee_id as "사원번호"
        , first_name || ' ' || last_name as "사원명"
        , salary as "기본급여"
        , department_id as "부서번호"
   from employees
   where department_id = 80 and salary >= 10000;
   
   
   -- employees 테이블에서 30번, 60번, 80번, null 부서에 근무하는 사원들만 
   -- 사원번호, 사원명, 기본급여, 부서번호를 나타내세요.
   select employee_id as "사원번호"
        , first_name || ' ' || last_name as "사원명"
        , salary as "기본급여"
        , department_id as "부서번호"
   from employees
   where department_id = 30 or
        department_id = 60 or
        department_id = 80 or
        department_id is null;
        
    -- 또는
    select employee_id as "사원번호"
        , first_name || ' ' || last_name as "사원명"
        , salary as "기본급여"
        , department_id as "부서번호"
   from employees
   where nvl(department_id, -100) in(30, 60, 80, -100)
   order by department_id;
   
   -- employees 테이블에서 30번, 60번, 80번, null 부서에 근무하는 사원들만 
   -- 사원번호, 사원명, 기본급여, 부서번호를 나타내세요.
   desc employees;
   
   select employee_id as "사원번호"
        , first_name || ' ' || last_name as "사원명"
        , salary as "기본급여"
        , department_id as "부서번호"
   from employees
   where nvl(department_id, -9999) != 30 and
        nvl(department_id, -9999) != 60 and
        nvl(department_id, -9999) != 80
   order by department_id; 
   
   -- 또는
   select employee_id as "사원번호"
        , first_name || ' ' || last_name as "사원명"
        , salary as "기본급여"
        , department_id as "부서번호"
   from employees
   where department_id not in(30, 60, 80)
   order by department_id;
   -- 또는
   select employee_id as "사원번호"
        , first_name || ' ' || last_name as "사원명"
        , salary as "기본급여"
        , department_id as "부서번호"
   from employees
   where not nvl(department_id, -9999) in(30, 60, 80)
   order by department_id;
   
   
   
   
   /*
   
      === !!!!! AND 와 OR 가 혼용되어지면 연산의 우선순위에 따라 AND 가 먼저 실행된다. !!!!!
      === !!!!! 연산자에 있어서 가장 최우선은 괄호( )가 제일 우선한다. !!!!!
   
      select 2+3*4 (2+3)*4
      from dual;
      --      14     20
      
        
   */
   
   -- employees 테이블에서 부서번호가 30, 50, 60번 부서에 근무하는 사원들중에 
   -- 연봉(월급*12)이 20000 이상 60000 이하인 사원들만 
   -- 사원번호, 사원명, 연봉(월급*12), 부서번호를 나타내되 
   -- 부서번호의 오름차순으로 정렬한 후 동일한 부서번호내에서는 연봉의 내림차순으로 나타내세요. 
   
   -- !!! 이것은 틀린 풀이이다 !!! --
   
   select employee_id 사원번호
         , first_name ||' '|| last_name 사원명
         , nvl(salary + (salary * commission_pct), salary) *12 연봉
         , department_id 부서번호
   from employees
   where department_id =30 or
         department_id =50 or
         department_id =60 and
         nvl(salary + (salary * commission_pct), salary) *12 >= 20000 and
         nvl(salary + (salary * commission_pct), salary) *12 <= 60000
   order by 4, 3 desc;
   
   
   -- !!! 이것이 올바르게 풀이한 것이다 !!! ---
   select employee_id 사원번호
         , first_name ||' '|| last_name 사원명
         , nvl(salary + (salary * commission_pct), salary) *12 연봉
         , department_id 부서번호
   from employees
   where (department_id =30 or
         department_id =50 or
         department_id =60) and
         nvl(salary + (salary * commission_pct), salary) *12 >= 20000 and
         nvl(salary + (salary * commission_pct), salary) *12 <= 60000
   order by 4, 3 desc;
   
   --또는
   select employee_id 사원번호
         , first_name ||' '|| last_name 사원명
         , nvl(salary + (salary * commission_pct), salary) *12 연봉
         , department_id 부서번호
   from employees
   where department_id in(30, 50, 60) and
         nvl(salary + (salary * commission_pct), salary) *12 >= 20000 and
         nvl(salary + (salary * commission_pct), salary) *12 <= 60000
   order by 4, 3 desc;  --> in( ) 은 괄호가 있는 or 이다.
   
   
   
   
   
   ------- ****  ===== 범위 연산자 ===== ***** ---------
   --   >   <   >=  <=  between A and B(A이상 B이하)
   --   범위 연산자에 사용되는 데이터는 숫자 뿐만 아니라 문자, 날짜까지 모두 사용된다.
   select employee_id 사원번호
         , first_name ||' '|| last_name 사원명
         , nvl(salary + (salary * commission_pct), salary) *12 연봉
         , department_id 부서번호
   from employees
   where department_id in(30, 50, 60) and
         nvl(salary + (salary * commission_pct), salary) *12 between 20000 and 60000
   order by 4, 3 desc;  --> in( ) 은 괄호가 있는 or 이다.
   
   -- 또는
   select first_name, last_name, salary
   from employees
   where first_name between 'J' and 'S'
   order by first_name;
   
   ----- *** === 현재시각을 알려주는 것 === *** -----
   select sysdate, current_date, localtimestamp,                 systimestamp
   from dual;
   --     24/09/12	 24/09/12	 24/09/12 12:07:26.553000000    24/09/12 12:08:39.755000000 +09:00
   
   /*
       날짜타입은 date 이다.
       date 타입의 기본적인 표현방식은 'RR/MM/DD' 으로 나타내어진다.
       RR 은 년도의 2자리만 나타내어주는데 50 ~ 99 는  1950 ~ 1999 을 말하는 것이다.
       RR 은 년도의 2자리만 나타내어주는데 00 ~ 49 는  2000 ~ 2049 을 말하는 것이다.
       MM 은 월이고, DD 는 일이다.
   */
   select sysdate
        , to_char(sysdate, 'yyyy-mm-dd')
        , to_char(sysdate, 'hh24:mi:ss')
        , to_char(sysdate, 'yyyy^^mm:<dd hh24:mi:ss')
        , to_char(sysdate, 'yyyy^^mm:<dd hh24:mi:ss')
        , to_char(sysdate, 'yyyy#mm#dd am hh:mi:ss')
        , to_char(sysdate, 'yyyy#mm#dd pm hh:mi:ss')
        , to_char(sysdate, 'yyyy~mm~dd hh24:mi:ss')
   from dual;
   
   desc employees;
   --HIRE_DATE(입사일자)      NOT NULL DATE (날짜)
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , hire_date 입사일
        , to_char(hire_date, 'yyyy-mm-dd hh24:mi:ss') 입사일자2
   from employees;
   --154	Nanette Cambrault	06/12/09	2006-12-09
   -- employees 테이블에 저장된 사원번호가 154인 행에 대해서 hire_date(입사일자) 컬럼의 값을 '2006-12-31 09:00:00' 로 수정(변경)하고자 한다. 
   
   update employees set hire_date = to_date('2006-12-31 09:00:00', 'yyyy-mm-dd hh24:mi:ss')
   where employee_id = 154;
   --1 행 이(가) 업데이트되었습니다.
   commit;
   --커밋 완료.
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , hire_date 입사일
        , to_char(hire_date, 'yyyy-mm-dd') 입사일자2
   from employees;
   
   
   -- employees 테이블에서 입사일자가 2005년 1월 1일 부터 2006년 12월 31일 까지 입사한 사원들만
   -- 사원번호, 사원명, 입사일자를 나타내세요.
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , hire_date 입사일
        , to_char(hire_date, 'yyyy-mm-dd') 입사일자2
   from employees
   where '05/01/01' <= hire_date and hire_date <= '06/12/31'
   order by 3;
   -- 틀린 풀이!!!--
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , hire_date 입사일
        , to_char(hire_date, 'yyyy-mm-dd') 입사일자2
   from employees
   where hire_date between '05/01/01' and '06/12/31'
   order by 3;
    -- 틀린 풀이!!!--
   
   ---w(ﾟДﾟ)w
   --- !!!!! 중요 !!!!  날짜를 나타낼때 시,분,초 가 없는 년,월,일만 나타내어주면 자동적으로 0시0분0초가 된다.
   ---                 즉, 자정(그날의 시작)을 뜻한다.
   
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , hire_date 입사일
        , to_char(hire_date, 'yyyy-mm-dd') 입사일자2
   from employees
   where '05/01/01' <= hire_date and hire_date < '07/01/01' -- 미만
   order by 3;
    -- 올바른 풀이!!!--
    
    'A' --> 65
    'a' --> 97
    '0' --> 48
    ' ' --> 32
    select ascii('A'), ascii('a'), ascii('0'), ascii(' ')
    from dual;
    --65	97	48	32
    select chr(65), chr(97), chr(48), chr(32)
    from dual;
    --A	 a	 0	  (공백)
    
    --——————————————————————————————————————————
    --어떤 테이블에 존재하는 행의 컬럼의 값을 변경(수정)하려고 할 때는
    --update 명령어를 사용해서 변경해준다.
    
    select *
    from employees
    where employee_id = 100;
    
    /*
        —————————————————————————————————————
        employer_id     first_name      last_name       hire_date
        —————————————————————————————————————
             100          Steven           King          03/06/17
    */
    
    update employees set first_name = '규영', last_name = '연', hire_date = sysdate
    where employee_id = 100;    -- -> 메모리(RAM)상에서 변경된 것이다.
    
    select employee_id, first_name, last_name, hire_date
    from employees
    where employee_id = 100;
    
    
    commit; --> 메모리(RAM)상에서 변경 되어진 것을 실제 디스크(DISK) 파일에 적용(저장)시켜주는 것이다.
    
    update employees set first_name = 'こんにちは', last_name = 'よろしく', hire_date = sysdate
    where employee_id = 100;    -- -> 메모리(RAM)상에서 변경된 것이다.
    
    select employee_id, first_name, last_name, hire_date
    from employees
    where employee_id = 100;
    
    rollback; --> 메모리(RAM)상에서 변경되어진 정보를 삭제해주는 것이다.
              --> 즉, 위에서 실행한 update 명령을 한 결과를 취소하겠다는 말이다.
    
    select employee_id, first_name, last_name, hire_date
    from employees
    where employee_id = 100;
    
    desc employees;
    
    update employees set first_name = 'Steven', last_name = 'King', hire_date = to_date('03/06/17', 'RR/MM/DD') -->'03/06/17' 이렇게 입력해도 윈도우는 ok 리눅스는 x to_date('03/06/17', 'RR/MM/DD')이렇게 입력해야 됨
    where employee_id = 100;    -- -> 메모리(RAM)상에서 변경된 것이다.
    
    --rollback;
    
    commit;
    
    select employee_id, first_name, last_name, hire_date
    from employees
    where employee_id = 100;
    
    --*** employees 테이블에 jubun(주민번호) 이라는 컬럼을 추가하겠습니다. ***---
    desc employees;
    
    select 0010204234567, 9010202234567
        , '0010204234567', '9010202234567'
    from dual;
    
    select *
    from employees;
    
    /*
       jubun(주민번호) 컬럼의 값을 입력할때는 '-' 는 빼고 숫자로만 입력할 것입니다.
       jubun(주민번호) 컬럼의 값을 입력할 때 맨 처음 첫자리에 0 이 들어올 수 있다라면 
       number 타입이 아니라 varchar2 타입으로 해야 한다.
       왜냐하면 number 타입으로 해주면 맨 앞에 입력한 값이 0 일때는 0이 생략 되어지기 때문이다.
       맨 앞의 0 도 나오게 하려면 컬럼의 데이터 타입은 varchar2 가 되어야 한다.
   */
    
    
    -- *** 컬럼 추가하기 *** --
    alter table employees
    add jubun varchar2(13);
    
    desc employees;
    
    select *
    from employees;
    
    
    update employees set jubun = '6101131234567'
    where employee_id = 100;
    
    update employees set jubun = '8510151234567'
    where employee_id = 101;
    
    update employees set jubun = '6510152234567'
    where employee_id = 102;
    
    update employees set jubun = '7510151234567'
    where employee_id = 103;
    
    update employees set jubun = '6110152234567'
    where employee_id = 104;
    
    update employees set jubun = '6510151234567'
    where employee_id = 105;
    
    update employees set jubun = '6107221234567'
    where employee_id = 106;
    
    update employees set jubun = '0803153234567'
    where employee_id = 107;
    
    update employees set jubun = '0712154234567'
    where employee_id = 108;
    
    update employees set jubun = '8810151234567'
    where employee_id = 109;
    
    update employees set jubun = '8908152234567'
    where employee_id = 110;
    
    update employees set jubun = '9005052234567'
    where employee_id = 111;
    
    update employees set jubun = '6610151234567'
    where employee_id = 112;
    
    update employees set jubun = '6710151234567'
    where employee_id = 113;
    
    update employees set jubun = '6709152234567'
    where employee_id = 114;
    
    update employees set jubun = '6110151234567'
    where employee_id = 115;
    
    update employees set jubun = '6204291234567'
    where employee_id = 116;
    
    update employees set jubun = '6110152234567'
    where employee_id = 117;
    
    update employees set jubun = '7810151234567'
    where employee_id = 118;
    
    update employees set jubun = '7909151234567'
    where employee_id = 119;
    
    update employees set jubun = '7702152234567'
    where employee_id = 120;
    
    update employees set jubun = '7009151234567'
    where employee_id = 121;
    
    update employees set jubun = '7111011234567'
    where employee_id = 122;
    
    update employees set jubun = '8010131234567'
    where employee_id = 123;
    
    update employees set jubun = '8110191234567'
    where employee_id = 124;
    
    update employees set jubun = '9012132234567'
    where employee_id = 125;
    
    update employees set jubun = '9406251234567'
    where employee_id = 126;
    
    update employees set jubun = '9408252234567'
    where employee_id = 127;
    
    update employees set jubun = '9204152234567'
    where employee_id = 128;
    
    update employees set jubun = '8507251234567'
    where employee_id = 129;
    
    update employees set jubun = '6511111234567'
    where employee_id = 130;
    
    update employees set jubun = '0010153234567'
    where employee_id = 131;
    
    update employees set jubun = '0005254234567'
    where employee_id = 132;
    
    update employees set jubun = '0110194234567'
    where employee_id = 133;
    
    update employees set jubun = '0412154234567'
    where employee_id = 134;
    
    update employees set jubun = '0503253234567'
    where employee_id = 135;
    
    update employees set jubun = '9510012234567'
    where employee_id = 136;
    
    update employees set jubun = '9510021234567'
    where employee_id = 137;
    
    update employees set jubun = '9610041234567'
    where employee_id = 138;
    
    update employees set jubun = '9610052234567'
    where employee_id = 139;
    
    update employees set jubun = '7310011234567'
    where employee_id = 140;
    
    update employees set jubun = '7310092234567'
    where employee_id = 141;
    
    update employees set jubun = '7510121234567'
    where employee_id = 142;
    
    update employees set jubun = '7612012234567'
    where employee_id = 143;
    
    update employees set jubun = '7710061234567'
    where employee_id = 144;
    
    update employees set jubun = '7810052234567'
    where employee_id = 145;
    
    update employees set jubun = '6810251234567'
    where employee_id = 146;
    
    update employees set jubun = '6811062234567'
    where employee_id = 147;
    
    update employees set jubun = '6712052234567'
    where employee_id = 148;
    
    update employees set jubun = '6102231234567'
    where employee_id = 149;
    
    update employees set jubun = '6210062234567'
    where employee_id = 150;
    
    update employees set jubun = '6110191234567'
    where employee_id = 151;
    
    update employees set jubun = '5712062234567'
    where employee_id = 152;
    
    update employees set jubun = '5810231234567'
    where employee_id = 153;
    
    update employees set jubun = '6311051234567'
    where employee_id = 154;
    
    update employees set jubun = '6412182234567'
    where employee_id = 155;
    
    update employees set jubun = '6110191234567'
    where employee_id = 156;
    
    update employees set jubun = '6210112234567'
    where employee_id = 157;
    
    update employees set jubun = '6311132234567'
    where employee_id = 158;
    
    update employees set jubun = '8511112234567'
    where employee_id = 159;
    
    update employees set jubun = '8710131234567'
    where employee_id = 160;
    
    update employees set jubun = '8710072234567'
    where employee_id = 161;
    
    update employees set jubun = '9010171234567'
    where employee_id = 162;
    
    update employees set jubun = '9112072234567'
    where employee_id = 163;
    
    update employees set jubun = '9110241234567'
    where employee_id = 164;
    
    update employees set jubun = '9212251234567'
    where employee_id = 165;
    
    update employees set jubun = '9310232234567'
    where employee_id = 166;
    
    update employees set jubun = '9811151234567'
    where employee_id = 167;
    
    update employees set jubun = '9810252234567'
    where employee_id = 168;
    
    update employees set jubun = '9910301234567'
    where employee_id = 169;
    
    update employees set jubun = '0910153234567'
    where employee_id = 170;
    
    update employees set jubun = '1011153234567'
    where employee_id = 171;
    
    update employees set jubun = '1006153234567'
    where employee_id = 172;
    
    update employees set jubun = '1111154234567'
    where employee_id = 173;
    
    update employees set jubun = '1209103234567'
    where employee_id = 174;
    
    update employees set jubun = '1207154234567'
    where employee_id = 175;
    
    update employees set jubun = '0906153234567'
    where employee_id = 176;
    
    update employees set jubun = '0812113234567'
    where employee_id = 177;
    
    update employees set jubun = '9810132234567'
    where employee_id = 178;
    
    update employees set jubun = '8712111234567'
    where employee_id = 179;
    
    update employees set jubun = '8310012234567'
    where employee_id = 180;
    
    update employees set jubun = '6510191234567'
    where employee_id = 181;
    
    update employees set jubun = '6510221234567'
    where employee_id = 182;
    
    update employees set jubun = '6510232234567'
    where employee_id = 183;
    
    update employees set jubun = '8512131234567'
    where employee_id = 184;
    
    update employees set jubun = '8510182234567'
    where employee_id = 185;
    
    update employees set jubun = '7510192234567'
    where employee_id = 186;
    
    update employees set jubun = '8512192234567'
    where employee_id = 187;
    
    update employees set jubun = '9511151234567'
    where employee_id = 188;
    
    update employees set jubun = '7509302234567'
    where employee_id = 189;
    
    update employees set jubun = '8510161234567'
    where employee_id = 190;
    
    update employees set jubun = '9510192234567'
    where employee_id = 191;
    
    update employees set jubun = '0510133234567'
    where employee_id = 192;
    
    update employees set jubun = '0810194234567'
    where employee_id = 193;
    
    update employees set jubun = '0910183234567'
    where employee_id = 194;
    
    update employees set jubun = '1010134234567'
    where employee_id = 195;
    
    update employees set jubun = '9510032234567'
    where employee_id = 196;
    
    update employees set jubun = '9710181234567'
    where employee_id = 197;
    
    update employees set jubun = '9810162234567'
    where employee_id = 198;
    
    update employees set jubun = '7511171234567'
    where employee_id = 199;
    
    update employees set jubun = '7810172234567'
    where employee_id = 200;
    
    update employees set jubun = '7912172234567'
    where employee_id = 201;
    
    update employees set jubun = '8611192234567'
    where employee_id = 202;
    
    update employees set jubun = '7810252234567'
    where employee_id = 203;
    
    update employees set jubun = '7803251234567'
    where employee_id = 204;
    
    update employees set jubun = '7910232234567'
    where employee_id = 205;
    
    update employees set jubun = '8010172234567'
    where employee_id = 206;
    
    commit;
    
    select employee_id, jubun
    from employees;
    
    
    
    -------------------------------------------------------------------------------------------------------
    
    
    
    ---- **** ==== like 연산자 ==== **** ----
    
    select *
    from employees
    where department_id = 30;
    
    select *
    from employees
    where department_id like 30;
    
    /*
        like 연산자와 함께 사용되어지는 % 와 _ 를 wild character 라고 부른다.
        like 연산자와 함께 사용되어지는 % 의 뜻은 글자수와는 관계없이 글자가 있든지 없든지 관계없다라는 말이고,
        like 연산자와 함께 사용되어지는 _ 의 뜻은 반드시 아무글자 1개만을 뜻하는 것이다.
    */
    
    -- employees 테이블에서 여자 1990년생과 남자 1991년생의 사원들만
    -- 사원번호, 사원명, 주민번호를 나타내세요

    select employee_id 사원번호
        , first_name || ' ㅡ' || last_name 사원명
        , jubun 주민번호
    from employees
    where jubun like '90____2%' or
         jubun like '91____1%';
    
    
    -- employees 테이블에서 first_name 컬럼의 값이 'J' 로 시작하는 사원들만
    -- 사원번호, 이름, 성, 기본급여를 나타내세요.

    select employee_id 사원번호
        , first_name 이름
        , last_name 성
        , salary 기본급여
    from employees
    where first_name like 'J%';
        
    -- employees 테이블에서 first_name 컬럼의 값이 's' 로 끝나는 사원들만
    -- 사원번호, 이름, 성, 기본급여를 나타내세요.
    select employee_id 사원번호
        , first_name 이름
        , last_name 성
        , salary 기본급여
    from employees
    where first_name like '%s';
    
    
    -- employees 테이블에서 first_name 컬럼의 값중에 'ee' 라는 글자가 들어있는 사원들만
    -- 사원번호, 이름, 성, 기본급여를 나타내세요. 
    select employee_id 사원번호
        , first_name 이름
        , last_name 성
        , salary 기본급여
    from employees
    where first_name like '%ee%';
    
    
    -- employees 테이블에서 first_name 컬럼의 값중에 'e' 가 2개이상 들어있는 사원들만
    -- 사원번호, 이름, 성, 기본급여를 나타내세요. 
    select employee_id 사원번호
        , first_name 이름
        , last_name 성
        , salary 기본급여
    from employees
    where first_name like '%e%e%';
    
    
    -- employees 테이블에서 last_name 컬럼의 값이 첫글자는 'F' 이고 두번째 글자는 아무거나 이고
    -- 세번째 글자는 소문자 'e' 이며 4번째 부터는 글자가 있든지 없든지 상관없는 사원들만 
    -- 사원번호, 이름, 성, 기본급여를 나타내세요. 
    select employee_id 사원번호
        , first_name 이름
        , last_name 성
        , salary 기본급여
    from employees
    where last_name like 'F_e%';
    
    --- *** like 연산자와 함꼐 사용되어지는 %와 _인 wild character 의 escape(탈출) 시키기 *** ---
    
    create table tbl_watch
    (--watchname  varchar2(10)    -- varchar2(10)은 최대 10byte 까지만 허용           --> '쌍용교육센터' --> 12byte 이므로 입력불가 'oracle' --> 6byte --> OK
     watchname nvarchar2(10)    -- nvarchar2(10)은 최대 글자수가 10글자 까지만 허용    --> '쌍용교육센터' --> 6글자 이므로 입력 OK 'oracle' --> 6글자 --> OK
    , bigo     nvarchar2(100)
    );
    --Table TBL_WATCH이(가) 생성되었습니다.


    desc tbl_watch;

--어떤 테이블에 데이터(행)를 입력할때는 insert 라는 명령어를 사용한다.
    insert into tbl_watch(watchname, bigo) values('금시계', '순금 99.99% 함유 고급시계');
    
    select *
    from tbl_watch;
    
    commit;
    
    insert into tbl_watch(watchname, bigo) values('은시계', '고객만족도 99.99점 획득한 고급시계');
    insert into tbl_watch(watchname, bigo) values('동시계', '가성비가 뛰어난 고급시계');
    
    create table tbl_test
    (--watchname  varchar2(10)    -- varchar2(10)은 최대 10byte 까지만 허용           --> '쌍용교육센터' --> 12byte 이므로 입력불가 'oracle' --> 6byte --> OK
     watchname nvarchar2(10)    -- nvarchar2(10)은 최대 글자수가 10글자 까지만 허용    --> '쌍용교육센터' --> 6글자 이므로 입력 OK 'oracle' --> 6글자 --> OK
    , bigo     nvarchar2(100)
    );
    
    --Table TBL_TEST이(가) 생성되었습니다. 자동으로 커밋이 된다
    
    -- DML(Data Manuplation Language) : insert, update, delete, merge ==> 수동commit 이다. 직접 커밋 해줘야함
    -- DDL(Data Definition Language) : create, alter, drop, truncate  ==> 자동 commit 이다.
    -- DCL(Data Control Language) : grant, revoke                     ==> 자동 commit
    -- TCL(Transaction Control Language) : commit, rollback
    
    rollback;
    --위에서 create가 되어서 자동 commit 이 되어져서 rollback이 의미 없다.
    
    --특정행 삭제하기
    delete from  tbl_watch -- from 은 생략 가능
    where watchname = '은시계';
    
    select *
    from tbl_watch;
    
    commit;
    
    --모든 행 삭제하기
    delete from tbl_watch;
    
    commit;
    
    
    insert into tbl_watch(watchname, bigo) values('금시계', '순금 99.99% 함유 고급시계');
    insert into tbl_watch(watchname, bigo) values('은시계', '고객만족도 99.99점 획득한 고급시계');
    insert into tbl_watch(watchname, bigo) values('동시계', '가성비가 뛰어난 고급시계');
    
    commit;
    
    delete tbl_watch;
    
    
    rollback;
    
    /*
        WATCHNAME	BIGO
        금시계	순금 99.99% 함유 고급시계
        은시계	고객만족도 99.99점 획득한 고급시계
        동시계	가성비가 뛰어난 고급시계
    */
    
    --tbl_watch 테이블에서 bigo 컬럼에 99.99% 라는 글자가 들어있는 행만 추출하세요.
    select *
    from tbl_watch
    where bigo like '%99.99%%';
    
    ---- *** like 연산자와 함께 사용되어지는 % 와 _ 인 wild character 의 escape(탈출) 시키기 *** ---
    
    select *
    from tbl_watch
    where bigo like '%99.99\%%' escape '\' ;
    -- escape 문자로 '\'를 주었으므로 '\' 다음에 나오는 % 1개만 wild character 기능에서 탈출시켜 버리므로 %는 진짜 글자 퍼센트(%)로 된다.
    
    select *
    from tbl_watch
    where bigo like '%99.992%%' escape '2' ;
    -- escape 문자로 '\'를 주었으므로 '\' 다음에 나오는 % 1개만 wild character 기능에서 탈출시켜 버리므로 %는 진짜 글자 퍼센트(%)로 된다.
    
    select *
    from tbl_watch
    where bigo like '%99.99a%%' escape 'a' ;
    -- escape 문자로 '\'를 주었으므로 '\' 다음에 나오는 % 1개만 wild character 기능에서 탈출시켜 버리므로 %는 진짜 글자 퍼센트(%)로 된다.
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    --------------------------------------------------------------------------------------------------------------------------
    
            --------->> 단일행 함수 <<---------------
    /*
        🙌단일행 함수의 종류
        1. 문자 함수
        2. 숫자 함수
        3. 날짜 함수
        4. 변환 함수
        5. 기타 함수
    
    */
    
    ---------------->> 1. 문자 함수 <<-------------------------------
    -- 1.1 upper('문자열') ==>  '문자열' 에 영문자가 있으면 모두 대문자로 변환시켜주는 것. 
    select 'kOreA SEoul', upper('kOreA SEoul')
    from dual;
    -- 1.2 lower('문자열') ==>  '문자열' 에 영문자가 있음녀 모두 소문자로 변환시켜주는 것.
    select 'kOreA SEoul', lower('kOreA SEoul')
    from dual;
    -- 1.3 initcap('문자열') ==> '문자열' 을 단어별(구분자가 공백)로 첫글자만 대문자로, 나머지는 모두 소문자로 변환시켜주는 것.
    select 'kOreA SEoul', initcap('kOreA SEoul')
    from dual;
    
    select *
    from employees
    where last_name = 'King';
    
    select last_name, upper(last_name), lower(last_name), initcap(last_name)
    from employees;
    
    select *
    from employees
    where initcap(last_name) = initcap('KinG');
    
    -- 1.4  substr('문자열', 시작글자번호, 뽑아야할글자길이)
    --      ==> '문자열' 중에 문자열의 특정 일부분을 선택해올 때 사용한다.
    
    select '쌍용교육센터'
      , substr('쌍용교육센터', 2, 3)  -- '쌍용교육센터' 에서 2번째 글자인 '용' 부터 3글자만 뽑아온다.
      , substr('쌍용교육센터', 2)     -- '쌍용교육센터' 에서 2번째 글자인 '용' 부터 끝까지 뽑아온다.
    from dual;
    
    ---  *** substr() 함수를 사용하여 employees 테이블에서 성별이 '여자'인 사원들만
    ---      사원번호, 사원명, 주민번호를 나타내세요. ***
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , jubun 주민번호
    from employees
    where substr(jubun, 7, 1) = '2' 
        or substr(jubun, 7, 1) = '4';
        
    --- *** substr() 함수를 사용하여 employees 테이블에서 1990년 ~ 1995년에 태어난 사원들중
    ---     성별이 '남자' 인 사원들만 사원번호, 사원명, 주민번호를 나타내세요. ***
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , jubun 주민번호
    from employees
    where substr(jubun,1, 2) in('90', '91', '92', '93', '94', '95')
        and substr(jubun, 7, 1) = '1';
    
    select jubun
    from employees
    where substr(jubun, 1, 2) between '90' and '95';
    ----------------------------------------9월12일----------------------------------------------------------------
    
    
    -- 1.5  instr : 어떤 문자열에서 명명된 문자열의 위치를 알려주는 것 --
 
     select '쌍용교육센터 서울교육대학교 교육문화원'
     
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1, 1)  교육-- 3
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 1 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1, 2)  교육1-- 10
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 1 번째 부터 검색해서 2 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 4, 1)  교육2-- 10
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 4 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.      
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 4, 3)  교육3-- 0
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 4 번째 부터 검색해서 3 번째로 나오는 '교육'의 위치를 알려달라는 말이다.            
          -- 그러한 값이 없다라면 0 이 나온다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1)  교육4-- 3
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 1 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          -- 출발점만 나오면 뒤에 ,1 이 생략된 것이다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 4)  교육5-- 10
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 4 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          -- 출발점만 나오면 뒤에 ,1 이 생략된 것이다.
     from dual;
     
     -- 1.6  reverse : 어떤 문자열을 거꾸로 보여주는 것이다. --
     select 'ORACLE', reverse('ORACLE')
           ,'대한민국', reverse('대한민국'), reverse( reverse('대한민국') )
     from dual;
     
          ------ [퀴즈] -------
     create table tbl_files
     (fileno      number(3)
     ,filepath    varchar2(200)
     );
     -- Table TBL_FILES이(가) 생성되었습니다.
     
     insert into tbl_files(fileno, filepath) values(1, 'c:\myDocuments\resume.hwp'); 
     insert into tbl_files(fileno, filepath) values(2, 'd:\mymusic.mp3');
     insert into tbl_files(fileno, filepath) values(3, 'c:\myphoto\2024\02\face.jpg');
     
     commit;
     -- 커밋 완료.
     
     select fileno, filepath
     from tbl_files;
     
         -- 아래와 같이 나오도록 하세요. --
     /*
       --------------------------
        파일번호       파일명
       --------------------------
         1             resume.hwp
         2             mymusic.mp3
         3             face.jpg   
       --------------------------  
     */
     
     select fileno 파일번호, reverse(   substr(reverse(filepath),1,(  instr(reverse(filepath),'\',1)   -1))   ) 파일명
     from tbl_files;
     
     select *
     from tbl_files
     where filepath;
     
     select '쌍용교육센터 서울교육대학교 교육문화원'
 
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', 1, 1)  -- 3
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 1 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -1, 1)  -- 16
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 역순으로 1 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
         
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -1)  -- 16
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -5, 1)  -- 16
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 역순으로 5 번째 부터 검색해서 1 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -6, 2)  -- 3
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 역순으로 6 번째 부터 검색해서 2 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          
          , instr('쌍용교육센터 서울교육대학교 교육문화원', '교육', -6, 3)  -- 0
          -- '쌍용교육센터 서울교육대학교 교육문화원' 에서 '교육' 이 나오는 위치를 찾는데
          -- 출발점이 역순으로 6 번째 부터 검색해서 2 번째로 나오는 '교육'의 위치를 알려달라는 말이다.
          -- 그러한 값이 없다라면 0 이 나온다.
     from dual;
     
     select fileno 파일번호, substr(filepath,instr(filepath,'\', -1)+1)  파일명
     from tbl_files;
     
     
     
     -- 1.7  lpad : 왼쪽부터 문자를 자리채움 *** -----
     -- 1.8  rpad : 오른쪽부터 문자를 자리채움 *** -----
     
     select lpad('교육센터', 20, '*') -- 20 byte를 확보해서 거기에 '교육센터'를 넣습니다.
         ,  rpad('교육센터', 20, '*') -- 20 byte를 확보해서 거기에 '교육센터'를 넣습니다.
     from dual;
     
     select lpad('*', 10, '*'), rpad ('=', 10, '=')
     from dual;
     
     
      -- 1.9   ltrim : 왼쪽부터 문자를 제거한다.  ------
 -- 1.10  rtrim : 오른쪽부터 문자를 제거한다. ------
 -- 1.11  trim :  왼쪽,오른쪽부터 공백문자를 제거한다. ------
    
    select ltrim('abbbcccccdaabbccdTabaacccdddSSS', 'abcd'),
           rtrim('abbbcccccdaabbccdTabaacccddd', 'abcd'),
           rtrim( ltrim('abbbcccccdaabbccdTabaacccddd', 'abcd'), 'abcd' )
    from dual;
    
    select '쌍용' || '                     교육                 센터'
         , '쌍용' || ltrim('                     교육                 센터') 
    from dual;
    
    select '쌍용                    ' || '교육                 센터' 
         , rtrim('쌍용                    ') || '교육                 센터'
    from dual;
    
    
    select '쌍용' || '          교육              ' || '센터' 
         , '쌍용' || trim('          교육              ') || '센터'  
    from dual;
     
     
     -- 1.12   translate  ------
    select translate('010-3456-7890'
                    ,'0123456789'
                    ,'영일이삼사오육칠팔구')
    from dual;
    
    
    -- 1.13   replace  ------
    select replace('쌍용교육센터 서울교육대학교 교육문화원'
                  ,'교육'
                  ,'education')
    from dual;
    
    
    -- 1.14   length  ==> 문자열의 길이를 알려주는 것  *** ------
    select length('쌍용center')  -- 8
    from dual;
    
    
    ----------------------------------------->> 2. 숫자 함수 <<------------------------------------------------------
    
    --2.1 mod : 나머지를 구해주는 것
    select 5/2, mod(5,2), trunc(5/2)
    from dual;
     
    
    
    -- 2.2 round : 반올림을 해주는 것
    select 94.547
       , round(94.547)         -- 95
       , round(94.547, 0)      -- 95       0 은 정수 1자리까지만 나타내어준다.
       , round(94.547, 1)      -- 94.5     1 은 소수 첫째자리까지만 나타내어준다.
       , round(94.547, 2)      -- 94.55    2 은 소수 둘째자리까지만 나타내어준다.
       , round(94.547, -1)     -- 90       -1 은 정수 10자리까지만 나타내어준다.
       , round(94.547, -2)     -- 100      -2 은 정수 100자리까지만 나타내어준다.
  from dual;
  
  -- 2.3  trunc : 절삭을 해주는 것.
  select 94.547
       , trunc(94.547)         -- 94
       , trunc(94.547, 0)      -- 94       0 은 정수 1자리까지만 나타내어준다.
       , trunc(94.547, 1)      -- 94.5     1 은 소수 첫째자리까지만 나타내어준다.
       , trunc(94.547, 2)      -- 94.54    2 은 소수 둘째자리까지만 나타내어준다.
       , trunc(94.547, -1)     -- 90       -1 은 정수 10자리까지만 나타내어준다.
       , trunc(94.547, -2)     -- 0        -2 은 정수 100자리까지만 나타내어준다.
  from dual;
  
  
    -- *** [성적처리] *** --
  create table tbl_sungjuk
  (hakbun      varchar2(20)
  ,name        Nvarchar2(10)
  ,kor         number(3)
  ,eng         number(3)
  ,math        number(3)
  );
  -- Table TBL_SUNGJUK이(가) 생성되었습니다.
  
  select *
  from tbl_sungjuk;
    
  --- *** 데이터 입력하기 *** ---
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist001','한석규',90,92,93);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist002','두석규',100,100,100);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist003','세석규',71,72,73);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist004','네석규',89,87,81);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist005','오석규',60,50,40);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist006','육석규',80,81,87);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist006','육석규',80,81,87);
  
  rollback;
  commit; 
  -- 커밋 완료.
  
  
  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
    학번  성명  국어  영어  수학  총점  평균(소수부 첫째자리까지 나타내되 반올림)  학점(평균이 90 이상이면 'A', 90 미만 80 이상이면 'B', 80 미만 70 이상이면 'C'.... 60 미만이면 'F')   
  ---------------------------------------------------------------------------------------------------------------------------------------------------------------------
  
  desc tbl_sungjuk;
  
  select *
  from tbl_sungjuk;
  
  select hakbun 학번
        ,name 이름
        ,kor 국어
        ,eng 영어
        ,math 수학
        ,kor + eng + math 총점
        ,round((kor+eng+math)/3 , 1) 평균
        ,case trunc(round((kor+eng+math)/3 , 1), -1)
            when 100 then 'A'
            when  90 then 'A'
            when  80 then 'B'
            when  70 then 'C'
            when  60 then 'D'
            else 'F'
            end 학점1
        ,decode (trunc(round((kor+eng+math)/3 , 1), -1), 100, 'A'
                                                    , 90, 'A'
                                                    , 80, 'B'
                                                    , 70, 'C'
                                                    , 60, 'D'
                                                        , 'F') 학점2
  from tbl_sungjuk;
  
      -- 2.4  power : 거듭제곱
      select 2*2*2*2*2, power(2,5) -- 2의 5승
      from dual;
  
     -- 2.5  sqrt : 제곱근
     select sqrt(4), sqrt(16), sqrt(2), sqrt(3)
     from dual;
     
     -- 2.6  sin, cos, tan, asin, acos, atan
     select sin(90), cos(90), tan(90), asin(0.3), acos(0.3), atan(0.3)
     from dual;
     
     -- 2.7  log
     select log(10, 100)
     from dual;
     --   2
  
  
      -- 2.8  sign  ==> 결과값이 양수이라면 1, 결과값이 0 이라면 0, 결과값이 음수이라면 -1
     select sign(5-2), sign(5-5), sign(3-5)
     from dual;
     --        1         0            -1 
      
      
      -- 2.9  ceil(실수)  ==> 입력되어진 실수 보다 큰 최소의 정수를 나타내어준다.
     --      ceil(정수)  ==> 입력되어진 정수를 그대로 나타내어준다.
     select ceil(10.1), ceil(-10.1), ceil(10), ceil(-10)
     from dual;
 --        11         -10           10        -10
  
      -- 2.10  floor(실수)  ==> 입력되어진 실수 보다 작은 최대의 정수를 나타내어준다.
     --       floor(정수)  ==> 입력되어진 정수를 그대로 나타내어준다.
     select floor(10.1), floor(-10.1), floor(10), floor(-10)
     from dual;
  
      -- 2.11  ascii, chr 
     select ascii('A'), ascii('a'), ascii('0'), ascii(' ')
     from dual;
     --         65         97          48         32
        
     select chr(65), chr(97), chr(48), chr(32)
     from dual;
     --        A        a       0      ' ' 
  
  
  
  
  
  
  ---------------------------------------->> 날짜 함수<<-----------------------------------------------------------------------
  
  /*
    날짜1 + 숫자 = 날짜2  ==> 날짜1 에서 숫자(일수)만큼 더한 값이 날짜2 가 된다.
    날짜1 - 숫자 = 날짜2  ==> 날짜1 에서 숫자(일수)만큼 뺀 값이 날짜2 가 된다.
    
    여기서 중요한 것은 숫자의 단위는 일수 이다.
 */
  
  
  select 
        sysdate -1, to_char(sysdate-1, 'yyyy-mm-dd hh24:mi:ss') 어제시각,
        sysdate, to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각,
        sysdate+1, to_char(sysdate+1, 'yyyy-mm-dd hh24:mi:ss') 내일시각
  from dual;
  
  --- 단위환산 ---
  /*
     1 kg = 1000 g
     1 g = 1/1000 kg
     1 일 = 24 시간
     1 시간 = 60 분
     1 분 = 1/60 시간
     1 분 = 60 초
     1 초 = 1/60 분
  */
  
  --*** 현재시각으로 부터 1일 2시간 3분 4초 뒤를 나타내세요
  
  select
        to_char(sysdate+1+ 2/24 + (3/(24*60)) + (4/(24*60*60)) , 'yyyy-mm-dd hh24:mi:ss') 시간
  from dual;
  
  -- 3.1  add_months(날짜, 숫자)
  /*
        ==> 숫자가 양수이면 날짜에서 숫자 개월수 만큼 더해준 날짜를 나타내는 것이고,
            숫자가 음수이면 날짜에서 숫자 개월수 만큼 뺀    날짜를 나타내는 것이다.
            
        여기서 숫자의 단위는 개월수 이다.     
  */
  select 
        to_char(add_months(sysdate, -2), 'yyyy-mm-dd hh24:mi:ss') "2개월 전",
        to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각,
        to_char(add_months(sysdate, 2), 'yyyy-mm-dd hh24:mi:ss') "2개월 후"
  from dual;
  
  
  --[퀴즈] 현재일로부터 1개월 2일 3시간 4분 5초 뒤를 나타내세요
  select
        to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각,
        to_char(add_months(sysdate, 1)+2+ 3/24 + (4/(24*60)) + (5/(24*60*60)) , 'yyyy-mm-dd hh24:mi:ss') 시간
  from dual;
  
  
  
  -- 3.2  months_between(날짜1, 날짜2)
  /*
          날짜1 에서 날짜2 를 뺀 값으로 그 결과는 숫자가 나오는데 결과물 숫자의 단위는 개월수 이다.
          즉, 두 날짜의 개월차이를 구할 때 사용한다.
  */
  
  select months_between(add_months(sysdate, 3), sysdate) "개월수 차이"
  from dual;
  
  
  --  ****   날짜1 - 날짜2 = 숫자   ==> 날짜1 에서 날짜2 를 뺀 값으로 숫자가 나오는데 결과물 숫자의 단위는 일수 이다.
  --                                  즉, 두 날짜의 일수차이를 구할 때 사용한다.
  
  select sysdate + 3 -sysdate
  from dual;
  
  
  select add_months(sysdate,1) -sysdate
  from dual;
  
  
  -- 3.3  extract  ==> 날짜에서 년, 월, 일을 숫자형태로 추출해주는 것이다.
  select sysdate
       , extract(year from sysdate) , to_char(sysdate, 'yyyy')
       , extract(month from sysdate), to_char(sysdate, 'mm')
       , extract(day from sysdate)  , to_char(sysdate, 'dd')
  from dual;
  
  select to_date('2024-09-14', 'yyyy-mm-dd') -sysdate
  select to_date('2024/09/14', 'yyyy/mm/dd') -sysdate
  from dual;
  
  select employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,substr(jubun, 1, 7) || '*******' 주민번호1
        ,substr(jubun, 1, 7) || lpad('*',6,'*') 주민번호2
        ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') 주민번호3
        
  from employees;
  
  select employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') 주민번호
        ,case substr(jubun, 7, 1)
                when '1' then '남'
                when '2' then '여'
                when '3' then '남'
                else '여'
                end  성별
        ,case 
            when substr(jubun, 7, 1) in ('1', '3') then '남'
            else '여'
        end 성별2
        
        , case substr(jubun, 7, 1)
        when '1' then '19'||substr(jubun, 1, 2)
        when '2' then '19'||substr(jubun, 1, 2)
        when '3' then '20'||substr(jubun, 1, 2)
        else '20'||substr(jubun, 1, 2)
        end 출생년도
        ,to_number(case substr(jubun, 7, 1)
        when '1' then '19'||substr(jubun, 1, 2)
        when '2' then '19'||substr(jubun, 1, 2)
        when '3' then '20'||substr(jubun, 1, 2)
        else '20'||substr(jubun, 1, 2)
        end) 출생년도2
        ,case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2) 출생년도3
        
        
        ,trunc((sysdate-to_date(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2) ||substr(jubun, 3,4), 'yyyymmdd'))/365.2425, 0) "만나이 내방식"
        
        
        ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
              to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
         else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
         end 만나이
        
        
        /*
            올해년도 - 출생년도   ==> 올해생일이 현재일 보다 같거다 또는 이전일 경우
            올해년도 - 출생년도-1 ==> 올해생일이 현재일 보다 이후일 경우
        */
        
  from employees;
  
  --선생님 방식
  select employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , substr(jubun, 1, 7) || lpad('*', length(jubun)-7 ,'*') AS 주민번호
       
       , case substr(jubun, 7, 1) 
              when '1' then '남'
              when '3' then '남'
              else '여'
         end AS 성별1
       
       , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS 성별2 
       
       , decode( substr(jubun, 7, 1), '1', '남'
                                    , '3', '남'
                                         , '여') AS 성별3
                                         
       , case when substr(jubun, 7, 1) in('1','2') then '19' else '20' end || substr(jubun, 1, 2) AS 출생년도1 
       
       , to_number( case when substr(jubun, 7, 1) in('1','2') then '19' else '20' end || substr(jubun, 1, 2) ) AS 출생년도2 
       
       , to_date( to_char(sysdate, 'yyyy') || substr(jubun, 3, 4), 'yyyymmdd') AS 올해생일 
       
       , case when sysdate - to_date( to_char(sysdate, 'yyyy') || substr(jubun, 3, 4), 'yyyymmdd') >= 0
              then extract(year from sysdate) - to_number( case when substr(jubun, 7, 1) in('1','2') then '19' else '20' end || substr(jubun, 1, 2) ) 
              else extract(year from sysdate) - to_number( case when substr(jubun, 7, 1) in('1','2') then '19' else '20' end || substr(jubun, 1, 2) ) - 1
         end AS 만나이     
       /*
           올해년도 - 출생년도      ==> 올해생일이 현재일과 같거나 또는 이전인 경우
           올해년도 - 출생년도 - 1  ==> 올해생일이 현재일 보다 이후인 경우
       */
       
  from employees;
  
  select 2024 - '1998',
        2024 - to_number('1998')
  from dual;
  
  -- 3.4  last_day(특정날짜)
  --      ==> 특정날짜가 포함된 달력에서 맨 마지막 날짜를 알려주는 것이다.
  
  select sysdate, last_day(sysdate)
  from dual;
  
  -- 3.5  next_day(특정날짜, '일')   '일'~'토'
  --      ==> 특정날짜로 부터 다음번에 돌아오는 가장 빠른 '일'~'토' 의 날짜를 알려주는 것이다.
  
  select sysdate
        , next_day(sysdate, '금')
        , next_day(sysdate, '월')
  from dual;
  -- 3.6  to_yminterval , to_dsinterval 
  /*
     to_yminterval 은 년 과 월을 나타내어 연산자가 + 이면 날짜에서 더해주는 것이고,
     to_dsinterval 은 일 시간 분 초를 나타내어 연산자가 + 이면 날짜에서 더해주는 것이다.
     연산자가 - 를 쓰면 날짜를 빼주는 것이다.
  */
  -- 현재일로 부터 1년 2개월 3일 4시간 5분 6초 뒤를 나타내세요
  select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각
        ,sysdate + to_yminterval('01-02') + to_dsinterval('003 04:05:06')
        ,to_char(sysdate + to_yminterval('01-02') + to_dsinterval('003 04:05:06'), 'yyyy-mm-dd hh24:mi:ss') as "1년 2개월 3일 4시간 5분 6초 이후"
        ,to_char(add_months(sysdate, 12*1+2) + 3 + 4/24 + 5/(24*60) + 6/(24*60*60), 'yyyy-mm-dd hh24:mi:ss')
  from dual;
  
  -- 현재일로 부터 1년 2개월 3일 4시간 5분 6초 전을 나타내세요
  select to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각
        ,sysdate + to_yminterval('01-02') + to_dsinterval('003 04:05:06')
        ,to_char(sysdate - to_yminterval('01-02') - to_dsinterval('003 04:05:06'), 'yyyy-mm-dd hh24:mi:ss') as "1년 2개월 3일 4시간 5분 6초 전"
        ,to_char(add_months(sysdate, -(12*1+2)) -3 -4/24 - 5/(24*60) - 6/(24*60*60), 'yyyy-mm-dd hh24:mi:ss') as "1년 2개월 3일 4시간 5분 6초 전2"
  from dual;
  
  
  
  
  
  
  
  
  
  ---------------------------------------->> 4. 변환 함수 << --------------------------------------------------------
  -- 4.1  to_char(날짜, '형태')  ==> 날짜를 '형태' 모양으로 문자형태로 변환시켜주는 것이다.
  --      to_char(숫자, '형태')  ==> 숫자를 '형태' 모양으로 문자형태로 변환시켜주는 것이다.
  --- 날짜를 문자형태로 변환하기 ---
     select to_char(sysdate, 'yyyy') AS 년도
          , to_char(sysdate, 'mm')   AS 월
          , to_char(sysdate, 'dd')   AS 일
          , to_char(sysdate, 'hh24') AS "24시간"
          , to_char(sysdate, 'am hh') AS "12시간"
          , to_char(sysdate, 'pm hh') AS "12시간"
          , to_char(sysdate, 'mi')   AS 분
          , to_char(sysdate, 'ss')   AS 초
          , to_char(sysdate, 'q')    AS 분기       -- 1월~3월 => 1,   4월~6월 => 2,   7월~9월 => 3,    10월~12월 => 4 
          , to_char(sysdate, 'day')  AS 요일명     -- 월요일(Windows) , Monday(Linux) 
          , to_char(sysdate, 'dy')   AS 줄인요일명  -- 월(Windows) , Mon(Linux)
     from dual;
     
     select to_char(sysdate, 'd')  -- sysdate 의 주의 일요일 부터(지금은 2024년 9월 8일) sysdate(지금은 2024년 9월 13일) 까지 며칠째 인지를 알려주는 것이다. 
                                   -- 1(일요일) 2(월요일) 3(화요일) 4(수요일) 5(목요일) 6(금요일) 7(토요일)   
     from dual;
  
  
    select case to_char(sysdate, 'd')
            when '1' then '일'
            when '2' then '월'
            when '3' then '화'
            when '4' then '수'
            when '5' then '목'
            when '6' then '금'
            when '7' then '토'
            end AS 오늘의요일명1
        
          , decode(to_char(sysdate, 'd'), '1', '일'
                                        , '2', '월'
                                        , '3', '화'
                                        , '4', '수'
                                        , '5', '목'
                                        , '6', '금'
                                        , '7', '토') AS 오늘의요일명2     
    from dual;
  
    select to_char(sysdate, 'dd')  -- sysdate 의 월 1일 부터(지금은 2024년 9월 1일) sysdate(지금은 2024년 9월 13일) 까지 며칠째 인지를 알려주는 것이다.
     from dual;
     
     select to_char(sysdate, 'ddd')  -- sysdate 의 년도 1월 1일 부터(지금은 2024년 1월 1일) sysdate(지금은 2024년 9월 13일) 까지 며칠째 인지를 알려주는 것이다.
     from dual;
     
     select to_char(sysdate, 'sssss')  -- sysdate 의 0시 0분 0초 부터 sysdate(현재가 2024년 9월 13일 오후 5시 17분 37초 이라면) 까지 
                                       -- 흘러간 초를 말한다.
     from dual;
     
     
    --->>> 숫자를 문자형태로 변환하기 <<<--- 
     select 1234567890
          , to_char(1234567890, '9,999,999,999')
          , to_char(1234567890, '$9,999,999,999')
          , to_char(1234567890, 'L9,999,999,999')  -- L 은 그 나라의 화폐기호 이다.
     from dual;
     --  1234567890      1,234,567,890    $1,234,567,890           ￦1,234,567,890
  
    select 100, 95.7
          , to_char(100, '999.0')
          , to_char(95.7, '999.0')
     from dual;
     -- 100     95.7    100.0     95.7
     
     
     select hakbun AS 학번
       , name AS 성명
       , kor AS 국어
       , eng AS 영어
       , math AS 수학
       , (kor + eng + math) AS 총점
       
       , to_char( round( (kor + eng + math)/3, 1),'999.0') AS 평균
     
       , case trunc( round( (kor + eng + math)/3, 1), -1 ) 
         when 100 then 'A'
         when  90 then 'A'
         when  80 then 'B'
         when  70 then 'C'
         when  60 then 'D'
         else 'F'
         end AS 학점
  from tbl_sungjuk;
  
  
  -- 4.2  to_date(문자, '형태')  ==> 문자를 '형태' 모양으로 날짜형태로 변환시켜주는 것이다.    
  select '2024-09-13' + 1
  from dual;
  -- ORA-01722: 수치가 부적합합니다
  -- 01722. 00000 -  "invalid number"
  
  
  select to_date('2024-09-13', 'yyyy-mm-dd') + 1
       , to_date('2024/09/13', 'yyyy/mm/dd') + 1
       , to_date('20240913', 'yyyymmdd') + 1
  from dual;
  --   24/09/14      24/09/14       24/09/14
  
  
  select to_date('2024-02-29', 'yyyy-mm-dd') + 1
  from dual;
  -- 24/03/01
  
  select to_date('2023-02-29', 'yyyy-mm-dd') + 1  -- 2023-02-29 은 달력에 없으므로 오류!!
  from dual;
  -- ORA-01839: 지정된 월에 대한 날짜가 부적합합니다
  
  -- 4.3  to_number(문자)  ==> 숫자모양을 가지는 문자를 숫자형태로 변환시켜주는 것이다.
   select '12345', to_number('12345'),
          '007'  , to_number('007')
   from dual;
     
     
   select to_number('50')+10
        , '50'+10   -- 자동형변환이 되어짐.
   from dual;
     

   select to_number('홍길동')
   from dual;
   -- ORA-01722: 수치가 부적합합니다
   -- ORA-01722: invalid number
  
  
  ----------------------------------------->> 5. 기타 함수 <<------------------------------------------------------
  
  --5.1 case when then else end ==> !!!암기 !!!
  
  select case 5-2
         when 4 then '5-2 = 3'
         when 1 then '5-2 = 1'
         when 3 then '5-2 = 3'
         else '나는 수학을 몰라요😭'
         end 결과
  from dual;
  
  
  select case 
         when 4 > 5 then '4는 5보다 큽니다.'
         when 5 > 7 then '5는 7보다 큽니다.'
         when 3 > 2 then '3은 2보다 큽니다.'
         else '나는 수학을 몰라요😭'
         end 결과
  from dual;
  
  --5.2 decode  ==> !!!암기 !!!🤚
  select case 5-2
         when 4 then '5-2 = 3'
         when 1 then '5-2 = 1'
         when 3 then '5-2 = 3'
         else '나는 수학을 몰라요😭'
         end 결과
        ,decode(5-2, 4, '5-2=4 입니다'
                    , 1, '5-2=1 입니다'
                    , 3, '5-2=3 입니다'
                        , '나는 수학을 몰라요😭') 결과2
  from dual;
  
  
  
  -- 5.3  greatest , least 
 select greatest(10, 90, 100, 80)  -- 나열되어진것들 중에 제일큰값을 알려주는 것
      , least(10, 90, 100, 80)     -- 나열되어진것들 중에 제일작은값을 알려주는 것
 from dual;
 -- 100    10
 
 
 select greatest('김유신','허준','고수','엄정화')  -- 나열되어진것들 중에 제일큰값을 알려주는 것
      , least('김유신','허준','고수','엄정화')     -- 나열되어진것들 중에 제일작은값을 알려주는 것
 from dual;
 -- 허준    고수
 
 
 -- 5.4  rank ==> 등수(석차)구하기  ,  dense_rank  ==>  서열구하기
 
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist007','칠석규',98,99,99);
  insert into tbl_sungjuk(hakbun, name, kor, eng, math) values('sist008','팔석규',100,96,100);
  commit;
  rollback;
     select hakbun 학번
            ,name 이름
            ,kor 국어
            ,eng 영어
            ,math 수학
            ,kor + eng + math 총점
            ,round((kor+eng+math)/3 , 1) 평균
            ,case trunc(round((kor+eng+math)/3 , 1), -1)
                when 100 then 'A'
                when  90 then 'A'
                when  80 then 'B'
                when  70 then 'C'
                when  60 then 'D'
                else 'F'
                end 학점1
            ,decode (trunc(round((kor+eng+math)/3 , 1), -1), 100, 'A'
                                                        , 90, 'A'
                                                        , 80, 'B'
                                                        , 70, 'C'
                                                        , 60, 'D'
                                                            , 'F') 학점2
            ,rank() over(order by(kor + eng + math) desc) as 전체등수
            ,dense_rank() over(order by (kor + eng + math) desc) as 전체서열
            ,rank() over(order by kor desc) as 국어등수
            ,rank() over(order by eng desc) as 영어등수
            ,rank() over(order by math desc) as 수학등수 -- 맨 마지막 컬럼기준으로 정렬되어져 나온다.
      from tbl_sungjuk;
      
      
      select hakbun 학번
            ,name 이름
            ,kor 국어
            ,eng 영어
            ,math 수학
            ,kor + eng + math 총점
            ,round((kor+eng+math)/3 , 1) 평균
            ,case trunc(round((kor+eng+math)/3 , 1), -1)
                when 100 then 'A'
                when  90 then 'A'
                when  80 then 'B'
                when  70 then 'C'
                when  60 then 'D'
                else 'F'
                end 학점1
            ,decode (trunc(round((kor+eng+math)/3 , 1), -1), 100, 'A'
                                                        , 90, 'A'
                                                        , 80, 'B'
                                                        , 70, 'C'
                                                        , 60, 'D'
                                                            , 'F') 학점2
            ,rank() over(order by(kor + eng + math) desc) as 전체등수
            ,dense_rank() over(order by (kor + eng + math) desc) as 전체서열
            ,rank() over(order by kor desc) as 국어등수
            ,rank() over(order by eng desc) as 영어등수
            ,rank() over(order by math desc) as 수학등수 -- 맨 마지막 컬럼기준으로 정렬되어져 나온다.
      from tbl_sungjuk
      order by 총점 desc;
      
      
      select employee_id 사원번호
        ,first_name || ' ' || last_name as 사원명
        ,nvl(salary + (salary * commission_pct), salary) as 월급
        ,rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as 월급등수
        ,dense_rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as 월급서열
      from employees;
      
    --- employees 테이블에서 월급의 등수가 1등 부터 10등 까지인 사원들만
    --- 사원번호, 사원명, 월급, 월급등수 를 나타내세요..
       select employee_id 사원번호
        ,first_name || ' ' || last_name as 사원명
        ,nvl(salary + (salary * commission_pct), salary) as 월급
        ,rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as 월급등수
        ,dense_rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as 월급서열
      from employees
      where dense_rank() over(order by nvl(salary + (salary * commission_pct), salary) desc <= 10;
      -- 오류이다.!! 왜냐하면 rank() 함수와 dense_rank() 함수는 where 절에 막바로 쓸수가 없습니다.
      
      ---!!! 이럴때 다음에 나오는 inline view를 사용하여 구한다.  !!!---
      
      select employee_id 사원번호
            ,fullname 사원명
            ,monthsal 월급
            ,monthsal_rank 월급등수
            ,monthsal_order 월급서열
      from
      (
          select employee_id
            ,first_name || ' ' || last_name as fullname
            ,nvl(salary + (salary * commission_pct), salary) as monthsal
            ,rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as monthsal_rank
            ,dense_rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as monthsal_order
          from employees
      ) V
      where monthsal_rank <= 10;
      
      
      
      
      
      select employee_id 사원번호
        ,first_name || ' ' || last_name as 사원명
        ,department_id 부서번호
        ,nvl(salary + (salary * commission_pct), salary) as 월급
        ,rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as 월급등수
        ,dense_rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as 월급서열
        ,rank() over(partition by department_id order by nvl(salary + (salary * commission_pct), salary) desc) as "부서내 월급 등수"
        ,dense_rank() over(partition by department_id order by nvl(salary + (salary * commission_pct), salary) desc) as "부서내 월급 서열"
      from employees
      order by 부서번호, 월급 desc;
      
      
       --- [퀴즈] 각 부서번호별로 월급에 대한 등수가 1등인 사원들만 아래와 같이 나오도록 하세요.
 --------------------------------------------------------------------
  부서번호   사원번호   사원명                 월급   부서내등수   전체등수 
 --------------------------------------------------------------------
    10        200   Jennifer Whalen         4,400     1         61
    20        201   Michael Hartstein    13,000       1         12
    30        114   Den Raphaely        11,000       1         23
    40        203   Susan Mavris         6,500       1         54
    50        121   Adam Fripp             8,200       1         39
    60        103   Alexander Hunold     9,000       1         32
    70        204   Hermann Baer        10,000       1         29
    80        145   John Russell        19,600       1          2
    90        100   Steven King            24,000       1          1
   100        108   Nancy Greenberg        12,008       1         17
   110        205   Shelley Higgins        12,008       1         17
   null       178   Kimberely Grant         8,050       1         41
   
   select department_id 부서번호
        , employee_id 사원번호
        , fullname 사원명
        , to_char(monthsal, '$99,999') as 월급
        , "monthsal_rank in department" 부서내등수
        , monthsal_rank 전체등수
   from
   (
   select employee_id
        ,first_name || ' ' || last_name as fullname
        ,department_id
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
        ,rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as monthsal_rank
        ,dense_rank() over(order by nvl(salary + (salary * commission_pct), salary) desc) as monthsal_order
        ,rank() over(partition by department_id order by nvl(salary + (salary * commission_pct), salary) desc) as "monthsal_rank in department"
        ,dense_rank() over(partition by department_id order by nvl(salary + (salary * commission_pct), salary) desc) as "monthsal_order in department"
      from employees
   )V
   where "monthsal_rank in department" = 1
   order by 부서번호;
   
   --5.5 lag, lead 함수 ==>웹의 게시판 또는 상품보기 등등에서 특정글(특정상품)을 조회할 때 많이 사용한다.
   
   
   create table tbl_board                                                                                                                      
    (boardno       number                -- 글번호   -- number 는 22byte 를 차지하며, 38자리까지 표현 가능하다. 10의 -130승 ~ 10의 126승 까지 숫자를 저장할 수 있다. 
    ,subject       varchar2(4000)        -- 글제목   varchar2 의 최대크기는 4000 이다. 4000 보다 크면 오류!!!
    ,content       Nvarchar2(2000)       -- 글내용   Nvarchar2 의 최대크기는 2000 이다. 2000 보다 크면 오류!!!
--  ,content       clob -> clob 타입은 최대 2gb까지 저장가능하다.
    ,userid        varchar2(40)          -- 글쓴이의 ID
    ,registerday   date default sysdate  -- 작성일자   default sysdate 은 데이터 입력시 registerday 컬럼의 값을 생략하면 기본적으로 sysdate 가 입력된다는 말이다. 
    ,readcount     number(10)            -- 조회수
    );
    -- Table TBL_BOARD이(가) 생성되었습니다.
    
    desc tbl_board
    
    insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
    values(1, '안녕하세요', '글쓰기 연습입니다.', 'leess', sysdate, 0);
    
    insert into tbl_board
    values('반갑습니다.', 2, '모두 취업대박 나십시오.', 'eomjh', sysdate, 0);
    /*
        오류 보고 -
        SQL 오류: ORA-01722: 수치가 부적합합니다
    */
    insert into tbl_board(subject, boardno, content, userid, registerday, readcount)
    values('반갑습니다.', 2, '모두 취업대박 나십시오.', 'eomjh', sysdate, 0);
    
    insert into tbl_board(boardno, subject, content, userid, registerday, readcount) 
    values(3, '건강하세요', '로또 1등을 기원합니다', 'youks', default, 0);
    
    insert into tbl_board(boardno, subject, content, userid, readcount) 
    values(4, '기쁘고 감사함이 넘치는 좋은 하루되세요', '늘 행복하세요', 'leess', 0);
    
    insert into tbl_board(boardno, subject, content, userid, readcount) 
    values(5, '오늘도 좋은 하루되세요', '늘 감사합니다', 'hongkd', 0);
    
    commit;
    select *
    from tbl_board;
    
    /*
      ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
        이전글번호      이전글제목                           글번호    글제목                                글내용                 다음글번호    다음글제목
      ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
         NULL          NULL                                 5      오늘도 좋은 하루되세요                   늘 감사합니다              4        기쁘고 감사함이 넘치는 좋은 하루되세요 
         5              오늘도 좋은 하루되세요                  4      기쁘고 감사함이 넘치는 좋은 하루되세요       늘 행복하세요              3        건강하세요
         4              기쁘고 감사함이 넘치는 좋은 하루되세요     3      건강하세요                               로또 1등을 기원합니다       2        반갑습니다
         3              건강하세요                            2      반갑습니다                               모두 취업대박 나십시오      1        안녕하세요
         2              반갑습니다                            1      안녕하세요                               글쓰기 연습입니다          NULL      NULL 
      ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
      
      */
      --lag lead 함수
      
      select lag (boardno, 1) over(order by boardno desc) 이전글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lag (subject, 1) over(order by boardno desc) 이전글제목 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 subject 컬럼의 값을 가져온다.
            ,boardno 글번호 
            ,subject 글제목 
            ,content 글내용 
            ,lead (boardno, 1) over(order by boardno desc) 다음글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lead (subject, 1) over(order by boardno desc) 다음글제목
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 subject 컬럼의 값을 가져온다.
      from tbl_board;
      
      
      select lag (boardno, 2) over(order by boardno desc) 이전글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 2칸 올라간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lag (subject, 2) over(order by boardno desc) 이전글제목 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 2칸 올라간 행에서 subject 컬럼의 값을 가져온다.
            ,boardno 글번호 
            ,subject 글제목 
            ,content 글내용 
            ,lead (boardno, 2) over(order by boardno desc) 다음글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 2칸 내려간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lead (subject, 2) over(order by boardno desc) 다음글제목
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 2칸 내려간 행에서 subject 컬럼의 값을 가져온다.
      from tbl_board;
      
      
      --subject 컬럼의 값의 길이가 16보다 크면 subject 컬럼의 값 중 16글자만 보여주고 뒤에 ...을 붙여서 나타내세요.
       select lag (boardno) over(order by boardno desc) 이전글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lag (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by boardno desc) 이전글제목 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 subject 컬럼의 값을 가져온다.
            ,boardno 글번호 
            ,case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end as 글제목 
            ,content 글내용 
            ,lead (boardno) over(order by boardno desc) 다음글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lead (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by boardno desc) 다음글제목
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 subject 컬럼의 값을 가져온다.
      from tbl_board;
      
      
      --- 또는---
    select lag(boardno) over(order by boardno desc) 이전글번호
        , lag(subject) over(order by boardno desc)이전글제목
        , boardno 글번호
        , subject 글제목
        , content 글내용
        , lead(boardno) over(order by boardno desc) 다음글번호
        , lead(subject) over(order by boardno desc) 다음글제목
        
    from 
    (
        select boardno 
              ,case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end as subject
              ,content 
        from tbl_board
    )V;
  
  -- [퀴즈] !!!!아래와 같이 나오도록 하세요!!!! 자주쓰는 방식
      ----------------------------------------------------------------------------------------------------------------------------------------------------------------------
        이전글번호      이전글제목                           글번호    글제목                                글내용                 다음글번호    다음글제목
      ---------------------------------------------------------------------------------------------------------------------------------------------------------------------- 
         4              기쁘고 감사함이 넘치는 좋은 ...         3      건강하세요                               로또 1등을 기원합니다       2        반갑습니다
      ----------------------------------------------------------------------------------------------------------------------------------------------------------------------      
    -- 아래는 틀린 풀이이다. 😭기억해줘!!
    --     왜냐하면 tbl_board 테이블에서 boardno 컬럼의 값이 3 인 행만 메모리에 올렸기 때문에 
    --     1개행만 올린상태에서 이전행, 다음행이 없기 때문이다. *** --
      select lag (boardno) over(order by boardno desc) 이전글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lag (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by boardno desc) 이전글제목 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 subject 컬럼의 값을 가져온다.
            ,boardno 글번호 
            ,case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end as 글제목 
            ,content 글내용 
            ,lead (boardno) over(order by boardno desc) 다음글번호 
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 boardno 컬럼의 값을 가져온다.
            
            ,lead (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by boardno desc) 다음글제목
            -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 subject 컬럼의 값을 가져온다.
      from tbl_board
      where boardno = 3;
      
      
      -- 아래가 올바른 풀이이다 (inline view를 사용하는 것이다).
      select previous_no 이전글번호
            ,previous_subject 이전글제목
            ,boardno 글번호
            ,subject 글제목
            ,content 글내용
            ,next_no 다음글번호
            ,next_subject 다음글제목
      from
      (
          select lag (boardno) over(order by boardno desc) previous_no
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 boardno 컬럼의 값을 가져온다.
                
                ,lag (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by boardno desc) previous_subject 
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 subject 컬럼의 값을 가져온다.
                ,boardno 
                ,case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end as subject 
                ,content
                ,lead (boardno) over(order by boardno desc) next_no
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 boardno 컬럼의 값을 가져온다.
                
                ,lead (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by boardno desc) next_subject
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 subject 컬럼의 값을 가져온다.
          from tbl_board
      )V
      where V.boardno = 3;
  
  
  
  
  -------------------------------------- *** 아주아주 중요!!!****-----------------------------------------------------------
  -------------------------------------- *** 아주아주 중요!!!****-----------------------------------------------------------(ノ｀Д)ノ
  -- view(뷰) 란? 테이블은 아니지만 select 되어진 결과물을 마치 테이블 처럼 보는것(간주 하는 것)이다.
  -- view(뷰)의 종류는 inline view와 stored view가 있다.
  
  -- >>>*** inline view 예제 *** <<< ---
  select V.*
  from 
  (
  select employee_id
        ,first_name || ' ' || last_name as fullname
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
  from employees
  ) V;
  
  --inline view는 바로 위의 예제에 보이는 V인 것이다. 즉, select 구문을 괄호()를 쳐서 별칭(예: V)을 부여한 것을 말한다.
  
  select employee_id
        ,first_name || ' ' || last_name as fullname
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
  from employees
  where nvl(salary + (salary * commission_pct), salary) between 15000 and 20000;
  -- 또는
  select V.*
  from 
  (
  select employee_id
        ,first_name || ' ' || last_name as fullname
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
  from employees
  ) V
  where V.monthsal between 15000 and 20000;
  
  -- 또는 inline view 의 별칭인 V는 생략 하는하다. V. 생략가능
  select *
  from 
  (
  select employee_id
        ,first_name || ' ' || last_name as fullname
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
  from employees
  ) V
  where monthsal between 15000 and 20000;
  
  
  select V.employee_id. V.fullname, V.monthsal
  from 
  (
  select employee_id
        ,first_name || ' ' || last_name as fullname
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
  from employees
  ) V
  where monthsal between 15000 and 20000;
  
  select employee_id as 사원번호
        ,fullname as 사원명
        ,to_char(monthsal, '$99,999') as월급
  from 
  (
  select employee_id
        ,first_name || ' ' || last_name as fullname
        ,nvl(salary + (salary * commission_pct), salary) as monthsal
  from employees
  ) V
  where monthsal between 15000 and 20000;
  
  
  -----------------------------------------------------
  -- 사원번호    사원명     주민번호       성별      만나이
  -----------------------------------------------------
        /*
            올해년도 - 출생년도   ==> 올해생일이 현재일 보다 같거다 또는 이전일 경우
            올해년도 - 출생년도-1 ==> 올해생일이 현재일 보다 이후일 경우
        */
        
  select employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') 주민번호
        ,case 
            when substr(jubun, 7, 1) in ('1', '3') then '남'
            else '여'
        end 성별
        ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
              to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
         else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
         end 만나이
  from employees;
  
  
  --employees 테이블에서 연령대가 20인 여자와 40대인 남자만
  -- 사원번호    사원명     주민번호       성별      만나이 를 나타내세요
  
  select employee_id 사원번호, fullname 사원명, jubun 주민번호, gender 성별, age 만나이
  from
  (
        select employee_id
            ,first_name || ' ' || last_name fullname
            ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
            ,case 
                when substr(jubun, 7, 1) in ('1', '3') then '남'
                else '여'
            end gender
            ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                  to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
             else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
             end age
      from employees
  ) V
  where (trunc(age, -1) = 20 and gender = '여') or
        (trunc(age, -1) = 40 and gender = '남')
  order by 4, 5;
  
  
  
  -- stored view 는 복잡한 SQL(Structured Query Language == 정형화된 질의어)을 저장하여 select 문을 간단하게 사용하고자 할 때 쓰인다.
  
  -- >>>*** stored view 예제 *** <<< ---
  
  /*
      
      create or replace view 뷰명 --> 뷰명 으로 되어진 stored view 가 없으면 create(생성) 하고, 만약에 stored view 가 이미 존재한다라면 이전에 정의해둔 stored view 를 없애버리고 select 문장 으로 replace(변경)하라는 말이다.
      as
      select 문장;
      
  */
  
      create or replace view view_emp_age
      as
      select employee_id
            ,first_name || ' ' || last_name fullname
            ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
            ,case 
                when substr(jubun, 7, 1) in ('1', '3') then '남'
                else '여'
            end gender
            ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                  to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
             else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
            end age
      from employees;
      
      
      -- stored view 는 복잡한 sql 을 저장하여
      -- 그래서 inline view 는 1회성인 반면 stored view 는 ?
      
      --View VIEW_EMP_AGE이(가) 생성되었습니다.
      
      select employee_id 사원번호, fullname 사원명, jubun 주민번호, gender 성별, age 만나이
      from view_emp_age
      where (trunc (age, -1) = 30 and gender = '여') or
            (trunc (age, -1) = 20 and gender = '남')
      order by 4, 5;
      
      
      --USER이(가) "SYSTEM"입니다.

-- ** 현재 오라클 서버에 접속되어진 사용자(지금은 hr)가 만든(소유의) 테이블(Table) 목록을 조회해 본다.
select *
from tab;

/*

    —————————————
    TNAME	    TABTYPE
    —————————————
    COUNTRIES	TABLE
    DEPARTMENTS	TABLE
    EMPLOYEES	TABLE
    EMP_DETAILS_VIEW	VIEW (VIEW 는 테이블은 아니지만 SELECT 되어진 결과물을 마치 테이블인것처럼 보는것)
    JOBS	TABLE
    JOB_HISTORY	TABLE
    LOCATIONS	TABLE
    REGIONS	TABLE
    TBL_FILES	TABLE
    TBL_SUNGJUK	TABLE
    TBL_TEST	TABLE
    TBL_WATCH	TABLE
    VIEW_EMP_AGE	VIEW (VIEW 는 테이블은 아니지만 SELECT 되어진 결과물을 마치 테이블인것처럼 보는것)
    
    
    ——————————————————
    TNAME               TABTYPE
    ——————————————————
    COUNTRIES	        TABLE
    DEPARTMENTS	        TABLE
    EMPLOYEES	        TABLE
    EMP_DETAILS_VIEW	VIEW (VIEW 는 테이블은 아니지만 SELECT 되어진 결과물을 마치 테이블인것처럼 보는것)
    JOBS	            TABLE
    JOB_HISTORY	        TABLE
    LOCATIONS	        TABLE
    REGIONS	            TABLE
*/
    desc employees;
    desc view_emp_age;

EMP_DETAILS_VIEW
TBL_FILES
VIEW_EMP_AGE


---- *** Stored View (저장된 뷰)의 원본소스(select문) 알아보기 *** ----

select *
from user_views;

select text
from user_views
where view_name = 'VIEW_EMP_AGE';
/*
"select employee_id
            ,first_name || ' ' || last_name fullname
            ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
            ,case 
                when substr(jubun, 7, 1) in ('1', '3') then '남'
                else '여'
            end gender
            ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                  to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
             else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
            end age
      from employees"
*/


---- view_emp_age 이라는 stored view (저장된 뷰)를 수정하기

create or replace view view_emp_age
as
select  employee_id
            ,first_name || ' ' || last_name fullname
            ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
            ,department_id
            ,case 
                when substr(jubun, 7, 1) in ('1', '3') then '남'
                else '여'
            end gender
            ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                  to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
             else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
            end age
            , nvl(salary + (salary * commission_pct), salary) as monthsal
      from employees;

select text
from user_views
where view_name = 'VIEW_EMP_AGE';

/*
    "select  department_id
                ,employee_id
                ,first_name || ' ' || last_name fullname
                ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
                ,case 
                    when substr(jubun, 7, 1) in ('1', '3') then '남'
                    else '여'
                end gender
                ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                      to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                 else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
                end age
                , nvl(salary + (salary * commission_pct), salary) as monthsal
          from employees"
*/

select *
from view_emp_age;


-- employees 테이블에서 월급이 10000 이상인 20대 여자와 30대 남자만 
-- 사원번호 사원명 주민번호 부서번호 성별 만나이 월급을 나타내세요.

select employee_id 사원번호
        , fullname 사원명
        , jubun 주민번호
        , department_id 부서번호
        , gender 성별
        , age 만나이
        , monthsal 월급
from view_emp_age
where (monthsal >= 10000 and trunc(age, -1) = 20 and gender ='여') or
      (monthsal >= 10000 and trunc(age, -1) = 30 and gender = '남');
      
      
      /*
    --- [퀴즈] ---
    employees 테이블에서 모든 사원들에 대해
    사원번호, 사원명, 주민번호, 성별, 현재나이, 월급, 입사일자, 정년퇴직일, 정년까지근무개월수, 퇴직금 을 나타내세요.
    [inline view 를 사용하여 구하세요] 입사일 * 정년퇴직일 근무년수 * 퇴직금
    
    여기서 정년퇴직일이라 함은 
    해당 사원의 생월이 3월에서 8월에 태어난 사람은 
    해당사원의 만나이가 63세가 되는 년도의 8월말일(8월 31일)로 하고,
    해당사원의 생월이 9월에서 2월에 태어난 사람은 
    해당사원의 만나이가 63세가 되는 년도의 2월말일(2월 28일 또는 2월 29일)로 한다.
 */
     desc employees;
     
     select *
     from employees;
     
     ----------------이건 내방식--------------------------------------------
     
     select employee_id 사원번호
            ,fullname 사원명
            ,jubun 주민번호
            ,age 나이
            ,gender 성별
            ,monthsal 월급
            ,hire_date 입사일자
            ,retire 정년퇴직일
            ,trunc(months_between(retire, hire_date)) "퇴직까지 근무 개월"
            ,to_char((extract(year from retire) - extract(year from hire_date)) * monthsal, '$9,999,999') 퇴직금
     from
      (
            select employee_id
                ,first_name || ' ' || last_name fullname
                ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
                ,case 
                    when substr(jubun, 7, 1) in ('1', '3') then '남'
                    else '여'
                end gender
                ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                      to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                 else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
                 end age
                 , nvl(salary + (salary * commission_pct), salary) as monthsal
                 , hire_date
                 ,case
                    when to_number(substr(jubun,3,2)) between 3 and 8 then  last_day(to_date((extract(year from (sysdate + (63 - 
                    
                    case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                      to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                 else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
                 end
                 
                 )* 365.25)))||'0801','yyyymmdd'))
                 
                    else last_day(to_date((extract(year from (sysdate + (63 - 
                    
                    case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                      to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                 else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
                 end
                 
                 )* 365.25)))||'0201','yyyymmdd'))
                    end retire
          from employees
      ) V;
      
      ---------------------------------------------- 강사님의 방식을 참고(이중 inline view)----------------------------------
      select employee_id 사원번호
            ,fullname 사원명
            ,jubun 주민번호
            ,age 나이
            ,gender 성별
            ,monthsal 월급
            ,hire_date 입사일자
            ,retire_date 정년퇴직일
            ,trunc(months_between(retire_date, hire_date)) "퇴직까지 근무 개월"
            ,to_char((extract(year from retire_date) - extract(year from hire_date)) * monthsal, '$9,999,999') 퇴직금
      from 
      (
          select employee_id
                ,fullname
                ,jubun
                ,age
                ,gender
                ,monthsal
                ,hire_date
                ,fucking_korean_age
                ,case
                        when to_number(substr(jubun,3,2)) between 3 and 8 then  last_day(to_date((extract(year from (sysdate + (63 - fucking_korean_age)* 365.25)))||'0801','yyyymmdd'))
                        else last_day(to_date((extract(year from (sysdate + (63 - age)* 365.25)))||'0201','yyyymmdd'))
                        end retire_date
          from (
            select employee_id
                    ,first_name || ' ' || last_name fullname
                    ,substr(jubun, 1, 7) || lpad('*',length(jubun)-7,'*') jubun
                    ,case 
                        when substr(jubun, 7, 1) in ('1', '3') then '남'
                        else '여'
                    end gender
                    ,case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                              to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                         else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
                     end age
                     ,extract(year from sysdate) - to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) fucking_korean_age
                     ,nvl(salary + (salary * commission_pct), salary) as monthsal
                     ,hire_date
            from employees
          )V
      )T; --alias 는 생략 가능
     



      -----------------------------------------------------------9월19일 까지의 정리----------------------------------------------------------------------------------
      
      ----------->> 그룹함수(집계함수) <<------------------- 🤚🤚🤚🤚🤚중요합니다!!
      
      /*
        1. sum      --합계
        2. avg      --평균
        3. max      --최대값
        4. min      --최소값
        5. medial   --중앙값
        6. count    --select 되어져 나온 결과물의 행의 개수
        7. variance --분산
        8. stddev   --표준편차
      
      
        분산    : 분산의 제곱근이 표준편차  (평균에서 떨어진 정도)
        표준편차 : 표준편차의 제곱승이 분산  (평균과의 차액)
    
        <<연봉>>
        'A'부서
        1000   1200   1100   1300   1100   6200 
        
        select 1000 +  1200 +  1100 +  1300 + 1100 +  6200 
        from dual;  --> 11900
        
        select (1000 +  1200 +  1100 +  1300 + 1100 +  6200) / 6
        from dual;  -- 1983.333333333333333333333333333333333333
        
        'B'부서
        1800   2200   2100   1700   2100   2000 
        
        select 1800 +  2200 +  2100 +  1700 + 2100 +  2000 
        from dual;  --> 11900
           
        select (1800 +  2200 +  2100 +  1700 + 2100 +  2000) / 6 
        from dual;  --> 1983.333333333333333333333333333333333333 
        
        >>> 주식투자 <<<
        50  60  40  50  55  45  52  48   평균 50    편차가 적음   -- 안정투자
        10  90  20  80  30  70  90  10   평균 50    편차가 큼     -- 투기성투자(위험을 안고서 투자함) 
        
        분산과 표준편차는 어떤 의사결정시 도움이 되는 지표이다.
      
      */
      
      
      select sum(salary)
      from employees;
      --691416
      --그룹함수(집계함수)는 결과값이 1개행만 나온다.
      
      select substr(jubun, 7, 1)
      from employees;
      -- 단일행함수는 결과값이 메모리에 로드되어진 행의 개수 만큼 동일한 개수로 나온다.
      
      
      -----************!!!!! 중요중요중요중요중요중요중요 !!!!********--------------
      -- 그룹함수(집계함수) 에서는 null 이 있으면 무조건 null은 제외시킨 후 연산을 한다.
      
      
      select 20 + 57 + 178 + 43 + null + 109
      from dual;
      
      select 20 + 57 + 178 + 43 + 109
      from dual;
      
      select sum(commission_pct * salary)
      from employees;
      
      select sum(nvl(commission_pct, 0) * salary)
      from employees; -- 위에랑 같음
      
      select sum(salary * commission_pct)
      from employees
      where commission_pct is not null; -- 결과가 똑같다
      
      select salary
      from employees;
      
      select sum(salary), avg(salary), max(salary), min(salary), median(salary), variance(salary), stddev(salary)
      from employees;
      
      -- 10 20  15  16  22  13  17  19  300000
      -- 10 13  15  16  17`  20  22  300000  중앙값은 가운데 값
      
      
      desc employees;
      
      select employee_id
      from employees;
      
      select salary
      from employees;
      
      select commission_pct
      from employees;
      
      select department_id
      from employees;
      
      select count(employee_id)　as "모든사원수"
            ,count(department_id) as "부서번호가 null이 아닌 사원수"
            ,count(commission_pct) as "커미션을 받는 사원수"
      from employees;
      
      
      -- 테이블에 입력되어진 행의 개수(즉, 사원수)는 어떻게 구할까요?
      select count(employee_id), count(*)
      from employees;
      
      
      --- **** avg(평균)을 구하실 때는 주의를 요합니다. *** -----
      
      -- employees 테이블에서 기본급여(salary)의 평균치를 구해라
      select sum(salary),count(salary)
            ,sum(salary) / count(salary)
            ,avg(salary)
      from employees;
      -- employees 테이블에서 수당금액(salary * commission_pct)의 평균치를 구해라
      select sum(salary * commission_pct),count(salary * commission_pct)
            ,sum(salary * commission_pct) / count(salary * commission_pct)
            ,avg(salary * commission_pct)
      from employees;
      
      select avg(salary * commission_pct)
      from employees;   --> 이것은 수당금액을 받는 사원들(35명)만의 수당금액의 평균값을 구한다
      
      select salary * commission_pct
            , nvl(salary * commission_pct, 0))
      from employees;
      
      select count(salary * commission_pct)
                 , sum(nvl(salary * commission_pct, 0)), count(nvl(salary * commission_pct, 0))
                 , sum(nvl(salary * commission_pct, 0))/ count(nvl(salary * commission_pct, 0))
                 , avg(nvl(salary * commission_pct, 0))
      from employees;
      
      
      select avg(salary * commission_pct)
            ,avg(nvl(salary * commission_pct, 0))
      from employees;
      -- avg(salary * commission_pct) 은 수당금액을 받는 사원들(35명)만의 수당금액의 평균값이고,
      -- avg( nvl(salary * commission_pct, 0) ) 은 수당금액이 NULL 인 사원들은 수당금액을 0 으로 계산하여 모든사원들(107명)의 수당금액의 평균값이다. 
      
      
      
      -- ********그룹함수(집계함수)와 함께 사용되어지는 group by 절에 대해서 알아봅시다.*******--
      
      -- employees 테이블에서 부서번호별로 인원수를 나타내세요.
      /*
     -------------------
       부서번호   인원수
     -------------------  
        10          1
        20          2
        30          6
        40          1
        50         45
        60          5
        70         1
        80         34
        90          3
        100          6
        110          2
        null       1
   */
   select count(*)
    from employees
    where department_id = 30;
    
    select count(*)
    from employees
    where department_id = 50;
      
    select department_id 부서버노 , count(*) 이넌수
    from employees
    group by department_id      -- department_id 컬럼의 값이 같은것 끼리 그룹을 짓는다
    order by department_id asc;
    
    desc employees;
    
    select *
    from employees;
    
    --- emplyess 테이블에서 성별로 인원수를 나타내세요.
    
    
    select gender 성별, count(*) 이넌수
    from
    (
        select case when substr(jubun, 7, 1)in ('1', '3') then '남' else '여' end as gender
        from employees
    )V
    group by v.gender; -- gender 컬럼의 값이 같은 것 끼리 그룹을 짓는다.
    
    
    --- [퀴즈] employees 테이블에서 연령대별로 인원수를 나타내세요.
    /*
    ------------------
      연령대   인원수 
    -----------------
       10      16
       20      18
       30      21
       40      20
       50      17
       60      15
    */
    
    select age 연령대
        , count(*) 이넌수
    from
    (
        select trunc((sysdate-to_date(case when substr(jubun, 7, 1) in ('1', '2') then '19' || substr(jubun, 1, 6) else '20'|| substr(jubun, 1, 6) end, 'yyyymmdd'))/365.2422, -1) as age
        from employees
    )V
    group by age
    order by age;
    
    -- 선생님 방식
    --- [퀴즈] employees 테이블에서 연령대별로 인원수를 나타내세요. --- 
/*
   -------------------
     연령대    인원수
   -------------------
        10      16
        20      16
        30      23
        40      20
        50      17
        60      15      
*/

/*
   from employees
   group by 연령대
*/
/*
 SELECT AGE_LINE AS 연령대
      , COUNT(*) AS 인원수
 FROM
 (
   select trunc(만나이, -1) AS AGE_LINE
   from employees
 ) V
 GROUP BY AGE_LINE 
*/
 /*
    만나이 => 올해 생일이 현재일 보다 뒤에 있으면     현재년도 - 태어난년도 - 1
    만나이 => 올해 생일이 현재일과 같거나 앞에 있으면  현재년도 - 태어난년도
    
    올해 생일 => 올해년도 || 태어난월일
    올해 생일 => to_char(sysdate, 'yyyy') || substr(jubun, 3, 4)
               '20240601'
               to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4) , 'yyyymmdd')  ==> 24/06/01
               
    sysdate - 올해생일인날짜 < 0  ==> 현재년도 - 태어난년도 - 1
    else                        ==> 현재년도 - 태어난년도
    
    태어난년도  ==> 주민번호 7자리에서 1개 값이 1 또는 2 이라면 to_number('19'||substr(jubun,1,2))
                   else   to_number('20'||substr(jubun,1,2))                   
 */
 select CASE WHEN sysdate - to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4) , 'yyyymmdd') < 0 
             THEN extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end - 1
             ELSE extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end   
        END AS AGE
 from employees;
 
-------------------------------------------------------------- 

 SELECT AGE_LINE AS 연령대
      , COUNT(*) AS 인원수
 FROM
 (
   select trunc(CASE WHEN sysdate - to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4) , 'yyyymmdd') < 0 
                THEN extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end - 1
                ELSE extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end   
                END, -1) AS AGE_LINE
   from employees
 ) V
 GROUP BY AGE_LINE 
 ORDER BY AGE_LINE;
 
 
 -----------------------*** 1차 그룹, 2차 그룹 짓기 *** -----------------------------
 
   ---- employees 테이블에서 부서번호별, 성별 인원수를 나타내세요.. ---
  /*
      ------------------------
       부서번호    성별   인원수
      ------------------------
            10      여   1
            20      여   2
            30      남   4
            30      여   2
            40      여   1
            50      남   23
            50      여   22
            60      남   4
            60      여   1
            70      남   1
            80      남   19
            80      여   15
            90      남   2
            90      여   1
            100      남   3
            100      여   3
            110      여    2
          (null)   여   1
  */
  
  select department_id 부서번호
        ,gender 성별
        ,count(*) 인원수
  from 
  (
  select department_id
        , case when substr(jubun, 7, 1) in ('1', '3') then '남' else '여' end as gender
  from employees
  )V
  group by department_id, gender
  order by department_id;
  
   ---- [퀴즈] employees 테이블에서 연령대별, 성별, 인원수, 퍼센티지(%)를 나타내세요.. ---
   
   /*
        ---------------------------------------
         연령대    성별    인원수   퍼센티지(%)
        ---------------------------------------
          10      남      
          10      여
          20      남
          20      여
          30      남
          30      여
          ...    ...
   */
   
   select age 연령대
        , gender 성별
        , count(*) 인원수
        , round(count(*) /(select count(*) from employees) *100, 1) 퍼센티지
   from
   (
        select trunc(CASE WHEN sysdate - to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4) , 'yyyymmdd') < 0 
                    THEN extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end - 1
                    ELSE extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end   
                    END, -1) AS age
                    , case when substr(jubun, 7, 1)in ('1', '3') then '남' else '여' end as gender
       from employees
   ) V
   group by age, gender
   order by age;
   
   -- 또는
   -- ===>> with 절을 사용한 inline view 만들기 <<< === 이건 오라클만 존재한다.
   
    with
    V as
    (
        select trunc(CASE WHEN sysdate - to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4) , 'yyyymmdd') < 0 
                    THEN extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end - 1
                    ELSE extract(year from sysdate) - case when substr(jubun, 7, 1) in('1','2') then to_number('19'||substr(jubun,1,2)) else to_number('20'||substr(jubun,1,2)) end   
                    END, -1) AS age
                    , case when substr(jubun, 7, 1)in ('1', '3') then '남' else '여' end as gender
       from employees
    )
    
    select age 연령대
        , gender 성별
        , count(*) 인원수
        , round(count(*) /(select count(*) from employees) *100, 1) 퍼센티지
    from v
    group by age, gender
    order by age;
    
    
    ----- **** 요약값을 보여주는 rollup, cube, grouping sets, grouping 에 대해서 알아봅니다. **** ------
    
    
    
    select department_id 부서번호
        , count(*) 인원수
    from employees
    group by rollup(department_id);
    
    select department_id 부서번호
        , grouping (department_id) -- grouping(department_id) 은 결과값이 오로지 2개만 나온다.(0 아니면 1) 
                                   -- 0 또는 1 인데, 
                                   -- 0 이라함은 department_id 컬럼의 값으로 그룹을 지었다는 말이고,
                                   -- 1 이라함은 그룹을 안지었다는 말이다.
        , count(*) 인원수
    from employees
    group by rollup(department_id);
    
    select decode(grouping (department_id),0, nvl(to_char(department_id), '부서없음'), '전체') 부서번호
--      , grouping (department_id) 
        , count(*) 인원수
        , to_char(round(count(*) / (select count(*) from employees) * 100, 1), '990.0') as "퍼센티지(%)"
    from employees
    group by rollup(department_id);
    
    ---- employees 테이블에서 성별로 인원수 와 퍼센티지(%)를 나타내면서 전체인원수로 나타내세요. ----
    with
    V as
    (
        select case when substr(jubun, 7, 1) in ('1', '3') then '남' else '여' end gender
        from employees
    )
    
    select decode(grouping(gender), 0, gender, '전체')  성별
        , count(*) 인원수
        , to_char(round(count(*) / (select count(*)from employees) * 100, 1), '990.0') as "퍼센티지(%)"
    from v
    group by rollup(gender);
    
    
    ---- employees 테이블에서 부서번호별, 성별로 인원수 와 퍼센티지(%)를 나타내면서 전체인원수로 나타내세요. ----
    with
    V as
    (
        select department_id
            , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end gender
        from employees
    )
    
    select decode(grouping(department_id),0 , nvl(to_char(department_id), '부서없음'), '전체') 부서번호
        ,decode(grouping(gender), 0, gender, ' ') 성별
        , count(*) 인원수
        , to_char(round(count(*) / (select count(*) from employees) * 100,1),'990.0') as "퍼센티지(%)"
    from V
    group by rollup(department_id, gender);
    
      ----- >>>>> 요약값(rollup, cube, grouping sets) <<<<< ------
  /*
      1. rollup(a,b,c) 은 grouping sets( (a,b,c),(a,b),(a),() ) 와 같다.
    
         group by rollup(department_id, gender) 은
         group by grouping sets( (department_id, gender), (department_id), () ) 와 같다.
  
      2. cube(a,b,c) 은 grouping sets( (a,b,c),(a,b),(b,c),(a,c),(a),(b),(c),() ) 와 같다.
 
         group by cube(department_id, gender) 은
         group by grouping sets( (department_id, gender), (department_id), (gender), () ) 와 같다.
  */
  
   with
    V as
    (
        select department_id
            , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end gender
        from employees
    )
    
    select decode(grouping(department_id),0 , nvl(to_char(department_id), '부서없음'), '전체') 부서번호
        ,decode(grouping(gender), 0, gender, ' ') 성별
        , count(*) 인원수
        , to_char(round(count(*) / (select count(*) from employees) * 100,1),'990.0') as "퍼센티지(%)"
    from V
    group by cube(department_id, gender) -- 큐브는 전체 남녀에 대한 통계도 나타내줌 더 자세하다
    order by department_id, 부서번호, gender; -- order by 절 잘 보자
    
    
    with
    V as
    (
        select department_id
            , case when substr(jubun, 7, 1) in ('2', '4') then '여' else '남' end gender
        from employees
    )
    
    select decode(grouping(department_id),0 , nvl(to_char(department_id), '부서없음'), '전체') 부서번호
        ,decode(grouping(gender), 0, gender, ' ') 성별
        , count(*) 인원수
        , to_char(round(count(*) / (select count(*) from employees) * 100,1),'990.0') as "퍼센티지(%)"
    from V
    --group by cube(department_id, gender) -- 큐브는전체 남녀에 대한 통계도 나타내줌 더 자세하다
    group by grouping sets( (department_id, gender),(gender) ,() )
    order by department_id, 부서번호, gender; -- order by 절 잘 보자
    
    
    
    ----- **** group by 절에서 사용하는 listagg 함수 대해서 알아봅니다. **** ------
/* 
    listagg(보여줄컬럼, '구분자') within group(order by 보여줄컬럼명의정렬기준이되는컬럼명)
    ==> 그룹화 데이터를 하나의 컬럼에 가로로 나열하여 출력하는데 사용됨. 
*/
select department_id
    , listagg(first_name || ' ' || last_name, ', ') within group(order by nvl(salary + (salary * commission_pct), salary) desc) as "월급이 많은 순서대로 사원명 출력"
from employees
group by department_id;

select department_id
    , listagg(first_name || ' ' || last_name || ':' || nvl(salary + (salary * commission_pct), salary), ', ') within group(order by nvl(salary + (salary * commission_pct), salary) desc) as "월급이 많은 순서대로 사원명 출력"
from employees
group by department_id;


select department_id
    , listagg(first_name || ' ' || last_name || ':' || to_char(nvl(salary + (salary * commission_pct), salary),'$99,999'), ', ') within group(order by nvl(salary + (salary * commission_pct), salary) desc) as "월급이 많은 순서대로 사원명 출력"
from employees
group by department_id;








----- ===== ***   having 그룹함수조건절   *** ====== ------
   /*
       group by 절을 사용하여 그룹함수의 값을 나타내었을때
       그룹함수의 값이 특정 조건에 해당하는 것만 추출하고자 할 때는 where 절을 사용하는 것이 아니라
       having 그룹함수조건절 을 사용해야 한다.
   */ 
  
  --employees 테이블에서 사원이 10명 이상 근무하는 부서번호와 그 인원수를 나타내세요
  
  select department_id, count(*)
  from employees
  where count(*) >= 10 
  group by department_id; --오류
  
  select department_id 부서번호, count(*) 인원수
  from employees
  group by department_id
  having count(*) >= 10
  order by 2;
  
  --- employees 테이블에서 부서번호별로 벌어들이는 salary 의 합계가 50000 이상인 부서에 대해서만
  --- 부서번호, 기본급여의합계 를 나타내세요.
  
  select department_id 부서번호 , to_char(sum(salary),'$999,999') 기본급여의합계
  from employees
  group by department_id
  having sum(salary) >= 50000
  order by 2;
  
  desc employees;
  
  select salary * nvl(commission_pct, 1)
  from employees;
  --- [퀴즈1] employees 테이블에서 부서번호별로 벌어들이는 월급 합계가 40000 이상인 부서에 대해서만
  --- 부서번호, 월급의합계 를 나타내세요.
      select department_id 부서번호
            ,to_char(sum(salary * nvl(commission_pct, 1)),'$999,999') 월급의합계
      from employees
      group by department_id
      having sum(nvl(salary + salary *commission_pct, salary)) >= 40000
      order by 2;
      
      
  --- [퀴즈2] employees 테이블에서 부서번호별 월급의 합계와 함께 월급합계의 등수(석차)도 나타내세요. --- 
      select department_id 부서번호
            ,sum_salary 월급의합계
            ,rank() over(order by sum_salary desc) "월급합계의 등수"
      from
      (
          select department_id
                ,to_char(sum(nvl(salary + salary *commission_pct, salary)),'$999,999') sum_salary
          from employees
          group by department_id
      )
      order by 2 desc;
      
       select department_id 부서번호
            ,sum_salary 월급의합계
            ,rank() over(order by sum_salary desc) "월급합계의 등수"
      from
      (
          select department_id
                ,to_char(sum(nvl(salary + salary *commission_pct, salary)),'$999,999') sum_salary
          from employees
          group by department_id
      );
      
      
  --- [퀴즈3] employees 테이블에서 부서번호별 인원수와 월급의 평균과 함께 월급평균의 등수(석차)도 나타내세요. --- 
      select department_id 부서번호
            ,avg_salary 월급의평균
            ,rank() over(order by avg_salary desc) "월급평균의 등수"
      from
      (
          select department_id
                ,count(*)
                ,to_char(avg(nvl(salary + salary *commission_pct, salary)),'$999,999') avg_salary
          from employees
          group by department_id
      )
      order by 2 desc;
  
  --- [퀴즈4] employees 테이블에서 모든 사원들이 벌어들이는 월급의 합계를 100% 로 보았을때 
  ---        각 부서번호별로 벌어들이는 월급의 합계를 % 로 나타내어보자.
      select department_id 부서번호
            ,sum_salary 월급의합계
            ,round(sum_salary / (select sum(nvl(salary + salary *commission_pct, salary)) from employees) * 100, 1) "퍼센티지"
      from
      (
          select department_id
                ,sum(nvl(salary + salary *commission_pct, salary)) sum_salary
          from employees
          group by department_id
      )
      order by 2 desc;
  --- [퀴즈5] employees 테이블에서 모든 사원들이 벌어들이는 월급의 합계를 100% 로 보았을때 
  ---        각 부서번호별로 벌어들이는 월급합계의 퍼센티지가 5% 이상인 부서만 
  ---        부서번호 월급의합계 퍼센티지를 나타내어 보세요.
  
      select department_id 부서번호
            ,sum_salary 월급의합계
            ,percentage 퍼센티지
      from
      (
          select department_id
                ,sum(nvl(salary + salary *commission_pct, salary)) sum_salary
                ,round(sum(nvl(salary + salary *commission_pct, salary)) / (select sum(nvl(salary + salary *commission_pct, salary)) from employees) * 100, 1) percentage
          from employees
          group by department_id
      )
      where percentage >= 5
      order by 2 desc;
      
      
      select department_id
            ,sum(salary + salary * nvl(commission_pct, 1)) sum_salary
            ,round(sum(nvl(salary + salary *commission_pct, salary)) / (select sum(nvl(salary + salary *commission_pct, salary)) from employees) * 100, 1) percentage
      from employees
      group by department_id
      having round(sum(nvl(salary + salary *commission_pct, salary)) / (select sum(nvl(salary + salary *commission_pct, salary)) from employees) * 100, 1) >= 5
      order by 2 desc;
      
      
      --------------------선생님 풀이-------------------------------------------
     --- [퀴즈1] employees 테이블에서 부서번호별로 벌어들이는 월급 합계가 40000 이상인 부서에 대해서만
   --- 부서번호, 월급의합계 를 나타내세요.
   select department_id as 부서번호 ,
          to_char( sum ( nvl( salary + (salary * commission_pct) , salary) ), '999,999') as 월급의합계 
   from employees
   group by department_id 
   having sum( nvl( salary + (salary * commission_pct) , salary) ) >= 40000
   order by 2;
  
  
   --- [퀴즈2] employees 테이블에서 부서번호별 월급의 합계와 함께 월급합계의 등수(석차)도 나타내세요. --- 
   select department_id 부서번호, 
          to_char(sum(nvl(salary+salary*commission_pct, salary)), '999,999') 월급의합계,
          rank() over(order by sum(nvl(salary+salary*commission_pct, salary)) desc) 등수
   from employees
   group by department_id;
   
   
   --- [퀴즈3] employees 테이블에서 부서번호별 인원수와 월급의 평균과 함께 월급평균의 등수(석차)도 나타내세요. --- 
   select department_id as 부서번호, 
          count(*) as 인원수,
          to_char(avg(nvl(salary+salary*commission_pct, salary)), '$999,999') as 평균월급,
          rank() over(order by avg(nvl(salary+salary*commission_pct, salary)) desc) as 등수
   from employees
   group by department_id;
   
   
   --- [퀴즈4] employees 테이블에서 모든 사원들이 벌어들이는 월급의 합계를 100% 로 보았을때 
   ---        각 부서번호별로 벌어들이는 월급의 합계를 % 로 나타내세요.
   select decode( grouping(department_id), 0, nvl(to_char(department_id), '부서없음'), '전체') as 부서번호
        , to_char( round( sum(nvl(salary+salary*commission_pct , salary))/(select sum(nvl(salary+salary*commission_pct , salary)) from employees) *100,1 ), '990.0') as "부서번호별 월급의 합계 퍼센티지(%)"
   from employees
   group by rollup(department_id);
  
  
   --- [퀴즈5] employees 테이블에서 모든 사원들이 벌어들이는 월급의 합계를 100% 로 보았을때 
   ---        각 부서번호별로 벌어들이는 월급합계의 퍼센티지가 5% 이상인 부서만 
   ---        부서번호 월급의합계 퍼센티지를 나타내어 보세요.
  /* 
   select decode(grouping(department_id), 0, nvl( to_char(department_id), '부서없음') , '전체') as 부서번호
        , to_char( round(sum(nvl(salary+salary*commission_pct , salary))/(select sum(nvl(salary+salary*commission_pct , salary)) from employees) *100,1), '990.0') as "부서번호별 월급의 합계 퍼센티지(%)"
   from employees
   group by rollup(department_id)
   order by department_id, 1;
  */ 
   
   select department_id as 부서번호
        , round(sum(nvl(salary+salary*commission_pct , salary))/(select sum(nvl(salary+salary*commission_pct , salary)) from employees) *100,1) as "부서번호별 월급의 합계 퍼센티지(%)"
   from employees
   group by department_id
   having round(sum(nvl(salary+salary*commission_pct , salary))/(select sum(nvl(salary+salary*commission_pct , salary)) from employees) *100,1) >= 5  
   order by 2;
   
   
   
   ------- **** !!! 누적(누계)에 대해서 알아봅니다. !!! **** ---------
   /*
        sum(누적되어야할 컬럼명) over(order by 누적되어질 기준이 되는 컬럼명 asc[desc] )
        
        sum(누적되어야할 컬럼명) over(partition by 그룹화 되어질 컬럼명 
                                   order by 누적되어질 기준이 되는 컬럼명 asc[desc] )
   */ 
   
   
        create table tbl_panmae
        (panmaedate  date
        ,jepumname   varchar2(20)
        ,panmaesu    number
        ); 
        -- Table TBL_PANMAE이(가) 생성되었습니다.
     delete from tbl_panmae;
        
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-2), '새우깡', 10);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-2)+1, '새우깡', 15); 
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-2)+2, '감자깡', 20);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-2)+3, '새우깡', 10);
     
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-2)+3, '새우깡', 3);
     
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-1), '고구마깡', 7);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-1)+1, '새우깡', 8); 
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-1)+2, '감자깡', 10);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( add_months(sysdate,-1)+3, '감자깡', 5);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate - 4, '허니버터칩', 30);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate - 3, '고구마깡', 15);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate - 2, '고구마깡', 10);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate - 1, '허니버터칩', 20);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '새우깡', 10);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '새우깡', 10);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '감자깡', 5);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '허니버터칩', 15);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '고구마깡', 20);
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '감자깡', 10); 
    
     insert into tbl_panmae(panmaedate, jepumname, panmaesu)
     values( sysdate, '새우깡', 10);
    
     commit;     
     -- 커밋 완료.
     
     --- *** tbl_panmae 테이블에서 '새우깡'에 대한 일별판매량과 일별누적판매량을 나타내세요. *** ---
     
     select to_char(panmaedate, 'yyyy-mm-dd hh24:mi:ss') as panmaedate
            ,jepumname, panmaesu
     from tbl_panmae
     where jepumname = '새우깡';
     
     select *
     from tbl_panmae;
     
     
     select to_char(panmaedate, 'yyyy-mm-dd') 판매일자
            ,sum(panmaesu) as 일별판매량
            ,sum(sum(panmaesu)) over(order by to_char(panmaedate, 'yyyy-mm-dd') asc ) 일별누적판매량  
            ,to_char(round(sum(sum(panmaesu)) over(order by to_char(panmaedate, 'yyyy-mm-dd') asc )/ (select sum(panmaesu) from tbl_panmae where jepumname = '새우깡') *100, 1),'990.0')as "일별누적퍼센티지(%)"
     from tbl_panmae
     where jepumname = '새우깡'
     group by to_char(panmaedate, 'yyyy-mm-dd');
     
     --- *** tbl_panmae 테이블에서 모든 제품에 대한 일별판매량과 일별누적판매량을 나타내세요. *** ---
     
     select jepumname 제품명
            , to_char(panmaedate, 'yyyy-mm-dd') 판매일자
            , sum(panmaesu) 일별판매량
         -- , sum(누적되어야할 컬럼명) over(partition by 그룹화 되어질 컬럼명 
  --                               order by 누적되어질 기준이 되는 컬럼명 asc[desc] )
            , sum(sum(panmaesu) ) over(partition by jepumname 
                                   order by to_char(panmaedate, 'yyyy-mm-dd') asc ) 일별누적판매량
            ,to_char(round(sum(sum(panmaesu)) over(partition by jepumname order by to_char(panmaedate, 'yyyy-mm-dd') asc)/ (select sum(panmaesu) from tbl_panmae where jepumname = P.jepumname) *100, 1),'990.0')as "일별누적판매량퍼센티지(%)"
     from tbl_panmae P
     group by jepumname, to_char(panmaedate, 'yyyy-mm-dd'); -- 테이블 옆에 P를 붙임
     
     with
     V as
     (
        select jepumname
            , to_char(panmaedate, 'yyyy-mm-dd') panmaedate
            , sum(panmaesu) day_sum_panmaesu
            , sum(sum(panmaesu) ) over(partition by jepumname 
                                       order by to_char(panmaedate, 'yyyy-mm-dd') asc ) day_nugye_panmaesu
        from tbl_panmae
        group by jepumname, to_char(panmaedate, 'yyyy-mm-dd')
     )
     
     select jepumname as 제품명
            ,panmaedate 판매일자
            ,day_sum_panmaesu 일별판매량
            ,day_nugye_panmaesu 일별누적판매량
            ,to_char(round(day_nugye_panmaesu/ (select sum(panmaesu) from tbl_panmae where jepumname = v.jepumname) *100, 1),'990.0') as "일별누적판매량퍼센티지(%)"
     from v
     order by 1;
     
     
     --- [퀴즈] tbl_panmae 테이블에서 판매일자가 1개월전의 '01'일 (즉, 현재가 2024년 9월 23일 이므로  2024년 8월 1일 부터) 현재까지 판매된 
     --- '감자깡' 과 '새우깡'에 대해 일별판매량과 일별누적판매량 및 일별누적판매량 퍼센티지(%) 을 나타내세요. 
     
     select sysdate 현재시각
            ,to_char(sysdate, 'yyyy-mm-dd') 현재시각2
            ,to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') 현재시각3
            ,last_day(add_months(sysdate, -2))+1 한달전1일
            ,to_char(last_day(add_months(sysdate, -2))+1, 'yyyy-mm-dd') 현재시각3
     from dual;
     
     with
     V as
     (
        select jepumname
            , to_char(panmaedate, 'yyyy-mm-dd') panmaedate
            , sum(panmaesu) day_sum_panmaesu
            , sum(sum(panmaesu) ) over(partition by jepumname 
                                       order by to_char(panmaedate, 'yyyy-mm-dd') asc ) day_nugye_panmaesu
        from tbl_panmae
        where jepumname in ('감자깡', '새우깡') and (to_date(to_char(last_day(add_months(sysdate, -2))+1, 'yyyy-mm-dd'),'yyyy-mm-dd') <= panmaedate and sysdate < to_date(to_char(sysdate, 'yyyy-mm-dd'),'yyyy-mm-dd')+1) 
        group by jepumname, to_char(panmaedate, 'yyyy-mm-dd')
     )
     
     select jepumname as 제품명
            ,panmaedate 판매일자
            ,day_sum_panmaesu 일별판매량
            ,day_nugye_panmaesu 일별누적판매량
            ,to_char(round(day_nugye_panmaesu/ (select sum(panmaesu) from tbl_panmae where jepumname = v.jepumname and (to_date(to_char(last_day(add_months(sysdate, -2))+1, 'yyyy-mm-dd'),'yyyy-mm-dd') <= panmaedate and sysdate < to_date(to_char(sysdate, 'yyyy-mm-dd'),'yyyy-mm-dd')+1) ) *100, 1),'990.0') as "일별누적판매량퍼센티지(%)"
     from v
     order by 1;
     
      ------- ====== ***** 아래처럼 나오도록 하세요 ***** ====== -------
 
     -------------------------------------------------------
--     전체사원수    10대   20대   30대   40대  50대   60대
     -------------------------------------------------------  
--       107       16      18     21   20     17   15
     ------------------------------------------------------- 
      --case when sysdate - current_year_birthday < 0 then extract(year from sysdate) - to_number(age) -1
            --    else extract(year from sysdate) - to_number(age) end as age
     
     with
     V as
     (
         select trunc(extract(year from sysdate) - age - (case when sysdate -current_year_birthday < 0 then 1 else 0 end),-1) age_line
         from
         (
             select case when substr(jubun, 7, 1) in ('1','2') then '19' else '20' end || substr(jubun, 1, 2) age
                    ,to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4), 'yyyymmdd') as current_year_birthday
             from employees
         )
     )
    select count(age_line) as 전체사원수
        , sum(decode(age_line, 10, 1)) as "10대"
        , sum(decode(age_line, 20, 1)) as "20대"
        , sum(decode(age_line, 30, 1)) as "30대"
        , sum(decode(age_line, 40, 1)) as "40대"
        , sum(decode(age_line, 50, 1)) as "50대"
        , sum(decode(age_line, 60, 1)) as "60대"
    from V;
    
    with
     V as
     (
         select trunc(extract(year from sysdate) - age - (case when sysdate -current_year_birthday < 0 then 1 else 0 end),-1) age_line
         from
         (
             select case when substr(jubun, 7, 1) in ('1','2') then '19' else '20' end || substr(jubun, 1, 2) age
                    ,to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4), 'yyyymmdd') as current_year_birthday
             from employees
         )
     )
    select count(age_line) as 전체사원수
        , to_char(round(sum(decode(age_line, 10, 1))/count(age_line)*100,1),'990.0') as "10대"
        , to_char(round(sum(decode(age_line, 20, 1))/count(age_line)*100,1),'990.0') as "20대"
        , to_char(round(sum(decode(age_line, 30, 1))/count(age_line)*100,1),'990.0') as "30대"
        , to_char(round(sum(decode(age_line, 40, 1))/count(age_line)*100,1),'990.0') as "40대"
        , to_char(round(sum(decode(age_line, 50, 1))/count(age_line)*100,1),'990.0') as "50대"
        , to_char(round(sum(decode(age_line, 60, 1))/count(age_line)*100,1),'990.0') as "60대"
    from V;
    
    
    
           -------- ==== *** [퀴즈] 아래처럼 나오도록 하세요 *** ==== ---------
       
  select employee_id, first_name, job_id, salary 
  from employees;
 /* 
   --------------------------------------------------------------------------------------------------------------------------------------
     직종ID          남자기본급여평균    여자기본급여평균     기본급여평균    평균과남자평균차액                     평균과여자평균차액 
                                                                     (남자기본급여평균 - 기본급여평균)        (여자기본급여평균 - 기본급여평균)
   --------------------------------------------------------------------------------------------------------------------------------------
     ........           ....              ....             ....             ...                                 ...     
     FI_ACCOUNT         7900              7950             7920             -20                                  30 
     IT_PROG            5700              6000             5760             -60                                 240 
     ........           ....              ....             ....             ...                                 ...   
   --------------------------------------------------------------------------------------------------------------------------------------
   */ 
   with
   A as (
   select job_id
        , decode (gender, '남',average_salary) mansalary
        , decode (gender, '여',average_salary) womensalary
        , decode (gender, '남', people) man_count
        , decode (gender, '여', people) women_count
    from (
        select job_id
            , gender
            , round(sum(salary) / count(*)) average_salary
            --, round(sum(salary) / count(*)) women_salary
            ,count(*) people
        from(
            select job_id
                    ,case when substr(jubun, 7, 1) in ('1','3') then '남' else '여' end gender
                    ,salary
            from employees
        ) P
        group by job_id, gender
        )
    )
    select job_id
        , nvl(sum(mansalary),0) 남자기본급여평균
        , nvl(sum(womensalary),0) 여자기본급여평균
        , round((nvl(sum(mansalary),0) + nvl(sum(womensalary),0))/2) 기본급여평균
        , round((nvl(sum(mansalary),0) + nvl(sum(womensalary),0))/2) - nvl(sum(mansalary),0) 평균과남자평균차액
        , round((nvl(sum(mansalary),0) + nvl(sum(womensalary),0))/2) - nvl(sum(womensalary),0) 평균과여자평균차액
    from a
    group by job_id;
    
    
    -----------------밑에는 선생님코드-------------------------------------
    WITH
   V AS
   (
      select job_id 
           , trunc(AVG(case when substr(jubun, 7, 1) in('1','3') then salary end)) AS MALE_SALARY_AVG
           , trunc(AVG(case when substr(jubun, 7, 1) in('2','4') then salary end)) AS FEMALE_SALARY_AVG
           , trunc(AVG(salary)) AS SALARY_AVG
      from employees
      group by job_id
   )
   SELECT job_id AS 직종ID
        , NVL( to_char(MALE_SALARY_AVG, '99,999'), ' ') AS 남자기본급여평균
        , NVL( to_char(FEMALE_SALARY_AVG, '99,999'), ' ') AS 여자기본급여평균
        , to_char(SALARY_AVG, '99,999') AS 기본급여평균
        , NVL( to_char(MALE_SALARY_AVG - SALARY_AVG, '99,999'), ' ') AS 평균과남자평균차액
        , NVL( to_char(FEMALE_SALARY_AVG - SALARY_AVG, '99,999'), ' ') AS 평균과여자평균차액
   FROM V
   ORDER BY 1;
    
    -------------------------------------------------------------------------------------
    
    ------------ ====== ***** Sub Query (서브쿼리) ***** ====== --------------
    /*
       -- Sub Query (서브쿼리)란?
       select 문속에 또 다른 select 문이 포함되어져 있을 때 포함되어진 select 문을 Sub Query (서브쿼리)라고 부른다.
       
       
       select ...
       from .....  ==> Main Query(메인쿼리 == 외부쿼리)
       where ... in (select ... 
                     from .....) ==> Sub Query (서브쿼리 == 내부쿼리)
                     
       select ...
            , (select ... from .... ) ==> Sub Query (서브쿼리 == 내부쿼리)
       from .... ==> Main Query(메인쿼리 == 외부쿼리) 
       
   */
   
   /*
     employees 테이블에서
     기본급여가 제일 많은 사원과 기본급여가 제일적은 사원의 정보를 
     사원번호, 사원명, 기본급여로 나타내세요..
   */
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , salary 기본급여
   from employees
   where salary = (select max(salary) from employees) or
         salary = (select min(salary) from employees);
         
     /*
    [퀴즈]
    employees 테이블에서 부서번호가 60, 80번 부서에 근무하는 사원들중에
    월급이 50번 부서 직원들의 '평균월급' 보다 많은 사원들만 
    부서번호, 사원번호, 사원명, 월급을 나타내세요....
  */
  select department_id 부서번호,
         employee_id 사원번호,
         first_name || ' ' || last_name 사원명,
         nvl(salary + salary * commission_pct, salary) 월급
  from employees
  where department_id in(60, 80) and 
        nvl(salary + salary * commission_pct, salary) > (select avg(nvl(salary + salary * commission_pct, salary)) from employees where department_id =50)
  order by 1, 4 desc;
  
  select avg(nvl(salary + salary * commission_pct, salary)) from employees where department_id =50;
  
  select avg (case department_id when 50 then nvl(salary + salary * commission_pct, salary) end)
  from employees;
  -- 평균을 구하는 조금 다른 방식
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  create table tbl_authorbook
   (bookname       varchar2(100)
   ,authorname     varchar2(20)
   ,loyalty        number(5)
   );
   -- Table TBL_AUTHORBOOK이(가) 생성되었습니다.
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('자바프로그래밍','이순신',1000);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('로빈슨크루소','한석규',800);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('로빈슨크루소','이순신',500);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('조선왕조실록','엄정화',2500);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('그리스로마신화','유관순',1200);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('그리스로마신화','이혜리',1300);
   
   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('그리스로마신화','서강준',1700);

   insert into tbl_authorbook(bookname, authorname, loyalty)
   values('어린왕자','김유신',1800);
   
   commit;
   
   
   select * 
   from tbl_authorbook;
   
   
   ---  tbl_authorbook 테이블에서 공저(도서명은 동일하지만 작가명이 다른 도서)로 지어진 도서정보를 나타내세요... ---
   
   /*
       ---------------------------------
         도서명         작가명    로얄티
       ---------------------------------  
         로빈슨크루소    한석규        800
         로빈슨크루소    이순신        500
         그리스로마신화  유관순       1200
         그리스로마신화  이혜리       1300
         그리스로마신화  서강준       1700
       ---------------------------------  
   */
   
   select *
   from tbl_authorbook
   where bookname in('로빈슨크루소', '그리스로마신화');
   
   select *
   from tbl_authorbook
   where bookname in(select bookname
   from tbl_authorbook
   group by bookname
   having count (*) > 1);
   
   --tbl_authorbook 테이블에서 bookname 컬럼의 값이 동일한 것이 2개 이상 나오는 행의 bookname 컬럼값
   
   
   select bookname
   from tbl_authorbook
   group by bookname
   having count (*) > 1;
   
      ----- ===== **** Pairwise(쌍) Sub Query **** ===== -----
  /*
      employees 테이블에서
      부서번호별로 salary 가 최대인 사원과
      부서번호별로 salary 가 최소인 사원의 정보를
      부서번호, 사원번호, 사원명, 기본급여를 나타내세요.. 
  */
  
  -- 부서번호 30번에 근무하는 사원들중에 기본급여가 최대인 사원들만
  -- 부서번호, 사원번호, 사원명, 기본급여를 나타내세요?
  select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
  from employees
  where department_id = 30 and
        salary = (select max(salary)from employees); --틀림
        
  select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
  from employees
  where salary = (select max(salary)from employees where department_id = 30); -- 틀림
        
  select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
  from employees
  where department_id = 30 and
        salary = (select max(salary)from employees where department_id = 30); -- 이게 정답
        
  -- 부서번호 50번에 근무하는 사원들중에 기본급여가 최대인 사원들만
  -- 부서번호, 사원번호, 사원명, 기본급여를 나타내세요?
  
  
  select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
  from employees
  where department_id = 50 and
        salary = (select max(salary)from employees where department_id = 50); -- 이게 정답
        
  -- 부서번호 30번에 근무하는 사원들중에 기본급여가 최소인 사원들만
  -- 부서번호, 사원번호, 사원명, 기본급여를 나타내세요?
  
  
  select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
  from employees
  where department_id = 30 and
        salary = (select min(salary)from employees where department_id = 30); -- 이게 정답
        
        
        
   -- 부서번호 50번에 근무하는 사원들중에 기본급여가 최소인 사원들만
  -- 부서번호, 사원번호, 사원명, 기본급여를 나타내세요?
  
  
  select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
  from employees
  where department_id = 50 and
        salary = (select min(salary)from employees where department_id = 50); -- 이게 정답
        
    -- 부서번호별 기본급여의 최대값을 알아보고자 한다.
    
    select department_id, max(salary)
    from employees
    group by department_id;
    
    -- 부서번호별 기본급여의 최소값을 알아보고자 한다.
    
    select department_id, min(salary)
    from employees
    group by department_id;
    
    /*
      employees 테이블에서
      부서번호별로 salary 가 최대인 사원과
      부서번호별로 salary 가 최소인 사원의 정보를
      부서번호, 사원번호, 사원명, 기본급여를 나타내세요.. 
    */
    select department_id AS 부서번호
       , employee_id AS 사원번호
       , first_name || ' ' || last_name AS 사원명
       , salary AS 기본급여
    from employees
    where (nvl(department_id,-9999), salary) in (select nvl(department_id, -9999), max(salary)--<< 이게 pairwise, 괄호 꼭 필요
                                      from employees
                                      group by department_id)  
        or (nvl(department_id,-9999), salary) in (select nvl(department_id, -9999), min(salary)--<< 이게 pairwise
                                      from employees
                                      group by department_id)
    order by 1, 4 desc;
    
    /*
      employees 테이블에서
      부서번호별로 salary 가 최대인 사원과
      부서번호별로 salary 가 최소인 사원의 정보를
      부서번호, 사원번호, 사원명, 기본급여, 부서내등수, 전체등수 를 나타내세요.. 
    */
    
    with
    V as
    (
       select department_id
       , employee_id
       , first_name || ' ' || last_name AS name
       , salary
       , rank() over(order by salary desc) total_rank
       , rank() over(partition by department_id order by salary desc) rank_in_department
        from employees
    )
    
    select department_id AS 부서번호
       , employee_id AS 사원번호
       , name AS 사원명
       , salary AS 기본급여
       , rank_in_department 부서랭킹구
       , total_rank 랭킹구
    from V
    where (nvl(department_id,-9999), salary) in (select nvl(department_id, -9999), max(salary)--<< 이게 pairwise, 괄호 꼭 필요
                                      from V
                                      group by department_id)  
        or (nvl(department_id,-9999), salary) in (select nvl(department_id, -9999), min(salary)--<< 이게 pairwise
                                      from V
                                      group by department_id)
    order by 1, 4 desc;
    
    --------- ===== **** 상관서브쿼리(== 서브상관쿼리) ****  ===== ---------    
   /*
      상관서브쿼리 이라함은 Main Query(== 외부쿼리)에서 사용된 테이블(뷰)에 존재하는 컬럼이
      Sub Query(== 내부쿼리)의 조건절(where절, having절)에 사용되어질때를 
      상관서브쿼리(== 서브상관쿼리)라고 부른다.
   */ 
   
   -- employees 테이블에서 기본급여에 대해 전체등수 및 부서내등수를 구하세요.
   -- 첫번째 방법은 rank() 함수를 사용하여 구해본다.
   
   select department_id AS 부서번호
        , employee_id AS 사원번호 
        , salary AS 기본급여
        , rank() over(order by salary desc) AS 전체등수 
        , rank() over(partition by department_id order by salary desc) AS 부서내등수 
   from employees
   order by 1, 3 desc;
   
   desc employees;
   -- employees 테이블에서 기본급여에 대해 전체등수 및 부서내등수를 구하세요.
   -- 두번째 방법은 count(*)를 이용하여 상관서브쿼리를 사용하여 구해본다
   
   select salary
   from employees;
   
   -- 자신의 기본급여가 12008 이라면 등수가 몇등?
   select salary
   from employees
   where salary > 12008;
   
   
   select count(salary)+1 as rank
   from employees
   where salary > 12008; -- 범용적으로 사용가능 
   
   select department_id AS 부서번호
        , employee_id AS 사원번호 
        , E.salary AS 기본급여
        , (select count(*) +1 from employees where salary > E.salary) AS 전체등수 
        , (select count(*) +1 from employees where department_id = E.department_id and salary > E.salary) AS 부서내등수 
   from employees E --별칭
   order by 1, 3 desc;
   ------------------------ gpt 문제 시작----------------------------
   --부서별로 가장 높은 급여를 받는 직원의 정보를 조회
   select max(salary) from employees group by department_id;
   
   select *
   from employees
   where (nvl(department_id, -9), salary) in (select nvl(department_id,-9), max(salary) from employees group by department_id)
   order by department_id;
   
   --각 부서에서 평균 급여보다 높은 급여를 받는 직원들의 정보를 조회
   
       select department_id,avg(salary)
       from employees
       group by department_id
       order by 1;
       
       select employee_id
            , first_name || ' ' || last_name name
            , email
            , phone_number
            , hire_date
            , job_id
            , commission_pct
            , manager_id
            , department_id
            , jubun
            , salary
            , case when department_id = E.department_id then round((select avg(salary) from employees where department_id = E.department_id)) end avg_salary
       from employees E
       where salary > (select avg(salary)
                              from employees
                              group by department_id
                              having E.department_id = department_id)  
       order by department_id;
       
       
       -----------------gpt 문제 끝----------------------------------------------
       
       
       --- ==== *** Sub Query 를 사용하여 테이블을 생성할 수 있습니다. *** ==== ---
       
   select department_id
        , employee_id
        , first_name || ' ' || last_name AS ENAME
        , nvl(salary + (salary * commission_pct), salary) AS MONTHSAL
        , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
        , jubun
   from employees
   where department_id in (30, 60);
   
   select * from tab;
   
   ---- *** employees 테이블을 가지고 데이터없이 employees 테이블의 구조만 동일한 tbl_employees_sub 이라는 테이블을 생성하겠습니다. *** ---
   select *
   from employees
   where 1=1;
   
   select *
   from employees
   where 1=2;
   
   create table tbl_employees_sub
   as
   select *
   from employees
   where 1=2;
   
   desc employees;
   desc tbl_employees_sub;
   
   ----- ****** !!!! 필수로 꼭 알아두시길 바랍니다. !!!! ****** ------
   --    =====  상관서브쿼리(== 서브상관쿼리)를 사용한 UPDATE 처리하기 ===== 
   /*
       회사에 입사하셔서 delete 또는 update 를 할 때 먼저 반드시 해당 테이블을 백업해두시고 하시길 바랍니다.
       실수하면 복구하기 위한 것이다.
   */
   
   create table tbl_employees_backup_20240924_0912
   as
   select *
   from employees;
   
   select *
   from tbl_employees_backup_20240924_0912;
   
   update employees set first_name = '순신', last_name = '이';
   
   commit;
   
   select *
   from employees;
   
   ---- ====상관서브쿼리(==서브상관커리)를 사용한 update 처리하기 ====----
   update employees E set first_name = (select first_name from tbl_employees_backup_20240924_0912 where employee_id = E.employee_id)
                        , last_name = (select last_name from tbl_employees_backup_20240924_0912 where employee_id = E.employee_id);
                        
                        
                        
    
   --- *** 데이터베이스 링크(datebase link) 만들기 ***---
   
   select first_name, last_name
   from employees
   where employee_id = 100;
   
   update employees set first_name = '규영', last_name = '연'
   where employee_id = 100;
   
   select *
   from employees;
   
   -- 1. DB클라이언트 컴퓨터의 탐색기에서  C:\OracleXE18C\product\18.0.0\dbhomeXE\network\admin 에 간다.
   -- 2. tnsnames.ora 파일을 메모장으로 연다.
   /*
    3.
      TEACHER =
      (DESCRIPTION =
        (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.0.219)(PORT = 1521))
        (CONNECT_DATA =
          (SERVER = DEDICATED)
          (SERVICE_NAME = XE)
        )
      )
     을 추가한다.
     HOST = 192.168.0.219 이 연결하고자 하는 원격지 오라클서버의 IP 주소이다.
     그런데 전제조건은 원격지 오라클서버(192.168.0.219)의 방화벽에서 포트번호 1521 을 허용으로 만들어주어야 한다.
     
     그리고 TEACHER 를 'Net Service Name 네트서비스네임(넷서비스명)' 이라고 부른다.
   */
   
   --4. 명령프롬프트를 열어서 원격지 오라클서버(192.168.0.219)에 연결이 가능한지 테스트를 한다. 
 --   C:\Users\user>tnsping TEACHER 5
 
 별칭 분석을 위해 TNSNAMES 어댑터 사용
        (DESCRIPTION = (ADDRESS = (PROTOCOL = TCP)(HOST = 192.168.0.219)(PORT = 1521)) (CONNECT_DATA = (SERVER = DEDICATED) (SERVICE_NAME = XE)))에 접속하려고 시도하는 중
        확인(0밀리초)
        확인(0밀리초)
        확인(0밀리초)
        확인(0밀리초)
        확인(0밀리초)
        
        
       -- 5.  데이터베이스 링크(database link) 만들기    
  
 -- drop database link teacher_oracle_server;
  
    create database link teacher_oracle_server
    connect to hr identified by gclass -- 이때 hr 과 암호 gclass 는 연결하고자 하는 원격지 오라클서버(192.168.0.220)의 계정명과 암호이다.  
    using 'TEACHER';  -- TEACHER 은 Net Service Name 네트서비스네임(넷서비스명) 이다. 
    -- Database link TEACHER_ORACLE_SERVER이(가) 생성되었습니다.
    
    create database link minjung_oracle_server
    connect to hr identified by gclass -- 이때 hr 과 암호 gclass 는 연결하고자 하는 원격지 오라클서버(192.168.0.189)의 계정명과 암호이다.  
    using 'MINJUNG';
    
    select *
    from employees@minjung_oracle_server --원격지 오라클 서버(192.168.0.189)
    order by employee_id asc;
    
    select *
    from employees@XE --로컬 서버
    order by employee_id asc;
    
    select *
    from employees
    order by employee_id asc;
    
    select *
    from tbl_employees_backup_20240924_0924@minjung_oracle_server --원격지 오라클 서버(192.168.0.189)
    order by employee_id asc;
    
    update employees set first_name = '정화', last_name = '엄';
    
    commit;
    
    select *
    from employees;
    
    ---- ====상관서브쿼리(==서브상관커리)를 사용한 update 처리하기 ====----
   update employees E set first_name = (select first_name from tbl_employees_backup_20240924_0924@minjung_oracle_server where employee_id = E.employee_id)
                        , last_name = (select last_name from tbl_employees_backup_20240924_0924@minjung_oracle_server where employee_id = E.employee_id);

    
    create table tbl_departments_backup
    as
    select *
    from departments;
    
    select *
    from tbl_departments_backup;
    
    select *
    from departments;
    
    desc departments;
    
    update departments set department_name = '몰라', manager_id = 200;
    update departments set location_id = 1000;
    commit;
    
    select *
    from tbl_departments_backup_20240924_0924@minjung_oracle_server --원격지 오라클 서버(192.168.0.189)
    order by department_id asc;
    
    update departments E set department_name = (select department_name from tbl_departments_backup_20240924_0924@minjung_oracle_server where department_id = E.department_id)
                        , manager_id = (select manager_id from tbl_departments_backup_20240924_0924@minjung_oracle_server where department_id = E.department_id);
                        
    update departments E set location_id = (select location_id from tbl_departments_backup_20240924_0924@minjung_oracle_server where department_id = E.department_id);
    
    -- ** 생성된 데이터베이스 링크(database link) 조회하기 ***---
    
    select *
    from user_db_links;
    
  --——————————————————————————————————————————————————————————————————
  --  DB_LINK	            USERNAME	PASSWORD	HOST	    CREATED	    HIDDEN	SHARD_INTERNAL	VALID
  --——————————————————————————————————————————————————————————————————
  --  MINJUNG_ORACLE_SERVER	HR		                MINJUNG	    24/09/24	NO	    NO	            YES
    
    --데이터베이스 링크(database link) 삭제하기 
    drop database link minjung_oracle_server;
    
    create database link teacher_oracle_server
    connect to hr identified by gclass -- 이때 hr 과 암호 gclass 는 연결하고자 하는 원격지 오라클서버(192.168.0.220)의 계정명과 암호이다.  
    using 'TEACHER';
    
    insert into tbl_imsi@teacher_oracle_server values('연규영');
    commit;
    select * from tbl_imsi@teacher_oracle_server;
    
    
    
    
    
    
    
    ----- *** Sub Query 절을 사용하여 데이터를 입력(insert)할 수 있다. *** -----
    create table tbl_employees_3060
    as
    select department_id
        , employee_id
        , first_name || ' ' || last_name AS ENAME
        , nvl(salary + (salary * commission_pct), salary) AS MONTHSAL
        , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
        , jubun
   from employees
   where department_id in (30, 60);
    
    select *
    from tbl_employees_3060;

    insert into tbl_employees_3060
    select department_id
        , employee_id
        , first_name || ' ' || last_name AS ENAME
        , nvl(salary + (salary * commission_pct), salary) AS MONTHSAL
        , case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end AS GENDER
        , jubun
   from employees
   where department_id in (20, 40);
   
   commit;
   rollback;
    
    
    ----- *** Sub Query 절을 사용하여 데이터를 수정(update)할 수 있다. *** -----
  
  -- tbl_employees_3060 테이블에서 부서번호 60번에 해당하는 사원들의 monthsal 값을 
  -- employees 테이블에 있는 부서번호 30번에 해당하는 사원들의 월급평균 값으로 변경하세요. 
  
  update tbl_employees_3060 set monthsal = (select avg(nvl(salary + salary * commission_pct, salary)) from employees where department_id = 30)
  where department_id = 60;
  
  
  ----- *** Sub Query 절을 사용하여 데이터를 삭제(delete)할 수 있다. *** -----
  
  --- *** tbl_employees_3060 테이블에서 monthsal 이 평균 monthsal 보다 작은 행들만 삭제하세요. *** ---
  
  delete from tbl_employees_3060
  where monthsal < ( tbl_employees_3060 테이블의 monthsal 컬럼의 평균);
  
  select avg(monthsal)
  from tbl_employees_3060;
  
  delete from tbl_employees_3060
  where monthsal < ( select avg(monthsal)
                     from tbl_employees_3060);
                     
                     
                     
                     
   -----------------------------------------------------------------------------------------
           --  !!!! 중요    JOIN 은 면접에 가시면 무조건 물어봅니다.     중요 !!!! --
   -----------------------------------------------------------------------------------------
   ------- ====== **** JOIN **** ====== --------
   /*
       JOIN(조인)은 테이블(뷰)과 테이블(뷰)을 합치는 것을 말하는데 
       행(ROW) 과 행(ROW)을 합치는 것이 아니라, 컬럼(COLUMN) 과 컬럼(COLUMN)을 합치는 것을 말한다.
       위에서 말한 행(ROW) 과 행(ROW)을 합치는 것은 UNION 연산자를 사용하는 것이다.
   
       -- 면접질문 : INNER JOIN(내부조인) 과 OUTER JOIN(외부조인)의 차이점에 대해 말해보세요.
       -- 면접질문 : JOIN 과 UNION 의 차이점에 대해서 말해보세요.
       
       
       A = {1, 2, 3}    원소가 3개
       B = {a, b}       원소가 2개
       
       A ⊙ B = { (1,a), (1,b)
                 ,(2,a), (2,b)
                 ,(3,a), (3,b) }
                 
       데카르트곱(수학)  ==> 원소의 곱 :   3 * 2 = 6개(모든 경우의 수)
       --> 수학에서 말하는 데카르트곱을 데이터베이스에서는 Cartesian Product 라고 부른다.
       
       
       JOIN  =>  SQL 1992 CODE 방식  -->  테이블(뷰) 과 테이블(뷰) 사이에 콤마(,)를 찍어주는 것.  
                                         콤마(,)를 찍어주는 것을 제외한 나머지 문법은 데이터베이스 밴더(회사) 제품마다 조금씩 다르다.   
       
       JOIN  =>  SQL 1999 CODE 방식(ANSI) --> 테이블(뷰) 과 테이블(뷰) 사이에 JOIN 이라는 단어를 넣어주는 것.
                                             ANSI(표준화) SQL
   */ 
   
   select *
   from employees;
   
   select count(*)
   from employees;
   
   select *
   from departments;
   
   select count(*)
   from departments;
   
   select *
   from employees, departments; -- SQL 1992 CODE 방식, Cartesian Product
   
   select count(*)
   from employees, departments; -- SQL 1992 CODE 방식
   
   select 107 * 27
   from dual;
   
   select *
   from employees cross join departments; -- SQL 1999 ANSI 방식, Cartesian Product
   
   select count(*)
   from employees cross join departments; -- SQL 1999 ANSI 방식
   
   /*
      1. CROSS JOIN 은 프로야구를 예로 들면 10개팀이 있는데 
         각 1팀당 경기를 몇번해야 하는지 구할때 쓰인다. 1팀당 모든 팀과 경기를 펼쳐야 한다. 
         
      2. CROSS JOIN 은 그룹함수로 나온 1개의 행을 가지고 어떤 데이터 값을 얻으려고 할때 사용된다. 
    */
    
          -- [ CROSS JOIN 사용 예 ]
      /*
          사원번호    사원명    부서번호    기본급여    모든사원들의기본급여평균    기본급여평균과의차액    평균대비기본급여  
          이 나오도록 하세요..
      */ 
    
     /*  
       ----------------------------------------------        --------------------------
        사원번호    사원명    부서번호    기본급여                    모든사원들의기본급여평균
       ----------------------------------------------        ---------------------------
                    107개행                                             1개행
     */  
     
     select employee_id AS 사원번호
         , first_name || ' ' || last_name AS 사원명
         , department_id AS 부서번호
         , salary AS 기본급여
    from employees;
    
    select *
    from employees
    where salary is null;
    
    select avg(salary)
    from employees;
    
    select employee_id AS 사원번호
         , first_name || ' ' || last_name AS 사원명
         , department_id AS 부서번호
         , salary AS 기본급여
         , avg(salary) 
    from employees; -- 오류
    
    
    select employee_id 사원번호
         , fullname 사원명
         , department_id 부서번호
         , salary 기본급여
         , round(b.salary_avg) 기본급여평균
         , salary - round(b.salary_avg)기본급여평균과의차액   
         , trunc(salary/round(b.salary_avg), 1)평균대비기본급여
    from
    (
        select employee_id
             , first_name || ' ' || last_name AS fullname
             , department_id
             , salary
        from employees
    )A --107개행
    cross join
    (
        select avg(salary) salary_avg
        from employees
    )B ; -- 1개행
    
    ------ 또는 ------
    with
    A as
    (
        select employee_id
             , first_name || ' ' || last_name AS fullname
             , department_id
             , salary
        from employees
    )
    ,
    B as
    (
        select avg(salary) salary_avg
        from employees
    )--107개행
    
     select employee_id 사원번호
         , fullname 사원명
         , department_id 부서번호
         , salary 기본급여
         , round(b.salary_avg) 기본급여평균
         , salary - round(b.salary_avg)기본급여평균과의차액   
         , trunc(salary/round(b.salary_avg), 1)평균대비기본급여
    from a cross join b;
    
    ------또는 -----------
    with
    A as
    (
        select employee_id
             , first_name || ' ' || last_name AS fullname
             , department_id
             , salary
        from employees
    )
    ,
    B as
    (
        select avg(salary) salary_avg
        from employees
    )--107개행
    
     select employee_id 사원번호
         , fullname 사원명
         , department_id 부서번호
         , salary 기본급여
         , round(b.salary_avg) 기본급여평균
         , salary - round(b.salary_avg)기본급여평균과의차액   
         , trunc(salary/round(b.salary_avg), 1)평균대비기본급여
    from a, b;
    
    
    ---- **** EQUI JOIN (SQL 1992 CODE 방식) **** ----
  /*
     [EQUI JOIN 예]
     
     부서번호   부서명   사원번호   사원명   기본급여
     이 나오도록 하세요..
  */

  /*
      부서번호                        부서명          사원번호   사원명   기본급여
     --------                       -------        -------------------------
     departments.department_id      departments             employees 
     employees.department_id
  */
  
  select *
  from departments;
  
  select *
  from employees;
  
  select *
  from employees E , departments D -- sql 1992 code
  where E.department_id = D.department_id
  -- 이떄 where 절을 join 조건절 이라고 부른다.
  order by 1;
  
  select *
  from employees E , departments D -- sql 1992 code
  where E.department_id = D.department_id
  -- 이떄 where 절을 join 조건절 이라고 부른다.
  order by employee_id;
  
  select *
  from employees E , departments D -- sql 1992 code
  where E.department_id = D.department_id
  -- 이떄 where 절을 join 조건절 이라고 부른다.
  order by department_id;   --ORA-00918: 열의 정의가 애매합니다 ==> 두개의 테이블에 중복으로 있으니깐 오류!
  
  select *
  from employees E , departments D -- sql 1992 code
  where E.department_id = D.department_id
  -- 이떄 where 절을 join 조건절 이라고 부른다.
  order by department_id;   --ORA-00918: 열의 정의가 애매합니다 ==> 두개의 테이블에 중복으로 있으니깐 오류!
  
  select *
  from employees E , departments D -- sql 1992 code
  where E.department_id = D.department_id(+)
  order by E.department_id;
  /*
     조인 조건절에서 (+)가 안 붙은 테이블인 E(employees) 테이블의 모든 행을 출력해주고 나서 
     이어서 where E.department_id = D.department_id 조건에 만족하는 행들을 보여준다.
     그러므로 E.department_id 값이 null 인 킴밸리그랜트는 출력되어진다.
  */
  
  /*
     [참고]
     microsoft 사 제품인 MS-SQL 서버에서는 아래와 같이 한다.
     
     select *
     from employees E , departments D
     where E.department_id *= D.department_id;
  */
  
  select *
  from employees E , departments D -- sql 1992 code
  where E.department_id(+) = D.department_id
  order by E.department_id; -- 이것은 department_id 가 null인 킴밸리그랜트가 나온다
  
  /*
     조인 조건절에서 (+)가 안 붙은 테이블인 D(departments) 테이블의 모든 행을 출력해주고 나서 
     이어서 where E.department_id = D.department_id 조건에 만족하는 행들을 보여준다.
     그러므로 departments 의 부서번호가 120번 부터 270번 부서까지 출력되어진다.
  */
  
  
  
  
  ---- **** INNER JOIN(== 내부조인) (SQL 1999 CODE 방식, ANSI) **** ----
  /*
     [INNER JOIN 예]
     
     부서번호   부서명   사원번호   사원명   기본급여
     이 나오도록 하세요..
  */

  /*
      부서번호                        부서명          사원번호   사원명   기본급여
     --------                       -------        -------------------------
     departments.department_id      departments             employees 
     employees.department_id
  */
  
  select *
  from employees E inner join departments D -- sql 1999 ANSI 방식
  on E.department_id = D.department_id -- 이때 on 절을 join 조건절 이라고 부른다.
  order by E.department_id; -- 이것은 department_id 가 null인 킴밸리그랜트가 나온다
  
     ---- **** OUTER JOIN(== 외부조인) (SQL 1999 CODE 방식, ANSI) **** ----
  /*
     [OUTER JOIN 예]
     
     부서번호   부서명   사원번호   사원명   기본급여
     이 나오도록 하세요..
  */

  /*
      부서번호                        부서명          사원번호   사원명   기본급여
     --------                       -------        -------------------------
     departments.department_id      departments             employees 
     employees.department_id
  */
  
  select *
  from employees E left outer join departments D -- sql 1999 ANSI 방식
  on E.department_id = D.department_id -- 이때 on 절을 join 조건절 이라고 부른다.
  order by E.department_id; -- 이것은 department_id 가 null인 킴밸리그랜트가 나온다
  
  select *
  from employees E right outer join departments D -- sql 1999 ANSI 방식
  on E.department_id = D.department_id -- 이때 on 절을 join 조건절 이라고 부른다.
  order by d.department_id; 
  
  select *
  from employees E full outer join departments D -- sql 1999 ANSI 방식
  on E.department_id = D.department_id -- 이때 on 절을 join 조건절 이라고 부른다.
  order by d.department_id; 
  
  select *
  from employees E full outer join departments D -- sql 1999 ANSI 방식
  on E.department_id = D.department_id -- 이때 on 절을 join 조건절 이라고 부른다.
  order by d.department_id; 
  
  -------------------------------------------------정리-----------------------
  
  select *
  from employees E cross join departments D; -- 모든 경우의 수. Cartesian Product
  
  select *
  from employees E inner join departments D -- Inner 조인(내부조인)
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  select *
  from employees E join departments D -- Inner 조인(내부조인), inner 는 생략 가능하다
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  select *
  from employees E left outer join departments D -- outer 조인(외부)
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  select *
  from employees E left join departments D -- outer 조인(외부), outer 는 생략 가능하다
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  select *
  from employees E right outer join departments D -- outer 조인(외부)
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  select *
  from employees E right join departments D -- outer 조인(외부), outer 는 생략 가능하다
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  select *
  from employees E full join departments D -- outer 조인(외부),  outer 는 생략 가능하다
  on E.department_id = D.department_id; --조인조건절 꼭 필요
  
  /*
     부서번호   부서명   사원번호   사원명   기본급여
     이 나오도록 하세요..
  */
  department_id 부서번호
        ,departnebt_name 부서명
        ,employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,salary 기본급여
  ;
  
  select E.department_id 부서번호
        ,department_name 부서명
        ,employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,salary 기본급여
  from departments D join employees E
  on E.department_id = D.department_id; --킴벨리 제외
  
  
  select E.department_id 부서번호
        ,department_name 부서명
        ,employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,salary 기본급여
  from departments D right join employees E
  on E.department_id = D.department_id; --킴벨리 표시
  
  /*
     30번, 60번 부서에 근무하는 사원들만
     부서번호   부서명   사원번호   사원명   기본급여
     이 나오도록 하세요..
  */
  
  select E.department_id 부서번호
        ,department_name 부서명
        ,employee_id 사원번호
        ,first_name || ' ' || last_name 사원명
        ,salary 기본급여
  from departments D join employees E
  on E.department_id = D.department_id
  where E.department_id in (30, 60);
  
  
  -----또는 ------ << 이거 권장, 속도를 위해서 이렇게 함
  
  select V.department_id 부서번호
        ,D.department_name 부서명
        ,V.employee_id 사원번호
        ,V.full_name 사원명
        ,V.salary 기본급여
  from 
  (
    select department_id
        ,employee_id
        ,first_name || ' ' || last_name full_name
        ,salary
    from employees where department_id in (30, 60)
  ) V
  join 
  (
        select department_id
            ,department_name
        from departments where department_id in (30, 60)
  ) D
  on V.department_id = D.department_id;
  
  
  with
  V as
  (
    select department_id
        ,employee_id
        ,first_name || ' ' || last_name full_name
        ,salary
    from employees where department_id in (30, 60)
  ) 
  ,
  D as
  (
        select department_id
            ,department_name
        from departments where department_id in (30, 60)
  )
  select V.department_id 부서번호
        ,D.department_name 부서명
        ,V.employee_id 사원번호
        ,V.full_name 사원명
        ,V.salary 기본급여
  from V join D
  on D.department_id = V.department_id;
  
  --------------------------------------------------------------------------------
  
  select department_id as 부서번호
        ,count(*) as 인원수
  from employees
  group by department_id
  order by 1;
  /*
  
    -------------------------
    부서명            인원수
    -------------------------
    Accounting           2
    Administration       1
    Executive           3
    Finance               6
    Human Resources       1
    IT                   5
    Marketing           2
    Public Relations   1
    Purchasing           6
    Sales              34
    Shipping          45
    (null)              1
    
    */
    
    with
    E as
    (
        select department_id
               ,count(*) as cnt
        from employees
        group by department_id
    )
    ,
    D as
    (
        select department_id, department_name
        from departments
        where manager_id is not null
    )
    select nvl(department_name, 'There is no department'), cnt
    from E left join D
    on E.department_id = D.department_id
    order by 1;
    
    
    ---- **** JOIN 을 사용한 응용문제 **** ----
    /*
        아래와 같이 나오도록 하세요.
        
        -----------------------------------------------------------------------
         부서번호   사원번호   사원명   기본급여   부서평균기본급여    부서평균과의차액
        -----------------------------------------------------------------------
        10   200   Jennifer Whalen      4,400     4,400         0
        20   202   Pat Fay              6,000     9,500    -3,500
        20   201   Michael Hartstein    13,000    9,500     3,500
        30   119   Karen Colmenares     2,500     4,150    -1,650
        30   118   Guy Himuro           2,600     4,150    -1,550
        30   117   Sigal Tobias         2,800     4,150    -1,350
        30   116   Shelli Baida         2,900     4,150    -1,250
        30   115   Alexander Khoo       3,100     4,150    -1,050
        30   114   Den Raphaely         11,000    4,150    6,850
        40   203   Susan Mavris         6,500     6,500     0
        50   132   TJ Olson             2,100     3,475    -1,375
        50   128   Steven Markle        2,200     3,475    -1,275
        50   136   Hazel Philtanker     2,200     3,475    -1,275
        50   127   James Landry         2,400     3,475    -1,075

    */
    
    with
    a as
    (
        select nvl(department_id, '-9999') as department_id, round(avg(salary)) average_salary
        from employees
        group by department_id
    )
    select E.department_id 부서번호
        , employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , to_char(salary,'99,999') 기본급여
        , to_char(average_salary,'99,999') 부서평균기본급여
        , to_char(salary -average_salary,'99,999') 부서평균과의차액
    from a right join employees E
    on a.department_id = nvl(e.department_id,-9999)
    order by 1, e.salary; --기본 숫자를 기준으로 정렬
    
    /*
        부서번호가 10, 30, 50번 부서에 근무하는 사원들만  아래와 같이 나오도록 하세요.
            
        ----------------------------------------------------------------------------------------------------------
          부서번호   사원번호   사원명   기본급여   부서평균기본급여    부서평균과의차액   부서내기본급여등수   전체기본급여등수
        ----------------------------------------------------------------------------------------------------------    
    */
    
    with
    a as
    (
        select department_id, round(avg(salary)) average_salary
        from employees
        group by department_id
    )
    ,
    with
    A as
    (
--        select department_id
--            , employee_id
--            , salary
--            , first_name || ' ' || last_name fullname
--            , rank() over(order by salary desc) rank_in_all
--            , rank() over(partition by department_id order by salary desc) rank_in_department
        from employees
    );
    
    with
    a as
    (
        select department_id department_id, round(avg(salary)) average_salary
        from employees
        group by department_id
    )
    ,
    e as
    (
         select department_id
            , employee_id
            , salary
            , first_name || ' ' || last_name fullname
            , rank() over(order by salary desc) rank_in_all
            , rank() over(partition by department_id order by salary desc) rank_in_department
        from employees
    )
    select E.department_id 부서번호
        , employee_id 사원번호
        , fullname 사원명
        , to_char(salary,'99,999') 기본급여
        , to_char(average_salary,'99,999') 부서평균기본급여
        , to_char(salary -average_salary,'99,999') 부서평균과의차액
        , rank_in_department 부서내기본급여등수
        , rank_in_all 전체기본급여등수
    from a right join E
    on a.department_id = e.department_id
    where e.department_id in (10, 30, 50)
    order by 1, 7;
    
    desc departments;
    desc employees;
    
    ---- =========== *** NON-EQUI JOIN *** ============= ----
  /*
      조인조건절에 사용되는 컬럼의 값이 특정한 범위에 속할 때 사용하는 것이 NON-EQUI JOIN 이다. 
      NON-EQUI JOIN 에서는 조인조건절에 = 을 사용하는 것이 아니라 between A and B 를 사용하는 것이다. 
  */
  
    -- 소득세율 지표 관련 테이블을 생성한다. 
  create table tbl_taxindex
  (lowerincome   number       -- 연봉의 최저
  ,highincome    number       -- 연봉의 최대
  ,taxpercent    number(2,2)  -- 세율  -0.99 ~ 0.99 
  );
  -- Table TBL_TAXINDEX이(가) 생성되었습니다.
   
  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(1, 99999, 0.02);

  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(100000, 149999, 0.05);

  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(150000, 199999, 0.08);

  insert into tbl_taxindex(lowerincome,highincome,taxpercent)
  values(200000, 10000000000000000, 0.1);

  commit;
  
  select * 
  from tbl_taxindex;
  
  
  ------------------------------------------------------
   사원번호     사원명     연봉     세율      소득세액
  ------------------------------------------------------
    1001       홍길동    50000    0.02      50000 *  0.02
    1002       엄정화   170000    0.08     170000 *  0.08
    ....       ......  ......    .....     ............. 
    
    --1992방식 --
    
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , to_char (nvl(salary + commission_pct * salary, salary)*12, '999,999') 연봉
        , taxpercent 세율
        , to_char((nvl(salary + commission_pct * salary, salary)*12) * taxpercent,'99,999') 소득세
    from employees E, tbl_taxindex T
    where nvl(salary + commission_pct * salary, salary)*12 between lowerincome and highincome
    order by 3 desc;
    
    
    --1999방식 --
    
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , to_char (nvl(salary + commission_pct * salary, salary)*12, '999,999') 연봉
        , taxpercent 세율
        , to_char((nvl(salary + commission_pct * salary, salary)*12) * taxpercent,'99,999') 소득세
    from employees E join tbl_taxindex T
    on nvl(salary + commission_pct * salary, salary)*12 between lowerincome and highincome
    order by 3 desc;
    
    
    ------------------ ===== **** SELF JOIN(자기조인) **** ===== ------------------ 
   /*
       자기자신의 테이블(뷰)을 자기자신의 테이블(뷰)과 JOIN 시키는 것을 말한다.
       이때 반드시 테이블(뷰)에 대한 alias(별칭)를 달리 주어서 실행해야 한다.
   */
   
   select *
   from employees;
   
      --- 아래처럼 나오도록 하세요... ---
   -------------------------------------------------------------------------------------------------------
    사원번호              사원명             이메일     급여      직속상관번호             직속상관명
  employee_id   first_name || last_name    email     salary   employee_id      first_name || last_name
  -------------------------------------------------------------------------------------------------------
     100             Steven King           SKING     24000     null                 null 
     102             Lex De Haan           LDEHAAN   17000     100                  Steven King
     103             Alexander   Hunold       AHUNOLD   9000      102                  Lex De Haan
     104             Bruce Ernst           BERNST    6000      103                  Alexander Hunold
    
    
    --1992방식 --
    
    
    select E1.employee_id as 사원번호
        , E1.first_name || ' ' || E1.last_name 사원명
        , E1.email 이메일
        , E1.salary 급여
        , nvl(to_char(E2.Employee_id), ' ') 직속상관번호
        , E2.first_name || ' ' || E2.last_name 직속상관명
    from employees E1, employees E2
    where E1.manager_id = E2.employee_id(+) -- (+)가 없으면 사장님은 안나오셔요
    order by 1;
    --E1.employee_id as 사원번호
        --, E1.first_name || ' ' || E1.last_name 사원명
        --, E1.email 이메일
        --, E1.salary 급여
        --, E2.Employee_id 직속상관번호
       -- , E2.first_name || ' ' || E2.last_name 직속상관명
    
    --1999방식 --
    select E1.employee_id as 사원번호
        , E1.first_name || ' ' || E1.last_name 사원명
        , E1.email 이메일
        , E1.salary 급여
        , nvl(to_char(E2.Employee_id), ' ') 직속상관번호
        , E2.first_name || ' ' || E2.last_name 직속상관명
    from employees E1 left join employees E2
    on E1.manager_id = E2.employee_id -- (+)가 없으면 사장님은 안나오셔요
    order by 1;
    
    --- SELF JOIN(자기조인)을 사용하여 tbl_authorbook 테이블에서 공저(도서명은 동일하지만 작가명이 다른 도서)로 지어진 도서정보를 나타내세요... ---
   ---                                                                  =                !=                                       
   /*
       ---------------------------------
         도서명         작가명    로얄티
       ---------------------------------  
         로빈슨크루소    한석규         800
         로빈슨크루소    이순신         500
         그리스로마신화  유관순       1,200
         그리스로마신화  이혜리       1,300
         그리스로마신화  서강준       1,700
       ---------------------------------  
   */
    
    select *
    from tbl_authorbook;
    
    select distinct author1.*
    from tbl_authorbook author1 join tbl_authorbook author2
    on author1.bookname = author2.bookname and author1.authorname!= author2.authorname;
    /*
        distinct를 안하고 그냥 *만 하면 이렇게 나옴
        BOOKNAME	AUTHORNAME	LOYALTY	BOOKNAME_1	AUTHORNAME_1	LOYALTY_1
        _______________________________________________________________________
        로빈슨크루소	이순신	    500	    로빈슨크루소	한석규	        800
        로빈슨크루소	한석규	    800	    로빈슨크루소	이순신	        500
        그리스로마신화	이혜리	    1300	그리스로마신화	유관순	        1200
        그리스로마신화	서강준	    1700	그리스로마신화	유관순	        1200
        그리스로마신화	유관순	    1200	그리스로마신화	이혜리	        1300
        그리스로마신화	서강준	    1700	그리스로마신화	이혜리	        1300
        그리스로마신화	유관순	    1200	그리스로마신화	서강준	        1700
        그리스로마신화	이혜리	    1300	그리스로마신화	서강준	        1700
    */
    
    
   select department_id
   from employees;
   
   select employee_id, department_id
   from employees;
   
   select distinct department_id
   from employees;
   
   select distinct employee_id, department_id
   from employees;
   
   
       ----- ===== **** Multi Table JOIN (다중 테이블 조인) **** ===== -----
    
    --> 3개 이상의 테이블(뷰)을 가지고 조인 시켜주는 것이다.
    
   /*
       
      --------------------------------------------------------------------------------------------------------------------------
         대륙명        국가명                        부서주소                    부서번호    부서명       사원번호  사원명       기본급여
      --------------------------------------------------------------------------------------------------------------------------   
         Americas     United States of America     Seattle 2004 Charade Rd      90      Executive    100    Steven King   24000
   
   
         대륙명   ==>  regions.region_name                                    regions.region_id 
         국가명   ==>  countries.country_name                                 countries.region_id       countries.country_id
         부서주소  ==> locations.city || ' ' || locations.street_address      locations.country_id      locations.location_id
         부서명   ==> departments.department_name                             departments.location_id   departments.department_id 
         사원명   ==> employees.first_name || ' ' || employees.last_name      employees.department_id
   */
   
   select * from tab;
   select * from departments;
   select * from locations;
   select * from employees;
   select * from countries;
   select * from regions;
   
   select R.region_name AS 대륙명
        , C.country_name AS 국가명
        , L.state_province || ' ' || L.city || ' ' || L.street_address AS 부서주소
        , D.department_id AS 부서번호
        , D.department_name AS 부서명
        , E.employee_id AS 사원번호
        , E.first_name || ' ' || E.last_name AS 사원명
        , E.salary AS 기본급여 
   from
   regions R
   join countries C
   on R.region_id = C.region_id
   join locations L
   on C.country_id = L.country_id
   join departments D
   on L.location_id = D.location_id
   right join employees E
   on D.department_id = E.department_id;
   
   
   ---- 아래와 같이 나오도록 하세요 ----
   /*
      -------------------------------------------- 
       부서번호   부서명   부서장사원번호   부서장사원명
      --------------------------------------------
       ==============================   *********
              departments  D            employees  E
                      D.manager_id = E.employee_id
              
   */
   select *
   from departments;
   
   select D.department_id 부서번호
        , D.department_name 부서명
        , D.manager_id 부서장사원번호
        , E.first_name || ' ' || E.last_name 부서장사원명
   from departments D join employees E
   on d.manager_id = E.employee_id
   order by 1;
   
   
      ---- [과제] -----
   --  아래와 같이 나오도록 하세요...
    
   -- 사원수가 107명이 나오도록 하세요
   /*
        ----------------------------------------------------------------------------------------------------------------------------------------------------
         부서번호    부서명    부서주소    부서장성명    사원번호   사원명    성별    나이    연봉    연봉소득세액    부서내연봉평균차액    부서내연봉등수     전체연봉등수 
        ----------------------------------------------------------------------------------------------------------------------------------------------------
   */

   /*
       email : tjdudgkr0959@naver.com
       메일제목 : SQL JOIN 과제_강이훈
       첨부파일 : SQL JOIN 과제_강이훈.txt
   */
   with
   E as
   (
        select employee_id
            , first_name ||' '|| last_name fullname
            , manager_id
            , E.department_id
            , case when substr(jubun, 7, 1) in ('1','3') then '남' else '여' end gender    
            , case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                        to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                   else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
              end age
            , to_char(nvl(salary + salary * commission_pct, salary) * 12,'999,999') annual_salary    
            , to_char((nvl(salary + commission_pct * salary, salary)*12) * taxpercent,'99,999') income_tax_annual_salary    
            , to_char(round(nvl(salary + salary * commission_pct, salary)*12 - S.Average),'999,999') avg_money
            , rank() over(partition by E.department_id order by (nvl(salary + commission_pct * salary, salary)*12) desc) rank_in_department
            , rank() over(order by (nvl(salary + commission_pct * salary, salary)*12) desc) rank_in_all
        from employees E left join tbl_taxindex T
        on nvl(salary + commission_pct * salary, salary)*12 between lowerincome and highincome
        left join 
        (
            select nvl(department_id,-9999) department_id
                ,avg(nvl(salary + commission_pct * salary, salary)*12) Average
            from employees S
            group by department_id
        ) S
        on s.department_id = nvl(E.department_id,-9999)
   )
   ,
   D as
   (
        select A.department_id
        , A.department_name
        , A.manager_id
        , B.first_name || ' ' || B.last_name manager_name
        , location_id
       from departments A join employees B
       on A.manager_id = B.employee_id
   )
   select E.department_id 부서번호
        , D.department_name 부서명
        , L.state_province || ' ' || L.city || ' ' || L.street_address 부서주소
        , D.manager_name 부서장성명
        , E.employee_id 사원번호
        , E.fullname 사원명
        , E.gender 성별
        , E.age 나이
        , E.annual_salary 연봉
        , E.income_tax_annual_salary 연봉소득세액
        , avg_money 부서내연봉평균차액
        , E.rank_in_department 부서내연봉등수
        , E.rank_in_all 전체연봉등수
   from E left join D
   on D.department_id = E.department_id
   left join locations L
   on L.location_id = D.location_id
   order by 1;
   
   select to_char(round(nvl(salary + salary * commission_pct, salary) - (select avg(nvl(salary + salary * commission_pct, salary)) from employees where nvl(department_id,-9999) = nvl(E.department_id,-9999) group by department_id)),'9,999') avg_money  
    from employees E join tbl_taxindex T
    on nvl(salary + commission_pct * salary, salary)*12 between lowerincome and highincome;
    
    with
   E as
   (
        select employee_id
            , first_name ||' '|| last_name fullname
            , manager_id
            , department_id
            , case when substr(jubun, 7, 1) in ('1','3') then '남' else '여' end gender    
            , case when (sysdate-to_date(extract(year from sysdate) || substr(jubun, 3,4),'yyyymmdd')) >= 0 then
                        to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2))
                   else to_number(extract(year from sysdate))-to_number(case when substr(jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(jubun, 1, 2)) -1
              end age
            , to_char(nvl(salary + salary * commission_pct, salary) * 12,'999,999') annual_salary    
            , to_char((nvl(salary + commission_pct * salary, salary)*12) * taxpercent,'99,999') income_tax_annual_salary    
            , to_char(round(nvl(salary + salary * commission_pct, salary)*12 - (select avg(nvl(salary + salary * commission_pct, salary)*12) from employees where nvl(department_id,-9999) = nvl(E.department_id,-9999) )),'999,999') avg_money
            , rank() over(partition by department_id order by (nvl(salary + commission_pct * salary, salary)*12) desc) rank_in_department
            , rank() over(order by (nvl(salary + commission_pct * salary, salary)*12) desc) rank_in_all
        from employees E join tbl_taxindex T
        on nvl(salary + commission_pct * salary, salary)*12 between lowerincome and highincome
   )
   ,
   D as
   (
        select A.department_id
        , A.department_name
        , A.manager_id
        , B.first_name || ' ' || B.last_name manager_name
        , location_id
       from departments A join employees B
       on A.manager_id = B.employee_id
   )
   select E.department_id 부서번호
        , D.department_name 부서명
        , L.state_province || ' ' || L.city || ' ' || L.street_address 부서주소
        , D.manager_name 부서장성명
        , E.employee_id 사원번호
        , E.fullname 사원명
        , E.gender 성별
        , E.age 나이
        , E.annual_salary 연봉
        , E.income_tax_annual_salary 연봉소득세액
        , avg_money 부서내연봉평균차액
        , E.rank_in_department 부서내연봉등수
        , E.rank_in_all 전체연봉등수
   from E left join D
   on D.department_id = E.department_id
   left join locations L
   on L.location_id = D.location_id
   order by 1;
   
   
     ------ ====== **** SET Operator(SET 연산자, 집합연산자) **** ======= ------
    /*
        -- 종류 --
        1. UNION 
        2. UNION ALL
        3. INTERSECT
        4. MINUS
        
        -- 면접시 JOIN 과 UNION 의 차이점에 대해서 말해 보세요~~~ !! --
        
    ==>  UNION 은 테이블(뷰)과 테이블(뷰)을 합쳐서 보여주는 것으로써,
         이것은 행(ROW)과 행(ROW)을 합친 결과를 보여주는 것이다.

    A = { a, x, b, e, g }
          -     -
    B = { c, d, a, b, y, k, m}    
                -  -    
    A ∪ B = {a, b, c, d, e, g, k, m, x, y}  ==> UNION      첫번째 컬럼기준으로 오름차순 정렬시켜줌         
                                             {a, b, c, d, e, g, k, m, x, y}

                                             ==> UNION ALL 
                                             {a, x, b, e, g, c, d, a, b, y, k, m} 

    A ∩ B = {a,b}  ==> INTERSECT
                       {a,b}

    A - B = {x,e,g} ==> MINUS 
                        {x,e,g}

    B - A = {c,d,y,k,m} ==> MINUS 
                           {c,d,y,k,m}
 */
    
     select *
     from tbl_panmae;
    
    
    -- tbl_panmae 테이블에서 2달전에 해당하는 월(현재가 2024년 9월이므로 2024년 7월)에 판매되어진 정보만 추출해서 tbl_panmae_202407 라는 테이블로 생성하세요. 
    
    create table tbl_panmae_202407
    as
    select *
    from tbl_panmae
    where to_char(panmaedate, 'yyyymm') = to_char(add_months(sysdate, -2),'yyyymm');
    -- Table TBL_PANMAE_202407이(가) 생성되었습니다.
    
    select *
    from tbl_panmae_202407;
    
    -- tbl_panmae 테이블에서 1달전에 해당하는 월(현재가 2024년 9월이므로 2024년 8월)에 판매되어진 정보만 추출해서 tbl_panmae_202408 라는 테이블로 생성하세요. 
    create table tbl_panmae_202408
    as
    select *
    from tbl_panmae 
    where to_char(panmaedate, 'yyyymm') = to_char(add_months(sysdate, -1), 'yyyymm');
    -- Table TBL_PANMAE_202408이(가) 생성되었습니다.
    
    select * 
    from tbl_panmae_202408;
    
    
    select * 
    from tbl_panmae;
    
    -- tbl_panmae 테이블에서 이번달에 해당하는 월(현재가 2024년 9월)에 판매되어진 정보만 남겨두고 나머지는 모두 삭제하세요.
    
    delete
    from tbl_panmae 
    where to_char(panmaedate, 'yyyymm') != to_char(sysdate, 'yyyymm');
    
    commit;
    rollback;
    
    ---- *** 최근 3개월간 판매되어진 정보를 가지고 제품별 판매량의 합계를 추출하세요 *** ----   
    
    -- 2달전 데이터
    select *
    from tbl_panmae_202407;
    
    -- 1달전 데이터
    select * 
    from tbl_panmae_202408;
    
    -- 이번달 데이터
    select * 
    from tbl_panmae;
    
    
    
    select *
    from tbl_panmae_202407 -- 2달전 데이터
    union
    select * 
    from tbl_panmae_202408 -- 1달전 데이터
    union
    select * 
    from tbl_panmae; -- 이번달 데이터
    
    
    select * 
    from tbl_panmae
    union
    select * 
    from tbl_panmae_202408
    union
    select *
    from tbl_panmae_202407; -- 항상 첫번째 컬럼 기준으로 오름차순 정렬
    -- UNION 을 하면 항상 첫번째 컬럼(지금은 panmaedate)을 기준으로 오름차순 정렬되어 나온다.
    -- 그래서 2024년 07월 데이터 부터 먼저 나온다.
    
    select jepumname, panmaedate, panmaesu 
    from tbl_panmae_202408  -- 1달전 데이터
    UNION 
    select jepumname, panmaedate, panmaesu 
    from tbl_panmae_202407  -- 2달전 데이터
    UNION 
    select jepumname, panmaedate, panmaesu 
    from tbl_panmae; -- 이번달 데이터
    -- UNION 을 하면 항상 첫번째 컬럼(지금은 jepumname)을 기준으로 오름차순 정렬되어 나온다.
    -- 그래서 감자깡 부터 먼저 나온다.
    
    select panmaedate, jepumname, panmaesu 
    from tbl_panmae_202408  -- 1달전 데이터
    UNION 
    select panmaedate AS 판매일자, jepumname AS 제품명, panmaesu AS 판매량
    from tbl_panmae_202407  -- 2달전 데이터
    UNION 
    select panmaedate, jepumname, panmaesu 
    from tbl_panmae; -- 이번달 데이터
    -- UNION 을 사용할때 컬럼에 대한 별칭(alias)은 맨 처음 select 구문에 쓴것만 되어진다. 
    
    select panmaedate AS 판매일자, jepumname AS 제품명, panmaesu AS 판매수
    from tbl_panmae_202408  -- 1달전 데이터
    UNION 
    select panmaedate AS 판매날날날짜, jepumname AS 제품명명명, panmaesu AS 판매량량량
    from tbl_panmae_202407  -- 2달전 데이터
    UNION 
    select panmaedate AS 널넝ㄹㄴㄹㅇ, jepumname AS SFSDFSFDSD, panmaesu AS 러ㅗㅇ리ㅏㄴ어라ㅓ
    from tbl_panmae; -- 이번달 데이터
    -- UNION 을 사용할때 컬럼에 대한 별칭(alias)은 맨 처음 select 구문에 쓴것만 되어진다.
    
    select panmaedate AS 판매일자, jepumname AS 제품명, panmaesu AS 판매수
    from tbl_panmae_202408  -- 1달전 데이터
    UNION 
    select panmaedate, jepumname, panmaesu
    from tbl_panmae_202407  -- 2달전 데이터
    UNION 
    select panmaedate, jepumname, panmaesu
    from tbl_panmae; -- 이번달 데이터
    -- UNION 을 사용할때 컬럼에 대한 별칭(alias)은 맨 처음 select 구문에 쓴것만 되어진다.
    
    
    select jepumname AS 제품명, panmaedate AS 판매일자, panmaesu AS 판매수
    from tbl_panmae_202408  -- 1달전 데이터
    UNION 
    select panmaedate, jepumname, panmaesu
    from tbl_panmae_202407  -- 2달전 데이터
    UNION 
    select panmaedate, jepumname, panmaesu
    from tbl_panmae; -- 이번달 데이터
    -- 오류!!!
    -- UNION 을 할때는 반드시 컬럼의 순서는 동일한 데이터타입을 가지도록 해야 한다.
    
    ---- *** 최근 3개월간 판매되어진 정보를 가지고 제품별 판매량의 합계를 추출하세요 *** ----

    select jepumname, panmaesu
    from tbl_panmae_202407  -- 2달전 데이터
    UNION 
    select jepumname, panmaesu
    from tbl_panmae_202408  -- 1달전 데이터 
    UNION 
    select jepumname, panmaesu
    from tbl_panmae; -- 이번달 데이터
    
    /*
        UNION ALL 을 사용하면 정렬 없이 그냥 순서대로 행을 붙여서 보여줄 뿐이다.
        또한 UNION 과 달리 UNION ALL 을 사용하면 중복된 행이 있더라도 제거하지 않고 그대로 보여준다.
        그러므로 올바른 데이터 집계를 하려면 UNION ALL 을 사용해야 한다.!!!!
    */
    
    -- UNION 을 사용하면 중복된 데이터 행은 제거하고 1번만 보여주므로 올바른 데이터 집계가 아니므로
    -- 위와 같이 하면 안된다.!!!!
    
    
     select jepumname, panmaesu
    from tbl_panmae_202407  -- 2달전 데이터
    UNION all
    select jepumname, panmaesu
    from tbl_panmae_202408  -- 1달전 데이터 
    UNION all
    select jepumname, panmaesu
    from tbl_panmae; -- 이번달 데이터
    
    select jepumname 제품명, sum(panmaesu) 판매량
    from
    (
        select jepumname, panmaesu
        from tbl_panmae_202407  -- 2달전 데이터
        UNION all
        select jepumname, panmaesu
        from tbl_panmae_202408  -- 1달전 데이터 
        UNION all
        select jepumname, panmaesu
        from tbl_panmae -- 이번달 데이터
    )
    group by jepumname
    order by 제품명;
    
    -- 또는
    with
    v as
    (
        select jepumname, panmaesu
        from tbl_panmae_202407  -- 2달전 데이터
        UNION all
        select jepumname, panmaesu
        from tbl_panmae_202408  -- 1달전 데이터 
        UNION all
        select jepumname, panmaesu
        from tbl_panmae -- 이번달 데이터
    )
    select jepumname 제품명, sum(panmaesu) 판매량
    from V
    group by jepumname
    order by 제품명;
    
    
    --- *** [퀴즈] 최근 3개월간 판매되어진 정보를 가지고
    ---           아래와 같이 제품명, 판매년월, 판매량의합계, 백분율(%)을 추출하세요 *** ---
    --------------------------------------------
    제품명   판매년월   판매량의합계     백분율(%)
    --------------------------------------------
    감자깡    2024-07         20      8.2
    감자깡    2024-08         15      6.2
    감자깡    2024-09         15      6.2
    감자깡                     50     20.6
    새우깡    2024-07         38     15.6
    새우깡    2024-08          8      3.3
    새우깡    2024-09         30     12.3
    새우깡                     76     31.3
    고구마깡    2024-08          7      2.9
    고구마깡    2024-09         45     18.5
    고구마깡                     52     21.4
    허니버터칩 2024-09         65     26.7
    허니버터칩                 65     26.7
    전체                        243    100.0
    ---------------------------------------------
    
    with V as
    (
        select jepumname, panmaedate, panmaesu
        from tbl_panmae_202407  -- 2달전 데이터
        UNION all
        select jepumname, panmaedate, panmaesu
        from tbl_panmae_202408  -- 1달전 데이터 
        UNION all
        select jepumname, panmaedate, panmaesu
        from tbl_panmae -- 이번달 데이터
    )
    select decode(grouping(jepumname), 0, jepumname, '전체') 제품명
        , decode(grouping(to_char(panmaedate, 'yyyy-mm')), 0, to_char(panmaedate, 'yyyy-mm'), ' ') 판매년월
        --, to_char(panmaedate, 'yyyy-mm') 
        , sum(panmaesu) 판매량의합계
        , to_char(round(sum(panmaesu) / (select sum(panmaesu) from v)*100),'999.0') as "백분율(%)"
    from v
    group by grouping sets((jepumname, to_char(panmaedate, 'yyyy-mm')),(jepumname), ());
    -- 또는
    with V as
    (
        select jepumname, panmaedate, panmaesu
        from tbl_panmae_202407  -- 2달전 데이터
        UNION all
        select jepumname, panmaedate, panmaesu
        from tbl_panmae_202408  -- 1달전 데이터 
        UNION all
        select jepumname, panmaedate, panmaesu
        from tbl_panmae -- 이번달 데이터
    )
    from v
    group by rollup();
    
    
    WITH
    V
    AS
    (
     select jepumname, panmaedate, panmaesu
     from tbl_panmae_202407  -- 2달전 데이터
     UNION ALL 
     select jepumname, panmaedate, panmaesu
     from tbl_panmae_202408  -- 1달전 데이터 
     UNION ALL 
     select jepumname, panmaedate, panmaesu
     from tbl_panmae -- 이번달 데이터
    )
    SELECT decode(grouping(jepumname), 0, jepumname, '전체') AS 제품명
      -- , grouping(jepumname)
         , decode(grouping( to_char(panmaedate, 'yyyy-mm') ), 0, to_char(panmaedate, 'yyyy-mm'), ' ') AS 판매년월
      -- , grouping( to_char(panmaedate, 'yyyy-mm') )
         , SUM(panmaesu) AS 판매량의합계
         , to_char( ROUND( SUM(panmaesu) / (select sum(panmaesu) from V)*100, 1 ), '990.0') AS "백분율(%)"
    FROM V
    GROUP BY grouping sets((jepumname , to_char(panmaedate, 'yyyy-mm')), (jepumname), () ); 
    -- 또는
    -- GROUP BY ROLLUP(jepumname , to_char(panmaedate, 'yyyy-mm'));
    
    
    
    ---- **** INTERSECT(교집합) **** ----
    insert into tbl_panmae_202407(panmaedate, jepumname, panmaesu)
    values( to_date('2024-09-01', 'yyyy-mm-dd'), '쵸코파이', 10);
  
    insert into tbl_panmae_202408(panmaedate, jepumname, panmaesu)
    values( to_date('2024-09-01', 'yyyy-mm-dd'), '쵸코파이', 10);
  
    insert into tbl_panmae(panmaedate, jepumname, panmaesu)
    values( to_date('2024-09-01', 'yyyy-mm-dd'), '쵸코파이', 10);
  
    commit;
    
    select *
    from tbl_panmae_202407
    intersect
    select *
    from tbl_panmae_202408
    intersect
    select *
    from tbl_panmae;
    
    delete tbl_panmae_202407
    where jepumname = '쵸코파이';
    
    delete tbl_panmae
    where jepumname = '쵸코파이';
    
    delete tbl_panmae
    where jepumname = '쵸코파이';
    
    commit;
    
    
    
    --- ====== **** MINUS(차집합) *** ====== ---
    
    create table tbl_employees_backup
    as
    select *
    from employees;
    
    select *
    from tbl_employees_backup;
    
    select *
    from employees
    where employee_id in (173, 185, 195);
    
    select *
    from tbl_employees_backup
    where employee_id in (173, 185, 195);
    
    -- *** 개발자가 실수로 employees 테이블에 있던 사원들을 삭제(delete)했다. 
    --     그런데 누구를 삭제했는지 모른다.!!!!
    --     백업받은 tbl_employees_backup 테이블을 이용하여 삭제된 사원들을 다시 복구하도록 하겠다. *** ---
    
    delete from employees
    where employee_id in (173, 185, 195);
    
    commit;
    
    select *
    from tbl_employees_backup
    minus
    select *
    from employees;
    -- tbl_employees_backup 테이블에는 존재하지만 employees 테이블에는 존재하지 않는 행을 찾는 것이다.
  /*
     173
     185
     195
  */
  
  -- 이제 employees 테이블을 복원하도록 한다.
    insert into employees
    select *
    from tbl_employees_backup
    where employee_id in (173, 185, 195);
    
    commit;
    
    select *
    from employees
    where employee_id in (173, 185, 195);
    --복구되어 나온다
    
    
    ----------- ====== **** Pseudo(의사, 유사, 모조) Column **** ====== -----------
  
    ------ Pseudo(의사) Column 은 rowid 와 rownum 이 있다. rownum이 특히 중요
      
    /*
        1. rowid
           rowid 는 오라클이 내부적으로 사용하기 위해 만든 id 값으로써 행에 대한 id값 인데
           오라클 전체내에서 고유한 값을 가진다.
    */
    
    
    
    /*
      2. rownum (!!! 게시판 등 웹에서 아주아주아주 많이많이많이 사용됩니다. !!!)
   */
    
    create table tbl_heowon
  (userid     varchar2(20),
   name       varchar2(20),
   address    varchar2(100)
  );
  -- Table TBL_HEOWON이(가) 생성되었습니다.
    
  insert into tbl_heowon(userid, name, address) values('leess','이순신','서울');
  insert into tbl_heowon(userid, name, address) values('eomjh','엄정화','인천');
  insert into tbl_heowon(userid, name, address) values('kangkc','강감찬','수원');
    
  insert into tbl_heowon(userid, name, address) values('leess','이순신','서울');
  insert into tbl_heowon(userid, name, address) values('eomjh','엄정화','인천');
  insert into tbl_heowon(userid, name, address) values('kangkc','강감찬','수원');
        
  insert into tbl_heowon(userid, name, address) values('leess','이순신','서울');
  insert into tbl_heowon(userid, name, address) values('eomjh','엄정화','인천');
  insert into tbl_heowon(userid, name, address) values('kangkc','강감찬','수원');
        
  commit;
    
  select *
  from tbl_heowon;
  
  delete from tbl_heowon
  where userid = 'leess';
  
  rollback;
  
  select rowid, userid, name, address
  from tbl_heowon
  where rowid between 'AAASDNAAHAAAAG2AAD' and 'AAASDNAAHAAAAG2AAI';
  
  delete from tbl_heowon
  where rowid between 'AAASDNAAHAAAAG2AAD' and 'AAASDNAAHAAAAG2AAI';
  
  commit;
  
  select *
  from tbl_board;
  
  select boardno as 글번호
        ,subject as 글제목
        ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') 작성일자
  from tbl_board;
  
  
  insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(6, '9월26일의 첫번째 글', '안녕하세요 1', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(7, '9월26일의 두번째 글', '안녕하세요 2', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(8, '9월26일의 세번째 글', '안녕하세요 3', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(9, '9월26일의 네번째 글', '안녕하세요 4', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(10, '9월26일의 다섯번째 글', '안녕하세요 5', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(11, '9월26일의 여섯번째 글', '안녕하세요 6', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(12, '9월26일의 일곱번째 글', '안녕하세요 7', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(13, '9월26일의 여덟번째 글', '안녕하세요 8', 'leess', default, 0);
   
   insert into tbl_board(boardno, subject, content, userid, registerday, readcount)
   values(14, '9월26일의 아홉번째 글', '안녕하세요 9', 'leess', default, 0);
   
   commit;
   -- 커밋 완료.
   
    select boardno as 글번호
        ,subject as 글제목
        ,userid as 글쓴이
        ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') 작성일자
    from tbl_board
    order by boardno desc;
    
    -------------------------------------------------------------------------
    번호  글번호   글제목                   글쓴이   작성일자
    -------------------------------------------------------------------------
     1    14	9월26일의 아홉번째 글	2024-09-26 10:15:16
     2    13	9월26일의 여덟번째 글	2024-09-26 10:15:12
     3    12	9월26일의 일곱번째 글	2024-09-26 10:15:06
     4    11	9월26일의 여섯번째 글	2024-09-26 10:15:02
     5    10	9월26일의 다섯번째 글	2024-09-26 10:14:56
     6    9	    9월26일의 네번째 글    	2024-09-26 10:14:50
     7    8	    9월26일의 세번째 글	    2024-09-26 10:14:45
     8    7	    9월26일의 두번째 글    	2024-09-26 10:14:40
     9    6	    9월26일의 첫번째 글	    2024-09-26 10:14:30
     10   5	    오늘도 좋은 하루되세요	2024-09-19 16:15:19
     11   4	    기쁘고 감사함이 넘치는 좋은 하루되세요	2024-09-19 16:14:07
     12   3	    건강하세요	           2024-09-19 16:12:20
     13   2	    반갑습니다.	      2024-09-19 16:11:07
     14   1	    안녕하세요	          2024-09-19 16:06:53
     
     
    select rownum --rownum(행번호)은 기본적으로 insert 되어진 순서대로 나온다.
        ,boardno as 글번호
        ,subject as 글제목
        ,userid as 글쓴이
        ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') 작성일자
    from tbl_board
    order by boardno desc;
    
    select rno
        , boardno
        , subject
        , userid
        , registed_day
    from
    (
        select boardno
            ,subject
            ,userid
            ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') registed_day
        from tbl_board
        order by boardno desc
    );
    
    /*
   한 페이지당 3개씩 보여주고자 한다.
   
   1 페이지 ==> rownum : 1 ~ 3    boardno : 14 ~ 12
   2 페이지 ==> rownum : 4 ~ 6    boardno : 11 ~ 9
   3 페이지 ==> rownum : 7 ~ 9    boardno :  8 ~ 6
   4 페이지 ==> rownum : 10 ~ 12  boardno :  5 ~ 3
   5 페이지 ==> rownum : 13 ~ 15  boardno :  2 ~ 1
    */
    
    /*
    === 페이징처리의 공식 ===
    where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
    
    where RNO between (1 * 3) - (3 - 1) and (1 * 3);
    where RNO between (3) - (2) and (3);
    where RNO between 1 and 3;
 */
    select RNO
        , boardno
        , subject
        , userid
        , registed_day
    from
    (
        select rownum RNO
            , D.boardno
            , D.subject
            , D.userid
            , D.registed_day
        from
        (
            select boardno
                ,subject
                ,userid
                ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') registed_day
            from tbl_board
            order by boardno desc
        )D
    ) T
    where T.RNO between 1 and 3;
    
     -- 또는 rownum 을 사용하지 않고 row_number() 함수를 사용해서 나타낼 수 있다.
     
     select row_number() over(order by boardno desc)
        ,boardno as 글번호
        ,subject as 글제목
        ,userid as 글쓴이
        ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') 작성일자
    from tbl_board
    where row_number() over(order by boardno desc) between 1 and 3;
    -- 오류 생김
    -- row_number()은 where 절에 바로 사용 불가
    
    -- row_number() 함수를 사용한 올바른 예제 
    
    select *
    from
    (
        select row_number() over(order by boardno desc) rno
            ,boardno
            ,subject
            ,userid
            ,to_char(registerday, 'yyyy-mm-dd hh24:mi:ss') registed_day
        from tbl_board
    )v
    where rno between 4 and 6;
    /*
    === 페이징처리의 공식 ===
    where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
    
    where RNO between (1 * 3) - (3 - 1) and (1 * 3);
    where RNO between (3) - (2) and (3);
    where RNO between 1 and 3;
 */
 
 
 
 
     --------------------***====계층형 쿼리====***-----------------------------
     /*
        계층형 쿼리는 Spring 시간에 답변형 게시판에 사용한다.
        또한 전자결제 에서도 사용된다.
     */
     
     /*
        1.          윤영주
                      |
        2.          강이훈
                      |
             —————————————
             |        |         |
        3. 이상우     김성훈     김시진
            |         |         |
        4. 이준영              조원재
                                |
        5.                    김진성
        
     */
     
     select *
     from employees
     order by employee_id asc;
     --결제라인을 만들어 보겠습니다.
     --         eomployee_id
     --출발       104  ==> 103 ==> 102 ==> 100 ==> null
     --level       1  ==>   2 ==>   3 ==>   4
     select level
            ,employee_id 사원번호
            ,first_name || ' ' || last_name 이름
            ,manager_id 직속결재권자
     from employees
     start with employee_id = 104
     connect by prior manager_id = employee_id; --104번 행의 매니저id 와 일치하는 직원번호를 연결
     -- **** !!!! prior 다음에 나오는 manager_id 컬럼은 start with 되어지는 행의 manager_id 컬럼의 값이다. !!!! *** --
     /*
        start with employee_id = 104
        connect by prior 103 = employee_id;
        
        start with employee_id = 103
        connect by prior 102 = employee_id;
        
        start with employee_id = 102
        connect by prior 100 = employee_id;
        
        start with employee_id = 100
        connect by prior null = employee_id;
     */
     
     /*
        ----------------------------------------------------------------------
         단계   사원번호   사원명            직속결재권자사원번호   직속결재권자사원명
        ----------------------------------------------------------------------
          1        104    Bruce Ernst        103              Alexander Hunold
          2        103    Alexander Hunold    102              Lex De Haan
          3        102    Lex De Haan        100              Steven King
          4        100    Steven King   
    */
     select *
     from employees
     order by employee_id asc;
     --결제라인을 만들어 보겠습니다.
     --         eomployee_id
     --출발       104  ==> 103 ==> 102 ==> 100 ==> null
     --level       1  ==>   2 ==>   3 ==>   4
     
     select lvl
            ,A.employee_id
            ,fullname
            ,A.manager_id
            ,E.first_name || ' ' || E.last_name 직속결제권자
     from 
     (
         select level lvl
                ,employee_id 
                ,first_name || ' ' || last_name fullname
                ,manager_id 
         from employees
         start with employee_id = 104
         connect by prior manager_id = employee_id
    ) A left join employees E
    on A.manager_id = E.employee_id;
    
    -- 또는
    with A as
    (
        select level lvl
                ,employee_id 
                ,first_name || ' ' || last_name fullname
                ,manager_id 
         from employees
         start with employee_id = 104
         connect by prior manager_id = employee_id
    )
    select lvl 레벨
            ,A.employee_id 직원번호
            ,fullname 이름
            ,A.manager_id 상관직원번호
            ,E.first_name || ' ' || E.last_name 직속결제권자
    from A left join employees E
    on A.manager_id = E.employee_id;
    
    
    
    
    -------------------------------------------------------------------------------------- 
 /*
      DML(Data Manuplation Language) : 데이터 조작어 ==> insert, update, delete, merge
                                     : 수동 commit 이므로 rollback 이 가능하다.
                                     
      DDL(Data Definition Language) : 데이터 정의어 ==> create, drop, alter, truncate
                                    : 자동 commit(Auto commit) 이므로 rollback 이 불가하다.
                                    
      DCL(Data Control Language) : 데이터 제어어 ==> grant, revoke     
                                 : 자동 commit(Auto commit) 이므로 rollback 이 불가하다.
                                 
      TCL(Transaction Control Language) : 트랜잭션 제어어 ==> commit, rollback
      
      DQL(Data Query Language) : 데이터 질의어 ==> select 
 */  
 
 -------- **** 데이터 조작어(DML == Data Manuplation Language) **** ---------
   --- DML 문은 기본적으로 수동 commit 이다.
   --- 즉, DML 문을 수행한 다음에는 바로 디스크(파일)에 적용되지 않고 commit 해야만 적용된다.
   --- 그래서 DML 문을 수행한 다음에 디스크(파일)에 적용치 않고자 한다라면 rollback 하면 된다.
   
   1. insert  --> 데이터 입력
   2. update  --> 데이터 수정
   3. delete  --> 데이터 삭제
   4. merge   --> 데이터 병합 
   
   
   -------- ====== ****   merge(병합)   **** ====== --------
   -- 어떤 2개 이상의 테이블에 존재하는 데이터를 다른 테이블 한곳으로 모으는것(병합)이다. 
   
   -- 생성되어진 데이터베이스 링크(database link)확인하기 --
   select *
   from user_db_links;
   
   /*
     ---------------------------------------------------------------------
       DB_LINK                  USERNAME   PASSWORD   HOST      CREATED
     ---------------------------------------------------------------------
       TEACHER_ORACLE_SERVER    HR         (null)     TEACHER   24/09/24
                                                  --  TEACHER 는 Net Service Name 네트서비스네임(넷서비스명)이다.
                                                  --  네트서비스네임 확인은 DB클라이언트 컴퓨터의 탐색기에서 C:\OracleXE18C\product\18.0.0\dbhomeXE\network\admin\tnsnames.ora 파일을 메모장으로 열어서 확인한다. 
   */
   
   -- ** 데이터베이스 링크(database link) 삭제하기 ** --
   drop database link TEACHER_ORACLE_SERVER;
   -- Database link TEACHER_ORACLE_SERVER이(가) 삭제되었습니다.
   
   
   -- ** 데이터베이스 링크(database link) 생성하기 ** --
   create database link bonjum_server_minjung
   connect to hr identified by gclass   -- hr 은 본점서버에 연결할 사용자계정명 이고,
   using 'MINJUNG';                     -- gclass 는 본점서버에 연결할 사용자계정명인 hr 의 암호이다. 
   -- Database link BONJUM_SERVER이(가) 생성되었습니다.
   
   /*
     ---------------------------------------------------------------------
       DB_LINK                  USERNAME   PASSWORD   HOST      CREATED
     ---------------------------------------------------------------------
       BONJUM_SERVER            HR         (null)     TEACHER   24/09/26
                                                  --  TEACHER 는 Net Service Name 네트서비스네임(넷서비스명)이다.
                                                  --  네트서비스네임 확인은 DB클라이언트 컴퓨터의 탐색기에서 C:\OracleXE18C\product\18.0.0\dbhomeXE\network\admin\tnsnames.ora 파일을 메모장으로 열어서 확인한다. 
   */
   
   -- 각 지점은 tbl_reservation_yeonkyuyeong 이라는 테이블을 생성한다.
   create table tbl_reservation_yeonkyuyeong
   (rsvno       varchar2(20)    -- 예약고유번호
   ,memberid    varchar2(20)    -- 회원ID
   ,ticketcnt   number          -- 티켓개수
   ,constraint PK_tbl_reservation_yeonkyuyeong primary key(rsvno)
   );
   -- Table TBL_RESERVATION_SEOYOUNGHAK이(가) 생성되었습니다.
   
   insert into tbl_reservation_yeonkyuyeong(rsvno, memberid, ticketcnt)
   values('yeonkyuyeong001', '연규영', 2);
   
   insert into tbl_reservation_yeonkyuyeong(rsvno, memberid, ticketcnt)
   values('yeonkyuyeong002', '윤석열', 7);
   
   commit;
   
   -- 아래는 본점DB서버(샘 PC)에서만 하는 것이다.
   create table tbl_reservation_merge
   (rsvno       varchar2(20)    -- 예약고유번호
   ,memberid    varchar2(20)    -- 회원ID
   ,ticketcnt   number          -- 티켓개수
   ,constraint PK_tbl_reservation_merge primary key(rsvno)
   );
   -- Table TBL_RESERVATION_MERGE이(가) 생성되었습니다.
   
   select *
   from tbl_reservation_merge@bonjum_server;
   
   
   select *
   from tbl_reservation_merge;
   
   select *
   from tbl_reservation_yeonkyuyeong;
   
   -- 아래는 지점DB서버(각자 여러분들)에서 하는 것입니다.
   merge into tbl_reservation_merge@bonjum_server R
   using tbl_reservation_yeonkyuyeong L
   on (L.rsvno = R.rsvno)
   when matched then  
        update set R.memberid = L.memberid, R.ticketcnt = L.ticketcnt
   when not matched then
        insert(rsvno, memberid, ticketcnt)
        values(L,rsvno, L.memberid, L.ticketcnt);
        
    -- 아래는 지점DB서버(각자 여러분들)에서 하는 것입니다.
   merge into tbl_reservation_merge@BONJUM_SERVER R 
   using tbl_reservation_yeonkyuyeong L 
   on (L.rsvno = R.rsvno)
   when matched then 
        update set R.memberid = L.memberid
                 , R.ticketcnt = L.ticketcnt
   when not matched then 
        insert(rsvno, memberid, ticketcnt) values(L.rsvno, L.memberid, L.ticketcnt);
        
    update tbl_reservation_yeonkyuyeong set memberid = 'Yoon Seokyeol', ticketcnt = 15 
   where rsvno = 'yeonkyuyeong002';
   -- 1 행 이(가) 업데이트되었습니다.
   
   merge into tbl_reservation_merge@BONJUM_SERVER R 
   using tbl_reservation_yeonkyuyeong L 
   on (L.rsvno = R.rsvno)
   when matched then 
        update set R.memberid = L.memberid
                 , R.ticketcnt = L.ticketcnt
   when not matched then 
        insert(rsvno, memberid, ticketcnt) values(L.rsvno, L.memberid, L.ticketcnt);
        
    commit;
    
   INSERT INTO  tbl_reservation_merge(rsvno, memberid, ticketcnt) values('yeonkyuyeong001', '연규영', 2);
   INSERT INTO  tbl_reservation_merge(rsvno, memberid, ticketcnt) values('yeonkyuyeong002', '윤석열', 7);
   
   commit;
   
   select *
   from tbl_reservation_merge;
   
   
   merge into tbl_reservation_merge_mj@bonjum_server_minjung R 
   using tbl_reservation_yeonkyuyeong L 
   on (L.rsvno = R.rsvno)
   when matched then 
        update set R.memberid = L.memberid
                 , R.ticketcnt = L.ticketcnt
   when not matched then 
        insert(rsvno, memberid, ticketcnt) values(L.rsvno, L.memberid, L.ticketcnt);
        
    select *
    from tbl_reservation_yeonkyuyeong;
            
    commit;
    
    insert 는 문법이
    insert into 테이블명(컬럼명1,컬럼명2,...) values(값1,값2,...); 
   이다.
   
   ※ Unconditional insert all  -- ==> 조건이 없는 insert 
    [문법] insert all 
           into 테이블명1(컬럼명1, 컬럼명2, ....)
           values(값1, 값2, .....)
           into 테이블명2(컬럼명3, 컬럼명4, ....)
           values(값3, 값4, .....)
           SUB Query문;
    
    ※ Conditional insert all -- ==> 조건이 있는 insert 
   조건(where절)에 일치하는 행들만 특정 테이블로 찾아가서 insert 하도록 하는 것이다.
   
   create table tbl_emp1
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(6)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );
   -- Table TBL_EMP1이(가) 생성되었습니다.
    create table tbl_emp1_backup
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(6)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );
   
   
   insert all 
           into tbl_emp1(empno, ename, monthsal, gender, manager_id, department_id, department_name)
           values(employee_id, fullname, month_sal, gender, manager_id, department_id, department_name)
           into tbl_emp1_backup(empno, ename, monthsal, gender, manager_id, department_id, department_name)
           values(employee_id, fullname, month_sal, gender, manager_id, department_id, department_name)
   select employee_id
        , first_name || ' ' || last_name AS fullname 
        , nvl(salary + (salary * commission_pct), salary) AS month_sal
        , case when substr(jubun,7,1) in('1','3') then '남' else '여' end AS gender
        , E.manager_id
        , E.department_id
        , department_name
   from employees E LEFT JOIN departments D 
   on E.department_id = D.department_id
   order by E.department_id asc, employee_id asc;
   -- 214개 행 이(가) 삽입되었습니다. 107개 행 * 2
   
   select *
   from tbl_emp1;
   
   select *
   from tbl_emp1_backup;
   
   rollback;
   commit;
   
   ※ Conditional insert all -- ==> 조건이 있는 insert 
   조건(where절)에 일치하는 행들만 특정 테이블로 찾아가서 insert 하도록 하는 것이다.
   
   create table tbl_emp_dept30
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(4)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );
   -- Table TBL_EMP_DEPT30이(가) 생성되었습니다.


   create table tbl_emp_dept50
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(4)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );
   -- Table TBL_EMP_DEPT50이(가) 생성되었습니다.
   

   create table tbl_emp_dept80
   (empno            number(6)
   ,ename            varchar2(50)
   ,monthsal         number(7)
   ,gender           varchar2(4)
   ,manager_id       number(6)
   ,department_id    number(4)
   ,department_name  varchar2(30)
   );
   -- Table TBL_EMP_DEPT80이(가) 생성되었습니다.
   
   
   insert all
        when department_id = 30 then
        into tbl_emp_dept30(empno, ename, monthsal, gender, manager_id, department_id, department_name)
        values(employee_id, fullname, month_sal, gender, manager_id, department_id, department_name)
        when department_id = 50 then
        into tbl_emp_dept50(empno, ename, monthsal, gender, manager_id, department_id, department_name)
        values(employee_id, fullname, month_sal, gender, manager_id, department_id, department_name)
        when department_id = 80 then
        into tbl_emp_dept80(empno, ename, monthsal, gender, manager_id, department_id, department_name)
        values(employee_id, fullname, month_sal, gender, manager_id, department_id, department_name)
   select employee_id
        , first_name || ' ' || last_name AS fullname 
        , nvl(salary + (salary * commission_pct), salary) AS month_sal
        , case when substr(jubun,7,1) in('1','3') then '남' else '여' end AS gender
        , E.manager_id
        , E.department_id
        , department_name
   from employees E LEFT JOIN departments D 
   on E.department_id = D.department_id
   where E.department_id in(30, 50, 80)
   order by E.department_id asc, employee_id asc;
   
   select * 
   from tbl_emp_dept30;
   
   select * 
   from tbl_emp_dept50;
   
   select * 
   from tbl_emp_dept80;
   
   commit;
   
   
   
   
   
   
   
   -------- **** 데이터 질의어(DQL == Data Query Language) **** ---------
   -->  DQL 은 select 를 말한다.
   
   
   
   
   -------- **** 트랜잭션 제어어(TCL == Transaction Control Language) **** ---------
   -->  TCL 은 commit, rollback 을 말한다.
   
   -- *** Transaction(트랜잭션) 처리 *** --
   --> Transaction(트랜잭션)이라 함은 관련된 일련의 DML로 이루어진 한꾸러미(한세트)를 말한다.
   --> Transaction(트랜잭션)이라 함은 데이터베이스의 상태를 변환시키기 위하여 논리적 기능을 수행하는 하나의 작업단위를 말한다. 
   /*
      예>   네이버카페(다음카페)에서 활동
            글쓰기(insert)를 1번하면 내포인트 점수가 10점이 올라가고(update),
            댓글쓰기(insert)를 1번하면 내포인트 점수가 5점이 올라가도록 한다(update)
           
           위와같이 정의된 네이버카페(다음카페)에서 활동은 insert 와 update 가 한꾸러미(한세트)로 이루어져 있는 것이다.
           이와 같이 서로 다른 DML문이 1개의 작업을 이룰때 Transaction(트랜잭션) 처리라고 부른다.
           
           Transaction(트랜잭션) 처리에서 가장 중요한 것은 
           모든 DML문이 성공해야만 최종적으로 모두 commit 을 해주고,
           DML문중에 1개라도 실패하면 모두 rollback 을 해주어야 한다는 것이다. 
           
           예를 들면 네이버카페(다음카페)에서 글쓰기(insert)가 성공 했다라면
           그 이후에 내포인트 점수가 10점이 올라가는(update) 작업을 해주고, update 작업이 성공했다라면
           commit 을 해준다. 
           만약에 글쓰기(insert) 또는 10점이 올라가는(update) 작업이 실패했다라면
           rolllback 을 해준다.
           이러한 실습은 자바에서 하겠습니다.
   */
   
       ----- **** ====  ROLLBACK TO SAVEPOINT  ==== **** -----
    ----- **** 특정 시점까지 rollback 을 할 수 있습니다. **** -----
    
    select *
    from employees
    where department_id = 50;
    
    update employees set first_name = '몰라'
    where department_id = 50;
    -- 45개 행 이(가) 업데이트되었습니다.
    
    savepoint point_1;
    -- Savepoint이(가) 생성되었습니다.
    
    delete from employees
    where department_id is null;
    -- 1 행 이(가) 삭제되었습니다.
        
    select first_name
    from employees
    where department_id = 50;
    -- 전부다 '몰라' 로 나온다.
    
    select *
    from employees
    where department_id is null;
    -- 행이 없다.
    
    rollback to savepoint point_1;
    -- 롤백 완료.
    -- savepoint point_1; 이 선언되어진 이후로 실행된 DML문을 rollback 시킨다.
    /*
       그러므로
       delete from employees
       where department_id is null; 만 롤백시킨다.
    */
    
    select *
    from employees
    where department_id is null;
    -- 행이 나온다.
    
    select first_name
    from employees
    where department_id = 50;
    -- 아직까지 전부다 '몰라' 로 나온다.
    
    rollback;  --> commit; 한 이후로 수행되어진 모든 DML문을 롤백시킨다.
    -- 롤백 완료.
   
    select first_name
    from employees
    where department_id = 50;
    -- first_name 컬럼의 값이 원상복구됨.  
    
    
    ---- *** >>> 데이터 백업 없이 delete, update 한 후 commit 되어진 데이터 복구하기 <<< *** ----
   /*
        타임머신 기능을 이용하여 delete, update 한 후 commit 되어진 데이터를 복구할 수 있다. (oracle 11g 이후 부터 가능)
   */
   
   --  flashback query : 어떤 테이블의 데이터값을 과거의 데이터값으로 돌아가는 기능이다.
   create table tbl_exam
   (no      number(4)
   ,name    Nvarchar2(20)
   ,address Nvarchar2(20)
   );
   -- Table TBL_EXAM이(가) 생성되었습니다.
   
   insert into tbl_exam(no, name, address) values(101, '이순신', '서울시 강동구');
   insert into tbl_exam(no, name, address) values(201, '엄정화', '서울시 강서구');
   insert into tbl_exam(no, name, address) values(301, '유관순', '서울시 강남구');
   insert into tbl_exam(no, name, address) values(401, '서강준', '서울시 강북구');
   
   commit;
   -- 커밋 완료.
   
   select systimestamp
   from dual;
   --24/09/26 15:14:05.652000000 +09:00
   
   select *
   from tbl_exam;
   
    select systimestamp
    from dual;
    --24/09/26 15:16:23.772000000 +09:00
    
    delete from tbl_exam;
    
    commit;
    
    select systimestamp
    from dual;
    --24/09/26 15:14:05.652000000 +09:00
    
    select *
    from tbl_exam;
    -- 아무것도 나오지 않는다.
    create table tbl_exam_backup
    as
    select *
    from tbl_exam as of timestamp(systimestamp - interval'8' minute);
    /*
        NO	NAME	ADDRESS
        101	이순신	서울시 강동구
        201	엄정화	서울시 강서구
        301	유관순	서울시 강남구
        401	서강준	서울시 강북구
    */
    insert into tbl_exam
    select *
    from tbl_exam_backup;
    
    drop table tbl_exam_backup purge;
    
    update tbl_exam set name = '몰라', address = '없음'
    where no in(101, 401);
    
    commit;
    
    select *
    from tbl_exam as of timestamp(systimestamp - interval '8' minute)
    minus
    select *
    from tbl_exam;
    
    create table tbl_exam_origin
    as
    select *
    from tbl_exam as of timestamp(systimestamp - interval '8' minute)
    minus
    select *
    from tbl_exam;
    
    select *
    from tbl_exam_origin;
    
    update tbl_exam A set name = (select name from tbl_exam_origin where no = A.no)
                       , address = (select address from tbl_exam_origin where no = A.no)
   where no in (select no from tbl_exam_origin); 


 
    
    select *
    from tbl_exam;
    
    commit;
    
    
    -------- **** 데이터 정의어(DDL == Data Defination Language) **** ---------
    ==> DDL : create, drop, alter, truncate 
    --> 여기서 중요한 것은 DDL 문을 실행을 하면 자동적으로 commit; 이 되어진다.
    --  즉, auto commit 되어진다.
    
    select *
    from employees
    where employee_id = 100;
    -- salary ==> 24000
    -- email  ==> SKING 
    
    update employees set salary = 11111, email = 'sdfsdf'
    where employee_id = 100;
    -- 1 행 이(가) 업데이트되었습니다.
    
    create table tbl_temp
    (no    number
    ,name  varchar2(20)
    );
    -- Table tbl_temp이(가) 생성되었습니다.
    -- DDL 문을 실행했으므로 자동적으로 commit; 이 되어진다.
    
    select *
    from employees
    where employee_id = 100;
    
    rollback;
    -- 롤백 완료.
    
    select *
    from employees
    where employee_id = 100;
    -- 위에서 DDL문(create)을 실행했으므로 자동적으로 commit; 이 되어지기 때문에 rollback 안 됨.
   
   
    update employees set salary = 24000, email = 'SKING'
    where employee_id = 100;
    -- 1 행 이(가) 업데이트되었습니다.
    
    commit;
    -- 커밋 완료.
    
    
    
    
    
    
    
    
        ------ ====== **** TRUNCATE table 테이블명; **** ====== ------  
    --> TRUNCATE table 테이블명; 을 실행하면 테이블명 에 존재하던 모든 행(row)들을 삭제해주고,
    --  테이블명에 해당하는 테이블은 완전초기화 가 되어진다.
    --  중요한 사실은 TRUNCATE table 테이블명; 은 DDL 문이기에 auto commit; 되어지므로 rollback 이 불가하다.
   
    --  delete from 테이블명; 을 실행하면 이것도 테이블명 에 존재하던 모든 행(row)들을 삭제해준다.
    --  이것은 DML문 이므로 rollback 이 가능하다.
    
    create table tbl_emp_copy1
    as
    select * from employees;
    -- Table TBL_EMP_COPY1이(가) 생성되었습니다.
    
    select *
    from tbl_emp_copy1;
    
    delete from tbl_emp_copy1;
    -- 107개 행 이(가) 삭제되었습니다.
    
    select count(*)
    from tbl_emp_copy1;  -- 0
    
    rollback;
    -- 롤백 완료.
    
    select count(*)
    from tbl_emp_copy1;  -- 107
    
    
    truncate table tbl_emp_copy1;
    -- Table TBL_EMP_COPY1이(가) 잘렸습니다.
    
    select *
    from tbl_emp_copy1;
    
    select count(*)
    from tbl_emp_copy1; -- 0 
    
    rollback;  -- auto commit 이 되어졌으므로 rollback 해봐야 소용없다. 
    -- 롤백 완료.
    
    select *
    from tbl_emp_copy1;
    
    select count(*)
    from tbl_emp_copy1; -- 0
    
    
    
    
    
    
    
    
    
    
    
    
    
        ---------- **** 데이터 제어어(DCL == Data Control Language) **** -------------
    ==> DCL : grant(권한 부여하기) , revoke(권한 회수하기)
    --> 여기서 중요한 것은 DCL 문을 실행을 하면 자동적으로 commit; 이 되어진다.
    --  즉, auto commit 되어진다.
    
    
    --- **** SYS 또는 SYSTEM 에서 아래와 같은 작업을 한다. 시작 **** ---
    show user;
    -- USER이(가) "SYS"입니다.
    
    --  orauser1 이라는 오라클 일반사용자 계정을 생성합니다. 암호는 gclass 라고 하겠습니다.
    alter session set "_ORACLE_SCRIPT"=true;
    -- Session이(가) 변경되었습니다.
    
    create user orauser1 identified by gclass default tablespace users;
    -- User ORAUSER1이(가) 생성되었습니다.
    
    -- orauser1 계정의 암호를 abcd 로 변경한 것임.
    alter user orauser1 identified by abcd;
    -- User ORAUSER1이(가) 변경되었습니다.
    
    alter user orauser1 identified by gclass;
    -- User ORAUSER1이(가) 변경되었습니다.
    
    -- 생성되어진 오라클 일반사용자 계정인 orauser1 에게 오라클서버에 접속이 되어지고, 
    -- 접속이 되어진 후 테이블 등을 생성할 수 있도록 권한을 부여해주겠다.
    grant connect, resource, unlimited tablespace to orauser1;
    -- Grant을(를) 성공했습니다.
    
    --- **** SYS 또는 SYSTEM 에서 아래와 같은 작업을 한다. 끝 **** ---
    
    --- **** HR 에서 아래와 같은 작업을 한다. **** ---
    show user;
    -- USER이(가) "HR"입니다.
    
    select *
    from HR.employees;
    
    -- 현재 오라클 서버에 접속된 사용자가 HR 이므로 HR.employees 대신에 employees 을 쓰면 HR.employees 으로 인식해준다.
    select *
    from employees;
    --HR이 자신의 소유인 employees 테이블에 대해 orauser1 에게 select 할 수 있도록 권한을 부여하기전
    show user;
    --USER이(가) "ORAUSER1"입니다.
    select *
    from tab;
    
    select *
    from HR.employees;
    --ORA-00942: 테이블 또는 뷰가 존재하지 않습니다
    --> 현재 orauser1 이 HR.employees 테이블에 대해서 select 할 수 있는 권한이 없다는 뜻이다.
    
    --*** HR로 접속을 한다. ***----
    show user;
    --USER이(가) "HR"입니다.
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 select 할 수 있도록 권한을 부여하겠습니다.
    
    grant select on employees to orauser1;
    --Grant을(를) 성공했습니다.
    
    --*** orauser1로 접속을 한다. ***----
    show user;
    --USER이(가) "ORAUSER1"입니다.
    
    select *
    from HR.employees;
    
    update HR.employees set email = '몰라';
    --SQL 오류: ORA-01031: 권한이 불충분합니다
    
    delete HR.employees where department_id is null;
    --SQL 오류: ORA-01031: 권한이 불충분합니다
    
    grant  select on departments to orauser1;
    
    select *
    from hr.departments;
    
    select *
    from hr.employees E join hr.departments D
    on E.department_id = D.department_id;
    
    --*** HR로 접속을 한다. ***----
    show user;
    --USER이(가) "HR"입니다.
    
    -- orauser1 에게 HR이 자신의 소유인 employees 테이블에 대해 select 할 수 있도록 권한을 부여한 것을 회수하겠습니다.
    revoke select on employees from orauser1;
    --Revoke을(를) 성공했습니다.
    
    revoke select on departments from orauser1;
    
    --*** orauser1로 접속을 한다. ***----
    show user;
    --USER이(가) "ORAUSER1"입니다.
    
    select *
    from hr.employees;
    --ORA-00942: 테이블 또는 뷰가 존재하지 않습니다
    
    select *
    from hr.departments;
    
    
    
    
    -------------------------------------------
    -- *** HR로 접속을 한다. ***---
    show user;
    -- USER이(가) "HR"입니다.
    
    select *
    from user_tab_privs;
    -- 현재 오라클 서버에 접속한 유저(HR)가 자신의 소유인 테이블에 대해서
    -- 다른 사용자에게 권한을 부여해준것을 조회하는 것이다.
    
    grant select,update, delete on employees to orauser1;
    
    select *
    from user_tab_privs;
    
    grant select,update, delete on departments to orauser1;
    
    select *
    from user_tab_privs;
    
    
    --*** orauser1로 접속을 한다. ***----
    show user;
    --USER이(가) "ORAUSER1"입니다.
    
    select *
    from user_tab_privs_recd;
    -- 현재 오라클 서버에 접속한 유저(ORAUSER1)가 다른 사용자로 부터
    -- 권한을 부여받은 것을 조회하는 것이다.
    
    -- *** HR로 접속을 한다. ***---
    show user;
    -- USER이(가) "HR"입니다.
    
    revoke select,update, delete on employees from orauser1;
    
    select *
    from user_tab_privs;
    
    revoke select,update, delete on departments from orauser1;
    
    select *
    from user_tab_privs;
    
    ------- ==== *** 시노님(Synonym, 동의어) *** ==== --------
    
    --*** orauser1로 접속을 한다. ***----
    show user;
    --USER이(가) "ORAUSER1"입니다.
    
    select *
    from user_tab_privs_recd;
    -- 현재 오라클 서버에 접속한 유저(ORAUSER1)가 다른 사용자로 부터
    -- 권한을 부여받은 것을 조회하는 것이다.
    
    select *
    from HR.employees;
    
    --HR.employees 이름을 emp라는 이름으로 사용하도록 한다.
    -- 이러한 경우 시노님(Synonym, 동의어)을 사용하여 해결한다.
    
    create or replace Synonym emp for hr.employees;
    -- ORA-01031: 권한이 불충분합니다
    
    --*** SYS 또는 SYSTEM 으로 접속을 한다. ***----
    show user;
    --USER이(가) "SYS"입니다.
    
    grant create synonym to orauser1;
    
    
    --*** orauser1로 접속을 한다. ***----
    show user;
    --USER이(가) "ORAUSER1"입니다.
    
    create or replace Synonym emp for hr.employees;
    --성공
    
    --- *** 생성되어진 시노님(Synonym, 동의어)을 조회해 본다. *** ---
  select *
  from user_synonyms;
  /*
      ------------------------------------------------------
        SYNONYM_NAME	TABLE_OWNER	TABLE_NAME	DB_LINK	ORIGIN_CON_ID
      ------------------------------------------------------
        EMP	            HR	        EMPLOYEES	(null)  1
      */
    
    
    select *
    from emp;
    
    select * from tab;
    
        -- *** HR 로 접속을 한다. *** --
    show user;
    -- USER이(가) "HR"입니다.
    
    select *
    from user_db_links;
   /*
     ---------------------------------------------------------------------
       DB_LINK                  USERNAME   PASSWORD   HOST      CREATED
     ---------------------------------------------------------------------
       BONJUM_SERVER            HR         (null)     TEACHER   24/09/26
                                                  --  TEACHER 는 Net Service Name 네트서비스네임(넷서비스명)이다.
                                                  --  네트서비스네임 확인은 DB클라이언트 컴퓨터의 탐색기에서 C:\OracleXE18C\product\18.0.0\dbhomeXE\network\admin\tnsnames.ora 파일을 메모장으로 열어서 확인한다. 
   */   
   
   select * 
   from tbl_reservation_merge@BONJUM_SERVER;
   
   create or replace synonym reservation for tbl_reservation_merge@BONJUM_SERVER;
   -- Synonym RESERVATION이(가) 생성되었습니다.
   
   --- *** 생성되어진 시노님(Synonym, 동의어)을 조회해 본다. *** ---
   select *
   from user_synonyms;
  /*
      ----------------------------------------------------------------------
        SYNONYM_NAME   TABLE_OWNER   TABLE_NAME                DB_LINK
      ----------------------------------------------------------------------
         RESERVATION                TBL_RESERVATION_MERGE     BONJUM_SERVER
  */ 
   select * 
   from RESERVATION;
   
   select *
   from TBL_RESERVATION_MERGE@BONJUM_SERVER;
   
   
   
   
   
   
   
   
   
   
   
   -------- **** ==== 시퀀스(sequence) ===== **** ----------
           
   -- 시퀀스(sequence)란? 쉽게 생각하면 은행에서 발급해주는 대기번호표 와 비슷한 것이다.
   -- 시퀀스(sequence)는 숫자로 이루어져 있으며 매번 정해진 증가치 만큼 숫자가 증가되어지는 것이다.    
   
   
      /*
     create sequence seq_yeyakno   -- seq_yeyakno 은 시퀀스(sequence) 이름이다.
     start with 1    -- 첫번째 출발은 1 부터 한다.
     increment by 1  -- 증가치 값    2 3 4 5 ......
     maxvalue 5      -- 최대값이 5 이다.
  -- nomaxvalue      -- 최대값이 없는 무제한. 계속 증가시키겠다는 말이다.
     minvalue 2      -- 최소값이 2 이다. cycle 이 있을때만 minvalue 에 주어진 값이 사용된다. 
                     --                nocycle 일 경우에는 minvalue 에 주어진 값이 사용되지 않는다.
                     -- minvalue 숫자 에 해당하는 숫자 값은 start with 숫자 에 해당하는 숫자 값과 같든지 
                     -- 아니면 start with 숫자 에 해당하는 숫자보다 작아야 한다.
                     
  -- nominvalue      -- 최소값이 없다.   
     cycle           -- 반복을 한다.
  -- nocycle         -- 반복이 없는 직진.
     nocache;
  */
   
   
    create sequence seq_yeyakno   -- seq_yeyakno 은 시퀀스(sequence) 이름이다.
    start with 1    -- 첫번째 출발은 1 부터 한다.
    increment by 1  -- 증가치 값    2 3 4 5 ......
    maxvalue 5      -- 최대값이 5 이다.
    minvalue 2      -- 최소값이 2 이다. cycle 이 있을때만 minvalue 에 주어진 값이 사용된다. 
                         --                nocycle 일 경우에는 minvalue 에 주어진 값이 사용되지 않는다.
                         -- minvalue 숫자 에 해당하는 숫자 값은 start with 숫자 에 해당하는 숫자 값과 같든지 
                         -- 아니면 start with 숫자 에 해당하는 숫자보다 작아야 한다.   
    cycle           -- 반복을 한다.
    nocache;
   
   --오류 ORA-04006: START WITH 에 MINVALUE 보다 작은 값은 지정할 수 없습니다
   
   
   create sequence seq_yeyakno   -- seq_yeyakno 은 시퀀스(sequence) 이름이다.
    start with 2    -- 첫번째 출발은 2 부터 한다.
    increment by 1  -- 증가치 값    2 3 4 5 ......
    maxvalue 5      -- 최대값이 5 이다.
    minvalue 1      -- 최소값이 1 이다. cycle 이 있을때만 minvalue 에 주어진 값이 사용된다. 
                         --                nocycle 일 경우에는 minvalue 에 주어진 값이 사용되지 않는다.
                         -- minvalue 숫자 에 해당하는 숫자 값은 start with 숫자 에 해당하는 숫자 값과 같든지 
                         -- 아니면 start with 숫자 에 해당하는 숫자보다 작아야 한다.   
    cycle           -- 반복을 한다.
    nocache;
    
    
    
    ---- **** 생성되어진 시퀀스(sequence)를 조회해 봅니다. **** ----
  select last_number -- 다음번에 들어올 시퀀스 값을 미리 알려주는 것이다
  from user_sequences
  where sequence_name = 'SEQ_YEYAKNO';
  
  
  
  create table tbl_board_test_1
  (boardno        number
  ,subject        varchar2(100)
  ,registerdate   date default sysdate
  );
  -- Table TBL_BOARD_TEST_1이(가) 생성되었습니다.  
    
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '첫번째 글입니다.'); ---암기
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 start 값이 2 이었다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '두번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '세번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '네번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.
  -- seq_yeyakno_1 시퀀스의 maxvalue 값이 5 이었고, cycle 이었다. 즉, 반복을 한다. 
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '다섯번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 minvalue 값이 1 이었고, cycle(반복) 이었으므로
  -- maxvalue 값이 사용되어진 다음에 들어오는 시퀀스 값은 minvalue 값인 1 이 들어온다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '여섯번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.
  
  insert into tbl_board_test_1(boardno, subject) values(seq_yeyakno.nextval, '일곱번째 글입니다.');
  -- 1 행 이(가) 삽입되었습니다.
  -- seq_yeyakno_1 시퀀스의 increment 값이 1 이었다.
  
  commit; 
  -- 커밋 완료.
  
  select *
  from tbl_board_test_1;
  /*
      seq_yeyakno_1 시퀀스값의 사용은 
      2(start)  3  4  5(maxvalue) 1(minvalue) 2 3 4 5(maxvalue) 1(minvalue) 2 3 4 5 1 2 3 ...... 
      와 같이 사용된다.
  */
  
  create sequence seq_yeyakno_2
  start with 1    -- 첫번째 출발은 1 부터 한다. 
  increment by 1  -- 증가치는 1 이다. 즉, 1씩 증가한다. 
  nomaxvalue      -- 최대값은 없는 무제한. 계속 증가시키겠다는 말이다. 
  nominvalue      -- 최소값이 없다.
  nocycle         -- 반복을 안한다.
  nocache; 
  -- Sequence SEQ_YEYAKNO_2이(가) 생성되었습니다.
  
  create sequence seq_yeyakno_3;
  --Sequence SEQ_YEYAKNO_3이(가) 생성되었습니다.
  --위의 시퀀스랑 같은 내용지만 캐시는 20으로 설정된다
  
  select *
  from user_sequences
  where sequence_name in('SEQ_YEYAKNO', 'SEQ_YEYAKNO_2', 'SEQ_YEYAKNO_3');
  
  create table tbl_board_test_2
  (boardno        number
  ,subject        varchar2(100)
  ,registerdate   date default sysdate
  );
  -- Table TBL_BOARD_TEST_2이(가) 생성되었습니다.
                  
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '첫번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '두번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '세번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '네번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '다섯번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '여섯번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '일곱번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '여덟번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '아홉번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '열번째 글입니다.');
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '열한번째 글입니다.');
 
 commit;        
 
 select *
 from tbl_board_test_2;
 
 -- *** 시퀀스 SEQ_YEYAKNO_2 이 마지막으로 사용되어진 값(= 현재 시퀀스값) 을 알아보려고 한다. *** -- 
 select seq_yeyakno_2.currval
 from dual;
 -- 11
 
 select seq_yeyakno_2.nextval
 from dual;
 --12
 
 select seq_yeyakno_2.currval
 from dual;
 --13
 
 select last_number  -- 다음번에 들어올 시퀀스 값을 미리 알려주는 것이다.
 from user_sequences
 where sequence_name = 'SEQ_YEYAKNO_2';
 
 
 insert into tbl_board_test_2(boardno, subject) values(seq_yeyakno_2.nextval, '열두번째 글입니다.');
 
 commit;        
 
 select *
 from tbl_board_test_2;
 
 ---- *** 시퀀스(sequence) 삭제하기 *** ----
 drop sequence seq_yeyakno_2;
 -- Sequence SEQ_YEYAKNO_2이(가) 삭제되었습니다.
 
 select *
  from user_sequences
  where sequence_name in('SEQ_YEYAKNO', 'SEQ_YEYAKNO_2', 'SEQ_YEYAKNO_3');
  
  
  
  
  
  
  
  
  
  
  --!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--
  --                중요
  --- ==== Constraint(제약조건)을 사용하여 테이블을 생성해 보겠습니다. === ----
    /*
     >>>> 제약조건(Constraint)의 종류 <<<<
     
     --   제약조건의 이름은 오라클 전체에서 고유해야 한다.
     
     1. Primary Key(기본키, 대표식별자) 제약 [P]  -- 하나의 테이블당 오로지 1개만 생성할 수 있다.
                                               -- 어떤 컬럼에 Primary Key(기본키) 제약을 주면 그 컬럼에는 자동적으로 NOT NULL 이 주어지면서 동시에 그 컬럼에는 중복된 값은 들어올 수 없고 오로지 고유한 값만 들어오게 되어진다.
                                               -- 컬럼 1개를 가지고 생성된 Primary Key 를 Single Primary Key 라고 부르고,
                                               -- 컬럼 2개 이상을 가지고 생성된 Primary Key 를 Composite(복합) Primary Key 라고 부른다.
     
     2. Unique 제약 [U]              -- 하나의 테이블당 여러개를 생성할 수 있다.                                 
                                    -- 어떤 컬럼에 Unique 제약을 주면 그 컬럼에는 NULL 을 허용할 수 있으며, 그 컬럼에는 중복된 값은 들어올 수 없고 오로지 고유한 값만 들어오게 되어진다.             
                                    -- 여러개의 Unique Key 중에 후보키, 후보식별자가 되려면 해당 컬럼은 NOT NULL 이어야 한다. 
  
     3. Foreign Key(외래키) 제약 [R]  -- 하나의 테이블당 여러개를 생성할 수 있다. 
                                     -- Foreign Key(외래키) 제약에 의해 참조(Reference)받는 컬럼은 반드시 NOT NULL 이어야 하고, 중복된 값을 허락하지 않는 고유한 값만 가지는 컬럼이어야 한다. 
                                     
     4. Check 제약 [C]               -- 하나의 테이블당 여러개를 생성할 수 있다.
                                    -- insert(입력) 또는 update(수정) 시 어떤 컬럼에 입력되거나 수정되는 데이터값을 아무거나 허락하는 것이 아니라 조건에 맞는 데이터값만 넣고자 할 경우에 사용되는 것이다.
  
     5. NOT NULL 제약 [C]            -- 하나의 테이블당 여러개를 생성할 수 있다.
                                    -- 특정 컬럼에 NOT NULL 제약을 주면 그 컬럼에는 반드시 데이터값을 주어야 한다는 말이다. 
  */
  
    ---- ***   "고객" 이라는 테이블을 생성해 보겠습니다. *** ----
  
    create table tbl_gogek
    (gogekId     varchar2(30)
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    );
    -- Table TBL_GOGEK이(가) 생성되었습니다.
  
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('leess', '이순신', '010-2345-6789');
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('leess', '이삼순', '010-6789-1234');
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('eomjh', '엄정화', null);
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('null', '엄주희', '010-2237-9987');
    rollback;
    commit;
    
    select *
    from tbl_gogek;
    
    drop table tbl_gogek; --휴지통에 버리기
    
    select * from tab;
    
    select *
    from "BIN$Vv8zhbLfQymLIWTeuHKDIQ==$0";
  
    ---- *** 휴지통 조회하기 ***---
    
    select *
    from user_recyclebin;
  
    flashback table TBL_GOGEK to before drop;
    
    drop table tbl_gogek purge; --영구히 삭제
    
  -----------------------------------------------------------------------------------
    create table tbl_gogek
    (gogekId     varchar2(30) primary key -- column level(컬럼수준) 제약조건
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    );
    
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('leess', '이순신', '010-2345-6789');
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('leess', '이삼순', '010-6789-1234');
    --ORA-00001: 무결성 제약 조건(HR.SYS_C007357)에 위배됩니다
    --SYS_C007357은 제약조건의 이름인데 column level(컬럼수준) 제약조건으로 생성하면
    --제약조건 이름은 자동적으로 항상 SYS_C~~~ 로 되어진 고유한 제약조건명이 되어진다
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('eomjh', '엄정화', null);
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values(null, '엄주희', '010-2237-9987');
    
    select *
    from tbl_gogek;
  
    commit;
    
    desc tbl_gogek;
    
    /*
    이름         널?       유형           
    ---------- -------- ------------ 
    GOGEKID    NOT NULL VARCHAR2(30) 
    GOGEKNAME  NOT NULL VARCHAR2(30) 
    GOGEKPHONE          VARCHAR2(30) 
  */
    drop table tbl_gogek purge;
    
    create table tbl_gogek
    (gogekId     varchar2(30) primary key -- column level(컬럼수준) 제약조건
    ,email       varchar2(50) primary key 
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    );
    -- ORA-02260: 테이블에는 하나의 기본 키만 가질 수 있습니다.
    
    
    
    create table tbl_gogek
    (gogekId     varchar2(30) 
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    ,constraint PK_tbl_gogek_gogekId primary key(gogekId) -- row level(행 수준) 제약조건
    -- gogekId 컬럼에 primary key(기본키) 제약을 준 것이다.
    -- PK_tbl_gogek_gogekId 은 primary key(==기본키) 제약조건의 이름이다.
    );
    
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('leess', '이순신', '010-2345-6789');
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('leess', '이삼순', '010-6789-1234');
    --ORA-00001: 무결성 제약 조건(HR.SYS_C007357)에 위배됩니다
    --ORA-00001: 무결성 제약 조건(HR.PK_TBL_GOGEK_GOGEKID)에 위배됩니다 < 이렇게 뜨면 유지보수 측면에 더 좋음
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values('eomjh', '엄정화', null);
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values(null, '엄주희', '010-2237-9987');
    
    select *
    from tbl_gogek;
  
    commit;
    
    desc tbl_gogek;
    
    /*
    이름         널?       유형           
    ---------- -------- ------------ 
    GOGEKID    NOT NULL VARCHAR2(30) 
    GOGEKNAME  NOT NULL VARCHAR2(30) 
    GOGEKPHONE          VARCHAR2(30) 
  */
    
    create table tbl_gogek
    (gogekId     varchar2(30)
    ,email       varchar2(50)
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    ,constraint PK_tbl_gogek_gogekId primary key(gogekId) -- row level(행 수준) 제약조건
    ,constraint PK_tbl_gogek_email primary key(email)
    );
    --기본키는 한개밖에 안됨
    
    --- *** Single Primary Key *** ---
    -- Single Primary Key : 컬럼 1개에 대해서만 Primary Key 를 준 것.
    create table tbl_gogek_1
    (gogekId     varchar2(30)
    ,email       varchar2(50)
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    ,constraint PK_tbl_gogek_1_gogekId primary key(gogekId) -- row level(행 수준) 제약조건
    );
    
    --- *** Composite(복합) Primary Key *** ---
    -- Composite(복합) Primary Key : 컬럼 2개 이상에 대해서 Primary Key 를 준 것.
    create table tbl_gogek_2
    (gogekId     varchar2(30)
    ,email       varchar2(50)
    ,gogekName   varchar2(30) not null
    ,gogekPhone  varchar2(30)
    ,constraint PK_tbl_gogek_2_gogekId primary key(gogekId, email) -- row level(행 수준) 제약조건
    );
    
    insert into tbl_gogek_1(gogekId, email, gogekName, gogekPhone) values('leess','leess@naver.com', '이순신', '010-2345-6789');
    insert into tbl_gogek_1(gogekId, email, gogekName, gogekPhone) values('leess','leess@gmail.com', '이삼순', '010-6789-1234');
    --ORA-00001: 무결성 제약 조건(HR.PK_TBL_GOGEK_1_GOGEKID)에 위배됩니다
    
    select *
    from tbl_gogek_1;
    
    insert into tbl_gogek_2(gogekId, email, gogekName, gogekPhone) values('leess','leess@naver.com', '이순신', '010-2345-6789');
    insert into tbl_gogek_2(gogekId, email, gogekName, gogekPhone) values('leess','leess@gmail.com', '이삼순', '010-6789-1234');
    
    select *
    from tbl_gogek_2;
    
    
    insert into tbl_gogek_2(gogekId, email, gogekName, gogekPhone) values('leess','leess@naver.com', '이승삼', '010-7788-5252');
    -- 오류!!
    
    
    
    
    --- >>> Unique Key(후보키, 후보식별자) 제약에 대해서 알아봅니다. <<< ---
    --   Unique Key 로 되어지는 컬럼은 NULL 은 허용한다.
    --   그런데 어떤 테이블에서 후보키(==후보식별자)로 사용되어질 컬럼이라면
    --   반드시 Unique 제약이면서 NOT NULL 이어야 한다.
    
    --   !!! Unique 제약은 하나의 테이블당 여러개를 생성할 수 있다. !!!      
    --   오라클은 어떤 컬럼에 Unique 제약을 주면서 그 컬럼에 null을 허락한다면
    --   이 컬럼에 null 이 여러번 들어와도 괜찮다. 그러나 Unique 제약을 준 컬럼에
    --   null 이 아닌 중복된 값을 넣었을 때는 오류가 발생한다.
    --   (참고로 Microsoft 사의 MS-SQL 서버는 어떤 컬럼에 Unique 제약을 주면서
    --   그 컬럼에 null을 허락한다면 이 컬럼에는 null 이 딱 1번만 들어올 수 있다.
    --   그래서 MS-SQL 서버는 어떤 컬럼에 Unique 제약을 주면 null 도 고유한 것으로 취급을 해서
    --   null은 1번만 들어온다.)
    
    create table tbl_gogek_3
    (gogekId     varchar2(30)
    ,gogekName   varchar2(30) not null
    ,email       varchar2(50) not null-- 후보식별자
    ,gogekPhone  varchar2(30)
    ,constraint PK_tbl_gogek_3_gogekId primary key(gogekId) -- row level(행 수준) 제약조건
    ,constraint UQ_tbl_gogek_3_email unique(email)   -- row level(행 수준) 제약조건
    ,constraint UQ_tbl_gogek_3_gogekPhone unique(gogekPhone)   -- row level(행 수준) 제약조건
    );
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('leess', '이순신', 'leess@naver.com', '010-3456-7890');
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('eomjh', '엄정화', 'leess@naver.com', '010-6789-1234');
    --ORA-00001: 무결성 제약 조건(HR.UQ_TBL_GOGEK_3_EMAIL)에 위배됩니다
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('eomjh', '엄정화', null, '010-6789-1234');
    --SQL 오류: ORA-01400: NULL을 ("HR"."TBL_GOGEK_3"."EMAIL") 안에 삽입할 수 없습니다
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('eomjh', '엄정화', 'eomjh@naver.com', '010-6789-1234');
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('seokj', '서강준', 'seokj@naver.com', '010-6789-1234');
    -- ORA-00001: 무결성 제약 조건(HR.UQ_TBL_GOGEK_3_GOGEKPHONE)에 위배됩니다
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('nagoding', '나고딩', 'nagoding@naver.com', null);
    
    insert into tbl_gogek_3(gogekId, gogekName, email, gogekPhone)
    values('najungding', '나중딩', 'najungding@naver.com', null);
    
    
    commit;
    
    select *
    from tbl_gogek_3;
    
    
    --- *** Composite(복합) Unique Key 를 제약하려면 row level(행수준) unique key 로 해야 합니다.
    --drop table tbl_jumun_2 purge;
    
    create table tbl_jumun_2
    (jumunNo    varchar2(20)
    ,gogekId    varchar2(20)    not null
    ,jepumCode  varchar2(30)    not null
    ,jumunDay   date default sysdate not null
    ,jumunSu    number(10)
    ,constraint PK_tbl_jubun_2_jumunNo primary key(jumunNo)
    ,constraint UQ_tbl_jumun_2_ unique (gogekId, jepumCode, jumunDay)
    );
    
    --drop sequence seq_tbl_jumun_2;
    
    
    create sequence seq_tbl_jumun_2;
    
    insert into tbl_jumun_2(jumunno, gogekid, jepumCode, jumunday, jumunsu)
    values( to_char(sysdate,'yyyymmdd') || '-' || seq_tbl_jumun_2.nextval, 'leess', '새우깡', default, 20);
    
    insert into tbl_jumun_2(jumunno, gogekid, jepumCode, jumunday, jumunsu)
    values( to_char(sysdate,'yyyymmdd') || '-' || seq_tbl_jumun_2.nextval, 'leess', '감자깡', default, 30);
    
    insert into tbl_jumun_2(jumunno, gogekid, jepumCode, jumunday, jumunsu)
    values( to_char(sysdate,'yyyymmdd') || '-' || seq_tbl_jumun_2.nextval, 'eomjh', '감자깡', default, 30);
    
    commit;
    
    select jumunno, gogekid, jepumCode, to_char(jumunday, 'yyyy-mm-dd hh24:mi_ss') jumunday, jumunsu
    from tbl_jumun_2;
    
    insert into tbl_jumun_2(jumunno, gogekid, jepumCode, jumunday, jumunsu)
    values( to_char(sysdate,'yyyymmdd') || '-' || seq_tbl_jumun_2.nextval, 'eomjh', '양파링', default, 40);
    
    insert into tbl_jumun_2(jumunno, gogekid, jepumCode, jumunday, jumunsu)
    values( to_char(sysdate,'yyyymmdd') || '-' || seq_tbl_jumun_2.nextval, 'eomjh', '양파링', default, 40);
    
    /*
        오류 보고 -
        ORA-00001: 무결성 제약 조건(HR.UQ_TBL_JUMUN_2_)에 위배됩니다
        이것은 위의 insert 문 2개 문장을 동시에 블럭을 잡고서 실행할 때 발생하는 오류이다.
    */
    
    
    --- !!!**** 생성되어진 제약조건을 조회해보도록 하겠습니다. ***!!! -----
    
    select *
    from user_constraints;
    
    /*
        CONSTRAINT_NAME = 제약조건명
        CONSTRAINT_TYPE = P(Primary key), U(Unique key), R(Reference key = Foreign key), C(Check), C(Not Null)
    */
    
    select *
    from user_constraints
    where table_name = 'TBL_JUMUN_2';
    
    
    select *
    from user_cons_columns;
    
    /*
        constraint_name = 제약조건명
        column_name = 제약조건이 걸린 컬럼명
    */
    
    
    select A.table_name, A.constraint_name, A.constraint_type, A.search_condition
        , B.column_name, B.position
    from user_constraints A join user_cons_columns B
    on A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_JUMUN_2';
    
    -- 'P', 'U' 타입의 제약조건을 확인
    select A.table_name, A.constraint_name, A.constraint_type, A.search_condition
        , B.column_name, B.position
    from user_constraints A join user_cons_columns B
    on A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_JUMUN_2' and constraint_type in('P', 'U');
    
    
    
    
    --- **** >>> Check 제약에 대해서 알아봅니다. <<< *** ----
    
    create table tbl_sawon
    (sano       number
    ,saname     varchar2(20) not null
    ,salary     number(5) not null      --급여는 커미션보다 커야한다.
    ,commission number(5)               --커미션은 0 이상이어야 한다.
    ,jik        varchar2(20) default '사원' --직급의 종류는 '사장', '부장', '과장', '대리', '사원' 만 가능하다
    ,email      varchar2(50) not null
    ,hire_date  date default sysdate
    ,constraint PK_tbl_sawon_sano primary key(sano)
    ,constraint UQ_tbl_sawon_email unique (email)
    ,constraint CK_tbl_sawon_salary_commission check(salary > commission and commission >= 0)
    ,constraint CK_tbl_sawon_jik check(jik in ('사장', '부장', '과장', '대리', '사원'))
    );
    
    insert into tbl_sawon(sano, saname, salary, commission, jik, email)
    values(1001, '홍길동', 500, 1000, '과장', 'hongkd@gmail.com');
    --ORA-02290: 체크 제약조건(HR.CK_TBL_SAWON_SALARY_COMMISSION)이 위배되었습니다
    
    insert into tbl_sawon(sano, saname, salary, commission, jik, email)
    values(1001, '홍길동', 500, -100, '과장', 'hongkd@gmail.com');
    --ORA-02290: 체크 제약조건(HR.CK_TBL_SAWON_SALARY_COMMISSION)이 위배되었습니다
    
    insert into tbl_sawon(sano, saname, salary, commission, jik, email)
    values(1001, '홍길동', 500, 100, '병장', 'hongkd@gmail.com');
    --ORA-02290: 체크 제약조건(HR.CK_TBL_SAWON_JIK)이 위배되었습니다
    
    insert into tbl_sawon(sano, saname, salary, commission, jik, email)
    values(1001, '홍길동', 500, 100, '과장', 'hongkd@gmail.com');
    
    commit;
    
    select *
    from tbl_sawon;
    
    update tbl_sawon set commission = 500
    where sano = 1001;
    --ORA-02290: 체크 제약조건(HR.CK_TBL_SAWON_SALARY_COMMISSION)이 위배되었습니다
    
    update tbl_sawon set commission = 499
    where sano = 1001;
    
    
    -- TBL_SAWON 테이블의 모든 제약조건을 확인
    select A.table_name, A.constraint_name, A.constraint_type, A.search_condition
        , B.column_name, B.position
    from user_constraints A join user_cons_columns B
    on A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_SAWON';
    
    
    
    
    
    
    
    
    
    
    
    
    ---- **** >>> Foreign Key(외래키, 참조키) 제약에 대해서 알아봅니다. <<< **** ----
    
    -- 먼저 고객들의 정보가 담기는 "TBL_GOGEK" 테이블을 생성해 보겠습니다. --
    create table tbl_gogek
    (gogekId        varchar2(30)
    ,gogekName      varchar2(30) not null
    ,gogekPhone     varchar2(30)
    ,constraint PK_tbl_gogek_gogekId primary key(gogekId)
    );
    
    
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values ('leess', '이순신', '010-2345-6789');
    
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values ('eomjh', '엄정화', '010-3456-7890');
    
    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values ('sunsin', '이순신', '010-4567-8901');

    insert into tbl_gogek(gogekId, gogekName, gogekPhone) values ('kangkc', '강감찬', null);
    
    commit;
    
    desc tbl_gogek;
    /*
        이름         널?       유형           
        ---------- -------- ------------ 
        GOGEKID    NOT NULL VARCHAR2(30) 
        GOGEKNAME  NOT NULL VARCHAR2(30) 
        GOGEKPHONE          VARCHAR2(30)     
    */
    
    -- 고객들의 예약을 받아주는 "TBL_YEYAK" 테이블을 생성해 보겠습니다. --
    
    -- 어떤 한명의 고객은 (예: leess 이순신) 에약을 1번도 안 할수도 있고, 
    -- 예약을 딱 1번만 할 수 있고, 예약을 여러번 할 수도 이따.
    
    create sequence seq_tbl_yeyak
    start with 1
    increment by 1
    nomaxvalue
    nominvalue
    nocycle
    nocache;
    
    create table tbl_yeyak
    (yeyakno        number--> 예약번호.  예약번호의 값은 NOT NULL 이면서 고유한 값만 가져야 한다. 
                          --  그러므로 yeyakno 컬럼에는 Primary Key 제약을 주어야 한다.
                          /*
                              예약번호는 사용자가 수동적으로 입력치 않고 자동적으로 들어와야 한다.
                              그리고 예약번호는 매번 그 숫자가 증가되면서 고유해야 한다.
                              이렇게 되려면 sequence 를 사용하면 된다.
                          */
    ,fk_gogekId     varchar2(30) not null --고객테이블에 있는 고객ID를 참조
                                          --fk_gogekId 컬럼의 들어올 수 있는 값은 tbl_gogek 테이블의 gogekId 컬럼의 값만 들어와야 한다.
                                          --참조를 당하는 컬럼은 (여기서는 tbl_gogek 테이블의 gogekId 컬럼임)
                                          -- 반드시 고유한 값을 가지는 컬럼이어야 하며, NULL 값이어야 한다.
                                          -- 그래서 참조를 당하는 컬럼은 Primary Key 제얄을 가지는 것이든지, 또는 Unique 제약을 가지면서 NOT NULL 인 커럼이어야 한다.
    ,ticketCnt      number(2) not null    -- 예약티켓개수
                                          -- 데이터타입이 number(2)  이므로 -99 ~ 99 갑드리 드러온다
                                          -- 그런데 예약티켓개수는 1번에 예약에 최대 10개 까지만 허락하고자 한다.
                                          -- 즉, ticketCnt 컬럼에 드러오는 갑은 1 ~ 10 까지만 가능하도록 해야 한다.
                                          -- 이러한 경우 값을 검사해야 하므로 Check 제약을 사용하면 된다.
    ,constraint PK_tbl_yeyak_yeyakno primary key(yeyakno)
    ,constraint FK_tbl_yeyak_fk_gogekId foreign key(fk_gogekId) references tbl_gogek(gogekId)
    /*
        tbl_yeyak 테이블의 fk_gogekId 컬럼에는 foreign key 제약을 만들었는데
        그 뜻은 tbl_yeyak 테이블의 fk_gogekId 컬럼에 입력되거나 수정되어지는 값은 반드시 tbl_gogek 테이블의 gogekId 컬럼에 존재하는 값들만 가능하다는 말이다.
        즉, tbl_gogek 테이블의 gogekId 컬럼에 존재하지 않는 값은 tbl_yeyak 테이블의 fk_gogekId 컬러멩 들어올 수 없다.
        그리고 중요한 것은 tbl_gogek 테이블의 gogekID 컬럼은 식별자 컬럼(Primary Key 또는 NOT NULL 이면서 Unique Key)
    */
    
    ,constraint CK_tbl_yeyak_ticktCnt check (ticketCnt > 0 and ticketCnt <= 10)
    
    
    );

    
    insert into tbl_yeyak(yeyakno, fk_gogekId, ticketCnt) values(seq_tbl_yeyak.nextval, 'leess', 5);
    
    insert into tbl_yeyak(yeyakno, fk_gogekId, ticketCnt) values(seq_tbl_yeyak.nextval, 'superman', 5);
    --ORA-02291: 무결성 제약조건(HR.FK_TBL_YEYAK_FK_GOGEKID)이 위배되었습니다- 부모 키가 없습니다
    
    insert into tbl_yeyak(yeyakno, fk_gogekId, ticketCnt) values(seq_tbl_yeyak.nextval, 'eomjh', 3);
    
    insert into tbl_yeyak(yeyakno, fk_gogekId, ticketCnt) values(seq_tbl_yeyak.nextval, 'eomjh', 8);
    
    insert into tbl_yeyak(yeyakno, fk_gogekId, ticketCnt) values(seq_tbl_yeyak.nextval, 'eomjh', 20);
    --ORA-02290: 체크 제약조건(HR.CK_TBL_YEYAK_TICKTCNT)이 위배되었습니다
    
    insert into tbl_yeyak(yeyakno, fk_gogekId, ticketCnt) values(seq_tbl_yeyak.nextval, 'sunsin', 5);
    
    commit;
    
    select *
    from tbl_yeyak;
    
    select *
    from tbl_gogek;
    
    --- *** foreign key 제약이 있는 테이블을 "자식(child)" 테이블 이라고 부르고,
    ---     foreign key 에 의해 참조를 당하는 테이블을 "부모(parent)" 테이블 이라고 부른다
    
    select *
    from tbl_gogek; -- "부모(parent)" 테이블
    
    select *
    from tbl_yeyak; -- "자식(child)" 테이블
    
    
    예약번호        --tbl_yeyak
    고객아이디      --tbl_yeyak
    고객명         --tbl_yeyak
    연락처         --tbl_gogek
    티켓수         --tbl_yeyak
    
    
    select y.yeyakno 예약번호, g.gogekid 고객아이디, g.gogekname 고객명, g.gogekphone 연락처, y.ticketcnt 티켓수
    from tbl_yeyak y join tbl_gogek g
    on y.fk_gogekId = g.gogekId
    order by 예약번호 asc;
    
    
    select *
    from departments;   -- 부모테이블, department_id 컬럼이 P.K
    
    select *
    from employees      -- 자식테이블, fk_department_id 컬럼이 F.K
    order by department_id asc;
    
    update employees set department_id = 110
    where employee_id = 100;
    
    update employees set department_id = 500
    where employee_id = 100;
    -- 오류
    
    rollback;
    
    select *        --> R_CONSTRAINT_NAME 값이 참조를 당하는 부모테이블의 식별자의 제약조건명이다.
    from user_constraints
    where table_name = 'TBL_YEYAK';
    
    -- TBL_YEYAK 테이브레 생성되어진 foreign key 제약조건을 조회합니다. ** --
    select A.table_name, A.constraint_name, A.constraint_type, A.search_condition
        , B.column_name, B.position
        , A.r_constraint_name as "참조 받는 부모테이블의 식별자 제약조건명"
    from user_constraints A join user_cons_columns B
    on A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_YEYAK' and A.constraint_type = 'R';
    
    select *
    from user_constraints;
    
    select *
    from user_cons_columns
    where table_name = 'TBL_YEYAK';
    
    
    --!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--
    -- tbl_yeyak 테이블에 존재하는 foreign key 컬럼명과 부모테이블명과 참조를 당하는 Primary key(unique key)에 해당하는 컬럼명을 조회하도록 한다. ---
    
    -------------------------------------------------
    --"외래키컬럼명"   "부모테이블명"   "참조를당하는컬럼명"
    -------------------------------------------------
    --FK_GOGEKID    TBL_GOGEK       GOGEKID
    -------------------------------------------------
    ---------------- 내 방식 -------------------
    select B.column_name
        , C.table_name
        , C.column_name
    from user_constraints A join user_cons_columns B
    on A.constraint_name = B.constraint_name
    join user_cons_columns C
    on A.r_constraint_name = C.constraint_name
    where A.table_name = 'TBL_YEYAK' and A.constraint_type = 'R';
    
    --------------- 선생님 방식--------------------
    select *
   from user_cons_columns
   where constraint_name = 'PK_TBL_GOGEK_GOGEKID';
   
   
   SELECT C.column_name AS "외래키컬럼명"
        , D.table_name AS "부모테이블명"
        , D.column_name AS "참조를당하는컬럼명"
   FROM 
   (
       SELECT B.column_name 
            , A.r_constraint_name 
       FROM user_constraints A JOIN user_cons_columns B
       ON A.constraint_name = B.constraint_name 
       WHERE A.table_name = 'TBL_YEYAK' AND A.constraint_type = 'R'
   ) C JOIN user_cons_columns D
   ON C.r_constraint_name = D.constraint_name;
   
   /*
       ------------------------------------------------
         외래키컬럼명     부모테이블명    참조를당하는컬럼
       ------------------------------------------------ 
         FK_GOGEKID     TBL_GOGEK     GOGEKID
       ------------------------------------------------  
   */
   
   
   
    select B.column_name
        , B.table_name
        , C.table_name
        , C.column_name
    from user_constraints A join user_cons_columns B
    on A.constraint_name = B.constraint_name
    join user_cons_columns C
    on A.r_constraint_name = C.constraint_name
    where A.table_name = 'EMPLOYEES' and A.constraint_type = 'R';
    
    /*
        COLUMN_NAME 	TABLE_NAME	    COLUMN_NAME_1
        —————————————————————————————
        DEPARTMENT_ID	DEPARTMENTS	    DEPARTMENT_ID
        JOB_ID	        JOBS	        JOB_ID
        MANAGER_ID	    EMPLOYEES	    EMPLOYEE_ID
    */
    
    
    
    -- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --
    -- "자식" 테이블(여기서는 tbl_yeyak 테이블)에 입력되어진 데이터가
    
    -- "부모" 테이블(여기서는 tbl_gogek 테이블)에 존재하는 경우에
    -- "부모" 테이블의 행을 삭제할 때 어떻게 되어지는지 알아봅니다.
    
    
    
    --"자식" 테이블 tbl_yeyak 테이블의 fk_gogekid 컬럼에는 'kangkc' 은 존재한다.
    
    select *
    from tbl_gogek; -- "부모(parent)" 테이블
    
    select *
    from tbl_yeyak; -- "자식(child)" 테이블
    
    delete from tbl_gogek
    where gogekid = 'kangkc';
    
    --"자식" 테이블 tbl_yeyak 테이블의 fk_gogekid 컬럼에는 'eomjh' 은 존재한다.
    
    delete from tbl_gogek
    where gogekid = 'eomjh';
    --ORA-02292: 무결성 제약조건(HR.FK_TBL_YEYAK_FK_GOGEKID)이 위배되었습니다- 자식 레코드가 발견되었습니다
    --레코드(record)란? row(행)을 말하는 것이다.
    
    --!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    -- "부모" 테이블에 존재하는 행을 삭제하고자 할 때
    -- "자식" 테이블에 foreign key 컬럼에 삭제하려는 행의 값이 존재한다면
    -- 위와 같이 오류무결성 제약조건(foreign key 제약)에 의해 삭제가 안된다.!!! 
    
    
    -- "tbl_gogek" 테이블에서 회원탈퇴라는 것은 더 이상 로그인이 안되면 되는 것이다.
    -- 그래서 로그인 처리를 하기 위해서 "tbl_gogek" 테이블에 passwd 라는 컬럼을 추가하겠다
    
    alter table tbl_gogek
    add passwd varchar2(20);
    
    --!!!! 또한 "회원탈퇴" 처리를 위해서 status 라는 컬럼을 추가하겠다.
    alter table tbl_gogek
    add status number(1) default 1;
    
    
    -- tbl_gogek 테이블에 status 라는 컬럼에는 오로지 0 또는 1 값만 들어오도록 체크제약을 만든다
    -- status 라는 컬럼의 값이 1이면 정상 활동중으로 보고, status 라는 컬럼의 값이 0 이면 탈퇴로 간주한다.
    
    
    alter table tbl_gogek
    add constraint CK_tbl_gogek_status check(status in (0, 1));
    
    update tbl_gogek set passwd = 'qwer1234$';
    
    commit;
    
    select count(*) -- 1은 정상로그인
    from tbl_gogek
    where status = 1 and gogekid = 'eomjh' and passwd = 'qwer1234$';
    
    select count(*) -- 0은 로그인 실패
    from tbl_gogek
    where status = 1 and gogekid = 'yeonkyuyeong' and passwd = 'qwer1234$';
    
    
    select count(*) -- 0은 로그인 실패
    from tbl_gogek
    where status = 1 and gogekid = 'eomjh' and passwd = 'jfjweff';
    
    -- "회원탈퇴" 처리
    delete from tbl_gogek
    where gogekid = 'eomjh';
    --ORA-02292: 무결성 제약조건(HR.FK_TBL_YEYAK_FK_GOGEKID)이 위배되었습니다- 자식 레코드가 발견되었습니다
    --레코드(record)란? row(행)을 말하는 것이다.
    
    
    update tbl_gogek set status = 0 -- 0을 "회원탈퇴"로 본다.!!!
    where status = 1 and gogekid = 'eomjh';
    
    commit;
    
    select count(*) -- 로그인 실패
    from tbl_gogek
    where status = 1 and gogekid = 'eomjh' and passwd = 'qwer1234$';
    
    
    
    
    -----------------------------------------------------------------------------------------------------
    /*
        게시판에서 원글이 있고, 원글에 딸린 댓글이 있어
        댓글에 원글이 존재할때만 댓글이 있는 거야
        그러면 "원글" 테이블과 "댓글" 테이블은 부모-자식 관계를 이룰 것이다.
        이러한 경우 "원글" 테이블에 어떤 한 행이 삭제가 되어지면 그 원글에 딸린 모든 댓글 또한 삭제가 되어져야 할 것이다.
        이럴 때 자식테이블에 해당하는 "댓글" 테이블에는 on delete cascade 가 있는 foreign key 로 생성해야 한다.
    */
    
    create table tbl_original_board     -- "원글" 테이블, 부모 테이블 
    (boardno     number                 -- 원글번호
    ,subject     Nvarchar2(50) not null -- 글제목 
    ,constraint  PK_tbl_original_board_boardno primary key(boardno)
    );
    
    create table tbl_comment     -- "댓글" 테이블, 자식 테이블 
    (commentno   number          -- 댓글번호
    ,contents    Nvarchar2(100)  -- 댓글내용
    ,fk_boardno  number          -- 원글번호
    ,constraint  PK_tbl_comment_commentno  primary key(commentno)
    ,constraint  FK_tbl_comment_fk_boardno foreign key(fk_boardno) references tbl_original_board(boardno) on delete cascade
    -- tbl_comment 테이블의 "부모" 테이블인 tbl_original_board 에서 어떤 한행을 삭제할 경우 
    -- 먼저, 테이블인 tbl_comment 테이블에서 삭제하려는 "부모" 테이블의 그 행을 참조하는 자식행(자식레코드)들을 먼저 delete 한 이후에
    -- 그 다음에 "부모" 테이블인 tbl_original_board 에서 어떤 한 행을 삭제한다.
    );
    
    
    insert into tbl_original_board(boardno, subject) values(1, '첫번째 원글입니다.');
    insert into tbl_original_board(boardno, subject) values(2, '두번째 원글입니다.');
    insert into tbl_original_board(boardno, subject) values(3, '세번째 원글입니다.');
    
    commit;
    
    select *
    from tbl_original_board;  -- 원글 테이블 
    
    insert into tbl_comment(commentno, contents, fk_boardno)
    values(101, '좋은 글 이네요', 1);
    
    insert into tbl_comment(commentno, contents, fk_boardno)
    values(102, '공감입니다.', 1);
    
    insert into tbl_comment(commentno, contents, fk_boardno)
    values(103, '감동입니다', 1);
    
    
    commit;
    
    insert into tbl_comment(commentno, contents, fk_boardno)
    values(104, '좋은 하루되세요~', 2);
    
    commit;
    
    select *
    from tbl_comment; -- 댓글 테이블
    
    select *
    from tbl_original_board;  -- 원글 테이블
    
    -- "부모" 테이블인 tbl_original_board 테이블에서 자식레코드가 존재하는 행을 삭제하겠습니다.
    
    delete from tbl_original_board
    where boardno = 1;  --자식 테이블 지운 후 에 부모 테이블을 지움(여기서는 댓글을 먼저 다 지운 후 원글을 지운다)
    
    rollback;
    
        /*
            == foreign key 생성시 on delete cascade 를 안주어야 할 경우 ==
            
            "부서" 테이블 ==> 부서번호(P.K)  부서명   부서장사원번호
                                10       마케팅    1001
                                20       영업부    2001  --> "사원" 테이블에서 부서번호 20 인 사원들을 모두 10 으로 변경(update)한 후 delete 하면 된다.
                            
            "사원" 테이블 ==> 사원번호(P.K)  사원명   직급명  부서번호(F.K) 
                               1001      나기획   부장      10
                               1002      김길동   과장      10
                               2001      나영업   부장      20 --> 10
                               2002      나세일   과장      20 --> 10
                               2003      너판매   사원      20 --> 10 
            
            update "사원" set 부서번호 = 10 
            where 부서번호 = 20;
            
            delete from "부서" 
            where 부서번호 = 20;
        */
    
    /*
        게시판에서 원글이 있고, 원글에 딸린 댓글이 있어
        댓글에 원글이 존재할때만 댓글이 있는 거야
        그러면 "원글" 테이블과 "댓글" 테이블은 부모-자식 관계를 이룰 것이다.
        이러한 경우 "원글" 테이블에 어떤 한 행이 삭제가 되어지기 위해서 그 원글행에 달려있는 모든 댓글은 부모-자식 관계를 끊기 위해서 foreign key 컬럼에 해당하는 값을 null로 변경시킨다.
        그런 다음에 "원글" 테이블에 어떤 한행을 삭제한다.
        이럴 때 자식테이블에 해당하는 "댓글" 테이블에는 on delete set null 이 있는 foreign key 로 생성해야 한다.
        이때 주의해야할 사항은 자식테이블의 foreign key에 해당하는 컬럼은 반드시 null 을 허락해야 한다.!!!
    */
    
    create table tbl_original_board_2     -- "원글" 테이블, 부모 테이블 
    (boardno     number                 -- 원글번호
    ,subject     Nvarchar2(50) not null -- 글제목 
    ,constraint  PK_tbl_original_board_2_boardno primary key(boardno)
    );
    
    
    create table tbl_comment_2     -- "댓글" 테이블, 자식 테이블 
    (commentno   number          -- 댓글번호
    ,contents    Nvarchar2(100)  -- 댓글내용
    ,fk_boardno  number          -- 원글번호
    ,constraint  PK_tbl_comment_2_commentno  primary key(commentno)
    ,constraint  FK_tbl_comment_2_fk_boardno foreign key(fk_boardno) references tbl_original_board_2(boardno) on delete set null
    -- tbl_comment_2 테이블의 "부모" 테이블인 tbl_original_board_2 에서 어떤 한행을 삭제할 경우 
    -- 먼저, 테이블인 tbl_comment_2 테이블에서 삭제하려는 "부모" 테이블의 그 행을 참조하는 자식행(자식레코드)들의 fereign key 컬럼인 fk_boardno 값을 먼저 null로 update한 이후에
    -- 그 다음에 "부모" 테이블인 tbl_original_board_2 에서 어떤 한 행을 삭제한다.
    -- foreign kery 에 해당하는 컬럼(fk_boardno)은 반드시 null을 허락해줘야 한다.
    );
    
     
    insert into tbl_original_board_2(boardno, subject) values(1, '첫번째 원글입니다.');
    insert into tbl_original_board_2(boardno, subject) values(2, '두번째 원글입니다.');
    insert into tbl_original_board_2(boardno, subject) values(3, '세번째 원글입니다.');
    
    commit;
    
    select *
    from tbl_original_board;  -- 원글 테이블 
    
    insert into tbl_comment_2(commentno, contents, fk_boardno)
    values(101, '좋은 글 이네요', 1);
    
    insert into tbl_comment_2(commentno, contents, fk_boardno)
    values(102, '공감입니다.', 1);
    
    insert into tbl_comment_2(commentno, contents, fk_boardno)
    values(103, '감동입니다', 1);
    
    
    commit;
    
    insert into tbl_comment_2(commentno, contents, fk_boardno)
    values(104, '좋은 하루되세요~', 2);
    
    commit;
    
    select *
    from tbl_comment_2; -- 댓글 테이블
    
    select *
    from tbl_original_board_2;  -- 원글 테이블
    
    -- "부모" 테이블인 tbl_original_board_2 테이블에서 자식레코드가 존재하는 행을 삭제하겠습니다.
    
    delete from tbl_original_board_2
    where boardno = 1;  --자식 테이블 지운 후 에 부모 테이블을 지움(여기서는 댓글을 먼저 다 지운 후 원글을 지운다)
    
    rollback;
    
    -- >> !!! on delete set null 이 사용되어져야 하는 예 !!! << ---
    create table tbl_emp
    (empno    number(4)     
    ,ename    nvarchar2(10) not null
    ,jik      nvarchar2(10) not null 
    ,mgr_no   number(4)    
    ,constraint  PK_tbl_emp_empno primary key(empno)
    ,constraint  FK_tbl_emp_mgr_no foreign key(mgr_no) references tbl_emp(empno)
    );
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1001, '나사장', '사장', null);
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1002, '나부장', '부장', 1001);
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1003, '나과장', '사장', 2001);
    --ORA-02291: 무결성 제약조건(HR.FK_TBL_EMP_MGR_NO)이 위배되었습니다- 부모 키가 없습니다
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1003, '나과장', '사장', 1002);
    
    
    commit;
    
    select *
    from tbl_emp;
    
    delete from tbl_emp
    where empno = 1002;
    
    drop table tbl_emp purge;
    
    create table tbl_emp
    (empno    number(4)     
    ,ename    nvarchar2(10) not null
    ,jik      nvarchar2(10) not null 
    ,mgr_no   number(4)    
    ,constraint  PK_tbl_emp_empno primary key(empno)
    ,constraint  FK_tbl_emp_mgr_no foreign key(mgr_no) references tbl_emp(empno) on delete set null
    );
    
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1001, '나사장', '사장', null);
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1002, '나부장', '부장', 1001);
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_emp(empno, ename, jik, mgr_no)
    values(1003, '나과장', '과장', 1002);
    
    
    commit;
    
    
    
    delete from tbl_emp
    where empno = 1002;
    
    select *
    from tbl_emp;
    
    select *
    from user_constraints
    where table_name = 'EMPLOYEES';
    
    
    
    
    --- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ---   
  --- employees 테이블에 존재하는 foreign key 컬럼명과 부모테이블명과 참조를 당하는 primary key(unique key)에 해당하는 컬럼명을 조회해보세요. ---
  
  SELECT C.column_name AS "외래키컬럼명"
       , D.table_name AS "부모테이블명"
       , D.column_name AS "참조를당하는컬럼명"
       , C.delete_rule AS "CASCADE옵션"
  FROM 
  (
      select B.column_name, A.r_constraint_name, A.delete_rule 
      from user_constraints A JOIN user_cons_columns B
      ON A.constraint_name = B.constraint_name
      where A.table_name = 'EMPLOYEES' and A.constraint_type = 'R'
  ) C JOIN user_cons_columns D
  ON C.r_constraint_name = D.constraint_name;
  /*
     -----------------------------------------------------------------
      외래키컬럼명       부모테이블명     참조를당하는컬럼명    CASCADE옵션 
     -----------------------------------------------------------------
      DEPARTMENT_ID   DEPARTMENTS    DEPARTMENT_ID      NO ACTION
      JOB_ID          JOBS           JOB_ID             NO ACTION 
      MANAGER_ID      EMPLOYEES      EMPLOYEE_ID        NO ACTION
  */
    
    SELECT C.column_name AS "외래키컬럼명"
       , D.table_name AS "부모테이블명"
       , D.column_name AS "참조를당하는컬럼명"
       , C.delete_rule AS "CASCADE옵션"
    FROM 
    (
      select B.column_name, A.r_constraint_name, A.delete_rule 
      from user_constraints A JOIN user_cons_columns B
      ON A.constraint_name = B.constraint_name
      where A.table_name = 'TBL_COMMENT' and A.constraint_type = 'R'
    ) C JOIN user_cons_columns D
    ON C.r_constraint_name = D.constraint_name;
    
    /*  
        -------------------------------------------------------------------
          외래키컬럼명       부모테이블명      참조를당하는컬럼명    CASCADE옵션 
        -------------------------------------------------------------------
          FK_BOARDNO   TBL_ORIGINAL_BOARD   BOARDNO              CASCADE
    */     
    
    
    SELECT C.column_name AS "외래키컬럼명"
       , D.table_name AS "부모테이블명"
       , D.column_name AS "참조를당하는컬럼명"
       , C.delete_rule AS "CASCADE옵션"
    FROM 
    (
      select B.column_name, A.r_constraint_name, A.delete_rule 
      from user_constraints A JOIN user_cons_columns B
      ON A.constraint_name = B.constraint_name
      where A.table_name = 'TBL_EMP' and A.constraint_type = 'R'
    ) C JOIN user_cons_columns D
    ON C.r_constraint_name = D.constraint_name;
    
     /*  
        -------------------------------------------------------------------
          외래키컬럼명       부모테이블명      참조를당하는컬럼명    CASCADE옵션 
        -------------------------------------------------------------------
          MGR_NO	       TBL_EMP	       EMPNO        	SET NULL
    */     
    
    
    select *
    from user_cons_columns
    where table_name = 'EMPLOYEES';
    
    select B.column_name, B.position,
         A.constraint_name, A.constraint_type, A.search_condition, A.r_constraint_name 
    from user_constraints A JOIN user_cons_columns B
    ON A.constraint_name = B.constraint_name
    where A.table_name = 'EMPLOYEES';
    
    
    --------- **** 어떤 테이블에 제약조건을 추가하기 **** -----------
  /*
       제약조건 추가시 NOT NULL 제약을 제외한 나머지 4개는 아래와 같이한다.
       
       alter table 테이블명 add constraint 제약조건명 primary key(컬럼명);
       alter table 테이블명 add constraint 제약조건명 unique(컬럼명);
       alter table 테이블명 add constraint 제약조건명 check( ... );
       
       alter table 테이블명 add constraint 제약조건명 foreign key(컬럼명) references 부모테이블명(식별자컬럼명);
       alter table 테이블명 add constraint 제약조건명 foreign key(컬럼명) references 부모테이블명(식별자컬럼명) on delete cascade;
       alter table 테이블명 add constraint 제약조건명 foreign key(컬럼명) references 부모테이블명(식별자컬럼명) on delete set null;
 */
 
 /*
       NOT NULL 제약을 추가할 때는 아래와 같이 한다.
       
       alter table 테이블명 modify 컬럼명 [constraint 제약조건명] not null;
 */
    
    
    create table tbl_employees_backup_20241007
    as
    select *
    from employees;
    
    -- !!!! 서브쿼리를 사용한 테이블 생성은 원래 테이블에 있던 NOT NULL 제약만 
    --      새로운 이름(SYS_C뭐뭐뭐~~)으로 생성되어지고, 나머지 제약은 생성되어지지 않는다. !!!!
    
    select *
    from tbl_employees_backup_20241007;
    
    select B.column_name, B.position,
         A.constraint_name, A.constraint_type, A.search_condition, A.r_constraint_name 
    from user_constraints A JOIN user_cons_columns B
    ON A.constraint_name = B.constraint_name
    where A.table_name = 'EMPLOYEES';
    
    select B.column_name, B.position,
         A.constraint_name, A.constraint_type, A.search_condition, A.r_constraint_name 
    from user_constraints A JOIN user_cons_columns B
    ON A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_EMPLOYEES_BACKUP_20241007';
    -- not null 만 추가됨
    
    
    --- 1. primary key 추가하기
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_EMP_BACKUP_20241007_ID_PK primary key(EMPLOYEE_ID);
    /*
        !!! 주의사항 !!!
        만약에 사용하고자 하는 오라클 DB서버의 버전이 11g 이라면 
        테이블명 또는 컬럼명 또는 제약조건명 등등 최대길이가 30글자 이므로 30글자가 넘으면 오류이다.!!!
    */
    --- 2. unique key 추가하기
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_EMAIL_UK unique(EMAIL);
    
    --- 3. check 제약 추가하기
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_SALARY_MIN check( salary > 0 );
    
    --- 4. foreign key 추가하기
    SELECT C.column_name AS "외래키컬럼명"
       , D.table_name AS "부모테이블명"
       , D.column_name AS "참조를당하는컬럼명"
       , C.delete_rule AS "CASCADE옵션"
    FROM 
    (
      select B.column_name, A.r_constraint_name, A.delete_rule 
      from user_constraints A JOIN user_cons_columns B
      ON A.constraint_name = B.constraint_name
      where A.table_name = 'EMPLOYEES' and A.constraint_type = 'R'
    ) C JOIN user_cons_columns D
    ON C.r_constraint_name = D.constraint_name;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_DEPT_FK foreign key(DEPARTMENT_ID) references DEPARTMENTS(DEPARTMENT_ID);
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_JOB_FK foreign key(JOB_ID) references JOBS(JOB_ID);
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_MANAGER_FK foreign key(MANAGER_ID) references EMPLOYEES(EMPLOYEE_ID);
    
    
    
    
    --- **** >>> 제약조건명 이름 변경하기 <<< **** ----
    /*
        alter table 테이블명
        rename constraint 현재제약조건이름 to 새로운제약조건이름;
    */
    alter table TBL_EMPLOYEES_BACKUP_20241007
    rename constraint SYS_C007410 to EMP_BACKUP_20241007_LAST_NAME_NN;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007
    rename constraint SYS_C007411 to EMP_BACKUP_20241007_EMAIL_NN;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007
    rename constraint SYS_C007412 to EMP_BACKUP_20241007_HIRE_DATE_NN;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007
    rename constraint SYS_C007413 to EMP_BACKUP_20241007_JOB_NN;
    
    
    --- 5. not null 제약 추가하기
    --- *** >>> salary 컬럼에 not null 제약을 추가하겠다 <<< *** ---
    desc tbl_employees_backup_20241007;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_EMP_BACKUP_20241007_SALARY_NN salary not null;
    -- 문법오류!!!
    
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 modify salary constraint EMP_EMP_BACKUP_20241007_SALARY_NN not null;
    
    
    
    
    select B.column_name, B.position,
         A.constraint_name, A.constraint_type, A.search_condition, A.r_constraint_name 
    from user_constraints A JOIN user_cons_columns B
    ON A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_EMPLOYEES_BACKUP_20241007';
    
    
    --- *** >>> 어떤 테이블에 제약조건을 삭제하기 <<<< **** ---
    /*
      alter table 테이블명 drop constraint 제약조건명;
        
      그런데 NOT NULL 제약은 위의 것처럼 해도 되고, 또는 아래처럼 해도 된다.
      alter table 테이블명 modify 컬럼명 null;
        
      어떤 테이블에 primary key 제약조건을 삭제할 경우에는 위의 것처럼 해도 되고, 또는 아래처럼 해도 된다.
      alter table 테이블명 drop primary key;
 */ 
    --SALARY		EMP_BACKUP_20241007_SALARY_MIN	C	 salary > 0 	
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_SALARY_MIN;
    
    -- 외래키 삭제
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_DEPT_FK;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_JOB_FK;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_MANAGER_FK;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_EMAIL_UK;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_EMP_BACKUP_20241007_ID_PK;
    -- 또는 Primary Key 이므로 아래처럼 해도 된다.
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop primary key;

    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_EMAIL_NN;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 modify HIRE_DATE null;
    
    
    
    
    -- *** 어떤 테이블에 생성되어진 제약조건의 내용을 변경하기 *** ---
    --     기존 제약조건을 삭제하고서 내용이 변경되어진 제약조건을 새로이 추가하는 것이다.
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_SALARY_MIN check( salary > 0 );
    
    select B.column_name, B.position,
    A.constraint_name, A.constraint_type, A.search_condition, A.r_constraint_name 
    from user_constraints A JOIN user_cons_columns B
    ON A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_EMPLOYEES_BACKUP_20241007';

    alter table TBL_EMPLOYEES_BACKUP_20241007 drop constraint EMP_BACKUP_20241007_SALARY_MIN;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 add constraint EMP_BACKUP_20241007_SALARY_MIN check( salary >= 100 );

    -- *** >>> 어떤 테이블에 존재하는 제약조건을 비활성화 시키기 <<< *** ---
    select B.column_name, B.position,
            A.constraint_name, A.constraint_type, A.search_condition, A.r_constraint_name
            ,A.status
    from user_constraints A JOIN user_cons_columns B
    ON A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_EMPLOYEES_BACKUP_20241007';
    
    /*
        alter table 테이블명 disable constraint 제약조건명;
    */
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 disable constraint EMP_BACKUP_20241007_SALARY_MIN;
    
    --- *** >>> 어떤 테이블에 존재하는 제약조건을 활성화 시키기 <<< *** ---
    /*
        alter table 테이블명 enable constraint 제약조건명;
    */
    alter table TBL_EMPLOYEES_BACKUP_20241007 enable constraint EMP_BACKUP_20241007_SALARY_MIN;
    
    
    
    
    --- *** 어떤 테이블에 새로운 컬럼 추가하기 *** ---
    
    /*
        alter table 테이블명 add 추가할 컬럼명 데이터타입;
    */
    
    select *
    from TBL_EMPLOYEES_BACKUP_20241007;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 add school nvarchar2(10);
    
    desc TBL_EMPLOYEES_BACKUP_20241007; 
    
    
    --- *** 어떤 테이블에 존재하는 컬럼을 삭제하기 *** ---
    /*
        alter table 테이블명 drop column 삭제할컬럼명;
    */
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 drop column school;
    
    --- *** 어떤 테이블에 새로운 컬럼을 추가하는데 NOT NULL 이어야 한다. *** ---
    
    /*
        alter table 테이블명 add 추가할 컬럼명 데이터타입 not null; <-- 테이블명에 insert 되어진 행이 없을 경우에만 가능함
    */
    alter table TBL_EMPLOYEES_BACKUP_20241007 add school nvarchar2(10) not null;
    --ORA-01758: 테이블은 필수 열을 추가하기 위해 (NOT NULL) 비어 있어야 합니다.


    /*
        alter table 테이블명 add 추가할 컬럼명 데이터타입 default 기본값 not null; <-- 테이블명에 insert 되어진 행이 있는 경우에 사용
    */
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 add school nvarchar2(10) default ' ' not null;
    
    
    
    desc TBL_EMPLOYEES_BACKUP_20241007; 
    
    select *
    from TBL_EMPLOYEES_BACKUP_20241007;
    
    -- *** 어떤 테이블에 존재하는 default 값 알아보기 *** --
    
    select column_name, data_type, data_length, nullable, data_default
    from user_tab_columns
    where table_name = 'TBL_EMPLOYEES_BACKUP_20241007';
    
    
    ---- **** 어떤 테이블의 어떤 컬럼에 default 값 넣어주기 *** ----
    /*
        alter table 테이블명 modify 컬럼명 default 디폴트값;
    */
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 modify HIRE_DATE default sysdate;
    
    
    ---- **** 어떤 테이블의 어떤 컬럼에 default 값 삭제하기 *** ----
    /*
        alter table 테이블명 modify 컬럼명 default null;
    */
    
    
    alter table TBL_EMPLOYEES_BACKUP_20241007 modify HIRE_DATE default null;
    -- null 이렇게 되는데 (null)이랑 같아서 몬다이나이
    
    
    
    ---- **** 어떤 테이블에 존재하는 컬럼명을 변경하기 **** ----
    /*
        alter table 테이블명
        rename column 현재컬럼명 to 새로이변경할 컬럼명;
    */
    desc TBL_EMPLOYEES_BACKUP_20241007; 
    
    select *
    from TBL_EMPLOYEES_BACKUP_20241007;
    
    
    alter table TBL_EMPLOYEES_BACKUP_20241007
    rename column salary to pay;
    
    alter table TBL_EMPLOYEES_BACKUP_20241007
    rename column pay to salary;
    
    --- *** 어떤 테이블에 존재하는 데이터타입 변경하기 *** ---
    /*
        alter table 테이블명
        modify 컬럼명 새로운데이터타입;
    */
    
    
    desc TBL_EMPLOYEES_BACKUP_20241007;
    -- salary number(8,2) -> (10,2) 
    alter table TBL_EMPLOYEES_BACKUP_20241007
    modify salary number(10,2);
    
    --- **** 어떤 테이블의 테이블명 변경하기 **** ----
    /*
        rename 현재테이블명 to 새로운테이블명;
    */
    
    rename TBL_EMPLOYEES_BACKUP_20241007 to TBL_EMPLOYEES_COPY;
    
    select *
    from TBL_EMPLOYEES_COPY;
    
    
    -- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --
    -- 테이블을 생성한 이후에 웬만하면 테이블 명에 대한 주석문을 꼭 달아주도록 하자!!!!
    
    --- *** 테이블명에 달려진 주석문 조회하기 *** ---
    
    
    select *
    from user_tab_comments;
    
    
    
    create table tbl_jikwon
    (sano    number
    ,saname  Nvarchar2(10) not null
    ,salary  number(6) default 100 not null
    ,comm    number(5)
    ,constraint PK_tbl_jikwon_sano primary key(sano)
    );
    
    comment on table tbl_jikwon
    is '우리회사 사원들의 정보가 들어있는 테이블';
    
    -- !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! --
    -- 테이블을 생성한 이후에 웬만하면 컬럼 명에 대한 주석문을 꼭 달아주도록 하자!!!!
    
    select *
    from user_col_comments
    where table_name = 'TBL_JIKWON';
    
    
    comment on column tbl_jikwon.sano
    is '사원번호 Primary Key';
    
    comment on column tbl_jikwon.saname is '사원명'; -- Comment이(가) 생성되었습니다.
    comment on column tbl_jikwon.salary is '기본급여 기본값은 100'; -- Comment이(가) 생성되었습니다.
    comment on column tbl_jikwon.comm is '수당 null 허락함'; -- Comment이(가) 생성되었습니다.
    
    
    select column_name, comments
    from user_col_comments
    where table_name = 'TBL_JIKWON';


    --- **** 자식테이블이 있는 부모테이블을 drop 하기 **** -----
    create table tbl_buseo_parent
    (buno     number(2)
    ,buname   varchar2(20)
    ,constraint PK_tbl_buseo_parent primary key(buno)
    );
    
    create table tbl_sawon_child
    (sano    number(4)
    ,saname  varchar2(20)
    ,fk_buno number(2)
    ,constraint PK_tbl_sawon_child_sano primary key(sano)
    ,constraint FK_tbl_sawon_child_fk_buno foreign key(fk_buno) references tbl_buseo_parent(buno)
    );
    
    select *
    from user_constraints
    where table_name = 'TBL_SAWON_CHILD';
    /*
        PK_TBL_SAWON_CHILD_SANO	    P
        FK_TBL_SAWON_CHILD_FK_BUNO	R
    */
    
    drop table tbl_buseo_parent purge;
    --ORA-02449: 외래 키에 의해 참조되는 고유/기본 키가 테이블에 있습니다
    
    drop table tbl_buseo_parent cascade constraint purge;
    /*
     tbl_buseo_parent 테이블을 참조하고 있는 모든 자식테이블(지금은 tbl_sawon_child)에 존재하는
     foreign key 중에서 tbl_buseo_parent 테이블을 참조하고 있는 foreign key 만 먼저 삭제한다.
     그런 다음에는 부모자식 관계가 없으므로 tbl_buseo_parent 테이블을 drop purge 한다.
    */
    
    select *
    from user_constraints
    where table_name = 'TBL_SAWON_CHILD';
    
    
    
    
    
    -----------------------------------------------------------------------------------------
    ---- *** >>> drop 되어진 테이블 복구하기 <<< *** -----
    
    --flashback drop ==> drop 되어진 테이블을 복구가 가능하도록 만들어 주는 것이다.
    
    ---- !!! 테이블을 삭제시 휴지통에 버리기 !!!! ----
    
    create table tbl_exam_01
    (name varchar2(20));
    
    insert into tbl_exam_01(name) values('연습1');
    commit;
    
    create table tbl_exam_02
    (name varchar2(20));
    
    insert into tbl_exam_02(name) values('연습1');
    commit;
    
    create table tbl_exam_03
    (name varchar2(20));
    
    insert into tbl_exam_03(name) values('연습1');
    commit;
    
    create table tbl_exam_04
    (name varchar2(20));
    
    insert into tbl_exam_04(name) values('연습1');
    commit;
    
    
    create table tbl_exam_05
    (name varchar2(20));
    
    insert into tbl_exam_05(name) values('연습1');
    commit;
    
    create table tbl_exam_06
    (name varchar2(20));
    
    insert into tbl_exam_06(name) values('연습1');
    commit;
    
    
    drop table tbl_exam_01;--> tbl_exam_01 테이블을 영구히 삭제하는 것이 아니라 휴지통에 버리는 것이다. 
    
    
    select * from tab;
    -- 결과물에서 tname 컬럼에 BIN$로 시작하는 것은 휴지통에 버려진 테이블이다.
    
    drop table tbl_exam_02;--> tbl_exam_01 테이블을 영구히 삭제하는 것이 아니라 휴지통에 버리는 것이다. 
    
    
    select * from tbl_exam_02;
    -- 오류
    
    select * from "BIN$STfVaseOThajDrJwqYhOug==$0"; --TBL_EXAM_02
    
    select * from "BIN$YGUZJheBSaWiBsb9+QgAEQ==$0"; --TBL_EXAM_01
    
    
    ----- ====== **** 휴지통 조회하기 **** ===== -----
    select *
    from user_recyclebin;
    
    
    ----- ====== **** 휴지통에 있던 테이블 복원하기 **** ===== -----
    flashback table TBL_EXAM_01 to before drop;
    --TBL_EXAM_01은 user_recyclebin 에서 보여지는 original_name 컬럼에 나오는 것이다.
    
    select *
    from tbl_exam_01;
    -- 복원됨
    
    ----- ====== **** 휴지통에 있는 테이블 영구히 삭제하기 **** ===== -----
    select *
    from user_recyclebin;
    
    purge table tbl_exam_02;
    --TBL_EXAM_02 은 user_recyclebin 에서 보여지는 original_name 컬럼에 나오는 것이다.
    --Table이(가) 비워졌습니다
    
    
    ----- ====== **** 휴지통에 있는 모든 테이블을 영구히 삭제하기 **** ===== -----
    
    drop table tbl_exam_03;
    
    drop table tbl_exam_04 ;
    
    select *
    from user_recyclebin;
    
    purge recyclebin;-- 휴지통에 있던 모든테이블을 영구히 삭제하기
    
    select * from tab; -- BIN$ 로 시작하는 것이 아무것도 없다.
    
    select *
    from tbl_exam_05;
    
    --- *** 테이블을 영구히 삭제하기 ===> drop 되어진 테이블은 복원이 불가하다. *** ---
    
    drop table tbl_exam_05 purge;
    
    select *
    from user_recyclebin;
    
    
    
       --------- ======= ***** INDEX(인덱스, 색인) ***** ======= ---------
   
   /* 
       index(==색인)는 예를 들어 설명하면 아주 두꺼운 책 뒤에 나오는 "찾아보기" 와 같은 기능을 하는 것이다.
       "찾아보기" 의 특징은 정렬되어 있는 것인데 index(==색인) 에 저장된 데이터도 정렬되어 저장되어 있다는 것이 특징이다.
    */
    -- index(==색인)를 생성해서 사용하는 이유는 where 절이 있는 select 명령문의 속도를 향상 시키기 위함이다.
    -- index(==색인)은 어떤 컬럼에 만들어 할까요?
    
    /*
       1. where 절에서 자주 사용되어진 컬럼에 만들어야 한다.
       
       2. 선택도(selectivity)가 높은 컬럼에 만들어야 한다.
       ※ 선택도(selectivity)가 높다라는 것은 고유한 데이터일수록 선택도(selectivity)가 높아진다.
       예: 성별컬럼 --> 선택도(selectivity)가 아주 낮다. 왜냐하면 수많은 사람중 남자 아니면 여자중 하나만 골라야 하므로 선택의 여지가 아주 낮다.
           학번    --> 선택도(selectivity)가 아주 좋다. 왜냐하면 학번은 다양하고 고유하므로 골라야할 대상이 아주 많으므로 선택도가 높은 것이다.
    
       3. 카디널리티(cardinality)가 높은 컬럼에 만들어야 한다.
       ※ 카디널리티(cardinality)의 사전적인 뜻은 집합원의 갯수를 뜻하는 것으로서,
          카디널리티(cardinality)가 높다라는 것은 중복도가 낮아 고유한 데이터일수록 카디널리티(cardinality)가 상대적으로 높다 라는 것이다.
          카디널리티(cardinality)가 낮다라는 것은 중복도가 높아 중복된 데이터가 많을수록 카디널리티(cardinality)가 상대적으로 낮다 라는 것이다.
          
          카디널리티(cardinality)는 "상대적인 개념" 이다.
          예를들어, 주민등록번호 같은 경우는 중복되는 값이 없으므로 카디널리티(cardinality)가 높다고 할 수 있다.
          이에 비해 성명같은 경우는 "주민등록번호에 비해" 중복되는 값이 많으므로, 성명은 "주민등록번호에 비해" 카디널리티가 낮다고 할 수 있다.
          이와같이 카디널리티(cardinality)는 상대적인 개념으로 이해해야 한다.
    */ 
    
    create table tbl_student_1
    (hakbun      varchar2(20) not null
    ,name        varchar2(20)
    ,email       varchar2(30)
    ,address     varchar2(200)
    );
    
    --- *** unique 한 index 생성하기 *** ---
   /* 
      어떤 컬럼에 unique 한 index 를 생성하면 그 컬럼에 들어오는 값은 중복된 값은 들어올 수 없으며 오로지 고유한 값만 들어오게 된다.
      unique 한 index 가 뒤에 나오는 non-unique 한 index 보다 검색속도가 "조금" 더 빠르다.
   */ 
  /*
     [문법]
     create unique index 인덱스명
     on 해당테이블명(컬럼명 asc|desc);
  */
    
    create unique index idx_tbl_student_1_hakbun
    on tbl_student_1(hakbun); -- on tbl_student_1(hakbun asc); 와 동일하다
    --on tbl_student_1(hakbun asc);
    --on tbl_student_1(hakbun desc);
    
    select *
    from tbl_student_1;
    
    insert into tbl_student_1(hakbun, name, email, address) values('1', '일미자', 'ilmj@naver.com', '서울시 강동구');
   -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_student_1(hakbun, name, email, address) values('1', '이미자', 'twomj@naver.com', '서울시 강서구');
    --ORA-00001: 무결성 제약 조건(HR.IDX_TBL_STUDENT_1_HAKBUN)에 위배됩니다
    --마치 유니크나 기본키처럼 작동
    
    insert into tbl_student_1(hakbun, name, email, address) values('2', '이미자', 'twomj@naver.com', '서울시 강서구');
    
    commit;
    
    --- *** tbl_student_1 테이블에 생성 되어진 index 조회하기 *** ---
    
    select *
    from user_indexes
    where table_name = 'TBL_STUDENT_1';
    
    select *
    from user_ind_columns
    where table_name = 'TBL_STUDENT_1';
    
    select A.index_name, A.uniqueness, B.column_name, B.descend
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    
    --- *** non-unique 한 index 생성하기 *** ---
  /* 
     어떤 컬럼에 non-unique 한 index 생성하면 그 컬럼에 들어오는 값은 중복된 값이 들어올 수 있다는 것이다.
     non-unique 한 index 는 unique 한 index 보다 검색속도가 다소 늦은 편이다.
  */ 
  /*
    [문법]
    create index 인덱스명
    on 해당테이블명(컬럼명 asc|desc);
  */
  
    
    select *
    from tbl_student_1
    where name ='일미자';
    
    create index idx_tbl_student_1_name
    on tbl_student_1(name);
    
    insert into tbl_student_1(hakbun, name, email, address) values('3', '삼미자', 'three@naver.com', '서울시 강서구');
    -- 1 행 이(가) 삽입되었습니다.
    
    insert into tbl_student_1(hakbun, name, email, address) values('4', '삼미자', 'sam@naver.com', '서울시 강남구');
    -- 1 행 이(가) 삽입되었습니다.
    
    commit;
    -- 커밋 완료.
    
    select * 
    from tbl_student_1;
    
    select A.index_name, A.uniqueness, B.column_name, B.descend
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    
    /*
      ---------------------------------------------------------------------
       index_name                   uniqueness      column_name    descend
      ---------------------------------------------------------------------
       IDX_TBL_STUDENT_1_HAKBUN       UNIQUE           HAKBUN          ASC
       IDX_TBL_STUDENT_1_NAME       NONUNIQUE           NAME           ASC
      ---------------------------------------------------------------------- 
   */
    
    
    select *
    from tbl_student_1
    where hakbun = '2';
    -->  unique한 인덱스 IDX_TBL_STUDENT_1_HAKBUN 를 사용하여 빠르게 조회해옴.
    
    select *
    from tbl_student_1
    where name = '이미자';
    -->  non-unique한 인덱스 IDX_TBL_STUDENT_1_HAKBUN 를 사용하여 빠르게 조회해옴.
    
    
    select *
    from tbl_student_1
    where address = '서울시 강동구';--> address 컬럼에는 인덱스가 없으므로 tbl_student_1 테이블에 있는 모든 데이터를 조회해서 
                                    --  address 컬럼의 값이  '서울시 강동구' 인 데이터를 가져온다.
                                    --  이와 같이 인덱스를 사용하지 않고 데이터를 조회해올 때를 Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회) 이라고 부른다.
                                    --  Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회)이 속도가 가장 느린 것이다.
    
    delete from tbl_student_1;  
    -- 4개 행 이(가) 삭제되었습니다.
    
    commit;
    -- 커밋 완료.
   
    create sequence seq_tbl_student_1
    start with 1
    increment by 1
    nomaxvalue
    nominvalue
    nocycle
    nocache;
    -- Sequence SEQ_TBL_STUDENT_1이(가) 생성되었습니다.
    
    
     commit;
     
     
    select *
    from tbl_student_1
    order by hakbun asc;
    
    select count(*)
    from tbl_student_1;
    
    select seq_tbl_student_1.currval as "최근에 사용한 시퀀스 값"
    from dual;
    
    
    declare
        v_today varchar2(8);
        v_cnt number := 0;
        v_seq number ;
    begin
        select to_char(sysdate, 'yyyymmdd') into v_today
        from dual;
        loop
            v_cnt := v_cnt + 1;
            exit when v_cnt > 10000;
            
            --채번 (번호 추출)
            select seq_tbl_student_1.nextval into v_seq
            from dual;
            
            insert into tbl_student_1(hakbun, name, email, address)
            values (v_today || '-' || v_seq, '이순신'||v_seq, 'leess' || v_seq || '@gmail.com', '서울시 마포구 월드컵로 ' || v_seq);
            
        end loop;
    end;
    
    
    
    
    insert into tbl_student_1(hakbun, name, email, address)
    values (to_char(sysdate, 'yyyymmdd') || '-' || 10001, '이순신'|| 10001, 'baesuji' || 10001 || '@gmail.com', '서울시 마포구 월드컵로 ' || 10001);
    
    insert into tbl_student_1(hakbun, name, email, address)
    values (to_char(sysdate, 'yyyymmdd') || '-' || 10002, '이순신'|| 10002, 'baesuji' || 10002 || '@gmail.com', '서울시 마포구 월드컵로 ' || 10002);
    
    insert into tbl_student_1(hakbun, name, email, address)
    values (to_char(sysdate, 'yyyymmdd') || '-' || 10003, '이순신'|| 10003, 'baesuji' || 10003 || '@gmail.com', '서울시 마포구 월드컵로 ' || 10003);
    
    commit;
    
    
    select A.index_name, A.uniqueness, B.column_name, B.descend
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    
    /*
      ---------------------------------------------------------------------
       index_name                   uniqueness      column_name    descend
      ---------------------------------------------------------------------
       IDX_TBL_STUDENT_1_HAKBUN       UNIQUE           HAKBUN          ASC
       IDX_TBL_STUDENT_1_NAME       NONUNIQUE           NAME           ASC
      ---------------------------------------------------------------------- 
   */
    
       -- ==== *** SQL*Developer 에서 Plan(실행계획) 확인하는 방법 *** ==== --
    /*
      select 문이 실행될 때 인덱스를 사용하여 데이터를 얻어오는지 인덱스를 사용하지 않고 
      Table Full Scan 하여 얻어오는지 알아봐야 한다.
      이럴때 사용하는 것이 SQL Plan(실행계획)이다. 
      
      SQL*Developer 에서는 "SQL편집창(SQL 워크시트)"에 Plan(실행계획) 과 Trace(자동추적) 메뉴가 상단에 있다.
      
      Plan(실행계획) 과 Trace(자동추적) 의 차이는,
      Plan(실행계획) 은 SQL을 실행하기 전에 Oracle Optimizer(옵티마이저, 최적화기)가 SQL을 어떻게 실행할지를 미리 알려주는 것이고,
      Trace(자동추적) 는 SQL을 실행해보고, Oracle Optimizer(옵티마이저, 최적화기)가 SQL을 어떻게 실행했는지 그 결과를 알려주는 것이다.

      그러므로, 정확도로 말하자면, Trace(자동추적)가 Plan(실행계획) 보다 훨씬 정확한 것이다.
      Plan(실행계획) 은 말그대로 계획이라서 Oracle Optimizer가 계획은 그렇게 세우긴 했으나 
      실제 실행할때는 여러가지 이유로 다르게 실행할 수도 있기 때문이다.
      그래서 Trace(자동추적)가 정확하기는 하나 Trace(자동추적)는 한번 실행해봐야 하는것이라서 
      시간이 오래 걸리는 SQL인 경우에는 한참 기다려야 하는 단점이 있기는 하다.
   */       
    
    
   /* 
      실행해야할 SQL문을 블럭으로 잡은 후에
      "SQL 워크시트" 의 상단 아이콘들중에 3번째 아이콘( 계획 설명... (F10) )을 클릭하면 현재 SQL의 Plan(실행계획)을 아래에 보여준다.
      COST(비용)의 값이 적을 수록 속도가 빠른 것이다.
   */
    
    select *
    from tbl_student_1
    where hakbun = '20241008-6789';
    --> unique 한 인덱스 IDX_TBL_STUDENT_1_HAKBUN 을 사용하여 빠르게 조회해옴. 
    
    select *
    from tbl_student_1
    where name = '이순신5783'; --> non-unique 한 인덱스 IDX_TBL_STUDENT_1_NAME 을 사용하여 빠르게 조회해옴.  
    
    select *
    from tbl_student_1
    where address = '서울시 마포구 월드컵로 3987';--> address 컬럼에는 인덱스가 없으므로 tbl_student_1 테이블에 있는 모든 데이터를 조회해서 
                                               --  address 컬럼의 값이  '서울시 마포구 월드컵로 3987' 인 데이터를 가져온다.
                                               --  이와 같이 인덱스를 사용하지 않고 데이터를 조회해올 때를 Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회) 이라고 부른다.
                                               --  Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회)이 속도가 가장 느린 것이다.
    
    select *
    from tbl_student_1
    where email = 'leess4439@gmail.com'; -- email 컬럼에는 인덱스가 없으므로 Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회)하여 조회해 오는 것임.
    
    
    
    
    -----------------------------------------------------------------------------------------------------------
    -- *** Trace(자동추적)을 하기 위해서는 SYS 또는 SYSTEM 으로 부터 권한을 부여 받은 후 HR은 재접속을 해야 한다. *** --
    show user;
    -- USER이(가) "SYS"입니다.
    
    grant select_catalog_role to hr;
    -- Grant을(를) 성공했습니다.
    
    grant select any dictionary to hr;
    -- Grant을(를) 성공했습니다.
    ---------------------------------------------------------------------------- 
    
    
    /* 
      실행해야할 SQL문을 블럭으로 잡은 후에
      "SQL 워크시트" 의 상단 아이콘들중에 4번째 아이콘( 자동 추적... (F6) )을 클릭하면 현재 SQL의 Trace(자동추적)을 아래에 보여준다.
      
      Trace(자동추적)을 하면 Plan(실행계획) 도 나오고, 동시에 아래쪽에 통계정보도 같이 나온다.

      오른쪽에 Plan(실행계획)에서는 보이지 않던 LAST_CR_BUFFER_GETS 와 LAST_ELAPSED_TIME 컬럼이 나온다.
      LAST_CR_BUFFER_GETS 는 SQL을 실행하면서 각 단계에서 읽어온 블록(Block) 갯수를 말하는 것이고,
      LAST_ELAPSED_TIME 은 경과시간 정보이다.
      즉, 이 정보를 통해서 어느 구간에서 시간이 많이 걸렸는지를 확인할 수 있으므로, 이 부분의 값이 적게 나오도록 SQL 튜닝을 하게 된다.
    */
    
     select *
    from tbl_student_1
    where hakbun = '20241008-6789';
    --> unique 한 인덱스 IDX_TBL_STUDENT_1_HAKBUN 을 사용하여 빠르게 조회해옴. 
    
    select *
    from tbl_student_1
    where name = '이순신5783'; --> non-unique 한 인덱스 IDX_TBL_STUDENT_1_NAME 을 사용하여 빠르게 조회해옴.  
    
    select *
    from tbl_student_1
    where address = '서울시 마포구 월드컵로 3987';--> address 컬럼에는 인덱스가 없으므로 tbl_student_1 테이블에 있는 모든 데이터를 조회해서 
                                               --  address 컬럼의 값이  '서울시 마포구 월드컵로 3987' 인 데이터를 가져온다.
                                               --  이와 같이 인덱스를 사용하지 않고 데이터를 조회해올 때를 Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회) 이라고 부른다.
                                               --  Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회)이 속도가 가장 느린 것이다.
    
    select *
    from tbl_student_1
    where email = 'leess4439@gmail.com'; -- email 컬럼에는 인덱스가 없으므로 Table Full-scan(인덱스를 사용하지 않고 테이블 전체 조회)하여 조회해 오는 것임.
    
    
    ---- *** DML(insert, update, delete)이 빈번하게 발생하는 테이블에 index가 생성되어 있으면
    ---      DML(insert, update, delete) 작업으로 인해 Index 에 나쁜 결과를 초래하므로  
    ---      index 가 많다고 해서 결코 좋은 것이 아니기에 테이블당 index 의 개수는 최소한의 개수로 만드는 것이 좋다.
    
    ---- *** index 가 생성되어진 테이블에 insert 를 하면 Index Split(인덱스 쪼개짐) 가 발생하므로
    ----     index 가 없을시 보다 insert 의 속도가 떨어지게 된다.
    ----     그러므로 index 가 많다고 결코 좋은 것이 아니므로 최소한의 개수로 index 를 만드는 것이 좋다.
    ----     Index Split(인덱스 쪼개짐)란 Index 의 block(블럭)들이 1개에서 2개로 나뉘어지는 현상을 말한다.
    ----     Index Split(인덱스 쪼개짐)이 발생하는 이유는 Index 는 정렬이 되어 저장되기 때문에 
    ---      Index 의 마지막 부분에 추가되는 것이 아니라 정렬로 인해 중간 자리에 끼워들어가는 현상이
    ----     발생할 수 있기 때문이다. 
    
    
    ---- *** index 가 생성되어진 테이블에 delete 를 하면 테이블의 데이터는 삭제가 되어지지만 
    ----     Index 자리에는 데이터는 삭제되지 않고서 사용을 안한다는 표시만 하게 된다.
    ----     그래서 10만 건이 들어있던 테이블에 9만건의 데이터를 delete 를 하면 테이블에는 데이터가 삭제되어 지지만
    ----     Index 자리에는 10만 건의 정보가 그대로 있고 1만건만 사용하고 9만건은 사용되지 않은채로 되어있기에
    ----     사용하지 않는 9만건의 Index 정보로 인해서 index를 사용해서 select를 해올 때 index 검색속도가 떨어지게 된다.   
    ----     이러한 경우 Index Rebuild 작업을 해주어 사용하지 않는 9만건의 index 정보를 삭제해주어야만 
    ----     select를 해올 때 index 검색속도가 빨라지게 된다. 
    
    
    ---- *** index 가 생성되어진 테이블에 update 를 하면 테이블의 데이터는 "수정" 되어지지만 
    ----     Index 는 "수정" 이라는 작업은 없고 index 를 delete 를 하고 새로이 insert 를 해준다.
    ----     그러므로 index 를 delete 할 때 발생하는 단점 및 index 를 insert 를 할 때 발생하는 Index Split(인덱스 쪼개짐) 가 발생하므로
    ----     Index 에는 최악의 상황을 맞게 된다. 
    ----     이로 인해 테이블의 데이터를 update를 빈번하게 발생시켜 버리면 select를 해올 때 index 검색속도가 현저히 느려지게 된다. 
    ----     이러한 경우도 select를 해올 때 index 검색속도가 빨라지게끔 Index Rebuild 작업을 해주어야 한다. 
    
    ---- **** !!!!Index(인덱스)의 상태 확인하기!!!! **** ----
    analyze index IDX_TBL_STUDENT_1_NAME validate structure;
    -- Index IDX_TBL_STUDENT_1_NAME이(가) 분석되었습니다.
    
    
    select (del_lf_rows_len / lf_rows_len) * 100 "인덱스상태(Balance)"
    from index_stats
    where name = 'IDX_TBL_STUDENT_1_NAME';
    
    
    /*
       인덱스상태(Balance)
       ------------------
              0          <== 0 에 가까울 수록 인덱스 상태가 좋은 것이다.
    */
    select count(*)
    from tbl_student_1;
    
    
    delete from tbl_student_1
    where hakbun between '20241008-400' and '20241008-9400';
    
    commit;
    
    
    select (del_lf_rows_len / lf_rows_len) * 100 "인덱스상태(Balance)"
    from index_stats
    where name = 'IDX_TBL_STUDENT_1_NAME';
    
    
    /*
       인덱스상태(Balance)
       ------------------
              0          <== delete 하기전의 index를 분석한 것이므로 0 이라고 나올 뿐이다
    */
    
    
    
    
    
    ---- **** !!!!Index(인덱스)의 상태 확인하기!!!! **** ----
    analyze index IDX_TBL_STUDENT_1_NAME validate structure;
    -- Index IDX_TBL_STUDENT_1_NAME이(가) 분석되었습니다.
    
    select (del_lf_rows_len / lf_rows_len) * 100 "인덱스상태(Balance)"
    from index_stats
    where name = 'IDX_TBL_STUDENT_1_NAME';
    /*
       인덱스상태(Balance)
       ------------------
              59.99108333467217197114534967787542374243          <== 인덱스 밸런스가 대략 60%정도가 꺠진 것이다
    */
    
    update tbl_student_1 set name = '홍길동'
    where hakbun between '20241008-9401' and '20241008-9901';
    
    commit;
    
    ---- **** !!!!Index(인덱스)의 상태 확인하기!!!! **** ----
    analyze index IDX_TBL_STUDENT_1_NAME validate structure;
    -- Index IDX_TBL_STUDENT_1_NAME이(가) 분석되었습니다.
    
    select (del_lf_rows_len / lf_rows_len) * 100 "인덱스상태(Balance)"
    from index_stats
    where name = 'IDX_TBL_STUDENT_1_NAME';
    
    /*
       인덱스상태(Balance)
       ------------------
              60.02567952283672581981671548237301763108          <== 인덱스 밸런스가 대략 60%정도가 꺠진 것이다
    */
    
    
    ----- *** ==== Index Rebuild(인덱스 재건축) 하기 ==== *** -----
    -- 인덱스 밸런스가 대략 61% 정도 깨진 IDX_TBL_STUDENT_1_NAME 을 Index Rebuild(인덱스 재건축) 하겠습니다. --
    alter index IDX_TBL_STUDENT_1_NAME Rebuild;
    
    ---- **** !!!!Index(인덱스)의 상태 확인하기!!!! **** ----
    analyze index IDX_TBL_STUDENT_1_NAME validate structure;
    -- Index IDX_TBL_STUDENT_1_NAME이(가) 분석되었습니다.
    
    select (del_lf_rows_len / lf_rows_len) * 100 "인덱스상태(Balance)"
    from index_stats
    where name = 'IDX_TBL_STUDENT_1_NAME';
    
    
    select A.index_name, A.uniqueness, B.column_name, B.descend
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    
    
    
    drop index IDX_TBL_STUDENT_1_NAME;
    
    drop index IDX_TBL_STUDENT_1_HAKBUN;
    
    
    
    
    ------ **** !!!!! 복합인덱스(Composite index) 생성하기 !!!!! **** -------
    -- 복합인덱스(composite index)란? 
    -- 2개 이상의 컬럼으로 묶어진 인덱스를 말하는 것으로서
    -- where 절에 2개의 컬럼이 사용될 경우 각각 1개 컬럼마다 각각의 인덱스를 만들어서 사용하는 것보다
    -- 2개의 컬럼을 묶어서 하나의 인덱스로 만들어 사용하는 것이 속도가 좀 더 빠르다.
    
    select *
    from tbl_student_1
    where name = '배수지10001' and address = '서울시 마포구 월드컵로 10001';
    
    select *
    from tbl_student_1
    where name = '이순신10001' and address = '서울시 마포구 월드컵로 10001';
    
    
    
    -- !!!!  중요  !!!! --
    -- 복합인덱스(composite index) 생성시 중요한 것은 선행컬럼을 정하는 것이다.
    -- 선행컬럼은 맨처음에 나오는 것으로 아래에서는 name 이 선행컬럼이 된다.`
    -- 복합인덱스(composite index)로 사용되는 컬럼중 선행컬럼으로 선정되는 기준은 where 절에 가장 많이 사용되는 것이며 
    -- 선택도(selectivity)가 높은 컬럼이 선행컬럼으로 선정되어야 한다.
    create index idx_tbl_student_1_name_addr
    on tbl_student_1(name, address); -- name 컬럼이 선행컬럼이 된다.
    
    /*
    create index idx_tbl_student_1_name_addr
    on tbl_student_1(address, name); -- address 컬럼이 선행컬럼이 된다.
    */
    
    select A.index_name, A.uniqueness, B.column_name, B.descend ,B.column_position
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    
    /*
      --------------------------------------------------------------------------------------
       index_name                   uniqueness      column_name    descend  column_position
      --------------------------------------------------------------------------------------
       IDX_TBL_STUDENT_1_NAME_ADDR	NONUNIQUE      	ADDRESS	        ASC           2
       IDX_TBL_STUDENT_1_NAME_ADDR	NONUNIQUE	      NAME	        ASC           1(1이 선행컬럼)
      --------------------------------------------------------------------------------------
   */
    
    select *
    from tbl_student_1
    where name = '이순신10001' and address = '서울시 마포구 월드컵로 10001';
    -- where 절에 선행컬럼인 name 이 사용되어지면 복합인덱스(composite index)인 IDX_TBL_STUDENT_1_NAME_ADDR 을 사용하여 빨리 조회해온다.
    
    select *
    from tbl_student_1
    where address = '서울시 마포구 월드컵로 10001' and name = '이순신10001';
    -- where 절에 선행컬럼인 name 이 사용되어지면 복합인덱스(composite index)인 IDX_TBL_STUDENT_1_NAME_ADDR 을 사용하여 빨리 조회해온다.
    
     select *
    from tbl_student_1
    where name = '이순신10001';
    -- where 절에 선행컬럼인 name 이 사용되어지면 복합인덱스(composite index)인 IDX_TBL_STUDENT_1_NAME_ADDR 을 사용하여 빨리 조회해온다.
    
     select *
    from tbl_student_1
    where address = '서울시 마포구 월드컵로 10001';
    -- where 절에 선행컬럼인 name 이 사용되어지면 복합인덱스(composite index)인 IDX_TBL_STUDENT_1_NAME_ADDR 을 사용하여 빨리 조회해온다.
    -- where 절에 선행컬럼이 없으므로 복합인덱스(composite index)인 IDX_TBL_STUDENT_1_NAME_ADDR 을 사용하지 못하고 Table Full Scan 하여 조회해오므로 속도가 떨어진다.   
    
    
    
    
    create table tbl_member
    (userid      varchar2(20)
    ,passwd      varchar2(30) not null
    ,name        varchar2(20) not null 
    ,address     varchar2(100)
    ,email       varchar2(50) not null 
    ,constraint  PK_tbl_member_userid primary key(userid)
    ,constraint  UQ_tbl_member_email unique(email)
    );
    -- Table TBL_MEMBER이(가) 생성되었습니다.
    
    
    declare 
         v_cnt  number := 1;  
    begin
         loop
             exit when v_cnt > 10000;
             
             insert into tbl_member(userid, passwd, name, address, email)
             values('hongkd'||v_cnt, 'qwer1234$', '홍길동'||v_cnt, '서울시 마포구 '||v_cnt, 'hongkd'||v_cnt||'@gmail.com');
             
             v_cnt := v_cnt + 1;
         end loop;
    end;
    -- PL/SQL 프로시저가 성공적으로 완료되었습니다.
    
    commit;
    -- 커밋 완료.
    
    
    
    select *
    from tbl_member;
    
    select A.index_name, A.uniqueness, B.column_name, B.descend ,B.column_position
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_MEMBER';
    
    /*
      --------------------------------------------------------------------------------------
       index_name                   uniqueness      column_name    descend  column_position
      --------------------------------------------------------------------------------------
        PK_TBL_MEMBER_USERID	      UNIQUE	      USERID	    ASC	        1
        UQ_TBL_MEMBER_EMAIL	          UNIQUE	      EMAIL	        ASC	        1
      --------------------------------------------------------------------------------------
   */
    
    -- Primary Key 또는 Unique Key 제약을 주면 제약조건명이 인덱스명이 되어진다.
    -- Primary Key 또는 Unique Key 제약을 준 컬럼에 UNIQUE 한 인덱스가 생성되어진다.
    
    -- 로그인을 하는데 로그인이 성공되어지면 그 회원의 성명만을 보여주도록 한다.
    select name
    from tbl_member
    where userid = 'hongkd201' and passwd = 'qwer1234$';
    
    --- **** userid, passwd, name 컬럼을 가지고 복합인덱스(composite index)를 생성해 봅니다. **** ---
    create index idx_tbl_member_pwd_name
    on tbl_member(userid, passwd, name);
    
    
    select A.index_name, A.uniqueness, B.column_name, B.descend ,B.column_position
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_MEMBER';
    
    /*
      --------------------------------------------------------------------------------------
       index_name                   uniqueness      column_name    descend  column_position
      --------------------------------------------------------------------------------------
        PK_TBL_MEMBER_USERID	    UNIQUE	        USERID	         ASC	    1
        UQ_TBL_MEMBER_EMAIL	        UNIQUE	        EMAIL	         ASC	    1
        IDX_TBL_MEMBER_PWD_NAME	    NONUNIQUE	    USERID	         ASC	    1
        IDX_TBL_MEMBER_PWD_NAME	    NONUNIQUE	    PASSWD	         ASC	    2
        IDX_TBL_MEMBER_PWD_NAME 	NONUNIQUE	    NAME	         ASC     	3
      --------------------------------------------------------------------------------------
   */
    
    
    drop index IDX_TBL_MEMBER_PWD_NAME;
    
    select name
    from tbl_member
    where userid = 'hongkd201' and passwd = 'qwer1234$';
    -- where 절 및 select 에 복합인덱스(composite index)인 IDX_TBL_MEMBER_ID_PWD_NAME 에 사용되어진 컬럼만 있으므로
    -- 테이블 tbl_member 에는 접근하지 않고 인덱스 IDX_TBL_MEMBER_ID_PWD_NAME 에만 접근해서 조회하므로 속도가 빨라진다. 
    
    select name, address
    from tbl_member
    where userid = 'hongkd201' and passwd = 'qwer1234$';
    -- where 절에 userid 및 passwd 가 사용되었으므로 복합인덱스(composite index)인 IDX_TBL_MEMBER_ID_PWD_NAME 을 사용하는데 
    -- select 절에 IDX_TBL_MEMBER_ID_PWD_NAME 에 없는 address 컬럼이 있으므로 테이블 tbl_member 에 접근해야 한다.
    -- 그러므로 인덱스 IDX_TBL_MEMBER_ID_PWD_NAME 만 접근하는 것 보다는 조회 속도가 쬐금 느려진다. 
    
    
    drop index IDX_TBL_MEMBER_PWD_NAME;
    
    
    
    
    ----- **** 함수기반 인덱스(function based index) 생성하기 **** ----
    select A.index_name, A.uniqueness, B.column_name, B.descend ,B.column_position
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    
    drop index IDX_TBL_STUDENT_1_NAME_ADDR;
    
    create index idx_tbl_student_1_name
    on tbl_student_1(name);
    
    
    select * 
    from tbl_student_1
    where name = '이순신10002';
    -- name 컬럼에 생성해둔 인덱스 IDX_TBL_STUDENT_1_NAME 인덱스를 사용하여 조회해온다.
    
    
    select * 
    from tbl_student_1
    where substr(name, 2,2) = '순신';
    -- name 컬럼에 생성해둔 인덱스 IDX_TBL_STUDENT_1_NAME 인덱스를 사용하지 않고 Table Full Scan 하여 조회해온다.  
    
    create index idx_func_tbl_student_1_name
    on tbl_student_1(substr(name, 2,2));
    -- 함수기반 인덱스(function based index) 생성
    
    select A.index_name, A.uniqueness, B.column_name, B.descend ,B.column_position
    from user_indexes A join user_ind_columns B
    on A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_1';
    -- 함수기반 인덱스인 IDX_FUNC_TBL_STUDENT_1_NAME 을 사용하여 조회해온다.
    
    drop index IDX_FUNC_TBL_STUDENT_1_NAME;
    
    /*
     ---------------------------------------------------------------------------------------------
      index_name                   uniqueness    column_name   descend      column_position
     --------------------------------------------------------------------------------------------- 
      IDX_TBL_STUDENT_1_NAME       NONUNIQUE         NAME        ASC               1
     --------------------------------------------------------------------------------------------- 
    */
    
    
    select * 
    from tbl_student_1
    where name = '이순신10002';
    -- 인덱스 IDX_TBL_STUDENT_1_NAME 을 사용하여 조회해온다.
    
    
    select * 
    from tbl_student_1
    where name like '이순신10002';
    -- 인덱스 IDX_TBL_STUDENT_1_NAME 을 사용하여 조회해온다.
    
    select * 
    from tbl_student_1
    where name like '이순신1____%';
    -- 인덱스 IDX_TBL_STUDENT_1_NAME 을 사용하여 조회해온다.
    
    select * 
    from tbl_student_1
    where name like '%이순신%';
    -- 맨앞에 % 또는 _ 가 나오면 인덱스 IDX_TBL_STUDENT_1_NAME 을 사용하지 않고 Table Full Scan 하여 조회해온다. 
    
    select *
    from employees
    where func_age(jubun) = 25;
    
    
    create index idx_func_age(jubun) on employees(func_age(jubun));
    
    create index idx_func_age
    on employees(func_age(jubun));
  /*
     -- 오류 보고 -
     ORA-30553: 함수가 결정적인 것이 아닙니다
     
     왜냐하면 func_age(jubun) 함수 내용물속에 sysdate 가 들어가 있으므로
     값이 고정적이지 않고 매번 바뀌기 때문에 함수기반 인덱스로 만들수가 없다.!!!
  */
    
    
    
    
    
    
    --- **** 어떤 테이블의 어떤 컬럼에 Primary Key 제약 또는 Unique 제약을 주면
    --       자동적으로 그 컬럼에는 unique 한 index가 생성되어진다.
    --       인덱스명은 제약조건명이 된다. **** 
    
    create table tbl_student_2
    (hakbun      varchar2(10) 
    ,name        varchar2(20)
    ,email       varchar2(20) not null
    ,address     varchar2(20)
    ,constraint PK_tbl_student_2_hakbun primary key(hakbun)
    ,constraint UQ_tbl_student_2_email unique(email)
    );
    -- Table TBL_STUDENT_2이(가) 생성되었습니다.
    
    select A.index_name, uniqueness, column_name, descend 
    from user_indexes A JOIN user_ind_columns B
    ON A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_2';
    
    
    -- Primary Key 제약 또는 Unique 제약으로 생성되어진 index 의 제거는 
    -- drop index index명; 이 아니라
    -- alter table 테이블명 drop constraint 제약조건명; 이다.
    -- 제약조건을 삭제하면 자동적으로 index 도 삭제가 된다.
    
    drop index PK_TBL_STUDENT_2_HAKBUN;
    /*
       오류 보고 -
       ORA-02429: 고유/기본 키 적용을 위한 인덱스를 삭제할 수 없습니다.
    */
    
    drop index UQ_TBL_STUDENT_2_EMAIL;
    /*
       오류 보고 -
       ORA-02429: 고유/기본 키 적용을 위한 인덱스를 삭제할 수 없습니다.
    */
   
    alter table tbl_student_2
    drop primary key;
    -- Table TBL_STUDENT_2이(가) 변경되었습니다.
    
    alter table tbl_student_2
    drop constraint UQ_tbl_student_2_email;
    -- Table TBL_STUDENT_2이(가) 변경되었습니다.
    
    
    select A.constraint_name, A.constraint_type, A.search_condition, 
           B.column_name, B.position 
    from user_constraints A join user_cons_columns B 
    on A.constraint_name = B.constraint_name
    where A.table_name = 'TBL_STUDENT_2';
    
    
    select A.index_name, uniqueness, column_name, descend 
    from user_indexes A JOIN user_ind_columns B
    ON A.index_name = B.index_name
    where A.table_name = 'TBL_STUDENT_2';
    
    
    
    
    -----  ===== ***** 데이터사전(Data Dictionary) **** ==== -----
    
    
    
    ---- **** ORACLE DATA DICTIONARY VIEW(오라클 데이터 사전 뷰) **** ---- 
    show user;
    -- USER이(가) "SYS"입니다.
    
    -- 먼저 index 공부를 할때 trace(자동추적) 때문에 아래와 같이 HR에게 부여했던 SELECT ANY DICTIONARY 권한을 회수하겠습니다. 
    revoke SELECT ANY DICTIONARY from HR;
    -- Revoke을(를) 성공했습니다.
    
    revoke SELECT_CATALOG_ROLE from HR;
    -- Revoke을(를) 성공했습니다.
    
    
    show user;
    -- USER이(가) "HR"입니다.
    
    select * 
    from dictionary;
    -- 또는
    select * 
    from dict;
    
    /*
        USER_CONS_COLUMNS
        ALL_CONS_COLUMNS
    */
    
    --- ===== SYS 로 접속한 것 시작 ===== ---
    show user;
    -- USER이(가) "SYS"입니다.
    
    select * 
    from dictionary;
    -- 또는
    select * 
    from dict;
    /*
      USER_CONS_COLUMNS
      ALL_CONS_COLUMNS
      DBA_CONS_COLUMNS
    */
    
    
    /*
     DBA_로 시작하는 것 
     ==> 관리자만 조회가능한 것으로 모든 오라클사용자정보, 모든 테이블, 모든 인덱스, 모든 데이터베이스링크 등등등 의 정보가 다 들어있는 것.
     
     USER_로 시작하는 것 
     ==> 오라클서버에 접속한 사용자 소유의 자신의 오라클 사용자정보, 자신이 만든 테이블, 자신이 만든 인덱스, 자신이 만든 데이터베이스링크 등등등 의 정보가 다 들어있는 것.
     
     ALL_로 시작하는 것 
     ==> 오라클서버에 접속한 사용자 소유의 즉, 자신의 오라클 사용자정보, 자신이 만든 테이블, 자신이 만든 인덱스, 자신이 만든 데이터베이스링크 등등등 의 정보가 다 들어있는 것
         과(와)
         자신의 것은 아니지만 조회가 가능한 다른 사용자의 오라클 사용자정보, 다른 사용자 소유의 테이블, 다른 사용자 소유의 인덱스, 다른 사용자 소유의 데이터베이스링크 등등등 의 정보가 다 들어있는 것. 
    */
    
    select *
    from dba_tables
    where owner in('HR', 'ORAUSER1');
    
    --- ===== SYS 로 접속한 것 끝 ===== ---
    
    
    --- ===== HR 로 접속한 것 시작 ===== ---
    show user;
    -- USER이(가) "HR"입니다.
    
    select *
    from dba_tables;
    -- ORA-00942: 테이블 또는 뷰가 존재하지 않습니다
    
    select *
    from user_tables;

    select *
    from all_tables;
    
    select *
    from all_tables
    where owner = 'SYS';
    
    
    
    -- *** 자신이 만든 테이블에 대한 모든 정보를 조회하고 싶다. 어디서 보면 될까요? *** --
    select *
    from dict
    where table_name like 'USER_%' and lower(comments) like '%table%';
    
    
    select *
    from USER_TABLES;
    
    -- *** USER_TABLES 에서 보여지는 컬럼에 대한 설명을 보고 싶으면 아래와 같이 하면 됩니다. *** -- 
    
    select *
    from dict_columns
    where table_name = 'USER_TABLES';
    
    -- *** 자신이 만든 테이블의 컬럼에 대한 모든 정보를 조회하고 싶다. 어디서 보면 될까요? *** -- 
    select *
    from dict
    where table_name like 'USER_%' and lower(comments) like '%column%';
    
    select *
    from USER_TAB_COLUMNS
    where table_name = 'EMPLOYEES';
    
    -- *** USER_TAB_COLUMNS 에서 보여지는 컬럼에 대한 설명을 보고 싶으면 아래와 같이 하면 됩니다. *** -- 
    select *
    from dict_columns
    where table_name = 'USER_TAB_COLUMNS';
    
    -- *** 자신이 만든 테이블의 제약조건에 대한 모든 정보를 조회하고 싶다. 어디서 보면 될까요? *** --
    select *
    from dict
    where table_name like 'USER_%' and lower(comments) like '%constraint%';
    
    select *
    from USER_CONSTRAINTS
    where table_name = 'EMPLOYEES';
    
    
    select *
    from USER_CONS_COLUMNS
    where table_name = 'EMPLOYEES';
    
    -- *** 자신이 만든 데이터베이스 링크 대한 모든 정보를 조회하고 싶다. 어디서 보면 될까요? *** --
    select *
    from dict
    where table_name like 'USER_%' and lower(comments) like '%database link%';
    
    select *
    from USER_DB_LINKS;
    
     -- *** 자신이 만든 시퀀스에 대한 모든 정보를 조회하고 싶다. 어디서 보면 될까요? *** --
    select *
    from dict
    where table_name like 'USER_%' and lower(comments) like '%sequence%';
    
    
    select *
    from user_sequences;
    
    
     -- *** 자신이 만든 시퀀스에 대한 모든 정보를 조회하고 싶다. 어디서 보면 될까요? *** --
    select *
    from dict
    where table_name like 'USER_%' and lower(comments) like '%index%';
    
    select *
    from USER_INDEXES
    where table_name = 'EMPLOYEES';
    
    select *
    from USER_IND_COLUMNS
    where table_name = 'EMPLOYEES';
    
    --- ===== HR 로 접속한 것 끝 ===== ---
    
    
    
    
    
    
    
    
    
    
    
  
  --- **** PL/SQL (Procedure Language / Structured Query Language) **** ---
  
  --*** PL/SQL 구문에서 변수의 사용법 첫번째 ***---
  exec pcd_empInfo(101);
    /*
       <결과물>
       ---------------------------------
        사원번호    사원명   성별   월급
       ---------------------------------
         101       ....    ....  ....
   */
   
    exec pcd_empInfo(102);
    /*
       <결과물>
       ---------------------------------
        사원번호    사원명   성별   월급
       ---------------------------------
         101       ....    ....  ....
   */
  
  
  create or replace procedure pcd_empInfo
  (p_employee_id  IN  number) -- 매개변수, in은 입력모드이다, out모드도 있다, number(5)같이 자리수를 넣어주면 오류이다 자주 실수하는 부분
  is
    --변수의 선언부
    v_employee_id   number(10); --자리수를 사용해도 OK
    v_ename         varchar2(100);
    v_gender        nvarchar2(5);
    v_monthsal      varchar2(20);
  begin
    --실행부
    select employee_id
        , first_name || ' ' || last_name
        , case when substr(7,1) in ('1', '3') then '남' else '여' end
        , to_char(nvl(salary + (salary * commission_pct), salary),'9,999,999')
        into
        v_employee_id, v_ename, v_gender, v_monthsal -- 각 컬럼의 값을 변수에 넣어준 것이다.
    from employees
    where employee_id = p_employee_id;
    
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line('사원번호    사원명   성별   월급');
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line(V_employee_id || ' ' ||
                        V_ename || ' ' ||
                        V_gender || ' ' ||
                        V_monthsal);
  end pcd_empInfo;
  
  ------------------------------------------------------------------------------- 선생님 코드---------------
  
  /*
  create or replace procedure pcd_empInfo
 (p_employee_id  IN  number)   --  IN 은 입력모드를 말한다. number(5) 와 같이 자리수를 넣어주면 오류이다.!!! 
 is
   -- 변수의 선언부
   v_employee_id   number(10);     -- 자리수를 사용한다.
   v_ename         varchar2(100);  -- 자리수를 사용한다.
   v_gender        Nvarchar2(3);
   v_monthsal      varchar2(20);
 begin
   -- 실행부 
   select employee_id, first_name || ' ' || last_name,
          case when substr(jubun, 7, 1) in('1','3') then '남' else '여' end,
          to_char( nvl(salary + (salary * commission_pct), salary), '9,999,999')
          INTO
          v_employee_id, v_ename, v_gender, v_monthsal 
   from employees
   where employee_id = p_employee_id;
   
   dbms_output.put_line( lpad('-',40,'-') );
   dbms_output.put_line( '사원번호    사원명   성별   월급' );
   dbms_output.put_line( lpad('-',40,'-') );
   
   dbms_output.put_line( v_employee_id || ' ' ||
                         v_ename || ' ' ||
                         v_gender || ' ' ||
                         v_monthsal);
 end pcd_empInfo;
 
 */
 -- Procedure PCD_EMPINFO이(가) 컴파일되었습니다.
 
    /* === SQL Developer 의 메뉴의 보기를 클릭하여 DBMS 출력을 클릭해주어야 한다. ===
         === 이어서 하단부에 나오는 DBMS 출력 부분의 녹색 + 기호를 클릭하여 local_hr 로 연결을 해준다. === 
    */
    
    exec pcd_empInfo(101);
    
    exec pcd_empInfo(102);
    
    --- *** 생성되어진 Procedure 인 pcd_empInfo 의 소스 알아보기 *** ---
    select text
    from user_source
    where type = 'PROCEDURE' and name ='PCD_EMPINFO';
    
    
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , case when substr(7,1) in ('1', '3') then '남' else '여' end 성별
        , to_char(nvl(salary + (salary * commission_pct), salary),'9,999,999') 월급
    from employees
    where employee_id = 101;
    -- 같은 결과인데 프로시져로 보는 것이 속도가 더 빠르다
    -- sql문은 실행때 마다 문법 검사, 테이블 검사를 하지만 프로시져는 컴파일 할 때 다 확인을 했기 때문에 더 빠르다

  --*** PL/SQL 구문에서 변수의 사용법 두번째 ***---
    desc employees;
    --EMPLOYEE_ID    NOT NULL NUMBER(6)  
    
    
    
    create or replace procedure pcd_empInfo
  (p_employee_id  IN  employees.EMPLOYEE_ID%type) -- p_employee_id 매개변수의 데이터타입은 employees.employee_id%type 이다.
   -- 그런데 employees.employee_id%type의 뜻은 employees 테이블의 employee_id 컬럼의 데이터타입을 그대로 사용하겠다는 말이다. 
  is
    v_employee_id   employees.EMPLOYEE_ID%type;
    v_ename         varchar2(100);
    v_gender        nvarchar2(5);
    v_monthsal      varchar2(20);
  begin
    select employee_id
        , first_name || ' ' || last_name
        , case when substr(7,1) in ('1', '3') then '남' else '여' end
        , to_char(nvl(salary + (salary * commission_pct), salary),'9,999,999')
        into
        v_employee_id, v_ename, v_gender, v_monthsal -- 각 컬럼의 값을 변수에 넣어준 것이다.
    from employees
    where employee_id = p_employee_id;
    
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line('사원번호    사원명   성별   월급');
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line(V_employee_id || ' ' ||
                        V_ename || ' ' ||
                        V_gender || ' ' ||
                        V_monthsal);
  end pcd_empInfo;
  
    exec pcd_empInfo(101);
    
    exec pcd_empInfo(102);
    
    
    --*** PL/SQL 구문에서 변수의 사용법 세번째 ***---
    
    create or replace procedure pcd_empInfo
    (p_employee_id  IN  employees.EMPLOYEE_ID%type) -- p_employee_id 매개변수의 데이터타입은 employees.employee_id%type 이다.
    -- 그런데 employees.employee_id%type의 뜻은 employees 테이블의 employee_id 컬럼의 데이터타입을 그대로 사용하겠다는 말이다. 
    is
        --행을 row라고 부르기도 하지만 record로도 부른다
        -- record 타입 생성 --
        type myEmptype is record
        (
            empid employees.EMPLOYEE_ID%type
            ,ename varchar2(50)
            ,gender Nvarchar2(3)
            ,monthsal varchar2(15)
            ,age number(3)
        );
        
        --변수생성--
        v_rcd myEmptype;
        
    begin
    select employee_id
        , first_name || ' ' || last_name
        , case when substr(7,1) in ('1', '3') then '남' else '여' end
        , to_char(nvl(salary + (salary * commission_pct), salary),'9,999,999')
        , case when to_date(to_char(sysdate, 'yyyy') || substr(jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
                  then extract(year from sysdate) - ( to_number(substr(jubun,1,2)) + case when substr(jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1
                  else extract(year from sysdate) - ( to_number(substr(jubun,1,2)) + case when substr(jubun,7,1) in('1','2') then 1900 else 2000 end )
          end
        into
        v_rcd -- 각 컬럼의 값을 변수에 넣어준 것이다.
    from employees
    where employee_id = p_employee_id;
    
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line('사원번호    사원명   성별   월급      나이');
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line(v_rcd.empid || ' ' ||
                        v_rcd.ename || ' ' ||
                        v_rcd.gender || ' ' ||
                        v_rcd.monthsal || '    ' ||
                        v_rcd.age);
    end pcd_empInfo;
    
    --*** PL/SQL 구문에서 변수의 사용법 네번째 ***---
    
    create or replace procedure pcd_empInfo
    (p_employee_id  IN  employees.EMPLOYEE_ID%type)
    is
        v_all employees%rowtype;
        -- v_all 변수의 타입은 employees 테이블의 모든 컬럼을 받아주는 행타입이다.
        v_result varchar2(1000);
    begin
    select * into v_all
    from employees
    where employee_id = p_employee_id;
    
    --ps/sql에서 변수에 값을 넣는것은 select into 변수명과 := 방식이 가능
     v_result := v_all.employee_id || ' ' || 
                v_all.first_name || ' ' || v_all.last_name || ' ' ||
                case when substr(v_all.jubun, 7,1) in ('1', '3') then '남' else '여' end || ' ' ||
                to_char(nvl(v_all.salary + (v_all.salary * v_all.commission_pct), v_all.salary),'9,999,999')|| '     ' ||
                case when to_date(to_char(sysdate, 'yyyy') || substr(v_all.jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
                    then extract(year from sysdate) - ( to_number(substr(v_all.jubun,1,2)) + case when substr(v_all.jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1
                    else extract(year from sysdate) - ( to_number(substr(v_all.jubun,1,2)) + case when substr(v_all.jubun,7,1) in('1','2') then 1900 else 2000 end )
                end || ' ' ||
                nvl(to_char(v_all.department_id),'부서없음');
                
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line('사원번호    사원명   성별   월급      나이   부서번호');
    dbms_output.put_line(lpad('-',40,'-'));
    dbms_output.put_line(v_result);
    end pcd_empInfo;
    
    
 
    exec pcd_empInfo(101);
    
    exec pcd_empInfo(102);
    
    exec pcd_empInfo(178);
    
    
    
       ---------------------------------------------------------------------------
             ----- ***** 사용자 정의 함수(Function) ***** -----
   ---------------------------------------------------------------------------
   
   ----  주민번호를 입력받아서 성별을 알려주는 함수 func_gender(주민번호)을 생성해보겠습니다. ----
   /*
      [문법]
      create or replace function 함수명 
      (파라미터변수명  IN  파라미터변수의타입)
      return 리턴되어질타입
      is
         변수선언;
      begin
         실행문;
         return 리턴되어질값;
      end 함수명;
   */ 
    create or replace function func_gender 
    (p_jubun  IN  varchar2) -- varchar2(13) 와 같이 자리수를 쓰면 오류이다.!!!
    return nvarchar2 -- 이것도 nvarchar2(13) 와 같이 자리수를 쓰면 오류이다.!!!
    is
        v_result  nvarchar2(2);
    begin
        select case when substr(p_jubun, 7, 1) in('1', '3') then '남' else '여' end
                into
                v_result
        from dual;
        
        return v_result;
    end func_gender;
    -- Function FUNC_GENDER이(가) 컴파일되었습니다.
    
    select func_gender('9010201234567')
        , func_gender('9010202234567')
        , func_gender('0110203234567')
        , func_gender('0110204234567')
   from dual;
   
    create or replace function func_gender_2
    (p_jubun  IN  varchar2) -- varchar2(13) 와 같이 자리수를 쓰면 오류이다.!!!
    return nvarchar2 -- 이것도 nvarchar2(13) 와 같이 자리수를 쓰면 오류이다.!!!
    is
        v_result  nvarchar2(2);
    begin
        
        v_result := case when substr(p_jubun, 7, 1) in('1', '3') then '남' else '여' end;
        
        return v_result;
    end func_gender_2;
    -- Function FUNC_GENDER_2이(가) 컴파일되었습니다.
    
    select func_gender_2('9010201234567')
        , func_gender_2('9010202234567')
        , func_gender_2('0110203234567')
        , func_gender_2('0110204234567')
   from dual;
   
   -- := 보다 select into 가 관리하기에 편리하기에 select into를 더 선호한다
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 이름
        , jubun 주민번호
        , func_gender(jubun)성별
   from employees
   where func_gender(jubun) = '남'
   order by 1;
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 이름
        , jubun 주민번호
        , func_gender(jubun)성별
   from employees
   where func_gender(jubun) = '여'
   order by 1;
   
    select text
    from user_source
    where type = 'FUNCTION' and name ='FUNC_GENDER_2';
    
    
    
    
    ----  주민번호를 입력받아서 나이를 알려주는 함수 func_age(주민번호)을 생성해보세요. ----
    
    create or replace function func_age
    (p_jubun  IN  varchar2)
    return number
    is
        v_result number(3);
    begin
        select case when to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
                    then extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1
                    else extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end )
                end
                into
                v_result
        from dual;
        return v_result;
    end func_age;
    
    
    create or replace function func_age
    (p_jubun  IN  varchar2)
    return number
    is
        v_result number(3);
    begin
        v_result := case when to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
                    then extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1
                    else extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) end;
        return v_result;
    end func_age;
    
    
    select func_age('9602271392710')
    from dual;
    
    select func_age('9010201234567')
        , func_age('9010202234567')
        , func_age('0110203234567')
        , func_age('0110204234567')
   from dual;
   
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , jubun 주민번호
        , func_age(jubun) 나이1
   from employees
   order by 1;
   
   -- employees 테이블에서 나이가 20대인 여자와 40대인 남자 사원들만 
   -- 사원번호, 사원명, 주민번호, 성별, 나이를 나타내세요.
   
   
   select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , jubun 주민번호
        , func_gender(jubun) 성별
        , func_age(jubun) 나이
   from employees
   where (trunc(func_age(jubun),-1) = 20 and  func_gender(jubun) = '여') or
         (trunc(func_age(jubun),-1) = 40  and  func_gender(jubun) = '남')
   order by 성별, 나이 ;
   
   ----- *** 정년퇴직일을 구해주는 함수 만들기 *** -----
   /*
    여기서 정년퇴직일이라 함은 
    해당 사원의 생월이 3월에서 8월에 태어난 사람은 
    해당사원의 나이가 63세가 되는 년도의 8월말일(8월 31일)로 하고,
    해당사원의 생월이 9월에서 2월에 태어난 사람은 
    해당사원의 나이가 63세가 되는 년도의 2월말일(2월 28일 또는 2월 29일)로 한다.
  */
  
  func_retirement_day(주민번호) ==> 날짜
  
  create or replace function func_retirement_day
    (p_jubun  IN  varchar2)
    return date
    is
        v_result date;
  begin
      select case
                        when to_number(substr(p_jubun,3,2)) between 3 and 8 then  last_day(to_date((extract(year from (sysdate + (63 - korean_age)* 365.25)))||'0801','yyyymmdd'))
                        else last_day(to_date((extract(year from (sysdate + (63 - age)* 365.25)))||'0201','yyyymmdd'))
                        end
            into v_result
        from
        (
            select extract(year from sysdate) - to_number(case when substr(p_jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(p_jubun, 1, 2)) korean_age
                , case when (sysdate-to_date(extract(year from sysdate) || substr(p_jubun, 3,4),'yyyymmdd')) >= 0 then
                              to_number(extract(year from sysdate))-to_number(case when substr(p_jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(p_jubun, 1, 2))
                         else to_number(extract(year from sysdate))-to_number(case when substr(p_jubun, 7, 1) in ('1', '2') then '19' else '20' end || substr(p_jubun, 1, 2)) -1
                     end age
            from dual
        );
        return v_result;
  end func_retirement_day;
  
  select to_char(func_retirement_day('9010201234567'), 'yyyy-mm-dd')
        , func_retirement_day('9010202234567')
        , func_retirement_day('0110203234567')
        , func_retirement_day('0110204234567')
   from dual;
  
    /*
        --- [퀴즈] ---
        employees 테이블에서 모든 사원들에 대해
        사원번호, 사원명, 주민번호, 성별, 현재나이, 월급, 입사일자, 정년퇴직일, 정년까지근무개월수, 퇴직금 을 나타내세요.
        [사용자 정의 함수를 사용하여 구하세요] 입사일 * 정년퇴직일 근무년수 * 퇴직금
        
        여기서 정년퇴직일이라 함은 
        해당 사원의 생월이 3월에서 8월에 태어난 사람은 
        해당사원의 만나이가 63세가 되는 년도의 8월말일(8월 31일)로 하고,
        해당사원의 생월이 9월에서 2월에 태어난 사람은 
        해당사원의 만나이가 63세가 되는 년도의 2월말일(2월 28일 또는 2월 29일)로 한다.
    */
    
    
    select employee_id 사원번호
        , first_name || ' ' || last_name 사원명
        , jubun 주민번호
        , func_gender(jubun)성별
        , func_age(jubun)현재나이
        , to_char(nvl(salary + commission_pct * salary, salary),'999,999')월급
        , to_char(hire_date, 'yyyy-mm-dd') 입사일자
        , to_char(func_retirement_day(jubun), 'yyyy-mm-dd')정년퇴직일
        , trunc(months_between(func_retirement_day(jubun), hire_date)) 정년까지근무개월수
        , to_char(trunc(trunc(months_between(func_retirement_day(jubun), hire_date))/12)* nvl(salary + commission_pct * salary, salary),'$999,999')퇴직금
    from employees;

       ---- [퀴즈] 아래와 같은 결과물이 나오도록 프로시저( pcd_employees_info )를 생성하세요...
   ----       성별과 나이는 위에서 만든 사용자정의함수를 사용하세요..
   
   execute pcd_employees_info(101);  -- 여기서 숫자 101은 사원번호이다. 
   exec    pcd_employees_info(101);  -- 여기서 숫자 101은 사원번호이다.
   /*
      ------------------------------------------------------------
       사원번호    부서명    부서장명   사원명    입사일자   성별   나이
      ------------------------------------------------------------
        101       .....    ......   .......   ....     ...   ...
   */
    
    /*
           사원번호      부서명                         부서장명                      사원명    입사일자   성별   나이        부서번호
          --------     -------                       ---------                     ------------------------------      ------- 
          employees    departments                    employees                          employees                     employees   
                       department_id(P.K)             employee_id(사원번호 P.K)                                         department_id(F.K)  
                       manager_id(부서장사원번호 F.K)  
    */ 
    
    
    with
    de as
    (
        select D.department_id
            , D.department_name
            , D.manager_id
            , E.first_name || ' ' || E.last_name fullname
        from departments D join employees E
        on E.employee_id = D.manager_id
    )
    
    select E.employee_id
        , D.department_name
        , fullname manager_name
        , E.first_name || ' ' || E.last_name employee_name
        , E.hire_date
        , func_gender(jubun) gender
        , func_age(jubun) age
        , E.department_id
    from employees E left join departments D
    on E.department_id = D.department_id
    left join de A
    on D.department_id = A.department_id
    order by 1;
    
    
     with
        de as
        (
            select D.department_id
                , D.department_name
                , D.manager_id
                , E.first_name || ' ' || E.last_name fullname
            from departments D join employees E
            on E.employee_id = D.manager_id
        )
    

    create or replace procedure pcd_employees_info (p_employee_id in number)
    is
        v_employee_id number(3);
        v_department_name varchar(20);
        v_manager_name varchar(50);
        v_employee_name varchar(50);
        v_hire_date date;
        v_gender nvarchar2(3);
        v_age number(3);
        v_department_id number(4);
        --v_result varchar(1000);
        
    begin
    
        with
        de as
        (
            select D.department_id
                , D.department_name
                , D.manager_id
                , E.first_name || ' ' || E.last_name fullname
            from departments D join employees E
            on E.employee_id = D.manager_id
        )
        
        select E.employee_id
            , D.department_name
            , fullname manager_name
            , E.first_name || ' ' || E.last_name employee_name
            , E.hire_date
            , func_gender(jubun) gender
            , func_age(jubun) age
            , E.department_id
            into
                v_employee_id, v_department_name, v_manager_name, v_employee_name, v_hire_date, v_gender, v_age, v_department_id
            from employees E left join departments D
            on E.department_id = D.department_id
            left join de A
            on D.department_id = A.department_id
            where p_employee_id = E.employee_id;
            
            
            DBMS_OUTPUT.PUT_LINE(lpad('—',60,'—'));
            DBMS_OUTPUT.PUT_LINE('사원번호      부서명           부서장명        사원명           입사일자      성별    나이   부서번호');
            DBMS_OUTPUT.PUT_LINE(lpad('—',60,'—'));
            DBMS_OUTPUT.PUT_LINE(v_employee_id || '         ' || v_department_name || '     ' || v_manager_name || '   ' || v_employee_name || '      ' || v_hire_date || '     ' || v_gender || '      ' || v_age || '     ' || v_department_id);

    end pcd_employees_info;
    
    execute pcd_employees_info(104);
    
    
    
    
    
    ---------------- ==== **** 제어문(if) 문 *** ==== ----------------------
    
    /*
       ※ 형식
       
       if     조건1  then  실행문장1;
       elsif  조건2  then  실행문장2;
       elsif  조건3  then  실행문장3;
       else               실행문장4;
       end if;
       
    */
    
    -- 주민번호를 입력받아서 성별을 알려주는 함수 func_gender_3 (주민번호)을 생성하세요. --
    create or replace function func_gender_3 (p_jubun in varchar2) return nvarchar2
    is
        v_gender_num  number(1);
        v_result nvarchar2(2);
    begin
        v_gender_num := substr(p_jubun, 7, 1);
        
        if v_gender_num = '1' or v_gender_num ='3' then v_result := '남';
        else v_result := '여';
        end if;
        return v_result;
    end func_gender_3;
    
    create or replace function func_gender_4 (p_jubun in varchar2) return nvarchar2
    is
        v_gender_num  number(1);
        v_result nvarchar2(2);
    begin
        v_gender_num := substr(p_jubun, 7, 1);
        
        if v_gender_num = '1' or v_gender_num = '3' then v_result := '남';
        else v_result := '여';
        end if;
        return v_result;
    end func_gender_4;
    
    
    create or replace function func_gender_5 (p_jubun in varchar2) return nvarchar2
    is
        v_gender_num  number(1);
        v_result nvarchar2(2);
    begin
        v_gender_num := substr(p_jubun, 7, 1);
        
        if v_gender_num in ('1', '3') then v_result := '남';
        else v_result := '여';
        end if;
        return v_result;
    end func_gender_5;
    
    select employee_id, first_name, jubun
    ,func_gender(jubun)
    ,func_gender_2(jubun)
    ,func_gender_3(jubun)
    ,func_gender_4(jubun)
    ,func_gender_5(jubun)
    from employees
    order by 1;
    
     ---------- ===== **** 반복문 **** ===== ----------
  /*
     반복문에는 종류가 3가지가 있다.
  
     1. 기본 LOOP 문
     2. FOR LOOP 문
     3. WHILE LOOP 문
  */
  
  
  ----- ====== ****  1. 기본 LOOP 문 **** ====== -----
  /*
      [문법]
      LOOP
          반복해야할 실행문장;
      EXIT WHEN 탈출조건;   -- 탈출조건이 참 이라면 LOOP 를 탈출한다.
      END LOOP;
  */
  
  ----- ====== ****  2. FOR LOOP 문 **** ====== -----
  /*
      [문법]
      
      for 변수  in  [reverse]  시작값..마지막값  loop
          반복해야할 실행문장;
      end loop;
  */
  
    ----- ====== ****  3. WHILE LOOP 문 **** ====== -----
  /*
     [문법]
     WHILE  조건  LOOP
         반복해야할 실행문장;   -- 조건이 참이라면 실행함. 조건이 거짓이 되어지면 반복문을 빠져나간다.
     END LOOP;
     
     WHILE NOT 조건  LOOP
         반복해야할 실행문장;   -- 조건이 참이라면 반복문을 빠져나간다.
     END LOOP;
  */
  

    declare
        v_cnt number(2) := 1; --변수의 선언 및 초기화
    begin
        LOOP
          DBMS_OUTPUT.PUT_LINE(v_cnt||'.안녕 오라클!!');
          v_cnt := v_cnt + 1;
        EXIT WHEN v_cnt > 10;   -- 탈출조건이 참 이라면 LOOP 를 탈출한다.
        END LOOP;
    end;
    
    begin
        for i in reverse  1..10  loop
              DBMS_OUTPUT.PUT_LINE(i||'.Hi Oracle!!');
          end loop;
    end;
    
    Declare
        v_cnt number(2) := 1;
    begin
        WHILE  v_cnt <= 10  LOOP
             DBMS_OUTPUT.PUT_LINE(v_cnt||'.안녕 Java!!');   -- 조건이 참이라면 실행함. 조건이 거짓이 되어지면 반복문을 빠져나간다.
             v_cnt := v_cnt + 1;
        END LOOP;
    end;
    
    Declare
        v_cnt number(2) := 1;
    begin
        WHILE NOT v_cnt > 10  LOOP
             DBMS_OUTPUT.PUT_LINE(v_cnt||'.안녕 Java!!');   -- 조건이 참이라면 실행함. 조건이 거짓이 되어지면 반복문을 빠져나간다.
             v_cnt := v_cnt + 1;   -- 조건이 참이라면 반복문을 빠져나간다.
         END LOOP;
     end;
     
     create table tbl_looptest_1
     (bunho number
     ,name varchar(50)
     );
     --Table TBL_LOOPTEST_1이(가) 생성되었습니다.
     
     --*** tbl_looptest_1 테이블에 행을 20000개를 insert 해보겠습니다..***---
     create or replace procedure pcd_looptest_1_insert (p_name in tbl_looptest_1.name%type, p_count in number) --p_count 에 20000을 넣을 것이다.
     is
        v_bunho tbl_looptest_1.bunho%type := 0; -- 변수의 초기화
     begin
        loop
            v_bunho := v_bunho + 1;
            exit when v_bunho > p_count; -- 20001 > 20000 탈출조건이 참 이라면 loop를 탈출한다.
            insert into tbl_looptest_1(bunho, name) values(v_bunho, p_name || v_bunho);
        end loop;
     end pcd_looptest_1_insert;
     
     exec pcd_looptest_1_insert('이순신', 20000);
     
     select *
     from tbl_looptest_1
     order by 1 asc;
     
     truncate table tbl_looptest_1;
     rollback;
 
 
    --*** 이름이 없는 프로시저로 tbl_looptest_1 테이블에 행을 30000개를 insert 해보겠습니다..***---

    Declare
        v_bunho tbl_looptest_1.bunho%type := 0; --변수의 선언 및 초기화
        v_name Nvarchar2(10) := '차은우';
     begin
        loop
            v_bunho := v_bunho + 1;
            exit when v_bunho > 30000; -- 30001 > 30000 탈출조건이 참 이라면 loop를 탈출한다.
            insert into tbl_looptest_1(bunho, name) values(v_bunho, v_name || v_bunho);
        end loop;
     end;
     
    --*** 이름이 없는 프로시저로 tbl_looptest_1 테이블에 행을 40000개를 insert 해보겠습니다..***---
    ------- 2. for loop 를 이용한 방법 -------------------
     
     Declare
        v_name Nvarchar2(10) := '이혜리';
     begin
            for i in 1.. 40000 loop --변수 i에 맨처음에는 1이 들어가고 매번 1씩 증가된 값이 i에 들어가는데 40000 까지 i에 들어간다
            insert into tbl_looptest_1(bunho, name) values(i, v_name || i);
        end loop;
     end;
     
     select *
     from tbl_looptest_1
     order by 1 asc;
     
     
     
    --*** 이름이 없는 프로시저로 tbl_looptest_1 테이블에 행을 40000개를 insert 해보겠습니다..***---
     Declare
        v_cnt number(5):= 1; --변수의 선언 및 초기화
        v_name Nvarchar2(10) := '강감찬';
     begin
            while not(v_cnt > 50000) loop    --not(탈출 조건) 탈출 조건이 참이라면 전체가 거짓이 되어지므로
            insert into tbl_looptest_1(bunho, name) values(v_cnt, v_name || v_cnt);
            v_cnt := v_cnt + 1;
        end loop;
     end;
     
     select func_age('960227139271'), func_age('960227239271'), func_age('000423329817'), func_age('030423429817')
     from dual;
     
     select func_gender('960227'), func_gender('960227'), func_gender('00042'), func_gender('030423')
     from dual;
     
     select func_age('9t0227139271'), func_age('9t0227239271'), func_age('0t0423329817'), func_age('0t0423429817')
     from dual;
     
     
     --------------------------------------------------------------------------------------------9월30일-------------------------------------------------
     --- ** 주민번호를 입력받아서 만나이를 알려주는 함수 func_age(주민번호)를 생성하겠습니다. ** ----
     select text
     from user_source
     where type = 'FUNCTION' and name = 'FUNC_AGE';
     
     /*
        "function func_age
        "
        "    (p_jubun  IN  varchar2)
        "
        "    return number
        "
        "    is
        "
        "        v_result number(3);
        "
        "    begin
        "
        "        v_result := case when to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
        "
        "                    then extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1
        "
        "                    else extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) end;
        "
        "        return v_result;
        "
        end func_age;
     */
     
     
     create or replace function FUNC_AGE
            (p_jubun  IN  varchar2)
            return number
            is
                error_jubun exception; --error_jubun은 사용자(개발자)가 정의하는 예외절(Exception)임을 선언한다.
                i number(2) := 0;
                v_result number(3);
                v_year number(4);
            begin
                if length(p_jubun) != 13 then raise error_jubun;
                end if;
                
                loop
                    i := i+1;
                    exit when i > 13 ;
                    if not (substr(p_jubun, i ,1) between '0' and '9') then
                        raise error_jubun;
                    end if;
                end loop;
                
                if
                    substr(p_jubun, 7, 1) in('1', '2') then v_year := 1990;
                elsif
                    substr(p_jubun, 7, 1) in('3', '4') then v_year := 2000;
                else
                    raise error_jubun;
                end if;
              
                if
                    (to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd')) > 0
                    then v_result := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_j   ubun,7,1) in('1','2') then 1900 else 2000 end ) - 1
              --  else
              --      v_result := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end )
                end if;
                
                return v_result;
                
                exception
                    when error_jubun then
                        raise_application_error(-20001, '>>올바르지 않은 주민번호 입니다.<<');
                        -- -20001은 오류번호로써, 사용자가 정의해주는 exception 에 대해서는 오류번호를 -20001 부터 -20999 까지만 사용하도록 오라클에서 비워두었다.
        end func_age;
        
        
    insert into departments(department_id, department_name, manager_id, location_id)
    --values('누칼협?',1223,444,2222)
    
    ------------------------선생님 방식-----------------------
    create or replace function func_age 
    (p_jubun  IN  varchar2)   
    return   number          
    is
       error_jubun  exception;  -- error_jubun 은 사용자가 정의하는 예외절(Exception)임을 선언한다. 
       i number(2) := 0;
       v_year number(4);
       v_age  number(3);
       
    begin
       if length(p_jubun) != 13 then raise error_jubun;
       end if;
       
       loop
          i := i+1;
          exit when i > 13;
          if not (substr(p_jubun, i, 1) between '0' and '9') then
             raise error_jubun;
          end if;
       end loop;
       
       if     substr(p_jubun, 7, 1) in('1','2') then v_year := 1900; 
       elsif  substr(p_jubun, 7, 1) in('3','4') then v_year := 2000;
       else   raise error_jubun;
       end if;
      
       if to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
          then v_age := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1; 
       else v_age := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ); 
       end if; 
      
       return v_age;
    
       exception
          when error_jubun then 
               raise_application_error(-20001, '>> 올바르지 않은 주민번호 입니다. <<');
               --   -20001 은 오류번호로써, 사용자가 정의해주는 exception 에 대해서는 오류번호를 -20001 부터 -20999 까지만 사용하도록 오라클에서 비워두었다. 
    end func_age;
    -- Function FUNC_AGE이(가) 컴파일되었습니다.

  select func_age('900930123456')
  from dual;
  /*
   ORA-20001: >> 올바르지 않은 주민번호 입니다. <<
   ORA-06512: "HR.FUNC_AGE",  36행
  */
  
  select func_age('90s9301234567')
  from dual;
  /*
   ORA-20001: >> 올바르지 않은 주민번호 입니다. <<
   ORA-06512: "HR.FUNC_AGE",  36행
  */
  
  select func_age('9009307234567')
  from dual;
  /*
   ORA-20001: >> 올바르지 않은 주민번호 입니다. <<
   ORA-06512: "HR.FUNC_AGE",  36행
  */
  
  select func_age('9009301234567')
  from dual;
  
  select func_age_2('9009301234567')
  from dual;
    
    select name
    from user_source;
    
    
    -- *** 함수 func_age_2를 for loop 문을 사용하여 올바르게 수정하세요--
    create or replace function func_age_2 
    (p_jubun  IN  varchar2)   
    return   number          
    is
       error_jubun  exception;  -- error_jubun 은 사용자가 정의하는 예외절(Exception)임을 선언한다. 
       v_year number(4);
       v_age  number(3);
       
    begin
       if length(p_jubun) != 13 then raise error_jubun;
       end if;
       
       for i in 1..13 loop
          if not (substr(p_jubun, i, 1) between '0' and '9') then
             raise error_jubun;
          end if;
       end loop;
       
       if     substr(p_jubun, 7, 1) in('1','2') then v_year := 1900; 
       elsif  substr(p_jubun, 7, 1) in('3','4') then v_year := 2000;
       else   raise error_jubun;
       end if;
      
       if to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
          then v_age := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1; 
       else v_age := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ); 
       end if; 
      
       return v_age;
    
       exception
          when error_jubun then 
               raise_application_error(-20001, '>> 올바르지 않은 주민번호 입니다. <<');
               --   -20001 은 오류번호로써, 사용자가 정의해주는 exception 에 대해서는 오류번호를 -20001 부터 -20999 까지만 사용하도록 오라클에서 비워두었다. 
    end func_age_2;
    -- Function FUNC_AGE이(가) 컴파일되었습니다.
    
    select func_age_2('9009301234567')
  from dual;
  
  select func_age('900930123456')
  from dual;
  
  select func_age_2('9009301234567')
  from dual;
    
    -- *** 함수 func_age_3를 while loop 문을 사용하여 올바르게 수정하세요--
    
    create or replace function func_age_3
    (p_jubun  IN  varchar2)   
    return   number          
    is
       error_jubun  exception;  -- error_jubun 은 사용자가 정의하는 예외절(Exception)임을 선언한다. 
       i number(2) := 0;
       v_year number(4);
       v_age  number(3);
       
    begin
       if length(p_jubun) != 13 then raise error_jubun;
       end if;
       
       while not(i = 14 or not(substr(p_jubun, i, 1) between '0' and '9')) loop
          i := i+1;
       end loop;
       
       if i != 14 then raise error_jubun;
       end if;
       
       if     substr(p_jubun, 7, 1) in('1','2') then v_year := 1900; 
       elsif  substr(p_jubun, 7, 1) in('3','4') then v_year := 2000;
       else   raise error_jubun;
       end if;
      
       if to_date(to_char(sysdate, 'yyyy') || substr(p_jubun, 3, 4), 'yyyymmdd') - to_date( to_char(sysdate, 'yyyymmdd'), 'yyyymmdd') > 0 
          then v_age := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ) - 1; 
       else v_age := extract(year from sysdate) - ( to_number(substr(p_jubun,1,2)) + case when substr(p_jubun,7,1) in('1','2') then 1900 else 2000 end ); 
       end if; 
      
       return v_age;
    
       exception
          when error_jubun then 
               raise_application_error(-20001, '>> 올바르지 않은 주민번호 입니다. <<');
               --   -20001 은 오류번호로써, 사용자가 정의해주는 exception 에 대해서는 오류번호를 -20001 부터 -20999 까지만 사용하도록 오라클에서 비워두었다. 
    end func_age_3;
    
    select func_age_3('9009301234567')
  from dual;
  
  select func_age_3('900930123456')
  from dual;
  
  select func_age_3('9009301234567')
  from dual;
  
    create table tbl_member_test1
    (userid      varchar2(20)
    ,passwd      varchar2(20) not null
    ,name        varchar2(30) not null
    ,constraint  PK_tbl_member_test1_userid primary key(userid)
    );
    -- Table TBL_MEMBER_TEST1이(가) 생성되었습니다.
    
    -- [퀴즈] tbl_member_test1 테이블에 insert 해주는 pcd_tbl_member_test1_insert 라는 프로시저를 작성하세요.  
    exec pcd_tbl_member_test1_insert('hongkd','qwer1234$','홍길동'); --> 정상적으로 insert 되어진다.
    
    exec pcd_tbl_member_test1_insert('eomjh','a3$','유관순');       --> 오류메시지 -20002  '암호는 최소 5글자 이상이면서 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다.' 이 뜬다. 그러므로 insert 가 안되어진다. 
    exec pcd_tbl_member_test1_insert('eomjh','abc1234','유관순');   --> 오류메시지 -20002  '암호는 최소 5글자 이상 20글자 이하의 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다.' 이 뜬다. 그러므로 insert 가 안되어진다.
    exec pcd_tbl_member_test1_insert('eomjh','abc1234ㅇ!','유관순');   --> 오류메시지 -20002  '암호는 최소 5글자 이상 20글자 이하의 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다.' 이 뜬다. 그러므로 insert 가 안되어진다.
    
    create or replace procedure  pcd_tbl_member_test1_insert(p_id varchar2, p_pwd varchar2, p_name varchar2)
    is
        error_input exception;
        number_cnt number(2) := 0;
        char_cnt number(2) := 0;
        special_cnt number(2) := 0;
    begin
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
        
         
    exception
        when error_input then
        raise_application_error(-20002, '암호는 최소 5글자 이상 20글자 이하의 영문자 및 숫자 및 특수기호가 혼합되어져야 합니다');
    end pcd_tbl_member_test1_insert;
    
    rollback;
    
    exec pcd_tbl_member_test1_insert('hongkd','qwer1234$','홍길동');
    
    select *
    from tbl_member_test1;
    
    select ascii('A') , ascii('Z'), ascii('a'), ascii('z')
    from dual;
    
    select ascii('0') , ascii('9'), ascii(' ')
    from dual;
    
    select ascii('!') , ascii('@'), ascii(')')
    from dual;
    
    select ascii('ㄱ') , ascii('ㅎ'), ascii('ㅏ'), ascii('ㅣ')
    from dual;
    
    select ascii('가') , ascii('나'), ascii('히'), ascii('힣')
    from dual;
    
    truncate table tbl_member_test1;
    delete
    from tbl_member_test1;
    
    commit;
    
    
    ---- **** 나중에 Spring 시간에 사용할 것이다. ***----- 웹에서 중요합니다.
    
    '서울시강북구 쌍용강북myface.jpg교육센터입니다. 좋은 하루 되세요~~ 쌍용강북12345.jpg교육센터 복된하루 건강한 하루 쌍용강북notebook.png교육센터'
    
    --결과:
    --myface.jpg/12345.jpg/notebook.png
    
    
    Declare
        v_content varchar2(4000);
        v_result varchar2(4000);
        v_length number(3);
    begin
        select '서울시강북구 쌍용강북myface.jpg교육센터입니다. 좋은 하루 되세요~~ 쌍용강북12345.jpg교육센터 복된하루 건강한 하루 쌍용강북notebook.png교육센터'
            into v_content
        from dual;
        
        v_length := length('쌍용강북');
        
        while not(instr(v_content,'쌍용강북', 1) = 0) loop
            v_content := substr(v_content, instr(v_content,'쌍용강북', 1)+v_length);
            v_result := substr(v_content, 1, instr(v_content, '교육센터')-1);
            dbms_output.put_line(v_result);
            
            dbms_output.put_line(lpad('-', 40, '-'));
        end loop;
    end;
    
    --------------------------------------------------------------------------------------------------------------------------------------
    
    Declare
        v_content varchar2(4000);
        v_result varchar2(4000);
        v_length number(3);
    begin
        select '서울시강북구 쌍용강북myface.jpg교육센터입니다. 좋은 하루 되세요~~ 쌍용강북12345.jpg교육센터 복된하루 건강한 하루 쌍용강북notebook.png교육센터'
            into v_content
        from dual;
        
        v_length := length('쌍용강북');
        
        while not(instr(v_content,'쌍용강북', 1) = 0) loop
            v_content := substr(v_content, instr(v_content,'쌍용강북', 1)+v_length);
            dbms_output.put_line(v_content);
            
            v_result := v_result || '/'||substr(v_content, 1, instr(v_content, '교육센터')-1);
           
        end loop;
        dbms_output.put_line(substr(v_result, 2));
    end;
    
    select null || '하하하'
    from dual;
    
    --————————————————————————————————————————————————————————————————————————————————————
    
    create table tbl_contents
    (seq      number(4)
    ,content  varchar2(4000)
    );
    
    insert into tbl_contents(seq, content) values(1, '<p>우헤헤헤</p>');
    insert into tbl_contents(seq, content) values(2, '<p>쉐보레전면 파일 첨부 연습입니다. ㅎㅎㅎ </p>');
    insert into tbl_contents(seq, content) values(3, '<p>안녕하세요~~ ㅎㅎ</p><p> </p><p><img src="/myspring/resources/photo_upload/20240701103900418624245312000.jpg" title="20240701103900418624245312000.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240701103900418624245316400.jpg" title="20240701103900418624245316400.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240701103900418624251603700.jpg" title="20240701103900418624251603700.jpg"></p><p> </p><p>다음에 또 뵐께요~~~</p><p> </p><p> </p><p><br style="clear:both;"> </p>   20240701103900418624245312000.jpg,20240701103900418624245316400.jpg,20240701103900418624251603700.jpg');
    insert into tbl_contents(seq, content) values(4, '<p>안녕하세요?</p><p> </p><p><img src="/myspring/resources/photo_upload/20240701104205418809040269000.png" title="20240701104205418809040269000.png"  width="400" height="297" rwidth="400" rheight="297.3333333333333" style="border-color: rgb(0, 0, 0); width: 400px; height: 297.333px;"><br style="clear:both;"> </p><p>다른 사진 또 있어요</p><p><img src="/myspring/resources/photo_upload/20240701104416418940042020100.png" title="20240701104416418940042020100.png" sqeid="QE_171979825933158231" width="400" height="296" rwidth="400" rheight="296.77419354838713" style="border-color: rgb(0, 0, 0); width: 400px; height: 296.774px;"></p><p> </p><p>안녕히 계세요~~</p><p> </p><p> </p><p><br style="clear:both;"> </p>   20240701104205418809040269000.png,20240701104416418940042020100.png');
    insert into tbl_contents(seq, content) values(5, '<p><span style="font-size: 18pt;"><span style="color: rgb(255, 0, 0);">연습</span><span style="color: rgb(58, 50, 195);">입니다.</span></span></p><p><span style="font-size: 18pt;">ㅎㅎ</span></p><p><img src="/myspring/resources/photo_upload/2024080421002699901943109900.jpg" title="2024080421002699901943109900.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/2024080421002699901943961300.jpg" title="2024080421002699901943961300.jpg"><br style="clear:both;"> </p><p><span style="font-size: 18pt; color: rgb(255, 0, 0);">좋은 옷 입니다.</span></p><p> </p>   2024080421002699901943109900.jpg,2024080421002699901943961300.jpg');
    insert into tbl_contents(seq, content) values(6, '<p>테스트</p><p> </p><p><img src="/myspring/resources/photo_upload/20240907202803767533578451100.jpg" title="20240907202803767533578451100.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240907202803767533578335200.jpg" title="20240907202803767533578335200.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240907202803767533578462400.jpg" title="20240907202803767533578462400.jpg"><br style="clear:both;"> </p>   20240907202803767533578451100.jpg,20240907202803767533578335200.jpg,20240907202803767533578462400.jpg');
    insert into tbl_contents(seq, content) values(7, '<p>하하하하</p>');
    insert into tbl_contents(seq, content) values(8, '<p>get 방식으로 보내는 데이터 값에 [ ] 이 들어갈 경우 </p><p>HTTP Status 400 – Bad Request</p><p><span style="white-space: normal;"> Message : Invalid character found in the request target    </span></p><p><span style="white-space: normal;"> The valid characters are defined in RFC 7230 and RFC 3986 이 발생하는데</span></p><p>이것을 해결함.!!</p>');
    insert into tbl_contents(seq, content) values(9, '<p>연습2 입니다.</p><p> </p><p><img src="/myspring/resources/photo_upload/202409180206113074582447900.jpg" title="202409180206113074582447900.jpg"  width="200" height="148" rwidth="200" rheight="148.38709677419357" style="border-color: rgb(0, 0, 0); width: 200px; height: 148.387px;"></p><p> </p><p>좋은 옷 입니다.</p><p> </p><p><img src="http://localhost:9090/resources/photo_upload/202409180206113074582679400.jpg" title="202409180206113074582679400.jpg" sqeid="QE_172659282302631046" width="200" height="148" rwidth="200" rheight="148.38709677419357" style="border-color: rgb(0, 0, 0); width: 200px; height: 148.387px;"></p><p> </p><p>좋아요~~~</p><p><br style="clear:both;"><img src="/myspring/resources/photo_upload/202409180206113074583190500.jpg" title="202409180206113074583190500.jpg" sqeid="QE_172659281994283452" width="200" height="89" rwidth="200" rheight="89.40092165898618" style="border-color: rgb(0, 0, 0); width: 200px; height: 89.4009px;"><br style="clear:both;"><br style="clear:both;"> </p><p><br style="clear:both;"> </p>   202409180206113074582447900.jpg,202409180206113074583190500.jpg');
    insert into tbl_contents(seq, content) values(10, '<p>테스트1</p>');
    insert into tbl_contents(seq, content) values(11, '<p>test2</p><p><img src="/myspring/resources/photo_upload/20240922165110401783618711400.jpg" title="20240922165110401783618711400.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240922165110401783618883900.jpg" title="20240922165110401783618883900.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240922165110401783618706200.jpg" title="20240922165110401783618706200.jpg"><br style="clear:both;"> </p>   20240922165110401783618711400.jpg,20240922165110401783618883900.jpg,20240922165110401783618706200.jpg');
    insert into tbl_contents(seq, content) values(12, '<p>test3 입니다.</p><p><img src="/myspring/resources/photo_upload/20240922204904416058240743200.jpg" title="20240922204904416058240743200.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240922204904416058250359900.jpg" title="20240922204904416058250359900.jpg"><br style="clear:both;"> </p>   20240922204904416058240743200.jpg,20240922204904416058250359900.jpg');
    insert into tbl_contents(seq, content) values(13, '<p>연습99 입니다. </p><p><img src="/myspring/resources/photo_upload/20240922205512416425644073200.jpg" title="20240922205512416425644073200.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240922205512416425644073200.jpg" title="20240922205512416425644073200.jpg"><br style="clear:both;"> </p>   20240922205512416425644073200.jpg,20240922205512416425644073200.jpg');
    
    commit;
    
    select *
    from tbl_contents;
    
    /*
        <p>안녕하세요~~ ㅎㅎ</p><p> </p><p><img src="/myspring/resources/photo_upload/20240701103900418624245312000.jpg" title="20240701103900418624245312000.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240701103900418624245316400.jpg" title="20240701103900418624245316400.jpg"><br style="clear:both;"><img src="/myspring/resources/photo_upload/20240701103900418624251603700.jpg" title="20240701103900418624251603700.jpg"></p><p> </p><p>다음에 또 뵐께요~~~</p><p> </p><p> </p><p><br style="clear:both;"> </p>   20240701103900418624245312000.jpg,20240701103900418624245316400.jpg,20240701103900418624251603700.jpg
        <p>안녕하세요?</p><p> </p><p><img src="/myspring/resources/photo_upload/20240701104205418809040269000.png" title="20240701104205418809040269000.png"  width="400" height="297" rwidth="400" rheight="297.3333333333333" style="border-color: rgb(0, 0, 0); width: 400px; height: 297.333px;"><br style="clear:both;"> </p><p>다른 사진 또 있어요</p><p><img src="/myspring/resources/photo_upload/20240701104416418940042020100.png" title="20240701104416418940042020100.png" sqeid="QE_171979825933158231" width="400" height="296" rwidth="400" rheight="296.77419354838713" style="border-color: rgb(0, 0, 0); width: 400px; height: 296.774px;"></p><p> </p><p>안녕히 계세요~~</p><p> </p><p> </p><p><br style="clear:both;"> </p>   20240701104205418809040269000.png,20240701104416418940042020100.png
        
        
    */
    
    select seq, substr(func_imgfile_name(seq), 2) AS "이미지파일명"
    from tbl_contents
    order by seq desc;
    
    /*
        -------------------------------------------------------------------------------------
        SEQ  이미지파일명
        -------------------------------------------------------------------------------------
        13    20240922205512416425644073200.jpg/20240922205512416425644073200.jpg
        12    20240922204904416058240743200.jpg/20240922204904416058250359900.jpg
        11    20240922165110401783618711400.jpg/20240922165110401783618883900.jpg/20240922165110401783618706200.jpg
        10   
        9    202409180206113074582447900.jpg/202409180206113074583190500.jpg
        8   
        7   
        6    20240907202803767533578451100.jpg/20240907202803767533578335200.jpg/20240907202803767533578462400.jpg
        5    2024080421002699901943109900.jpg/2024080421002699901943961300.jpg
        4    20240701104205418809040269000.png/20240701104416418940042020100.png
        3    20240701103900418624245312000.jpg/20240701103900418624245316400.jpg/20240701103900418624251603700.jpg
        2   
        1   
    */
    
    create or replace function func_imgfile_name
    (p_seq in number)
    return varchar2
    is
        v_content tbl_contents.content%type;
        v_result varchar2(4000);
        v_length number(2);
    begin
        select content into v_content
        from tbl_contents
        where seq =p_seq;
        
        v_length := length('<img src="/myspring/resources/photo_upload/');
        
        while not(instr(v_content, '<img src="/myspring/resources/photo_upload/', 1) = 0) loop
            v_content := substr(v_content, instr(v_content, '<img src="/myspring/resources/photo_upload/' ) + v_length);
              v_result := v_result || '/' || substr(v_content, 1, instr(v_content, '" title="', 1) - 1);
        end loop;
        
        return v_result;
        
    end func_imgfile_name;
    
    select seq, content, substr(func_imgfile_name(seq),2) as "이미지파일명"
    from tbl_contents
    order by seq desc;
    
    select seq, substr(func_imgfile_name(seq),2) as "이미지파일명"
    from tbl_contents
    order by seq desc;
    
    
    
    
    
    
    
    
    
    
    
    
    
      ------------ ***** 사용자 정의 예외절(EXCEPTION) ***** ----------------
     예외절 = 오류절
     
     ※ 형식
     
     exception
          when  익셉션이름1  [or 익셉션이름2]  then
                실행문장1;
                실행문장2;
                실행문장3;
                
          when  익셉션이름3  [or 익셉션이름4]  then
                실행문장4;
                실행문장5;
                실행문장6; 
                
          when  others  then  
                실행문장7;
                실행문장8;
                실행문장9; 
   ------------------------------------------------------------------ 
    
    
    /*
      === tbl_member_test1 테이블에 insert 할 수 있는 요일명과 시간을 제한해 두겠습니다. ===
        
          tbl_member_test1 테이블에 insert 할 수 있는 요일명은 월,화,수,목,금 만 가능하며
          또한 월,화,수,목,금 중에 오후 2시 부터 오후 5시 이전까지만(오후 5시 정각은 안돼요) insert 가 가능하도록 하고자 한다.
          만약에 insert 가 불가한 요일명(토,일)이거나 불가한 시간대에 insert 를 시도하면 
          '영업시간(월~금 14:00 ~ 16:59:59 까지) 아니므로 입력불가함!!' 이라는 오류메시지가 뜨도록 한다. 
   */
   
    select to_char(sysdate, 'd')  -- sysdate 의 주의 일요일 부터(지금은 2024년 9월 29일) sysdate(지금은 2024년 9월 30일) 까지 며칠째 인지를 알려주는 것이다. 
                                   -- '1'(일요일) '2'(월요일) '3'(화요일) '4'(수요일) '5'(목요일) '6'(금요일) '7'(토요일)   
    from dual;
    
    
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
    
    -- 현재시각은 월요일 오후 4시 47분 입니다.
    exec pcd_tbl_member_test1_insert('leehr','qwer1234$','이혜리');
    
    select *
    from tbl_member_test1;
    
    commit;
    
    -- 현재시각은 월요일 오후 5시 0분 입니다.
    exec pcd_tbl_member_test1_insert('leess','aaaa0070$','이순신');
    exec pcd_tbl_member_test1_insert('leejm','qwer0070$','이재명');
    
    
    /*
        자바를 예를 들면
        int jumsu = 0;
        변수 jumsu 에 90, 95, 88, 75, 91, 80 이라는 6개의 점수를 입력하고자 한다.
        jumsu = 90;
        jumsu = 85;
        jumsu = 88;
        jumsu = 75;
        jumsu = 91;
        jumsu = 80;
        
        최종적으로 변수 jumsu 에 담긴 값은 80 이 된다.
        
        그래서 자바에서는 아래와 같이 배열로 만들어서 한다.
        int[] arr_jumsu = new int[6];
        
        arr_jumsu[0] = 90;
        arr_jumsu[1] = 85;
        arr_jumsu[2] = 88;
        arr_jumsu[3] = 75;
        arr_jumsu[4] = 91;
        arr_jumsu[5] = 80;
        
        ————————————————————
        | 90 | 85 | 88 | 75 | 91 | 80 |
        ————————————————————
        
        오라클에서는 컬럼을 1갬나 가지는 것으로 select 를 하면
        자바의 1차원 배열의 모양을 90도 회전한 것과 같다
        즉, 아래처럼 컬럼을 1개만 가지는 것으로 select 한 결과물을 table 타입 변수로 받아서
        마치 자바의 배열처럼 사용한다.
        
        select employee_id
        from employees
        where department_id = 30;
        
        EMPLOYEE_ID
        -----------
        | 114 |
        ————
        | 115 |
        ————
        | 116 |
        ————
        | 117 |
        ————
        | 118 |
        ————
        | 119 |
        ————
    */


    with E as
    (
      select department_id
           , employee_id
           , first_name || ' ' || last_name AS ENAME
           , to_char(hire_date, 'yyyy-mm-dd') AS HIREDATE
           , func_gender(jubun) AS GENDER
           , func_age(jubun) AS AGE
      from employees
      where department_id = 30
    )
    select E.department_id, D.department_name, E.employee_id, E.ename, E.hiredate, E.gender, E.age
    from departments D right join E
    on D.department_id = E.department_id;
    
    
    
    exec pcd_employees_info_deptid(10);
    -- PL/SQL 프로시저가 성공적으로 완료되었습니다.
    /*
        ------------------------------------------------------------
        부서번호    부서명     사원번호     사원명    입사일자   성별   나이
        ------------------------------------------------------------
        10 Administration 200 Jennifer Whalen 2003-09-17 여 45
    */
    
    --아래의 소스는 잘못된 것 입니다 --
    create or replace procedure pcd_employees_info_deptid
    (p_department_id  in  employees.department_id%type)
    is
       v_department_id      employees.department_id%type;
       v_department_name    departments.department_name%type;
       v_employee_id        employees.employee_id%type;
       v_ename              varchar2(30);
       v_hiredate           varchar2(10);
       v_gender             varchar2(6);
       v_age                number(3);
    begin
    
        with E as
        (
          select department_id
               , employee_id
               , first_name || ' ' || last_name AS ENAME
               , to_char(hire_date, 'yyyy-mm-dd') AS HIREDATE
               , func_gender(jubun) AS GENDER
               , func_age(jubun) AS AGE
          from employees
          where department_id = p_department_id
        )
        select E.department_id, D.department_name, E.employee_id, E.ename, E.hiredate, E.gender, E.age
               into
               v_department_id, v_department_name, v_employee_id, v_ename, v_hiredate, v_gender, v_age  
        from departments D right join E
        on D.department_id = E.department_id;
        
        dbms_output.put_line( lpad('-',60,'-') );
        dbms_output.put_line( '부서번호    부서명     사원번호     사원명    입사일자   성별   나이' );
        dbms_output.put_line( lpad('-',60,'-') );
        
        dbms_output.put_line( v_department_id || ' ' || 
                              v_department_name || ' ' || 
                              v_employee_id || ' ' ||
                              v_ename || ' ' ||
                              v_hiredate || ' ' ||
                              v_gender || ' ' ||
                              v_age );
    
    end pcd_employees_info_deptid;


    -- 위에서 만든 pcd_employees_info_deptid을 올바르게 작동하게 하기 --
    create or replace procedure pcd_employees_info_deptid
    (p_department_id  in  employees.department_id%type)
    is 
        type department_id_type
        is table of employees.department_id%type index by binary_integer;
        
        type department_name_type
        is table of departments.department_name%type index by binary_integer;
        
        type employee_id_type
        is table of employees.employee_id%type index by binary_integer;
        
        type ename_type
        is table of varchar2(30) index by binary_integer;
        
        type hiredate_type
        is table of varchar2(10) index by binary_integer;
        
        type gender_type
        is table of varchar2(6) index by binary_integer;
        
        type age_type
        is table of number(3) index by binary_integer;
        
        i binary_integer :=0;  -- i가 마치 배열의 방번호 용도 처럼 쓰인다.
                               -- 그런데 자바에서 배열의 시작은 0 부터 시작하지만
                               -- 오라클에서는 1 부터 시작한다.
                               
        v_department_id     department_id_type;
        v_department_name   department_name_type;
        v_employee_id       employee_id_type;
        v_ename             ename_type;
        v_hiredate          hiredate_type;
        v_gender            gender_type;
        v_age               age_type; 
        
    begin
        
        for v_rcd in (
            with E as
                (
                  select department_id
                       , employee_id
                       , first_name || ' ' || last_name AS ENAME
                       , to_char(hire_date, 'yyyy-mm-dd') AS HIREDATE
                       , func_gender(jubun) AS GENDER
                       , func_age(jubun) AS AGE
                  from employees
                  where department_id = p_department_id
                )
                select E.department_id, D.department_name, E.employee_id, E.ename, E.hiredate, E.gender, E.age
                from departments D right join E
                on D.department_id = E.department_id                
        ) loop
        
        i := i + 1;
        v_department_id(i) := v_rcd.department_id;
        v_department_name(i) := v_rcd.department_name;
        v_employee_id(i) := v_rcd.employee_id;
        v_ename(i) := v_rcd.ename;
        v_hiredate(i) := v_rcd.hiredate;
        v_gender(i) := v_rcd.gender;
        v_age(i) := v_rcd.age;
         
        
        end loop;
        if(i = 0) then
            raise no_data_found;
        else
            dbms_output.put_line( lpad('-',60,'-') );
            dbms_output.put_line( '부서번호    부서명     사원번호     사원명    입사일자   성별   나이' );
            dbms_output.put_line( lpad('-',60,'-') );
            for k in 1..i loop
            dbms_output.put_line(v_department_id(k) || ' ' ||
                            v_department_name(k) || ' ' ||
                            v_employee_id(k) || ' ' ||
                            v_ename(k) || ' ' ||
                            v_hiredate(k) || ' ' ||
                            v_gender(k) || ' ' ||
                            v_age(k));
            end loop;
        end if;
        
        exception
            when no_data_found  then dbms_output.put_line('>> 부서번호 ' || p_department_id || '는 존재하지 않습니다 <<'); -- no_data_found 은 오라클에서 데이터가 존재하지 않을 경우 발생하는 오류임.
    end pcd_employees_info_deptid;
    
    
    create or replace procedure pcd_employee_search (p_employee_id in employees.employee_id%type) is
        v_fullname varchar2(50);
        v_salary employees.salary%type;
    begin
        select first_name || ' ' || last_name as FULLNAME, salary
            into
            v_fullname, v_salary
        from employees
        where employee_id = p_employee_id;
        
        dbms_output.put_line( lpad('-',30,'-') );
        dbms_output.put_line( '사원명    기본급여' );
        dbms_output.put_line( lpad('-',30,'-') );
        dbms_output.put_line(v_fullname || ' ' || v_salary);
    
    end pcd_employee_search;
    
    
    exec pcd_employees_info_deptid(30);
    
    exec pcd_employees_info_deptid(500);
    
    --PL/SQL에서 데이터가 존재하지 않는다면 "no data found" 라는 오류가 발생된다.
    exec pcd_employee_search(100);
    exec pcd_employee_search(999);
    /*
        ORA-01403: 데이터를 찾을 수 없습니다.
        ORA-06512: "HR.PCD_EMPLOYEE_SEARCH",  5행
        ORA-06512:  1행
    */
    
    create or replace procedure pcd_employee_search (p_employee_id in employees.employee_id%type) is
        v_fullname varchar2(50);
        v_salary employees.salary%type;
    begin
        select first_name || ' ' || last_name as FULLNAME, salary
            into
            v_fullname, v_salary
        from employees
        where employee_id = p_employee_id;
        
        dbms_output.put_line( lpad('-',30,'-') );
        dbms_output.put_line( '사원명    기본급여' );
        dbms_output.put_line( lpad('-',30,'-') );
        dbms_output.put_line(v_fullname || ' ' || v_salary);
    
        exception
            when no_data_found  then dbms_output.put_line('>> 사원번호 ' || p_employee_id || '는 존재하지 않습니다 <<'); -- no_data_found 은 오라클에서 데이터가 존재하지 않을 경우 발생하는 오류임.
        
    end pcd_employee_search;
    
    
    
    
    
    
    --👆위의 방식은 잘 사용 안함, 가독성 별로라서 안씀
    
    -------------------------------------
               ---- ===== **** CURSOR **** ===== -----
              
  --  PL/SQL 에서 SELECT 되어져 나오는 행의 개수가 2개 이상인 경우에는 위에서 한 것처럼
  --  table 타입의 변수를 사용하여 나타낼 수 있고, 또는 CURSOR 를 사용하여 나타낼 수도 있다. 
  --  table 타입의 변수를 사용하는 것 보다 CURSOR 를 사용하는 것이 더 편하므로 
  --  대부분 CURSOR 를 많이 사용한다.
    
    
    /*
          ----- *** 명시적 CURSOR 만들기 *** -----
          ※ 형식
        
          1.단계 -- CURSOR 의 선언(정의)
             
            CURSOR 커서명
            IS
            SELECT 문;  
        
          2.단계 -- CURSOR 의 OPEN
        
            OPEN 커서명;
        
          3.단계 -- CURSOR 의 FETCH
                   (FETCH 란? SELECT 되어진 결과물을 끄집어 내는 작업을 말한다)
            
            FETCH  커서명 INTO 변수;
        
          4.단계 -- CURSOR 의 CLOSE
        
            CLOSE 커서명;
              
        
        
         ※ ==== 커서의 속성변수 ==== ※
        
         1. 커서명%ISOPEN   ==> 커서가 OPEN 되어진 상태인가를 체크하는 것.
                               만약에 커서가 OPEN 되어진 상태이라면 TRUE.
        
         2. 커서명%FOUND    ==> FETCH 된 레코드(행)이 있는지 체크하는 것.
                               만약에 FETCH 된 레코드(행)이 있으면 TRUE.
        
         3. 커서명%NOTFOUND ==> FETCH 된 레코드(행)이 없는지 체크하는 것.
                               만약에 FETCH 된 레코드(행)이 없으면 TRUE.
        
         4. 커서명%ROWCOUNT ==> 현재까지 FETCH 된 레코드(행)의 갯수를 반환해줌. 
    */
    
    
    
    
    
    
    
  
     create or replace procedure pcd_employees_deptid_cursor
    (p_department_id  in  employees.department_id%type)
    is 
        --1.단계 -- CURSOR 의 선언(정의)
        Cursor cur_empinfo
        Is
        with E as
            (
              select department_id
                   , employee_id
                   , first_name || ' ' || last_name AS ENAME
                   , to_char(hire_date, 'yyyy-mm-dd') AS HIREDATE
                   , func_gender(jubun) AS GENDER
                   , func_age(jubun) AS AGE
              from employees
              where department_id = p_department_id
            )
            select E.department_id, D.department_name, E.employee_id, E.ename, E.hiredate, E.gender, E.age
            from departments D right join E
            on D.department_id = E.department_id;
            
            v_department_id     employees.department_id%type;
            v_department_name   departments.department_name%type;
            v_employee_id       employees.employee_id%type;
            v_ename             varchar2(30);
            v_hiredate          varchar2(10);
            v_gender            varchar2(6);
            v_age               number(3);
            
            v_fetch_count       number := 0;
        
    begin
        --2.단계 -- CURSOR 의 OPEN
        Open cur_empinfo;
        
        --3.단계 -- CURSOR 의 FETCH
                --(FETCH 란? SELECT 되어진 결과물을 끄집어 내는 작업을 말한다)
        loop
            fetch  cur_empinfo into v_department_id, v_department_name, v_employee_id, v_ename, v_hiredate, v_gender, v_age; --셀렉트 되어진 순서를 맞춰야 한다
            exit when cur_empinfo%NOTFOUND; --더 이상 fetch 되어진 행이 없다라면 반복문을 빠져나간다.
            
            v_fetch_count := cur_empinfo%ROWCOUNT;
            
            if(v_fetch_count = 1) then
                dbms_output.put_line( lpad('-',60,'-') );
                dbms_output.put_line( '부서번호    부서명     사원번호     사원명    입사일자   성별   나이' );
                dbms_output.put_line( lpad('-',60,'-') );
            end if;
            
            dbms_output.put_line(v_department_id || ' ' ||
                                v_department_name || ' ' ||
                                v_employee_id || ' ' ||
                                v_ename || ' ' ||
                                v_hiredate || ' ' ||
                                v_gender || ' ' ||
                                v_age);
            
        end loop;
        
        --4.단계 -- CURSOR 의 CLOSE
        
        close cur_empinfo;
        
        if(v_fetch_count = 0) then
            dbms_output.put_line('>> 부서번호 ' || p_department_id || '는 존재하지 않습니다 <<');
        else
            dbms_output.put_line(' ');
            dbms_output.put_line('>> 조회된 행의 개수 : ' || v_fetch_count || '개');
            
        end if;
    end pcd_employees_deptid_cursor;
    
    
    
    exec pcd_employees_deptid_cursor(10);
    
    exec pcd_employees_deptid_cursor(30);
    
    exec pcd_employees_deptid_cursor(999);
    
    
    
    
    
    
    
    
      
     -------------- *****  FOR LOOP CURSOR 만들기 ***** -----------------< 주로 이걸씀
     /*
         FOR LOOP CURSOR 문을 사용하면
         커서의 OPEN, 커서의 FETCH, 커서의 CLOSE 가 자동적으로 발생되어지기 때문에
         우리는 커서의 OPEN, 커서의 FETCH, 커서의 CLOSE 문장을 기술할 필요가 없다.
     */
     
     ※ 형식
      FOR 변수명(select 되어진 행의 정보가 담기는 변수) IN 커서명 LOOP
          실행문장;
      END LOOP;
     
     
    create or replace procedure pcd_employees_deptid_forcursor
    (p_department_id  in  employees.department_id%type)
    is 
        --1.단계 -- CURSOR 의 선언(정의)
        Cursor cur_empinfo
        Is
        with E as
            (
              select department_id
                   , employee_id
                   , first_name || ' ' || last_name AS ENAME
                   , to_char(hire_date, 'yyyy-mm-dd') AS HIREDATE
                   , func_gender(jubun) AS GENDER
                   , func_age(jubun) AS AGE
              from employees
              where department_id = p_department_id
            )
            select E.department_id, D.department_name, E.employee_id, E.ename, E.hiredate, E.gender, E.age
            from departments D right join E
            on D.department_id = E.department_id;
            
            v_fetch_count       number := 0;
        
    begin
        /*
        --  2단계
        FOR 변수명(select 되어진 행의 정보가 담기는 변수) IN 커서명 LOOP
            실행문장;
        END LOOP;
        */
        FOR v_rcd IN cur_empinfo LOOP
        
            v_fetch_count := cur_empinfo%ROWCOUNT;
            
            if(v_fetch_count = 1) then
                dbms_output.put_line( lpad('-',60,'-') );
                dbms_output.put_line( '부서번호    부서명     사원번호     사원명    입사일자   성별   나이' );
                dbms_output.put_line( lpad('-',60,'-') );
            end if;
            
            dbms_output.put_line(v_rcd.department_id || ' ' ||
                                v_rcd.department_name || ' ' ||
                                v_rcd.employee_id || ' ' ||
                                v_rcd.ename || ' ' ||
                                v_rcd.hiredate || ' ' ||
                                v_rcd.gender || ' ' ||
                                v_rcd.age);
            
        END LOOP;
        
        if(v_fetch_count = 0) then
            dbms_output.put_line('>> 부서번호 ' || p_department_id || '는 존재하지 않습니다 <<');
        else
            dbms_output.put_line(' ');
            dbms_output.put_line('>> 조회된 행의 개수 : ' || v_fetch_count || '개');
            
        end if;
    end pcd_employees_deptid_forcursor;

    exec pcd_employees_deptid_forcursor(10);
    
    exec pcd_employees_deptid_forcursor(30);
    
    exec pcd_employees_deptid_forcursor(999);
    
    
    
    
    
    ------------------- ****** PACKAGE(패키지) ****** -------------------
    
    --->   PACKAGE(패키지)란?  여러개의 Procedure 와 여러개의 Function 들의 묶음
    
    -- 1. PACKAGE(패키지)의 선언하기
    create or replace package employee_pack
    is
        -- employee_pack 패키지에 들어올 프로시저 또는 함수를 선언해준다.
        procedure pcd_emp_info(p_deptno in employees.department_id%type);
        procedure pcd_dept_info(p_deptno in departments.department_id%type);
        function func_gender(p_jubun employees.jubun%type) return Nvarchar2;
    end employee_pack;
    
    -- 2. PACKAGE(패키지)의 Body(본문) 생성하기
    create or replace package body employee_pack
    is
        procedure pcd_emp_info(p_deptno in employees.department_id%type) 
        is
            cursor cur_empinfo
            is
            SELECT E.department_id, D.department_name, E.employee_id, E.ename
            FROM
            (
                select department_id, employee_id, first_name || ' ' || last_name AS ename
                from employees
                where department_id = p_deptno
            ) E JOIN departments D
            ON E.department_id = D.department_id;
            
            v_fetch_count       number := 0;
        begin
            for v_rcd in cur_empinfo loop
                v_fetch_count := cur_empinfo%rowcount;
                
                if(v_fetch_count = 1) then
                    dbms_output.put_line( lpad('-',60,'-') );
                    dbms_output.put_line('부서번호  부서명       사원번호  사원명 ');
                    dbms_output.put_line( lpad('-',60,'-') );
                end if;
                
                dbms_output.put_line(v_rcd.department_id || ' ' || 
                                     v_rcd.department_name || ' ' || 
                                     v_rcd.employee_id || ' ' ||
                                     v_rcd.ename);
                
            end loop;
            
            if(v_fetch_count = 0) then
                dbms_output.put_line('>> 부서번호 ' || p_deptno || '은 없습니다.<<');
            else
                dbms_output.put_line(' ');
                dbms_output.put_line('>> 조회건수 ' || v_fetch_count || '개');
            end if;
        end pcd_emp_info;
        
        
        procedure pcd_dept_info(p_deptno in departments.department_id%type)
        is
            v_department_id     departments.department_id%type;
            v_department_name   departments.department_name%type;
        begin
            select department_id, department_name
                into
                v_department_id, v_department_name
            from departments
            where department_id = p_deptno;
            
            dbms_output.put_line( lpad('-',40,'-') );
            dbms_output.put_line('부서번호  부서명');
            dbms_output.put_line( lpad('-',40,'-') );
            
            dbms_output.put_line(v_department_id || ' ' || v_department_name);
            
            exception
                when no_data_found then
                dbms_output.put_line('>> 부서번호 ' || p_deptno || '은 없습니다.<<');
        end pcd_dept_info;
        
        
        function func_gender(p_jubun employees.jubun%type)
        return Nvarchar2
        is
            v_jubun_length      number(3);
            v_gender            nvarchar2(1);
            
            error_jubun         exception;
        begin
            v_jubun_length := length(p_jubun);
            
            if(v_jubun_length !=13) then
                raise error_jubun;
            else
                for i in 1..v_jubun_length loop
                    if not (substr(p_jubun, i, 1) between '0' and '9') then
                        raise error_jubun;
                    end if;
                end loop;
                
                if(substr(p_jubun, 7, 1) in ('1', '3') ) then
                    v_gender := '남';
                elsif(substr(p_jubun, 7, 1) in ('2', '4') ) then
                    v_gender := '여';
                else
                    raise error_jubun;
                end if;
                
            end if;
            
            return v_gender;
            
            exception
                when error_jubun then
                    raise_application_error(-20001, '>> 주민번호가 올바르지 않습니다. <<'); -- 에러번호가 예전에 같은거 쓰더라노 몬다이나이
        end func_gender;
    end employee_pack;
    
    
    -- *** 패키지 실행하기 ***---
    begin
        employee_pack.pcd_emp_info(30);
    end;
    
    begin
        employee_pack.pcd_dept_info(999);
    end;
    
    
    select employee_pack.func_gender('9602271392710')
        , employee_pack.func_gender('9602272392710')
    from dual;
    
    select employee_pack.func_gender('960227139271b')
        , employee_pack.func_gender('9602272392710')
    from dual;
    
    select employee_pack.func_gender('960227839271b')
        , employee_pack.func_gender('9602272392710')
    from dual;
    
    select employee_pack.func_gender('960227139271b')
        , employee_pack.func_gender('9602272392710')
    from dual;
    
    select employee_id, first_name, jubun, employee_pack.func_gender(jubun)
    from employees
    order by 1;
    
    --** 패키지 소스 보기 ** --
    select text
    from user_source
    where type = 'PACKAGE' and name = 'EMPLOYEE_PACK';
    
    --** 패키지 BODY(본문) 보기 ** --
    select text
    from user_source
    where type = 'PACKAGE BODY' and name = 'EMPLOYEE_PACK';
    
    
    -- *** 패키지 삭제하기 *** --
    drop package employee_pack;
    
    