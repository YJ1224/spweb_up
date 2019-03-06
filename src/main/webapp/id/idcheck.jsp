<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ss.js"></script>
<h2>아이디 중복확인</h2>

<c:url value="/idCheck.do" var="idcheck"/>
<form action="${idcheck}" method="get" name="frm">
아이디 <input type="text" name="userid" value="${param.userid}">
<input type="submit" value="중복체크">
<br>
<c:if test="${param.result == 1 }">
<script type="text/javascript">
	opener.document.frm.userid.value="";
</script>
${param.userid}는 중복된 아이디 입니다.
</c:if>
<c:if test="${param.result == -1}">
${param.userid}는 사용 가능한 아이디입니다.
<input type="button" value="사용" class="cancel" onclick="idok()">
</c:if>
</form>