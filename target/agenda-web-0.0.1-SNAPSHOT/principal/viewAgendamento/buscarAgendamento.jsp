<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/javascript"><%@include file="/js/funcoes.js" %></script>

	<form method="post" action="<%=request.getContextPath() %>/BuscarAgendamento" onsubmit="return validaCampo(this);">
	
		<div class="campo">
			
				<select name="tipoPesquisa" id="tipoPesquisa">
					<option value="idAgendamento" selected="selected">Id agendamento</option>
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
					<th scope="col">Descrição</th>
					<th scope="col">Conteúdo</th>
					<th scope="col">Data</th>
					<th scope="col">Horário</th>
					<th class="tamId" scope="col">ID Contato</th>
					<th scope="col">Nome do Contato</th>
					<th class="tamE" scope="col">Editar</th>
					<th class="tamE" scope="col">Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${agendamento.contato.id != null}">
					<tr>
						<td><c:out value="${agendamento.id}"></c:out></td>
						<td><c:out value="${agendamento.descricao}"></c:out></td>
						<td><c:out value="${agendamento.conteudo}"></c:out></td>
						<td><fmt:formatDate value="${agendamento.data_agendada}" type="date" pattern="dd/MM/yyyy"/></td>
						<td><fmt:formatDate value="${agendamento.hora_agendada}" type="date" pattern="HH:mm"/></td>
						<td><c:out value="${agendamento.contato.id}"></c:out></td>
						<td><c:out value="${agendamento.contato.nome}"></c:out></td>
						<td><a class="editar" href="<%=request.getContextPath() %>/BuscarEnderecoContato?acao=editar&id=${ec.id}&idContato=${ec.contato.id}&idEndereco=${ec.endereco.id}">Editar</a></td>
						<td><a class="excluir" href="<%=request.getContextPath() %>/BuscarEnderecoContato?acao=excluir&id=${ec.id}">Excluir</a></td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</form>
