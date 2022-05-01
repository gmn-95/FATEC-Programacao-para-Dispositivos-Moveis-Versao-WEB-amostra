package br.com.agenda_web.controller.usuario;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoUsuario;

@WebServlet(name = "/UsuarioServletValidar", urlPatterns = {"/UsuarioServletValidar", "/-u"})
public class UsuarioServletValidar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServletValidar() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("sair")) {
			request.getSession().invalidate(); //invalida a sessão
			RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
			redirecionar.forward(request, response);
		}
		else {
			doPost(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        String redirecionar = "";
        String mensagem;
        
        BeanUsuario usuario = new BeanUsuario(login, senha);
        DaoUsuario daoUsuario = new DaoUsuario();
        
        usuario = daoUsuario.validarLogin(usuario);
        
        
        if(usuario != null) {
        	redirecionar = "principal/principal.jsp?acao=home";
        	request.getSession().setAttribute("usuario", usuario);
        	
        }
        else {
        	redirecionar = "index.jsp";
        	mensagem = "Login ou senha inválidos!";
        	
        	request.removeAttribute("userLogado");
        	request.setAttribute("userLogado", usuario);
        	
        	request.removeAttribute("mensagem");
        	request.setAttribute("mensagem", mensagem);
        	
        }
        
        RequestDispatcher view = request.getRequestDispatcher(redirecionar);
        view.forward(request, response);
	}

}
