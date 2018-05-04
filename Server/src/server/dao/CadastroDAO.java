package server.dao;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import server.database.Conexao;
import server.database.MeuResultSet;
import server.dbo.CadastroDBO;

public class CadastroDAO {

	public void cadastro(CadastroDBO dbos) throws Exception {

		if (dbos == null)
			throw new Exception("Cadastro nulo");

		try {
			String sql = "INSERT INTO tbl_usuario (email, senha, nome) VALUES (?,?,?)";

			Conexao.conexao.prepareStatement(sql);
			Conexao.conexao.setString(1, dbos.getEmail());
			Conexao.conexao.setString(2, dbos.getSenha());
			Conexao.conexao.setString(3, dbos.getNome());
			Conexao.conexao.executeUpdate();
			Conexao.conexao.commit();
			throw new Exception("Cadastro do usuario: ("+ dbos.getNome() +") realizado com sucesso!");
		} catch (SQLException e) {
			throw new Exception("Erro ao realizar cadastro." + e);
		}
	}

	public boolean validaEmail(String email) {
		{
			boolean isEmailIdValid = false;
			if (email != null && email.length() > 0) {
				String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
				Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
				Matcher matcher = pattern.matcher(email);
				if (matcher.matches()) {
					isEmailIdValid = true;
				}
			}
			return isEmailIdValid;
		}
	}

	public CadastroDBO getEmail(String usuario) throws SQLException, Exception {
		CadastroDBO cadastroDBO = null;
		String sql;

		try {
			sql = "SELECT * " + "FROM tbl_usuario " + "WHERE email = ?";

			Conexao.conexao.prepareStatement(sql);

			Conexao.conexao.setString(1, usuario);

			MeuResultSet resultado = (MeuResultSet) Conexao.conexao.executeQuery();

			if (!resultado.first())
				throw new Exception("Usuario nao cadastrado.");

			cadastroDBO = new CadastroDBO(resultado.getString("email"), resultado.getString("senha"), resultado.getString("nome"));

		} catch (Exception e) {
			throw new Exception(e);
		}

		return cadastroDBO;

	}

}