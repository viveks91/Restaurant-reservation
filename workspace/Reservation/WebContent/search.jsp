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
<INPUT TYPE="text" id="name" placeholder="restaurantName" class="form-control"/>

<INPUT TYPE="text" id="location" placeholder="location" class="form-control"/> 
<button id="search" class="btn btn-primary btn-block">Search</button>

<script>

	$(function(){

		$("#search").click(searchRestaurant);
		
	});
	

	function searchRestaurant() {
		var restaurantName = $("#name").val();
		var location = $("#location").val();
		var searchParameters = restaurantName+","+location;
		alert("Location :"+location);
		alert("RestaurantName :"+restaurantName);
		alert("searchParameters: "+searchParameters);
		
		$.ajax({
			url : "http://localhost:8080/Reservation/rest/search/"+searchParameters,
			type:"put",
			data: JSON.stringify(restaurantName),
			dataType: "json",
			contentType: "application/json",
			success:function(response) {
				//console.log(response);
				alert("response" + JSON.stringify(response));
				//responseHandler(response);
			}
		});
		
	}
	
	
	
</script>
</div>
</body>
</html>