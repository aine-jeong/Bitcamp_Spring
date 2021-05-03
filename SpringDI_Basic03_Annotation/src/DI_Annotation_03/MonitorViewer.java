package DI_Annotation_03;

import javax.annotation.Resource;

public class MonitorViewer {
	private Recorder recorder;

	public Recorder getRecorder() {
		return recorder;
	}
	
	//@Autowired (같은 Type)
	@Resource(name="zz") // 같은 Name > 같은 타입의 객체가 여러개 있더라도 name 값으로 찾는다
	public void setRecorder(Recorder recorder) {
		this.recorder = recorder;
	}
	
	
}
