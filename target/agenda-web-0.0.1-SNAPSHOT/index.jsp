<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Agenda Web</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">
</head>

<body class="fundo-login">

	<br><br>
	<br><br>
	<br>	
	<div id="login">
	
		<form class="card" method="post" action="<%= request.getContextPath() %>/UsuarioServletValidar">
			
			<div class="card-header">
				<h2>Agenda Web</h2>
			</div>
			
			<div class="card-content">
			
				<div class="card-content-area">
					<h4 class="mensagem-login">${msg}</h4>
					<h4 class="mensagem-login">${mensagem}</h4>
					<label for="login"><strong>Usuário</strong></label>
					<input type="text" name="login" id="login" size="20" maxlength="20" autocomplete="off">
				</div>
				
				<div class="card-content-area">
					<label for="senha"><strong>Senha</strong></label>
					<input type="password" name="senha" id="senha" size="20" maxlength="20" autocomplete="off">
				</div>
			</div>
			
			<div class="card-footer">
				<input type="submit" class="submit" name="btEntrar" id="btEntrar" size="6" maxlength="6" value="Entrar">
			</div>
			
		</form>
	</div>
</body>
</html>