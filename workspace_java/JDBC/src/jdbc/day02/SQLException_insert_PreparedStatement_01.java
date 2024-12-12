package jdbc.day02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLException_insert_PreparedStatement_01 {

	public static void main(String[] args) {

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);

		try {
			String stno;
			String name;
			String tel;
			String addr;
			String fk_classno;
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "JDBC_USER", "gclass");
			//——————————————————————————————————————————————————————————————————————————————————————————————
			
			
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			do {
				System.out.print("😊 학번 : ");
				stno = sc.nextLine();

				try {
					Integer.parseInt(stno);
					//————————중복 검사 하기————————
					
					String sql = " select * "
								+" from tbl_student "
								+" where stno = ? ";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, stno);
					rs = pstmt.executeQuery();
					
					if(rs.next()) {
						System.out.println("🤚 입력하신 학번 " + stno + "은(는) 이미 사용 중입니다. 🤚");
					}
					else {
						System.out.println("🤚 입력하신 학번 " + stno + "은(는) 사용 가능합니다. 🤚");
						break;
					}
					
					//—————————중복 검사 끝————————
					
				} catch (NumberFormatException e) {
					System.out.println(">> [경고] 학번은 숫자로만 입력하세요!! <<\n");
				}
			} while (true);
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			do {
				System.out.print("😊 성명 : ");
				name = sc.nextLine();

				if (name.isEmpty()) {
					System.out.println(">> [경고] 성명은 필수입력 사항 입니다!! <<\n");
				} else if (name.length() > 20) {
					System.out.println(">> [경고] 성명은 최대 20글자 이내이어야 합니다!! <<\n");
				} else {
					break;
				}
			} while (true);
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			do {
				System.out.print("😊 연락처 : ");
				tel = sc.nextLine();

				if (tel.isEmpty()) {
					System.out.println(">> [경고] 연락처는 필수입력 사항 입니다!! <<\n");
				} else if (tel.length() > 15) {
					System.out.println(">> [경고] 연락처는 최대 15글자 이내이어야 합니다!! <<\n");
				} else {
					break;
				}
			} while (true);
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			do {

				System.out.print("😊 주소 : ");
				addr = sc.nextLine();

				if (addr.length() > 100) {
					System.out.println(">> [경고] 주소는 최대 100글자 이내이어야 합니다!! <<\n");
					continue;
				}
				break;

			} while (true);
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
			
			do {
				System.out.print("😊 학급번호 : ");
				fk_classno = sc.nextLine();

				try {
					Integer.parseInt(fk_classno);

					String sql = " insert into tbl_student(stno, name, tel, addr, fk_classno) "
							+ " values (to_number(?), ?, ?, ?, to_number(?) )";

					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, stno);

					pstmt.setString(2, name);

					pstmt.setString(3, tel);

					pstmt.setString(4, addr);

					pstmt.setString(5, fk_classno);

					int n = 0;

					
					n = pstmt.executeUpdate();
					
					if (n == 1) {
						System.out.println(">> 학생데이터 입력 성공!! <<");
						
						sql = " select stno, name, tel, addr, to_char(registerdate, 'yyyy-mm-dd') as registerday, fk_classno, classname, teachername "
							+ " from tbl_student S join tbl_class C "
							+ " on s.fk_classno = c.classno "
							+ " where stno = ? ";
						pstmt = conn.prepareStatement(sql);
						pstmt.setString(1, stno);
						
						rs = pstmt.executeQuery();
						
						rs.next();
						
						System.out.println("\n==== 입력한 결과 조회 ====");
						System.out.println("🤔 학번 : " + rs.getInt("stno"));
						System.out.println("🤔 연락처 : " + rs.getString("tel"));
						System.out.println("🤔 주소 : " + rs.getString("addr"));
						System.out.println("🤔 입력일자 : " + rs.getString("registerday"));
						System.out.println("🤔 학급번호 : " + rs.getInt("fk_classno"));
						System.out.println("🤔 학급명 : " + rs.getString("classname"));
						System.out.println("🤔 담임교사명 : " + rs.getString("teachername"));
						
						
						break;
					}

					
				} catch (NumberFormatException e) {
					System.out.println(">> [경고] 학급번호는 숫자로만 입력하세요!! <<\n");
				} catch (SQLException e) {
					if (e.getErrorCode() == 2291)
					System.out.println("입력하신 학급번호 " + fk_classno + "은(는) 학급테이블에 존재하지 않습니다.");
					
					String sql = " select classno, classname "
								+ " from tbl_class ";
					
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					
					StringBuilder sb = new StringBuilder();
					sb.append("-".repeat(20)+ "\n");
					sb.append("학급번호\t학급명\n");
					sb.append("-".repeat(20)+ "\n");
					while (rs.next()) {
						sb.append(rs.getInt("classno") + "\t" + rs.getString("classname")+ "\n");
						
					}
					System.out.println(sb.toString());
					
				}
			} while (true);
			// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

			
			
			
			
			
			//——————————————————————————————————————————————————————————————————————————————————————————————
			
			
		} catch (ClassNotFoundException e) {
			System.out.println(">>> ojdbc8.jar 파일이 없습니다. <<<");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}

				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}

				if (conn != null) {
					conn.close();
					conn = null;
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		sc.close();
		System.out.println("프로그램 종료😘");
	}// end of main()-------------------------------------------------------------------

}
