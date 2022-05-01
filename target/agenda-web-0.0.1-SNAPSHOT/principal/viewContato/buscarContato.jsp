<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/javascript"><%@include file="/js/funcoes.js" %></script>


<form method="post" action="<%=request.getContextPath() %>/BuscarContato" onsubmit="return validaCampo(this);">
		
	<div class="campo">
		
		<select name="tipoPesquisa" id="tipoPesquisa">
			<option value="idContato" selected="selected">Id do contato</option>
		</select>
	
		<input type="number" name="valorProcurado" id="valorProcurado" size="40" maxlength="40" >
        <button class="botao" type="submit" name="btPesquisar" value="Pesquisar"> Pesquisar </button>
    </div>
     <br>

	<h4 class="mensagem">${mensagem}</h4>
	<table class="tabela">
		<thead>
			<tr>
				<th class="tamId" scope="col">ID</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col">Celular</th>
					<th scope="col">Email</th>
					<th scope="col">Obs</th>
					<th class="tamE" scope="col">Editar</th>
					<th class="tamE" scope="col">Excluir</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${contato != null}">
			<tr>
				<td><c:out value="${contato.id}"></c:out></td>
				<td><c:out value="${contato.nome}"></c:out></td>
				<td><c:out value="${contato.telefone_fixo}"></c:out></td>
				<td><c:out value="${contato.celular}"></c:out></td>
				<td><c:out value="${contato.email}"></c:out></td>
				<td><c:out value="${contato.obs}"></c:out></td>
				<td><a class="editar" href="<%=request.getContextPath() %>/ContatoServletBuscar?acao=editar&id=${contato.id}">Editar</a></td>
				<td><a class="excluir" href="<%=request.getContextPath() %>/ContatoServletBuscar?acao=excluir&id=${contato.id}">Excluir</a></td>
			</tr>
		</c:if>
		</tbody>
	</table>
</form>

