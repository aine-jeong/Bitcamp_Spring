package DI2;

public class NewRecordView {
	//점수 출력하는 클래스
	
	private NewRecord record; //member field (setter)
	
	public void setRecord(NewRecord record) { //record의 setter함수
		this.record = record;
	}
	
	//나는 니가 필요해
	//1. [생성자]를 통해서 필요한 객체를 생성 또는 주입 >> DI >> 복합, 집합
	//2. 함수(setter)를 통해서 필요한 객체를 주입 >> DI2 >> 집합 >> 필요에 따라서
	

	public void print() {
		System.out.println(record.total() + "/" + record.avg());
	}
}
