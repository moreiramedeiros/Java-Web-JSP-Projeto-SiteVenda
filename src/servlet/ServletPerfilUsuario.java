package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.BeanUsuario;
import dao.DaoPedido;
import dao.DaoUsuario;

@WebServlet("/ServletPerfilUsuario")
public class ServletPerfilUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletPerfilUsuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			DaoUsuario daoUsuario = new DaoUsuario();
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			BeanUsuario beanUsuario = (BeanUsuario) session.getAttribute("usuario");
			DaoPedido daoPedido = new DaoPedido();
			
			
			if (acao != null) {
			   if (acao.equals("excluir")) { 
					daoUsuario.deletar(beanUsuario.getId());  
					session .invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("msgSucesso","Conta de usuário excluida com sucesso!");
					rd.forward(request, response);
				} else if (acao.equals("sair")) {
					session.invalidate();
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}else {
				if (beanUsuario != null) {
				 /* Sempre atualizar usuario caso haja alteração do administrador*/
				    beanUsuario = daoUsuario.consultar(beanUsuario.getId());					
					RequestDispatcher rd = request.getRequestDispatcher("perfilUsuario.jsp");
					request.setAttribute("user", beanUsuario);
					request.setAttribute("join", daoPedido.listarJoin(beanUsuario.getId()));
					rd.forward(request, response);
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					rd.forward(request, response);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
