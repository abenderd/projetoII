package server.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Teste {
	public static void main(String[] args) {
		try {
			Socket connection = new Socket("127.0.0.1", 11111);
			
			ObjectOutputStream transmissor = new ObjectOutputStream(connection.getOutputStream());
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String mensagem = null;
			do{
				mensagem = reader.readLine();
				transmissor.writeObject(mensagem);
				transmissor.flush();
			}
			while(true);
			
			//transmissor.close();
			//connection.close();
		}
		catch(Exception erro){
			System.err.println(erro.getMessage());
		}
	}
}


