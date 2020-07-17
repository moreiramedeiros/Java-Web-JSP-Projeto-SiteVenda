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
import bean.BeanProduto;
import bean.BeanUsuario;
import dao.DaoPedido;
import dao.DaoProduto;
import dao.DaoUsuario;

@WebServlet("/ServletPesquisa")
public class ServletPesquisa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletPesquisa() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String descricao = request.getParameter("descricao");
			
			 
			
			if (acao != null) {
                 if(acao.equals("pesquisarCategoria")) {
 					DaoProduto daoProduto = new DaoProduto();
 					List<BeanProduto> lista;
 					lista = daoProduto.pesquisarCategoria(descricao); 					 
 					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
 					request.setAttribute("produtos", lista);
 					request.setAttribute("msg", "Categoria :" + descricao);
 					rd.forward(request, response);
                 } else if(acao.equals("pesquisarCategoriaRodape")) {
  					 					
                	 
                	 String[] Categorias = { "Alimento","Automotivo","Brinquedo","Celular","Eletrodoméstico",  
  			                  "Game","Informática","Livro","Mídia","Móveis","Roupa","Sem categoria"};	
                	 
                	 
                	  
  					
  					descricao = Categorias[Integer.parseInt(descricao)-1];
  					
  					DaoProduto daoProduto = new DaoProduto();
  					List<BeanProduto> lista;
  					lista = daoProduto.pesquisarCategoria(descricao); 					 
  					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
  					request.setAttribute("produtos", lista);
  					request.setAttribute("msg", "Categoria :" + descricao);
  					rd.forward(request, response);	 
                 } 
                	 
			} else {
				/* Redirecionar para a página home */

				DaoProduto daoProduto = new DaoProduto();
				List<BeanProduto> lista;

				lista = daoProduto.listar();

				RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
				rd.forward(request, response);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String tipo_pesquisa = request.getParameter("tipo_pesquisa");
			String descricao = request.getParameter("descricao");

			if (tipo_pesquisa != null) {

				/* Pesqusar Usuários na pagina de administrador */
				if (tipo_pesquisa.equalsIgnoreCase("pesquisa_nome_usuario")) {
					// Listar
					DaoUsuario daoUsuario = new DaoUsuario();
					List<BeanUsuario> lista;
					lista = daoUsuario.pesquisar(descricao.toLowerCase());
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					request.setAttribute("usuarios", lista);
					rd.forward(request, response);
				} else /* Pesqusar produtos na pagina de administrador */
				if (tipo_pesquisa.equalsIgnoreCase("pesquisa_nome_produto_admin")) {
					DaoProduto daoProduto = new DaoProduto();
					List<BeanProduto> lista;
					lista = daoProduto.pesquisar(descricao.toLowerCase());
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroProduto.jsp");
					request.setAttribute("produtos", lista);
					rd.forward(request, response);

				} else /* Pesqusar produtos na pagina home pelo cliente */
				if (tipo_pesquisa.equalsIgnoreCase("pesquisa_nome_produto_usuario")) {
					DaoProduto daoProduto = new DaoProduto();
					List<BeanProduto> lista;
					lista = daoProduto.pesquisarHome(descricao.toLowerCase());
					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
					request.setAttribute("produtos", lista);
					request.setAttribute("msg", "Pesquisa :" + descricao);
					rd.forward(request, response);

				} else /* Pesqusar produtos por categoria na pagina home pelo cliente */
				if (tipo_pesquisa.equalsIgnoreCase("pesquisa_nome_produto_categoria")) {
					DaoProduto daoProduto = new DaoProduto();
					List<BeanProduto> lista;
					if (descricao.equals("Todas categorias")) {
						lista = daoProduto.listarHome();
					} else {
						lista = daoProduto.pesquisarCategoria(descricao);
					}

					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
					request.setAttribute("produtos", lista);
					request.setAttribute("msg", "Categoria :" + descricao);
					rd.forward(request, response);
				} else if(tipo_pesquisa.equals("pesquisa_nome_usuario_pedido")){
         			DaoPedido daoPedido = new DaoPedido();
        			List<BeanJoin> lista;       		 
     			    lista = daoPedido.pesquisarUsuarioPedido(descricao.toLowerCase());
    				RequestDispatcher rd = request.getRequestDispatcher("adminPedido.jsp");
    				request.setAttribute("pedidos", lista);
    				rd.forward(request, response);	                	 
                 }

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
