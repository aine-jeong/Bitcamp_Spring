package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class IntroController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("IntroController 요청 실행: handleRequest함수 실행" );
		//필요한 객체를 만들고 view 지정
		ModelAndView mav = new ModelAndView();
		mav.addObject("name","hong");
		mav.setViewName("Intro");
		
		return mav;
	}

}
