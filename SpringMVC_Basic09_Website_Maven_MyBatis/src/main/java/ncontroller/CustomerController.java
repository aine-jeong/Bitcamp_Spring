package ncontroller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String notices(@RequestParam(value="pg" , defaultValue = "1") String pg , 
						@RequestParam(value="f" , defaultValue = "TITLE") String f , 
						@RequestParam(value="q" , defaultValue = "%%") String q, Model model) {
		
		/*
		//default 값 설정
		int page = 1;
		String field="TITLE";
		String query = "%%";
		if(pg != null   && ! pg.equals("")) { page  = Integer.parseInt(pg); }
		if(f != null   && ! f.equals("")) { field = f; }
		if(q != null   && ! q.equals("")) { query = q; }
		*/
		
		
		//DAO 작업
		List<Notice> list = null;
		try {
			list = noticedao.getNotices(Integer.parseInt(pg), f, q);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("list", list); // 자동으로 notice.jsp forward
		//<c:forEach items="${list}" var="n"> 사용 가능
		
		//모델 객체의 주소를 준다. 거기다가 addAttribute해두면, 리턴해주는 주소에 자동으로 forward시켜준다.
		return "customer/notice";
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("notice", notice);
		return "customer/noticeDetail";
	}
	
	
	//글쓰기 화면 (GET)
	@RequestMapping(value="noticeReg.htm", method = RequestMethod.GET)
	public String noticeReg() {
		//http://localhost:8090/SpringMVC_Basic04_WebSite_Annotation/customer
		return "customer/noticeReg";
	}
	
	//글쓰기 처리(POST)
	@RequestMapping(value="noticeReg.htm", method = RequestMethod.POST)
	public String noticeReg(Notice n ,HttpServletRequest request) {
		//Notice >> DTO
		// private List<CommonsMultipartFile'> files
		
		//files[0] >> 1.jpg
		//files[1] >> 2.jpg
		
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames =new ArrayList<String>(); //파일명 관리
		
		if(files != null && files.size() > 0) { //한개라도 업로드된 파일이 존재한다면
			for(CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로
				String fpath = path + "\\" + filename;
				System.out.println(fpath);
				
				if(!filename.equals("")) { //웹서버에 실 파일 업로드 과정
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(multifile.getBytes());
						
						filenames.add(filename); //DB에 입력될 파일명
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							fs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		//파일 업로드 2개 한다는 전제
		//파일명 추출 (DTO)
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));
		
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
		Notice notice = null;
		try {
			notice = noticedao.getNotice(seq);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("notice", notice);
		
		return "customer/noticeEdit";
	}
	
	//글 수정 처리 (POST)
	@RequestMapping(value="noticeEdit.htm", method = RequestMethod.POST)
	public String noticeEdit(Notice n, HttpServletRequest request) {
		List<CommonsMultipartFile> files = n.getFiles();
		List<String> filenames =new ArrayList<String>(); //파일명 관리
		
		if(files != null && files.size() > 0) { //한개라도 업로드된 파일이 존재한다면
			for(CommonsMultipartFile multifile : files) {
				String filename = multifile.getOriginalFilename();
				String path = request.getServletContext().getRealPath("/customer/upload"); //배포된 서버 경로
				String fpath = path + "\\" + filename;
				System.out.println(fpath);
				
				if(!filename.equals("")) { //웹서버에 실 파일 업로드 과정
					FileOutputStream fs = null;
					try {
						fs = new FileOutputStream(fpath);
						fs.write(multifile.getBytes());
						
						filenames.add(filename); //DB에 입력될 파일명
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try {
							fs.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		//파일명 추출 (DTO)
		n.setFileSrc(filenames.get(0));
		n.setFileSrc2(filenames.get(1));
		
		try {
			noticedao.update(n); //DB insert
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//다시 상세페이지로 redirect
		return "redirect:noticeDetail.htm?seq="+n.getSeq();
	}
	
	@RequestMapping("noticeDel.htm") //customer/noticeDel.htm
	public String noticeDel(String seq) {
		try {
			noticedao.delete(seq);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "redirect:notice.htm";
	}
	
}
