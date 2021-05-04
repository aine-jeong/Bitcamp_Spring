package controllers.customer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import dao.NoticeDao;
import vo.Notice;

// 게시판 상세보기 서비스 controller

public class NoticeDetailController implements Controller{
	
	public NoticeDetailController() {
		System.out.println("[NoticeDetailController]");
	}
	
	private NoticeDao noticedao;
	//setter를 통해서 NoticeDao객체의 주소를 주입받겠다.
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//public Notice getNotice(String seq)
		
		String seq = request.getParameter("seq");
		Notice notice = noticedao.getNotice(seq);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("notice", notice);
		mv.setViewName("noticeDetail.jsp");
		
		return mv;
	}

}
