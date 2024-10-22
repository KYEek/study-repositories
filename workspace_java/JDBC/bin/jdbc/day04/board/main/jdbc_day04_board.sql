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

insert into tbl_board(boardno, fk_userid, subject, contents, boardpasswd)
values(seq_board.nextval, 'rbdud9698', 'ddd', 'asdff' , 'asdfs');

truncate table tbl_board;

drop sequence seq_board;

select *
from tbl_board;

commit;
rollback;

    select boardno
        , case when length(subject) > 15 then substr(subject, 1, 13)||'..' else subject end subject
        , name, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') writeday, viewcount
    from tbl_board B join tbl_member M
    on B.fk_userid = M.userid
    order by boardno desc;
    
    select subject, contents, name, viewcount, fk_userid
    from 
    (
        select subject, contents, viewcount, fk_userid
        from  tbl_board
        where boardno = 5
    ) B join tbl_member M
    on B.fk_userid = M.userid;
    
    
    
    
    
    ---- *** 댓글 테이블 생성하기 *** ----
    create table tbl_comment
    (commentno   number         not null        -- 댓글번호
    ,fk_boardno  number         not null        -- 원글의 글번호 
    ,fk_userid   varchar2(30)   not null        -- 작성자 아이디
    ,contents    Nvarchar2(100) not null        -- 댓글내용
    ,writeday    date default sysdate not null  -- 작성일자
    ,constraint PK_tbl_comment_commentno primary key(commentno)
    ,constraint FK_tbl_comment_fk_boardno foreign key(fk_boardno) references tbl_board(boardno) on delete cascade  
    ,constraint FK_tbl_comment_fk_userid foreign key(fk_userid) references tbl_member(userid) 
    );
    -- Table TBL_COMMENT이(가) 생성되었습니다.
    
    
    
    create sequence seq_comment
    start with 1
    increment by 1
    nomaxvalue
    nominvalue
    nocycle
    nocache;
    -- Sequence SEQ_COMMENT이(가) 생성되었습니다.
    
    select *
    from tbl_comment;
    
    
    select fk_boardno, count(*) cmtcnt
    from tbl_comment
    group by fk_boardno;
    
    select boardno, case when cmtcnt is null then subject else subject|| ' [' ||cmtcnt || ']' end subject
            , name, writeday, viewcount
    from
    (select boardno
    , case when length(subject) > 15 then substr(subject, 1, 13)||'..' else subject end subject
    , name, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') writeday, viewcount
    from tbl_board B join tbl_member M
    on B.fk_userid = M.userid) V1
    left join
    (select fk_boardno, count(*) cmtcnt
    from tbl_comment
    group by fk_boardno) V2
    on V1.boardno = V2.fk_boardno
    order by boardno desc;
    
    
    select contents, name, to_char(writeday, 'yyyy-mm-dd hh24:mi:ss') writeady
    from
    (
    select *
    from tbl_comment 
    where fk_boardno = 1
    ) C join tbl_member M
    on C.fk_userid = M.userid
    order by commentno desc;
    
    
    select subject, contents from tbl_board 
                    where boardno = 1;
                    
                    
    
    select count(*) as total    --전체 글의 개수가 몇개인지 구해줘요
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 6, 1,0)) as previous6    --6일전
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 5, 1,0)) as previous5    --5일전
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 4, 1,0)) as previous4    --4일전
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 3, 1,0)) as previous3    --3일전
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 2, 1,0)) as previous2    --2일전
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 1, 1,0)) as previous1    --1일전 작성일과의 차이를 구하고 1(어제)면 1, 아니면 0
            ,sum(decode(to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd'), 0, 1,0)) as today    --오늘과 작성일과의 차이를 구하고 0(오늘)이면 1, 아니면 0
            --그리고 그렇게 구해진 날자를 sum함수로 그 날짜에 글이 몇개인지 구해줘요
    from tbl_board
    where to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') - to_date(to_char(writeday,'yyyymmdd'),'yyyymmdd') < 7;     --날짜에서 시분초는 제외하고 날짜만 보기 위해서 to_char를 했어요 그리고 다시 날짜로 바꾸기 위해 to_date를 했어요 그리고 작성일과의 날짜가 7일 이내인 것을 조건으로 했어요
    
    
    
    select to_char(writeday, 'yyyy-mm-dd') as writeday
            , grouping(to_char(writeday, 'yyyy-mm-dd'))
            , count(*) as cnt
    from tbl_board
    where to_char(writeday, 'yyyymm') = to_char(sysdate, 'yyyymm')
    group by rollup(to_char(writeday, 'yyyy-mm-dd'));