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

<h1>Product added into your shopping cart... <br><br></h1>
<h2>
<a href="${context}/product/prodList">click here</a> to continue shopping
<br><br>
<a href="${context}/order/placeOrder">click here</a> to place your order
</h2>

</body>
</html>
