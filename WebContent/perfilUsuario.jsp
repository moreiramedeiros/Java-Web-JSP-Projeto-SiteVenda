
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ page import="bean.BeanUsuario" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/formulario.css">
<link rel="stylesheet" href="resources/css/tabela.css">
<link rel="stylesheet" href="resources/css/menu.css">
 <link rel="icon" href="resources/imagem/venda.png">
<title>Minha Conta</title>

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
     <table  style='margin: 0 auto; width: 300px;  text-align:center;' >
				 <tr>
	            	<td  style='vertical-align: middle;'>
		              <h2>Minha Conta</h2>	
		          	</td>
		          	<td>
		          	<img alt="login" src="resources/imagem/user.png" width="80px" height="80px">		          	
		          	</td>     	
	             </tr>            	
	  </table>
   </div>
           <!-- Mensagem do servlet -->
	    <center> 
	         <div style="color:red"> ${msgNaoSucesso} </div>
	          <div style="color:green"> ${msgSucesso} </div>
	     </center>  





 <!-- Formulário -->
 
<div class="formulario"> 
 
   <form action="ServletUsuario" method="post" onsubmit="return validarCampos() ? true : false" >


	 <table>
	 <tr>
	 	<td>
	 	    <input type="hidden" id="id" name="id" value="${user.id}"> 
			Login*
			<input type="text" id="login" name="login" placeholder="Login" value="${user.login}"  maxlength="70"> 
			Senha*
			<input type="password" id="senha" name="senha" placeholder="Senha" value="${user.senha}"  maxlength="45"> 
			Nome* 
			<input type="text" id="nome" name="nome" placeholder="Nome" value="${user.nome}"  maxlength="70"> 
			Sexo*  
			<div class="radio">
			<input type="radio" id="masculino" name="sexo" value="masculino"
			    
				<%
			       BeanUsuario  beanUsuario = (BeanUsuario) request.getAttribute("user");
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
			<input type="text" id="cpf" name="cpf" placeholder="CPF" value="${user.cpf}"  maxlength="45">
			Email*
			<input type="text" id="email" name="email" placeholder="Email" value="${user.email}"  maxlength="45"> 
			Telefone
			<input type="text" id="telefone" name="telefone" placeholder="Telefone" value="${user.telefone}"  maxlength="45"> 
			Celular
			<input type="text" id="celular" name="celular" placeholder="Celular" value="${user.celular}"  maxlength="45"> 		
	 	</td>

	 	<td>	 
	 		CEP 
			<input type="text" id="cep" name="cep" placeholder="CEP" value="${user.cep}"  onblur="consultaCep()"    maxlength="45" > 
			Rua 
			<input type="text" id="rua" name="rua" placeholder="Rua" value="${user.rua}"   maxlength="45"> 
			Número 
			<input type="text" id="numero" name="numero" placeholder="Número" value="${user.numero}"  maxlength="45"> 
			Bairro 
			<input type="text" id="bairro" name="bairro" placeholder="Bairro" value="${user.bairro}"   maxlength="45"> 
			Cidade
			<input type="text" id="cidade" name="cidade" placeholder="Cidade" value="${user.cidade}"   maxlength="45"> 
			Estado
			<input type="text" id="estado" name="estado" placeholder="Estado" value="${user.estado}"   maxlength="45"> 
	
			<input type="hidden" id="nivel_acesso" name="nivel_acesso" value="${user.nivel_acesso}"  >  
			<input type="hidden" id="pagina" name="pagina" value="perfilusuario"> 
			
	 	</td>
	 </tr>	 
		</table>
		
		<input type="submit" value="Editar"> 		 
	</form>
	
	
     <a href="ServletPerfilUsuario?acao=exibirContaUsuario"  onclick="return confirm('Deseja cancelar?')"  ><button>Cancelar Edição</button> </a>
     <a href="ServletPerfilUsuario?acao=excluir"  onclick="return confirm('Deseja excluir sua conta?')"  ><button>Excluir Conta</button> </a> 
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
 
 
 
 
 
 
 
 










 <!-- histórico de compras -->
    
    <Center> 
      <div class="campoPesquisarAdmin">
         <h2>Pedidos</h2>	
     </div>
     </Center>

<table id="customers">
  <tr>
    <th>Produto</th>
    <th>Descrição</th>
  </tr>
  
 
  
 <c:forEach items="${join}" var="produto">  
     <tr>
    <td style="width:400px; text-align:center">
     
        <c:if test="${produto.imagem.isEmpty() == false}">
           <img src="<c:out value="${produto.imagem}"/>" width="200px" height="200px">
        </c:if>
        <c:if test="${produto.imagem.isEmpty() == true || produto.imagem == null }">
           <img src="resources/imagem/logo.png" width="200px" height="200px" >
        </c:if>  
         
        <br>
        <br> <span style="font-size:110%">  ${produto.nome} </span>

    </td>
    <td>  <b>ID do item: </b> <span style="color:#5c5954"> #${produto.id_item} </span> <br>       
          <b>ID do carrinho: </b> <span style="color:#5c5954"> #${produto.id_pedido} </span> <br>
           <hr>
          <b>Preço:</b> 
              <span style="color:#5c5954">
                <fmt:formatNumber  type="currency"  value="${produto.preco_unitario}" /> 
             </span><br>    
          <b>Data do pedido: </b> <span style="color:#5c5954"> ${produto.data_pedido}  </span> <br>
          <b>Unidades: </b> <span style="color:#5c5954"> ${produto.unidades}  </span> <br>
          <hr>
          <b>Modo de pagamento: </b> <span style="color:#5c5954"> ${produto.pagamento}  </span><br>
          <b>Parcelamento: </b> <span style="color:#5c5954"> ${produto.parcelamento}  </span><br>
          <b>Status do pagamento: </b> 
          <c:if test="${produto.status_pagamento == 'Aguardando pagamento'}">
             <span style="color:orange"> ${produto.status_pagamento}  </span>
          </c:if>
          <c:if test="${produto.status_pagamento == 'Pagamento confirmado'}">
             <span style="color:green"> ${produto.status_pagamento}  </span>
          </c:if>
          <c:if test="${produto.status_pagamento == 'Pagamento cancelado'}">
             <span style="color:red"> ${produto.status_pagamento}  </span>
          </c:if>
          <br>
          <hr>
          <b>Status de entrega:</b> 
          <c:if test="${produto.status_entrega == 'Produto ainda não entregue'}">
            <span style="color:orange">${produto.status_entrega}</span> <br>   
          </c:if>  
           <c:if test="${produto.status_entrega == 'Produto está à caminho'}">
            <span style="color:orange">${produto.status_entrega}</span> <br>   
          </c:if>  
          <c:if test="${produto.status_entrega == 'Produto entregue'}">
            <span style="color:green">${produto.status_entrega}</span> <br>   
          </c:if> 
          <c:if test="${produto.status_entrega == 'Entrega cancelada'}">
            <span style="color:red">${produto.status_entrega}</span> <br>   
          </c:if>            
          <b>Data de entrega: </b> <span style="color:#5c5954"> ${produto.data_entrega}  </span> <br>    
     </td>
  </tr>
 </c:forEach>
 </table>
 
 
 
</div>

</body>
</html>

 