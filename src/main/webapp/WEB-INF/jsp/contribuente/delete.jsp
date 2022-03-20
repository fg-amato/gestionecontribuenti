<!doctype html>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Rimuovi Contribuente</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  
			  
			  	<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Rimuovi contribuente</h5> 
				    </div>
				    <div class='card-body'>
		
		
		
							<form method="post" action="${pageContext.request.contextPath }/contribuente/remove" class="row g-3" novalidate="novalidate">
							
								<div class='card-body'>
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Id:</dt>
							  <dd class="col-sm-9">${delete_contribuente_attr.id}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Nome:</dt>
							  <dd class="col-sm-9">${delete_contribuente_attr.nome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Cognome:</dt>
							  <dd class="col-sm-9">${delete_contribuente_attr.cognome}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Codice Fiscale:</dt>
							  <dd class="col-sm-9">${delete_contribuente_attr.codiceFiscale}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Indirizzo:</dt>
							  <dd class="col-sm-9">${delete_contribuente_attr.indirizzo}</dd>
					    	</dl>
					    	
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Nascita:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${delete_contribuente_attr.dataDiNascita}" /></dd>
					    	</dl>
					    	
					    	
					    
						
					    <!-- end card body -->
					    </div>
								
							<div class='card-footer'>
							
									<input type ="hidden" name= "idContribuente" value = "${delete_contribuente_attr.id}">
								
								
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-outline-danger">Conferma rimozione</button>
		 							<a href="" class='btn btn-outline-secondary' style='width:80px'>
					            		<i class='fa fa-chevron-left'></i> Back
					        		</a>
							  </div>
		
						</form>
  
				   		
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</html>