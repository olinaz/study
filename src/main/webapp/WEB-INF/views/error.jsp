<%@ include file="./include.jsp"%>

<html>
<head>
   <title>Error</title>
</head>
<body>
<h3  align="right">
<a href="${context}//product/prodList">Home</a>
<a href="${context}" style="margin-left:9px;margin-right:30px">Logout</a>
</h3>

  <h1>Oops ... <br></h1>
 
<c:if test="${not empty errorMessage}">
  <p style="font-size: 0.8em;color:#ff0000">${errorMessage}</p>
</c:if>

<br><br>
</body>
</html>