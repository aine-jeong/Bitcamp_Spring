package DI_10_Spring;

import java.util.Properties;

public class BookClient {
	
	//Properties: 특수한 HashMap (키, 밸류가 String)
	private Properties config;
	public void setConfig(Properties config) {
		this.config = config;
	}
	
	//일반함수 
	public void connect() {
		//getProperty: 키를 넣으면 밸류값을 얻을 수 있음
		String server = config.getProperty("server");
		String timeout = config.getProperty("connectiontimeout");
		
		System.out.println("server : " + server);
		System.out.println("timeout : " + timeout);
	}
	
}
