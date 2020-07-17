package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import bean.BeanProduto;
import bean.BeanUsuario;
import dao.DaoProduto;

@WebServlet("/ServletCarrinho")
public class ServletCarrinho extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletCarrinho() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
 
		if (acao != null) {
			if (acao.equalsIgnoreCase("remover")) {
				HttpServletRequest req = (HttpServletRequest) request;
				HttpSession session = req.getSession();
				List<BeanProduto> carrinho = (List<BeanProduto>) session.getAttribute("carrinho");

				/* Remover do carrinho */
				BeanProduto produto;
				Long id_produto = Long.parseLong(id);
				for (int i = 0; i < carrinho.size(); i++) {
					produto = carrinho.get(i);
					if (produto.getId() == id_produto) {
						carrinho.remove(i);
						break;
					}
				}

				/* Calcular preço total */

				double totalpreco = 0;
				double subtotal = 0;
				for (int i = 0; i < carrinho.size(); i++) {
					produto = carrinho.get(i);
					subtotal = produto.getPreco() * produto.getQuantidadeConsumida();
					totalpreco = totalpreco + subtotal;
				}

				/* Calcular unidades total */
				int totalunidades = 0;
				for (int i = 0; i < carrinho.size(); i++) {
					produto = carrinho.get(i);
					totalunidades = totalunidades + produto.getQuantidadeConsumida();
				}

				/* Listar */
				if (carrinho.size() > 0) {	 
					/*Ir para a página de carrinho*/
					RequestDispatcher rd = request.getRequestDispatcher("carrinho.jsp");
					request.setAttribute("produtos", carrinho);
					request.setAttribute("totalunidades", totalunidades);
					request.setAttribute("totalpreco", totalpreco);
					rd.forward(request, response);
				} else {
					/*Voltar para a página Home caso o carrinho esteja vazio*/
					try {
						DaoProduto daoProduto = new DaoProduto();
						List<BeanProduto> lista;

						lista = daoProduto.listarHome();

						RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
						request.setAttribute("produtos", lista);
						rd.forward(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		} else {
			/* Lista produtos do carrinho na página de carrinho */
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			List<BeanProduto> carrinho = (List<BeanProduto>) session.getAttribute("carrinho");

			if (carrinho != null) {

				/* Calcular preço total */
				BeanProduto produto;
				double totalpreco = 0;
				double subtotal = 0;
				for (int i = 0; i < carrinho.size(); i++) {
					produto = carrinho.get(i);
					subtotal = produto.getPreco() * produto.getQuantidadeConsumida();
					totalpreco = totalpreco + subtotal;
				}

				/* Calcular unidades total */
				int totalunidades = 0;
				for (int i = 0; i < carrinho.size(); i++) {
					produto = carrinho.get(i);
					totalunidades = totalunidades + produto.getQuantidadeConsumida();
				}

				/* Listar */
				RequestDispatcher rd = request.getRequestDispatcher("carrinho.jsp");
				request.setAttribute("produtos", carrinho);
				request.setAttribute("totalunidades", totalunidades);
				request.setAttribute("totalpreco", totalpreco);
				rd.forward(request, response);
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("carrinho.jsp");
				rd.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/* Adiciona produtos no carrinho */

		// Recupera ID do produto e unidades
		try {
			String id = request.getParameter("id");
			String unidades = request.getParameter("unidades");
			String acao = request.getParameter("acao");

			if (acao != null) {
				if (acao.equals("addCarrrinho")) {

					DaoProduto daoProduto = new DaoProduto();
					BeanProduto beanProduto = daoProduto.consultar(Long.parseLong(id));
					beanProduto.setQuantidadeConsumida(Integer.parseInt(unidades));
					/* Criar carrinho */

					HttpServletRequest req = (HttpServletRequest) request;
					HttpSession session = req.getSession();
					List<BeanProduto> carrinho = (List<BeanProduto>) session.getAttribute("carrinho");

					if (carrinho == null) {
						req = (HttpServletRequest) request;
						session = req.getSession();
						carrinho = new ArrayList<BeanProduto>();
					}

					boolean adicionarNovo = true;
					String mensagem = "";
					/* Atualizar produto se ele já existe no carrinho */
					int qtd = 0;
					for (int i = 0; i < carrinho.size(); i++) {
						if (beanProduto.getId() == carrinho.get(i).getId()) {

							qtd = beanProduto.getQuantidadeConsumida() + carrinho.get(i).getQuantidadeConsumida();

							if (qtd <= beanProduto.getQuantidade()) {
								carrinho.get(i).setQuantidadeConsumida(qtd);
							} else {
								carrinho.get(i).setQuantidadeConsumida(beanProduto.getQuantidade());
							}

							adicionarNovo = false;
						}
					}

					/* Adicionar oa carrinho se produto está disponível */
					if (beanProduto.getQuantidade() > 0) {

						if (adicionarNovo) {
							/* Adicionar produto ao carrinho */
							carrinho.add(beanProduto);
							session.setAttribute("carrinho", carrinho);
						}

						response.getWriter().write(carrinho.size() + "; Adicionado ao carrinho com sucesso!");

					} else {
						response.getWriter().write(carrinho.size() + "; Produto nao disponivel!");
					}
				}
			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
