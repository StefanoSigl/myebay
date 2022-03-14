<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="/public/header.jsp" />
	   
	   <title>Inserisci Nuovo Annuncio</title>
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
				        <h5>Inserisci Nuovo Annuncio</h5> 
				    </div>
				   <div class='card-body'>
		
							<form method="post" action="ExecuteEditAnnuncioServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="testoAnnuncio" class="form-label">Descrizione</label>
									<input type="text" name="testoAnnuncio" value="${edit_annuncio.testoAnnuncio}" id="testoAnnuncio" class="form-control" placeholder="Inserire parole chiave che potrebbero essere nella descrizione " >
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo</label>
									<input type="number" name="prezzo" value="${edit_annuncio.prezzo}" id="prezzo" class="form-control" placeholder="Inserire un minimo di prezzo" >
								</div>
			
								<div class="col-md-6">
									<label for="stato">Stato</label>
								    <select class="form-select" id="stato" name="stato">
								    	<option value="" selected> -- Selezionare una voce -- </option>
								      	<c:forEach items="${stati_attr }" var="statiItem">
								      		<option value="${statiItem}" ${edit_annuncio.getIsAperto() eq statiItem?'selected':''} >${statiItem? 'Attivo':'Chiuso'} </option>
								      	</c:forEach>
								    </select>
								</div>
								
								<div class="col-md-6 form-check">
									<p>Categorie:</p>
									<c:forEach items="${mappaCategorie_attr}" var="categoriaEntry">
									
									<div class="btn-group" role="group" aria-label="Basic checkbox toggle button group">
  										<input type="checkbox" name="categoriaEntry" class="btn-check" value="${categoriaEntry.key.id}" id="categoriaInput-${categoriaEntry.key.id}" ${categoriaEntry.value?'checked':''} autocomplete="off">
  										<label class="btn btn-outline-success" for="categoriaInput-${categoriaEntry.key.id}" >${categoriaEntry.key.descrizione}</label>
  									</div>
										
								  	</c:forEach>
								</div>
									
								<input  type="hidden" name="dataInserimento" value="${edit_annuncio.dataInserimento }">
								<input type="hidden" name="idAnnuncio" value="${edit_annuncio.id}">
								<input type="hidden" name="idUtente" value="${edit_annuncio.utente.id}">
								
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