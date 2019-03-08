<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" ng-app="crudApp">
<head>
<title>${title}</title>
<link href="css/bootstrap.css" rel="stylesheet" />
<link href="css/app.css" rel="stylesheet" />
</head>
<body>

	<div id="main">
		<div class="fof">
			<h1> 404 not found</h1>
		</div>
	</div>

	<style>
* {
	transition: all 0.6s;
}

html {
	height: 100%;
}

body {
	font-family: 'Lato', sans-serif;
	color: #888;
	margin: 0;
}

#main {
	display: table;
	width: 100%;
	height: 100vh;
	text-align: center;
}

.fof {
	display: table-cell;
	vertical-align: middle;
}

.fof h1 {
	font-size: 50px;
	display: inline-block;
	padding-right: 12px;
	animation: type .5s alternate infinite;
}

@
keyframes type {
	from {box-shadow: inset -3px 0px 0px #888;
}

to {
	box-shadow: inset -3px 0px 0px transparent;
}
}
</style>
</body>
</html>