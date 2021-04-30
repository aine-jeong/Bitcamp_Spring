package DI_04_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		//java 코드
		//MessageBeanImpI messagebean = new MessageBeanImpI("hong");
		//messagebean.setGreeting("hello");
		//messagebean.sayHello();
		
		//Spring코드
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:DI_04_Spring/DI_04.xml");
		
		MessageBean messagebean = context.getBean("m3", MessageBean.class);
		messagebean.sayHello();
	}

}
