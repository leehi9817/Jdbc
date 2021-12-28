package com.javaex.ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookSelectAll {

	public static void main(String[] args) {

		List<AllVo> allList = new ArrayList();
		
		//책, 작가 데이터 가져오기
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. Connection 얻어오기
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			System.out.println("접속성공");
			
			// 3. SQL문 준비 / 바인딩 / 실행
			//문자열 만들기
			String query = "";
			query += "select  b.book_id, ";
			query += "        b.title, ";
			query += "        b.pubs, ";
			query += "        b.pub_date, ";
			query += "        a.author_id, ";
			query += "        a.author_name, ";
			query += "        a.author_desc ";
			query += "from author a, book b ";
			query += "where a.author_id = b.author_id ";
			
			//문자열 쿼리문으로 만들기
			pstmt = conn.prepareStatement(query);
			
			//바인딩 --> 생략 (? 없음)
			
			//실행
			rs = pstmt.executeQuery();
			
			// 4.결과처리
			while(rs.next()) {
				int bookId = rs.getInt("book_id");
				String bookTitle = rs.getString("title");
				String bookPubs = rs.getString("pubs");
				String bookPubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				String authorName = rs.getString("author_name");
				String authorDesc = rs.getString("author_desc");
				
				AllVo vo = new AllVo(bookId, bookTitle, bookPubs, bookPubDate, authorId, authorName, authorDesc);
				allList.add(vo);
				
				//System.out.println(bookId + ", " + bookTitle + ", " + bookPubs + ", " + bookPubDate + ", " + authorId + ", " + authorName + ", " + authorDesc);
			}
			
			//출력
			for(int i=0; i<allList.size(); i++) {
				AllVo allVo = allList.get(i);
				System.out.println(allVo.getBookId() + ", " + allVo.getBookTitle() + ", " + allVo.getBookPubs() + ", " + allVo.getBookPubDate() + ", " +allVo.getAuthorId() + ", " +allVo.getAuthorName() + ", " +allVo.getAuthorDesc());
			}
			
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
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

	}

}
