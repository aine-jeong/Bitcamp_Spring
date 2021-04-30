package DI3;

public class Program {
	public static void main(String[] args) {
		
		NewRecordView view = new NewRecordView();
		NewRecord record = new NewRecord();
		
		view.setRecord(record); //setter주입 (다형성)
		view.input();
		view.print();
	}
}