<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">

<form action="/auth/login" method="POST">
  
  <div class="form-group">
    <label for="userName">UserName</label>
    <input type="text" name="username" class="form-control" placeholder="Enter UserName" id="userName">
  </div>
  

  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  

   <button id="btn-login" class="btn btn-primary">로그인</button>
   <a href="https://kauth.kakao.com/oauth/authorize?client_id=bdc11d8e1ae4c53a58109a1a6218d8f7&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code"><img height ="39px" src="/image/kakao_login_button.png"></a>
</form>



</div>


<%@ include file="../layout/footer.jsp" %>




