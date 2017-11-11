<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, iPhone, PHP e muito mais - Casa
	do Código</title>
</head>
<body>
	<!-- Conteúdo do "action": O script tenta resolver o nome das classes de 
	acordo com as letras maiúsculas. Logo, quando informamos "PC" no método 
	"mvcUrl", o Spring consegue resolver como "ProdutorController". -->
	<!-- Conteúdo do "commandName": Para evitar que, em todos os campos "path"
	 das tags "form:errors", seja necessário adicionar o prefixo "produto", 
	 basta informar neste campo qual o objeto que está sendo manipulado
	 (cadastrado) no formulário. -->
	<form:form action="${s:mvcUrl('PC#gravar').build() }" method="POST"
		commandName="produto" enctype="multipart/form-data">
		<div>
			<label>Título</label>
			<form:input path="titulo" />
			<form:errors path="titulo" />
		</div>
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao" rows="10" cols="20" />
			<form:errors path="descricao" />
		</div>
		<div>
			<label>Páginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas" />
		</div>
		<div>
			<label>Data de lançamento</label>
			<form:input path="dataLancamento" />
			<form:errors path="dataLancamento" />
		</div>

		<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor" />
				<form:input type="hidden" path="precos[${status.index}].tipo"
					value="${tipoPreco}" />
			</div>
		</c:forEach>

		<div>
			<label>Sumário</label> <input name="sumario" type="file" />
		</div>

		<button type="submit">Cadastrar</button>
	</form:form>
</body>
</html>