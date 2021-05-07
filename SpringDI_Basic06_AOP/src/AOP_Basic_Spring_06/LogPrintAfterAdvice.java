package AOP_Basic_Spring_06;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.AfterReturningAdvice;

public class LogPrintAfterAdvice implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnvalue, Method method, Object[] args, Object target) throws Throwable {
		//returnvalue : 주함수가 실행된 뒤의 값을 받아온다
		
		Log log = LogFactory.getLog(this.getClass());
		log.info("[After Advice start]");
		log.info("[보조업무: 주함수가 실행되고, 그 다음 실행되는 보조 공통 함수]");
		log.info("[return value] " + returnvalue); // MUL함수의 결과값 (5000)
		log.info("[method] " + method.getName()); // MUL
		log.info("[args] " + Arrays.toString(args)); // 100, 50
		log.info("[target] " + target.toString()); // AOP_Basic_Spring_05.NewCalc@1ed4ae0f
		
	}
	
}
