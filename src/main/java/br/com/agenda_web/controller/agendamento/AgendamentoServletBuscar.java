package br.com.agenda_web.controller.agendamento;

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
import br.com.agenda_web.model.bean.BeanAgendamento;
import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoAgendamento;
import br.com.agenda_web.model.dao.DaoContato;

/**
 * Servlet implementation class AgendamentoServletBuscar
 */
@WebServlet(name = "/AgendamentoServletBuscar", urlPatterns = {"/AgendamentoServletBuscar", "/BuscarAgendamento"})
public class AgendamentoServletBuscar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendamentoServletBuscar() {
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
					
					BeanAgendamento agendamento = new BeanAgendamento(id, usuario);
					
					DaoAgendamento daoAgendamento = new DaoAgendamento();
					
					agendamento = daoAgendamento.buscarAgendamento(agendamento);
					
					request.removeAttribute("agendamento");
					request.setAttribute("agendamento", agendamento);
					
					if(acao.equals("editar")) {
						Long idContato = Long.parseLong(request.getParameter("idContato"));

						BeanContato contato = new BeanContato(idContato, usuario);
						DaoContato daoContato = new DaoContato();
						List<BeanContato> contatos = new ArrayList<BeanContato>();
						contatos = daoContato.listarContatos(contato, "Todos");
						
						if(contatos != null) {
							redirecionar = "/principal/principal.jsp?acao=editarAgendamento";
							request.removeAttribute("contatos");
							request.setAttribute("contatos", contatos);
						}
					}
					else if(acao.equals("excluir")) {
						redirecionar = "/principal/principal.jsp?acao=excluirAgendamento";
					}
				}
				else {
					
					Long id = Long.parseLong(request.getParameter("valorProcurado"));
					
					BeanAgendamento agendamento = new BeanAgendamento(id, usuario);
					
					DaoAgendamento daoAgendamento = new DaoAgendamento();
					
					agendamento = daoAgendamento.buscarAgendamento(agendamento);
					
					if(agendamento != null) {
						redirecionar = "/principal/principal.jsp?acao=buscarAgendamento";
						request.removeAttribute("agendamento");
						request.setAttribute("agendamento", agendamento);
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarAgendamento";
						mensagem = "Erro ao buscar agendamento";
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
