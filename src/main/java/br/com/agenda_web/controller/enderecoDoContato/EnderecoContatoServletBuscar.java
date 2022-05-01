package br.com.agenda_web.controller.enderecoDoContato;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanContato;
import br.com.agenda_web.model.bean.BeanEndereco;
import br.com.agenda_web.model.bean.BeanEnderecoContato;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoContato;
import br.com.agenda_web.model.dao.DaoEndereco;
import br.com.agenda_web.model.dao.DaoEnderecoContato;

/**
 * Servlet implementation class EnderecoContatoServletBuscar
 */
@WebServlet(name = "/EnderecoContatoServletBuscar", urlPatterns =  {"/EnderecoContatoServletBuscar", "/BuscarEnderecoContato"})
public class EnderecoContatoServletBuscar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    public EnderecoContatoServletBuscar() {
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
					
					BeanEnderecoContato enderecoContato = new BeanEnderecoContato(id, usuario);
					DaoEnderecoContato daoEnderecoContato = new DaoEnderecoContato();
					enderecoContato = daoEnderecoContato.buscarEnderecoContato(enderecoContato);
					
					request.removeAttribute("enderecoContato");
					request.setAttribute("enderecoContato", enderecoContato);
					
					if(acao.equals("editar")){
						
						Long idContato = Long.parseLong(request.getParameter("idContato"));
						Long idEndereco = Long.parseLong(request.getParameter("idEndereco"));
						
						BeanEndereco endereco = new BeanEndereco(idEndereco, usuario);
						DaoEndereco daoEndereco = new DaoEndereco();
						
						BeanContato contato = new BeanContato(idContato, usuario);
						DaoContato daoContato = new DaoContato();
						
						String tipoPesquisa = "Todos";
						
						List<BeanEndereco> enderecos = new ArrayList<BeanEndereco>();
						enderecos = daoEndereco.listarEnderecos(endereco, tipoPesquisa);
						
						List<BeanContato> contatos = new ArrayList<BeanContato>();
						contatos = daoContato.listarContatos(contato, tipoPesquisa);
						
						if(contatos != null && enderecos != null) {
							redirecionar = "/principal/principal.jsp?acao=editarEnderecoContato";
							
							request.removeAttribute("enderecos");
							request.setAttribute("enderecos", enderecos);
							
							request.removeAttribute("contatos");
							request.setAttribute("contatos", contatos);
						}
					}
					else if(acao.equals("excluir")){
						redirecionar = "/principal/principal.jsp?acao=excluirEnderecoContato";
					}
				}
				else{
					Long id = Long.parseLong(request.getParameter("valorProcurado"));
					
					BeanEnderecoContato enderecoContato = new BeanEnderecoContato(id, usuario);
					
					DaoEnderecoContato daoEnderecoContato = new DaoEnderecoContato();
					
					enderecoContato = daoEnderecoContato.buscarEnderecoContato(enderecoContato);
					
					if(enderecoContato != null) {
						redirecionar = "/principal/principal.jsp?acao=buscarEnderecoContato";
						request.removeAttribute("enderecoContato");
						request.setAttribute("enderecoContato", enderecoContato);
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=buscarEnderecoContato";
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
