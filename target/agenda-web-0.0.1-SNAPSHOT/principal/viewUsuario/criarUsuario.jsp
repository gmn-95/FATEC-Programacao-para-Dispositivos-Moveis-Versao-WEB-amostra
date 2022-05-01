<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>

<c:choose>
	<c:when test="${usuario.criar_novo_usuario == true}">
		<form class="formulario-usuario" method="post" action="<%=request.getContextPath() %>/CriarUsuario" onsubmit="return validaSenha();">
		
				
				<div class="forms-usuario">
					<h4 class="mensagem">${mensagem}</h4>
					<div class="field-usuario">
						<div class="control-usuario">
							<label class="label-usuario">Nome </label> 
							<input name="nome" id="nome" class="input-usuario" type="text" placeholder="Nome" required="required" size="20" maxlength="40">
						</div>
					</div>
			
					<div class="field-usuario">
						<div class="control-usuario">
							<label class="label-usuario">Login </label>
							<input name="login" id="login_" class="input-usuario" type="text" placeholder="Login" size="20" maxlength="40" required="required">
						</div>
					</div>
			
					<div class="field-usuario">
						<div class="control-usuario">
							<label class="label-usuario">Senha </label>
							<input type="password" name="senha" id="senha" class="input-usuario" size="20" maxlength="40" placeholder="*******" required="required">
						</div>
					</div>
					
					<div class="field-usuario">
						<div class="control-usuario">
							<label class="label-usuario">Confirmar Senha </label>
							<input type="password" name="confSenha" id="confSenha" class="input-usuario" size="20" maxlength="40" placeholder="*******"  required="required">
						</div>
					</div>
					
					<div class="field-usuario">
						<div class="control-usuario">
							<input type="checkbox" name="criarUsuario" id="criarUsuario">
							<label class="label-usuario">Criar Usuário</label>
							
							<input type="checkbox" name="editarUsuario" id="editarUsuario">
							<label class="label-usuario">Editar Usuário</label>
							
						</div>
					</div>
					
					<div class="field-usuario">
						<div class="control-usuario">
							<input type="checkbox" name="excluirUsuario" id="excluirUsuario">
							<label class="label-usuario">Excluir Usuário</label>
							
							<input type="checkbox" name="buscarUsuario" id="buscarUsuario">
							<label class="label-usuario">Buscar Usuário</label>
						</div>
					</div>
					
					<div class="field-usuario">
						<div class="control-usuario">
							<input type="checkbox" name="listarUsuario" id="listarUsuario">
							<label class="label-usuario" for="listarUsuarios" >Listar Usuário</label>
						</div>
					</div>
				</div>
					<div class="bt-contato">
						<div class="control-usuario">
							<button class="botao">Criar</button>
							<button type="reset" class="botao">Limpar</button>
						</div>
					</div>
		
		</form>
	</c:when>
	<c:otherwise>
		<h3 class="mensagem">Você não tem permissão para criar um usuário!</h3>
		<h3 class="mensagem">Verifique com o administrador!</h3>
	</c:otherwise>
</c:choose>