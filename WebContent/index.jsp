<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Meu site de vendas</title>
<link rel="stylesheet" href="resources/css/login.css">
 <link rel="icon" href="resources/imagem/venda.png">
<style>
body {
	margin:0;
	background-image: url(resources/imagem/bg.jpg);
	height: 1000px;
	background-size: cover;
	background-attachment: fixed;
}
</style>
</head>
<body>
   
 
	<div class="login">
		<form action="ServletLogin" method="post">
			<center>
				<img alt="login" src="resources/imagem/login.png" width="80px" height="80px">
				<br>Usuário
				<div style="color:red"> ${msgNaoSucesso} </div>
				<div style="color:green"> ${msgSucesso} </div>
			</center>
			Login / email
			<input type="text" id="login" name="login" placeholder="login" value="${login}"> 
			Senha 
			<input type="password" id="senha" name="senha" placeholder="senha" value="${senha}"> 
			<input type="submit" value="Entrar">
		    <center><a style=" text-decoration: none; color: white; " 
		      onmouseout="style.color='white'"
		      onmouseover="style.color='#325a99'"
		      href="autoCadastroUsuario.jsp"> 	   
		     Criar uma nova conta
		   </a>
 
		   </center>  
		</form>
	</div>

</body>
</html>