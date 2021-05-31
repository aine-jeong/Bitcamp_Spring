package kr.or.bit.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import kr.or.bit.dao.EmpDao;
import kr.or.bit.dto.Emp;

@Service
public class EmpService {
	private SqlSession sqlsession;
	
	@Autowired
	public void setSqlsession(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}
	
	public ModelAndView empList() {
		
		EmpDao empdao = sqlsession.getMapper(EmpDao.class);
		
		List<Emp> list = empdao.empList();
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("list", list);
		mv.setViewName("EmpList");
		
		return mv;
	}
}
