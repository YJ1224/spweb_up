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
<legend>회원가입하기</legend>
<c:url value="/join.do" var="dao"></c:url>
<form action="${dao}" method="post" name="frm" id="frm" class="" onsubmit="return check()">
<div class="form-group"> 
                    <label for="name">이름</label> 
                    <input type="text" class="form-control" size="10" name="username" id="username"> 
                </div> 
                <div class="form-group"> 
                    <label for="name">아이디</label> 
                    <input type="text" class="form-control" size="1" name="userid" id="userid"><br>
                    <input type="hidden" name="reid" size="20">
                    <input type="button" onclick="idCheck()" value="중복확인">
                </div>
                <div class="form-group"> 
                    <label for="name">비밀번호</label> 
                    <input type="text" class="form-control" size="10" name="userpass" id="userpass"> 
                </div> 
                <div class="form-group"> 
                    <label for="name">생년월일</label> 
                    <input type="text" class="form-control" size="10" name="userage" id="userage"><br>
                </div> 
                <div class="form-group"> 
                    <label for="name">이메일</label> 
                    <input type="text" class="form-control" size="10" name="useremail" id="useremail"> 
                </div> 
                <div class="form-group"> 
                    <label for="name">핸드폰번호</label> 
                    <input type="text" class="form-control" size="10" name="phone" id="phone"> 
                </div> 
                <div class="form-group"> 
                    <label for="name">주소</label> 
                    <input type="text" class="form-control" size="10" name="addr" id="addr">  
                </div> 
                <br><br><br>
                <input type=submit value="가입" class="btn btn-primary"> 
                <input type=reset value="취소" class="btn btn-danger"> 
            </form> 
        </fieldset>
        </div> 
        <br>