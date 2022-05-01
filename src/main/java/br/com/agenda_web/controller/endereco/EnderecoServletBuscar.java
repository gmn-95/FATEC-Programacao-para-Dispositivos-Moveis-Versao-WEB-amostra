package br.com.agenda_web.controller.endereco;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanEndereco;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoEndereco;

/**
 * Servlet implementation class EnderecoServletBuscar
 */
@WebServlet(name = "/EnderecoServletBuscar", urlPatterns =  {"/EnderecoServletBuscar", "/BuscarEndereco"})
public class EnderecoServletBuscar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    public EnderecoServletBuscar() {
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
					
					BeanEndereco endereco = new BeanEndereco(id, usuario);
					
					DaoEndereco daoEndereco = new DaoEndereco();
					
					endereco = daoEndereco.buscarEndereco(endereco);
					
					if(endereco != null) {
						if(acao.equals("editar")) {
							redirecionar = "/principal/principal.jsp?acao=editarEndereco";
						}
						else {
							
							redirecionar = "/principal/principal.jsp?acao=excluirEndereco";
						}
						request.removeAttribute("endereco");
						request.setAttribute("endereco", endereco);
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarEndereco";
						mensagem = "Erro ao buscar";
					}
					
				}
				else {
					Long id = Long.parseLong(request.getParameter("valorProcurado"));
					
					BeanEndereco endereco = new BeanEndereco(id, usuario);
					
					DaoEndereco daoEndereco = new DaoEndereco();
					
					endereco = daoEndereco.buscarEndereco(endereco);
					
					if(endereco != null) {
						redirecionar = "/principal/principal.jsp?acao=buscarEndereco";
						request.removeAttribute("endereco");
						request.setAttribute("endereco", endereco);
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarEndereco";
						mensagem = "Erro ao buscar";
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
