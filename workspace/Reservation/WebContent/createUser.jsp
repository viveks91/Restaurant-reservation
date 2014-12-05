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
<form action="" method="post">
<label>FirstName</label><INPUT TYPE="text" name="FirstName" class="form-control"/>
<label>LastName</label> <INPUT TYPE="text" name="LastName" class="form-control"/>
<label>Address</label> <INPUT TYPE="text" name="Address" class="form-control"/>
<label>UserName</label> <INPUT TYPE="text" name="UserName" class="form-control"/> 
<label>Password</label> <INPUT TYPE="password" name="Password" class="form-control"/> 
<label>PhoneNo</label> <INPUT TYPE="text" name="PhoneNo" class="form-control"/>
<label>Email</label> <INPUT TYPE="text" name="Email" class="form-control"/>

<button class="btn btn-primary btn-block">Create</button>
</div>

</body>
</html>