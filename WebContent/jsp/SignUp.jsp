<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.2.3.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-ui.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.validate.js"></script>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath() %>/js/SignUp.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<title>Register</title>
</head>
<body>
<form action="register" method="post" id="registration-Form">
First Name <input type="text" name="firstName">
<br>Last Name <input type="text" name="lastName">
<br>User Name <input type="text" name="userName">
<br>Date of Birth <input type="text" name="dob" id="datepicker">
<br>Phone Number <input type="text" name="phoneNumber">
<br>Email <input type="text" name="email">
<br>Password <input type="password" name="password">
<br>Confirm Password <input type="password" name="confirmPassword">
<br><input type="submit" value="Register">
</form>
<noscript>
    <div id="noscript" style="position: absolute; top: 0; left: 0; background-color: #FFF; z-index: 999; height: 100%; width: 100%; text-align: center; padding-top: 50px;">
    You must enable Javascript in order to use this site.
    </div>
</noscript>
</body>
</html>