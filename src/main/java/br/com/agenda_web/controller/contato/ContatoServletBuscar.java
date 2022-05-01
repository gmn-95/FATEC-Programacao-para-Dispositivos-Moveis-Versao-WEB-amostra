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
 * Servlet implementation class ContatoServletBuscar
 */
@WebServlet(name = "/ContatoServletBuscar", urlPatterns = {"/ContatoServletBuscar", "/BuscarContato"})
public class ContatoServletBuscar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContatoServletBuscar() {
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
				
				String acao = request.getParameter("acao");
				
				if(acao == null) {
					acao = "";
				}
				
				if(acao.equals("editar") || acao.equals("excluir")) {
					Long id = Long.parseLong(request.getParameter("id"));
					
					BeanContato contato = new BeanContato(id, usuario);
					
					DaoContato daoContato = new DaoContato();
					
					contato = daoContato.buscarContato(contato);
					
					if(contato != null) {
						
						redirecionar = acao.equals("editar") ? "/principal/principal.jsp?acao=editarContato" : "/principal/principal.jsp?acao=excluirContato";
						
						request.removeAttribute("contato");
						request.setAttribute("contato", contato);
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarContato";
						mensagem = "Erro ao buscar";
					}
					
				}
				else {
					
					Long id = Long.parseLong(request.getParameter("valorProcurado"));
					
					BeanContato contato = new BeanContato(id, usuario);
					
					DaoContato daoContato = new DaoContato();
					
					contato = daoContato.buscarContato(contato);
					
					if(contato != null) {
						redirecionar = "/principal/principal.jsp?acao=buscarContato";
						request.removeAttribute("contato");
						request.setAttribute("contato", contato);
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarContato";
						mensagem = "Erro ao buscar contato";
					}
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
    
    @Override
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		processRequest(request, response);
   	}

}
