------ ***** spring 기초 ***** ------

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
order by writeday desc;

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
order by writeday desc;

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
