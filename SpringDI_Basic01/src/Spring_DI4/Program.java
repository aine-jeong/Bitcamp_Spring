package Spring_DI4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Program {
	public static void main(String[] args) {
		/*
		1. SpringFramework가 제공하는 컨테이너 안에 객체가 생성된다. (메모리 공간: IOC 컨테이너)
		2. 우리가 해야할 일은?
		 - 컨테이너를 만들고 그 메모리에 필요한 객체를 생성하고 조립(주입 (DI)), 소멸
		  
		NewRecordView view = new NewRecordView();
		NewRecord record = new NewRecord();
		
		view.setRecord(record); //setter주입 (다형성)
		
		*/
		
		//ClassPathXmlApplicationContext : 컨테이너를 만드는 코드
		//아래 코드: 저장 공간인 컨테이너를 만들고, 그 다음 xml파일을 read하기 시작한다.
		//		   컨테이너 안에 객체를 생성하고 주입하는 과정이 실행된다.
		ApplicationContext context = new ClassPathXmlApplicationContext("DIConfig.xml");
		
		//이제 컨테이너 안에서 필요한 객체만 골라서 놀면 된당
		//= 레고박스 안에 만들어진 블럭들이 있고, 그 중에 원하는 블럭을 가져와서 놀기
		RecordView view = (RecordView)context.getBean("view");
		
		view.input();
		view.print();
	}
}
