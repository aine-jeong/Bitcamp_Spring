package DI_Annotation_04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//ConfigContext 파일은 DI_Config.xml과 동일한 효과를 갖는다 (어노테이션 Configuration 붙여서!)

//xml 없이 java코드를 이용해서 설정 작업하기

@Configuration
public class ConfigContext { //객체를 생성, 주입하는 역할

	// xml : <bean id="user" class="DI_Annotation_04.User"></bean>
	// 위의 xml을 java에서 하려면?
	// 함수를 통해서 객체를 리턴하도록 한다
	@Bean
	public User user() {
		return new User();
	}
	
	// xml : <bean id="user2" class="DI_Annotation_04.User2"></bean>
	@Bean
	public User2 user2() {
		return new User2();
	}
}
