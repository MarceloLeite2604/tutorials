<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- Adiciona a biblioteca de tags do nosso sistema. --%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>

<%-- Utiliza as tags criadas no diretório "WEB-VIEW/tags", onde estão os templates de páginas. --%>
<tags:pageTemplate titulo="Produto não encontrado">
	<section id="index-section" class="container middle">
		<!-- <h2>O produto informado não foi encontrado.</h2> -->
		<h2>Erro genérico acontecendo.</h2>

		<!--
		Mensagem: ${exception.message }
		<c:forEach items="${exception.stackTrace }" var="stack">
			${stack }
		</c:forEach> 
		-->
	</section>
</tags:pageTemplate>

