package com.javaex.ex05;

import java.util.ArrayList;
import java.util.List;

public class BookApp {

	public static void main(String[] args) {
		
		List<BookVo> list = new ArrayList<BookVo>();
		BookDao bookDao = new BookDao();
		
		//책등록
		BookVo vo01 = new BookVo("우리들의 일그러진 영웅", "다림", "1998-02-22", 1);
		bookDao.bookInsert(vo01);
		
		BookVo vo02 = new BookVo("삼국지", "민음사", "2002-03-01", 1);
		bookDao.bookInsert(vo02);
		
		BookVo vo03 = new BookVo("토지", "재미주의", "2015-03-09", 3);
		bookDao.bookInsert(vo03);
		
		BookVo vo04 = new BookVo("유시민의 글쓰기 특강", "생각의길", "2015-04-01", 3);
		bookDao.bookInsert(vo04);
		
		BookVo vo05 = new BookVo("패션왕", "중앙북스(books)", "2021-02-22", 4);
		bookDao.bookInsert(vo05);
		
		BookVo vo06 = new BookVo("순정만화", "재미주의", "2011-08-03", 5);
		bookDao.bookInsert(vo06);
		
		BookVo vo07 = new BookVo("오직두사람", "문학동네", "2017-05-04", 6);
		bookDao.bookInsert(vo07);
		
		BookVo vo08 = new BookVo("26년", "재미주의", "2012-02-04", 5);
		bookDao.bookInsert(vo08);
		
		BookVo vo09 = new BookVo("1984", "동동출판사", "1973-09-17", 6);
		bookDao.bookInsert(vo09);
		
		printBook(list, bookDao);
		
		
		//책수정
		BookVo vo10 = new BookVo(3, "토지", "마로니에북스", "2012-08-15", 2);
		bookDao.bookUpdate(vo10);
		
		printBook(list, bookDao);
		
		
		//책삭제
		BookVo vo11 = new BookVo(9);
		bookDao.bookDelete(vo11);
		
		printBook(list, bookDao);
		
	}

	public static void printBook(List<BookVo> list, BookDao bookDao) {
		System.out.println("-----------------------------------");
		list = bookDao.bookSelect();
		for(int i=0; i<list.size(); i++) {
			BookVo vo = list.get(i);
			System.out.println(vo.getBookId() + ", " + vo.getTitle() + ", " + vo.getPubs() + ", " + vo.getPubDate() + ", " + vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("-----------------------------------");
	}
	
}
