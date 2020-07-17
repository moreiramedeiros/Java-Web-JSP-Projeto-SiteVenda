
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ page import="bean.BeanUsuario" %>
     <%@ page import="bean.BeanJoin" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/formulario.css">
<link rel="stylesheet" href="resources/css/tabela.css">
<link rel="stylesheet" href="resources/css/menu.css">
 <link rel="icon" href="resources/imagem/venda.png">
 <script type="text/javascript" src="resources/javascript/jquery-3.5.1.js"></script>
<title>Administração de Pedidos</title>

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



<!-- Pesquisar -->

      <div class="campoPesquisarAdmin">
        <center> <h2>Lista de Pedidos</h2>	 </center>
     </div>

<!-- Pesquisar nome do usuario -->

<div class="campoPesquisarAdmin" >
	<center>
		<form action="ServletPesquisa"  method="post"  > 
  	 	 Nome: 
		<input type="text" id="descricao" name="descricao" placeholder="nome do usuário"> 
		<input type="hidden" id="tipo_pesquisa" name="tipo_pesquisa" value="pesquisa_nome_usuario_pedido"> 
		<input type="submit" value="Pesquisar"> 
		</form>
	</center>
</div>


<!-- Tabela de usuários -->

 
<table id="customers">
  <tr>
    <th>Id do usuario </th>
    <th>Nome do usuario</th>
    <th>Id pedido </th>
    <th>Data do pedido</th>
    <th>Valor total</th>
    <th>Pagamento</th>
    <th>Parcelamento</th>
    <th>Status de pagamento</th>
    <th>Status geral</th>
    <th>Items do pedido</th>
  </tr>
 
  <c:forEach items="${pedidos}" var="pedido">
  <tr>
    <td>${pedido.id_usuario}</td>
    <td>${pedido.nome_usuario}</td>
    <td>${pedido.id_pedido}</td>
    <td>${pedido.data_pedido}</td>
    <td>
       <fmt:formatNumber  type="currency"  value="${pedido.total_preco_pedido}" /> 
    </td>
    <td>${pedido.pagamento}</td>
    <td>${pedido.parcelamento}</td>   
    <td>
   
     <select id="status_pagamento${pedido.id_pedido}" name="status_pagamento${pedido.id_pedido}"  onChange="atualizarStatusPagamento('${pedido.id_pedido}');">
        <option value="Aguardando pagamento"
           	<c:if test="${pedido.status_pagamento == 'Aguardando pagamento'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        >Aguardando pagamento</option> 
        <option value="Pagamento confirmado"
            <c:if test="${pedido.status_pagamento == 'Pagamento confirmado'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        >Pagamento confirmado</option>
        <option value="Pagamento cancelado"
            <c:if test="${pedido.status_pagamento == 'Pagamento cancelado'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        >Pagamento cancelado</option>
     </select>
           	<c:if test="${pedido.status_pagamento == 'Aguardando pagamento'}">
        	  <div id="cor_status${pedido.id_pedido}" style="float:right; background-color:orange;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if>
            <c:if test="${pedido.status_pagamento == 'Pagamento confirmado'}">
        	  <div id="cor_status${pedido.id_pedido}" style="float:right; background-color:green;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if> 
        	<c:if test="${pedido.status_pagamento == 'Pagamento cancelado'}">
        	  <div id="cor_status${pedido.id_pedido}" style="float:right; background-color:red;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if>        
    </td>
     <td>
        ${pedido.status_geral_pedido}
        <c:if test="${pedido.status_geral_pedido == 'Aberto'}">
        	  <div id="cor_status${pedido.id_pedido}" style="float:right; background-color:red;  width:16px;  height:16px; border-radius: 16px;"></div>
        </c:if> 
        <c:if test="${pedido.status_geral_pedido == 'Fechado'}">
        	  <div id="cor_status${pedido.id_pedido}" style="float:right; background-color:green;  width:16px;  height:16px; border-radius: 16px;"></div>
        </c:if>    
      </td>
       <td>
          <a href="ServletPedido?acao=listaritenspedido&id_pedido=${pedido.id_pedido}&id_usuario=${pedido.id_usuario}&nome_usuario=${pedido.nome_usuario}" >Administrar items (${pedido.qtd_itens}) </a>
     </td>
  </tr>
 </c:forEach>
 
 <script type="text/javascript">
 
 function atualizarStatusPagamento(id_pedido){
	var status_pagamento= document.getElementById("status_pagamento"+id_pedido).value;
	var acao = "atualizar_status_pagamento";
	$.ajax({
			method: "POST",                                        // Método de envio   
			url: "ServletPedido",                              // nome do mapeamento da servlet
			data: {id_pedido : id_pedido, status_pagamento:status_pagamento, acao:acao }          //Passa o valor da variável valorInformado para o parâmetro valorParam
		  })
	      .always(function(response) {                                      //Captar o retorno
	                         //Executar ação   
	    		if(status_pagamento == 'Aguardando pagamento'){
	    			document.getElementById("cor_status"+id_pedido).style.backgroundColor = "orange";
	    		}else if(status_pagamento == 'Pagamento confirmado'){
	    			document.getElementById("cor_status"+id_pedido).style.backgroundColor = "green";
	    		}else if(status_pagamento == 'Pagamento cancelado'){
	    			document.getElementById("cor_status"+id_pedido).style.backgroundColor = "red";
	    		}    
	      });
	
	

	
 }
 
 </script>
 
</table>


</div>

</body>
</html>

 