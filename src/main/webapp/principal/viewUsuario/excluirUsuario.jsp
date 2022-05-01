<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>

<form class="formulario-usuario" method="post" action="<%=request.getContextPath() %>/ExcluirUsuario" >

		
		<div class="forms-usuario">
			<h4>${mensagem}</h4>
			<div class="field-usuario">
				<div class="control-usuario">
					<input name="idP" id="idP" class="input-usuario" hidden="hidden" value="${usuario.id}" type="text" placeholder="Nome" required="required" size="5" readonly="readonly" maxlength="40">
				</div>
			</div>
			<div class="field-usuario">
				<div class="control-usuario">
					<label class="label-usuario">Id </label> 
					<input name="id" id="id" class="input-usuario" value="${usuario.id_usuario}" type="text" placeholder="Nome" required="required" size="5" readonly="readonly" maxlength="40">
				</div>
			</div>
			<div class="field-usuario">
				<div class="control-usuario">
					<label class="label-usuario">Nome </label> 
					<input name="nome" id="nome" class="input-usuario" value="${usuario.nome}" type="text" placeholder="Nome" required="required" readonly="readonly" size="20" maxlength="40">
				</div>
			</div>
	
			<div class="field-usuario">
				<div class="control-usuario">
					<label class="label-usuario">Login </label>
					<input name="login" id="login_" class="input-usuario" value="${usuario.login}" type="text" placeholder="Login" readonly="readonly" size="20" maxlength="40" required="required">
				</div>
			</div>
	
			<div class="field-usuario">
				<div class="control-usuario">
					<label class="label-usuario">Senha </label>
					<input type="password" name="senha" id="senha" class="input-usuario" value="${usuario.senha}" readonly="readonly" size="20" maxlength="40" placeholder="*******" required="required">
				</div>
			</div>
			
			<div class="field-usuario">
				<div class="control-usuario">
					<label class="label-usuario">Confirmar Senha </label>
					<input type="password" name="confSenha" id="confSenha" class="input-usuario" value="${usuario.senha}" readonly="readonly" size="20" maxlength="40" placeholder="*******"  required="required">
				</div>
			</div>
			
			<div class="field-usuario">
				<div class="control-usuario">
					<label></label>
					<input type="checkbox" ${usuario.criar_novo_usuario == true ? 'checked="checked"' : ''} name="criarUsuario" id="criarUsuario" onclick="return false;">
					<label class="label-usuario">Criar Usuário</label>
					
					<input type="checkbox" ${usuario.editar_usuario == true ? 'checked="checked"' : ""} name="editarUsuario" id="editarUsuario" onclick="return false;">
					<label class="label-usuario">Editar Usuário</label>
					
				</div>
			</div>
			
			<div class="field-usuario">
				<div class="control-usuario">
					<input type="checkbox" ${usuario.excluir_usuario == true ? 'checked="checked"' : ""} name="excluirUsuario" id="excluirUsuario" onclick="return false;">
					<label class="label-usuario">Excluir Usuário</label>
				</div>
			</div>
		</div>
			<div class="bt-contato">
				<div class="control-usuario">
					<button class="botao">Excluir</button>
				</div>
			</div>

</form>