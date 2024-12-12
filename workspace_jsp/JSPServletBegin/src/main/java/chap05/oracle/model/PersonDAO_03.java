package chap05.oracle.model;

import java.sql.SQLException;
import java.util.List;

import chap05.oracle.domain.PersonDTO_02;

public interface PersonDAO_03 {
	// 개인성향을 입력(insert)해주는 추상메소드(미완성메소드)
	int personRegister(PersonDTO_02 psdto) throws SQLException;
	
	//tbl_person_interest 테이블에 저장되어진 행(데이터)을 읽어다가(select) 웹페이지에 보여줘야 한다.
	List<PersonDTO_02> selectAll() throws SQLException;
	
	//tbl_person_interest 테이블에 저장되어진 특정 1개행(데이터)만 읽어다가(select) 웹페이지에 보여줘야 한다.
	PersonDTO_02 selectOne(String seq) throws SQLException;

	int personDelete(int seqNum) throws SQLException;

}
