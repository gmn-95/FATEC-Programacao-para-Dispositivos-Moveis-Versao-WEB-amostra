package br.com.agenda_web.controller.usuario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoUsuario;

/**
 * Servlet implementation class UsuarioServletListar
 */
@WebServlet(name = "/UsuarioServletListar", urlPatterns =  {"/UsuarioServletListar", "/ListarUsuarios"})
public class UsuarioServletListar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServletListar() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String redirecionar = "";
        String mensagem = "";
        
        try {
	        
	        BeanUsuario usuario = new BeanUsuario(super.getUserLogado(request));
	        
	        if(usuario.getId_usuario() != 0 && usuario.getId_usuario() != null) {
	        	
	        	BeanUsuario usuarioLogado = new BeanUsuario(usuario.getId_usuario());
	        	DaoUsuario daoUsuarioLogado = new DaoUsuario();
	        	usuarioLogado = daoUsuarioLogado.buscarUsuario(usuarioLogado);
		        
		        String tipoPesquisa = request.getParameter("tipoPesquisa");
		        String valor = request.getParameter("valorProcurado");
		        
		        if(tipoPesquisa == null) {
		        	tipoPesquisa = "Todos";
		        }
		        else if(tipoPesquisa.equals("Login")) {
		        	usuario = new BeanUsuario();
		        	usuario.setLogin(valor);
		        }
		        else if(tipoPesquisa.equals("Nome")) {
		        	usuario = new BeanUsuario(valor);
		        }
		        
		    	List<BeanUsuario> usuarios = new ArrayList<BeanUsuario>();
		        
		        DaoUsuario daoUsuario = new DaoUsuario();
		        usuarios = daoUsuario.listarUsuarios(usuario, tipoPesquisa);
		
		        if(usuarios != null) {
		        	redirecionar = "/principal/principal.jsp?acao=listarUsuarios";
		        	request.removeAttribute("usuarios");
		        	request.setAttribute("usuarios", usuarios);
		        	
		        	request.removeAttribute("usuarioLogado");
		        	request.setAttribute("usuarioLogado", usuarioLogado);
		        	
		        	mensagem = (String) request.getAttribute("mensagem");
		        }
		        else {
		        	redirecionar = "/principal/principal.jsp?acao=listarUsuarios";
					mensagem = "Erro ao listar";
		        }
		        
	        }
	        else {
	        	redirecionar = "index.jsp";
	        	mensagem = "Por favor, realize o login!";
	        }
	        
	        request.removeAttribute("mensagem");
			request.setAttribute("mensagem", mensagem);
	        
	        RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
	        
        }catch (Exception e) {
        	e.printStackTrace();
			
			redirecionar = "/principal/principal.jsp?acao=erro";
			
			RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
        }
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
