package AOP_Basic_02_JAVA;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

//보조업무(공통관심)를 처리하는 클래스
//실 함수를 대신해서 처리할 수 있는 기능(대리함수) : invoke
//invoke 함수는 여러개의 함수를 대리하는 방법이다.
public class LogPrintHandler implements InvocationHandler {
	
	private Object target; //실객체의 주소값
	
	public LogPrintHandler(Object target) {
		System.out.println("logPrintHandler 생성자 호출");
		this.target = target;
	}
	
	
	//invoke 함수: 대리함수 >> 'Add, Mul, Sub'를 대신해서 동작한다
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("invoke함수가 호출되었습니다.");
		System.out.println("method함수명: " + method);
		System.out.println("method parameter: " + Arrays.toString(args));
		
		//보조업무
		//현재 동작하는 함수의 정보를 가진 뒤, log라는 인터페이스 타입의 객체 반환
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머시작]");
		
		//주업무 실행(실객체의 실함수 호출): 주객체의 주함수(ADD, MUL, SUB)를 호출
		int result = (int)method.invoke(this.target, args);
		
		//보조업무
		sw.stop();
		log.info("[타이머종료]");
		log.info("[Time log Method: Mul]");
		log.info("[Time log Method:" + sw.getTotalTimeMillis() + "]");
		
		return result;
	}

}
