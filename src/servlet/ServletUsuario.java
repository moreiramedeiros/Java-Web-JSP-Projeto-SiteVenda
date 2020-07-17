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

import bean.BeanUsuario;
import dao.DaoUsuario;

@WebServlet("/ServletUsuario")
public class ServletUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ServletUsuario() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		DaoUsuario daoUsuario = new DaoUsuario();
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");

		try {

			if (acao != null) {

				if (acao.equalsIgnoreCase("excluir")) {
					daoUsuario.deletar(Long.parseLong(id));
					request.setAttribute("msgSucesso", "Excluido com sucesso!");
				} else if (acao.equalsIgnoreCase("editar")) {
					BeanUsuario beanUsuario = daoUsuario.consultar(Long.parseLong(id));
					// System.out.print("cpf:"+beanUsuario.getCpf());
					request.setAttribute("user", beanUsuario);

				}

				List<BeanUsuario> lista = daoUsuario.listar();
				RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
				request.setAttribute("usuarios", lista);
				rd.forward(request, response);

			} else {
				List<BeanUsuario> lista = daoUsuario.listar();
				RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
				request.setAttribute("usuarios", lista);
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
			DaoUsuario daoUsuario = new DaoUsuario();

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String nome = request.getParameter("nome");
			String cpf = request.getParameter("cpf");
			String telefone = request.getParameter("telefone");
			String celular = request.getParameter("celular");
			String cep = request.getParameter("cep");
			String rua = request.getParameter("rua");
			String numero = request.getParameter("numero");
			String bairro = request.getParameter("bairro");
			String cidade = request.getParameter("cidade");
			String estado = request.getParameter("estado");
			String sexo = request.getParameter("sexo");
			String email = request.getParameter("email");
			String nivel_acesso = request.getParameter("nivel_acesso");
			String pagina = request.getParameter("pagina");

			BeanUsuario beanUsuario = new BeanUsuario();
			beanUsuario.setLogin(login);
			beanUsuario.setSenha(senha);
			beanUsuario.setNome(nome);
			beanUsuario.setCpf(cpf);
			beanUsuario.setTelefone(telefone);
			beanUsuario.setCelular(celular);
			beanUsuario.setCep(cep);
			beanUsuario.setRua(rua);
			beanUsuario.setNumero(numero);
			beanUsuario.setBairro(bairro);
			beanUsuario.setCidade(cidade);
			beanUsuario.setEstado(estado);
			beanUsuario.setEmail(email);
			beanUsuario.setNivel_acesso(nivel_acesso);

			if (sexo != null) {
				beanUsuario.setSexo(sexo);
			} else {
				beanUsuario.setSexo("indefinido");
			}

			beanUsuario.setId((id == null || id.isEmpty()) ? null : Long.parseLong(id));

			/* Validar campos obrigatórios */ // msgNaoSucesso

			if (login.isEmpty() || login == null) {
				if (!response.isCommitted()) {
					List<BeanUsuario> lista = daoUsuario.listar();
					request.setAttribute("msgNaoSucesso", "O campo login está vazio!");
					request.setAttribute("user", beanUsuario);
					request.setAttribute("usuarios", lista);
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					rd.forward(request, response);
				}
				return;

			} else if (senha.isEmpty() || senha == null) {
				if (!response.isCommitted()) {
					List<BeanUsuario> lista = daoUsuario.listar();
					request.setAttribute("msgNaoSucesso", "O campo senha está vazio!");
					request.setAttribute("user", beanUsuario);
					request.setAttribute("usuarios", lista);
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					rd.forward(request, response);
				}
				return;
			} else if (nome.isEmpty() || nome == null) {
				if (!response.isCommitted()) {
					List<BeanUsuario> lista = daoUsuario.listar();
					request.setAttribute("msgNaoSucesso", "O campo nome está vazio!");
					request.setAttribute("user", beanUsuario);
					request.setAttribute("usuarios", lista);
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					rd.forward(request, response);
				}
				return;
			} else if (email.isEmpty() || email == null) {
				if (!response.isCommitted()) {
					List<BeanUsuario> lista = daoUsuario.listar();
					request.setAttribute("msgNaoSucesso", "O campo email está vazio!");
					request.setAttribute("user", beanUsuario);
					request.setAttribute("usuarios", lista);
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					rd.forward(request, response);
				}
				return;
			} else if (cpf.isEmpty() || cpf == null) {
				if (!response.isCommitted()) {
					List<BeanUsuario> lista = daoUsuario.listar();
					request.setAttribute("msgNaoSucesso", "O campo CPF está vazio!");
					request.setAttribute("user", beanUsuario);
					request.setAttribute("usuarios", lista);
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					rd.forward(request, response);
				}
				return;
			}

			
			
			/* Validar campos duplicados para salvar */ // msgNaoSucesso
		
			String url = "";
			if (pagina.equalsIgnoreCase("autocadastro")) {
				  url = "autoCadastroUsuario.jsp";			 
			} else if (pagina.equalsIgnoreCase("perfilusuario")) {
				  url = "perfilUsuario.jsp";
			} else if (pagina.equalsIgnoreCase("admin")) {
				  url = "adminCadastroUsuario.jsp";
			}
			
			
			if (id == null || id.isEmpty()) {
				if (daoUsuario.validarLoginDuplicado(login)) {
					if (!response.isCommitted()) {
						List<BeanUsuario> lista = daoUsuario.listar();
						request.setAttribute("msgNaoSucesso", "Este login já existe!");
						request.setAttribute("user", beanUsuario);
						request.setAttribute("usuarios", lista);
						RequestDispatcher rd = request.getRequestDispatcher(url);
						rd.forward(request, response);
					}
					return;
				} else if (daoUsuario.validarEmailDuplicado(email)) {
					if (!response.isCommitted()) {
						List<BeanUsuario> lista = daoUsuario.listar();
						request.setAttribute("msgNaoSucesso", "Este email já existe!");
						request.setAttribute("user", beanUsuario);
						request.setAttribute("usuarios", lista);
						RequestDispatcher rd = request.getRequestDispatcher(url);
						rd.forward(request, response);
					}
					return;
				}
			}else {
				/* Validar campos duplicados para Editar */
				if (daoUsuario.validarLoginDuplicado(login,id)) {
					if (!response.isCommitted()) {
						List<BeanUsuario> lista = daoUsuario.listar();
						request.setAttribute("msgNaoSucesso", "Este login já existe!");
						request.setAttribute("user", beanUsuario);
						request.setAttribute("usuarios", lista);
						RequestDispatcher rd = request.getRequestDispatcher(url);
						rd.forward(request, response);
					}
					return;
				} else if (daoUsuario.validarEmailDuplicado(email,id)) {
					if (!response.isCommitted()) {
						List<BeanUsuario> lista = daoUsuario.listar();
						request.setAttribute("msgNaoSucesso", "Este email já existe!");
						request.setAttribute("user", beanUsuario);
						request.setAttribute("usuarios", lista);
						RequestDispatcher rd = request.getRequestDispatcher(url);
						rd.forward(request, response);
					}
					return;
				}
			}

			
			/* Salvar Usuário */
			if (id == null || id.isEmpty()) {
				daoUsuario.salvar(beanUsuario);
				RequestDispatcher rd = null;
				/* Cadastro feito da página de auto-cadastro */
				if (pagina.equalsIgnoreCase("autocadastro")) {
					rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("login", login);
					request.setAttribute("senha", senha);
					request.setAttribute("msgSucesso", "Cadastro feito com sucesso!");
					rd.forward(request, response);

				/* Cadastro feito na página de administrador */
				} else if (pagina.equalsIgnoreCase("admin")) {
					List<BeanUsuario> lista;

					lista = daoUsuario.listar();
					rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					request.setAttribute("usuarios", lista);
					request.setAttribute("msgSucesso", "Cadastro feito com sucesso!");
					rd.forward(request, response);
				}

			} else {
				/* Editar Usuário */
				// beanUsuario.setId(Long.parseLong(id));
				daoUsuario.atualizar(beanUsuario);

				/* Editar usuário na página de perfil do usuário */
				if (pagina.equalsIgnoreCase("perfilusuario")) {

					RequestDispatcher rd = request.getRequestDispatcher("perfilUsuario.jsp");
					request.setAttribute("msgSucesso", "Editado com sucesso!");
					request.setAttribute("user", beanUsuario);
					rd.forward(request, response);

					/* Editar usuário na página do administrador */
				} else if (pagina.equalsIgnoreCase("admin")) {
					// Listar
					List<BeanUsuario> lista;
					lista = daoUsuario.listar();
					RequestDispatcher rd = request.getRequestDispatcher("adminCadastroUsuario.jsp");
					request.setAttribute("usuarios", lista);
					request.setAttribute("msgSucesso", "Editado com sucesso!");
					rd.forward(request, response);
				}

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
