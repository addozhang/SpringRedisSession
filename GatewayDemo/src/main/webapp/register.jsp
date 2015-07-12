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
	function passwordCheck(){
		if($("#passwordRp").val() != $("#password").val()){
			$("#pwdCheck").text("Not same");
			$("#button").attr('disabled','disabled');
			return false;
		}else{
			$("#pwdCheck").text("");
			$("#button").removeAttr('disabled');
			return true;
		}
	}
	
	$("#passwordRp, #password").change(passwordCheck);
	
	$("#button").click(function(){
		if(!passwordCheck()){
			alert("Two inputed passwords not same!")
			return;
		}
		$.ajax({
			url: $("#ajaxform").attr("action"),
			type: "POST",
			dataType: "json",
			contentType: "application/json", 
			data: JSON.stringify($("#ajaxform").serializeObject()),
			success: function(data, status){
				if(data.ErrorCode != undefined){
					alert(data.Msg);
				}else{
					location="<c:url value="/loadUserProfile.do"/>";
				}
			},
			error: function(){
				
			}
		});
	});
});
</script>
</head>
<body>
<form id="ajaxform" action="<c:url value="/register.do"/>" method="POST">
	Username: <input type="text" name="username" value=""><br>
	Password: <input type="password" id="password" name="password" value=""><br>
	Password Repeat: <input type="password" id="passwordRp" name="passwordRp" value=""><span id="pwdCheck"></span><br>
	Email: <input type="input" name="email" value=""><br>
	Industry: <label><input type="checkbox" name="industry" value="lighting">灯饰</label>
	<label><input type="checkbox" name="industry" value="dress">服装</label>
	 <label><input type="checkbox" name="industry" value="toy">玩具</label><br>
	 <input type="button" disabled id="button" value="submit">
</form>
</body>
</html>