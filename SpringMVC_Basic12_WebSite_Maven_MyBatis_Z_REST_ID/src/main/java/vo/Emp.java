package vo;

import lombok.Data;

//@Data는 다가지고있는 애

//클래스에 선언한 애들한테 다 자동으로 만들어주겠다
//@Getter
//@Setter
//@AllArgsConstructor 모든 멤버필드를 parameter로 ,,,
//@ToString
//@NoArgsConstructor
//이렇게 각각 따로 쓸 수 있다

@Data
public class Emp {
	private int empno;
	private String ename;
}
