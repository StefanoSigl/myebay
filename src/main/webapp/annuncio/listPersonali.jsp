<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="it" class="h-100" >

	 <head>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="/public/header.jsp" />
	   
	   <title>Annunci attivi</title>
	 </head>
	 
	<body class="d-flex flex-column h-100">
	 
		<!-- Fixed navbar -->
		<jsp:include page="/public/navbar.jsp"></jsp:include>
	 
	
		<!-- Begin page content -->
		<main class="flex-shrink-0">
		  <div class="container">
		  
		  		<div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
				  ${errorMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
		  
		  		<div class='card'>
				    <div class='card-header'>
				        <h5>Annunci attivi</h5> 
				    </div>
				    <div class='card-body'>
				    <a class="btn btn-success " href="PrepareInsertAnnuncioServlet">Add New</a>
				        <div class='table-responsive'>
				            <table class='table table-striped ' >
				                <thead>
				                    <tr>
			                         	<th>Descrizione</th>
				                        <th>Prezzo</th>
				                        <th>Stato</th>
				                        <th>Data di Creazione</th>
				                        <th>Azioni</th>
				                    </tr>
				                </thead>
				                <tbody>
				                	<c:forEach items="${annunciPersonali_list_attribute }" var="annuncioItem">
										<tr>
											<td>${annuncioItem.testoAnnuncio}</td>
											<td>${annuncioItem.prezzo }</td>
											<td>${annuncioItem.getIsAperto()? 'Attivo':'Venduto'}</td>
											<td><fmt:formatDate type = "date" value = "${annuncioItem.dataInserimento }" /></td>
											<td>
												<a class="btn  btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/annuncio/ExecuteVisualizzaAnnuncioPersonaliServlet?idAnnuncio=${annuncioItem.id }">Visualizza</a>
												<c:if test="${annuncioItem.getIsAperto()==true }">
												<a class="btn  btn-sm btn-outline-success  ml-2 mr-2" href="${pageContext.request.contextPath}/annuncio/PrepareEditAnnuncioServlet?idAnnuncio=${annuncioItem.id }">Edit</a>
												</c:if>
												<c:if test="${annuncioItem.getIsAperto()==true }">
												<a class="btn btn-outline-danger btn-sm" href="${pageContext.request.contextPath}/annuncio/PrepareDeleteAnnuncioServlet?idAnnuncio=${annuncioItem.id }">Delete</a>											
												</c:if>
											</td>
										</tr>
									</c:forEach>
				                </tbody>
				            </table>
				        </div>
				   
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