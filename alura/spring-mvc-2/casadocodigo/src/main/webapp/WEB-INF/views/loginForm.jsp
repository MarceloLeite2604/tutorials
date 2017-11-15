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
	<div class="container">
		<h1>Login da Casa do Código</h1>

		<form:form servletRelativeAction="/login" method="POST">
			<div class="form-group">
				<label>E-mail</label> <input name="username" type="text"
					class="form-control" />
			</div>
			<div class="form-group">
				<label>Senha</label> <input name="password" type="password" class="form-control" />
			</div>
			<button type="submit" class="btn btn-primary">Logar</button>
		</form:form>
	</div>
</body>
</html>