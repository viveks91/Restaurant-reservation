package rest;
/**
 * @author Harshad
 *
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;

import models.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// /rest/search
@Path("/search")
public class RestaurantSearchWebServiceClient {
	
	@Resource
	private WebServiceContext context;
	
	public WebServiceContext getContext() {
		return context;
	}

	public void setContext(WebServiceContext context) {
		this.context = context;
	}

	private String urlAPIPlaces = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={NAME}+restaurants+in+{LOCATION}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	private String urlAPIPlaceDetails ="https://maps.googleapis.com/maps/api/place/details/json?placeid={PLACE}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	public static String dayNumber;
	@POST
	@Path("/{searchParameters}")
	@Consumes("application/json")
	public void getParameters(@PathParam("searchParameters") String searchParameters, @Context HttpServletRequest req){
		String[] parts = searchParameters.split(",");
		String restaurantName= parts[0];
		String location = parts[1];
		List<RestaurantSearch> searchResult = null;
		searchResult = getRestaurantByNameAndLocation(restaurantName, location);
		if (searchResult!=null)
		 req.getSession().setAttribute("searchResults", searchResult);
	}
	
	public List<RestaurantSearch> getRestaurantByNameAndLocation(String name, String location) {
		// Correct the input parameters,required for the URL pattern
		name = name.replace(" ", "+");
		location = location.replace(" ", "+");
		
		String urlStr = urlAPIPlaces.replace("{NAME}+restaurants+in+{LOCATION}", name
				+ "+restaurants+in+" + location);
		List<RestaurantSearch> searchResults= new ArrayList<RestaurantSearch>();
		try {
			URL url = new URL(urlStr);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);

			String json = "";
			String line = "";

			while ((line = buffer.readLine()) != null) {
				json += line;
			}
			JSONParser parser = new JSONParser();
			try {
				JSONObject root = (JSONObject) parser.parse(json);
				JSONArray results = (JSONArray) root.get("results");
				if(!results.isEmpty()){
					System.out.println("length"+results.size());
					int size=results.size();
					for (int i=0;i<size;i++)
					{
						RestaurantSearch restaurant = new RestaurantSearch();
						JSONObject restaurantObj = (JSONObject) results.get(i);
						if(restaurantObj!=null){
							String placeId = restaurantObj.get("place_id").toString();
							String restaurantName=restaurantObj.get("name").toString();
							String address=restaurantObj.get("formatted_address").toString();
							String priceLevel;
							try{
								priceLevel=restaurantObj.get("price_level").toString();
							}catch(NullPointerException ne){
								priceLevel="Not Available";
							}
							String rating;
							try{
								rating = restaurantObj.get("rating").toString();
							}catch(NullPointerException ne){
								rating = "Not Available";
							}
							restaurant.setPlaceId(placeId);
							restaurant.setName(restaurantName);
							restaurant.setAddress(address);
							restaurant.setPriceLevel(priceLevel);
							restaurant.setRatings(rating);
							searchResults.add(restaurant);
						}
					}
					return searchResults;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}catch (IndexOutOfBoundsException io) {
				io.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@PUT
	@Path("/{placeId}")
	@Consumes("application/json")
	private RestaurantSearch getPlaceDetails(@PathParam("placeId") String placeId) {
		String placeDetailURL = urlAPIPlaceDetails.replace("{PLACE}", placeId);
		int day = 0;
		RestaurantSearch restaurant = new RestaurantSearch();
		try {
			URL url = new URL(placeDetailURL);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String json = "";
			String line = "";
			int capacity = 50;
			while ((line = buffer.readLine()) != null) {
				json += line;
			}
			System.out.println(json);

			JSONParser parser = new JSONParser();
			try {
				JSONObject root = (JSONObject) parser.parse(json);
				JSONObject restaurantResult = (JSONObject) root.get("result");
				
				if(restaurantResult!=null)
				{
					String address = restaurantResult.get("formatted_address").toString();
					String phoneNo;
					try{
						phoneNo = (String) restaurantResult.get("formatted_phone_number");
					}catch(NullPointerException ne){
						phoneNo = "Not Available";
					}
					String restaurantURL;
					try{
						restaurantURL = (String) restaurantResult.get("website");
					}catch(NullPointerException ne){
						restaurantURL = "Not Available";
					}
					String name = restaurantResult.get("name").toString();
					
					String closeRestaurantTime;
					String openRestaurantTime;
					
					try{
						JSONObject timings = (JSONObject) restaurantResult.get("opening_hours");
						JSONArray periods = (JSONArray) timings.get("periods");
						JSONObject dayTimings = (JSONObject) periods.get(day);
						JSONObject closeTime = (JSONObject) dayTimings.get("close");
						JSONObject openTime = (JSONObject) dayTimings.get("open");
						
						closeRestaurantTime = closeTime.get("time").toString();
						openRestaurantTime = openTime.get("time").toString();
					}catch(NullPointerException ne){
						closeRestaurantTime = "Not Available";
						openRestaurantTime = "Not Available";
					}
					
					String priceLevel;
					try{
						priceLevel=restaurantResult.get("price_level").toString();
					}catch(NullPointerException ne){
						priceLevel="Not Available";
					}
					String rating;
					try{
						rating = restaurantResult.get("rating").toString();
					}catch(NullPointerException ne){
						rating = "Not Available";
					}
						restaurant.setPlaceId(placeId);
						restaurant.setName(name);
						restaurant.setWebsite(restaurantURL);
						restaurant.setAddress(address);
						restaurant.setPhoneNo(phoneNo);
						restaurant.setCapacity(capacity);
						restaurant.setOpeningTime(openRestaurantTime);
						restaurant.setClosingTime(closeRestaurantTime);
						restaurant.setRatings(rating);
						restaurant.setPriceLevel(priceLevel);
						return restaurant;
				}
			} catch (ParseException pe) {
				pe.printStackTrace();
			}
			catch (NullPointerException ne) {
				System.out.println("NE Ignore");
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
