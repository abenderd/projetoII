package cliente.conection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConexao {
	private Socket connection;
	
	public ClientConexao(String ip) throws UnknownHostException, IOException{
		this.connection = new Socket(ip, 11111);
		Thread t = new Thread(new Recebe());
        t.start();
	}

	public void Envia(String menssagem){
		try {
			ObjectOutputStream transmissor = new ObjectOutputStream(connection.getOutputStream());
			transmissor.writeObject(menssagem);
			transmissor.flush();
			//transmissor.close();
		}
		catch(Exception erro){
			System.err.println(erro.getMessage());
		}
	}
	
	private class Recebe implements Runnable {
        @Override
        public void run() {
        	try{
    			ObjectInputStream server = new ObjectInputStream(connection.getInputStream());
    			String mensagem = null;
    			do{
    				mensagem = String.valueOf(server.readObject());
    				System.out.println(mensagem);
    			}while(true);
    			
    			//server.close();
    		}
    		catch(Exception erro) {
    			System.err.println(erro.getMessage());
    		}
        }
    }
}
