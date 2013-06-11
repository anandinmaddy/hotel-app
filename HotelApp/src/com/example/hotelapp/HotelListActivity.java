package com.example.hotelapp;

import java.util.List;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;



public class HotelListActivity extends Activity  {
	
	private static final String TAG_LOG = "HotelActivity";
	private HotelArrayAdapter mAdapter;
	private AlertDialog.Builder mDialog;
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);
      Log.d(TAG_LOG, "Successfully hotel List!");
      
      mDialog =  new AlertDialog.Builder(this).setTitle("Hotel");
      mDialog.setNegativeButton("Cancel", null);
      
      HotelParser hotelParser = (( HotelParser)getApplicationContext());  
      List<Hotel> hotelList = hotelParser.getList();
		
		// Create a customized ArrayAdapter
	 mAdapter = new HotelArrayAdapter(
				getApplicationContext(), R.layout.hotel_item, hotelList);
      ListView hotelLV = (ListView) findViewById(R.id.HotelListView);
      hotelLV.setAdapter(mAdapter);
      hotelLV.setOnItemClickListener(new OnItemClickListener() {
    	  @Override
          public void onItemClick(AdapterView<?> parent, View view,
              int position, long id) {
              // selected item
    		  Hotel hotel = mAdapter.getItem(position);
    		  mDialog.setItems(new String[]{"Name:" + hotel.name, "Street Address:" + hotel.street_address, "Nightly Rate:" + hotel.nightly_rate}, null);
    		  mDialog.show();
          }

		
		
        });
      
  }
	
	

}
