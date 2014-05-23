<%@ include file="./include.jsp"%>

<html>
<head>
   <title>Product Details</title>
</head>

<body>
<h3 align="right">
<a href="${context}/product/prodList">Home</a>
<a href="${context}/order/shoppingcart" style="margin-left:9px">Shopping Cart</a>
<a href="${context}" style="margin-left:9px;margin-right:30px">Logout</a>
<br></h3>

  <h1>ALL about this product ...<br></h1>
  <table BORDER="3" bgcolor="#FAEBD7" cellspacing="10" cellpadding="15">
<tr>
	<th>Product Id</th>
	<th>Name</th>
	<th>Brand</th>
	<th>Price</th>
	<th>Inventory</th>
</tr>
		<tr>
		   <td>${product.id}</td>
		   <td>${product.name}</td>
		   <td>${product.brand}</td>
		   <td>${product.price}</td>
		   <td>${product.invtQuantity}</td>
		</tr>

</table>

<br>
  <form:form action="${context}/order/addItem/${product.id}">
  <table BORDER="2" bgcolor="#FAEBD7" cellspacing="10" cellpadding="15">
  	<tr>
  	<td>Quantity to Order:</td>
        <td><input name="numOfProdOrdered" type="text"/>
        </td>
    </tr>
 <br><br>
	<tr>
    <td><input type="submit" value="Add to Cart" /></td>
    </tr>
    </table>
  </form:form>

</body>
</html>