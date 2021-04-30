package DI;

public class NewRecordView {
	//점수 출력하는 클래스
	
	//복합연관 (두개의 생명주기가 같다 / has a)
	private NewRecord record;
	public NewRecordView(int kor, int eng, int math) {
		record = new NewRecord(kor, eng, math);
	}

	public void print() {
		System.out.println(record.total() + "/" + record.avg());
	}
}
