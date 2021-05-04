package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import Spring.Client;

//xml > java 파일 > prototype 설정

//xml없이 컨테이너에 빈 객체를 만들고, 주입할 수 있는 자원
@Configuration
public class JavaConfigPrototype {
	
//scope="prototype" : getBean할 때 마다 객체를 새롭게 만든다
	
/*	
  <bean id="client" class="Spring.Client" scope="prototype">
	<property name="host" value="webserver"></property>
  </bean>
*/
	
	//xml에 scope="prototype" 하는 것과 같은 효과를 java로 작성하고싶다면?
	//@Scope("prototype") 안붙이면 그냥 Singleton이얌~~
	@Bean
	@Scope("prototype")
	public Client client(){
		Client client = new Client();
		client.setHost("webserver");
		return client;
	}

}


