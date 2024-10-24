select *
from tab;



select * from user_cons_columns where table_name = 'TBL_APPLYS';
select * from user_constraints where table_name =  'tbl_applys';
desc TBL_COMPANIES;

desc TBL_USERS;

SELECT * FROM USER_COL_COMMENTS WHERE table_name = 'TBL_COMPANIES';

select * from user_col_comments where table_name = 'TBL_USERS';

select * from TBL_JOB_TYPES;

select * from tbl_users;
commit;



select * from user_tab_columns where table_name = 'TBL_USERS';

select * from user_sequences;   

select * from TBL_COMPANIES;
select * from TBL_JOB_TYPES;
select * from tbl_users;
select * from tbl_admin;


insert into tbl_companies(COM_NO, COM_ID, COM_PASSWD, COM_NAME, COM_INTRO, COM_EMAIL, COM_PRESIDENT, COM_REVENUE, COM_TEL, COM_ADDRESS, FK_JOB_TCODE)
values(COM_SEQ.nextval, 'samsungelctronic', 'qwer1234$', '삼성전자', '갤럭시생성중', 'samsung@samsung.com', '이재용', 1000000000, '010-3434-4343', '서울시 강남구', 2);

insert into tbl_users(USER_NO, USER_ID, USER_PASSWD, USER_NAME, USER_JUBUN, USER_EMAIL, USER_TEL, USER_ADDRESS, FK_JOB_TCODE)
values(USER_SEQ.nextval, 'kangdw', 'qwer1234$', '강동원', '93002281', 'gangdw@naver.com', '010-2234-5567', '서울시 마포구 창전동', 1);


update tbl_users set USER_NAME = '메시', USER_TEL = '010-2312-4123', USER_ADDRESS = '강남구', FK_JOB_TCODE = 5 where user_no = '9999';   --개인 업데이트
update tbl_companies set com_name = '삼전', com_TEL = '043-232-4123', com_ADDRESS = '강남구',com_president = '연규영' ,com_revenue = 2000000, FK_JOB_TCODE = 5 where com_no = 1004; --기업 업데이트


rollback;
commit;

update tbl_companies set com_president = '이건희', com_tel = '02-1111-4444' where com_no = 1004;
rollback;

-- 일반 회원 로그인
select user_no, user_id, user_passwd, user_name, user_jubun, user_email, user_tel, user_address, user_regi, user_status, fk_job_tcode, job_type
from TBL_USERS join TBL_JOB_TYPES on FK_JOB_TCODE = JOB_CODE
where user_id ='ronaldo' and user_passwd = 'qwer1234$' and user_status = 1;

-- 기업 회원 로그인
select com_no, com_id, com_passwd, com_name, com_intro, com_email, com_president, com_revenue, com_tel, com_address, fk_job_tcode, com_regi, com_status, job_code, job_type 
from TBL_companies join TBL_JOB_TYPES on FK_JOB_TCODE = JOB_CODE 
where COM_ID = 'samsungelctronic' and COM_PASSWD = 'qwer1234$' and com_status = 1;

select * from tbl_companies where com_id = 'amazon' and com_status = 1;


-- 관리자 로그인
select admin_id, admin_passwd, admin_name from TBL_admin
where admin_ID = 'admin' and admin_passwd = 'qwer1234$';

select admin_id, admin_passwd, admin_name from TBL_admin
where admin_ID = 'admin' and admin_passwd = 'qwer1234$';
-- 개인 탈퇴, 비번 변경
update tbl_users set user_status = 0 where user_no = 14;
update tbl_users set user_passwd = 'Qwer1234!' where user_no = 14;
-- 기업 탈퇴, 비번 변경
update tbl_companies set com_status = 1 where com_no = 1006;
update tbl_companies set com_passwd = 'qwer1234!' where com_no = 1006;

update tbl_users set user_status = 1 where user_no = 14;
update tbl_companies set com_status = 1 where com_no = 1006;

select user_id
from tbl_users
where user_name = '이지은' and user_email = 'iu@gmail.com' and user_status = 1;

select * from tbl_companies where com_id = 'iu';


rollback;
commit;



-- 아이디 찾기
select user_id from tbl_users where user_name = '호날두' and user_email = 'ronaldo7@naver.com';
select com_id from tbl_companies where com_name = '아마존' and com_email = 'amazon@gmail.com';

--비번 변경
update tbl_users set user_passwd = 'aa' where user_id = 'ronaldo' and user_name = '호날두' and user_email = 'ronaldo7@naver.com';
update tbl_companies set com_passwd = 'aa' where com_id ='amazon' and com_name = '아마존' and com_email = 'amazon@gmail.com';


--아이디 비교 
select * from tbl_users where user_id = 'iu';
select * from tbl_companies where com_id = 'amazon';
select * from tbl_education;



SELECT user_name, age, gender, user_email, edu_degree, major_name, res_career
FROM
(
SELECT user_name, age, gender, user_email, edu_degree, fk_major_code, res_career, fk_edu_code
FROM
(
SELECT user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email, fk_edu_code, fk_major_code, res_career
FROM tbl_resumes R JOIN tbl_users U
ON R.fk_user_no = U.user_no
WHERE fk_major_code = 1
)A JOIN tbl_education E
ON A.fk_edu_code = E.edu_code
)B JOIN tbl_major M
ON B.fk_major_code = M.major_code
UNION 
SELECT user_name, age, gender, user_email, edu_degree, major_name, res_career
FROM
(
SELECT user_name, age, gender, user_email, edu_degree, fk_major_code, res_career
FROM
(
SELECT user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email, fk_edu_code, fk_major_code, res_career
FROM tbl_resumes R JOIN tbl_users U
ON R.fk_user_no = U.user_no
WHERE fk_major_code = 1
)A JOIN tbl_education E
ON A.fk_edu_code = E.edu_code
)B JOIN tbl_major M
ON B.fk_major_code = M.major_code;






SELECT user_name, age, gender, user_email, edu_degree, fk_major_code, res_career, fk_edu_code
FROM
(
SELECT user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email, fk_edu_code, fk_major_code, res_career
FROM tbl_resumes R JOIN tbl_users U
ON R.fk_user_no = U.user_no
WHERE fk_major_code = 1
)A JOIN tbl_education E
ON A.fk_edu_code = E.edu_code;

select user_name, age, gender, A.user_email, fk_major_code, res_career, fk_edu_code
from
(
    SELECT user_email, max(fk_edu_code)
    FROM tbl_resumes R JOIN tbl_users U
    ON R.fk_user_no = U.user_no
    WHERE fk_major_code = 1
    group by user_email
)a join
(
    SELECT user_name, func_age(user_jubun) AS age, func_gender(user_jubun) AS gender, user_email, fk_edu_code, fk_major_code, res_career
    FROM tbl_resumes R JOIN tbl_users U
    ON R.fk_user_no = U.user_no
    WHERE fk_major_code = 1
) B
on a.user_email = b.user_email;

select * from tbl_companies where com_no = 1001;