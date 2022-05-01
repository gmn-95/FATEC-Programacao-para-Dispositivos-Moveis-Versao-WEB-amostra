<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/javascript"><%@include file="/js/funcoes.js" %></script>


<form method="post" action="<%=request.getContextPath() %>/BuscarEndereco" onsubmit="return validaCampo(this);">

	<div class="campo">
		
			<select name="tipoPesquisa" id="tipoPesquisa">
				<option value="idEndereco" selected="selected">Id do endereço</option>
			</select>
		
		<input type="number" name="valorProcurado" id="valorProcurado" size="40" maxlength="40">
        <button class="botao" type="submit" name="btPesquisar" value="Pesquisar"> Pesquisar </button>
    </div>
     <br>
	<h4 class="mensagem">${mensagem}</h4>
	<table class="tabela">
		<thead>
			<tr>
				<th class="tamId" scope="col">ID</th>
				<th scope="col">Bairro</th>
				<th scope="col">Cidade</th>
				<th scope="col">Estado</th>
				<th scope="col">Cep</th>
				<th scope="col">Logradouro</th>
				<th class="tamNum" scope="col">Número</th>
				<th scope="col">Complemento</th>
				<th class="tamE" style="width: 100px;" scope="col">Editar</th>
				<th class="tamE" style="width: 100px;" scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${endereco != null}">
				<tr>
					<td><c:out value="${endereco.id}"></c:out></td>
					<td><c:out value="${endereco.bairro}"></c:out></td>
					<td><c:out value="${endereco.cidade}"></c:out></td>
					<td><c:out value="${endereco.estado}"></c:out></td>
					<td><c:out value="${endereco.cep}"></c:out></td>
					<td><c:out value="${endereco.logradouro}"></c:out></td>
					<td><c:out value="${endereco.numero}"></c:out></td>
					<td><c:out value="${endereco.complemento}"></c:out></td>
					<td><a class="editar" href="<%=request.getContextPath() %>/EnderecoServletBuscar?acao=editar&id=${endereco.id}">Editar</a></td>
					<td><a class="excluir" href="<%=request.getContextPath() %>/EnderecoServletBuscar?acao=excluir&id=${endereco.id}">Excluir</a></td>
				</tr>
			</c:if>
		</tbody>
	</table>
</form>
