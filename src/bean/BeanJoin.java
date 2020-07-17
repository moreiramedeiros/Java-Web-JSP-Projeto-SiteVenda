package bean;

import java.sql.SQLException;

import dao.DaoPedido;

public class BeanJoin {
	private Long id_pedido;
	private Long id_item;
	private Long id_usuario; 
	private String nomeItem;
	private String nome_usuario;
	private String imagem;
	private String pagamento;
	private int parcelamento;
	private String status_pagamento;
	private String data_pedido;
	private String status_entrega;
	private String data_entrega;
	private double preco_unitario;
	private int unidades;
	
	
	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public Long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Long id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public String getNome() {
		return nomeItem;
	}

	public void setNome(String nome) {
		this.nomeItem = nome;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public String getPagamento() {
		return pagamento;
	}

	public void setPagamento(String pagamento) {
		this.pagamento = pagamento;
	}

	public String getParcelamento() {
		//
		if(parcelamento==0) {
			return "Nenhum";
		}else {
			return parcelamento+"";
		}
		
	}

	public void setParcelamento(int parcelamento) {
		this.parcelamento = parcelamento;
	}

	public String getStatus_pagamento() {
		return status_pagamento;
	}

	public void setStatus_pagamento(String status_pagamento) {
		this.status_pagamento = status_pagamento;
	}

	public String getData_pedido() {
		//2020-07-14 18:39:06.056859-03
		String d=this.data_pedido;
		String[] data = d.split(" ");
		data = data[0].split("-");
		return data[2]+"/"+data[1]+"/"+data[0];
	}
	
 

	public void setData_pedido(String data_pedido) {
		this.data_pedido = data_pedido;
	}

	public String getStatus_entrega() {
		return status_entrega;
	}

	public void setStatus_entrega(String status_entrega) {
		this.status_entrega = status_entrega;
	}

	public String getData_entrega() {
		//2020-07-14 18:39:06.056859-03
		if(data_entrega == null || data_entrega.isEmpty() ) {
			return "--";
		}else {
			String d=this.data_entrega;
			String[] data = d.split(" ");
			data = data[0].split("-");
			return data[2]+"/"+data[1]+"/"+data[0];
		}
	}

	public String getData_entrega_bd() {
		//2020-07-14 18:39:06.056859-03
		if(data_entrega == null || data_entrega.isEmpty() ) {
			return "";
		}else {
			String d=this.data_entrega;
			String[] data = d.split(" ");
			return data[0];
		}
	}

	
	
	public void setData_entrega(String data_estrega) {
		this.data_entrega = data_estrega;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}


	public Long getId_item() {
		return id_item;
	}

	public void setId_item(Long id_item) {
		this.id_item = id_item;
	}
	
	
	public int getQtd_itens() {
		int qtd = 0;
		
		try {
			DaoPedido daoPedido = new DaoPedido();
			  qtd = daoPedido.quantidadeItens(id_pedido);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return qtd;	 
	}

	public String getStatus_geral_pedido() {
	 
		
		try {
			DaoPedido daoPedido = new DaoPedido();
			  boolean sts = daoPedido.status_geral_pedido(id_pedido);
			  if(sts) {
				  return "Fechado";
			  }else {
				  return "Aberto";
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;	 
	}
	
	
	public double getTotal_preco_pedido() {
		double total=0;
		
	 	try {
			DaoPedido daoPedido = new DaoPedido();
			 total = daoPedido.total_preco_pedido(id_pedido);
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return total;	 
	}
	
}
