package com.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.NewArticleCommand;
import com.service.ArticleService;

/*
 -클라이언트 요청
 1. 화면 주세요(글쓰기, 로그인하기): write.do
 2. 처리해주세요(글쓰기 입력 처리, 로그인 완료 처리): writeok.do
 
 - 요청주소가 write.do 라면, 화면보여주기
 - 요청 주소가 writeok.do라면, 처리해주기
 
 클라이언트의 요청 주소 1개를 가지고 나눠서 쓰고싶다 (근거: GET, POST)
 >> http://localhost:8090/SpringMVC/article/newArticle.do
 
 전송 방식이
 1. GET  > 화면  > view 제공
 2. POST > 서비스 > insert, update처리
 */

@Controller
@RequestMapping("/article/newArticle.do")
public class NewArticleController {
	
	private ArticleService articleservice;
	
	@Autowired //자동 주소값 주입
	public void setArticleservice(ArticleService articleservice) {
		this.articleservice = articleservice;
	}

	
	@RequestMapping(method=RequestMethod.GET) //화면 제공
	public String form() { // 매핑되는 함수의 return타입이 String이라면, view의 주소인 것으로 약속되어있다.
		return "article/newArticleForm";
		// /WEB-INF/views/ + article/newArticleForm + .jsp
	}
	
	/*
	 #### 1.
	@RequestMapping(method=RequestMethod.POST) //로직(insert) 처리
	public ModelAndView submit(HttpServletRequest request) { 
		
		NewArticleCommand article = new NewArticleCommand();
		article.setParentId(Integer.parseInt(request.getParameter("parentId")));
		article.setTitle(request.getParameter("title"));
		article.setContent(request.getParameter("content"));
		
		//NewArticleController가 service를 필요로 한다
		//NewArticleController가 service의 주소값을 갖고싶다 -> 연관
		
		this.articleservice.writeArticle(article);
		//처리 완료
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", article);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}
	
	//#### 
	//2. Spring에서 parameter를 DTO 객체로 받기
	//2.1. 자동화 >> 전제조건 >> input name="" 값이 DTO 객체의 member field명과 동일
	@RequestMapping(method=RequestMethod.POST) //로직(insert) 처리
	public ModelAndView submit(NewArticleCommand command) { 
		
		//1. 자동 DTO 생성 (NewArticleCommand article = new NewArticleCommand();)
		//2. 넘어온 parameter값이 setter 통해서 자동 주입
		//3. NewArticleCommand 객체가 IOC 컨테이너 안에 자동 생성 > id값이 자동으로 붙는다 (id="newArticleCommand")
		// 즉, 세터로 주입되는 과정 생략 가능
		
		//NewArticleController가 service를 필요로 한다
		//NewArticleController가 service의 주소값을 갖고싶다 -> 연관
		this.articleservice.writeArticle(command);
		//처리 완료
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("newArticleCommand", command);
		mv.setViewName("article/newArticleSubmitted");
		
		return mv;
	}
	*/
	
	@RequestMapping(method=RequestMethod.POST) //로직(insert) 처리
	public String submit(@ModelAttribute("Articladata") NewArticleCommand command) { 
		
		//NewArticleController가 service의 주소값을 갖고싶다 -> 연관
		this.articleservice.writeArticle(command);
		//처리 완료
		//view 페이지가 데이터를 어떻게 받느냐
		//NewArticleCoaand 객체가 IOC 컨테이너 안에 자동 생성 > id 값이 자동으로 붙는다 > id="newArticleCommand"
		
		//자동 forward > view에서 객체 이름을 뭘로 받는거지 ? 
		//> newArticleCommand가 key값으로 자동 forward되는 것
		
		//forward key의 이름을 직접 정의하고싶을 때!
		//어노테이션 사용 (modelAttribute)
		//@ModelAttribute("Articladata") NewArticleCommand command
		// > mv.addObject("Articledata", commad) 한 것과 같다.
		
		// 제일 중요한 것 : Parameter로 DTO 받을 수 있다~~~~~~~~~~~ 
		
		return "article/newArticleSubmitted";
	}
	
}
