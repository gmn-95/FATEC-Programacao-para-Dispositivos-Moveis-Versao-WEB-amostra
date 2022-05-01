<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>

	<form method="post" action="<%=request.getContextPath() %>/LitarAgendamentos">
	
		<div class="campo">
			
				<select name="tipoPesquisa" id="tipoPesquisa" onchange="desabilitaInput()">
					<option value="Todos" selected="selected">Todos</option>
					<option value="Hora">Hora</option>
					<option value="Data">Data</option>
					<option value="Descricao">Descrição</option>
					<option value="Conteudo">Conteúdo</option>
					<option value="Contato">Nome contato</option>
					<option value="Id contato">Id contato</option>
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
				<c:forEach items="${agendamentos}" var="agendamento">
					<tr>
						<td><c:out value="${agendamento.id}"></c:out></td>
						<td><c:out value="${agendamento.descricao}"></c:out></td>
						<td><c:out value="${agendamento.conteudo}"></c:out></td>
						<td><fmt:formatDate value="${agendamento.data_agendada}" type="date" pattern="dd/MM/yyyy"/></td>
						<td><fmt:formatDate value="${agendamento.hora_agendada}" type="date" pattern="HH:mm"/></td>
						<td><c:out value="${agendamento.contato.id}"></c:out></td>
						<td><c:out value="${agendamento.contato.nome}"></c:out></td>
						<td><a class="editar" href="<%=request.getContextPath() %>/AgendamentoServletBuscar?acao=editar&id=${agendamento.id}&idContato=${agendamento.contato.id}">Editar</a></td>
						<td><a class="excluir" href="<%=request.getContextPath() %>/AgendamentoServletBuscar?acao=excluir&id=${agendamento.id}">Excluir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
