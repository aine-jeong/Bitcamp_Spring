package DI_03_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	
	public static void main(String[] args) {
		//영문
		//MessageBean_en messagebean_en = new MessageBean_en();
		//messagebean_en.sayHello("hong");
		
		//한글
		//MessageBean_kr messagebean_kr = new MessageBean_kr();
		//messagebean_kr.sayHello("hong");
		
		//spring 컨테이너 생성 > 생성된 컨테이너 객체 생성 조립(xml)
		//컨테이너 생성방법: 다양한 방법
		//ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:DI_03_Spring/DI_03.xml");
		//Generic > 형변환에 대한 이점
		//두번쨰 파라미터로 메타정보를 제공 (.class (클래스의 내부 정보 입력))
		//두번째 파라미터를 입력함으로써 형변환(타입캐스팅) 안해줘도 된다
		MessageBean message = context.getBean("messagebean", MessageBean.class);
		message.sayHello("hong");
	}
}
