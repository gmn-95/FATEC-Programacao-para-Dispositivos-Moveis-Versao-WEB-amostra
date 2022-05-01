package br.com.agenda_web.controller.agendamento;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanAgendamento;
import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoAgendamento;

/**
 * Servlet implementation class AgendamentoServletListar
 */
@WebServlet(name = "/AgendamentoServletListar", urlPatterns =  {"/AgendamentoServletListar", "/LitarAgendamentos"})
public class AgendamentoServletListar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    public AgendamentoServletListar() {
        super();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        SimpleDateFormat horaFormat = new SimpleDateFormat("HH:mm");
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd-MM-yyyy");
        
        
        String redirecionar = "";
        String mensagem = "";
        
		try {
			BeanUsuario usuario = new BeanUsuario(super.getUserLogado(request));
			
			if(usuario.getId_usuario() != 0 && usuario.getId_usuario() != null) {
				BeanAgendamento agendamento = new BeanAgendamento(usuario);
				
				String tipoPesquisa = request.getParameter("tipoPesquisa");
				String valor = request.getParameter("valorProcurado");
				
				if(tipoPesquisa == null) {
					tipoPesquisa = "Todos";
				}
				else if(tipoPesquisa.equals("Hora")) {
					Date hora = horaFormat.parse(valor);
					agendamento.setHora_agendada(hora);
				}
				else if(tipoPesquisa.equals("Data")) {
					Date data = dataFormat.parse(valor);
					agendamento.setData_agendada(data);
				}
				else if(tipoPesquisa.equals("Descricao")) {
					agendamento.setDescricao(valor);
				}
				else if(tipoPesquisa.equals("Conteudo")) {
					agendamento.setConteudo(valor);
				}
				else if(tipoPesquisa.equals("Contato")) {
					agendamento.setContato(new BeanContato(valor));
				}
				else if(tipoPesquisa.equals("Id contato")) {
					agendamento.setContato(new BeanContato(Long.parseLong(valor)));
				}
				
				
				List<BeanAgendamento> agendamentos = new ArrayList<BeanAgendamento>();
				
				DaoAgendamento daoAgendamento = new DaoAgendamento();
				
				agendamentos = daoAgendamento.listarAgendamento(agendamento, tipoPesquisa);
				
				if(agendamentos != null) {
					redirecionar = "/principal/principal.jsp?acao=listarAgendamentos";
					request.removeAttribute("agendamentos");
					request.setAttribute("agendamentos", agendamentos);
					mensagem = (String) request.getAttribute("mensagem");
					
				}
				else {
					redirecionar = "/principal/principal.jsp?acao=listarAgendamentos";
					mensagem = "Erro ao listar!";
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
