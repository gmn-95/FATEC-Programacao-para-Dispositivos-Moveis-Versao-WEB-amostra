package br.com.agenda_web.controller.endereco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class EnderecoServletListar
 */
@WebServlet(name = "/EnderecoServletListar", urlPatterns =  {"/EnderecoServletListar", "/ListarEnderecos"})
public class EnderecoServletListar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    public EnderecoServletListar() {
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
				BeanEndereco endereco = new BeanEndereco(usuario);
				
				String tipoPesquisa = request.getParameter("tipoPesquisa");
				String valor = request.getParameter("valorProcurado");
				
				if(tipoPesquisa == null) {
					tipoPesquisa = "Todos";
				}
				else if(tipoPesquisa.equals("Bairro")) {
					endereco.setBairro(valor);
				}
				else if(tipoPesquisa.equals("Cidade")) {
					endereco.setCidade(valor);
				}
				else if(tipoPesquisa.equals("Estado")) {
					endereco.setEstado(valor);
				}
				else if(tipoPesquisa.equals("Cep")) {
					endereco.setCep(valor);
				}
				else if(tipoPesquisa.equals("Logradouro")) {
					endereco.setLogradouro(valor);
				}
				else if(tipoPesquisa.equals("Número")) {
					endereco.setNumero(valor);
				}
				else if(tipoPesquisa.equals("Complemento")) {
					endereco.setComplemento(valor);
				}
				
				List<BeanEndereco> enderecos = new ArrayList<BeanEndereco>();
				
				DaoEndereco daoEndereco = new DaoEndereco();
				
				enderecos = daoEndereco.listarEnderecos(endereco, tipoPesquisa);
				
				if(enderecos != null) {
					redirecionar = "/principal/principal.jsp?acao=listarEnderecos";
					request.removeAttribute("enderecos");
					request.setAttribute("enderecos", enderecos);
					mensagem = (String) request.getAttribute("mensagem");
					
				}
				else {
					redirecionar = "/principal/principal.jsp?acao=listarEnderecos";
					mensagem = "Erro ao listar";
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
