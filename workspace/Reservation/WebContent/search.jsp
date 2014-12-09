<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="models.Person"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.js"></script>
<%
	Person person = (Person)session.getAttribute("user");
	if (person == null) {
%>
<script>alert("Please login");</script>
<jsp:forward page="/login.jsp" />
<%
	};
%>
<title>Home - FoodWorld</title>
<script type="text/javascript" src="js/jquery.js"></script>
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
     <a href="/Reservation/profile.jsp" style="color:#FFF"><%= person.getFirstName() %></a>
  </span>
</div>
  
<div style= "background-color: #83888E;padding-right:10px;padding-top:20px;padding-bottom:5px; width:200px; height:330px;float:left;"> 
<p style="text-indent:20px;font-size:120%;font-weight: bold;"><a href="/Reservation/search.jsp" style="color:#FFF"> Make a reservation</a></p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My reservations</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My favorites</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My reviews</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/following.jsp" style="color:#FFF"> Following</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/finduser.jsp" style="color:#FFF"> Find a user</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/editprofile.jsp" style="color:#FFF"> Edit profile</a> </p>
</div>
<div style="margin-left: 1.2cm;margin-top: 0.1cm;float: left; width:700px">
<h1 style="font-size:300%;text-indent: 20px;float:left;">Search a restaurant</h1>

<div id="div2" style="margin-left: 3.2cm;margin-top: 0.5cm;float: left; width:100px;display:none;">
<button id="back" class="btn btn-info btn-block" style="height: 1cm; width:5cm;font-size:18px" >Back to search</button><br>
</div>
<hr style="height:1px;background-color:#DDD;clear:both">

<div id="div1" style="display:block;">

   <div style="margin-left: 0.2cm;">
       <INPUT style="font-size:17px;height: 1cm;" TYPE="text" id="name" placeholder="Restaurant name" class="form-control" maxlength="225" style/>
   </div>
   <div style="margin-left: 0.2cm;margin-top: 0.2cm;">
       <INPUT style="font-size:17px;height: 1cm;" TYPE="text" id="location" placeholder="Location or place" class="form-control" maxlength="225" style/>
   </div>
   <div style="margin-left: 0.2cm;margin-top: 0.3cm;">
       <button id="search" class="btn btn-info btn-block" style="height: 1cm;font-size:18px" >Search restaurant</button>
   </div>
	  <hr style="height:1px;background-color:#DDD;">
</div>

<div>
<table id="recordtable" border=2 class="table" style="display:none">
	<tr>
		<th>Restaurant Name</th>
		<th>Address</th>
		<th>WebSite</th>
		<th>OpeningTime</th>
		<th>ClosingTime</th>
	</tr>
</table>
</div>

</div>

<script>

	$(function(){
		$("#search").click(stateChange);
		$("#back").click(stateRevert);
		
	});
	
	function stateChange() {
		var div1 = document.getElementById('div1');
		div1.style.display = (div1.style.display == "none") ? "block" : "none";
		var div2 = document.getElementById('div2');
		div2.style.display = (div2.style.display == "none") ? "block" : "none";
		searchRestaurant();
	}
	
	function stateRevert() {
		var div1 = document.getElementById('div1');
		div1.style.display = (div1.style.display == "none") ? "block" : "none";
		var div2 = document.getElementById('div2');
		div2.style.display = (div2.style.display == "none") ? "block" : "none";
	}
	
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
            trHTML += '<tr><td><a href="searchDetails.jsp">'+ item.name +'</a></td><td>' + item.address + '</td><td><a href="'+ item.website + '">'+ item.website +'</a></td><td>' + item.openingTime + '</td><td>' + item.closingTime + '</td></tr>';
        });
        $('#recordtable').append(trHTML);
        displayTable();
    }

</script>
</body>
</html>