<!doctype html>
<html lang="it">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

	<head>
		<meta charset="utf-8">
		<title>Accedi</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="${pageContext.request.contextPath}/assets/css/signin.css" rel="stylesheet">
	
	</head>
	
	<!-- Fixed navbar -->
	<jsp:include page="/public/navbar.jsp"></jsp:include>	
	<body class="text-center ">
	    
		<main class="form-signin">
		
		  <form  action="${idAnnuncio==null?'LoginServlet':'LoginByAnnuncioServlet'}" method="post" novalidate="novalidate">
		
	  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		 		 ${errorMessage}
			</div>
		  <div class="alert alert-success alert-dismissible fade show  ${successMessage==null?'d-none':'' }" role="alert">
				  ${successMessage}
				  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
				</div>
				<div class="card " style="width: 100%;background-color: #ddfada;">
				  <img src="${pageContext.request.contextPath}/assets/img/ebay.webp" class="card-img-top" alt="...">
				  <div class="card-body">
				    <h5 class="card-title">Please sign in</h5>
				    <p class="card-text"> <div class="form-floating">
		      <input type="text" name="inputUsername" class="form-control" id="inputUsername" placeholder="username">
		      <label for="inputUsername">Email address</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" name="inputPassword" class="form-control" id="inputPassword" placeholder="Password">
		      <label for="inputPassword">Password</label>
		    </div>
		    
		    	<input type="hidden" value="${idAnnuncio}" name="idAnnuncio">
				    <button class="w-100 btn btn-lg btn-success" onclick="changeSpinner()" type="submit">Sign in 
				    
				    </button>
				  </div>
				</div>
		    <p class="mt-5 mb-3 text-muted"> &#10069 Se non sei ancora registrato clicca qui &#10069</p>
		   <a href="${pageContext.request.contextPath}/public/PrepareRegistrationUtenteServlet" class="btn btn-outline-success" style="width:300px;">
		   Registrati</a>
		   <br>
		   <a href="${pageContext.request.contextPath}/" class="btn btn-outline-success" style="width:300px;">
		   Home</a>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		  </form>
		</main>
	
	    
	</body>


</html>