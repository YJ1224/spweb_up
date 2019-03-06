	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<center><legend>Running<br><br></legend></center>
<div class="row" style="margin-top: 30px;">
			<c:forEach var="i" items="${imglist}">
			<c:if test="${i.P_STOCK eq 'woman' && i.C_CODE eq 'running'}">
			<c:url value="/" var="home"/>
			<div class="col-sm-4" style="margin-bottom:20px;" align="center">
			<a href="view.do?p=${i.P_NUM}">
			<img class="img-rounded" style="width:50%;height:200px;"
						src="${pageContext.request.contextPath}/img/${i.P_IMG}"></a>
			<div class="font-italic" align="center" style="margin-top: 10px;">

				${i.P_NAME}</div>
				<div class="font-italic" align="center" style="margin-top: 10px;">
				<fmt:formatNumber pattern="###,###,###" value="${i.P_PRICE}" />
				원</div>

			</div>
			</c:if>
		</c:forEach>
	</div>
	<br>
	<br>
	<br>