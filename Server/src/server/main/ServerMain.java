package server.main;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import server.conection.ServerConexao;

public class ServerMain {

	ArrayList<ServerConexao> clientOutputStreams;

	public static void main(String[] args) {
		// PEGA O IP DO SERVIDOR
		try {
			System.out.println(Inet4Address.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new ServerMain().go();
	}

	private void go() {
		// Guarda os clientes
		clientOutputStreams = new ArrayList();

		try {
			ServerSocket receptor = new ServerSocket(11111);
			System.out.println("Servidor up");

			// Mantem o servidor escutando
			while (true) {
				Socket connection = receptor.accept();
				ServerConexao conexao = new ServerConexao();
				clientOutputStreams.add(conexao);
				Thread t = new Thread(new ClientHandler(conexao, receptor, connection));
				t.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Cuida das conexoes com novos clientes
	private class ClientHandler implements Runnable {
		ServerConexao conexao;
		ServerSocket receptor;
		Socket connection;

		public ClientHandler(ServerConexao conexao, ServerSocket receptor, Socket connection) throws IOException {
			this.conexao = conexao;
			this.receptor = receptor;
			this.connection = connection;
		}

		@Override
		public void run() {
			try {
				System.out.println("Recebido uma nova conex�o.");
				conexao.conecta(receptor, connection);
			} catch (NullPointerException e) {
				System.out.println("Usuario Desconectado");
			}
		}
	}
}
