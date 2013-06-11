package com.example.hotelapp;




import org.json.JSONException;
import org.json.JSONObject;



import android.util.Log;


public class JSONParser {


	static JSONObject jObj = null;
	static String json = "";
	int statusCode = 0;

	// constructor
	public JSONParser() {

	}

	public JSONObject parseFromStr(String jsonText) {

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(jsonText);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
	
	


}