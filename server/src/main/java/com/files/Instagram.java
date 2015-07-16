package com.files;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Instagram {

	private String Address;
	
	public String getLocation(String city) throws Exception {
		
		String url = "http://maps.googleapis.com/maps/api/geocode/json?address="+ city +"&sensor=false";
 
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");

 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
 
		JSONObject location = null;
		if(responseCode == HttpURLConnection.HTTP_OK){
			 BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        String lineJSON="";
		        String per;
		        while ((per = reader.readLine()) != null) {
		        	lineJSON += per;
		        }
		        reader.close();

		        JSONParser parser = new JSONParser();
		        Object obj1 = parser.parse(lineJSON);
		        JSONObject jsonObject = (JSONObject) obj1;
		        
			     JSONArray results= (JSONArray) jsonObject.get("results");
			     JSONObject resultsObj = (JSONObject) results.get(0);
			     
			     setAddress((resultsObj.get("formatted_address").toString()));
			     
			     location = (JSONObject)((JSONObject) resultsObj.get("geometry")).get("location");
		}
		
		return location.get("lat").toString() + "#" + location.get("lng").toString();
 
	}
	
	public String geImages(String latLng, String distance) throws Exception {
		String[] splitLatLng = latLng.split("#");
		String url = "https://api.instagram.com/v1/media/search?access_token=1996613752.1fb234f.0e0c4fc32f4d4de1a724a326b11ecd5e&lat="+ splitLatLng[0] +"&lng="+  splitLatLng[1] +"&distance="+ distance;
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		con.setRequestMethod("GET");
		con.setRequestProperty("Accept", "application/json");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' location request to URL : " + url);
		System.out.println("Response location Code : " + responseCode);
 
		String lineJSON = null;
		if(responseCode == HttpURLConnection.HTTP_OK){
			 BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
		        lineJSON="";
		        String per;
		        while ((per = reader.readLine()) != null) {
		        	lineJSON += per;
		        }
		        reader.close();
		}
		lineJSON = "{\"citylocation\": {\"lat\": " + splitLatLng[0] + ", \"lng\": "+ splitLatLng[1] +"}," + lineJSON.substring(1);
		return lineJSON;
 
	}
	
	public String getAddress() {
		return Address;
	}



	public void setAddress(String address) {
		Address = address;
	}

}
