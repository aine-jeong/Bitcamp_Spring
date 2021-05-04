package spring;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class Client implements InitializingBean , DisposableBean {
    
	public Client() {
		System.out.println("Client Default");
	}
	
	private String defaulthost;
	public Client(String defaulthost){
		this.defaulthost = defaulthost;
		System.out.println("Client Overloading :" + this.defaulthost);
	}
	
	private String host;
	public String getHost() {
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
		System.out.println("Client setHost() 호출 : " + this.host);
	}

	public void send(){
		System.out.println("데이터 보내기....");
	}
	
	
	//초기화
	//InitializingBean 인터페이스의 메소드로, BeanFactory에 의해 모든 property가 설정되고 난 뒤 실행되는 메소드
	//주로 실행시점의 custom 초기화 로직이 필요하거나, 주입받은 property를 확인하는 용도로 사용
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("Client 초기화 함수 호출");		
	}

	//소멸 (객체가 소멸될때 호출되는 함수)
	@Override
	public void destroy() throws Exception {
		System.out.println("Client 소멸자 함수 호출");
		
	}

}
