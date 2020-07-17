package bean;

public class BeanItemPedido {
	private Long id_produto;
	private Long id_pedido;
	private int unidades;
	private String status_entrega;
	double preco_unitario;

	public Long getId_produto() {
		return id_produto;
	}

	public void setId_produto(Long id_produto) {
		this.id_produto = id_produto;
	}

	public Long getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(Long id_pedido) {
		this.id_pedido = id_pedido;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public String getStatus_entrega() {
		return status_entrega;
	}

	public void setStatus_entrega(String status_entrega) {
		this.status_entrega = status_entrega;
	}

	public double getPreco_unitario() {
		return preco_unitario;
	}

	public void setPreco_unitario(double preco_unitario) {
		this.preco_unitario = preco_unitario;
	}
}
