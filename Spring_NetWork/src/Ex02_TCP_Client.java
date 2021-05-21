import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

//client

//server IP : 192.168.0.12
//port : 9999
public class Ex02_TCP_Client {
	public static void main(String[] args) throws Exception, IOException {
		Socket socket = new Socket("192.168.0.9", 9999);
		System.out.println("서버와 연결 되었습니다");
		
		
		//서버에서 보낸 메시지 받기
		InputStream in = socket.getInputStream();
		DataInputStream dis = new  DataInputStream(in);
		
		String servermsg = dis.readUTF();
		System.out.println("서버에서 보낸 메시지 :" + servermsg);
		
		dis.close();
		in.close();
		socket.close();
		
		//서버는 소켓을 만든다
		//클라이언트는 서버의 아이피와 포트번호를 통해 접속 시도
		//접속되면 새로운 소켓을 만들어서 연결 통로를 제공한다
		//연결통로는 인풋, 아웃풋 두가지 길을 가지고있음

	}

}
