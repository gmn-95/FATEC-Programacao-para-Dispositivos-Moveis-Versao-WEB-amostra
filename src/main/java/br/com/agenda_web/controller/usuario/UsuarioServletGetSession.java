package br.com.agenda_web.controller.usuario;

import java.io.Serializable;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.agenda_web.model.bean.BeanUsuario;
import br.com.agenda_web.model.dao.DaoUsuario;

public class UsuarioServletGetSession extends HttpServlet implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private DaoUsuario daoUsuario = new DaoUsuario();
       
	public Long getUserLogado(HttpServletRequest request) throws Exception {
		
		HttpSession session = request.getSession();
		
		BeanUsuario usuario = (BeanUsuario) session.getAttribute("usuario");
		
		Long id;
		if(usuario != null) {
			id = daoUsuario.consultaUsuarioLogado(usuario).getId_usuario();
		}
		else {
			id = 0L;
		}
		
		return id;
		
	}
	
}
