<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css"><%@include file="/css/style.css" %></style>


<form method="post" action="<%=request.getContextPath() %>/ListarContatos">
		
		<div class="campo">
			
			<select name="tipoPesquisa" id="tipoPesquisa" onchange="desabilitaInput()">
				<option value="Todos" selected="selected">Todos</option>
				<option value="Nome">Nome</option>
				<option value="Telefone">Telefone</option>
				<option value="Celular">Celular</option>
				<option value="Email">Email</option>
				<option value="Obs">Obs</option>
			</select>
		
			<input type="text" name="valorProcurado" id="valorProcurado" size="40" maxlength="40">
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
				<c:forEach items="${contatos}" var="cont">
					<tr>
						<td><c:out value="${cont.id}"></c:out></td>
						<td><c:out value="${cont.nome}"></c:out></td>
						<td><c:out value="${cont.telefone_fixo}"></c:out></td>
						<td><c:out value="${cont.celular}"></c:out></td>
						<td><c:out value="${cont.email}"></c:out></td>
						<td><c:out value="${cont.obs}"></c:out></td>
						<td><a class="editar" href="<%=request.getContextPath() %>/ContatoServletBuscar?acao=editar&id=${cont.id}">Editar</a></td>
						<td><a class="excluir" href="<%=request.getContextPath() %>/ContatoServletBuscar?acao=excluir&id=${cont.id}">Excluir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>