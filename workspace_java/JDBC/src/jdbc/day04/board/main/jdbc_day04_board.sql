show user;

select *
from tbl_member
order by userseq desc;


--- 게시판 테이블 생성하기 ---
create table tbl_board
(boardno       number         not null        -- 글번호
,fk_userid     varchar2(30)   not null        -- 작성자아이디
,subject       Nvarchar2(100) not null        -- 글제목
,contents      Nvarchar2(200) not null        -- 글내용
,writeday      date default sysdate not null  -- 작성일자 
,viewcount     number default 0 not null      -- 조회수 
,boardpasswd   varchar2(20) not null          -- 글암호
,constraint PK_tbl_board_boardno primary key(boardno)
,constraint FK_tbl_board_fk_userid foreign key(fk_userid) references tbl_member(userid)
);



create sequence seq_board
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

desc tbl_board;

select *
from tbl_board
order by boardno desc;

desc tbl_member;
-- 글번호   글제목   작성자   작성일자    조회수

-----------------------------------------------------------------------------------------
/*
   Transaction(트랜잭션) 처리 실습을 위해서
   tbl_member 테이블의 point 컬럼의 값은 최대 30을 넘지 못하도록 check 제약을 걸도록 하겠습니다.
*/
-----------------------------------------------------------------------------------------

alter table tbl_member
add constraint CK_tbl_member_point check (point between 0 and 30);

select *
from tbl_member
order by userseq desc;

update tbl_member set point = point + 10
where userid = 'rbdud9698';