<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.2.3.js"></script>
<script type="text/javascript">
</script>
<title>Login</title>
</head>
<body>
<form action="login" method="post" id="LoginForm">
UserName:<input type="text" name="userName">
<br>
Password:<input type="password"name="password">
<br>
<input type="submit" name="Login">
<br>
<c:if test="${!empty message}">
    <c:out value="${message}"/>
</c:if>
</form>
<form action="signup" method="post">
<input type="submit">
</form>
</body>
</html>