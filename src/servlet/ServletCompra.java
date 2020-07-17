package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BeanItemPedido;
import bean.BeanPedido;
import bean.BeanProduto;
import bean.BeanUsuario;
import dao.DaoPedido;
import dao.DaoProduto;

@WebServlet("/ServletCompra")
public class ServletCompra extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCompra() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			/* Obter produtos do carrinho */
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			List<BeanProduto> carrinho = (List<BeanProduto>) session.getAttribute("carrinho");

			if (carrinho == null) {
				req = (HttpServletRequest) request;
				session = req.getSession();
				carrinho = new ArrayList<BeanProduto>();
			}

			/* Obter dados do usuário */

			BeanUsuario usuario = (BeanUsuario) session.getAttribute("usuario");

			if (usuario != null) {

				/* Resumo da compra */
				String pagamento = request.getParameter("pagamento");
				String parcelamento = request.getParameter("parcelamento");

				/* SALVANDO PEDIDO */

				DaoPedido daoPedido = new DaoPedido();
				BeanPedido beanPedido = new BeanPedido();
				beanPedido.setId_usuario(usuario.getId());
				beanPedido.setPagamento(pagamento);
				beanPedido.setStatus_pagamento("Aguardando pagamento");
				beanPedido.setParcelamento(
						(parcelamento == null || parcelamento.isEmpty()) ? 0 : Integer.parseInt(parcelamento));
				daoPedido.salvarPedido(beanPedido);

				/* SALVANDO ITEM PEDIDO */
				DaoProduto daoProduto = new DaoProduto();
				BeanItemPedido beanItemPedido = new BeanItemPedido();
				Long id_pedido = daoPedido.ultimoPedido();
				for (int i = 0; i < carrinho.size(); i++) {
					beanItemPedido.setId_pedido(id_pedido);
					beanItemPedido.setId_produto(carrinho.get(i).getId());
					beanItemPedido.setPreco_unitario(carrinho.get(i).getPreco());
					beanItemPedido.setStatus_entrega("Produto ainda não entregue");
					beanItemPedido.setUnidades(carrinho.get(i).getQuantidadeConsumida());
					daoPedido.salvarItemPedido(beanItemPedido);
					/* ATUALIZAR ESTOQUE */
					daoProduto.decrementarQuantidadeEstoque(carrinho.get(i).getId(), carrinho.get(i).getQuantidadeConsumida());					
				}

				
				carrinho.clear();

				/* Listar */
				RequestDispatcher rd = request.getRequestDispatcher("perfilUsuario.jsp");
				request.setAttribute("msgSucesso", "Parabens! Pedito finalizado com sucesso!");
				request.setAttribute("join", daoPedido.listarJoin(usuario.getId()));

				// request.setAttribute("produtos", carrinho);
				// request.setAttribute("totalunidades", totalunidades);
				// request.setAttribute("totalpreco", totalpreco);
				rd.forward(request, response);

			} else {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
