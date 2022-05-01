package br.com.agenda_web.controller.contato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class ContatoServletListar
 */
@WebServlet(name = "/ContatoServletListar", urlPatterns = {"/ContatoServletListar", "/ListarContatos", "/criarAgendamento"})
public class ContatoServletListar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ContatoServletListar() {
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
				BeanContato contato = new BeanContato(usuario);
				
				String tipoPesquisa = request.getParameter("tipoPesquisa");
				String valor = request.getParameter("valorProcurado");
				String acao = request.getParameter("acao");
				
				if(acao == null) {
					acao = "";
				}
				
				if(!acao.equals("criarCbx")) {
					
					if(tipoPesquisa == null) {
						tipoPesquisa = "Todos";
					}
					else if(tipoPesquisa.equals("Nome")) {
						contato.setNome(valor);
					}
					else if(tipoPesquisa.equals("Telefone")) {
						contato.setTelefone_fixo(valor);
					}
					else if(tipoPesquisa.equals("Celular")) {
						contato.setTelefone_fixo(valor);
					}
					else if(tipoPesquisa.equals("Email")) {
						contato.setEmail(valor);
					}
					else if(tipoPesquisa.equals("Obs")) {
						contato.setObs(valor);
					}
					
					List<BeanContato> contatos = new ArrayList<BeanContato>();
					
					DaoContato daoContato = new DaoContato();
					
					contatos = daoContato.listarContatos(contato, tipoPesquisa);
					
					if(contatos != null) {
						redirecionar = "/principal/principal.jsp?acao=listarContatos";
						request.removeAttribute("contatos");
						request.setAttribute("contatos", contatos);
						mensagem = (String) request.getAttribute("mensagem");
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=listarContatos";
						mensagem = "Erro ao listar";
					}
				}
				else if(acao.equalsIgnoreCase("criarCbx")) {
					tipoPesquisa = "Todos";
					
					List<BeanContato> contatos = new ArrayList<BeanContato>();
					
					DaoContato daoContato = new DaoContato();
					
					contatos = daoContato.listarContatos(contato, tipoPesquisa);
					
					if(contatos != null) {
						redirecionar = "/principal/principal.jsp?acao=criarAgendamento";
						request.removeAttribute("contatos");
						request.setAttribute("contatos", contatos);
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=home";
						mensagem = "Erro ao listar combo box contatos";
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
