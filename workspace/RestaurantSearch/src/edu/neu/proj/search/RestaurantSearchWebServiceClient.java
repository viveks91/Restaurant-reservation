package edu.neu.proj.search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class RestaurantSearchWebServiceClient {

	private String urlAPIPlaces = "https://maps.googleapis.com/maps/api/place/textsearch/json?query={NAME} in {LOCATION}&key=AIzaSyCTxX10Hznx4ta5ZvlCS1BFXxDOwNJlQ-s";
	private String urlAPIPlaceDetails;
	//private String urlApi = "https://api.worldweatheronline.com/free/v1/weather.ashx?q={{ZIP}},USA&format=json&num_of_days=2&key=s3uv4fjbaw4pqtp26rh48afd";

	
	public void getRestaurantByNameAndLocation(String name, String location) {
		String urlStr = urlAPIPlaces.replace("{NAME} in {LOCATION}", name
				+ " in " + location);
		System.out.println("New URL:" + urlStr);

		
		//String urlStr1 = urlApi.replace("{{ZIP}}", zip);
		
		System.out.println(urlStr);
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
			
			System.out.println("JSON: "+json);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		RestaurantSearchWebServiceClient client = new RestaurantSearchWebServiceClient();
		client.getRestaurantByNameAndLocation("Chutney", "Seattle");

	}

}
