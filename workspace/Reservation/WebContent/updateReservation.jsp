<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="models.User,models.Address, models.Reservation,  models.Restaurant,managers.RestaurantManager, managers.AddressManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>update reservation</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<%
User user = (User)session.getAttribute("user");
Reservation res = (Reservation)session.getAttribute("reservationDetails");
Restaurant rest = (Restaurant)session.getAttribute("populateRestaurant");
int pathId = (Integer)session.getAttribute("path");
%>

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
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/favorites.jsp" style="color:#FFF"> My favorites</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/home.jsp" style="color:#FFF"> My reviews</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Reservation/editProfile.jsp" style="color:#FFF"> Edit profile</a> </p>
</div>
<div style="margin-left: 0.7cm;float: left;" class="col-lg-6">
<h1 style="font-size:300%;text-indent: 30px;">Update Reservation</h1><hr style="height:1px;background-color:#DDD;">

<form class="form-horizontal" role="form">
<input type="hidden" name="customerTime" value="<%=res.getTime()%>" />
  <div class="form-group form-group-lg">
    <label class="col-sm-3 control-label" for="lg">Number of people:</label>
    <div class="col-sm-9">
      <INPUT TYPE="text" id="people_count" value= "<%=res.getPeople_count()%>" class="form-control" maxlength="225"/>
    </div>
  </div>
  
  <div class="form-group form-group-lg">
    <label class="col-sm-3 control-label" for="lg">Date</label>
    <div class="col-sm-9">
      <INPUT TYPE="date" id="datepicker" value= "<%=res.getDate()%>" class="form-control" maxlength="225"/>
    </div>
  </div>
  
  
  <div class="form-group form-group-lg">
  
 	<label class="col-sm-3 control-label" for="lg">Time</label>
 	
 		<div class="col-sm-9">
 			<select   id = "timepicker" class="form-control" maxlength="225">
				<option value="18:00">06:00 PM - 07:00 PM</option>
				<option value="19:00">07:00 PM - 08:00 PM</option>
				<option value="20:00">08:00 PM - 09:00 PM</option>
				<option value="21:00">09:00 PM - 10:00 PM</option>
				
			</select>
		</div>
 </div>
  
</form>
 <div style="margin-left: 50%;">
         <button class="btn btn-warning col-xs-5" id="updateRev" style="width:232px;">Update Reservation</button>
         
 </div>
  
  <script>
  $(function(){
		alert("helooooooo");
		$("#updateRev").click(updateReservationHandler);
	});
  
  function updateReservationHandler() {
	  
		var addrId = "<%= user.getAddressId()%>";
		var ppCount = $("#people_count").val();
		 alert("ppCount" +ppCount);
		var id = "<%= res.getId() %>";
		var booking = document.getElementById("datepicker").value;
		var currentdate = new Date(); 
		var datewhole = currentdate.getFullYear() + "-" + (currentdate.getMonth()+1) + "-" + currentdate.getDate() ;
	     //alert(currentdate.getDate());
		var hoursvalidate = currentdate.getHours();
		var minutesvalidate = currentdate.getMinutes();
		var time = document.getElementById("timepicker").value;
		if(datewhole == booking && hoursvalidate > 16 && minutesvalidate > 00)
			{
				window.alert("Sorry, booking is open till 4:00 pm only");
			}
		else{
			
		var newReservation = {
				"id": "<%= res.getId() %>",
				"people_count":$("#people_count").val(),
				"date": document.getElementById("datepicker").value,
				"time": document.getElementById("timepicker").value,
				
				};
		
		updateReservation(newReservation);
			
		}
	}
		
	function updateReservation(newReservation) {
		
		$.ajax({
			
			url : "http://localhost:8080/Reservation/rest/user/updateReservation"+newReservation,
			type : "post",
			data : JSON.stringify(newReservation),
			dataType : 'text',
			contentType : "application/json",
			async : false,
			success : function(response) {
				alert("successsssss");
				location.href = "/Reservation/reservation.jsp";
				//return false;
		    }
		
		});
		
	}
	
	
  
  </script>
</div>
</body>

</html>