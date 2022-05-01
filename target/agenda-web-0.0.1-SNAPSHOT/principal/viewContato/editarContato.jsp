<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css"><%@include file="/css/style.css" %></style>

<form class="formulario" method="post" action="<%=request.getContextPath() %>/EditarContato">

		
		<div class="forms-contato">
			<h4>${mensagem}</h4>
			<div class="field-contato">
				<div class="control-contato">
					<label class="label-contato">ID </label> 
					<input name="id" class="input-contato" type="text" readonly="readonly" value="${contato.id}"  size="5" maxlength="20">
				</div>
			</div>
			<div class="field-contato">
				<div class="control-contato">
					<label class="label-contato">Nome* </label> 
					<input name="nome" class="input-contato" type="text" placeholder="Nome do contato" required="required" value="${contato.nome}" size="20" maxlength="40">
				</div>
			</div>
	
			<div class="field-contato">
				<div class="control-contato">
					<label class="label-contato">Email </label>
					<input name="email" class="input-contato" type="email" placeholder="Email do contato" value="${contato.email}" size="30" maxlength="40">
				</div>
			</div>
	
			<div class="field-contato">
				<div class="control-contato">
					<label class="label-contato">Celular </label>
					<input type="text" name="celular" class="input-contato" value="${contato.celular}" size="15" maxlength="13" placeholder="Ex: 11-91111-1111" onkeypress="mascaraCampo(this, '##-#####-####'); return somenteNumero(event);">
				</div>
			</div>
			
			<div class="field-contato">
				<div class="control-contato">
					<label class="label-contato">Telefone </label>
					<input name="telefone" class="input-contato" size="15" maxlength="12" value="${contato.telefone_fixo}" placeholder="Ex: 11-9111-1111" onkeypress="mascaraCampo(this, '##-####-####'); return somenteNumero(event);">
				</div>
			</div>
			
			<div class="field-contato">
				<div class="control-contato">
					<label class="label-contato">Obs </label>
					<textarea name="obs" class="textarea" placeholder="Ex: contato" >${contato.obs}</textarea>
				</div>
			</div>
	
		</div>
		<div class="bt-contato">
			<div class="control-contato">
				<button class="botao">Editar</button>
			</div>
		</div>

</form>