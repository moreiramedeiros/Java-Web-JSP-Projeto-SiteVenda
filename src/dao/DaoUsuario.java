package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.BeanUsuario;
import connection.SingleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = SingleConnection.getConnection();
	}
	
	/* Validar usuário ao fazer login */

	public boolean validarLogin(String login, String senha) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE (login = '" + login + "' OR email = '"+login+"') AND senha = '" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			return true; // Possue usuário
		} else {
			return false; // Não possue usuário
		}

	}

	/* Verificar dados duplicado */
	
	public boolean validarLoginDuplicado(String login) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
        int cont=0;
		while (resultSet.next()) {
			cont++;
			if(cont>0) {
				return true;
			}
		}
       return false;
	}
	

	public boolean validarEmailDuplicado(String email) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE email = '" + email + "' ";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
        int cont=0;
		while (resultSet.next()) {
			cont++;
			if(cont>0) {
				return true;
			}
		}
       return false;
	}
	
	
	/* Verificar dados duplicado em editar*/
	
	
	public boolean validarLoginDuplicado(String login, String id) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND id <> "+id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
        int cont=0;
		while (resultSet.next()) {
			cont++;
			if(cont>0) {
				return true;
			}
		}
       return false;
	}
	

	public boolean validarEmailDuplicado(String email, String id) throws SQLException {

		String sql = "SELECT * FROM usuario WHERE email = '" + email + "' AND id <> "+id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
        int cont=0;
		while (resultSet.next()) {
			cont++;
			if(cont>0) {
				return true;
			}
		}
       return false;
	}
	
	
	
	
	public void salvar(BeanUsuario usuario) {
		try {

			String sql = "INSERT INTO usuario (login, senha, nome, cpf, telefone, celular, cep, rua, numero, bairro, cidade, estado, email, sexo, nivel_acesso, data) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getCpf());
			preparedStatement.setString(5, usuario.getTelefone());
			preparedStatement.setString(6, usuario.getCelular());
			preparedStatement.setString(7, usuario.getCep());
			preparedStatement.setString(8, usuario.getRua());
			preparedStatement.setString(9, usuario.getNumero());
			preparedStatement.setString(10, usuario.getBairro());
			preparedStatement.setString(11, usuario.getCidade());
			preparedStatement.setString(12, usuario.getEstado());
			preparedStatement.setString(13, usuario.getEmail());
			preparedStatement.setString(14, usuario.getSexo());
			preparedStatement.setString(15, usuario.getNivel_acesso());
			preparedStatement.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public BeanUsuario consultar(Long id) throws SQLException {
		String sql = "select * FROM usuario where id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setCpf(resultSet.getString("cpf"));
			usuario.setTelefone(resultSet.getString("telefone"));
			usuario.setCelular(resultSet.getString("celular"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setEstado(resultSet.getString("estado"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setNivel_acesso(resultSet.getString("nivel_acesso"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSexo(resultSet.getString("sexo"));

			return usuario;
		}
		return null;
	}

	public BeanUsuario consultar(String login, String senha) throws SQLException {
		String sql = "SELECT * FROM usuario WHERE login = '" + login + "' AND senha = '" + senha + "'";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setTelefone(resultSet.getString("telefone"));
			usuario.setCelular(resultSet.getString("celular"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setCpf(resultSet.getString("cpf"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setEstado(resultSet.getString("estado"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setNivel_acesso(resultSet.getString("nivel_acesso"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSexo(resultSet.getString("sexo"));
			return usuario;
		}
		return null;
	}

	public List<BeanUsuario> listar() throws SQLException {
		List<BeanUsuario> lista = new ArrayList<BeanUsuario>();
		String sql = "SELECT * FROM usuario order by id";
		consultaUsuario(lista, sql);
		return lista;
	}

	public List<BeanUsuario> pesquisar(String nome) throws SQLException {
		List<BeanUsuario> lista = new ArrayList<BeanUsuario>();
		String sql = "SELECT * FROM usuario where LOWER(nome) like '%" + nome + "%' order by id";
		consultaUsuario(lista, sql);
		return lista;
	}

	private void consultaUsuario(List<BeanUsuario> lista, String sql) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanUsuario usuario = new BeanUsuario();
			usuario.setId(resultSet.getLong("id"));
			usuario.setLogin(resultSet.getString("login"));
			usuario.setSenha(resultSet.getString("senha"));
			usuario.setNome(resultSet.getString("nome"));
			usuario.setTelefone(resultSet.getString("telefone"));
			usuario.setCelular(resultSet.getString("celular"));
			usuario.setCep(resultSet.getString("cep"));
			usuario.setRua(resultSet.getString("rua"));
			usuario.setCpf(resultSet.getString("cpf"));
			usuario.setBairro(resultSet.getString("bairro"));
			usuario.setCidade(resultSet.getString("cidade"));
			usuario.setEstado(resultSet.getString("estado"));
			usuario.setNumero(resultSet.getString("numero"));
			usuario.setNivel_acesso(resultSet.getString("nivel_acesso"));
			usuario.setEmail(resultSet.getString("email"));
			usuario.setSexo(resultSet.getString("sexo"));
			lista.add(usuario);
		}
	}

	public void deletar(Long id) {
		try {

			String sql = "delete from usuario where id = " + id;
			PreparedStatement delete = connection.prepareStatement(sql);
			delete.execute();
			connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public void atualizar(BeanUsuario usuario) {
		try {

			String sql = "update usuario set login = ?, senha = ?, nome = ?, cpf = ?, telefone = ?, celular = ?, "
					+ "cep = ?, rua = ?, numero = ?,  bairro = ?, cidade = ?, estado = ?, email = ? , sexo = ? , nivel_acesso = ?  "
					+ " where id = " + usuario.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, usuario.getLogin());
			preparedStatement.setString(2, usuario.getSenha());
			preparedStatement.setString(3, usuario.getNome());
			preparedStatement.setString(4, usuario.getCpf());
			preparedStatement.setString(5, usuario.getTelefone());
			preparedStatement.setString(6, usuario.getCelular());
			preparedStatement.setString(7, usuario.getCep());
			preparedStatement.setString(8, usuario.getRua());
			preparedStatement.setString(9, usuario.getNumero());
			preparedStatement.setString(10, usuario.getBairro());
			preparedStatement.setString(11, usuario.getCidade());
			preparedStatement.setString(12, usuario.getEstado());
			preparedStatement.setString(13, usuario.getEmail());
			preparedStatement.setString(14, usuario.getSexo());
			preparedStatement.setString(15, usuario.getNivel_acesso());
			preparedStatement.executeUpdate();
			connection.commit();

		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}

		}
	}

}
