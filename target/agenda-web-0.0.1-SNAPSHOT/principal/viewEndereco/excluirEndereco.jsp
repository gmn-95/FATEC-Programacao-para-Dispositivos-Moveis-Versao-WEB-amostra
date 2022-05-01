<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/javascript"><%@include file="/js/funcoes.js" %></script>

<form class="formulario-endereco" method="post" action="<%=request.getContextPath() %>/ExcluirEndereco">

		
		<div class="forms-endereco">
			<h4>${mensagem}</h4>
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">ID </label> 
					<input name="id" id="id" class="input-endereco" type="text" readonly="readonly" value="${endereco.id}" size="5" maxlength="20">
				</div>
			</div>
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Cep </label> 
					<input name="cep" id="cep" class="input-endereco" type="text" readonly="readonly" value="${endereco.cep}" placeholder="00000-000" required="required" size="10" maxlength="9">
				</div>
			</div>
	
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Bairro </label>
					<input name="bairro" id="bairro" class="input-endereco" type="text" readonly="readonly" value="${endereco.bairro}" placeholder="ex: bairro das flores" required="required" size="30" maxlength="40">
				</div>
			</div>
	
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Cidade </label>
					<input type="text" name="cidade" id="cidade" class="input-endereco" readonly="readonly" value="${endereco.cidade}" size="30" maxlength="30" placeholder="Ex: São Paulo" required="required">
				</div>
			</div>
			
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Estado </label>
					<input name="estado" id="estado" class="input-endereco" readonly="readonly" value="${endereco.estado}" size="30" maxlength="30" placeholder="Ex: São Paulo" required="required">
				</div>
			</div>
			
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Logradouro </label>
					<input name="logradouro" id="logradouro" class="input-endereco" readonly="readonly" value="${endereco.logradouro}" size="30" maxlength="30" placeholder="Ex: Rua exemplo" required="required">
				</div>
			</div>
			
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Número </label>
					<input name="numero" id="numero" class="input-endereco" readonly="readonly" value="${endereco.numero}" size="8" maxlength="12" placeholder="Ex: 1" required="required">
				</div>
			</div>
			
			<div class="field-endereco">
				<div class="control-endereco">
					<label class="label-endereco">Complemento </label>
					<input name="complemento" id="complemento" class="input-endereco" readonly="readonly" value="${endereco.complemento}" size="30" maxlength="30" placeholder="Ex: Particular 1">
				</div>
			</div>
		</div>
		<div class="bt-endereco">
			<div class="control-endereco">
				<button class="botao">Excluir</button>
			</div>
		</div>

</form>