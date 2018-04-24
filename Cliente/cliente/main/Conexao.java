package cliente.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class Conexao {
	private Socket cliente;
	private BufferedReader reader;
    private PrintWriter writer;
    private String recebe = null;
    
	
	public Conexao(){
		
		try {
            cliente = new Socket("127.0.0.1", 5000);
            InputStreamReader streamReader = new InputStreamReader(cliente.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(cliente.getOutputStream());
            System.out.println("Conectado");
        } catch (IOException e) {
        	System.out.println("Nao foi possivel conectar");
        }
		
		Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();
		
	}
	
	public void Envia(String var1, String var2, String var3, String var4){
		
		String x = (var1+"/"+var2+"/"+var3+"/"+var4);	
		try {
			System.out.println(x);
            writer.println(x);
            writer.flush();
        } catch (Exception ex) {
        	System.err.println("Sistema nao conseguiu enviar a informação");
        }
	}
	
	public String[] Recebe(String var2) throws ClassNotFoundException{
		String[] menssagem = null;
		String delimiter = "/";
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			System.out.println("ERRO NO COMANDO WAIT");
		}
		while(menssagem == null){
			menssagem = recebe.split(delimiter);
			if((menssagem[0]).equals(var2)){
				menssagem = null;
				System.out.println("Menssagem desconsiderada");
			}
		}
		
		return menssagem;
	}
	
	private class IncomingReader implements Runnable {
        @Override
        public void run() {
            String message;

            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println("read " + message);
                    recebe = message;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	@Override
	public String toString() {
		return "Conexao [cliente=" + cliente + ", reader=" + reader + ", writer=" + writer + ", recebe=" + recebe + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		result = prime * result + ((recebe == null) ? 0 : recebe.hashCode());
		result = prime * result + ((writer == null) ? 0 : writer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Conexao))
			return false;
		Conexao other = (Conexao) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (reader == null) {
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		if (recebe == null) {
			if (other.recebe != null)
				return false;
		} else if (!recebe.equals(other.recebe))
			return false;
		if (writer == null) {
			if (other.writer != null)
				return false;
		} else if (!writer.equals(other.writer))
			return false;
		return true;
	}
	
}
