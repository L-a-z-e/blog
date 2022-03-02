<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp" %>

<div class="container">

<form action="/action_page.php">
  
  <div class="form-group">
    <label for="userName">UserName</label>
    <input type="text" class="form-control" placeholder="Enter UserName" id="userName">
  </div>
  
  <div class="form-group">
    <label for="email">Email</label>
    <input type="email" class="form-control" placeholder="Enter email" id="email">
  </div>
  
  <div class="form-group">
    <label for="password">Password:</label>
    <input type="password" class="form-control" placeholder="Enter password" id="password">
  </div>
  
  
  <button type="submit" class="btn btn-primary">가입하기</button>
</form>



</div>

<%@ include file="../layout/footer.jsp" %>



