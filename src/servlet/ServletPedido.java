package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BeanJoin;
import bean.BeanPedido;
import bean.BeanProduto;
import dao.DaoPedido;
import dao.DaoProduto;

@WebServlet("/ServletPedido")
public class ServletPedido extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletPedido() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			DaoPedido daoPedido = new DaoPedido();
			List<BeanJoin> lista;

			String acao = request.getParameter("acao");
			String id_pedido = request.getParameter("id_pedido");
			String id_usuario = request.getParameter("id_usuario");
			String nome_usuario = request.getParameter("nome_usuario");
			if (acao != null) {
				if (acao.equalsIgnoreCase("listaritenspedido")) {
					lista = daoPedido.listarJoin(id_usuario, id_pedido);
					RequestDispatcher rd = request.getRequestDispatcher("adminItem.jsp");
					request.setAttribute("itens", lista);
					request.setAttribute("nome_usuario", nome_usuario);
					request.setAttribute("id_pedido", id_pedido);
					request.setAttribute("id_usuario", id_usuario);
					rd.forward(request, response);
				}
			} else {

				lista = daoPedido.listarPedidos();
				RequestDispatcher rd = request.getRequestDispatcher("adminPedido.jsp");
				request.setAttribute("pedidos", lista);
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao.equals("atualizar_status_pagamento")) {

			String id_pedido = request.getParameter("id_pedido");
			String status_pagamento = request.getParameter("status_pagamento");

			// System.out.print(id_pedido+" "+status_pagamento+"\n");
			DaoPedido daoPedido = new DaoPedido();
			daoPedido.atualizarStatus_pagamento(id_pedido, status_pagamento);
		} else if(acao.equals("atualizar_data_entrega")) {
			String id_item = request.getParameter("id_item");
			String data_entrega = request.getParameter("data_entrega");
			DaoPedido daoPedido = new DaoPedido();
			daoPedido.atualizar_data_entrega(id_item, data_entrega);
			
			 response.getWriter().write(id_item + "  " + data_entrega);
		} else if(acao.equals("atualizar_status_entrega")) {
			 
		 	String id_item = request.getParameter("id_item");
		 	String status_entrega = request.getParameter("status_entrega");	
		 	DaoPedido daoPedido = new DaoPedido();
		 	daoPedido.atualizarStatus_entrega(id_item, status_entrega);
		 	 response.getWriter().write(id_item + "  " + status_entrega);
		}
	}

}
