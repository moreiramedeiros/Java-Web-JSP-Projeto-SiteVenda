package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.BeanItemPedido;
import bean.BeanJoin;
import bean.BeanPedido;
import bean.BeanProduto;
import connection.SingleConnection;

public class DaoPedido {

	private Connection connection;

	public DaoPedido() {
		connection = SingleConnection.getConnection();
	}
	

	public void salvarPedido(BeanPedido pedido) {
		try {

			String sql = "INSERT INTO pedido (pagamento, parcelamento, status_pagamento, id_usuario, data) VALUES (?,?,?,?,current_timestamp);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, pedido.getPagamento());
			preparedStatement.setInt(2, pedido.getParcelamento());
			preparedStatement.setString(3, pedido.getStatus_pagamento());
			preparedStatement.setLong(4, pedido.getId_usuario());
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


	
	public List<BeanJoin> listarPedidos() throws SQLException {
		List<BeanJoin> lista = new ArrayList<BeanJoin>();
		String sql =   
		"SELECT "+
		" u.nome AS nomeUsuario, "+	
		" u.id AS idUsuario, "+	
		" pe.id AS idPedido, "+				
		" pe.pagamento AS pagamentoPedido, "+
		" pe.parcelamento AS parcelamentoPedido, "+				
		" pe.status_pagamento AS status_pagamentoPedido, "+
		" pe.data AS dataPedido "+	
		" FROM pedido pe INNER JOIN usuario u  "+
		" ON u.id = pe.id_usuario "+
		" order by pe.data DESC";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		// produto -> nome, imagem
        // pedido ->  id, pagamento , parcelamento, status_pagamento, data
		 // item_pedido -> status_entrega, data_estrega, preco_unitario, unidades
		
		while (resultSet.next()) {			
		   BeanJoin beanJoin = new BeanJoin();
		   beanJoin.setNome_usuario(resultSet.getString("nomeUsuario"));
		   beanJoin.setData_pedido(resultSet.getString("dataPedido"));
		   beanJoin.setId_pedido(resultSet.getLong("idPedido"));
		   beanJoin.setId_usuario(resultSet.getLong("idUsuario"));
		   beanJoin.setPagamento(resultSet.getString("pagamentoPedido"));
		   beanJoin.setParcelamento(resultSet.getInt("parcelamentoPedido"));
		   beanJoin.setStatus_pagamento(resultSet.getString("status_pagamentoPedido"));
		   lista.add(beanJoin);
		}
			
		return lista;
	}
	
	
	public void atualizarStatus_pagamento(String id_pedido, String status_pagamento) {
		try {
			 
			String sql = "update pedido set status_pagamento = ?    "
					+ " where id = " + id_pedido;

			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, status_pagamento);
 
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
	
	
	public List<BeanJoin> pesquisarUsuarioPedido(String nome) throws SQLException {
		List<BeanJoin> lista = new ArrayList<BeanJoin>();
		String sql =   
		"SELECT "+
		" u.nome AS nomeUsuario, "+	
		" u.id AS idUsuario, "+	
		" pe.id AS idPedido, "+				
		" pe.pagamento AS pagamentoPedido, "+
		" pe.parcelamento AS parcelamentoPedido, "+				
		" pe.status_pagamento AS status_pagamentoPedido, "+
		" pe.data AS dataPedido "+	
		" FROM pedido pe INNER JOIN usuario u  "+
		" ON u.id = pe.id_usuario "+
		" WHERE LOWER(u.nome) like '%"+nome+"%' "+
		" order by pe.data DESC";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		// produto -> nome, imagem
        // pedido ->  id, pagamento , parcelamento, status_pagamento, data
		 // item_pedido -> status_entrega, data_estrega, preco_unitario, unidades
		
		while (resultSet.next()) {			
		   BeanJoin beanJoin = new BeanJoin();
		   beanJoin.setNome_usuario(resultSet.getString("nomeUsuario"));
		   beanJoin.setData_pedido(resultSet.getString("dataPedido"));
		   beanJoin.setId_pedido(resultSet.getLong("idPedido"));
		   beanJoin.setId_usuario(resultSet.getLong("idUsuario"));
		   beanJoin.setPagamento(resultSet.getString("pagamentoPedido"));
		   beanJoin.setParcelamento(resultSet.getInt("parcelamentoPedido"));
		   beanJoin.setStatus_pagamento(resultSet.getString("status_pagamentoPedido"));
		   lista.add(beanJoin);
		}
		return lista;
	}
	
	
	
	/*
	 
	 SELECT * FROM usuario u INNER JOIN pedido p 
ON u.id = p.id_usuario INNER JOIN item_pedido i
ON p.id = i.id_pedido INNER JOIN produto pr
ON i.id_produto = pr.id 
WHERE u.id = 3228
 
	 */
	
	
	
	
/* Retorna o ultimo ID do pedido */
	
	public Long ultimoPedido() throws SQLException {
		String sql = "select MAX(id) AS ultimoId FROM pedido";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {			 
			Long ultimoId = resultSet.getLong("ultimoId"); 
			return ultimoId;
		}
		return null;
	}

	
	
/* Salvar Item Pedido */
	
	public void salvarItemPedido(BeanItemPedido ItemPedido) {
		try {

			String sql = "INSERT INTO item_pedido (id_pedido, id_produto, unidades, preco_unitario, status_entrega) VALUES (?,?,?,?,?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, ItemPedido.getId_pedido());
			preparedStatement.setLong(2, ItemPedido.getId_produto());
			preparedStatement.setInt(3, ItemPedido.getUnidades());
			preparedStatement.setDouble(4, ItemPedido.getPreco_unitario());
			preparedStatement.setString(5, ItemPedido.getStatus_entrega());
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
	
	
	
	/* Listar Itens por usuario*/
	
	public List<BeanJoin> listarJoin(Long id_usuario) throws SQLException {
		List<BeanJoin> lista = new ArrayList<BeanJoin>();
		String sql =   
		"SELECT "+
		" pr.nome AS nomeProduto, "+				
		" pr.imagem AS imagemProduto, "+
		" pe.id AS idPedido, "+				
		" pe.pagamento AS pagamentoPedido, "+
		" pe.parcelamento AS parcelamentoPedido, "+				
		" pe.status_pagamento AS status_pagamentoPedido, "+
		" pe.data AS dataPedido, "+	
		" i.id AS idItem, "+	
		" i.status_entrega AS status_entregaItem, "+
		" i.data_entrega AS data_entregaItem, "+
		" i.preco_unitario AS preco_unitarioItem, "+
		" i.unidades AS unidadesItem "+
		" FROM pedido pe INNER JOIN item_pedido i "+
		" ON pe.id = i.id_pedido INNER JOIN produto pr "+
		" ON i.id_produto = pr.id "+
		" WHERE pe.id_usuario = "+id_usuario+" order by pe.data DESC";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		// produto -> nome, imagem
        // pedido ->  id, pagamento , parcelamento, status_pagamento, data
		 // item_pedido -> status_entrega, data_estrega, preco_unitario, unidades
		
		while (resultSet.next()) {			
		   BeanJoin beanJoin = new BeanJoin();
		   beanJoin.setData_entrega(resultSet.getString("data_entregaItem"));
		   beanJoin.setData_pedido(resultSet.getString("dataPedido"));
		   beanJoin.setId_pedido(resultSet.getLong("idPedido"));
		   beanJoin.setId_item(resultSet.getLong("idItem"));
		   beanJoin.setImagem(resultSet.getString("imagemProduto"));
		   beanJoin.setNome(resultSet.getString("nomeProduto"));
		   beanJoin.setPagamento(resultSet.getString("pagamentoPedido"));
		   beanJoin.setParcelamento(resultSet.getInt("parcelamentoPedido"));
		   beanJoin.setPreco_unitario(resultSet.getDouble("preco_unitarioItem"));		   
		   beanJoin.setStatus_entrega(resultSet.getString("status_entregaItem"));
		   beanJoin.setStatus_pagamento(resultSet.getString("status_pagamentoPedido"));
		   beanJoin.setUnidades(resultSet.getInt("unidadesItem"));
		   lista.add(beanJoin);
		}
			
		return lista;
	}
	
	
	
/* Listar Itens por usuario e pedidos */
	
	public List<BeanJoin> listarJoin(String id_usuario, String id_pedido) throws SQLException {
		List<BeanJoin> lista = new ArrayList<BeanJoin>();
		String sql =   
		"SELECT "+
		" pr.nome AS nomeProduto, "+				
		" pr.imagem AS imagemProduto, "+
		" pe.id AS idPedido, "+				
		" pe.pagamento AS pagamentoPedido, "+
		" pe.parcelamento AS parcelamentoPedido, "+				
		" pe.status_pagamento AS status_pagamentoPedido, "+
		" pe.data AS dataPedido, "+	
		" i.id AS idItem, "+	
		" i.status_entrega AS status_entregaItem, "+
		" i.data_entrega AS data_entregaItem, "+
		" i.preco_unitario AS preco_unitarioItem, "+
		" i.unidades AS unidadesItem "+
		" FROM pedido pe INNER JOIN item_pedido i "+
		" ON pe.id = i.id_pedido INNER JOIN produto pr "+
		" ON i.id_produto = pr.id "+
		" WHERE pe.id_usuario = "+id_usuario+" AND pe.id = "+id_pedido+"order by pe.data DESC";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
	
		while (resultSet.next()) {			
		   BeanJoin beanJoin = new BeanJoin();
		   beanJoin.setData_entrega(resultSet.getString("data_entregaItem"));
		   beanJoin.setData_pedido(resultSet.getString("dataPedido"));
		   beanJoin.setId_pedido(resultSet.getLong("idPedido"));
		   beanJoin.setId_item(resultSet.getLong("idItem"));
		   beanJoin.setImagem(resultSet.getString("imagemProduto"));
		   beanJoin.setNome(resultSet.getString("nomeProduto"));
		   beanJoin.setPagamento(resultSet.getString("pagamentoPedido"));
		   beanJoin.setParcelamento(resultSet.getInt("parcelamentoPedido"));
		   beanJoin.setPreco_unitario(resultSet.getDouble("preco_unitarioItem"));		   
		   beanJoin.setStatus_entrega(resultSet.getString("status_entregaItem"));
		   beanJoin.setStatus_pagamento(resultSet.getString("status_pagamentoPedido"));
		   beanJoin.setUnidades(resultSet.getInt("unidadesItem"));
		   lista.add(beanJoin);
		}
			
		return lista;
	}
	
	
	
	public void atualizarStatus_entrega(String id_item, String status_entrega) {
		try {
			String sql="";
			if(status_entrega.equals("Produto entregue")) {
				sql = "update item_pedido set status_entrega = ?, data_entrega = current_timestamp  "
						+ " where id = " + id_item;				
			}else {
				sql = "update item_pedido set status_entrega = ?, data_entrega = null  "
						+ " where id = " + id_item;
			}
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, status_entrega);
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
	
	public void atualizar_data_entrega(String id_item, String data_entrega) {
		try {
			 
			String sql = "update item_pedido set data_entrega = ?    "
					+ " where id = " + id_item;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDate(1, java.sql.Date.valueOf(data_entrega));
		//	preparedStatement.setDate(parameterIndex, x);

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
	/* Listar Itens por usuario*/
	
	public int quantidadeItens(Long id_pedido) throws SQLException {
	 
		String sql =   
		"SELECT "+
		" * "+				
		" FROM pedido pe INNER JOIN item_pedido i "+
		" ON pe.id = i.id_pedido " +
		" WHERE pe.id = "+id_pedido+" ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
	 
		int cont=0;
		while (resultSet.next()) {			
			cont++;		 
		}
			
		return cont;
	}
	
	public boolean status_geral_pedido(Long id_pedido) throws SQLException {
		 
		String sql =   
		"SELECT "+
		" i.status_entrega AS status_entregaItem "+				
		" FROM pedido pe INNER JOIN item_pedido i "+
		" ON pe.id = i.id_pedido " +
		" WHERE pe.id = "+id_pedido+" ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
	 
		int cont=0;
		while (resultSet.next()) {		
			if(resultSet.getString("status_entregaItem").equals("Produto entregue")) {
				cont++;		
			} 
			 
		}
		int qtd = quantidadeItens(id_pedido);
		if(qtd==cont) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	public double total_preco_pedido(Long id_pedido) throws SQLException {
		 
		String sql =   
		"SELECT "+
		" i.preco_unitario AS preco_unitarioItem, "+	
		" i.unidades AS unidadesItem "+	
		" FROM pedido pe INNER JOIN item_pedido i "+
		" ON pe.id = i.id_pedido " +
		" WHERE pe.id = "+id_pedido+" ";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
	    int unidades=0;
	    double preco_unitario=0;
		int cont=0;
		double total=0;
		while (resultSet.next()) {		
			preco_unitario = resultSet.getDouble("preco_unitarioItem");
			unidades = resultSet.getInt("unidadesItem"); 
			total = total + preco_unitario*unidades;
			 
		}
	 
			return total;
	 
		
	}
	
}	