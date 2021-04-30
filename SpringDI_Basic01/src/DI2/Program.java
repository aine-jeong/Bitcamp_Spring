package DI2;

public class Program {
	public static void main(String[] args) {
		
		NewRecordView view = new NewRecordView();
		
		//놀다가 NewRecord 객체가 필요하다면
		NewRecord record = new NewRecord(100, 50, 50);
		view.setRecord(record); //Setter를 통해서 주입(의존성)
		
		view.print();
	}
}
