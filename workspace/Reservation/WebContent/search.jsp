<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<h1>Search a Restaurant</h1>
<form action="/Restaurant/createUser.jsp" method="post">
<INPUT TYPE="text" id="" name="restaurantName" class="form-control"/> 
<label>Location</label> <INPUT TYPE="text" name="locationName" class="form-control"/>

<button class="btn btn-primary btn-block">Search</button>
</div>


</form>
</body>
</html>