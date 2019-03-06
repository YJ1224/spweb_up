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

	<div class="container">
		<center>
			<legend>장바구니</legend>
		</center>
		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>상품사진</th>
						<th>상품명</th>
						<th>단가</th>
						<th>주문수량</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="i" items="${carts}">
						<tr>
							<td><a href="view.do?p=${i.P_NUM}"> <img
									src="${pageContext.request.contextPath}/img/${i.P_IMG}"
									style="width: 100px; height: 100px;">
							</a></td>
							<td>${i.P_NAME}</td>
							<td><fmt:formatNumber pattern="###,###,###" value="${i.P_PRICE}" />원</td>
							<td>${i.C_STOCK}</td>
							<td>
							<c:url value="delete.do" var="cd"></c:url>
							<form action="${cd}" method="post">
							<input type="submit" value="삭제">
							<input type="hidden" name="c_num" value="${i.C_NUM}">
							</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<table>
			<thead>
			<c:forEach var="s" items="${sum}">
				<tr>
					<th>
					<h4>
					총금액 : <fmt:formatNumber pattern="###,###,###" value="${s.MONEY}" />원 
					</h4>
					</th>
				</tr>
			</c:forEach>
			</thead>
			</table>
			<br><br>
			<c:url value="delete2.do" var="cd2" />
			<form action="${cd2}" method="post">
			<button type="submit" class="btn btn-danger">전체삭제</button><br><br>
			</form>
			<div class="orderOpne">
			<button type="button" class="orderOpne_bnt btn btn-success">주문하기</button>
			<script>
			 $(".orderOpne_bnt").click(function(){
			  $(".orderInfo").slideDown();
			  $(".orderOpne_bnt").slideUp();
			 });      
			</script>
			</div>
			<br>
		</div>
	</div>
	<div class="orderInfo">
<c:url value="/cartList" var="order"></c:url>
 <form role="form" method="post" autocomplete="off" action="${order}">
  <c:forEach var="s" items="${sum}">  
  <input type="hidden" name="c_stock" value="${s.MONEY}" />
  </c:forEach>
    
  <div class="inputArea">
   <label for="">수령인</label>
   <input type="text" name="orderrec" id="orderrec" required="required" class="form-control"  placeholder="예)한예지" />
  </div>
  
  <div class="inputArea">
   <label for="orderPhon">수령인 연락처</label>
   <input type="text" name="orderphon" id="orderphon" required="required" class="form-control" placeholder="예)010-1111-2222" />
  </div>
  
  <div class="inputArea">
   <label for="userAddr1">우편번호</label>
   <input type="text" name="useraddr1" id="useraddr1" required="required" class="form-control" placeholder="예)111-111" />
  </div>
  
  <div class="inputArea">
   <label for="userAddr2">시·도/시·군·구</label>
   <input type="text" name="useraddr2" id="useraddr2" required="required" class="form-control" placeholder="예)충청남도 천안시 서북구 성정공원5로 35"/>
  </div>
  
  <div class="inputArea">
   <label for="userAddr3">상세 주소</label>
   <input type="text" name="useraddr3" id="useraddr3" required="required" class="form-control" placeholder="예)6층 휴먼교육센터" />
  </div>
  
  <div class="inputArea">
   <button type="submit" class="order_btn">주문</button>
   <button type="button" class="cancel_btn">취소</button>
   <script>
$(".cancel_btn").click(function(){
 $(".orderInfo").slideUp();
 $(".orderOpne_bnt").slideDown();
});      
</script> 
  </div>
  
 </form> 
</div>
</body>
</html>
