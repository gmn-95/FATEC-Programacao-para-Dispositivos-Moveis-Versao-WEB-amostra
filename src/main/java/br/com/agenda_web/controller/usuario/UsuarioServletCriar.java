package br.com.agenda_web.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoPessoa;
import br.com.agenda_web.model.dao.DaoUsuario;

/**
 * Servlet implementation class UsuarioServletCriar
 */
@WebServlet(name = "/UsuarioServletCriar", urlPatterns = {"/UsuarioServletCriar", "/CriarUsuario"})
public class UsuarioServletCriar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     TODO: handle exception* @see HttpServlet#HttpServlet()
     */
    public UsuarioServletCriar() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String redirecionar = "";
        String mensagem = "";
        
		try {
			BeanUsuario usuario = new BeanUsuario(super.getUserLogado(request));
			
			if(usuario.getId_usuario() != 0 && usuario.getId_usuario() != null) {
				
				String nome = request.getParameter("nome");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				Boolean criarUsuario = Boolean.parseBoolean(request.getParameter("criarUsuario"));
				Boolean editarUsuario = Boolean.parseBoolean(request.getParameter("editarUsuario"));
				Boolean excluirUsuario = Boolean.parseBoolean(request.getParameter("excluirUsuario"));
				
				usuario.setNome(nome);
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setCriar_novo_usuario(criarUsuario);
				usuario.setEditar_usuario(editarUsuario);
				usuario.setExcluir_usuario(excluirUsuario);
				
				DaoPessoa daoPessoa = new DaoPessoa();
				usuario = daoPessoa.criarPessoa(usuario);
				
				DaoUsuario daoUsuario = new DaoUsuario();
				usuario = daoUsuario.criarUsuario(usuario);
				
				redirecionar = "/ListarUsuarios";

				mensagem = usuario != null ? "Usuario Criado com sucesso!" : "Falha ao criar usuario!";  

				request.removeAttribute("mensagem");
	        	request.setAttribute("mensagem", mensagem);
			}
			else {
				redirecionar = "index.jsp";
	        	mensagem = "Por favor, realize o login!";
	        	
	        	request.removeAttribute("mensagem");
	        	request.setAttribute("mensagem", mensagem);
			}
			
			RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
			
		}
		catch (SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			redirecionar = "/principal/principal.jsp?acao=criarUsuario";
			mensagem = "Login já existente!";
			
			request.removeAttribute("mensagem");
			request.setAttribute("mensagem", mensagem);
			
			RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
			
			redirecionar = "/principal/principal.jsp?acao=erro";
			
			RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
		} 
		catch (Exception e) {
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
