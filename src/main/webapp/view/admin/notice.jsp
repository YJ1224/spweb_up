<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	 <script
   src="${pageContext.request.contextPath}/js/ss.js"></script>
</head>
	
	<div class="container"> 
	<fieldset>
	<legend>공지사항등록</legend>
	<c:url value="/notice.do" var="notice"></c:url>
	<form action="${notice}" method="post" onsubmit="return check()">
		<div class="form-group"> 
            <label for="name">글제목</label> 
            <input type="text" class="form-control" size="10" name="n_title" id="username"> 
        </div>
        <div class="form-froup">
        	<label>글내용</label>
        	<textarea name="n_content" class="form-control" rows="35" cols="80" placeholder="내용을 입력해주세요">
        	</textarea>
        </div>       
        <br><br><br>
        <input type=submit value="등록" class="btn btn-primary"> 
        <input type=reset value="취소" class="btn btn-danger"> 
     </form> 
     </fieldset>
     </div> 
     <br>