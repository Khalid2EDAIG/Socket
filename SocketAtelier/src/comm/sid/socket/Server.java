package comm.sid.socket;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try{
			ServerSocket ss=new ServerSocket(1234);
			System.out.println("Read and expected connexion of client");
			Socket s=ss.accept();
			InputStream is=s.getInputStream();
			OutputStream os=s.getOutputStream();
			System.out.println("expected number");
			int nb=is.read();
			int resp=nb*10;
			System.out.println("Send respense for client ");
			os.write(resp);
			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
