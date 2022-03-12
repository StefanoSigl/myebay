<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header>
  <!-- Fixed navbar -->
 <nav style="background-color:	#800080;" class="navbar navbar-expand-lg navbar-dark " aria-label="Eighth navbar example">
    <div class="container">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarsExample07" aria-controls="navbarsExample07" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExample07">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="dropdown07" data-bs-toggle="dropdown" aria-expanded="false">Dropdown</a>
            <ul class="dropdown-menu" aria-labelledby="dropdown07">
            <c:if test="${userInfo!=null }">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/">Home</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/utente/AreaPersonaleServlet">Area Personale</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/annuncio/PrepareSearchAnnuncioPersonaliServlet">Annunci Personali</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/acquisto/PrepareSearchAcquistiServlet">Acquisti</a></li>
               <c:forEach items="${userInfo.ruoli}" var="ruoliItem">
               <c:if test="${ruoliItem.codice eq 'ROLE_ADMIN'}">
               <li><a class="dropdown-item" href="${pageContext.request.contextPath}/manageutente/PrepareSearchUtenteServlet">Ricerca Utenti</a></li>
               </c:if>
               </c:forEach>
             </c:if>
              <c:if test="${userInfo==null }">
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/">Home</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/public/PrepareLoginUtenteServlet">Login</a></li>
              <li><a class="dropdown-item" href="${pageContext.request.contextPath}/PrepareRegistrazioneUtenteServlet">Registrazione</a></li>
          
             </c:if>
            </ul> 
          </li>   
        </ul>
      </div>
      <c:if test="${userInfo!= null}">
      <div class="col-md-3 text-end">
        <p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })
     <a href="${pageContext.request.contextPath}/public/LogoutServlet">Logout</a></p>
      </div>
      </c:if>
      <c:if test="${userInfo == null}">
      <div class="col-md-3 text-end">
      <p class="navbar-text">Guest ->
     <a href="${pageContext.request.contextPath}/public/PrepareLoginUtenteServlet">Login</a></p>
      </div>
      </c:if>
      
      
    </div>
  </nav>

  
  
</header>