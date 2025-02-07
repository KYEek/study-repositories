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


select seq, fk_userid, name, 
       case when length(subject) <= 30 then subject 
            else substr(subject, 1, 28)||'..' end AS subject
     , readCount, to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate
from tbl_board
where status = 1
order by seq desc;


SELECT previousseq, previoussubject
     , seq, fk_userid, name, subject, content, readCount, regDate, pw
     , nextseq, nextsubject
FROM
 (
     select lag(seq) over(order by seq desc) AS previousseq
          , lag(subject) over(order by seq desc) AS previoussubject
          , seq, fk_userid, name, subject, content, readCount, regDate, pw  
          , lead(seq) over(order by seq desc) AS nextseq
          , lead(subject) over(order by seq desc) AS nextsubject
     from tbl_board
     where status = 1
 ) V
WHERE V.seq = 3;

------------------------------------------------------------------------

   ----- **** 댓글 게시판 **** -----

/* 
  댓글쓰기(tbl_comment 테이블)를 성공하면 원게시물(tbl_board 테이블)에
  댓글의 갯수(1씩 증가)를 알려주는 컬럼 commentCount 을 추가하겠다. 
*/

drop table tbl_board purge;
-- Table TBL_BOARD이(가) 삭제되었습니다.

create table tbl_board
(seq           number                not null    -- 글번호
,fk_userid     varchar2(20)          not null    -- 사용자ID
,name          varchar2(20)          not null    -- 글쓴이 
,subject       Nvarchar2(200)        not null    -- 글제목
,content       Nvarchar2(2000)       not null    -- 글내용   -- clob (최대 4GB까지 허용) 
,pw            varchar2(20)          not null    -- 글암호
,readCount     number default 0      not null    -- 글조회수
,regDate       date default sysdate  not null    -- 글쓴시간
,status        number(1) default 1   not null    -- 글삭제여부   1:사용가능한 글,  0:삭제된글
,commentCount  number default 0      not null    -- 댓글의 개수
,constraint PK_tbl_board_seq primary key(seq)
,constraint FK_tbl_board_fk_userid foreign key(fk_userid) references tbl_member(userid)
,constraint CK_tbl_board_status check( status in(0,1) )
);
-- Table TBL_BOARD이(가) 생성되었습니다.


drop sequence boardSeq;
-- Sequence BOARDSEQ이(가) 삭제되었습니다.

create sequence boardSeq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence BOARDSEQ이(가) 생성되었습니다.


----- **** 댓글 테이블 생성 **** -----
create table tbl_comment
(seq           number               not null   -- 댓글번호
,fk_userid     varchar2(20)         not null   -- 사용자ID
,name          varchar2(30)         not null   -- 성명
,content       Nvarchar2(1000)       not null  -- 댓글내용
,regDate       date default sysdate not null   -- 작성일자
,parentSeq     number               not null   -- 원게시물 글번호
,status        number(1) default 1  not null   -- 글삭제여부
                                               -- 1 : 사용가능한 글,  0 : 삭제된 글
                                               -- 댓글은 원글이 삭제되면 자동적으로 삭제되어야 한다.
,constraint PK_tbl_comment_seq primary key(seq)
,constraint FK_tbl_comment_userid foreign key(fk_userid) references tbl_member(userid)
,constraint FK_tbl_comment_parentSeq foreign key(parentSeq) references tbl_board(seq) on delete cascade
,constraint CK_tbl_comment_status check( status in(1,0) ) 
);
-- Table TBL_COMMENT이(가) 생성되었습니다.

create sequence commentSeq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;
-- Sequence COMMENTSEQ이(가) 생성되었습니다.

select *
from tbl_comment
order by seq desc;

select *
from tbl_board
order by seq desc;


-- ==== Transaction 처리를 위한 시나리오 만들기 ==== --
---- 회원들이 게시판에 글쓰기를 하면 글작성 1건당 POINT 를 100점을 준다.
---- 회원들이 게시판에서 댓글쓰기를 하면 댓글작성 1건당 POINT 를 50점을 준다.
---- 그런데 데이터베이스 오류 발생시 스프링에서 롤백해주는 Transaction 처리를 알아보려고 일부러 POINT 는 300을 초과할 수 없다고 하겠다.

select *
from tbl_member;

update tbl_member set point = 0;

commit;
-- 커밋 완료.

-- tbl_member 테이블에 point 컬럼에 check 제약을 추가한다.
alter table tbl_member
add constraint CK_tbl_member_point check( point between 0 and 300 );
-- Table TBL_MEMBER이(가) 변경되었습니다.

update tbl_member set point = 301
where userid = 'seoyh';
/*
   오류 보고 -
   ORA-02290: 체크 제약조건(MYMVC_USER.CK_TBL_MEMBER_POINT)이 위배되었습니다
*/

update tbl_member set point = 300
where userid = 'seoyh';
-- 1 행 이(가) 업데이트되었습니다.

rollback;
-- 롤백 완료.

select *
from tbl_comment
order by seq desc;

select seq, subject, commentcount
from tbl_board
order by seq desc;

select userid, point
from tbl_member
where userid in ('eomjh','seoyh');


select seq, fk_userid, name, content, to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate
from tbl_comment
where parentSeq = 3
order by seq desc;


--- === 이제는 Transaction 처리를 위한 시나리오 때문에 만들었던 포인트 체크제약을 없애겠다. === ---
alter table tbl_member
drop constraint CK_tbl_member_point;
-- Table TBL_MEMBER이(가) 변경되었습니다.

select userid, point
from tbl_member
where userid in('seoyh', 'eomjh');


select subject
from tbl_board
where status = 1 
and lower(subject) like '%'||lower('jAvA')||'%';

select subject
from tbl_board
where status = 1 
and lower(subject) like '%'||lower('dsfkjsdkfjlksjfd')||'%';


select distinct name
from tbl_board
where status = 1 
and lower(name) like '%'||lower('정화')||'%';


select name
from tbl_board
where status = 1 
and lower(name) like '%'||lower('ㄴㅇㄹㄴㅇㄹㅇㄴㄹ')||'%';


/*
   select 문에서 distinct 와 order by 절을 함께 사용할때는 조심해야 한다.
   order by 절에는 select 문에서 사용된 컬럼만 들어올 수가 있다.
   또는 order by 절을 사용하지 않아야 한다.
*/
select distinct name
from tbl_board
where status = 1 
and lower(name) like '%'||lower('정화')||'%'
order by regdate desc;
-- ORA-01791: SELECT 식이 부적합합니다

select distinct name
from tbl_board
where status = 1 
and lower(name) like '%'||lower('정화')||'%';

select distinct name
from tbl_board
where status = 1 
and lower(name) like '%'||lower('정화')||'%'
order by name asc;

-----------------------------------------------------------------------------------

begin
    for i in 1..100 loop
        insert into tbl_board(seq, fk_userid, name, subject, content, pw, readCount, regDate, status, commentCount)
        values(boardSeq.nextval, 'seoyh', '서영학', '서영학 입니다'||i, '안녕하세요? 서영학'|| i ||' 입니다.', '1234', default, default, default, default);
    end loop;
end;

begin
    for i in 101..200 loop
        insert into tbl_board(seq, fk_userid, name, subject, content, pw, readCount, regDate, status, commentCount)
        values(boardSeq.nextval, 'eomjh', '엄정화', '엄정화 입니다'||i, '안녕하세요? 엄정화'|| i ||' 입니다.', '1234', default, default, default, default);
    end loop;
end;

commit;
-- 커밋 완료.

select *
from tbl_board
order by seq desc;


select count(*)
from tbl_board
order by seq desc;


-- 아래의 방법은 Oracle 11g 와 호환됨. 
SELECT seq, fk_userid, name, subject, readCount, regDate, commentCount 
FROM 
(
    select row_number() over(order by seq desc) AS rno 
         , seq, fk_userid, name, subject
         , readCount, to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate 
         , commentCount 
    from tbl_board
    where status = 1 
    -- and lower(subject) like '%'||lower('입니다')||'%'
    -- and lower(content) like '%'||lower('안녕하세요')||'%'
    -- and (lower(subject) like '%'||lower('JavA')||'%' or lower(content) like '%'||lower('안녕하세요')||'%')
    and lower(name) like '%'||lower('정화')||'%'
) V
-- WHERE rno BETWEEN 1 AND 10;    -- 1 페이지
-- WHERE rno BETWEEN 11 AND 20;   -- 2 페이지 
WHERE rno BETWEEN 21 AND 30;      -- 3 페이지


-- 아래의 방법은 Oracle 12c 이상에서만 가능함.
select seq, fk_userid, name, subject
     , readCount, to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate 
     , commentCount 
from tbl_board
where status = 1 
-- and lower(subject) like '%'||lower('입니다')||'%'
-- and lower(content) like '%'||lower('안녕하세요')||'%'
-- and (lower(subject) like '%'||lower('JavA')||'%' or lower(content) like '%'||lower('안녕하세요')||'%')
and lower(name) like '%'||lower('정화')||'%'
ORDER BY seq DESC
OFFSET (1-1)*10 ROW     -- 1 페이지 
-- OFFSET (2-1)*10 ROW  -- 2 페이지 
-- OFFSET (3-1)*10 ROW  -- 3 페이지 
FETCH NEXT 10 ROW ONLY;

---------------------------------------------
delete from tbl_board
where seq = 149;
-- 1 행 이(가) 삭제되었습니다.

commit;
-- 커밋 완료.

delete from tbl_board
where seq = 150;
-- 1 행 이(가) 삭제되었습니다.

commit;
-- 커밋 완료.
----------------------------------------------------------------------

      ---- **** 댓글 및 답변글 및 파일첨부가 있는 게시판 **** ----
      
--- *** 답변글쓰기는 일반회원은 불가하고 직원(관리파트)들만 답변글쓰기가 가능하도록 한다. *** ---    

--- *** tbl_member(회원) 테이블에 gradelevel 이라는 컬럼을 추가하겠다. *** ---
alter table tbl_member
add gradelevel number default 1;
-- Table TBL_MEMBER이(가) 변경되었습니다.

-- *** 직원(관리자)들에게는 gradelevel 컬럼의 값을 10 으로 부여하겠다. 
--     gradelevel 컬럼의 값이 10 인 직원들만 답변글쓰기가 가능하다 *** --
update tbl_member set gradelevel = 10
where userid in ('admin', 'seoyh');
-- 2개 행 이(가) 업데이트되었습니다.

commit;
-- 커밋 완료.

select *
from tbl_member
order by gradelevel desc;


-- *** 이전글, 다음글 보기 *** --
SELECT previousseq, previoussubject
     , seq, fk_userid, name, subject, content, readCount, regDate, pw
     , nextseq, nextsubject
FROM
 (
     select lag(seq) over(order by seq desc) AS previousseq
          , lag(subject) over(order by seq desc) AS previoussubject
          , seq, fk_userid, name, subject, content, readCount, regDate, pw  
          , lead(seq) over(order by seq desc) AS nextseq
          , lead(subject) over(order by seq desc) AS nextsubject
     from tbl_board
     where status = 1
     and lower(subject) like '%'||lower('jAvA')||'%'
 ) V
WHERE V.seq = 6;


SELECT previousseq, previoussubject
     , seq, fk_userid, name, subject, content, readCount, regDate, pw
     , nextseq, nextsubject
FROM
 (
     select lag(seq) over(order by seq desc) AS previousseq
          , lag(subject) over(order by seq desc) AS previoussubject
          , seq, fk_userid, name, subject, content, readCount, regDate, pw  
          , lead(seq) over(order by seq desc) AS nextseq
          , lead(subject) over(order by seq desc) AS nextsubject
     from tbl_board
     where status = 1
     and lower(subject) like '%'||lower('jAvA')||'%'
 ) V
WHERE V.seq = 7;


SELECT previousseq, previoussubject
     , seq, fk_userid, name, subject, content, readCount, regDate, pw
     , nextseq, nextsubject
FROM
 (
     select lag(seq) over(order by seq desc) AS previousseq
          , lag(subject) over(order by seq desc) AS previoussubject
          , seq, fk_userid, name, subject, content, readCount, regDate, pw  
          , lead(seq) over(order by seq desc) AS nextseq
          , lead(subject) over(order by seq desc) AS nextsubject
     from tbl_board
     where status = 1
     and lower(subject) like '%'||lower('jAvA')||'%'
 ) V
WHERE V.seq = 11;


--- 댓글 페이징 처리 하기 ---
SELECT seq, fk_userid, name, content, regDate
FROM 
(
    select row_number() over(order by seq desc) AS rno
         , seq, fk_userid, name, content
         , to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate
    from tbl_comment
    where parentSeq = to_number('211')
) V
WHERE rno BETWEEN 1 AND 3;  -- 1페이지


SELECT seq, fk_userid, name, content, regDate
FROM 
(
    select row_number() over(order by seq desc) AS rno
         , seq, fk_userid, name, content
         , to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate
    from tbl_comment
    where parentSeq = to_number('211')
) V
WHERE rno BETWEEN 4 AND 6;  -- 2페이지


select seq, fk_userid, name, content
     , to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate
from tbl_comment
where parentSeq = to_number('211')
ORDER BY seq DESC
OFFSET (to_number('1')-1)*3 ROW  
FETCH NEXT 3 ROW ONLY;  -- 1페이지


select seq, fk_userid, name, content
     , to_char(regDate, 'yyyy-mm-dd hh24:mi:ss') AS regDate
from tbl_comment
where parentSeq = to_number('211')
ORDER BY seq DESC
OFFSET (to_number('2')-1)*3 ROW  
FETCH NEXT 3 ROW ONLY;  -- 2페이지

/

drop table tbl_comment purge;
drop sequence commentSeq;
drop table tbl_board purge;
drop sequence boardSeq;


create table tbl_board
(seq           number                not null    -- 글번호
,fk_userid     varchar2(20)          not null    -- 사용자ID
,name          varchar2(20)          not null    -- 글쓴이 
,subject       Nvarchar2(200)        not null    -- 글제목
,content       Nvarchar2(2000)       not null    -- 글내용   -- clob (최대 4GB까지 허용) 
,pw            varchar2(20)          not null    -- 글암호
,readCount     number default 0      not null    -- 글조회수
,regDate       date default sysdate  not null    -- 글쓴시간
,status        number(1) default 1   not null    -- 글삭제여부   1:사용가능한 글,  0:삭제된글
,commentCount  number default 0      not null    -- 댓글의 개수

,groupno       number                not null    -- 답변글쓰기에 있어서 그룹번호 
                                                 -- 원글(부모글)과 답변글은 동일한 groupno 를 가진다.
                                                 -- 답변글이 아닌 원글(부모글)인 경우 groupno 의 값은 groupno 컬럼의 최대값(max)+1 로 한다.

,fk_seq         number default 0      not null   -- fk_seq 컬럼은 절대로 foreign key가 아니다.!!!!!!
                                                 -- fk_seq 컬럼은 자신의 글(답변글)에 있어서 
                                                 -- 원글(부모글)이 누구인지에 대한 정보값이다.
                                                 -- 답변글쓰기에 있어서 답변글이라면 fk_seq 컬럼의 값은 
                                                 -- 원글(부모글)의 seq 컬럼의 값을 가지게 되며,
                                                 -- 답변글이 아닌 원글일 경우 0 을 가지도록 한다.

,depthno        number default 0       not null  -- 답변글쓰기에 있어서 답변글 이라면
                                                 -- 원글(부모글)의 depthno + 1 을 가지게 되며,
                                                 -- 답변글이 아닌 원글일 경우 0 을 가지도록 한다.

,fileName       varchar2(255)                    -- WAS(톰캣)에 저장될 파일명(2025020709291535243254235235234.png)                                       
,orgFilename    varchar2(255)                    -- 진짜 파일명(강아지.png)  // 사용자가 파일을 업로드 하거나 파일을 다운로드 할때 사용되어지는 파일명 
,fileSize       number                           -- 파일크기 


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


create table tbl_comment
(seq           number               not null   -- 댓글번호
,fk_userid     varchar2(20)         not null   -- 사용자ID
,name          varchar2(20)         not null   -- 성명
,content       varchar2(1000)       not null   -- 댓글내용
,regDate       date default sysdate not null   -- 작성일자
,parentSeq     number               not null   -- 원게시물 글번호
,status        number(1) default 1  not null   -- 글삭제여부
                                               -- 1 : 사용가능한 글,  0 : 삭제된 글
                                               -- 댓글은 원글이 삭제되면 자동적으로 삭제되어야 한다.
,constraint PK_tbl_comment_seq primary key(seq)
,constraint FK_tbl_comment_userid foreign key(fk_userid) references tbl_member(userid)
,constraint FK_tbl_comment_parentSeq foreign key(parentSeq) references tbl_board(seq) on delete cascade
,constraint CK_tbl_comment_status check( status in(1,0) ) 
);

create sequence commentSeq
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;


desc tbl_board;

begin
    for i in 1..100 loop
        insert into tbl_board(seq, fk_userid, name, subject, content, pw, readCount, regDate, status, groupno)
        values(boardSeq.nextval, 'dltnstls89', '연규영', '연규영 입니다'||i, '안녕하세요? 연규영'|| i ||' 입니다.', '1234', default, default, default, i);
    end loop;
end;
/

begin
    for i in 101..200 loop
        insert into tbl_board(seq, fk_userid, name, subject, content, pw, readCount, regDate, status, groupno)
        values(boardSeq.nextval, 'eomjh', '엄정화', '엄정화 입니다'||i, '안녕하세요? 엄정화'|| i ||' 입니다.', '1234', default, default, default, i);
    end loop;
end;
/
commit;

select * 
from tbl_board 
order by seq desc;
