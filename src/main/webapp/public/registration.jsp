<!doctype html>
<html lang="it">

	<head>
		<meta charset="utf-8">
		<title>Accedi</title>
	
		<!-- Common imports in pages -->
	 	<jsp:include page="./header.jsp" />
	
	
		 <!-- Custom styles for login -->
	    <link href="${pageContext.request.contextPath}/assets/css/signin.css" rel="stylesheet">
	
	</head>
	
	
	<body class="text-center">
	    
		<main class="form-signin">
		  <form action="RegistrationServlet" method="post" novalidate="novalidate">
		  
	  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		 		 ${errorMessage}
			</div>
		  
		    <img class="mb-4" src="${pageContext.request.contextPath}/assets/brand/bootstrap-logo.svg" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		
		    <div class="form-floating">
		      <input type="text" name="inputUsername" class="form-control" id="inputUsername" placeholder="username">
		      <label for="inputUsername">Email address/Username</label>
		    </div>
		    <br>
		    <div class="form-floating">
		      <input type="password" name="inputPassword" class="form-control" id="inputPassword" placeholder="Password">
		      <label for="inputPassword">Password</label>
		    </div>
		    <br>
			<div class="form-floating">
		      <input type="text" name="inputNome" class="form-control" id="inputNome" placeholder="nome">
		      <label for="inputNome">Nome</label>
		    </div>
		    <br>
		    <div class="form-floating">
		      <input type="text" name="inputCognome" class="form-control" id="inputCognome" placeholder="cognome">
		      <label for="inputCognome">Cognome</label>
		    </div>
		    <br>
		     <div class="form-floating">
		      <input type="number" name="inputCredito" class="form-control" id="inputCredito" placeholder="credito">
		      <label for="inputCredito">Credito </label>
		    </div>
		     <br>
		    <button class="w-100 btn btn-lg btn-primary" type="submit">Confirm registration</button>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		  </form>
		</main>
	
	    
	</body>


</html>