<%@ include file="./include.jsp"%>

<html>
<head>
   <title>ALL Products Available</title>
</head>

<body>
<h3 align="right">
<a href="${context}/order/shoppingcart">Shopping Cart</a>
<a href="${context}" style="margin-left:9px;margin-right:30px">Logout</a>
<br></h3>

  <h1>ALL Products Available For Now:<br></h1>
  <table BORDER="3" bgcolor="#FAEBD7" cellspacing="10" cellpadding="15">
<tr>
	<th>Product Id</th>
	<th>Name</th>
	<th>Brand</th>
	<th>Price</th>
	<th>Inventory</th>
</tr>
	<c:forEach var="curProd" items="${prodList}">
		<tr>
		   <td>${curProd.id}</td>
		   <td>${curProd.name}</td>
		   <td>${curProd.brand}</td>
		   <td>${curProd.price}</td>
		   <td>${curProd.invtQuantity}</td>
		   <td><a href="${context}/product/prodDetails/${curProd.id}">Details</a></td>
		</tr>
	</c:forEach>
</table>


<br><br>
</body>
</html>