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
<h3 align="right">
<a href="${context}/product/prodList">Home</a>
<a href="${context}" style="margin-left:9px;margin-right:30px">Logout</a>
<br></h3>

<h1>Items in your shopping cart:<br></h1>

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
</table>

<br><br>
<h2>
<a href="${context}/product/prodList">click here</a> to continue shopping
<br><br>
<a href="${context}/order/placeOrder">click here</a> to place your order
</h2>
<br><br>
</body>
</html>
