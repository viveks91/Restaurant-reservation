<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,models.User,models.Address,models.Reservation,managers.AddressManager,dao.ReservationDAO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit account</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<%
    User user = (User)session.getAttribute("user");
	ReservationDAO revs = new ReservationDAO();
	Reservation rev = new Reservation();
	List<Object[]> reserve = revs.findDetailsByUserName(user.getUserName());
	
%>

<body style= "background-color:#F3F3F3;">

<div style= "background-color: #006699;color:white;text-indent:20px;padding-top:5px;padding-bottom:5px;">
  <span style="font-size:150%;font-weight: bold;">Food World</span>
  <span style="float:right;padding-right:20px;padding-top:5px;">
     <button id="logout" class="btn btn-link btn-xs" style="color:#FFF">Logout</button>
  </span>
  <span style="float:right;padding-right:10px;padding-top:5px;font-weight: bold;font-size:12pt;">
     <a href="/Restaurant_Reservation/home.jsp" style="color:#FFF">Home</a>
  </span>
  <span style="float:right;padding-right:20px;font-weight: bold;font-size:150%;">
     <a href="/Restaurant_Reservation/profile.jsp" style="color:#FFF"><%= user.getFirstName() %></a>
  </span>
</div>
  
<div style= "background-color: #83888E;padding-right:10px;padding-top:20px;padding-bottom:5px; width:200px; height:250px;float: left;"> 
<p style="text-indent:20px;font-size:120%;font-weight: bold;"><a href="/Restaurant_Reservation/home.jsp" style="color:#FFF"> Make a reservation</a></p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/reservation.jsp" style="color:#FFF"> My reservations</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/favorites.jsp" style="color:#FFF"> My favorites</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/home.jsp" style="color:#FFF"> My reviews</a> </p>
<p style="text-indent:20px;font-size:120%;font-weight: bold;padding-top:5px;"> <a href="/Restaurant_Reservation/editProfile.jsp" style="color:#FFF"> Edit profile</a> </p>
</div>
<div style="margin-left: 0.7cm;float: left;" class="col-lg-6">
<h1 style="font-size:150%;text-indent: 30px;">My Reservations</h1><hr style="height:1px;background-color:#DDD;">

<div style="margin-left: 0.3cm;float: left;" class="col-lg-8">
	<h1 style="font-size:300%;text-indent: 20px;">
	<%= user.getFirstName()%> <%= user.getLastName()%>
	</h1><hr style="height:1px;background-color:#DDD;">
	<div style="margin-left: 0.1cm;background-color: white;width:400px; float: left;position: relative;clear:both;">
		<p style="text-indent:20px;padding-top:20px;font-weight: bold;font-size:120%;text-decoration: underline;">
		<%
			for(int i=0;i< reserve.size();i++)
				{
				
				Object[] obj = reserve.get(i);
				String restName = (String) obj[0];
				int ppl = (Integer) obj[1];
			    String time = (String) obj[2];
			    Date date = (Date) obj[3];
			    
			     String dateString = ""+(date.getMonth()+1)+"-"+date.getDate()+"-"+(date.getYear()+1900) ;
			     System.out.println(dateString);
			    
			    //System.out.println("f" + restaurantid);
		%>
  		<div style="margin-left: 0.1cm;background-color: white;width:400px; float: left;position: relative;clear:both;">
		<p style="text-indent:20px;padding-top:20px;font-weight: bold;font-size:120%;text-decoration: underline;"></p>
	
		<p style="text-indent:23px;line-height:0px;padding-top:0px;font-size:170%;"><%= restName%></p>
		<p style="text-indent:23px;line-height:20px;padding-top:0px;font-size:100%;">No.of people: <%= ppl%></p>
		<p style="text-indent:23px;line-height:10px;padding-top:0px;font-size:100%;">Date: <%= dateString%></p>
		<p style="text-indent:23px;line-height:10px;padding-top:0px;font-size:100%;">Time: <%= time%></p>
		<button class="btn btn-warning col-xs-5" id="fetchit" style="width:200px;">Delete Reservation</button>
	</div>
		<%
				}
 		 %>
	</div>
</div>
 
<script>

$(function(){
	
	$("#fetchit").click(fetchReservationHandler);
});
function fetchReservationHandler(){
	
 	$.ajax({
		url : "http://localhost:8080/Restaurant_Reservation/rest/user/makereservation/",
		type : "get",
		dataType : "text",
		async : false,
		success : function (response) {
			
			location.href = "/Restaurant_Reservation/makeReservation.jsp";
		}
 	
	}); 
 	
return false;
}



	/*$(function(){

		$("#getFavorites").click(getFavoritesHandler);
		
	});
	
	function getFavoritesHandler() {
		findFavorites($("#userName").val());
	}
	
	
	function findFavorites(userName) {
		//alert("calling findFavorites api for username " + userName);
		
		$.ajax({
			url : "http://localhost:8080/Restaurant_Reservation/rest/user/"+userName,
			type:"get",
			contentType: "application/json",
			success:function(response) {
				
				responseHandler(response);

			}
		});
		
	}
	function responseHandler(response)
    {
		
		alert("response" + JSON.stringify(response));
		var Options = '';
		
		$.each(response, function(i, item) {
			alert("item" +item.name);	
			Options +='<li>'+item.name+'</li>';
			
        });
        $("#favoritesList").append(Options);
    }*/

</script>
</div>

</body>
</html>