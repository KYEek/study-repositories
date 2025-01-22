------ ***** spring 기초 시작 ***** ------

show user;
-- USER이(가) "MYMVC_USER"입니다.

create table spring_test
(no         number
,name       varchar2(100)
,writeday   date default sysdate
);
-- Table SPRING_TEST이(가) 생성되었습니다.

select no, name, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from spring_test
order by no desc, writeday desc;

-----------------------------------------------------------------------------

show user;
-- USER이(가) "HR"입니다.

create table spring_exam
(no         number
,name       varchar2(100)
,address    Nvarchar2(100)
,writeday   date default sysdate
);
-- Table SPRING_EXAM이(가) 생성되었습니다.

select no, name, address, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from spring_exam
order by no desc, writeday desc;

-------------------------------------------------------------
show user;
-- USER이(가) "MYMVC_USER"입니다.


insert into spring_test(no, name, writeday)
values(102, '김태희', default);

insert into spring_test(no, name, writeday)
values(103, '변우석', default);

commit;

select no, name, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') AS writeday
from spring_test
order by no desc;

------ ***** spring 기초 끝 ***** ------

---------------------------------------------------------------------
---------------------------------------------------------------------

show user;
-- USER이(가) "MYMVC_USER"입니다.

create table tbl_main_image_product
(imgno           number not null
,productname     Nvarchar2(20) not null
,imgfilename     varchar2(100) not null
,constraint PK_tbl_main_image_product primary key(imgno)
);
-- Table TBL_MAIN_IMAGE_PRODUCT이(가) 생성되었습니다.

create sequence seq_main_image_product
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence SEQ_MAIN_IMAGE_PRODUCT이(가) 생성되었습니다.

insert into tbl_main_image_product(imgno, productname, imgfilename) values(seq_main_image_product.nextval, '미샤', '미샤.png');  
insert into tbl_main_image_product(imgno, productname, imgfilename) values(seq_main_image_product.nextval, '원더플레이스', '원더플레이스.png'); 
insert into tbl_main_image_product(imgno, productname, imgfilename) values(seq_main_image_product.nextval, '레노보', '레노보.png'); 
insert into tbl_main_image_product(imgno, productname, imgfilename) values(seq_main_image_product.nextval, '동원', '동원.png'); 

commit;

select imgno, productname, imgfilename
from tbl_main_image_product
order by imgno asc;


---------- 로그인 처리 ----------
select *
from tbl_member;

select *
from tbl_member
where userid = 'seoyh' and pwd = '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382';


SELECT userid, name, coin, point, pwdchangegap, 
       NVL( lastlogingap, trunc( months_between(sysdate, registerday) ) ) AS lastlogingap,  
       idle, email, mobile, postcode, address, detailaddress, extraaddress     
FROM 
  ( select userid, name, coin, point, 
           trunc( months_between(sysdate, lastpwdchangedate) ) AS pwdchangegap,  
           registerday, idle, email, mobile, postcode, address, detailaddress, extraaddress   
    from tbl_member 
    where status = 1 and userid = 'seoyh' and pwd = '9695b88a59a1610320897fa84cb7e144cc51f2984520efb77111d94b402a8382' ) M   
CROSS JOIN 
( select trunc( months_between(sysdate, max(logindate)) ) AS lastlogingap  
  from tbl_loginhistory 
  where fk_userid = 'seoyh' ) H;


select userid, lastpwdchangedate, status, idle
from tbl_member
where userid in('seoyh', 'eomjh', 'leess');

select *
from tbl_loginhistory
order by historyno desc;


select *
from tbl_loginhistory
where fk_userid = 'leess'
order by historyno desc;

select userid, lastpwdchangedate, status, idle
from tbl_member
where userid = 'leess';

update tbl_member set idle = 0
where userid = 'leess';
-- 1 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.

delete from tbl_loginhistory
where historyno = 226;
-- 1 행 이(가) 삭제되었습니다.

commit;
-- 커밋 완료.



------- **** spring 게시판(답변글쓰기가 없고, 파일첨부도 없는) 글쓰기 **** -------

show user;
-- USER이(가) "MYMVC_USER"입니다.    
    
    
desc tbl_member;

create table tbl_board
(seq         number                not null    -- 글번호
,fk_userid   varchar2(20)          not null    -- 사용자ID
,name        varchar2(20)          not null    -- 글쓴이 
,subject     Nvarchar2(200)        not null    -- 글제목
,content     Nvarchar2(2000)       not null    -- 글내용   -- clob (최대 4GB까지 허용) 
,pw          varchar2(20)          not null    -- 글암호
,readCount   number default 0      not null    -- 글조회수
,regDate     date default sysdate  not null    -- 글쓴시간
,status      number(1) default 1   not null    -- 글삭제여부   1:사용가능한 글,  0:삭제된글
,constraint PK_tbl_board_seq primary key(seq)
,constraint FK_tbl_board_fk_userid foreign key(fk_userid) references tbl_member(userid)
,constraint CK_tbl_board_status check( status in(0,1) )
);
-- Table TBL_BOARD이(가) 생성되었습니다.

create sequence boardSeq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

select *
from tbl_board
order by seq desc;


select previous_no 
            ,previous_subject 
            ,seq 
            ,subject 
            ,content 
            ,next_no 
            ,next_subject 
            ,REGDATE
            ,readcount
            ,name
      from
      (
          select lag (seq) over(order by seq desc) previous_no
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 boardno 컬럼의 값을 가져온다.
                
                ,lag (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by seq desc) previous_subject 
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 위쪽으로 1칸 올라간 행에서 subject 컬럼의 값을 가져온다.
                ,seq 
                ,case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end as subject 
                ,content
                ,lead (seq) over(order by seq desc) next_no
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 boardno 컬럼의 값을 가져온다.
                
                ,lead (case when length(subject) > 19 then substr(subject, 1, 16) || '...' else subject end) over(order by seq desc) next_subject
                -- boardno(글번호) 컬럼의 값을 내림차순 정렬했을 아래쪽으로 1칸 내려간 행에서 subject 컬럼의 값을 가져온다.
                ,REGDATE
                ,readcount
                ,name
          from tbl_board
      )V
      where V.seq = 3;
