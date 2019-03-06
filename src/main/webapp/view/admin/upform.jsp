<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <meta http-equiv="X-UA-Compatible" content="ie=edge"> 
    <title>Document</title> 
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"> 
    <style> 
        /* https://www.w3schools.com/bootstrap/bootstrap_buttons.asp 참고해서 스타일 적용하세요 */ 
        fieldset{width: 600px; margin: auto; padding: 40px; border: 1px solid #ccc; border-radius: 8px; box-shadow: inset 0 1px 1px rgba(0,0,0,.075)} 
        legend{width: auto; border-bottom: 0; margin: 0; font-weight: 700; font-size: 18px;} 
        .form-group:nth-child(1) label{display: block;} 
        .form-group:nth-child(1) input[type=text]{width: 80%; display: inline-block} 

        .form-group:nth-child(3) label{display: block;} 
        .form-group:nth-child(3) input{display: inline-block;  width: 48%} 
        .form-group:nth-child(3) input:first-of-type{margin-right: 3%;} 

        .form-group:nth-child(4) label{display: block;} 
        .form-group:nth-child(4) input{float: left; width: 30%; margin-right: 3%;} 
        .form-group:nth-child(4) select{width: 24%; height: 34px; border-radius:4px;} 
   
        .form-group:nth-child(5) > label{display: block;} 
        .form-group:nth-child(5) .radio_wrap{padding: 6px 4px 3px 4px; text-align: center; border-radius:6px; border: 1px solid #ccc;} 
        .form-group:nth-child(5) .radio_wrap input{margin-right: 4%;} 

        .form-group:nth-child(6) > label:first-of-type{display: block;} 
        .form-group:nth-child(6) .checkbox_wrap {padding: 6px 4px 3px 4px; text-align: center; border-radius:6px; border: 1px solid #ccc; } 
        .form-group:nth-child(6) .checkbox_wrap input{margin-right: 3%;} 

        .form-group:nth-child(7) label{display: block;} 
        .form-group:nth-child(7) input{display: inline-block;  width: 35%} 
        .form-group:nth-child(7) input:first-of-type{margin-right: 3%;} 
        .form-group:nth-child(7) input[type=button]{width: 24%;} 

        .form-group:nth-child(9) textarea {width: 100%; resize: none; border: 1px solid #ccc; border-radius: 8px; box-shadow: inset 0 1px 1px rgba(0,0,0,.075)} 
    </style> 
</head>
<body>
<div class="container"> 
<fieldset> 
<legend>상품등록</legend>
<c:url value="/up" var="upproc"></c:url>
<form action="${upproc}" method="post" enctype="multipart/form-data">
			   <div class="form-group"> 
               <label>
               1차분류 : <select name="c_code">
               <option value="man">남성</option>
               <option value="woman">여성</option>
               </select>
               2차분류 : <select name="c_name">
               <option value="life">라이프스타일</option>
               <option value="running">러닝</option>
               </select>
               </label>
                </div> 
				<div class="form-group"> 
                    <label for="name">상품이름</label> 
                     <textarea rows="1" cols="10" name="p_name" class="form-control" id="p_name">
                    </textarea>
                </div> 
                <div class="form-group"> 
                    <label for="name">상품설명</label>
                    <textarea rows="20" cols="100" name="p_stock" class="form-control" id="p_stock">
                    </textarea> 
                </div>
                <div class="form-group"> 
                    <label for="name">상품가격</label> 
                    <textarea rows="1" cols="10" name="p_price" class="form-control" id="p_price">
                    </textarea>
                </div>
                <div class="form-group"> 
                    <label for="name">수량</label> 
                    <textarea rows="1" cols="10" name="p_des" class="form-control" id="p_des">
                    </textarea>
                </div>
                <div class="form-group"> 
                    <input type="file" name="p_img" required="required" id="p_img"> 
                </div>
                
                 <input type=submit value="상품등록" class="btn btn-primary"> 
                <input type=reset value="취소" class="btn btn-danger">
                 </form>
                 </fieldset>
                 </div>
                 <br><br>