package br.com.agenda_web.filter;

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

import br.com.agenda_web.model.bean.BeanUsuario;

/**
 * Servlet Filter implementation class FilterAutenticacao
 */
@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
			
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();
			
			BeanUsuario usuarioLogado = (BeanUsuario) session.getAttribute("usuario");
			
			String urlParaAutenticar = req.getServletPath(); /*URL que está sendo acessada*/
			
			/* Validar se está logado, se não, redireciona para a tela de login*/
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/UsuarioServletValidar")) {
				RequestDispatcher redireciona = request.getRequestDispatcher("../index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Por favor, realize o login!");
				redireciona.forward(request, response); /*comando de redirecionamento*/
				
				return; /*Para a execução e redireciona para o login*/
			}
			else {
				chain.doFilter(request, response);
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
			
			String redirecionar = "/principal/principal.jsp?acao=erro";
			
			RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
