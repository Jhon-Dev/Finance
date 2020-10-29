<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adicionar Contas</title>

 
<link rel="stylesheet" href="resources/css/cadastro.css">
</head>
<body>


	<center>
		<h1>Cadastro de Contas</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>

	<form action="servletContas" method="post" id="formContas"
		onsubmit="return validarCampos() ? true : false;">
		<ul class="form-style-1">
			<li>
				<table>
					<tr>
						<td>Código:</td>
						<td><input type="text" readonly="readonly" id="id" name="id" placeholder = "ID"
							value="${contas.id}" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome:</td>
						<td><input type="text" id="nome" name="nome"placeholder = "nome"  maxlength="100" value="${contas.nome}"></td>
					</tr>

					<tr>
						<td>Valor R$:</td>
						<td><input type="text" id="valor" name="valor"placeholder = "valor" maxlength="8" data-thousands="." data-decimal="," data-precision="2"
						value="${contas.valor}"></td>	
					<tr>
					<tr>
					<tr>
						<td></td>
						<td>
						<input type="submit" value="Salvar" style="width: 90px"> 
						<input style="width: 90px" type="submit" value="Cancelar" onclick="document.getElementById('formContas').action = 'servletContas?acao=reset'"></td>
					</tr>
				</table>

			</li>
		</ul>
	</form>

	<div class="container">
		<table class="responsive-table">
			<caption>Lista de contas</caption>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Valor</th>
				<th>Delete</th>
				<th>Editar</th>


			</tr>

			<c:forEach items="${contas}" var="contas">
				<tr>

					<td style="width: 150px"><c:out value="${contas.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${contas.nome}"></c:out></td>
										
					<td><fmt:formatNumber type="number" maxFractionDigits="3" value="${contas.valor}"/></td>

					<td><a onclick="return confirm('Confirmar a exclusão?');" href="servletContas?acao=delete&contas=${contas.id}"><img
							src="resources/img/Excluir.png" alt="Exluir" title="Exluir"
							width="20px" height="20px"></a></td>
					<td><a href="servletContas?acao=editar&contas=${contas.id}"><img
							src="resources/img/Editar.png" alt="Editar" title="Editar"
							width="20px" height="20px"></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert('Informe o Nome');
				return false;
			} else if (document.getElementById("quantidade").value == '') {
				alert('Informe o Quantidade');
				return false;
			} else if (document.getElementById("valor").value == '') {
				alert('Informe o Valor R$');
				return false;
			}
			return true;
		}
	</script>
<script type="text/javascript">

$(function() {
    $('#valor').maskMoney();
 });
 
 
 $(document).ready(function() {
  $("#quantidade").keyup(function() {
      $("#quantidade").val(this.value.match(/[0-9]*/));
  });
});

</script>
