package DI_01;

public class HelloApp {
	
	public static void main(String[] args) {
		MessageBean messagebean = new MessageBean();
		messagebean.sayHello("ain");
		
	}
}

/*
요구사항 (hong 입력시)
1. 한글 버전 : 안녕 hong
2. 영문 버전 : Hello hong

MessageBean_kr > 안녕 hong
MessageBean_en > Hello hong

>>인터페이스: MessageBean 인터페이스 설계(다형성)

*/