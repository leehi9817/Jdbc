package com.javaex.ex07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SearchDao {

	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 생성자

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
	
	public List<SearchVo> searchSelect(SearchVo searchVo) {

		List<SearchVo> searchList = new ArrayList<SearchVo>();
		
		// 1. JDBC 드라이버 (Oracle) 로딩
		// 2. Connection 얻어오기
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행
			String query = "";
			query += "select  b.book_id, ";
			query += " 		  b.title, ";
			query += "        b.pubs, ";
			query += "        b.pub_date, ";	
			query += "        a.author_name ";
			query += "from book b, author a ";
			query += "where b.author_id = a.author_id ";
			query += "and     (b.title like ? ";
			query += "         or b.pubs like ? ";
			query += "         or a.author_name like ?) ";
			query += "order by b.book_id asc ";
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩
			pstmt.setString(1, "%" + searchVo.getKey() + "%");
			pstmt.setString(2, "%" + searchVo.getKey() + "%");
			pstmt.setString(3, "%" + searchVo.getKey() + "%");
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while (rs.next()) {
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				String authorName = rs.getString("author_name");
				
				SearchVo vo = new SearchVo(bookId, title, pubs, pubDate, authorName);
				searchList.add(vo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			// 5. 자원정리
			close();
		}

		return searchList;
		
	}

}
