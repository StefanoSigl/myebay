<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="it" class="h-100" >
	 <head>

	 	<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	 	
	   <title>Visualizza Dettaglio Annuncio</title>
	   
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="./navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  <div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
			  		<div class='card'>
					    <div class='card-header'>
					        <h5>Visualizza dettaglio Annuncio</h5>
					    </div>
					    
					
					    <div class='card-body'>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Descrizione:</dt>
							  <dd class="col-sm-9">${visual_annuncio.testoAnnuncio}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Prezzo:</dt>
							  <dd class="col-sm-9">${visual_annuncio.prezzo}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Propietario Annuncio:</dt>
							  <dd class="col-sm-9">${visual_annuncio.utente.username}</dd>
					    	</dl>
					    	
					    	<dl class="row">
							  <dt class="col-sm-3 text-right">Data di Inserimento:</dt>
							  <dd class="col-sm-9"><fmt:formatDate type = "date" value = "${visual_annuncio.dataInserimento}" /></dd>
					    	</dl>
					    	
				    		<p>
							  <a class="btn btn-primary btn-sm" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
							    Categorie
							  </a>
							</p>
					    	<div class="collapse" id="collapseExample">
					    	<c:forEach items="${visual_annuncio.categorie}" var="categoriaItem" >
							  <div class="card card-body">
							  	<dl class="row">
								  <dt class="col-sm-3 text-right">${categoriaItem.descrizione}</dt>
								  <dd class="col-sm-9"></dd>
							   	</dl>
							 </div>
							</c:forEach>
							</div>
					    		
					    <!-- end card body -->
					    </div>
					    
					    <div class='card-footer'>
					        <a href="ExecuteSearchAnnuncioServlet" class='btn btn-outline-secondary' style='width:80px'>
					            <i class='fa fa-chevron-left'></i>Back
					        </a> 
					        <a href="ExecuteAcquistoServlet?idAnnuncio=${visual_annuncio.id }" class='btn btn-outline-success' style='width:83px'>
					            <i class='fa fa-chevron-left'></i>Compra 
					        </a>
					       
					    </div>
					<!-- end card -->
					</div>	
			  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="./footer.jsp" />
	  </body>
</html>