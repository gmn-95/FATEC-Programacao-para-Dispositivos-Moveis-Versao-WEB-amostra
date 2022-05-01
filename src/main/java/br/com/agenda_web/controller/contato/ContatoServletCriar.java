package br.com.agenda_web.controller.contato;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoContato;

/**
 * Servlet implementation class ContatoServletCriar
 */
@WebServlet(name = "/ContatoServletCriar", urlPatterns = {"/ContatoServletCriar", "/CriarContato"})
public class ContatoServletCriar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContatoServletCriar() {
        super();
        
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String redirecionar = "";
        String mensagem;
        
		try {
			BeanUsuario usuario = new BeanUsuario(super.getUserLogado(request));
			
			if(usuario.getId_usuario() != 0 && usuario.getId_usuario() != null) {
				
				String nome = request.getParameter("nome");
				String email = request.getParameter("email");
				String celular = request.getParameter("celular");
				String telefone = request.getParameter("telefone");
				String obs = request.getParameter("obs");
				
				if(email == null) {
					email = "";
				}
				
				if(celular == null) {
					celular = "";
				}
				
				if(telefone == null) {
					telefone = "";
				}
				
				if(obs == null) {
					obs = "";
				}
				
				BeanContato contato = new BeanContato(usuario, nome, telefone, celular, email, obs);
				
				DaoContato daoContato = new DaoContato();
				
				contato = daoContato.criarContato(contato);
				
				redirecionar = "/principal/principal.jsp?acao=criarContato";

				mensagem = contato != null ? "Contato Criado com sucesso!" : "Falha ao criar Contato";
				
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
			
		} catch (Exception e) {
			e.printStackTrace();
			
			redirecionar = "/principal/principal.jsp?acao=erro";
			
			RequestDispatcher view = request.getRequestDispatcher(redirecionar);
	        view.forward(request, response);
		}
    }

    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
