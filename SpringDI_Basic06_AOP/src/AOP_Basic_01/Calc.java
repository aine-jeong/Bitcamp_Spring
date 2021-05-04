package AOP_Basic_01;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StopWatch;

public class Calc {
/*
 	간단한 사칙연산 계산기 프로그램
 	> 주관심(주업무): 사칙연산 (ADD, MUL..) -> 기능(함수)구현
 	> 보조관심(공통관심): 연산에 걸린 시간을 log로 출력(console에 red색상으로)
 	
 	아래와 같은 함수가 100여가지라고 가정 > 유지보수 할 일이 생겼다 ...... > 100개 함수 수정해야 한다.
 */

	public int Add(int x, int y) {
		
		//보조업무
		//현재 동작하는 함수의 정보를 가진 뒤, log라는 인터페이스 타입의 객체 반환
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머시작]");
		
		//주업무
		int result = x + y;
		
		//보조업무
		sw.stop();
		log.info("[타이머종료]");
		log.info("[Time log Method: Add]");
		log.info("[Time log Method:" + sw.getTotalTimeMillis() + "]");
		
		return result;
	}
	
	public int Mul(int x, int y) {
		//보조업무
		//현재 동작하는 함수의 정보를 가진 뒤, log라는 인터페이스 타입의 객체 반환
		Log log = LogFactory.getLog(this.getClass());
		StopWatch sw = new StopWatch();
		sw.start();
		log.info("[타이머시작]");
				
		//주업무
		int result = x * y;
				
		//보조업무
		sw.stop();
		log.info("[타이머종료]");
		log.info("[Time log Method: Mul]");
		log.info("[Time log Method:" + sw.getTotalTimeMillis() + "]");
				
		return result;
	}
}
