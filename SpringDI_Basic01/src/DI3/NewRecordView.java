package DI3;

import java.util.Scanner;

public class NewRecordView implements RecordView{
	//점수 출력하는 클래스
	
	private NewRecord record; //member field (setter)
	
	//장점: interface타입 (다형성)
	public void setRecord(Record record) { //record의 setter함수
		this.record = (NewRecord)record;
	}
	
	//나는 니가 필요해
	//1. [생성자]를 통해서 필요한 객체를 생성 또는 주입 >> DI >> 복합, 집합
	//2. 함수(setter)를 통해서 필요한 객체를 주입 >> DI2 >> 집합 >> 필요에 따라서
	
	@Override
	public void print() {
		System.out.println(record.total() + "/" + record.avg());
	}

	@Override
	public void input() {
		Scanner sc = new Scanner(System.in);
		System.out.print("kor: ");
		record.setKor(sc.nextInt());
		
		System.out.print("eng: ");
		record.setEng(sc.nextInt());
		
		System.out.print("math: ");
		record.setMath(sc.nextInt());
	}
}
