<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="models.User,models.Address,managers.AddressManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.js"></script>
<%
	User user = (User)session.getAttribute("user");
    User viewuser = (User)session.getAttribute("viewuser");
	AddressManager addrmgr = new AddressManager();
	Address addr = addrmgr.findAddressById(viewuser.getAddressId());

%>
<title>
<%= viewuser.getFirstName()%> <%= viewuser.getLastName()%>
</title>

<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body style= "background-color:#F3F3F3;">

<div style= "background-color: #006699;color:white;text-indent:20px;padding-top:5px;padding-bottom:5px;">
  <span style="font-size:150%;font-weight: bold;">Food World</span>
  <span style="float:right;padding-right:20px;padding-top:5px;">
     <button id="logout" class="btn btn-link btn-xs" style="color:#FFF">Logout</button>
  </span>
  <span style="float:right;padding-right:10px;padding-top:5px;font-weight: bold;font-size:12pt;">
     <a href="/Reservation/home.jsp" style="color:#FFF">Home</a>
  </span>
  <span style="float:right;padding-right:20px;font-weight: bold;font-size:150%;">
     <a href="/Reservation/profile.jsp" style="color:#FFF"><%= user.getFirstName() %></a>
  </span>
</div>
  
<div style= "background-color: #83888E;padding-right:10px;padding-top:20px;padding-bottom:5px; width:200px; height:250px;float: left;"> 
<p style="text-indent:20px;font-size:120%;font-weight: bold;"><a href="/Reservation/home.jsp" style="color:#FFF"> Make a reservation</a></p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My reservations</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My favorites</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My reviews</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/editProfile.jsp" style="color:#FFF"> Edit profile</a> </p>
</div>

<div style="margin-left: 0.3cm;float: left;" class="col-lg-8">
	<h1 style="font-size:300%;text-indent: 20px;">
	<%= viewuser.getFirstName()%> <%= viewuser.getLastName()%>
	</h1><hr style="height:1px;background-color:#DDD;">
	<div style="margin-left: 0.1cm;background-color: white;width:400px; float: left;position: relative;clear:both;">
		<p style="text-indent:20px;padding-top:20px;font-weight: bold;font-size:120%;text-decoration: underline;">ABOUT</p>
		<p style="text-indent:23px;padding-top:10px;font-size:100%;">Lives at :
		<%= addr.getStreet()%>,<%= addr.getapt_No()%>,<%= addr.getCity()%>,<%= addr.getState()%></p>
		<p style="text-indent:31px;font-size:100%;"> Phone :
		<%= viewuser.getPhoneNo()%></p>
		<p style="text-indent:20px;padding-down:30px;font-size:100%;">Email ID :
		<%= viewuser.getEmailId()%></p><br>
	
	</div>


</div>

<script>

$(function(){
	
	

	$("#logout").click(logoutHandler);
	
});

function logoutHandler(){
	
	$.ajax({
		url : "http://localhost:8080/Reservation/rest/user/logout",
		type : "post",
	});
	
	location.href= "/Reservation/login.jsp";
}

</script>
</body>
</html>