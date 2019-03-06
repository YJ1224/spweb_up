<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<style>
.orderInfo {
	border: 5px solid #eee;
	padding: 20px;
	display: none;
}

.orderInfo .inputArea {
	margin: 10px 0;
}

.orderInfo .inputArea label {
	display: inline-block;
	width: 120px;
	margin-right: 10px;
}

.orderInfo .inputArea input {
	font-size: 14px;
	padding: 5px;
}

#userAddr2, #userAddr3 {
	width: 250px;
}

.orderInfo .inputArea:last-child {
	margin-top: 30px;
}

.orderInfo .inputArea button {
	font-size: 20px;
	border: 2px solid #ccc;
	padding: 5px 10px;
	background: #fff;
	margin-right: 20px;
}
ul{
margin: auto;
    padding:0px;margin:0px;list-style:none
}
</style>
<body>

	<div class="container">
		<div class="customer-center__box-header">
			<h2 class="customer-center__box-title">FAQ</h2>
			<p class="customer-center__box-description">자주 찾는 질문에서 궁금한 내용을
				찾아보세요.</p>
			<hr class="customer-center__hr">
		</div>
		<br>
		<div class="customer-center__search-area">
			<div class="customer-center__search">
				<c:url value="/" var="home" />
				<form class="form-inline" action="${home}search.do" method="get">
					<input class="form-control mr-sm-2" type="text"
						placeholder="검색어를 입력하세요" aria-label="search" name="search">
					<button type="submit" class="btn btn-default">
						<span class="glyphicon glyphicon-search"></span> Search
					</button>
				</form>
			</div>
		</div>

		<div class="table-responsive">
			<table class="table">
				<thead>
					<tr>
						<th>#</th>
						<th>제목</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>＊</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${not empty notice}">
						<c:forEach var="n" items="${notice}">
							<tr>
								<td>${n.N_NUM}</td>
								<td>${n.N_TITLE}</td>
								<td>${n.N_NAME}</td>
								<td>${n.N_DATE}</td>
								<td class="orderOpne"><a href="noticeview.do?n=${n.N_NUM}">
										<button type="button" class="btn btn-link">상세보기</button>
								</a></td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${not empty faq}">
						<c:forEach var="n" items="${faq}">
							<tr>
								<td>${n.N_NUM}</td>
								<td>${n.N_TITLE}</td>
								<td>${n.N_NAME}</td>
								<td>${n.N_DATE}</td>
								<td class="orderOpne"><a href="noticeview.do?n=${n.N_NUM}">
										<button type="button" class="btn btn-link">상세보기</button>
								</a></td>
							</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<div style="margin-left:320px;">
			<fmt:parseNumber value="${page.endn}" var="endn" />
         <fmt:parseNumber value="${page.lstbnum}" var="lstbnum" />
         <ul class="pagination justify-content-center">
            <li class="page-item"><a class="page-link"
               href="naticelist.do?pg=1&bEa=${page.bEa}">처음</a></li>
            <c:if test="${(page.stn-1) <= 0}">
               <li class="page-item"><a class="page-link"
                  href="naticelist.do?pg=1&bEa=${page.bEa}">◀</a></li>
            </c:if>
            <c:if test="${(page.stn-1) > 0}">
               <li class="page-item"><a class="page-link"
                  href="naticelist.do?pg=${page.stn-1}&bEa=${page.bEa}">◀</a></li>
            </c:if>
            <c:if test="${ endn>=lstbnum }">
               <c:forEach var="i" begin="${page.stn}" end="${lstbnum}">
                  <li class="page-item"><a class="page-link"
                     href="naticelist.do?pg=${i}&bEa=${page.bEa}">${i}</a></li>
               </c:forEach>
            </c:if>
            <c:if test="${ endn<lstbnum }">
               <c:forEach var="i" begin="${page.stn}" end="${endn}">
                  <li class="page-item"><a class="page-link"
                     href="naticelist.do?pg=${i}&bEa=${page.bEa}">${i}</a></li>
               </c:forEach>
            </c:if>
            <c:if test="${ endn>=lstbnum }">
               <li class="page-item"><a class="page-link"
                  href="naticelist.do?pg=${lstbnum}&bEa=${page.bEa}">▶</a></li>
            </c:if>
            <c:if test="${ endn<lstbnum }">
               <li class="page-item"><a class="page-link"
                  href="naticelist.do?pg=${endn+1}&bEa=${page.bEa}">▶</a></li>
            </c:if>
            <li class="page-item"><a class="page-link"
               href="naticelist.do?pg=${lstbnum}&bEa=${page.bEa}">마지막</a></li>
         </ul>
         </div>
         <center>
		</div>
	</div>
</body>
</html>
