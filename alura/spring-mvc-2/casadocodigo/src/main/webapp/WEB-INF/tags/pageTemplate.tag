<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="titulo" required="true"%>
<%@ attribute name="bodyClass" required="false"%>
<%@ attribute name="extraScripts" fragment="true"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link href="casadocodigo/resources/css/cssbase-min.css" rel="stylesheet"
	type="text/css" media="all" />
<link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700'
	rel='stylesheet' />
<link href="casadocodigo/resources/css/fonts.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="/casadocodigo/resources/css/fontello-ie7.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/casadocodigo/resources/css/fontello-embedded.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/casadocodigo/resources/css/fontello.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="/casadocodigo/resources/css/style.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="/casadocodigo/resources/css/layout-colors.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/casadocodigo/resources/css/responsive-style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/casadocodigo/resources/css/guia-do-programador-style.css"
	rel="stylesheet" type="text/css" media="all" />
<link href="/casadocodigo/resources/css/produtos.css" rel="stylesheet"
	type="text/css" media="all" />
<link rel="canonical" href="http://www.casadocodigo.com.br/" />
<link href="/casadocodigo/resources/css/book-collection.css"
	rel="stylesheet" type="text/css" media="all" />
<title>${titulo }-CasadoCódigo</title>
</head>


<body class="${bodyClass }">

	<!-- Adiciona o cabeçalho da página. -->
	<%@include file="/WEB-INF/views/cabecalho.jsp"%>

	<jsp:doBody />

	<!-- Adiciona o rodapé da página. -->
	<%@include file="/WEB-INF/views/rodape.jsp"%>

	<!--  Adiciona um novo fragmento de código nas páginas. 
	Um fragmento não é um elemento obrigatório e pode ser
	customizado em cada uma das páginas. -->
	<jsp:invoke fragment="extraScripts" />

	<!-- Ao contrário do fragmento "extraScripts", o conteúdo abaixo refere-se a scripts que devem existir em todas a páginas. -->
	<script>
		var Shopify = Shopify || {};
		Shopify.shop = "casadocodigo.myshopify.com";
		Shopify.theme = {
			"name" : "8d4b686 - Footer Novo",
			"id" : 169045514,
			"theme_store_id" : null,
			"role" : "main"
		};
		Shopify.theme.handle = "null";
		Shopify.theme.style = {
			"id" : null,
			"handle" : null
		};
	</script>

</body>
</html>