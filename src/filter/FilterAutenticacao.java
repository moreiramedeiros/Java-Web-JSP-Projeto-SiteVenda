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



//@WebFilter(urlPatterns= {"/*"})     //Com esta declara��o, n�o precisa declarar o filtro no web.xml
                                    // {"/*"} indica que o filtro deve interceptar todas as p�ginas
public class FilterAutenticacao implements Filter{

	//Faz alguma coisa quando a aplica��o � derrubada
	@Override
	public void destroy() {
		
	}
	
	//Intercepta todas nossas requisi��es
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {	
		
			HttpServletRequest  req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
	/*		
			//Obter dados salvo na sess�o
			UserLogado userLogado = (UserLogado) session.getAttribute("usuario");
						
			//Obter a url acessado no browser pelo usu�rio
			String urlParaAutenticar = req.getServletPath();
			System.out.print(urlParaAutenticar);

			//Se o usu�rio � null ou p�gina acessado diferente da p�gina de autetificacao
			if(userLogado == null && !urlParaAutenticar.equals("/ServletAutenticacao")) {
				//Redireciona qualquer p�gina para a p�gina de autentifica��o  
				//Atribue o valor do url para o atributo url
				RequestDispatcher view = request.getRequestDispatcher("/autenticar.jsp?url="+urlParaAutenticar);
				view.forward(request, response);
				return;
			}else {
				//Permite exibir as p�ginas caso esteja logado
			    chain.doFilter(request, response);  
			}
			*/
						   
	}

	// Executa alguma coisa quando a aplica��o � iniciada
	@Override
	public void init(FilterConfig arg0) throws  ServletException {
		 		
	}
}
