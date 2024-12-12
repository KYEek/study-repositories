---- **** MyMVC 다이내믹웹프로젝트 에서 작업한 것 **** ----

-- 오라클 계정 생성을 위해서는 SYS 또는 SYSTEM 으로 연결하여 작업을 해야 합니다. [SYS 시작] --
show user;
-- USER이(가) "SYS"입니다.

-- 오라클 계정 생성시 계정명 앞에 c## 붙이지 않고 생성하도록 하겠습니다.
alter session set "_ORACLE_SCRIPT"=true;
-- Session이(가) 변경되었습니다.

-- 오라클 계정명은 MYMVC_USER 이고 암호는 gclass 인 사용자 계정을 생성합니다.
create user MYMVC_USER identified by gclass default tablespace users; 
-- User MYMVC_USER이(가) 생성되었습니다.

-- 위에서 생성되어진 MYMVC_USER 이라는 오라클 일반사용자 계정에게 오라클 서버에 접속이 되어지고,
-- 테이블 생성 등등을 할 수 있도록 여러가지 권한을 부여해주겠습니다.
grant connect, resource, create view, unlimited tablespace to MYMVC_USER;
-- Grant을(를) 성공했습니다.

-----------------------------------------------------------------------

show user;
-- USER이(가) "MYMVC_USER"입니다.

create table tbl_main_page
(imgno        number  not null
,imgname      Nvarchar2(20) not null
,imgfilename  Nvarchar2(30) not null
,constraint   PK_tbl_main_page_imgno primary key(imgno)
);
-- Table TBL_MAIN_PAGE이(가) 생성되었습니다.

create sequence seq_main_image
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_MAIN_IMAGE이(가) 생성되었습니다.

insert into tbl_main_page(imgno, imgname, imgfilename) values(seq_main_image.nextval, '미샤', '미샤.png');      
insert into tbl_main_page(imgno, imgname, imgfilename) values(seq_main_image.nextval, '원더플레이스', '원더플레이스.png');
insert into tbl_main_page(imgno, imgname, imgfilename) values(seq_main_image.nextval, '레노보', '레노보.png');
insert into tbl_main_page(imgno, imgname, imgfilename) values(seq_main_image.nextval, '동원', '동원.png');

commit;
-- 커밋 완료.

select imgno, imgname, imgfilename
from tbl_main_page
order by imgno asc;


---- **** 회원 테이블 생성 **** ----
/*
   평문(plain text) ==> 암호화가 안된 문장
   I am a boy
   
   암호화된 문장(encrypted text)
   평문(plain text) + 암호화키(key)
   I am a boy + 1 ==> J bn b cpz
   
   복호화된 문장(decrypted text) ==> 해독된 문장
   암호화된 문장(encrypted text) + 암호화키(key)
   J bn b cpz - 1 ==> I am a boy
   
   AES256 방식 ==> 양방향 암호화 (암호화 및 복호화 가능함) , 암호화키(key)가 반드시 필요함.
   SHA256 방식 ==> 단방향 암호화 (암호화만 되어지고 복호화가 불가능함), 암호화키(key)가 없음.
*/

create table tbl_member    
(userid             varchar2(40)   not null  -- 회원아이디
,pwd                varchar2(200)  not null  -- 비밀번호 (SHA-256 암호화 대상)
,name               varchar2(30)   not null  -- 회원명
,email              varchar2(200)  not null  -- 이메일 (AES-256 암호화/복호화 대상)
,mobile             varchar2(200)            -- 연락처 (AES-256 암호화/복호화 대상) 
,postcode           varchar2(5)              -- 우편번호
,address            varchar2(200)            -- 주소
,detailaddress      varchar2(200)            -- 상세주소
,extraaddress       varchar2(200)            -- 참고항목
,gender             varchar2(1)              -- 성별   남자:1  / 여자:2
,birthday           varchar2(10)             -- 생년월일   
,coin               number default 0         -- 코인액
,point              number default 0         -- 포인트 
,registerday        date default sysdate     -- 가입일자 
,lastpwdchangedate  date default sysdate     -- 마지막으로 암호를 변경한 날짜  
,status             number(1) default 1 not null     -- 회원탈퇴유무   1: 사용가능(가입중) / 0:사용불능(탈퇴) 
,idle               number(1) default 0 not null     -- 휴면유무      0 : 활동중  /  1 : 휴면중 
,constraint PK_tbl_member_userid primary key(userid)
,constraint UQ_tbl_member_email  unique(email)
,constraint CK_tbl_member_gender check( gender in('1','2') )
,constraint CK_tbl_member_status check( status in(0,1) )
,constraint CK_tbl_member_idle check( idle in(0,1) )
);
-- Table TBL_MEMBER이(가) 생성되었습니다.

select *
from tbl_member
order by registerday desc;

select userid
from tbl_member
where userid = 'leess';

select userid
from tbl_member
where userid = 'eomjh';

select userid
from tbl_member
where userid = 'seoyh';

-------------------------------------------------------------------
create table tbl_loginhistory
(historyno   number
,fk_userid   varchar2(40) not null  -- 회원아이디
,logindate   date default sysdate not null -- 로그인되어진 접속날짜및시간
,clientip    varchar2(20) not null
,constraint  PK_tbl_loginhistory primary key(historyno)
,constraint  FK_tbl_loginhistory_fk_userid foreign key(fk_userid) references tbl_member(userid)
);
-- Table TBL_LOGINHISTORY이(가) 생성되었습니다.

create sequence seq_historyno
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_HISTORYNO이(가) 생성되었습니다.


select * 
from tbl_loginhistory
order by historyno desc;



---------- 로그인 처리를 위한 SQL 문 작성 ------------

update tbl_member set registerday = add_months(registerday, -13)
where userid = 'leess';

rollback;

SELECT userid, name, coin, point, pwdchangegap, 
       NVL( lastlogingap, TRUNC( months_between(sysdate, registerday)) ) AS lastlogingap, 
       idle, email, mobile, postcode, address, detailaddress, extraaddress 
FROM 
(
    SELECT userid, name, coin, point, 
           trunc( months_between(sysdate, lastpwdchangedate) ) AS pwdchangegap, 
           registerday, idle, email, mobile, postcode, address, detailaddress, extraaddress  
    FROM tbl_member
    WHERE status = 1 AND userid = 'leess' and pwd = '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382'
) M
CROSS JOIN 
(
    SELECT TRUNC( months_between(sysdate, MAX(logindate))) AS lastlogingap 
    FROM tbl_loginhistory
    WHERE fk_userid = 'leess'
) H;


update tbl_loginhistory set logindate = add_months(logindate, -15)
where historyno = 1;

select *
from tbl_loginhistory
order by historyno desc;

commit;

update tbl_loginhistory set logindate = add_months(logindate, -5)
where historyno between 2 and 5;

update tbl_loginhistory set logindate = add_months(logindate, -4)
where historyno between 6 and 8;

commit;

select *
from tbl_loginhistory
order by historyno desc;

update tbl_member set lastpwdchangedate = add_months(lastpwdchangedate, -4)
where userid = 'eomjh';

update tbl_member set registerday = add_months(registerday, -5)
where userid = 'eomjh';

commit;

select *
from tbl_member
order by registerday desc;


