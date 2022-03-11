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
		
							<form method="post" action="ExecuteInsertAnnuncioServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="testoAnnuncio" class="form-label">Descrizione</label>
									<input type="text" name="testoAnnuncio" id="testoAnnuncio" class="form-control" placeholder="Inserire parole chiave che potrebbero essere nella descrizione " >
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Prezzo</label>
									<input type="number" name="prezzo" id="prezzo" class="form-control" placeholder="Inserire un minimo di prezzo" >
								</div>
							
								<div class="col-md-6 form-check">
									<p>Categorie:</p>
									<c:forEach items="${mappaCategorie_attr}" var="categoriaEntry">
										<div class="form-check">
											  <input class="form-check-input" name="categoriaEntry" type="checkbox" value="${categoriaEntry.key.id}" id="categoriaInput-${categoriaEntry.key.id}" ${categoriaEntry.value?'checked':''}>
											  <label class="form-check-label" for="categoriaInput-${categoriaEntry.key.id}" >
											    ${categoriaEntry.key.descrizione}
											  </label>
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