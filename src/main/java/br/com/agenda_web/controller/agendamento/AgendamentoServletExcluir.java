package br.com.agenda_web.controller.agendamento;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanAgendamento;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoAgendamento;

/**
 * Servlet implementation class AgendamentoServletExcluir
 */
@WebServlet(name = "/AgendamentoServletExcluir", urlPatterns = {"/AgendamentoServletExcluir", "/ExcluirAgendamento"})
public class AgendamentoServletExcluir extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendamentoServletExcluir() {
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
				
				Long id = Long.parseLong(request.getParameter("id"));
				
				BeanAgendamento agendamento = new BeanAgendamento(id, usuario);
				
				DaoAgendamento daoAgendamento = new DaoAgendamento();
				
				agendamento = daoAgendamento.excluirAgendamento(agendamento);
				
				redirecionar = "/LitarAgendamentos";
				
				mensagem = agendamento != null ? "Agendamento Excluído com sucesso!" : "Erro ao excluir Agendamento!";

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
