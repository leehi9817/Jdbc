package com.javaex.ex05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자
	public BookDao() {

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

	// 책 추가
	public void bookInsert(BookVo bookVo) {

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "insert into book ";
			query += "values (seq_book_id.nextval, ?, ?, ?, ?) ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 저장되었습니다.(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}

	}

	// 책 삭제
	public void bookDelete(BookVo bookVo) {
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "delete from book ";
			query += "where book_id = ? ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setInt(1, bookVo.getBookId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 삭제되었습니다.(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}
	}

	// 책 수정
	public void bookUpdate(BookVo bookVo) {
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "update  book ";
			query += "set     title = ?, ";
			query += "        pubs = ?, ";
			query += "        pub_date = ?, ";
			query += "        author_id = ? ";
			query += "where book_id = ? ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩
			pstmt.setString(1, bookVo.getTitle());
			pstmt.setString(2, bookVo.getPubs());
			pstmt.setString(3, bookVo.getPubDate());
			pstmt.setInt(4, bookVo.getAuthorId());
			pstmt.setInt(5, bookVo.getBookId());

			// 실행
			int count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println(count + " 건이 수정되었습니다.(책)");

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}
	}

	// 책 리스트 가져오기
	public List<BookVo> bookSelect() {

		List<BookVo> bookList = new ArrayList<BookVo>();

		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select  b.book_id, ";
			query += "        b.title, ";
			query += "        b.pubs, ";
			query += "        b.pub_date, ";
			query += "        a.author_id, ";
			query += "        a.author_name, ";
			query += "        a.author_desc ";
			query += "from book b, author a ";
			query += "where b.author_id = a.author_id ";
			query += "order by book_id asc ";

			// 문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);

			// 바인딩 --> 생략 (? 없음)

			// 실행
			rs = pstmt.executeQuery();

			// 4.결과처리
			while (rs.next()) {

				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");

				BookVo vo = new BookVo(bookId, title, pubs, pubDate, authorId, authorName, authorDesc);
				bookList.add(vo);

			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}

		return bookList;

	}

}
