<%@ include file="./include.jsp"%>
<html>
<head>
	<title>Customer Login</title>
	<style>
		.error {
		    font-size: 0.8em;
			color: #ff0000;
		}
	</style>
</head>
<body>
<h3 align="right"><a href="${context}/register" style="margin-right:30px">Register</a></h3>

<h1><fmt:message key="custLoginForm.title" /></h1>

  <form:form action="./login" method="POST" commandName="customer">
  <table>
  	<tr>
  	<td><fmt:message key="name" /></td>
        <td><form:input path="name" />
            <form:errors path="name" cssClass="error"/>
        </td>
    </tr>
    <tr>
  	<td><fmt:message key="addr" /></td>
        <td><form:input path="addr" />
            <form:errors path="addr" cssClass="error"/>
        </td>
    </tr>
 
	<tr>
    <td><input type="submit" value="<fmt:message key="loginBtn" />"> </td>
    </tr>
    </table>
  </form:form>
 
</body>
</html>
