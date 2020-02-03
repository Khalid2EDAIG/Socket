package comm.sid.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMT extends Thread{
	private int cptClients=0;
	@Override
	public void run() {
		
		try {
			//Creer un ServerSocket
			ServerSocket ss=new ServerSocket(7000);
			//Boucle infinie pour garder le server toujour demarrer et accepter n'importe quel connexion des clients
			while(true) {
				Socket socket=ss.accept();
				++cptClients;
				new Conversation(socket,cptClients).start();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public class Conversation extends Thread {
		
		private Socket socket;
		private int clientId;
		private Scanner clavier;
		
		public Conversation(Socket s,int id) {
			super();
			this.socket=s;
			this.clientId=id;
			this.clavier=new Scanner(System.in);
		}
		@Override
		public void run() {
			//Code de conversation :
			
			try {
				//Pour Lire
				InputStream is=socket.getInputStream();
				//Pour lire des caracteres 
				InputStreamReader isr=new InputStreamReader(is);
				//Pour lire une chaine de carateres
				BufferedReader br=new BufferedReader(isr);
				//Mpour Envoyer 
				OutputStream os=socket.getOutputStream();
				//écriture os sous forme de texte
				PrintWriter pw= new PrintWriter(os,true);
			    //
				String IP=socket.getRemoteSocketAddress().toString();
				System.out.println("Connexion de client id :"+clientId+"  IP : "+IP);
				pw.println("Bienvenue , vous etre le client votre id est : "+clientId);
				
				while(true) {
					String request=br.readLine();
					System.out.println("ClientID : "+clientId+" a envoyé la : "+request );
					if(!request.isEmpty()) {
						System.out.println("Entrer la response pour le client de id = : "+clientId);
						String response=clavier.nextLine();
						//String resp="Size est : "+request.length();
						pw.println(response);
						response="";
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		//Creer un objet de type ServerMT pour demarrer le Server
		new ServerMT().start();;

	}

}
