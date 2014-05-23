<%@ include file="./include.jsp"%>
<html>
<head>
	<title>Customer Registration</title>
	<style>
		.error {
		    font-size: 0.8em;
			color: #ff0000;
		}
	</style>
</head>
<body>
<h3 align="right"><a href="${context}" style="margin-right:30px">LogIn</a>
</h3>

<h1><fmt:message key="custRegForm.title" /></h1>
<br>
  <form:form action="./register" method="POST" commandName="customer">
<c:if test="${not empty errorMessage}">
  <p style="font-size: 0.8em;color:#ff0000">${errorMessage}</p>
</c:if>

  <table>
  	<tr>
  	<td><fmt:message key="name" /></td>
        <td><form:input path="name" />
            <form:errors path="name" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="email" /></td>
        <td><form:input path="email" />
            <form:errors path="email" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="phone" /></td>
        <td><form:input path="phone" />
            <form:errors path="phone" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="addr" /></td>
        <td><form:input path="addr" />
            <form:errors path="addr" cssClass="error"/>
        </td>
    </tr>
 	<tr>
  	<td><fmt:message key="state" /></td>
        <td><form:input path="state" />
            <form:errors path="state" cssClass="error"/>
        </td>
    </tr>
	<tr>
    <td>
    <br><input type="submit" value="<fmt:message key="registerBtn" />"> </td>
    </tr>
    </table>
  </form:form>
 
</body>
</html>
