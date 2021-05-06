package com.service;

import com.model.NewArticleCommand;

/*
 1. 객체 만들 때 어노테이션 붙이고
 @Service
 public class ArticleService
 
 2. xml 상단에 아래줄과 같이 적어주면
 <context:component-scan base-package="com.service">
 
 어노테이션이 붙어있는 것들을 다 객체로 만들어준다!!!!!
 
 지금은 수동으로 해보쟈 ,,
 */

public class ArticleService {
	
	public ArticleService() {
		System.out.println("ArticleService 생성자 호출");
	}
	
	public void writeArticle(NewArticleCommand command) {
		//DAO 있다고 가정
		//insert 실행됐다고 가정
		//지금은 DB작업을 실제로 하지는 않는다~~
		System.out.println("글쓰기 작업 완료: " + command.toString());
	}
}
