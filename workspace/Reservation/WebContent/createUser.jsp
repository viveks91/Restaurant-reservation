<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<h1>Create New User</h1>
<INPUT TYPE="text" id="firstName" placeholder="FirstName" class="form-control"/>
<INPUT TYPE="text" id="lastName" placeholder="LastName" class="form-control"/>
<INPUT TYPE="text" id="addressId" placeholder="Address" class="form-control"/>
<INPUT TYPE="text" id="userName" placeholder="UserName" class="form-control"/> 
<INPUT TYPE="password" id="password" placeholder="Password" class="form-control"/> 
<INPUT TYPE="text" id="phoneNo" placeholder="PhoneNo" class="form-control"/>
<INPUT TYPE="text" id="emailId" placeholder="Email" class="form-control"/>

<button id="create" class="btn btn-primary btn-block">Create</button>
<script>

	$(function(){

		$("#create").click(createHandler);
		
	});
	
	function createHandler() {
		var newUser = {
				"firstName":$("#firstName").val(),
				"lastName":$("#lastName").val(),
				"addressId":$("#addressId").val(),
				"userName":$("#userName").val(),
				"password":$("#password").val(),
				"phoneNo":$("#phoneNo").val(),
				"emailId":$("#emailId").val()
		};
		createUser(newUser);
	}
	
	
	function createUser(user) {
		
		$.ajax({
			url : "http://localhost:8080/Reservation/rest/user",
			type:"post",
			data: JSON.stringify(user),
			dataType: "json",
			contentType: "application/json",
			success:function(response) {
				console.log(response);
			}
		});
		
	}

</script>
</div>

</body>
</html>