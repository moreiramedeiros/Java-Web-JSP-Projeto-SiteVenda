
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="bean.BeanUsuario" %>
       <%@ page import="bean.BeanProduto" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/formulario.css">
<link rel="stylesheet" href="resources/css/tabela.css">
<link rel="stylesheet" href="resources/css/menu.css">
 <link rel="icon" href="resources/imagem/venda.png">
<title>Cadastro de Produtos</title>

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

 <form action="ServletProduto" method="post" onsubmit="return validarCampos() ? true : false" enctype="multipart/form-data" >
			 
			 <table  style='margin: 0 auto;' >
				 <tr>
	            	<td  style='vertical-align: middle;'>
		              <h2>Cadastro de Produtos	</h2>	
		          	</td>
		          	<td>
		          	<img alt="login" src="resources/imagem/produto.png" width="80px" height="80px">		          	
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
	 	    <input type="hidden" id="id" name="id" value="${produto.id}"> 
			Nome*
			<input type="text" id="nome" name="nome" placeholder="Nome" value="${produto.nome}" maxlength="70"> 
			
			Categoria
			<select  id="categoria" name="categoria" >
			   <option value="Sem categoria" > Sem categoria </option>  
			   <option value="Alimento"
			   	  <%
			   	    BeanProduto beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Alimento")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Alimento </option>
			   <option value="Automotivo"
			   	  <%
			   	     beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Automotivo")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Automotivo </option>			   
			   <option value="Brinquedo"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Brinquedo")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Brinquedo </option> 	 
			   <option value="Celular"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Celular")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Celular </option> 
              <option value="Eletrodoméstico"  			  
			   <%
		   	   beanProduto = (BeanProduto) request.getAttribute("produto");
		        if(beanProduto!=null){
		    	   if(beanProduto.getCategoria().equals("Eletrodoméstico")){
		    		   out.print(" selected ");
		    	   }
		       } 
			   %>			   
			   > Eletrodomestico </option> 
			    <option value="Game"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Game")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Game </option> 
			   <option value="Informática"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Informática")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Informática </option>  
			   <option value="Livro"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Livro")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 	 
			   > Livro </option>  			   
		       <option value="Mídia"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Mídia")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Mídia </option> 	
			   	<option value="Móveis"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Móveis")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Móveis </option> 
			   	 <option value="Roupa"
			   	  <%
			   	   beanProduto = (BeanProduto) request.getAttribute("produto");
			        if(beanProduto!=null){
			    	   if(beanProduto.getCategoria().equals("Roupa")){
			    		   out.print(" selected ");
			    	   }
			       } 
			    %>	 
			   > Roupa </option>    		   		 
			</select>
			
			Valor/Preço (R$)
		   <input type="number" step="any" id="preco" name="preco" placeholder="(R$) Preco " value="${produto.preco}" onKeyDown="limitar(this,15);" >
			
			Descrição 
			<textarea  id="descricao" name="descricao" rows="6" onKeyDown="limitar(this,2000);"   >${produto.descricao}</textarea>   
		         
		     <input type="file" id="foto" name="foto"  > 
	 	</td>

	 	<td>	 
	 		Quantidade de estoque 
			<input type="number" id="quantidade" name="quantidade" placeholder="Quantidade" value="${produto.quantidade}"  onKeyDown="limitar(this,9);"> 
			Altura  (cm)
			<input type="number" step="any"  id="altura" name="altura" placeholder="Altura (cm)" value="${produto.altura}"  onKeyDown="limitar(this,15);">
			Comprimento  (cm)
			<input type="number" step="any"  id="comprimento" name="comprimento" placeholder="Comprimento (cm)" value="${produto.comprimento}"  onKeyDown="limitar(this,15);"> 
			Largura  (cm)
			<input type="number" step="any"  id="largura" name="largura" placeholder="Largura (cm)" value="${produto.largura}"  onKeyDown="limitar(this,15);"> 
			Peso (Kg)
			<input type="number"  step="any" id="peso" name="peso" placeholder="Peso (Kg)" value="${produto.peso}"  onKeyDown="limitar(this,15);"> 
	 	</td>
	 </tr>	 
		</table>
		
		<input type="submit" value="Salvar"> 
		 
	</form>
        <a href="ServletProduto"  onclick="return confirm('Deseja cancelar?')"  ><button>Cancelar</button> </a>
      
</div>








	<script type="text/javascript">

		
		
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
		
		

function limitar(v,t){
	
	if(v.value.length>=t) {  			    
		v.value=v.value.substring(0,t); 
		
	}
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
		<input type="hidden" id="tipo_pesquisa" name="tipo_pesquisa" value="pesquisa_nome_produto_admin"> 
		<input type="submit" value="Pesquisar"> 
		</form>
	</center>
</div>


<!-- Tabela de usuários -->



<table id="customers">
  <tr>
    <th>Id</th>
    <th>Nome</th>
    <th>Imagem</th>
    <th>Categoria</th>
    <th>Quantidade</th>
    <th>Editar</th>
    <th>Excluir</th>
  </tr>
  
  
  <c:forEach items="${produtos}" var="produto">
  <tr>
    <td>${produto.id}</td>
    <td>${produto.nome}</td>
     <td>
       <c:if test="${produto.imagem.isEmpty() == false}">
         <a href="ServletProduto?acao=download&id=${produto.id}">
           <img title="Download" alt="imagem" src="<c:out value="${produto.imagem}"/>" width="40px" height="40px" >
          </a>
        </c:if>
        <c:if test="${produto.imagem.isEmpty() == true || produto.imagem == null }">
           <img src="resources/imagem/logo.png"  width="40px" height="40px" >
        </c:if>  
     </td> 
    <td>${produto.categoria}</td>
    <td>${produto.quantidade}</td>
    <td><a href="ServletProduto?acao=editar&id=${produto.id}">Editar</a></td>
    <td><a href="ServletProduto?acao=excluir&id=${produto.id}"  onclick="return confirm('Deseja excluir?')">Excluir</a></td>
  </tr>
 </c:forEach>
</table>
</body>
</html>

 
</div>

</body>
</html>