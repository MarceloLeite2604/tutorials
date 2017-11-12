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
<!--  Tomar cuidado com os paths relativos. Neste caso, a página do 
formulário vem de uma subpasta "produtos". Com isto, se colocarmos fixo 
o endereço na tag, o servidor irá tentar buscar a partir da pasta
"produtos". A solução é deixar o core do JSF resolver o endereço. -->
<c:url value="/resources/css" var="cssPath" />
<link rel="stylesheet" href="${cssPath}/bootstrap.min.css" />
<link rel="stylesheet" href="${cssPath}/bootstrap-theme.min.css" />
<!-- <script src="resources/js/bootstrap.min.js" /> -->
<style type="text/css">
body {
	/* Isto fará com que as margens superior e inferior 
	sejam de 60 pixels e que as margens esquerda e direita sejam de 0 pixels. */
	padding: 60px 0px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${s:mvcUrl('HC#index').build() }">Casa do Código</a>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${s:mvcUrl('PC#listar').build() }">Lista de
							Produtos</a></li>
					<li><a href="${s:mvcUrl('PC#form').build() }">Cadastro de
							Produtos</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
	</nav>

	<div class="container">
		<h1>Cadastro de produto</h1>

		<!-- Conteúdo do "action": O script tenta resolver o nome das classes de 
	acordo com as letras maiúsculas. Logo, quando informamos "PC" no método 
	"mvcUrl", o Spring consegue resolver como "ProdutorController". -->
		<!-- Conteúdo do "commandName": Para evitar que, em todos os campos "path"
	 das tags "form:errors", seja necessário adicionar o prefixo "produto", 
	 basta informar neste campo qual o objeto que está sendo manipulado
	 (cadastrado) no formulário. -->
		<form:form action="${s:mvcUrl('PC#gravar').build() }" method="POST"
			commandName="produto" enctype="multipart/form-data">
			<div class="form-group">
				<label>Título</label>
				<form:input path="titulo" cssClass="form-control" />
				<form:errors path="titulo" />
			</div>
			<div class="form-group">
				<label>Descrição</label>
				<form:textarea path="descricao" cssClass="form-control" />
				<form:errors path="descricao" />
			</div>
			<div class="form-group">
				<label>Páginas</label>
				<form:input path="paginas" cssClass="form-control" />
				<form:errors path="paginas" />
			</div>
			<div class="form-group">
				<label>Data de lançamento</label>
				<form:input path="dataLancamento" cssClass="form-control" />
				<form:errors path="dataLancamento" />
			</div>

			<c:forEach items="${tipos}" var="tipoPreco" varStatus="status">
				<div class="form-group">
					<label>${tipoPreco}</label>
					<form:input path="precos[${status.index}].valor"
						cssClass="form-control" />
					<form:input type="hidden" path="precos[${status.index}].tipo"
						value="${tipoPreco}" />
				</div>
			</c:forEach>

			<div class="form-group">
				<label>Sumário</label> <input name="sumario" type="file"
					class="form-control" />
			</div>

			<button type="submit" class="btn btn-primary">Cadastrar</button>
		</form:form>
	</div>
</body>
</html>