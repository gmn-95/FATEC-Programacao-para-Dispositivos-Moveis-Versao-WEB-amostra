package br.com.agenda_web.controller.agendamento;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

/**
 * Servlet implementation class AgendamentoServletEditar
 */
@WebServlet(name = "/AgendamentoServletEditar", urlPatterns = {"/AgendamentoServletEditar", "/EditarAgendamento"})
public class AgendamentoServletEditar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgendamentoServletEditar() {
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
				
				SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
				
				Long id = Long.parseLong(request.getParameter("id"));
				Date data = dataFormat.parse(request.getParameter("data"));
				Date hora = horaFormat.parse(request.getParameter("hora"));
				String descricao = request.getParameter("descricao");
				BeanContato contato = new BeanContato(Long.parseLong(request.getParameter("idContato")));
				String conteudo = request.getParameter("conteudo");
				
				if(descricao == null) {
					descricao = "";
				}
				
				if(conteudo == null) {
					conteudo = "";
				}
				
				BeanAgendamento agendamento = new BeanAgendamento(id, contato, usuario, data, hora, descricao, conteudo);
				
				DaoAgendamento daoAgendamento = new DaoAgendamento();
				
				agendamento = daoAgendamento.atualizarAgendamento(agendamento);
				
				redirecionar = "/LitarAgendamentos";
				
				mensagem = contato != null ? "Agendamento atualizado com sucesso!" : "Erro ao atualizar Agendamento!";

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
