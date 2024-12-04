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

create table tbl_main_page
(imgno          number not null
,imgname        Nvarchar2(20) not null
,imgfilename    Nvarchar2(30) not null
,constraint     PK_tbl_main_page_imgno primary key(imgno)
);

create sequence seq_main_image
start with 1
increment by 1
nomaxvalue
nominvalue
nocycle
nocache;

insert into tbl_main_page(imgno, imgname, imgfilename) values (seq_main_image.nextval, '미샤', '미샤.png');
insert into tbl_main_page(imgno, imgname, imgfilename) values (seq_main_image.nextval, '원더플레이스', '원더플레이스.png');
insert into tbl_main_page(imgno, imgname, imgfilename) values (seq_main_image.nextval, '레노보', '레노보.png');
insert into tbl_main_page(imgno, imgname, imgfilename) values (seq_main_image.nextval, '동원', '동원.png');

commit;
select imgno, imgname, imgfilename
from tbl_main_page
order by imgno asc;