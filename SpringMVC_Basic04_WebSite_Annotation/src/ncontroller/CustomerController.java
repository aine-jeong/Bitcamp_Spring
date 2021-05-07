package ncontroller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
@RequestMapping("/customer/")
public class CustomerController {

	private NoticeDao noticedao;
	
	@Autowired
	public void setNoticedao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}
	
	/*
	 1. method안에서 return type이 "String"이면 리턴값이 view의 주소
	 
	 2. public ModelAndView notices ... > ModelAndView 객체 생성 > 데이터, 뷰 설정 > return ModelAndView 객체
	 	
	 3. public String 해도 데이터 담을 수 있도록 해주겠다
	 	public String notices (Model model){ }
	 	 >> 함수 실행시 내부적으로 Model객체의 주소가 들어온다 (Interface를 통해서)
	 	 
	 */
	
	/*
	//2번 방법
	//public List<Notice> getNotices(int page, String field, String query) 
	@RequestMapping("/customer/notice.htm")
	public ModelAndView notices(String pg , String f , String q) {
		
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
	
		if(f != null   && ! f.equals("")) {
			field = f;
		}

		if(q != null   && ! q.equals("")) {
			query = q;
		}
		
		//DAO 작업
		List<Notice> list = null;
		try {
			list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		//Spring 적용
		ModelAndView mv = new ModelAndView();
		mv.addObject("list", list);
		mv.setViewName("notice.jsp");
		return mv;
	}
	*/
	
	//3번 방법
	@RequestMapping("notice.htm")
	public String notices(String pg , String f , String q, Model model) {
		
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		
		if(pg != null   && ! pg.equals("")) {
			page  = Integer.parseInt(pg);
		}
	
		if(f != null   && ! f.equals("")) {
			field = f;
		}

		if(q != null   && ! q.equals("")) {
			query = q;
		}
		
		//DAO 작업
		List<Notice> list = null;
		try {
			list = noticedao.getNotices(page, field, query);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("list", list); // 자동으로 notice.jsp forward
		//<c:forEach items="${list}" var="n"> 사용 가능
		
		//모델 객체의 주소를 준다. 거기다가 addAttribute해두면, 리턴해주는 주소에 자동으로 forward시켜준다.
		return "notice.jsp";
	}
	
	/*
	//2번 방법
	//public Notice getNotice(String seq)
	@RequestMapping("/customer/noticeDetail.htm")
	public ModelAndView noticesDetail(String seq) {
	
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		ModelAndView  mv = new ModelAndView();
		
		mv.addObject("notice", notice);
		mv.setViewName("noticeDetail.jsp");
		
		return mv;
	}
	*/
	
	//3번 방법
	@RequestMapping("noticeDetail.htm")
	public String noticesDetail(String seq, Model model) {
	
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("notice", notice);
		return "noticeDetail.jsp";
	}
	
	
	//글쓰기 화면 (GET)
	@RequestMapping(value="noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {
		//http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer
		return "noticeReg.jsp";
	}
	
	//글쓰기 처리(POST)
	@RequestMapping(value="noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice n ,HttpServletRequest request) {
		System.out.println(n.toString());
		
		//cos.jar 자동 파일 업로드
		//실제 파일 업로드 구현 
		String filename = n.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로
		String fpath = path + "\\" + filename;
		System.out.println(fpath);
		
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(fpath);
			fs.write(n.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//파일명 추출 (DTO)
		n.setFileSrc(filename);
		
		try {
			noticedao.insert(n); //DB insert
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//insert나 update 하고나서 F5 누르면 계속 글이 써진당... 
		//so, 이동시켜야된다 -> 리스트로!
		//서버에게 새로운 요청(목록보기)  / ##목록재요청##
		//Spring) redirect:notice.htm
		//Servlet, jsp) location.href / response.sendRedirect
		
		return "redirect:notice.htm";
	}
	
	//글 수정 화면 (GET)
	@RequestMapping(value="noticeEdit.htm", method = RequestMethod.GET)
	public String noticeEdit(String seq, Model model) {
		//noticedao.getNotice(seq) 활용
		
		System.out.println("글번호뭔데? " + seq);
		
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("notice", notice);
		
		return "noticeEdit.jsp";
	}
	
	//글 수정 처리 (POST)
	@RequestMapping(value="noticeEdit.htm", method = RequestMethod.POST)
	public String noticeEdit(Notice n, HttpServletRequest request) {
		//파일 업로드 가능하게 하세용
		String filename = n.getFile().getOriginalFilename();
		String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로
		String fpath = path + "\\" + filename;
		System.out.println(fpath);
		
		FileOutputStream fs = null;
		try {
			fs = new FileOutputStream(fpath);
			fs.write(n.getFile().getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//파일명 추출 (DTO)
		n.setFileSrc(filename);
		
		try {
			noticedao.update(n); //DB update
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//다시 상세페이지로 redirect
		return "redirect:noticeDetail.htm?seq="+n.getSeq();
	}
}
