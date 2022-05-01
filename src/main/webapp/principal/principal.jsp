<%@page import="br.com.agenda_web.model.bean.BeanUsuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  

<c:set scope="session" var="usuario" value='<%= request.getSession().getAttribute("usuario") %>'></c:set>

<%!
    String acao;
    String opcao;
%>

<%
    
    acao = request.getParameter("acao");

    if(acao != null){
    	//views do contato
    	if(acao.equals("criarContato")){
            opcao = "/principal/viewContato/criarContato.jsp";
        }
    	else if(acao.equals("editarContato")){
            opcao = "/principal/viewContato/editarContato.jsp";
        }
    	else if(acao.equals("excluirContato")){
            opcao = "/principal/viewContato/excluirContato.jsp";
        }
       	else if(acao.equals("listarContatos")){
       		opcao = "/principal/viewContato/listarContatos.jsp";
       	}
       	else if(acao.equals("buscarContato")){
       		opcao = "/principal/viewContato/buscarContato.jsp";
       	}
    	
    	//views de endereço do contato
    	else if(acao.equals("listarEnderecosContatos")){
            opcao = "/principal/viewEnderecoContato/listarEnderecosContatos.jsp";
        }
    	else if(acao.equals("criarEnderecoContato")){
            opcao = "/principal/viewEnderecoContato/criarEnderecoContato.jsp";
        }
    	else if(acao.equals("buscarEnderecoContato")){
            opcao = "/principal/viewEnderecoContato/buscarEnderecoContato.jsp";
        }
    	else if(acao.equals("editarEnderecoContato")){
            opcao = "/principal/viewEnderecoContato/editarEnderecoContato.jsp";
        }
    	else if(acao.equals("excluirEnderecoContato")){
            opcao = "/principal/viewEnderecoContato/excluirEnderecoContato.jsp";
        }
    	
    	//views de endereço
    	else if(acao.equals("criarEndereco")){
            opcao = "/principal/viewEndereco/criarEndereco.jsp";
        }
    	else if(acao.equals("listarEnderecos")){
            opcao = "/principal/viewEndereco/listarEnderecos.jsp";
        }
    	else if(acao.equals("buscarEndereco")){
       		opcao = "/principal/viewEndereco/buscarEndereco.jsp";
       	}
    	else if(acao.equals("editarEndereco")){
            opcao = "/principal/viewEndereco/editarEndereco.jsp";
        }
    	else if(acao.equals("excluirEndereco")){
            opcao = "/principal/viewEndereco/excluirEndereco.jsp";
        }
    	
    	//views de agendamento
    	else if(acao.equals("criarAgendamento")){
            opcao = "/principal/viewAgendamento/criarAgendamento.jsp";
        }
    	else if(acao.equals("listarAgendamentos")){
            opcao = "/principal/viewAgendamento/listarAgendamentos.jsp";
        }
    	else if(acao.equals("buscarAgendamento")){
            opcao = "/principal/viewAgendamento/buscarAgendamento.jsp";
        }
    	else if(acao.equals("editarAgendamento")){
            opcao = "/principal/viewAgendamento/editarAgendamento.jsp";
        }
    	else if(acao.equals("excluirAgendamento")){
            opcao = "/principal/viewAgendamento/excluirAgendamento.jsp";
        }
    	
    	
    	//views de usuario
    	else if(acao.equals("criarUsuario")){
            opcao = "/principal/viewUsuario/criarUsuario.jsp";
        }
    	else if(acao.equals("buscarUsuario")){
            opcao = "/principal/viewUsuario/buscarUsuario.jsp";
        }
    	else if(acao.equals("editarUsuario")){
            opcao = "/principal/viewUsuario/editarUsuario.jsp";
        }
    	else if(acao.equals("excluirUsuario")){
            opcao = "/principal/viewUsuario/excluirUsuario.jsp";
        }
    	else if(acao.equals("listarUsuarios")){
            opcao = "/principal/viewUsuario/listarUsuarios.jsp";
        }
    	
    	else if(acao.equals("erro")){
       		opcao = "/principal/erro.jsp";
       	}
    	
       	else if(acao.equals("home")){
       		opcao = "/principal/home.jsp";
       	}
       	else{
       		opcao = "home.jsp";
       	}
    }
    else{
        opcao = "home.jsp";
    }
%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta charset="UTF-8">
        
	<title>Agenda Web</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/funcoes.js"></script>
	
</head>
<body onload="desabilitaInput()">

	<div id="tudo">
	
		<div id="cabecalho">
			<nav>
	            <label class="logo">Agenda Web</label>
	            <img alt="" src="<%= request.getContextPath()%>/img/agenda.png">
			</nav>
		</div>
		
		<div class="dp-menu">
			<ul >
				<li class="menu"><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=home">Home</a></li>
				<li class="menu"><a>Agendamento</a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/criarAgendamento?acao=criarCbx">Criar</a></li>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=buscarAgendamento">Buscar</a></li>
						<li><a href="<%=request.getContextPath() %>/LitarAgendamentos">Listar</a></li>
					</ul>
				</li>
				<li class="menu"><a>Contato</a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=criarContato">Criar</a></li>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=buscarContato">Buscar</a></li> 
						<li><a href="<%=request.getContextPath() %>/ListarContatos">Listar</a></li>
					</ul>
				</li>
				<li class="menu"><a>Endereço</a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=criarEndereco">Criar</a></li>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=buscarEndereco">Buscar</a></li>
						<li><a href="<%=request.getContextPath() %>/ListarEnderecos">Listar</a></li>
					</ul>
				</li>
				<li class="menu"><a>Endereço do Contato</a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/ListarEnderecosDosContatos?acao=criar">Criar</a></li>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=buscarEnderecoContato">Buscar</a></li>
						<li><a href="<%=request.getContextPath() %>/ListarEnderecosDosContatos">Listar</a></li>
					</ul>
				</li>
				<li class="menu"><a>Usuário</a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/PodeCriar?acao=criar">Criar</a></li>
						<li><a href="<%=request.getContextPath() %>/principal/principal.jsp?acao=buscarUsuario">Buscar</a></li>
						<li><a href="<%=request.getContextPath() %>/ListarUsuarios">Listar</a></li>
					</ul>
				</li>
				<li class="menu menu-sair"><a>${usuario.login}</a>
					<ul>
						<li><a href="<%=request.getContextPath() %>/BuscarUsuario?acao=editar&id=${usuario.id_usuario}">Edit.</a></li>
						<li><a href="<%=request.getContextPath() %>/UsuarioServletValidar?acao=sair">Sair</a></li>
					</ul>
				</li>
			</ul>
			
		</div>
		
		
		<div id="principal">
		 	<jsp:include page="<%= opcao%>"/> 
        </div>
        
        <div class="rodape">
       		<div class="links">
       			<a href="https://www.linkedin.com/in/gustavo-macedo-360/"><img alt="" src="<%=request.getContextPath()%>/img/linkedin.png"></a>
       			<a href="mailto:gustavo.macedo9@outlook.com"><img alt="" src="<%=request.getContextPath()%>/img/outlook.png"></a>
       			<a href="https://github.com/gmn-95"><img alt="" src="<%=request.getContextPath()%>/img/github.png"></a>
       		</div>
       		<br>
        	<p>
        		Desenvolvedor: Gustavo Macedo <br>Copyright &copy; 2022; Agenda Web &reg;
       		</p>
        </div>
		
		
	</div>
	
	
</body>
</html>