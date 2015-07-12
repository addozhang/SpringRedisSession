<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Update Profile</title>
<script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<c:if test="${not empty userprofile}">
	Username: <span>${userprofile.username}</span>
		<br>
	Email: <span>${userprofile.email}</span>
		<br>
	Industry: <span>${userprofile.industry}</span>
	</c:if>
</body>
</html>