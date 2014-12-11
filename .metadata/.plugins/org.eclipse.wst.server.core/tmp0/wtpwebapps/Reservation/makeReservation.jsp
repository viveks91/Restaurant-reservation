<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="models.User,models.Address, models.Restaurant, managers.RestaurantManager, managers.AddressManager"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>jQuery UI Datepicker - Restrict date range</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.2/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
<script type="text/javascript" src="js/jquery.js"></script>
<script>


$(function() {
	$("#pickupdate").datepicker({ 
	minDate: 0, 
	dateFormat: "mm-dd-yy" }).val()
	   $("#dropoffdate").datepicker({ 
	   	maxDate: "+1M",
	   	dateFormat: "mm-dd-yy" }).val()
	});


</script>
<%
	User user = (User)session.getAttribute("user");
    User viewuser = (User)session.getAttribute("viewuser");
    Restaurant rest = (Restaurant)session.getAttribute("populateRestaurant");
	

%>
<title>

</title>

<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body style = "background-color:#F3F3F3">
<div style= "background-color: #83888E;padding-right:10px;padding-top:20px;padding-bottom:5px; width:200px; height:250px;float: left;"> 
<p style="text-indent:20px;font-size:120%;font-weight: bold;"><a href="/Restaurant_Reservation/home.jsp" style="color:#FFF"> Make a reservation</a></p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/home.jsp" style="color:#FFF"> My reservations</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/favorites.jsp" style="color:#FFF"> My favorites</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/home.jsp" style="color:#FFF"> My reviews</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/editProfile.jsp" style="color:#FFF"> Edit profile</a> </p>
</div>
<div style="margin-left: 0.7cm;float: left;" class="col-lg-6">
<h1 style="font-size:300%;text-indent: 30px;">Make a reservation</h1><hr style="height:1px;background-color:#DDD;">


<form class="form-horizontal" role="form">
  <div class="form-group form-group-lg">
    <label class="col-sm-3 control-label" for="lg">Number of people:</label>
    <div class="col-sm-9">
      <INPUT TYPE="text" id="people_count" placeholder="eg. 5" class="form-control" maxlength="225"/>
    </div>
  </div>
  
  <div class="form-group form-group-lg">
    <label class="col-sm-3 control-label" for="lg">Date and time</label>
    <div class="col-sm-9">
      <INPUT TYPE="text" id="pickupdate" class="form-control" maxlength="225"/>
    </div>
  
 
  
</div>
 <div style="margin-left: 50%;">
         <button class="btn btn-warning col-xs-5" id="update" style="width:232px;">Make Reservation</button>
         
 </div>
  
</form>

</html>