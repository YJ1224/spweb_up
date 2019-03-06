 <div class="container"> 
<fieldset> 
<legend>로그인하기</legend>
<c:url value="/admin/login" var="login"></c:url>
<form action="${login}" method="post" name="myform" id="myform" class="" onsubmit="return check()">
		<div class="form-group"> 
           <label for="name">아이디</label> 
           <input type="text" class="form-control" size="10" name="userid"> 
        </div> 
        <div class="form-group"> 
           <label for="name">비밀번호</label> 
           <input type="text" class="form-control" size="10" name="userpass"> 
        </div>
       <!--<div class="form-group"> 
           <input type="radio" name="verify" value="0" checked="checked">일반회원
           <input type="radio" name="verify" value="1">관리자
        </div>-->
           <input type=submit value="로그인" class="btn btn-primary"> 
           <input type=button value="회원가입" class="btn btn-danger" onclick="location.href='join.do'">
          </form>
       </fieldset>
    </div>
<br><br>