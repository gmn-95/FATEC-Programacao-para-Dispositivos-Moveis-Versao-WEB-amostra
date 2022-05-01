package br.com.agenda_web.controller.enderecoDoContato;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanEndereco;
import br.com.agenda_web.model.bean.BeanEnderecoContato;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoEnderecoContato;

/**
 * Servlet implementation class EnderecoContatoServletCriar
 */
@WebServlet(name = "/EnderecoContatoServletCriar", urlPatterns = {"/EnderecoContatoServletCriar", "/CriarEnderecoContato"})
public class EnderecoContatoServletCriar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnderecoContatoServletCriar() {
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
				
				Long idContato = Long.parseLong(request.getParameter("idContato"));
				Long idEndereco = Long.parseLong(request.getParameter("idEndereco"));
				String obs = request.getParameter("Obs");
				
				if(obs == null) {
					obs = "";
				}
				
				BeanEndereco endereco = new BeanEndereco(idEndereco);
				BeanContato contato = new BeanContato(idContato);
				
				BeanEnderecoContato enderecoContato = new BeanEnderecoContato(endereco, contato, obs, usuario);
				
				DaoEnderecoContato daoEnderecoContato = new DaoEnderecoContato();
				
				enderecoContato = daoEnderecoContato.criarEnderecoContato(enderecoContato);
				
				redirecionar = "/EnderecoContatoServletListar";

				mensagem = enderecoContato != null ? "Endereço do contato Criado com sucesso!" : "Falha ao criar Endereço do contato";  

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
