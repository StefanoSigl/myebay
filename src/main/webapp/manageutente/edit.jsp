<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="/public/header.jsp" />
	   
	   <title>Modifica Utente</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="/public/navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica Utente</h5> 
				    </div>
				    <div class='card-body'>
		 
							<form method="post" action="ExecuteEditUtenteServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="nome" class="form-label">Nome </label>
									<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" value="${edit_utente_attr.nome}"  >
								</div>
								
								<div class="col-md-6">
									<label for="cognome" class="form-label">Cognome </label>
									<input type="text" name="cognome" id="cognome" class="form-control" placeholder="Inserire il cognome" value="${edit_utente_attr.cognome}"  >
								</div>
							
								<div class="col-md-6">
									<label for="username" class="form-label">Username </label>
									<input type="text" class="form-control" name="username" id="username" placeholder="Inserire l'username" value="${edit_utente_attr.username}" >
								</div>
								<div class="col-md-6">
									<label for="password" class="form-label">Password </label>
									<input type="password" class="form-control" name="password" id="password" placeholder="Inserire la password" value="${edit_utente_attr.password}" >
								</div>
								<div class="col-md-6">
									<label for="credito" class="form-label">Credito </label>
									<input type="number" class="form-control" name="credito" id="credito" placeholder="Inserire il credito" value="${edit_utente_attr.creditoResiduo}" >
								</div>
								<div class="col-md-6">
									<label for="stato">Stato</label>
								    <select class="form-select" id="stato" name="stato">
								    	<option value="" selected>-Selezionare una voce-</option>
								      	<c:forEach items="${stati_attr}" var="statoItem">
								      		<option value="${statoItem}" ${edit_utente_attr.stato eq statoItem?'selected':''} >${statoItem }</option>
								      	</c:forEach>
								    </select>
								</div>
								
								<input type="hidden" value="${edit_utente_attr.id}" name="idUtenteEdit">
								<input type="hidden" value="${edit_utente_attr.dateCreated}" name="dataCreazioneEdit">
								
								<div class="col-md-6 form-check">
									<p>Ruoli:</p>
									<c:forEach items="${mappaRuoliConSelezionati_attr}" var="ruoloInput">	
									<div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
  										<input type="checkbox" name="ruoloInput" class="btn-check" value="${ruoloInput.key.id}" id="ruoloInput-${ruoloInput.key.id}" ${ruoloInput.value?'checked':'' } autocomplete="off">
  										<label class="btn btn-outline-success" for="ruoloInput-${ruoloInput.key.id}" > ${ruoloInput.key.descrizione}</label>
  									</div>	
								  	</c:forEach>	
                            	
								</div>
								
							<div class="col-12">
							
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
								
								<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
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
			<jsp:include page="/public/footer.jsp" />
	  </body>
</html>