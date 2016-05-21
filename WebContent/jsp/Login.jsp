<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.2.3.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>
<script type="text/javascript">
$.ready(function(){
	$("#login-Form").validate({
		rules:{
			userName:{
				required:true
			},
			password:{
				required:true
			}	
		},
		message:{
			userName:{
				required:"Please enter a username"
			},
			password:{
				required:"Please enter a password"
			}
		},
		submitHandler: function(form) {
	          form.submit();
	      }
	});
	
});
</script>
<title>Login</title>
</head>
<body>
<form action="login" method="post" id="login-Form">
UserName:<input type="text" name="userName">
<br>
Password:<input type="password"name="password">
<br>
<input type="submit" value="Login">
&nbsp;&nbsp;&nbsp;
<c:if test="${!empty message}">
    <c:out value="${message}"/>
</c:if>
</form>
<div><a href="signup">Sign-Up</a></div>
</body>
</html>