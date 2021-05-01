package DI_09_Spring;

import java.util.Map;

public class ProtocolHandlerFactory {
	public ProtocolHandlerFactory() {
		System.out.println("ProtocolHandlerFactory 기본생성자");
	}
	
	//Map(key, value)
	public Map<String, ProtocolHandler> handlers;
	
	public void setHandlers(Map<String, ProtocolHandler> handelers) {
		this.handlers = handelers;
		System.out.println("setter 주입성공: " + this.handlers);
	}
}
