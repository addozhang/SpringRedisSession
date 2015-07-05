<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Update Profile</title>
<script type="text/javascript" src="resources/js/jquery-2.1.4.js"></script>
<script type="text/javascript">
$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                /* o[this.name] = [o[this.name]]; */
                o[this.name] = o[this.name] + "," + this.value
            }
            /* o[this.name].push(this.value || ''); */
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};
$(document).ready(function(e){
console.log($("#ajaxform").serializeObject())
	$("#button").click(function(){
		console.log($("#ajaxform").serializeArray())
		$.ajax({
			url: $("#ajaxform").attr("action"),
			type: "POST",
			dataType: "json",
			contentType: "application/json", 
			data: JSON.stringify($("#ajaxform").serializeObject()),
			success: function(data, status){
				
			},
			error: function(){
				
			}
		});
	});
});
</script>
</head>
<body>
<c:if test="${not empty userprofile}">
<form id="ajaxform" action="<c:url value="/updateUserProfile.do"/>" method="POST">
	<input type="hidden" name="id" value="${userprofile.id}">
	Username: <input type="text" name="username" value="${userprofile.username}"><br>
	Password: <input type="text" name="password" value=""><br>
	Email: <input type="input" name="email" value="${userprofile.email}"><br>
	Industry: <label><input type="checkbox" name="industry" value="lighting">灯饰</label>
	<label><input type="checkbox" name="industry" value="dress">服装</label>
	 <label><input type="checkbox" name="industry" value="toy">玩具</label><br>
	 <input type="button" id="button" value="submit">
</form>
</c:if>
</body>
</html>