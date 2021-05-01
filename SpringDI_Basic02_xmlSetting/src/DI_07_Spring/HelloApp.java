package DI_07_Spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class HelloApp {

	public static void main(String[] args) {
		/*
		ProtocolHandler handler = new ProtocolHandler();
		
		List<MyFilter> list = new ArrayList<MyFilter>();
		list.add(new EncFilter()); //다형성
		list.add(new HeaderFilter());
		list.add(new ZipFilter());
		
		
		handler.setFilters(list); //객체 주입(Injection)
		System.out.println(handler.filter_length());
		*/
		
		ApplicationContext context = 
				new GenericXmlApplicationContext("classpath:DI_07_Spring/DI_07.xml");
		
		ProtocolHandler handler = context.getBean("protocolHandler", ProtocolHandler.class);
		
		System.out.println(handler.filter_length());
		
	}
}
