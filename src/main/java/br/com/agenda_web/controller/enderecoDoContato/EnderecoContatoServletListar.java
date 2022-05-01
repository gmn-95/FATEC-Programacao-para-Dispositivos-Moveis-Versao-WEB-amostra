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
 * Servlet implementation class EnderecoContatoServletListar
 */
@WebServlet(name = "/EnderecoContatoServletListar", urlPatterns =  {"/EnderecoContatoServletListar", "/ListarEnderecosDosContatos"})
public class EnderecoContatoServletListar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    public EnderecoContatoServletListar() {
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
				
				if(!acao.equals("criar")) {
					
					BeanEnderecoContato enderecoContato = new BeanEnderecoContato(usuario);
					
					String tipoPesquisa = request.getParameter("tipoPesquisa");
					String valor = request.getParameter("valorProcurado");
					
					if(tipoPesquisa == null) {
						tipoPesquisa = "Todos";
					}
					else if(tipoPesquisa.equals("Obs")) {
						enderecoContato.setObs(valor);
					}
					else if(tipoPesquisa.equals("Id endereco")) {
						enderecoContato.setEndereco(new BeanEndereco(Long.parseLong(valor)));
					}
					else if(tipoPesquisa.equals("Estado")) {
						enderecoContato.setContato(new BeanContato(Long.parseLong(valor)));
					}
					
					List<BeanEnderecoContato> enderecoContatos = new ArrayList<BeanEnderecoContato>();
					
					DaoEnderecoContato daoEnderecoContato = new DaoEnderecoContato();
					
					enderecoContatos = daoEnderecoContato.listarEnderecoContato(enderecoContato, tipoPesquisa);
					
					if(enderecoContatos != null) {
						redirecionar = "/principal/principal.jsp?acao=listarEnderecosContatos";
						request.removeAttribute("enderecoContatos");
						request.setAttribute("enderecoContatos", enderecoContatos);
						mensagem = (String) request.getAttribute("mensagem");
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=listarEnderecosContatos";
						mensagem = "Erro ao listar!";
					}
				}
				else if(acao.equals("criar")) {
					
					String tipoPesquisa = "Todos";
					
					BeanEndereco endereco = new BeanEndereco(usuario);
					List<BeanEndereco> enderecos = new ArrayList<BeanEndereco>();
					DaoEndereco daoEndereco = new DaoEndereco();
					enderecos = daoEndereco.listarEnderecos(endereco, tipoPesquisa);
					
					
					BeanContato contato = new BeanContato(usuario);
					List<BeanContato> contatos = new ArrayList<BeanContato>();
					DaoContato daoContato = new DaoContato();
					contatos = daoContato.listarContatos(contato, tipoPesquisa);
					
					
					if(contatos != null && enderecos != null) {
						redirecionar = "/principal/principal.jsp?acao=criarEnderecoContato";
						
						request.removeAttribute("enderecos");
						request.setAttribute("enderecos", enderecos);
						
						request.removeAttribute("contatos");
						request.setAttribute("contatos", contatos);
						
					}
					else {
						redirecionar = "/principal/principal.jsp?acao=home";
						mensagem = "Erro ao listar";
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
