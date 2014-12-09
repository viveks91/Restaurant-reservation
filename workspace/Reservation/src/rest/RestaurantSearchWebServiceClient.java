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

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import models.User;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// /rest/search
@Path("/search")
public class RestaurantSearchWebServiceClient {

	private String urlAPIPlaces = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={NAME}+restaurants+in+{LOCATION}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	private String urlAPIPlaceDetails ="https://maps.googleapis.com/maps/api/place/details/json?placeid={PLACE}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	public static String dayNumber;
	@PUT
	@Path("/{searchParameters}")
	@Consumes("application/json")
	@Produces("application/json")
	public List<RestaurantSearch> getParameters(@PathParam("searchParameters") String searchParameters){
		String[] parts = searchParameters.split(",");
		String restaurantName= parts[0];
		String location = parts[1];
		dayNumber = parts[2];
		List<RestaurantSearch> searchResult = null;
		if(!location.equals(null)){
			searchResult = getRestaurantByNameAndLocation(restaurantName, location);
		}
		return searchResult;
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
						JSONObject firstRestaurant = (JSONObject) results.get(i);
						String restaurantId = firstRestaurant.get("place_id").toString();
						RestaurantSearch restaurant = getPlaceDetails(restaurantId);
						if(restaurant!=null)
						searchResults.add(restaurant);
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

	private RestaurantSearch getPlaceDetails(String restaurantId) {
		String placeDetailURL = urlAPIPlaceDetails.replace("{PLACE}", restaurantId);
		int day = Integer.parseInt(dayNumber);
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
			JSONParser parser = new JSONParser();
			try {
				JSONObject root = (JSONObject) parser.parse(json);
				JSONObject restaurantResults = (JSONObject) root.get("result");
				
				if(restaurantResults!=null)
				{
					String address = restaurantResults.get("formatted_address").toString();
					String phoneNo = restaurantResults.get("formatted_phone_number").toString();
					String restaurantURL = restaurantResults.get("website").toString();
					String name = restaurantResults.get("name").toString();
					
					JSONObject timings = (JSONObject) restaurantResults.get("opening_hours");
					JSONArray periods = (JSONArray) timings.get("periods");
					JSONObject dayTimings = (JSONObject) periods.get(day);
					JSONObject closeTime = (JSONObject) dayTimings.get("close");
					JSONObject openTime = (JSONObject) dayTimings.get("open");
					
					String closeRestaurantTime = closeTime.get("time").toString();
					String openRestaurantTime = openTime.get("time").toString();
					if(!address.isEmpty() & !phoneNo.isEmpty() & !restaurantURL.isEmpty() & !name.isEmpty() & !openRestaurantTime.isEmpty() & !closeRestaurantTime.isEmpty()){
						restaurant.setName(name);
						restaurant.setWebsite(restaurantURL);
						restaurant.setAddress(address);
						restaurant.setPhoneNo(phoneNo);
						restaurant.setCapacity(capacity);
						restaurant.setOpeningTime(openRestaurantTime);
						restaurant.setClosingTime(closeRestaurantTime);
						return restaurant;
					}
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
