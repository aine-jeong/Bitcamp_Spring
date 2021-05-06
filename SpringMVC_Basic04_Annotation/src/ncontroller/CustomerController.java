package ncontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.NoticeDao;
import vo.Notice;

@Controller
public class CustomerController {

	private NoticeDao noticedao;
	
	@Autowired
	public void setNoticeDao(NoticeDao noticedao) {
		this.noticedao = noticedao;
	}
	
	//public List<Notice> getNotices(int page, String field, String query)
	@RequestMapping("/customer/notice.htm")
	public ModelAndView notices(String pg, String f, String q) {
		return null;
	}
	
	//public Notice getNotice(String seq)
	@RequestMapping("/customer/noticeDetail.htm")
	public ModelAndView  noticeDetail(String seq) {
		return null;
	}
}
