<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<style>
.orderInfo { border:5px solid #eee; padding:20px; display:none; }
.orderInfo .inputArea { margin:10px 0; }
.orderInfo .inputArea label { display:inline-block; width:120px; margin-right:10px; }
.orderInfo .inputArea input { font-size:14px; padding:5px; }
#userAddr2, #userAddr3 { width:250px; }

.orderInfo .inputArea:last-child { margin-top:30px; }
.orderInfo .inputArea button { font-size:20px; border:2px solid #ccc; padding:5px 10px; background:#fff; margin-right:20px;}
</style>
<body>
		<center>
			<legend>주문내역</legend>
		</center>
		
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>주문번호</th>
						<th>주문ID</th>
						<th>수령인</th>
						<th>주소</th>
						<th>가격</th>
						<th>주문날짜</th>
						<th>배송현황</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${orderlist}">
						<tr>
							<td><a href="deliveryview?d=${o.ORDERID}">
							${o.ORDERID}</a></td>
							<td>${o.USERID}</td>
							<td>${o.ORDERREC}</td>
							<td>(${o.USERADDR1})${o.USERADDR2}${o.USERADDR3}</td>
							<td>${o.C_STOCK}</td>
							<td>${o.ORDERDATE}</td>
							<td>${o.DELIVERY}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
</body>
</html>
