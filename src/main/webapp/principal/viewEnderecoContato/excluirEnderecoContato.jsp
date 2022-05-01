<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>


<form class="formulario-enderecoContato" method="post" action="<%=request.getContextPath() %>/ExcluirEnderecoContato">
	
	<div class="forms-enderecoContato">
		<h4>${mensagem}</h4>
		
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
						
				<label class="label-enderecoContato">Id: </label>
				<input class="input-enderecoContato" type="text" name="id" id="id" readonly="readonly" value="${enderecoContato.id}" size="5" maxlength="20">
			
			</div>
		</div>
		
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
			
				<label class="label-enderecoContato">Contato: </label>
				
				<select name="idContato" id="tipoPesquisa-contato">
					<option value="${enderecoContato.contato.id}">
						<c:out value="${enderecoContato.contato.nome}"></c:out>
					</option>
				</select>
				
			</div>
		</div>
		
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
			
				<label class="label-enderecoContato">Endereço: </label>
				
				<select name="idEndereco" id="tipoPesquisa-endereco">
					<option value="${enderecoContato.endereco.id}">
						<c:out value="${enderecoContato.endereco.bairro}"></c:out>, 
						<c:out value="${enderecoContato.endereco.cidade}"></c:out>, 
						<c:out value="${enderecoContato.endereco.estado}"></c:out>,
						<c:out value="${enderecoContato.endereco.cep}"></c:out>, 
						<c:out value="${enderecoContato.endereco.logradouro}"></c:out>,
						<c:out value="${enderecoContato.endereco.numero}"></c:out>,
						<c:out value="${enderecoContato.endereco.complemento}"></c:out> 
					</option>
				</select>
			</div>
		</div>
		
		
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
						
				<label class="label-enderecoContato">Obs: </label>
				<input class="input-enderecoContato" type="text" readonly="readonly" name="Obs" id="Obs" placeholder="Observação..." value="${enderecoContato.obs}" size="20" maxlength="40">
			
			</div>
		</div>
		
	</div>
	<div class="bt-enderecoContato">
		<div class="control-enderecoContato">
			<button class="botao">Excluir</button>
		</div>
	</div>
</form>
