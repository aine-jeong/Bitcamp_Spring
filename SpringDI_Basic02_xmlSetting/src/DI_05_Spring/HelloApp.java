package DI_05_Spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		
		/*
		//java코드
		MyBean mybean = new MyBean();
		MyBean mybean2 = new MyBean("hong");
		MyBean mybean3 = new MyBean();
		System.out.println("mybean: " + mybean);
		//mybean: DI_05_Spring.MyBean@7637f22
		System.out.println("mybean2: " + mybean2);
		//mybean2: DI_05_Spring.MyBean@4926097b
		System.out.println("mybean3: " + mybean3);
		//mybean3: DI_05_Spring.MyBean@762efe5d
		
		Singleton single = Singleton.getInstance();
		Singleton single2 = Singleton.getInstance();
		System.out.println("single: " + single);
		//single: DI_05_Spring.Singleton@52feb982
		System.out.println("single2: " + single2);
		//single2: DI_05_Spring.Singleton@52feb982
		//주소가 같다
		*/
		
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:DI_05_Spring/DI_05.xml");
		//스프링 컨테이너(메모리)가 구성되고 xml파일을 read(파싱)해서 객체 생성, 조립, 소멸 담당
		//컨테이너 안에서 필요한 객체를 얻어서 사용
		
		MyBean mybean = context.getBean("mybean", MyBean.class);
		MyBean mybean2 = context.getBean("mybean", MyBean.class);
		MyBean mybean3 = context.getBean("mybean", MyBean.class);
		//mybean, mybean2, mybean3의 변수가 가지는 주소값은 같다
		//getBean할 때 마다 new를 하는 것이 아님
		System.out.println("주소값: " + mybean + ":" + mybean2 + ":" + mybean3);
		//주소값: DI_05_Spring.MyBean@69e1dd28:DI_05_Spring.MyBean@69e1dd28:DI_05_Spring.MyBean@69e1dd28
		
		//Default
		//+ bean이 만들어졌을 때 Default 생성자로 만들어졌다
		
		//getBean()
		//1. return type Object (타입에 맞는 casting)
		//2. 호출시 새로운 객체를 만드는 것이 아니다. (new x)
		//** 스프링 컨테이너 안에 객체들의 타입은 Default로 Singleton
		//** 예외적으로 getBean()이 객체를 생성하도록 할 수도 있음 but 거의 사용되지 않음
		
		MyBean mybean4 = context.getBean("mybean2", MyBean.class);
		System.out.println("생성자 사용: " + mybean4);
		
		Singleton single = context.getBean("single",Singleton.class);
		Singleton single2 = context.getBean("single",Singleton.class);
		
		System.out.println("주소값: " + single + " / " + single2);
		
	}
}
