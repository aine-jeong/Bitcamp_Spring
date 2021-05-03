package DI_Annotation_01;

import org.springframework.beans.factory.annotation.Autowired;

public class MonitorViewer {
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	//setter를 통해서 Recorder 객체의 주소를 자동으로 주입받겠다
	//By Type이 컨테이너 안에 존재한다면 ... (존재하면 injection)
	//@Autowired(required = true) : 무조건 injection하겠다 (default)
	//@Autowired(required = false) : 컨테이너 안에 원하는 타입이 없으면 하지마~
	@Autowired(required = true)
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
}
