package com.javaex.ex07;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.javaex.ex04.AuthorVo;
import com.javaex.ex05.BookVo;

public class SearchApp {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		List<SearchVo> list = new ArrayList<SearchVo>();
		SearchDao searchDao = new SearchDao();
		
		//작가 Dao
		AuthorDao authorDao = new AuthorDao();
		
		//작가 추가
		//SearchVo vo01 = new SearchVo("이문열", "경북 영양");
		//authorDao.authorInsert(vo01);

		SearchVo vo02 = new SearchVo("박경리", "경상남도 통영");
		authorDao.authorInsert(vo02);
		
		SearchVo vo03 = new SearchVo("유시민", "17대 국회의원");
		authorDao.authorInsert(vo03);
		
		SearchVo vo04 = new SearchVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(vo04);
		
		SearchVo vo05 = new SearchVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(vo05);
		
		SearchVo vo06 = new SearchVo("김영하", "알쓸신잡");
		authorDao.authorInsert(vo06);
		
		SearchVo vo07 = new SearchVo("이고잉", "개발자");
		authorDao.authorInsert(vo07);
		
		//책 Dao
		BookDao bookDao = new BookDao();
		
		//책 추가
		//SearchVo vo08 = new SearchVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		//bookDao.bookInsert(vo08);
		
		SearchVo vo09 = new SearchVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(vo09);
		
		SearchVo vo10 = new SearchVo("토지", "재미주의", "2015-03-09", 3);
		bookDao.bookInsert(vo10);
		
		SearchVo vo11 = new SearchVo("자바프로그래밍 입문", "위키북스", "2015-04-01", 7);
		bookDao.bookInsert(vo11);
		
		SearchVo vo12 = new SearchVo("패션왕", "중앙북스(books)", "2021-02-22", 4);
		bookDao.bookInsert(vo12);
		
		SearchVo vo13 = new SearchVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(vo13);
		
		SearchVo vo14 = new SearchVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(vo14);
		
		SearchVo vo15 = new SearchVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(vo15);

		
		System.out.println("키워드를 입력하세요.");
		System.out.print(">>");
		String key = sc.nextLine();
		
		SearchVo vo = new SearchVo(key);
		list = searchDao.searchSelect(vo);
		for(int i=0; i<list.size(); i++) {
			SearchVo searchVo = list.get(i);
			System.out.println(searchVo.getBookId() + ", " + searchVo.getTitle() + ", " + searchVo.getPubs() + ", " + searchVo.getPubDate() + ", " + searchVo.getAuthorName());
		}
		
		sc.close();
	}
	
}
