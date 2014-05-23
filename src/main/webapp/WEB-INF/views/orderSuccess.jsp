<%@ include file="./include.jsp"%>
<html>
<head>
	<title>Item Added</title>
	<style>
		.error {
		    font-size: 0.8em;
			color: #ff0000;
		}
	</style>
</head>
<body>

<h1>Your order placed successfully, details as below: </h1>
<h2>Order Number: ${order.id} <br></h2>

  <table BORDER="3" bgcolor="#FAEBD7" cellspacing="10" cellpadding="15">
<tr>
	<th>Name</th>
	<th>Brand</th>
	<th>Price</th>
	<th>Quantities Ordered</th>
</tr>
	<c:forEach var="curProd" items="${prodList}">
		<tr>
		   <td>${curProd.name}</td>
		   <td>${curProd.brand}</td>
		   <td>${curProd.price}</td>
		   <td>${curProd.totalOrders}</td>
		</tr>
	</c:forEach>
	
<tr>
	<td>Subtotal:</td>
	<td>${order.subtotal}</td>
</tr>
<tr>
	<td>Tax:</td>
	<td>${order.tax}</td>
</tr>
<tr>
	<td>Total:</td>
	<td>${order.total}</td>
</tr>
</table>

<br>
<h2><a href="${context}/">Login again</a> to place another order</h2>
<br>
</body>
</html>
