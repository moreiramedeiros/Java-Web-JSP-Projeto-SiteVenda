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
import javax.servlet.http.HttpSession;

import bean.BeanProduto;
import bean.BeanUsuario;
import dao.DaoProduto;
import dao.DaoUsuario;

@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletLogin() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			DaoUsuario daoUsuario = new DaoUsuario();

		
		 
		 
			if (daoUsuario.validarLogin(login, senha)) {
				
               //Salvar usuário na sessão		    	 
		    	   HttpServletRequest req = (HttpServletRequest) request;
		    	   HttpSession session = req.getSession();
		    	   BeanUsuario beanUsuario = daoUsuario.consultar(login, senha);
		    	   session.setAttribute("usuario", beanUsuario);
		    	//   BeanUsuario beanUsuario2 = (BeanUsuario) session.getAttribute("usuario"); 
		    	//   System.out.print(beanUsuario2.getNivel_acesso());
			 	    
		    	   /*Listar produtos */
					DaoProduto daoProduto = new DaoProduto();
					List<BeanProduto> lista;
					lista = daoProduto.listarHome();

					RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
					request.setAttribute("produtos", lista);
					rd.forward(request, response);
		    	    
			} else {
				RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
				request.setAttribute("msgNaoSucesso", "Senha ou Usuários incorretos!");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
