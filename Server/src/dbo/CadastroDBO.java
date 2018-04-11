package dbo;

import javax.swing.JOptionPane;

import dao.CadastroDAO;

public class CadastroDBO {
	CadastroDAO cadastroDao = new CadastroDAO();

	private String senha, email;

	public CadastroDBO(String email, String senha) {
		this.email = email;
		this.senha = senha;
	}

	public void cadastroEmail(String email) {
		if (this.email == null || " ".equals(email)) {
			JOptionPane.showMessageDialog(null, "Email invalido ou nulo");
		} else {
			this.email = email;
		}
	}

	public void cadastroSenha(String senha) {
		if (this.senha == null || "".equals(senha)) {
			JOptionPane.showMessageDialog(null, "Senha invalida");
		} else {
			this.senha = senha;
		}
	}

	public void cadastroCsenha(String senha, String confirmacaoSenha) throws Exception {
		if (this.senha.equals(confirmacaoSenha)) {

		} else {
			JOptionPane.showMessageDialog(null, "Cadastro nao realizado senhas incompativeis");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cadastroDao == null) ? 0 : cadastroDao.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CadastroDBO other = (CadastroDBO) obj;
		if (cadastroDao == null) {
			if (other.cadastroDao != null)
				return false;
		} else if (!cadastroDao.equals(other.cadastroDao))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "CadastroDBO [cadastroDao=" + cadastroDao + ", senha=" + senha + ", email=" + email + "]";
	}

	public String getSenha() {
		return senha;
	}

	public String getEmail() {
		return email;
	}
}