package ncontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import service.JoinService;

@RestController
public class IDcheckController {
	 
	@Autowired
	private JoinService joinservice;
	
	@RequestMapping(value="checkUserIdExist.htm", method=RequestMethod.GET)
	public String checkUserIdExist(String user_id) {
		System.out.println("여기오니 ?" + user_id);
		
		int chk = joinservice.idCheck(user_id);
		
		if(chk > 0) {
			return "true";
		}
		return "false";
	}
}
