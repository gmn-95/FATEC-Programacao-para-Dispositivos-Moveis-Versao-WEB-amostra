<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>

<form class="formulario" method="post" action="<%=request.getContextPath() %>/ExcluirAgendamento">

		
		<div class="forms-agendamento">
			<h4>${mensagem}</h4>
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Id </label> 
					<input name="id" class="input-agendamento_" value="${agendamento.id}" type="number" readonly="readonly" maxlength="40">
				</div>
			</div>
			
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Data </label> 
					<input name="data" class="input-agendamento" value="${agendamento.data_agendada}" type="date" readonly="readonly">
				</div>
			</div>
	
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Horário </label>
					<input name="hora" class="input-agendamento" value="<fmt:formatDate value="${agendamento.hora_agendada}" type="date" pattern="HH:mm"/>" type="time" readonly="readonly">
				</div>
			</div>
	
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Descrição </label>
					<input type="text" name="descricao" class="input-agendamento" value="${agendamento.descricao}" size="30" maxlength="40" placeholder="Ex: almoço" readonly="readonly">
				</div>
			</div>
			
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Contato </label>
					<select name="idContato" id="tipoPesquisa-contato" style="font-size: 15px;">
						<option value="${agendamento.contato.id}" selected="selected" >
							<c:out value="${agendamento.contato.nome}"></c:out>
						</option>
					</select>
				</div>
			</div>
			
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Conteúdo </label>
					<textarea name="conteudo" class="textarea" readonly="readonly" placeholder="Ex: almoço em casa" >${agendamento.conteudo}</textarea>
				</div>
			</div>
		</div>
			<div class="bt-agendamento">
				<div class="control-agendamento">
					<button class="botao">Excluir</button>
				</div>
			</div>

</form>