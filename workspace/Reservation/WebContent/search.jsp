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
<body style = "background-color:#F3F3F3">
<div class="container">
<h1 style="font-size:300%;text-indent: 30px;">Search a Restaurant</h1>
<hr style="height:1px;background-color:#DDD;">
<div class="container">
 	  <label class="col-sm-3 control-label" for="lg">Restaurant Name</label>
      <INPUT TYPE="text" id="name" placeholder="Search a Restaurant" class="form-control" maxlength="225"/>
	  <label class="col-sm-3 control-label" for="lg">Location Name</label>
      <INPUT TYPE="text" id="location" placeholder="Location Name" class="form-control" maxlength="225"/>
	  <hr style="height:1px;background-color:#DDD;">
      <BUTTON id="search" class="btn btn-success btn-block" >Search</BUTTON>

 	  <label id="label" class="col-sm-3 control-label" style="display:none">Results</label>
	  <hr id="labelLine" style="height:1px;background-color:#DDD;display:none">

<table id="recordtable" border=2 class="table" style="display:none">
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
		var element=document.getElementById("label");     
        element.style.display="block";    

    }
	function clearTable(){
		document.getElementById('recordtable').textContent = "";
	}
	function searchRestaurant() {
		var restaurantName = $("#name").val();
		var location = $("#location").val();
		var date=new Date();
		var dayNumber = date.getDay();
		clearTable();
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
            trHTML += '<tr id="i"><a href="searchDetails.jsp"><td>'+ item.name +'</td></a><td>' + item.address + '</td><a href="'+ item.website + '"><td>'+ item.website +'</td></a><td>' + item.openingTime + '</td><td>' + item.closingTime + '</td></tr>';
        });
        $('#recordtable').append(trHTML);
        displayTable();
    }

</script>
</div>
</body>
</html>