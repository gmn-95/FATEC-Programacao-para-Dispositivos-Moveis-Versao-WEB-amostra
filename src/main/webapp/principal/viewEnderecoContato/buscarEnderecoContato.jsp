<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>


	<form method="post" action="<%=request.getContextPath() %>/BuscarEnderecoContato" onsubmit="return validaCampo(this);">
	
		<div class="campo">
				<select name="tipoPesquisa" id="tipoPesquisa">
					<option value="id" selected="selected">Id</option>
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
					<th scope="col">Nome do Contato</th>
					<th scope="col">Bairro</th>
					<th scope="col">Cidade</th>
					<th scope="col">Estato</th>
					<th scope="col">Cep</th>
					<th scope="col">Rua</th>
					<th class="tamNum" scope="col">Número</th>
					<th scope="col">Complemento</th>
					<th scope="col">Obs</th>
					<th class="tamE" scope="col">Editar</th>
					<th class="tamE" scope="col">Excluir</th>
				</tr>
			</thead>
			<tbody>
			<c:if test="${enderecoContato != null}">
				<tr>
					<td><c:out value="${enderecoContato.id}"></c:out></td>
					<td><c:out value="${enderecoContato.contato.nome}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.bairro}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.cidade}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.estado}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.cep}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.logradouro}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.numero}"></c:out></td>
					<td><c:out value="${enderecoContato.endereco.complemento}"></c:out></td>
					<td><c:out value="${enderecoContato.obs}"></c:out></td>
					<td><a class="editar" href="<%=request.getContextPath() %>/BuscarEnderecoContato?acao=editar&id=${enderecoContato.id}&idContato=${enderecoContato.contato.id}&idEndereco=${enderecoContato.endereco.id}">Editar</a></td>
					<td><a class="excluir" href="<%=request.getContextPath() %>/BuscarEnderecoContato?acao=excluir&id=${enderecoContato.id}">Excluir</a></td>
				</tr>
			</c:if>
			</tbody>
		</table>
	</form>
