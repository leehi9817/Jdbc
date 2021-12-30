package com.javaex.ex04;

import java.util.ArrayList;
import java.util.List;

public class AuthorApp {

	public static void main(String[] args) {

		List<AuthorVo> list = new ArrayList<AuthorVo>();
		AuthorDao authorDao = new AuthorDao();
		
		
		//작가등록
		AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(vo01);

		AuthorVo vo02 = new AuthorVo("박경리", "경상남도 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("유시민", "17대 국회의원");
		authorDao.authorInsert(vo03);
		
		AuthorVo vo04 = new AuthorVo("기안84", "기안동에서 산 84년생");
		authorDao.authorInsert(vo04);
		
		AuthorVo vo05 = new AuthorVo("강풀", "온라인 만화가 1세대");
		authorDao.authorInsert(vo05);
		
		AuthorVo vo06 = new AuthorVo("김영하", "알쓸신잡");
		authorDao.authorInsert(vo06);
		
		printAuthor(list, authorDao);
		
		//작가수정
		AuthorVo authorVo = new AuthorVo(2, "박경리(수정)", "경상남도 통영(수정)");
		authorDao.authorUpdate(authorVo);
		
		printAuthor(list, authorDao);
		
		//작가삭제
		authorDao.authorDelete(1);
		
		printAuthor(list, authorDao);
		
	}
	
	public static void printAuthor(List<AuthorVo> list, AuthorDao authorDao) {
		System.out.println("-----------------------------------");
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		System.out.println("-----------------------------------");
	}

}
