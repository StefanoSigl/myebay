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
	
	<jsp:include page="/public/navbar.jsp"></jsp:include>
	<body class="text-center ">
	    
		<main class="form-signin">
		  <form action="RegistrationServlet"  method="post" novalidate="novalidate">
		  
	  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		 		 ${errorMessage}
			</div>
		  <div class="card " style="width: 100%;background-color: #ddfada;">
				  <img src="${pageContext.request.contextPath}/assets/img/ebay.webp" class="card-img-top" alt="...">
				  <div class="card-body">
				   <h1 class="h3 mb-3 fw-normal">Inserisci le tue informazioni</h1>
		
		    <div class="form-floating">
		      <input type="text" name="inputUsername" value="${reg_utente_attr.username}" class="form-control" id="inputUsername" placeholder="username">
		      <label for="inputUsername">Email address/Username</label>
		    </div>
		    <br>
		    <div class="form-floating">
		      <input type="password" name="inputPassword" value="${reg_utente_attr.password}" class="form-control" id="inputPassword" placeholder="Password">
		      <label for="inputPassword">Password</label>
		    </div>
		    <br>
			<div class="form-floating">
		      <input type="text" name="inputNome" value="${reg_utente_attr.nome}" class="form-control" id="inputNome" placeholder="nome">
		      <label for="inputNome">Nome</label>
		    </div>
		    <br>
		    <div class="form-floating">
		      <input type="text" name="inputCognome" value="${reg_utente_attr.cognome}" class="form-control" id="inputCognome" placeholder="cognome">
		      <label for="inputCognome">Cognome</label>
		    </div>
		    <br>
		     <div class="form-floating">
		      <input type="number" name="inputCredito" value="${reg_utente_attr.creditoResiduo}" class="form-control" id="inputCredito" placeholder="credito">
		      <label for="inputCredito">Credito </label>
		    </div>
		     <br>
		    <button class="w-100 btn btn-lg btn-success" type="submit">Confirm registration</button>
				  </div>
				</div>
				
		    <!-- <img class="mb-4" src="${pageContext.request.contextPath}/assets/img/ebay.webp" alt="" width="72" height="57">
		    <h1 class="h3 mb-3 fw-normal">Please sign in</h1>
		
		    <div class="form-floating">
		      <input type="text" name="inputUsername" value="${reg_utente_attr.username}" class="form-control" id="inputUsername" placeholder="username">
		      <label for="inputUsername">Email address/Username</label>
		    </div>
		    <br>
		    <div class="form-floating">
		      <input type="password" name="inputPassword" value="${reg_utente_attr.password}" class="form-control" id="inputPassword" placeholder="Password">
		      <label for="inputPassword">Password</label>
		    </div>
		    <br>
			<div class="form-floating">
		      <input type="text" name="inputNome" value="${reg_utente_attr.nome}" class="form-control" id="inputNome" placeholder="nome">
		      <label for="inputNome">Nome</label>
		    </div>
		    <br>
		    <div class="form-floating">
		      <input type="text" name="inputCognome" value="${reg_utente_attr.cognome}" class="form-control" id="inputCognome" placeholder="cognome">
		      <label for="inputCognome">Cognome</label>
		    </div>
		    <br>
		     <div class="form-floating">
		      <input type="number" name="inputCredito" value="${reg_utente_attr.creditoResiduo}" class="form-control" id="inputCredito" placeholder="credito">
		      <label for="inputCredito">Credito </label>
		    </div>
		     <br>
		    <button class="w-100 btn btn-lg btn-success" type="submit">Confirm registration</button>
		     -->
		    <p class="mt-5 mb-3 text-muted">Sei gia registrato fai il login!</p>
		    <a href="${pageContext.request.contextPath}/public/PrepareLoginUtenteServlet" class="btn btn-outline-success" style="width:300px;">
		   Login</a>
		    <a href="${pageContext.request.contextPath}/" class="btn btn-outline-success" style="width:300px;">
		   Home</a>
		   <br>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017-2021</p>
		  </form>
		</main>
	
	    
	</body>


</html>