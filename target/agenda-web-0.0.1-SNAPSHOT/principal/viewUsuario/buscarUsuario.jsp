<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>

	<form  method="post" action="<%=request.getContextPath() %>/BuscarUsuario">
	
		<div class="campo">
			<select name="tipoPesquisa" id="tipoPesquisa">
				<option value="idUsuario" selected="selected">Id usuário</option>
			</select>
		
			<input type="number" name="valorProcurado" id="valorProcurado" size="40" maxlength="40">
	        <button class="botao" type="submit" name="btPesquisar" value="Pesquisar"> Pesquisar </button>
	    </div>
	     <br>
	
		<h4 class="mensagem">${mensagem}</h4>
		<table class="tabela">
			<thead>
				<tr>
					<th scope="col">ID Usuário</th>
					<th scope="col">ID Pessoa</th>
					<th scope="col">Nome</th>
					<th scope="col">Login</th>
					<th scope="col">Editar</th>
					<th scope="col">Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${usuario != null && usuario.id != null}">
					<tr>
						<td><c:out value="${usuario.id_usuario}"></c:out></td>
						<td><c:out value="${usuario.id}"></c:out></td>
						<td><c:out value="${usuario.nome}"></c:out></td>
						<td><c:out value="${usuario.login}"></c:out></td>
						<c:choose>
							<c:when test="${usuarioLogado.editar_usuario == true}">
								<td><a class="editar" href="<%=request.getContextPath() %>/BuscarUsuario?acao=editar&id=${usuario.id_usuario}">Editar</a></td>
							</c:when>
							<c:when test="${usuarioLogado.editar_usuario == false && usuarioLogado.id_usuario == usuario.id_usuario}">
								<td><a class="editar" href="<%=request.getContextPath() %>/BuscarUsuario?acao=editar&id=${usuario.id_usuario}">Editar</a></td>
							</c:when>
							<c:otherwise>
								<td>Sem permissão para alterar este usuário</td>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${usuarioLogado.excluir_usuario == true}">
								<td><a class="excluir" href="<%=request.getContextPath() %>/BuscarUsuario?acao=editar&id=${usuario.id_usuario}">Excluir</a></td>
							</c:when>
							<c:when test="${usuarioLogado.excluir_usuario == false && usuarioLogado.id_usuario == usuario.id_usuario}">
								<td><a class="excluir" href="<%=request.getContextPath() %>/BuscarUsuario?acao=editar&id=${usuario.id_usuario}">Excluir</a></td>
							</c:when>
							<c:otherwise>
								<td>Sem permissão para excluir este usuário</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>

