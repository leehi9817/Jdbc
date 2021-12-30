package com.javaex.ex04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	public AuthorDao() {

	}

	// 메소드 gs

	// 메소드 일반
	public void getConnection() {

		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

	}

	//작가 추가
	public void authorInsert(AuthorVo authorVo) {
		
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행

			// 쿼리문 만들기 --> ? 주의
			String query = "";

			// query = query + "문자열"
			query += "insert into author ";
			query += "values(seq_author_id.nextval, ?, ?)";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName()); // 첫번째 물음표의 데이터
			pstmt.setString(2, authorVo.getAuthorDesc());// 두번째 물음표의 데이터

			// 실행
			int count = pstmt.executeUpdate(); // 쿼리문 실행

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}

	}

	//작가 삭제
	public void authorDelete(int authorId) {

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기 --> ? 주의
			String query = "";
			// query = query + "문자열"
			query += "delete from author ";
			query += "where author_id = ? ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, authorId);

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}
	}
	
	//작가 수정
	public void authorUpdate(AuthorVo authorVo) {
		
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();
		
		try {
			
			// 3. SQL문 준비 / 바인딩 / 실행

			// 문자열 만들기 --> ? 주의
			String query = "";
			// query = query + "문자열"
			query += "update author ";
			query += "set author_name = ?, ";
			query += "    author_desc = ? ";
			query += "where author_id = ? ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, authorVo.getAuthorName());
			pstmt.setString(2, authorVo.getAuthorDesc());
			pstmt.setInt(3, authorVo.getAuthorId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 수정되었습니다.(작가)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}

	}

	//작가 리스트 가져오기
	public List<AuthorVo> authorSelect() {

		List<AuthorVo> authorList = new ArrayList<AuthorVo>();

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			// 문자열 만들기
			String query = "";
			query += "select  author_id, ";
			query += "        author_name, ";
			query += "        author_desc ";
			query += "from author ";
			query += "order by author_id asc ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩 --> 생략 (? 없음)

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				AuthorVo vo = new AuthorVo(authorId, authorName, authorDesc);
				authorList.add(vo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}

		return authorList;

	}


}
