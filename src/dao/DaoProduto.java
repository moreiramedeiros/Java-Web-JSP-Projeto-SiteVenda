package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import bean.BeanProduto;
import bean.BeanUsuario;
import connection.SingleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = SingleConnection.getConnection();
	}

 
  
	
	   
	 
	
	public void salvar(BeanProduto produto) {
		try {

			String sql = "INSERT INTO produto (nome, categoria, preco, descricao,  quantidade, comprimento,  largura, altura, peso, imagem, data) VALUES (?,?,?,?,?,?,?,?,?,?,current_timestamp);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getCategoria());
			preparedStatement.setDouble(3, produto.getPreco());
			preparedStatement.setString(4, produto.getDescricao());
			preparedStatement.setInt(5, produto.getQuantidade());
			preparedStatement.setDouble(6, produto.getComprimento());
			preparedStatement.setDouble(7, produto.getLargura());
			preparedStatement.setDouble(8, produto.getAltura());
			preparedStatement.setDouble(9, produto.getPeso());
			preparedStatement.setString(10, produto.getImagem());
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
	
	public BeanProduto consultar(Long id) throws SQLException {
		String sql = "select * FROM produto where id = " + id;
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setCategoria(resultSet.getString("categoria"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setComprimento(resultSet.getDouble("comprimento"));
			produto.setLargura(resultSet.getDouble("largura"));
			produto.setAltura(resultSet.getDouble("altura"));
			produto.setPeso(resultSet.getDouble("peso"));
			produto.setImagem(resultSet.getString("imagem"));
			return produto;
		}
		return null;
	}

	

	public List<BeanProduto> listar() throws SQLException {
		List<BeanProduto> lista = new ArrayList<BeanProduto>();
		String sql = "SELECT * FROM produto order by id";
		consultaUsuario(lista, sql);
		return lista;
	}

	public List<BeanProduto> pesquisar(String nome) throws SQLException {
		List<BeanProduto> lista = new ArrayList<BeanProduto>();
		String sql = "SELECT * FROM produto where LOWER(nome) like '%" + nome + "%' order by id";
		consultaUsuario(lista, sql);
		return lista;
	}
 
	
	private void consultaUsuario(List<BeanProduto> lista, String sql) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			BeanProduto produto = new BeanProduto();
			produto.setId(resultSet.getLong("id"));
			produto.setNome(resultSet.getString("nome"));
			produto.setCategoria(resultSet.getString("categoria"));
			produto.setPreco(resultSet.getDouble("preco"));
			produto.setDescricao(resultSet.getString("descricao"));
			produto.setQuantidade(resultSet.getInt("quantidade"));
			produto.setComprimento(resultSet.getDouble("comprimento"));
			produto.setLargura(resultSet.getDouble("largura"));
			produto.setAltura(resultSet.getDouble("altura"));
			produto.setPeso(resultSet.getDouble("peso"));
			produto.setImagem(resultSet.getString("imagem"));
			lista.add(produto);
		}
	}

	public void deletar(Long id) {
		try {

			String sql = "delete from produto where id = " + id;
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

	public void atualizar(BeanProduto produto) {
		try {
			 
			String sql = "update produto set nome = ?, categoria = ?, preco = ?,  descricao = ?, quantidade = ?, comprimento = ?, largura = ? , altura = ? , peso = ?, imagem = ?    "
					+ " where id = " + produto.getId();

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, produto.getNome());
			preparedStatement.setString(2, produto.getCategoria());
			preparedStatement.setDouble(3, produto.getPreco());
			preparedStatement.setString(4, produto.getDescricao());
			preparedStatement.setInt(5, produto.getQuantidade());
			preparedStatement.setDouble(6, produto.getComprimento());
			preparedStatement.setDouble(7, produto.getLargura());
			preparedStatement.setDouble(8, produto.getAltura());
			preparedStatement.setDouble(9, produto.getPeso());
			preparedStatement.setString(10, produto.getImagem());
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
	
	
	
	
	
	/* Pesquisar e listar na página home */
	
	public List<BeanProduto> listarHome() throws SQLException {
		List<BeanProduto> lista = new ArrayList<BeanProduto>();
		String sql = "SELECT * FROM produto order by data DESC";
		consultaUsuario(lista, sql);
		return lista;
	}

	
	public List<BeanProduto> pesquisarHome(String nome) throws SQLException {
		List<BeanProduto> lista = new ArrayList<BeanProduto>();
		String sql = "SELECT * FROM produto where LOWER(nome) like '%" + nome + "%' OR LOWER(categoria) like '%" + nome + "%' OR LOWER(descricao) like '%" + nome + "%' order by data DESC";
		consultaUsuario(lista, sql);
		return lista;
	}

	public List<BeanProduto> pesquisarCategoria(String categoria) throws SQLException {
		List<BeanProduto> lista = new ArrayList<BeanProduto>();
		String sql = "SELECT * FROM produto where categoria like '%" + categoria + "%' order by data DESC";
		consultaUsuario(lista, sql);
		return lista;
	}	
	
	
	/* Atualizar estoque */
	
	public void decrementarQuantidadeEstoque(Long id, int quantidade_consumida) {
		try {
			 
			String sql = " UPDATE produto SET quantidade =  " + 
					    " (SELECT quantidade FROM produto WHERE id = "+id+") - "+quantidade_consumida + 
					    " WHERE id = "+id+" ";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
 
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
