package server.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import server.dao.CadastroDAO;
import server.dbo.CadastroDBO;

public class main {
	
	ArrayList<ConexaoRede> clientOutputStreams;
    public static void main(String[] args) {
        new main().go();
    }
    
    private void go() {
    	//Guarda os clientes
        clientOutputStreams = new ArrayList();

        try {
			ServerSocket receptor = new ServerSocket(11111);
            System.out.println("Servidor up");

//            Mantem o servidor escutando
            while (true) {
            	ConexaoRede conexao = new ConexaoRede();
            	conexao.conecta(receptor);
                clientOutputStreams.add(conexao);
                Thread t = new Thread(new ClientHandler(conexao, receptor));
                t.start();
                System.out.println("Recebido uma nova conexão");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Cuida das conexoes com novos clientes
    private class ClientHandler implements Runnable {
    	ConexaoRede conexao;
    	ServerSocket receptor;
        public ClientHandler(ConexaoRede conexao, ServerSocket receptor) throws IOException {
            this.conexao = conexao;
            this.receptor = receptor;
        }
        
        @Override
        public void run() {
            try {
                conexao.conecta(receptor);
            } catch (NullPointerException e){
            	System.out.println("Usuario Desconectado");
            }
        }
    }
}
