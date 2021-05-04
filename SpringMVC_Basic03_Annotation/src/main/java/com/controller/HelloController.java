package com.controller;

import java.util.Calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
implements Controller 로 handleRequest함수를 쓰는 방식은 
서비스 개수만큼 컨트롤러를 생성해야 한다.
ex)
게시판: 목록보기 >> list()
	  글쓰기 >> write()
	  수정하기 >> edit()

대안은 Controller 어노테이션 사용
 >> method 단위로 service 매핑 (: 하나의 controller로 모든 service를 만들 수 있다)
 */


@Controller
public class HelloController{
	public HelloController() {
		System.out.println("[HelloController 생성자 호출]");
	}
	
	@RequestMapping("/hello.do") //<a href="hello.do"></a> 와 연결
	public ModelAndView hello() {
		System.out.println("[hello.do Method Call]");
		ModelAndView mv = new ModelAndView();
		mv.addObject("greeting", getGreeting());
		mv.setViewName("Hello");
		
		return mv;
	}
	
	private String getGreeting() {
		int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		String data="";
		if(hour >= 6 && hour <=10) {
			data="학습시간";
		}else if(hour >= 11 && hour <=13) {
			data="배고픈시간";
		}else if(hour >= 14 && hour <=18) {
			data="졸린시간";
		}else {
			data="go home";
		}
		
		return data;
	}
}
