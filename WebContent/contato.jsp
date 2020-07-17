
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
<title>Perfil do Usuário</title>

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


  <!-- Cabeçalho-->  
  <div class="cabecalho">
      <center>
        <img alt="Logo" src="resources/imagem/venda.png" width="50px"  height="50px"> 
            MEU SITE DE VENDAS 
       </center>
  </div>

    <!-- Menu-->  
   
    <div class="barra">
        <a>
        <div class="campoPesquisar" >   
           <form action="ServletPesquisa"  method="post"  > 				
    			 <input type="text" name="descricao" placeholder="Nome do produto" >
    			 <input type="hidden" name="tipo_pesquisa" value="pesquisa_nome_produto_usuario">  
   				 <input type="submit"  value="Pesquisar">
   		   </form> 
   		  </div>
        </a>
        
       <div class="menu"> 
        <a href="servletHome" >Home</a>   
        <a href="ServletCarrinho" >      
          <%@ page import="bean.BeanProduto" %>
          <%@ page import="java.util.ArrayList " %>
          <%@ page import="java.util.List" %>
          <%        
            HttpServletRequest req = (HttpServletRequest) request;
            session = req.getSession();
			List<BeanProduto> carrinho = (List<BeanProduto>) session.getAttribute("carrinho");
			
			if (carrinho != null) {
				out.print("Carrinho <span id=\"carrinho\">("+carrinho.size()+")</span>");  
			}else{
				out.print("Carrinho <span id=\"carrinho\">(0)</span>");  
			}	  
         %>          
        </a>
        <a href="ServletPerfilUsuario">Minha conta</a>
         <a href="contato.jsp">Contato</a>
        <a href="ServletPerfilUsuario?acao=sair">Sair</a>
       </div>
    </div>
 
 
<div class="conteudo"> 

 <!-- Titulo da página -->
   <div class="campoPesquisarAdmin">
     <table  style='margin: 0 auto; width: 200px;  text-align:center;' >
				 <tr>
	            	<td  style='vertical-align: middle;'>
		              <h2>Contato</h2>	
		          	</td>
		          	<td>
		          	<img alt="login" src="resources/imagem/call.png" width="50px" height="50px">		          	
		          	</td>     	
	             </tr>            	
	  </table>
</div>
 







</body>
</html>