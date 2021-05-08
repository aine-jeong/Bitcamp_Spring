package com.aopEx2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;




public class Program {
	public static void main(String[] args) {
		
		ApplicationContext context = new GenericXmlApplicationContext("classpath:ApplicationContext.xml");
		
		Calc calc = context.getBean("calc",Calc.class);
		
		int result = calc.ADD(555, 555);
		
		System.out.println("");
		System.out.println("----------------------------------------");
		System.out.println("");
		
		result = calc.MUL(555, 555);
		
		System.out.println("");
		System.out.println("----------------------------------------");
		System.out.println("");
		
		result = calc.SUB(555, 555);
	}
}
