<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style type="text/css"><%@include file="/css/style.css" %></style>
<script type="text/js"><%@include file="/js/funcoes.js"%></script>

<form class="formulario" method="post" action="<%=request.getContextPath() %>/CriarAgendamento" onsubmit="return validaContatoSelecionado();">

		
		<div class="forms-agendamento">
			<h4>${mensagem}</h4>
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Data </label> 
					<input name="data" class="input-agendamento" type="date" required="required">
				</div>
			</div>
	
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Horário </label>
					<input name="hora" class="input-agendamento" type="time" required="required">
				</div>
			</div>
	
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Descrição </label>
					<input type="text" name="descricao" class="input-agendamento" size="30" maxlength="40" placeholder="Ex: almoço">
				</div>
			</div>
			
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Contato </label>
					<select name="idContato" id="tipoPesquisa-contato" style="font-size: 15px;">
						<option value="Selecione" selected="selected">Selecione</option>
						<c:forEach items="${contatos}" var="contato">
							<option value="${contato.id}">
								<c:out value="${contato.nome}"></c:out>
							</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="field-agendamento">
				<div class="control-agendamento">
					<label class="label-agendamento">Conteúdo </label>
					<textarea name="conteudo" class="textarea" placeholder="Ex: almoço em casa" ></textarea>
				</div>
			</div>
		</div>
			<div class="bt-agendamento">
				<div class="control-agendamento">
					<button class="botao">Criar</button>
					<button type="reset" class="botao">Limpar</button>
				</div>
			</div>

</form>