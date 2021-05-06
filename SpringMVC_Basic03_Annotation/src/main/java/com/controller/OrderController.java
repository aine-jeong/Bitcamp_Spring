package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.OrderCommand;

/*
 하나의 요청 주소로 2개의 업무를 처리하겠다
 /order/order.do
 1. GET  -> 화면 보여주기
 2. POST -> 처리해주기
 
 */

@Controller
@RequestMapping("/order/order.do") //메소드 단위에서 클래스 단위로 올려주기
public class OrderController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String form() {
		return "order/OrderForm"; 
		//view의 주소 리턴 > resolver가 조합해서 실 주소를 만든다
		//실주소 : /WEB-INF/views/order/OrderForm.jsp
		//실주소 만들어서 클라이언트에게 뿌리기~~
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String submit(OrderCommand ordercommand) {
		
	/*
		java코드로 작성해본다면?
		
		주문 발생
		OrderCommand ordercommand = new orderCommand();
				ㄴ> private List<OrderItem> orderItem;
		
		List<OrderItem> itemlist = new ArrayList<>();
		list.add(new OrderItem(1,10,"파손주의");
		list.add(new OrderItem(10, 1, "리모콘은 별도구매");
		list.add(new OrderItem(11, 1, "리모콘은 별도구매");
		
		command.setOrderItem(itemlist);
		
		위 작업을 자동화하기
		Parameter를 (OrderCommand ordercommand)와 같이 쓰고
		OrderFrom.jsp와 같이 세팅해서 넘겨주면
		위의 작업들이 자동으로 만들어진다.
		
		DTO 타입을 명시해주면
		ordercommand가 자동으로 생성, list객체 생성, add, setter로 주입 까지 다 자동!
		
		key값은 DTO 객체의 앞자리만 소문자로 바꿔서 자동으로 들어간다 (orderCommand)
		(따로 지정해주고 싶다면 어노테이션 사용 @ModelAttribute)
	*/
		
		return "order/OrderCommited";
	}
	
}
