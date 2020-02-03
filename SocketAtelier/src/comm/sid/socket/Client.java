package comm.sid.socket;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		try {
			Socket s=new Socket("localhost",1234);
			InputStream is=s.getInputStream();
			OutputStream os=s.getOutputStream();
			
			Scanner clavier=new Scanner(System.in);
			System.out.print("Please enter number :");
			int nb=clavier.nextInt();
			os.write(nb);
			System.out.println("Result is : "+is.read());
			s.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
