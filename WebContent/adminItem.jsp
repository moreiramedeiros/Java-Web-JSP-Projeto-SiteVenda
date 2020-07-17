
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
      <center>
        Usuário: ${nome_usuario} <br>
        Id do Usuário: ${id_usuario} <br>
        Id do pedido: ${id_pedido} <br>
         <h2>Lista de Itens</h2>	 
        </center>
     </div>

 

<!-- Tabela de usuários -->

 
<table id="customers">
  <tr>
    <th>Id do item</th>
    <th>Nome</th>
    <th>Preço unitário </th>
    <th>Unidades</th>
    <th>Status da entrega</th>
    <th>Data da entrega</th>
  </tr>
 
  <c:forEach items="${itens}" var="item">
  <tr>
    <td>${item.id_item}</td>
    <td>${item.nome}</td>
    <td>
      <fmt:formatNumber  type="currency"  value="${item.preco_unitario}" /> 
    </td>   
    <td>${item.unidades}</td>  
    <td> 
         <select id="status_entrega${item.id_item}" name="status_entrega${item.id_item}"  onChange="atualizarStatusEntrega('${item.id_item}');">
         
        <option value="Produto ainda não entregue"
           	<c:if test="${item.status_entrega == 'Produto ainda não entregue'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        > Produto ainda não entregue </option> 
        <option value="Produto está à caminho"
            <c:if test="${item.status_entrega == 'Produto está à caminho'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        >Produto está à caminho</option>
        <option value="Produto entregue"
            <c:if test="${item.status_entrega == 'Produto entregue'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        >Produto entregue</option>
        <option value="Entrega cancelada"
            <c:if test="${item.status_entrega == 'Entrega cancelada'}">
        	  <c:out value=" selected "></c:out>
        	</c:if>
        >Entrega cancelada</option>        
 
     </select>
         	<c:if test="${item.status_entrega == 'Produto ainda não entregue'}">
        	  <div id="cor_status${item.id_item}" style="float:right; background-color:yellow;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if>
            <c:if test="${item.status_entrega == 'Produto está à caminho'}">
        	  <div id="cor_status${item.id_item}" style="float:right; background-color:orange;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if> 
        	<c:if test="${item.status_entrega == 'Produto entregue'}">
        	  <div id="cor_status${item.id_item}" style="float:right; background-color:green;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if> 
        	<c:if test="${item.status_entrega == 'Entrega cancelada'}">
        	  <div id="cor_status${item.id_item}" style="float:right; background-color:red;  width:16px;  height:16px; border-radius: 16px;"></div>
        	</c:if>     
    
    
    </td>
    <td>
    
          <input type="date" id="data_entrega${item.id_item}" value="${item.data_entrega_bd}" onChange="atualizarDataEntrega('${item.id_item}')">
        
    
    </td>
   </tr>
 </c:forEach>
 
 <script type="text/javascript">
 
 function atualizarDataEntrega(id_item){
		var data_entrega = document.getElementById("data_entrega"+id_item).value;
		var acao = "atualizar_data_entrega";
		
			 $.ajax({
			method: "POST",                                        // Método de envio   
			url: "ServletPedido",                              // nome do mapeamento da servlet
			data: {id_item : id_item, data_entrega : data_entrega, acao : acao }          //Passa o valor da variável valorInformado para o parâmetro valorParam
		  })
	      .always(function(response) {                                      //Captar o retorno
	            alert(response);
		  });	
	 }
 
 function atualizarStatusEntrega(id_item){
	var status_entrega= document.getElementById("status_entrega"+id_item).value;
	var acao = "atualizar_status_entrega"; 
	
	 $.ajax({
			method: "POST",                                        // Método de envio   
			url: "ServletPedido",                              // nome do mapeamento da servlet
			data: {id_item : id_item, status_entrega:status_entrega, acao:acao }          //Passa o valor da variável valorInformado para o parâmetro valorParam
		  })
	      .always(function(response) {                                      //Captar o retorno
	                         //Executar ação  
	             
	    	 	if(status_entrega == 'Produto ainda não entregue'){
	    			document.getElementById("cor_status"+id_item).style.backgroundColor = "yellow";
	    		}else if(status_entrega == 'Produto está à caminho'){
	    			document.getElementById("cor_status"+id_item).style.backgroundColor = "orange";
	    		}else if(status_entrega == 'Produto entregue'){
	    			document.getElementById("cor_status"+id_item).style.backgroundColor = "green";
	    		}else if(status_entrega == 'Entrega cancelada'){
	    			document.getElementById("cor_status"+id_item).style.backgroundColor = "red";
	    		}    
	      });
	
	 
 }
 
 </script>
 
</table>


</div>

</body>
</html>

 