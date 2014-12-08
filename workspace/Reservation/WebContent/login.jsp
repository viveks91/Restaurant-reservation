<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FoodWorld.com</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body style="background-color: #F9F9F9;">
<div class="container" style="width:600px;">

	<h1 style="text-align: center; font-size:500%; padding-top:60px;font-family:verdana;text-shadow: 4px 2.5px #A8A8A8;">FOOD WORLD</h1><br>
	<p style="text-align: center; font-size:150%; font-weight: bold; "> Connecting foodies</p><hr style="height:1px;background-color:#EEE;">
</div>
<div class="container" style="width:500px;">
<!-- <form action="/Reservation/loginAction" method="post">  -->
    <div style="padding-bottom:5px;"><INPUT TYPE="text" id="userName" placeholder="Enter username" class="form-control" maxlength="225"/></div>
    <div style="padding-bottom:7px;"><INPUT TYPE="password" id="password" placeholder="Enter password" class="form-control" maxlength="225"/></div>
	
	<div class="form-group form-group-lg">
    <button class="btn btn-primary col-xs-1" id="login" style="width:227px;">Login</button>
    <div class="col-xs-1">
      <button class="btn btn-primary btn-block" style="width:226px;" onclick="location.href = '/Reservation/createAccount.jsp';">Register</button>
    </div>
  </div>
  <script>

   $(function(){

		$("#login").click(loginHandler);
		
	});

function loginHandler() {
	var newLogin = {
			"userName":$("#userName").val(),
			"password":$("#password").val(),
	};
	handleLogin(newLogin);
	return false;
}

function handleLogin(login) {

	$.ajax({
		url : "http://localhost:8080/Reservation/rest/user/login",
		type : "post",
		data : JSON.stringify(login),
		dataType : "text",
		async : false,
		contentType : "application/json",
		success : function(response) {
			//alert(response.status == "pass");
			  if(response == "pass") location.href = "/Reservation/Home.jsp";
				else alert("Username/Password is incorrect"); 
		}
	});
} 


</script>
  
</div>
<div class="container" style="width:600px;"><hr style="height:1px;background-color:#EEE;"></div>
</body>
</html>