package AOP_Basic_Spring_03;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;


//보조업무 구현
public class LogPrintAroundAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		System.out.println("Around Advice invoke Start");
		System.out.println("method: " + method);
		
		//보조업무
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머시작]");
		
		//주업무 (실객체의 함수 호출)
		Object result = method.proceed(); //실객체의 실함수를 호출한 결과를 받는다
		
		//보조업무
		sw.stop();
		log.info("[타이머종료]");
		log.info("[Time log Method"+ method +"]");
		log.info("[Time log Method:" + sw.getTotalTimeMillis() + "]");
		
		return result;
	}


}
