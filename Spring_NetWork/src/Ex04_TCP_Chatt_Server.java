import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//Echo : 순차적인 데이터 처리 (Ex01 ~ Ex03)

//Server : read , write
//Client : read , write
//동시에 (Thread) ....
//read (Thread) >> stack , write(Thread) >> stack

//1:1 채팅은 입력과 출력이 동시에 ..
public class Ex04_TCP_Chatt_Server {

	public static void main(String[] args){
		ServerSocket serversocket = null;
		
		try {
			serversocket = new ServerSocket(9999);
			System.out.println("접속 대기중 ...");
			Socket socket = serversocket.accept(); //클라이언트가 접속하면 ....
			System.out.println("연결완료");
			
			
			//ServerSend 객체나
			//ServerReceive 객체나 socket 객체의 주소가 필요 
			//WHY : socket을 통해서 read , write 
			
			//ServerSend send = new  ServerSend(socket);
			//send.start();
			new ServerSend(socket).start();
			
			//ServerReceive receive = new ServerReceive(socket);
			//receive.start();
			new ServerReceive(socket).start();
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				serversocket.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}

}

//고민 :socket (read , write 객체)
//Write : socket > outputStream
class ServerSend extends Thread{
	//cmd 창에서 입력한 값을 read 해서 
	//OutputStream 사용해서 write >> 연결된 socket을 통해서
	
	Socket socket;
	public ServerSend(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() { //다른 stack main 함수와 같은역활
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			/*
				Scanner sc = new Scanner(System.in);
				String msg = sc.nextLine();
				OutputStream out = socket.getOutputStream();
				DataOutputStream dos = new  DataOutputStream(out);
				dos.writeUTF(msg);
			*/
			//Buffer >> Line() read
			//InputStreamReader sc = new InputStreamReader(System.in);
			
			
			br = new BufferedReader(new InputStreamReader(System.in));
			//현재 연결된 통로에서 write하겠다.
			pw = new PrintWriter(socket.getOutputStream(),true);
			
			while(true) {
				String data = br.readLine(); //sc.nextLine()
				if(data.equals("exit"))break;
				pw.println(data); //접속한 클라이언트 메시지 보내기  dos.writeUTF(msg);
			}
			System.out.println("server send 작업 종료");
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				pw.close();
				br.close();
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
}


//Read : socket > inputStream
class ServerReceive extends Thread{
	//연결된 socket 을 통해서 read 해서
	//cmd 그 결과를 출력
	Socket socket;
	public ServerReceive(Socket socket) {
		this.socket = socket;
	}
	
	@Override
	public void run() {
		BufferedReader br = null;
		try {
			/*
				InputStream in = socket.getInputStream();
				DataInputStream dis = new  DataInputStream(in);
				String msg = dis.readUTF();
			*/
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String data = null;
			while((data = br.readLine()) != null) {
				System.out.println("Client 받은 메시지 [ " + data + " ]");
			}
			System.out.println("ServerReceive 종료");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				br.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
		
		
	}
	
}







