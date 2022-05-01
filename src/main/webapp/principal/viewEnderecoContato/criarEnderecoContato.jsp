<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>


<form class="formulario-enderecoContato" method="post" action="<%=request.getContextPath() %>/CriarEnderecoContato"  onsubmit="return validaContatoSelecionado();">
	
	<div class="forms-enderecoContato">
		<h4>${mensagem}</h4>
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
			
				<label class="label-enderecoContato">Contato: </label>
				
				<select name="idContato" id="tipoPesquisa-contato" >
				<option value="Selecione" selected="selected">Selecione</option>
				<c:forEach items="${contatos}" var="contato">
					<option value="${contato.id}">
						<c:out value="${contato.nome}"></c:out>
					</option>
				</c:forEach>
				</select>
				
				<label class="label-enderecoContato">Endereço: </label>
				
				<select name="idEndereco" id="tipoPesquisa-endereco" style="width: 400px;">
				<option value="Selecione" selected="selected">Selecione</option>
				<c:forEach items="${enderecos}" var="endereco">
					<option value="${endereco.id}">
						<c:out value="${endereco.bairro}"></c:out>, 
						<c:out value="${endereco.cidade}"></c:out>, 
						<c:out value="${endereco.estado}"></c:out>,
						<c:out value="${endereco.cep}"></c:out>, 
						<c:out value="${endereco.logradouro}"></c:out>,
						<c:out value="${endereco.numero}"></c:out>,
						<c:out value="${endereco.complemento}"></c:out> 
					</option>
				</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
						
				
			</div>
		</div>
		
		<div class="field-enderecoContato">
			<div class="control-enderecoContato">
						
				<label class="label-enderecoContato">Obs: </label>
				<input class="input-enderecoContato" type="text" name="Obs" id="Obs" placeholder="Observação..." size="20" maxlength="40">
			
			</div>
		</div>
		
	</div>
	<div class="bt-enderecoContato">
		<div class="control-enderecoContato">
			<button class="botao">Criar</button>
			<button type="reset" class="botao">Limpar</button>
		</div>
	</div>
	

</form>
