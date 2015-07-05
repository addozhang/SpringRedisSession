<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="j_spring_security_check" method="POST">
	USERNAME: <input type="text" name="username">
	PASSWORD: <input type="password" name="password">
	<input type="Submit" value="Logon"> 
</form>
</body>
</html>