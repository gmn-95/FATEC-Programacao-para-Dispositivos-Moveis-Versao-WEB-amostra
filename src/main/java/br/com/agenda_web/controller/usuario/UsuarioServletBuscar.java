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

/**
 * Servlet implementation class UsuarioServletBuscar
 */
@WebServlet(name = "/UsuarioServletBuscar", urlPatterns =  {"/UsuarioServletBuscar", "/BuscarUsuario", "/PodeCriar"})
public class UsuarioServletBuscar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsuarioServletBuscar() {
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
				
				String acao = request.getParameter("acao");
				
				if(acao == null) {
					acao = "";
				}
				
				if(acao.equals("editar") || acao.equals("excluir")) {
					Long id = Long.parseLong(request.getParameter("id"));
					
					usuario.setId_usuario(id);
					
					DaoUsuario daoUsuario = new DaoUsuario();
					usuario = daoUsuario.buscarUsuario(usuario);
					
					if(usuario != null) {
						redirecionar = acao.equals("editar") ? "/principal/principal.jsp?acao=editarUsuario" : "/principal/principal.jsp?acao=excluirUsuario";
					
						request.removeAttribute("usuario");
						request.setAttribute("usuario", usuario);
						
						request.removeAttribute("usuarioLogado");
			        	request.setAttribute("usuarioLogado", usuarioLogado);
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarUsuario";
						mensagem = "Erro ao buscar";
					}
				}
				else if(acao.equals("criar")){
					DaoUsuario daoUsuario = new DaoUsuario();
					usuario = daoUsuario.buscarUsuario(usuario);
					
					if(usuario != null) {
						redirecionar = "/principal/principal.jsp?acao=criarUsuario";
					
						request.removeAttribute("usuario");
						request.setAttribute("usuario", usuario);
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=criarUsuario";
						mensagem = "Erro ao validar usuário logado";
					}
				}
				else {
					
						
					Long id = Long.parseLong(request.getParameter("valorProcurado"));
					
					usuario.setId_usuario(id);
					
					DaoUsuario daoUsuario = new DaoUsuario();
					usuario = daoUsuario.buscarUsuario(usuario);
					
					if(usuario != null) {
						redirecionar = "/principal/principal.jsp?acao=buscarUsuario";
						request.removeAttribute("usuario");
						request.setAttribute("usuario", usuario);
						
						request.removeAttribute("usuarioLogado");
			        	request.setAttribute("usuarioLogado", usuarioLogado);
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarUsuario";
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
