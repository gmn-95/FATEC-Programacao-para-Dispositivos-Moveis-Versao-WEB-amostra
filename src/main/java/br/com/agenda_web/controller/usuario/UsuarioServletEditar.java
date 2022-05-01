package br.com.agenda_web.controller.usuario;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoPessoa;
import br.com.agenda_web.model.dao.DaoUsuario;

/**
 * Servlet implementation class UsuarioServletEditar
 */
@WebServlet(name = "/UsuarioServletEditar", urlPatterns = {"/UsuarioServletEditar", "/EditarUsuario"})
public class UsuarioServletEditar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServletEditar() {
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
				
				Long idUsuario = Long.parseLong(request.getParameter("id"));
				Long idPessoa = Long.parseLong(request.getParameter("idP"));
				String nome = request.getParameter("nome");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				boolean criarUsuario = false;
				boolean editarUsuario = false;
				boolean excluirUsuario = false;
				
				if(request.getParameter("criarUsuario") != null) {
					criarUsuario = true;
				}
				
				if(request.getParameter("editarUsuario") != null) {
					editarUsuario = true;
				}
				
				if(request.getParameter("excluirUsuario") != null) {
					excluirUsuario = true;
				}
				
				usuario.setId_usuario(idUsuario);
				usuario.setId(idPessoa);
				usuario.setNome(nome);
				usuario.setLogin(login);
				usuario.setSenha(senha);
				usuario.setCriar_novo_usuario(criarUsuario);
				usuario.setEditar_usuario(editarUsuario);
				usuario.setExcluir_usuario(excluirUsuario);
				
				DaoPessoa daoPessoa = new DaoPessoa();
				usuario = daoPessoa.atualizarPessoa(usuario);
				
				DaoUsuario daoUsuario = new DaoUsuario();
				usuario = daoUsuario.atualizarUsuario(usuario);
				
				redirecionar = "/ListarUsuarios";

				mensagem = usuario != null ? "Usuario atualizado com sucesso!" : "Falha ao atualizado usuario!";  

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
			
			redirecionar = "/ListarUsuarios";
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
