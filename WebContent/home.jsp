<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/menu.css">
<link rel="stylesheet" href="resources/css/tabela.css">
<link rel="stylesheet" href="resources/css/formulario.css">
<script type="text/javascript" src="resources/javascript/jquery-3.5.1.js"></script>
 <link rel="icon" href="resources/imagem/venda.png">
<title>Meu site de vendas</title>
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
         <%@ page import="bean.BeanUsuario" %>
         <%        
	  	 	 HttpServletRequest req = (HttpServletRequest) request;
	    	 session = req.getSession();
	         BeanUsuario beanUsuario = (BeanUsuario) session.getAttribute("usuario"); 
	      
	        if(beanUsuario!=null){
		       String nivel_acesso = beanUsuario.getNivel_acesso();		            
			     if(nivel_acesso.equalsIgnoreCase("administrador")){
			    	  out.print("<a href=\"ServletProduto\" >Opções do administrador</a>");   
			    }	        	 
	        }
         %>
        <a href="servletHome" >Home</a>   
        <a href="ServletCarrinho" >      
          <%@ page import="bean.BeanProduto" %>
          <%@ page import="java.util.ArrayList " %>
          <%@ page import="java.util.List" %>
          <%        
			req = (HttpServletRequest) request;
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
 
<!--  Título da página e usuário do sistema  -->

<div class="conteudo">
   <div class="campoPesquisarAdmin">
    <center> 
     <h2>Home</h2>	
    <br>
         <%         
	        if(beanUsuario!=null){		  
	        	if(beanUsuario.getNivel_acesso().equals("usuario")){
	        		 out.print("Usuário : "+beanUsuario.getNome());   
	        	}else if(beanUsuario.getNivel_acesso().equals("administrador")){
	        	     out.print("Administrador : "+beanUsuario.getNome());   
	        	}
			 			  	        	 
	        } 
         %>
    </center> 
   </div>

 
   <!-- Pesquisar-->  
    
  
<div class="campoPesquisar" >    

		  <!-- Pesquisar por categoria -->
		  
   		<form action="ServletPesquisa"  method="post" > 	
				  
	 			 <select  name="descricao" >
			       <option value="Todas categorias" > Todas categorias </option>   
			       <option value="Alimento" > Alimento </option>
			       <option value="Automotivo" > Automotivo </option>
			       <option value="Brinquedo" > Brinquedo </option> 	 
			       <option value="Celular" > Celular </option> 
                   <option value="Eletrodoméstico"   > Eletrodomestico </option> 
			       <option value="Game"  > Game </option> 
			       <option value="Informática"  > Informática </option>  
			       <option value="Livro" > Livro </option>  			   
		           <option value="Mídia" > Mídia </option> 	
		           <option value="Móveis" > Móveis </option>   
		           <option value="Roupa" > Roupa </option> 	   	 
		           <option value="Sem categoria" > Sem categoria </option> 		
		              		 	
 				</select>		
			     <input type="hidden"  name="tipo_pesquisa" value="pesquisa_nome_produto_categoria"> 			     
   				 <input type="submit"  value="Pesquisar por categoria">
		 </form>
		 
		 
		 
</div>  



    <!-- Tabela de produtos -->
    
      <div class="campoPesquisarAdmin">
        <center> <h2>${msg}</h2></center>
     </div>
    

<table id="customersHome">
  <tr>
    <th colspan="2" style="text-align:center" >Lista de produto</th>
   
  </tr>
  
 <c:forEach items="${produtos}" var="produto">
  <tr>
    <td style="width:400px; text-align:center">
     
        <c:if test="${produto.imagem.isEmpty() == false}">
           <img src="<c:out value="${produto.imagem}"/>" width="200px" height="200px">
        </c:if>
        <c:if test="${produto.imagem.isEmpty() == true || produto.imagem == null }">
           <img src="resources/imagem/logo.png" width="200px" height="200px" >
        </c:if>  
         
        <br>
        <br> <span style="font-size:130%">  ${produto.nome} </span>
        <br> <b>Preço:</b> <span style="color:red; font-weight:bold; font-size:20px"> 
         <fmt:formatNumber  type="currency"  value="${produto.preco}" />
        </span><br>
        <b>Categoria: </b> 
        <a href="ServletPesquisa?acao=pesquisarCategoria&descricao=${produto.categoria}">
            <span style="color:red"> ${produto.categoria} </span>
         </a>
    </td>
    <td>         
          <b>Descrição: </b> <span style="color:#5c5954"> ${produto.descricao} </span> <br>
          <b>Altura: </b> <span style="color:#5c5954"> ${produto.altura} cm  </span><br>
          <b>Comprimento: </b> <span style="color:#5c5954"> ${produto.comprimento} cm </span><br>
          <b>Largura: </b> <span style="color:#5c5954"> ${produto.largura} cm </span><br>
          <b>Peso: </b> <span style="color:#5c5954"> ${produto.peso} kg </span> <br>
          <b> Quantidade no estoque:</b> <span style="color:#5c5954">${produto.quantidade}</span> <br>       
          <div class="campoTabelaProdutos">
     	    <input type="number"   id="qtd${produto.id}" value="1" min="1" max="${produto.quantidade}" value="0" onkeyup="calculaSubtotal('${produto.id}','${produto.preco}')" onclick="calculaSubtotal('${produto.id}','${produto.preco}')" >
        	<input type="button" onclick="addCarrinho('${produto.id}','${produto.preco}');" value="Adicionar ao Carrinho">
      	 </div>    
      	  <b> Subtotal:</b> 
      	      <span style="color:red"> <span id="subtotal${produto.id}"> 
      	        <fmt:formatNumber  type="currency"  value="${produto.preco}" />
      	      </span>     	          
      	      </span> <br>  
     </td>
  </tr>
 
 </c:forEach> 
 
 </table>
</div>

  <script type="text/javascript">
  	function addCarrinho(id){
  	    var unidades = document.getElementById('qtd'+id).value;
  	    var acao = "addCarrrinho";
  	    
  	
  			
  			$.ajax({
  				method: "POST",                                        // Método de envio   
  				url: "ServletCarrinho",                              // nome do mapeamento da servlet
  				data: {id : id, unidades:unidades , acao:acao }          //Passa o valor da variável valorInformado para o parâmetro valorParam
  			  })
  		      .always(function(response) {                                      //Captar o retorno
  		                         //Executar ação   
  		           var lista = response.split(";");
  		           document.getElementById('carrinho').innerHTML = "("+lista[0]+")";
  		           alert(lista[1])      
  		      });
  		
  	} 
  	
	function calculaSubtotal(id,preco){
	    var unidades = document.getElementById('qtd'+id).value;
	    var subtotal = preco * unidades;
	    var subtotal_formatado = "R$ "+subtotal.toFixed(2);
	    subtotal_formatado = subtotal_formatado.replace(".",",");
		document.getElementById('subtotal'+id).innerHTML = subtotal_formatado;
	}
  	 
  	 
  </script>

  <div class="rodape">
     Categorias
     <ul>
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=1" > Alimento   </li>  </a>
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=2"  > Automotivo  </li>  </a> 
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=3"  > Brinquedo  </li>  </a> 
        <li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=4"  > Celular  </li>  </a>
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=5"   > Eletrodoméstico  </li>  </a>
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=6"   > Game </li>  </a>
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=7"  > Informática  </li>  </a> 			   
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=8"  > Livro  </li>  </a>	   
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=9"  > Mídia  </li>  </a>
	    <li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=10"  > Móveis  </li>  </a> 			   
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=11"  > Roupa  </li>  </a>	    
		<li><a href="ServletPesquisa?acao=pesquisarCategoriaRodape&descricao=12"  > Sem categoria  </li>  </a>
    </ul>
  </div>

 
</body>
</html>

    
    
