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
 <link rel="icon" href="resources/imagem/venda.png">
<title>Carrinho</title>
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
     <table  style='margin: 0 auto; width: 350px;  text-align:center;' >
				 <tr>
	            	<td  style='vertical-align: middle;'>
		              <h2>Carrinho de compras</h2>	
		          	</td>
		          	<td>
		          	<img alt="login" src="resources/imagem/shop.png" width="80px" height="80px">		          	
		          	</td>     	
	             </tr>            	
	  </table>
   </div>



  <!-- Campo de preço total da compra -->
  
      <div class="campopreco">
       <form action="ServletCompra" method="post"   onsubmit="return validarCampos() ? true : false" >
         Quantidade de produtos: ${totalunidades} <br>
         Total:    <fmt:formatNumber  type="currency"  value="${totalpreco}" />  <br>
         Modo de pagamento
             <select  name="pagamento" id="pagamento" onChange="gerarCampoPagamentoParcelado()">
			    <option value="Indefinido" > [Escolher de pagamento] </option>   
			    <option value="Cartão de crédito" > Cartão de crédito </option>   
			    <option value="Boleto" > Boleto </option>    		 	
 			</select>
 			<div id="campoparcelamento">
 			    <!-- aqui campo select gerado por javascript para pagamento parcelado no cartão -->
 			    <!-- name declarado no codigo html no javascript -> name="parcelamento" -->
 			</div>
 		 <input type="hidden" id="totalpreco" name="totalpreco" value="${totalpreco}">  
 		 <input type="hidden" id="totalunidades" name="totalunidades"  value="${totalunidades}">  
         <input type="submit" value="Finalizar pedido">  
        </form> 
      </div>
      
      
  
   <script type="text/javascript">
   
         <!--  Gerar campo para pagamento parcelado  -->    
          function gerarCampoPagamentoParcelado(){
        	  var pagamento = document.getElementById("pagamento").value;
        	  var  totalpreco = document.getElementById("totalpreco").value;
        	  /* Gerar código html identificador de campo name='vezescartao'*/
        	 if(totalpreco>0){ 
        		  var html="<select  name='parcelamento' >";
        		  if(pagamento == "Cartão de crédito"){        		
        			 var i=0;
        			 var precoParcelado;
        			 var maxparcelas; 
        			 if(totalpreco>=1000){
        				 maxparcelas=12; 
        			 }else if(totalpreco>=500){
        				 maxparcelas=8; 
        			 }else if(totalpreco>=250){
        				 maxparcelas=6; 
        			 }else if(totalpreco>=50){
        				 maxparcelas=3; 
        			 }else {
        				 maxparcelas=1; 
        			 }
        			 
        		 	 for(i=1;i<=maxparcelas;i++){  
        				  precoParcelado = totalpreco/i;
        				  precoParcelado_formatado = "R$ "+precoParcelado.toFixed(2);
        			 	  precoParcelado_formatado = precoParcelado_formatado.replace(".",",");
        			  	   html=html+"<option value='"+i+"' > "+i+" x "+precoParcelado_formatado+"</option> ";
        			  }
        		 	  html=html+"</select>";
        		  
        	      /* Fim Gerar código html caso modo pagamento por cartão seja selecionado */
        		  
        		     document.getElementById('campoparcelamento').innerHTML = html;
        	     }else{
        		    document.getElementById('campoparcelamento').innerHTML = "";
        	     }
        	 }else{
        		 alert("Não há valor para parcelar!");
        	 }   
          }
          
          
          //  Validar compra    
          function validarCampos(){
        	  
        	  var totalunidades = document.getElementById("totalunidades").value;
        	  var pagamento = document.getElementById("pagamento").value;
        	  var  totalpreco = document.getElementById("totalpreco").value;
        	  if(totalunidades<=0){
        		  alert("Não há produtos no carrinho!");
        		  return false;
        	  }else if(totalpreco>0 && pagamento=="Indefinido"){
        		  alert("Escolha uma forma de pagamento!");
        		  return false;
        	  }
        	  
        	  
        	  
        	  
        	  return true;
          }    
          
   </script>
    <!-- carrinho -->
    
   
   
   <table id="customers">
  <tr>
    <th colspan="2" style="text-align:center" >Carrinho de compras</th>
   
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
            <span style="color:red"> ${produto.categoria} </span>      
    </td>
    <td>         
          <b>Descrição: </b> <span style="color:#5c5954"> ${produto.descricao} </span> <br>
          <b>Altura: </b> <span style="color:#5c5954"> ${produto.altura} cm  </span><br>
          <b>Comprimento: </b> <span style="color:#5c5954"> ${produto.comprimento} cm </span><br>
          <b>Largura: </b> <span style="color:#5c5954"> ${produto.largura} cm </span><br>
          <b>Peso: </b> <span style="color:#5c5954"> ${produto.peso} kg </span> <br>
          <b> Unidades:</b> <span style="color:#5c5954">${produto.quantidadeConsumida}</span> <br>  
           <div class="campoTabelaProdutosCarrinho">	   
        	  <a href="ServletCarrinho?acao=remover&id=${produto.id}"> <button>Remover do carrinho</button> </a>
      	  </div>      
      	  <b> Subtotal:</b> 
      	        <fmt:formatNumber  type="currency"  value="${produto.preco * produto.quantidadeConsumida}" />
      	      </span>     	          
      	      </span> <br>  
       </td>
     </tr>
    </c:forEach>  
   </table>
  </div> 
</div>
  
 
</body>
</html>