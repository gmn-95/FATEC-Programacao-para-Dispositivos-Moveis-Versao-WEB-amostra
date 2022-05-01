package br.com.agenda_web.controller.endereco;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.agenda_web.controller.usuario.UsuarioServletGetSession;
import br.com.agenda_web.model.bean.BeanEndereco;
import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoEndereco;

/**
 * Servlet implementation class EnderecoServletEditar
 */
@WebServlet(name = "/EnderecoServletEditar", urlPatterns = {"/EnderecoServletEditar", "/EditarEndereco"})
public class EnderecoServletEditar extends UsuarioServletGetSession {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnderecoServletEditar() {
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
				
				Long id = Long.parseLong(request.getParameter("id"));
				String cep = request.getParameter("cep");
				String bairro = request.getParameter("bairro");
				String cidade = request.getParameter("cidade");
				String estado = request.getParameter("estado");
				String logradouro = request.getParameter("logradouro");
				String numero = request.getParameter("numero");
				String complemento = request.getParameter("complemento");
				
				if(complemento == null) {
					complemento = "";
				}
				
				BeanEndereco endereco = new BeanEndereco(id, bairro, cidade, estado, cep, logradouro, numero, complemento, usuario);
				
				DaoEndereco daoEndereco = new DaoEndereco();
				
				endereco = daoEndereco.atualizarEndereco(endereco);
				
				redirecionar = "/ListarEnderecos";
				
				mensagem = endereco != null ? "Endereço editado com sucesso!" : "Falha ao editar Endereço";

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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
