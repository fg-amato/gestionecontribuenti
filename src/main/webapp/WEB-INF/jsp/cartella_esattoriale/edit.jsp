<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!doctype html>
<html lang="it" class="h-100">
	<head>
		<jsp:include page="../header.jsp" />
		
	    <link rel="stylesheet" href="${pageContext.request.contextPath }/assets/css/jqueryUI/jquery-ui.min.css" />
		<style>
			.ui-autocomplete-loading {
				background: white url("../assets/img/jqueryUI/anim_16x16.gif") right center no-repeat;
			}
			.error_field {
		        color: red; 
		    }
		</style>
		<title>Aggiorna Cartella Esattoriale</title>
	    
	</head>
	<body class="d-flex flex-column h-100">
		<jsp:include page="../navbar.jsp" />
		
		<!-- Begin page content -->
		<main class="flex-shrink-0">
			<div class="container">
		
					<%-- se l'attributo in request ha errori --%>
					<spring:hasBindErrors  name="film_regista_attr">
						<%-- alert errori --%>
						<div class="alert alert-danger " role="alert">
							Attenzione!! Sono presenti errori di validazione
						</div>
					</spring:hasBindErrors>
				
					<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					
					<div class='card'>
					    <div class='card-header'>
					        <h5>Inserisci i dati per l'aggiornamento della cartella_esattoriale esattoriale</h5> 
					    </div>
					    <div class='card-body'>
			
								<form:form method="post" modelAttribute="edit_cartella_esattoriale_attr" action="${pageContext.request.contextPath }/cartella_esattoriale/update" novalidate="novalidate" class="row g-3">
									<form:hidden path="id"/>
								
									<div class="col-md-6">
										<label for="descrizione" class="form-label">Descrizione</label>
										<spring:bind path="descrizione">
											<input type="text" name="descrizione" id="descrizione" class="form-control ${status.error ? 'is-invalid' : ''}" placeholder="Inserire la descrizione" value="${edit_cartella_esattoriale_attr.descrizione }">
										</spring:bind>
										<form:errors  path="descrizione" cssClass="error_field" />
									</div>
										
									<div class="col-md-6">
										<label for="importo" class="form-label">Importo (euro)</label>
										<spring:bind path="importo">
											<input type="number" class="form-control ${status.error ? 'is-invalid' : ''}" name="importo" id="importo" placeholder="Inserire l'importo" value="${edit_cartella_esattoriale_attr.importo }">
										</spring:bind>
										<form:errors  path="importo" cssClass="error_field" />
									</div>
									
									<div class="col-md-6">
									<label for="stato" class="form-label">Stato <span class="text-danger">*</span></label>
								    <spring:bind path="stato">
									    <select class="form-select ${status.error ? 'is-invalid' : ''}" id="stato" name="stato" required>
									    	<option value="" selected> - Selezionare - </option>
									      	<option value="CREATA" ${edit_cartella_esattoriale_attr.stato == 'CREATA'?'selected':''} >CREATA</option>
									      	<option value="IN_VERIFICA" ${edit_cartella_esattoriale_attr.stato == 'IN_VERIFICA'?'selected':''} >IN VERIFICA</option>
									      	<option value="CONCLUSA" ${edit_cartella_esattoriale_attr.stato == 'CONCLUSA'?'selected':''} >CONCLUSA</option>
									        <option value="IN_CONTENZIOSO" ${edit_cartella_esattoriale_attr.stato == 'IN_CONTENZIOSO'?'selected':''} >IN CONTENZIOSO</option>
									    </select>
								    </spring:bind>
								    <form:errors  path="stato" cssClass="error_field" />
								</div>
									
									
									<div class="col-md-6">
										<label for="contribuenteSearchInput" class="form-label">Contribuente:</label>
										<spring:bind path="contribuente">
											<input class="form-control ${status.error ? 'is-invalid' : ''}" type="text" id="contribuenteSearchInput"
												name="contribuenteInput" value="${edit_cartella_esattoriale_attr.contribuente.nome}${empty edit_cartella_esattoriale_attr.contribuente.nome?'':' '}${edit_cartella_esattoriale_attr.contribuente.cognome}">
										</spring:bind>
										<input type="hidden" name="contribuente.id" id="contribuenteId" value="${edit_cartella_esattoriale_attr.contribuente.id}">
										<form:errors  path="contribuente" cssClass="error_field" />
									</div>
								
									<div class="col-12">	
										<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
									</div>
										
									
								</form:form>
								
								<%-- FUNZIONE JQUERY UI PER AUTOCOMPLETE --%>
								<script>
									$("#contribuenteSearchInput").autocomplete({
										 source: function(request, response) {
										        $.ajax({
										            url: "${pageContext.request.contextPath }/contribuente/searchContribuentiAjax",
										            datatype: "json",
										            data: {
										                term: request.term,   
										            },
										            success: function(data) {
										                response($.map(data, function(item) {
										                    return {
											                    label: item.label,
											                    value: item.value
										                    }
										                }))
										            }
										        })
										    },
										//quando seleziono la voce nel campo deve valorizzarsi la descrizione
									    focus: function(event, ui) {
									        $("#contribuenteSearchInput").val(ui.item.label)
									        return false
									    },
									    minLength: 2,
									    //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
									    select: function( event, ui ) {
									    	$('#contribuenteId').val(ui.item.value);
									    	//console.log($('#contribuenteId').val())
									        return false;
									    }
									});
								</script>
								<!-- end script autocomplete -->	
								
					    
						<!-- end card-body -->			   
					    </div>
					<!-- end card -->
					</div>
				<!-- end container -->
				</div>	
		
		<!-- end main -->	
		</main>
		<jsp:include page="../footer.jsp" />
		
	</body>
</html>