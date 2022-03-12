<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="it" class="h-100" >
	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="/public/header.jsp" />
	   
	   <title>Ricerca</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="/public/navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		
			  
			   <div class='card'>
				    <div class='card-header'>
				        <h5>Cerca Annunci Personali</h5> 
				    </div>
				        <div class='card-body'>
		
							<form method="post" action="${pageContext.request.contextPath}/acquisto/ExecuteAcquistiListUtenteServlet" class="row g-3" >
							
							
								<div class="col-md-6">
									<label for="descrizione" class="form-label">Descrizione</label>
									<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire parole chiave che potrebbero essere nella descrizione " >
								</div>
								
								<div class="col-md-6">
									<label for="prezzo" class="form-label">Spesa</label>
									<input type="number" name="prezzo" id="prezzo" class="form-control" placeholder="Inserire un minimo di prezzo" >
								</div>
							
								<div class="col-md-3">
									<label for="dataDiAcquisto" class="form-label">Data di Acquisto</label>
                        			<input class="form-control" id="dataDiAcquisto" type="date" placeholder="dd/MM/yy"
                            			title="formato : gg/mm/aaaa"  name="dataDiAcquisto"   >
                            	</div>	
								
								
								<div class="col-12">
									<button type="submit" name="submit" value="submit" id="submit" class="btn btn-success">Conferma</button>
									<input class="btn btn-outline-warning" type="reset" value="Ripulisci">
								</div>
								
						</form>
				  </div>     
			    </div> 	
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="/public/footer.jsp" />
	  </body>
</html>