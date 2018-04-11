package database;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

public class MeuStatement implements Statement {
	
	protected Connection conexao = null;
	protected Statement comando = null;

	protected ArrayList<Statement> comandos = new ArrayList<Statement>();

	protected ArrayList<MeuResultSet> resultados = new ArrayList<MeuResultSet>();

	public MeuStatement(String drv, String strCon, String usr, String senha)
			throws ClassNotFoundException, SQLException {
		Class.forName(drv);

		this.conexao = DriverManager.getConnection(strCon, usr, senha);

		this.conexao.setAutoCommit(false);

		this.comando = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	}

	// metodos herdados da interface Statement

	public void addBatch(String sql) throws SQLException {
		this.comando.addBatch(sql);
	}

	public void cancel() throws SQLException {
		this.comando.cancel();
	}

	public void clearBatch() throws SQLException {
		this.comando.clearBatch();
	}

	public void clearWarnings() throws SQLException {
		this.comando.clearWarnings();
	}

	public void close() throws SQLException {
		for (ResultSet r : this.resultados)
			r.close();

		for (Statement s : this.comandos)
			s.close();

		this.comando.close();
		this.conexao.close();
	}

	public void closeOnCompletion() throws SQLException {
		this.comando.closeOnCompletion();
	}

	public boolean execute(String sql) throws SQLException {
		return this.comando.execute(sql);
	}

	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		return this.comando.execute(sql, autoGeneratedKeys);
	}

	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		return this.comando.execute(sql, columnIndexes);
	}

	public boolean execute(String sql, String[] columnNames) throws SQLException {
		return this.comando.execute(sql, columnNames);
	}

	public int[] executeBatch() throws SQLException {
		return this.comando.executeBatch();
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		ResultSet resultado = this.comando.executeQuery(sql);

		MeuResultSet retorno = new MeuResultSet(this.comando, resultado);

		this.resultados.add(retorno);
		this.comandos.add(this.comando);

		this.comando = this.conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		return retorno;
	}

	public int executeUpdate(String sql) throws SQLException {
		return this.comando.executeUpdate(sql);
	}

	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		return this.comando.executeUpdate(sql, autoGeneratedKeys);
	}

	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		return this.comando.executeUpdate(sql, columnIndexes);
	}

	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		return this.comando.executeUpdate(sql, columnNames);
	}

	public Connection getConnection() throws SQLException {
		return this.comando.getConnection();
	}

	public int getFetchDirection() throws SQLException {
		return this.comando.getFetchDirection();
	}

	public int getFetchSize() throws SQLException {
		return this.comando.getFetchSize();
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		ResultSet resultado = this.comando.getGeneratedKeys();

		MeuResultSet retorno = new MeuResultSet(this.comando, resultado);

		this.comando = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		return retorno;
	}

	public int getMaxFieldSize() throws SQLException {
		return this.comando.getMaxFieldSize();
	}

	public int getMaxRows() throws SQLException {
		return this.comando.getMaxRows();
	}

	public boolean getMoreResults() throws SQLException {
		return this.comando.getMoreResults();
	}

	public boolean getMoreResults(int current) throws SQLException {
		return this.comando.getMoreResults(current);
	}

	public int getQueryTimeout() throws SQLException {
		return this.comando.getQueryTimeout();
	}

	public ResultSet getResultSet() throws SQLException {
		return this.comando.getResultSet();
	}

	public int getResultSetConcurrency() throws SQLException {
		return this.comando.getResultSetConcurrency();
	}

	public int getResultSetHoldability() throws SQLException {
		return this.comando.getResultSetHoldability();
	}

	public int getResultSetType() throws SQLException {
		return this.comando.getResultSetType();
	}

	public int getUpdateCount() throws SQLException {
		return this.comando.getUpdateCount();
	}

	public SQLWarning getWarnings() throws SQLException {
		return this.comando.getWarnings();
	}

	public boolean isClosed() throws SQLException {
		return this.comando.isClosed();
	}

	public boolean isCloseOnCompletion() throws SQLException {
		return this.comando.isCloseOnCompletion();
	}

	public boolean isPoolable() throws SQLException {
		return this.comando.isPoolable();
	}

	public void setCursorName(String name) throws SQLException {
		this.comando.setCursorName(name);
	}

	public void setEscapeProcessing(boolean enable) throws SQLException {
		this.comando.setEscapeProcessing(enable);
	}

	public void setFetchDirection(int direction) throws SQLException {
		this.comando.setFetchDirection(direction);
	}

	public void setFetchSize(int rows) throws SQLException {
		this.comando.setFetchSize(rows);
	}

	public void setMaxFieldSize(int max) throws SQLException {
		this.comando.setMaxFieldSize(max);
	}

	public void setMaxRows(int max) throws SQLException {
		this.comando.setMaxRows(max);
	}

	public void setPoolable(boolean poolable) throws SQLException {
		this.comando.setPoolable(poolable);
	}

	public void setQueryTimeout(int seconds) throws SQLException {
		this.comando.setQueryTimeout(seconds);
	}

	// metodos herdados da interface Wrapper

	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.comando.isWrapperFor(iface);
	}

	public <T> T unwrap(Class<T> iface) throws SQLException {
		return this.comando.unwrap(iface);
	}

	public int hashCode() {
		int ret = super.hashCode();

		ret = 13 * ret + (this.conexao == null ? 0 : this.conexao.hashCode());
		ret = 13 * ret + (this.comando == null ? 0 : this.comando.hashCode());
		ret = 13 * ret + (this.comandos == null ? 0 : this.comandos.hashCode());
		ret = 13 * ret + (this.resultados == null ? 0 : this.resultados.hashCode());

		return ret;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (this.getClass() == obj.getClass()) {
			Statement s = (Statement) obj;

			if (!super.equals(s))
				return false;

			MeuStatement ms = (MeuStatement) obj;

			if ((this.conexao == null && ms.conexao != null) || (this.conexao != null && ms.conexao == null))
				return false;

			if (this.conexao != null && ms.conexao != null && this.conexao.equals(ms.conexao))
				return false;

			if ((this.comando == null && ms.comando != null) || (this.comando != null && ms.comando == null))
				return false;

			if (this.comando != null && ms.comando != null && this.comando.equals(ms.comando))
				return false;

			if ((this.comandos == null && ms.comandos != null) || (this.comandos != null && ms.comandos == null))
				return false;

			if (this.comandos != null && ms.comandos != null && this.comandos.equals(ms.comandos))
				return false;

			if ((this.resultados == null && ms.resultados != null)
					|| (this.resultados != null && ms.resultados == null))
				return false;

			if (this.resultados != null && ms.resultados != null && this.resultados.equals(ms.resultados))
				return false;

			return true;
		}

		return false;
	}

	public String toString() {
		String ret = "Herdado...: " + super.toString() + "\n";

		ret += "Conexao...: " + this.conexao + "\n";
		ret += "Comando...: " + this.comando + "\n";
		ret += "Comandos..: " + this.comandos + "\n";
		ret += "Resultados: " + this.resultados;

		return ret;
	}
}