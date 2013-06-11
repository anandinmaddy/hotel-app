package com.example.hotelapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Application;
import android.util.Log;

public class HotelParser   extends Application{
	private static final String TAG_LOG = "HotelParser";
	private static final String TAG_HOTELS = "hotels";
	private static final String TAG_NAME = "name";
    private static final String TAG_STREET_ADDRESS = "street_address";
    private static final String TAG_THUMNAIL_URL = "thumbnail";
    private static final String TAG_NIGHTLY_RATE = "nightly_rate";
   
	
	private final HashMap<String, String> map;
	private final List<Hotel> hotelList;
	private JSONArray hotels = null;
	
	public HotelParser(){
		this.hotelList = new ArrayList<Hotel>();
		this.map = new HashMap<String, String>();
		
	}
	
	public List<Hotel> getList() {
		return this.hotelList;
	}

	public String getStreetAddress(String country) {
		return (String) this.map.get(country);
	}
	
	public void parse(String jsonText){
		JSONParser jParser = new JSONParser();
		
		JSONObject json = jParser.parseFromStr(jsonText);
		
		try{
			
			
			hotels = json.getJSONArray(TAG_HOTELS);
			 for(int i = 0; i < hotels.length(); i++){
	                JSONObject hotel = hotels.getJSONObject(i);
	                String name = hotel.getString(TAG_NAME);
	            	String street_address = hotel.getString(TAG_STREET_ADDRESS);
	            	String thumbnail_url;
	            	try{
	            		thumbnail_url = hotel.getString(TAG_THUMNAIL_URL);
	            	}catch(JSONException e){
	            		thumbnail_url = null;
		            	Log.d(TAG_LOG, "No thumbnail value");
	            	}
	            	
	            	String nightly_rate = hotel.getString(TAG_NIGHTLY_RATE);
	            	
	            	Hotel h = new Hotel(name, street_address, thumbnail_url, nightly_rate);
	            	hotelList.add(h);
	            	map.put(name, street_address);
	            	Log.d(TAG_LOG, h.toString());
	            	
			 }
		}
	    catch (JSONException e) {
        e.printStackTrace();
	    }
	}
	 
	

}
