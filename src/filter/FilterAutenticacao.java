package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



//@WebFilter(urlPatterns= {"/*"})     //Com esta declaração, não precisa declarar o filtro no web.xml
                                    // {"/*"} indica que o filtro deve interceptar todas as páginas
public class FilterAutenticacao implements Filter{

	//Faz alguma coisa quando a aplicação é derrubada
	@Override
	public void destroy() {
		
	}
	
	//Intercepta todas nossas requisições
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {	
		
			HttpServletRequest  req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
	/*		
			//Obter dados salvo na sessão
			UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
						
			//Obter a url acessado no browser pelo usuário
			String urlParaAutenticar = req.getServletPath();
			System.out.print(urlParaAutenticar);

			//Se o usuário é null ou página acessado diferente da página de autetificacao
			if(userLogado == null && !urlParaAutenticar.equals("/ServletAutenticacao")) {
				//Redireciona qualquer página para a página de autentificação  
				//Atribue o valor do url para o atributo url
				RequestDispatcher view = request.getRequestDispatcher("/autenticar.jsp?url="+urlParaAutenticar);
				view.forward(request, response);
				return;
			}else {
				//Permite exibir as páginas caso esteja logado
			    chain.doFilter(request, response);  
			}
			*/
						   
	}

	// Executa alguma coisa quando a aplicação é iniciada
	@Override
	public void init(FilterConfig arg0) throws  ServletException {
		 		
	}
}
