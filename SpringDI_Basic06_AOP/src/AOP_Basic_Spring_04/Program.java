package AOP_Basic_Spring_04;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Program {

	public static void main(String[] args) {
		//주업무와 보조업무를 분리
		//보조업무를 대체할 가짜 -> Proxy(보조업무를 심어준다)
		//Proxy를 통해서 진짜를 호출하는 것처럼 보이도록 한다
		//장점: 주업무와 보조업무를 하는 개발자가 서로의 일에 대해 몰라도 된다
		//		-> 유지보수 good
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:AOP_Basic_Spring_04/ApplicationContext.xml");
	
		Calc calc = context.getBean("proxy", Calc.class);
		int result = calc.MUL(100, 50);
		System.out.println("AOP 결과: " + result);
	}
}
