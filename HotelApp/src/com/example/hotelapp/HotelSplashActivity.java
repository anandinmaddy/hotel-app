package com.example.hotelapp;


import org.apache.http.client.methods.HttpGet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;


import com.example.hotelapp.DownTask.ResponseCallback;

public class HotelSplashActivity extends Activity  implements ResponseCallback{
	private static String url = "http://s3-ap-northeast-1.amazonaws.com/testhotel/hotels.json";
	private static final String TAG_LOG = "HotelActivity";
		
		private long ms = 0;
		private long splashTime = 5000;
		private boolean splashActive = true;
		

		
		
		@Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.hotel_start);
	        startSplashScreen();
	        startHotelParse();
	        
	        Log.d(TAG_LOG,"On Create end");
	    }
		
		private void startSplashScreen(){
			
			 Thread splashThread = new Thread(){
		    	   public void run(){
		    		   try{
		    			   while(splashActive || ms < splashTime){
		    				   ms = ms + 100;
		    				   sleep(100);
		    				   
		    			   }
		    		   }
		    		   catch(Exception e){
		    			   
		    		   }
		    		   finally{
		    			   Intent intent = new Intent(HotelSplashActivity.this, HotelListActivity.class);
		    			   startActivity(intent);
		    			   HotelSplashActivity.this.finish();	    		  
		    			  }
		    	   }
		    	   
		       };
		      
		       splashThread.start();
			
		}
		
		private void startHotelParse(){
			try{
	            //Simple GET
	            
	            HttpGet searchRequest = new HttpGet(url);
	            DownTask task = new DownTask();
	            task.setResponseCallback(this);
	            task.execute(searchRequest);
	          
	        } catch (Exception e) {
	          Log.d(TAG_LOG, e.getMessage());
	        }
		
		}
		
	    
		@Override
		public void onRequestSuccess(String response) {
	        //Clear progress indicator
			
	        //Process the response data (here we just display it)
			splashActive = false;
	        HotelParser hotelParser = (( HotelParser)getApplicationContext());  
	        hotelParser.parse(response);
	  		
		}
		
		@Override
		public void onRequestError(Exception e) {
	        //Clear progress indicator
	      
	        //Process the response data (here we just display it)
	        Log.d(TAG_LOG, e.getMessage());
		}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.hotel, menu);
        return true;
    }
    
}
