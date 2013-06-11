package com.example.hotelapp;


import java.util.ArrayList;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


public class HotelArrayAdapter extends ArrayAdapter<Hotel> {
	
	private static final String TAG_LOG = "HotelArrayAdapter";
	private Context context;
	private ImageView hThumbIV;
	private TextView  hNameTV;
	private TextView  hSATV;
	private List<Hotel> hotels = new ArrayList<Hotel>();
	private DrawableManager imageManager = new DrawableManager();
	public HotelArrayAdapter(Context context, int textViewResourceId,
			List<Hotel> objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.hotels = objects;
	}

	public int getCount() {
		return this.hotels.size();
	}

	public Hotel getItem(int index) {
		return this.hotels.get(index);
	}
	
	
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(TAG_LOG, "Starting  Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.hotel_item, parent, false);
			Log.d(TAG_LOG, "Successfully completed  Row Inflation!");
		}

		// Get item
		Hotel hotel = getItem(position);
		hThumbIV = (ImageView) row.findViewById(R.id.hotel_thumb);
		hNameTV = (TextView) row.findViewById(R.id.hotel_name);
		hSATV = (TextView) row.findViewById(R.id.hotel_street_address);

		hNameTV.setText(hotel.name);
		hSATV.setText(hotel.street_address);
		if(hotel.thumbnail_url != null){
			this.imageManager.fetchDrawableOnThread(hotel.thumbnail_url, hThumbIV);
		}
		

		return row;
	}

	
	

}
