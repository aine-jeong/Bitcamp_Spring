package AOP_Basic_02_JAVA;

import java.lang.reflect.Proxy;

public class Program {

	public static void main(String[] args) {
		//1. 실객체의 주소
		Calc calc = new NewCalc();
		
		//2. 가짜(Proxy)를 생성
		Calc cal = (Calc)Proxy.newProxyInstance(calc.getClass().getClassLoader(), //실 객체의 메타 정보(내부 정보)
								calc.getClass().getInterfaces(), //행위 정보(Interface의 정보)
								new LogPrintHandler(calc)); //보조객체의 정보
		
		int result = cal.ADD(555, 555);
		System.out.println("ADD result: " + result);
		
		result = cal.MUL(465, 166);
		System.out.println("MUL result: " + result);
		
		result = cal.SUB(1000, 166);
		System.out.println("SUB result: " + result);
	}

}
