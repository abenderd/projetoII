package database;

import javax.swing.JOptionPane;

import dao.CadastroDAO;

public class Conexao {

	public static final MeuPreparedStatement conexao;
	public static final CadastroDAO cadastroDao;

	static {
		MeuPreparedStatement _conexao = null;
		CadastroDAO _cadastroDao = null;

		try {
			_conexao = new MeuPreparedStatement("com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"jdbc:sqlserver://DESKTOP-54DQGM1\\SQLEXPRESS:1433", "DESKTOP-54DQGM1\\abend", "");

			_cadastroDao = new CadastroDAO();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro de conexao com o banco de dados." + e);
			System.err.println(e);
		}

		conexao = _conexao;
		cadastroDao = _cadastroDao;
	}

	public static MeuPreparedStatement getConexao() {
		return conexao;
	}

	public static CadastroDAO getCadastrodao() {
		return cadastroDao;
	}

}