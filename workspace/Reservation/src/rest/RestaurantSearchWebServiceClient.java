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
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RestaurantSearchWebServiceClient {

	private String urlAPIPlaces = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={NAME}+in+{LOCATION}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	private String urlAPIPlaceDetails ="https://maps.googleapis.com/maps/api/place/details/json?placeid={PLACE}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	
	public RestaurantSearch getRestaurantByNameAndLocation(String name, String location) {
		String urlStr = urlAPIPlaces.replace("{NAME}+in+{LOCATION}", name
				+ "+in+" + location);
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
				JSONObject firstRestaurant = (JSONObject) results.get(0);
				String restaurantId = firstRestaurant.get("place_id").toString();
				RestaurantSearch restaurant = getPlaceDetails(restaurantId);
				return restaurant;
				
			} catch (ParseException e) {
				e.printStackTrace();
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
		int day = 0;
		RestaurantSearch restaurant = new RestaurantSearch();
		System.out.println("Place detail URL:"+placeDetailURL);
		
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

				restaurant.setName(name);
				restaurant.setWebsite(restaurantURL);
				restaurant.setAddress(address);
				restaurant.setPhoneNo(phoneNo);
				restaurant.setCapacity(capacity);
				restaurant.setOpeningTime(openRestaurantTime);
				restaurant.setClosingTime(closeRestaurantTime);

				return restaurant;
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String name="Chutneys";
		String location = "Seattle";
		RestaurantSearchWebServiceClient client = new RestaurantSearchWebServiceClient();
		RestaurantSearch searchResults = client.getRestaurantByNameAndLocation(name, location);
		
	}

}
