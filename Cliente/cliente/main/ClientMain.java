package cliente.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;

import cliente.conection.ClientConexao;

public class ClientMain {

	public static void main(String[] args) {

		
		//FALTA FAZER MODO GRAFICO, FEITO EM TEXTO PARA TESTE
		//AS MENSSAGENS QUE O SERVIDOR PRECISA RECEBER SAO ASSIM
		//(VAR1/VAR2/VAR3/VAR4)
		//VAR1 - LOG OU CAD
		//VAR2 - EMAIL
		//VAR3 - NOME
		//VAR4 - SENHA
		//LOG - LOGIN (LOG/EMAIL/NOME/SENHA)
		//CAD - CADASTRO (CAD/EMAIL/NOME/SENHA)
		try {
			String ip, menssagem;
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Digite o ip do servidor:\n");
			ip = reader.readLine();
			System.out.println("Digite a mensagem nos padroes VAR1/VAR2/VAR3/VAR4 :\n");
			menssagem = reader.readLine();
			ClientConexao c = new ClientConexao(ip);
			c.Envia(menssagem);
			c.Recebe();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
