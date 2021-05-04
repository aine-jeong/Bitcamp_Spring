package kr.or.bit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class HelloController implements Controller{
	//중간 프로젝트에서, 서블릿 프론트컨트롤러 역할을 얘가 한다 (Controller 인터페이스)
	
	public HelloController() {
		System.out.println("HelloController 객체 생성");
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//자동 호출되는 곳 (doGet, doPost처럼!)
		System.out.println("HelloController 요청 실행: handleRequest 함수 실행");
		
		//데이터를 넣을 수 있고, (request.setAttribute)
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("name","bituser"); //= request.setAttribute("name","bituser")
		mav.setViewName("Hello");
		
		//모델뷰 객체의 주소값 리턴 -> resolver한테 보낸다
		//internalResourceViewResolver에 의해서 view단의 주소가 조합된다
		//(prefix) /WEB-INF/views/  +  Hello  + .jsp >> /WEB-INF/views/Hello.jsp
		
		//만약 resolver만들기 싫다면?
		//mav.setViewName("/WEB-INF/views/Hello.jsp") 이렇게 작성해도 된다
		
		return mav;
	}
	

}
