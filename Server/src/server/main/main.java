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
	
	ArrayList clientOutputStreams;
    public static void main(String[] args) {
        new main().go();
    }
    
    private void go() {
    	//Guarda os clientes
        clientOutputStreams = new ArrayList();

        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Servidor up");

//            Mantem o servidor escutando
            while (true) {
                Socket clientSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream());
                clientOutputStreams.add(writer);

                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();
                System.out.println("Recebido uma nova conexão");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    Cuida das conexoes com novos clientes
    private class ClientHandler implements Runnable {
        BufferedReader reader;
        Socket sock;
        public ClientHandler(Socket clientSocket) {
            try {
                sock = clientSocket;
                reader = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void run() {
            String message;
            try {
            	while ((message = reader.readLine()) != "fim") {
            		
            		String[] temp;
                    String delimiter = "/";
                    temp = message.split(delimiter); 
                    String var1 = temp[0];
                    String var2 = temp[1];
                    String var3 = temp[2];
                    String var4 = temp[3];
                    CadastroDBO cad = new CadastroDBO(var2,var4,var3);
                	CadastroDAO cadDao = new CadastroDAO();
                	
                    System.out.println(var1+"/"+var2+"/"+var3+"/"+var4);
                    
                    //(VAR1/VAR2/VAR3/VAR4)
                    //CLIENTE
                    //CAD - CADASTRO (CAD/EMAIL/NOME/SENHA)
                    //LOG - LOGIN (LOG/EMAIL/NOME/SENHA)
                    //SERVIDOR
                    //CAD - (OK - JA EXISTE)
                    //LOG - (EMAIL/LOGADO COM SUCESSO)
                    
                    if(var1.equals("CAD")){
                    	try {
							cadDao.cadastro(cad);
						} catch (Exception e) {
							tellEveryone(var2 + "/" + e);
						}
                    	tellEveryone(var2 + "/Cadastrado com sucesso");
                    }else if(var1.equals("LOG")){
                    	try {
							cad = cadDao.getEmail(var2);
							System.out.println(cad.toString());
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							tellEveryone(var2 + "/" + e);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							tellEveryone(var2 + "/" + e);
						}
                    	tellEveryone(var2 + "/Logado com sucesso");
                    }else{
                    	tellEveryone(var2 + "/MENSSAGEM INVALIDA");
                    }
            	}
            } catch (NullPointerException e){
            	System.out.println("Usuario Desconectado");
            } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }

//    Envia um broadcast para todo mundo
    private void tellEveryone(String message) {
        System.out.println("sending a broadcast");
        Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) {
            try {
                PrintWriter writer = (PrintWriter) it.next();
                writer.println(message);
                writer.flush();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
