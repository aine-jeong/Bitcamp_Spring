package DI_04_Spring;

public class MessageBeanImpI implements MessageBean {
	private String name;
	private String greeting;
	
	public MessageBeanImpI(String name) {
		this.name = name;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	
	@Override
	public void sayHello() {
		System.out.println(this.name + "," + this.greeting);
	}

}
