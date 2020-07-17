
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="bean.BeanUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/formulario.css">
<link rel="stylesheet" href="resources/css/tabela.css">
<link rel="stylesheet" href="resources/css/menu.css">
 <link rel="icon" href="resources/imagem/venda.png">
<title>Cadastro de Usuário</title>

<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous">
	
</script>


<style>
body {
	background-image: url(resources/imagem/bg.jpg);
	background-size: cover;
	background-attachment: fixed;
	margin:0;
}
</style>
</head>
<body>


    <div class="barra">
      <div class="menu">
        <a href="servletHome" >Voltar</a>   
        <a href="ServletProduto">Produtos</a>
        <a href="ServletUsuario">Usuários</a>
        <a href="ServletPedido">Pedidos</a>
        <a href="ServletPerfilUsuario?acao=sair">Sair</a>
       </div>
    </div>

<div class="conteudo">
<!-- Exibir nome na página  -->
       
 

   <div class="campoPesquisarAdmin">
    <center> 
    <br>
          <%@ page import="bean.BeanUsuario" %>
          
         <%   
  	 	    HttpServletRequest req = (HttpServletRequest) request;
    	    HttpSession session_ = req.getSession();
            BeanUsuario beanUsuario = (BeanUsuario) session_.getAttribute("usuario");             
	        if(beanUsuario!=null){		       
			  out.print("Área do administrador<br>"+beanUsuario.getNome());   			 
	        } 
         %>
    </center> 
   </div>


<!-- Formulário  -->

<div class="formulario">

 <form action="ServletUsuario" method="post" onsubmit="return validarCampos() ? true : false" >
			 
			 <table  style='margin: 0 auto;' >
				 <tr>
	            	<td  style='vertical-align: middle;'>
		              <h2>Cadastro de Usuário	</h2>	
		          	</td>
		          	<td>
		          	<img alt="login" src="resources/imagem/user.png" width="80px" height="80px">		          	
		          	</td>     	
	            	</tr>            	 
				 <table>
	             <center> 
	             	<div style="color:red"> ${msgNaoSucesso} </div>
	             	<div style="color:green"> ${msgSucesso} </div>
	             </center>  
	 <table>
	 <tr>
	 	<td>
	 	    <input type="hidden" id="id" name="id" value="${user.id}"> 
			Login*
			<input type="text" id="login" name="login" placeholder="Login" value="${user.login}" maxlength="70"> 
			Senha*
			<input type="password" id="senha" name="senha" placeholder="Senha" value="${user.senha}" maxlength="45"> 
			Nome*
			<input type="text" id="nome" name="nome" placeholder="Nome" value="${user.nome}" maxlength="70" > 
			Sexo*  
			<div class="radio">
			<input type="radio" id="masculino" name="sexo" value="masculino"
			
				<%
			     beanUsuario = (BeanUsuario) request.getAttribute("user");
			       if(beanUsuario!=null){
			    	   if(beanUsuario.getSexo().equals("masculino")){
			    		   out.print(" checked ");
			    	   }
			       }
			   %>	
 
			> Masculino 
			<input type="radio" id="feminino" name="sexo" value="feminino"
			
			 <%
			       beanUsuario = (BeanUsuario) request.getAttribute("user");
			       if(beanUsuario!=null){
			    	   if(beanUsuario.getSexo().equals("feminino")){
			    		   out.print(" checked ");
			    	   }
			       }
			   %>	
			
			
			
			> Feminino <br>
			</div>
			CPF* 
			<input type="text" id="cpf" name="cpf" placeholder="CPF" value="${user.cpf}" maxlength="45">
			Email*
			<input type="text" id="email" name="email" placeholder="Email" value="${user.email}" maxlength="45"> 
			Telefone
			<input type="text" id="telefone" name="telefone" placeholder="Telefone" value="${user.telefone}" maxlength="45"> 
			Celular
			<input type="text" id="celular" name="celular" placeholder="Celular" value="${user.celular}" maxlength="45"> 		
	 	</td>

	 	<td>	 
	 		CEP 
			<input type="text" id="cep" name="cep" placeholder="CEP" value="${user.cep}"  onblur="consultaCep()" maxlength="45" > 
			Rua 
			<input type="text" id="rua" name="rua" placeholder="Rua" value="${user.rua}" maxlength="45"> 
			Número 
			<input type="text" id="numero" name="numero" placeholder="Número" value="${user.numero}" maxlength="45"> 
			Bairro 
			<input type="text" id="bairro" name="bairro" placeholder="Bairro" value="${user.bairro}" maxlength="45"> 
			Cidade
			<input type="text" id="cidade" name="cidade" placeholder="Cidade" value="${user.cidade}" maxlength="45"> 
			Estado
			<input type="text" id="estado" name="estado" placeholder="Estado" value="${user.estado}" maxlength="45"> 
		    Nível acesso
			<select  id="nivel_acesso" name="nivel_acesso" >
			   <option value="usuario"  
			  
			   <%
			       beanUsuario = (BeanUsuario) request.getAttribute("user");
			       if(beanUsuario!=null){
			    	   if(beanUsuario.getNivel_acesso().equals("usuario")){
			    		   out.print(" selected ");
			    	   }
			       }
			   %>			   
			   > Usuário </option>  
			
			   <option value="administrador"
			   	  <%
			         beanUsuario = (BeanUsuario) request.getAttribute("user");
			        if(beanUsuario!=null){
			    	   if(beanUsuario.getNivel_acesso().equals("administrador")){
			    		   out.print(" selected ");
			    	   }
			       }
			   %>	
			   
			   > Administrador </option>  
			</select>			 
			<input type="hidden" id="pagina" name="pagina" value="admin">
	 	</td>
	 </tr>	 
		</table>
		
		<input type="submit" value="Salvar"> 
		 
	</form>
        <a href="ServletUsuario"  onclick="return confirm('Deseja cancelar?')"  ><button>Cancelar</button> </a>
      
</div>








	<script type="text/javascript">


		//Consultando CEP com jQuery

		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						if (!("erro" in dados)) {
							//Atualiza os campos com os valores da consulta.
							$("#rua").val(dados.logradouro);
							$("#bairro").val(dados.bairro);
							$("#cidade").val(dados.localidade);
							$("#estado").val(dados.uf);
							$("#ibge").val(dados.ibge);
						} //end if.
						else {
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							//CEP pesquisado não foi encontrado.
							alert("CEP não encontrado.");
						}
					});

		}
		
		
function validarCampos() {
	       
			
			if(document.getElementById("feminino").checked == false && document.getElementById("masculino").checked == false){
	    		alert("Informe o sexo!");
	    		return false;
	       } 
			
			if (document.getElementById("login").value == '') {
				alert("Informe o login!");
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert("Informe o senha!");
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert("Informe a nome!");
				return false;
			} else if (document.getElementById("cpf").value == '') {
				alert("Informe o CPF!");
				return false;
			} else if (document.getElementById("email").value == '') {
				alert("Informe o email!");
				return false;
			} 

			return true;
		}
		
	</script>











      <div class="campoPesquisarAdmin">
        <center> <h2>Lista de Usuários</h2>	 </center>
     </div>

<!-- Pesquisar nome -->

<div class="campoPesquisarAdmin" >
	<center>
		<form action="ServletPesquisa"  method="post"  > 
  	 	 Nome: 
		<input type="text" id="descricao" name="descricao" placeholder="nome"> 
		<input type="hidden" id="tipo_pesquisa" name="tipo_pesquisa" value="pesquisa_nome_usuario"> 
		<input type="submit" value="Pesquisar"> 
		</form>
	</center>
</div>


<!-- Tabela de usuários -->



<table id="customers">
  <tr>
    <th>Id</th>
    <th>Login</th>
    <th>Nome</th>
    <th>CPF</th>
    <th>Nivel de acesso</th>
    <th>Editar</th>
    <th>Excluir</th>
  </tr>
  
  
  <c:forEach items="${usuarios}" var="user">
  <tr>
    <td>${user.id}</td>
    <td>${user.login}</td>
    <td>${user.nome}</td>
    <td>${user.cpf}</td>
    <td>${user.nivel_acesso}</td>
  
    <td><a href="ServletUsuario?acao=editar&id=${user.id}">Editar</a></td>
    <td><a href="ServletUsuario?acao=excluir&id=${user.id}"  onclick="return confirm('Deseja excluir?')">Excluir</a></td>
  </tr>
 </c:forEach>
</table>


</div>

</body>
</html>

 