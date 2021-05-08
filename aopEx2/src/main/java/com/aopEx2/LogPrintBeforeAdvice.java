package com.aopEx2;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class LogPrintBeforeAdvice{
	
	// 아무런 기능도 하지 않는 메서드를 선언해 PointCut으로 쓰기 위해 표현식 지정
	@Pointcut("execution(public int ADD(..))")
	public void PointCut() {}
	
	// 메서드 실행의 전과 후 모두 작업할 수 있는 공통 로직
	@Around("within(com.aopEx2.*)")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable{
		
		//공통 기능이 적용되는 메서드가 어떤 메서드인지 출력하기 위해 메서드명을 얻어옴
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + "시작"); //메서드 실행
		
		//공통기능
		System.out.println("핵심 기능 전에 실행 할 공통 기능입니다. "+System.currentTimeMillis());
		Object obj = null;
		try {
			
			obj = joinpoint.proceed(); //핵심 기능 실행 ( Advice를 적용 가능한 지점 == 메서드 호출, 필드값 변경 )
			System.out.println("=== 결과값: " + obj + " ===");
			
		}catch (Exception e) {
			
		}
		//공통기능
		System.out.println("핵심 기능 후에 실행 할 공통 기능입니다. "+System.currentTimeMillis());
		
		System.out.println(signatureStr + "끝");
		return obj;
	}
	
	// 표현식에 해당하는 클래스의 메서드가 실행되기 전에 먼저 실행하기 위한 Before
	@Before("PointCut()")
	public void before(JoinPoint j){
		System.out.println("");
		System.out.println("====== " + j.getSignature().getName() + " 함수실행전 Before 함수 실행 =====");
		System.out.println("");
	}
	

	// 표현식에 해당하는 클래스의 메서드가 종료된 후 실행하기 위한 After 
	@After("PointCut()")
	public void after(JoinPoint j) {
		System.out.println("");
		System.out.println("====== " + j.getSignature().getName() + " 함수실행후 After 함수 실행 =====");
		System.out.println("");
	}
}
