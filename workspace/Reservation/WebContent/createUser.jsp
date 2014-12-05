<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New User</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<h1>Create New User</h1>
<INPUT TYPE="text" id="FirstName" placeholder="FirstName" class="form-control"/>
<INPUT TYPE="text" id="LastName" placeholder="LastName" class="form-control"/>
<INPUT TYPE="text" id="Address" placeholder="Address" class="form-control"/>
<INPUT TYPE="text" id="UserName" placeholder="UserName" class="form-control"/> 
<INPUT TYPE="password" id="Password" placeholder="Password" class="form-control"/> 
<INPUT TYPE="text" id="PhoneNo" placeholder="PhoneNo" class="form-control"/>
<INPUT TYPE="text" id="Email" placeholder="Email" class="form-control"/>

<button id="create" class="btn btn-primary btn-block">Create</button>
<script>

	$(function(){

		$("#create").click(createHandler);
		
	});
	
	function createHandler() {
		var newUser = {
				"FirstName":$("#FirstName").val(),
				"LastName":$("#LastName").val(),
				"Address":$("#Address").val(),
				"UserName":$("#UserName").val(),
				"Password":$("#Password").val(),
				"PhoneNo":$("#PhoneNo").val(),
				"Email":$("#Email").val()
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