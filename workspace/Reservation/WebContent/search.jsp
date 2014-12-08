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

<table id="recordtable" border=1 class="table" style="display:none">
	<tr>
		<th>Restaurant Name</th>
		<th>Address</th>
		<th>WebSite</th>
		<th>OpeningTime</th>
		<th>ClosingTime</th>
	</tr>
</table>
<script>

	$(function(){
		$("#search").click(searchRestaurant);
	});
	
	function displayTable()    
    {    
		var element=document.getElementById("recordtable");     
        element.style.display="block";    
    }
	function searchRestaurant() {
		var restaurantName = $("#name").val();
		var location = $("#location").val();
		var date=new Date();
		var dayNumber = date.getDay();
		
		var searchParameters = restaurantName+","+location+","+dayNumber;

		$.ajax({
			url : "http://localhost:8080/Reservation/rest/search/"+searchParameters,
			type:"put",
			dataType: "json",
			contentType: "application/json",
			success:function(response) {
				responseHandler(response);
				
			}
		});
	}
	
	function responseHandler(response)
    {
		var trHTML = '';
        $.each(response, function (i, item) {
            trHTML += '<tr><a href="searchDetails.jsp"><td>'+ item.name +'</td></a><td>' + item.address + '</td><a href="'+ item.website + '"><td>'+ item.website +'</td></a><td>' + item.openingTime + '</td><td>' + item.closingTime + '</td></tr>';
        });
        $('#recordtable').append(trHTML);
        displayTable();
    }

</script>
</div>
</body>
</html>